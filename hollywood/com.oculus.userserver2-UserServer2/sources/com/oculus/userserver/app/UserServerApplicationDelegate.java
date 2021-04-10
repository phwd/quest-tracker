package com.oculus.userserver.app;

import X.BZ;
import X.Ok;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.impl.AppInitializer;
import javax.annotation.Nullable;

public final class UserServerApplicationDelegate implements Ok {
    @Inject
    @Eager
    public AppInitLock mAppInitLock;
    @Inject
    @Eager
    public AppInitializer mAppInitializer;
    public final Context mContext;
    @Nullable
    public BZ mInjector;
    @Nullable
    public final TestCallbacks mTestCallbacks = null;

    @VisibleForTesting
    public interface TestCallbacks {
    }

    public UserServerApplicationDelegate(Context context) {
        this.mContext = context;
    }
}
