package com.oculus.vrshell.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.oculus.modules.codegen.ServiceModule;

public class SystemOverlaysService extends StartRenderingOverlaysService {
    Handler mMainHandler = new Handler();
    BroadcastReceiver mStopRenderingOverlaysReceiver;

    public SystemOverlaysService() {
        super("home");
        BaseService.loadLibrary();
    }

    @Override // com.oculus.vrshell.home.StartRenderingOverlaysService
    public void onCreate() {
        super.onCreate();
        BaseService.initialize(this, ServiceModule.ServiceType.SYSTEM_OVERLAYS, this.mMainHandler);
        OverlaysInputBlocker.stopBlockingInput(this);
        IntentFilter filter = new IntentFilter("com.oculus.vrshell.home.STOP_RENDERING_OVERLAY");
        this.mStopRenderingOverlaysReceiver = new BroadcastReceiver() {
            /* class com.oculus.vrshell.home.SystemOverlaysService.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                Intent tearDownIntent = new Intent("com.oculus.vrdriver.teardown_notification");
                tearDownIntent.putExtra("_ci_", PendingIntent.getService(context, 0, tearDownIntent, 0));
                context.sendBroadcast(tearDownIntent);
                System.exit(0);
            }
        };
        registerReceiver(this.mStopRenderingOverlaysReceiver, filter);
    }

    @Override // com.oculus.vrshell.home.StartRenderingOverlaysService
    public void onDestroy() {
        super.onDestroy();
        BaseService.destroy();
        unregisterReceiver(this.mStopRenderingOverlaysReceiver);
    }
}
