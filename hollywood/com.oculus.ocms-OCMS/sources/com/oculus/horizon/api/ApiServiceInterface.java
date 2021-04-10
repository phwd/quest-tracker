package com.oculus.horizon.api;

import com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesResponse;
import com.oculus.horizon.api.cloudstorage2.UserCloudFileWildcardsResponse;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.api.commerce.ExternalCreditCardsResponse;
import com.oculus.horizon.api.commerce.OrdersResponse;
import com.oculus.horizon.api.commerce.PaymentAccountResponse;
import com.oculus.horizon.api.commerce.PaymentMethodsResponse;
import com.oculus.horizon.api.commerce.RefundResponse;
import com.oculus.horizon.api.common.MinimumAppVersion;
import com.oculus.horizon.api.item.GenerateDownloadUriResponse;
import com.oculus.horizon.api.library.ActiveEntitlementsResponse;
import com.oculus.horizon.api.login.LoginRequest;
import com.oculus.horizon.api.login.LoginResponse;
import com.oculus.horizon.api.logout.LogoutRequest;
import com.oculus.horizon.api.logout.LogoutResponse;
import com.oculus.horizon.api.media.CastNotifResponse;
import com.oculus.horizon.api.media.DeleteCastOfferResponse;
import com.oculus.horizon.api.media.GetCastAnswerResponse;
import com.oculus.horizon.api.media.SetCastOfferResponse;
import com.oculus.horizon.api.profile.ChangePasswordResponse;
import com.oculus.horizon.api.profile.ChangePinResponse;
import com.oculus.horizon.api.profile.DataBlobResponse;
import com.oculus.horizon.api.profile.MyUserProfileResponse;
import com.oculus.horizon.api.push.PushTokenResponse;
import com.oculus.horizon.api.registration.RegisterResponse;
import com.oculus.horizon.api.registration.SupportedCountriesResponse;
import com.oculus.horizon.api.store.SectionItemsResponse;
import com.oculus.horizon.api.store.StoresResponse;
import com.oculus.horizon.api.tos.AcceptTermsOfServiceResponse;
import com.oculus.horizon.api.tos.CheckTermsOfServiceResponse;
import com.oculus.horizon.api.tos.TermsOfServiceResponse;
import java.util.Map;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;

public interface ApiServiceInterface {
    public static final String HEADER_AUTHORIZATION = "Authorization";

    @POST("/graphql")
    void acceptTos(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<AcceptTermsOfServiceResponse> callback);

    @POST("/graphql")
    void changePassword(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<ChangePasswordResponse> callback);

    @POST("/change_pin")
    void changePin(@Query("current_password") String str, @Query("pin") String str2, @Body String str3, Callback<ChangePinResponse> callback);

    @GET("/required_mobile_versions")
    void checkMinimumRequiredAppVersion(Callback<MinimumAppVersion> callback);

    @GET("/required_mobile_versions?account_creation=true")
    void checkSetupRequiredAppVersion(Callback<MinimumAppVersion> callback);

    @GET("/graphql")
    void checkTos(@Query("q") String str, Callback<CheckTermsOfServiceResponse> callback);

    @GET("/graphql")
    void fetchAllPaymentMethods(@Query("q") String str, Callback<PaymentMethodsResponse> callback);

    @GET("/graphql")
    void fetchOrders(@Query("q") String str, Callback<OrdersResponse> callback);

    @GET("/graphql")
    void fetchPaymentAccount(@Query("q") String str, Callback<PaymentAccountResponse> callback);

    @GET("/graphql")
    void fetchPaymentMethods(@Query("q") String str, Callback<ExternalCreditCardsResponse> callback);

    @GET("/graphql")
    GenerateDownloadUriResponse generateDownloadUri(@Query("q") String str, @Query("query_params") String str2);

    @GET("/graphql")
    void generateDownloadUri(@Query("q") String str, @Query("query_params") String str2, Callback<GenerateDownloadUriResponse> callback);

    @GET("/graphql")
    ActiveEntitlementsResponse getActiveEntitlements(@Query("q") String str, @Query("query_params") String str2);

    @GET("/graphql")
    DataBlobResponse getDataBlob(@Query("q") String str);

    @GET("/graphql")
    void getMyUserProfile(@Header("Authorization") String str, @Query("q") String str2, @Query("query_params") String str3, Callback<MyUserProfileResponse> callback);

    @GET("/graphql")
    void getSectionItems(@Query("q") String str, @Query("query_params") String str2, Callback<Map<String, SectionItemsResponse>> callback);

    @GET("/graphql")
    void getStores(@Query("q") String str, @Query("query_params") String str2, @Header("x-oculus-store-version") String str3, Callback<StoresResponse> callback);

    @GET("/countries")
    void getSupportedCountries(Callback<SupportedCountriesResponse> callback);

    @GET("/tos")
    void getTOS(@Query("type") String str, @Query("locale") String str2, Callback<TermsOfServiceResponse> callback);

    @GET("/graphql")
    void getUserCloudFileWildcards(@Query("q") String str, @Query("query_params") String str2, Callback<UserCloudFileWildcardsResponse> callback);

    @GET("/graphql")
    void getUserCloudFiles(@Query("q") String str, @Query("query_params") String str2, Callback<UserCloudFilesResponse> callback);

    @POST("/login")
    void logIn(@Body LoginRequest loginRequest, Callback<LoginResponse> callback);

    @POST("/logout")
    void logOut(@Body LogoutRequest logoutRequest, Callback<LogoutResponse> callback);

    @POST("/graphql")
    void registerPushToken(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<PushTokenResponse> callback);

    @POST("/register")
    void registerUser(@QueryMap Map<String, String> map, @Body String str, Callback<RegisterResponse> callback);

    @POST("/graphql")
    void requestCastNotif(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<CastNotifResponse> callback);

    @POST("/graphql")
    void requestDeleteCastOffer(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<DeleteCastOfferResponse> callback);

    @GET("/graphql")
    void requestGetCastAnswer(@Query("q") String str, @Query("query_params") String str2, Callback<GetCastAnswerResponse> callback);

    @POST("/graphql")
    void requestRefund(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<RefundResponse> callback);

    @POST("/graphql")
    void requestSetCastOffer(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<SetCastOfferResponse> callback);

    @POST("/graphql")
    Map<String, DataBlobResponse> setDataBlob(@Query("q") String str, @Query("query_params") String str2, @Body String str3);

    @POST("/graphql")
    void uploadAndRemoveUserCloudFiles(@Query("q") String str, @Query("query_params") String str2, @Body String str3, Callback<UploadAndRemoveUserCloudFilesResponse> callback);

    @POST("/upload_user_profile_photo")
    @Multipart
    void uploadUserProfilePicture(@Part("file") TypedFile typedFile, Callback<Void> callback);
}
