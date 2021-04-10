package X;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public final class UG extends AnonymousClass2e {
    public final ExecutorService A00 = Executors.newFixedThreadPool(4, new AnonymousClass2d(this));
    public final Object A01 = new Object();
    @Nullable
    public volatile Handler A02;

    @Override // X.AnonymousClass2e
    public final void A02(Runnable runnable) {
        Handler handler;
        if (this.A02 == null) {
            synchronized (this.A01) {
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
}
