package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerDialog;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerViewModel;

public abstract class FilePreviewerDialogBinding extends ViewDataBinding {
    @NonNull
    public final FilePreviewerDialog filePreviewerDialog;
    @Bindable
    protected FilePreviewerViewModel mViewModel;
    @NonNull
    public final FilePreviewerPreviewBinding previewLayout;
    @NonNull
    public final FilePreviewerToolbarBinding previewToolbar;

    public abstract void setViewModel(@Nullable FilePreviewerViewModel filePreviewerViewModel);

    protected FilePreviewerDialogBinding(Object obj, View view, int i, FilePreviewerDialog filePreviewerDialog2, FilePreviewerPreviewBinding filePreviewerPreviewBinding, FilePreviewerToolbarBinding filePreviewerToolbarBinding) {
        super(obj, view, i);
        this.filePreviewerDialog = filePreviewerDialog2;
        this.previewLayout = filePreviewerPreviewBinding;
        setContainedBinding(this.previewLayout);
        this.previewToolbar = filePreviewerToolbarBinding;
        setContainedBinding(this.previewToolbar);
    }

    @Nullable
    public FilePreviewerViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FilePreviewerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePreviewerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static FilePreviewerDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePreviewerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_dialog, null, false, obj);
    }

    public static FilePreviewerDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePreviewerDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePreviewerDialogBinding) bind(obj, view, R.layout.file_previewer_dialog);
    }
}
