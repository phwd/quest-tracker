package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCTextView;

public class OcbadgeBindingImpl extends OcbadgeBinding {
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
        String str = this.mBadgeCount;
        if ((j & 3) != 0) {
            C11051qV.A02(this.text, str);
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

    @Override // com.oculus.common.ocui.databinding.OcbadgeBinding
    public void setBadgeCount(@Nullable String str) {
        this.mBadgeCount = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(172);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (172 != i) {
            return false;
        }
        setBadgeCount((String) obj);
        return true;
    }

    public OcbadgeBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OcbadgeBindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (OCTextView) objArr[0]);
        this.mDirtyFlags = -1;
        this.text.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
