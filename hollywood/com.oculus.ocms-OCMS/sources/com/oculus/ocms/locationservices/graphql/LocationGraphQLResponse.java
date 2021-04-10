package com.oculus.ocms.locationservices.graphql;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import com.facebook.graphservice.interfaces.Summary;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class LocationGraphQLResponse {
    public WirelessPositioning wireless_positioning_oculus;

    public static class WirelessPositioning {
        public float accuracy;
        public double altitude;
        public float altitude_accuracy;
        public double confidence;
        public String country;
        @Nullable
        public Integer floor;
        public int grid_line_frequency;
        public double latitude;
        public double longitude;
        public String model;
        public long timestamp;
        @Nullable
        public String timezone;
    }

    @Nullable
    public Location createLocationWithElapsedRealTimeNanos(long j) {
        if (this.wireless_positioning_oculus == null) {
            return null;
        }
        Location location = new Location(Summary.Source.NETWORK);
        location.setTime(this.wireless_positioning_oculus.timestamp);
        location.setLatitude(this.wireless_positioning_oculus.latitude);
        location.setLongitude(this.wireless_positioning_oculus.longitude);
        location.setAccuracy(this.wireless_positioning_oculus.accuracy);
        location.setAltitude(this.wireless_positioning_oculus.altitude);
        if (Build.VERSION.SDK_INT >= 26) {
            location.setVerticalAccuracyMeters(this.wireless_positioning_oculus.altitude_accuracy);
        }
        Bundle bundle = new Bundle();
        bundle.putString("country", this.wireless_positioning_oculus.country);
        bundle.putInt("gridLineFrequency", this.wireless_positioning_oculus.grid_line_frequency);
        if (this.wireless_positioning_oculus.timezone != null) {
            bundle.putString("timezone", this.wireless_positioning_oculus.timezone);
        }
        if (this.wireless_positioning_oculus.floor != null) {
            bundle.putInt("floor", this.wireless_positioning_oculus.floor.intValue());
        }
        location.setExtras(bundle);
        location.setElapsedRealtimeNanos(j);
        return location;
    }
}
