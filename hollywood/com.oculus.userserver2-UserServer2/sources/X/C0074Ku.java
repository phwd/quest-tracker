package X;

import java.sql.Timestamp;
import java.util.Date;

/* renamed from: X.Ku  reason: case insensitive filesystem */
public class C0074Ku implements AbstractC0237hg {
    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        if (h6Var.rawType != Timestamp.class) {
            return null;
        }
        return new C0075Kv(this, hrVar.A03(new h6<>(Date.class)));
    }
}
