package com.facebook.analytics2.logger;

import X.AnonymousClass82;
import X.AnonymousClass83;
import X.Er;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.concurrent.TimeUnit;

public class AlarmBasedUploadService extends Service {
    public static final long A01 = TimeUnit.HOURS.toMillis(6);
    public static final long A02 = TimeUnit.MINUTES.toMillis(5);
    public AnonymousClass83 A00;

    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getAction().startsWith("com.facebook.analytics2.logger.UPLOAD_NOW")) {
            intent.setAction("com.facebook.analytics2.logger.UPLOAD_NOW");
        }
        AnonymousClass83 r1 = this.A00;
        Er.A00(r1);
        return r1.A02(intent, new AnonymousClass82(this, i2));
    }

    public final void onCreate() {
        this.A00 = AnonymousClass83.A00(this);
    }
}
