package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCTextView;

public class PeopleSearchResultHeaderBindingImpl extends PeopleSearchResultHeaderBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mTitle;
        long j2 = 3 & j;
        if ((j & 2) != 0 && AnonymousClass1uW.SDK_INT >= 11) {
            this.peopleTabletHeader.setAlpha(1.0f);
        }
        if (j2 != 0) {
            C11051qV.A02(this.peopleTabletHeader, str);
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

    @Override // com.oculus.panelapp.people.databinding.PeopleSearchResultHeaderBinding
    public void setTitle(@Nullable String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(67);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (67 != i) {
            return false;
        }
        setTitle((String) obj);
        return true;
    }

    public PeopleSearchResultHeaderBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public PeopleSearchResultHeaderBindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (OCTextView) objArr[0]);
        this.mDirtyFlags = -1;
        this.peopleTabletHeader.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
