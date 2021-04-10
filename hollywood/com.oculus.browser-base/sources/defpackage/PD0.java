package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Size;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.UnguessableToken;

/* renamed from: PD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PD0 {

    /* renamed from: a  reason: collision with root package name */
    public SD0 f8676a;
    public VD0 b;
    public YD0 c;
    public C1629aE0 d;
    public List e = new ArrayList();

    public PD0(Context context, AbstractC5900zD0 zd0, UnguessableToken unguessableToken, int i, int i2, int i3, int i4, boolean z, C4372qE0 qe0, C2151dE0 de0, Runnable runnable) {
        UH0 uh0 = new UH0(UH0.c(UD0.g), null);
        OverScroller overScroller = new OverScroller(context);
        overScroller.setFriction(ViewConfiguration.getScrollFriction() / 2.0f);
        this.f8676a = new SD0(uh0, zd0, de0, unguessableToken, new Size(i, i2), i3, i4);
        if (z) {
            SD0 sd0 = this.f8676a;
            de0.getClass();
            this.b = new VD0((Matrix) uh0.g(UD0.f), sd0, new LD0(de0));
        }
        SD0 sd02 = this.f8676a;
        de0.getClass();
        YD0 yd0 = new YD0(overScroller, sd02, new MD0(de0), new ND0(de0));
        this.c = yd0;
        C1629aE0 ae0 = new C1629aE0(context, z, this.f8676a, new RD0(this.b, yd0, this.f8676a), runnable);
        this.d = ae0;
        if (qe0 != null) {
            this.c.f9263a = qe0;
        }
        ZH0.a(uh0, ae0, new OD0());
    }

    public void a() {
        KD0 kd0 = this.f8676a.m;
        ID0 id0 = kd0.f8351a;
        if (id0 != null) {
            id0.c();
            kd0.f8351a = null;
        }
        ID0 id02 = kd0.b;
        if (id02 != null) {
            id02.c();
            kd0.b = null;
        }
        for (PD0 pd0 : this.e) {
            pd0.a();
        }
    }

    public void b(boolean z) {
        YD0 yd0 = this.c;
        if (yd0 != null) {
            yd0.k = z;
        }
        VD0 vd0 = this.b;
        if (vd0 != null) {
            vd0.g = z;
        }
        for (PD0 pd0 : this.e) {
            pd0.b(z);
        }
    }
}
