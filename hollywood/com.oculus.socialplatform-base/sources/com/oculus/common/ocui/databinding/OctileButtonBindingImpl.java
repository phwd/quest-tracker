package com.oculus.common.ocui.databinding;

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
import com.oculus.socialplatform.R;

public class OctileButtonBindingImpl extends OctileButtonBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;
    @NonNull
    public final OCTextView mboundView2;
    @NonNull
    public final OCTextView mboundView6;

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
            this.mDirtyFlags = 128;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cta_icon, 7);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mLabel;
        Drawable drawable = this.mActiveIndicator;
        String str2 = this.mTitle;
        Drawable drawable2 = this.mTitleIcon;
        String str3 = this.mSubtitle;
        String str4 = this.mCtaText;
        Drawable drawable3 = this.mBackground;
        long j2 = 129 & j;
        long j3 = 130 & j;
        long j4 = j & 132;
        long j5 = j & 136;
        long j6 = j & 144;
        long j7 = j & 160;
        long j8 = j & 192;
        if (j3 != 0) {
            C11051qV.A01(this.activeIndicatorView, drawable);
        }
        if (j8 != 0) {
            this.mboundView0.setBackground(drawable3);
        }
        if (j2 != 0) {
            C11051qV.A02(this.mboundView2, str);
        }
        if (j7 != 0) {
            C11051qV.A02(this.mboundView6, str4);
        }
        if (j6 != 0) {
            C11051qV.A02(this.subtitle, str3);
        }
        if (j4 != 0) {
            C11051qV.A02(this.title, str2);
        }
        if (j5 != 0) {
            this.titleIconImage.setImageDrawable(drawable2);
        }
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setActiveIndicator(@Nullable Drawable drawable) {
        this.mActiveIndicator = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(167);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(177);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setCtaText(@Nullable String str) {
        this.mCtaText = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setLabel(@Nullable String str) {
        this.mLabel = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(171);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setSubtitle(@Nullable String str) {
        this.mSubtitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(80);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setTitle(@Nullable String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(67);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setTitleIcon(@Nullable Drawable drawable) {
        this.mTitleIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(180);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (171 == i) {
            setLabel((String) obj);
            return true;
        } else if (167 == i) {
            setActiveIndicator((Drawable) obj);
            return true;
        } else if (67 == i) {
            setTitle((String) obj);
            return true;
        } else if (180 == i) {
            setTitleIcon((Drawable) obj);
            return true;
        } else if (80 == i) {
            setSubtitle((String) obj);
            return true;
        } else if (16 == i) {
            setCtaText((String) obj);
            return true;
        } else if (177 != i) {
            return false;
        } else {
            setBackground((Drawable) obj);
            return true;
        }
    }

    public OctileButtonBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 8, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public OctileButtonBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (OCTextView) objArr[5], (ImageView) objArr[7], (OCTextView) objArr[4], (OCTextView) objArr[3], (ImageView) objArr[1]);
        this.mDirtyFlags = -1;
        this.activeIndicatorView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        OCTextView oCTextView = (OCTextView) objArr[2];
        this.mboundView2 = oCTextView;
        oCTextView.setTag(null);
        OCTextView oCTextView2 = (OCTextView) objArr[6];
        this.mboundView6 = oCTextView2;
        oCTextView2.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        this.titleIconImage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
