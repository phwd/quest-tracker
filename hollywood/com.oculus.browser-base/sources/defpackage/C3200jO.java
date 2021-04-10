package defpackage;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: jO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3200jO extends EK0 implements LK0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10202a = {16842919};
    public static final int[] b = new int[0];
    public final int[] A = new int[2];
    public final ValueAnimator B;
    public int C;
    public final Runnable D;
    public final MK0 E;
    public final int c;
    public final int d;
    public final StateListDrawable e;
    public final Drawable f;
    public final int g;
    public final int h;
    public final StateListDrawable i;
    public final Drawable j;
    public final int k;
    public final int l;
    public int m;
    public int n;
    public float o;
    public int p;
    public int q;
    public float r;
    public int s = 0;
    public int t = 0;
    public RecyclerView u;
    public boolean v = false;
    public boolean w = false;
    public int x = 0;
    public int y = 0;
    public final int[] z = new int[2];

    public C3200jO(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i3, int i4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.B = ofFloat;
        this.C = 0;
        this.D = new RunnableC2517fO(this);
        C2688gO gOVar = new C2688gO(this);
        this.E = gOVar;
        this.e = stateListDrawable;
        this.f = drawable;
        this.i = stateListDrawable2;
        this.j = drawable2;
        this.g = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.h = Math.max(i2, drawable.getIntrinsicWidth());
        this.k = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.l = Math.max(i2, drawable2.getIntrinsicWidth());
        this.c = i3;
        this.d = i4;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        ofFloat.addListener(new C2859hO(this));
        ofFloat.addUpdateListener(new C3030iO(this));
        RecyclerView recyclerView2 = this.u;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.j0(this);
                RecyclerView recyclerView3 = this.u;
                recyclerView3.b0.remove(this);
                if (recyclerView3.c0 == this) {
                    recyclerView3.c0 = null;
                }
                List list = this.u.S0;
                if (list != null) {
                    list.remove(gOVar);
                }
                j();
            }
            this.u = recyclerView;
            recyclerView.g(this);
            this.u.b0.add(this);
            this.u.i(gOVar);
        }
    }

    @Override // defpackage.LK0
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i2 = this.x;
        if (i2 == 1) {
            boolean l2 = l(motionEvent.getX(), motionEvent.getY());
            boolean k2 = k(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() == 0 && (l2 || k2)) {
                if (k2) {
                    this.y = 1;
                    this.r = (float) ((int) motionEvent.getX());
                } else if (l2) {
                    this.y = 2;
                    this.o = (float) ((int) motionEvent.getY());
                }
                n(2);
                return true;
            }
        } else if (i2 == 2) {
            return true;
        }
        return false;
    }

    @Override // defpackage.LK0
    public void c(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.x != 0) {
            if (motionEvent.getAction() == 0) {
                boolean l2 = l(motionEvent.getX(), motionEvent.getY());
                boolean k2 = k(motionEvent.getX(), motionEvent.getY());
                if (l2 || k2) {
                    if (k2) {
                        this.y = 1;
                        this.r = (float) ((int) motionEvent.getX());
                    } else if (l2) {
                        this.y = 2;
                        this.o = (float) ((int) motionEvent.getY());
                    }
                    n(2);
                }
            } else if (motionEvent.getAction() == 1 && this.x == 2) {
                this.o = 0.0f;
                this.r = 0.0f;
                n(1);
                this.y = 0;
            } else if (motionEvent.getAction() == 2 && this.x == 2) {
                o();
                if (this.y == 1) {
                    float x2 = motionEvent.getX();
                    int[] iArr = this.A;
                    int i2 = this.d;
                    iArr[0] = i2;
                    iArr[1] = this.s - i2;
                    float max = Math.max((float) iArr[0], Math.min((float) iArr[1], x2));
                    if (Math.abs(((float) this.q) - max) >= 2.0f) {
                        int m2 = m(this.r, max, iArr, this.u.computeHorizontalScrollRange(), this.u.computeHorizontalScrollOffset(), this.s);
                        if (m2 != 0) {
                            this.u.scrollBy(m2, 0);
                        }
                        this.r = max;
                    }
                }
                if (this.y == 2) {
                    float y2 = motionEvent.getY();
                    int[] iArr2 = this.z;
                    int i3 = this.d;
                    iArr2[0] = i3;
                    iArr2[1] = this.t - i3;
                    float max2 = Math.max((float) iArr2[0], Math.min((float) iArr2[1], y2));
                    if (Math.abs(((float) this.n) - max2) >= 2.0f) {
                        int m3 = m(this.o, max2, iArr2, this.u.computeVerticalScrollRange(), this.u.computeVerticalScrollOffset(), this.t);
                        if (m3 != 0) {
                            this.u.scrollBy(0, m3);
                        }
                        this.o = max2;
                    }
                }
            }
        }
    }

    @Override // defpackage.LK0
    public void e(boolean z2) {
    }

    @Override // defpackage.EK0
    public void i(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        if (this.s != this.u.getWidth() || this.t != this.u.getHeight()) {
            this.s = this.u.getWidth();
            this.t = this.u.getHeight();
            n(0);
        } else if (this.C != 0) {
            if (this.v) {
                int i2 = this.s;
                int i3 = this.g;
                int i4 = i2 - i3;
                int i5 = this.n;
                int i6 = this.m;
                int i7 = i5 - (i6 / 2);
                this.e.setBounds(0, 0, i3, i6);
                this.f.setBounds(0, 0, this.h, this.t);
                RecyclerView recyclerView2 = this.u;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                boolean z2 = true;
                if (recyclerView2.getLayoutDirection() != 1) {
                    z2 = false;
                }
                if (z2) {
                    this.f.draw(canvas);
                    canvas.translate((float) this.g, (float) i7);
                    canvas.scale(-1.0f, 1.0f);
                    this.e.draw(canvas);
                    canvas.scale(-1.0f, 1.0f);
                    canvas.translate((float) (-this.g), (float) (-i7));
                } else {
                    canvas.translate((float) i4, 0.0f);
                    this.f.draw(canvas);
                    canvas.translate(0.0f, (float) i7);
                    this.e.draw(canvas);
                    canvas.translate((float) (-i4), (float) (-i7));
                }
            }
            if (this.w) {
                int i8 = this.t;
                int i9 = this.k;
                int i10 = i8 - i9;
                int i11 = this.q;
                int i12 = this.p;
                int i13 = i11 - (i12 / 2);
                this.i.setBounds(0, 0, i12, i9);
                this.j.setBounds(0, 0, this.s, this.l);
                canvas.translate(0.0f, (float) i10);
                this.j.draw(canvas);
                canvas.translate((float) i13, 0.0f);
                this.i.draw(canvas);
                canvas.translate((float) (-i13), (float) (-i10));
            }
        }
    }

    public final void j() {
        this.u.removeCallbacks(this.D);
    }

    public boolean k(float f2, float f3) {
        if (f3 >= ((float) (this.t - this.k))) {
            int i2 = this.q;
            int i3 = this.p;
            return f2 >= ((float) (i2 - (i3 / 2))) && f2 <= ((float) ((i3 / 2) + i2));
        }
    }

    public boolean l(float f2, float f3) {
        RecyclerView recyclerView = this.u;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (recyclerView.getLayoutDirection() == 1) {
            if (f2 > ((float) this.g)) {
                return false;
            }
        } else if (f2 < ((float) (this.s - this.g))) {
            return false;
        }
        int i2 = this.n;
        int i3 = this.m / 2;
        return f3 >= ((float) (i2 - i3)) && f3 <= ((float) (i3 + i2));
    }

    public final int m(float f2, float f3, int[] iArr, int i2, int i3, int i4) {
        int i5 = iArr[1] - iArr[0];
        if (i5 == 0) {
            return 0;
        }
        int i6 = i2 - i4;
        int i7 = (int) (((f3 - f2) / ((float) i5)) * ((float) i6));
        int i8 = i3 + i7;
        if (i8 >= i6 || i8 < 0) {
            return 0;
        }
        return i7;
    }

    public void n(int i2) {
        if (i2 == 2 && this.x != 2) {
            this.e.setState(f10202a);
            j();
        }
        if (i2 == 0) {
            this.u.invalidate();
        } else {
            o();
        }
        if (this.x == 2 && i2 != 2) {
            this.e.setState(b);
            j();
            this.u.postDelayed(this.D, (long) 1200);
        } else if (i2 == 1) {
            j();
            this.u.postDelayed(this.D, (long) 1500);
        }
        this.x = i2;
    }

    public void o() {
        int i2 = this.C;
        if (i2 != 0) {
            if (i2 == 3) {
                this.B.cancel();
            } else {
                return;
            }
        }
        this.C = 1;
        ValueAnimator valueAnimator = this.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.B.setDuration(500L);
        this.B.setStartDelay(0);
        this.B.start();
    }
}
