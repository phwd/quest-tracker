package X;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0kQ  reason: invalid class name */
public final class AnonymousClass0kQ {
    public long A00 = 0;
    @Nullable
    public AnonymousClass0kO A01;
    public ArrayList<String> A02 = new ArrayList<>();
    public ArrayList<String> A03 = new ArrayList<>();
    public Map<C02920k8, Set<String>> A04 = new HashMap();

    public final AnonymousClass0kR A00() {
        if (this.A01 == null || this.A04.isEmpty()) {
            if (!this.A04.isEmpty()) {
                this.A01 = new AnonymousClass0kO(this.A04);
            }
            return new AnonymousClass0kR(this);
        }
        throw new IllegalArgumentException("TrustedCaller needs to be configured with either a TrustedApp or list of trusted packages");
    }
}
