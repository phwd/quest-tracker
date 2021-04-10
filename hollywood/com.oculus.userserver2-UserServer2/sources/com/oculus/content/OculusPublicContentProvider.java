package com.oculus.content;

import X.AbstractC0199er;
import X.BZ;
import X.IX;
import X.SZ;
import android.content.Context;
import com.facebook.secure.content.PublicContentProvider;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.AppInitLock;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import javax.annotation.Nullable;

public abstract class OculusPublicContentProvider extends PublicContentProvider {
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

    public static final void _UL_staticInjectMe(SZ sz, OculusPublicContentProvider oculusPublicContentProvider) {
        oculusPublicContentProvider.mAppInitLock = (AppInitLock) IX.A00(76, sz);
        oculusPublicContentProvider.mOculusContentProviderLogger = (OculusContentProviderLogger) IX.A00(17, sz);
    }

    public static final void _UL_injectMe(Context context, OculusPublicContentProvider oculusPublicContentProvider) {
        _UL_staticInjectMe(BZ.get(context), oculusPublicContentProvider);
    }

    @Override // X.AbstractC0195ed
    @Nullable
    public AbstractC0199er getLogger() {
        return this.mOculusContentProviderLogger;
    }

    @Override // X.AbstractC0195ed
    public final void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
        this.mAppInitLock.A00();
        doInitialization();
    }
}
