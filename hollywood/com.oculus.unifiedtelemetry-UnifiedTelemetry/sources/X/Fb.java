package X;

import com.facebook.infer.annotation.NullsafeStrict;
import javax.annotation.Nullable;

@NullsafeStrict
public final class Fb {
    public final GO A00;
    public final String A01;
    @Nullable
    public final String A02;
    public final boolean A03;

    public Fb(@Nullable String str, String str2, GO go, boolean z) {
        this.A02 = str;
        this.A01 = str2;
        this.A00 = go;
        this.A03 = z;
    }
}
