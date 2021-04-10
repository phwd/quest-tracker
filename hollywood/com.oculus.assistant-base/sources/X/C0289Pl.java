package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.Pl  reason: case insensitive filesystem */
public final class C0289Pl {
    public final NN A00;
    public final C1015qj A01;
    public final AbstractC1024qt A02;
    public final JsonSerializer A03;
    public final boolean A04;

    public static C0289Pl A00(AbstractC1024qt qtVar, String str, NN nn, boolean z) {
        C1015qj qjVar;
        if (str == null) {
            qjVar = null;
        } else {
            qjVar = new C1015qj(str);
        }
        return new C0289Pl(qtVar, qjVar, nn, null, z);
    }

    public C0289Pl(AbstractC1024qt qtVar, C1015qj qjVar, NN nn, JsonSerializer jsonSerializer, boolean z) {
        this.A02 = qtVar;
        this.A01 = qjVar;
        this.A00 = nn;
        this.A03 = jsonSerializer;
        this.A04 = z;
    }
}
