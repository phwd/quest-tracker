package com.facebook.quicklog.identifiers;

public class ImagepipelineTwo {
    public static final short MODULE_ID = 301;
    public static final int NATIVE_NEWSFEED = 19726338;
    public static final int NATIVE_NEWSFEED_100 = 19726339;
    public static final int NATIVE_NEWSFEED_TEST = 19726341;
    public static final int NATIVE_STORIES = 19726342;
    public static final int PROFILE_PERF = 19726343;
    public static final int UNKNOWN = 19726337;
    public static final int UNKNOWN_TEST = 19726340;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "IMAGEPIPELINE_TWO_UNKNOWN";
            case 2:
                return "IMAGEPIPELINE_TWO_NATIVE_NEWSFEED";
            case 3:
                return "IMAGEPIPELINE_TWO_NATIVE_NEWSFEED_100";
            case 4:
                return "IMAGEPIPELINE_TWO_UNKNOWN_TEST";
            case 5:
                return "IMAGEPIPELINE_TWO_NATIVE_NEWSFEED_TEST";
            case 6:
                return "IMAGEPIPELINE_TWO_NATIVE_STORIES";
            case 7:
                return "IMAGEPIPELINE_TWO_PROFILE_PERF";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
