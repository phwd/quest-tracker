package X;

import java.util.concurrent.ExecutorService;

public final class K8 {
    public final AbstractC0464a8 A00;
    public final ExecutorService A01;
    public final ExecutorService A02;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof K8)) {
            return false;
        }
        K8 k8 = (K8) obj;
        return C0514bB.A05(this.A02, k8.A02) && C0514bB.A05(this.A01, k8.A01) && C0514bB.A05(this.A00, k8.A00);
    }

    public final int hashCode() {
        ExecutorService executorService = this.A02;
        int i = 0;
        int hashCode = (executorService != null ? executorService.hashCode() : 0) * 31;
        ExecutorService executorService2 = this.A01;
        int hashCode2 = (hashCode + (executorService2 != null ? executorService2.hashCode() : 0)) * 31;
        AbstractC0464a8 a8Var = this.A00;
        if (a8Var != null) {
            i = a8Var.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimpleSQLConfig(queryExecutor=");
        sb.append(this.A02);
        sb.append(", mutationExecutor=");
        sb.append(this.A01);
        sb.append(", databaseAccessConfig=");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public K8(ExecutorService executorService, ExecutorService executorService2, AbstractC0464a8 a8Var) {
        C0514bB.A02(executorService, "queryExecutor");
        C0514bB.A02(executorService2, "mutationExecutor");
        C0514bB.A02(a8Var, "databaseAccessConfig");
        this.A02 = executorService;
        this.A01 = executorService2;
        this.A00 = a8Var;
    }
}
