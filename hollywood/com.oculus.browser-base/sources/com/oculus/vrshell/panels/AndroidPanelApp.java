package com.oculus.vrshell.panels;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AndroidPanelApp {
    public final native void nativeHideLayer(long j, String str, int i, boolean z);

    public final native void nativeQueueRawCommand(long j, String str);

    public final native void nativeSetNextFrameAction(long j, String str, String str2);

    public final native void nativeShowLayer(long j, String str, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, int i3, boolean z3);
}
