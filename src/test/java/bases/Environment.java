package bases;

public class Environment {
    public static boolean isDebug() {
        return System.getProperty("debug") != null;
    }
}
