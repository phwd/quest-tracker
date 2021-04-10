package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Om  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class View$OnKeyListenerC0886Om extends AbstractC1366Wi0 implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public final Context G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f8646J;
    public final boolean K;
    public final Handler L;
    public final List M = new ArrayList();
    public final List N = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener O = new ViewTreeObserver$OnGlobalLayoutListenerC0582Jm(this);
    public final View.OnAttachStateChangeListener P = new View$OnAttachStateChangeListenerC0643Km(this);
    public final AbstractC0696Li0 Q = new C0764Mm(this);
    public int R;
    public int S;
    public View T;
    public View U;
    public int V;
    public boolean W;
    public boolean X;
    public int Y;
    public int Z;
    public boolean a0;
    public boolean b0;
    public AbstractC1886bj0 c0;
    public ViewTreeObserver d0;
    public PopupWindow.OnDismissListener e0;
    public boolean f0;

    public View$OnKeyListenerC0886Om(Context context, View view, int i, int i2, boolean z) {
        int i3 = 0;
        this.R = 0;
        this.S = 0;
        this.G = context;
        this.T = view;
        this.I = i;
        this.f8646J = i2;
        this.K = z;
        this.a0 = false;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        this.V = view.getLayoutDirection() != 1 ? 1 : i3;
        Resources resources = context.getResources();
        this.H = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.f15880_resource_name_obfuscated_RES_2131165207));
        this.L = new Handler();
    }

    @Override // defpackage.AbstractC2057cj0
    public void a(C4616ri0 ri0, boolean z) {
        int size = this.N.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (ri0 == ((C0825Nm) this.N.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.N.size()) {
                ((C0825Nm) this.N.get(i2)).b.c(false);
            }
            C0825Nm nm = (C0825Nm) this.N.remove(i);
            nm.b.t(this);
            if (this.f0) {
                nm.f8573a.g0.setExitTransition(null);
                nm.f8573a.g0.setAnimationStyle(0);
            }
            nm.f8573a.dismiss();
            int size2 = this.N.size();
            if (size2 > 0) {
                this.V = ((C0825Nm) this.N.get(size2 - 1)).c;
            } else {
                View view = this.T;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                this.V = view.getLayoutDirection() == 1 ? 0 : 1;
            }
            if (size2 == 0) {
                dismiss();
                AbstractC1886bj0 bj0 = this.c0;
                if (bj0 != null) {
                    bj0.a(ri0, true);
                }
                ViewTreeObserver viewTreeObserver = this.d0;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.d0.removeGlobalOnLayoutListener(this.O);
                    }
                    this.d0 = null;
                }
                this.U.removeOnAttachStateChangeListener(this.P);
                this.e0.onDismiss();
            } else if (z) {
                ((C0825Nm) this.N.get(0)).b.c(false);
            }
        }
    }

    @Override // defpackage.AbstractC3386kV0
    public boolean b() {
        return this.N.size() > 0 && ((C0825Nm) this.N.get(0)).f8573a.b();
    }

    @Override // defpackage.AbstractC3386kV0
    public void d() {
        if (!b()) {
            for (C4616ri0 ri0 : this.M) {
                v(ri0);
            }
            this.M.clear();
            View view = this.T;
            this.U = view;
            if (view != null) {
                boolean z = this.d0 == null;
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                this.d0 = viewTreeObserver;
                if (z) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.O);
                }
                this.U.addOnAttachStateChangeListener(this.P);
            }
        }
    }

    @Override // defpackage.AbstractC3386kV0
    public void dismiss() {
        int size = this.N.size();
        if (size > 0) {
            C0825Nm[] nmArr = (C0825Nm[]) this.N.toArray(new C0825Nm[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0825Nm nm = nmArr[i];
                if (nm.f8573a.b()) {
                    nm.f8573a.dismiss();
                }
            }
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public void e(AbstractC1886bj0 bj0) {
        this.c0 = bj0;
    }

    @Override // defpackage.AbstractC3386kV0
    public ListView f() {
        if (this.N.isEmpty()) {
            return null;
        }
        List list = this.N;
        return ((C0825Nm) list.get(list.size() - 1)).f8573a.f11052J;
    }

    @Override // defpackage.AbstractC2057cj0
    public void h(boolean z) {
        for (C0825Nm nm : this.N) {
            ListAdapter adapter = nm.f8573a.f11052J.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((C4104oi0) adapter).notifyDataSetChanged();
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean i(SubMenuC4510r31 r31) {
        for (C0825Nm nm : this.N) {
            if (r31 == nm.b) {
                nm.f8573a.f11052J.requestFocus();
                return true;
            }
        }
        if (!r31.hasVisibleItems()) {
            return false;
        }
        r31.b(this, this.G);
        if (b()) {
            v(r31);
        } else {
            this.M.add(r31);
        }
        AbstractC1886bj0 bj0 = this.c0;
        if (bj0 != null) {
            bj0.b(r31);
        }
        return true;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean j() {
        return false;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void l(C4616ri0 ri0) {
        ri0.b(this, this.G);
        if (b()) {
            v(ri0);
        } else {
            this.M.add(ri0);
        }
    }

    @Override // defpackage.AbstractC1366Wi0
    public void n(View view) {
        if (this.T != view) {
            this.T = view;
            int i = this.R;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            this.S = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.AbstractC1366Wi0
    public void o(boolean z) {
        this.a0 = z;
    }

    public void onDismiss() {
        C0825Nm nm;
        int size = this.N.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                nm = null;
                break;
            }
            nm = (C0825Nm) this.N.get(i);
            if (!nm.f8573a.b()) {
                break;
            }
            i++;
        }
        if (nm != null) {
            nm.b.c(false);
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void p(int i) {
        if (this.R != i) {
            this.R = i;
            View view = this.T;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            this.S = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.AbstractC1366Wi0
    public void q(int i) {
        this.W = true;
        this.Y = i;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void r(PopupWindow.OnDismissListener onDismissListener) {
        this.e0 = onDismissListener;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void s(boolean z) {
        this.b0 = z;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void t(int i) {
        this.X = true;
        this.Z = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0142, code lost:
        if (((r9.getWidth() + r11[0]) + r4) > r10.right) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0148, code lost:
        if ((r11[0] - r4) < 0) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014c, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void v(defpackage.C4616ri0 r17) {
        /*
        // Method dump skipped, instructions count: 527
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnKeyListenerC0886Om.v(ri0):void");
    }
}
