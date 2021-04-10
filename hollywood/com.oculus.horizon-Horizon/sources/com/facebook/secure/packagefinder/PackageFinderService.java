package com.facebook.secure.packagefinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PackageFinderService extends Service {
    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final void onCreate() {
        super.onCreate();
        stopSelf();
    }
}
