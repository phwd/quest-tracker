package defpackage;

import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import android.util.Rational;

/* renamed from: st1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4820st1 implements Runnable {
    public final C4650rt1 F;
    public final /* synthetic */ C5330vt1 G;

    public RunnableC4820st1(C5330vt1 vt1, C4650rt1 rt1) {
        this.G = vt1;
        this.F = rt1;
    }

    public void run() {
        Rect rect;
        CameraCharacteristics i = C5330vt1.i(this.G.d);
        Rect rect2 = (Rect) i.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        double d = this.F.f11231a;
        if (d != 0.0d) {
            float max = Math.max(1.0f, Math.min((float) d, this.G.r));
            float f = (max - 1.0f) / (max * 2.0f);
            float f2 = 1.0f - f;
            this.G.s = new Rect(Math.round(((float) rect2.width()) * f), Math.round(((float) rect2.height()) * f), Math.round(((float) rect2.width()) * f2), Math.round(((float) rect2.height()) * f2));
            this.G.s.toString();
        }
        C4650rt1 rt1 = this.F;
        int i2 = rt1.b;
        if (i2 != 0) {
            this.G.v = i2;
        }
        double d2 = rt1.c;
        if (d2 != 0.0d) {
            this.G.w = (float) d2;
        }
        int i3 = rt1.d;
        if (i3 != 0) {
            this.G.x = i3;
        }
        double d3 = rt1.j;
        if (d3 != 0.0d) {
            this.G.y = (long) d3;
        }
        int i4 = rt1.k;
        if (i4 != 0) {
            this.G.B = i4;
        }
        double d4 = rt1.e;
        if (d4 > 0.0d) {
            this.G.t = (int) Math.round(d4);
        }
        double d5 = this.F.f;
        if (d5 > 0.0d) {
            this.G.u = (int) Math.round(d5);
        }
        MeteringRectangle meteringRectangle = this.G.z;
        if (meteringRectangle != null && !meteringRectangle.getRect().isEmpty() && this.F.f11231a > 0.0d) {
            this.G.z = null;
        }
        C5330vt1 vt1 = this.G;
        if (vt1.v == 1 || vt1.x == 1) {
            vt1.z = null;
        }
        if ((((Integer) i.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0 || ((Integer) i.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0 || ((Integer) i.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB)).intValue() > 0) && this.F.g.length > 0) {
            if (this.G.s.isEmpty()) {
                rect = rect2;
            } else {
                rect = this.G.s;
            }
            int round = (int) Math.round(this.F.g[0] * ((double) rect.width()));
            int round2 = (int) Math.round(this.F.g[1] * ((double) rect.height()));
            if (rect.equals(this.G.s)) {
                round += (rect2.width() - rect.width()) / 2;
                round2 += (rect2.height() - rect.height()) / 2;
            }
            int width = rect.width() / 8;
            int height = rect.height() / 8;
            this.G.z = new MeteringRectangle(Math.max(0, round - (width / 2)), Math.max(0, round2 - (height / 2)), width, height, 1000);
            double[] dArr = this.F.g;
            double d6 = dArr[0];
            double d7 = dArr[1];
            rect.toString();
            rect2.toString();
            this.G.z.toString();
        }
        C4650rt1 rt12 = this.F;
        if (rt12.h) {
            this.G.A = (int) Math.round(rt12.i / ((double) ((Rational) i.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP)).floatValue()));
        }
        double d8 = this.F.l;
        if (d8 > 0.0d) {
            this.G.D = (int) Math.round(d8);
        }
        double d9 = this.F.r;
        if (d9 > 0.0d) {
            this.G.C = (int) Math.round(d9);
        }
        C4650rt1 rt13 = this.F;
        if (rt13.m) {
            this.G.E = rt13.n;
        }
        int i5 = rt13.o;
        if (i5 != 0) {
            this.G.F = i5;
        }
        if (rt13.p) {
            this.G.G = rt13.q;
        }
        C5330vt1 vt12 = this.G;
        if (vt12.j != null) {
            vt12.g(vt12.l);
            C5330vt1 vt13 = this.G;
            vt13.k = vt13.l.build();
            try {
                C5330vt1 vt14 = this.G;
                vt14.j.setRepeatingRequest(vt14.k, null, null);
            } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                AbstractC1220Ua0.a("VideoCapture", "setRepeatingRequest: ", e);
            }
        }
    }
}
