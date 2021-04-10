package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.oculus.browser.R;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Ll1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ll1 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static Ll1 F;
    public static Ll1 G;
    public final View H;
    public final CharSequence I;

    /* renamed from: J  reason: collision with root package name */
    public final int f8437J;
    public final Runnable K = new Jl1(this);
    public final Runnable L = new Kl1(this);
    public int M;
    public int N;
    public Ml1 O;
    public boolean P;

    public Ll1(View view, CharSequence charSequence) {
        int i;
        this.H = view;
        this.I = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        Method method = AbstractC2091cu1.f9729a;
        if (Build.VERSION.SDK_INT >= 28) {
            i = viewConfiguration.getScaledHoverSlop();
        } else {
            i = viewConfiguration.getScaledTouchSlop() / 2;
        }
        this.f8437J = i;
        a();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void c(Ll1 ll1) {
        Ll1 ll12 = F;
        if (ll12 != null) {
            ll12.H.removeCallbacks(ll12.K);
        }
        F = ll1;
        if (ll1 != null) {
            ll1.H.postDelayed(ll1.K, (long) ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        this.M = Integer.MAX_VALUE;
        this.N = Integer.MAX_VALUE;
    }

    public void b() {
        if (G == this) {
            G = null;
            Ml1 ml1 = this.O;
            if (ml1 != null) {
                ml1.a();
                this.O = null;
                a();
                this.H.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (F == this) {
            c(null);
        }
        this.H.removeCallbacks(this.L);
    }

    public void d(boolean z) {
        int i;
        int i2;
        long j;
        int i3;
        long j2;
        View view = this.H;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (view.isAttachedToWindow()) {
            c(null);
            Ll1 ll1 = G;
            if (ll1 != null) {
                ll1.b();
            }
            G = this;
            this.P = z;
            Ml1 ml1 = new Ml1(this.H.getContext());
            this.O = ml1;
            View view2 = this.H;
            int i4 = this.M;
            int i5 = this.N;
            boolean z2 = this.P;
            CharSequence charSequence = this.I;
            if (ml1.b.getParent() != null) {
                ml1.a();
            }
            ml1.c.setText(charSequence);
            WindowManager.LayoutParams layoutParams = ml1.d;
            layoutParams.token = view2.getApplicationWindowToken();
            int dimensionPixelOffset = ml1.f8501a.getResources().getDimensionPixelOffset(R.dimen.f26510_resource_name_obfuscated_RES_2131166270);
            if (view2.getWidth() < dimensionPixelOffset) {
                i4 = view2.getWidth() / 2;
            }
            if (view2.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = ml1.f8501a.getResources().getDimensionPixelOffset(R.dimen.f26500_resource_name_obfuscated_RES_2131166269);
                i = i5 + dimensionPixelOffset2;
                i2 = i5 - dimensionPixelOffset2;
            } else {
                i = view2.getHeight();
                i2 = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = ml1.f8501a.getResources().getDimensionPixelOffset(z2 ? R.dimen.f26540_resource_name_obfuscated_RES_2131166273 : R.dimen.f26530_resource_name_obfuscated_RES_2131166272);
            View rootView = view2.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                Context context = view2.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else if (context instanceof Activity) {
                        rootView = ((Activity) context).getWindow().getDecorView();
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
            } else {
                rootView.getWindowVisibleDisplayFrame(ml1.e);
                Rect rect = ml1.e;
                if (rect.left < 0 && rect.top < 0) {
                    Resources resources = ml1.f8501a.getResources();
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    ml1.e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen(ml1.g);
                view2.getLocationOnScreen(ml1.f);
                int[] iArr = ml1.f;
                int i6 = iArr[0];
                int[] iArr2 = ml1.g;
                iArr[0] = i6 - iArr2[0];
                iArr[1] = iArr[1] - iArr2[1];
                layoutParams.x = (iArr[0] + i4) - (rootView.getWidth() / 2);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                ml1.b.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredHeight = ml1.b.getMeasuredHeight();
                int[] iArr3 = ml1.f;
                int i7 = ((iArr3[1] + i2) - dimensionPixelOffset3) - measuredHeight;
                int i8 = iArr3[1] + i + dimensionPixelOffset3;
                if (z2) {
                    if (i7 >= 0) {
                        layoutParams.y = i7;
                    } else {
                        layoutParams.y = i8;
                    }
                } else if (measuredHeight + i8 <= ml1.e.height()) {
                    layoutParams.y = i8;
                } else {
                    layoutParams.y = i7;
                }
            }
            ((WindowManager) ml1.f8501a.getSystemService("window")).addView(ml1.b, ml1.d);
            this.H.addOnAttachStateChangeListener(this);
            if (this.P) {
                j = 2500;
            } else {
                if ((this.H.getWindowSystemUiVisibility() & 1) == 1) {
                    j2 = 3000;
                    i3 = ViewConfiguration.getLongPressTimeout();
                } else {
                    j2 = 15000;
                    i3 = ViewConfiguration.getLongPressTimeout();
                }
                j = j2 - ((long) i3);
            }
            this.H.removeCallbacks(this.L);
            this.H.postDelayed(this.L, j);
        }
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        boolean z;
        if (this.O != null && this.P) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.H.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                a();
                b();
            }
        } else if (this.H.isEnabled() && this.O == null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (Math.abs(x - this.M) > this.f8437J || Math.abs(y - this.N) > this.f8437J) {
                this.M = x;
                this.N = y;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                c(this);
            }
        }
        return false;
    }

    public boolean onLongClick(View view) {
        this.M = view.getWidth() / 2;
        this.N = view.getHeight() / 2;
        d(true);
        return true;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
