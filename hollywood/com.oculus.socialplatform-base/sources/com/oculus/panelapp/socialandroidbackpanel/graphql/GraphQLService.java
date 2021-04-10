package com.oculus.panelapp.socialandroidbackpanel.graphql;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLRequestAuthTokenFactory;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

public class GraphQLService {
    public static final JSONObject EMPTY_JSON = new JSONObject();
    public static final String TAG = LoggingUtil.tag(GraphQLService.class);
    public final GraphQLRequestAuthTokenFactory mGraphQLRequestAuthTokenFactory;
    @Nullable
    public OkHttpClient mOkHttpClient;

    public interface Callback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public void queryDoc(String str, @Nullable JSONObject jSONObject, Callback callback) {
        OkHttpClient httpClient = getHttpClient();
        if (httpClient == null) {
            callback.onFailure("okHttpClient is null");
        } else {
            this.mGraphQLRequestAuthTokenFactory.registerQueryWithAccessToken(new GraphQLRequestAuthTokenFactory.AuthTokenCallback(httpClient, str, jSONObject, callback) {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.$$Lambda$GraphQLService$M8otbiJl8pOzQ9MoXVVx3XmOQ2 */
                public final /* synthetic */ OkHttpClient f$1;
                public final /* synthetic */ String f$2;
                public final /* synthetic */ JSONObject f$3;
                public final /* synthetic */ GraphQLService.Callback f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLRequestAuthTokenFactory.AuthTokenCallback
                public final void onAccessTokenReceived(String str) {
                    GraphQLService.this.lambda$queryDoc$0$GraphQLService(this.f$1, this.f$2, this.f$3, this.f$4, str);
                }
            });
        }
    }

    private void createHttpClient() {
        if (this.mOkHttpClient == null) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
                this.mOkHttpClient = builder.build();
            } catch (Exception e) {
                Log.e(TAG, "Error create http client: ", e);
            }
        }
    }

    private OkHttpClient getHttpClient() {
        if (this.mOkHttpClient == null) {
            createHttpClient();
        }
        return this.mOkHttpClient;
    }

    public void destroy() {
        this.mGraphQLRequestAuthTokenFactory.destroy();
        this.mOkHttpClient = null;
    }

    public /* synthetic */ void lambda$queryDoc$0$GraphQLService(OkHttpClient okHttpClient, String str, JSONObject jSONObject, final Callback callback, String str2) {
        if (jSONObject == null) {
            jSONObject = EMPTY_JSON;
        }
        GraphQLUtil.queryDoc(okHttpClient, str, jSONObject, str2, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                callback.onFailure(str);
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                callback.onSuccess(jSONObject);
            }
        });
    }

    public GraphQLService(Application application) {
        createHttpClient();
        this.mGraphQLRequestAuthTokenFactory = new GraphQLRequestAuthTokenFactory(application);
    }
}
