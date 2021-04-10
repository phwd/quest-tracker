package com.oculus.tablet.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.vrshell.panels.views.ShellButton;

public class CommonTabletRectangularButtonBindingImpl extends CommonTabletRectangularButtonBinding {
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
        Drawable drawable = this.mIcon;
        String str = this.mText;
        long j2 = 5 & j;
        long j3 = j & 6;
        if (j2 != 0) {
            C11051qV.A00(this.button, drawable);
        }
        if (j3 != 0) {
            C11051qV.A02(this.button, str);
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

    @Override // com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(211);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding
    public void setText(@Nullable String str) {
        this.mText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(234);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (211 == i) {
            setIcon((Drawable) obj);
            return true;
        } else if (234 != i) {
            return false;
        } else {
            setText((String) obj);
            return true;
        }
    }

    public CommonTabletRectangularButtonBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public CommonTabletRectangularButtonBindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (ShellButton) objArr[0]);
        this.mDirtyFlags = -1;
        this.button.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
