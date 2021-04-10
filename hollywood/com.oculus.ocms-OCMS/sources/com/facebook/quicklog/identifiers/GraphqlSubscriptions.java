package com.facebook.quicklog.identifiers;

public class GraphqlSubscriptions {
    public static final short MODULE_ID = 640;
    public static final int PUBLISH_RECEIVED_BLADERUNNER_ANDROID = 41943042;
    public static final int PUBLISH_RECEIVED_MQTT_ANDROID = 41943044;
    public static final int SUBSCRIBE_BLADERUNNER_ANDROID = 41943041;
    public static final int SUBSCRIBE_MQTT_ANDROID = 41943043;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "GRAPHQL_SUBSCRIPTIONS_PUBLISH_RECEIVED_MQTT_ANDROID" : "GRAPHQL_SUBSCRIPTIONS_SUBSCRIBE_MQTT_ANDROID" : "GRAPHQL_SUBSCRIPTIONS_PUBLISH_RECEIVED_BLADERUNNER_ANDROID" : "GRAPHQL_SUBSCRIPTIONS_SUBSCRIBE_BLADERUNNER_ANDROID";
    }
}
