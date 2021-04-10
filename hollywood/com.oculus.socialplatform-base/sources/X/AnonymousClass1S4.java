package X;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.annotation.NonNull;

/* renamed from: X.1S4  reason: invalid class name */
public final class AnonymousClass1S4 implements AnonymousClass0Dx {
    public boolean A00;
    public boolean A01;
    public final AnonymousClass1SD A02;
    public final BroadcastReceiver A03 = new AnonymousClass1SB(this);
    public final Context A04;

    @Override // X.AbstractC08541eo
    public final void onDestroy() {
    }

    @SuppressLint({"MissingPermission"})
    public static final boolean A00(@NonNull Context context) {
        Object systemService = context.getSystemService("connectivity");
        AnonymousClass1S2.A00(systemService);
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
                return true;
            }
        }
    }

    @Override // X.AbstractC08541eo
    public final void onStart() {
        if (!this.A01) {
            Context context = this.A04;
            this.A00 = A00(context);
            try {
                context.registerReceiver(this.A03, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.A01 = true;
            } catch (SecurityException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to register", e);
                }
            }
        }
    }

    @Override // X.AbstractC08541eo
    public final void onStop() {
        if (this.A01) {
            this.A04.unregisterReceiver(this.A03);
            this.A01 = false;
        }
    }

    public AnonymousClass1S4(@NonNull Context context, @NonNull AnonymousClass1SD r3) {
        this.A04 = context.getApplicationContext();
        this.A02 = r3;
    }
}
