package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.guidebar.QPGuideBarView;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.BindingUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import java.util.List;
import java.util.Map;

public class AnytimeTabletLibraryViewV2BindingImpl extends AnytimeTabletLibraryViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private InverseBindingListener filterSelectselectedFilterAttrChanged;
    private long mDirtyFlags;
    private InverseBindingListener mOldEventSelectedFilterAttrChanged1567378957;
    private InverseBindingListener mOldEventSelectedPlatformAttrChanged459927723;
    private InverseBindingListener mOldEventSelectedSorterAttrChanged1529934315;
    private LibraryFilter mOldViewModelCurrentFilter;
    private LibraryPlatform mOldViewModelCurrentPlatform;
    private LibrarySorter mOldViewModelCurrentSorter;
    private Map<LibraryFilter, Integer> mOldViewModelFilterCounts;
    private List<LibraryFilter> mOldViewModelFilterOptions;
    private List<LibraryPlatform> mOldViewModelPlatformOptions;
    private List<LibrarySorter> mOldViewModelSorterOptions;
    @NonNull
    private final LibraryView mboundView0;
    private InverseBindingListener platformSelectselectedPlatformAttrChanged;
    private InverseBindingListener sorterSelectselectedSorterAttrChanged;

    static {
        sViewsWithIds.put(R.id.tablet_title, 5);
        sViewsWithIds.put(R.id.back_to_top_button_height_layout, 6);
        sViewsWithIds.put(R.id.back_to_top_icon, 7);
        sViewsWithIds.put(R.id.back_to_top_text, 8);
        sViewsWithIds.put(R.id.back_to_top_button, 9);
        sViewsWithIds.put(R.id.qp_guide_bar, 10);
        sViewsWithIds.put(R.id.guide_bar_body, 11);
        sViewsWithIds.put(R.id.guide_bar_icon, 12);
        sViewsWithIds.put(R.id.guide_bar_text, 13);
        sViewsWithIds.put(R.id.guide_bar_cta, 14);
        sViewsWithIds.put(R.id.dismiss_cta_icon, 15);
    }

    public AnytimeTabletLibraryViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletLibraryViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[9], (LinearLayout) objArr[6], (ImageView) objArr[7], (OCTextView) objArr[8], (OCButton) objArr[15], (OCSelect) objArr[3], (View) objArr[11], (OCButton) objArr[14], (ImageView) objArr[12], (OCTextView) objArr[13], (OCRecyclerView) objArr[4], (OCSelect) objArr[1], (QPGuideBarView) objArr[10], (OCSelect) objArr[2], (OCTextView) objArr[5]);
        this.filterSelectselectedFilterAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2BindingImpl.AnonymousClass1 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                LibraryFilter captureSelectedFilter = BindingUtils.captureSelectedFilter(AnytimeTabletLibraryViewV2BindingImpl.this.filterSelect);
                LibraryViewModel libraryViewModel = AnytimeTabletLibraryViewV2BindingImpl.this.mViewModel;
                if (libraryViewModel != null) {
                    libraryViewModel.setCurrentFilter(captureSelectedFilter);
                }
            }
        };
        this.platformSelectselectedPlatformAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2BindingImpl.AnonymousClass2 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                LibraryPlatform captureSelectedPlatform = BindingUtils.captureSelectedPlatform(AnytimeTabletLibraryViewV2BindingImpl.this.platformSelect);
                LibraryViewModel libraryViewModel = AnytimeTabletLibraryViewV2BindingImpl.this.mViewModel;
                if (libraryViewModel != null) {
                    libraryViewModel.setCurrentPlatform(captureSelectedPlatform);
                }
            }
        };
        this.sorterSelectselectedSorterAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2BindingImpl.AnonymousClass3 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                LibrarySorter captureSelectedSorter = BindingUtils.captureSelectedSorter(AnytimeTabletLibraryViewV2BindingImpl.this.sorterSelect);
                LibraryViewModel libraryViewModel = AnytimeTabletLibraryViewV2BindingImpl.this.mViewModel;
                if (libraryViewModel != null) {
                    libraryViewModel.setCurrentSorter(captureSelectedSorter);
                }
            }
        };
        this.mDirtyFlags = -1;
        this.filterSelect.setTag(null);
        this.libraryContent.setTag(null);
        this.mboundView0 = (LibraryView) objArr[0];
        this.mboundView0.setTag(null);
        this.platformSelect.setTag(null);
        this.sorterSelect.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1024;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((LibraryViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding
    public void setViewModel(@Nullable LibraryViewModel libraryViewModel) {
        updateRegistration(0, libraryViewModel);
        this.mViewModel = libraryViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((LibraryViewModel) obj, i2);
    }

    private boolean onChangeViewModel(LibraryViewModel libraryViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.platformOptions) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.currentPlatform) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.platformDropdownEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.sorterOptions) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.currentSorter) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.filterOptions) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.filterCounts) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.currentFilter) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i != BR.items) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        LibrarySorter librarySorter;
        List<LibrarySorter> list;
        LibraryPlatform libraryPlatform;
        List<LibraryPlatform> list2;
        LibraryFilter libraryFilter;
        int i;
        List<LibraryItem> list3;
        Map<LibraryFilter, Integer> map;
        List<LibraryFilter> list4;
        List<LibraryFilter> list5;
        LibraryFilter libraryFilter2;
        List<LibraryPlatform> list6;
        LibraryPlatform libraryPlatform2;
        List<LibrarySorter> list7;
        LibrarySorter librarySorter2;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LibraryViewModel libraryViewModel = this.mViewModel;
        int i2 = 0;
        List<LibraryItem> list8 = null;
        if ((2047 & j) != 0) {
            if ((j & 1473) == 0 || libraryViewModel == null) {
                map = null;
                libraryFilter2 = null;
                list5 = null;
            } else {
                map = libraryViewModel.getFilterCounts();
                libraryFilter2 = libraryViewModel.getCurrentFilter();
                list5 = libraryViewModel.getFilterOptions();
            }
            if ((j & 1031) == 0 || libraryViewModel == null) {
                libraryPlatform2 = null;
                list6 = null;
            } else {
                libraryPlatform2 = libraryViewModel.getCurrentPlatform();
                list6 = libraryViewModel.getPlatformOptions();
            }
            int i3 = ((j & 1033) > 0 ? 1 : ((j & 1033) == 0 ? 0 : -1));
            if (i3 != 0) {
                if (libraryViewModel != null) {
                    z = libraryViewModel.getPlatformDropdownEnabled();
                } else {
                    z = false;
                }
                if (i3 != 0) {
                    j |= z ? 4096 : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
                if (!z) {
                    i2 = 8;
                }
            }
            if ((j & 1073) == 0 || libraryViewModel == null) {
                librarySorter2 = null;
                list7 = null;
            } else {
                librarySorter2 = libraryViewModel.getCurrentSorter();
                list7 = libraryViewModel.getSorterOptions();
            }
            if (!((j & 1537) == 0 || libraryViewModel == null)) {
                list8 = libraryViewModel.getItems();
            }
            i = i2;
            list3 = list8;
            libraryFilter = libraryFilter2;
            list4 = list5;
            libraryPlatform = libraryPlatform2;
            list2 = list6;
            librarySorter = librarySorter2;
            list = list7;
        } else {
            i = 0;
            list4 = null;
            map = null;
            list3 = null;
            libraryFilter = null;
            list2 = null;
            libraryPlatform = null;
            list = null;
            librarySorter = null;
        }
        int i4 = ((1473 & j) > 0 ? 1 : ((1473 & j) == 0 ? 0 : -1));
        if (i4 != 0) {
            BindingUtils.updateLibraryFilterDropdown(this.filterSelect, this.mOldViewModelFilterOptions, this.mOldViewModelFilterCounts, this.mOldViewModelCurrentFilter, this.mOldEventSelectedFilterAttrChanged1567378957, list4, map, libraryFilter, this.filterSelectselectedFilterAttrChanged);
        }
        if ((1024 & j) != 0) {
            BindingUtils.setLayoutPaddingHorizontal(this.libraryContent, this.libraryContent.getResources().getDimension(R.dimen.octablet_gutter) - this.libraryContent.getResources().getDimension(R.dimen.octablet_tile_padding));
        }
        if ((1537 & j) != 0) {
            BindingUtils.updateItems(this.libraryContent, list3);
        }
        if ((1033 & j) != 0) {
            this.platformSelect.setVisibility(i);
        }
        int i5 = ((1031 & j) > 0 ? 1 : ((1031 & j) == 0 ? 0 : -1));
        if (i5 != 0) {
            BindingUtils.updateLibraryPlatformDropdown(this.platformSelect, this.mOldViewModelPlatformOptions, this.mOldViewModelCurrentPlatform, this.mOldEventSelectedPlatformAttrChanged459927723, list2, libraryPlatform, this.platformSelectselectedPlatformAttrChanged);
        }
        int i6 = ((j & 1073) > 0 ? 1 : ((j & 1073) == 0 ? 0 : -1));
        if (i6 != 0) {
            BindingUtils.updateLibrarySorterDropdown(this.sorterSelect, this.mOldViewModelSorterOptions, this.mOldViewModelCurrentSorter, this.mOldEventSelectedSorterAttrChanged1529934315, list, librarySorter, this.sorterSelectselectedSorterAttrChanged);
        }
        if (i4 != 0) {
            this.mOldViewModelFilterOptions = list4;
            this.mOldViewModelFilterCounts = map;
            this.mOldViewModelCurrentFilter = libraryFilter;
            this.mOldEventSelectedFilterAttrChanged1567378957 = this.filterSelectselectedFilterAttrChanged;
        }
        if (i5 != 0) {
            this.mOldViewModelPlatformOptions = list2;
            this.mOldViewModelCurrentPlatform = libraryPlatform;
            this.mOldEventSelectedPlatformAttrChanged459927723 = this.platformSelectselectedPlatformAttrChanged;
        }
        if (i6 != 0) {
            this.mOldViewModelSorterOptions = list;
            this.mOldViewModelCurrentSorter = librarySorter;
            this.mOldEventSelectedSorterAttrChanged1529934315 = this.sorterSelectselectedSorterAttrChanged;
        }
    }
}
