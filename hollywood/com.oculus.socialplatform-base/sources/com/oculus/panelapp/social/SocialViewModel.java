package com.oculus.panelapp.social;

import X.AnonymousClass006;
import X.AnonymousClass1uc;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.Log;
import androidx.databinding.Bindable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.PartyMicrophoneInputLocation;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialViewerInfo;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class SocialViewModel extends AnonymousClass1uc implements AndroidPanelApp.PanelFrameCallback, ViewModelLifecycle {
    public static final String BROADCAST_ACTION_TABLET_ACTIVE = "com.oculus.socialplatform.broadcast.TABLET_ACTIVE";
    public static final String BROADCAST_ACTION_TABLET_ACTIVE_EXTRA = "active";
    public static final long SOCIAL_ACTIVE_HI_PRI_DATA_UPDATE_RATE_MILLIS = 11000;
    public static final long SOCIAL_ACTIVE_LOW_PRI_DATA_UPDATE_RATE_MILLIS = 21000;
    public static final long SOCIAL_ACTIVE_PARTY_DATA_UPDATE_RATE_MILLIS = 4900;
    public static final long SOCIAL_INACTIVE_DATA_UPDATE_RATE_MILLIS = 45000;
    public static final long SOCIAL_PARTY_SPEAKING_INDICATOR_RATE_MILLIS = 500;
    public static final long SOCIAL_SYSTEM_DATA_UPDATE_RATE_MILLIS = 5000;
    public static final String TAG = LoggingUtil.tag(SocialViewModel.class);
    public ConnectivityManager mConnection;
    public Context mContext;
    public boolean mEnoughDataFetched;
    public boolean mEnoughFBLinkDataFetched;
    public boolean mEnoughFriendDataFetched;
    public boolean mEnoughSuggestedActionDataFetched;
    @Nullable
    public AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchJoinablePartiesAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchPYMKsAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchPartyMicrophoneInputLocationHandle;
    @Nullable
    public AsyncQueryHandle mFetchSocialFriendsAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchSocialViewerAsyncQueryHandle;
    public List<SocialUser> mFriends = new ArrayList();
    public boolean mHasInternetConnection;
    public long mHighPriLastServerUpdateTimeMillis;
    public boolean mIgnoreNextPartyUpdate;
    public List<SocialParty> mJoinableParties = new ArrayList();
    public long mLastSpeakingIndicatorUpdateTimeMillis;
    public long mLastSystemUpdateTimeMillis;
    public LinkedAccountsInfo mLinkedAccountsInfo;
    public long mLowPriLastServerUpdateTimeMillis;
    public ArrayList<SocialDataObserver> mObservers = new ArrayList<>();
    public SocialPopupMenu mOpenMenu;
    public ArrayList<SocialUser> mPYMKs = new ArrayList<>();
    public SocialPanelApp mPanelApp;
    public SocialParty mParty;
    public long mPartyLastServerUpdateTimeMillis;
    public PartyMicrophoneInputLocation mPartyMicrophoneInputLocation;
    public ArrayList<SocialPartyObserver> mPartyObservers = new ArrayList<>();
    public Map<String, ISpeakerIndicatorListener> mPartySpeakingIndicatorListeners;
    public float mPartyVolume;
    public final Resources mResources;
    public SocialPlatformBroadcastReceiver mSocialPlatformBroadcastReceiver;
    public SocialViewerInfo mSocialViewerInfo;
    public boolean mTabletActive;

    public enum SocialList {
        REQUESTS,
        FRIENDS,
        PYMK
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFriends(List<SocialUser> list) {
        this.mEnoughFriendDataFetched = true;
        if (!this.mFriends.equals(list)) {
            list.size();
            this.mFriends = list;
            updateFriendsListForObservers();
        }
    }

    public void ignoreNextPartyUpdate() {
        this.mIgnoreNextPartyUpdate = true;
    }

    /* renamed from: com.oculus.panelapp.social.SocialViewModel$9  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass9 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002c */
        static {
            /*
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation[] r0 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation = r1
                r3 = 1
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r0 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.APP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r2 = 2
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r0 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.PARTY     // Catch:{ NoSuchFieldError -> 0x001b }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.social.SocialViewModel$SocialList[] r0 = com.oculus.panelapp.social.SocialViewModel.SocialList.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList = r1
                com.oculus.panelapp.social.SocialViewModel$SocialList r0 = com.oculus.panelapp.social.SocialViewModel.SocialList.FRIENDS     // Catch:{ NoSuchFieldError -> 0x002c }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002c }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x002c }
            L_0x002c:
                com.oculus.panelapp.social.SocialViewModel$SocialList r0 = com.oculus.panelapp.social.SocialViewModel.SocialList.PYMK     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.<clinit>():void");
        }
    }

    public class SocialPlatformBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY.equals(action)) {
                SocialViewModel.this.removePartyOptimistically();
            } else if (PartiesBroadcastActions.ACTION_PARTY_CREATED.equals(action) || PartiesBroadcastActions.ACTION_PARTY_JOINED.equals(action)) {
                SocialViewModel.this.loadPartyData();
            } else if (SocialViewModel.BROADCAST_ACTION_TABLET_ACTIVE.equals(action)) {
                SocialViewModel.this.setTabletActive(intent.getBooleanExtra("active", false));
            }
        }

        public SocialPlatformBroadcastReceiver() {
        }
    }

    private void alertListObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().updateFriendsList(this.mFriends, this.mPYMKs, this.mJoinableParties, this.mParty);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchCurrentPartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchCurrentPartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchCurrentPartyAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchJoinablePartiesAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchJoinablePartiesAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchJoinablePartiesAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPYMKsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPYMKsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPYMKsAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPartyMicrophoneInputLocationAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPartyMicrophoneInputLocationHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPartyMicrophoneInputLocationHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialFriendsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSocialFriendsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSocialFriendsAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialViewerAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSocialViewerAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSocialViewerAsyncQueryHandle = null;
        }
    }

    private void loadFriends() {
        if (DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER)) {
            this.mEnoughFriendDataFetched = true;
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchSocialFriendsAsyncQueryHandle();
        this.mFetchSocialFriendsAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialFriends(this.mContext, null, null, new HorizonContentProviderHelper.FetchSocialFriendsCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onError(Exception exc) {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_friend", SocialViewModel.TAG, AnonymousClass006.A07("Failed to fetch Friends, latency: ", AnonymousClass006.A00(System.currentTimeMillis() - currentTimeMillis, "ms")));
                SocialViewModel.this.clearFetchSocialFriendsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onSuccess(List<SocialUser> list) {
                System.currentTimeMillis();
                list.size();
                SocialViewModel.this.setFriends(list);
                SocialViewModel.this.clearFetchSocialFriendsAsyncQueryHandle();
            }
        });
    }

    private void loadLinkedAccountsData() {
        HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(this.mContext, new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_linked_accounts_data", SocialViewModel.TAG, "Failed to fetch current linked accounts data");
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
            public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                SocialViewModel socialViewModel = SocialViewModel.this;
                socialViewModel.mLinkedAccountsInfo = linkedAccountsInfo;
                if (!socialViewModel.mEnoughFBLinkDataFetched) {
                    socialViewModel.mEnoughFBLinkDataFetched = true;
                    socialViewModel.updateFriendsListForObservers();
                }
            }
        });
    }

    private void loadPYMKs() {
        if (DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER)) {
            this.mEnoughFriendDataFetched = true;
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchPYMKsAsyncQueryHandle();
        this.mFetchPYMKsAsyncQueryHandle = HorizonContentProviderHelper.fetchPYMKs(this.mContext, new HorizonContentProviderHelper.FetchPYMKsCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_pymk", SocialViewModel.TAG, AnonymousClass006.A07("Failed to fetch PYMKs, latency: ", AnonymousClass006.A00(System.currentTimeMillis() - currentTimeMillis, "ms")));
                SocialViewModel.this.clearFetchPYMKsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPYMKsCallback
            public void onSuccess(ArrayList<SocialUser> arrayList) {
                System.currentTimeMillis();
                arrayList.size();
                SocialViewModel.this.setPYMKs(arrayList);
                SocialViewModel.this.clearFetchPYMKsAsyncQueryHandle();
            }
        });
    }

    public static void removeCurrentParty(List<SocialParty> list, String str) {
        list.removeIf(new Predicate(str) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$amDcUD_Iid4vsB84D8GLC2Hdp3k2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialParty) obj).mID.equals(this.f$0);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJoinableParties(List<SocialParty> list) {
        if (!this.mJoinableParties.equals(list)) {
            list.size();
            this.mJoinableParties = list;
            updateFriendsListForObservers();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPYMKs(ArrayList<SocialUser> arrayList) {
        if (!this.mPYMKs.equals(arrayList)) {
            arrayList.size();
            this.mPYMKs = arrayList;
            this.mEnoughFriendDataFetched = true;
            updateFriendsListForObservers();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParty(SocialParty socialParty) {
        String str;
        if (socialParty == null || (str = socialParty.mID) == null || str.isEmpty()) {
            socialParty = null;
        }
        if (!Objects.equals(this.mParty, socialParty)) {
            this.mParty = socialParty;
            Iterator<SocialPartyObserver> it = this.mPartyObservers.iterator();
            while (it.hasNext()) {
                it.next().onUpdateParty(this.mParty);
            }
            alertListObservers();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateFriendsListForObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().updateFriendsList(this.mFriends, this.mPYMKs, getJoinableParties(), this.mParty);
        }
    }

    private void updateInternetConnection() {
        boolean z = false;
        if (this.mConnection.getActiveNetwork() != null) {
            z = true;
        }
        if (z != this.mHasInternetConnection) {
            this.mHasInternetConnection = z;
            if (z) {
                refreshHiPriData();
                refreshPartyData();
                refreshLowPriData();
            }
            if (!this.mHasInternetConnection && this.mParty != null) {
                setParty(null);
            }
        }
    }

    private void updateObserversOnEnoughDataFetched() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onEnoughDataFetched();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateSocialViewerForObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().updateSocialViewer();
        }
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mObservers.clear();
        this.mPartyObservers.clear();
        SocialPlatformBroadcastReceiver socialPlatformBroadcastReceiver = this.mSocialPlatformBroadcastReceiver;
        if (socialPlatformBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(socialPlatformBroadcastReceiver);
            this.mSocialPlatformBroadcastReceiver = null;
        }
        clearFetchSocialViewerAsyncQueryHandle();
        clearFetchSocialFriendsAsyncQueryHandle();
        clearFetchJoinablePartiesAsyncQueryHandle();
        clearFetchCurrentPartyAsyncQueryHandle();
        clearFetchPYMKsAsyncQueryHandle();
        clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
    }

    public void fetchSpeakerStatuses() {
        try {
            this.mPartySpeakingIndicatorListeners.forEach(new BiConsumer() {
                /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$k4j0thkm5t_FLPOjiytCcoW7svQ2 */

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SocialViewModel.this.lambda$fetchSpeakerStatuses$3$SocialViewModel((String) obj, (ISpeakerIndicatorListener) obj2);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception when fetching user speaker statuses.", e);
        }
    }

    public String getAlias() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        if (socialViewerInfo == null) {
            return "";
        }
        return socialViewerInfo.mAlias;
    }

    public String getAvatarPhotoUrl() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        if (socialViewerInfo == null) {
            return "";
        }
        return socialViewerInfo.mAvatarPhotoUrl;
    }

    public boolean getEnoughDataFetched() {
        if (!this.mEnoughFriendDataFetched || !this.mEnoughSuggestedActionDataFetched || !this.mEnoughFBLinkDataFetched) {
            return false;
        }
        return true;
    }

    public List<SocialUser> getFriends() {
        return this.mFriends;
    }

    public List<SocialParty> getJoinableParties() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            removeCurrentParty(this.mJoinableParties, socialParty.mID);
        }
        return this.mJoinableParties;
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    @Nullable
    public SocialPopupMenu getOpenMenu() {
        return this.mOpenMenu;
    }

    public ArrayList<SocialUser> getPYMKs() {
        return this.mPYMKs;
    }

    @Nullable
    public SocialParty getPartyData() {
        return this.mParty;
    }

    public PartyMicrophoneInputLocation getPartyMicrophoneInputLocation() {
        return this.mPartyMicrophoneInputLocation;
    }

    public float getPartyVolume() {
        return this.mPartyVolume;
    }

    public String getProfilePhotoUrl() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        if (socialViewerInfo == null) {
            return "";
        }
        return socialViewerInfo.mProfilePhotoUrl;
    }

    @Bindable
    public String getSharePartyButtonText() {
        return this.mResources.getString(R.string.anytime_tablet_social_party_share_party_button);
    }

    @Bindable
    public boolean getShouldShowSharePartyButton() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.SHARE_PARTY_BUTTON);
    }

    public boolean hasInternetConnection() {
        return this.mHasInternetConnection;
    }

    public boolean isPartyMuted() {
        if (this.mParty == null || HorizonContentProviderHelper.getPartyLocalMute(this.mContext) != 1) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$fetchSpeakerStatuses$3$SocialViewModel(final String str, final ISpeakerIndicatorListener iSpeakerIndicatorListener) {
        HorizonContentProviderHelper.getHasParticipantRecentlySpokenDEPRECATED(this.mContext, str, new HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass7 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback
            public void onSuccess(boolean z) {
                iSpeakerIndicatorListener.updateIsSpeaking(z);
            }
        });
    }

    public void notifyPartyCreated() {
        if (this.mPanelApp.isSocialPlatformApp()) {
            this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_CREATED));
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long j;
        long j2 = inputFrame.mTimeMillis;
        if (j2 - this.mLastSystemUpdateTimeMillis > 5000) {
            this.mLastSystemUpdateTimeMillis = j2;
            updateInternetConnection();
        }
        long j3 = 45000;
        if (this.mTabletActive) {
            j = 11000;
        } else {
            j = 45000;
        }
        if (j2 - this.mHighPriLastServerUpdateTimeMillis > j) {
            this.mHighPriLastServerUpdateTimeMillis = j2;
            refreshHiPriData();
        }
        long j4 = j2 - this.mLowPriLastServerUpdateTimeMillis;
        if (this.mTabletActive) {
            j3 = 21000;
        }
        if (j4 > j3) {
            this.mLowPriLastServerUpdateTimeMillis = j2;
            refreshLowPriData();
        }
        if (j2 - this.mPartyLastServerUpdateTimeMillis > 4900) {
            this.mPartyLastServerUpdateTimeMillis = (j2 / 4900) * 4900;
            refreshPartyData();
        }
        if (j2 - this.mLastSpeakingIndicatorUpdateTimeMillis > 500 && this.mPartySpeakingIndicatorListeners != null && this.mTabletActive) {
            this.mLastSpeakingIndicatorUpdateTimeMillis = j2;
            fetchSpeakerStatuses();
        }
        if (!this.mEnoughDataFetched && getEnoughDataFetched()) {
            this.mEnoughDataFetched = true;
            updateObserversOnEnoughDataFetched();
        }
    }

    public void refreshHiPriData() {
        if (this.mHasInternetConnection) {
            loadFriends();
        }
    }

    public void refreshLowPriData() {
        if (this.mHasInternetConnection) {
            if (!this.mObservers.isEmpty()) {
                loadLinkedAccountsData();
                loadPYMKs();
                loadJoinableParties();
            } else {
                if (!this.mEnoughFBLinkDataFetched) {
                    loadLinkedAccountsData();
                }
                if (this.mEnoughSuggestedActionDataFetched) {
                    return;
                }
            }
            loadSocialViewerInfo();
        }
    }

    public void refreshPartyData() {
        if (this.mHasInternetConnection) {
            loadPartyData();
        }
    }

    public void registerObserver(SocialDataObserver socialDataObserver) {
        if (!this.mObservers.contains(socialDataObserver)) {
            this.mObservers.add(socialDataObserver);
        }
    }

    public void registerPartyObserver(SocialPartyObserver socialPartyObserver) {
        if (!this.mPartyObservers.contains(socialPartyObserver)) {
            this.mPartyObservers.add(socialPartyObserver);
        }
    }

    public void removeMenu() {
        SocialPopupMenu socialPopupMenu = this.mOpenMenu;
        if (socialPopupMenu != null) {
            socialPopupMenu.dismissPopup();
        }
    }

    public void removeObserver(SocialDataObserver socialDataObserver) {
        if (this.mObservers.contains(socialDataObserver)) {
            this.mObservers.remove(socialDataObserver);
        }
    }

    public void removePartyInviteOptimisticAndRefetch(String str) {
        this.mJoinableParties.removeIf(new Predicate(str) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$RcWR9E7cMx_wgRC9Krokjgheakc2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialParty) obj).mID.equals(this.f$0);
            }
        });
        alertListObservers();
        loadPartyData();
    }

    public void removePartyObserver(SocialPartyObserver socialPartyObserver) {
        if (this.mPartyObservers.contains(socialPartyObserver)) {
            this.mPartyObservers.remove(socialPartyObserver);
        }
    }

    public void removePartyOptimistically() {
        if (this.mParty != null) {
            this.mIgnoreNextPartyUpdate = true;
            setParty(null);
        }
        if (this.mPanelApp.isSocialPlatformApp()) {
            this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY));
        }
    }

    public void setTabletActive(boolean z) {
        this.mTabletActive = z;
        if (this.mPanelApp.isSocialPlatformApp()) {
            Intent intent = new Intent(BROADCAST_ACTION_TABLET_ACTIVE);
            intent.putExtra("active", z);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void startSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        Map map = this.mPartySpeakingIndicatorListeners;
        if (map == null) {
            map = new HashMap();
            this.mPartySpeakingIndicatorListeners = map;
        }
        map.put(str, iSpeakerIndicatorListener);
    }

    public void stopFetchingSpeakingIndicators() {
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null) {
            map.clear();
        }
    }

    public void stopSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null && map.containsKey(str)) {
            this.mPartySpeakingIndicatorListeners.remove(str);
        }
    }

    public SocialViewModel(Context context, SocialPanelApp socialPanelApp) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mConnection = (ConnectivityManager) context.getSystemService("connectivity");
        this.mIgnoreNextPartyUpdate = false;
        this.mHasInternetConnection = true;
        this.mPartyMicrophoneInputLocation = PartyMicrophoneInputLocation.PARTY;
        this.mPartyVolume = 1.0f;
        this.mPanelApp = socialPanelApp;
        setTabletActive(false);
        if (!this.mPanelApp.isSocialPlatformApp()) {
            this.mSocialPlatformBroadcastReceiver = new SocialPlatformBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter(PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY);
            intentFilter.addAction(PartiesBroadcastActions.ACTION_PARTY_CREATED);
            intentFilter.addAction(PartiesBroadcastActions.ACTION_PARTY_JOINED);
            intentFilter.addAction(BROADCAST_ACTION_TABLET_ACTIVE);
            this.mContext.registerReceiver(this.mSocialPlatformBroadcastReceiver, intentFilter);
        }
    }

    private void fetchPartyMicrophoneInputLocation() {
        clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
        this.mFetchPartyMicrophoneInputLocationHandle = HorizonContentProviderHelper.getPartyMicrophoneInputLocation(this.mContext, new HorizonContentProviderHelper.GetPartyMicrophoneInputLocationCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass8 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(SocialViewModel.TAG, "Failed to get party microphone input location");
                SocialViewModel.this.clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.GetPartyMicrophoneInputLocationCallback
            public void onSuccess(PartyMicrophoneInputLocation partyMicrophoneInputLocation) {
                SocialViewModel socialViewModel = SocialViewModel.this;
                socialViewModel.mPartyMicrophoneInputLocation = partyMicrophoneInputLocation;
                socialViewModel.clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
            }
        });
    }

    private void loadJoinableParties() {
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchJoinablePartiesAsyncQueryHandle();
        this.mFetchJoinablePartiesAsyncQueryHandle = HorizonContentProviderHelper.fetchJoinableParties(this.mContext, new HorizonContentProviderHelper.FetchJoinablePartiesCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_joinable_parties", SocialViewModel.TAG, AnonymousClass006.A07("Failed to fetch joinable parties, latency: ", AnonymousClass006.A00(System.currentTimeMillis() - currentTimeMillis, "ms")));
                SocialViewModel.this.clearFetchJoinablePartiesAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchJoinablePartiesCallback
            public void onSuccess(List<SocialParty> list) {
                System.currentTimeMillis();
                list.size();
                SocialViewModel.this.setJoinableParties(list);
                SocialViewModel.this.clearFetchJoinablePartiesAsyncQueryHandle();
            }
        });
    }

    private void loadSocialViewerInfo() {
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchSocialViewerAsyncQueryHandle();
        this.mFetchSocialViewerAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialViewer(this.mContext, new HorizonContentProviderHelper.FetchSocialViewerInfoCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass6 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_social_viewer_info", SocialViewModel.TAG, AnonymousClass006.A07("Failed to fetch social viewer info, latency: ", AnonymousClass006.A00(System.currentTimeMillis() - currentTimeMillis, "ms")));
                SocialViewModel.this.clearFetchSocialViewerAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialViewerInfoCallback
            public void onSuccess(SocialViewerInfo socialViewerInfo) {
                System.currentTimeMillis();
                SocialViewModel socialViewModel = SocialViewModel.this;
                socialViewModel.mSocialViewerInfo = socialViewerInfo;
                if (!socialViewModel.mEnoughSuggestedActionDataFetched) {
                    socialViewModel.mEnoughSuggestedActionDataFetched = true;
                    socialViewModel.updateFriendsListForObservers();
                }
                SocialViewModel.this.clearFetchSocialViewerAsyncQueryHandle();
                SocialViewModel.this.updateSocialViewerForObservers();
            }
        });
    }

    public boolean hasAvatarPhoto() {
        return !getAvatarPhotoUrl().isEmpty();
    }

    public void loadPartyData() {
        clearFetchCurrentPartyAsyncQueryHandle();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyDEPRECATED(this.mContext, new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                if (socialParty != null) {
                    SocialViewModel socialViewModel = SocialViewModel.this;
                    if (socialViewModel.mIgnoreNextPartyUpdate) {
                        socialViewModel.mIgnoreNextPartyUpdate = false;
                        return;
                    }
                }
                SocialViewModel socialViewModel2 = SocialViewModel.this;
                socialViewModel2.mIgnoreNextPartyUpdate = false;
                socialViewModel2.setParty(socialParty);
                SocialViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mContext, "load_party_data", SocialViewModel.TAG, "Failed to fetch current party data");
                SocialViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
            }
        });
        fetchPartyMicrophoneInputLocation();
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeUserOptimisticAndRefetch(java.lang.String r3, com.oculus.panelapp.social.SocialViewModel.SocialList r4) {
        /*
            r2 = this;
            int r0 = r4.ordinal()
            switch(r0) {
                case 1: goto L_0x0011;
                case 2: goto L_0x0014;
                default: goto L_0x0007;
            }
        L_0x0007:
            r2.alertListObservers()
            r2.loadFriends()
            r2.loadPYMKs()
            return
        L_0x0011:
            java.util.List<com.oculus.horizoncontent.social.SocialUser> r1 = r2.mFriends
            goto L_0x0016
        L_0x0014:
            java.util.ArrayList<com.oculus.horizoncontent.social.SocialUser> r1 = r2.mPYMKs
        L_0x0016:
            if (r1 == 0) goto L_0x0007
            com.oculus.panelapp.social.-$$Lambda$SocialViewModel$fJfZA8MpgoYMVClBwuDh1brCp9M2 r0 = new com.oculus.panelapp.social.-$$Lambda$SocialViewModel$fJfZA8MpgoYMVClBwuDh1brCp9M2
            r0.<init>(r3)
            r1.removeIf(r0)
            goto L_0x0007
            switch-data {1->0x0011, 2->0x0014, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialViewModel.removeUserOptimisticAndRefetch(java.lang.String, com.oculus.panelapp.social.SocialViewModel$SocialList):void");
    }

    public void setOpenMenu(SocialPopupMenu socialPopupMenu) {
        removeMenu();
        this.mOpenMenu = socialPopupMenu;
    }

    public void setPartyMicrophoneInputLocation(PartyMicrophoneInputLocation partyMicrophoneInputLocation, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        switch (partyMicrophoneInputLocation.ordinal()) {
            case 0:
                HorizonContentProviderHelper.sendMicrophoneInputToApp(this.mContext, singleIDCallback);
                return;
            case 1:
                HorizonContentProviderHelper.sendMicrophoneInputToParty(this.mContext, singleIDCallback);
                return;
            default:
                Log.e(TAG, "Invalid party microphone input location");
                return;
        }
    }

    public void setPartyVolume(float f) {
        this.mPartyVolume = f;
    }
}
