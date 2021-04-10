package com.oculus.panelapp.messenger.fetchers;

import X.AnonymousClass1uc;
import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PartiesFetcher extends AnonymousClass1uc implements AndroidPanelApp.PanelFrameCallback, ViewModelLifecycle {
    public static final long PARTY_DATA_UPDATE_RATE_MILLIS = 4900;
    public static final String TAG = LoggingUtil.tag(PartiesFetcher.class);
    @Nullable
    public Set<String> mAllPartyUsers;
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    public boolean mHasLinkSharing;
    public boolean mIsInParty;
    public long mLastPartyRefreshTime;
    public ArrayList<PartiesDataObserver> mObservers = new ArrayList<>();
    @Nullable
    public String mPartyID;

    public interface PartiesDataObserver {
        void onPartiesDataUpdated();
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

    @Nullable
    private Set<String> getAllUsersAsSet(@Nullable SocialParty socialParty) {
        if (socialParty == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (SocialUser socialUser : socialParty.mPartyMembers) {
            hashSet.add(socialUser.mID);
        }
        for (SocialUser socialUser2 : socialParty.getInvitedUsers()) {
            hashSet.add(socialUser2.mID);
        }
        for (SocialUser socialUser3 : socialParty.getBlockedInvitedUsers()) {
            hashSet.add(socialUser3.mID);
        }
        for (SocialUser socialUser4 : socialParty.getBlockedMembers()) {
            hashSet.add(socialUser4.mID);
        }
        return hashSet;
    }

    private void refreshPartyData() {
        if (this.mFetchCurrentPartyAsyncQueryHandle == null) {
            loadPartyData();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParty(@Nullable SocialParty socialParty) {
        Set<String> set;
        String str;
        if (socialParty == null || (str = socialParty.mID) == null || str.isEmpty()) {
            this.mHasLinkSharing = false;
            this.mIsInParty = false;
            set = null;
            this.mPartyID = null;
        } else {
            this.mHasLinkSharing = socialParty.mHasLinkSharing;
            this.mIsInParty = true;
            this.mPartyID = str;
            set = getAllUsersAsSet(socialParty);
        }
        this.mAllPartyUsers = set;
        Iterator<PartiesDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onPartiesDataUpdated();
        }
    }

    @Nullable
    public Set<String> getAllUsers() {
        return this.mAllPartyUsers;
    }

    public boolean getHasLinkSharing() {
        return this.mHasLinkSharing;
    }

    public boolean getIsPartyActive() {
        return this.mIsInParty;
    }

    @Nullable
    public String getPartyID() {
        return this.mPartyID;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long j = inputFrame.mTimeMillis;
        if (j - this.mLastPartyRefreshTime > 4900) {
            this.mLastPartyRefreshTime = (j / 4900) * 4900;
            refreshPartyData();
        }
    }

    public void registerObserver(PartiesDataObserver partiesDataObserver) {
        if (!this.mObservers.contains(partiesDataObserver)) {
            this.mObservers.add(partiesDataObserver);
        }
    }

    public void removeObserver(PartiesDataObserver partiesDataObserver) {
        if (this.mObservers.contains(partiesDataObserver)) {
            this.mObservers.remove(partiesDataObserver);
        }
    }

    public PartiesFetcher(Context context) {
        this.mContext = context;
        this.mHasLinkSharing = false;
        this.mIsInParty = false;
        this.mPartyID = null;
        this.mAllPartyUsers = null;
    }

    private void loadPartyData() {
        clearFetchCurrentPartyAsyncQueryHandle();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyV2(this.mContext, new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.messenger.fetchers.PartiesFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                PartiesFetcher.this.setParty(null);
                PartiesFetcher.this.clearFetchCurrentPartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                PartiesFetcher.this.setParty(socialParty);
                PartiesFetcher.this.clearFetchCurrentPartyAsyncQueryHandle();
            }
        });
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        clearFetchCurrentPartyAsyncQueryHandle();
    }
}
