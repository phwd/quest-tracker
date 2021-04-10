package com.oculus.panelapp.bugreporter.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.BR;
import com.oculus.panelapp.bugreporter.MediaView;
import com.oculus.panelapp.bugreporter.MediaViewModel;
import com.oculus.panelapp.bugreporter.R;

public class BugReportMediaViewBindingImpl extends BugReportMediaViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final MediaView mboundView0;

    static {
        sViewsWithIds.put(R.id.back_button, 3);
        sViewsWithIds.put(R.id.title, 4);
        sViewsWithIds.put(R.id.description, 5);
        sViewsWithIds.put(R.id.camera_roll_label, 6);
        sViewsWithIds.put(R.id.camera_roll, 7);
        sViewsWithIds.put(R.id.cancel_button, 8);
    }

    public BugReportMediaViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private BugReportMediaViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCBackButton) objArr[3], (OCRecyclerView) objArr[7], (OCTextView) objArr[6], (OCButton) objArr[8], (OCButton) objArr[2], (OCTextView) objArr[5], (OCTextView) objArr[1], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.continueButton.setTag(null);
        this.mboundView0 = (MediaView) objArr[0];
        this.mboundView0.setTag(null);
        this.sizeWarning.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        setViewModel((MediaViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.bugreporter.databinding.BugReportMediaViewBinding
    public void setViewModel(@Nullable MediaViewModel mediaViewModel) {
        updateRegistration(0, mediaViewModel);
        this.mViewModel = mediaViewModel;
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
        return onChangeViewModel((MediaViewModel) obj, i2);
    }

    private boolean onChangeViewModel(MediaViewModel mediaViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.hasExceededFileSizeLimit) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != BR.continueButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        boolean z;
        boolean z2;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaViewModel mediaViewModel = this.mViewModel;
        int i2 = 0;
        if ((15 & j) != 0) {
            int i3 = ((j & 11) > 0 ? 1 : ((j & 11) == 0 ? 0 : -1));
            if (i3 != 0) {
                if (mediaViewModel != null) {
                    z2 = mediaViewModel.getHasExceededFileSizeLimit();
                } else {
                    z2 = false;
                }
                if (i3 != 0) {
                    if (z2) {
                        j3 = j | 32;
                        j2 = 128;
                    } else {
                        j3 = j | 16;
                        j2 = 64;
                    }
                    j = j3 | j2;
                }
                z = !z2;
                i = z2 ? 0 : 8;
            } else {
                z = false;
                i = 0;
            }
            if (!((j & 13) == 0 || mediaViewModel == null)) {
                i2 = mediaViewModel.getContinueButtonText();
            }
        } else {
            z = false;
            i = 0;
        }
        if ((13 & j) != 0) {
            this.continueButton.setText(i2);
        }
        if ((j & 11) != 0) {
            this.continueButton.setEnabled(z);
            this.sizeWarning.setVisibility(i);
        }
    }
}
