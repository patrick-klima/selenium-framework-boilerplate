package repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that loads Object Repository .yaml files and manage locators
 */
@Slf4j
public class ObjectRepository {

    @AllArgsConstructor
    public static class Element {
        public String by;
        public String locator;
    }

    private static String jsonRepo;

    /**
     * Reads .yaml object repository file which will be used for the test suite.
     * Needs to be initialized once.
     * @param yamlPath Path to the yaml file
     */
    public static void initialize(String yamlPath) {
        try {
            Path path = Path.of(yamlPath).toRealPath();
            String yamlContent = Files.readString(path);
            jsonRepo = convertYamlToJson(yamlContent);
        } catch (IOException e) {
            log.error("Could not read Yaml Object Repository file. Make sure that the file exist.");
            e.printStackTrace();
        }
    }

    private static String convertYamlToJson(String yaml) {
        try {
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            var obj = yamlReader.readValue(yaml, Object.class);

            ObjectMapper jsonWriter = new ObjectMapper();
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonMappingException mappingException) {
            log.error("Unable to map Yaml to JSON. Please check the syntax of the YAML repository file and try again. Detailed report: " + mappingException.getLocalizedMessage());
            Assert.fail();

        } catch (JsonProcessingException processingException) {
            log.error("Unable to process Yaml file. Please check the syntax of the YAML repository file and try again. Detailed report: " + processingException.getLocalizedMessage());
            Assert.fail();
        }

        return "";
    }

    private static By getByFromString(String by, String locator) {
        switch (by) {
            case "xpath":
            case "xp":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "name":
                return By.name(locator);
            case "linkText":
            case "linktext":
            case "lt":
                return By.linkText(locator);
            case "css":
            case "cssSelector":
                return By.cssSelector(locator);
            case "className":
            case "class":
            case "cls":
            case "c":
                return By.className(locator);
            case "partialLinkText":
            case "partiallinktext":
            case "pll":
                return By.partialLinkText(locator);
        }

        return By.xpath(locator);
    }

    private static Element parseElementFromValue(String value) {
        final Pattern pattern = Pattern.compile("^(?<by>.+)\\:(?<loc>.+$)");
        final Matcher matcher = pattern.matcher(value);
        matcher.find();

        if (matcher.matches()) {
            String by = matcher.group("by");
            String loc = matcher.group("loc");

            return new Element(by, loc);
        } else {
            log.error("Could not parse by or location from " + value + ". Invalid syntax? Syntax should be: <by>:<locator>. Example: xpath:*");
            Assert.fail();
            return null;
        }

    }

    /**
     * Returns the corresponding by locator defined in the .yaml file
     * @param elementQuery Path to locator defined in the .yaml file
     * @return By Locator
     */
    public static By getLocator(String elementQuery) {
        try {
            elementQuery = elementQuery.replace('/', '.');

            String elementPath = "$..%s".formatted(elementQuery);

            JSONArray jsonArrayResult = JsonPath.parse(jsonRepo).read(elementPath);
            String loc = jsonArrayResult.get(0).toString();
            Element element = parseElementFromValue(loc);

            return getByFromString(element.by, element.locator);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Locator '%s' could not be found in the Object Repository!".formatted(elementQuery));
            Assert.fail();
        }

        return null;
    }
}
