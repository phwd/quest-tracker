package com.oculus.panelapp.people;

import X.AnonymousClass006;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.google.common.collect.ImmutableList;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCAllConnectionsFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCCurrentPartyFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCFriendRequestFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPYMKFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCSearchFetcher;
import com.oculus.panelapp.people.model.OCViewerSocialParty;
import com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewType;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.common.CommonPeopleUserAction;
import com.oculus.panelapp.people.views.actions.common.OCCreatePartyWith;
import com.oculus.panelapp.people.views.actions.common.OCJoinParty;
import com.oculus.panelapp.people.views.actions.oc.OCAcceptFriendRequest;
import com.oculus.panelapp.people.views.actions.oc.OCAddFriend;
import com.oculus.panelapp.people.views.actions.oc.OCAddToParty;
import com.oculus.panelapp.people.views.actions.oc.OCBlock;
import com.oculus.panelapp.people.views.actions.oc.OCCancelFriendRequest;
import com.oculus.panelapp.people.views.actions.oc.OCChat;
import com.oculus.panelapp.people.views.actions.oc.OCDenyFriendRequest;
import com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction;
import com.oculus.panelapp.people.views.actions.oc.OCReport;
import com.oculus.panelapp.people.views.actions.oc.OCUnFriend;
import com.oculus.panelapp.people.views.actions.oc.OCViewProfile;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OCPeopleTabletPanelApp extends PeopleTabletPanelApp {
    public static final int ALL_FRIENDS_FETCH_DELAY_MS = 500;
    public static final String TAG = LoggingUtil.tag(OCPeopleTabletPanelApp.class);
    public OCAllConnectionsFetcher mAllConnectionsFetcher;
    public OCCurrentPartyFetcher mCurrentPartyFetcher;
    public OCLinkedAccountsInfoFetcher mFblinkingFetcher;
    public OCFriendRequestFetcher mFriendRequestFetcher;
    public OCFriendsFetcher mFriendsFetcher;
    public Handler mHandler;
    public OCPYMKFetcher mPYMKFetcher;
    public OCPeopleNearbyFetcher mPeopleNearbyFetcher;
    public OCSearchFetcher mSearchFetcher;

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseAllConnectionsFetcher() {
    }

    /* renamed from: com.oculus.panelapp.people.OCPeopleTabletPanelApp$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUserFriendshipStatus;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0061 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0069 */
        static {
            /*
            // Method dump skipped, instructions count: 114
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.OCPeopleTabletPanelApp.AnonymousClass2.<clinit>():void");
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public ISearchFetcher acquireAllPeopleNearbyFetcher() {
        OCPeopleNearbyFetcher oCPeopleNearbyFetcher = this.mPeopleNearbyFetcher;
        oCPeopleNearbyFetcher.incrementRefCount();
        return oCPeopleNearbyFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCLinkedAccountsInfoFetcher acquireLinkedAccountsInfoFetcher() {
        OCLinkedAccountsInfoFetcher oCLinkedAccountsInfoFetcher = this.mFblinkingFetcher;
        oCLinkedAccountsInfoFetcher.incrementRefCount();
        return oCLinkedAccountsInfoFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCPeopleNearbyFetcher acquirePeopleNearbyFetcher() {
        OCPeopleNearbyFetcher oCPeopleNearbyFetcher = this.mPeopleNearbyFetcher;
        oCPeopleNearbyFetcher.incrementRefCount();
        return oCPeopleNearbyFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public ISearchFetcher acquireSearchFetcher() {
        OCSearchFetcher oCSearchFetcher = this.mSearchFetcher;
        oCSearchFetcher.incrementRefCount();
        return oCSearchFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void actionNavigateToProfile(String str) {
        actionNavigate(SystemUXRoute.AUI_PROFILE, new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_VR_PROFILE_URI).appendPath(str).appendQueryParameter("returnRoute", SystemUXRoute.AUI_PEOPLE.path()).appendQueryParameter("entrypoint", "aui_oc_people_tablet").build().toString());
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        AndroidPanelApp.PanelFrameCallback panelFrameCallback = this.mFblinkingFetcher;
        if (panelFrameCallback != null) {
            removePanelFrameCallback(panelFrameCallback);
            this.mFblinkingFetcher.destroy();
        }
        OCSearchFetcher oCSearchFetcher = this.mSearchFetcher;
        if (oCSearchFetcher != null) {
            oCSearchFetcher.destroy();
        }
        AndroidPanelApp.PanelFrameCallback panelFrameCallback2 = this.mFriendsFetcher;
        if (panelFrameCallback2 != null) {
            removePanelFrameCallback(panelFrameCallback2);
            this.mFriendsFetcher.destroy();
        }
        AndroidPanelApp.PanelFrameCallback panelFrameCallback3 = this.mFriendRequestFetcher;
        if (panelFrameCallback3 != null) {
            removePanelFrameCallback(panelFrameCallback3);
            this.mFriendRequestFetcher.destroy();
        }
        AndroidPanelApp.PanelFrameCallback panelFrameCallback4 = this.mPeopleNearbyFetcher;
        if (panelFrameCallback4 != null) {
            removePanelFrameCallback(panelFrameCallback4);
            this.mPeopleNearbyFetcher.destroy();
        }
        AndroidPanelApp.PanelFrameCallback panelFrameCallback5 = this.mPYMKFetcher;
        if (panelFrameCallback5 != null) {
            removePanelFrameCallback(panelFrameCallback5);
            this.mPYMKFetcher.destroy();
        }
        OCCurrentPartyFetcher oCCurrentPartyFetcher = this.mCurrentPartyFetcher;
        if (oCCurrentPartyFetcher != null) {
            oCCurrentPartyFetcher.decrementRefCount();
            removePanelFrameCallback(oCCurrentPartyFetcher);
            this.mCurrentPartyFetcher.destroy();
        }
        OCAllConnectionsFetcher oCAllConnectionsFetcher = this.mAllConnectionsFetcher;
        if (oCAllConnectionsFetcher != null) {
            oCAllConnectionsFetcher.destroy();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
        super.destroy();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void executeAction(PeopleUserAction peopleUserAction, Context context, PeopleUserActionHandler peopleUserActionHandler) {
        if (peopleUserAction instanceof OCPeopleUserAction) {
            ((OCPeopleUserAction) peopleUserAction).execute(this, context, peopleUserActionHandler);
        } else if (peopleUserAction instanceof CommonPeopleUserAction) {
            ((CommonPeopleUserAction) peopleUserAction).execute(this, context, peopleUserActionHandler);
        } else {
            Log.w(TAG, AnonymousClass006.A07("Received invalid action: ", peopleUserAction.getClass().getSimpleName()));
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public PeopleEmptyAdapterItemType getEmptyType(PeopleViewType peopleViewType) {
        PeopleEmptyAdapterItemType peopleEmptyAdapterItemType = PeopleEmptyAdapterItemType.ERROR;
        switch (peopleViewType.ordinal()) {
            case 0:
                return PeopleEmptyAdapterItemType.NO_OC_FRIENDS_ALL_CONNECTIONS;
            case 1:
                return PeopleEmptyAdapterItemType.NO_OC_ALL_PEOPLE_NEARBY;
            case 2:
                return getEmptyTypeForFBLinkedStatus(PeopleEmptyAdapterItemType.NO_OC_FRIENDS);
            case 3:
                return PeopleEmptyAdapterItemType.NO_REQUESTS;
            case 4:
                return PeopleEmptyAdapterItemType.EMPTY_SEARCH_LANDING_PAGE;
            case 5:
                return PeopleEmptyAdapterItemType.NO_SUGGESTIONS;
            case 6:
                return PeopleEmptyAdapterItemType.NO_OC_PEOPLE_NEARBY;
            default:
                return peopleEmptyAdapterItemType;
        }
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public FBLinkedStatus getFBLinkedStatus() {
        OCLinkedAccountsInfoFetcher oCLinkedAccountsInfoFetcher = this.mFblinkingFetcher;
        if (oCLinkedAccountsInfoFetcher == null || oCLinkedAccountsInfoFetcher.getIsErrored()) {
            return FBLinkedStatus.ERROR;
        }
        if (!oCLinkedAccountsInfoFetcher.getEnoughDataFetched()) {
            return FBLinkedStatus.NOT_READY;
        }
        if (this.mFblinkingFetcher.mLinkedAccountsInfo.isFbLinked()) {
            return FBLinkedStatus.LINKED;
        }
        return FBLinkedStatus.NOT_LINKED;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public PeopleTabletType getPeopleTabletType() {
        return PeopleTabletType.OCULUS;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public List<PeopleUserAction> getPossibleActions(PeopleUserAdapterItem peopleUserAdapterItem) {
        return (List) Stream.of((Object[]) new PeopleUserAction[]{new OCAddFriend(peopleUserAdapterItem, "PEOPLE_YOU_MAY_KNOW"), new OCCancelFriendRequest(peopleUserAdapterItem, "PEOPLE_YOU_MAY_KNOW"), new OCCreatePartyWith(peopleUserAdapterItem, this.mContext), new OCChat(peopleUserAdapterItem), new OCBlock(peopleUserAdapterItem), new OCReport(peopleUserAdapterItem), new OCAcceptFriendRequest(peopleUserAdapterItem), new OCDenyFriendRequest(peopleUserAdapterItem), new OCViewProfile(peopleUserAdapterItem), new OCUnFriend(peopleUserAdapterItem)}).filter($$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2.INSTANCE).collect(Collectors.toList());
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public List<PeopleUserAction> getPrimaryCTAAction(PeopleUserAdapterItem peopleUserAdapterItem) {
        ImmutableList A06;
        SocialUser socialUser = peopleUserAdapterItem.mUser;
        SocialUserFriendshipStatus friendship = socialUser.getFriendship();
        OCViewerSocialParty oCViewerSocialParty = this.mCurrentPartyFetcher.mCurrentParty;
        switch (friendship.ordinal()) {
            case 0:
                OCChat oCChat = new OCChat(peopleUserAdapterItem);
                OCJoinParty oCJoinParty = new OCJoinParty(peopleUserAdapterItem);
                if (oCViewerSocialParty != null) {
                    SocialParty.PartyMembership userPartyMembership = oCViewerSocialParty.getUserPartyMembership(socialUser);
                    if (userPartyMembership != SocialParty.PartyMembership.MEMBER) {
                        if (!oCViewerSocialParty.isViewerInvitedToParty(peopleUserAdapterItem.mUser.mCurrentPartyID)) {
                            if (userPartyMembership != SocialParty.PartyMembership.INVITED) {
                                if (oCViewerSocialParty.getID() != null) {
                                    A06 = ImmutableList.A07(new OCAddToParty(peopleUserAdapterItem, oCViewerSocialParty), oCChat);
                                    break;
                                }
                            }
                        }
                        A06 = ImmutableList.A07(oCJoinParty, oCChat);
                        break;
                    }
                    A06 = ImmutableList.A06(oCChat);
                    break;
                }
                if (!peopleUserAdapterItem.mUser.isInJoinableParty()) {
                    A06 = ImmutableList.A07(new OCCreatePartyWith(peopleUserAdapterItem, this.mContext), oCChat);
                    break;
                }
                A06 = ImmutableList.A07(oCJoinParty, oCChat);
            case 1:
            default:
                A06 = ImmutableList.of();
                break;
            case 2:
                A06 = ImmutableList.A06(new OCAddFriend(peopleUserAdapterItem, "PEOPLE_YOU_MAY_KNOW"));
                break;
            case 3:
                A06 = ImmutableList.A07(new OCAcceptFriendRequest(peopleUserAdapterItem), new OCDenyFriendRequest(peopleUserAdapterItem));
                break;
            case 4:
                A06 = ImmutableList.A06(new OCCancelFriendRequest(peopleUserAdapterItem, "PEOPLE_YOU_MAY_KNOW"));
                break;
        }
        List<PeopleUserAction> list = (List) A06.stream().filter($$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2.INSTANCE).collect(Collectors.toList());
        if (list.isEmpty()) {
            return Arrays.asList(new OCViewProfile(peopleUserAdapterItem));
        }
        return list;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public String getViewerID() {
        return this.mEnvironmentArgs.getOrDefault("_oc_userid", "");
    }

    public /* synthetic */ void lambda$new$0$OCPeopleTabletPanelApp() {
        this.mSearchFetcher.fetchFriends();
        this.mAllConnectionsFetcher.fetchFriends();
        this.mPeopleNearbyFetcher.fetchAllPeopleNearby();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseAllPeopleNearbyFetcher() {
        this.mPeopleNearbyFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseFriendRequestFetcher() {
        this.mFriendRequestFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseFriendsFetcher() {
        this.mFriendsFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseLinkedAccountsInfoFetcher() {
        this.mFblinkingFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releasePYMKFetcher() {
        this.mPYMKFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releasePeopleNearbyFetcher() {
        this.mPeopleNearbyFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void releaseSearchFetcher() {
        this.mSearchFetcher.decrementRefCount();
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public void sendMessage(String str, String str2, final PeopleUserActionHandler peopleUserActionHandler) {
        HorizonContentProviderHelper.messageSendToThread(this.mContext, str, str2, new HorizonContentProviderHelper.MessageSendToThreadCallback() {
            /* class com.oculus.panelapp.people.OCPeopleTabletPanelApp.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MessageSendToThreadCallback
            public void onSuccess(String str) {
                peopleUserActionHandler.onSuccess();
            }
        });
    }

    public OCPeopleTabletPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        SocialLogger logger = getLogger();
        this.mFblinkingFetcher = new OCLinkedAccountsInfoFetcher(context, logger);
        OCCurrentPartyFetcher oCCurrentPartyFetcher = new OCCurrentPartyFetcher(context, logger);
        this.mCurrentPartyFetcher = oCCurrentPartyFetcher;
        oCCurrentPartyFetcher.incrementRefCount();
        this.mFriendsFetcher = new OCFriendsFetcher(context, logger);
        this.mFriendRequestFetcher = new OCFriendRequestFetcher(context, logger);
        this.mPeopleNearbyFetcher = new OCPeopleNearbyFetcher(context, DeviceConfigHelper.getBoolean(context, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_PEOPLE_NEARBY), logger);
        this.mPYMKFetcher = new OCPYMKFetcher(context, logger);
        this.mSearchFetcher = new OCSearchFetcher(context, logger);
        this.mAllConnectionsFetcher = new OCAllConnectionsFetcher(context, logger);
        Handler handler = new Handler(context.getMainLooper());
        this.mHandler = handler;
        handler.postDelayed(new Runnable() {
            /* class com.oculus.panelapp.people.$$Lambda$OCPeopleTabletPanelApp$4pKcwCF9ONUf3fPvxSFGkiIv0bw2 */

            public final void run() {
                OCPeopleTabletPanelApp.this.lambda$new$0$OCPeopleTabletPanelApp();
            }
        }, 500);
        addPanelFrameCallback(this.mFblinkingFetcher);
        addPanelFrameCallback(this.mFriendsFetcher);
        addPanelFrameCallback(this.mFriendRequestFetcher);
        addPanelFrameCallback(this.mPeopleNearbyFetcher);
        addPanelFrameCallback(this.mPYMKFetcher);
        addPanelFrameCallback(this.mCurrentPartyFetcher);
        showMainLayer();
    }

    private PeopleEmptyAdapterItemType getEmptyTypeForFBLinkedStatus(PeopleEmptyAdapterItemType peopleEmptyAdapterItemType) {
        FBLinkedStatus fBLinkedStatus = getFBLinkedStatus();
        if (fBLinkedStatus == FBLinkedStatus.LINKED) {
            return peopleEmptyAdapterItemType;
        }
        if (fBLinkedStatus == FBLinkedStatus.NOT_LINKED) {
            return PeopleEmptyAdapterItemType.NOT_FB_LINKED;
        }
        return PeopleEmptyAdapterItemType.ERROR;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCAllConnectionsFetcher acquireAllConnectionsFetcher() {
        return this.mAllConnectionsFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCFriendRequestFetcher acquireFriendRequestFetcher() {
        OCFriendRequestFetcher oCFriendRequestFetcher = this.mFriendRequestFetcher;
        oCFriendRequestFetcher.incrementRefCount();
        return oCFriendRequestFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCFriendsFetcher acquireFriendsFetcher() {
        OCFriendsFetcher oCFriendsFetcher = this.mFriendsFetcher;
        oCFriendsFetcher.incrementRefCount();
        return oCFriendsFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCPYMKFetcher acquirePYMKFetcher() {
        OCPYMKFetcher oCPYMKFetcher = this.mPYMKFetcher;
        oCPYMKFetcher.incrementRefCount();
        return oCPYMKFetcher;
    }

    @Override // com.oculus.panelapp.people.PeopleTabletPanelApp
    public OCCurrentPartyFetcher getCurrentPartyFetcher() {
        return this.mCurrentPartyFetcher;
    }
}
