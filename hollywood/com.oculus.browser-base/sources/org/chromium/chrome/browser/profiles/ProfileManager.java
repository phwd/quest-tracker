package org.chromium.chrome.browser.profiles;

import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProfileManager {

    /* renamed from: a  reason: collision with root package name */
    public static C1322Vq0 f10754a = new C1322Vq0();
    public static boolean b;

    public static void onProfileAdded(Profile profile) {
        b = true;
        Iterator it = f10754a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1818bH0) uq0.next()).f(profile);
            } else {
                return;
            }
        }
    }
}
