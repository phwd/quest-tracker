package X;

import com.fasterxml.jackson.databind.JsonSerializer;

public final class MP extends C1060ra {
    public final C1060ra A00;
    public final Class A01;

    public MP(C1060ra raVar, Class cls) {
        super(raVar, raVar.A06);
        this.A00 = raVar;
        this.A01 = cls;
    }

    @Override // X.C1060ra
    public final void A04(JsonSerializer jsonSerializer) {
        this.A00.A04(jsonSerializer);
    }
}
