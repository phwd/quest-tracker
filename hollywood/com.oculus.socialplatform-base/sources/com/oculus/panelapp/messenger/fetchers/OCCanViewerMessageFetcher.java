package com.oculus.panelapp.messenger.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass1y2;
import X.C137220e;
import android.util.Log;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.IOException;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class OCCanViewerMessageFetcher implements ICanViewerMessageFetcher {
    public static final String QUERY = "query OCCanViewerMessageQuery($node_id:ID!) {\n  node(node_id: $node_id) {\n    ... on User {\n      can_viewer_message\n    }\n  }\n}";
    public static final String TAG = "OCCanViewerMessageFetcher";
    public String mAccessToken;
    public OkHttpClient mOkHttpClient;
    public AbstractC12271xB mTokenDisposable;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    private JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("node_id", String.valueOf(str));
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(TAG, "error building GraphQL variables");
            return null;
        }
    }

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void destroy() {
        this.mTokenDisposable.dispose();
    }

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void query(String str, final ICanViewerMessageFetcher.CanViewerMessageSuccessCallback canViewerMessageSuccessCallback, final ICanViewerMessageFetcher.CanViewerMessageFailureCallback canViewerMessageFailureCallback) {
        if (this.mAccessToken == null) {
            Log.w(TAG, "access token is null when query is called");
        } else {
            GraphQLUtil.queryDoc(this.mOkHttpClient, QUERY, buildGraphQLVariables(str), this.mAccessToken, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.messenger.fetchers.OCCanViewerMessageFetcher.AnonymousClass1 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    Log.e(OCCanViewerMessageFetcher.TAG, "querying can viewer message participants failed");
                    UiThreadExecutor.getInstance().execute(new Runnable(str) {
                        /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCCanViewerMessageFetcher$1$OtDsuHqNaUhQZEfyQeVBE391Aeg2 */
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ICanViewerMessageFetcher.CanViewerMessageFailureCallback.this.onFailure(new IOException(this.f$1));
                        }
                    });
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    JSONObject optJSONObject;
                    try {
                        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
                        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("node")) == null) {
                            Log.e(OCCanViewerMessageFetcher.TAG, "node is null when parsing whether viewer can message user");
                            UiThreadExecutor.getInstance().execute(new Runnable("node is null when parsing whether viewer can message user") {
                                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCCanViewerMessageFetcher$1$WOWeTBcDp3b2brC2Xx6oShlh14g2 */
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
                        UiThreadExecutor.getInstance().execute(new Runnable(optJSONObject.getBoolean("can_viewer_message")) {
                            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCCanViewerMessageFetcher$1$NEpJBahVJ62PMo6w98wTzjxBbc2 */
                            public final /* synthetic */ boolean f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                ICanViewerMessageFetcher.CanViewerMessageSuccessCallback.this.onSuccess(Boolean.valueOf(this.f$1));
                            }
                        });
                    } catch (JSONException e) {
                        Log.e(OCCanViewerMessageFetcher.TAG, "failed to parse whether viewer can message user: ", e);
                        UiThreadExecutor.getInstance().execute(new Runnable(e) {
                            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCCanViewerMessageFetcher$1$hKptADjEoovtaE8e0nhXa_iuQmk2 */
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
            });
        }
    }

    public OCCanViewerMessageFetcher(OkHttpClient okHttpClient, UserAppScopedAuthHelper userAppScopedAuthHelper) {
        this.mOkHttpClient = okHttpClient;
        this.mTokenDisposable = userAppScopedAuthHelper.queryAccessToken().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCCanViewerMessageFetcher$bKR6HipwdIPgmKsiWSDVJHH0hG02 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCCanViewerMessageFetcher.this.mAccessToken = (String) obj;
            }
        }, C137220e.A06);
    }

    public /* synthetic */ void lambda$new$0$OCCanViewerMessageFetcher(String str) throws Exception {
        this.mAccessToken = str;
    }
}
