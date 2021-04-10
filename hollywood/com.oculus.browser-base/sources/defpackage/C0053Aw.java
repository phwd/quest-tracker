package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import com.oculus.browser.R;

/* renamed from: Aw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0053Aw implements Bv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC3719mR f7706a = new C5847yw("opacity");
    public final RectF b;
    public final RectF c = new RectF();
    public final AbstractC6017zw d;
    public int e;
    public int f;
    public int g;
    public int h;
    public float i;
    public float j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public String o;
    public String p;

    public C0053Aw(Context context, float f2, float f3, AbstractC6017zw zwVar) {
        RectF rectF = new RectF();
        this.b = rectF;
        rectF.set(0.0f, 0.0f, f2, f3);
        this.i = 1.0f;
        this.k = false;
        this.l = true;
        this.m = false;
        this.n = true;
        Resources resources = context.getResources();
        this.j = resources.getDimension(R.dimen.f17460_resource_name_obfuscated_RES_2131165365) * (1.0f / resources.getDisplayMetrics().density);
        this.d = zwVar;
    }

    @Override // defpackage.Bv1
    public boolean a(float f2, float f3) {
        if (this.i < 1.0f || !this.l || !this.n) {
            return false;
        }
        this.c.set(this.b);
        RectF rectF = this.c;
        float f4 = this.j;
        rectF.inset(-f4, -f4);
        return this.c.contains(f2, f3);
    }

    @Override // defpackage.Bv1
    public String b() {
        return this.m ? this.p : this.o;
    }

    @Override // defpackage.Bv1
    public void c(long j2) {
        this.d.a(j2);
    }

    @Override // defpackage.Bv1
    public void d(RectF rectF) {
        rectF.set(this.b);
        float f2 = this.j;
        rectF.inset((float) ((int) (-f2)), (float) ((int) (-f2)));
    }

    public boolean e(float f2, float f3) {
        if (a(f2, f3)) {
            return this.k;
        }
        this.k = false;
        return false;
    }

    public int f() {
        return this.k ? this.m ? this.h : this.f : this.m ? this.g : this.e;
    }

    public boolean g() {
        boolean z = this.k;
        this.k = false;
        return z;
    }

    public void h(int i2, int i3, int i4, int i5) {
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.h = i5;
    }

    public void i(float f2) {
        RectF rectF = this.b;
        rectF.right = rectF.width() + f2;
        this.b.left = f2;
    }

    public void j(float f2) {
        RectF rectF = this.b;
        rectF.bottom = rectF.height() + f2;
        this.b.top = f2;
    }
}
