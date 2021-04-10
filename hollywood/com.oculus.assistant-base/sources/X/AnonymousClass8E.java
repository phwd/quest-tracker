package X;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.8E  reason: invalid class name */
public final class AnonymousClass8E {
    public AnonymousClass8D A00 = AnonymousClass8D.FOREGROUND;
    public AnonymousClass8U A01;
    public String A02;
    public String A03;
    public List A04 = new ArrayList();
    public byte[] A05;

    public final AnonymousClass8F A00() {
        if (this.A03 != null) {
            return new AnonymousClass8F(this);
        }
        throw null;
    }

    public final void A01(String str) {
        if (str != null) {
            CT.A00(!TextUtils.isEmpty(str));
            this.A04.add(str);
            return;
        }
        throw null;
    }
}
