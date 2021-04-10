package org.chromium.ui.base;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IdleDetector extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11017a;
    public long b;

    public IdleDetector() {
        if (isScreenLocked()) {
            this.f11017a = true;
            this.b = SystemClock.elapsedRealtime();
        } else {
            this.f11017a = false;
            this.b = 0;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        ContextUtils.getApplicationContext().registerReceiver(this, intentFilter);
    }

    public static IdleDetector create() {
        return new IdleDetector();
    }

    public final long getIdleTime() {
        if (!this.f11017a) {
            return 0;
        }
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.b);
    }

    public final boolean isScreenLocked() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (((KeyguardManager) applicationContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return true;
        }
        return !((PowerManager) applicationContext.getSystemService("power")).isInteractive();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            this.f11017a = true;
            this.b = SystemClock.elapsedRealtime();
        } else if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            this.f11017a = false;
            this.b = 0;
        }
    }
}
