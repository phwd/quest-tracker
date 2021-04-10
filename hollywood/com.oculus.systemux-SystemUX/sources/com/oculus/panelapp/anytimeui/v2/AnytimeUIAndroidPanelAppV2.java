package com.oculus.panelapp.anytimeui.v2;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocauth.OVRAuthHelper;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.common.quickpromotion.QuickPromotionEventHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.panelapp.anytimeui.AnytimeUIAndroidPanelService;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.broadcasts.AnytimeUIBroadcastReceiver;
import com.oculus.panelapp.anytimeui.config.DynamicConfigSetting;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2Binding;
import com.oculus.panelapp.anytimeui.dialogs.AnytimeUIDialogConstants;
import com.oculus.panelapp.anytimeui.dialogs.Dialog;
import com.oculus.panelapp.anytimeui.dialogs.IPDAdjustDialog;
import com.oculus.panelapp.anytimeui.dialogs.VideoStatusListener;
import com.oculus.panelapp.anytimeui.toast.ToastController;
import com.oculus.panelapp.anytimeui.toast.policies.NotificationAlreadyToastedPolicy;
import com.oculus.panelapp.anytimeui.toast.view.ToastLayerView;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.tooltip.TooltipView;
import com.oculus.panelapp.anytimeui.utils.DeviceUtils;
import com.oculus.panelapp.anytimeui.utils.LaunchDownloadBroadcastReceiver;
import com.oculus.panelapp.anytimeui.v2.bar.BarView;
import com.oculus.panelapp.anytimeui.v2.bar.BarViewModel;
import com.oculus.panelapp.anytimeui.v2.bar.status.ControllerBatteryState;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryStateHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ContentHostView;
import com.oculus.panelapp.anytimeui.v2.tablet.common.HostView;
import com.oculus.panelapp.anytimeui.v2.tablet.common.NavHostView;
import com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.SideNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IOnNotificationsInitialized;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NativeNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationManagerSourceHandler;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.PersistedNotificationSourceHandler;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.MultiUserHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.panelapp.anytimeui.v2.util.MemoryManager;
import com.oculus.panelapp.anytimeui.v2.util.PauseStateResult;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureButtonUtil;
import com.oculus.panelapp.anytimeui.v2.util.ScreenCaptureUtil;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.systemux.SystemUXApplication;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;
import com.oculus.vrshell.panels.telemetry.SectionTrackerImpl;
import com.oculus.vrshell.panels.telemetry.TelemetryManager;
import com.oculus.vrshell.timer.TimeServiceElapsedRealtime;
import com.oculus.vrshell.util.PackageUtil;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class AnytimeUIAndroidPanelAppV2 extends AnytimeUIPanelAppBase implements OVRAuthHelper.OvrAuthTokenCallback, SocialPanelApp, ToastController.IToastDisplayer, ToastLayerView.IActionURIHandler, TooltipController.TooltipHost, QuickPromotionEventHandler {
    private static final int BAR_VIEW_HEIGHT = 88;
    private static final int BAR_VIEW_WIDTH = 576;
    private static final int BAR_VIEW_WIDTH_WITH_DEBUG_BAR = 704;
    private static final String DIALOG_LAYER = "dialog";
    private static final String ENVIRONMENT_ARG_MINIMAL_OVERLAY_VALUE = "minimal_overlay";
    private static final String ENVIRONMENT_ARG_SHELL_HOST_NAME = "_oc_shell_host";
    private static final String IPC_COMMAND_DISMISS_NOTIFICATION_TOAST = "dismissNotificationToast";
    private static final String IPC_COMMAND_GUARDIAN_CONFIG = "updateGuardianConfigValue";
    private static final String IPC_COMMAND_HIDE_TOOLTIP = "tooltipHide";
    private static final String IPC_COMMAND_SHOW_NOTIFICATION_TOAST = "showNotificationToast";
    private static final String IPC_COMMAND_SHOW_TOOLTIP = "tooltipShow";
    private static final String IPC_COMMAND_UPDATE_HAND_DIALOG = "updateHandDialog";
    private static final String IPC_COMMAND_VIDEO_STATUS = "videoStatus";
    private static final String LEFT_HAND_LAYER = "hand2";
    private static final String LOG_EVENT_AUI_PANELAPP_TTI = "oculus_vrshell_aui_panelapp_tti";
    private static final String LOG_FIELD_AUI_PANELAPP_TTI_MILLIS = "panelapp_tti_millis";
    private static final String LOG_FIELD_AUI_TABLET_CONTENT_VIEWS = "aui_tablet_content_views";
    private static final String LOG_FIELD_AUI_TABLET_VIEWS = "aui_tablet_views";
    private static final String LOG_FIELD_IS_AUIV2_ON = "is_aui_v2_on";
    private static final String LOG_FIELD_PANELAPP_ALIVE_INSTANCES = "aui_alive_instances";
    private static final String LOG_FIELD_PANELAPP_ALL_INSTANCES = "aui_all_instances";
    private static final String LOG_FIELD_PANELAPP_UPTIME_SECS = "aui_panelapp_uptime_secs";
    private static final String LOG_FIELD_SERVICE_UPTIME_SECS = "aui_service_uptime_secs";
    private static final String RIGHT_HAND_LAYER = "hand1";
    private static final int SCREEN_LAYER_DIALOG_HEIGHT = 1600;
    private static final int SCREEN_LAYER_DIALOG_WIDTH = 2880;
    private static final String TABLET_CONTENT_LAYER = "tabletContent";
    private static final int TABLET_CONTENT_WIDTH = 552;
    private static final String TABLET_HIDING_REASON_EXTERNAL_APP_LAUNCH = "external_app_launch";
    private static final String TABLET_LAYER = "tablet";
    public static final String TABLET_NAV_LAYER = "tabletNav";
    private static final int TABLET_NAV_WIDTH = 216;
    private static final String TAG = LoggingUtil.tag(AnytimeUIAndroidPanelAppV2.class);
    private static final String TOAST_LAYER = "toast";
    private static final String TOOLTIP_LAYER = "tooltip";
    private static int panelAppAliveInstanceCount;
    private static int panelAppAllInstanceCount;
    private AndroidSettingsViewModel mAndroidSettingsViewModel;
    private final SystemUXApplication mApplication;
    private BarView mBarView;
    private BarViewModel mBarViewModel;
    private boolean mBarViewShown;
    private Dialog mCurrentDialog;
    private Tablet mCurrentTablet;
    private DestinationUIViewModel mDestinationUIViewModel;
    private final AnytimeUIDialogConstants mDialogConstants;
    private EnterpriseProfileViewModel mEnterpriseProfileViewModel;
    private final GuardianModule mGuardianModule;
    private IPDAdjustDialog.IPDAdjustState mIPDAdjustSkipToState;
    private IPDAdjustDialog.IPDAdjustState mIPDAdjustState = IPDAdjustDialog.IPDAdjustState.START;
    private final boolean mIsDebugBarEnabled;
    private boolean mIsScreenLayerDialog;
    private LaunchDownloadBroadcastReceiver mLaunchDownloadBroadcastReceiver;
    private ManagedLauncherViewModel mManagedLauncherViewModel;
    private final MemoryManager mMemoryManager;
    private NotificationDatasetProxy mNotificationsProxy;
    private NotificationsViewModel mNotifsViewModel;
    private final long mPanelAppStartMillis;
    private ProfileViewModel mProfileViewModel;
    private QuickPromotionController mQuickPromotionController;
    private String mReturnComponent;
    private ScreenCaptureButtonUtil mScreenCaptureButtonUtil;
    private ScreenCaptureUtil mScreenCaptureUtil;
    private final AnytimeUIAndroidPanelService mService;
    private SharingViewModel mSharingViewModel;
    private SideNavUtil mSideNavUtil;
    private SocialViewModel mSocialViewModel;
    private StatusViewModel mStatusViewModel;
    private TooltipController mSystemTooltipController;
    private ContentHostView mTabletContentView;
    private TabletNavUtil mTabletNavUtil;
    private NavHostView mTabletNavView;
    private HostView mTabletView;
    private TelemetryManager mTelemetryManager;
    private final ToastController mToastController;
    private ToastLayerView mToastLayerView;
    private String mUserAccessToken;
    private BroadcastReceiver mWifiBroadcastReceiver;

    public static native long registerShouldShowOculusLinkButton(Runnable runnable);

    public static native boolean shouldShowOculusLinkButton();

    public static native void unregisterShouldShowOculusLinkButton(long j);

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public AndroidPanelApp getAndroidPanelApp() {
        return this;
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public boolean isSocialPlatformApp() {
        return false;
    }

    /* access modifiers changed from: protected */
    public native long nativeCreateInstance(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeGetMessengerTestDataEnabled();

    public AnytimeUIAndroidPanelAppV2(AnytimeUIAndroidPanelService anytimeUIAndroidPanelService, SystemUXApplication systemUXApplication, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(systemUXApplication, context, surface, map, bundle);
        float f;
        boolean z = false;
        this.mIsScreenLayerDialog = false;
        this.mReturnComponent = "";
        this.mGuardianModule = new GuardianModule();
        this.mPanelAppStartMillis = System.currentTimeMillis();
        panelAppAliveInstanceCount++;
        panelAppAllInstanceCount++;
        Log.i(TAG, "Creating AUI PanelApp, isInOverlay: " + isInOverlayMode() + ", instanceCount: " + panelAppAliveInstanceCount);
        this.mApplication = systemUXApplication;
        this.mService = anytimeUIAndroidPanelService;
        context.setTheme(getPanelThemeId());
        this.mReturnComponent = computeReturnComponent("");
        this.mDialogConstants = new AnytimeUIDialogConstants();
        this.mScreenCaptureUtil = new ScreenCaptureUtil(context, this.mReturnComponent);
        this.mLaunchDownloadBroadcastReceiver = new LaunchDownloadBroadcastReceiver(this);
        Context context2 = getContext();
        LaunchDownloadBroadcastReceiver launchDownloadBroadcastReceiver = this.mLaunchDownloadBroadcastReceiver;
        context2.registerReceiver(launchDownloadBroadcastReceiver, launchDownloadBroadcastReceiver.getIntentFilter());
        setupNotifications(this.mApplication);
        this.mApplication.getAuthHelper().registerAccessTokenListener(this);
        NotificationDataSetService.getInstance().connectToProvider(context);
        try {
            f = Float.parseFloat(getEnvironmentArg("_oc_shell_pixel_density"));
        } catch (Exception unused) {
            f = 13.0f;
        }
        this.mToastController = new ToastController(context, this, f);
        this.mToastController.addPassFailPolicy(new NotificationAlreadyToastedPolicy(NotificationDataSetService.getInstance(), isGKEnabled(Gatekeeper.NOTIFICATIONS_FEED_REFRESH)));
        HorizonContent.buildDeviceSynchronized(this.mApplication);
        this.mMemoryManager = new MemoryManager(this);
        this.mIsDebugBarEnabled = (isGKEnabled(Gatekeeper.TRUSTED_USER) || isDynamicConfigSettingEnabled(DynamicConfigSetting.ARVR_ENTERPRISE_INTERNAL_USER_HW)) ? true : z;
        AnytimeUIBroadcastReceiver.requestCaptureState(context);
        configureLayerSurfaceGeometryBorderRadiuses();
        acquireDestinationUIViewModel();
        acquireNotifsViewModel();
        acquireSharingViewModel();
        getStoreOptionalityExperiment();
        AndroidPanelLayer ensurePanelLayer = ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, getMainLayerSpec(), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AndroidPanelApp.MAIN_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return AnytimeUIAndroidPanelAppV2.this.createBarLayerView(context);
            }
        });
        if (isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR)) {
            attachResizeLayoutListener(ensurePanelLayer, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT);
        }
        ensurePanelLayer("tablet", getTabletLayerSpec(), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass2 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "tablet";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return AnytimeUIAndroidPanelAppV2.this.createTabletLayerView(context);
            }
        });
        ensurePanelLayer(TABLET_NAV_LAYER, getTabletNavLayerSpec(), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass3 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AnytimeUIAndroidPanelAppV2.TABLET_NAV_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return AnytimeUIAndroidPanelAppV2.this.createTabletNavLayerView(context);
            }
        });
        ensurePanelLayer(TABLET_CONTENT_LAYER, getTabletContentLayerSpec(), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass4 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AnytimeUIAndroidPanelAppV2.TABLET_CONTENT_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return AnytimeUIAndroidPanelAppV2.this.createTabletContentLayerView(context);
            }
        });
    }

    @Override // com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        this.mTabletView.destroy();
        this.mTabletContentView.destroy();
        this.mTabletNavView.destroy();
        this.mTabletNavUtil.destroy();
        SideNavUtil sideNavUtil = this.mSideNavUtil;
        if (sideNavUtil != null) {
            sideNavUtil.destroy();
        }
        releaseDestinationUIViewModel();
        releaseSharingViewModel();
        releaseNotifsViewModel();
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.destroy();
            this.mCurrentDialog = null;
        }
        this.mApplication.getAuthHelper().removeAccessTokenListener(this);
        this.mToastController.destroy();
        this.mBarView.destroy();
        getContext().unregisterReceiver(this.mLaunchDownloadBroadcastReceiver);
        if (this.mWifiBroadcastReceiver != null) {
            getContext().unregisterReceiver(this.mWifiBroadcastReceiver);
        }
        this.mScreenCaptureUtil.destroy();
        this.mMemoryManager.destroy();
        panelAppAliveInstanceCount--;
        Log.i(TAG, "Destroyed AUI PanelApp, isInOverlay: " + isInOverlayMode() + ", instanceCount: " + panelAppAliveInstanceCount);
        super.destroy();
    }

    private void configureLayerSurfaceGeometryBorderRadiuses() {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.octablet_border_radius);
        configureLayerSurfaceGeometryBorderRadius("tablet", AndroidPanelLayer.BorderRadiusType.All, dimensionPixelSize);
        configureLayerSurfaceGeometryBorderRadius(TABLET_NAV_LAYER, AndroidPanelLayer.BorderRadiusType.Left, dimensionPixelSize);
        configureLayerSurfaceGeometryBorderRadius(TABLET_CONTENT_LAYER, AndroidPanelLayer.BorderRadiusType.Right, dimensionPixelSize);
    }

    public SideNavUtil getSideNavUtil() {
        if (this.mSideNavUtil == null) {
            this.mSideNavUtil = new SideNavUtil(this, getContext().getResources());
        }
        return this.mSideNavUtil;
    }

    public TabletNavUtil getTabletNavUtil() {
        if (this.mTabletNavUtil == null) {
            this.mTabletNavUtil = new TabletNavUtil(this, getContext().getResources());
        }
        return this.mTabletNavUtil;
    }

    public GuardianModule getGuardianModule() {
        return this.mGuardianModule;
    }

    public ScreenCaptureButtonUtil getScreenCaptureButtonUtil() {
        if (this.mScreenCaptureButtonUtil == null) {
            this.mScreenCaptureButtonUtil = new ScreenCaptureButtonUtil(this.mScreenCaptureUtil);
        }
        return this.mScreenCaptureButtonUtil;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String[] getLayersToAutoEnable() {
        return new String[0];
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        char c;
        switch (str.hashCode()) {
            case -1987604259:
                if (str.equals(TABLET_NAV_LAYER)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1332085432:
                if (str.equals("dialog")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1140076541:
                if (str.equals(TOOLTIP_LAYER)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -881377690:
                if (str.equals("tablet")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 35667036:
                if (str.equals(AndroidPanelApp.MAIN_LAYER)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99044770:
                if (str.equals(RIGHT_HAND_LAYER)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 99044771:
                if (str.equals(LEFT_HAND_LAYER)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 110532135:
                if (str.equals(TOAST_LAYER)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 959360819:
                if (str.equals(TABLET_CONTENT_LAYER)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
                throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
            default:
                throw new IllegalArgumentException("Unknown layerName: " + str);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        char c;
        Log.d(TAG, "Inflating view for layer: " + str + ", viewIdentifier = " + i);
        switch (str.hashCode()) {
            case -1987604259:
                if (str.equals(TABLET_NAV_LAYER)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1332085432:
                if (str.equals("dialog")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1140076541:
                if (str.equals(TOOLTIP_LAYER)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -881377690:
                if (str.equals("tablet")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 35667036:
                if (str.equals(AndroidPanelApp.MAIN_LAYER)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99044770:
                if (str.equals(RIGHT_HAND_LAYER)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 99044771:
                if (str.equals(LEFT_HAND_LAYER)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 110532135:
                if (str.equals(TOAST_LAYER)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 959360819:
                if (str.equals(TABLET_CONTENT_LAYER)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
                throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", new Object[0]));
            default:
                throw new IllegalArgumentException("Unknown layerName: " + str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createBarLayerView(Context context) {
        if (this.mBarView == null) {
            AnytimeBarViewV2Binding inflate = AnytimeBarViewV2Binding.inflate(LayoutInflater.from(context));
            BarView barView = (BarView) inflate.getRoot();
            this.mBarView = barView;
            this.mBarViewShown = true;
            getSystemTooltipController().initializeTooltipsOnSubtree(this.mBarView, AndroidPanelApp.MAIN_LAYER, R.id.tooltip_text, R.id.tooltip_subtext, R.id.tooltip_data);
            barView.setBinding(inflate);
            barView.initialize(this, AndroidPanelApp.MAIN_LAYER);
            barView.onShow();
            return barView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer view!");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createTabletLayerView(Context context) {
        if (this.mTabletView == null) {
            this.mTabletView = (HostView) LayoutInflater.from(context).inflate(R.layout.anytime_tablet_host_view_v2, (ViewGroup) null);
            this.mTabletView.initialize(this, "tablet");
            attachDefaultKeyboardHandler(this.mTabletView);
            this.mMemoryManager.setTabletView(this.mTabletView);
            return this.mTabletView;
        }
        throw new UnsupportedOperationException("trying to recreate tablet layer!");
    }

    private int getPanelThemeId() {
        if (!isGKEnabled(Gatekeeper.SYSTEM_THEME_SETTING)) {
            return R.style.PanelAppTheme;
        }
        if ((getContext().getResources().getConfiguration().uiMode & 48) != 16) {
            return R.style.PanelAppTheme;
        }
        return R.style.PanelAppTheme_Light;
    }

    private AndroidPanelLayer.Spec getTabletLayerSpec() {
        return new AndroidPanelLayer.Spec("tablet", 768, 340, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createTabletNavLayerView(Context context) {
        if (this.mTabletNavView == null) {
            this.mTabletNavView = (NavHostView) LayoutInflater.from(context).inflate(R.layout.anytime_tablet_nav_host_view_v2, (ViewGroup) null);
            this.mTabletNavView.initialize(this);
            return this.mTabletNavView;
        }
        throw new UnsupportedOperationException("trying to recreate tablet nav layer!");
    }

    private AndroidPanelLayer.Spec getTabletNavLayerSpec() {
        return new AndroidPanelLayer.Spec(TABLET_NAV_LAYER, 216, 340, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createTabletContentLayerView(Context context) {
        if (this.mTabletContentView == null) {
            this.mTabletContentView = (ContentHostView) LayoutInflater.from(context).inflate(R.layout.anytime_tablet_content_host_view_v2, (ViewGroup) null);
            this.mTabletContentView.initialize(this, TABLET_CONTENT_LAYER);
            attachDefaultKeyboardHandler(this.mTabletContentView);
            this.mMemoryManager.setTabletContentView(this.mTabletContentView);
            return this.mTabletContentView;
        }
        throw new UnsupportedOperationException("trying to recreate tablet content layer!");
    }

    private AndroidPanelLayer.Spec getTabletContentLayerSpec() {
        return new AndroidPanelLayer.Spec(TABLET_CONTENT_LAYER, TABLET_CONTENT_WIDTH, 340, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId());
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onActionButton() {
        AndroidPanelLayer panelLayer = getPanelLayer("dialog");
        if (panelLayer == null || !panelLayer.isVisible()) {
            return false;
        }
        View contentView = panelLayer.getContentView();
        if (contentView instanceof Dialog) {
            return ((Dialog) contentView).onActionButton();
        }
        return false;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        AndroidPanelLayer panelLayer = getPanelLayer("dialog");
        if (panelLayer == null || !panelLayer.isVisible()) {
            return false;
        }
        View contentView = panelLayer.getContentView();
        if (contentView instanceof Dialog) {
            return ((Dialog) contentView).onBackButton();
        }
        return false;
    }

    public Tablet getDefaultTablet() {
        if (!getSystemUXConfig().isEnterpriseMode) {
            return Tablet.NONE;
        }
        if (getSystemUXConfig().isEnterpriseAdminModeEnabled) {
            return Tablet.PROFILE;
        }
        return getSystemUXConfig().isDefaultApplicationSet ? Tablet.PAUSE : Tablet.ANDROID_LIBRARY;
    }

    public String getDefaultUri() {
        if (!getSystemUXConfig().isEnterpriseMode) {
            return TabletDeepLinkingUriUtil.AUI_TABLET_NONE_URI;
        }
        if (getSystemUXConfig().isEnterpriseAdminModeEnabled) {
            return TabletDeepLinkingUriUtil.AUI_PROFILE_URI;
        }
        return getSystemUXConfig().isDefaultApplicationSet ? TabletDeepLinkingUriUtil.AUI_PAUSE_URI : TabletDeepLinkingUriUtil.AUI_LIBRARY_URI;
    }

    private boolean isShellEnvRunning(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if ("com.oculus.shellenv".equals(runningAppProcessInfo.processName)) {
                Log.d(TAG, "ShellEnv is running");
                return true;
            }
        }
        Log.d(TAG, "ShellEnv is not running");
        return false;
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp, com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase
    public String getReturnComponent() {
        return this.mReturnComponent;
    }

    private String computeReturnComponent(String str) {
        if (TextUtils.isEmpty(str)) {
            return isShellEnvRunning(getContext()) ? "com.oculus.shellenv" : "com.oculus.vrshell";
        }
        return str;
    }

    public void logNotificationEvent(String str, IBaseVRNotification iBaseVRNotification) {
        rawLogJsonEvent(str, NotificationsHelper.formLogEvent(iBaseVRNotification).toString());
    }

    private AndroidPanelLayer.Spec getDialogLayerSpec(int i) {
        int GetWidthForViewId = this.mDialogConstants.GetWidthForViewId(i);
        if (GetWidthForViewId <= 1000) {
            int GetHeightForViewId = this.mDialogConstants.GetHeightForViewId(i);
            if (GetHeightForViewId <= 1000) {
                AndroidPanelLayer.ResizeBehavior GetResizeBehaviorForViewId = this.mDialogConstants.GetResizeBehaviorForViewId(i);
                AndroidPanelLayer.HitTestingBehavior GetHitTestingBehaviorForViewId = this.mDialogConstants.GetHitTestingBehaviorForViewId(i);
                AndroidPanelLayer.Shape GetShapeForViewId = this.mDialogConstants.GetShapeForViewId(i);
                if (this.mIsScreenLayerDialog) {
                    GetWidthForViewId = SCREEN_LAYER_DIALOG_WIDTH;
                }
                if (this.mIsScreenLayerDialog) {
                    GetHeightForViewId = SCREEN_LAYER_DIALOG_HEIGHT;
                }
                if (this.mIsScreenLayerDialog) {
                    GetShapeForViewId = AndroidPanelLayer.Shape.Screen;
                }
                return new AndroidPanelLayer.Spec("dialog", GetWidthForViewId, GetHeightForViewId, GetResizeBehaviorForViewId, GetHitTestingBehaviorForViewId, GetShapeForViewId, getPanelThemeId());
            }
            throw new IllegalArgumentException("Height for " + i + " exceeds max of " + 1000);
        }
        throw new IllegalArgumentException("Width for " + i + " exceeds max of " + 1000);
    }

    private AndroidPanelLayer.Spec getMainLayerSpec() {
        int i = isDebugBarEnabled() ? BAR_VIEW_WIDTH_WITH_DEBUG_BAR : BAR_VIEW_WIDTH;
        if (this.mStoreOptionalityExperiment.isStorePinnedToBar()) {
            i += DensityUtils.dipToPixelsInt(getContext().getResources().getDimension(R.dimen.anytime_bar_nav_button_width_v2), getContext().getResources().getDisplayMetrics());
        }
        return new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, i, 88, isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR) ? AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT : AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId());
    }

    public IPDAdjustDialog.IPDAdjustState getIPDAdjustState() {
        return this.mIPDAdjustState;
    }

    public void setIPDAdjustState(IPDAdjustDialog.IPDAdjustState iPDAdjustState, boolean z) {
        this.mIPDAdjustState = iPDAdjustState;
        if (z) {
            switch (iPDAdjustState) {
                case HORIZONTAL_INTERACTIVE:
                case VERTICAL_INTERACTIVE:
                    setIsScreenLayerDialog(true);
                    return;
                case START:
                case DONE:
                case FIT_AND_HORIZONTAL_VIDEO:
                case VERTICAL_VIDEO:
                    setIsScreenLayerDialog(false);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown IPD adjust dialog state: " + iPDAdjustState.name());
            }
        }
    }

    public IPDAdjustDialog.IPDAdjustState getIPDAdjustSkipToState() {
        return this.mIPDAdjustSkipToState;
    }

    public void setIPDAdjustSkipToState(IPDAdjustDialog.IPDAdjustState iPDAdjustState) {
        this.mIPDAdjustSkipToState = iPDAdjustState;
    }

    private void setIsScreenLayerDialog(boolean z) {
        this.mIsScreenLayerDialog = z;
        ensurePanelLayer("dialog", getDialogLayerSpec(R.layout.ipd_adjust_dialog), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass5 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "ipd_adjust_dialog";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.ipd_adjust_dialog, (ViewGroup) null);
                if (inflate instanceof Dialog) {
                    Dialog dialog = (Dialog) inflate;
                    dialog.initialize(AnytimeUIAndroidPanelAppV2.this);
                    return dialog;
                }
                throw new RuntimeException("Dialog must be used for the IPD adjust dialog.");
            }
        });
    }

    public Map<String, String> getPerfLoggingExtraFields() {
        HashMap hashMap = new HashMap();
        hashMap.put(LOG_FIELD_PANELAPP_UPTIME_SECS, String.valueOf(Long.valueOf((System.currentTimeMillis() - this.mPanelAppStartMillis) / 1000)));
        hashMap.put(LOG_FIELD_SERVICE_UPTIME_SECS, String.valueOf(this.mService.getServiceUptimeSecs()));
        hashMap.put(LOG_FIELD_PANELAPP_ALIVE_INSTANCES, String.valueOf(panelAppAliveInstanceCount));
        hashMap.put(LOG_FIELD_PANELAPP_ALL_INSTANCES, String.valueOf(panelAppAllInstanceCount));
        hashMap.put(LOG_FIELD_IS_AUIV2_ON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT);
        hashMap.put(LOG_FIELD_AUI_TABLET_VIEWS, this.mTabletView.getInstantiatedViewsForLogging());
        hashMap.put(LOG_FIELD_AUI_TABLET_CONTENT_VIEWS, this.mTabletContentView.getInstantiatedViewsForLogging());
        return hashMap;
    }

    public boolean isCurrentTabletMessengerVrApp() {
        if (this.mCurrentTablet == null) {
            return false;
        }
        switch (this.mCurrentTablet) {
            case MESSENGER_LOADED:
            case MESSENGER_LOADING:
            case PARTIES_LOADED:
            case PARTIES_LOADING:
            case CHATS_LOADED:
            case CHATS_LOADING:
            case PEOPLE_LOADED:
            case PEOPLE_LOADING:
            case PEOPLE_FB_LOADED:
            case PEOPLE_FB_LOADING:
            case SOCIAL_SETTINGS_LOADED:
            case SOCIAL_SETTINGS_LOADING:
                return true;
            default:
                return false;
        }
    }

    public Tablet getCurrentTablet() {
        return this.mCurrentTablet;
    }

    public ScreenCaptureUtil getScreenCaptureUtil() {
        return this.mScreenCaptureUtil;
    }

    public boolean isInOverlayMode() {
        return ENVIRONMENT_ARG_MINIMAL_OVERLAY_VALUE.equals(getEnvironmentArg(ENVIRONMENT_ARG_SHELL_HOST_NAME));
    }

    public void maybeResetLibraryStateOnAppLaunchOrQuit(boolean z) {
        Context context = getContext();
        if (LibraryStateHelper.loadLastFilterState(context) != LibraryFilter.UNKNOWN_SOURCES) {
            if (isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
                LibraryStateHelper.saveDefaultDropdownsState(context);
                if (this.mLibraryViewModel != null) {
                    this.mLibraryViewModel.resetDropdowns();
                }
            }
            LibraryStateHelper.saveDefaultScrollPositionState(context);
            if (z && this.mLibraryViewModel != null) {
                this.mLibraryViewModel.setShouldSkipSavingScrollPositionOnDestroy(true);
            }
            new LibraryLogger(context, null).logLibraryStateReset(LibraryLogger.STATE_RESET_REASON_IMMERSIVE_APP_LAUNCH_OR_QUIT);
        }
    }

    public boolean isMemoryConstrained() {
        return isInOverlayMode() || this.mDestinationUIViewModel.getIsPaused();
    }

    public boolean isDynamicConfigSettingEnabled(String str) {
        return SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(getEnvironmentArg("_oc_dynamic_config:" + str));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    public void actionSetRoute(SystemUXRoute systemUXRoute) {
        String str = TAG;
        Log.i(str, "actionSetRoute - Route:  " + systemUXRoute);
        getCommandChannel().sendCommand(String.format("navigationRoute %s", systemUXRoute.path()));
    }

    public void actionReorient() {
        Log.i(TAG, "actionReorient BedMode ");
        getCommandChannel().sendCommand("resetShellCenter");
    }

    public void actionSetVolumeLayerEnabled(boolean z) {
        String str = TAG;
        Log.i(str, "actionSetVolumeLayerEnabled - " + z);
        FrameCommandChannel commandChannel = getCommandChannel();
        StringBuilder sb = new StringBuilder();
        sb.append("volumeLayerEnabled ");
        sb.append(z ? "1" : "0");
        commandChannel.sendCommand(sb.toString());
    }

    public void actionSetControllerVisibility(boolean z) {
        String str = TAG;
        Log.i(str, "actionSetControllerVisibility - " + z);
        if (z) {
            getCommandChannel().sendCommand("showController");
        } else {
            getCommandChannel().sendCommand("hideController");
        }
    }

    public void actionQuitAndHide() {
        Log.i(TAG, "actionQuitAndHide");
        getCommandChannel().quit();
    }

    public void actionDialogResult(String str) {
        String str2 = TAG;
        Log.i(str2, "actionHideDialog - " + str);
        FrameCommandChannel commandChannel = getCommandChannel();
        commandChannel.sendCommand("dialogResult " + str);
    }

    public void actionPauseStateResult(PauseStateResult pauseStateResult) {
        String str = TAG;
        Log.i(str, "actionPauseStateResult - " + pauseStateResult.getIPCString());
        FrameCommandChannel commandChannel = getCommandChannel();
        commandChannel.sendCommand("systemux pauseStateResult " + pauseStateResult.getIPCString());
    }

    public void actionPlayVideo(String str) {
        String str2 = TAG;
        Log.i(str2, "actionPlayVideo - " + str);
        FrameCommandChannel commandChannel = getCommandChannel();
        commandChannel.sendCommand("video new");
        commandChannel.sendCommand("video setParam videoUri " + str);
        commandChannel.sendCommand("video setParam targetLayer dialog");
        commandChannel.sendCommand("video setParam videoFormat 2D");
        commandChannel.sendCommand("video setParam videoLayout RECT");
        commandChannel.sendCommand("video setParam autoResume true");
        commandChannel.sendCommand("video start");
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp, com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase
    public boolean isGKEnabled(Gatekeeper gatekeeper) {
        if (!nativeGetMessengerTestDataEnabled() || (gatekeeper != Gatekeeper.AUI_V2_MESSENGER && gatekeeper != Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            return super.isGKEnabled(gatekeeper);
        }
        return true;
    }

    private boolean handleShowNotificationToast(Scanner scanner) {
        String next = scanner.next();
        Log.i(TAG, IPC_COMMAND_SHOW_NOTIFICATION_TOAST);
        IBaseVRNotification vrNotificiationFromNotifKey = getNotificationDatasetProxy().getVrNotificiationFromNotifKey(next);
        if (vrNotificiationFromNotifKey instanceof NativeNotification) {
            NativeNotification nativeNotification = (NativeNotification) vrNotificiationFromNotifKey;
            if (nativeNotification.isMessengerNotification() && (this.mCurrentTablet == Tablet.MESSENGER_LOADED || this.mCurrentTablet == Tablet.MESSENGER_LOADING)) {
                return true;
            }
            if (nativeNotification.isOculusChatsNotification() && (this.mCurrentTablet == Tablet.CHATS_LOADED || this.mCurrentTablet == Tablet.CHATS_LOADING)) {
                return true;
            }
        }
        this.mToastController.onReceiveNotificationToToast(next);
        return true;
    }

    private boolean handleDismissNotificationToast(Scanner scanner) {
        this.mToastController.onReceiveNotificationToDismiss(scanner.next());
        return true;
    }

    public boolean isAccessTokenValid() {
        return !TextUtils.isEmpty(this.mUserAccessToken);
    }

    public String getAccessToken() {
        return this.mUserAccessToken;
    }

    private void setupNotifications(SystemUXApplication systemUXApplication) {
        initializeNotificationManagerNotifications();
        if (DeviceUtils.isWifiConnected(getContext())) {
            Log.v(TAG, "Wifi is connected");
            if (isAccessTokenValid()) {
                Log.v(TAG, "Wifi connected and we have a token");
                initializePersistedNotifications();
                return;
            }
            Log.v(TAG, "Wifi connected but no token yet");
            this.mApplication.getAuthHelper().fetchAccessToken();
            return;
        }
        Log.v(TAG, "No wifi, setting up receiver and client notifs");
        initializeWifiBroadcastReceiver(systemUXApplication);
    }

    private void initializeWifiBroadcastReceiver(final SystemUXApplication systemUXApplication) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mWifiBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass6 */

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.v(AnytimeUIAndroidPanelAppV2.TAG, String.format("Wifi receiver received action", new Object[0]));
                if ("android.net.wifi.STATE_CHANGE".equals(action) && DeviceUtils.isWifiConnected(AnytimeUIAndroidPanelAppV2.this.getContext())) {
                    Log.v(AnytimeUIAndroidPanelAppV2.TAG, "Wifi is connected");
                    if (AnytimeUIAndroidPanelAppV2.this.isAccessTokenValid()) {
                        Log.v(AnytimeUIAndroidPanelAppV2.TAG, "Access token valid, initializing persisted notifs");
                        AnytimeUIAndroidPanelAppV2.this.initializePersistedNotifications();
                        return;
                    }
                    systemUXApplication.getAuthHelper().fetchAccessToken();
                }
            }
        };
        getContext().registerReceiver(this.mWifiBroadcastReceiver, intentFilter);
    }

    private void fetchQPsForAllSurfaces() {
        if (isGKEnabled(Gatekeeper.AUI_V2_QP_KILLSWITCH)) {
            ThreadExecutor.getInstance().execute(new Callable() {
                /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$AnytimeUIAndroidPanelAppV2$_FnXcgblKRKO0EvnjOgWYNQ3JwE */

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return AnytimeUIAndroidPanelAppV2.this.lambda$fetchQPsForAllSurfaces$3$AnytimeUIAndroidPanelAppV2();
                }
            });
        }
    }

    public /* synthetic */ Object lambda$fetchQPsForAllSurfaces$3$AnytimeUIAndroidPanelAppV2() throws Exception {
        Context context = getContext();
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS).build();
        this.mQuickPromotionController = new QuickPromotionController(context);
        this.mQuickPromotionController.initialize(QuickPromotionController.QPApplication.AUI, true);
        this.mQuickPromotionController.fetchForAllSurfaces(context, build, this.mUserAccessToken, QuickPromotionController.QPApplication.AUI, new QuickPromotionController.SurfaceFetchCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass7 */

            @Override // com.oculus.common.quickpromotion.QuickPromotionController.SurfaceFetchCallback
            public void onFetchComplete() {
            }
        });
        return null;
    }

    public QuickPromotionController getQuickPromotionController() {
        return this.mQuickPromotionController;
    }

    public NotificationDatasetProxy getNotificationDatasetProxy() {
        return this.mNotificationsProxy;
    }

    private synchronized void initializeNotificationManagerNotifications() {
        Log.v(TAG, "Initializing local notifs only");
        if (NotificationDatasetProxy.isInitialized()) {
            this.mNotificationsProxy = NotificationDatasetProxy.getInstance(getContext(), null, null);
            for (INotificationSourceHandler iNotificationSourceHandler : this.mNotificationsProxy.getHandlers()) {
                if (iNotificationSourceHandler instanceof NotificationManagerSourceHandler) {
                    onNotificationsInitialized();
                    return;
                }
            }
        } else {
            NotificationManagerSourceHandler notificationManagerSourceHandler = new NotificationManagerSourceHandler(getContext());
            this.mNotificationsProxy = NotificationDatasetProxy.getInstance(getContext(), new IOnNotificationsInitialized() {
                /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$U5YoZsZQw7s5A58t6uOYZ_FLrY */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IOnNotificationsInitialized
                public final void onNotificationsInitialized() {
                    AnytimeUIAndroidPanelAppV2.this.onNotificationsInitialized();
                }
            }, notificationManagerSourceHandler);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void initializePersistedNotifications() {
        Log.v(TAG, "Late initializing persisted notifs");
        if (!(this.mNotificationsProxy == null || !DeviceUtils.isWifiConnected(getContext()) || this.mUserAccessToken == null)) {
            for (INotificationSourceHandler iNotificationSourceHandler : this.mNotificationsProxy.getHandlers()) {
                if (iNotificationSourceHandler instanceof PersistedNotificationSourceHandler) {
                    onNotificationsInitialized();
                    return;
                }
            }
            this.mNotificationsProxy.setAllowBackgroundRefresh(isGKEnabled(Gatekeeper.NOTIFICATIONS_FEED_REFRESH));
            this.mNotificationsProxy.addHandler(new PersistedNotificationSourceHandler(this.mUserAccessToken, getContext()), new IOnNotificationsInitialized() {
                /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$U5YoZsZQw7s5A58t6uOYZ_FLrY */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IOnNotificationsInitialized
                public final void onNotificationsInitialized() {
                    AnytimeUIAndroidPanelAppV2.this.onNotificationsInitialized();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onNotificationsInitialized() {
        if (this.mBarView != null) {
            Log.v(TAG, "Setting notifs badge");
            this.mBarView.initializeNotificationsBadge();
        }
    }

    public TooltipController getSystemTooltipController() {
        if (this.mSystemTooltipController == null) {
            this.mSystemTooltipController = new TooltipController(this);
        }
        return this.mSystemTooltipController;
    }

    public TelemetryManager getTelemetryManager() {
        if (this.mTelemetryManager == null) {
            SectionTrackerImpl sectionTrackerImpl = new SectionTrackerImpl(new TimeServiceElapsedRealtime());
            this.mTelemetryManager = new TelemetryManager(this, sectionTrackerImpl);
            sectionTrackerImpl.setSectionListener(this.mTelemetryManager);
        }
        return this.mTelemetryManager;
    }

    public boolean isDebugBarEnabled() {
        return this.mIsDebugBarEnabled;
    }

    private ToastLayerView showToastLayer() {
        if (this.mToastLayerView == null) {
            this.mToastLayerView = (ToastLayerView) ensurePanelLayer(TOAST_LAYER, new AndroidPanelLayer.Spec(TOAST_LAYER, 420, 72, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId()), new AndroidPanelApp.ViewCreator() {
                /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass8 */

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public String name() {
                    return AnytimeUIAndroidPanelAppV2.TOAST_LAYER;
                }

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public View createView(Context context) {
                    return LayoutInflater.from(context).inflate(R.layout.toast, (ViewGroup) null);
                }
            }).getContentView();
            this.mToastLayerView.setActionURIHandler(this);
        }
        return this.mToastLayerView;
    }

    public void onAUIBarFirstDraw() {
        ThreadExecutor.getInstance().execute(new Callable(System.nanoTime() / 1000000) {
            /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$AnytimeUIAndroidPanelAppV2$hHqelDqEft1EwpYOJjC_dyiPBk */
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AnytimeUIAndroidPanelAppV2.this.lambda$onAUIBarFirstDraw$4$AnytimeUIAndroidPanelAppV2(this.f$1);
            }
        });
    }

    public /* synthetic */ Object lambda$onAUIBarFirstDraw$4$AnytimeUIAndroidPanelAppV2(long j) throws Exception {
        long parseLong = j - Long.parseLong(getEnvironmentArg("_oc_launch_timestamp"));
        HashMap hashMap = new HashMap();
        hashMap.put(LOG_FIELD_AUI_PANELAPP_TTI_MILLIS, String.valueOf(parseLong));
        hashMap.putAll(getPerfLoggingExtraFields());
        rawLogEvent(LOG_EVENT_AUI_PANELAPP_TTI, hashMap);
        return null;
    }

    public synchronized BarViewModel acquireBarViewModel() {
        if (this.mBarViewModel == null) {
            this.mBarViewModel = new BarViewModel(getContext(), this);
        }
        addViewModelReference(this.mBarViewModel);
        return this.mBarViewModel;
    }

    public synchronized void releaseBarViewModel() {
        if (removeViewModelReference(this.mBarViewModel)) {
            this.mBarViewModel.destroy();
            this.mBarViewModel = null;
        }
    }

    public synchronized StatusViewModel acquireStatusViewModel() {
        if (this.mStatusViewModel == null) {
            this.mStatusViewModel = new StatusViewModel(getContext());
        }
        addViewModelReference(this.mStatusViewModel);
        return this.mStatusViewModel;
    }

    public synchronized void releaseStatusViewModel() {
        if (removeViewModelReference(this.mStatusViewModel)) {
            this.mStatusViewModel.destroy();
            this.mStatusViewModel = null;
        }
    }

    public synchronized ManagedLauncherViewModel acquireManagedLauncherViewModel() {
        if (this.mManagedLauncherViewModel == null) {
            this.mManagedLauncherViewModel = new ManagedLauncherViewModel(getContext(), this);
        }
        addViewModelReference(this.mManagedLauncherViewModel);
        return this.mManagedLauncherViewModel;
    }

    public synchronized void releaseManagedLauncherViewModel() {
        if (removeViewModelReference(this.mManagedLauncherViewModel)) {
            this.mManagedLauncherViewModel.destroy();
            this.mManagedLauncherViewModel = null;
        }
    }

    public synchronized SharingViewModel acquireSharingViewModel() {
        if (this.mSharingViewModel == null) {
            this.mSharingViewModel = new SharingViewModel(getContext(), this);
        }
        addViewModelReference(this.mSharingViewModel);
        return this.mSharingViewModel;
    }

    public synchronized void releaseSharingViewModel() {
        if (removeViewModelReference(this.mSharingViewModel)) {
            this.mSharingViewModel.destroy();
            this.mSharingViewModel = null;
        }
    }

    public synchronized ProfileViewModel acquireProfileViewModel() {
        if (this.mProfileViewModel == null) {
            this.mProfileViewModel = new ProfileViewModel(getContext(), this, getLocalUserId());
            addPanelFrameCallback(this.mProfileViewModel);
        }
        addViewModelReference(this.mProfileViewModel);
        return this.mProfileViewModel;
    }

    public synchronized void releaseProfileViewModel() {
        if (removeViewModelReference(this.mProfileViewModel)) {
            removePanelFrameCallback(this.mProfileViewModel);
            this.mProfileViewModel.destroy();
            this.mProfileViewModel = null;
        }
    }

    public synchronized AndroidSettingsViewModel acquireAndroidSettingsViewModel() {
        if (this.mAndroidSettingsViewModel == null) {
            this.mAndroidSettingsViewModel = new AndroidSettingsViewModel(getContext(), this);
        }
        addViewModelReference(this.mAndroidSettingsViewModel);
        return this.mAndroidSettingsViewModel;
    }

    public synchronized void releaseAndroidSettingsViewModel() {
        if (removeViewModelReference(this.mAndroidSettingsViewModel)) {
            this.mAndroidSettingsViewModel.destroy();
            this.mAndroidSettingsViewModel = null;
        }
    }

    public synchronized MultiUserHelper getMultiUserHelper() {
        return new MultiUserHelper(getContext());
    }

    public synchronized ProfilePictureHelper getProfilePictureHelper() {
        return new ProfilePictureHelper(getContext());
    }

    public synchronized EnterpriseProfileViewModel acquireEnterpriseProfileViewModel() {
        if (this.mEnterpriseProfileViewModel == null) {
            this.mEnterpriseProfileViewModel = new EnterpriseProfileViewModel(getContext(), this);
        }
        addViewModelReference(this.mEnterpriseProfileViewModel);
        return this.mEnterpriseProfileViewModel;
    }

    public synchronized void releaseEnterpriseProfileViewModel() {
        if (removeViewModelReference(this.mEnterpriseProfileViewModel)) {
            this.mEnterpriseProfileViewModel.destroy();
            this.mEnterpriseProfileViewModel = null;
        }
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public synchronized SocialViewModel acquireSocialViewModel() {
        if (this.mSocialViewModel == null) {
            this.mSocialViewModel = new SocialViewModel(getContext(), this);
            addPanelFrameCallback(this.mSocialViewModel);
        }
        addViewModelReference(this.mSocialViewModel);
        return this.mSocialViewModel;
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public synchronized void releaseSocialViewModel() {
        if (removeViewModelReference(this.mSocialViewModel)) {
            removePanelFrameCallback(this.mSocialViewModel);
            this.mSocialViewModel.destroy();
            this.mSocialViewModel = null;
        }
    }

    public synchronized DestinationUIViewModel acquireDestinationUIViewModel() {
        if (this.mDestinationUIViewModel == null) {
            this.mDestinationUIViewModel = new DestinationUIViewModel(getContext(), this);
        }
        addViewModelReference(this.mDestinationUIViewModel);
        return this.mDestinationUIViewModel;
    }

    public synchronized void releaseDestinationUIViewModel() {
        if (removeViewModelReference(this.mDestinationUIViewModel)) {
            this.mDestinationUIViewModel.destroy();
            this.mDestinationUIViewModel = null;
        }
    }

    public synchronized NotificationsViewModel acquireNotifsViewModel() {
        if (this.mNotifsViewModel == null) {
            this.mNotifsViewModel = new NotificationsViewModel(this);
        }
        addViewModelReference(this.mNotifsViewModel);
        return this.mNotifsViewModel;
    }

    public synchronized void releaseNotifsViewModel() {
        if (removeViewModelReference(this.mNotifsViewModel)) {
            this.mNotifsViewModel.destroy();
            this.mNotifsViewModel = null;
        }
    }

    public OVRAuthHelper getOVRAuthHelper() {
        return this.mApplication.getAuthHelper();
    }

    @Override // com.oculus.common.ocauth.OVRAuthHelper.OvrAuthTokenCallback
    public void onAccessTokenReceived(String str) {
        this.mUserAccessToken = str;
        if (DeviceUtils.isWifiConnected(getContext())) {
            initializePersistedNotifications();
            fetchQPsForAllSurfaces();
        }
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public String getLocalUserId() {
        return (String) this.mEnvironmentArgs.getOrDefault("_oc_userid", "");
    }

    @Override // com.oculus.panelapp.social.SocialPanelApp
    public void logSocialPartyEvent(SocialPartyEvent socialPartyEvent, String... strArr) {
        String[] strArr2 = new String[(strArr.length + 2)];
        strArr2[0] = "name";
        strArr2[1] = socialPartyEvent.toString();
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i + 2] = strArr[i];
        }
        rawLogEvent(SocialPartyEvent.ROOT_EVENT.toString(), strArr2);
    }

    @Override // com.oculus.panelapp.anytimeui.toast.ToastController.IToastDisplayer
    public void showNotification(StatusBarNotification statusBarNotification) {
        showToastLayer().showNotification(statusBarNotification, this.mBarView);
    }

    @Override // com.oculus.panelapp.anytimeui.toast.ToastController.IToastDisplayer
    public void hideNotification() {
        destroyLayer(TOAST_LAYER);
        this.mToastLayerView = null;
    }

    @Override // com.oculus.panelapp.anytimeui.toast.ToastController.IToastDisplayer
    public void hideNotificationIfDisplaying(String str) {
        ToastLayerView toastLayerView = this.mToastLayerView;
        if (toastLayerView != null && toastLayerView.hideNotificationIfDisplaying(str)) {
            hideNotification();
        }
    }

    @Override // com.oculus.panelapp.anytimeui.toast.view.ToastLayerView.IActionURIHandler
    public void navigateToToastURI(String str) {
        String[] split = str.split("/\\?", 2);
        actionNavigate(split[0], split.length > 1 ? split[1] : "");
    }

    @Override // com.oculus.panelapp.anytimeui.tooltip.TooltipController.TooltipHost
    public void onShowTooltip(TooltipDefinition tooltipDefinition, TooltipUVCoordinates tooltipUVCoordinates) {
        this.mTooltipManager.showTooltip(tooltipDefinition, tooltipUVCoordinates);
    }

    @Override // com.oculus.panelapp.anytimeui.tooltip.TooltipController.TooltipHost
    public void onHideTooltip() {
        this.mTooltipManager.hideTooltip();
    }

    public void setBarVisibility(boolean z) {
        Log.i(TAG, String.format("setBarVisibility(%s)", Boolean.valueOf(z)));
        BarView barView = this.mBarView;
        if (barView != null) {
            if (z && !this.mBarViewShown) {
                barView.onShow();
                this.mBarViewShown = true;
            } else if (!z && this.mBarViewShown) {
                this.mBarView.onHide();
                this.mBarViewShown = false;
            }
        }
    }

    public void setTabletVisibility(boolean z) {
        Log.i(TAG, String.format("setTabletVisibility(%s)", Boolean.valueOf(z)));
        HostView hostView = this.mTabletView;
        if (hostView != null) {
            if (z) {
                hostView.onTabletLayerShown();
                this.mTabletContentView.onTabletLayerShown();
                return;
            }
            hostView.onTabletLayerHidden();
            this.mTabletContentView.onTabletLayerHidden();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onDeepLink(String str, String str2) {
        Log.d(TAG, String.format("Received deeplink; uri: %s.", str2));
        if (this.mBarView == null) {
            Log.e(TAG, "Unable to handle deeplink; bar view has not been initialized.");
        } else if (this.mTabletView == null) {
            Log.e(TAG, "Unable to handle deeplink; tablet view has not been initialized.");
        } else if (this.mTabletNavView == null) {
            Log.e(TAG, "Unable to handle deeplink; tablet nav view has not been initialized.");
        } else if (this.mTabletContentView == null) {
            Log.e(TAG, "Unable to handle deeplink; tablet content view has not been initialized.");
        } else if (str2 == null) {
            Log.e(TAG, "Unable to handle deeplink; uri is null.");
        } else {
            Tablet tabletForDeepLinkUri = Tablet.getTabletForDeepLinkUri(str2);
            if (tabletForDeepLinkUri == null) {
                Log.e(TAG, String.format("Unable to handle deeplink; invalid uri: %s.", str2));
            } else if (!tabletForDeepLinkUri.isInternal() || isDebugBarEnabled()) {
                if (tabletForDeepLinkUri == Tablet.NONE) {
                    Context context = getContext();
                    if (TABLET_HIDING_REASON_EXTERNAL_APP_LAUNCH.equals(TabletDeepLinkingUriUtil.getTabletHidingReason(str2))) {
                        Log.d(TAG, "Hiding tablet due to external app launch");
                        maybeResetLibraryStateOnAppLaunchOrQuit(true);
                    } else {
                        Log.d(TAG, "Hiding tablet due to unknown reason");
                        if (isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET) && LibraryStateHelper.loadLastFilterState(context) != LibraryFilter.UNKNOWN_SOURCES) {
                            LibraryStateHelper.saveDefaultScrollPositionState(context);
                            if (this.mLibraryViewModel != null) {
                                this.mLibraryViewModel.resetScrollPosition();
                            }
                            new LibraryLogger(context, null).logLibraryStateReset(LibraryLogger.STATE_RESET_REASON_AUI_OFF_FOCUS);
                        }
                    }
                }
                this.mTabletView.showTablet(tabletForDeepLinkUri, str2);
                this.mTabletNavView.showTablet(tabletForDeepLinkUri, str2);
                this.mTabletContentView.showTablet(tabletForDeepLinkUri, str2);
                this.mBarView.onTabletShown(tabletForDeepLinkUri);
                this.mCurrentTablet = tabletForDeepLinkUri;
                if (this.mOnboardingTutorial != null) {
                    this.mOnboardingTutorial.onTabletShown(tabletForDeepLinkUri);
                }
                if (isMemoryConstrained()) {
                    this.mMemoryManager.onTrimMemory(15);
                }
            } else {
                actionNavigate(SystemUXRoute.LIBRARY, "");
            }
        }
    }

    public void updateActiveComponent(String str, String str2) {
        String str3 = TAG;
        Log.i(str3, "updateActiveComponent - " + str + ", returnComponent = " + str2);
        this.mReturnComponent = computeReturnComponent(str2);
        this.mDestinationUIViewModel.setPausedApp(str2);
        this.mScreenCaptureUtil.setCurrentPackageForCapture(this.mReturnComponent);
        this.mScreenCaptureUtil.refreshAllObservers();
        if (TextUtils.isEmpty(str2) || PackageUtil.isShellApp(str2)) {
            SystemUXScreenshotUtil.clearAppScreenshot();
        }
    }

    public void updateGuardianStatus(boolean z, int i, boolean z2, boolean z3) {
        String str = TAG;
        Log.i(str, "updateGuardianStatus - " + z + " - " + i + " - " + z2 + " - " + z3);
        this.mBarView.updateGuardianStatus(z, i, z2, z3);
        if (this.mLibraryViewModel != null) {
            this.mLibraryViewModel.updateTracking();
        }
    }

    public void updateRealityTunerStatus(boolean z, int i) {
        this.mBarView.updateRealityTunerStatus(z, i);
        if (this.mLibraryViewModel != null) {
            this.mLibraryViewModel.updateTracking();
        }
    }

    public void configureDialog(String str, final String str2, @Nullable String str3) {
        String str4 = TAG;
        Log.i(str4, "configureDialog:  " + str + " [" + str2 + "] (" + str3 + ")");
        SystemUXRoute fromPath = SystemUXRoute.fromPath(str);
        actionSetRoute(fromPath);
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.destroy();
            this.mCurrentDialog = null;
        }
        if (fromPath == SystemUXRoute.NONE) {
            destroyLayer("dialog");
            return;
        }
        final int GetViewIdForRoute = this.mDialogConstants.GetViewIdForRoute(fromPath);
        attachResizeLayoutListener(ensurePanelLayer("dialog", getDialogLayerSpec(GetViewIdForRoute), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass9 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "dialog";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                View inflate = LayoutInflater.from(context).inflate(GetViewIdForRoute, (ViewGroup) null);
                if (inflate instanceof Dialog) {
                    Dialog dialog = (Dialog) inflate;
                    dialog.initialize(AnytimeUIAndroidPanelAppV2.this);
                    dialog.configure(str2);
                    return dialog;
                }
                throw new RuntimeException("Dialog must be used for the dialog layer.");
            }
        }), this.mDialogConstants.GetResizeBehaviorForViewId(GetViewIdForRoute));
        this.mCurrentDialog = (Dialog) getPanelLayer("dialog").getContentView();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public boolean handleGenericMessage(String str) {
        char c;
        boolean z;
        Log.i(TAG, "handleGenericMessage " + str);
        Scanner scanner = new Scanner(str);
        try {
            String next = scanner.next();
            switch (next.hashCode()) {
                case -610593153:
                    if (next.equals(IPC_COMMAND_SHOW_NOTIFICATION_TOAST)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -437270464:
                    if (next.equals(IPC_COMMAND_UPDATE_HAND_DIALOG)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -384849262:
                    if (next.equals(IPC_COMMAND_DISMISS_NOTIFICATION_TOAST)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 837838821:
                    if (next.equals(IPC_COMMAND_HIDE_TOOLTIP)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 838165920:
                    if (next.equals(IPC_COMMAND_SHOW_TOOLTIP)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1042434125:
                    if (next.equals(IPC_COMMAND_VIDEO_STATUS)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1791668597:
                    if (next.equals(IPC_COMMAND_GUARDIAN_CONFIG)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    z = handleShowTooltip(scanner);
                    break;
                case 1:
                    z = handleHideTooltip(scanner);
                    break;
                case 2:
                    z = handleUpdateHandDialog(scanner);
                    break;
                case 3:
                    z = handleShowNotificationToast(scanner);
                    break;
                case 4:
                    z = handleDismissNotificationToast(scanner);
                    break;
                case 5:
                    z = handleVideoStatus(scanner);
                    break;
                case 6:
                    z = handleGuardianConfigStatus(scanner);
                    break;
                default:
                    z = super.handleGenericMessage(str);
                    break;
            }
            return z;
        } catch (InputMismatchException e) {
            Log.e(TAG, "Received Invalid response: " + e.getMessage());
            return false;
        }
    }

    public void updateCaptureAllowed(boolean z, boolean z2) {
        String str = TAG;
        Log.i(str, "updateCaptureAllowed - " + z + " capturing - " + z2);
        this.mScreenCaptureUtil.setCaptureAllowed(z);
    }

    public void updateLiveStreamStatus(boolean z, boolean z2) {
        String str = TAG;
        Log.i(str, "updateLiveStreamStatus - isLiveStreaming=" + z + ", allowed=" + z2);
        this.mScreenCaptureUtil.setLiveStreamStatus(z, z2);
    }

    public void updateControllerBattery(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "updateControllerBattery - " + str + ", " + i + " percent");
        this.mBarView.updateControllerBattery(new ControllerBatteryState(str, i));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleUpdateHandDialog(java.util.Scanner r13) {
        /*
        // Method dump skipped, instructions count: 133
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.handleUpdateHandDialog(java.util.Scanner):boolean");
    }

    private boolean handleShowTooltip(Scanner scanner) {
        final String nextLine = scanner.nextLine();
        Log.i(TAG, String.format("showTooltip (configuring Android tooltip view) - %s", nextLine));
        ensurePanelLayer(TOOLTIP_LAYER, new AndroidPanelLayer.Spec(TOOLTIP_LAYER, 0, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId()), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2.AnonymousClass11 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AnytimeUIAndroidPanelAppV2.TOOLTIP_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.tooltip_view, (ViewGroup) null);
                if (inflate instanceof TooltipView) {
                    TooltipView tooltipView = (TooltipView) inflate;
                    tooltipView.initialize(AnytimeUIAndroidPanelAppV2.this);
                    tooltipView.configureTooltip(nextLine);
                    return tooltipView;
                }
                throw new RuntimeException("TooltipView must be used for the tooltip layer.");
            }
        });
        return true;
    }

    private boolean handleHideTooltip(Scanner scanner) {
        Log.i(TAG, "hideTooltip (configuring Android tooltip view)");
        try {
            destroyLayer(TOOLTIP_LAYER);
            return true;
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "Error trying to hide tooltip layer: " + e.getMessage());
            return true;
        }
    }

    private boolean handleVideoStatus(Scanner scanner) {
        String next = scanner.next();
        Dialog dialog = this.mCurrentDialog;
        if (!(dialog instanceof VideoStatusListener)) {
            return true;
        }
        ((VideoStatusListener) dialog).onVideoStatus(VideoStatusListener.VideoState.getFromString(next));
        return true;
    }

    private boolean handleGuardianConfigStatus(Scanner scanner) {
        this.mGuardianModule.updateGuardianSettings(Integer.parseInt(scanner.next()), Float.parseFloat(scanner.next()));
        return true;
    }
}
