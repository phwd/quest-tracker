package com.facebook.models;

public interface ModelLoaderCallbacks {
    void onError(String str);

    void onResult(ModelMetadata modelMetadata);
}
