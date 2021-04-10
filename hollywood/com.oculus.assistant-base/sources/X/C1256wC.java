package X;

import android.os.Bundle;
import java.util.List;

/* renamed from: X.wC  reason: case insensitive filesystem */
public final class C1256wC implements AbstractC0106Ak {
    public Bundle A00;
    public String A01;
    public List A02;

    public C1256wC(String str, Bundle bundle, List list) {
        C0514bB.A02(str, "messageType");
        C0514bB.A02(bundle, "messageData");
        C0514bB.A02(list, "subscriptions");
        this.A01 = str;
        this.A00 = bundle;
        this.A02 = list;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
