package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.23g  reason: invalid class name and case insensitive filesystem */
public final class C144423g {
    public final String A00;
    public volatile C141521z A01;

    public C144423g(C141521z r2, String str) {
        if (r2 == null) {
            throw null;
        } else if (str != null) {
            this.A01 = r2;
            this.A00 = str;
        } else {
            throw null;
        }
    }
}
