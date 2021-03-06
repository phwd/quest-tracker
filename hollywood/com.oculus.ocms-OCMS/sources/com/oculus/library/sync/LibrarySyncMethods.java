package com.oculus.library.sync;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.library.model.App;
import com.oculus.library.sync.SyncModule;
import java.util.List;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class LibrarySyncMethods {
    private final Methods mMethods;

    /* access modifiers changed from: private */
    public interface Methods {
        @POST("/graphql")
        void syncLibrary(@Query("doc") String str, @Query("variables") LibrarySyncMutationParams librarySyncMutationParams, @Body String str2, Callback<LibrarySyncMutationResponse> callback);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_library_sync_LibrarySyncMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(SyncModule.UL_id._UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final LibrarySyncMethods _UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (LibrarySyncMethods) UL.factorymap.get(SyncModule.UL_id._UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final LibrarySyncMethods _UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new LibrarySyncMethods(ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_library_sync_LibrarySyncMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(SyncModule.UL_id._UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public LibrarySyncMethods(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    public void syncLibrary(String str, String str2, String str3, String str4, List<App> list, ApiCallback<LibrarySyncMutationResponse> apiCallback) {
        this.mMethods.syncLibrary(LibrarySyncQuery.SYNC_APP_LIBRARY_OSS_MUTATION, LibrarySyncMutationParams.getParamsForLibrarySync(str, str2, str3, str4, list), "", apiCallback);
    }
}
