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
import com.oculus.panelapp.people.views.PeopleNavBarViewModel;
import com.oculus.socialplatform.R;

public class PeopleNavBarBindingImpl extends PeopleNavBarBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(PeopleNavBarViewModel peopleNavBarViewModel, int i) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PeopleNavBarViewModel peopleNavBarViewModel = this.mViewModel;
        String str = null;
        long j2 = j & 3;
        if (!(j2 == 0 || peopleNavBarViewModel == null)) {
            str = peopleNavBarViewModel.friendButtonText;
        }
        if (j2 != 0) {
            C11051qV.A02(this.friendsButton, str);
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

    @Override // com.oculus.panelapp.people.databinding.PeopleNavBarBinding
    public void setViewModel(@Nullable PeopleNavBarViewModel peopleNavBarViewModel) {
        updateRegistration(0, peopleNavBarViewModel);
        this.mViewModel = peopleNavBarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.people_nearby_button, 2);
        sparseIntArray.put(R.id.suggested_button, 3);
        sparseIntArray.put(R.id.friend_requests_button, 4);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleNavBarViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleNavBarViewModel) obj);
        return true;
    }

    public PeopleNavBarBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 5, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleNavBarBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 1, (OCButton) objArr[4], (OCButton) objArr[1], (ConstraintLayout) objArr[0], (OCButton) objArr[2], (OCButton) objArr[3]);
        this.mDirtyFlags = -1;
        this.friendsButton.setTag(null);
        this.peopleNavBar.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
