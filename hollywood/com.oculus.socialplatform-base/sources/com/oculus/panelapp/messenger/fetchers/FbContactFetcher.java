package com.oculus.panelapp.messenger.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass1y2;
import android.util.Log;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FbContactFetcher implements IContactFetcher {
    public static final String CONTACT_QUERY_ID = "3518037618256805";
    public static final String TAG = LoggingUtil.tag(FbContactFetcher.class);
    public String mAccessToken;
    public OkHttpClient mOkHttpClient;
    public AbstractC12271xB mTokenDisposable = FBAuthManager.queryAccessToken().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
        /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$zEeNKcovC0S58rIdRqP4lAiKsVs2 */

        @Override // X.AbstractC12851yS
        public final void accept(Object obj) {
            FbContactFetcher.this.mAccessToken = (String) obj;
        }
    }, $$Lambda$FbContactFetcher$XUL6eqiMWbRK4mvhmApXstCroNo2.INSTANCE);

    public static /* synthetic */ void lambda$new$1(Throwable th) throws Exception {
    }

    public static /* synthetic */ void lambda$queryContacts$4(IContactFetcher.ContactQueryCallback contactQueryCallback, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = FacebookGraphQLUtil.parseField(FacebookGraphQLUtil.parseField(jSONObject, "friends_search"), "results").optJSONArray("nodes");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(parseContact(optJSONArray.optJSONObject(i)));
            }
            UiThreadExecutor.getInstance().execute(new Runnable(arrayList) {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$vjkqKld1dSXikYfsINJl889d5zU2 */
                public final /* synthetic */ ArrayList f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IContactFetcher.ContactQueryCallback.this.onResult(this.f$1);
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
            UiThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$HLeM3fMmhyf9cGSyHNrJRGMWy0I2 */

                public final void run() {
                    IContactFetcher.ContactQueryCallback.this.onError();
                }
            });
        }
    }

    public static /* synthetic */ void lambda$queryContacts$6(IContactFetcher.ContactQueryCallback contactQueryCallback, Throwable th) {
        Log.e(TAG, "Failed to fetch contacts");
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$6ZdWVr1JGk2FekRFQcslgnXPAng2 */

            public final void run() {
                IContactFetcher.ContactQueryCallback.this.onError();
            }
        });
    }

    public static MessengerContact parseContact(JSONObject jSONObject) throws JSONException {
        return new MessengerContact(jSONObject.getString("id"), jSONObject.getJSONObject("profile_picture").getString("uri"), jSONObject.getString("name"));
    }

    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void destroy() {
        this.mTokenDisposable.dispose();
    }

    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void queryContacts(String str, IContactFetcher.ContactQueryCallback contactQueryCallback) {
        if (this.mAccessToken != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("query", str);
                FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, CONTACT_QUERY_ID, jSONObject, this.mAccessToken, new FacebookGraphQLUtil.SuccessCallback() {
                    /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$QxYaB0x18058B4mFRaFMed5eXw2 */

                    @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
                    public final void callback(JSONObject jSONObject) {
                        FbContactFetcher.lambda$queryContacts$4(IContactFetcher.ContactQueryCallback.this, jSONObject);
                    }
                }, new FacebookGraphQLUtil.FailureCallback() {
                    /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$FbContactFetcher$6QtfiU8jZkMogybbYJ1V2ajoOsQ2 */

                    @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                    public final void callback(Throwable th) {
                        FbContactFetcher.lambda$queryContacts$6(IContactFetcher.ContactQueryCallback.this, th);
                    }
                });
            } catch (JSONException unused) {
                Log.e(TAG, " invalid variable JSON");
            }
        }
    }

    public FbContactFetcher(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public /* synthetic */ void lambda$new$0$FbContactFetcher(String str) throws Exception {
        this.mAccessToken = str;
    }
}
