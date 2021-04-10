package com.facebook.ultralight.names;

public class UltralightNames {
    public static String decodeFromJavaName(String type) {
        return type.replace("$x5D", "]").replace("$x5B", "[").replace("$x3E", ">").replace("$x3C", "<").replace("$x2C", ",").replace("_", ".").replace("$x5F", "_").replace("$x24", "$");
    }
}
