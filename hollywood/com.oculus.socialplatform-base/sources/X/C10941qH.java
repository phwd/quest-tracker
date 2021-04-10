package X;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1qH  reason: invalid class name and case insensitive filesystem */
public class C10941qH {
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
    public final C05700wg<String, Object> A0E;
    public final C10971qM A0F;

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00fd, code lost:
        if (r12.A00 != 0) goto L_0x00ff;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.C10941qH r20, X.C10971qM r21, android.graphics.Matrix r22, android.graphics.Canvas r23, int r24, int r25) {
        /*
        // Method dump skipped, instructions count: 508
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10941qH.A00(X.1qH, X.1qM, android.graphics.Matrix, android.graphics.Canvas, int, int):void");
    }

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

    public C10941qH() {
        this.A0B = new Matrix();
        this.A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A03 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A02 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A04 = MediaProviderUtils.JPEG_HEADER;
        this.A09 = null;
        this.A08 = null;
        this.A0E = new C05700wg<>();
        this.A0F = new C10971qM();
        this.A0C = new Path();
        this.A0D = new Path();
    }

    public C10941qH(C10941qH r4) {
        this.A0B = new Matrix();
        this.A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A03 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A02 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A04 = MediaProviderUtils.JPEG_HEADER;
        this.A09 = null;
        this.A08 = null;
        C05700wg<String, Object> r2 = new C05700wg<>();
        this.A0E = r2;
        this.A0F = new C10971qM(r4.A0F, r2);
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
}
