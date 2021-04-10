package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerDialog;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerViewModel;

public class FilePreviewerDialogBindingImpl extends FilePreviewerDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(3);
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"file_previewer_toolbar", "file_previewer_preview"}, new int[]{1, 2}, new int[]{R.layout.file_previewer_toolbar, R.layout.file_previewer_preview});
    }

    public FilePreviewerDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private FilePreviewerDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (FilePreviewerDialog) objArr[0], (FilePreviewerPreviewBinding) objArr[2], (FilePreviewerToolbarBinding) objArr[1]);
        this.mDirtyFlags = -1;
        this.filePreviewerDialog.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.previewToolbar.invalidateAll();
        this.previewLayout.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.previewLayout.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.previewToolbar.hasPendingBindings() == false) goto L_0x0016;
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
            com.oculus.panelapp.androiddialog.databinding.FilePreviewerToolbarBinding r0 = r4.previewToolbar
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.androiddialog.databinding.FilePreviewerPreviewBinding r0 = r4.previewLayout
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((FilePreviewerViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBinding
    public void setViewModel(@Nullable FilePreviewerViewModel filePreviewerViewModel) {
        updateRegistration(2, filePreviewerViewModel);
        this.mViewModel = filePreviewerViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.previewToolbar.setLifecycleOwner(lifecycleOwner);
        this.previewLayout.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangePreviewToolbar((FilePreviewerToolbarBinding) obj, i2);
        }
        if (i == 1) {
            return onChangePreviewLayout((FilePreviewerPreviewBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeViewModel((FilePreviewerViewModel) obj, i2);
    }

    private boolean onChangePreviewToolbar(FilePreviewerToolbarBinding filePreviewerToolbarBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePreviewLayout(FilePreviewerPreviewBinding filePreviewerPreviewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModel(FilePreviewerViewModel filePreviewerViewModel, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        FilePreviewerViewModel filePreviewerViewModel = this.mViewModel;
        if ((j & 12) != 0) {
            this.previewLayout.setViewModel(filePreviewerViewModel);
            this.previewToolbar.setViewModel(filePreviewerViewModel);
        }
        executeBindingsOn(this.previewToolbar);
        executeBindingsOn(this.previewLayout);
    }
}
