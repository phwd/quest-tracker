package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: YZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YZ0 extends AbstractC1366Wi0 implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public final Context G;
    public final C4616ri0 H;
    public final C4104oi0 I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f9280J;
    public final int K;
    public final int L;
    public final int M;
    public final C1706aj0 N;
    public final ViewTreeObserver.OnGlobalLayoutListener O = new WZ0(this);
    public final View.OnAttachStateChangeListener P = new XZ0(this);
    public PopupWindow.OnDismissListener Q;
    public View R;
    public View S;
    public AbstractC1886bj0 T;
    public ViewTreeObserver U;
    public boolean V;
    public boolean W;
    public int X;
    public int Y = 0;
    public boolean Z;

    public YZ0(Context context, C4616ri0 ri0, View view, int i, int i2, boolean z) {
        this.G = context;
        this.H = ri0;
        this.f9280J = z;
        this.I = new C4104oi0(ri0, LayoutInflater.from(context), z, R.layout.f36460_resource_name_obfuscated_RES_2131623955);
        this.L = i;
        this.M = i2;
        Resources resources = context.getResources();
        this.K = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.f15880_resource_name_obfuscated_RES_2131165207));
        this.R = view;
        this.N = new C1706aj0(context, null, i, i2);
        ri0.b(this, context);
    }

    @Override // defpackage.AbstractC2057cj0
    public void a(C4616ri0 ri0, boolean z) {
        if (ri0 == this.H) {
            dismiss();
            AbstractC1886bj0 bj0 = this.T;
            if (bj0 != null) {
                bj0.a(ri0, z);
            }
        }
    }

    @Override // defpackage.AbstractC3386kV0
    public boolean b() {
        return !this.V && this.N.b();
    }

    @Override // defpackage.AbstractC3386kV0
    public void d() {
        View view;
        boolean z = true;
        if (!b()) {
            if (this.V || (view = this.R) == null) {
                z = false;
            } else {
                this.S = view;
                this.N.g0.setOnDismissListener(this);
                C1706aj0 aj0 = this.N;
                aj0.X = this;
                aj0.s(true);
                View view2 = this.S;
                boolean z2 = this.U == null;
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                this.U = viewTreeObserver;
                if (z2) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.O);
                }
                view2.addOnAttachStateChangeListener(this.P);
                C1706aj0 aj02 = this.N;
                aj02.W = view2;
                aj02.S = this.Y;
                if (!this.W) {
                    this.X = AbstractC1366Wi0.m(this.I, null, this.G, this.K);
                    this.W = true;
                }
                this.N.r(this.X);
                this.N.g0.setInputMethodMode(2);
                C1706aj0 aj03 = this.N;
                Rect rect = this.F;
                Objects.requireNonNull(aj03);
                aj03.e0 = rect != null ? new Rect(rect) : null;
                this.N.d();
                C1823bJ bJVar = this.N.f11052J;
                bJVar.setOnKeyListener(this);
                if (this.Z && this.H.n != null) {
                    FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.G).inflate(R.layout.f36450_resource_name_obfuscated_RES_2131623954, (ViewGroup) bJVar, false);
                    TextView textView = (TextView) frameLayout.findViewById(16908310);
                    if (textView != null) {
                        textView.setText(this.H.n);
                    }
                    frameLayout.setEnabled(false);
                    bJVar.addHeaderView(frameLayout, null, false);
                }
                this.N.p(this.I);
                this.N.d();
            }
        }
        if (!z) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // defpackage.AbstractC3386kV0
    public void dismiss() {
        if (b()) {
            this.N.dismiss();
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public void e(AbstractC1886bj0 bj0) {
        this.T = bj0;
    }

    @Override // defpackage.AbstractC3386kV0
    public ListView f() {
        return this.N.f11052J;
    }

    @Override // defpackage.AbstractC2057cj0
    public void h(boolean z) {
        this.W = false;
        C4104oi0 oi0 = this.I;
        if (oi0 != null) {
            oi0.notifyDataSetChanged();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070  */
    @Override // defpackage.AbstractC2057cj0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean i(defpackage.SubMenuC4510r31 r10) {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.YZ0.i(r31):boolean");
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean j() {
        return false;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void l(C4616ri0 ri0) {
    }

    @Override // defpackage.AbstractC1366Wi0
    public void n(View view) {
        this.R = view;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void o(boolean z) {
        this.I.H = z;
    }

    public void onDismiss() {
        this.V = true;
        this.H.c(true);
        ViewTreeObserver viewTreeObserver = this.U;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.U = this.S.getViewTreeObserver();
            }
            this.U.removeGlobalOnLayoutListener(this.O);
            this.U = null;
        }
        this.S.removeOnAttachStateChangeListener(this.P);
        PopupWindow.OnDismissListener onDismissListener = this.Q;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
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
        this.Y = i;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void q(int i) {
        this.N.M = i;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void r(PopupWindow.OnDismissListener onDismissListener) {
        this.Q = onDismissListener;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void s(boolean z) {
        this.Z = z;
    }

    @Override // defpackage.AbstractC1366Wi0
    public void t(int i) {
        C1706aj0 aj0 = this.N;
        aj0.N = i;
        aj0.P = true;
    }
}
