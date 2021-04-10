package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.socialplatform.R;

public class AnytimeTabletSocialHorizontalUsersViewBindingImpl extends AnytimeTabletSocialHorizontalUsersViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
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

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.horizontal_recycler, 1);
    }

    public AnytimeTabletSocialHorizontalUsersViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 2, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletSocialHorizontalUsersViewBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (OCRecyclerView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.socialHorizontalCards.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
