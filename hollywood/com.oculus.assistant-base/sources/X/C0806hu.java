package X;

import com.google.common.collect.ImmutableList;
import java.util.List;

/* renamed from: X.hu  reason: case insensitive filesystem */
public final class C0806hu extends AbstractC0100Ae implements AbstractC0106Ak {
    public final String A00;
    public final String A01;
    public final List A02;

    public C0806hu(String str, String str2, List list) {
        this.A01 = str;
        this.A00 = str2;
        this.A02 = list == null ? ImmutableList.of() : list;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
