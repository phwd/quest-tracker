package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Su1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Su1 extends Lu1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2620g0 f8924a = new Pu1(this);
    public final AbstractC2620g0 b = new Qu1(this);
    public AK0 c;
    public final /* synthetic */ ViewPager2 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Su1(ViewPager2 viewPager2) {
        super(viewPager2, null);
        this.d = viewPager2;
    }

    @Override // defpackage.Lu1
    public void a(C3803mw mwVar, RecyclerView recyclerView) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        recyclerView.setImportantForAccessibility(2);
        this.c = new Ru1(this);
        if (this.d.getImportantForAccessibility() == 0) {
            this.d.setImportantForAccessibility(1);
        }
    }

    @Override // defpackage.Lu1
    public void b() {
        e();
    }

    @Override // defpackage.Lu1
    public void c() {
        e();
    }

    public void d(int i) {
        ViewPager2 viewPager2 = this.d;
        if (viewPager2.V) {
            viewPager2.c(i, true);
        }
    }

    public void e() {
        int b2;
        ViewPager2 viewPager2 = this.d;
        int i = 16908360;
        AbstractC1920bu1.j(viewPager2, 16908360);
        AbstractC1920bu1.k(16908361, viewPager2);
        AbstractC1920bu1.h(viewPager2, 0);
        AbstractC1920bu1.k(16908358, viewPager2);
        AbstractC1920bu1.h(viewPager2, 0);
        AbstractC1920bu1.k(16908359, viewPager2);
        AbstractC1920bu1.h(viewPager2, 0);
        AbstractC5750yK0 yk0 = this.d.P.T;
        if (yk0 != null && (b2 = yk0.b()) != 0) {
            ViewPager2 viewPager22 = this.d;
            if (viewPager22.V) {
                if (viewPager22.M.r == 0) {
                    boolean a2 = viewPager22.a();
                    int i2 = a2 ? 16908360 : 16908361;
                    if (a2) {
                        i = 16908361;
                    }
                    if (this.d.f9488J < b2 - 1) {
                        AbstractC1920bu1.l(viewPager2, new A(i2, null), null, this.f8924a);
                    }
                    if (this.d.f9488J > 0) {
                        AbstractC1920bu1.l(viewPager2, new A(i, null), null, this.b);
                        return;
                    }
                    return;
                }
                if (viewPager22.f9488J < b2 - 1) {
                    AbstractC1920bu1.l(viewPager2, new A(16908359, null), null, this.f8924a);
                }
                if (this.d.f9488J > 0) {
                    AbstractC1920bu1.l(viewPager2, new A(16908358, null), null, this.b);
                }
            }
        }
    }
}
