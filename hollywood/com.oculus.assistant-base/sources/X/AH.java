package X;

import android.content.Context;
import java.util.concurrent.ExecutorService;

public final class AH {
    public final K5 A00;
    public final ExecutorService A01;
    public final ExecutorService A02;
    public final AbstractC0464a8 A03 = new C0849jx(new C0645dn(this));
    public final Context A04;

    public AH(Context context, ExecutorService executorService, ExecutorService executorService2) {
        C0514bB.A02(context, "context");
        C0514bB.A02(executorService, "queryExecutor");
        C0514bB.A02(executorService2, "writeExecutor");
        this.A04 = context;
        this.A01 = executorService;
        this.A02 = executorService2;
        C0956pY pYVar = new C0956pY(new DB(context, new C0793hh(), new D9("AssistantTtsConfig.db", "85e012d266b33234627b629590b4f3269f63b34c-fbb9ad2c879d477143c1697b72869dc43873d76e-")));
        this.A00 = new K5(pYVar, new C0952pU(), new C0954pW(new C0792hg(pYVar)));
    }
}
