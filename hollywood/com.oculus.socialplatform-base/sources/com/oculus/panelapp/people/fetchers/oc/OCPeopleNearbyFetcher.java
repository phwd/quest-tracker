package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyLoader;
import java.util.ArrayList;
import java.util.List;

public class OCPeopleNearbyFetcher extends BaseFetcher implements ISearchFetcher {
    public static final int DEFAULT_USERS_FETCH_COUNT = 20;
    public static final String TAG = LoggingUtil.tag(OCPeopleNearbyFetcher.class);
    public Context mContext;
    public boolean mEnoughDataFetchedAll;
    public DataFetchedCriteria mEnoughDataFetchedCriteria;
    public boolean mHasAppSupport = false;
    public List<SocialUser> mPeopleNearby;
    public boolean mPeopleNearbyEnabled;
    public OCPeopleNearbyLoader mPeopleNearbyLoader;
    public List<SocialUser> mSearchResults;
    public String mSearchString;

    public enum DataFetchedCriteria {
        FETCH_SEARCH_ALL,
        FETCH_DEFAULT
    }

    public void fetchAllPeopleNearby() {
        loadPeopleNearby(null);
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadPeopleNearby(null);
    }

    private void loadPeopleNearby(@Nullable final Integer num) {
        if (this.mPeopleNearbyEnabled) {
            this.mPeopleNearbyLoader.loadPeopleNearby(TAG, num, new OCPeopleNearbyLoader.PeopleNearbyLoadingCallback() {
                /* class com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher.AnonymousClass1 */

                @Override // com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyLoader.PeopleNearbyLoadingCallback
                public void onError() {
                    OCPeopleNearbyFetcher.this.mIsErrored = true;
                }

                @Override // com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyLoader.PeopleNearbyLoadingCallback
                public void onSuccess(ArrayList<SocialUser> arrayList) {
                    OCPeopleNearbyFetcher oCPeopleNearbyFetcher = OCPeopleNearbyFetcher.this;
                    oCPeopleNearbyFetcher.mIsErrored = false;
                    oCPeopleNearbyFetcher.setPeopleNearby(arrayList, num);
                }
            });
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        this.mPeopleNearbyLoader.destroy();
        super.destroy();
        this.mPeopleNearby = ImmutableList.of();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        if (this.mEnoughDataFetchedCriteria != DataFetchedCriteria.FETCH_SEARCH_ALL || TextUtils.isEmpty(this.mSearchString)) {
            return this.mPeopleNearby;
        }
        return this.mSearchResults;
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public boolean getEnoughDataFetched() {
        if (this.mEnoughDataFetchedCriteria == DataFetchedCriteria.FETCH_SEARCH_ALL) {
            return this.mEnoughDataFetchedAll;
        }
        return super.getEnoughDataFetched();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.HIGH;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public String getSearchString() {
        return this.mSearchString;
    }

    public boolean hasAppSupport() {
        return this.mHasAppSupport;
    }

    public void removeUserPeopleNearby(SocialUser socialUser) {
        this.mPeopleNearby.remove(socialUser);
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public void searchForUser(String str) {
        this.mSearchString = str;
        updateSearchList(str);
        notifyPeopleListObservers();
    }

    public OCPeopleNearbyFetcher(Context context, boolean z, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        ImmutableList of = ImmutableList.of();
        this.mPeopleNearby = of;
        this.mSearchResults = of;
        this.mPeopleNearbyEnabled = z;
        this.mPeopleNearbyLoader = new OCPeopleNearbyLoader(context);
        this.mEnoughDataFetchedAll = false;
        this.mEnoughDataFetchedCriteria = DataFetchedCriteria.FETCH_DEFAULT;
        loadPeopleNearby(20);
    }

    private void updateSearchList(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mSearchResults = new ArrayList();
            for (SocialUser socialUser : this.mPeopleNearby) {
                if (socialUser.mAlias.indexOf(str) != -1) {
                    this.mSearchResults.add(socialUser);
                }
            }
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public int getCount() {
        if (getData() == null) {
            return 0;
        }
        return getData().size();
    }

    @VisibleForTesting
    public void setPeopleNearby(List<SocialUser> list, @Nullable Integer num) {
        if (list.size() < 1) {
            this.mIsErrored = true;
            return;
        }
        this.mHasAppSupport = list.get(0).mIsInRoom;
        this.mPeopleNearby = list.subList(1, list.size());
        updateSearchList(this.mSearchString);
        if (num == null) {
            this.mEnoughDataFetchedAll = true;
        }
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public void setEnoughDataFetchedCriteria(DataFetchedCriteria dataFetchedCriteria) {
        this.mEnoughDataFetchedCriteria = dataFetchedCriteria;
    }
}
