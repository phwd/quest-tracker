package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.social.SocialViewModel;

public class AnytimeBarActiveCallBarFullV2BindingImpl extends AnytimeBarActiveCallBarFullV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.buttons_guideline, 8);
        sViewsWithIds.put(R.id.end_call_icon, 9);
        sViewsWithIds.put(R.id.end_call_button, 10);
    }

    public AnytimeBarActiveCallBarFullV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private AnytimeBarActiveCallBarFullV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[1], (Guideline) objArr[8], (OCButton) objArr[3], (ImageView) objArr[2], (OCButton) objArr[10], (ImageView) objArr[9], (OCButton) objArr[5], (ImageView) objArr[4], (OCButton) objArr[7], (ImageView) objArr[6]);
        this.mDirtyFlags = -1;
        this.activeCallButton.setTag(null);
        this.callVolumeButton.setTag(null);
        this.callVolumeIcon.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.microphoneInputButton.setTag(null);
        this.microphoneOutputIcon.setTag(null);
        this.muteMicrophoneButton.setTag(null);
        this.muteMicrophoneIcon.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((SocialViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveCallBarFullV2Binding
    public void setViewModel(@Nullable SocialViewModel socialViewModel) {
        updateRegistration(0, socialViewModel);
        this.mViewModel = socialViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((SocialViewModel) obj, i2);
    }

    private boolean onChangeViewModel(SocialViewModel socialViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.activeCallButtonTitle) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.shouldShowActiveCallBarAdvancedControls) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != BR.muteMicrophoneButtonIcon) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialViewModel socialViewModel = this.mViewModel;
        Drawable drawable = null;
        int i = 0;
        if ((31 & j) != 0) {
            int i2 = ((j & 21) > 0 ? 1 : ((j & 21) == 0 ? 0 : -1));
            if (i2 != 0) {
                if (socialViewModel != null) {
                    z = socialViewModel.getShouldShowActiveCallBarAdvancedControls();
                } else {
                    z = false;
                }
                if (i2 != 0) {
                    j |= z ? 64 : 32;
                }
                if (!z) {
                    i = 4;
                }
            }
            str = ((j & 19) == 0 || socialViewModel == null) ? null : socialViewModel.getActiveCallButtonTitle();
            if (!((j & 25) == 0 || socialViewModel == null)) {
                drawable = socialViewModel.getMuteMicrophoneButtonIcon();
            }
        } else {
            str = null;
        }
        if ((19 & j) != 0) {
            TextViewBindingAdapter.setText(this.activeCallButton, str);
        }
        if ((21 & j) != 0) {
            this.callVolumeButton.setVisibility(i);
            this.callVolumeIcon.setVisibility(i);
            this.microphoneInputButton.setVisibility(i);
            this.microphoneOutputIcon.setVisibility(i);
            this.muteMicrophoneButton.setVisibility(i);
            this.muteMicrophoneIcon.setVisibility(i);
        }
        if ((j & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.muteMicrophoneIcon, drawable);
        }
    }
}
