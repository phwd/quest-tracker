package defpackage;

import org.chromium.base.ApplicationStatus;

/* renamed from: yL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5752yL0 extends AbstractC0585Jn0 implements AbstractC1678aa {
    public boolean b;

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        if (ApplicationStatus.hasVisibleActivities()) {
            d();
        } else {
            this.f8312a.h();
        }
    }

    @Override // defpackage.AbstractC0585Jn0
    public void b() {
        if (!this.b) {
            ApplicationStatus.h.c(this);
            this.b = true;
        }
    }

    @Override // defpackage.AbstractC0585Jn0
    public void c(C0646Kn0 kn0) {
        this.f8312a = kn0;
        ApplicationStatus.h.b(this);
        a(0);
    }
}
