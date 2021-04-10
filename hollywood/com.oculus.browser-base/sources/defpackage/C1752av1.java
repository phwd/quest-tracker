package defpackage;

import android.view.View;

/* renamed from: av1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1752av1 extends AbstractC2264dv1 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9499a = false;
    public int b = 0;
    public final /* synthetic */ C1923bv1 c;

    public C1752av1(C1923bv1 bv1) {
        this.c = bv1;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        int i = this.b + 1;
        this.b = i;
        if (i == this.c.f9573a.size()) {
            AbstractC2094cv1 cv1 = this.c.d;
            if (cv1 != null) {
                cv1.b(null);
            }
            this.b = 0;
            this.f9499a = false;
            this.c.e = false;
        }
    }

    @Override // defpackage.AbstractC2264dv1, defpackage.AbstractC2094cv1
    public void c(View view) {
        if (!this.f9499a) {
            this.f9499a = true;
            AbstractC2094cv1 cv1 = this.c.d;
            if (cv1 != null) {
                cv1.c(null);
            }
        }
    }
}
