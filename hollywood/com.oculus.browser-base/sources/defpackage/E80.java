package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/* renamed from: E80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E80 {

    /* renamed from: a  reason: collision with root package name */
    public int f7937a = -1;
    public RecyclerView b;
    public IK0 c;
    public boolean d;
    public boolean e;
    public View f;
    public final TK0 g = new TK0(0, 0);
    public boolean h;
    public final LinearInterpolator i = new LinearInterpolator();
    public final DecelerateInterpolator j = new DecelerateInterpolator();
    public PointF k;
    public final DisplayMetrics l;
    public boolean m = false;
    public float n;
    public int o = 0;
    public int p = 0;

    public E80(Context context) {
        this.l = context.getResources().getDisplayMetrics();
    }

    public int a(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == -1) {
            return i4 - i2;
        }
        if (i6 == 0) {
            int i7 = i4 - i2;
            if (i7 > 0) {
                return i7;
            }
            int i8 = i5 - i3;
            if (i8 < 0) {
                return i8;
            }
            return 0;
        } else if (i6 == 1) {
            return i5 - i3;
        } else {
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public float b(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    public int c(int i2) {
        return (int) Math.ceil(((double) d(i2)) / 0.3356d);
    }

    public int d(int i2) {
        float abs = (float) Math.abs(i2);
        if (!this.m) {
            this.n = b(this.l);
            this.m = true;
        }
        return (int) Math.ceil((double) (abs * this.n));
    }

    public PointF e(int i2) {
        IK0 ik0 = this.c;
        if (ik0 instanceof UK0) {
            return ((UK0) ik0).a(i2);
        }
        StringBuilder i3 = AbstractC2531fV.i("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
        i3.append(UK0.class.getCanonicalName());
        Log.w("RecyclerView", i3.toString());
        return null;
    }

    public void f(int i2, int i3) {
        PointF e2;
        RecyclerView recyclerView = this.b;
        int i4 = -1;
        if (this.f7937a == -1 || recyclerView == null) {
            h();
        }
        if (this.d && this.f == null && this.c != null && (e2 = e(this.f7937a)) != null) {
            float f2 = e2.x;
            if (!(f2 == 0.0f && e2.y == 0.0f)) {
                recyclerView.o0((int) Math.signum(f2), (int) Math.signum(e2.y), null);
            }
        }
        boolean z = false;
        this.d = false;
        View view = this.f;
        if (view != null) {
            Objects.requireNonNull(this.b);
            XK0 M = RecyclerView.M(view);
            if (M != null) {
                i4 = M.g();
            }
            if (i4 == this.f7937a) {
                g(this.f, recyclerView.Q0, this.g);
                this.g.a(recyclerView);
                h();
            } else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.f = null;
            }
        }
        if (this.e) {
            VK0 vk0 = recyclerView.Q0;
            TK0 tk0 = this.g;
            if (this.b.U.z() == 0) {
                h();
            } else {
                int i5 = this.o;
                int i6 = i5 - i2;
                if (i5 * i6 <= 0) {
                    i6 = 0;
                }
                this.o = i6;
                int i7 = this.p;
                int i8 = i7 - i3;
                if (i7 * i8 <= 0) {
                    i8 = 0;
                }
                this.p = i8;
                if (i6 == 0 && i8 == 0) {
                    PointF e3 = e(this.f7937a);
                    if (e3 != null) {
                        float f3 = e3.x;
                        if (!(f3 == 0.0f && e3.y == 0.0f)) {
                            float f4 = e3.y;
                            float sqrt = (float) Math.sqrt((double) ((f4 * f4) + (f3 * f3)));
                            float f5 = e3.x / sqrt;
                            e3.x = f5;
                            float f6 = e3.y / sqrt;
                            e3.y = f6;
                            this.k = e3;
                            this.o = (int) (f5 * 10000.0f);
                            this.p = (int) (f6 * 10000.0f);
                            tk0.b((int) (((float) this.o) * 1.2f), (int) (((float) this.p) * 1.2f), (int) (((float) d(10000)) * 1.2f), this.i);
                        }
                    }
                    tk0.d = this.f7937a;
                    h();
                }
            }
            TK0 tk02 = this.g;
            if (tk02.d >= 0) {
                z = true;
            }
            tk02.a(recyclerView);
            if (z && this.e) {
                this.d = true;
                recyclerView.N0.a();
            }
        }
    }

    public void g(View view, VK0 vk0, TK0 tk0) {
        int i2;
        int i3;
        int i4;
        PointF pointF = this.k;
        int i5 = 0;
        int i6 = (pointF == null || pointF.x == 0.0f) ? 0 : i4 > 0 ? 1 : -1;
        IK0 ik0 = this.c;
        if (ik0 == null || !ik0.f()) {
            i2 = 0;
        } else {
            JK0 jk0 = (JK0) view.getLayoutParams();
            i2 = a(ik0.D(view) - ((ViewGroup.MarginLayoutParams) jk0).leftMargin, ik0.G(view) + ((ViewGroup.MarginLayoutParams) jk0).rightMargin, ik0.O(), ik0.p - ik0.P(), i6);
        }
        PointF pointF2 = this.k;
        int i7 = (pointF2 == null || pointF2.y == 0.0f) ? 0 : i3 > 0 ? 1 : -1;
        IK0 ik02 = this.c;
        if (ik02 != null && ik02.g()) {
            JK0 jk02 = (JK0) view.getLayoutParams();
            i5 = a(ik02.H(view) - ((ViewGroup.MarginLayoutParams) jk02).topMargin, ik02.C(view) + ((ViewGroup.MarginLayoutParams) jk02).bottomMargin, ik02.Q(), ik02.q - ik02.N(), i7);
        }
        int c2 = c((int) Math.sqrt((double) ((i5 * i5) + (i2 * i2))));
        if (c2 > 0) {
            tk0.b(-i2, -i5, c2, this.j);
        }
    }

    public final void h() {
        if (this.e) {
            this.e = false;
            this.p = 0;
            this.o = 0;
            this.k = null;
            this.b.Q0.f9076a = -1;
            this.f = null;
            this.f7937a = -1;
            this.d = false;
            IK0 ik0 = this.c;
            if (ik0.g == this) {
                ik0.g = null;
            }
            this.c = null;
            this.b = null;
        }
    }
}
