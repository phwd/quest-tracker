package X;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;

public final class ZZ {
    public final EnumC0455Zk A00;
    public final String A01;
    public final String A02;
    public final List A03;
    public final List A04;
    public final List A05;
    public final List A06;
    public final List A07;
    public final Map A08;

    public final String toString() {
        return AnonymousClass08.A07("App[id=", this.A01, ", packageName=", this.A02, "]");
    }

    public ZZ(String str, String str2, Map map, List list, List list2, String str3, EnumC0455Zk zk, List list3, List list4, List list5) {
        if (!TextUtils.isEmpty(str3)) {
            this.A01 = str;
            this.A02 = str2;
            this.A08 = map;
            this.A05 = list;
            this.A07 = list2;
            this.A00 = zk;
            this.A04 = list3;
            this.A06 = list4;
            this.A03 = list5;
            return;
        }
        throw new IllegalArgumentException("null entitlement hash");
    }
}
