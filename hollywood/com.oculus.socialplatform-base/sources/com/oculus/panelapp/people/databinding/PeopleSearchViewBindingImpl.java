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
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.views.PeopleSearchTopBar;
import com.oculus.panelapp.people.views.PeopleSearchViewModel;
import com.oculus.socialplatform.R;

public class PeopleSearchViewBindingImpl extends PeopleSearchViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    private boolean onChangeViewModel(PeopleSearchViewModel peopleSearchViewModel, int i) {
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
        int i;
        boolean z;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PeopleSearchViewModel peopleSearchViewModel = this.mViewModel;
        long j4 = j & 3;
        int i2 = 0;
        if (j4 != 0) {
            if (peopleSearchViewModel != null) {
                z = peopleSearchViewModel.mIsLoading;
            } else {
                z = false;
            }
            if (j4 != 0) {
                if (z) {
                    j2 = j | 8;
                    j3 = 32;
                } else {
                    j2 = j | 4;
                    j3 = 16;
                }
                j = j2 | j3;
            }
            i = 8;
            if (!z) {
                i = 0;
                i2 = 8;
            }
        } else {
            i = 0;
        }
        if ((j & 3) != 0) {
            this.loadingSpinner.setVisibility(i2);
            this.peopleList.setVisibility(i);
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

    @Override // com.oculus.panelapp.people.databinding.PeopleSearchViewBinding
    public void setViewModel(@Nullable PeopleSearchViewModel peopleSearchViewModel) {
        updateRegistration(0, peopleSearchViewModel);
        this.mViewModel = peopleSearchViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.people_search_top_bar, 3);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleSearchViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleSearchViewModel) obj);
        return true;
    }

    public PeopleSearchViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleSearchViewBindingImpl(AbstractC003408r r10, View view, Object[] objArr) {
        super(r10, view, 1, (ProgressBar) objArr[1], (OCRecyclerView) objArr[2], (PeopleSearchTopBar) objArr[3]);
        this.mDirtyFlags = -1;
        this.loadingSpinner.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.peopleList.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
