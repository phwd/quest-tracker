package X;

import android.graphics.Matrix;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;

/* renamed from: X.1qM  reason: invalid class name and case insensitive filesystem */
public class C10971qM extends AbstractC11161qj {
    public float A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A02 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A03 = 1.0f;
    public float A04 = 1.0f;
    public float A05 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A06 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public int A07;
    public String A08 = null;
    public int[] A09;
    public final Matrix A0A = new Matrix();
    public final Matrix A0B = new Matrix();
    public final ArrayList<AbstractC11161qj> A0C = new ArrayList<>();

    public static void A00(C10971qM r4) {
        Matrix matrix = r4.A0A;
        matrix.reset();
        matrix.postTranslate(-r4.A00, -r4.A01);
        matrix.postScale(r4.A03, r4.A04);
        matrix.postRotate(r4.A02, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        matrix.postTranslate(r4.A05 + r4.A00, r4.A06 + r4.A01);
    }

    public String getGroupName() {
        return this.A08;
    }

    public Matrix getLocalMatrix() {
        return this.A0A;
    }

    public float getPivotX() {
        return this.A00;
    }

    public float getPivotY() {
        return this.A01;
    }

    public float getRotation() {
        return this.A02;
    }

    public float getScaleX() {
        return this.A03;
    }

    public float getScaleY() {
        return this.A04;
    }

    public float getTranslateX() {
        return this.A05;
    }

    public float getTranslateY() {
        return this.A06;
    }

    public void setPivotX(float f) {
        if (f != this.A00) {
            this.A00 = f;
            A00(this);
        }
    }

    public void setPivotY(float f) {
        if (f != this.A01) {
            this.A01 = f;
            A00(this);
        }
    }

    public void setRotation(float f) {
        if (f != this.A02) {
            this.A02 = f;
            A00(this);
        }
    }

    public void setScaleX(float f) {
        if (f != this.A03) {
            this.A03 = f;
            A00(this);
        }
    }

    public void setScaleY(float f) {
        if (f != this.A04) {
            this.A04 = f;
            A00(this);
        }
    }

    public void setTranslateX(float f) {
        if (f != this.A05) {
            this.A05 = f;
            A00(this);
        }
    }

    public void setTranslateY(float f) {
        if (f != this.A06) {
            this.A06 = f;
            A00(this);
        }
    }

    public C10971qM() {
    }

    public C10971qM(C10971qM r6, C05700wg<String, Object> r7) {
        AbstractC11071qY r1;
        this.A02 = r6.A02;
        this.A00 = r6.A00;
        this.A01 = r6.A01;
        this.A03 = r6.A03;
        this.A04 = r6.A04;
        this.A05 = r6.A05;
        this.A06 = r6.A06;
        this.A09 = r6.A09;
        String str = r6.A08;
        this.A08 = str;
        this.A07 = r6.A07;
        if (str != null) {
            r7.put(str, this);
        }
        this.A0A.set(r6.A0A);
        ArrayList<AbstractC11161qj> arrayList = r6.A0C;
        for (int i = 0; i < arrayList.size(); i++) {
            AbstractC11161qj r2 = arrayList.get(i);
            if (r2 instanceof C10971qM) {
                this.A0C.add(new C10971qM((C10971qM) r2, r7));
            } else {
                if (r2 instanceof AnonymousClass1qU) {
                    r1 = new AnonymousClass1qU((AnonymousClass1qU) r2);
                } else if (r2 instanceof C11151qi) {
                    r1 = new C11151qi((C11151qi) r2);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.A0C.add(r1);
                String str2 = r1.A02;
                if (str2 != null) {
                    r7.put(str2, r1);
                }
            }
        }
    }
}
