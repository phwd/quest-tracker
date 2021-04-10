package X;

public final class K5 {
    public final K7 A00;
    public final C0954pW A01;
    public final C0956pY A02;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof K5)) {
            return false;
        }
        K5 k5 = (K5) obj;
        return C0514bB.A05(this.A02, k5.A02) && C0514bB.A05(this.A00, k5.A00) && C0514bB.A05(this.A01, k5.A01);
    }

    public final int hashCode() {
        C0956pY pYVar = this.A02;
        int i = 0;
        int hashCode = (pYVar != null ? pYVar.hashCode() : 0) * 31;
        K7 k7 = this.A00;
        int hashCode2 = (hashCode + (k7 != null ? k7.hashCode() : 0)) * 31;
        C0954pW pWVar = this.A01;
        if (pWVar != null) {
            i = pWVar.hashCode();
        }
        return ((hashCode2 + i) * 31) + 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DatabaseAccessConfig(databaseConnectionManager=");
        sb.append(this.A02);
        sb.append(", operationInstrumentation=");
        sb.append(this.A00);
        sb.append(", changeManager=");
        sb.append(this.A01);
        sb.append(", printStatements=");
        sb.append(false);
        sb.append(")");
        return sb.toString();
    }

    public /* synthetic */ K5(C0956pY pYVar, K7 k7, C0954pW pWVar) {
        C0514bB.A02(pYVar, "databaseConnectionManager");
        C0514bB.A02(k7, "operationInstrumentation");
        C0514bB.A02(pWVar, "changeManager");
        this.A02 = pYVar;
        this.A00 = k7;
        this.A01 = pWVar;
    }
}
