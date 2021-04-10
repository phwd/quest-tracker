package com.oculus.ocms.defaultapps;

import com.facebook.infer.annotation.Nullsafe;
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
import com.oculus.device.DeviceType;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.ocms.defaultapps.DefaultAppsModule;
import com.oculus.ocms.defaultapps.graphql.DefaultAppsGraphQLQuery;
import com.oculus.ocms.defaultapps.net.DefaultAppsRequest;
import com.oculus.ocms.defaultapps.net.DefaultAppsResponse;
import javax.inject.Provider;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_ocms_defaultapps_graphql_DefaultAppsGraphQLQuery_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultAppsMethods {
    private InjectionContext _UL_mInjectionContext;
    private Methods mMethods;

    /* access modifiers changed from: private */
    public interface Methods {
        @GET("/graphql")
        DefaultAppsResponse getDefaultApps(@Query("doc") String str, @Query("variables") String str2);
    }

    @AutoGeneratedAccessMethod
    public static final DefaultAppsMethods _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DefaultAppsMethods) UL.factorymap.get(DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DefaultAppsMethods _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new DefaultAppsMethods(injectorLike, ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public DefaultAppsMethods(InjectorLike injectorLike, @OculusRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID, injectorLike);
    }

    public DefaultAppsResponse getDefaultApps(boolean z) {
        return this.mMethods.getDefaultApps(((DefaultAppsGraphQLQuery) FbInjector.lazyInstance(0, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_graphql_DefaultAppsGraphQLQuery_ULSEP_BINDING_ID, this._UL_mInjectionContext)).mDefaultAppsQuery, GraphQLParamsHelper.encodeParams(new DefaultAppsRequest(DeviceType.current().hmdType, z).getParams()));
    }
}
