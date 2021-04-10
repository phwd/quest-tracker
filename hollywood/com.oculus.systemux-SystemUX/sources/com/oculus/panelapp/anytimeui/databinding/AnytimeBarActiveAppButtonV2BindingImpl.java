package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.BindingUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;

public class AnytimeBarActiveAppButtonV2BindingImpl extends AnytimeBarActiveAppButtonV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.button, 3);
    }

    public AnytimeBarActiveAppButtonV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private AnytimeBarActiveAppButtonV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (SimpleDraweeView) objArr[1], (ImageView) objArr[2], (OCButton) objArr[3]);
        this.mDirtyFlags = -1;
        this.appImage.setTag(null);
        this.appScreenshot.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.destinationUIViewModel != i) {
            return false;
        }
        setDestinationUIViewModel((DestinationUIViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveAppButtonV2Binding
    public void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel) {
        updateRegistration(0, destinationUIViewModel);
        this.mDestinationUIViewModel = destinationUIViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.destinationUIViewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeDestinationUIViewModel((DestinationUIViewModel) obj, i2);
    }

    private boolean onChangeDestinationUIViewModel(DestinationUIViewModel destinationUIViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.squareImage) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.gameScreenshot) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != BR.isScreenshotVisible) {
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
        DestinationUIViewModel destinationUIViewModel = this.mDestinationUIViewModel;
        Drawable drawable = null;
        int i = 0;
        if ((31 & j) != 0) {
            int i2 = ((j & 25) > 0 ? 1 : ((j & 25) == 0 ? 0 : -1));
            if (i2 != 0) {
                if (destinationUIViewModel != null) {
                    z = destinationUIViewModel.getIsScreenshotVisible();
                } else {
                    z = false;
                }
                if (i2 != 0) {
                    j |= z ? 64 : 32;
                }
                if (!z) {
                    i = 8;
                }
            }
            str = ((j & 19) == 0 || destinationUIViewModel == null) ? null : destinationUIViewModel.getSquareImage();
            if (!((j & 21) == 0 || destinationUIViewModel == null)) {
                drawable = destinationUIViewModel.getGameScreenshot();
            }
        } else {
            str = null;
        }
        if ((19 & j) != 0) {
            BindingUtils.loadImage(this.appImage, str);
        }
        if ((j & 21) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appScreenshot, drawable);
        }
        if ((j & 25) != 0) {
            this.appScreenshot.setVisibility(i);
        }
    }
}
