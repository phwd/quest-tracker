package com.oculus.panelapp.parties.databinding;

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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;

public class InviteToPartyCtaButtonBindingImpl extends InviteToPartyCtaButtonBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        int i2;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mCtaIcon;
        String str = this.mCtaText;
        boolean z = this.mIsLoading;
        long j4 = j & 12;
        boolean z2 = false;
        int i3 = 0;
        if (j4 != 0) {
            if (j4 != 0) {
                if (z) {
                    j2 = j | 32;
                    j3 = 128;
                } else {
                    j2 = j | 16;
                    j3 = 64;
                }
                j = j2 | j3;
            }
            i2 = 0;
            if (z) {
                i2 = 8;
            }
            boolean z3 = !z;
            if (!z) {
                i3 = 8;
            }
            i = i3;
            z2 = z3;
        } else {
            i = 0;
            i2 = 0;
        }
        if ((12 & j) != 0) {
            this.inviteToPartyCtaButton.setEnabled(z2);
            this.inviteToPartyCtaButtonSpinner.setVisibility(i);
            this.inviteToPartyCtaIcon.setVisibility(i2);
            this.inviteToPartyCtaText.setVisibility(i2);
        }
        if ((9 & j) != 0) {
            this.inviteToPartyCtaIcon.setImageDrawable(drawable);
        }
        if ((j & 10) != 0) {
            C11051qV.A02(this.inviteToPartyCtaText, str);
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

    @Override // com.oculus.panelapp.parties.databinding.InviteToPartyCtaButtonBinding
    public void setCtaIcon(@Nullable Drawable drawable) {
        this.mCtaIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(47);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.InviteToPartyCtaButtonBinding
    public void setCtaText(@Nullable String str) {
        this.mCtaText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.InviteToPartyCtaButtonBinding
    public void setIsLoading(boolean z) {
        this.mIsLoading = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (47 == i) {
            setCtaIcon((Drawable) obj);
            return true;
        } else if (16 == i) {
            setCtaText((String) obj);
            return true;
        } else if (23 != i) {
            return false;
        } else {
            setIsLoading(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public InviteToPartyCtaButtonBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 5, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public InviteToPartyCtaButtonBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (OCButton) objArr[1], (ConstraintLayout) objArr[0], (OCSpinner) objArr[4], (ImageView) objArr[2], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.inviteToPartyCtaButton.setTag(null);
        this.inviteToPartyCtaButtonLayout.setTag(null);
        this.inviteToPartyCtaButtonSpinner.setTag(null);
        this.inviteToPartyCtaIcon.setTag(null);
        this.inviteToPartyCtaText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
