package com.oculus.content;

import X.AbstractC02630jR;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.content.Context;
import com.facebook.secure.content.SecureContentProvider;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.AppInitModule;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import javax.annotation.Nullable;

public abstract class OculusSecureContentProvider extends SecureContentProvider {
    @Inject
    @Eager
    public AppInitLock mAppInitLock;
    @Inject
    @Eager
    public OculusContentProviderLogger mOculusContentProviderLogger;

    @Override // X.AnonymousClass0jF
    public final boolean allowOwnCallingProcess() {
        return true;
    }

    public void doInitialization() {
    }

    public static final void _UL_injectMe(Context context, OculusSecureContentProvider oculusSecureContentProvider) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusSecureContentProvider);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r1, OculusSecureContentProvider oculusSecureContentProvider) {
        oculusSecureContentProvider.mAppInitLock = AppInitModule._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_ACCESS_METHOD(r1);
        oculusSecureContentProvider.mOculusContentProviderLogger = OculusContentProviderLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusContentProviderLogger_ULSEP_ACCESS_METHOD(r1);
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public AbstractC02630jR getLogger() {
        return this.mOculusContentProviderLogger;
    }

    @Override // X.AnonymousClass0jF
    public final void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
        this.mAppInitLock.waitForInitialization();
    }
}
