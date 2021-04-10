package X;

import com.facebook.proxygen.RequestStats;
import com.facebook.proxygen.TraceEventHandler;

public final class kl implements TraceEventHandler {
    public final /* synthetic */ Ea A00;

    @Override // com.facebook.proxygen.TraceEventHandler
    public final void decorateStatistics(RequestStats requestStats, long j) {
    }

    public kl(Ea ea) {
        this.A00 = ea;
    }
}
