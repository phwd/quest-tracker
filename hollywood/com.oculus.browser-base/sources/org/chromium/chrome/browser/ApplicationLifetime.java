package org.chromium.chrome.browser;

import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ApplicationLifetime {

    /* renamed from: a  reason: collision with root package name */
    public static C1322Vq0 f10600a = new C1322Vq0();

    public static void terminate(boolean z) {
        Iterator it = f10600a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((S9) uq0.next()).b(z);
            } else {
                return;
            }
        }
    }
}
