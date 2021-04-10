package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.common.util.UriUtil;
import com.google.common.net.HttpHeaders;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.notifications.OkHttpClientFactory;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsViewModel implements ViewModelLifecycle {
    private static final String GRAPHURL = "graph.oculus.com";
    private static final String TAG = "NotificationsViewModel";
    private OkHttpClient mOkhttpClient = OkHttpClientFactory.getInstance(this.mPanelApp.getContext().getApplicationContext()).getClient();
    private AnytimeUIAndroidPanelAppV2 mPanelApp;

    public NotificationsViewModel(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        Log.v(TAG, "NotificationsViewModel constructed");
    }

    public synchronized void syncSeenState(final IBaseVRNotification iBaseVRNotification, final AbstractVRNotification.NotificationSeenState notificationSeenState, boolean z, @Nullable final Callback callback) {
        if (z) {
            logSeenStateEvent(iBaseVRNotification, notificationSeenState);
        }
        if (validAccessToken() && !TextUtils.isEmpty(iBaseVRNotification.getFBID())) {
            Request build = prepareGraphqlRequest().post(new FormBody.Builder().addEncoded(FacebookGraphQLUtil.QUERY_PARAM_DOC, NotificationsGraphQL.notificationUpdateSeenState(Long.parseLong(iBaseVRNotification.getFBID()), notificationSeenState.name())).addEncoded("access_token", getAccessToken()).build()).build();
            Log.v(TAG, String.format("Sync seen state queued for %s, %s", iBaseVRNotification, notificationSeenState));
            this.mOkhttpClient.newCall(build).enqueue(new Callback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsViewModel.AnonymousClass1 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.v(NotificationsViewModel.TAG, String.format("updateNotificationSeenState mutation failed for notif %s to state %s", iBaseVRNotification, notificationSeenState), iOException);
                    Callback callback = callback;
                    if (callback != null) {
                        callback.onFailure(call, iOException);
                    }
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    Log.v(NotificationsViewModel.TAG, String.format("updateNotificationSeenState mutation success for %s to state %s", iBaseVRNotification, notificationSeenState));
                    Callback callback = callback;
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                }
            });
        }
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mOkhttpClient = null;
        this.mPanelApp = null;
    }

    private void logSeenStateEvent(IBaseVRNotification iBaseVRNotification, AbstractVRNotification.NotificationSeenState notificationSeenState) {
        this.mPanelApp.logNotificationEvent(NotificationsHelper.NotificationLogEvents.fromSeenState(notificationSeenState).getLogEventName(), iBaseVRNotification);
    }

    private Request.Builder prepareGraphqlRequest() {
        Request.Builder url = new Request.Builder().url(new HttpUrl.Builder().scheme(UriUtil.HTTPS_SCHEME).host(GRAPHURL).addPathSegment("graphql").build());
        return url.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + getAccessToken());
    }

    private boolean validAccessToken() {
        return !TextUtils.isEmpty(this.mPanelApp.getAccessToken());
    }

    private String getAccessToken() {
        return this.mPanelApp.getAccessToken();
    }
}
