package defpackage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Iterator;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: Lc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0679Lc1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = O81.f8603a;
        if (qh0 == kh0) {
            long j = 218;
            if (uh0.h(qh0)) {
                boolean h = uh0.h(O81.e);
                Iterator it = ((C3919nd1) tabListRecyclerView.o1).i.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((AbstractC5619xc1) uq0.next()).c();
                }
                if (AbstractC4772sd1.j()) {
                    j = 50;
                }
                tabListRecyclerView.setAlpha(0.0f);
                tabListRecyclerView.setVisibility(0);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(tabListRecyclerView, View.ALPHA, 1.0f);
                tabListRecyclerView.m1 = ofFloat;
                ofFloat.setInterpolator(animation.InterpolatorC5286vf.g);
                tabListRecyclerView.m1.setDuration(j);
                tabListRecyclerView.m1.start();
                tabListRecyclerView.m1.addListener(new L91(tabListRecyclerView));
                if (!h) {
                    tabListRecyclerView.m1.end();
                    return;
                }
                return;
            }
            boolean h2 = uh0.h(O81.e);
            ValueAnimator valueAnimator = tabListRecyclerView.m1;
            if (valueAnimator != null) {
                valueAnimator.end();
            }
            ValueAnimator valueAnimator2 = tabListRecyclerView.n1;
            if (valueAnimator2 != null) {
                valueAnimator2.end();
            }
            tabListRecyclerView.D0();
            Iterator it2 = ((C3919nd1) tabListRecyclerView.o1).i.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((AbstractC5619xc1) uq02.next()).b();
            }
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(tabListRecyclerView, View.ALPHA, 0.0f);
            tabListRecyclerView.n1 = ofFloat2;
            ofFloat2.setInterpolator(animation.InterpolatorC5286vf.f);
            tabListRecyclerView.n1.setDuration(218L);
            tabListRecyclerView.n1.addListener(new N91(tabListRecyclerView));
            tabListRecyclerView.E0(false);
            tabListRecyclerView.n1.start();
            if (!h2) {
                tabListRecyclerView.n1.end();
                return;
            }
            return;
        }
        QH0 qh02 = O81.b;
        if (qh02 == kh0) {
            tabListRecyclerView.setBackgroundColor(AbstractC2934hr.b(tabListRecyclerView.getResources(), uh0.h(qh02)));
            return;
        }
        TH0 th0 = O81.c;
        if (th0 == kh0) {
            tabListRecyclerView.o1 = (P91) uh0.g(th0);
            return;
        }
        TH0 th02 = O81.d;
        if (th02 == kh0) {
            ((LinearLayoutManager) tabListRecyclerView.U).C1(((Integer) uh0.g(th02)).intValue(), 0);
            return;
        }
        SH0 sh0 = O81.f;
        if (sh0 == kh0) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) tabListRecyclerView.getLayoutParams();
            int f = uh0.f(sh0);
            if (f != layoutParams.topMargin) {
                layoutParams.topMargin = f;
                tabListRecyclerView.requestLayout();
                return;
            }
            return;
        }
        SH0 sh02 = O81.g;
        if (sh02 == kh0) {
            ((FrameLayout.LayoutParams) tabListRecyclerView.getLayoutParams()).bottomMargin = uh0.f(sh02);
            tabListRecyclerView.requestLayout();
            return;
        }
        SH0 sh03 = O81.h;
        if (sh03 == kh0) {
            tabListRecyclerView.u1 = uh0.f(sh03);
            if (tabListRecyclerView.t1 != null && (tabListRecyclerView.getParent() instanceof FrameLayout)) {
                tabListRecyclerView.t1.setTranslationY((float) tabListRecyclerView.u1);
                int computeVerticalScrollOffset = tabListRecyclerView.computeVerticalScrollOffset();
                if (computeVerticalScrollOffset == 0) {
                    tabListRecyclerView.E0(false);
                } else if (computeVerticalScrollOffset > 0) {
                    tabListRecyclerView.E0(true);
                }
            }
        } else {
            SH0 sh04 = O81.i;
            if (sh04 == kh0) {
                tabListRecyclerView.setPadding(tabListRecyclerView.getPaddingLeft(), tabListRecyclerView.getPaddingTop(), tabListRecyclerView.getPaddingRight(), uh0.f(sh04));
            }
        }
    }
}
