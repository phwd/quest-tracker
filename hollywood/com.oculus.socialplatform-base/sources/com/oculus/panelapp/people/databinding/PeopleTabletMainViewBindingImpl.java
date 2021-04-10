package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.panelapp.people.views.PeopleSearchView;
import com.oculus.panelapp.people.views.PeopleTabletMainView;
import com.oculus.panelapp.people.views.PeopleView;

public class PeopleTabletMainViewBindingImpl extends PeopleTabletMainViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final PeopleTabletMainView mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = this.mShowSearch;
        long j4 = j & 3;
        int i2 = 0;
        if (j4 != 0) {
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
            this.peopleSearchView.setVisibility(i2);
            this.peopleView.setVisibility(i);
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

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleTabletMainViewBinding
    public void setShowSearch(boolean z) {
        this.mShowSearch = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(71);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (71 != i) {
            return false;
        }
        setShowSearch(((Boolean) obj).booleanValue());
        return true;
    }

    public PeopleTabletMainViewBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public PeopleTabletMainViewBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (PeopleSearchView) objArr[1], (PeopleView) objArr[2]);
        this.mDirtyFlags = -1;
        PeopleTabletMainView peopleTabletMainView = (PeopleTabletMainView) objArr[0];
        this.mboundView0 = peopleTabletMainView;
        peopleTabletMainView.setTag(null);
        this.peopleSearchView.setTag(null);
        this.peopleView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
