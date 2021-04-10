package X;

import java.lang.reflect.Array;

public final class QE {
    public int A00;
    public QD A01;
    public QD A02;
    public Object[] A03;

    public static final void A00(QE qe, Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (QD qd = qe.A01; qd != null; qd = qd.A00) {
            Object[] objArr2 = qd.A01;
            int length = objArr2.length;
            System.arraycopy(objArr2, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            throw new IllegalStateException(AnonymousClass08.A02("Should have gotten ", i, " entries, got ", i4));
        }
    }

    public final Object[] A01() {
        QD qd = this.A02;
        if (qd != null) {
            this.A03 = qd.A01;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        Object[] objArr = this.A03;
        if (objArr == null) {
            return new Object[12];
        }
        return objArr;
    }

    public final Object[] A02(Object[] objArr) {
        int i;
        QD qd = new QD(objArr);
        if (this.A01 == null) {
            this.A02 = qd;
            this.A01 = qd;
        } else {
            QD qd2 = this.A02;
            if (qd2.A00 == null) {
                qd2.A00 = qd;
                this.A02 = qd;
            } else {
                throw new IllegalStateException();
            }
        }
        int length = objArr.length;
        this.A00 += length;
        if (length < 16384) {
            i = length + length;
        } else {
            i = length + (length >> 2);
        }
        return new Object[i];
    }

    public final Object[] A03(Object[] objArr, int i, Class cls) {
        int i2 = this.A00 + i;
        Object[] objArr2 = (Object[]) Array.newInstance(cls, i2);
        A00(this, objArr2, i2, objArr, i);
        QD qd = this.A02;
        if (qd != null) {
            this.A03 = qd.A01;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        return objArr2;
    }
}
