package com.oculus.ocms.locationservices.graphql;

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
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.ocms.locationservices.graphql.GraphqlModule;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
public class ApiDispatcher {
    private final Methods mMethods;

    public interface Methods {
        public static final String EMPTY_BODY = "";
        public static final String GRAPHQL_ENDPOINT = "/graphql";
        public static final String URSA_GRAPHQL_QUERY_KEY = "doc";
        public static final String URSA_GRAPHQL_QUERY_KEY_PARAMS = "variables";

        @POST("/graphql")
        void getLocationFromUrsa(@Query("doc") String str, @Query("variables") String str2, @Body String str3, Callback<LocationGraphQLResponse> callback);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(GraphqlModule.UL_id._UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ApiDispatcher _UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApiDispatcher) UL.factorymap.get(GraphqlModule.UL_id._UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ApiDispatcher _UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new ApiDispatcher(ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(GraphqlModule.UL_id._UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ApiDispatcher(@OculusRestAdapter RestAdapter restAdapter) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    public void getLocationFromUrsa(String str, boolean z, Callback<LocationGraphQLResponse> callback) throws ApiException {
        try {
            this.mMethods.getLocationFromUrsa(z ? GraphQLQuery.URSA_LOCATION_WITH_TIMEZONE_QUERY : GraphQLQuery.URSA_LOCATION_QUERY, str, "", callback);
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }
}
