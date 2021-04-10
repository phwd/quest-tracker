package org.chromium.components.crash.browser;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChildProcessCrashObserver {

    /* renamed from: a  reason: collision with root package name */
    public static C3904nY f10835a;

    public static void childCrashed(int i) {
        C3904nY nYVar = f10835a;
        if (nYVar == null) {
            AbstractC1220Ua0.f("ChildCrashObserver", "Ignoring crash observed before a callback was registered...", new Object[0]);
        } else {
            nYVar.f10495a.P.b();
        }
    }
}
