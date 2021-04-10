package com.oculus.panelapp.people.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.common.collect.ImmutableList;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.navbar.ProfileType;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.common.socialtablet.navbar.SocialTabletType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.FBLinkedStatus;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.panelapp.people.databinding.PeopleViewBinding;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.PeopleListDataObserver;
import com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyFetcher;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.BaseView;
import java.util.ArrayList;
import java.util.List;

public class PeopleView extends BaseView implements PeopleViewUpdateListener, PeopleListDataObserver {
    public static final Integer DEFAULT_NUM_USERS = 20;
    public static final String TAG = LoggingUtil.tag(PeopleView.class);
    public BaseFetcher mActiveFetcher;
    public Runnable mActiveFetcherReleaser;
    public PeopleViewBinding mBinding;
    public Context mContext;
    public PeopleViewType mCurrentViewType;
    public OCLinkedAccountsInfoFetcher mLinkedAccountsInfoFetcher;
    public PeopleTabletPanelApp mPanelApp;
    @VisibleForTesting
    public PeopleActionAdapter mPeopleAdapter;
    public OCPeopleNearbyFetcher mPeopleNearbyFetcher;
    public PeopleViewModel mPeopleViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.people.views.PeopleView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

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
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.PeopleView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SUGGESTIONS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.PEOPLE_NEARBY     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleView.AnonymousClass1.<clinit>():void");
        }
    }

    private void displayViewTypeButton(PeopleViewType peopleViewType, int i) {
        if (this.mCurrentViewType != peopleViewType) {
            this.mBinding.peopleNavBar.setButtonVisibility(peopleViewType, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2.getFbRequestsEnabled() != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.oculus.panelapp.people.views.PeopleViewType> getViewTypeOptions() {
        /*
            r4 = this;
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS
            r3.add(r0)
            com.oculus.panelapp.people.PeopleTabletPanelApp r2 = r4.mPanelApp
            com.oculus.panelapp.people.PeopleTabletType r1 = r2.getPeopleTabletType()
            com.oculus.panelapp.people.PeopleTabletType r0 = com.oculus.panelapp.people.PeopleTabletType.OCULUS
            if (r1 != r0) goto L_0x0025
            boolean r0 = r4.canSeeSuggestions()
            if (r0 == 0) goto L_0x001f
            com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SUGGESTIONS
            r3.add(r0)
        L_0x001f:
            com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS
            r3.add(r0)
        L_0x0024:
            return r3
        L_0x0025:
            boolean r0 = r2.getFbRequestsEnabled()
            if (r0 == 0) goto L_0x0024
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleView.getViewTypeOptions():java.util.List");
    }

    private boolean setLoadingState(boolean z) {
        this.mPeopleViewModel.mIsLoading = z;
        this.mBinding.invalidateAll();
        OCRecyclerView oCRecyclerView = this.mBinding.peopleList;
        int i = 0;
        if (z) {
            i = 8;
        }
        oCRecyclerView.setVisibility(i);
        return z;
    }

    private void setRecyclerViewPaddingLeft(int i) {
        this.mBinding.peopleList.setPadding(i, 0, 0, 0);
    }

    private void setupActiveFetcher(PeopleViewType peopleViewType) {
        BaseFetcher baseFetcher = this.mActiveFetcher;
        if (baseFetcher != null) {
            baseFetcher.removePeopleListObserver(this);
            this.mActiveFetcherReleaser.run();
        }
        switch (peopleViewType.ordinal()) {
            case 2:
                PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
                this.mActiveFetcher = peopleTabletPanelApp.acquireFriendsFetcher();
                this.mActiveFetcherReleaser = new Runnable() {
                    /* class com.oculus.panelapp.people.views.$$Lambda$tlS2fuIju2yEwHK0cppzp2RV6oU2 */

                    public final void run() {
                        PeopleTabletPanelApp.this.releaseFriendsFetcher();
                    }
                };
                break;
            case 3:
                this.mActiveFetcher = this.mPanelApp.acquireFriendRequestFetcher();
                this.mActiveFetcherReleaser = new Runnable() {
                    /* class com.oculus.panelapp.people.views.$$Lambda$xAR76uUCFO6pfCOROjiLjjOPC42 */

                    public final void run() {
                        PeopleTabletPanelApp.this.releaseFriendRequestFetcher();
                    }
                };
                break;
            case 5:
                PeopleTabletPanelApp peopleTabletPanelApp2 = this.mPanelApp;
                this.mActiveFetcher = peopleTabletPanelApp2.acquirePYMKFetcher();
                this.mActiveFetcherReleaser = new Runnable() {
                    /* class com.oculus.panelapp.people.views.$$Lambda$KjOuKyFELldnCiCpBuWim89n1pE2 */

                    public final void run() {
                        PeopleTabletPanelApp.this.releasePYMKFetcher();
                    }
                };
                break;
            case 6:
                PeopleTabletPanelApp peopleTabletPanelApp3 = this.mPanelApp;
                this.mActiveFetcher = peopleTabletPanelApp3.acquirePeopleNearbyFetcher();
                this.mActiveFetcherReleaser = new Runnable() {
                    /* class com.oculus.panelapp.people.views.$$Lambda$BY6aTmrDcrgLPGAXv4mlccOhef82 */

                    public final void run() {
                        PeopleTabletPanelApp.this.releasePeopleNearbyFetcher();
                    }
                };
                break;
        }
        this.mActiveFetcher.registerPeopleListObserver(this);
    }

    @VisibleForTesting
    public boolean canSeeSuggestions() {
        return DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_PYMK);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mActiveFetcherReleaser.run();
        this.mActiveFetcherReleaser = null;
        this.mActiveFetcher.removePeopleListObserver(this);
        this.mActiveFetcher = null;
        PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
        if (peopleTabletPanelApp != null) {
            peopleTabletPanelApp.releaseLinkedAccountsInfoFetcher();
            peopleTabletPanelApp.releasePeopleNearbyFetcher();
        }
        OCLinkedAccountsInfoFetcher oCLinkedAccountsInfoFetcher = this.mLinkedAccountsInfoFetcher;
        if (oCLinkedAccountsInfoFetcher != null) {
            oCLinkedAccountsInfoFetcher.removePeopleListObserver(this);
            this.mLinkedAccountsInfoFetcher = null;
        }
        OCPeopleNearbyFetcher oCPeopleNearbyFetcher = this.mPeopleNearbyFetcher;
        if (oCPeopleNearbyFetcher != null) {
            oCPeopleNearbyFetcher.removePeopleListObserver(this);
            this.mPeopleNearbyFetcher = null;
        }
        this.mPeopleAdapter = null;
        PeopleViewBinding peopleViewBinding = this.mBinding;
        peopleViewBinding.peopleNavBar.destroy();
        peopleViewBinding.peopleViewSideNav.destroy();
        this.mBinding.peopleTabletTopBar.destroy();
    }

    public PeopleViewType getCurrentViewType() {
        return this.mCurrentViewType;
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener) {
        ProfileType profileType;
        super.initialize(peopleTabletPanelApp, this.mBinding);
        this.mPanelApp = peopleTabletPanelApp;
        this.mPeopleAdapter = new PeopleActionAdapter(peopleTabletPanelApp);
        PeopleViewModel peopleViewModel = new PeopleViewModel();
        this.mPeopleViewModel = peopleViewModel;
        peopleViewModel.mIsLoading = false;
        this.mBinding.setViewModel(peopleViewModel);
        this.mBinding.peopleList.setAdapter(this.mPeopleAdapter);
        this.mBinding.peopleList.setLayoutManager(new LinearLayoutManager(peopleTabletPanelApp.mContext, 0, false));
        this.mPeopleAdapter.setData(new ArrayList());
        OCLinkedAccountsInfoFetcher acquireLinkedAccountsInfoFetcher = peopleTabletPanelApp.acquireLinkedAccountsInfoFetcher();
        this.mLinkedAccountsInfoFetcher = acquireLinkedAccountsInfoFetcher;
        if (acquireLinkedAccountsInfoFetcher != null) {
            acquireLinkedAccountsInfoFetcher.registerPeopleListObserver(this);
        }
        OCPeopleNearbyFetcher acquirePeopleNearbyFetcher = peopleTabletPanelApp.acquirePeopleNearbyFetcher();
        this.mPeopleNearbyFetcher = acquirePeopleNearbyFetcher;
        if (acquirePeopleNearbyFetcher != null) {
            acquirePeopleNearbyFetcher.registerPeopleListObserver(this);
        }
        this.mBinding.peopleNavBar.initialize(peopleTabletPanelApp, peopleViewUpdateListener, false, getViewTypeOptions());
        onPeopleListsUpdated();
        this.mBinding.peopleTabletTopBar.initialize(peopleTabletPanelApp, peopleViewUpdateListener);
        SocialTabletSideNav socialTabletSideNav = this.mBinding.peopleViewSideNav;
        SocialTabletType socialTabletType = SocialTabletType.PEOPLE;
        if (peopleTabletPanelApp.getPeopleTabletType() == PeopleTabletType.OCULUS) {
            profileType = ProfileType.OCULUS;
        } else {
            profileType = ProfileType.FACEBOOK;
        }
        socialTabletSideNav.initialize(peopleTabletPanelApp, socialTabletType, profileType);
    }

    @Override // com.oculus.panelapp.people.fetchers.PeopleListDataObserver
    public void onPeopleListsUpdated() {
        OCPeopleNearbyFetcher oCPeopleNearbyFetcher;
        int i;
        if (DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_PEOPLE_NEARBY) && this.mPanelApp.getPeopleTabletType() == PeopleTabletType.OCULUS && (oCPeopleNearbyFetcher = this.mPeopleNearbyFetcher) != null) {
            boolean z = oCPeopleNearbyFetcher.mHasAppSupport;
            PeopleViewType peopleViewType = PeopleViewType.PEOPLE_NEARBY;
            if (z) {
                i = 0;
            } else {
                i = 8;
            }
            displayViewTypeButton(peopleViewType, i);
        }
        onUpdatePeopleViewType(this.mBinding.peopleNavBar.mCurrentView);
    }

    @Override // com.oculus.panelapp.people.views.PeopleViewUpdateListener
    public void onUpdatePeopleViewType(PeopleViewType peopleViewType) {
        PeopleActionAdapter peopleActionAdapter;
        PeopleEmptyAdapterItemType peopleEmptyAdapterItemType;
        if (this.mCurrentViewType != peopleViewType) {
            this.mCurrentViewType = peopleViewType;
            setupActiveFetcher(peopleViewType);
        }
        if (this.mPanelApp.getCurrentViewType() == PeopleViewType.PEOPLE_NEARBY) {
            this.mPeopleNearbyFetcher.mEnoughDataFetchedCriteria = OCPeopleNearbyFetcher.DataFetchedCriteria.FETCH_DEFAULT;
        }
        boolean z = false;
        if (this.mActiveFetcher.mHasInternetConnection) {
            this.mBinding.peopleNavBar.setVisibility(0);
            this.mBinding.peopleTabletTopBar.setVisibility(0);
            boolean z2 = !this.mActiveFetcher.getEnoughDataFetched();
            setLoadingState(z2);
            if (z2) {
                setRecyclerViewPaddingLeft(0);
                return;
            }
            BaseFetcher baseFetcher = this.mActiveFetcher;
            if (baseFetcher.getIsErrored()) {
                setRecyclerViewPaddingLeft(0);
                peopleActionAdapter = this.mPeopleAdapter;
                peopleEmptyAdapterItemType = PeopleEmptyAdapterItemType.ERROR;
            } else if (baseFetcher.hasNoData()) {
                setRecyclerViewPaddingLeft(0);
                if (this.mPanelApp.getFBLinkedStatus() == FBLinkedStatus.NOT_READY) {
                    z = true;
                }
                setLoadingState(z);
                if (!z) {
                    peopleActionAdapter = this.mPeopleAdapter;
                    peopleEmptyAdapterItemType = this.mPanelApp.getEmptyType(peopleViewType);
                } else {
                    return;
                }
            } else {
                setRecyclerViewPaddingLeft(this.mContext.getResources().getDimensionPixelOffset(R.dimen.abc_dialog_padding_material));
                List<SocialUser> data = this.mActiveFetcher.getData();
                int size = data.size();
                int intValue = DEFAULT_NUM_USERS.intValue();
                if (size > intValue) {
                    data = data.subList(0, intValue);
                }
                this.mPeopleAdapter.setData(PeopleAdapterUtil.buildUserCardRow(data, peopleViewType, this.mContext));
                return;
            }
        } else {
            this.mBinding.peopleNavBar.setVisibility(8);
            this.mBinding.peopleTabletTopBar.setVisibility(8);
            this.mPeopleViewModel.mIsLoading = false;
            setRecyclerViewPaddingLeft(0);
            peopleActionAdapter = this.mPeopleAdapter;
            peopleEmptyAdapterItemType = PeopleEmptyAdapterItemType.NO_INTERNET;
        }
        peopleActionAdapter.setData(ImmutableList.A06(new PeopleEmptyAdapterItem(peopleEmptyAdapterItemType, this.mContext)));
    }

    public PeopleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = PeopleViewBinding.inflate(LayoutInflater.from(context), this, true);
        this.mContext = context;
    }
}
