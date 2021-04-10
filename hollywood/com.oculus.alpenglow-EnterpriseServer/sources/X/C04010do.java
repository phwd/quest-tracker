package X;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0do  reason: invalid class name and case insensitive filesystem */
public final class C04010do extends AnonymousClass05q {
    public final Object A00 = new Object();
    public final ExecutorService A01 = Executors.newFixedThreadPool(4, new ThreadFactoryC005705p(this));
    @Nullable
    public volatile Handler A02;

    @Override // X.AnonymousClass05q
    public final void A01(Runnable runnable) {
        this.A01.execute(runnable);
    }

    @Override // X.AnonymousClass05q
    public final void A02(Runnable runnable) {
        Handler handler;
        if (this.A02 == null) {
            synchronized (this.A00) {
                if (this.A02 == null) {
                    Looper mainLooper = Looper.getMainLooper();
                    if (Build.VERSION.SDK_INT >= 28) {
                        handler = Handler.createAsync(mainLooper);
                    } else {
                        try {
                            handler = (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(mainLooper, null, true);
                        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
                            handler = new Handler(mainLooper);
                        } catch (InvocationTargetException unused2) {
                            handler = new Handler(mainLooper);
                        }
                    }
                    this.A02 = handler;
                }
            }
        }
        this.A02.post(runnable);
    }

    @Override // X.AnonymousClass05q
    public final boolean A03() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }
}
