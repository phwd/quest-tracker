package com.oculus.ossdk.inject;

import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.os.SettingsManager;

@Generated({"By: InjectorProcessor"})
public class SettingsManagerMethodAutoProvider extends AnonymousClass0VG<SettingsManager> {
    public SettingsManager get() {
        return OsSdkModule.provideSettingsManager(C00610Hs.A00(this));
    }
}
