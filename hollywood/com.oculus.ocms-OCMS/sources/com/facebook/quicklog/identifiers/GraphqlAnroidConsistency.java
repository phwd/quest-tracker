package com.facebook.quicklog.identifiers;

public class GraphqlAnroidConsistency {
    public static final int CONFIRMED_OPERATION = 9764866;
    public static final short MODULE_ID = 149;
    public static final int OPTIMISTIC_OPERATION = 9764865;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "GRAPHQL_ANROID_CONSISTENCY_CONFIRMED_OPERATION" : "GRAPHQL_ANROID_CONSISTENCY_OPTIMISTIC_OPERATION";
    }
}
