package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;

public class AnytimeTabletSocialOfflineBindingImpl extends AnytimeTabletSocialOfflineBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mTitle;
        String str2 = this.mSubtitle;
        long j2 = 5 & j;
        if ((j & 6) != 0) {
            C11051qV.A02(this.offlineSubtitle, str2);
        }
        if (j2 != 0) {
            C11051qV.A02(this.offlineTitle, str);
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
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding
    public void setSubtitle(@Nullable String str) {
        this.mSubtitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(80);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBinding
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
        if (67 == i) {
            setTitle((String) obj);
            return true;
        } else if (80 != i) {
            return false;
        } else {
            setSubtitle((String) obj);
            return true;
        }
    }

    public AnytimeTabletSocialOfflineBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletSocialOfflineBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (OCTextView) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.offlineSubtitle.setTag(null);
        this.offlineTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
