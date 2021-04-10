package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import java.util.Objects;

/* renamed from: O4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O4 implements View.OnTouchListener, AbstractC4219pK0 {
    public final Rect F = new Rect();
    public final Rect G = new Rect();
    public final Context H;
    public final Handler I;

    /* renamed from: J  reason: collision with root package name */
    public final View f8596J;
    public final PopupWindow K;
    public final C4390qK0 L;
    public final Runnable M = new L4(this);
    public final PopupWindow.OnDismissListener N;
    public boolean O;
    public C1322Vq0 P;
    public N4 Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public int Z;
    public boolean a0;
    public boolean b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;

    public O4(Context context, View view, Drawable drawable, View view2, C4390qK0 qk0) {
        M4 m4 = new M4(this);
        this.N = m4;
        this.P = new C1322Vq0();
        this.Y = 0;
        this.Z = 0;
        this.H = context;
        this.f8596J = view.getRootView();
        if (C2588fp1.f9958a == null) {
            C2588fp1.f9958a = new C2588fp1();
        }
        Objects.requireNonNull(C2588fp1.f9958a);
        PopupWindow popupWindow = new PopupWindow(context);
        this.K = popupWindow;
        this.I = new Handler();
        this.L = qk0;
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setContentView(view2);
        popupWindow.setTouchInterceptor(this);
        popupWindow.setOnDismissListener(m4);
    }

    @Override // defpackage.AbstractC4219pK0
    public void a() {
        e();
    }

    @Override // defpackage.AbstractC4219pK0
    public void b() {
        this.K.dismiss();
    }

    public boolean c() {
        return this.K.isShowing();
    }

    public void d() {
        if (!this.K.isShowing()) {
            this.L.b(this);
            e();
            try {
                this.K.showAtLocation(this.f8596J, 8388659, this.R, this.S);
            } catch (WindowManager.BadTokenException unused) {
            }
        }
    }

    public final void e() {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        boolean z2;
        boolean z3 = this.b0;
        boolean z4 = this.c0;
        boolean z5 = this.K.isShowing() && !this.f0;
        this.K.getBackground().getPadding(this.F);
        Rect rect = this.F;
        int i5 = rect.left + rect.right;
        int i6 = rect.top + rect.bottom;
        int i7 = this.W;
        int width = this.f8596J.getWidth() - (this.V * 2);
        if (i7 == 0 || i7 >= width) {
            i7 = width;
        }
        int i8 = i7 > i5 ? i7 - i5 : 0;
        View contentView = this.K.getContentView();
        int i9 = this.X;
        if (i9 > 0) {
            if (i9 < i8) {
                i8 = i9;
            }
            i = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE);
        }
        contentView.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = contentView.getMeasuredHeight();
        int measuredWidth = contentView.getMeasuredWidth();
        this.f8596J.getWindowVisibleDisplayFrame(this.G);
        int[] iArr = new int[2];
        this.f8596J.getLocationOnScreen(iArr);
        this.G.offset(-iArr[0], -iArr[1]);
        Rect rect2 = this.L.F;
        boolean z6 = this.d0;
        int i10 = z6 ? rect2.bottom : rect2.top;
        Rect rect3 = this.G;
        int i11 = this.V;
        int i12 = ((i10 - rect3.top) - i6) - i11;
        int i13 = ((rect3.bottom - (z6 ? rect2.top : rect2.bottom)) - i6) - i11;
        boolean z7 = measuredHeight <= i13;
        boolean z8 = measuredHeight <= i12;
        if ((!z7 || i13 < i12) && z8) {
            i2 = i13;
            z = false;
        } else {
            i2 = i13;
            z = true;
        }
        this.b0 = z;
        if (z5 && z3 != z) {
            if (z3 && z7) {
                this.b0 = true;
            }
            if (!z3 && z8) {
                this.b0 = false;
            }
        }
        int i14 = this.Y;
        if (i14 == 1 && z7) {
            this.b0 = true;
        }
        if (i14 == 2 && z8) {
            this.b0 = false;
        }
        if (this.Z == 0) {
            boolean z9 = this.e0;
            int i15 = (z9 ? rect2.right : rect2.left) - rect3.left;
            int i16 = rect3.right - (z9 ? rect2.left : rect2.right);
            int i17 = measuredWidth + i6 + i11;
            boolean z10 = i15 >= i16;
            if (z5 && z10 != z4) {
                if (z4 && i17 <= i15) {
                    z10 = true;
                }
                if (!z4 && i17 <= i16) {
                    z2 = false;
                    this.c0 = z2;
                }
            }
            z2 = z10;
            this.c0 = z2;
        }
        contentView.measure(i, View.MeasureSpec.makeMeasureSpec(this.b0 ? i2 : i12, Integer.MIN_VALUE));
        this.T = contentView.getMeasuredWidth() + i5;
        this.U = contentView.getMeasuredHeight() + i6;
        Rect rect4 = this.G;
        int i18 = this.T;
        int i19 = this.V;
        boolean z11 = this.e0;
        int i20 = this.Z;
        boolean z12 = this.c0;
        if (i20 == 1) {
            i3 = ((rect2.width() - i18) / 2) + rect2.left + i19;
        } else if (z12) {
            i3 = (z11 ? rect2.right : rect2.left) - i18;
        } else {
            i3 = z11 ? rect2.left : rect2.right;
        }
        int i21 = (rect4.right - i18) - i19;
        int i22 = i19 > i21 ? i21 : i19;
        if (i19 <= i21) {
            i19 = i21;
        }
        if (i3 >= i22) {
            i22 = i3 > i19 ? i19 : i3;
        }
        this.R = i22;
        int i23 = this.U;
        boolean z13 = this.d0;
        boolean z14 = this.b0;
        if (z14) {
            i4 = z13 ? rect2.top : rect2.bottom;
        } else {
            i4 = (z13 ? rect2.bottom : rect2.top) - i23;
        }
        this.S = i4;
        N4 n4 = this.Q;
        if (n4 != null) {
            n4.a(z14, i22, i4, this.T, i23, rect2);
        }
        if (this.K.isShowing() && this.b0 != z3) {
            try {
                this.a0 = true;
                this.K.dismiss();
                try {
                    this.K.showAtLocation(this.f8596J, 8388659, this.R, this.S);
                } catch (WindowManager.BadTokenException unused) {
                }
            } finally {
                this.a0 = false;
            }
        }
        this.K.update(this.R, this.S, this.T, this.U);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.O) {
            this.K.dismiss();
        }
        return false;
    }
}
