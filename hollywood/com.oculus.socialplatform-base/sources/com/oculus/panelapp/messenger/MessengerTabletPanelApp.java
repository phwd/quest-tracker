package com.oculus.panelapp.messenger;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC136820a;
import X.AnonymousClass1y2;
import X.AnonymousClass219;
import X.C13051yn;
import X.C137220e;
import X.C138920v;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.devicelifecycle.RxNetworkAvailabilityObservable;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fbauth.FBUser;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.panelapp.messenger.api.FBMessengerApi;
import com.oculus.panelapp.messenger.api.IMessengerAPI;
import com.oculus.panelapp.messenger.api.MessengerAPIManager;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.api.TestMessengerAPI;
import com.oculus.panelapp.messenger.fetchers.FBCanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.FbContactFetcher;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.panelapp.messenger.fetchers.TestCanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.TestContactFetcher;
import com.oculus.panelapp.messenger.views.MessengerEmojiHandler;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Map;
import okhttp3.OkHttpClient;

public class MessengerTabletPanelApp extends BaseMessengerTabletPanelApp {
    public static final String TAG = LoggingUtil.tag(MessengerTabletPanelApp.class);
    @Nullable
    public MessengerAPIManager mApiManager;
    public final Application mApplication;
    @Nullable
    public ICanViewerMessageFetcher mCanViewerMessageFetcher;
    @Nullable
    public IContactFetcher mContactQuery;
    public final Context mContext;
    public MessengerEmojiHandler mEmojiHandler;
    @Nullable
    public AbstractC12271xB mFBUserFetchDisposable;
    public boolean mIsDeviceMounted = false;
    public boolean mIsForeground = true;
    public boolean mIsMessengerAuthenticated;
    public boolean mIsMessengerVrApiNetworkingStarted = false;
    public boolean mIsNetworkAvailable = false;
    public boolean mIsNetworkBlocked = true;
    public final AbstractC12271xB mNetworkAvailabilityDisposable;
    public final OkHttpClient mOkHttpClient;

    private synchronized void toggleMessengerVrApiNetworking() {
        MessengerVrApi internalApi;
        MessengerVrApi internalApi2;
        MessengerAPIManager aPIManager = getAPIManager();
        if (aPIManager != null) {
            if (!this.mIsForeground || this.mIsNetworkBlocked || !this.mIsNetworkAvailable || !this.mIsDeviceMounted) {
                if (this.mIsMessengerVrApiNetworkingStarted) {
                    this.mIsMessengerVrApiNetworkingStarted = false;
                    IMessengerAPI iMessengerAPI = aPIManager.mCurrentAPI;
                    if (!(iMessengerAPI == null || (internalApi = iMessengerAPI.getInternalApi()) == null)) {
                        internalApi.stopNetworking();
                    }
                }
            } else if (!this.mIsMessengerVrApiNetworkingStarted) {
                this.mIsMessengerVrApiNetworkingStarted = true;
                IMessengerAPI iMessengerAPI2 = aPIManager.mCurrentAPI;
                if (!(iMessengerAPI2 == null || (internalApi2 = iMessengerAPI2.getInternalApi()) == null)) {
                    internalApi2.startNetworking();
                }
            }
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onBackground() {
        this.mIsForeground = false;
        toggleMessengerVrApiNetworking();
        super.onBackground();
    }

    /* renamed from: com.oculus.panelapp.messenger.MessengerTabletPanelApp$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.panelapp.messenger.api.MessengerAPIType[] r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.MessengerTabletPanelApp.AnonymousClass1.$SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType = r2
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.FB_MSYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.TEST     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.MessengerTabletPanelApp.AnonymousClass1.<clinit>():void");
        }
    }

    private void initStepSetupClientInfra() {
        MessengerAPIType messengerAPIType;
        IContactFetcher fbContactFetcher;
        ICanViewerMessageFetcher fBCanViewerMessageFetcher;
        this.mApiManager = new MessengerAPIManager();
        boolean nativeGetTestDataEnabled = nativeGetTestDataEnabled();
        if (nativeGetTestDataEnabled) {
            messengerAPIType = MessengerAPIType.TEST;
        } else {
            messengerAPIType = MessengerAPIType.FB_MSYS;
        }
        updateAPIs(messengerAPIType);
        toggleMessengerVrApiNetworking();
        if (nativeGetTestDataEnabled) {
            fbContactFetcher = new TestContactFetcher();
        } else {
            fbContactFetcher = new FbContactFetcher(this.mOkHttpClient);
        }
        this.mContactQuery = fbContactFetcher;
        if (nativeGetTestDataEnabled) {
            fBCanViewerMessageFetcher = new TestCanViewerMessageFetcher();
        } else {
            fBCanViewerMessageFetcher = new FBCanViewerMessageFetcher(this.mOkHttpClient);
        }
        this.mCanViewerMessageFetcher = fBCanViewerMessageFetcher;
        showMainLayer();
        getLogger().logImpression(SurfaceType.FB_MESSENGER_INITIALIZED, null);
        this.mFBUserFetchDisposable = FBAccountManager.queryUser().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.$$Lambda$MessengerTabletPanelApp$LdEVkO7ZjokIy9PDS0ezpDTE4xc2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MessengerTabletPanelApp.this.lambda$initStepSetupClientInfra$2$MessengerTabletPanelApp((FBUser) obj);
            }
        }, $$Lambda$MessengerTabletPanelApp$TS_4pcdf6zE7GfnttouZZSTxAzs2.INSTANCE);
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        this.mNetworkAvailabilityDisposable.dispose();
        MessengerAPIManager messengerAPIManager = this.mApiManager;
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
        AbstractC12271xB r0 = this.mFBUserFetchDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        if (this.mEmojiHandler != null) {
            this.mEmojiHandler = null;
        }
        super.destroy();
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public MessengerAPIManager getAPIManager() {
        return this.mApiManager;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public ICanViewerMessageFetcher getCanViewerMessageFetcher() {
        return this.mCanViewerMessageFetcher;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public IContactFetcher getContactQuery() {
        return this.mContactQuery;
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public MessengerEmojiHandler getEmojiHandler() {
        return this.mEmojiHandler;
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp
    public TabletType getTelemetryTabletType() {
        return TabletType.FB_MESSENGER;
    }

    public /* synthetic */ void lambda$initStepSetupClientInfra$2$MessengerTabletPanelApp(FBUser fBUser) throws Exception {
        if (fBUser != null) {
            getLogger().mFacebookUserId = fBUser.mUserId;
        }
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void updateAPIs(MessengerAPIType messengerAPIType) {
        IMessengerAPI iMessengerAPI = this.mApiManager.mCurrentAPI;
        if (iMessengerAPI != null) {
            if (iMessengerAPI.getType() != messengerAPIType) {
                iMessengerAPI.destroy();
            } else {
                return;
            }
        }
        switch (messengerAPIType.ordinal()) {
            case 0:
                TestMessengerAPI testMessengerAPI = new TestMessengerAPI(this.mContext);
                testMessengerAPI.initialize();
                this.mApiManager.updateAPI(testMessengerAPI);
                return;
            case 1:
                FBMessengerApi fBMessengerApi = new FBMessengerApi(this.mApplication, this.mOkHttpClient);
                fBMessengerApi.initialize();
                this.mApiManager.updateAPI(fBMessengerApi);
                return;
            default:
                return;
        }
    }

    public MessengerTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        this.mOkHttpClient = builder.build();
        this.mApplication = application;
        this.mContext = context;
        this.mEmojiHandler = new MessengerEmojiHandler(context.getResources());
        getLogger().logImpression(SurfaceType.FB_MESSENGER_INITIALIZING, null);
        AbstractC136820a<Boolean> create = RxNetworkAvailabilityObservable.create(this.mContext);
        $$Lambda$MessengerTabletPanelApp$cNJOXgGiMTp0zQrqAw7cISJo2 r1 = $$Lambda$MessengerTabletPanelApp$cNJOXgGiMTp0zQrqAw7cISJo2.INSTANCE;
        AnonymousClass219.A01(r1, "predicate is null");
        this.mNetworkAvailabilityDisposable = new C13051yn(new C138920v(create, r1)).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.$$Lambda$MessengerTabletPanelApp$CSvP_DseHNW4iuqQBfQpboOpjiA2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MessengerTabletPanelApp.this.lambda$new$1$MessengerTabletPanelApp((Boolean) obj);
            }
        }, C137220e.A06);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(r4.mContext) != false) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initStepMessengerAuthCheck() {
        /*
            r4 = this;
            boolean r0 = r4.nativeGetTestDataEnabled()
            if (r0 != 0) goto L_0x000f
            android.content.Context r0 = r4.mContext
            boolean r1 = com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(r0)
            r0 = 0
            if (r1 == 0) goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            r4.mIsMessengerAuthenticated = r0
            com.oculus.common.sociallogger.SocialLogger r3 = r4.getLogger()
            com.oculus.common.sociallogger.ActionId r2 = com.oculus.common.sociallogger.ActionId.CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED
            com.oculus.common.sociallogger.ClickEventButtonId r1 = com.oculus.common.sociallogger.ClickEventButtonId.NO_BUTTON
            com.oculus.common.sociallogger.SurfaceType r0 = com.oculus.common.sociallogger.SurfaceType.FB_MESSENGER_INITIALIZING
            r3.logActionSuccess(r2, r1, r0)
            boolean r0 = r4.mIsMessengerAuthenticated
            if (r0 == 0) goto L_0x0027
            r4.initStepSetupClientInfra()
            return
        L_0x0027:
            com.oculus.vrshell.SystemUXRoute r1 = com.oculus.vrshell.SystemUXRoute.AUI_SOCIAL_REAUTH
            java.lang.String r0 = ""
            r4.actionNavigate(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.MessengerTabletPanelApp.initStepMessengerAuthCheck():void");
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void actionNavigateToProfile(long j) {
        IMessengerAPI iMessengerAPI = getAPIManager().mCurrentAPI;
        if (iMessengerAPI != null && iMessengerAPI.getCurrentThread() != null) {
            String userID = iMessengerAPI.getUserID();
            long threadKey = iMessengerAPI.getCurrentThread().getThreadKey();
            StringBuilder sb = new StringBuilder("/mailbox/");
            sb.append(userID);
            sb.append("/thread/");
            sb.append(threadKey);
            actionNavigate(SystemUXRoute.AUI_PROFILE, new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_FB_PROFILE_URI).appendPath(String.valueOf(j)).appendQueryParameter("returnRoute", SystemUXRoute.AUI_MESSENGER.path()).appendQueryParameter("returnUri", sb.toString()).appendQueryParameter("entrypoint", "aui_messenger_tablet").build().toString());
        }
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp, com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onDeviceMountedStateChange(boolean z) {
        super.onDeviceMountedStateChange(z);
        this.mIsDeviceMounted = z;
        toggleMessengerVrApiNetworking();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onForeground() {
        super.onForeground();
        boolean z = true;
        this.mIsForeground = true;
        if (!nativeGetTestDataEnabled() && !MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(this.mContext)) {
            z = false;
        }
        if (this.mIsMessengerAuthenticated) {
            if (z) {
                toggleMessengerVrApiNetworking();
            }
            this.mFrameCommandChannel.sendCommand("quitAndHide");
        } else {
            if (!z) {
                actionNavigate(SystemUXRoute.AUI_SOCIAL_REAUTH, "");
            }
            this.mFrameCommandChannel.sendCommand("quitAndHide");
        }
        this.mIsMessengerAuthenticated = z;
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp, com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onNetworkAvailabilityChange(boolean z) {
        super.onNetworkAvailabilityChange(z);
        this.mIsNetworkAvailable = z;
        toggleMessengerVrApiNetworking();
    }

    @Override // com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp, com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onNetworkBlockedStateChange(boolean z) {
        super.onNetworkBlockedStateChange(z);
        this.mIsNetworkBlocked = z;
        toggleMessengerVrApiNetworking();
    }

    public /* synthetic */ void lambda$new$1$MessengerTabletPanelApp(Boolean bool) throws Exception {
        initStepMessengerAuthCheck();
    }
}
