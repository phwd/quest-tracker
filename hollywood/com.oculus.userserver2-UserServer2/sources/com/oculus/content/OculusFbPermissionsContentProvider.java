package com.oculus.content;

import X.AbstractC0199er;
import X.BZ;
import X.IX;
import X.SZ;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.content.FbPermissionsContentProvider;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.AppInitLock;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import javax.annotation.Nullable;

public abstract class OculusFbPermissionsContentProvider extends FbPermissionsContentProvider {
    public static final String TAG = "OculusFbPermissionsContentProvider";
    @Inject
    @Eager
    public AppInitLock mAppInitLock;
    @Inject
    @Eager
    public OculusContentProviderLogger mOculusContentProviderLogger;

    @Override // X.AbstractC0195ed
    public final boolean allowOwnCallingProcess() {
        return true;
    }

    public void doInitialization() {
    }

    public static final void _UL_staticInjectMe(SZ sz, OculusFbPermissionsContentProvider oculusFbPermissionsContentProvider) {
        oculusFbPermissionsContentProvider.mAppInitLock = (AppInitLock) IX.A00(76, sz);
        oculusFbPermissionsContentProvider.mOculusContentProviderLogger = (OculusContentProviderLogger) IX.A00(17, sz);
    }

    public static final void _UL_injectMe(Context context, OculusFbPermissionsContentProvider oculusFbPermissionsContentProvider) {
        _UL_staticInjectMe(BZ.get(context), oculusFbPermissionsContentProvider);
    }

    @Override // X.AbstractC0195ed
    @Nullable
    public AbstractC0199er getLogger() {
        return this.mOculusContentProviderLogger;
    }

    @Override // X.AbstractC0195ed
    @VisibleForTesting
    public final void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
        this.mAppInitLock.A00();
    }
}
