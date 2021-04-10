package defpackage;

import android.net.ProxyInfo;
import android.net.Uri;

/* renamed from: kI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3358kI0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C3358kI0 f10271a = new C3358kI0("", 0, "", new String[0]);
    public final String b;
    public final int c;
    public final String d;
    public final String[] e;

    public C3358kI0(String str, int i, String str2, String[] strArr) {
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = strArr;
    }

    public static C3358kI0 a(ProxyInfo proxyInfo) {
        String str = null;
        if (proxyInfo == null) {
            return null;
        }
        Uri pacFileUrl = proxyInfo.getPacFileUrl();
        String host = proxyInfo.getHost();
        int port = proxyInfo.getPort();
        if (!Uri.EMPTY.equals(pacFileUrl)) {
            str = pacFileUrl.toString();
        }
        return new C3358kI0(host, port, str, proxyInfo.getExclusionList());
    }
}
