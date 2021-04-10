package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.google.common.net.HttpHeaders;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.notifications.OkHttpClientFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PersistedNotificationSourceHandler extends ListenerBasedNotificationSourceHandler<PersistedNotification> implements INotificationSourceHandler<PersistedNotification> {
    private static String FETCH_MORE_QUERY = "query {viewer {user {id notifications(application_platform: ANDROID, supported_by_caller: true, before: \"%s\") {edges {cursor node {id ndid notification_type xnotif_type notification_seen_state send_time rendered_notification {category title message deeplink_uri destination {package launch_params }extra_data }}}}}}}";
    private static String GRAPHURL = "graph.oculus.com";
    private static String INITIAL_QUERY = "query {viewer {user {id notifications(application_platform: ANDROID, supported_by_caller: true) {edges {cursor node {id ndid notification_type xnotif_type notification_seen_state send_time rendered_notification {category title message deeplink_uri destination {package launch_params }extra_data }}}}}}}";
    private static String TAG = "PersistedNotificationSourceHandler";
    private OkHttpClient mClient;
    private boolean mIsInitialized;
    private String mLastCursor;
    private List<PersistedNotification> mNotifs = new ArrayList();
    private String mToken;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public /* bridge */ /* synthetic */ void acceptListener(INotificationProxyListener iNotificationProxyListener) {
        super.acceptListener(iNotificationProxyListener);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler, com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public /* bridge */ /* synthetic */ boolean acceptsListeners() {
        return super.acceptsListeners();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public /* bridge */ /* synthetic */ void removeListener(INotificationProxyListener iNotificationProxyListener) {
        super.removeListener(iNotificationProxyListener);
    }

    public PersistedNotificationSourceHandler(String str, Context context) {
        super(context);
        this.mToken = str;
        this.mClient = OkHttpClientFactory.getInstance(this.mContext).getClient();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public List<PersistedNotification> initialFetch() {
        Log.i(TAG, "Fetching persisted notifs");
        try {
            this.mNotifs = fetchPersistedNotifs();
        } catch (Exception e) {
            Log.e(TAG, "Exception fetching notifs from network", e);
        }
        setInitializationState(true);
        return this.mNotifs;
    }

    public void refetchNotifs() {
        Log.v(TAG, String.format("Starting retrieval of notifications in background from cursor %s", this.mLastCursor));
        try {
            Response execute = this.mClient.newCall(setupRequest(String.format(FETCH_MORE_QUERY, this.mLastCursor))).execute();
            Log.v(TAG, "Successfully retrieved notifications from network in background");
            parseNotifications(execute.body().string()).forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$PersistedNotificationSourceHandler$lso__wJnB2WosqUtOzb2lRFOipg */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PersistedNotificationSourceHandler.this.lambda$refetchNotifs$218$PersistedNotificationSourceHandler((PersistedNotification) obj);
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "Exception retrieving notifications from network", e);
        }
    }

    public /* synthetic */ void lambda$refetchNotifs$218$PersistedNotificationSourceHandler(PersistedNotification persistedNotification) {
        notifyAdded(persistedNotification);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public Class<PersistedNotification> handlesType() {
        return PersistedNotification.class;
    }

    public void publishRemoval(PersistedNotification persistedNotification) {
        this.mNotifs.remove(persistedNotification);
        this.mListeners.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$PersistedNotificationSourceHandler$WgE0SEgWH4v9wFYJkMnPkZRMfz0 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PersistedNotification persistedNotification;
                ((INotificationProxyListener) obj).onNotificationRemoved(persistedNotification.getClass(), PersistedNotification.this);
            }
        });
    }

    public synchronized boolean getInitializationState() {
        return this.mIsInitialized;
    }

    private synchronized void setInitializationState(boolean z) {
        this.mIsInitialized = z;
    }

    private List<PersistedNotification> fetchPersistedNotifs() {
        Log.v(TAG, "Starting retrieval of notifications in background");
        try {
            Response execute = this.mClient.newCall(setupRequest(INITIAL_QUERY)).execute();
            Log.v(TAG, "Successfully retrieved notifications from network");
            return parseNotifications(execute.body().string());
        } catch (IOException e) {
            Log.e(TAG, "Exception retrieving notifications from network", e);
            return new ArrayList();
        }
    }

    private List<PersistedNotification> parseNotifications(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = (JSONObject) new JSONTokener(str).nextValue();
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing notifs json", e);
            jSONObject = jSONObject2;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray = jSONObject.getJSONObject("data").getJSONObject("viewer").getJSONObject("user").getJSONObject("notifications").getJSONArray("edges");
        } catch (JSONException e2) {
            Log.e(TAG, "Exception in deeply nested json retrieval", e2);
        }
        return toPersistedNotif(jSONArray);
    }

    private List<PersistedNotification> toPersistedNotif(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i == 0) {
                try {
                    this.mLastCursor = jSONArray.getJSONObject(i).getString("cursor");
                } catch (JSONException e) {
                    Log.e(TAG, "Error converting raw payload to PersistedNotification", e);
                }
            }
            arrayList.add(new PersistedNotification(jSONArray.getJSONObject(i).getJSONObject("node")));
        }
        return arrayList;
    }

    private Request setupRequest(String str) {
        HttpUrl build = new HttpUrl.Builder().scheme(UriUtil.HTTPS_SCHEME).host(GRAPHURL).addPathSegment("graphql").build();
        FormBody build2 = new FormBody.Builder().addEncoded(FacebookGraphQLUtil.QUERY_PARAM_DOC, str).addEncoded("access_token", this.mToken).build();
        Request.Builder url = new Request.Builder().url(build);
        return url.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + this.mToken).post(build2).build();
    }
}
