package com.oculus.content;

import X.AbstractC02640aw;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.content.Context;
import com.facebook.secure.content.SecureContentProvider;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.AppInitLock;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import javax.annotation.Nullable;

public abstract class OculusSecureContentProvider extends SecureContentProvider {
    @Inject
    @Eager
    public AppInitLock mAppInitLock;
    @Inject
    @Eager
    public OculusContentProviderLogger mOculusContentProviderLogger;

    @Override // X.AbstractC09361bk
    public final boolean allowOwnCallingProcess() {
        return true;
    }

    public void doInitialization() {
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, OculusSecureContentProvider oculusSecureContentProvider) {
        oculusSecureContentProvider.mAppInitLock = (AppInitLock) AnonymousClass117.A00(139, r1);
        oculusSecureContentProvider.mOculusContentProviderLogger = (OculusContentProviderLogger) AnonymousClass117.A00(158, r1);
    }

    public static final void _UL_injectMe(Context context, OculusSecureContentProvider oculusSecureContentProvider) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), oculusSecureContentProvider);
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public AbstractC02640aw getLogger() {
        return this.mOculusContentProviderLogger;
    }

    @Override // X.AbstractC09361bk
    public final void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
        this.mAppInitLock.A01();
    }
}
