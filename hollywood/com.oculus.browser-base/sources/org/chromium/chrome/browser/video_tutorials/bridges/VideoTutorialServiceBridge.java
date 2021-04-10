package org.chromium.chrome.browser.video_tutorials.bridges;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VideoTutorialServiceBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10798a;

    public VideoTutorialServiceBridge(long j) {
        this.f10798a = j;
    }

    public static VideoTutorialServiceBridge create(long j) {
        return new VideoTutorialServiceBridge(j);
    }

    public final void clearNativePtr() {
        this.f10798a = 0;
    }
}
