package com.oculus.horizon.api;

import android.content.Context;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.google.common.base.Preconditions;
import com.oculus.horizon.api.ApiModule;
import com.oculus.horizon.api.camera.UploadProfilePictureRequest;
import com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesRequest;
import com.oculus.horizon.api.cloudstorage2.UserCloudFileWildcardsRequest;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesRequest;
import com.oculus.horizon.api.commerce.ExternalCreditCardsRequest;
import com.oculus.horizon.api.commerce.OrdersRequest;
import com.oculus.horizon.api.commerce.PaymentAccountRequest;
import com.oculus.horizon.api.commerce.PaymentMethodsRequest;
import com.oculus.horizon.api.commerce.RefundRequest;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.api.graphql.GraphQLQuery;
import com.oculus.horizon.api.graphql.GraphQLSocialQuery;
import com.oculus.horizon.api.item.GenerateDownloadUriRequest;
import com.oculus.horizon.api.item.GenerateDownloadUriResponse;
import com.oculus.horizon.api.library.ActiveEntitlementRequest;
import com.oculus.horizon.api.library.ActiveEntitlementsResponse;
import com.oculus.horizon.api.login.LoginRequest;
import com.oculus.horizon.api.logout.LogoutRequest;
import com.oculus.horizon.api.media.CastNotifRequest;
import com.oculus.horizon.api.media.DeleteCastOfferRequest;
import com.oculus.horizon.api.media.GetCastAnswerRequest;
import com.oculus.horizon.api.media.SetCastOfferRequest;
import com.oculus.horizon.api.profile.ChangePasswordRequest;
import com.oculus.horizon.api.profile.ChangePinRequest;
import com.oculus.horizon.api.profile.MyUserProfileRequest;
import com.oculus.horizon.api.push.RegisterPushTokenRequest;
import com.oculus.horizon.api.registration.RegisterRequest;
import com.oculus.horizon.api.registration.SupportedCountriesRequest;
import com.oculus.horizon.api.store.CheckMinimumRequiredAppVersionsRequest;
import com.oculus.horizon.api.store.CheckSetupRequiredAppVersionsRequest;
import com.oculus.horizon.api.store.SectionItemsRequest;
import com.oculus.horizon.api.store.StoresRequest;
import com.oculus.horizon.api.tos.AcceptTermsOfServiceRequest;
import com.oculus.horizon.api.tos.CheckTermsOfServiceRequest;
import com.oculus.horizon.api.tos.TermsOfServiceRequest;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.http.core.base.ApiRequest;
import com.squareup.otto.Bus;
import com.squareup.otto.DeadEvent;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Provider;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApiDispatcher {
    private static volatile ApiDispatcher _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_INSTANCE;
    private final Bus mBus = new Bus(ThreadEnforcer.ANY);
    private final Context mContext;
    private final ApiServiceInterface mInterface;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_api_ApiDispatcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ApiModule.UL_id._UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ApiDispatcher _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApiDispatcher) UL.factorymap.get(ApiModule.UL_id._UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ApiDispatcher _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_INSTANCE == null) {
            synchronized (ApiDispatcher.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_INSTANCE = new ApiDispatcher(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(applicationInjector), com.oculus.http.core.ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_api_ApiDispatcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ApiModule.UL_id._UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    ApiDispatcher(@ForAppContext Context context, @OculusRestAdapter RestAdapter restAdapter) {
        this.mContext = context;
        this.mInterface = (ApiServiceInterface) restAdapter.create(ApiServiceInterface.class);
        this.mBus.register(this);
    }

    public void post(ApiRequest apiRequest) {
        Preconditions.checkNotNull(apiRequest.getCallback());
        this.mBus.post(apiRequest);
    }

    public void post(ApiError apiError) {
        this.mBus.post(apiError);
    }

    @Subscribe
    public void onRequest(LoginRequest loginRequest) {
        this.mInterface.logIn(loginRequest, loginRequest.getCallback());
    }

    @Subscribe
    public void onRequest(LogoutRequest logoutRequest) {
        this.mInterface.logOut(logoutRequest, logoutRequest.getCallback());
    }

    @Subscribe
    public void onRequest(MyUserProfileRequest myUserProfileRequest) {
        String str;
        if (myUserProfileRequest.accessToken == null) {
            str = null;
        } else {
            str = String.format(Locale.US, "Bearer %s", myUserProfileRequest.accessToken);
        }
        this.mInterface.getMyUserProfile(str, GraphQLSocialQuery.MY_USER_PROFILE_QUERY, GraphQLParamsHelper.encodeParams(myUserProfileRequest.getParams()), myUserProfileRequest.getCallback());
    }

    @Subscribe
    public void onRequest(ExternalCreditCardsRequest externalCreditCardsRequest) {
        this.mInterface.fetchPaymentMethods(GraphQLQuery.PAYMENT_METHODS_QUERY, externalCreditCardsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(PaymentMethodsRequest paymentMethodsRequest) {
        this.mInterface.fetchAllPaymentMethods(GraphQLQuery.ALL_PAYMENT_METHODS_QUERY, paymentMethodsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(PaymentAccountRequest paymentAccountRequest) {
        this.mInterface.fetchPaymentAccount(GraphQLQuery.PAYMENT_ACCOUNT_QUERY, paymentAccountRequest.getCallback());
    }

    @Subscribe
    public void onRequest(OrdersRequest ordersRequest) {
        this.mInterface.fetchOrders(GraphQLQuery.ORDERS_QUERY, ordersRequest.getCallback());
    }

    @Subscribe
    public void onRequest(RefundRequest refundRequest) {
        this.mInterface.requestRefund(GraphQLQuery.REQUEST_REFUND_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(refundRequest.getParams()), "", refundRequest.getCallback());
    }

    @Subscribe
    public void onRequest(ChangePasswordRequest changePasswordRequest) {
        this.mInterface.changePassword(GraphQLQuery.USER_PASSWORD_CHANGE_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(changePasswordRequest.getParams()), "", changePasswordRequest.getCallback());
    }

    @Subscribe
    public void onRequest(ChangePinRequest changePinRequest) {
        this.mInterface.changePin(changePinRequest.password, changePinRequest.pin, "", changePinRequest.getCallback());
    }

    @Subscribe
    public void onRequest(TermsOfServiceRequest termsOfServiceRequest) {
        this.mInterface.getTOS(termsOfServiceRequest.type, termsOfServiceRequest.locale, termsOfServiceRequest.getCallback());
    }

    @Subscribe
    public void onRequest(SupportedCountriesRequest supportedCountriesRequest) {
        this.mInterface.getSupportedCountries(supportedCountriesRequest.getCallback());
    }

    @Subscribe
    public void onRequest(RegisterPushTokenRequest registerPushTokenRequest) {
        this.mInterface.registerPushToken(GraphQLQuery.REGISTER_PUSH_TOKEN_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(registerPushTokenRequest.getParams()), "", registerPushTokenRequest.getCallback());
    }

    @Subscribe
    public void onRequest(RegisterRequest registerRequest) {
        this.mInterface.registerUser(registerRequest.getParams(), "", registerRequest.getCallback());
    }

    @Subscribe
    public void onRequest(StoresRequest storesRequest) {
        this.mInterface.getStores(GraphQLQuery.STORES_QUERY, GraphQLParamsHelper.encodeParams(storesRequest.getParams()), storesRequest.storeVersion.headerValue, storesRequest.getCallback());
    }

    @Subscribe
    public void onRequest(SectionItemsRequest sectionItemsRequest) {
        GraphQLParamsHelper.encodeParams(sectionItemsRequest.getParams());
        this.mInterface.getSectionItems(GraphQLQuery.STORE_ITEM_QUERY, GraphQLParamsHelper.encodeParams(sectionItemsRequest.getParams()), sectionItemsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(CheckTermsOfServiceRequest checkTermsOfServiceRequest) {
        this.mInterface.checkTos(GraphQLQuery.TOS_QUERY, checkTermsOfServiceRequest.getCallback());
    }

    @Subscribe
    public void onRequest(AcceptTermsOfServiceRequest acceptTermsOfServiceRequest) {
        this.mInterface.acceptTos(GraphQLQuery.ACCEPT_TOS_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(acceptTermsOfServiceRequest.getParams()), "", acceptTermsOfServiceRequest.getCallback());
    }

    public GenerateDownloadUriResponse generateDownloadUri(GenerateDownloadUriRequest generateDownloadUriRequest) throws ApiException {
        try {
            return this.mInterface.generateDownloadUri(GraphQLQuery.DOWNLOAD_URI_QUERY, GraphQLParamsHelper.encodeParams(generateDownloadUriRequest.getParams()));
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }

    @Subscribe
    public void onRequest(GenerateDownloadUriRequest generateDownloadUriRequest) {
        this.mInterface.generateDownloadUri(GraphQLQuery.DOWNLOAD_URI_QUERY, GraphQLParamsHelper.encodeParams(generateDownloadUriRequest.getParams()), generateDownloadUriRequest.getCallback());
    }

    public ActiveEntitlementsResponse getActiveEntitlements() throws ApiException {
        try {
            return this.mInterface.getActiveEntitlements(GraphQLQuery.ACTIVE_ENTITLEMENT_QUERY, GraphQLParamsHelper.encodeParams(ActiveEntitlementRequest.getParams(this.mContext)));
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }

    @Subscribe
    public void onRequest(CheckMinimumRequiredAppVersionsRequest checkMinimumRequiredAppVersionsRequest) {
        this.mInterface.checkMinimumRequiredAppVersion(checkMinimumRequiredAppVersionsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(CheckSetupRequiredAppVersionsRequest checkSetupRequiredAppVersionsRequest) {
        this.mInterface.checkSetupRequiredAppVersion(checkSetupRequiredAppVersionsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(UploadProfilePictureRequest uploadProfilePictureRequest) {
        this.mInterface.uploadUserProfilePicture(new TypedFile("image/jpeg", uploadProfilePictureRequest.profilePictureFile), uploadProfilePictureRequest.getCallback());
    }

    @Subscribe
    public void onDeadEvent(DeadEvent deadEvent) {
        throw new RuntimeException("Invalid request: " + deadEvent.event.getClass().getName());
    }

    @Subscribe
    public void onRequest(CastNotifRequest castNotifRequest) {
        this.mInterface.requestCastNotif(GraphQLQuery.CAST_NOTIF_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(castNotifRequest.getParams()), "", castNotifRequest.getCallback());
    }

    @Subscribe
    public void onRequest(SetCastOfferRequest setCastOfferRequest) {
        this.mInterface.requestSetCastOffer(GraphQLQuery.CAST_OFFER_QUERY, GraphQLParamsHelper.encodeAsNestedInputParams(setCastOfferRequest.getParams()), "", setCastOfferRequest.getCallback());
    }

    @Subscribe
    public void onRequest(GetCastAnswerRequest getCastAnswerRequest) {
        this.mInterface.requestGetCastAnswer(GraphQLQuery.CAST_ANSWER_QUERY, GraphQLParamsHelper.encodeParams(getCastAnswerRequest.getParams()), getCastAnswerRequest.getCallback());
    }

    @Subscribe
    public void onRequest(DeleteCastOfferRequest deleteCastOfferRequest) {
        this.mInterface.requestDeleteCastOffer(GraphQLQuery.CAST_OFFER_DELETE, GraphQLParamsHelper.encodeAsNestedInputParams(deleteCastOfferRequest.getParams()), "", deleteCastOfferRequest.getCallback());
    }

    @Subscribe
    public void onRequest(UserCloudFilesRequest userCloudFilesRequest) {
        this.mInterface.getUserCloudFiles(GraphQLQuery.GET_USER_CLOUD_FILES, GraphQLParamsHelper.encodeParams(userCloudFilesRequest.getParams()), userCloudFilesRequest.getCallback());
    }

    @Subscribe
    public void onRequest(UserCloudFileWildcardsRequest userCloudFileWildcardsRequest) {
        this.mInterface.getUserCloudFileWildcards(GraphQLQuery.GET_USER_CLOUD_FILE_WILDCARDS, GraphQLParamsHelper.encodeParams(userCloudFileWildcardsRequest.getParams()), userCloudFileWildcardsRequest.getCallback());
    }

    @Subscribe
    public void onRequest(UploadAndRemoveUserCloudFilesRequest uploadAndRemoveUserCloudFilesRequest) {
        this.mInterface.uploadAndRemoveUserCloudFiles(GraphQLQuery.UPLOAD_AND_REMOVE_CLOUD_STORAGE_FILES, GraphQLParamsHelper.encodeMutationParamsUnwrapped(uploadAndRemoveUserCloudFilesRequest.getParams()), "", uploadAndRemoveUserCloudFilesRequest.getCallback());
    }
}