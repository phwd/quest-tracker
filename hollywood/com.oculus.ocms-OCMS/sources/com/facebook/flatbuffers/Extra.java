package com.facebook.flatbuffers;

public interface Extra {
    void clearPersistentDataChanged();

    boolean isPersistentDataChanged();

    void setBaseObject(Flattenable flattenable);
}
