package defpackage;

import com.oculus.vrapi.SystemProps;

/* renamed from: xE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5562xE {
    public boolean a() {
        String str = "default";
        Class cls = SystemProps.f9720a;
        try {
            str = (String) SystemProps.f9720a.getMethod("get", String.class, String.class).invoke(null, "ro.build.type", str);
        } catch (Exception unused) {
        }
        return str.equals("userdev") || str.equals("userdebug");
    }
}
