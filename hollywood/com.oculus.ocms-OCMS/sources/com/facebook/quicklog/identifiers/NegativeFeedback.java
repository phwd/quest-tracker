package com.facebook.quicklog.identifiers;

public class NegativeFeedback {
    public static final short MODULE_ID = 77;
    public static final int NEGATIVE_FEEDBACK_GRAPHQL_FETCH = 5046273;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "NEGATIVE_FEEDBACK_NEGATIVE_FEEDBACK_GRAPHQL_FETCH";
    }
}
