package X;

import androidx.annotation.NonNull;

public final class TT extends CS {
    @NonNull
    public final TU A00;
    @NonNull
    public final Bs A01;

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Bs bs = this.A01;
        sb.append(bs.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(bs)));
        sb.append("}}");
        return sb.toString();
    }

    public TT(@NonNull Bs bs, @NonNull CB cb) {
        this.A01 = bs;
        this.A00 = (TU) new CA(cb, TU.A01).A00(TU.class);
    }
}
