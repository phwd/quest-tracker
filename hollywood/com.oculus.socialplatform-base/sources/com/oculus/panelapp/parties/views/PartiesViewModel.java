package com.oculus.panelapp.parties.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class PartiesViewModel extends AnonymousClass1uc implements AndroidPanelApp.PanelFrameCallback, ViewModelLifecycle {
    public static final long PARTY_DATA_UPDATE_RATE_MILLIS = 4900;
    public static final float SLIDER_THRESHOLD_OFF = 0.05f;
    public static final long SPEAKING_INDICATOR_UPDATE_RATE_MILLIS = 500;
    public static final long SYSTEM_DATA_UPDATE_RATE_MILLIS = 5000;
    public static final String TAG = LoggingUtil.tag(PartiesViewModel.class);
    public ConnectivityManager mConnection;
    public Context mContext;
    @Nullable
    public String mCorrelationId;
    @Nullable
    public AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    public final Stopwatch mFirstDataLoadTimer;
    @Nullable
    public Map<String, AsyncQueryHandle> mGetHasParticipantRecentlySpokenAsyncQueryHandles;
    public boolean mHasInternetConnection;
    public boolean mIgnoreNextPartyUpdate;
    public boolean mIsPartyFooterEnabled = false;
    public long mLastServerUpdateTimeMillis;
    public long mLastSpeakingIndicatorUpdateTimeMillis;
    public long mLastSystemUpdateTimeMillis;
    @Nullable
    public AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    public String mLocalUserId;
    public boolean mMutePartyVolume;
    public ArrayList<PartyDataObserver> mObservers;
    public SocialParty mParty;
    public Map<String, ISpeakerIndicatorListener> mPartySpeakingIndicatorListeners;
    public final Resources mResources;
    public ISelfMuteStateListener mSelfMuteStateListener;
    public boolean mShouldShowPartyLoading;
    public final SocialLogger mSocialLogger;
    @Nullable
    public String mSource;

    private void alertListObservers() {
        Iterator<PartyDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onUpdateParty(this.mParty);
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
    private void clearGetHasParticipantRecentlySpokenAsyncQueryHandle(String str) {
        AsyncQueryHandle asyncQueryHandle;
        Map<String, AsyncQueryHandle> map = this.mGetHasParticipantRecentlySpokenAsyncQueryHandles;
        if (map != null && (asyncQueryHandle = map.get(str)) != null) {
            asyncQueryHandle.destroy();
            this.mGetHasParticipantRecentlySpokenAsyncQueryHandles.remove(str);
        }
    }

    private void clearGetHasParticipantRecentlySpokenAsyncQueryHandles() {
        Map<String, AsyncQueryHandle> map = this.mGetHasParticipantRecentlySpokenAsyncQueryHandles;
        if (map != null) {
            for (AsyncQueryHandle asyncQueryHandle : map.values()) {
                if (asyncQueryHandle != null) {
                    asyncQueryHandle.destroy();
                }
            }
            this.mGetHasParticipantRecentlySpokenAsyncQueryHandles.clear();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeavePartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLeavePartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLeavePartyAsyncQueryHandle = null;
        }
    }

    private void fetchSelfMuteState() {
        if (this.mSelfMuteStateListener != null) {
            this.mSelfMuteStateListener.updateSelfMuteState(HorizonContentProviderHelper.getPartyLocalMute(this.mContext));
        }
    }

    private void refreshPartyData() {
        if (this.mHasInternetConnection) {
            loadPartyData();
        }
    }

    private void stopFetchingSpeakingIndicators() {
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null) {
            map.clear();
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
                refreshPartyData();
            }
            if (!this.mHasInternetConnection && this.mParty != null) {
                setParty(null);
            }
        }
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mObservers.clear();
        clearFetchCurrentPartyAsyncQueryHandle();
        clearLeavePartyAsyncQueryHandle();
        clearGetHasParticipantRecentlySpokenAsyncQueryHandles();
        stopFetchingSpeakingIndicators();
    }

    @Bindable
    public String getDescriptionText() {
        Resources resources;
        int i;
        if (this.mParty == null) {
            resources = this.mResources;
            i = R.string.parties_tablet_body_text_when_there_is_no_active_party;
        } else {
            resources = this.mResources;
            i = R.string.parties_tablet_body_text;
        }
        return resources.getString(i);
    }

    @Bindable
    public boolean getIsPartyActive() {
        if (this.mParty != null) {
            return true;
        }
        return false;
    }

    @Bindable
    public boolean getMutePartyVolume() {
        return this.mMutePartyVolume;
    }

    @Nullable
    public SocialParty getPartyData() {
        return this.mParty;
    }

    @Bindable
    public String getPartySpotsAvailable() {
        int i;
        int size;
        String string;
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            i = 0;
        } else {
            i = socialParty.mMaxPartySize;
        }
        if (socialParty == null) {
            size = 0;
        } else {
            size = socialParty.getSize();
        }
        int i2 = i - size;
        String quantityString = this.mResources.getQuantityString(R.plurals.anytime_tablet_social_party_party_spots_available, i2, Integer.valueOf(i2));
        SocialParty socialParty2 = this.mParty;
        if (socialParty2 == null) {
            string = "";
        } else {
            string = this.mResources.getString(socialParty2.mJoinType.getStringId());
        }
        return this.mContext.getString(R.string.parties_tablet_header_number_of_spots_and_privacy_combined_label, quantityString, string);
    }

    public int getPartyVolume() {
        return (int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f);
    }

    @Bindable
    public boolean getShouldShowPartyFooter() {
        if (this.mParty == null || !this.mIsPartyFooterEnabled) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowPartyLoading() {
        return this.mShouldShowPartyLoading;
    }

    public boolean hasInternetConnection() {
        return this.mHasInternetConnection;
    }

    public /* synthetic */ void lambda$fetchSpeakerStatuses$0$PartiesViewModel(final String str, final ISpeakerIndicatorListener iSpeakerIndicatorListener) {
        this.mGetHasParticipantRecentlySpokenAsyncQueryHandles.put(str, HorizonContentProviderHelper.getHasParticipantRecentlySpoken(this.mContext, str, new HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback() {
            /* class com.oculus.panelapp.parties.views.PartiesViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                PartiesViewModel.this.clearGetHasParticipantRecentlySpokenAsyncQueryHandle(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback
            public void onSuccess(boolean z) {
                iSpeakerIndicatorListener.updateIsSpeaking(z);
                PartiesViewModel.this.clearGetHasParticipantRecentlySpokenAsyncQueryHandle(str);
            }
        }));
    }

    public void leaveParty(final PartiesTabletPanelApp partiesTabletPanelApp, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            String str = socialParty.mID;
            clearLeavePartyAsyncQueryHandle();
            this.mLeavePartyAsyncQueryHandle = PartyUtils.leaveParty(this.mContext, str, clickEventButtonId, surfaceType, partiesTabletPanelApp, new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartiesViewModel.AnonymousClass2 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartiesViewModel.this.clearLeavePartyAsyncQueryHandle();
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartiesViewModel.this.removePartyOptimistically();
                    PartiesViewModel.this.clearLeavePartyAsyncQueryHandle();
                    PartyUtils.navigateToLastOpenedSocialTablet(partiesTabletPanelApp);
                }
            });
        }
    }

    public void loadPartyData() {
        if (this.mShouldShowPartyLoading) {
            notifyPropertyChanged(18);
        }
        clearFetchCurrentPartyAsyncQueryHandle();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyV2(this.mContext, new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.parties.views.PartiesViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                if (socialParty != null) {
                    PartiesViewModel partiesViewModel = PartiesViewModel.this;
                    if (partiesViewModel.mIgnoreNextPartyUpdate) {
                        partiesViewModel.mIgnoreNextPartyUpdate = false;
                        return;
                    }
                }
                PartiesViewModel partiesViewModel2 = PartiesViewModel.this;
                partiesViewModel2.mIgnoreNextPartyUpdate = false;
                partiesViewModel2.setParty(socialParty);
                PartiesViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
                PartiesViewModel partiesViewModel3 = PartiesViewModel.this;
                if (partiesViewModel3.mShouldShowPartyLoading) {
                    partiesViewModel3.mShouldShowPartyLoading = false;
                    partiesViewModel3.notifyPropertyChanged(18);
                }
                Stopwatch stopwatch = PartiesViewModel.this.mFirstDataLoadTimer;
                if (stopwatch.isRunning) {
                    stopwatch.stop();
                    long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    PartiesViewModel partiesViewModel4 = PartiesViewModel.this;
                    partiesViewModel4.mSocialLogger.logImpression(SurfaceType.PARTY_INITIAL_LOAD, partiesViewModel4.buildExtraLogParams(elapsed));
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                PartiesViewModel.this.clearFetchCurrentPartyAsyncQueryHandle();
                PartiesViewModel partiesViewModel = PartiesViewModel.this;
                if (partiesViewModel.mShouldShowPartyLoading) {
                    partiesViewModel.mShouldShowPartyLoading = false;
                    partiesViewModel.notifyPropertyChanged(18);
                }
                Stopwatch stopwatch = PartiesViewModel.this.mFirstDataLoadTimer;
                if (stopwatch.isRunning) {
                    stopwatch.stop();
                    long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    PartiesViewModel partiesViewModel2 = PartiesViewModel.this;
                    partiesViewModel2.mSocialLogger.logImpressionFailure(SurfaceType.PARTY_INITIAL_LOAD, "error loading party data", partiesViewModel2.buildExtraLogParams(elapsed));
                }
            }
        });
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long j = inputFrame.mTimeMillis;
        if (j - this.mLastSystemUpdateTimeMillis > 5000) {
            this.mLastSystemUpdateTimeMillis = j;
            updateInternetConnection();
        }
        if (j - this.mLastServerUpdateTimeMillis > 4900) {
            this.mLastServerUpdateTimeMillis = (j / 4900) * 4900;
            fetchSelfMuteState();
            refreshPartyData();
        }
        if (j - this.mLastSpeakingIndicatorUpdateTimeMillis > 500 && this.mPartySpeakingIndicatorListeners != null) {
            this.mLastSpeakingIndicatorUpdateTimeMillis = j;
            fetchSpeakerStatuses();
        }
    }

    public void registerObserver(PartyDataObserver partyDataObserver) {
        if (!this.mObservers.contains(partyDataObserver)) {
            this.mObservers.add(partyDataObserver);
        }
    }

    public void removeInvitedUserOptimistically(String str) {
        this.mParty.removeInvitedUser(str);
        alertListObservers();
    }

    public void removeObserver(PartyDataObserver partyDataObserver) {
        if (this.mObservers.contains(partyDataObserver)) {
            this.mObservers.remove(partyDataObserver);
        }
    }

    public void removePartyMemberOptimistically(String str) {
        this.mParty.removePartyMember(str);
        alertListObservers();
    }

    public void removePartyOptimistically() {
        if (this.mParty != null) {
            this.mIgnoreNextPartyUpdate = true;
            setParty(null);
        }
    }

    @VisibleForTesting
    public void setParty(SocialParty socialParty) {
        String str;
        if (socialParty == null || (str = socialParty.mID) == null || str.isEmpty()) {
            socialParty = null;
            stopFetchingSpeakingIndicators();
        }
        if (!Objects.equals(this.mParty, socialParty)) {
            this.mParty = socialParty;
            notifyPropertyChanged(32);
            notifyPropertyChanged(28);
            notifyPropertyChanged(6);
            notifyPropertyChanged(24);
            notifyPropertyChanged(13);
            alertListObservers();
        }
    }

    public void setPartyVolume(float f) {
        HorizonContentProviderHelper.setPartyVolume(this.mContext, f);
        boolean z = false;
        if (f < 0.05f) {
            z = true;
        }
        this.mMutePartyVolume = z;
        notifyPropertyChanged(21);
    }

    public void startSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        Map map = this.mPartySpeakingIndicatorListeners;
        if (map == null) {
            map = new HashMap();
            this.mPartySpeakingIndicatorListeners = map;
            this.mGetHasParticipantRecentlySpokenAsyncQueryHandles = new HashMap();
        }
        map.put(str, iSpeakerIndicatorListener);
    }

    public void stopSpeakingIndicatorSubscriptionForUserID(ISpeakerIndicatorListener iSpeakerIndicatorListener, String str) {
        Map<String, ISpeakerIndicatorListener> map = this.mPartySpeakingIndicatorListeners;
        if (map != null && map.containsKey(str)) {
            this.mPartySpeakingIndicatorListeners.remove(str);
        }
    }

    public PartiesViewModel(Context context, SocialLogger socialLogger, String str) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mSocialLogger = socialLogger;
        this.mLocalUserId = str;
        this.mFirstDataLoadTimer = Stopwatch.createStarted();
        this.mObservers = new ArrayList<>();
        this.mConnection = (ConnectivityManager) context.getSystemService("connectivity");
        this.mIgnoreNextPartyUpdate = false;
        this.mHasInternetConnection = true;
        this.mShouldShowPartyLoading = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<String, String> buildExtraLogParams(long j) {
        String str;
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put(LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(j));
        SocialParty socialParty = this.mParty;
        if (!(socialParty == null || (str = socialParty.mID) == null)) {
            A04.put("party_id", str);
        }
        String str2 = this.mCorrelationId;
        if (str2 != null) {
            A04.put("correlation_id", str2);
        }
        String str3 = this.mSource;
        if (str3 != null) {
            A04.put("source", str3);
        }
        return A04.build();
    }

    private void fetchSpeakerStatuses() {
        try {
            clearGetHasParticipantRecentlySpokenAsyncQueryHandles();
            this.mPartySpeakingIndicatorListeners.forEach(new BiConsumer() {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartiesViewModel$992jSrvb54BYLsTs4P2E1OGhRM2 */

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    PartiesViewModel.this.lambda$fetchSpeakerStatuses$0$PartiesViewModel((String) obj, (ISpeakerIndicatorListener) obj2);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception when fetching user speaker statuses.", e);
        }
    }

    @Bindable
    public boolean getShouldShowPartyAccessControls() {
        if (!getIsPartyActive() || !this.mParty.isUserPartyLeader(this.mLocalUserId)) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowPartyDescriptionText() {
        if (getIsPartyActive() || this.mShouldShowPartyLoading) {
            return false;
        }
        return true;
    }

    public void setCorrelationId(String str) {
        this.mCorrelationId = str;
    }

    public void setPartyFooterEnabled(boolean z) {
        this.mIsPartyFooterEnabled = z;
    }

    public void setSource(String str) {
        this.mSource = str;
    }

    public void subscribeToSelfMuteStateUpdates(ISelfMuteStateListener iSelfMuteStateListener) {
        this.mSelfMuteStateListener = iSelfMuteStateListener;
    }
}
