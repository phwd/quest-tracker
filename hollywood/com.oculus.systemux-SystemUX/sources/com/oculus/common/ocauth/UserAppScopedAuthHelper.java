package com.oculus.common.ocauth;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.ocauth.OVRAuthHelper;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAppScopedAuthHelper {
    private static final String OCULUS_GRAPHAPI_AUTH_PATH = "authenticate_application";
    private static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    private static final String TAG = "UserAppScopedAuthHelper";
    @Nullable
    private Single<String> mAppScopedAccessTokenSingle;
    private final OVRAuthHelper mAuthHelper;
    @Nullable
    private OVRAuthHelper.OvrAuthTokenCallback mCallback;
    private final String mOcAppId;
    private final OkHttpClient mOkHttpClient;
    private final String mUserId;

    public UserAppScopedAuthHelper(Application application, String str, String str2, OkHttpClient okHttpClient) {
        this.mAuthHelper = new OVRAuthHelper(application);
        this.mUserId = str;
        this.mOcAppId = str2;
        this.mOkHttpClient = okHttpClient;
    }

    public void destroy() {
        OVRAuthHelper.OvrAuthTokenCallback ovrAuthTokenCallback = this.mCallback;
        if (ovrAuthTokenCallback != null) {
            this.mAuthHelper.removeAccessTokenListener(ovrAuthTokenCallback);
            this.mCallback = null;
        }
        this.mAppScopedAccessTokenSingle = null;
    }

    public Single<String> queryAccessToken() {
        Single<String> single = this.mAppScopedAccessTokenSingle;
        if (single != null) {
            return single;
        }
        Log.d(TAG, "Logging user in.");
        this.mAppScopedAccessTokenSingle = Single.create(new SingleOnSubscribe() {
            /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$vIe4lPnPPPK8MN7ugAn7Bi9_EM */

            public final void subscribe(SingleEmitter singleEmitter) {
                UserAppScopedAuthHelper.this.lambda$queryAccessToken$1$UserAppScopedAuthHelper(singleEmitter);
            }
        }).subscribeOn(ExecutorUtil.getScheduler()).doOnSuccess($$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTRU0.INSTANCE).cache();
        return this.mAppScopedAccessTokenSingle;
    }

    public /* synthetic */ void lambda$queryAccessToken$1$UserAppScopedAuthHelper(SingleEmitter singleEmitter) throws Exception {
        singleEmitter.getClass();
        $$Lambda$1CoeolIcNhC76rIucG7AtPtBrSw r0 = new Consumer(singleEmitter) {
            /* class com.oculus.common.ocauth.$$Lambda$1CoeolIcNhC76rIucG7AtPtBrSw */
            private final /* synthetic */ SingleEmitter f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.onSuccess((String) obj);
            }
        };
        singleEmitter.getClass();
        fetchUserInfo(r0, new Consumer(singleEmitter) {
            /* class com.oculus.common.ocauth.$$Lambda$PEEqURjz1mrIDDAaNL4F6p7Eiyg */
            private final /* synthetic */ SingleEmitter f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.onError((Throwable) obj);
            }
        });
        Log.d(TAG, "Successfully logged in.");
    }

    private void fetchUserInfo(Consumer<String> consumer, Consumer<Throwable> consumer2) {
        Log.d(TAG, "fetchUserInfo - start");
        if (this.mCallback == null) {
            this.mCallback = new OVRAuthHelper.OvrAuthTokenCallback(consumer, consumer2) {
                /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$ytBn6LLk1rBqnSjVng7CPTl4Q_8 */
                private final /* synthetic */ Consumer f$1;
                private final /* synthetic */ Consumer f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthTokenCallback
                public final void onAccessTokenReceived(String str) {
                    UserAppScopedAuthHelper.this.lambda$fetchUserInfo$4$UserAppScopedAuthHelper(this.f$1, this.f$2, str);
                }
            };
        }
        this.mAuthHelper.registerAccessTokenListener(this.mCallback);
        this.mAuthHelper.fetchAccessToken();
    }

    public /* synthetic */ void lambda$fetchUserInfo$4$UserAppScopedAuthHelper(Consumer consumer, Consumer consumer2, String str) {
        Log.d(TAG, "fetchUserInfo - completed");
        synchronized (this) {
            if (!TextUtils.isEmpty(this.mUserId)) {
                if (!TextUtils.isEmpty(str)) {
                    sendPostRequestForUserInfo(str, consumer, new Consumer(consumer2) {
                        /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$eEACSQIi6mFUtxhDv3G7oN8uNWU */
                        private final /* synthetic */ Consumer f$0;

                        {
                            this.f$0 = r1;
                        }

                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            UserAppScopedAuthHelper.lambda$null$3(this.f$0, (Throwable) obj);
                        }
                    });
                    return;
                }
            }
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("fetchUserInfo -");
            sb.append(TextUtils.isEmpty(this.mUserId) ? " no valid userId" : "");
            sb.append(TextUtils.isEmpty(str) ? " no valid token" : "");
            Log.d(str2, sb.toString());
        }
    }

    static /* synthetic */ void lambda$null$3(Consumer consumer, Throwable th) {
        Log.e(TAG, "Error requesting app scoped access token");
        consumer.accept(th);
    }

    private void sendPostRequestForUserInfo(String str, final Consumer<String> consumer, final Consumer<Throwable> consumer2) {
        Log.d(TAG, "fetchUserInfo - fetching app scoped access token");
        HttpUrl.Builder newBuilder = ((HttpUrl) Objects.requireNonNull(HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT))).newBuilder();
        newBuilder.addPathSegment(OCULUS_GRAPHAPI_AUTH_PATH);
        this.mOkHttpClient.newCall(new Request.Builder().url(newBuilder.build()).post(new FormBody.Builder().add("access_token", str).add("app_id", this.mOcAppId).build()).build()).enqueue(new Callback() {
            /* class com.oculus.common.ocauth.UserAppScopedAuthHelper.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                consumer2.accept(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Log.d(UserAppScopedAuthHelper.TAG, "fetchUserInfo - fetched response for app scoped token request");
                if (!response.isSuccessful()) {
                    consumer2.accept(new RuntimeException(String.format("GraphAPI request got response (%s)", String.valueOf(response.code()))));
                    return;
                }
                try {
                    consumer.accept(new JSONObject(((ResponseBody) Objects.requireNonNull(response.body())).string()).getString("access_token"));
                } catch (IOException | JSONException e) {
                    consumer2.accept(e);
                }
            }
        });
    }
}
