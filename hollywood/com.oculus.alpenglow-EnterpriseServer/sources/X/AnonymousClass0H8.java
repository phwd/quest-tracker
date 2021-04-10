package X;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;

/* renamed from: X.0H8  reason: invalid class name */
public class AnonymousClass0H8 {
    public static final Matrix A0G = new Matrix();
    public float A00;
    public float A01;
    public float A02;
    public float A03;
    public int A04;
    public Paint A05;
    public Paint A06;
    public PathMeasure A07;
    public Boolean A08;
    public String A09;
    public int A0A;
    public final Matrix A0B;
    public final Path A0C;
    public final Path A0D;
    public final C03960dj<String, Object> A0E;
    public final AnonymousClass0c4 A0F;

    public float getAlpha() {
        return ((float) this.A04) / 255.0f;
    }

    public int getRootAlpha() {
        return this.A04;
    }

    public void setAlpha(float f) {
        this.A04 = (int) (f * 255.0f);
    }

    public void setRootAlpha(int i) {
        this.A04 = i;
    }

    public AnonymousClass0H8() {
        this.A0B = new Matrix();
        this.A01 = 0.0f;
        this.A00 = 0.0f;
        this.A03 = 0.0f;
        this.A02 = 0.0f;
        this.A04 = 255;
        this.A09 = null;
        this.A08 = null;
        this.A0E = new C03960dj<>();
        this.A0F = new AnonymousClass0c4();
        this.A0C = new Path();
        this.A0D = new Path();
    }

    public AnonymousClass0H8(AnonymousClass0H8 r4) {
        this.A0B = new Matrix();
        this.A01 = 0.0f;
        this.A00 = 0.0f;
        this.A03 = 0.0f;
        this.A02 = 0.0f;
        this.A04 = 255;
        this.A09 = null;
        this.A08 = null;
        C03960dj<String, Object> r2 = new C03960dj<>();
        this.A0E = r2;
        this.A0F = new AnonymousClass0c4(r4.A0F, r2);
        this.A0C = new Path(r4.A0C);
        this.A0D = new Path(r4.A0D);
        this.A01 = r4.A01;
        this.A00 = r4.A00;
        this.A03 = r4.A03;
        this.A02 = r4.A02;
        this.A0A = r4.A0A;
        this.A04 = r4.A04;
        this.A09 = r4.A09;
        String str = r4.A09;
        if (str != null) {
            this.A0E.put(str, this);
        }
        this.A08 = r4.A08;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0406, code lost:
        if (r6.A00 != 0) goto L_0x0408;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.AnonymousClass0H8 r48, X.AnonymousClass0c4 r49, android.graphics.Matrix r50, android.graphics.Canvas r51, int r52, int r53) {
        /*
        // Method dump skipped, instructions count: 1334
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0H8.A00(X.0H8, X.0c4, android.graphics.Matrix, android.graphics.Canvas, int, int):void");
    }
}
