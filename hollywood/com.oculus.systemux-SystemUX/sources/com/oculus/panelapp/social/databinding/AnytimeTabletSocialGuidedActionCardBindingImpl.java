package com.oculus.panelapp.social.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialGuidedAction;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialGuidedActionCardBindingImpl extends AnytimeTabletSocialGuidedActionCardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.action_card_hover_overlay, 4);
    }

    public AnytimeTabletSocialGuidedActionCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialGuidedActionCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[4], (ShellButton) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.actionCtaButton.setTag(null);
        this.actionDescription.setTag(null);
        this.profilePhoto.setTag(null);
        this.socialSuggestedActionCard.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.action == i) {
            setAction((SocialGuidedAction) obj);
        } else if (BR.hoveredOverCard != i) {
            return false;
        } else {
            setHoveredOverCard(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding
    public void setAction(@Nullable SocialGuidedAction socialGuidedAction) {
        this.mAction = socialGuidedAction;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.action);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding
    public void setHoveredOverCard(boolean z) {
        this.mHoveredOverCard = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.hoveredOverCard);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
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
            i3 = 0;
            i2 = 0;
            i = 0;
        } else {
            i2 = socialGuidedAction.getDescriptionId();
            i = socialGuidedAction.getIconId();
            i3 = socialGuidedAction.getButtonStringId();
        }
        int i6 = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        if (i6 != 0) {
            if (i6 != 0) {
                if (z) {
                    j3 = j | 16;
                    j2 = 64;
                } else {
                    j3 = j | 8;
                    j2 = 32;
                }
                j = j3 | j2;
            }
            int i7 = z ? 0 : 8;
            if (z) {
                i5 = 8;
            }
            i4 = i5;
            i5 = i7;
        } else {
            i4 = 0;
        }
        if ((5 & j) != 0) {
            this.actionCtaButton.setText(i3);
            this.actionDescription.setText(i2);
            this.profilePhoto.setImageResource(i);
        }
        if ((j & 6) != 0) {
            this.actionCtaButton.setVisibility(i5);
            this.actionDescription.setVisibility(i4);
        }
    }
}
