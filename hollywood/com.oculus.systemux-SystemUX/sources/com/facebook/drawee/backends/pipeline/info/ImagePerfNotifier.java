package com.facebook.drawee.backends.pipeline.info;

public interface ImagePerfNotifier {
    void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, int i);

    void notifyStatusUpdated(ImagePerfState imagePerfState, int i);
}
