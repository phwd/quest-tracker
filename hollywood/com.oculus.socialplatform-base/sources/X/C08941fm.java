package X;

import android.util.Log;

/* renamed from: X.1fm  reason: invalid class name and case insensitive filesystem */
public class C08941fm implements AbstractC08931fl {
    @Override // X.AbstractC08931fl
    public final void A5M(Throwable th) {
        if (th != null && Log.isLoggable("GlideExecutor", 6)) {
            Log.e("GlideExecutor", "Request threw uncaught throwable", th);
        }
    }
}
