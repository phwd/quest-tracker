package com.oculus.appmanager.uninstaller.tasks;

import android.os.ResultReceiver;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractAssistedProvider;
import com.facebook.inject.InjectorLike;
import javax.annotation.Nullable;

@Generated({"By: InjectorProcessor"})
public class UninstallerAsyncTaskProvider extends AbstractAssistedProvider<UninstallerAsyncTask> {
    public UninstallerAsyncTaskProvider(InjectorLike injectorLike) {
        super(injectorLike);
    }

    public UninstallerAsyncTask get(String str, @Nullable String str2, @Nullable ResultReceiver resultReceiver) {
        return new UninstallerAsyncTask(this, str, str2, resultReceiver);
    }
}
