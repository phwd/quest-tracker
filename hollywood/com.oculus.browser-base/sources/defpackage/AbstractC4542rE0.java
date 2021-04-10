package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: rE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4542rE0 {

    /* renamed from: a  reason: collision with root package name */
    public static Map f11192a = new HashMap();

    public static boolean a(String str) {
        return System.currentTimeMillis() - (f11192a.get(str) != null ? ((Long) f11192a.get(str)).longValue() : 0) < 500;
    }
}
