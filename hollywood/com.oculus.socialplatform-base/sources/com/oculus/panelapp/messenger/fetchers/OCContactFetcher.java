package com.oculus.panelapp.messenger.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass1y2;
import X.C137220e;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OCContactFetcher implements IContactFetcher {
    public static final String CONTACT_QUERY_ID = "3341438882644132";
    public static final String TAG = LoggingUtil.tag(OCContactFetcher.class);
    public String mAccessToken;
    public final OkHttpClient mOkHttpClient;
    public AbstractC12271xB mTokenQuery;

    public static MessengerContact parseContact(JSONObject jSONObject) throws JSONException {
        String str;
        JSONObject optJSONObject = jSONObject.optJSONObject("profile_photo");
        if (optJSONObject != null) {
            str = optJSONObject.optString("uri");
        } else {
            str = "";
        }
        return new MessengerContact(jSONObject.getString("id"), str, jSONObject.getString("alias"));
    }

    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void destroy() {
        this.mTokenQuery.dispose();
    }

    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void queryContacts(String str, final IContactFetcher.ContactQueryCallback contactQueryCallback) {
        JSONObject jSONObject = new JSONObject();
        if (this.mAccessToken == null) {
            Log.e(TAG, "no access token");
            contactQueryCallback.onError();
            return;
        }
        try {
            jSONObject.put("query", str);
            final long currentTimeMillis = System.currentTimeMillis();
            GraphQLUtil.query(this.mOkHttpClient, CONTACT_QUERY_ID, jSONObject.toString(), this.mAccessToken, new GraphQLUtil.Result() {
                /* class com.oculus.panelapp.messenger.fetchers.OCContactFetcher.AnonymousClass1 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    Log.e(OCContactFetcher.TAG, "queryContacts() failed");
                    UiThreadExecutor.getInstance().execute(new Runnable() {
                        /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCContactFetcher$1$H6FmHUErXyMYPMhuyCU_WJEVr682 */

                        public final void run() {
                            IContactFetcher.ContactQueryCallback.this.onError();
                        }
                    });
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    System.currentTimeMillis();
                    try {
                        JSONArray optJSONArray = FacebookGraphQLUtil.parseField(FacebookGraphQLUtil.parseField(jSONObject, "data"), "messenger_contacts").optJSONArray("nodes");
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            arrayList.add(OCContactFetcher.parseContact(optJSONArray.getJSONObject(i)));
                        }
                        UiThreadExecutor.getInstance().execute(new Runnable(arrayList) {
                            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCContactFetcher$1$QlA2QTg5chn9pbdxStVS7Aigad82 */
                            public final /* synthetic */ ArrayList f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                IContactFetcher.ContactQueryCallback.this.onResult(this.f$1);
                            }
                        });
                    } catch (JSONException e) {
                        Log.e(OCContactFetcher.TAG, e.toString());
                        UiThreadExecutor.getInstance().execute(new Runnable() {
                            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCContactFetcher$1$D5Ufd_Sp2KdAt5NiNih6rSwZmCg2 */

                            public final void run() {
                                IContactFetcher.ContactQueryCallback.this.onError();
                            }
                        });
                    }
                }
            });
        } catch (JSONException unused) {
            Log.e(TAG, " invalid variable JSON");
            contactQueryCallback.onError();
        }
    }

    public OCContactFetcher(OkHttpClient okHttpClient, UserAppScopedAuthHelper userAppScopedAuthHelper) {
        this.mOkHttpClient = okHttpClient;
        this.mTokenQuery = userAppScopedAuthHelper.queryAccessToken().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCContactFetcher$1demSbNUyWLofwhpRGoOurCGno2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCContactFetcher.this.mAccessToken = (String) obj;
            }
        }, C137220e.A06);
    }

    public /* synthetic */ void lambda$new$0$OCContactFetcher(String str) throws Exception {
        this.mAccessToken = str;
    }
}
