package X;

public abstract class Y4<T> implements eJ<T> {
    public final AbstractC0247Xu A00;
    public final AbstractC0094Hs A01;

    public abstract T A00(AbstractC0247Xu xu);

    @Override // X.eJ
    public final T get() {
        C00103y scopeUnawareInjector = this.A00.getScopeUnawareInjector();
        AbstractC0094Hs hs = this.A01;
        Object A1y = hs.A1y();
        try {
            return A00(scopeUnawareInjector);
        } finally {
            hs.A20(A1y);
        }
    }

    public Y4(AbstractC0247Xu xu) {
        this.A00 = xu;
        this.A01 = xu.getScopeAwareInjector();
    }
}
