package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.views.PeopleNavBar;
import com.oculus.panelapp.people.views.PeopleTabletTopBar;
import com.oculus.panelapp.people.views.PeopleViewModel;
import com.oculus.socialplatform.R;

public class PeopleViewBindingImpl extends PeopleViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    private boolean onChangeViewModel(PeopleViewModel peopleViewModel, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        boolean z;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PeopleViewModel peopleViewModel = this.mViewModel;
        long j3 = j & 3;
        int i = 0;
        if (j3 != 0) {
            if (peopleViewModel != null) {
                z = peopleViewModel.mIsLoading;
            } else {
                z = false;
            }
            if (j3 != 0) {
                if (z) {
                    j2 = 8;
                } else {
                    j2 = 4;
                }
                j |= j2;
            }
            if (!z) {
                i = 8;
            }
        }
        if ((j & 3) != 0) {
            this.loadingSpinner.setVisibility(i);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleViewBinding
    public void setViewModel(@Nullable PeopleViewModel peopleViewModel) {
        updateRegistration(0, peopleViewModel);
        this.mViewModel = peopleViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.people_view_side_nav, 2);
        sparseIntArray.put(R.id.people_tablet_top_bar, 3);
        sparseIntArray.put(R.id.header_guideline, 4);
        sparseIntArray.put(R.id.people_nav_bar, 5);
        sparseIntArray.put(R.id.people_list, 6);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleViewModel) obj);
        return true;
    }

    public PeopleViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleViewBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (Guideline) objArr[4], (ProgressBar) objArr[1], (OCRecyclerView) objArr[6], (PeopleNavBar) objArr[5], (PeopleTabletTopBar) objArr[3], (SocialTabletSideNav) objArr[2]);
        this.mDirtyFlags = -1;
        this.loadingSpinner.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
