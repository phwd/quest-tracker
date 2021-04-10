package X;

import android.content.Context;
import com.facebook.papaya.client.Papaya$CallbackDelegate;
import com.facebook.papaya.client.Papaya$LogDelegate;
import com.facebook.papaya.client.PapayaMetadata;
import com.facebook.papaya.client.PapayaMetadataInternal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: X.oW  reason: case insensitive filesystem */
public final class C0904oW {
    public final Papaya$LogDelegate A00;
    public final H7 A01;
    public final V0 A02;
    public final Papaya$CallbackDelegate A03;

    public C0904oW(Context context, PapayaMetadata papayaMetadata) {
        V0 by;
        Papaya$CallbackDelegate papaya$CallbackDelegate = new Papaya$CallbackDelegate(0);
        Papaya$LogDelegate papaya$LogDelegate = new Papaya$LogDelegate();
        this.A01 = new H7(context, new PapayaMetadataInternal(papayaMetadata, papaya$LogDelegate, papaya$CallbackDelegate));
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new Gr());
        if (newSingleThreadExecutor instanceof V0) {
            by = (V0) newSingleThreadExecutor;
        } else if (newSingleThreadExecutor instanceof ScheduledExecutorService) {
            by = new ScheduledExecutorServiceC0337Rr((ScheduledExecutorService) newSingleThreadExecutor);
        } else {
            by = new BY(newSingleThreadExecutor);
        }
        this.A02 = by;
        this.A00 = papaya$LogDelegate;
        this.A03 = papaya$CallbackDelegate;
    }
}
