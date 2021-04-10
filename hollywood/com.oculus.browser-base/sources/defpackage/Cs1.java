package defpackage;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;

/* renamed from: Cs1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cs1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Matrix f7845a = new Matrix();
    public final Path b;
    public final Path c;
    public final Matrix d;
    public Paint e;
    public Paint f;
    public PathMeasure g;
    public int h;
    public final C6007zs1 i;
    public float j;
    public float k;
    public float l;
    public float m;
    public int n;
    public String o;
    public Boolean p;
    public final C4931ta q;

    public Cs1() {
        this.d = new Matrix();
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 255;
        this.o = null;
        this.p = null;
        this.q = new C4931ta();
        this.i = new C6007zs1();
        this.b = new Path();
        this.c = new Path();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r13v12, resolved type: android.graphics.PathMeasure */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(defpackage.C6007zs1 r18, android.graphics.Matrix r19, android.graphics.Canvas r20, int r21, int r22, android.graphics.ColorFilter r23) {
        /*
        // Method dump skipped, instructions count: 596
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Cs1.a(zs1, android.graphics.Matrix, android.graphics.Canvas, int, int, android.graphics.ColorFilter):void");
    }

    public float getAlpha() {
        return ((float) getRootAlpha()) / 255.0f;
    }

    public int getRootAlpha() {
        return this.n;
    }

    public void setAlpha(float f2) {
        setRootAlpha((int) (f2 * 255.0f));
    }

    public void setRootAlpha(int i2) {
        this.n = i2;
    }

    public Cs1(Cs1 cs1) {
        this.d = new Matrix();
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 255;
        this.o = null;
        this.p = null;
        C4931ta taVar = new C4931ta();
        this.q = taVar;
        this.i = new C6007zs1(cs1.i, taVar);
        this.b = new Path(cs1.b);
        this.c = new Path(cs1.c);
        this.j = cs1.j;
        this.k = cs1.k;
        this.l = cs1.l;
        this.m = cs1.m;
        this.h = cs1.h;
        this.n = cs1.n;
        this.o = cs1.o;
        String str = cs1.o;
        if (str != null) {
            taVar.put(str, this);
        }
        this.p = cs1.p;
    }
}
