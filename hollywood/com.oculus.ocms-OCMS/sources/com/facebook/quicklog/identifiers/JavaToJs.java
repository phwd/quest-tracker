package com.facebook.quicklog.identifiers;

public class JavaToJs {
    public static final int JAVAMETHOD = 12779529;
    public static final int JSARRAY = 12779526;
    public static final int JSBOOLEAN = 12779522;
    public static final int JSFUNCTION = 12779528;
    public static final int JSNULL = 12779521;
    public static final int JSNUMBER = 12779523;
    public static final int JSOBJECT = 12779525;
    public static final int JSSTRING = 12779524;
    public static final int JSUNDEFINED = 12779527;
    public static final short MODULE_ID = 195;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "JAVA_TO_JS_JSNULL";
            case 2:
                return "JAVA_TO_JS_JSBOOLEAN";
            case 3:
                return "JAVA_TO_JS_JSNUMBER";
            case 4:
                return "JAVA_TO_JS_JSSTRING";
            case 5:
                return "JAVA_TO_JS_JSOBJECT";
            case 6:
                return "JAVA_TO_JS_JSARRAY";
            case 7:
                return "JAVA_TO_JS_JSUNDEFINED";
            case 8:
                return "JAVA_TO_JS_JSFUNCTION";
            case 9:
                return "JAVA_TO_JS_JAVAMETHOD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
