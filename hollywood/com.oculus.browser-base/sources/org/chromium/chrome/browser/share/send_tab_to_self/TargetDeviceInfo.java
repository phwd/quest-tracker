package org.chromium.chrome.browser.share.send_tab_to_self;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TargetDeviceInfo {

    /* renamed from: a  reason: collision with root package name */
    public final String f10760a;
    public final int b;
    public final String c;
    public final long d;

    public TargetDeviceInfo(String str, String str2, int i, long j) {
        this.c = str;
        this.f10760a = str2;
        this.b = i;
        this.d = j;
    }

    public static TargetDeviceInfo createTargetDeviceInfo(String str, String str2, int i, long j) {
        return new TargetDeviceInfo(str, str2, i, j);
    }
}
