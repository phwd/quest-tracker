package X;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0iz  reason: invalid class name and case insensitive filesystem */
public final class C05300iz {
    public long A00 = 0;
    @Nullable
    public C05280ix A01;
    public ArrayList<String> A02 = new ArrayList<>();
    public ArrayList<String> A03 = new ArrayList<>();
    public Map<C05140ii, Set<String>> A04 = new HashMap();

    public final AnonymousClass0j0 A00() {
        if (this.A01 == null || this.A04.isEmpty()) {
            if (!this.A04.isEmpty()) {
                this.A01 = new C05280ix(this.A04);
            }
            return new AnonymousClass0j0(this);
        }
        throw new IllegalArgumentException("TrustedCaller needs to be configured with either a TrustedApp or list of trusted packages");
    }
}
