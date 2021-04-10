package com.facebook.assistant;

public enum Modality {
    Speech(0);
    
    public final int value;

    /* access modifiers changed from: public */
    Modality(int i) {
        this.value = i;
    }

    public static Modality from(int i) {
        Modality[] values = values();
        for (Modality modality : values) {
            if (modality.value == i) {
                return modality;
            }
        }
        throw new IllegalArgumentException("Unsupported Modality");
    }
}
