package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

public class FilePickerPreviewBindingImpl extends FilePickerPreviewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final OCTextView mboundView11;

    static {
        sViewsWithIds.put(R.id.preview_image, 12);
    }

    public FilePickerPreviewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 13, sIncludes, sViewsWithIds));
    }

    private FilePickerPreviewBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 1, (View) objArr[0], (ImageView) objArr[1], (OCTextView) objArr[2], (OCTextView) objArr[7], (OCTextView) objArr[6], (OCTextView) objArr[9], (OCTextView) objArr[8], (OCTextView) objArr[10], (OCTextView) objArr[4], (OCTextView) objArr[5], (ImageView) objArr[12], (CardView) objArr[3]);
        this.mDirtyFlags = -1;
        this.mboundView11 = (OCTextView) objArr[11];
        this.mboundView11.setTag(null);
        this.previewBackground.setTag(null);
        this.previewEmptyImage.setTag(null);
        this.previewEmptyText.setTag(null);
        this.previewFileDate.setTag(null);
        this.previewFileDateTitle.setTag(null);
        this.previewFileDimensions.setTag(null);
        this.previewFileDimensionsTitle.setTag(null);
        this.previewFileDurationTitle.setTag(null);
        this.previewFileName.setTag(null);
        this.previewFileTypeAndSize.setTag(null);
        this.previewImageContainer.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        setViewModel((FilePickerViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.FilePickerPreviewBinding
    public void setViewModel(@Nullable FilePickerViewModel filePickerViewModel) {
        updateRegistration(0, filePickerViewModel);
        this.mViewModel = filePickerViewModel;
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
        return onChangeViewModel((FilePickerViewModel) obj, i2);
    }

    private boolean onChangeViewModel(FilePickerViewModel filePickerViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.selectedFilePath) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.selectedFileName) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.selectedFileTypeAndSize) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.selectedFileDateAdded) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.selectedFileDimensions) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i != BR.selectedFileDuration) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00fd  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 504
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.FilePickerPreviewBindingImpl.executeBindings():void");
    }
}
