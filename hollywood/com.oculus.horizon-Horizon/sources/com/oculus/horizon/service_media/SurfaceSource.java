package com.oculus.horizon.service_media;

public enum SurfaceSource {
    FIRSTPERSON(1),
    VRCAMERA(2);
    
    public final int value;

    /* access modifiers changed from: public */
    SurfaceSource(int i) {
        this.value = i;
    }

    public static SurfaceSource fromValue(int i) {
        SurfaceSource[] values = values();
        for (SurfaceSource surfaceSource : values) {
            if (surfaceSource.getValue() == i) {
                return surfaceSource;
            }
        }
        throw new IllegalArgumentException("");
    }

    public int getValue() {
        return this.value;
    }
}
