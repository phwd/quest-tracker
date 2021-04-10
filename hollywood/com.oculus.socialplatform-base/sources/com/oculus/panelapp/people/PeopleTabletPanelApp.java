package com.oculus.panelapp.people;

import X.AnonymousClass006;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCTheme;
import com.oculus.panelapp.people.databinding.PeopleTabletMainViewBinding;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher;
import com.oculus.panelapp.people.model.IViewerSocialParty;
import com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType;
import com.oculus.panelapp.people.views.PeopleQuickMessagePopup;
import com.oculus.panelapp.people.views.PeopleTabletMainView;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewType;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panelservice.VerifierConstants;
import com.oculus.vrshell.util.ShellFeatureSets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class PeopleTabletPanelApp extends AndroidPanelApp implements SocialTabletPanelApp, OCEventHandler {
    public static String TAG = LoggingUtil.tag(PeopleTabletPanelApp.class);
    public final DialogManager mDialogManager = new DialogManager();
    public FBLinkingFetcher mFBLinkingFetcher;
    public ImageHandler mImageHandler;
    @Nullable
    public PeopleTabletMainView mPeopleTabletMainView;
    @Nullable
    public PeopleQuickMessagePopup mQuickMessagePopup;
    public SocialLogger mSocialLogger;
    public final int mTabletHeight;
    public final int mTabletWidth;

    private native long nativeCreateInstance(long j, long j2);

    private native boolean nativeGetFbRequestsEnabled();

    public abstract ISearchFetcher acquireAllConnectionsFetcher();

    public abstract ISearchFetcher acquireAllPeopleNearbyFetcher();

    public abstract BaseFetcher acquireFriendRequestFetcher();

    public abstract BaseFetcher acquireFriendsFetcher();

    public abstract OCLinkedAccountsInfoFetcher acquireLinkedAccountsInfoFetcher();

    public abstract BaseFetcher acquirePYMKFetcher();

    public abstract OCPeopleNearbyFetcher acquirePeopleNearbyFetcher();

    public abstract ISearchFetcher acquireSearchFetcher();

    public abstract void actionNavigateToProfile(String str);

    public abstract void executeAction(PeopleUserAction peopleUserAction, Context context, PeopleUserActionHandler peopleUserActionHandler);

    public AndroidPanelApp getAndroidPanelApp() {
        return this;
    }

    public abstract IViewerSocialPartyFetcher getCurrentPartyFetcher();

    public abstract PeopleEmptyAdapterItemType getEmptyType(PeopleViewType peopleViewType);

    public abstract FBLinkedStatus getFBLinkedStatus();

    public abstract PeopleTabletType getPeopleTabletType();

    public abstract List<PeopleUserAction> getPossibleActions(PeopleUserAdapterItem peopleUserAdapterItem);

    public abstract List<PeopleUserAction> getPrimaryCTAAction(PeopleUserAdapterItem peopleUserAdapterItem);

    public abstract String getViewerID();

    public abstract void releaseAllConnectionsFetcher();

    public abstract void releaseAllPeopleNearbyFetcher();

    public abstract void releaseFriendRequestFetcher();

    public abstract void releaseFriendsFetcher();

    public abstract void releaseLinkedAccountsInfoFetcher();

    public abstract void releasePYMKFetcher();

    public abstract void releasePeopleNearbyFetcher();

    public abstract void releaseSearchFetcher();

    public abstract void sendMessage(String str, String str2, PeopleUserActionHandler peopleUserActionHandler);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mPeopleTabletMainView == null) {
            PeopleTabletMainViewBinding inflate = PeopleTabletMainViewBinding.inflate(LayoutInflater.from(context), null);
            PeopleTabletMainView peopleTabletMainView = (PeopleTabletMainView) inflate.mRoot;
            this.mPeopleTabletMainView = peopleTabletMainView;
            peopleTabletMainView.initialize(this, inflate);
            attachDefaultKeyboardHandler(this.mPeopleTabletMainView);
            return this.mPeopleTabletMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    public PeopleViewType getCurrentViewType() {
        return ((PeopleTabletMainView) Objects.requireNonNull(this.mPeopleTabletMainView, "mPeopleTabletMainView should not be null.")).mViewType;
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public FBLinkingFetcher getFBLinkingFetcher() {
        return this.mFBLinkingFetcher;
    }

    public boolean getFbSearchEnabled(Context context) {
        return DeviceConfigHelper.getBoolean(context, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_FB_SEARCH);
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public ImageHandler getImageHandler() {
        return this.mImageHandler;
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    public String getLoggingSurface() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPeopleTabletType().getLoggingTabletType());
        sb.append("_");
        sb.append(((PeopleTabletMainView) Objects.requireNonNull(this.mPeopleTabletMainView, "mPeopleTabletMainView should not be null.")).mViewType.name());
        return sb.toString();
    }

    public boolean getPeopleNearbyEnabled(Context context) {
        return DeviceConfigHelper.getBoolean(context, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_PEOPLE_NEARBY);
    }

    public PeopleViewType getPeopleViewCurrentViewType() {
        return ((PeopleTabletMainView) Objects.requireNonNull(this.mPeopleTabletMainView, "mPeopleTabletMainView should not be null.")).getPeopleViewCurrentViewType();
    }

    public PeopleQuickMessagePopup getQuickMessagePopup(Context context) {
        PeopleQuickMessagePopup peopleQuickMessagePopup = this.mQuickMessagePopup;
        if (peopleQuickMessagePopup != null) {
            return peopleQuickMessagePopup;
        }
        PeopleQuickMessagePopup peopleQuickMessagePopup2 = new PeopleQuickMessagePopup(context, this);
        this.mQuickMessagePopup = peopleQuickMessagePopup2;
        return peopleQuickMessagePopup2;
    }

    public boolean isGKEnabled(DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC) {
        return DeviceConfigHelper.getBoolean(this.mContext, deviceConfigSocialPlatformMC);
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
            /* class com.oculus.panelapp.people.PeopleTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "people_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return PeopleTabletPanelApp.this.createMainLayerView(context);
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

    public void updatePeopleViewType(PeopleViewType peopleViewType) {
        ((PeopleTabletMainView) Objects.requireNonNull(this.mPeopleTabletMainView, "mPeopleTabletMainView should not be null.")).onUpdatePeopleViewType(peopleViewType);
    }

    public PeopleTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        HorizonContent.buildDeviceSynchronized(application);
        boolean contains = new ShellFeatureSets(getEnvironmentArg(ShellFeatureSets.ENVIRONMENT_KEY_FEATURE_SETS)).mShellFeatureSets.contains(ShellFeatureSets.FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1);
        this.mTabletWidth = contains ? 640 : 768;
        this.mTabletHeight = contains ? 400 : 340;
        FBAccountManager.initialize(application);
        FBAuthManager.initialize(application, "581956559359077", "3534234083363713");
        this.mImageHandler = new ImageHandler(context);
        this.mFBLinkingFetcher = new FBLinkingFetcher(context);
        maybeShowDevBuildToast(application, context);
        configureLayerSurfaceGeometryBorderRadius("#main", AndroidPanelLayer.BorderRadiusType.All, context.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius));
        this.mSocialLogger = new SocialLogger(context, getPeopleTabletType().getLoggingTabletType());
    }

    private void maybeShowDevBuildToast(Application application, Context context) {
        if (VerifierConstants.OCULUS_SOCIAL_PLATFORM_TABLET_PACKAGE_NAME.equals(application.getPackageName())) {
            NotificationSender.Builder builder = new NotificationSender.Builder("oculus_mobile_people_tablet_dev_build_warning", "Warning", "Using People dev build.", R.drawable.ic_notif_alert);
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
        FBAccountManager.destroy();
        FBAuthManager.destroy();
        PeopleTabletMainView peopleTabletMainView = this.mPeopleTabletMainView;
        if (peopleTabletMainView != null) {
            peopleTabletMainView.destroy();
        }
        this.mImageHandler.destroy();
        this.mSocialLogger = null;
        this.mFBLinkingFetcher.destroy();
        PeopleQuickMessagePopup peopleQuickMessagePopup = this.mQuickMessagePopup;
        if (peopleQuickMessagePopup != null) {
            peopleQuickMessagePopup.dismiss();
            this.mQuickMessagePopup.destroy();
            this.mQuickMessagePopup = null;
        }
        super.destroy();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (dialogIPC != null) {
            this.mFrameCommandChannel.sendCommand(dialogIPC);
        }
    }

    public boolean getFbRequestsEnabled() {
        return nativeGetFbRequestsEnabled();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (str.hashCode() != 35667036 || !str.equals("#main")) {
            throw new IllegalArgumentException("Unsupported layer.");
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    public String getPartyPresenceOverride(Context context, PeopleUserAdapterItem peopleUserAdapterItem) {
        Resources resources;
        int i;
        IViewerSocialPartyFetcher currentPartyFetcher = getCurrentPartyFetcher();
        if (currentPartyFetcher != null) {
            IViewerSocialParty viewerSocialParty = currentPartyFetcher.getViewerSocialParty();
            if (viewerSocialParty != null) {
                if (viewerSocialParty.getUserPartyMembership(peopleUserAdapterItem.mUser) == SocialParty.PartyMembership.MEMBER) {
                    resources = context.getResources();
                    i = R.string.people_tablet_in_party_subtitle;
                } else if (viewerSocialParty.isViewerInvitedToParty(peopleUserAdapterItem.mUser.mCurrentPartyID)) {
                    resources = context.getResources();
                    i = R.string.people_tablet_waiting_for_you_party_subtitle;
                } else if (viewerSocialParty.getUserPartyMembership(peopleUserAdapterItem.mUser) == SocialParty.PartyMembership.INVITED) {
                    resources = context.getResources();
                    i = R.string.people_tablet_invited_to_party_subtitle;
                }
                return resources.getString(i);
            }
            if (peopleUserAdapterItem.mUser.isInJoinableParty()) {
                resources = context.getResources();
                i = R.string.people_tablet_joinable_party_subtitle;
                return resources.getString(i);
            }
        }
        return "";
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }

    public void actionNavigate(String str) {
        this.mFrameCommandChannel.launch(str, null);
    }

    @Override // com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp
    public void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        this.mSocialLogger.logButtonClick(clickEventButtonId, surfaceType);
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, String... strArr) {
        this.mSocialLogger.logButtonClick(clickEventButtonId, ((PeopleTabletMainView) Objects.requireNonNull(this.mPeopleTabletMainView, "mPeopleTabletMainView should not be null")).mViewType.getLoggingSurfaceType(), strArr);
    }
}
