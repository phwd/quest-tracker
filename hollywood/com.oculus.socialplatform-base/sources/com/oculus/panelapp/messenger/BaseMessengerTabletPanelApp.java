package com.oculus.panelapp.messenger;

import X.AnonymousClass006;
import X.AnonymousClass1uW;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.deviceconfig.DeviceConfigMC;
import com.oculus.common.devicelifecycle.DeviceLifecycleObserver;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.ocui.OCTheme;
import com.oculus.os.AnalyticsEvent;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding;
import com.oculus.panelapp.messenger.memory.MessengerMemoryManager;
import com.oculus.panelapp.messenger.util.EmojiReplacer;
import com.oculus.panelapp.messenger.views.MessengerView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panelservice.VerifierConstants;
import com.oculus.vrshell.util.ShellFeatureSets;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BaseMessengerTabletPanelApp extends AndroidPanelApp implements MessengerPanelApp, DeviceLifecycleObserver {
    public static String TAG = LoggingUtil.tag(BaseMessengerTabletPanelApp.class);
    public FBLinkingFetcher mFBLinkingFetcher;
    public ImageHandler mImageHandler;
    public boolean mIsNetworkAvailable;
    public MessengerMemoryManager mMessengerMemoryManager;
    @Nullable
    public MessengerView mMessengerView;
    public final AtomicReference<String> mPendingDeeplinkUri = new AtomicReference<>("");
    public SocialLogger mSocialLogger;
    public final int mTabletHeight;
    public final int mTabletWidth;

    private native long nativeCreateInstance(long j, long j2);

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public AndroidPanelApp getAndroidPanelApp() {
        return this;
    }

    public abstract TabletType getTelemetryTabletType();

    @VisibleForTesting
    public native boolean nativeGetMemoryManagerEnabled();

    public native boolean nativeGetTestDataEnabled();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mMessengerView == null) {
            AnytimeTabletMessengerViewV2Binding inflate = AnytimeTabletMessengerViewV2Binding.inflate(LayoutInflater.from(context), null);
            MessengerView messengerView = (MessengerView) inflate.mRoot;
            this.mMessengerView = messengerView;
            messengerView.initialize((MessengerPanelApp) this, (AnonymousClass1uW) inflate);
            this.mMessengerView.onShow(this.mPendingDeeplinkUri.getAndSet(""));
            this.mMessengerView.setVisibility(0);
            onNetworkAvailabilityChange(this.mIsNetworkAvailable);
            attachDefaultKeyboardHandler(this.mMessengerView);
            return this.mMessengerView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public boolean getDeviceConfig(DeviceConfigMC deviceConfigMC) {
        return DeviceConfigHelper.getBoolean(this.mContext, deviceConfigMC);
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public FBLinkingFetcher getFBLinkingFetcher() {
        return this.mFBLinkingFetcher;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public ImageHandler getImageHandler() {
        return this.mImageHandler;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    public /* synthetic */ void lambda$onNetworkAvailabilityChange$0$BaseMessengerTabletPanelApp(boolean z) {
        MessengerView messengerView = this.mMessengerView;
        if (messengerView != null) {
            messengerView.onInternetConnectionStatusChanged(z);
        }
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void logSectionEntry(SectionTrackerEvent sectionTrackerEvent) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_aui_section_entry");
        analyticsEvent.setExtra("section_id", sectionTrackerEvent.getTelemetrySectionId());
        this.mSocialLogger.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
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
        AtomicReference<String> atomicReference;
        if (str2 != null && str2.length() != 0) {
            MessengerView messengerView = this.mMessengerView;
            if (messengerView != null) {
                messengerView.onShow(str2);
                atomicReference = this.mPendingDeeplinkUri;
                str2 = "";
            } else {
                atomicReference = this.mPendingDeeplinkUri;
            }
            atomicReference.set(str2);
        }
    }

    @Override // com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onNetworkAvailabilityChange(boolean z) {
        this.mIsNetworkAvailable = z;
        UiThreadExecutor.getInstance().execute(new Runnable(z) {
            /* class com.oculus.panelapp.messenger.$$Lambda$BaseMessengerTabletPanelApp$D6GLWQDzPfZQNsBqrDkIeg_WdI2 */
            public final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                BaseMessengerTabletPanelApp.this.lambda$onNetworkAvailabilityChange$0$BaseMessengerTabletPanelApp(this.f$1);
            }
        });
    }

    public void showMainLayer() {
        int i;
        AnonymousClass1 r1 = new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "messenger_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return BaseMessengerTabletPanelApp.this.createMainLayerView(context);
            }
        };
        int i2 = this.mTabletWidth;
        int i3 = this.mTabletHeight;
        AndroidPanelLayer.ResizeBehavior resizeBehavior = AndroidPanelLayer.ResizeBehavior.NONE;
        AndroidPanelLayer.HitTestingBehavior hitTestingBehavior = AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE;
        AndroidPanelLayer.Shape shape = AndroidPanelLayer.Shape.Flat;
        if (getDeviceConfig(DeviceConfigSocialPlatformMC.OCUI_SYSTEM_THEME_SETTING)) {
            i = OCTheme.getThemeId(this.mContext);
        } else {
            i = OCTheme.DEFAULT_PANEL_THEME;
        }
        ensurePanelLayer("#main", new AndroidPanelLayer.Spec("#main", i2, i3, resizeBehavior, hitTestingBehavior, shape, i), r1);
    }

    public BaseMessengerTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        MessengerMemoryManager messengerMemoryManager;
        HorizonContent.buildDeviceSynchronized(application);
        FBAccountManager.initialize(application);
        FBAuthManager.initialize(application, "581956559359077", "3534234083363713");
        EmojiReplacer.initialize(context);
        boolean contains = new ShellFeatureSets(getEnvironmentArg(ShellFeatureSets.ENVIRONMENT_KEY_FEATURE_SETS)).mShellFeatureSets.contains(ShellFeatureSets.FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1);
        this.mTabletWidth = contains ? 640 : 768;
        this.mTabletHeight = contains ? 400 : 340;
        this.mSocialLogger = new SocialLogger(context, getTelemetryTabletType());
        this.mImageHandler = new ImageHandler(context);
        this.mFBLinkingFetcher = new FBLinkingFetcher(context);
        if (nativeGetMemoryManagerEnabled()) {
            messengerMemoryManager = new MessengerMemoryManager(this);
        } else {
            messengerMemoryManager = null;
        }
        this.mMessengerMemoryManager = messengerMemoryManager;
        maybeShowDevBuildToast(application, context);
        configureLayerSurfaceGeometryBorderRadius("#main", AndroidPanelLayer.BorderRadiusType.All, context.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius));
    }

    private void maybeShowDevBuildToast(Application application, Context context) {
        if (VerifierConstants.OCULUS_SOCIAL_PLATFORM_TABLET_PACKAGE_NAME.equals(application.getPackageName())) {
            NotificationSender.Builder builder = new NotificationSender.Builder("oculus_mobile_messenger_tablet_dev_build_warning", "Warning", "Using Messenger dev build.", R.drawable.ic_notif_alert);
            builder.mDisplayDuration = NotificationsDisplayDuration.LONG;
            builder.mIconImageType = NotificationConstants.LargeImageType.PROFILE;
            builder.send(context);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        if (str.hashCode() != 35667036 || !str.equals("#main")) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Unknown layerName: ", str));
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", str));
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        FBAuthManager.destroy();
        FBAccountManager.destroy();
        EmojiReplacer.destroy();
        MessengerView messengerView = this.mMessengerView;
        if (messengerView != null) {
            messengerView.destroy();
        }
        MessengerMemoryManager messengerMemoryManager = this.mMessengerMemoryManager;
        if (messengerMemoryManager != null) {
            messengerMemoryManager.destroy();
        }
        this.mImageHandler.destroy();
        this.mSocialLogger = null;
        this.mFBLinkingFetcher.destroy();
        super.destroy();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (str.hashCode() != 35667036 || !str.equals("#main")) {
            throw new IllegalArgumentException("Unsupported layer.");
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        clickEventButtonId.toString();
        this.mSocialLogger.logButtonClick(clickEventButtonId, surfaceType);
    }

    @Override // com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onDeviceMountedStateChange(boolean z) {
    }

    @Override // com.oculus.common.devicelifecycle.DeviceLifecycleObserver
    public void onNetworkBlockedStateChange(boolean z) {
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }

    @Override // com.oculus.panelapp.messenger.MessengerPanelApp
    public void actionNavigate(String str) {
        this.mFrameCommandChannel.launch(str, null);
    }
}
