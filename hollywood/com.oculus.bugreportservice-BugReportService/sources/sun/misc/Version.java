package sun.misc;

public class Version {
    public static void initSystemProperties() {
        System.setUnchangeableSystemProperty("java.version", "0");
        System.setUnchangeableSystemProperty("java.runtime.version", "0.9");
        System.setUnchangeableSystemProperty("java.runtime.name", "Android Runtime");
    }
}
