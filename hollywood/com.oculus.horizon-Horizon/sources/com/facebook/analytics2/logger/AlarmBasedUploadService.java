package com.facebook.analytics2.logger;

import X.AnonymousClass0P5;
import X.C00980Hw;
import X.C00990Hx;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AlarmBasedUploadService extends Service {
    public static final long A01 = TimeUnit.HOURS.toMillis(6);
    public static final long A02 = TimeUnit.MINUTES.toMillis(5);
    @Nullable
    public C00990Hx A00;

    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getAction().startsWith("com.facebook.analytics2.logger.UPLOAD_NOW")) {
            intent.setAction("com.facebook.analytics2.logger.UPLOAD_NOW");
        }
        C00990Hx r1 = this.A00;
        AnonymousClass0P5.A00(r1);
        return r1.A02(intent, new C00980Hw(this, i2));
    }

    public final void onCreate() {
        this.A00 = C00990Hx.A00(this);
    }
}
