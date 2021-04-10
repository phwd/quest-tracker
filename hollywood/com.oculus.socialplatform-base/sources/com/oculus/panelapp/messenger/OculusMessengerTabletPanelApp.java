package com.oculus.panelapp.messenger;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.fbauth.FBLinkingChecker;
import com.oculus.panelapp.messenger.api.IMessengerAPI;
import com.oculus.panelapp.messenger.api.MessengerAPIManager;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.api.OCMessengerAPI;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.panelapp.messenger.fetchers.OCCanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.OCContactFetcher;
import com.oculus.panelapp.messenger.views.MessengerEmojiHandler;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;

public final class OculusMessengerTabletPanelApp extends BaseMessengerTabletPanelApp {
    public static String TAG = LoggingUtil.tag(OculusMessengerTabletPanelApp.class);
    @Nullable
    public MessengerAPIManager mAPIManager;
    @Nullable
    public UserAppScopedAuthHelper mAuthHelper;
    @Nullable
    public ICanViewerMessageFetcher mCanViewerMessageFetcher;
    @Nullable
    public IContactFetcher mContactQuery;
    @Nullable
    public FBLinkingChecker mFBLinkingChecker;
    public boolean mInitialized;
    public boolean mIsFBLinked;
    @Nullable
    public OkHttpClient sOkHttpClient;

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public MessengerEmojiHandler getEmojiHandler() {
        return null;
    }

    public /* synthetic */ void lambda$new$0$OculusMessengerTabletPanelApp(Application application) {
        this.mIsFBLinked = true;
        if (this.sOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
            this.sOkHttpClient = builder.build();
        }
        String orDefault = this.mEnvironmentArgs.getOrDefault("_oc_userid", "");
        OkHttpClient okHttpClient = this.sOkHttpClient;
        UserAppScopedAuthHelper userAppScopedAuthHelper = new UserAppScopedAuthHelper(application, orDefault, "1953748974690454", okHttpClient);
        this.mAuthHelper = userAppScopedAuthHelper;
        this.mAPIManager = new MessengerAPIManager();
        OCMessengerAPI oCMessengerAPI = new OCMessengerAPI(application, orDefault, okHttpClient, userAppScopedAuthHelper);
        oCMessengerAPI.initialize();
        this.mAPIManager.updateAPI(oCMessengerAPI);
        this.mContactQuery = new OCContactFetcher(this.sOkHttpClient, this.mAuthHelper);
        this.mCanViewerMessageFetcher = new OCCanViewerMessageFetcher(this.sOkHttpClient, this.mAuthHelper);
        showMainLayer();
        this.mInitialized = true;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void updateAPIs(MessengerAPIType messengerAPIType) {
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void actionNavigateToProfile(long j) {
        Uri.Builder appendPath = new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_VR_PROFILE_URI).appendPath(String.valueOf(j));
        IMessengerAPI iMessengerAPI = getAPIManager().mCurrentAPI;
        if (!(iMessengerAPI == null || iMessengerAPI.getCurrentThread() == null)) {
            String userID = iMessengerAPI.getUserID();
            if (iMessengerAPI.getCurrentThread() != null) {
                appendPath.appendQueryParameter("returnRoute", SystemUXRoute.AUI_CHATS.path()).appendQueryParameter("returnUri", new Uri.Builder().encodedPath("").appendPath("mailbox").appendPath(userID).appendPath("thread").appendPath(String.valueOf(iMessengerAPI.getCurrentThread().getThreadKey())).build().toString()).appendQueryParameter("entrypoint", "aui_oc_chat_tablet");
            } else {
                return;
            }
        }
        actionNavigate(SystemUXRoute.AUI_PROFILE, appendPath.build().toString());
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        MessengerAPIManager messengerAPIManager = this.mAPIManager;
        if (messengerAPIManager != null) {
            messengerAPIManager.destroy();
        }
        IContactFetcher iContactFetcher = this.mContactQuery;
        if (iContactFetcher != null) {
            iContactFetcher.destroy();
        }
        ICanViewerMessageFetcher iCanViewerMessageFetcher = this.mCanViewerMessageFetcher;
        if (iCanViewerMessageFetcher != null) {
            iCanViewerMessageFetcher.destroy();
        }
        if (this.sOkHttpClient != null) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() {
                /* class com.oculus.panelapp.messenger.$$Lambda$OculusMessengerTabletPanelApp$G3bqgEItLaSj8QbELnZRl4gzP02 */

                public final void run() {
                    OculusMessengerTabletPanelApp.this.lambda$destroy$1$OculusMessengerTabletPanelApp();
                }
            });
            newSingleThreadExecutor.shutdown();
        }
        UserAppScopedAuthHelper userAppScopedAuthHelper = this.mAuthHelper;
        if (userAppScopedAuthHelper != null) {
            userAppScopedAuthHelper.destroy();
        }
        FBLinkingChecker fBLinkingChecker = this.mFBLinkingChecker;
        if (fBLinkingChecker != null) {
            fBLinkingChecker.destroy();
            this.mFBLinkingChecker = null;
        }
        super.destroy();
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public MessengerAPIManager getAPIManager() {
        return this.mAPIManager;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public ICanViewerMessageFetcher getCanViewerMessageFetcher() {
        return this.mCanViewerMessageFetcher;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public IContactFetcher getContactQuery() {
        return this.mContactQuery;
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp
    public TabletType getTelemetryTabletType() {
        return TabletType.OC_MESSENGER;
    }

    public /* synthetic */ void lambda$destroy$1$OculusMessengerTabletPanelApp() {
        this.sOkHttpClient.connectionPool.evictAll();
    }

    public OculusMessengerTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        FBLinkingChecker fBLinkingChecker = new FBLinkingChecker(this, getFBLinkingFetcher());
        this.mFBLinkingChecker = fBLinkingChecker;
        fBLinkingChecker.redirectToLinkFlowIfUnlinked(new FBLinkingChecker.OnVerifiedFBLinkedCallback(application) {
            /* class com.oculus.panelapp.messenger.$$Lambda$OculusMessengerTabletPanelApp$NMZ5HZeFAoKPykIa3G0ye32FuC82 */
            public final /* synthetic */ Application f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.common.socialtablet.fbauth.FBLinkingChecker.OnVerifiedFBLinkedCallback
            public final void onVerifiedFBLinked() {
                OculusMessengerTabletPanelApp.this.lambda$new$0$OculusMessengerTabletPanelApp(this.f$1);
            }
        });
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onForeground() {
        super.onForeground();
        if (!this.mIsFBLinked || !this.mInitialized) {
            this.mFrameCommandChannel.sendCommand("quitAndHide");
        }
    }
}
