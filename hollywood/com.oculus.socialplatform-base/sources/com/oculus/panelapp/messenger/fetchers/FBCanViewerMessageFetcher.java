package com.oculus.panelapp.messenger.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass1y2;
import X.C137220e;
import android.util.Log;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.IOException;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBCanViewerMessageFetcher implements ICanViewerMessageFetcher {
    public static final String TAG = "FBCanViewerMessageFetcher";
    public static final String docID = "4090127460999982";
    public String mAccessToken;
    public OkHttpClient mOkHttpClient;
    public AbstractC12271xB mTokenDisposable = FBAuthManager.queryAccessToken().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
        /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$T9hHGDN1ydeAoRITTLCDVzc9gs2 */

        @Override // X.AbstractC12851yS
        public final void accept(Object obj) {
            FBCanViewerMessageFetcher.this.mAccessToken = (String) obj;
        }
    }, C137220e.A06);

    public static JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(TAG, "error building GraphQL variables");
            return null;
        }
    }

    public static /* synthetic */ void lambda$query$4(ICanViewerMessageFetcher.CanViewerMessageFailureCallback canViewerMessageFailureCallback, ICanViewerMessageFetcher.CanViewerMessageSuccessCallback canViewerMessageSuccessCallback, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("messaging_actors");
            if (optJSONArray == null || optJSONArray.length() != 1) {
                Log.e(TAG, "invalid messaging actors when parsing whether viewer can message participant");
                UiThreadExecutor.getInstance().execute(new Runnable("invalid messaging actors when parsing whether viewer can message participant") {
                    /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$m284VfHnd0Vj1a4LzsCLghhzEkA2 */
                    public final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this.onFailure(new IOException(this.f$1));
                    }
                });
                return;
            }
            UiThreadExecutor.getInstance().execute(new Runnable(optJSONArray.getJSONObject(0).getBoolean("can_viewer_message")) {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$sWyxyD_lhhHD0x0qSVEOhv44TAs2 */
                public final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ICanViewerMessageFetcher.CanViewerMessageSuccessCallback.this.onSuccess(Boolean.valueOf(this.f$1));
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "failed to parse whether viewer can message participant: ", e);
            UiThreadExecutor.getInstance().execute(new Runnable(e) {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$PaYy4iWaKv4dlMQqbf35z0w05k2 */
                public final /* synthetic */ JSONException f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this.onFailure(this.f$1);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$query$6(ICanViewerMessageFetcher.CanViewerMessageFailureCallback canViewerMessageFailureCallback, Throwable th) {
        Log.e(TAG, "querying can viewer message failed");
        UiThreadExecutor.getInstance().execute(new Runnable(th) {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$Mhy3IS8JPXtpQUnn3HPuX5s4VY2 */
            public final /* synthetic */ Throwable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this.onFailure(this.f$1);
            }
        });
    }

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void destroy() {
        this.mTokenDisposable.dispose();
    }

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void query(String str, ICanViewerMessageFetcher.CanViewerMessageSuccessCallback canViewerMessageSuccessCallback, ICanViewerMessageFetcher.CanViewerMessageFailureCallback canViewerMessageFailureCallback) {
        if (this.mAccessToken == null) {
            Log.w(TAG, "access token is null when query is called");
        } else {
            FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, docID, buildGraphQLVariables(str), this.mAccessToken, new FacebookGraphQLUtil.SuccessCallback(canViewerMessageSuccessCallback) {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$fzUkDc4pOYLCB8zb30REMBC0G02 */
                public final /* synthetic */ ICanViewerMessageFetcher.CanViewerMessageSuccessCallback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
                public final void callback(JSONObject jSONObject) {
                    FBCanViewerMessageFetcher.lambda$query$4(ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this, this.f$1, jSONObject);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FBCanViewerMessageFetcher$hJqIo778Cfk9m2RMTjYeWp_VVpI2 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBCanViewerMessageFetcher.lambda$query$6(ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this, th);
                }
            });
        }
    }

    public FBCanViewerMessageFetcher(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public /* synthetic */ void lambda$new$0$FBCanViewerMessageFetcher(String str) throws Exception {
        this.mAccessToken = str;
    }
}
