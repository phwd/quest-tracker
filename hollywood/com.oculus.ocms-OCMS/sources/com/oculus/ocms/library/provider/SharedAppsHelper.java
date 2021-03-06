package com.oculus.ocms.library.provider;

import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.library.model.App;
import com.oculus.library.model.GrantReason;
import com.oculus.multiuser.MultiuserModule;
import com.oculus.multiuser.UserClassifier;
import com.oculus.ocms.library.OCMSLibraryModule;
import com.oculus.userserver.api.inject.UserServerInjectorModule;
import com.oculus.userserver.api.sharing.SharingManager;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_api_sharing_SharingManager_ULSEP_BINDING_ID"})
public class SharedAppsHelper {
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_library_provider_SharedAppsHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final SharedAppsHelper _UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (SharedAppsHelper) UL.factorymap.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final SharedAppsHelper _UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new SharedAppsHelper(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_library_provider_SharedAppsHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public SharedAppsHelper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    public ImmutableList<App> filterSharedApps(ImmutableList<App> immutableList) {
        if (!needFilterOutSharedApps()) {
            return immutableList;
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        UnmodifiableIterator<App> it = immutableList.iterator();
        while (it.hasNext()) {
            App dropSharedApp = dropSharedApp(it.next());
            if (dropSharedApp != null) {
                builder.add((Object) dropSharedApp);
            }
        }
        return builder.build();
    }

    @Nullable
    public App filterSharedApp(@Nullable App app) {
        return needFilterOutSharedApps() ? dropSharedApp(app) : app;
    }

    private boolean needFilterOutSharedApps() {
        return !((UserClassifier) FbInjector.lazyInstance(0, MultiuserModule.UL_id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isPrimary() && !((SharingManager) FbInjector.lazyInstance(1, UserServerInjectorModule.UL_id._UL__ULSEP_com_oculus_userserver_api_sharing_SharingManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLibrarySharingEnabled();
    }

    @Nullable
    private App dropSharedApp(@Nullable App app) {
        if (app != null && !GrantReason.SHARED_ON_DEVICE.equals(app.grantReason)) {
            return app;
        }
        return null;
    }
}
