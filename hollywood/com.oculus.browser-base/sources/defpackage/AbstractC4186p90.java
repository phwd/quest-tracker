package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: p90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4186p90 implements AbstractC3386kV0 {
    public static Method F;
    public static Method G;
    public Context H;
    public ListAdapter I;

    /* renamed from: J  reason: collision with root package name */
    public C1823bJ f11052J;
    public int K = -2;
    public int L = -2;
    public int M;
    public int N;
    public int O = 1002;
    public boolean P;
    public boolean Q;
    public boolean R;
    public int S = 0;
    public int T = Integer.MAX_VALUE;
    public int U = 0;
    public DataSetObserver V;
    public View W;
    public AdapterView.OnItemClickListener X;
    public final RunnableC4015o90 Y = new RunnableC4015o90(this);
    public final View$OnTouchListenerC3844n90 Z = new View$OnTouchListenerC3844n90(this);
    public final C3673m90 a0 = new C3673m90(this);
    public final RunnableC3331k90 b0 = new RunnableC3331k90(this);
    public final Handler c0;
    public final Rect d0 = new Rect();
    public Rect e0;
    public boolean f0;
    public PopupWindow g0;

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                F = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                G = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public AbstractC4186p90(Context context, AttributeSet attributeSet, int i, int i2) {
        this.H = context;
        this.c0 = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.a0, i, i2);
        this.M = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.N = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.P = true;
        }
        obtainStyledAttributes.recycle();
        C4864t8 t8Var = new C4864t8(context, attributeSet, i, i2);
        this.g0 = t8Var;
        t8Var.setInputMethodMode(1);
    }

    @Override // defpackage.AbstractC3386kV0
    public boolean b() {
        return this.g0.isShowing();
    }

    public int c() {
        return this.M;
    }

    @Override // defpackage.AbstractC3386kV0
    public void d() {
        int i;
        int i2;
        C1823bJ bJVar;
        int i3;
        if (this.f11052J == null) {
            C1823bJ q = q(this.H, !this.f0);
            this.f11052J = q;
            q.setAdapter(this.I);
            this.f11052J.setOnItemClickListener(this.X);
            this.f11052J.setFocusable(true);
            this.f11052J.setFocusableInTouchMode(true);
            this.f11052J.setOnItemSelectedListener(new C3160j90(this));
            this.f11052J.setOnScrollListener(this.a0);
            this.g0.setContentView(this.f11052J);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.g0.getContentView();
        }
        Drawable background = this.g0.getBackground();
        int i4 = 0;
        if (background != null) {
            background.getPadding(this.d0);
            Rect rect = this.d0;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.P) {
                this.N = -i5;
            }
        } else {
            this.d0.setEmpty();
            i = 0;
        }
        int maxAvailableHeight = this.g0.getMaxAvailableHeight(this.W, this.N, this.g0.getInputMethodMode() == 2);
        if (this.K == -1) {
            i2 = maxAvailableHeight + i;
        } else {
            int i6 = this.L;
            if (i6 == -2) {
                int i7 = this.H.getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = this.d0;
                i3 = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
            } else if (i6 != -1) {
                i3 = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
            } else {
                int i8 = this.H.getResources().getDisplayMetrics().widthPixels;
                Rect rect3 = this.d0;
                i3 = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), 1073741824);
            }
            int a2 = this.f11052J.a(i3, maxAvailableHeight - 0, -1);
            i2 = a2 + (a2 > 0 ? this.f11052J.getPaddingBottom() + this.f11052J.getPaddingTop() + i + 0 : 0);
        }
        boolean z = this.g0.getInputMethodMode() == 2;
        this.g0.setWindowLayoutType(this.O);
        if (this.g0.isShowing()) {
            View view = this.W;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (view.isAttachedToWindow()) {
                int i9 = this.L;
                if (i9 == -1) {
                    i9 = -1;
                } else if (i9 == -2) {
                    i9 = this.W.getWidth();
                }
                int i10 = this.K;
                if (i10 == -1) {
                    if (!z) {
                        i2 = -1;
                    }
                    if (z) {
                        this.g0.setWidth(this.L == -1 ? -1 : 0);
                        this.g0.setHeight(0);
                    } else {
                        PopupWindow popupWindow = this.g0;
                        if (this.L == -1) {
                            i4 = -1;
                        }
                        popupWindow.setWidth(i4);
                        this.g0.setHeight(-1);
                    }
                } else if (i10 != -2) {
                    i2 = i10;
                }
                this.g0.setOutsideTouchable(true);
                this.g0.update(this.W, this.M, this.N, i9 < 0 ? -1 : i9, i2 < 0 ? -1 : i2);
                return;
            }
            return;
        }
        int i11 = this.L;
        if (i11 == -1) {
            i11 = -1;
        } else if (i11 == -2) {
            i11 = this.W.getWidth();
        }
        int i12 = this.K;
        if (i12 == -1) {
            i2 = -1;
        } else if (i12 != -2) {
            i2 = i12;
        }
        this.g0.setWidth(i11);
        this.g0.setHeight(i2);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = F;
            if (method != null) {
                try {
                    method.invoke(this.g0, Boolean.TRUE);
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            this.g0.setIsClippedToScreen(true);
        }
        this.g0.setOutsideTouchable(true);
        this.g0.setTouchInterceptor(this.Z);
        if (this.R) {
            this.g0.setOverlapAnchor(this.Q);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = G;
            if (method2 != null) {
                try {
                    method2.invoke(this.g0, this.e0);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            this.g0.setEpicenterBounds(this.e0);
        }
        this.g0.showAsDropDown(this.W, this.M, this.N, this.S);
        this.f11052J.setSelection(-1);
        if ((!this.f0 || this.f11052J.isInTouchMode()) && (bJVar = this.f11052J) != null) {
            bJVar.N = true;
            bJVar.requestLayout();
        }
        if (!this.f0) {
            this.c0.post(this.b0);
        }
    }

    @Override // defpackage.AbstractC3386kV0
    public void dismiss() {
        this.g0.dismiss();
        this.g0.setContentView(null);
        this.f11052J = null;
        this.c0.removeCallbacks(this.Y);
    }

    public Drawable e() {
        return this.g0.getBackground();
    }

    @Override // defpackage.AbstractC3386kV0
    public ListView f() {
        return this.f11052J;
    }

    public void h(Drawable drawable) {
        this.g0.setBackgroundDrawable(drawable);
    }

    public void i(int i) {
        this.N = i;
        this.P = true;
    }

    public void k(int i) {
        this.M = i;
    }

    public int m() {
        if (!this.P) {
            return 0;
        }
        return this.N;
    }

    public void p(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.V;
        if (dataSetObserver == null) {
            this.V = new C3502l90(this);
        } else {
            ListAdapter listAdapter2 = this.I;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.I = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.V);
        }
        C1823bJ bJVar = this.f11052J;
        if (bJVar != null) {
            bJVar.setAdapter(this.I);
        }
    }

    public C1823bJ q(Context context, boolean z) {
        return new C1823bJ(context, z);
    }

    public void r(int i) {
        Drawable background = this.g0.getBackground();
        if (background != null) {
            background.getPadding(this.d0);
            Rect rect = this.d0;
            this.L = rect.left + rect.right + i;
            return;
        }
        this.L = i;
    }

    public void s(boolean z) {
        this.f0 = z;
        this.g0.setFocusable(z);
    }
}
