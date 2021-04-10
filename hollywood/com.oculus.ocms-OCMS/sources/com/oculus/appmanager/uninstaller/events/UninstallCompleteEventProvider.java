package com.oculus.appmanager.uninstaller.events;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractAssistedProvider;
import com.facebook.inject.InjectorLike;
import javax.annotation.Nullable;

@Generated({"By: InjectorProcessor"})
public class UninstallCompleteEventProvider extends AbstractAssistedProvider<UninstallCompleteEvent> {
    public UninstallCompleteEventProvider(InjectorLike injectorLike) {
        super(injectorLike);
    }

    public UninstallCompleteEvent get(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Throwable th, @Nullable Boolean bool, @Nullable String str3) {
        return new UninstallCompleteEvent(this, str, str2, num, th, bool, str3);
    }
}
