package com.facebook.analytics2.logger;

import X.HU;
import X.HV;
import X.P6;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AlarmBasedUploadService extends Service {
    public static final long A01 = TimeUnit.HOURS.toMillis(6);
    public static final long A02 = TimeUnit.MINUTES.toMillis(5);
    @Nullable
    public HV A00;

    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getAction().startsWith("com.facebook.analytics2.logger.UPLOAD_NOW")) {
            intent.setAction("com.facebook.analytics2.logger.UPLOAD_NOW");
        }
        HV hv = this.A00;
        P6.A00(hv);
        return hv.A02(intent, new HU(this, i2));
    }

    public final void onCreate() {
        this.A00 = HV.A00(this);
    }
}
