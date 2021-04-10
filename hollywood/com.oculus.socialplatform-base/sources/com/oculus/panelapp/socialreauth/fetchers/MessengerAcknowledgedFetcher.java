package com.oculus.panelapp.socialreauth.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass006;
import X.C137220e;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.IOException;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MessengerAcknowledgedFetcher {
    public static final String QUERY_ID = "3696449177102171";
    public static final String TAG = "MessengerAcknowledgedFetcher";
    public final UserAppScopedAuthHelper mAuthHelper;
    public OkHttpClient mOkHttpClient;
    @Nullable
    public AbstractC12271xB mTokenDisposable;

    @FunctionalInterface
    public interface IsMessengerAcknowledgedFailureCallback {
        void onFailure(Throwable th);
    }

    @FunctionalInterface
    public interface IsMessengerAcknowledgedSuccessCallback {
        void onSuccess(boolean z);
    }

    @VisibleForTesting
    public JSONObject parseObject(JSONObject jSONObject, String... strArr) {
        for (String str : strArr) {
            jSONObject = jSONObject.optJSONObject(str);
            if (jSONObject == null) {
                Log.e(TAG, AnonymousClass006.A07(str, " is empty"));
                return null;
            }
        }
        return jSONObject;
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_serial", str);
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(TAG, "error building GraphQL variables");
            return null;
        }
    }

    public void destroy() {
        AbstractC12271xB r0 = this.mTokenDisposable;
        if (r0 != null) {
            r0.dispose();
        }
    }

    public MessengerAcknowledgedFetcher(OkHttpClient okHttpClient, UserAppScopedAuthHelper userAppScopedAuthHelper) {
        this.mOkHttpClient = okHttpClient;
        this.mAuthHelper = userAppScopedAuthHelper;
    }

    public void query(String str, IsMessengerAcknowledgedSuccessCallback isMessengerAcknowledgedSuccessCallback, IsMessengerAcknowledgedFailureCallback isMessengerAcknowledgedFailureCallback) {
        this.mTokenDisposable = this.mAuthHelper.queryAccessToken().A05(new AbstractC12851yS(str, isMessengerAcknowledgedSuccessCallback, isMessengerAcknowledgedFailureCallback) {
            /* class com.oculus.panelapp.socialreauth.fetchers.$$Lambda$MessengerAcknowledgedFetcher$ZJIxNeEHtF0DiavAhqzgkCEvH9I2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ MessengerAcknowledgedFetcher.IsMessengerAcknowledgedSuccessCallback f$2;
            public final /* synthetic */ MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MessengerAcknowledgedFetcher.this.lambda$query$0$MessengerAcknowledgedFetcher(this.f$1, (String) obj, this.f$2, this.f$3);
            }
        }, C137220e.A06);
    }

    @VisibleForTesting
    /* renamed from: query */
    public void lambda$query$0$MessengerAcknowledgedFetcher(String str, String str2, final IsMessengerAcknowledgedSuccessCallback isMessengerAcknowledgedSuccessCallback, final IsMessengerAcknowledgedFailureCallback isMessengerAcknowledgedFailureCallback) {
        GraphQLUtil.query(this.mOkHttpClient, QUERY_ID, buildGraphQLVariables(str).toString(), str2, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                Log.e(MessengerAcknowledgedFetcher.TAG, "error querying whether viewer has acknowledged messenger on device");
                UiThreadExecutor.getInstance().execute(new Runnable(str) {
                    /* class com.oculus.panelapp.socialreauth.fetchers.$$Lambda$MessengerAcknowledgedFetcher$1$JjEJ15qcTO49VMPtLkyOgeaGx9w2 */
                    public final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback.this.onFailure(new IOException(this.f$1));
                    }
                });
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                try {
                    JSONObject parseObject = MessengerAcknowledgedFetcher.this.parseObject(jSONObject, "data", "viewer", "user");
                    if (parseObject == null) {
                        UiThreadExecutor.getInstance().execute(new Runnable() {
                            /* class com.oculus.panelapp.socialreauth.fetchers.$$Lambda$MessengerAcknowledgedFetcher$1$T5BIDWfy3wzi8kmMn6fRE5buFg2 */

                            public final void run() {
                                MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback.this.onFailure(new IOException("empty field when parsing whether viewer has acknowledged Messenger on device"));
                            }
                        });
                        return;
                    }
                    UiThreadExecutor.getInstance().execute(new Runnable(parseObject.getBoolean("has_acknowledged_messenger")) {
                        /* class com.oculus.panelapp.socialreauth.fetchers.$$Lambda$MessengerAcknowledgedFetcher$1$lEdKqISuHABpkyr20U7H1WUfVpE2 */
                        public final /* synthetic */ boolean f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            MessengerAcknowledgedFetcher.IsMessengerAcknowledgedSuccessCallback.this.onSuccess(this.f$1);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(MessengerAcknowledgedFetcher.TAG, "failed to parse whether viewer has acknowledged messenger on device");
                    UiThreadExecutor.getInstance().execute(new Runnable(e) {
                        /* class com.oculus.panelapp.socialreauth.fetchers.$$Lambda$MessengerAcknowledgedFetcher$1$0hcJQi63H9A3IWLS_V9ChTqcCc2 */
                        public final /* synthetic */ JSONException f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback.this.onFailure(this.f$1);
                        }
                    });
                }
            }
        });
    }
}
