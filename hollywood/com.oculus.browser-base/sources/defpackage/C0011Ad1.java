package defpackage;

import android.graphics.Rect;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: Ad1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0011Ad1 implements AbstractC5792yd1, Comparator {
    public static final int[] F = {0, 2, 1};
    public static final SparseIntArray G = new SparseIntArray();
    public PriorityQueue H;
    public TabImpl I;

    /* renamed from: J  reason: collision with root package name */
    public View f7682J;
    public AbstractC4541rE K;
    public final Rect L = new Rect();

    static {
        int i = 0;
        while (true) {
            int[] iArr = F;
            if (i < iArr.length) {
                G.put(iArr[i], i);
                i++;
            } else {
                return;
            }
        }
    }

    public C0011Ad1(TabImpl tabImpl) {
        this.I = tabImpl;
        this.H = new PriorityQueue(F.length, this);
    }

    public void a(AbstractC0072Bd1 bd1) {
        if (!this.H.contains(bd1)) {
            this.H.add(bd1);
            d((AbstractC0072Bd1) this.H.peek());
        }
    }

    public boolean b(AbstractC0072Bd1 bd1) {
        AbstractC0072Bd1 bd12 = (AbstractC0072Bd1) this.H.peek();
        return bd12 != null && bd12 == bd1;
    }

    public void c(AbstractC0072Bd1 bd1) {
        this.H.remove(bd1);
        d((AbstractC0072Bd1) this.H.peek());
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        SparseIntArray sparseIntArray = G;
        return sparseIntArray.get(((AbstractC0072Bd1) obj).c()) - sparseIntArray.get(((AbstractC0072Bd1) obj2).c());
    }

    public final void d(AbstractC0072Bd1 bd1) {
        AbstractC0072Bd1 bd12;
        if (this.I != null && (bd12 = (AbstractC0072Bd1) this.H.peek()) != bd1) {
            View view = null;
            if (bd12 != null) {
                view = bd12.b();
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
            }
            this.f7682J = view;
            if (this.I.W() != null && !this.I.W().v() && this.K == null) {
                C1708ak akVar = new C1708ak(this.I.W().M0());
                this.K = akVar;
                akVar.l(new C5962zd1(this));
                Rect rect = (Rect) ((C1078Rq0) this.K).H;
                if (rect != null) {
                    this.L.set(rect);
                    e();
                }
            }
            e();
            TabImpl tabImpl = this.I;
            tabImpl.N = this.f7682J;
            tabImpl.e0();
            if (bd1 != null) {
                bd1.e();
            }
            if (bd12 != null) {
                bd12.d();
            }
        }
    }

    public final void e() {
        if (this.f7682J != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            Rect rect = this.L;
            layoutParams.setMargins(rect.left, rect.top, rect.right, rect.bottom);
            this.f7682J.setLayoutParams(layoutParams);
        }
    }
}
