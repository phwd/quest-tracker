package com.oculus.panelapp.people.fetchers;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.vrshell.panels.InputFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseFetcher implements IFetcher {
    public static final long HI_PRI_DATA_UPDATE_RATE_MILLIS = 11000;
    public static final long INACTIVE_DATA_UPDATE_RATE_MILLIS = 45000;
    public static final long LOW_PRI_DATA_UPDATE_RATE_MILLIS = 21000;
    public static final long SYSTEM_DATA_UPDATE_RATE_MILLIS = 5000;
    public ConnectivityManager mConnection;
    public boolean mEnoughDataFetched;
    public boolean mHasInternetConnection;
    public boolean mIsErrored = false;
    public long mLastServerUpdateTimeMillis = 0;
    public long mLastSystemUpdateTimeMillis = 0;
    @VisibleForTesting(otherwise = 4)
    public ArrayList<PeopleListDataObserver> mPeopleListObservers = new ArrayList<>();
    @VisibleForTesting(otherwise = 2)
    public int mRefCount = 0;
    public SocialLogger mSocialLogger;

    public abstract String getLoggingTag();

    public abstract FetcherPriority getRefetchPriority();

    public abstract void refetchData();

    /* renamed from: com.oculus.panelapp.people.fetchers.BaseFetcher$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$fetchers$FetcherPriority;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.panelapp.people.fetchers.FetcherPriority[] r0 = com.oculus.panelapp.people.fetchers.FetcherPriority.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.fetchers.BaseFetcher.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$fetchers$FetcherPriority = r2
                com.oculus.panelapp.people.fetchers.FetcherPriority r0 = com.oculus.panelapp.people.fetchers.FetcherPriority.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.fetchers.FetcherPriority r0 = com.oculus.panelapp.people.fetchers.FetcherPriority.LOW     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.fetchers.FetcherPriority r0 = com.oculus.panelapp.people.fetchers.FetcherPriority.HIGH     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.fetchers.BaseFetcher.AnonymousClass1.<clinit>():void");
        }
    }

    private Long getUpdateRate() {
        long j;
        if (this.mRefCount != 0) {
            int ordinal = getRefetchPriority().ordinal();
            j = RecyclerView.FOREVER_NS;
            switch (ordinal) {
                case 1:
                    j = 21000;
                    break;
                case 2:
                    j = 11000;
                    break;
            }
        } else {
            j = 45000;
        }
        return Long.valueOf(j);
    }

    public void decrementRefCount() {
        int i = this.mRefCount;
        if (i > 0) {
            this.mRefCount = i - 1;
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public void destroy() {
        this.mPeopleListObservers.clear();
        this.mSocialLogger = null;
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public boolean getEnoughDataFetched() {
        if (this.mEnoughDataFetched || this.mIsErrored) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public boolean getIsErrored() {
        return this.mIsErrored;
    }

    public boolean hasInternetConnection() {
        return this.mHasInternetConnection;
    }

    public void incrementRefCount() {
        this.mRefCount++;
    }

    public void logFetchFailure(ActionId actionId, long j) {
        SocialLogger socialLogger = this.mSocialLogger;
        if (socialLogger != null) {
            socialLogger.logActionFailure(actionId, ClickEventButtonId.NO_BUTTON, SurfaceType.BACKGROUND_FETCHER, "", LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - j));
        }
    }

    public void logFetchSuccess(ActionId actionId, long j) {
        SocialLogger socialLogger = this.mSocialLogger;
        if (socialLogger != null) {
            socialLogger.logActionSuccess(actionId, ClickEventButtonId.NO_BUTTON, SurfaceType.BACKGROUND_FETCHER, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - j));
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public void notifyPeopleListObservers() {
        Iterator<PeopleListDataObserver> it = this.mPeopleListObservers.iterator();
        while (it.hasNext()) {
            it.next().onPeopleListsUpdated();
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long j = inputFrame.mTimeMillis;
        if (j - this.mLastSystemUpdateTimeMillis > 5000) {
            this.mLastSystemUpdateTimeMillis = j;
            boolean z = false;
            if (this.mConnection.getActiveNetwork() != null) {
                z = true;
            }
            if (this.mHasInternetConnection != z) {
                this.mHasInternetConnection = z;
                if (z) {
                    this.mLastServerUpdateTimeMillis = j;
                    refetchData();
                }
                notifyPeopleListObservers();
            }
        }
        if (this.mHasInternetConnection && j - this.mLastServerUpdateTimeMillis > getUpdateRate().longValue()) {
            refetchData();
            this.mLastServerUpdateTimeMillis = j;
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public void registerPeopleListObserver(PeopleListDataObserver peopleListDataObserver) {
        if (!this.mPeopleListObservers.contains(peopleListDataObserver)) {
            this.mPeopleListObservers.add(peopleListDataObserver);
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public void removePeopleListObserver(PeopleListDataObserver peopleListDataObserver) {
        if (this.mPeopleListObservers.contains(peopleListDataObserver)) {
            this.mPeopleListObservers.remove(peopleListDataObserver);
        }
    }

    public BaseFetcher(Context context, SocialLogger socialLogger) {
        boolean z = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mConnection = connectivityManager;
        this.mEnoughDataFetched = false;
        if (!(connectivityManager == null || connectivityManager.getActiveNetwork() == null)) {
            z = true;
        }
        this.mHasInternetConnection = z;
        this.mSocialLogger = socialLogger;
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public boolean hasNoData() {
        List<SocialUser> data = getData();
        if (data == null || data.isEmpty()) {
            return true;
        }
        return false;
    }

    public void setEnoughDataFetched(boolean z) {
        this.mEnoughDataFetched = z;
    }

    public void setIsErrored(boolean z) {
        this.mIsErrored = z;
    }
}
