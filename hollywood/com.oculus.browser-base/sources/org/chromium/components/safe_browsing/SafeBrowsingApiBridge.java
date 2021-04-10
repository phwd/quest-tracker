package org.chromium.components.safe_browsing;

import java.lang.reflect.InvocationTargetException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SafeBrowsingApiBridge {

    /* renamed from: a  reason: collision with root package name */
    public static Class f10885a;

    public static SafeBrowsingApiHandler create() {
        try {
            SafeBrowsingApiHandler safeBrowsingApiHandler = (SafeBrowsingApiHandler) f10885a.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (safeBrowsingApiHandler.a(new C3543lO0())) {
                return safeBrowsingApiHandler;
            }
            return null;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            StringBuilder i = AbstractC2531fV.i("Failed to init handler: ");
            i.append(e.getMessage());
            AbstractC1220Ua0.a("SBApiBridge", i.toString(), new Object[0]);
            return null;
        }
    }

    public static boolean startAllowlistLookup(SafeBrowsingApiHandler safeBrowsingApiHandler, String str, int i) {
        return safeBrowsingApiHandler.b(str, i);
    }

    public static void startUriLookup(SafeBrowsingApiHandler safeBrowsingApiHandler, long j, String str, int[] iArr) {
        safeBrowsingApiHandler.c(j, str, iArr);
    }
}
