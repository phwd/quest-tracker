package com.oculus.panelapp.social;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.util.Log;
import androidx.databinding.BaseObservable;
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

public class SocialViewModel extends BaseObservable implements AndroidPanelApp.PanelFrameCallback, ViewModelLifecycle {
    public static final String BROADCAST_ACTION_TABLET_ACTIVE = "com.oculus.socialplatform.broadcast.TABLET_ACTIVE";
    public static final String BROADCAST_ACTION_TABLET_ACTIVE_EXTRA = "active";
    private static final long PARTY_RESET_PERIOD_MILLIS = 21600000;
    private static final String PARTY_START_TIME_SHARED_PREFERENCES_KEY = "PARTY_START_TIME_SHARED_PREFERENCES_KEY";
    public static final long SOCIAL_ACTIVE_HI_PRI_DATA_UPDATE_RATE_MILLIS = 11000;
    public static final long SOCIAL_ACTIVE_LOW_PRI_DATA_UPDATE_RATE_MILLIS = 21000;
    public static final long SOCIAL_ACTIVE_PARTY_DATA_UPDATE_RATE_MILLIS = 4900;
    public static final long SOCIAL_INACTIVE_DATA_UPDATE_RATE_MILLIS = 45000;
    public static final long SOCIAL_PARTY_SPEAKING_INDICATOR_RATE_MILLIS = 500;
    public static final long SOCIAL_SYSTEM_DATA_UPDATE_RATE_MILLIS = 5000;
    private static final String TAG = LoggingUtil.tag(SocialViewModel.class);
    private ConnectivityManager mConnection;
    private Context mContext;
    private boolean mEnoughDataFetched;
    private boolean mEnoughFBLinkDataFetched;
    private boolean mEnoughFriendDataFetched;
    private boolean mEnoughSuggestedActionDataFetched;
    @Nullable
    private AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchJoinablePartiesAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchPYMKsAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchPartyMicrophoneInputLocationHandle;
    @Nullable
    private AsyncQueryHandle mFetchSocialFriendsAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mFetchSocialViewerAsyncQueryHandle;
    private List<SocialUser> mFriends = new ArrayList();
    private boolean mHasInternetConnection;
    private long mHighPriLastServerUpdateTimeMillis;
    private boolean mIgnoreNextPartyUpdate;
    private List<SocialParty> mJoinableParties = new ArrayList();
    private long mLastSpeakingIndicatorUpdateTimeMillis;
    private long mLastSystemUpdateTimeMillis;
    private LinkedAccountsInfo mLinkedAccountsInfo;
    private long mLowPriLastServerUpdateTimeMillis;
    private ArrayList<SocialDataObserver> mObservers = new ArrayList<>();
    private SocialPopupMenu mOpenMenu;
    private ArrayList<SocialUser> mPYMKs = new ArrayList<>();
    private SocialPanelApp mPanelApp;
    private SocialParty mParty;
    private long mPartyLastServerUpdateTimeMillis;
    private PartyMicrophoneInputLocation mPartyMicrophoneInputLocation;
    private ArrayList<SocialPartyObserver> mPartyObservers = new ArrayList<>();
    private Map<String, ISpeakerIndicatorListener> mPartySpeakingIndicatorListeners;
    private final Resources mResources;
    private final SharedPreferences mSharedPreferences;
    private SocialPlatformBroadcastReceiver mSocialPlatformBroadcastReceiver;
    private SocialViewerInfo mSocialViewerInfo;
    private boolean mTabletActive;

    public enum SocialList {
        REQUESTS,
        FRIENDS,
        PYMK
    }

    public boolean isPartyMuted() {
        if (getPartyData() == null || HorizonContentProviderHelper.getPartyLocalMute(this.mContext) != 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public class SocialPlatformBroadcastReceiver extends BroadcastReceiver {
        private SocialPlatformBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY.equals(action)) {
                SocialViewModel.this.removePartyOptimistically();
            } else if (PartiesBroadcastActions.ACTION_PARTY_CREATED.equals(action) || PartiesBroadcastActions.ACTION_PARTY_JOINED.equals(action)) {
                SocialViewModel.this.loadPartyData();
            } else if (SocialViewModel.BROADCAST_ACTION_TABLET_ACTIVE.equals(action)) {
                SocialViewModel.this.setTabletActive(intent.getBooleanExtra(SocialViewModel.BROADCAST_ACTION_TABLET_ACTIVE_EXTRA, false));
            }
        }
    }

    public SocialViewModel(Context context, SocialPanelApp socialPanelApp) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mResources = context.getResources();
        this.mConnection = (ConnectivityManager) context.getSystemService("connectivity");
        this.mIgnoreNextPartyUpdate = false;
        this.mHasInternetConnection = true;
        this.mPartyMicrophoneInputLocation = PartyMicrophoneInputLocation.PARTY;
        this.mPanelApp = socialPanelApp;
        this.mSharedPreferences = context.getSharedPreferences(PARTY_START_TIME_SHARED_PREFERENCES_KEY, 0);
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

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
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

    public void removeUserOptimisticAndRefetch(String str, SocialList socialList) {
        List list;
        String str2 = TAG;
        Log.d(str2, "removeUserOptimisticAndRefetch() - userId: " + str);
        int i = AnonymousClass9.$SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList[socialList.ordinal()];
        if (i == 1) {
            list = this.mFriends;
        } else if (i != 2) {
            list = null;
        } else {
            list = this.mPYMKs;
        }
        if (list != null) {
            list.removeIf(new Predicate(str) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$wZZJvl3ESTtrlhdCDEemxN8ris */
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((SocialUser) obj).getID().equals(this.f$0);
                }
            });
        }
        alertListObservers();
        loadFriends();
        loadPYMKs();
    }

    private void alertListObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().updateFriendsList(this.mFriends, this.mPYMKs, this.mJoinableParties, this.mParty);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        if (timeMillis - this.mLastSystemUpdateTimeMillis > 5000) {
            this.mLastSystemUpdateTimeMillis = timeMillis;
            updateInternetConnection();
        }
        boolean z = this.mTabletActive;
        long j = SOCIAL_INACTIVE_DATA_UPDATE_RATE_MILLIS;
        if (timeMillis - this.mHighPriLastServerUpdateTimeMillis > (z ? SOCIAL_ACTIVE_HI_PRI_DATA_UPDATE_RATE_MILLIS : 45000)) {
            this.mHighPriLastServerUpdateTimeMillis = timeMillis;
            refreshHiPriData();
        }
        long j2 = timeMillis - this.mLowPriLastServerUpdateTimeMillis;
        if (this.mTabletActive) {
            j = SOCIAL_ACTIVE_LOW_PRI_DATA_UPDATE_RATE_MILLIS;
        }
        if (j2 > j) {
            this.mLowPriLastServerUpdateTimeMillis = timeMillis;
            refreshLowPriData();
        }
        if (timeMillis - this.mPartyLastServerUpdateTimeMillis > SOCIAL_ACTIVE_PARTY_DATA_UPDATE_RATE_MILLIS) {
            this.mPartyLastServerUpdateTimeMillis = (timeMillis / SOCIAL_ACTIVE_PARTY_DATA_UPDATE_RATE_MILLIS) * SOCIAL_ACTIVE_PARTY_DATA_UPDATE_RATE_MILLIS;
            refreshPartyData();
        }
        if (timeMillis - this.mLastSpeakingIndicatorUpdateTimeMillis > 500 && this.mPartySpeakingIndicatorListeners != null && this.mTabletActive) {
            this.mLastSpeakingIndicatorUpdateTimeMillis = timeMillis;
            fetchSpeakerStatuses();
        }
        if (!this.mEnoughDataFetched && getEnoughDataFetched()) {
            this.mEnoughDataFetched = true;
            updateObserversOnEnoughDataFetched();
        }
    }

    public void updateActiveCallBar() {
        notifyPropertyChanged(BR.barLowerSectionVisible);
        notifyPropertyChanged(BR.activeCallBarSimpleVisible);
        notifyPropertyChanged(BR.activeCallBarFullVisible);
        notifyPropertyChanged(BR.activeCallBarTitle);
        notifyPropertyChanged(BR.muteMicrophoneButtonIcon);
        notifyPropertyChanged(BR.activeCallButtonTitle);
    }

    public long getActivePartyDurationMilliseconds() {
        long j = this.mSharedPreferences.getLong(PARTY_START_TIME_SHARED_PREFERENCES_KEY, -1);
        if (j == -1) {
            return -1;
        }
        return System.currentTimeMillis() - j;
    }

    public void setTabletActive(boolean z) {
        String str = TAG;
        Log.d(str, "setTabletActive() - isActive: " + z);
        this.mTabletActive = z;
        if (this.mPanelApp.isSocialPlatformApp()) {
            Intent intent = new Intent(BROADCAST_ACTION_TABLET_ACTIVE);
            intent.putExtra(BROADCAST_ACTION_TABLET_ACTIVE_EXTRA, z);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void refreshHiPriData() {
        Log.d(TAG, "refreshHiPriData()");
        if (this.mHasInternetConnection) {
            loadFriends();
        }
    }

    public void refreshPartyData() {
        Log.d(TAG, "refresPartyData()");
        if (this.mHasInternetConnection) {
            loadPartyData();
        }
    }

    public void refreshLowPriData() {
        Log.d(TAG, "refreshLowPriData()");
        if (this.mHasInternetConnection) {
            if (!this.mObservers.isEmpty()) {
                loadLinkedAccountsData();
                loadPYMKs();
                loadJoinableParties();
                loadSocialViewerInfo();
                return;
            }
            if (!this.mEnoughFBLinkDataFetched) {
                loadLinkedAccountsData();
            }
            if (!this.mEnoughSuggestedActionDataFetched) {
                loadSocialViewerInfo();
            }
        }
    }

    public List<SocialUser> getFriends() {
        return this.mFriends;
    }

    public ArrayList<SocialUser> getPYMKs() {
        return this.mPYMKs;
    }

    @Nullable
    public SocialParty getPartyData() {
        return this.mParty;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJoinableParties(List<SocialParty> list) {
        if (this.mJoinableParties.equals(list)) {
            Log.d(TAG, "setJoinableParties(), no change");
            return;
        }
        String str = TAG;
        Log.d(str, "setJoinableParties() - count: " + list.size());
        this.mJoinableParties = list;
        updateFriendsListForObservers();
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    public boolean hasInternetConnection() {
        return this.mHasInternetConnection;
    }

    private void loadLinkedAccountsData() {
        HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(this.mContext, new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
            public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                SocialViewModel.this.mLinkedAccountsInfo = linkedAccountsInfo;
                if (!SocialViewModel.this.mEnoughFBLinkDataFetched) {
                    SocialViewModel.this.mEnoughFBLinkDataFetched = true;
                    SocialViewModel.this.updateFriendsListForObservers();
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mPanelApp, "load_linked_accounts_data", SocialViewModel.TAG, "Failed to fetch current linked accounts data");
            }
        });
    }

    public void loadPartyData() {
        clearFetchCurrentPartyAsyncQueryHandle();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyDEPRECATED(this.mContext, new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                if (socialParty == null || !SocialViewModel.this.mIgnoreNextPartyUpdate) {
                    if (SocialViewModel.this.mParty == null && socialParty != null) {
                        long j = SocialViewModel.this.mSharedPreferences.getLong(SocialViewModel.PARTY_START_TIME_SHARED_PREFERENCES_KEY, -1);
                        long currentTimeMillis = System.currentTimeMillis();
                        if (j == -1 || currentTimeMillis - j > SocialViewModel.PARTY_RESET_PERIOD_MILLIS) {
                            SocialViewModel.this.mSharedPreferences.edit().putLong(SocialViewModel.PARTY_START_TIME_SHARED_PREFERENCES_KEY, currentTimeMillis).apply();
                        }
                    } else if (socialParty == null && SocialViewModel.this.mParty != null) {
                        SocialViewModel.this.mSharedPreferences.edit().remove(SocialViewModel.PARTY_START_TIME_SHARED_PREFERENCES_KEY).apply();
                    }
                    SocialViewModel.this.mIgnoreNextPartyUpdate = false;
                    String str = SocialViewModel.TAG;
                    Object[] objArr = new Object[1];
                    objArr[0] = socialParty == null ? com.facebook.debug.log.LoggingUtil.NO_HASHCODE : socialParty;
                    Log.d(str, String.format("Fetched party: %s ", objArr));
                    SocialViewModel.this.setParty(socialParty);
                    SocialViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
                    return;
                }
                SocialViewModel.this.mIgnoreNextPartyUpdate = false;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialViewModel.this.mPanelApp, "load_party_data", SocialViewModel.TAG, "Failed to fetch current party data");
                SocialViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
            }
        });
        fetchPartyMicrophoneInputLocation();
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

    private void loadFriends() {
        Log.d(TAG, "loadFriends()");
        if (DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER)) {
            this.mEnoughFriendDataFetched = true;
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchSocialFriendsAsyncQueryHandle();
        this.mFetchSocialFriendsAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialFriends(this.mContext, null, null, new HorizonContentProviderHelper.FetchSocialFriendsCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onSuccess(List<SocialUser> list) {
                String str = SocialViewModel.TAG;
                Log.d(str, String.format("loadFriends() - Fetched friends, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms") + " count:" + list.size(), new Object[0]));
                SocialViewModel.this.setFriends(list);
                SocialViewModel.this.clearFetchSocialFriendsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onError(Exception exc) {
                SocialPanelApp socialPanelApp = SocialViewModel.this.mPanelApp;
                String str = SocialViewModel.TAG;
                SocialLogger.logError(socialPanelApp, "load_friend", str, "Failed to fetch Friends, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                SocialViewModel.this.clearFetchSocialFriendsAsyncQueryHandle();
            }
        });
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

    private void loadPYMKs() {
        Log.d(TAG, "loadPYMKs()");
        if (DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER)) {
            this.mEnoughFriendDataFetched = true;
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchPYMKsAsyncQueryHandle();
        this.mFetchPYMKsAsyncQueryHandle = HorizonContentProviderHelper.fetchPYMKs(this.mContext, new HorizonContentProviderHelper.FetchPYMKsCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPYMKsCallback
            public void onSuccess(ArrayList<SocialUser> arrayList) {
                String str = SocialViewModel.TAG;
                Log.d(str, "loadPYMKs() - Fetched PYMKs, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms") + " count:" + arrayList.size());
                SocialViewModel.this.setPYMKs(arrayList);
                SocialViewModel.this.clearFetchPYMKsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPanelApp socialPanelApp = SocialViewModel.this.mPanelApp;
                String str = SocialViewModel.TAG;
                SocialLogger.logError(socialPanelApp, "load_pymk", str, "Failed to fetch PYMKs, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                SocialViewModel.this.clearFetchPYMKsAsyncQueryHandle();
            }
        });
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

    private void loadJoinableParties() {
        Log.d(TAG, "loadJoinableParties()");
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchJoinablePartiesAsyncQueryHandle();
        this.mFetchJoinablePartiesAsyncQueryHandle = HorizonContentProviderHelper.fetchJoinableParties(this.mContext, new HorizonContentProviderHelper.FetchJoinablePartiesCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchJoinablePartiesCallback
            public void onSuccess(List<SocialParty> list) {
                String str = SocialViewModel.TAG;
                Log.d(str, "loadJoinableParties() - Fetched joinable parties, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms") + " count:" + list.size());
                SocialViewModel.this.setJoinableParties(list);
                SocialViewModel.this.clearFetchJoinablePartiesAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPanelApp socialPanelApp = SocialViewModel.this.mPanelApp;
                String str = SocialViewModel.TAG;
                SocialLogger.logError(socialPanelApp, "load_joinable_parties", str, "Failed to fetch joinable parties, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                SocialViewModel.this.clearFetchJoinablePartiesAsyncQueryHandle();
            }
        });
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

    private void loadSocialViewerInfo() {
        Log.d(TAG, "loadSocialViewerInfo()");
        final long currentTimeMillis = System.currentTimeMillis();
        clearFetchSocialViewerAsyncQueryHandle();
        this.mFetchSocialViewerAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialViewer(this.mContext, new HorizonContentProviderHelper.FetchSocialViewerInfoCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass6 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialViewerInfoCallback
            public void onSuccess(SocialViewerInfo socialViewerInfo) {
                String str = SocialViewModel.TAG;
                Log.d(str, "loadSocialViewerInfo() - Fetched social viewer info, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                SocialViewModel.this.mSocialViewerInfo = socialViewerInfo;
                if (!SocialViewModel.this.mEnoughSuggestedActionDataFetched) {
                    SocialViewModel.this.mEnoughSuggestedActionDataFetched = true;
                    SocialViewModel.this.updateFriendsListForObservers();
                }
                SocialViewModel.this.clearFetchSocialViewerAsyncQueryHandle();
                SocialViewModel.this.updateSocialViewerForObservers();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPanelApp socialPanelApp = SocialViewModel.this.mPanelApp;
                String str = SocialViewModel.TAG;
                SocialLogger.logError(socialPanelApp, "load_social_viewer_info", str, "Failed to fetch social viewer info, latency: " + ((System.currentTimeMillis() - currentTimeMillis) + "ms"));
                SocialViewModel.this.clearFetchSocialViewerAsyncQueryHandle();
            }
        });
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

    private void updateInternetConnection() {
        boolean z = this.mConnection.getActiveNetwork() != null;
        if (z != this.mHasInternetConnection) {
            this.mHasInternetConnection = z;
            if (this.mHasInternetConnection) {
                Log.d(TAG, "updateInternetConnection() - internet connected");
                refreshHiPriData();
                refreshPartyData();
                refreshLowPriData();
            }
            if (!this.mHasInternetConnection && this.mParty != null) {
                Log.d(TAG, "updateInternetConnection() - internet disconnected");
                setParty(null);
            }
        }
    }

    public void removePartyOptimistically() {
        if (this.mParty != null) {
            this.mIgnoreNextPartyUpdate = true;
            setParty(null);
            this.mSharedPreferences.edit().remove(PARTY_START_TIME_SHARED_PREFERENCES_KEY).apply();
            updateActiveCallBar();
        }
        if (this.mPanelApp.isSocialPlatformApp()) {
            this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY));
        }
    }

    public void notifyPartyCreated() {
        if (this.mPanelApp.isSocialPlatformApp()) {
            this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_CREATED));
        }
    }

    public void ignoreNextPartyUpdate() {
        this.mIgnoreNextPartyUpdate = true;
    }

    public void removePartyInviteOptimisticAndRefetch(String str) {
        this.mJoinableParties.removeIf(new Predicate(str) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$k9OiaiWtxyYbdFqFQm2jFu6vN1U */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialParty) obj).getID().equals(this.f$0);
            }
        });
        alertListObservers();
        loadPartyData();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParty(SocialParty socialParty) {
        if (socialParty == null || socialParty.getID() == null || socialParty.getID().isEmpty()) {
            socialParty = null;
        }
        if (Objects.equals(this.mParty, socialParty)) {
            Log.d(TAG, "setParty() - no change");
            return;
        }
        this.mParty = socialParty;
        Iterator<SocialPartyObserver> it = this.mPartyObservers.iterator();
        while (it.hasNext()) {
            it.next().onUpdateParty(this.mParty);
        }
        alertListObservers();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPYMKs(ArrayList<SocialUser> arrayList) {
        if (this.mPYMKs.equals(arrayList)) {
            Log.d(TAG, "setPYMKs(), no change");
            return;
        }
        String str = TAG;
        Log.d(str, "setPYMKs(), count: " + arrayList.size());
        this.mPYMKs = arrayList;
        this.mEnoughFriendDataFetched = true;
        updateFriendsListForObservers();
    }

    public List<SocialParty> getJoinableParties() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            removeCurrentParty(this.mJoinableParties, socialParty.getID());
        }
        return this.mJoinableParties;
    }

    private static void removeCurrentParty(List<SocialParty> list, String str) {
        list.removeIf(new Predicate(str) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$n8H0w2ZWbj2aJMZD5unr1KyKVM */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialParty) obj).getID().equals(this.f$0);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFriends(List<SocialUser> list) {
        this.mEnoughFriendDataFetched = true;
        if (this.mFriends.equals(list)) {
            Log.d(TAG, "setFriends(), no change");
            return;
        }
        String str = TAG;
        Log.d(str, "setFriends(), count: " + list.size());
        this.mFriends = list;
        updateFriendsListForObservers();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateSocialViewerForObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            SocialDataObserver next = it.next();
            String str = TAG;
            Log.d(str, "updateSocialViewerForObservers(): " + next.getClass().getSimpleName());
            next.updateSocialViewer();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateFriendsListForObservers() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            SocialDataObserver next = it.next();
            String str = TAG;
            Log.d(str, "updateFriendsListForObservers(): " + next.getClass().getSimpleName());
            next.updateFriendsList(getFriends(), getPYMKs(), getJoinableParties(), getPartyData());
        }
    }

    private void updateObserversOnEnoughDataFetched() {
        Iterator<SocialDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            SocialDataObserver next = it.next();
            String str = TAG;
            Log.d(str, "updateObserversOnEnoughDataFetched(): " + next.getClass().getSimpleName());
            next.onEnoughDataFetched();
        }
    }

    public void registerPartyObserver(SocialPartyObserver socialPartyObserver) {
        String str = TAG;
        Log.d(str, "registerPartyObserver(): " + socialPartyObserver.getClass().getSimpleName());
        if (!this.mPartyObservers.contains(socialPartyObserver)) {
            this.mPartyObservers.add(socialPartyObserver);
        }
    }

    public void removePartyObserver(SocialPartyObserver socialPartyObserver) {
        String str = TAG;
        Log.d(str, "removePartyObserver(): " + socialPartyObserver.getClass().getSimpleName());
        if (this.mPartyObservers.contains(socialPartyObserver)) {
            this.mPartyObservers.remove(socialPartyObserver);
        }
    }

    public void registerObserver(SocialDataObserver socialDataObserver) {
        String str = TAG;
        Log.d(str, "registerObserver(): " + socialDataObserver.getClass().getSimpleName());
        if (!this.mObservers.contains(socialDataObserver)) {
            this.mObservers.add(socialDataObserver);
        }
    }

    public void removeObserver(SocialDataObserver socialDataObserver) {
        String str = TAG;
        Log.d(str, "removeObserver(): " + socialDataObserver.getClass().getSimpleName());
        if (this.mObservers.contains(socialDataObserver)) {
            this.mObservers.remove(socialDataObserver);
        }
    }

    public boolean getEnoughDataFetched() {
        return this.mEnoughFriendDataFetched && this.mEnoughSuggestedActionDataFetched && this.mEnoughFBLinkDataFetched;
    }

    public void removeMenu() {
        SocialPopupMenu socialPopupMenu = this.mOpenMenu;
        if (socialPopupMenu != null) {
            socialPopupMenu.dismissPopup();
        }
    }

    public void setOpenMenu(SocialPopupMenu socialPopupMenu) {
        removeMenu();
        this.mOpenMenu = socialPopupMenu;
    }

    @Nullable
    public SocialPopupMenu getOpenMenu() {
        return this.mOpenMenu;
    }

    public void startSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        if (this.mPartySpeakingIndicatorListeners == null) {
            this.mPartySpeakingIndicatorListeners = new HashMap();
        }
        String str2 = TAG;
        Log.i(str2, "Start speaking indicator subscription for userID: " + str);
        this.mPartySpeakingIndicatorListeners.put(str, iSpeakerIndicatorListener);
    }

    public void stopSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null && map.containsKey(str)) {
            String str2 = TAG;
            Log.i(str2, "Stop speaking indicator subscription for userID: " + str);
            this.mPartySpeakingIndicatorListeners.remove(str);
        }
    }

    public void stopFetchingSpeakingIndicators() {
        Log.i(TAG, "Stopping speaking indicator subscriptions for all party members.");
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null) {
            map.clear();
        }
    }

    public void fetchSpeakerStatuses() {
        try {
            this.mPartySpeakingIndicatorListeners.forEach(new BiConsumer() {
                /* class com.oculus.panelapp.social.$$Lambda$SocialViewModel$9kfiLxuLOcVhfbMtxnw2hiCPJaU */

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SocialViewModel.this.lambda$fetchSpeakerStatuses$5$SocialViewModel((String) obj, (ISpeakerIndicatorListener) obj2);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception when fetching user speaker statuses.", e);
        }
    }

    public /* synthetic */ void lambda$fetchSpeakerStatuses$5$SocialViewModel(final String str, final ISpeakerIndicatorListener iSpeakerIndicatorListener) {
        HorizonContentProviderHelper.getHasParticipantRecentlySpokenDEPRECATED(this.mContext, str, new HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass7 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback
            public void onSuccess(boolean z) {
                iSpeakerIndicatorListener.updateIsSpeaking(z);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                String str = SocialViewModel.TAG;
                Log.i(str, "Unable to fetch the speaking status for user ID: " + str);
            }
        });
    }

    private void fetchPartyMicrophoneInputLocation() {
        clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
        this.mFetchPartyMicrophoneInputLocationHandle = HorizonContentProviderHelper.getPartyMicrophoneInputLocation(this.mContext, new HorizonContentProviderHelper.GetPartyMicrophoneInputLocationCallback() {
            /* class com.oculus.panelapp.social.SocialViewModel.AnonymousClass8 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.GetPartyMicrophoneInputLocationCallback
            public void onSuccess(PartyMicrophoneInputLocation partyMicrophoneInputLocation) {
                SocialViewModel.this.mPartyMicrophoneInputLocation = partyMicrophoneInputLocation;
                SocialViewModel.this.clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(SocialViewModel.TAG, "Failed to get party microphone input location");
                SocialViewModel.this.clearFetchPartyMicrophoneInputLocationAsyncQueryHandle();
            }
        });
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

    public PartyMicrophoneInputLocation getPartyMicrophoneInputLocation() {
        return this.mPartyMicrophoneInputLocation;
    }

    /* renamed from: com.oculus.panelapp.social.SocialViewModel$9  reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation = new int[PartyMicrophoneInputLocation.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList = new int[SocialList.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|(2:5|6)|7|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        static {
            /*
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation[] r0 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation = r0
                r0 = 1
                int[] r1 = com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r2 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.APP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r3 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.PARTY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                com.oculus.panelapp.social.SocialViewModel$SocialList[] r2 = com.oculus.panelapp.social.SocialViewModel.SocialList.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList = r2
                int[] r2 = com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.oculus.panelapp.social.SocialViewModel$SocialList r3 = com.oculus.panelapp.social.SocialViewModel.SocialList.FRIENDS     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                int[] r0 = com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.$SwitchMap$com$oculus$panelapp$social$SocialViewModel$SocialList     // Catch:{ NoSuchFieldError -> 0x003c }
                com.oculus.panelapp.social.SocialViewModel$SocialList r2 = com.oculus.panelapp.social.SocialViewModel.SocialList.PYMK     // Catch:{ NoSuchFieldError -> 0x003c }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x003c }
            L_0x003c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialViewModel.AnonymousClass9.<clinit>():void");
        }
    }

    public void setPartyMicrophoneInputLocation(PartyMicrophoneInputLocation partyMicrophoneInputLocation, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        int i = AnonymousClass9.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation[partyMicrophoneInputLocation.ordinal()];
        if (i == 1) {
            HorizonContentProviderHelper.sendMicrophoneInputToApp(this.mContext, singleIDCallback);
        } else if (i != 2) {
            Log.e(TAG, "Invalid party microphone input location");
        } else {
            HorizonContentProviderHelper.sendMicrophoneInputToParty(this.mContext, singleIDCallback);
        }
    }

    @Bindable
    public boolean isActiveCallBarSimpleVisible() {
        return getPartyData() != null && this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR) && !this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR_ENHANCED);
    }

    @Bindable
    public boolean isActiveCallBarFullVisible() {
        return getPartyData() != null && this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR) && this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR_ENHANCED);
    }

    @Bindable
    public boolean isBarLowerSectionVisible() {
        return isActiveCallBarFullVisible() || isActiveCallBarSimpleVisible();
    }

    @Bindable
    public String getActiveCallBarTitle() {
        if (getPartyData() == null || HorizonContentProviderHelper.getPartyLocalMute(this.mContext) != 1) {
            return this.mResources.getString(R.string.anytime_bar_active_call_bar_title);
        }
        return this.mResources.getString(R.string.anytime_bar_active_call_bar_title_mic_muted);
    }

    @Bindable
    public Drawable getMuteMicrophoneButtonIcon() {
        if (isPartyMuted()) {
            return this.mResources.getDrawable(R.drawable.oc_icon_microphone_off_filled_24_d2d2d2, null);
        }
        return this.mResources.getDrawable(R.drawable.oc_icon_microphone_on_filled_24_d2d2d2, null);
    }

    @Bindable
    public boolean getShouldShowSharePartyButton() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.SHARE_PARTY_BUTTON);
    }

    @Bindable
    public boolean getShouldShowActiveCallBarAdvancedControls() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR_BUTTONS);
    }

    @Bindable
    public String getSharePartyButtonText() {
        return this.mResources.getString(R.string.anytime_tablet_social_party_share_party_button);
    }

    @Bindable
    public String getActiveCallButtonTitle() {
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            Log.e(TAG, "Party was null while getting active call bar title");
            return this.mResources.getString(R.string.anytime_bar_active_call_bar_full_title_fallback);
        }
        SocialUser partyLeader = socialParty.getPartyLeader();
        if (partyLeader == null) {
            Log.e(TAG, "Party leader was null while getting active call bar title");
            return this.mResources.getString(R.string.anytime_bar_active_call_bar_full_title_fallback);
        }
        String alias = partyLeader.getAlias();
        int size = this.mParty.getSize();
        return this.mResources.getQuantityString(R.plurals.anytime_bar_active_call_bar_full_title, size, alias, Integer.valueOf(size));
    }

    public String getAlias() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        return socialViewerInfo == null ? "" : socialViewerInfo.getAlias();
    }

    public String getProfilePhotoUrl() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        return socialViewerInfo == null ? "" : socialViewerInfo.getProfilePhotoUrl();
    }

    public String getAvatarPhotoUrl() {
        SocialViewerInfo socialViewerInfo = this.mSocialViewerInfo;
        return socialViewerInfo == null ? "" : socialViewerInfo.getAvatarPhotoUrl();
    }

    public boolean hasAvatarPhoto() {
        return !getAvatarPhotoUrl().isEmpty();
    }
}
