package com.facebook.quicklog.identifiers;

public class Mqtt {
    public static final short MODULE_ID = 89;
    public static final int MQTT_HANDLE_PAYLOAD_FOR_GRAPHQL_SUBSCRIPTION = 5832722;

    public static String getMarkerName(int i) {
        return i != 18 ? "UNDEFINED_QPL_EVENT" : "MQTT_MQTT_HANDLE_PAYLOAD_FOR_GRAPHQL_SUBSCRIPTION";
    }
}
