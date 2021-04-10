package org.chromium.ui;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface OverscrollRefreshHandler {
    void pull(float f, float f2);

    void release(boolean z);

    void reset();

    boolean start(int i, float f, float f2, boolean z);
}
