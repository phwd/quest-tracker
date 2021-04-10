package X;

import com.google.common.collect.ImmutableList;
import java.util.List;

/* renamed from: X.hz  reason: case insensitive filesystem */
public final class C0811hz extends AbstractC0100Ae implements AbstractC0106Ak {
    public final String A00;
    public final List A01;

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }

    public C0811hz(String str) {
        this.A00 = str;
        this.A01 = ImmutableList.of();
    }

    public C0811hz(String str, List list) {
        this.A00 = str;
        this.A01 = list == null ? ImmutableList.of() : list;
    }
}
