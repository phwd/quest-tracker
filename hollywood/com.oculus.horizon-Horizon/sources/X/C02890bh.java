package X;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0bh  reason: invalid class name and case insensitive filesystem */
public final class C02890bh {
    public long A00 = 0;
    @Nullable
    public C02870bf A01;
    public ArrayList<String> A02 = new ArrayList<>();
    public ArrayList<String> A03 = new ArrayList<>();
    public Map<AnonymousClass0bQ, Set<String>> A04 = new HashMap();

    public final C02900bi A00() {
        if (this.A01 == null || this.A04.isEmpty()) {
            if (!this.A04.isEmpty()) {
                this.A01 = new C02870bf(this.A04);
            }
            return new C02900bi(this);
        }
        throw new IllegalArgumentException("TrustedCaller needs to be configured with either a TrustedApp or list of trusted packages");
    }
}
