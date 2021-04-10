package X;

import com.google.android.gms.common.Feature;

/* renamed from: X.Ql  reason: case insensitive filesystem */
public final class C0314Ql extends UnsupportedOperationException {
    public final Feature zza;

    public final String getMessage() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
        sb.append("Missing ");
        sb.append(valueOf);
        return sb.toString();
    }

    public C0314Ql(Feature feature) {
        this.zza = feature;
    }
}
