package X;

import android.content.Context;
import android.content.Intent;
import com.facebook.papaya.IPapayaService;
import com.facebook.papaya.client.PapayaMetadataInternal;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class H7 {
    public final Context A00;
    public final PapayaMetadataInternal A01;
    public final Set A02;
    public final ScheduledExecutorService A03 = Executors.newScheduledThreadPool(1);

    public H7(Context context, PapayaMetadataInternal papayaMetadataInternal) {
        HashSet hashSet = new HashSet();
        this.A00 = context.getApplicationContext();
        this.A01 = papayaMetadataInternal;
        this.A02 = hashSet;
    }

    public final ListenableFuture A00() {
        Intent intent = new Intent();
        PapayaMetadataInternal papayaMetadataInternal = this.A01;
        intent.setComponent(papayaMetadataInternal.A02.A00);
        intent.putExtra("papaya_metadata", papayaMetadataInternal);
        intent.setAction(IPapayaService.class.getName());
        SettableFuture settableFuture = new SettableFuture();
        H6 h6 = new H6(this, settableFuture);
        this.A00.bindService(intent, h6, 1);
        Set set = this.A02;
        synchronized (set) {
            set.add(h6);
        }
        TimeUnit timeUnit = TimeUnit.SECONDS;
        ScheduledExecutorService scheduledExecutorService = this.A03;
        AnonymousClass1L r2 = new AnonymousClass1L(settableFuture);
        V6 v6 = new V6(r2);
        r2.A01 = scheduledExecutorService.schedule(v6, 60, timeUnit);
        settableFuture.addListener(v6, V3.INSTANCE);
        return r2;
    }

    public final void A01() {
        Set<H6> set = this.A02;
        synchronized (set) {
            for (H6 h6 : set) {
                this.A00.unbindService(h6);
            }
            set.clear();
        }
    }
}
