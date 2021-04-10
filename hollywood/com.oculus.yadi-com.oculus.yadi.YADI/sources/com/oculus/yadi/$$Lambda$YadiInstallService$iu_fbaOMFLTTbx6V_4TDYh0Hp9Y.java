package com.oculus.yadi;

import oculus.internal.StorageListenerInterface;
import oculus.internal.yadi.YadiPath;

/* renamed from: com.oculus.yadi.-$$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y implements StorageListenerInterface.Callback {
    public static final /* synthetic */ $$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y INSTANCE = new $$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y();

    private /* synthetic */ $$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y() {
    }

    public final void onStorageChange() {
        YadiPath.refreshMountInfo();
    }
}
