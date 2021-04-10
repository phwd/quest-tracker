package X;

import javax.annotation.Nullable;

public abstract class I1<T> implements AbstractC0246Xt<T> {
    public T A00;
    public final byte A01 = Qe.A01.get().A00;
    public final AbstractC0247Xu A02;
    @Nullable
    public volatile Y0 A03;

    public abstract T A00(AbstractC0247Xu xu);

    @Override // X.eJ, X.AbstractC0246Xt
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    Y0 y0 = this.A03;
                    Qe qe = Qe.A01.get();
                    byte b = this.A01;
                    byte b2 = qe.A00;
                    qe.A00 = (byte) (b | b2);
                    Object A1y = y0.A1y();
                    try {
                        this.A00 = A00(this.A02.getScopeUnawareInjector());
                        this.A03 = null;
                    } finally {
                        y0.A20(A1y);
                        qe.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public I1(AbstractC0247Xu xu) {
        this.A02 = xu;
        this.A03 = xu.getScopeAwareInjector();
    }
}
