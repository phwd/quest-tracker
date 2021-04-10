package defpackage;

import java.util.Objects;

/* renamed from: Tu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1207Tu {

    /* renamed from: a  reason: collision with root package name */
    public final M70 f8993a;
    public final C1321Vq b;
    public final C4294po1 c;

    public C1207Tu(M70 m70, C1321Vq vq, C4294po1 po1) {
        this.f8993a = m70;
        this.b = vq;
        this.c = po1;
    }

    public final void a(String str, boolean z) {
        int f = ((PU0) this.f8993a.get()).f(str, 0);
        for (int i = 0; i < f; i++) {
            Objects.requireNonNull(this.c);
            AbstractC3100ip1.f10165a.a(z ? "TrustedWebActivity.ClearDataDialogOnUninstallAccepted" : "TrustedWebActivity.ClearDataDialogOnClearAppDataAccepted", false);
        }
        ((PU0) this.f8993a.get()).l(str);
    }
}
