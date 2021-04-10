package X;

public abstract class QG {
    public int A00;
    public QF A01;
    public QF A02;
    public Object A03;

    private final Object A01(int i) {
        if (this instanceof C1071rn) {
            return new short[i];
        }
        if (this instanceof C1070rm) {
            return new long[i];
        }
        if (this instanceof C1069rl) {
            return new int[i];
        }
        if (this instanceof C1068rk) {
            return new float[i];
        }
        if (this instanceof C1067rj) {
            return new double[i];
        }
        if (!(this instanceof C1066ri)) {
            return new boolean[i];
        }
        return new byte[i];
    }

    public final Object A00() {
        QF qf = this.A02;
        if (qf != null) {
            this.A03 = qf.A02;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        Object obj = this.A03;
        if (obj == null) {
            return A01(12);
        }
        return obj;
    }

    public final Object A02(Object obj, int i) {
        int i2;
        QF qf = new QF(obj, i);
        if (this.A01 == null) {
            this.A02 = qf;
            this.A01 = qf;
        } else {
            QF qf2 = this.A02;
            if (qf2.A00 == null) {
                qf2.A00 = qf;
                this.A02 = qf;
            } else {
                throw new IllegalStateException();
            }
        }
        this.A00 += i;
        if (i < 16384) {
            i2 = i + i;
        } else {
            i2 = i + (i >> 2);
        }
        return A01(i2);
    }

    public final Object A03(Object obj, int i) {
        int i2 = this.A00 + i;
        Object A012 = A01(i2);
        int i3 = 0;
        for (QF qf = this.A01; qf != null; qf = qf.A00) {
            Object obj2 = qf.A02;
            int i4 = qf.A01;
            System.arraycopy(obj2, 0, A012, i3, i4);
            i3 += i4;
        }
        System.arraycopy(obj, 0, A012, i3, i);
        int i5 = i3 + i;
        if (i5 == i2) {
            return A012;
        }
        throw new IllegalStateException(AnonymousClass08.A02("Should have gotten ", i2, " entries, got ", i5));
    }
}
