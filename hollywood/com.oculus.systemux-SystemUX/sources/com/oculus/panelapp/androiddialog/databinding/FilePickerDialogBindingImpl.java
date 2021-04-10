package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerDialog;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

public class FilePickerDialogBindingImpl extends FilePickerDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(8);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final FilePickerDialog mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"file_picker_preview", "back_to_top"}, new int[]{2, 3}, new int[]{R.layout.file_picker_preview, R.layout.back_to_top});
        sViewsWithIds.put(R.id.category_selector, 4);
        sViewsWithIds.put(R.id.ordering_selector, 5);
        sViewsWithIds.put(R.id.files_list, 6);
        sViewsWithIds.put(R.id.cancel_button, 7);
    }

    public FilePickerDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private FilePickerDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (BackToTopBinding) objArr[3], (OCButton) objArr[7], (OCSelect) objArr[4], (OCButton) objArr[1], (OCRecyclerView) objArr[6], (OCSelect) objArr[5], (FilePickerPreviewBinding) objArr[2]);
        this.mDirtyFlags = -1;
        this.confirmButton.setTag(null);
        this.mboundView0 = (FilePickerDialog) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.previewLayout.invalidateAll();
        this.backToTop.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.backToTop.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.previewLayout.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.oculus.panelapp.androiddialog.databinding.FilePickerPreviewBinding r0 = r4.previewLayout
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.androiddialog.databinding.BackToTopBinding r0 = r4.backToTop
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((FilePickerViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBinding
    public void setViewModel(@Nullable FilePickerViewModel filePickerViewModel) {
        updateRegistration(2, filePickerViewModel);
        this.mViewModel = filePickerViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.previewLayout.setLifecycleOwner(lifecycleOwner);
        this.backToTop.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeBackToTop((BackToTopBinding) obj, i2);
        }
        if (i == 1) {
            return onChangePreviewLayout((FilePickerPreviewBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeViewModel((FilePickerViewModel) obj, i2);
    }

    private boolean onChangeBackToTop(BackToTopBinding backToTopBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePreviewLayout(FilePickerPreviewBinding filePickerPreviewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModel(FilePickerViewModel filePickerViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != BR.selectedFilePath) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        FilePickerViewModel filePickerViewModel = this.mViewModel;
        int i = ((28 & j) > 0 ? 1 : ((28 & j) == 0 ? 0 : -1));
        boolean z = false;
        if (i != 0) {
            if (filePickerViewModel != null) {
                str = filePickerViewModel.getSelectedFilePath();
            }
            if (str != null) {
                z = true;
            }
        }
        if (i != 0) {
            this.confirmButton.setEnabled(z);
        }
        if ((j & 20) != 0) {
            this.previewLayout.setViewModel(filePickerViewModel);
        }
        executeBindingsOn(this.previewLayout);
        executeBindingsOn(this.backToTop);
    }
}
