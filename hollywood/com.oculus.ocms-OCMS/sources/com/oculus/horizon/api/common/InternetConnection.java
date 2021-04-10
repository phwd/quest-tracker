package com.oculus.horizon.api.common;

import javax.annotation.Nullable;

public enum InternetConnection {
    REQUIRED("REQUIRED"),
    NOT_REQUIRED("NOT_REQUIRED"),
    REQUIRED_FOR_DOWNLOAD("REQUIRED_FOR_DOWNLOAD");
    
    public final String graphQLName;

    private InternetConnection(String str) {
        this.graphQLName = str;
    }

    public String toString() {
        return this.graphQLName;
    }

    @Nullable
    public static InternetConnection fromString(String str) {
        if (str == null) {
            return null;
        }
        InternetConnection[] values = values();
        for (InternetConnection internetConnection : values) {
            if (str.equalsIgnoreCase(internetConnection.graphQLName)) {
                return internetConnection;
            }
        }
        return null;
    }
}
