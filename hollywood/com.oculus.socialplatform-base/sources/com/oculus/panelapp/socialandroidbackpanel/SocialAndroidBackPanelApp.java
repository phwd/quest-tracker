package com.oculus.panelapp.socialandroidbackpanel;

import X.AnonymousClass006;
import X.AnonymousClass1Wu;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.TabletType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ApplicationInvitesViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.databinding.JoinPartyViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.databinding.PartyPrivacyViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.databinding.StartMessengerCallViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType;
import com.oculus.panelapp.socialandroidbackpanel.views.application_invites.ApplicationInvitesView;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyRequestFactory;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyView;
import com.oculus.panelapp.socialandroidbackpanel.views.load.LoadingContainer;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView;
import com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call.StartMessengerCallView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import java.util.Map;

public class SocialAndroidBackPanelApp extends AndroidPanelApp implements ISocialAndroidBackPanelApp {
    public static final String TAG = LoggingUtil.tag(SocialAndroidBackPanelApp.class);
    @Nullable
    @VisibleForTesting
    public SocialAndroidBackPanelView mCurrentView;
    @Nullable
    @VisibleForTesting
    public SocialOverlayPanelViewType mCurrentViewType;
    @Nullable
    public GraphQLService mGraphQLService;
    @Nullable
    public SocialLogger mSocialLogger;

    private native long nativeCreateInstance(long j, long j2);

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String[] getLayersToAutoEnable() {
        return new String[0];
    }

    /* renamed from: com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$SocialOverlayPanelViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType[] r0 = com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp.AnonymousClass2.$SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$SocialOverlayPanelViewType = r2
                com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType r0 = com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType.JOIN_PARTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType r0 = com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType.PARTY_PRIVACY     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType r0 = com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType.APPLICATION_INVITES     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType r0 = com.oculus.panelapp.socialandroidbackpanel.views.SocialOverlayPanelViewType.START_MESSENGER_CALL     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp.AnonymousClass2.<clinit>():void");
        }
    }

    private void destroyCurrentView() {
        SocialAndroidBackPanelView socialAndroidBackPanelView = this.mCurrentView;
        if (socialAndroidBackPanelView != null) {
            socialAndroidBackPanelView.destroy();
            this.mCurrentView = null;
            this.mCurrentViewType = null;
        }
    }

    private AndroidPanelLayer.Spec getMainLayerSpec() {
        return new AndroidPanelLayer.Spec("#main", 512, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, R.style.PanelAppTheme);
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
        GraphQLService graphQLService = this.mGraphQLService;
        if (graphQLService != null) {
            graphQLService.destroy();
            this.mGraphQLService = null;
        }
        this.mSocialLogger = null;
        destroyCurrentView();
        super.destroy();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.ISocialAndroidBackPanelApp
    public GraphQLService getGraphQLService() {
        return this.mGraphQLService;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new IllegalArgumentException("Unsupported layer.");
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.ISocialAndroidBackPanelApp
    public SocialLogger getLogger() {
        return this.mSocialLogger;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        SocialAndroidBackPanelView socialAndroidBackPanelView = this.mCurrentView;
        if (socialAndroidBackPanelView == null || !socialAndroidBackPanelView.onControllerBackButton()) {
            return true;
        }
        this.mFrameCommandChannel.playAudio(SoundType.CLOSE);
        return true;
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

    @Override // com.oculus.panelapp.socialandroidbackpanel.ISocialAndroidBackPanelApp
    public void quitApp() {
        this.mFrameCommandChannel.sendCommand("quitAndHide");
    }

    public SocialAndroidBackPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        HorizonContent.buildDeviceSynchronized(application);
        this.mGraphQLService = new GraphQLService(application);
        this.mSocialLogger = new SocialLogger(context, TabletType.OVERLAY_PANEL);
        AnonymousClass1Wu.A00(context, null, null);
        showMainLayer(getEnvironmentArg("uri"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SocialAndroidBackPanelView createMainLayerView(Context context, SocialOverlayPanelViewType socialOverlayPanelViewType, String str) {
        destroyCurrentView();
        switch (socialOverlayPanelViewType.ordinal()) {
            case 0:
                JoinPartyViewBinding inflate = JoinPartyViewBinding.inflate(LayoutInflater.from(context), null);
                JoinPartyView joinPartyView = (JoinPartyView) inflate.mRoot;
                LoadingContainer wrap = LoadingContainer.wrap(joinPartyView);
                joinPartyView.initialize(this, inflate, new JoinPartyRequestFactory(context), wrap);
                joinPartyView.onShow(str);
                wrap.waitFor(inflate.mJoinPartyViewModel);
                return wrap;
            case 1:
                PartyPrivacyViewBinding inflate2 = PartyPrivacyViewBinding.inflate(LayoutInflater.from(context), null);
                PartyPrivacyView partyPrivacyView = (PartyPrivacyView) inflate2.mRoot;
                LoadingContainer wrap2 = LoadingContainer.wrap(partyPrivacyView);
                partyPrivacyView.initialize(this, inflate2, wrap2);
                partyPrivacyView.onShow(str);
                wrap2.waitFor(inflate2.mPartyPrivacyViewModel);
                return wrap2;
            case 2:
                ApplicationInvitesViewBinding inflate3 = ApplicationInvitesViewBinding.inflate(LayoutInflater.from(context), null);
                ApplicationInvitesView applicationInvitesView = (ApplicationInvitesView) inflate3.mRoot;
                LoadingContainer wrap3 = LoadingContainer.wrap(applicationInvitesView);
                applicationInvitesView.initialize(this, inflate3, wrap3);
                wrap3.waitFor(inflate3.mApplicationInvitesViewModel);
                return wrap3;
            case 3:
                StartMessengerCallViewBinding inflate4 = StartMessengerCallViewBinding.inflate(LayoutInflater.from(context), null);
                StartMessengerCallView startMessengerCallView = (StartMessengerCallView) inflate4.mRoot;
                LoadingContainer wrap4 = LoadingContainer.wrap(startMessengerCallView);
                startMessengerCallView.initialize(this, inflate4, wrap4);
                return wrap4;
            default:
                return null;
        }
    }

    private SocialOverlayPanelViewType getViewType(@Nullable String str) {
        try {
            return SocialOverlayPanelViewType.fromString(Uri.parse(str).getLastPathSegment());
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A07("Error parsing uri: ", str), e);
            return SocialOverlayPanelViewType.UNKNOWN;
        }
    }

    private void showMainLayer(@Nullable final String str) {
        final SocialOverlayPanelViewType viewType = getViewType(str);
        if (viewType == SocialOverlayPanelViewType.UNKNOWN) {
            quitApp();
            return;
        }
        SocialAndroidBackPanelView socialAndroidBackPanelView = this.mCurrentView;
        if (socialAndroidBackPanelView == null || this.mCurrentViewType != viewType) {
            ensurePanelLayer("#main", getMainLayerSpec(), new AndroidPanelApp.ViewCreator() {
                /* class com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp.AnonymousClass1 */

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public String name() {
                    return "socialandroidbackpanel_#main";
                }

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public View createView(Context context) {
                    SocialAndroidBackPanelView createMainLayerView = SocialAndroidBackPanelApp.this.createMainLayerView(context, viewType, str);
                    if (createMainLayerView != null) {
                        SocialAndroidBackPanelApp socialAndroidBackPanelApp = SocialAndroidBackPanelApp.this;
                        socialAndroidBackPanelApp.mCurrentViewType = viewType;
                        socialAndroidBackPanelApp.mCurrentView = createMainLayerView;
                        return (View) createMainLayerView;
                    }
                    throw new RuntimeException(String.format("View uri is not handled by SocialAndroidBackPanelApp: %s", str));
                }
            });
            return;
        }
        socialAndroidBackPanelView.onShow(str);
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onDeepLink(String str, String str2) {
        showMainLayer(str2);
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.ISocialAndroidBackPanelApp
    public boolean isGKEnabled(Gatekeeper gatekeeper) {
        return DeviceConfigHelper.getBoolean(this.mContext, gatekeeper);
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.ISocialAndroidBackPanelApp
    public boolean isGKEnabled(DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC) {
        return DeviceConfigHelper.getBoolean(this.mContext, deviceConfigSocialPlatformMC);
    }
}
