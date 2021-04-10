package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialGuidedAction;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialGuidedActionCardBindingImpl extends AnytimeTabletSocialGuidedActionCardBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
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
            this.mDirtyFlags = 4;
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
        sparseIntArray.put(R.id.action_card_hover_overlay, 4);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialGuidedAction socialGuidedAction = this.mAction;
        boolean z = this.mHoveredOverCard;
        int i5 = 0;
        if ((j & 5) == 0 || socialGuidedAction == null) {
            i = 0;
            i2 = 0;
            i3 = 0;
        } else {
            i2 = socialGuidedAction.getDescriptionId();
            i3 = socialGuidedAction.getIconId();
            i = socialGuidedAction.getButtonStringId();
        }
        long j4 = j & 6;
        if (j4 != 0) {
            if (j4 != 0) {
                if (z) {
                    j2 = j | 16;
                    j3 = 64;
                } else {
                    j2 = j | 8;
                    j3 = 32;
                }
                j = j2 | j3;
            }
            i4 = 8;
            if (z) {
                i4 = 0;
                i5 = 8;
            }
        } else {
            i4 = 0;
        }
        if ((5 & j) != 0) {
            this.actionCtaButton.setText(i);
            this.actionDescription.setText(i2);
            this.profilePhoto.setImageResource(i3);
        }
        if ((j & 6) != 0) {
            this.actionCtaButton.setVisibility(i4);
            this.actionDescription.setVisibility(i5);
        }
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding
    public void setAction(@Nullable SocialGuidedAction socialGuidedAction) {
        this.mAction = socialGuidedAction;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(96);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding
    public void setHoveredOverCard(boolean z) {
        this.mHoveredOverCard = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(92);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (96 == i) {
            setAction((SocialGuidedAction) obj);
            return true;
        } else if (92 != i) {
            return false;
        } else {
            setHoveredOverCard(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public AnytimeTabletSocialGuidedActionCardBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 5, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletSocialGuidedActionCardBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (View) objArr[4], (ShellButton) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.actionCtaButton.setTag(null);
        this.actionDescription.setTag(null);
        this.profilePhoto.setTag(null);
        this.socialSuggestedActionCard.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
