package X;

public final class LF implements AbstractC0237hg {
    public final AbstractC0242hn<?> A00;
    public final Class<?> A01;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;LX/h6<*>;ZLjava/lang/Class<*>;)V */
    public LF(Object obj) {
        AbstractC0242hn<?> hnVar = obj instanceof AbstractC0242hn ? (AbstractC0242hn) obj : null;
        this.A00 = hnVar;
        C0236ha.A00(hnVar != null);
        this.A01 = Object.class;
    }

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        if (this.A01.isAssignableFrom(h6Var.rawType)) {
            return new LE(this.A00, hrVar, h6Var, this);
        }
        return null;
    }
}
