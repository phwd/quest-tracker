package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public class OcemptyLayoutBindingImpl extends OcemptyLayoutBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final RelativeLayout mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mHeader;
        Drawable drawable = this.mSplash;
        String str2 = this.mButtonText;
        long j2 = 9 & j;
        long j3 = 10 & j;
        if ((j & 12) != 0) {
            C11051qV.A02(this.cta, str2);
        }
        if (j2 != 0) {
            C11051qV.A02(this.headerText, str);
        }
        if (j3 != 0) {
            this.splashImage.setImageDrawable(drawable);
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

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setButtonText(@Nullable String str) {
        this.mButtonText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(168);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setHeader(@Nullable String str) {
        this.mHeader = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(178);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setSplash(@Nullable Drawable drawable) {
        this.mSplash = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(181);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (178 == i) {
            setHeader((String) obj);
            return true;
        } else if (181 == i) {
            setSplash((Drawable) obj);
            return true;
        } else if (168 != i) {
            return false;
        } else {
            setButtonText((String) obj);
            return true;
        }
    }

    public OcemptyLayoutBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 4, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OcemptyLayoutBindingImpl(AbstractC003408r r10, View view, Object[] objArr) {
        super(r10, view, 0, (OCButton) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[1]);
        this.mDirtyFlags = -1;
        this.cta.setTag(null);
        this.headerText.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        this.splashImage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
