package defpackage;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;

/* renamed from: zZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5950zZ0 {

    /* renamed from: a  reason: collision with root package name */
    public static final float f11751a = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    public static final float[] b = {2.3E-5f, 0.028561f, 0.057052f, 0.085389f, 0.113496f, 0.141299f, 0.168772f, 0.195811f, 0.222396f, 0.248438f, 0.274002f, 0.298968f, 0.323332f, 0.347096f, 0.370225f, 0.392725f, 0.41457f, 0.435829f, 0.456419f, 0.47641f, 0.495756f, 0.514549f, 0.532721f, 0.550285f, 0.567327f, 0.583811f, 0.599748f, 0.615194f, 0.630117f, 0.644548f, 0.65852f, 0.67204f, 0.6851f, 0.697728f, 0.709951f, 0.721775f, 0.733178f, 0.744231f, 0.754909f, 0.765247f, 0.775225f, 0.784877f, 0.794206f, 0.80323f, 0.811943f, 0.820371f, 0.828519f, 0.836379f, 0.843977f, 0.851323f, 0.858411f, 0.865253f, 0.871853f, 0.878233f, 0.884389f, 0.890316f, 0.896047f, 0.901557f, 0.906874f, 0.911995f, 0.916932f, 0.921675f, 0.926242f, 0.930633f, 0.934848f, 0.938901f, 0.94279f, 0.946522f, 0.950094f, 0.953518f, 0.95679f, 0.959924f, 0.962913f, 0.965762f, 0.968482f, 0.971068f, 0.973523f, 0.975851f, 0.97806f, 0.980149f, 0.982115f, 0.983968f, 0.985709f, 0.987335f, 0.988855f, 0.990269f, 0.991577f, 0.992784f, 0.993891f, 0.994899f, 0.995811f, 0.996627f, 0.997352f, 0.997985f, 0.998529f, 0.998984f, 0.999354f, 0.999639f, 0.99984f, 0.99996f, 1.0f};
    public static final float[] c = {2.0E-6f, 0.003501f, 0.007003f, 0.010507f, 0.014014f, 0.017523f, 0.021044f, 0.024569f, 0.028098f, 0.03164f, 0.035195f, 0.038755f, 0.042337f, 0.045926f, 0.04953f, 0.053156f, 0.056798f, 0.060456f, 0.064138f, 0.067844f, 0.071568f, 0.075316f, 0.079097f, 0.082904f, 0.086737f, 0.090596f, 0.094489f, 0.098416f, 0.102385f, 0.106382f, 0.110422f, 0.114497f, 0.118615f, 0.122783f, 0.126987f, 0.131243f, 0.135549f, 0.1399f, 0.144309f, 0.148776f, 0.153296f, 0.157881f, 0.162519f, 0.16723f, 0.172007f, 0.176851f, 0.181767f, 0.186757f, 0.191835f, 0.196993f, 0.20223f, 0.207555f, 0.212973f, 0.218491f, 0.224109f, 0.229833f, 0.235656f, 0.241598f, 0.247659f, 0.253837f, 0.260147f, 0.266598f, 0.273178f, 0.279912f, 0.286812f, 0.293848f, 0.301075f, 0.308475f, 0.31606f, 0.32384f, 0.331824f, 0.340037f, 0.348487f, 0.357182f, 0.366129f, 0.375349f, 0.384886f, 0.394732f, 0.404901f, 0.415447f, 0.426381f, 0.437738f, 0.449557f, 0.46186f, 0.474729f, 0.488177f, 0.502311f, 0.51715f, 0.532822f, 0.549455f, 0.56713f, 0.586069f, 0.606443f, 0.628536f, 0.652774f, 0.679739f, 0.710244f, 0.745801f, 0.789246f, 0.848082f, 1.0f};
    public int d;
    public int e;
    public int f;
    public int g;
    public float h;
    public float i;
    public long j;
    public int k;
    public int l;
    public int m;
    public boolean n = true;
    public int o;
    public final float p = ViewConfiguration.getScrollFriction();
    public float q = 1.0f;
    public int r;
    public long s;
    public int t;
    public int u = 0;
    public final float v;

    public C5950zZ0(Context context) {
        this.v = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
    }

    public static float f(int i2) {
        return i2 > 0 ? -2000.0f : 2000.0f;
    }

    public final void a(int i2, int i3, int i4) {
        float abs = Math.abs(((float) (i4 - i2)) / ((float) (i3 - i2)));
        int i5 = (int) (abs * 100.0f);
        if (i5 < 100) {
            float f2 = ((float) i5) / 100.0f;
            int i6 = i5 + 1;
            float[] fArr = c;
            float f3 = fArr[i5];
            this.k = (int) (((float) this.k) * AbstractC2531fV.a(fArr[i6], f3, (abs - f2) / ((((float) i6) / 100.0f) - f2), f3));
        }
    }

    public boolean b(long j2) {
        int i2 = this.u;
        if (i2 != 0) {
            if (i2 == 1) {
                return false;
            }
            if (i2 == 2) {
                this.j += (long) this.k;
                n(this.f, this.d);
            }
        } else if (this.k >= this.l) {
            return false;
        } else {
            this.d = this.f;
            int i3 = (int) this.h;
            this.g = i3;
            this.i = f(i3);
            this.j += (long) this.k;
            k();
        }
        o(j2);
        return true;
    }

    public void c() {
        this.e = this.f;
        this.n = true;
    }

    public void d(int i2, int i3, int i4, int i5, int i6, long j2) {
        int i7 = 1;
        if (this.t != 0) {
            float f2 = (float) i3;
            boolean z = Math.signum(f2) == Math.signum(this.h);
            int i8 = 12;
            if (Math.abs(i3) < 100) {
                i7 = 0;
            } else if (Math.abs(i3) >= 1800) {
                if (Math.abs(i3) < 2500) {
                    i7 = 2;
                } else if (Math.abs(i3) >= 5000) {
                    i7 = 12;
                } else {
                    i7 = ((int) (((float) (Math.abs(i3) - 2500)) / 277.0f)) + 3;
                }
            }
            if (i7 == 12 || (j2 < this.s + 1500 && z && Math.abs(i3) > 1000)) {
                this.s = j2;
            } else {
                i8 = i7;
            }
            double d2 = (double) ((-(this.r - (((int) Math.signum(f2)) * i8))) * this.t);
            if (d2 == ((double) this.e)) {
                this.s = 0;
            } else {
                e(i2, (int) d2, j2);
            }
        } else {
            this.o = i6;
            this.n = false;
            this.g = i3;
            float f3 = (float) i3;
            this.h = f3;
            this.l = 0;
            this.k = 0;
            this.j = j2;
            this.d = i2;
            this.e = i2;
            if (i2 <= i5 && i2 >= i4) {
                this.u = 0;
                double d3 = 0.0d;
                if (i3 != 0) {
                    int j3 = j(i3);
                    this.l = j3;
                    this.k = j3;
                    d3 = i(i3);
                }
                int signum = (int) (d3 * ((double) Math.signum(f3)));
                this.m = signum;
                int i9 = i2 + signum;
                this.f = i9;
                if (i9 < i4) {
                    a(this.d, i9, i4);
                    this.f = i4;
                }
                int i10 = this.f;
                if (i10 > i5) {
                    a(this.d, i10, i5);
                    this.f = i5;
                }
            } else if (i2 <= i4 || i2 >= i5) {
                boolean z2 = i2 > i5;
                int i11 = z2 ? i5 : i4;
                int i12 = i2 - i11;
                if (i12 * i3 < 0) {
                    i7 = 0;
                }
                if (i7 != 0) {
                    if (i3 != 0) {
                        i12 = i3;
                    }
                    float f4 = f(i12);
                    this.i = f4;
                    float f5 = ((float) (-i3)) / f4;
                    float sqrt = (float) Math.sqrt((((double) (((((float) (i3 * i3)) / 2.0f) / Math.abs(f4)) + ((float) Math.abs(i11 - i2)))) * 2.0d) / ((double) Math.abs(this.i)));
                    this.j -= (long) ((int) ((sqrt - f5) * 1000.0f));
                    this.d = i11;
                    this.g = (int) ((-this.i) * sqrt);
                    k();
                } else if (i(i3) > ((double) Math.abs(i12))) {
                    d(i2, i3, z2 ? i4 : i2, z2 ? i2 : i5, this.o, j2);
                } else {
                    n(i2, i11);
                }
            } else {
                Log.e("StackScroller", "startAfterEdge called from a valid position");
                this.n = true;
            }
        }
    }

    public void e(int i2, int i3, long j2) {
        this.d = i2;
        this.e = i2;
        this.f = i3;
        this.j = j2;
        int i4 = i3 - i2;
        this.m = i4;
        this.n = false;
        this.o = 0;
        this.u = 0;
        float signum = Math.signum((float) i4);
        double d2 = (double) f11751a;
        float round = (float) ((int) (signum * ((float) ((int) Math.round((Math.exp((Math.log(((double) Math.abs(this.m)) / ((double) (g() * this.v))) * (d2 - 1.0d)) / d2) * ((double) (g() * this.v))) / 0.3499999940395355d)))));
        this.h = round;
        int j3 = j((int) round);
        this.l = j3;
        this.k = j3;
    }

    public final float g() {
        return this.p * this.q;
    }

    public final double h(int i2) {
        return Math.log((double) ((((float) Math.abs(i2)) * 0.35f) / (g() * this.v)));
    }

    public final double i(int i2) {
        double h2 = h(i2);
        float f2 = f11751a;
        return Math.exp((((double) f2) / (((double) f2) - 1.0d)) * h2) * ((double) (g() * this.v));
    }

    public final int j(int i2) {
        return (int) (Math.exp(h(i2) / (((double) f11751a) - 1.0d)) * 1000.0d);
    }

    public final void k() {
        int i2 = this.g;
        float abs = ((float) (i2 * i2)) / (Math.abs(this.i) * 2.0f);
        float signum = Math.signum((float) this.g);
        int i3 = this.o;
        if (abs > ((float) i3)) {
            float f2 = -signum;
            int i4 = this.g;
            this.i = ((f2 * ((float) i4)) * ((float) i4)) / (((float) i3) * 2.0f);
            abs = (float) i3;
        }
        this.o = (int) abs;
        this.u = 2;
        int i5 = this.d;
        int i6 = this.g;
        if (i6 <= 0) {
            abs = -abs;
        }
        this.f = i5 + ((int) abs);
        this.k = -((int) ((((float) i6) * 1000.0f) / this.i));
    }

    public boolean l(int i2, int i3, int i4, long j2) {
        this.n = true;
        this.f = i2;
        this.d = i2;
        this.g = 0;
        this.j = j2;
        this.k = 0;
        if (i2 < i3) {
            n(i2, i3);
        } else if (i2 > i4) {
            n(i2, i4);
        }
        return !this.n;
    }

    public void m(int i2, int i3, long j2, int i4) {
        this.n = false;
        this.d = i2;
        this.f = i2 + i3;
        this.j = j2;
        this.k = i4;
        this.i = 0.0f;
        this.g = 0;
    }

    public final void n(int i2, int i3) {
        this.n = false;
        this.u = 1;
        this.d = i2;
        this.f = i3;
        int i4 = i2 - i3;
        this.i = f(i4);
        this.g = -i4;
        this.o = Math.abs(i4);
        this.k = (int) (Math.sqrt((((double) i4) * -2.0d) / ((double) this.i)) * 1000.0d);
    }

    public boolean o(long j2) {
        long j3 = j2 - this.j;
        int i2 = this.k;
        if (j3 > ((long) i2)) {
            return false;
        }
        double d2 = 0.0d;
        int i3 = this.u;
        if (i3 == 0) {
            int i4 = this.l;
            float f2 = ((float) j3) / ((float) i4);
            int i5 = (int) (f2 * 100.0f);
            float f3 = 1.0f;
            float f4 = 0.0f;
            if (i5 < 100) {
                float f5 = ((float) i5) / 100.0f;
                int i6 = i5 + 1;
                float[] fArr = b;
                float f6 = fArr[i5];
                f4 = (fArr[i6] - f6) / ((((float) i6) / 100.0f) - f5);
                f3 = AbstractC2531fV.a(f2, f5, f4, f6);
            }
            int i7 = this.m;
            d2 = (double) (f3 * ((float) i7));
            this.h = ((f4 * ((float) i7)) / ((float) i4)) * 1000.0f;
        } else if (i3 == 1) {
            float f7 = ((float) j3) / ((float) i2);
            float f8 = f7 * f7;
            float signum = Math.signum((float) this.g);
            int i8 = this.o;
            float f9 = (3.0f * f8) - ((2.0f * f7) * f8);
            this.h = ((-f7) + f8) * signum * ((float) i8) * 6.0f;
            d2 = (double) (f9 * ((float) i8) * signum);
        } else if (i3 == 2) {
            float f10 = ((float) j3) / 1000.0f;
            int i9 = this.g;
            float f11 = this.i;
            this.h = (f11 * f10) + ((float) i9);
            d2 = (double) ((((f11 * f10) * f10) / 2.0f) + (((float) i9) * f10));
        }
        this.e = this.d + ((int) Math.round(d2));
        return true;
    }
}
