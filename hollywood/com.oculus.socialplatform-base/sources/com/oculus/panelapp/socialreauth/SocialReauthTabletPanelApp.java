package com.oculus.panelapp.socialreauth;

import X.AbstractC12271xB;
import X.AnonymousClass006;
import X.C137220e;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fbauth.FBLinkingChecker;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCTheme;
import com.oculus.panelapp.socialreauth.databinding.SocialReauthTabletMainBinding;
import com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher;
import com.oculus.panelapp.socialreauth.views.SocialReauthNuxDialogManager;
import com.oculus.panelapp.socialreauth.views.SocialReauthView;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.util.ShellFeatureSets;
import java.util.Map;
import okhttp3.OkHttpClient;

public class SocialReauthTabletPanelApp extends AndroidPanelApp implements SocialTabletPanelApp, OCEventHandler {
    public static String TAG = LoggingUtil.tag(SocialReauthTabletPanelApp.class);
    public final UserAppScopedAuthHelper mAuthHelper;
    public final DialogManager mDialogManager;
    public FBLinkingChecker mFBLinkingChecker;
    public FBLinkingFetcher mFBLinkingFetcher;
    public ImageHandler mImageHandler;
    public boolean mInitialized;
    public boolean mIsFBLinked;
    public MessengerAcknowledgedFetcher mMessengerAcknowledgedFetcher;
    public SocialReauthNuxDialogManager mNuxDialogManager;
    public OkHttpClient mOkHttpClient;
    public SocialLogger mSocialLogger;
    public SocialReauthView mSocialReauthView;
    public final int mTabletHeight;
    public final int mTabletWidth;
    @Nullable
    public AbstractC12271xB mTokenDisposable;

    private native long nativeCreateInstance(long j, long j2);

    public /* synthetic */ void lambda$new$0$SocialReauthTabletPanelApp(Context context) {
        this.mIsFBLinked = true;
        if (MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(context)) {
            actionNavigate(SystemUXRoute.AUI_SOCIAL_V2, "");
            return;
        }
        this.mTokenDisposable = FBAuthManager.queryAccessToken().A05(C137220e.A04, C137220e.A06);
        showMainLayer();
        getLogger().logImpression(SurfaceType.REAUTH_INITIALIZED, null);
        handleNux();
        this.mInitialized = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mSocialReauthView == null) {
            SocialReauthTabletMainBinding inflate = SocialReauthTabletMainBinding.inflate(LayoutInflater.from(context), null);
            SocialReauthView socialReauthView = (SocialReauthView) inflate.mRoot;
            this.mSocialReauthView = socialReauthView;
            socialReauthView.initialize(this, inflate, DeviceConfigHelper.getBoolean(context, DeviceConfigSocialPlatformMC.MESSENGER_VR_REAUTH_REDUCE_NUX_FRICTION));
            this.mSocialReauthView.onShow("");
            this.mSocialReauthView.setVisibility(0);
            attachDefaultKeyboardHandler(this.mSocialReauthView);
            return this.mSocialReauthView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    @SuppressLint({"HardwareIds"})
    private void handleNux() {
        boolean z = DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.MESSENGER_VR_REAUTH_REDUCE_NUX_FRICTION);
        boolean hasCheckedHasAcknowledgedMessenger = MessengerVrAccountsContentProviderClient.hasCheckedHasAcknowledgedMessenger(this.mContext);
        Context context = this.mContext;
        if (!hasCheckedHasAcknowledgedMessenger) {
            MessengerVrAccountsContentProviderClient.setHasCheckedHasAcknowledgedMessenger(context, true);
            this.mMessengerAcknowledgedFetcher.query(Build.SERIAL, new MessengerAcknowledgedFetcher.IsMessengerAcknowledgedSuccessCallback(z) {
                /* class com.oculus.panelapp.socialreauth.$$Lambda$SocialReauthTabletPanelApp$5CCbvaiRGcxnvpngkyfEAiHnvk2 */
                public final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher.IsMessengerAcknowledgedSuccessCallback
                public final void onSuccess(boolean z) {
                    SocialReauthTabletPanelApp.this.lambda$handleNux$1$SocialReauthTabletPanelApp(this.f$1, z);
                }
            }, $$Lambda$SocialReauthTabletPanelApp$c9l0vYlxoUy220tMBfry8HagRhQ2.INSTANCE);
        } else if (!MessengerVrAccountsContentProviderClient.hasSeenNewUserAuthenticationDialog(context)) {
            maybeShowNuxDialog(false, z);
        }
    }

    private void maybeShowNuxDialog(boolean z, boolean z2) {
        if (!z2 || z) {
            getLogger().logImpression(SurfaceType.REAUTH_NUX_DIALOG, null);
            Resources resources = this.mContext.getResources();
            SocialReauthNuxDialogManager socialReauthNuxDialogManager = this.mNuxDialogManager;
            if (socialReauthNuxDialogManager == null) {
                socialReauthNuxDialogManager = new SocialReauthNuxDialogManager(getLogger(), resources, new SocialReauthNuxDialogManager.NavigateCallback() {
                    /* class com.oculus.panelapp.socialreauth.$$Lambda$W1pLQ_nNrpQgs6kx9I4lUzPgVb82 */

                    @Override // com.oculus.panelapp.socialreauth.views.SocialReauthNuxDialogManager.NavigateCallback
                    public final void navigate(SystemUXRoute systemUXRoute, String str) {
                        SocialReauthTabletPanelApp.this.actionNavigate(systemUXRoute, str);
                    }
                });
                this.mNuxDialogManager = socialReauthNuxDialogManager;
            }
            this.mDialogManager.showDialog(socialReauthNuxDialogManager.constructDialog(this.mContext, z, z2));
        }
    }

    private void maybeSkipReauth(boolean z) {
        if (z) {
            MessengerVrAccountsContentProviderClient.setIsMessengerAuthenticated(this.mContext, true);
            getLogger().logActionSuccess(ActionId.REAUTH_SKIP, ClickEventButtonId.NO_BUTTON, SurfaceType.REAUTH);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        throw new IllegalArgumentException(AnonymousClass006.A07("Unknown layerName: ", str));
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        SocialReauthView socialReauthView = this.mSocialReauthView;
        if (socialReauthView != null) {
            socialReauthView.destroy();
            this.mSocialReauthView = null;
        }
        this.mFBLinkingChecker.destroy();
        this.mFBLinkingChecker = null;
        this.mFBLinkingFetcher.destroy();
        this.mFBLinkingFetcher = null;
        FBAccountManager.destroy();
        FBAuthManager.destroy();
        this.mImageHandler.destroy();
        this.mImageHandler = null;
        AbstractC12271xB r0 = this.mTokenDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        this.mMessengerAcknowledgedFetcher.destroy();
        this.mMessengerAcknowledgedFetcher = null;
        this.mOkHttpClient.dispatcher.cancelAll();
        this.mOkHttpClient = null;
        UserAppScopedAuthHelper userAppScopedAuthHelper = this.mAuthHelper;
        if (userAppScopedAuthHelper != null) {
            userAppScopedAuthHelper.destroy();
        }
        this.mSocialLogger = null;
        SocialReauthNuxDialogManager socialReauthNuxDialogManager = this.mNuxDialogManager;
        if (socialReauthNuxDialogManager != null) {
            socialReauthNuxDialogManager.destroy();
        }
        super.destroy();
    }

    @VisibleForTesting
    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public FBLinkingFetcher getFBLinkingFetcher() {
        return this.mFBLinkingFetcher;
    }

    public OkHttpClient getHttpClient() {
        return this.mOkHttpClient;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public ImageHandler getImageHandler() {
        return this.mImageHandler;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new IllegalArgumentException("Unsupported layer.");
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    public /* synthetic */ void lambda$handleNux$1$SocialReauthTabletPanelApp(boolean z, boolean z2) {
        if (!MessengerVrAccountsContentProviderClient.hasSeenNewUserAuthenticationDialog(this.mContext)) {
            maybeSkipReauth(z2);
            maybeShowNuxDialog(z2, z);
        }
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onBackButtonClick() {
        this.mFrameCommandChannel.playAudio(SoundType.CLOSE);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonClick() {
        this.mFrameCommandChannel.playAudio(SoundType.SELECT);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonEnter() {
        this.mFrameCommandChannel.playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onSystemDialogResult(String str) {
        this.mDialogManager.handleSystemDialogResult(str);
    }

    public void showMainLayer() {
        int i;
        AnonymousClass1 r2 = new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.socialreauth.SocialReauthTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "socialreauth_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return SocialReauthTabletPanelApp.this.createMainLayerView(context);
            }
        };
        int i2 = this.mTabletWidth;
        int i3 = this.mTabletHeight;
        AndroidPanelLayer.ResizeBehavior resizeBehavior = AndroidPanelLayer.ResizeBehavior.NONE;
        AndroidPanelLayer.HitTestingBehavior hitTestingBehavior = AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE;
        AndroidPanelLayer.Shape shape = AndroidPanelLayer.Shape.Flat;
        if (DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.OCUI_SYSTEM_THEME_SETTING)) {
            i = OCTheme.getThemeId(this.mContext);
        } else {
            i = OCTheme.DEFAULT_PANEL_THEME;
        }
        ensurePanelLayer("#main", new AndroidPanelLayer.Spec("#main", i2, i3, resizeBehavior, hitTestingBehavior, shape, i), r2);
    }

    public SocialReauthTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        boolean contains = new ShellFeatureSets(getEnvironmentArg(ShellFeatureSets.ENVIRONMENT_KEY_FEATURE_SETS)).mShellFeatureSets.contains(ShellFeatureSets.FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1);
        this.mTabletWidth = contains ? 640 : 768;
        this.mTabletHeight = contains ? 400 : 340;
        this.mSocialLogger = new SocialLogger(context, TabletType.REAUTH);
        getLogger().logImpression(SurfaceType.REAUTH_INITIALIZING, null);
        this.mFrameCommandChannel.sendCommand("inhibitScreenCapture");
        HorizonContent.buildDeviceSynchronized(application);
        FBAccountManager.initialize(application);
        FBAuthManager.initialize(application, "581956559359077", "3534234083363713");
        configureLayerSurfaceGeometryBorderRadius("#main", AndroidPanelLayer.BorderRadiusType.All, this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius));
        this.mImageHandler = new ImageHandler(context);
        this.mDialogManager = new DialogManager();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        this.mOkHttpClient = builder.build();
        OkHttpClient okHttpClient = this.mOkHttpClient;
        UserAppScopedAuthHelper userAppScopedAuthHelper = new UserAppScopedAuthHelper(application, this.mEnvironmentArgs.getOrDefault("_oc_userid", ""), "1953748974690454", okHttpClient);
        this.mAuthHelper = userAppScopedAuthHelper;
        this.mMessengerAcknowledgedFetcher = new MessengerAcknowledgedFetcher(okHttpClient, userAppScopedAuthHelper);
        FBLinkingFetcher fBLinkingFetcher = new FBLinkingFetcher(context);
        this.mFBLinkingFetcher = fBLinkingFetcher;
        FBLinkingChecker fBLinkingChecker = new FBLinkingChecker(this, fBLinkingFetcher);
        this.mFBLinkingChecker = fBLinkingChecker;
        fBLinkingChecker.redirectToLinkFlowIfUnlinked(new FBLinkingChecker.OnVerifiedFBLinkedCallback(context) {
            /* class com.oculus.panelapp.socialreauth.$$Lambda$SocialReauthTabletPanelApp$f73d9OoKU9PGhrutJfSpp6WiFL02 */
            public final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.common.socialtablet.fbauth.FBLinkingChecker.OnVerifiedFBLinkedCallback
            public final void onVerifiedFBLinked() {
                SocialReauthTabletPanelApp.this.lambda$new$0$SocialReauthTabletPanelApp(this.f$1);
            }
        });
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (dialogIPC != null) {
            this.mFrameCommandChannel.sendCommand(dialogIPC);
        }
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        clickEventButtonId.toString();
        this.mSocialLogger.logButtonClick(clickEventButtonId, surfaceType);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onForeground() {
        super.onForeground();
        if (!this.mIsFBLinked || !this.mInitialized) {
            this.mFrameCommandChannel.sendCommand("quitAndHide");
        }
    }
}
