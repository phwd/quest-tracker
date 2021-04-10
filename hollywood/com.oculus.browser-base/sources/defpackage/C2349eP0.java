package defpackage;

import java.util.Objects;

/* renamed from: eP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2349eP0 implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final C2691gP0 f9853a;
    public final C5305vl0 b;
    public final Runnable c;
    public final boolean d;

    public C2349eP0(C2691gP0 gp0, C5305vl0 vl0, Runnable runnable, boolean z) {
        this.f9853a = gp0;
        this.b = vl0;
        this.c = runnable;
        this.d = z;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        C2691gP0 gp0 = this.f9853a;
        C5305vl0 vl0 = this.b;
        Runnable runnable = this.c;
        boolean z2 = this.d;
        Objects.requireNonNull(gp0);
        if (z) {
            vl0.c();
            if (runnable != null) {
                runnable.run();
            }
            gp0.c();
        } else if (z2) {
            gp0.d();
        }
    }
}
