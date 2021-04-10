package X;

import java.io.Serializable;

/* renamed from: X.jx  reason: case insensitive filesystem */
public final class C0849jx implements AbstractC0464a8, Serializable {
    public volatile Object _value = C0477aM.A00;
    public AbstractC0496aj initializer;
    public final Object lock = this;

    public /* synthetic */ C0849jx(AbstractC0496aj ajVar) {
        C0514bB.A02(ajVar, "initializer");
        this.initializer = ajVar;
    }

    @Override // X.AbstractC0464a8
    public final Object getValue() {
        Object obj;
        Object obj2 = this._value;
        C0477aM aMVar = C0477aM.A00;
        if (obj2 != aMVar) {
            return obj2;
        }
        synchronized (this.lock) {
            obj = this._value;
            if (obj == aMVar) {
                AbstractC0496aj ajVar = this.initializer;
                C0514bB.A00(ajVar);
                obj = ajVar.A3M();
                this._value = obj;
                this.initializer = null;
            }
        }
        return obj;
    }

    public final String toString() {
        if (this._value != C0477aM.A00) {
            return String.valueOf(getValue());
        }
        return "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new C0983pz(getValue());
    }
}
