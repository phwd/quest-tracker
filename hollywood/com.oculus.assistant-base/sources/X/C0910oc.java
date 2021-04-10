package X;

import android.content.Context;
import com.facebook.papaya.client.platform.PlatformJobScheduler$SchedulingGuard;
import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.oc  reason: case insensitive filesystem */
public final class C0910oc implements HA {
    public final /* synthetic */ Context A00;

    public C0910oc(Context context) {
        this.A00 = context;
    }

    @Override // X.HA
    public final ListenableFuture A39(PlatformJobScheduler$SchedulingGuard platformJobScheduler$SchedulingGuard) {
        Context context = this.A00;
        synchronized (platformJobScheduler$SchedulingGuard) {
            platformJobScheduler$SchedulingGuard.A03 = context.getApplicationContext();
            platformJobScheduler$SchedulingGuard.A02 = null;
            platformJobScheduler$SchedulingGuard.A04 = null;
        }
        return AnonymousClass1u.A01;
    }
}
