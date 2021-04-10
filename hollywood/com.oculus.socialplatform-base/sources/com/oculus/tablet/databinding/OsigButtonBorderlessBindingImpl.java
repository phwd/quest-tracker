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
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;

public class OsigButtonBorderlessBindingImpl extends OsigButtonBorderlessBinding {
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
        boolean z;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mIcon;
        Drawable drawable2 = this.mActiveIndicator;
        String str = this.mText;
        Boolean bool = this.mEnabled;
        long j3 = j & 24;
        boolean z2 = true;
        boolean z3 = false;
        if (j3 != 0) {
            z = false;
            if (bool != null) {
                z = true;
            }
            if (j3 != 0) {
                if (z) {
                    j2 = 64;
                } else {
                    j2 = 32;
                }
                j |= j2;
            }
        } else {
            z = false;
        }
        long j4 = 24 & j;
        if (j4 != 0) {
            if (z) {
                z2 = bool.booleanValue();
            }
            z3 = AnonymousClass1uW.safeUnbox(Boolean.valueOf(z2));
        }
        if (j4 != 0) {
            this.button.setEnabled(z3);
        }
        if ((20 & j) != 0) {
            C11051qV.A02(this.button, str);
        }
        if ((17 & j) != 0) {
            C11051qV.A01(this.button, drawable);
        }
        if ((j & 18) != 0 && AnonymousClass1uW.SDK_INT >= 23) {
            this.buttonForeground.setForeground(drawable2);
        }
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setActiveIndicator(@Nullable Drawable drawable) {
        this.mActiveIndicator = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(167);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setEnabled(@Nullable Boolean bool) {
        this.mEnabled = bool;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(174);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(211);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setText(@Nullable String str) {
        this.mText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(234);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (211 == i) {
            setIcon((Drawable) obj);
            return true;
        } else if (167 == i) {
            setActiveIndicator((Drawable) obj);
            return true;
        } else if (234 == i) {
            setText((String) obj);
            return true;
        } else if (174 != i) {
            return false;
        } else {
            setEnabled((Boolean) obj);
            return true;
        }
    }

    public OsigButtonBorderlessBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OsigButtonBorderlessBindingImpl(AbstractC003408r r10, View view, Object[] objArr) {
        super(r10, view, 0, (OCButton) objArr[1], (View) objArr[2], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.button.setTag(null);
        this.buttonForeground.setTag(null);
        this.container.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
