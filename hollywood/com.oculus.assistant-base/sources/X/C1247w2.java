package X;

import com.google.common.util.concurrent.ListenableFuture;
import com.oculus.assistant.learning.ExecutionJobService;

/* renamed from: X.w2  reason: case insensitive filesystem */
public final class C1247w2 implements AbstractC0382Ut {
    public final /* synthetic */ ExecutionJobService A00;

    public C1247w2(ExecutionJobService executionJobService) {
        this.A00 = executionJobService;
    }

    @Override // X.AbstractC0382Ut
    public final ListenableFuture A1F(Object obj) {
        C0904oW oWVar = (C0904oW) obj;
        if (oWVar == null) {
            C0139Dd.A09("com.oculus.assistant.learning.ExecutionJobService", "Could not cancel Papaya executor; is null");
            return AnonymousClass1u.A01;
        }
        return AnonymousClass1O.A00(oWVar.A01.A00(), new C0901oT(oWVar, C0912of.A00), oWVar.A02);
    }
}
