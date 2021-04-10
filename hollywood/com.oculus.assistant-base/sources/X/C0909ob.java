package X;

import android.content.ComponentName;
import android.content.Context;
import com.facebook.papaya.client.platform.PlatformJobScheduler$SchedulingGuard;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.ob  reason: case insensitive filesystem */
public final class C0909ob implements HA {
    public final /* synthetic */ long A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ ComponentName A02;
    public final /* synthetic */ Context A03;
    public final /* synthetic */ boolean A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;

    public C0909ob(Context context, ComponentName componentName, long j, boolean z, long j2, boolean z2, boolean z3) {
        this.A03 = context;
        this.A02 = componentName;
        this.A00 = j;
        this.A04 = z;
        this.A01 = j2;
        this.A05 = z2;
        this.A06 = z3;
    }

    @Override // X.HA
    public final ListenableFuture A39(PlatformJobScheduler$SchedulingGuard platformJobScheduler$SchedulingGuard) {
        Context context = this.A03;
        ComponentName componentName = this.A02;
        long j = this.A00;
        boolean z = this.A04;
        long j2 = this.A01;
        boolean z2 = this.A05;
        boolean z3 = this.A06;
        synchronized (platformJobScheduler$SchedulingGuard) {
            platformJobScheduler$SchedulingGuard.A03 = context.getApplicationContext();
            platformJobScheduler$SchedulingGuard.A02 = componentName;
            platformJobScheduler$SchedulingGuard.A04 = Long.valueOf(j);
            platformJobScheduler$SchedulingGuard.A05 = z;
            platformJobScheduler$SchedulingGuard.A01 = TimeUnit.SECONDS.toMillis(j2);
            platformJobScheduler$SchedulingGuard.A06 = z2;
            platformJobScheduler$SchedulingGuard.A07 = z3;
        }
        return AnonymousClass1u.A01;
    }
}
