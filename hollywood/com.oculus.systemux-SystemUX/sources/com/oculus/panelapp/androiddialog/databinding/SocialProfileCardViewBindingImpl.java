package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;

public class SocialProfileCardViewBindingImpl extends SocialProfileCardViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.social_profile_card_image, 2);
        sViewsWithIds.put(R.id.social_profile_card_subtitle, 3);
    }

    public SocialProfileCardViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private SocialProfileCardViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCTextView) objArr[1], (ImageView) objArr[2], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.socialProfileCardAlias.setTag(null);
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
        if (BR.user != i) {
            return false;
        }
        setUser((SocialUserViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialProfileCardViewBinding
    public void setUser(@Nullable SocialUserViewModel socialUserViewModel) {
        updateRegistration(0, socialUserViewModel);
        this.mUser = socialUserViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeUser((SocialUserViewModel) obj, i2);
    }

    private boolean onChangeUser(SocialUserViewModel socialUserViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != BR.alias) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialUserViewModel socialUserViewModel = this.mUser;
        String str = null;
        int i = ((j & 7) > 0 ? 1 : ((j & 7) == 0 ? 0 : -1));
        if (!(i == 0 || socialUserViewModel == null)) {
            str = socialUserViewModel.getAlias();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.socialProfileCardAlias, str);
        }
    }
}
