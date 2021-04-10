package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* renamed from: uT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5088uT0 {
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public float f11411a;
    @Deprecated
    public float b;
    @Deprecated
    public float c;
    @Deprecated
    public float d;
    @Deprecated
    public float e;
    @Deprecated
    public float f;
    public final List g = new ArrayList();
    public final List h = new ArrayList();

    public C5088uT0() {
        d(0.0f, 0.0f, 270.0f, 0.0f);
    }

    public final void a(float f2) {
        float f3 = this.e;
        if (f3 != f2) {
            float f4 = ((f2 - f3) + 360.0f) % 360.0f;
            if (f4 <= 180.0f) {
                float f5 = this.c;
                float f6 = this.d;
                C4408qT0 qt0 = new C4408qT0(f5, f6, f5, f6);
                qt0.g = this.e;
                qt0.h = f4;
                this.h.add(new C4066oT0(qt0));
                this.e = f2;
            }
        }
    }

    public void b(Matrix matrix, Path path) {
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC4748sT0) this.g.get(i)).a(matrix, path);
        }
    }

    public void c(float f2, float f3) {
        C4578rT0 rt0 = new C4578rT0();
        rt0.b = f2;
        rt0.c = f3;
        this.g.add(rt0);
        C4237pT0 pt0 = new C4237pT0(rt0, this.c, this.d);
        a(pt0.b() + 270.0f);
        this.h.add(pt0);
        this.e = pt0.b() + 270.0f;
        this.c = f2;
        this.d = f3;
    }

    public void d(float f2, float f3, float f4, float f5) {
        this.f11411a = f2;
        this.b = f3;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = (f4 + f5) % 360.0f;
        this.g.clear();
        this.h.clear();
    }
}
