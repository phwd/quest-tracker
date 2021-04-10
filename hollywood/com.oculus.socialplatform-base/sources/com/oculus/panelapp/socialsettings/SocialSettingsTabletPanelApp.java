package com.oculus.panelapp.socialsettings;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.OVRAuthHelper;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCTheme;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsTabletMainBinding;
import com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL;
import com.oculus.panelapp.socialsettings.views.SocialSettingsView;
import com.oculus.panelapp.socialsettings.views.SocialSettingsViewModel;
import com.oculus.panelapp.socialsettings.views.SocialSettingsViewType;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.util.ShellFeatureSets;
import java.util.Map;
import java.util.Objects;
import okhttp3.OkHttpClient;

public final class SocialSettingsTabletPanelApp extends AndroidPanelApp implements SocialTabletPanelApp, OCEventHandler, OVRAuthHelper.OvrAuthTokenCallback {
    public static String TAG = LoggingUtil.tag(SocialSettingsTabletPanelApp.class);
    @Nullable
    public OVRAuthHelper mAuthHelper;
    @Nullable
    public DialogManager mDialogManager;
    public FBLinkingFetcher mFBLinkingFetcher;
    @Nullable
    public ImageHandler mImageHandler;
    @Nullable
    public SocialLogger mSocialLogger;
    @Nullable
    public SocialSettingsGraphQL mSocialSettingsGraphQL;
    @Nullable
    public SocialSettingsView mSocialSettingsView;
    @Nullable
    public SocialSettingsViewModel mSocialSettingsViewModel;
    public final int mTabletHeight;
    public final int mTabletWidth;

    private native long nativeCreateInstance(long j, long j2);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mSocialSettingsView == null) {
            SocialSettingsTabletMainBinding inflate = SocialSettingsTabletMainBinding.inflate(LayoutInflater.from(context), null);
            SocialSettingsViewModel socialSettingsViewModel = new SocialSettingsViewModel();
            this.mSocialSettingsViewModel = socialSettingsViewModel;
            inflate.setViewModel(socialSettingsViewModel);
            SocialSettingsView socialSettingsView = (SocialSettingsView) inflate.mRoot;
            this.mSocialSettingsView = socialSettingsView;
            socialSettingsView.initialize(this, inflate);
            this.mSocialSettingsView.onShow("");
            this.mSocialSettingsView.setVisibility(0);
            return this.mSocialSettingsView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        throw new IllegalArgumentException("Unsupported layer.");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        SocialSettingsView socialSettingsView = this.mSocialSettingsView;
        if (socialSettingsView != null) {
            socialSettingsView.destroy();
        }
        this.mSocialLogger = null;
        OVRAuthHelper oVRAuthHelper = this.mAuthHelper;
        if (oVRAuthHelper != null) {
            oVRAuthHelper.removeAccessTokenListener(this);
        }
        this.mFBLinkingFetcher.destroy();
        super.destroy();
    }

    public DialogManager getDialogManager() {
        return (DialogManager) Objects.requireNonNull(this.mDialogManager, "mDialogManager should not be null.");
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public FBLinkingFetcher getFBLinkingFetcher() {
        return this.mFBLinkingFetcher;
    }

    public SocialSettingsGraphQL getGraphQLHelper() {
        return this.mSocialSettingsGraphQL;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public ImageHandler getImageHandler() {
        return (ImageHandler) Objects.requireNonNull(this.mImageHandler, "mImageHandler should not be null.");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new IllegalArgumentException("Unsupported layer.");
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthTokenCallback
    public void onAccessTokenReceived(String str) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        this.mSocialSettingsGraphQL = new SocialSettingsGraphQL(str, builder.build());
        ((SocialSettingsView) Objects.requireNonNull(this.mSocialSettingsView, "mSocialSettingsView should not be null.")).onGraphQLReady();
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
        ((DialogManager) Objects.requireNonNull(this.mDialogManager, "mDialogManager should not be null.")).handleSystemDialogResult(str);
    }

    public void showMainLayer() {
        int i;
        AnonymousClass1 r2 = new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "socialsettings_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return SocialSettingsTabletPanelApp.this.createMainLayerView(context);
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

    public void updateSocialSettingsViewType(SocialSettingsViewType socialSettingsViewType) {
        ((SocialSettingsViewModel) Objects.requireNonNull(this.mSocialSettingsViewModel, "mSocialSettingsViewModel should not be null.")).setSelectedSetting(socialSettingsViewType);
    }

    public SocialSettingsTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        boolean contains = new ShellFeatureSets(getEnvironmentArg(ShellFeatureSets.ENVIRONMENT_KEY_FEATURE_SETS)).mShellFeatureSets.contains(ShellFeatureSets.FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1);
        this.mTabletWidth = contains ? 640 : 768;
        this.mTabletHeight = contains ? 400 : 340;
        this.mSocialLogger = new SocialLogger(context, TabletType.SETTINGS);
        SocialLogger logger = getLogger();
        SurfaceType surfaceType = SurfaceType.SETTINGS_INITIALIZING;
        logger.logImpression(surfaceType, null);
        HorizonContent.buildDeviceSynchronized(application);
        this.mFBLinkingFetcher = new FBLinkingFetcher(context);
        boolean isMessengerAuthenticated = MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(context);
        getLogger().logActionSuccess(ActionId.CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED, ClickEventButtonId.NO_BUTTON, surfaceType);
        if (isMessengerAuthenticated) {
            FBAccountManager.initialize(application);
            OVRAuthHelper oVRAuthHelper = new OVRAuthHelper(application);
            this.mAuthHelper = oVRAuthHelper;
            this.mDialogManager = new DialogManager();
            oVRAuthHelper.registerAccessTokenListener(this);
            this.mAuthHelper.fetchAccessToken();
            configureLayerSurfaceGeometryBorderRadius("#main", AndroidPanelLayer.BorderRadiusType.All, this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius));
            this.mImageHandler = new ImageHandler(context);
            showMainLayer();
            getLogger().logImpression(SurfaceType.SETTINGS_INITIALIZED, null);
            return;
        }
        actionNavigate(SystemUXRoute.AUI_SOCIAL_REAUTH, "");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        String dialogIPC;
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        DialogManager dialogManager = this.mDialogManager;
        if (dialogManager != null && (dialogIPC = dialogManager.getDialogIPC()) != null) {
            this.mFrameCommandChannel.sendCommand(dialogIPC);
        }
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        clickEventButtonId.toString();
        ((SocialLogger) Objects.requireNonNull(this.mSocialLogger, "mSocialLogger should not be null.")).logButtonClick(clickEventButtonId, surfaceType);
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        actionNavigate(systemUXRoute.path(), str);
    }

    public void actionNavigate(String str, String str2) {
        this.mFrameCommandChannel.launch(str, str2);
    }
}
