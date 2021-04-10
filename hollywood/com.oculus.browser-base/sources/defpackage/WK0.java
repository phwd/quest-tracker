package defpackage;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: WK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WK0 implements Runnable {
    public int F;
    public int G;
    public OverScroller H;
    public Interpolator I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9140J = false;
    public boolean K = false;
    public final /* synthetic */ RecyclerView L;

    public WK0(RecyclerView recyclerView) {
        this.L = recyclerView;
        Interpolator interpolator = RecyclerView.H;
        this.I = interpolator;
        this.H = new OverScroller(recyclerView.getContext(), interpolator);
    }

    public void a() {
        if (this.f9140J) {
            this.K = true;
            return;
        }
        this.L.removeCallbacks(this);
        RecyclerView recyclerView = this.L;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        recyclerView.postOnAnimation(this);
    }

    public void b(int i, int i2, int i3, Interpolator interpolator) {
        int i4;
        if (i3 == Integer.MIN_VALUE) {
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((double) 0);
            int sqrt2 = (int) Math.sqrt((double) ((i2 * i2) + (i * i)));
            RecyclerView recyclerView = this.L;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i5 = width / 2;
            float f = (float) width;
            float f2 = (float) i5;
            float sin = (((float) Math.sin((double) ((Math.min(1.0f, (((float) sqrt2) * 1.0f) / f) - 0.5f) * 0.47123894f))) * f2) + f2;
            if (sqrt > 0) {
                i4 = Math.round(Math.abs(sin / ((float) sqrt)) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i4 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            i3 = Math.min(i4, 2000);
        }
        if (interpolator == null) {
            interpolator = RecyclerView.H;
        }
        if (this.I != interpolator) {
            this.I = interpolator;
            this.H = new OverScroller(this.L.getContext(), interpolator);
        }
        this.G = 0;
        this.F = 0;
        this.L.u0(2);
        this.H.startScroll(0, 0, i, i2, i3);
        a();
    }

    public void c() {
        this.L.removeCallbacks(this);
        this.H.abortAnimation();
    }

    public void run() {
        int i;
        int i2;
        RecyclerView recyclerView = this.L;
        if (recyclerView.U == null) {
            c();
            return;
        }
        this.K = false;
        this.f9140J = true;
        recyclerView.o();
        OverScroller overScroller = this.H;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i3 = currX - this.F;
            int i4 = currY - this.G;
            this.F = currX;
            this.G = currY;
            RecyclerView recyclerView2 = this.L;
            int[] iArr = recyclerView2.d1;
            iArr[0] = 0;
            iArr[1] = 0;
            if (recyclerView2.u(i3, i4, iArr, null, 1)) {
                int[] iArr2 = this.L.d1;
                i3 -= iArr2[0];
                i4 -= iArr2[1];
            }
            if (this.L.getOverScrollMode() != 2) {
                this.L.n(i3, i4);
            }
            RecyclerView recyclerView3 = this.L;
            if (recyclerView3.T != null) {
                int[] iArr3 = recyclerView3.d1;
                iArr3[0] = 0;
                iArr3[1] = 0;
                recyclerView3.o0(i3, i4, iArr3);
                RecyclerView recyclerView4 = this.L;
                int[] iArr4 = recyclerView4.d1;
                i = iArr4[0];
                i2 = iArr4[1];
                i3 -= i;
                i4 -= i2;
                E80 e80 = recyclerView4.U.g;
                if (e80 != null && !e80.d && e80.e) {
                    int b = recyclerView4.Q0.b();
                    if (b == 0) {
                        e80.h();
                    } else if (e80.f7937a >= b) {
                        e80.f7937a = b - 1;
                        e80.f(i, i2);
                    } else {
                        e80.f(i, i2);
                    }
                }
            } else {
                i2 = 0;
                i = 0;
            }
            if (!this.L.a0.isEmpty()) {
                this.L.invalidate();
            }
            RecyclerView recyclerView5 = this.L;
            int[] iArr5 = recyclerView5.d1;
            iArr5[0] = 0;
            iArr5[1] = 0;
            recyclerView5.v(i, i2, i3, i4, null, 1, iArr5);
            RecyclerView recyclerView6 = this.L;
            int[] iArr6 = recyclerView6.d1;
            int i5 = i3 - iArr6[0];
            int i6 = i4 - iArr6[1];
            if (!(i == 0 && i2 == 0)) {
                recyclerView6.w(i, i2);
            }
            if (!(this.L.awakenScrollBars())) {
                this.L.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
            RecyclerView recyclerView7 = this.L;
            E80 e802 = recyclerView7.U.g;
            if ((e802 != null && e802.d) || !z) {
                a();
                RecyclerView recyclerView8 = this.L;
                RU ru = recyclerView8.O0;
                if (ru != null) {
                    ru.a(recyclerView8, i, i2);
                }
            } else {
                if (recyclerView7.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                    if (i6 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i6 <= 0) {
                        currVelocity = 0;
                    }
                    RecyclerView recyclerView9 = this.L;
                    Objects.requireNonNull(recyclerView9);
                    if (i7 < 0) {
                        recyclerView9.y();
                        if (recyclerView9.u0.isFinished()) {
                            recyclerView9.u0.onAbsorb(-i7);
                        }
                    } else if (i7 > 0) {
                        recyclerView9.z();
                        if (recyclerView9.w0.isFinished()) {
                            recyclerView9.w0.onAbsorb(i7);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView9.A();
                        if (recyclerView9.v0.isFinished()) {
                            recyclerView9.v0.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView9.x();
                        if (recyclerView9.x0.isFinished()) {
                            recyclerView9.x0.onAbsorb(currVelocity);
                        }
                    }
                    if (!(i7 == 0 && currVelocity == 0)) {
                        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                        recyclerView9.postInvalidateOnAnimation();
                    }
                }
                int[] iArr7 = RecyclerView.F;
                PU pu = this.L.P0;
                int[] iArr8 = pu.c;
                if (iArr8 != null) {
                    Arrays.fill(iArr8, -1);
                }
                pu.d = 0;
            }
        }
        E80 e803 = this.L.U.g;
        if (e803 != null && e803.d) {
            e803.f(0, 0);
        }
        this.f9140J = false;
        if (this.K) {
            this.L.removeCallbacks(this);
            RecyclerView recyclerView10 = this.L;
            AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
            recyclerView10.postOnAnimation(this);
            return;
        }
        this.L.u0(0);
        this.L.A0(1);
    }
}
