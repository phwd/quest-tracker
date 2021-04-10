package com.oculus.panelapp.people.views;

import X.AnonymousClass1CG;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.FBLinkedStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.panelapp.people.SearchTextWatcher;
import com.oculus.panelapp.people.databinding.PeopleSearchViewBinding;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.PeopleListDataObserver;
import com.oculus.panelapp.people.fetchers.fb.FBAllConnectionsFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.BaseView;
import java.util.ArrayList;
import java.util.List;

public class PeopleSearchView extends BaseView implements PeopleViewUpdateListener, PeopleListDataObserver {
    public static final long SEARCH_TYPING_DELAY_MS = 1000;
    public static final String TAG = LoggingUtil.tag(PeopleSearchView.class);
    public BaseFetcher mActiveFriendsFetcher;
    public ISearchFetcher mAllConnectionsFetcher;
    public OCPeopleNearbyFetcher mAllPeopleNearbyFetcher;
    public final PeopleSearchViewBinding mBinding;
    public Context mContext;
    @VisibleForTesting
    public PeopleViewType mCurrentViewType;
    public Handler mHandler = new Handler();
    public OCLinkedAccountsInfoFetcher mLinkedAccountsInfoFetcher;
    public PeopleTabletPanelApp mPanelApp;
    @VisibleForTesting
    public PeopleAdapter mPeopleAdapter;
    @VisibleForTesting
    public AnonymousClass1CG mRecylerViewScrollListener;
    public ISearchFetcher mSearchFetcher;
    public PeopleSearchViewModel mSearchViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.people.views.PeopleSearchView$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.PeopleSearchView.AnonymousClass2.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.ALL_CONNECTIONS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.ALL_NEARBY     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleSearchView.AnonymousClass2.<clinit>():void");
        }
    }

    private ImmutableList.Builder<PeopleAdapterItem> buildActiveFriendsList() {
        BaseFetcher baseFetcher;
        ImmutableList.Builder<PeopleAdapterItem> builder = new ImmutableList.Builder<>();
        if (isSearchStringEmpty() && (baseFetcher = this.mActiveFriendsFetcher) != null && !baseFetcher.hasNoData()) {
            List<SocialUser> data = this.mActiveFriendsFetcher.getData();
            ArrayList arrayList = new ArrayList();
            for (SocialUser socialUser : data) {
                if (socialUser.getIsActive(this.mContext.getResources())) {
                    arrayList.add(socialUser);
                }
            }
            builder.A03(buildUserList(arrayList, this.mContext.getString(R.string.people_tablet_active_connections_header_title, Integer.valueOf(arrayList.size()))));
        }
        return builder;
    }

    private List<PeopleAdapterItem> buildUserList(List<SocialUser> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new HeaderAdapterItem(str));
            }
            for (SocialUser socialUser : list) {
                arrayList.add(new PeopleUserAdapterItem(socialUser));
            }
        }
        return arrayList;
    }

    private void removeRecyclerViewScrollListener() {
        AnonymousClass1CG r1 = this.mRecylerViewScrollListener;
        if (r1 != null) {
            this.mBinding.peopleList.removeOnScrollListener(r1);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: searchText */
    public void lambda$initialize$0$PeopleSearchView(String str) {
        this.mHandler.removeCallbacksAndMessages(null);
        setLoadingState(true);
        this.mHandler.postDelayed(new Runnable(str) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleSearchView$WTKiwLXn9BKJYW1OkhCpTgWWlY2 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                PeopleSearchView.this.lambda$searchText$1$PeopleSearchView(this.f$1);
            }
        }, 1000);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
        if (peopleTabletPanelApp != null) {
            peopleTabletPanelApp.releaseSearchFetcher();
            peopleTabletPanelApp.releasePeopleNearbyFetcher();
            peopleTabletPanelApp.releaseLinkedAccountsInfoFetcher();
            peopleTabletPanelApp.releaseFriendsFetcher();
        }
        ISearchFetcher iSearchFetcher = this.mSearchFetcher;
        if (iSearchFetcher != null) {
            iSearchFetcher.removePeopleListObserver(this);
            this.mSearchFetcher = null;
        }
        OCLinkedAccountsInfoFetcher oCLinkedAccountsInfoFetcher = this.mLinkedAccountsInfoFetcher;
        if (oCLinkedAccountsInfoFetcher != null) {
            oCLinkedAccountsInfoFetcher.removePeopleListObserver(this);
            this.mLinkedAccountsInfoFetcher = null;
        }
        BaseFetcher baseFetcher = this.mActiveFriendsFetcher;
        if (baseFetcher != null) {
            baseFetcher.removePeopleListObserver(this);
            this.mActiveFriendsFetcher = null;
        }
        removeRecyclerViewScrollListener();
        this.mRecylerViewScrollListener = null;
        ISearchFetcher iSearchFetcher2 = this.mAllConnectionsFetcher;
        if (iSearchFetcher2 != null) {
            iSearchFetcher2.removePeopleListObserver(this);
            this.mAllConnectionsFetcher = null;
        }
        OCPeopleNearbyFetcher oCPeopleNearbyFetcher = this.mAllPeopleNearbyFetcher;
        if (oCPeopleNearbyFetcher != null) {
            oCPeopleNearbyFetcher.removePeopleListObserver(this);
            this.mAllPeopleNearbyFetcher = null;
        }
        this.mBinding.peopleSearchTopBar.destroy();
    }

    @VisibleForTesting
    public ISearchFetcher getActiveFetcher() {
        switch (this.mCurrentViewType.ordinal()) {
            case 0:
                return this.mAllConnectionsFetcher;
            case 1:
                return this.mAllPeopleNearbyFetcher;
            default:
                return this.mSearchFetcher;
        }
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener) {
        PeopleSearchViewBinding peopleSearchViewBinding = this.mBinding;
        if (peopleSearchViewBinding == null) {
            Log.e(TAG, "Initialized before inflated");
            return;
        }
        this.mPanelApp = peopleTabletPanelApp;
        peopleSearchViewBinding.peopleSearchTopBar.initialize(peopleTabletPanelApp, peopleViewUpdateListener, new SearchTextWatcher() {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleSearchView$hI7sW93V6xE9utnx018qvALP4c2 */

            @Override // com.oculus.panelapp.people.SearchTextWatcher
            public final void onTextChanged(String str) {
                PeopleSearchView.this.lambda$initialize$0$PeopleSearchView(str);
            }
        });
        this.mPanelApp = peopleTabletPanelApp;
        PeopleAdapter peopleAdapter = new PeopleAdapter(peopleTabletPanelApp);
        this.mPeopleAdapter = peopleAdapter;
        this.mBinding.peopleList.setAdapter(peopleAdapter);
        this.mPeopleAdapter.setData(new ArrayList());
        ISearchFetcher acquireSearchFetcher = peopleTabletPanelApp.acquireSearchFetcher();
        this.mSearchFetcher = acquireSearchFetcher;
        if (acquireSearchFetcher != null) {
            acquireSearchFetcher.registerPeopleListObserver(this);
        }
        OCLinkedAccountsInfoFetcher acquireLinkedAccountsInfoFetcher = peopleTabletPanelApp.acquireLinkedAccountsInfoFetcher();
        this.mLinkedAccountsInfoFetcher = acquireLinkedAccountsInfoFetcher;
        if (acquireLinkedAccountsInfoFetcher != null) {
            acquireLinkedAccountsInfoFetcher.registerPeopleListObserver(this);
        }
        BaseFetcher acquireFriendsFetcher = peopleTabletPanelApp.acquireFriendsFetcher();
        this.mActiveFriendsFetcher = acquireFriendsFetcher;
        if (acquireFriendsFetcher != null) {
            acquireFriendsFetcher.registerPeopleListObserver(this);
        }
        ISearchFetcher acquireAllConnectionsFetcher = peopleTabletPanelApp.acquireAllConnectionsFetcher();
        this.mAllConnectionsFetcher = acquireAllConnectionsFetcher;
        if (acquireAllConnectionsFetcher != null) {
            acquireAllConnectionsFetcher.registerPeopleListObserver(this);
        }
        OCPeopleNearbyFetcher acquirePeopleNearbyFetcher = peopleTabletPanelApp.acquirePeopleNearbyFetcher();
        this.mAllPeopleNearbyFetcher = acquirePeopleNearbyFetcher;
        if (acquirePeopleNearbyFetcher != null) {
            acquirePeopleNearbyFetcher.registerPeopleListObserver(this);
        }
        PeopleSearchViewModel peopleSearchViewModel = new PeopleSearchViewModel();
        this.mSearchViewModel = peopleSearchViewModel;
        peopleSearchViewModel.mIsLoading = false;
        this.mBinding.setViewModel(peopleSearchViewModel);
        maybeAddRecyclerViewScrollListener(peopleTabletPanelApp);
    }

    @Override // com.oculus.panelapp.people.fetchers.PeopleListDataObserver
    public void onPeopleListsUpdated() {
        PeopleViewType peopleViewType = this.mCurrentViewType;
        if (peopleViewType != null) {
            onUpdatePeopleViewType(peopleViewType);
        }
    }

    @Override // com.oculus.panelapp.people.views.PeopleViewUpdateListener
    public void onUpdatePeopleViewType(PeopleViewType peopleViewType) {
        PeopleAdapter peopleAdapter;
        PeopleEmptyAdapterItemType emptyType;
        this.mCurrentViewType = peopleViewType;
        ISearchFetcher activeFetcher = getActiveFetcher();
        this.mBinding.peopleSearchTopBar.showSearchBar();
        this.mBinding.peopleSearchTopBar.onUpdatePeopleViewType(peopleViewType);
        if (this.mPanelApp.getCurrentViewType() == PeopleViewType.ALL_NEARBY) {
            this.mAllPeopleNearbyFetcher.mEnoughDataFetchedCriteria = OCPeopleNearbyFetcher.DataFetchedCriteria.FETCH_SEARCH_ALL;
        }
        boolean z = true;
        boolean z2 = !activeFetcher.getEnoughDataFetched();
        setLoadingState(z2);
        if (!z2) {
            if (activeFetcher.getIsErrored()) {
                peopleAdapter = this.mPeopleAdapter;
                emptyType = PeopleEmptyAdapterItemType.ERROR;
            } else if (activeFetcher.hasNoData()) {
                if (this.mPanelApp.getFBLinkedStatus() != FBLinkedStatus.NOT_READY) {
                    z = false;
                }
                setLoadingState(z);
                if (!z) {
                    PeopleViewType peopleViewType2 = this.mCurrentViewType;
                    if ((peopleViewType2 == PeopleViewType.ALL_CONNECTIONS || peopleViewType2 == PeopleViewType.ALL_NEARBY) && isSearchStringEmpty()) {
                        this.mBinding.peopleSearchTopBar.hideSearchBar();
                    }
                    if (!isSearchStringEmpty()) {
                        peopleAdapter = this.mPeopleAdapter;
                        emptyType = PeopleEmptyAdapterItemType.NO_SEARCH_RESULTS;
                    } else {
                        peopleAdapter = this.mPeopleAdapter;
                        emptyType = this.mPanelApp.getEmptyType(peopleViewType);
                    }
                } else {
                    return;
                }
            } else if (peopleViewType == PeopleViewType.SEARCH) {
                removeRecyclerViewScrollListener();
                populateSearchResults();
                return;
            } else if (peopleViewType == PeopleViewType.ALL_NEARBY) {
                maybeAddRecyclerViewScrollListener(this.mPanelApp);
                populateAllNearby();
                return;
            } else {
                maybeAddRecyclerViewScrollListener(this.mPanelApp);
                populateAllConnections();
                return;
            }
            peopleAdapter.setData(ImmutableList.A06(new PeopleEmptyAdapterItem(emptyType, this.mContext)));
        }
    }

    @VisibleForTesting
    public boolean setLoadingState(boolean z) {
        this.mSearchViewModel.mIsLoading = z;
        this.mBinding.invalidateAll();
        OCRecyclerView oCRecyclerView = this.mBinding.peopleList;
        int i = 8;
        if (!z) {
            i = 0;
        }
        oCRecyclerView.setVisibility(i);
        return z;
    }

    public PeopleSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mBinding = PeopleSearchViewBinding.inflate(LayoutInflater.from(context), this, true);
    }

    private boolean isSearchStringEmpty() {
        return TextUtils.isEmpty(getActiveFetcher().getSearchString());
    }

    private void populateAllConnections() {
        String string;
        ImmutableList.Builder<PeopleAdapterItem> buildActiveFriendsList = buildActiveFriendsList();
        if (isSearchStringEmpty()) {
            string = this.mContext.getString(R.string.people_tablet_all_connections_header_title, Integer.valueOf(this.mAllConnectionsFetcher.getCount()));
        } else {
            string = this.mContext.getString(R.string.people_tablet_search_results_header_title);
        }
        buildActiveFriendsList.A03(buildUserList(this.mAllConnectionsFetcher.getData(), string));
        this.mPeopleAdapter.setData(buildActiveFriendsList.build());
    }

    private void populateAllNearby() {
        String string;
        if (isSearchStringEmpty()) {
            string = this.mContext.getString(R.string.people_tablet_all_connections_header_title, Integer.valueOf(this.mAllPeopleNearbyFetcher.getCount()));
        } else {
            string = this.mContext.getString(R.string.people_tablet_search_results_header_title);
        }
        this.mPeopleAdapter.setData(buildUserList(this.mAllPeopleNearbyFetcher.getData(), string));
    }

    private void populateSearchResults() {
        ImmutableList.Builder builder;
        if (isSearchStringEmpty()) {
            List<SocialUser> data = this.mSearchFetcher.getData();
            builder = new ImmutableList.Builder();
            builder.add((Object) new HeaderAdapterItem(this.mContext.getString(R.string.people_tablet_search_null_state_friends_header_title, Integer.valueOf(data.size()))));
            builder.A03(PeopleAdapterUtil.buildUserCardRowForVerticalScroll(data, PeopleViewType.SEARCH, this.mContext));
        } else {
            builder = new ImmutableList.Builder();
            List<SocialUser> data2 = this.mSearchFetcher.getData();
            builder.add((Object) new HeaderAdapterItem(this.mContext.getString(R.string.people_tablet_search_results_header_title)));
            for (SocialUser socialUser : data2) {
                builder.add((Object) new PeopleUserAdapterItem(socialUser));
            }
        }
        this.mPeopleAdapter.setData(builder.build());
    }

    public /* synthetic */ void lambda$searchText$1$PeopleSearchView(String str) {
        getActiveFetcher().searchForUser(str);
    }

    @VisibleForTesting
    public void maybeAddRecyclerViewScrollListener(PeopleTabletPanelApp peopleTabletPanelApp) {
        if (peopleTabletPanelApp.getPeopleTabletType() == PeopleTabletType.FACEBOOK && this.mCurrentViewType == PeopleViewType.ALL_CONNECTIONS) {
            AnonymousClass1CG r1 = this.mRecylerViewScrollListener;
            if (r1 == null) {
                r1 = new AnonymousClass1CG() {
                    /* class com.oculus.panelapp.people.views.PeopleSearchView.AnonymousClass1 */

                    @Override // X.AnonymousClass1CG
                    public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.mLayout;
                        List<SocialUser> data = PeopleSearchView.this.getActiveFetcher().getData();
                        if (data != null && !data.isEmpty() && linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                            PeopleSearchView peopleSearchView = PeopleSearchView.this;
                            if (peopleSearchView.mCurrentViewType == PeopleViewType.ALL_CONNECTIONS) {
                                ISearchFetcher iSearchFetcher = peopleSearchView.mAllConnectionsFetcher;
                                if (iSearchFetcher instanceof FBAllConnectionsFetcher) {
                                    FBAllConnectionsFetcher fBAllConnectionsFetcher = (FBAllConnectionsFetcher) iSearchFetcher;
                                    if (fBAllConnectionsFetcher.hasMore()) {
                                        fBAllConnectionsFetcher.fetchMore();
                                    }
                                }
                            }
                        }
                    }
                };
                this.mRecylerViewScrollListener = r1;
            }
            this.mBinding.peopleList.addOnScrollListener(r1);
        }
    }
}
