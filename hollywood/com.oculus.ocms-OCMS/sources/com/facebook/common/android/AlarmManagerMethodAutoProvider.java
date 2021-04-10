package com.facebook.common.android;

import android.app.AlarmManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AlarmManagerMethodAutoProvider extends AbstractProvider<AlarmManager> {
    @Override // javax.inject.Provider
    public AlarmManager get() {
        return AndroidModule.provideAlarmManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
