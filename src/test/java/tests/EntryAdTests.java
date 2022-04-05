package tests;

import bases.BaseTest;
import org.testng.annotations.Test;
import pages.AdPage;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class EntryAdTests extends BaseTest {
    @Test(testName = "is ad visible", groups = {"smoke"})
    public void isAdVisible() {
        HomePage page = new HomePage();
        AdPage adPage = page.goToAd();

        assertThat(adPage.adExist()).isTrue();
    }
}
