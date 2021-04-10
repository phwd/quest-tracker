package defpackage;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: vE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5222vE {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11469a;
    public int b;
    public boolean c;
    public int d;
    public boolean e;

    public C5222vE(boolean z, int i, int i2, boolean z2, boolean z3, boolean z4) {
        this.d = 0;
        this.f11469a = z;
        this.b = i;
        this.c = z2;
        this.d = i2;
        this.e = z3;
    }

    public static Intent a(Context context) {
        return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public static C5222vE b(Context context) {
        Intent a2 = a(context);
        if (a2 == null) {
            return new C5222vE();
        }
        return new C5222vE(e(a2), c(a2), d(context), ((PowerManager) context.getSystemService("power")).isPowerSaveMode(), ((ConnectivityManager) context.getSystemService("connectivity")).isActiveNetworkMetered(), f(context));
    }

    public static int c(Intent intent) {
        int intExtra = intent.getIntExtra("scale", -1);
        if (intExtra == 0) {
            return 0;
        }
        return Math.round(((float) (intent.getIntExtra("level", -1) * 100)) / ((float) intExtra));
    }

    public static int d(Context context) {
        int i;
        int currentConnectionType = NetworkChangeNotifier.b() ? NetworkChangeNotifier.f11004a.getCurrentConnectionType() : 6;
        if (currentConnectionType != 6) {
            return currentConnectionType;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting())) {
            return currentConnectionType;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            i = 2;
        } else if (type == 0) {
            i = 3;
        } else if (type == 7) {
            return 7;
        } else {
            return 0;
        }
        return i;
    }

    public static boolean e(Intent intent) {
        int intExtra = intent.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    public static boolean f(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager == null || keyguardManager.isKeyguardLocked()) {
            return false;
        }
        return ((PowerManager) context.getSystemService("power")).isInteractive();
    }

    public C5222vE() {
        this.d = 0;
        this.c = true;
        this.e = true;
    }
}
