package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerViewModel;

public class FilePreviewerPreviewBindingImpl extends FilePreviewerPreviewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public FilePreviewerPreviewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 2, sIncludes, sViewsWithIds));
    }

    private FilePreviewerPreviewBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 1, (ImageView) objArr[1], (OCTextView) objArr[0]);
        this.mDirtyFlags = -1;
        this.previewImage.setTag(null);
        this.previewerEmptyText.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        setViewModel((FilePreviewerViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.FilePreviewerPreviewBinding
    public void setViewModel(@Nullable FilePreviewerViewModel filePreviewerViewModel) {
        updateRegistration(0, filePreviewerViewModel);
        this.mViewModel = filePreviewerViewModel;
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
        return onChangeViewModel((FilePreviewerViewModel) obj, i2);
    }

    private boolean onChangeViewModel(FilePreviewerViewModel filePreviewerViewModel, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        FilePreviewerViewModel filePreviewerViewModel = this.mViewModel;
        int i2 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            if (filePreviewerViewModel != null) {
                str = filePreviewerViewModel.getSelectedFilePath();
            }
            boolean z = true;
            boolean z2 = str == null;
            if (str == null) {
                z = false;
            }
            if (i2 != 0) {
                j |= z2 ? 32 : 16;
            }
            if ((j & 3) != 0) {
                j |= z ? 8 : 4;
            }
            i = z2 ? 0 : 8;
            if (!z) {
                i3 = 8;
            }
        } else {
            i = 0;
        }
        if ((j & 3) != 0) {
            this.previewImage.setVisibility(i3);
            this.previewerEmptyText.setVisibility(i);
        }
    }
}
