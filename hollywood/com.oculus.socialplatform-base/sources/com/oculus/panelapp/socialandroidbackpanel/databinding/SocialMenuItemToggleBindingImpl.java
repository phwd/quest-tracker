package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;

public class SocialMenuItemToggleBindingImpl extends SocialMenuItemToggleBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

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
            this.mDirtyFlags = 16;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mBottomText;
        String str2 = this.mTopText;
        Drawable drawable = this.mIcon;
        boolean z = this.mChecked;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 20 & j;
        long j5 = j & 24;
        if (j2 != 0) {
            C11051qV.A02(this.menuItemBottomText, str);
        }
        if (j4 != 0) {
            this.menuItemIconImage.setImageDrawable(drawable);
        }
        if (j3 != 0) {
            C11051qV.A02(this.menuItemTopText, str2);
        }
        if (j5 != 0) {
            OCToggle oCToggle = this.toggle;
            if (oCToggle.isChecked() != z) {
                oCToggle.setChecked(z);
            }
        }
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialMenuItemToggleBinding
    public void setBottomText(@Nullable String str) {
        this.mBottomText = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(217);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialMenuItemToggleBinding
    public void setChecked(boolean z) {
        this.mChecked = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(189);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialMenuItemToggleBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(211);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialMenuItemToggleBinding
    public void setTopText(@Nullable String str) {
        this.mTopText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(200);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (217 == i) {
            setBottomText((String) obj);
            return true;
        } else if (200 == i) {
            setTopText((String) obj);
            return true;
        } else if (211 == i) {
            setIcon((Drawable) obj);
            return true;
        } else if (189 != i) {
            return false;
        } else {
            setChecked(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public SocialMenuItemToggleBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 5, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public SocialMenuItemToggleBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (OCTextView) objArr[3], (ImageView) objArr[1], (ConstraintLayout) objArr[0], (OCTextView) objArr[2], (OCToggle) objArr[4]);
        this.mDirtyFlags = -1;
        this.menuItemBottomText.setTag(null);
        this.menuItemIconImage.setTag(null);
        this.menuItemToggle.setTag(null);
        this.menuItemTopText.setTag(null);
        this.toggle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
