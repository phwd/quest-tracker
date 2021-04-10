package com.oculus.content;

import android.content.Context;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.content.PublicContentProvider;
import com.facebook.secure.logger.ContentProviderLogger;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.AppInitModule;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import javax.annotation.Nullable;

public abstract class OculusPublicContentProvider extends PublicContentProvider {
    @Inject
    @Eager
    private AppInitLock mAppInitLock;
    @Inject
    @Eager
    private OculusContentProviderLogger mOculusContentProviderLogger;

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public final boolean allowOwnCallingProcess() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void doInitialization() {
    }

    private static final void _UL_injectMe(Context context, OculusPublicContentProvider oculusPublicContentProvider) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusPublicContentProvider);
        } else {
            FbInjector.injectMe(OculusPublicContentProvider.class, oculusPublicContentProvider, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusPublicContentProvider oculusPublicContentProvider) {
        oculusPublicContentProvider.mAppInitLock = AppInitModule._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_ACCESS_METHOD(injectorLike);
        oculusPublicContentProvider.mOculusContentProviderLogger = OculusContentProviderLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusContentProviderLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public final void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
        this.mAppInitLock.waitForInitialization();
        doInitialization();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    @Nullable
    public ContentProviderLogger getLogger() {
        return this.mOculusContentProviderLogger;
    }
}
