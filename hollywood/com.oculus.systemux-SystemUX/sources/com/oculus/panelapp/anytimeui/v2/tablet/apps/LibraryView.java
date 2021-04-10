package com.oculus.panelapp.anytimeui.v2.tablet.apps;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.ocui.OCDropdownVisibilityCallback;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.logging.OCRecyclerViewLogger;
import com.oculus.ocui.logging.OCSelectLogger;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding;
import com.oculus.panelapp.anytimeui.quickpromotion.QPGuideBarController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.LibraryContentAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.HmdInactivityUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryStateHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback;
import com.oculus.tablet.utils.backtotop.BackToTopButtonManager;
import com.oculus.tablet.utils.backtotop.BackToTopButtonProvider;
import com.oculus.tablet.utils.backtotop.PendingScrollUpdate;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class LibraryView extends BaseView implements HmdInactivityUtil.HmdInactivityObserver {
    private static final String TAG = LoggingUtil.tag(LibraryView.class);
    private OVRLibrary.OnChangeListener mAppsChangeListener;
    private BackToTopButtonManager mBackToTopButtonManager;
    private AnytimeTabletLibraryViewV2Binding mBinding;
    private LibraryContentAdapter mContentAdapter;
    private OCDropdownVisibilityCallback mDropdownVisibilityCallback;
    private LibraryLogger mLibraryLogger;
    @Nullable
    private OnboardingTutorial mOnboardingTutorial;
    @Nullable
    private OnboardingTutorial.OnboardingTutorialChangeListener mOnboardingTutorialChangeListener;
    private AnytimeUIPanelAppBase mPanelApp;
    private PendingScrollUpdate mPendingScrollUpdate = new PendingScrollUpdate();
    private ScrollCallback mScrollCallback;
    private SettingsViewModel mSettingsViewModel;
    private LibraryViewModel mViewModel;

    public LibraryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing LibraryView");
    }

    public void initialize(AnytimeUIPanelAppBase anytimeUIPanelAppBase, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIPanelAppBase, viewDataBinding);
        Log.d(TAG, "Initializing LibraryView");
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mBinding = (AnytimeTabletLibraryViewV2Binding) viewDataBinding;
        this.mViewModel = anytimeUIPanelAppBase.acquireLibraryViewModel();
        this.mSettingsViewModel = anytimeUIPanelAppBase.acquireSettingsViewModel();
        this.mBinding.setViewModel(this.mViewModel);
        this.mLibraryLogger = new LibraryLogger(getContext(), this.mViewModel);
        this.mViewModel.setLibraryLogger(this.mLibraryLogger);
        this.mViewModel.setShouldSkipSavingScrollPositionOnDestroy(false);
        initializeOnBoardingTutorial();
        checkBootTimeAndMaybeResetLibraryState();
        HmdInactivityUtil.addHmdInactivityObserver(this);
        this.mBackToTopButtonManager = new BackToTopButtonManager(getContext());
        this.mDropdownVisibilityCallback = new OCDropdownVisibilityCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass1 */

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onShow() {
                LibraryView.this.mViewModel.setScrollEnabled(false);
            }

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onHide() {
                LibraryView.this.mViewModel.setScrollEnabled(true);
            }
        };
        initializeScrollCallback();
        initializeDropdowns();
        initializeLibraryContent();
        initializeBackToTopButton();
        this.mAppsChangeListener = new OVRLibrary.OnChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryView$zua2BwLl4T6wlC98Fju8PSiI9WQ */

            @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
            public final void onChange(String str) {
                LibraryView.this.lambda$initialize$113$LibraryView(str);
            }
        };
        HorizonUtil.registerAppsChangeListener(getContext(), this.mAppsChangeListener);
        this.mViewModel.setScrollCallback(this.mScrollCallback);
        if ((anytimeUIPanelAppBase instanceof AnytimeUIAndroidPanelAppV2) && QPGuideBarController.shouldShowQPGuideBar((AnytimeUIAndroidPanelAppV2) anytimeUIPanelAppBase)) {
            new QPGuideBarController(this.mBinding, (AnytimeUIAndroidPanelAppV2) this.mPanelApp);
        }
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.APPS_TABLET);
    }

    public /* synthetic */ void lambda$initialize$113$LibraryView(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Received single app update signal from OCMS for: %s", str));
            this.mViewModel.getLibraryFetcher().fetchHorizonAppAsync(str);
            return;
        }
        Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, "Received whole library update signal from OCMS");
        this.mViewModel.getLibraryFetcher().fetchHorizonAppsAsync(false);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        OnboardingTutorial.OnboardingTutorialChangeListener onboardingTutorialChangeListener;
        Log.d(TAG, "Destroying LibraryView");
        if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_APPS_DISK_CACHE)) {
            this.mViewModel.saveAppsToCache();
        }
        if (this.mBackToTopButtonManager.isScrollToTopAnimationRunning()) {
            this.mBackToTopButtonManager.maybeStopScrollToTopAnimation();
            this.mBinding.libraryContent.scrollToPosition(0);
        }
        if (!this.mViewModel.getShouldSkipSavingScrollPositionOnDestroy()) {
            this.mViewModel.getLibraryStateHelper().saveScrollPositionState(((GridLayoutManager) this.mBinding.libraryContent.getLayoutManager()).findFirstVisibleItemPosition());
        }
        this.mContentAdapter.destroy();
        this.mPanelApp.releaseLibraryViewModel();
        this.mPanelApp.releaseSettingsViewModel();
        HorizonUtil.unregisterAppsChangeListener(getContext(), this.mAppsChangeListener);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
            HmdInactivityUtil.removeHmdInactivityObserver(this);
        }
        Fresco.getImagePipeline().clearMemoryCaches();
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (onboardingTutorial != null && (onboardingTutorialChangeListener = this.mOnboardingTutorialChangeListener) != null) {
            onboardingTutorial.removeOnChangeListener(onboardingTutorialChangeListener);
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing LibraryView");
        this.mLibraryLogger.initializeLibrarySessionId();
        this.mLibraryLogger.logLibrarySectionEntry();
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (onboardingTutorial != null && onboardingTutorial.isActive()) {
            this.mViewModel.setScrollEnabled(false);
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        if (!TextUtils.isEmpty(lowerCase) && TabletDeepLinkingUriUtil.isLibraryTabletDeepLinkUri(lowerCase) && !TabletDeepLinkingUriUtil.AUI_LIBRARY_URI.equals(lowerCase)) {
            this.mViewModel.getLibraryStateHelper().saveDefaultScrollPositionState();
            applyDeepLinkToLibraryFilter(lowerCase);
        }
        this.mContentAdapter.notifyDataSetChanged();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding LibraryView");
        this.mLibraryLogger.destroyLibrarySessionId();
        dismissDropdowns();
        this.mViewModel.resetScrollEnabled();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dismissDropdowns() {
        this.mBinding.platformSelect.dismissDropdown();
        this.mBinding.sorterSelect.dismissDropdown();
        this.mBinding.filterSelect.dismissDropdown();
        this.mContentAdapter.getDropdown().dismiss();
    }

    private void initializeDropdowns() {
        this.mViewModel.setPlatformOptions(new ArrayList(Arrays.asList(LibraryPlatform.ANDROID_6DOF, LibraryPlatform.ANDROID_3DOF)));
        this.mViewModel.setSorterOptions(new ArrayList(Arrays.asList(LibrarySorter.MOST_RECENT, LibrarySorter.A_TO_Z, LibrarySorter.Z_TO_A)));
        LibraryPlatform loadLastPlatformState = this.mViewModel.getLibraryStateHelper().loadLastPlatformState();
        LibrarySorter loadLastSorterState = this.mViewModel.getLibraryStateHelper().loadLastSorterState();
        LibraryFilter loadLastFilterState = this.mViewModel.getLibraryStateHelper().loadLastFilterState();
        LibraryViewModel libraryViewModel = this.mViewModel;
        if (loadLastPlatformState == null) {
            loadLastPlatformState = LibraryPlatform.getDefault();
        }
        libraryViewModel.setCurrentPlatform(loadLastPlatformState);
        LibraryViewModel libraryViewModel2 = this.mViewModel;
        if (loadLastSorterState == null) {
            loadLastSorterState = LibrarySorter.getDefault();
        }
        libraryViewModel2.setCurrentSorter(loadLastSorterState);
        LibraryViewModel libraryViewModel3 = this.mViewModel;
        if (loadLastFilterState == null) {
            loadLastFilterState = LibraryFilter.getDefault();
        }
        libraryViewModel3.setCurrentFilter(loadLastFilterState);
        this.mBinding.platformSelect.setVisibilityCallback(this.mDropdownVisibilityCallback);
        this.mBinding.sorterSelect.setVisibilityCallback(this.mDropdownVisibilityCallback);
        this.mBinding.filterSelect.setVisibilityCallback(this.mDropdownVisibilityCallback);
        this.mBinding.platformSelect.setEventHandler(this.mPanelApp);
        this.mBinding.sorterSelect.setEventHandler(this.mPanelApp);
        this.mBinding.filterSelect.setEventHandler(this.mPanelApp);
        this.mBinding.platformSelect.setLogger(createDropdownLogger(LibraryLogger.DROPDOWN_PLATFORM));
        this.mBinding.sorterSelect.setLogger(createDropdownLogger(LibraryLogger.DROPDOWN_SORTER));
        this.mBinding.filterSelect.setLogger(createDropdownLogger(LibraryLogger.DROPDOWN_FILTER));
    }

    private void initializeLibraryContent() {
        OCRecyclerView oCRecyclerView = this.mBinding.libraryContent;
        oCRecyclerView.setHasFixedSize(true);
        oCRecyclerView.setScrollBarStyle(33554432);
        this.mContentAdapter = new LibraryContentAdapter(getContext(), this.mPanelApp, this.mViewModel, this.mSettingsViewModel, this.mScrollCallback, this.mLibraryLogger);
        this.mContentAdapter.setDropdownVisibilityCallback(this.mDropdownVisibilityCallback);
        oCRecyclerView.setAdapter(this.mContentAdapter);
        final AnonymousClass2 r2 = new GridLayoutManager(getContext(), 4) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass2 */

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager, androidx.recyclerview.widget.LinearLayoutManager
            public boolean canScrollVertically() {
                return LibraryView.this.mViewModel.getIsScrollEnabled() && super.canScrollVertically();
            }
        };
        r2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass3 */

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                int itemViewType = LibraryView.this.mContentAdapter.getItemViewType(i);
                if (itemViewType == 3 || itemViewType == 7) {
                    return 1;
                }
                return r2.getSpanCount();
            }
        });
        oCRecyclerView.setLayoutManager(r2);
        oCRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass4 */

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 != 0 && LibraryView.this.mBackToTopButtonManager != null) {
                    if (i2 > 0) {
                        LibraryView.this.mBackToTopButtonManager.maybeStopScrollToTopAnimation();
                    }
                    int findFirstVisibleItemPosition = r2.findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition >= 0) {
                        LibraryView.this.mBackToTopButtonManager.doActionBasedOnPosition(findFirstVisibleItemPosition);
                    }
                }
            }
        });
        oCRecyclerView.setLogger(new OCRecyclerViewLogger() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass5 */

            @Override // com.oculus.ocui.logging.OCRecyclerViewLogger
            public void onScrollStart() {
            }

            @Override // com.oculus.ocui.logging.OCRecyclerViewLogger
            public void onScrollEnd() {
                int findFirstVisibleItemPosition = r2.findFirstVisibleItemPosition();
                if (findFirstVisibleItemPosition >= 0) {
                    LibraryView.this.mLibraryLogger.logLibraryScroll(findFirstVisibleItemPosition);
                }
            }
        });
        this.mViewModel.getLibraryFetcher().fetchHorizonAppsAsync(true);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_APPS_DISK_CACHE)) {
            this.mViewModel.loadAppsFromCacheAsync();
        }
    }

    private void initializeScrollCallback() {
        this.mScrollCallback = new ScrollCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass6 */

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void onUpdate() {
                int scrollToPosition;
                if (LibraryView.this.mPendingScrollUpdate != null && LibraryView.this.mBinding != null && LibraryView.this.mBinding.libraryContent != null && LibraryView.this.mPendingScrollUpdate.hasPendingScrollAction()) {
                    Log.d(LibraryView.TAG, String.format("Scroll to position: %d", Integer.valueOf(LibraryView.this.mPendingScrollUpdate.getScrollToPosition())));
                    LibraryView.this.mBinding.libraryContent.scrollToPosition(LibraryView.this.mPendingScrollUpdate.getScrollToPosition());
                    if (LibraryView.this.mBackToTopButtonManager != null && (scrollToPosition = LibraryView.this.mPendingScrollUpdate.getScrollToPosition()) >= 0) {
                        LibraryView.this.mBackToTopButtonManager.doActionBasedOnPosition(scrollToPosition);
                    }
                    LibraryView.this.mPendingScrollUpdate.resetPendingScrollAction();
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void addPendingScrollToPosition(int i) {
                if (LibraryView.this.mPendingScrollUpdate != null) {
                    LibraryView.this.mPendingScrollUpdate.addPendingScrollToPosition(i);
                    Log.d(LibraryView.TAG, String.format("Setting pending scroll position: %d", Integer.valueOf(LibraryView.this.mPendingScrollUpdate.getScrollToPosition())));
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void smoothScrollToPosition(int i) {
                if (LibraryView.this.mBinding != null && LibraryView.this.mBinding.libraryContent != null) {
                    LibraryView.this.mBackToTopButtonManager.animateScrollToTop(LibraryView.this.mBinding.libraryContent);
                }
            }
        };
    }

    private void initializeBackToTopButton() {
        this.mBinding.backToTopButton.setEventHandler(this.mPanelApp);
        this.mBackToTopButtonManager.initialize(new BackToTopButtonProvider(this.mBinding.backToTopButtonHeightLayout, this.mBinding.backToTopButton), this.mScrollCallback, new BackToTopButtonActionCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass7 */

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onClick() {
                LibraryView.this.dismissDropdowns();
                LibraryView.this.mViewModel.setScrollEnabled(true);
            }

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onHoverEnter() {
                if (!LibraryView.this.mBackToTopButtonManager.isScrollToTopAnimationRunning()) {
                    LibraryView.this.mViewModel.setScrollEnabled(false);
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onHoverExit() {
                LibraryView.this.mViewModel.setScrollEnabled(true);
            }
        });
    }

    private void checkBootTimeAndMaybeResetLibraryState() {
        long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        if (Math.abs(currentTimeMillis - this.mViewModel.getLibraryStateHelper().loadBootTime()) > 5) {
            Log.d(TAG, "Clearing library state on new power cycle");
            LibraryStateHelper libraryStateHelper = this.mViewModel.getLibraryStateHelper();
            if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
                libraryStateHelper.saveDefaultDropdownsState();
            }
            libraryStateHelper.saveDefaultScrollPositionState();
            libraryStateHelper.saveBootTime(currentTimeMillis);
            this.mLibraryLogger.logLibraryStateReset(LibraryLogger.STATE_RESET_REASON_HMD_REBOOT);
        }
    }

    private void initializeOnBoardingTutorial() {
        OnboardingTutorial onboardingTutorial = this.mPanelApp.getOnboardingTutorial();
        if (onboardingTutorial.isActive()) {
            this.mOnboardingTutorial = onboardingTutorial;
            this.mOnboardingTutorialChangeListener = new OnboardingTutorial.OnboardingTutorialChangeListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryView$MBHJuSktcCyBYfAw1wR_IlIURLU */

                @Override // com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.OnboardingTutorialChangeListener
                public final void onStepChanged(OnboardingTutorial.Step step) {
                    LibraryView.this.lambda$initializeOnBoardingTutorial$114$LibraryView(step);
                }
            };
            this.mOnboardingTutorial.addOnChangeListener(this.mOnboardingTutorialChangeListener);
            this.mOnboardingTutorialChangeListener.onStepChanged(this.mOnboardingTutorial.getCurrentStep());
        }
    }

    public /* synthetic */ void lambda$initializeOnBoardingTutorial$114$LibraryView(OnboardingTutorial.Step step) {
        this.mViewModel.setHighlightHome(step == OnboardingTutorial.Step.LIBRARY);
        this.mViewModel.setCurrentFilter(LibraryFilter.getDefault());
        boolean z = !this.mOnboardingTutorial.isActive();
        this.mBinding.sorterSelect.setClickable(z);
        this.mBinding.filterSelect.setClickable(z);
        if (z) {
            this.mViewModel.onFinishedOnboardingTutorial();
            this.mContentAdapter.notifyDataSetChanged();
        }
    }

    private void applyDeepLinkToLibraryFilter(String str) {
        if (TabletDeepLinkingUriUtil.LIBRARY_ALL_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.ALL);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_OCULUS_APPS_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.OCULUS_APPS);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_INSTALLED_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.INSTALLED);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_UPDATES_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.UPDATES);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_NOT_INSTALLED_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.NOT_INSTALLED);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_DEMOS_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.DEMOS);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_TUTORIALS_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.TUTORIALS);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_SHARED_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.SHARED);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_UNKNOWN_SOURCES_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.UNKNOWN_SOURCES);
        } else if (TabletDeepLinkingUriUtil.LIBRARY_PROTOTYPES_URI.equals(str)) {
            this.mViewModel.setCurrentFilter(LibraryFilter.PROTOTYPES);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.HmdInactivityUtil.HmdInactivityObserver
    public void onHmdInactivityEnd(long j) {
        if (j > (this.mPanelApp.isOCShellAutomationEnabled() ? 5000 : LibraryStateHelper.INACTIVITY_LIBRARY_STATE_RESET_THRESHOLD)) {
            if (this.mPanelApp.isGKEnabled(Gatekeeper.LIBRARY_STATE_AUTO_RESET)) {
                this.mViewModel.resetDropdowns();
            }
            this.mViewModel.resetScrollPosition();
        }
    }

    private OCSelectLogger createDropdownLogger(final String str) {
        return new OCSelectLogger() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView.AnonymousClass8 */

            @Override // com.oculus.ocui.logging.OCSelectLogger
            public void onLogClick() {
                LibraryView.this.mLibraryLogger.logDropdownClick(str);
            }

            @Override // com.oculus.ocui.logging.OCSelectLogger
            public void onLogOptionSelected(Object obj, Object obj2) {
                LibraryView.this.mLibraryLogger.logDropdownOptionClick(str, obj.toString(), obj2.toString());
            }
        };
    }
}
