package defpackage;

import J.N;
import org.chromium.chrome.browser.password_check.PasswordCheckBridge;

/* renamed from: Nx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0848Nx0 {

    /* renamed from: a  reason: collision with root package name */
    public static C0909Ox0 f8587a;

    public static void a() {
        C0909Ox0 ox0 = f8587a;
        if (ox0 != null) {
            PasswordCheckBridge passwordCheckBridge = ox0.f8657a;
            long j = passwordCheckBridge.f10731a;
            if (j != 0) {
                N.M$1pAUJ0(j);
                passwordCheckBridge.f10731a = 0;
            }
            f8587a = null;
        }
    }

    public static C0909Ox0 b(C2528fT0 ft0) {
        if (f8587a == null) {
            f8587a = new C0909Ox0(ft0);
        }
        return f8587a;
    }
}
