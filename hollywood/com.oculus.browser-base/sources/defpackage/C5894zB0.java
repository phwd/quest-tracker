package defpackage;

import android.view.WindowManager;
import java.util.Objects;

/* renamed from: zB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5894zB0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11732a;
    public boolean b;
    public final /* synthetic */ AB0 c;

    public C5894zB0(AB0 ab0) {
        this.c = ab0;
    }

    public void a() {
        this.b = true;
        b();
    }

    public final void b() {
        TA0 ta0 = this.c.w;
        if (ta0 != null) {
            boolean z = true;
            if (!this.f11732a && this.b) {
                IF r0 = ta0.M;
                Objects.requireNonNull(r0);
                try {
                    r0.f8211a.show();
                } catch (WindowManager.BadTokenException unused) {
                    z = false;
                }
            } else {
                ta0.M.f8211a.hide();
            }
            if (!z) {
                C0289Es es = (C0289Es) this.c.m;
                es.e.a(8);
                es.d("Fails to show payment request UI. Please try again.");
            }
        }
    }
}
