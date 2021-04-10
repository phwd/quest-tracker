package com.oculus.panelapp.androiddialog.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCInfo;
import com.oculus.ocui.OCProgressBar;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog;
import com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel;

public class CustomSystemDialogBindingImpl extends CustomSystemDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final CustomDialog mboundView0;

    static {
        sViewsWithIds.put(R.id.top_button_barrier, 11);
        sViewsWithIds.put(R.id.bottom_button_barrier, 12);
    }

    public CustomSystemDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private CustomSystemDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCBackButton) objArr[1], (OCTextView) objArr[6], (Barrier) objArr[12], (OCButton) objArr[4], (OCInfo) objArr[7], (OCButton) objArr[9], (OCProgressBar) objArr[5], (OCSpinner) objArr[3], (OCButton) objArr[8], (OCButton) objArr[10], (OCTextView) objArr[2], (Barrier) objArr[11]);
        this.mDirtyFlags = -1;
        this.backButton.setTag(null);
        this.body.setTag(null);
        this.iconButton.setTag(null);
        this.infoBox.setTag(null);
        this.mboundView0 = (CustomDialog) objArr[0];
        this.mboundView0.setTag(null);
        this.primaryButton.setTag(null);
        this.progressBar.setTag(null);
        this.progressSpinner.setTag(null);
        this.secondaryButton.setTag(null);
        this.tertiaryButton.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 131072;
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
        setViewModel((CustomDialogViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.CustomSystemDialogBinding
    public void setViewModel(@Nullable CustomDialogViewModel customDialogViewModel) {
        updateRegistration(0, customDialogViewModel);
        this.mViewModel = customDialogViewModel;
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
        return onChangeViewModel((CustomDialogViewModel) obj, i2);
    }

    private boolean onChangeViewModel(CustomDialogViewModel customDialogViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.backButtonVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.title) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.progressSpinnerVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.iconButtonVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.iconButtonIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.progressBarVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.progressBarProgress) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.body) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.informationBoxText) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.secondaryButtonDisabled) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.secondaryButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.primaryButtonDisabled) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.primaryButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == BR.primaryButtonStyle) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == BR.tertiaryButtonDisabled) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i != BR.tertiaryButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0174 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01ad A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x013a  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 859
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.CustomSystemDialogBindingImpl.executeBindings():void");
    }
}
