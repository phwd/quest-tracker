package X;

import android.graphics.Matrix;
import java.util.ArrayList;

/* renamed from: X.0c4  reason: invalid class name */
public class AnonymousClass0c4 extends AnonymousClass0H7 {
    public float A00 = 0.0f;
    public float A01 = 0.0f;
    public float A02 = 0.0f;
    public float A03 = 1.0f;
    public float A04 = 1.0f;
    public float A05 = 0.0f;
    public float A06 = 0.0f;
    public int A07;
    public String A08 = null;
    public int[] A09;
    public final Matrix A0A = new Matrix();
    public final Matrix A0B = new Matrix();
    public final ArrayList<AnonymousClass0H7> A0C = new ArrayList<>();

    @Override // X.AnonymousClass0H7
    public final boolean A01() {
        int i = 0;
        while (true) {
            ArrayList<AnonymousClass0H7> arrayList = this.A0C;
            if (i >= arrayList.size()) {
                return false;
            }
            if (arrayList.get(i).A01()) {
                return true;
            }
            i++;
        }
    }

    @Override // X.AnonymousClass0H7
    public final boolean A02(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (true) {
            ArrayList<AnonymousClass0H7> arrayList = this.A0C;
            if (i >= arrayList.size()) {
                return z;
            }
            z |= arrayList.get(i).A02(iArr);
            i++;
        }
    }

    public static void A00(AnonymousClass0c4 r4) {
        Matrix matrix = r4.A0A;
        matrix.reset();
        matrix.postTranslate(-r4.A00, -r4.A01);
        matrix.postScale(r4.A03, r4.A04);
        matrix.postRotate(r4.A02, 0.0f, 0.0f);
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

    public AnonymousClass0c4() {
    }

    public AnonymousClass0c4(AnonymousClass0c4 r6, C03960dj<String, Object> r7) {
        AbstractC03330c3 r1;
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
        ArrayList<AnonymousClass0H7> arrayList = r6.A0C;
        for (int i = 0; i < arrayList.size(); i++) {
            AnonymousClass0H7 r2 = arrayList.get(i);
            if (r2 instanceof AnonymousClass0c4) {
                this.A0C.add(new AnonymousClass0c4((AnonymousClass0c4) r2, r7));
            } else {
                if (r2 instanceof AnonymousClass0M0) {
                    r1 = new AnonymousClass0M0((AnonymousClass0M0) r2);
                } else if (r2 instanceof AnonymousClass0M1) {
                    r1 = new AnonymousClass0M1((AnonymousClass0M1) r2);
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
