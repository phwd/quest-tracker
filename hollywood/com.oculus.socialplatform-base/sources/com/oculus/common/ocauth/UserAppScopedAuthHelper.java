package com.oculus.common.ocauth;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC12361xL;
import X.AbstractC13251zE;
import X.AnonymousClass1zJ;
import X.AnonymousClass219;
import X.C13011yj;
import X.C13261zF;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.ocauth.OVRAuthHelper;
import com.oculus.messenger.service.MessengerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAppScopedAuthHelper {
    public static final String OCULUS_GRAPHAPI_AUTH_PATH = "authenticate_application";
    public static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    public static final String TAG = "UserAppScopedAuthHelper";
    @Nullable
    public AbstractC13251zE<String> mAppScopedAccessTokenSingle;
    public final OVRAuthHelper mAuthHelper;
    @Nullable
    public OVRAuthHelper.OvrAuthTokenCallback mCallback;
    public final String mOcAppId;
    public final OkHttpClient mOkHttpClient;
    public final String mUserId;

    public /* synthetic */ void lambda$fetchUserInfo$3$UserAppScopedAuthHelper(Consumer consumer, Consumer consumer2, String str) {
        synchronized (this) {
            if (TextUtils.isEmpty(this.mUserId) || TextUtils.isEmpty(str)) {
                TextUtils.isEmpty(this.mUserId);
                TextUtils.isEmpty(str);
            } else {
                sendPostRequestForUserInfo(str, consumer, new Consumer(consumer2) {
                    /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$ozLEldZKU0ZM4TnRQeT71AyxjLM2 */
                    public final /* synthetic */ Consumer f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        UserAppScopedAuthHelper.lambda$fetchUserInfo$2(this.f$0, (Throwable) obj);
                    }
                });
            }
        }
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    private void fetchUserInfo(Consumer<String> consumer, Consumer<Throwable> consumer2) {
        OVRAuthHelper.OvrAuthTokenCallback ovrAuthTokenCallback = this.mCallback;
        if (ovrAuthTokenCallback == null) {
            ovrAuthTokenCallback = new OVRAuthHelper.OvrAuthTokenCallback(consumer, consumer2) {
                /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$qdSjlr_6xyRFok9UAuCm0cbueKw2 */
                public final /* synthetic */ Consumer f$1;
                public final /* synthetic */ Consumer f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthTokenCallback
                public final void onAccessTokenReceived(String str) {
                    UserAppScopedAuthHelper.this.lambda$fetchUserInfo$3$UserAppScopedAuthHelper(this.f$1, this.f$2, str);
                }
            };
            this.mCallback = ovrAuthTokenCallback;
        }
        this.mAuthHelper.registerAccessTokenListener(ovrAuthTokenCallback);
        this.mAuthHelper.fetchAccessToken();
    }

    public static /* synthetic */ void lambda$fetchUserInfo$2(Consumer consumer, Throwable th) {
        Log.e(TAG, "Error requesting app scoped access token");
        consumer.accept(th);
    }

    private void sendPostRequestForUserInfo(String str, final Consumer<String> consumer, final Consumer<Throwable> consumer2) {
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
        newBuilder.addPathSegment(OCULUS_GRAPHAPI_AUTH_PATH);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(HttpUrl.canonicalize("access_token", " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        arrayList2.add(HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        String str2 = this.mOcAppId;
        arrayList.add(HttpUrl.canonicalize(MessengerService.InitParamKeys.APP_ID, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        arrayList2.add(HttpUrl.canonicalize(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        Request.Builder builder = new Request.Builder();
        builder.url(newBuilder.build());
        builder.post(new FormBody(arrayList, arrayList2));
        this.mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            /* class com.oculus.common.ocauth.UserAppScopedAuthHelper.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                consumer2.accept(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    consumer2.accept(new RuntimeException(String.format("GraphAPI request got response (%s)", String.valueOf(response.code))));
                    return;
                }
                try {
                    consumer.accept(new JSONObject(response.body.string()).getString("access_token"));
                } catch (IOException | JSONException e) {
                    consumer2.accept(e);
                }
            }
        });
    }

    public void destroy() {
        OVRAuthHelper.OvrAuthTokenCallback ovrAuthTokenCallback = this.mCallback;
        if (ovrAuthTokenCallback != null) {
            this.mAuthHelper.removeAccessTokenListener(ovrAuthTokenCallback);
            this.mCallback = null;
        }
        this.mAppScopedAccessTokenSingle = null;
    }

    public /* synthetic */ void lambda$queryAccessToken$0$UserAppScopedAuthHelper(AbstractC10551og r3) throws Exception {
        fetchUserInfo(new Consumer() {
            /* class com.oculus.common.ocauth.$$Lambda$1CoeolIcNhC76rIucG7AtPtBrSw2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC10551og.this.onSuccess(obj);
            }
        }, new Consumer() {
            /* class com.oculus.common.ocauth.$$Lambda$PEEqURjz1mrIDDAaNL4F6p7Eiyg2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC10551og.this.onError((Throwable) obj);
            }
        });
    }

    public AbstractC13251zE<String> queryAccessToken() {
        AbstractC13251zE<String> r0 = this.mAppScopedAccessTokenSingle;
        if (r0 != null) {
            return r0;
        }
        AbstractC13251zE A00 = AbstractC13251zE.A00(new AbstractC06371Zh() {
            /* class com.oculus.common.ocauth.$$Lambda$UserAppScopedAuthHelper$fmKyhicey0v5dHSFtRM3liNwg3g2 */

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r2) {
                UserAppScopedAuthHelper.this.lambda$queryAccessToken$0$UserAppScopedAuthHelper(r2);
            }
        });
        AbstractC12361xL r1 = ExecutorUtil.SCHEDULER;
        AnonymousClass219.A01(r1, "scheduler is null");
        C13011yj r3 = new C13011yj(A00, r1);
        $$Lambda$UserAppScopedAuthHelper$6sqCTMc78yGutdl95kb085dnx0Y2 r2 = $$Lambda$UserAppScopedAuthHelper$6sqCTMc78yGutdl95kb085dnx0Y2.INSTANCE;
        AnonymousClass219.A01(r2, "onSuccess is null");
        C13261zF r02 = new C13261zF(new AnonymousClass1zJ(r3, r2));
        this.mAppScopedAccessTokenSingle = r02;
        return r02;
    }

    public UserAppScopedAuthHelper(Application application, String str, String str2, OkHttpClient okHttpClient) {
        this.mAuthHelper = new OVRAuthHelper(application);
        this.mUserId = str;
        this.mOcAppId = str2;
        this.mOkHttpClient = okHttpClient;
    }

    public static /* synthetic */ void lambda$queryAccessToken$1(String str) throws Exception {
    }
}
