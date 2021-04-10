package org.chromium.chrome.browser.ntp;

import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ForeignSessionHelper {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ForeignSession {

        /* renamed from: a  reason: collision with root package name */
        public final List f10707a = new ArrayList();

        public ForeignSession(String str, String str2, int i, long j, XR xr) {
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ForeignSessionCallback {
        void onUpdated();
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ForeignSessionWindow {

        /* renamed from: a  reason: collision with root package name */
        public final List f10708a = new ArrayList();

        public ForeignSessionWindow(long j, int i, XR xr) {
        }
    }

    public static ForeignSession pushSession(List list, String str, String str2, int i, long j) {
        ForeignSession foreignSession = new ForeignSession(str, str2, i, j, null);
        list.add(foreignSession);
        return foreignSession;
    }

    public static void pushTab(ForeignSessionWindow foreignSessionWindow, String str, String str2, long j, int i) {
        foreignSessionWindow.f10708a.add(new YR(str, str2, j, i, null));
    }

    public static ForeignSessionWindow pushWindow(ForeignSession foreignSession, long j, int i) {
        ForeignSessionWindow foreignSessionWindow = new ForeignSessionWindow(j, i, null);
        foreignSession.f10707a.add(foreignSessionWindow);
        return foreignSessionWindow;
    }
}
