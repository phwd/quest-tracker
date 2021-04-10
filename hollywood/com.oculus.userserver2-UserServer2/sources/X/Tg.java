package X;

import androidx.annotation.NonNull;

public final class Tg implements Bs {
    public Tc A00 = null;

    @Override // X.Bs
    @NonNull
    public final AbstractC0041Bq getLifecycle() {
        Tc tc = this.A00;
        if (tc != null) {
            return tc;
        }
        Tc tc2 = new Tc(this);
        this.A00 = tc2;
        return tc2;
    }
}
