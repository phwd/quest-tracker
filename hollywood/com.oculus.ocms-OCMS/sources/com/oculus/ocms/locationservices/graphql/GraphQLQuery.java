package com.oculus.ocms.locationservices.graphql;

public class GraphQLQuery {
    public static final String URSA_LOCATION_QUERY = "query WirelessPositioningQuery($signals: OculusSignals!) {wireless_positioning_oculus(signals: $signals) {  timestamp,  latitude,  longitude,  accuracy,  confidence,  model,  floor,  country,  grid_line_frequency,  altitude,  altitude_accuracy}}";
    public static final String URSA_LOCATION_WITH_TIMEZONE_QUERY = "query WirelessPositioningQuery($signals: OculusSignals!) {wireless_positioning_oculus(signals: $signals) {  timestamp,  latitude,  longitude,  accuracy,  confidence,  model,  floor,  country,  grid_line_frequency,  altitude,  altitude_accuracy,  timezone}}";
}
