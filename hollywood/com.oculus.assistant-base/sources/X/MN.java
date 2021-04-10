package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;

public final class MN extends C1060ra {
    public final QC A00;

    @Override // X.C1060ra
    public final void A04(JsonSerializer jsonSerializer) {
        super.A04(jsonSerializer);
        JsonSerializer jsonSerializer2 = this.A02;
        if (jsonSerializer2 != null) {
            QC qc = this.A00;
            if (jsonSerializer2 instanceof UnwrappingBeanSerializer) {
                qc = new C1077rt(qc, ((UnwrappingBeanSerializer) jsonSerializer2).A00);
            }
            this.A02 = jsonSerializer2.A07(qc);
        }
    }

    public MN(C1060ra raVar, QC qc) {
        super(raVar, raVar.A06);
        this.A00 = qc;
    }

    public MN(MN mn, QC qc, C1015qj qjVar) {
        super(mn, qjVar);
        this.A00 = qc;
    }
}
