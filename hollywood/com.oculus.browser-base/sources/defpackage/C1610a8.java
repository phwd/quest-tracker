package defpackage;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1610a8 implements AbstractC5526x2 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC5526x2 f9411a;
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 b;

    public C1610a8(LayoutInflater$Factory2C3156j8 j8Var, AbstractC5526x2 x2Var) {
        this.b = j8Var;
        this.f9411a = x2Var;
    }

    @Override // defpackage.AbstractC5526x2
    public boolean a(AbstractC5696y2 y2Var, Menu menu) {
        return this.f9411a.a(y2Var, menu);
    }

    @Override // defpackage.AbstractC5526x2
    public boolean b(AbstractC5696y2 y2Var, MenuItem menuItem) {
        return this.f9411a.b(y2Var, menuItem);
    }

    @Override // defpackage.AbstractC5526x2
    public boolean c(AbstractC5696y2 y2Var, Menu menu) {
        ViewGroup viewGroup = this.b.e0;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        viewGroup.requestApplyInsets();
        return this.f9411a.c(y2Var, menu);
    }

    @Override // defpackage.AbstractC5526x2
    public void d(AbstractC5696y2 y2Var) {
        this.f9411a.d(y2Var);
        LayoutInflater$Factory2C3156j8 j8Var = this.b;
        if (j8Var.Z != null) {
            j8Var.O.getDecorView().removeCallbacks(this.b.a0);
        }
        LayoutInflater$Factory2C3156j8 j8Var2 = this.b;
        if (j8Var2.Y != null) {
            j8Var2.y();
            LayoutInflater$Factory2C3156j8 j8Var3 = this.b;
            Zu1 a2 = AbstractC1920bu1.a(j8Var3.Y);
            a2.a(0.0f);
            j8Var3.b0 = a2;
            Zu1 zu1 = this.b.b0;
            Z7 z7 = new Z7(this);
            View view = (View) zu1.f9382a.get();
            if (view != null) {
                zu1.e(view, z7);
            }
        }
        LayoutInflater$Factory2C3156j8 j8Var4 = this.b;
        M7 m7 = j8Var4.Q;
        if (m7 != null) {
            m7.t(j8Var4.X);
        }
        LayoutInflater$Factory2C3156j8 j8Var5 = this.b;
        j8Var5.X = null;
        ViewGroup viewGroup = j8Var5.e0;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        viewGroup.requestApplyInsets();
    }
}
