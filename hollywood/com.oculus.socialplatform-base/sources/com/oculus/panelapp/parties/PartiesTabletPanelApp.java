package com.oculus.panelapp.parties;

import X.AnonymousClass006;
import X.AnonymousClass1uW;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCTheme;
import com.oculus.ocui.OCTooltip;
import com.oculus.panelapp.parties.databinding.PartiesTabletMainBinding;
import com.oculus.panelapp.parties.utils.PartyRequestHelper;
import com.oculus.panelapp.parties.views.PartiesView;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.panelapp.parties.views.PartyActionMenu;
import com.oculus.panelapp.parties.views.actions.PartyAction;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.util.ShellFeatureSets;
import java.util.List;
import java.util.Map;

public final class PartiesTabletPanelApp extends AndroidPanelApp implements OCEventHandler {
    public static String TAG = LoggingUtil.tag(PartiesTabletPanelApp.class);
    public final DialogManager mDialogManager = new DialogManager();
    public ImageHandler mImageHandler;
    public Context mLayoutContext;
    public PartiesView mPartiesView;
    public PartiesViewModel mPartiesViewModel;
    public final PartyRequestHelper mRequestHelper;
    public SocialLogger mSocialLogger;
    public final int mTabletHeight;
    public final int mTabletWidth;

    private native long nativeCreateInstance(long j, long j2);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mPartiesView == null) {
            PartiesTabletMainBinding inflate = PartiesTabletMainBinding.inflate(LayoutInflater.from(context), null);
            PartiesView partiesView = (PartiesView) inflate.mRoot;
            this.mPartiesView = partiesView;
            partiesView.initialize(this, (AnonymousClass1uW) inflate);
            this.mPartiesView.onShow("");
            this.mPartiesView.setVisibility(0);
            return this.mPartiesView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    public PartyActionMenu createActionMenu(List<PartyAction> list) {
        return new PartyActionMenu(this.mLayoutContext, this, list);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    public OCTooltip createTooltip() {
        return new OCTooltip(this.mLayoutContext);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        PartiesView partiesView = this.mPartiesView;
        if (partiesView != null) {
            partiesView.destroy();
        }
        this.mPartiesViewModel.destroy();
        this.mPartiesViewModel = null;
        this.mImageHandler.destroy();
        this.mImageHandler = null;
        this.mSocialLogger = null;
        super.destroy();
    }

    public boolean getDeviceConfig(DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC) {
        return DeviceConfigHelper.getBoolean(this.mContext, deviceConfigSocialPlatformMC);
    }

    public void getDeviceConfigSynchronizedAsync(DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC, DeviceConfigHelper.GetDeviceBooleanCallback getDeviceBooleanCallback) {
        DeviceConfigHelper.getDeviceBooleanAsync(this.mContext, deviceConfigSocialPlatformMC, getDeviceBooleanCallback);
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    public ImageHandler getImageHandler() {
        return this.mImageHandler;
    }

    public String getLocalUserId() {
        return this.mEnvironmentArgs.getOrDefault("_oc_userid", "");
    }

    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    public PartiesViewModel getPartiesViewModel() {
        return this.mPartiesViewModel;
    }

    public PartyRequestHelper getRequestHelper() {
        return this.mRequestHelper;
    }

    public void launchApp(String str, String str2) {
        this.mFrameCommandChannel.launch(str, str2);
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
    public void onDeepLink(String str, String str2) {
        PartiesViewModel partiesViewModel = this.mPartiesViewModel;
        partiesViewModel.mSource = null;
        partiesViewModel.mCorrelationId = null;
        if (str2 != null && str2.length() != 0) {
            try {
                Uri parse = Uri.parse(str2);
                if (parse.getQueryParameterNames().contains("correlation_id")) {
                    this.mPartiesViewModel.mCorrelationId = parse.getQueryParameter("correlation_id");
                }
                if (parse.getQueryParameterNames().contains("source")) {
                    this.mPartiesViewModel.mSource = parse.getQueryParameter("source");
                }
            } catch (Exception e) {
                Log.e(TAG, AnonymousClass006.A07("onDeepLink: Failed to parse deeplink ", str2), e);
            }
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onSystemDialogResult(String str) {
        this.mDialogManager.handleSystemDialogResult(str);
    }

    public void showMainLayer() {
        int i;
        AnonymousClass1 r2 = new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.parties.PartiesTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "parties_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                PartiesTabletPanelApp partiesTabletPanelApp = PartiesTabletPanelApp.this;
                partiesTabletPanelApp.mLayoutContext = context;
                return partiesTabletPanelApp.createMainLayerView(context);
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

    public PartiesTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        HorizonContent.buildDeviceSynchronized(application);
        boolean contains = new ShellFeatureSets(getEnvironmentArg(ShellFeatureSets.ENVIRONMENT_KEY_FEATURE_SETS)).mShellFeatureSets.contains(ShellFeatureSets.FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1);
        this.mTabletWidth = contains ? 640 : 768;
        this.mTabletHeight = contains ? 400 : 340;
        this.mImageHandler = new ImageHandler(context);
        SocialLogger socialLogger = new SocialLogger(context, TabletType.PARTIES);
        this.mSocialLogger = socialLogger;
        this.mRequestHelper = new PartyRequestHelper(context);
        PartiesViewModel partiesViewModel = new PartiesViewModel(context, socialLogger, getLocalUserId());
        this.mPartiesViewModel = partiesViewModel;
        partiesViewModel.mIsPartyFooterEnabled = DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY);
        addPanelFrameCallback(this.mPartiesViewModel);
        showMainLayer();
        configureLayerSurfaceGeometryBorderRadius("#main", AndroidPanelLayer.BorderRadiusType.All, this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius));
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        if (str.hashCode() != 35667036 || !str.equals("#main")) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Unknown layerName: ", str));
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", new Object[0]));
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (dialogIPC != null) {
            this.mFrameCommandChannel.sendCommand(dialogIPC);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (str.hashCode() != 35667036 || !str.equals("#main")) {
            throw new IllegalArgumentException("Unsupported layer.");
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }

    public void actionNavigate(String str) {
        this.mFrameCommandChannel.launch(str, null);
    }
}
