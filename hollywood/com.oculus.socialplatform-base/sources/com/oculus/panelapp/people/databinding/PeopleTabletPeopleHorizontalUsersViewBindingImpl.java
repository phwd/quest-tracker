package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCRecyclerView;

public class PeopleTabletPeopleHorizontalUsersViewBindingImpl extends PeopleTabletPeopleHorizontalUsersViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
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
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public PeopleTabletPeopleHorizontalUsersViewBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public PeopleTabletPeopleHorizontalUsersViewBindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (OCRecyclerView) objArr[0]);
        this.mDirtyFlags = -1;
        this.horizontalRecycler.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
