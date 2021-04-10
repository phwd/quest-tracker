package defpackage;

import android.app.Activity;
import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: ZN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZN extends WN {
    public final int j;
    public final YN k;

    public ZN(int i) {
        super(ContextUtils.getApplicationContext());
        this.j = i;
        YN yn = new YN(this);
        this.k = yn;
        if (i != 1) {
            yn.a(1);
        }
    }

    @Override // defpackage.N9
    public C3506lA1 a() {
        C3506lA1 la1;
        j("Completing update.");
        int i = this.c;
        if (i == 11) {
            this.c = 3;
            Integer num = 0;
            if (num.equals(this.i)) {
                g();
            }
            la1 = AbstractC3412kf1.b(null);
        } else {
            la1 = AbstractC3412kf1.a(i == 3 ? new Z10(-8) : new Z10(-7));
        }
        if (this.j == 7) {
            i(9);
        } else {
            i(10);
        }
        return la1;
    }

    @Override // defpackage.N9
    public boolean c(C2823hA1 ha1, int i, Activity activity, int i2) {
        boolean z;
        int i3;
        j("Starting update flow.");
        int i4 = 0;
        if (!ha1.b(i)) {
            z = false;
        } else {
            if (i == 1) {
                this.h = true;
                i3 = 1;
            } else {
                this.g = true;
                i3 = 0;
            }
            this.i = i3;
            z = true;
        }
        if (!z) {
            return false;
        }
        int i5 = this.j;
        if (i5 != 3) {
            i4 = i5 == 4 ? 1 : -1;
        }
        PostTask.b(Zo1.f9374a, new XN(this, activity, i2, i4), 5000);
        return true;
    }

    public final void h(int i) {
        this.k.a(i);
    }

    public final void i(int i) {
        this.k.sendEmptyMessageDelayed(i, 5000);
    }

    public final void j(CharSequence charSequence) {
        Context applicationContext = ContextUtils.getApplicationContext();
        C1184Ti1.b(applicationContext, "Play Store Flow: " + ((Object) charSequence), 2000).b.show();
    }
}
