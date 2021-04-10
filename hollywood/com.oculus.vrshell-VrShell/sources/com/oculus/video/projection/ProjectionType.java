package com.oculus.video.projection;

import androidx.core.os.EnvironmentCompat;

public enum ProjectionType {
    RECT("rect", "RECT"),
    CUBEMAP("cubemap", "CUBEMAP_32"),
    UNPADDED_CUBEMAP("unpadded_cubemap", "UNPADDED_CUBEMAP_32"),
    ROTATED_CUBEMAP("rotated_cubemap", "ROTATED_CUBEMAP_23"),
    EQUIRECTANGULAR("equirectangular", "SPHERICAL"),
    HALF_EQUIRECTANGULAR("half_equirectangular", "SPHERICAL"),
    BARREL("barrel", "BARREL"),
    BARREL_SPLIT("barrel_split", "BARREL_SPLIT"),
    VR180("vr180", "VR180"),
    FISHEYE("fisheye", "FISHEYE"),
    MIXED360("mixed360", "MIXED360"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN, "UNKNOWN"),
    FLAT_LEGACY_DEPRECATED("flat", "SPHERICAL");
    
    public final String key;
    public final String videoLayout;

    private ProjectionType(String str, String str2) {
        this.key = str;
        this.videoLayout = str2;
    }

    public static ProjectionType fromFBProjection(String str) {
        if (str == null) {
            return UNKNOWN;
        }
        ProjectionType[] values = values();
        for (ProjectionType projectionType : values) {
            if (projectionType.key.equalsIgnoreCase(str)) {
                return projectionType;
            }
        }
        return UNKNOWN;
    }

    public static ProjectionType fromVideoLayout(String str) {
        if (str == null) {
            return UNKNOWN;
        }
        ProjectionType[] values = values();
        for (ProjectionType projectionType : values) {
            if (projectionType.videoLayout.equalsIgnoreCase(str)) {
                return projectionType;
            }
        }
        return UNKNOWN;
    }

    public int getLayoutID() {
        switch (this) {
            case RECT:
                return 0;
            case CUBEMAP:
                return 1;
            case UNPADDED_CUBEMAP:
                return 2;
            case ROTATED_CUBEMAP:
                return 3;
            case EQUIRECTANGULAR:
            case HALF_EQUIRECTANGULAR:
                return 4;
            case BARREL:
                return 5;
            case VR180:
                return 6;
            case FISHEYE:
                return 7;
            case MIXED360:
                return 8;
            case BARREL_SPLIT:
                return 9;
            default:
                return 15;
        }
    }

    public String toString() {
        return this.key;
    }
}
