package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleTabletTopBarViewModel;
import com.oculus.socialplatform.R;

public class PeopleTabletTopBarBindingImpl extends PeopleTabletTopBarBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(PeopleTabletTopBarViewModel peopleTabletTopBarViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 70) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != 68) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        int i = 0;
        PeopleTabletTopBarViewModel peopleTabletTopBarViewModel = this.mViewModel;
        if ((15 & j) != 0) {
            if (!((j & 11) == 0 || peopleTabletTopBarViewModel == null)) {
                str = peopleTabletTopBarViewModel.mTitleText;
            }
            if (!((j & 13) == 0 || peopleTabletTopBarViewModel == null)) {
                i = peopleTabletTopBarViewModel.mSearchIconVisibility;
            }
        }
        if ((j & 11) != 0) {
            C11051qV.A02(this.peopleTitle, str);
        }
        if ((j & 13) != 0) {
            this.searchButton.setVisibility(i);
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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleTabletTopBarBinding
    public void setViewModel(@Nullable PeopleTabletTopBarViewModel peopleTabletTopBarViewModel) {
        updateRegistration(0, peopleTabletTopBarViewModel);
        this.mViewModel = peopleTabletTopBarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.connections_button, 3);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleTabletTopBarViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleTabletTopBarViewModel) obj);
        return true;
    }

    public PeopleTabletTopBarBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleTabletTopBarBindingImpl(AbstractC003408r r11, View view, Object[] objArr) {
        super(r11, view, 1, (OCButton) objArr[3], (ConstraintLayout) objArr[0], (OCTextView) objArr[1], (OCButton) objArr[2]);
        this.mDirtyFlags = -1;
        this.container.setTag(null);
        this.peopleTitle.setTag(null);
        this.searchButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
