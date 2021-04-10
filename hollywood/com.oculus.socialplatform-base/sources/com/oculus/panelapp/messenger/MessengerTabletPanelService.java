package com.oculus.panelapp.messenger;

import X.AnonymousClass0l0;
import android.os.Bundle;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.oculus.common.devicelifecycle.DeviceLifecycleManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class MessengerTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(MessengerTabletPanelService.class);
    @Nullable
    public MessengerTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onDestroy() {
        if (this.mApp != null) {
            DeviceLifecycleManager.unregisterDeviceLifecycleObserver(getApplicationContext().getApplicationContext(), this.mApp);
        }
        super.onDestroy();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new MessengerTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        DeviceLifecycleManager.registerDeviceLifecycleObserver(getApplicationContext(), this.mApp);
        return this.mApp.getNativePointer();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        AnonymousClass0l0.A03(getApplication());
        System.loadLibrary("vrshellmessengertabletpanel");
        super.onCreate();
    }
}
