package com.oculus.panelapp.people;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AnonymousClass006;
import X.AnonymousClass1xU;
import X.AnonymousClass1y2;
import X.C137220e;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fbauth.FBUser;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher;
import com.oculus.panelapp.people.fetchers.fb.FBAllConnectionsFetcher;
import com.oculus.panelapp.people.fetchers.fb.FBFriendRequestFetcher;
import com.oculus.panelapp.people.fetchers.fb.FBFriendsFetcher;
import com.oculus.panelapp.people.fetchers.fb.FBSearchFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher;
import com.oculus.panelapp.people.graphql.FBFriendingMutator;
import com.oculus.panelapp.people.graphql.FBSendMessageMutation;
import com.oculus.panelapp.people.model.IViewerSocialParty;
import com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewType;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.common.CommonPeopleUserAction;
import com.oculus.panelapp.people.views.actions.common.OCCreatePartyWith;
import com.oculus.panelapp.people.views.actions.common.OCJoinParty;
import com.oculus.panelapp.people.views.actions.fb.FBAcceptFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.FBAddFriend;
import com.oculus.panelapp.people.views.actions.fb.FBBlockOnMessenger;
import com.oculus.panelapp.people.views.actions.fb.FBCancelFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.FBChat;
import com.oculus.panelapp.people.views.actions.fb.FBDenyFriendRequest;
import com.oculus.panelapp.people.views.actions.fb.FBPeopleUserAction;
import com.oculus.panelapp.people.views.actions.fb.FBReport;
import com.oculus.panelapp.people.views.actions.fb.FBShareParty;
import com.oculus.panelapp.people.views.actions.fb.FBViewProfile;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestCancelRef;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestHowFound;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestResponseRef;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

public class FBPeopleTabletPanelApp extends PeopleTabletPanelApp {
    public static final String TAG = LoggingUtil.tag(FBPeopleTabletPanelApp.class);
    @Nullable
    public FBAllConnectionsFetcher mAllConnectionsFetcher;
    public final Context mContext;
    @Nullable
    public FBFriendingMutator mFBFriendingMutator;
    @Nullable
    public FBFriendRequestFetcher mFriendRequestFetcher;
    @Nullable
    public FBFriendsFetcher mFriendsFetcher;
    public Handler mHandler;
    public boolean mIsMessengerAuthenticated;
    public final OkHttpClient mOkHttpClient;
    @Nullable
    public AbstractC12271xB mQueryUserDisposable;
    @Nullable
    public FBSearchFetcher mSearchFetcher;
    public final AnonymousClass1xU mSendMessageCompositeDisposable = new AnonymousClass1xU();

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public ISearchFetcher acquireAllPeopleNearbyFetcher() {
        return null;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCLinkedAccountsInfoFetcher acquireLinkedAccountsInfoFetcher() {
        return null;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public BaseFetcher acquirePYMKFetcher() {
        return null;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCPeopleNearbyFetcher acquirePeopleNearbyFetcher() {
        return null;
    }

    public /* synthetic */ void lambda$sendMessage$2$FBPeopleTabletPanelApp(final String str, String str2, final PeopleUserActionHandler peopleUserActionHandler, String str3) throws Exception {
        FBSendMessageMutation.sendMessage(str, str2, new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.people.FBPeopleTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public void callback(JSONObject jSONObject) {
                FBPeopleTabletPanelApp.this.mHandler.post(new Runnable(str, peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.$$Lambda$FBPeopleTabletPanelApp$1$roS85jvpLXt1648WnlOrFEV3n2I2 */
                    public final /* synthetic */ String f$0;
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        this.f$1.onSuccess();
                    }
                });
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.FBPeopleTabletPanelApp.AnonymousClass2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public void callback(Throwable th) {
                FBPeopleTabletPanelApp.this.mHandler.post(new Runnable(str, peopleUserActionHandler) {
                    /* class com.oculus.panelapp.people.$$Lambda$FBPeopleTabletPanelApp$2$mxQHCHKMV86M57A7CB_1jO59Bk82 */
                    public final /* synthetic */ String f$0;
                    public final /* synthetic */ PeopleUserActionHandler f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        String str = this.f$0;
                        this.f$1.onError("");
                    }
                });
            }
        }, str3, this.mOkHttpClient);
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseAllConnectionsFetcher() {
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseAllPeopleNearbyFetcher() {
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseLinkedAccountsInfoFetcher() {
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releasePYMKFetcher() {
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releasePeopleNearbyFetcher() {
    }

    /* renamed from: com.oculus.panelapp.people.FBPeopleTabletPanelApp$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUserFriendshipStatus;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004e */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.panelapp.people.FBPeopleTabletPanelApp.AnonymousClass3.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r1
                r5 = 1
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r1[r0] = r5     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r4 = 2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.ALL_CONNECTIONS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                r3 = 3
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SEARCH     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                r2 = 4
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.horizoncontent.social.SocialUserFriendshipStatus[] r0 = com.oculus.horizoncontent.social.SocialUserFriendshipStatus.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.panelapp.people.FBPeopleTabletPanelApp.AnonymousClass3.$SwitchMap$com$oculus$horizoncontent$social$SocialUserFriendshipStatus = r1
                com.oculus.horizoncontent.social.SocialUserFriendshipStatus r0 = com.oculus.horizoncontent.social.SocialUserFriendshipStatus.ARE_FRIENDS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r1[r0] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.oculus.horizoncontent.social.SocialUserFriendshipStatus r0 = com.oculus.horizoncontent.social.SocialUserFriendshipStatus.CAN_REQUEST     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.oculus.horizoncontent.social.SocialUserFriendshipStatus r0 = com.oculus.horizoncontent.social.SocialUserFriendshipStatus.OUTGOING_REQUEST     // Catch:{ NoSuchFieldError -> 0x004e }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                com.oculus.horizoncontent.social.SocialUserFriendshipStatus r0 = com.oculus.horizoncontent.social.SocialUserFriendshipStatus.INCOMING_REQUEST     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.FBPeopleTabletPanelApp.AnonymousClass3.<clinit>():void");
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public ISearchFetcher acquireAllConnectionsFetcher() {
        return this.mAllConnectionsFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public ISearchFetcher acquireSearchFetcher() {
        FBSearchFetcher fBSearchFetcher = this.mSearchFetcher;
        fBSearchFetcher.incrementRefCount();
        return fBSearchFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void actionNavigateToProfile(String str) {
        actionNavigate(SystemUXRoute.AUI_PROFILE, new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_FB_PROFILE_URI).appendPath(str).appendQueryParameter("returnRoute", SystemUXRoute.AUI_PEOPLE_FB.path()).appendQueryParameter("entrypoint", "aui_fb_people_tablet").build().toString());
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        FBFriendsFetcher fBFriendsFetcher = this.mFriendsFetcher;
        if (fBFriendsFetcher != null) {
            removePanelFrameCallback(fBFriendsFetcher);
            this.mFriendsFetcher.destroy();
        }
        FBAllConnectionsFetcher fBAllConnectionsFetcher = this.mAllConnectionsFetcher;
        if (fBAllConnectionsFetcher != null) {
            fBAllConnectionsFetcher.destroy();
        }
        FBSearchFetcher fBSearchFetcher = this.mSearchFetcher;
        if (fBSearchFetcher != null) {
            fBSearchFetcher.destroy();
        }
        FBFriendingMutator fBFriendingMutator = this.mFBFriendingMutator;
        if (fBFriendingMutator != null) {
            fBFriendingMutator.destroy();
        }
        this.mSendMessageCompositeDisposable.A01();
        this.mOkHttpClient.dispatcher.cancelAll();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
        AbstractC12271xB r0 = this.mQueryUserDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        super.destroy();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void executeAction(PeopleUserAction peopleUserAction, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        if (peopleUserAction instanceof FBPeopleUserAction) {
            ((FBPeopleUserAction) peopleUserAction).execute(this, context, peopleUserActionHandler);
        } else if (peopleUserAction instanceof CommonPeopleUserAction) {
            ((CommonPeopleUserAction) peopleUserAction).execute(this, context, peopleUserActionHandler);
        } else {
            Log.w(TAG, AnonymousClass006.A07("Received invalid action: ", peopleUserAction.getClass().getSimpleName()));
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public IViewerSocialPartyFetcher getCurrentPartyFetcher() {
        return this.mFriendsFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public PeopleEmptyAdapterItemType getEmptyType(PeopleViewType peopleViewType) {
        PeopleEmptyAdapterItemType peopleEmptyAdapterItemType = PeopleEmptyAdapterItemType.ERROR;
        switch (peopleViewType.ordinal()) {
            case 0:
                return PeopleEmptyAdapterItemType.NO_FB_FRIENDS_ALL_CONNECTIONS;
            case 1:
            default:
                return peopleEmptyAdapterItemType;
            case 2:
                return PeopleEmptyAdapterItemType.NO_FB_FRIENDS;
            case 3:
                return PeopleEmptyAdapterItemType.NO_REQUESTS;
            case 4:
                return PeopleEmptyAdapterItemType.EMPTY_SEARCH_LANDING_PAGE_FB;
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public FBLinkedStatus getFBLinkedStatus() {
        return FBLinkedStatus.LINKED;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public PeopleTabletType getPeopleTabletType() {
        return PeopleTabletType.FACEBOOK;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public List<PeopleUserAction> getPossibleActions(PeopleUserAdapterItem peopleUserAdapterItem) {
        FBChat fBChat = new FBChat(peopleUserAdapterItem);
        FBBlockOnMessenger fBBlockOnMessenger = new FBBlockOnMessenger(peopleUserAdapterItem);
        FBReport fBReport = new FBReport(peopleUserAdapterItem);
        FBViewProfile fBViewProfile = new FBViewProfile(peopleUserAdapterItem);
        FBFriendingMutator fBFriendingMutator = this.mFBFriendingMutator;
        FBAddFriend fBAddFriend = new FBAddFriend(peopleUserAdapterItem, fBFriendingMutator, FriendRequestHowFound.OCULUS_MESSENGER_VR);
        FBCancelFriendRequest fBCancelFriendRequest = new FBCancelFriendRequest(peopleUserAdapterItem, fBFriendingMutator, FriendRequestCancelRef.OCULUS_MESSENGER_VR);
        FriendRequestResponseRef friendRequestResponseRef = FriendRequestResponseRef.OCULUS_MESSENGER_VR;
        return (List) Stream.of((Object[]) new FBPeopleUserAction[]{fBChat, fBBlockOnMessenger, fBReport, fBViewProfile, fBAddFriend, fBCancelFriendRequest, new FBAcceptFriendRequest(peopleUserAdapterItem, fBFriendingMutator, friendRequestResponseRef), new FBDenyFriendRequest(peopleUserAdapterItem, fBFriendingMutator, friendRequestResponseRef)}).filter($$Lambda$blQ6FfbpdgNN_WDHngkoRtE0x3A2.INSTANCE).collect(Collectors.toList());
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public String getViewerID() {
        Optional ofNullable = Optional.ofNullable(FBAccountManager.sUser);
        if (ofNullable.isPresent()) {
            return ((FBUser) ofNullable.get()).mUserId;
        }
        return "";
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseFriendsFetcher() {
        this.mFriendsFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseSearchFetcher() {
        this.mSearchFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void sendMessage(String str, String str2, PeopleUserActionHandler peopleUserActionHandler) {
        this.mSendMessageCompositeDisposable.A1D(FBAuthManager.queryAccessToken().A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(str, str2, peopleUserActionHandler) {
            /* class com.oculus.panelapp.people.$$Lambda$FBPeopleTabletPanelApp$ZsPe0VTLlb9AY5YumLNrsVRck4U2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ PeopleUserActionHandler f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBPeopleTabletPanelApp.this.lambda$sendMessage$2$FBPeopleTabletPanelApp(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        }, C137220e.A06));
    }

    @SuppressLint({"CheckResult"})
    public FBPeopleTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        this.mContext = context;
        SocialLogger logger = getLogger();
        SurfaceType surfaceType = SurfaceType.FB_PEOPLE_INITIALIZING;
        logger.logImpression(surfaceType, null);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        this.mOkHttpClient = builder.build();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIsMessengerAuthenticated = MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(context);
        getLogger().logActionSuccess(ActionId.CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED, ClickEventButtonId.NO_BUTTON, surfaceType);
        if (this.mIsMessengerAuthenticated) {
            initialize();
        } else {
            actionNavigate(SystemUXRoute.AUI_SOCIAL_REAUTH, "");
        }
    }

    private void initialize() {
        SocialLogger logger = getLogger();
        FBFriendsFetcher fBFriendsFetcher = new FBFriendsFetcher(this.mContext, this.mOkHttpClient, FBAuthManager.queryAccessToken(), logger);
        this.mFriendsFetcher = fBFriendsFetcher;
        addPanelFrameCallback(fBFriendsFetcher);
        if (getFbRequestsEnabled()) {
            FBFriendRequestFetcher fBFriendRequestFetcher = new FBFriendRequestFetcher(this.mContext, this.mOkHttpClient, FBAuthManager.queryAccessToken(), logger);
            this.mFriendRequestFetcher = fBFriendRequestFetcher;
            addPanelFrameCallback(fBFriendRequestFetcher);
        }
        this.mAllConnectionsFetcher = new FBAllConnectionsFetcher(this.mContext, this.mOkHttpClient, FBAuthManager.queryAccessToken(), logger);
        this.mSearchFetcher = new FBSearchFetcher(this.mContext, this.mOkHttpClient, FBAuthManager.queryAccessToken(), DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_FB_SEARCH), logger);
        this.mQueryUserDisposable = FBAccountManager.queryUser().A03(AnonymousClass1y2.A00()).A05($$Lambda$FBPeopleTabletPanelApp$JYlqy50DFNmj8lugSQfRrZOm02.INSTANCE, $$Lambda$FBPeopleTabletPanelApp$N9sMSNwWKCQdbpSLaFDMBNDV6Y2.INSTANCE);
        showMainLayer();
        this.mFBFriendingMutator = new FBFriendingMutator(this.mOkHttpClient, FBAuthManager.queryAccessToken());
        getLogger().logImpression(SurfaceType.FB_PEOPLE_INITIALIZED, null);
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public List<PeopleUserAction> getPrimaryCTAAction(PeopleUserAdapterItem peopleUserAdapterItem) {
        ImmutableList A07;
        IViewerSocialParty viewerSocialParty = getCurrentPartyFetcher().getViewerSocialParty();
        FBChat fBChat = new FBChat(peopleUserAdapterItem);
        OCJoinParty oCJoinParty = new OCJoinParty(peopleUserAdapterItem);
        switch (peopleUserAdapterItem.mUser.getFriendship().ordinal()) {
            case 0:
                if (!DeviceConfigHelper.getBoolean(super.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_PARTIES_KILL_SWITCH)) {
                    if (viewerSocialParty != null) {
                        SocialParty.PartyMembership userPartyMembership = viewerSocialParty.getUserPartyMembership(peopleUserAdapterItem.mUser);
                        if (userPartyMembership != SocialParty.PartyMembership.MEMBER) {
                            if (!viewerSocialParty.isViewerInvitedToParty(peopleUserAdapterItem.mUser.mCurrentPartyID)) {
                                if (userPartyMembership != SocialParty.PartyMembership.INVITED) {
                                    if (viewerSocialParty.getID() != null && !DeviceConfigHelper.getBoolean(super.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_PARTIES_KILL_SWITCH)) {
                                        A07 = ImmutableList.A07(new FBShareParty(peopleUserAdapterItem, viewerSocialParty.getHasLinkSharing()), fBChat);
                                        break;
                                    }
                                }
                            }
                            A07 = ImmutableList.A07(oCJoinParty, fBChat);
                            break;
                        }
                    }
                    if (!peopleUserAdapterItem.mUser.isInJoinableParty()) {
                        if (!DeviceConfigHelper.getBoolean(super.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_PARTIES_KILL_SWITCH)) {
                            A07 = ImmutableList.A07(new OCCreatePartyWith(peopleUserAdapterItem, super.mContext), fBChat);
                            break;
                        }
                    }
                    A07 = ImmutableList.A07(oCJoinParty, fBChat);
                }
            case 1:
            default:
                A07 = ImmutableList.A06(fBChat);
                break;
            case 2:
                A07 = ImmutableList.A07(new FBAddFriend(peopleUserAdapterItem, this.mFBFriendingMutator, FriendRequestHowFound.OCULUS_MESSENGER_VR), fBChat);
                break;
            case 3:
                FBFriendingMutator fBFriendingMutator = this.mFBFriendingMutator;
                FriendRequestResponseRef friendRequestResponseRef = FriendRequestResponseRef.OCULUS_MESSENGER_VR;
                A07 = ImmutableList.A07(new FBAcceptFriendRequest(peopleUserAdapterItem, fBFriendingMutator, friendRequestResponseRef), new FBDenyFriendRequest(peopleUserAdapterItem, fBFriendingMutator, friendRequestResponseRef));
                break;
            case 4:
                A07 = ImmutableList.A07(new FBCancelFriendRequest(peopleUserAdapterItem, this.mFBFriendingMutator, FriendRequestCancelRef.OCULUS_MESSENGER_VR), fBChat);
                break;
        }
        List<PeopleUserAction> list = (List) A07.stream().filter($$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2.INSTANCE).collect(Collectors.toList());
        if (list.isEmpty()) {
            return Arrays.asList(new FBViewProfile(peopleUserAdapterItem));
        }
        return list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        if (r2 == false) goto L_0x0011;
     */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onForeground() {
        /*
            r3 = this;
            super.onForeground()
            android.content.Context r0 = r3.mContext
            boolean r2 = com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(r0)
            boolean r0 = r3.mIsMessengerAuthenticated
            java.lang.String r1 = "quitAndHide"
            if (r0 == 0) goto L_0x0019
            if (r2 != 0) goto L_0x0016
        L_0x0011:
            com.oculus.vrshell.panels.FrameCommandChannel r0 = r3.mFrameCommandChannel
            r0.sendCommand(r1)
        L_0x0016:
            r3.mIsMessengerAuthenticated = r2
            return
        L_0x0019:
            if (r2 != 0) goto L_0x0011
            com.oculus.vrshell.SystemUXRoute r1 = com.oculus.vrshell.SystemUXRoute.AUI_SOCIAL_REAUTH
            java.lang.String r0 = ""
            r3.actionNavigate(r1, r0)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.FBPeopleTabletPanelApp.onForeground():void");
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseFriendRequestFetcher() {
        if (getFbRequestsEnabled()) {
            this.mFriendRequestFetcher.decrementRefCount();
        }
    }

    public static /* synthetic */ void lambda$initialize$0(FBUser fBUser) throws Exception {
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public FBFriendRequestFetcher acquireFriendRequestFetcher() {
        if (!getFbRequestsEnabled()) {
            return null;
        }
        FBFriendRequestFetcher fBFriendRequestFetcher = this.mFriendRequestFetcher;
        fBFriendRequestFetcher.incrementRefCount();
        return fBFriendRequestFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public FBFriendsFetcher acquireFriendsFetcher() {
        FBFriendsFetcher fBFriendsFetcher = this.mFriendsFetcher;
        fBFriendsFetcher.incrementRefCount();
        return fBFriendsFetcher;
    }
}
