package X;

import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.fq  reason: case insensitive filesystem */
public final class C0223fq {
    @Nullable
    public final C0221fn A00;
    public final ArrayList<String> A01;
    public final ArrayList<String> A02;

    public C0223fq(C0222fp fpVar) {
        C0221fn fnVar = fpVar.A00;
        this.A00 = fnVar;
        this.A01 = fpVar.A01;
        ArrayList<String> arrayList = fpVar.A02;
        this.A02 = arrayList;
        if (fnVar == null && arrayList.isEmpty()) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }
}
