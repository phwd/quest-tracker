package defpackage;

import android.graphics.Color;
import java.util.Arrays;

/* renamed from: dw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2266dw0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9819a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public boolean f;
    public int g;
    public int h;
    public float[] i;

    public C2266dw0(int i2, int i3) {
        this.f9819a = Color.red(i2);
        this.b = Color.green(i2);
        this.c = Color.blue(i2);
        this.d = i2;
        this.e = i3;
    }

    public final void a() {
        int i2;
        int i3;
        if (!this.f) {
            int d2 = AbstractC1331Vv.d(-1, this.d, 4.5f);
            int d3 = AbstractC1331Vv.d(-1, this.d, 3.0f);
            if (d2 == -1 || d3 == -1) {
                int d4 = AbstractC1331Vv.d(-16777216, this.d, 4.5f);
                int d5 = AbstractC1331Vv.d(-16777216, this.d, 3.0f);
                if (d4 == -1 || d5 == -1) {
                    if (d2 != -1) {
                        i2 = AbstractC1331Vv.h(-1, d2);
                    } else {
                        i2 = AbstractC1331Vv.h(-16777216, d4);
                    }
                    this.h = i2;
                    if (d3 != -1) {
                        i3 = AbstractC1331Vv.h(-1, d3);
                    } else {
                        i3 = AbstractC1331Vv.h(-16777216, d5);
                    }
                    this.g = i3;
                    this.f = true;
                    return;
                }
                this.h = AbstractC1331Vv.h(-16777216, d4);
                this.g = AbstractC1331Vv.h(-16777216, d5);
                this.f = true;
                return;
            }
            this.h = AbstractC1331Vv.h(-1, d2);
            this.g = AbstractC1331Vv.h(-1, d3);
            this.f = true;
        }
    }

    public float[] b() {
        if (this.i == null) {
            this.i = new float[3];
        }
        AbstractC1331Vv.a(this.f9819a, this.b, this.c, this.i);
        return this.i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2266dw0.class != obj.getClass()) {
            return false;
        }
        C2266dw0 dw0 = (C2266dw0) obj;
        return this.e == dw0.e && this.d == dw0.d;
    }

    public int hashCode() {
        return (this.d * 31) + this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(C2266dw0.class.getSimpleName());
        sb.append(" [RGB: #");
        sb.append(Integer.toHexString(this.d));
        sb.append(']');
        sb.append(" [HSL: ");
        sb.append(Arrays.toString(b()));
        sb.append(']');
        sb.append(" [Population: ");
        sb.append(this.e);
        sb.append(']');
        sb.append(" [Title Text: #");
        a();
        sb.append(Integer.toHexString(this.g));
        sb.append(']');
        sb.append(" [Body Text: #");
        a();
        sb.append(Integer.toHexString(this.h));
        sb.append(']');
        return sb.toString();
    }
}
