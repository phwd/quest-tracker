package X;

import com.fasterxml.jackson.databind.JsonSerializer;

public final class MZ extends C1060ra {
    public final C1060ra A00;
    public final Class[] A01;

    public MZ(C1060ra raVar, Class[] clsArr) {
        super(raVar, raVar.A06);
        this.A00 = raVar;
        this.A01 = clsArr;
    }

    @Override // X.C1060ra
    public final void A04(JsonSerializer jsonSerializer) {
        this.A00.A04(jsonSerializer);
    }
}
