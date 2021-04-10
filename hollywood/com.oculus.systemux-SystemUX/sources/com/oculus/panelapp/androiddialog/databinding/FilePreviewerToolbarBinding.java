package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerViewModel;

public abstract class FilePreviewerToolbarBinding extends ViewDataBinding {
    @Bindable
    protected FilePreviewerViewModel mViewModel;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable FilePreviewerViewModel filePreviewerViewModel);

    protected FilePreviewerToolbarBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.title = oCTextView;
    }

    @Nullable
    public FilePreviewerViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FilePreviewerToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePreviewerToolbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_toolbar, viewGroup, z, obj);
    }

    @NonNull
    public static FilePreviewerToolbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePreviewerToolbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_toolbar, null, false, obj);
    }

    public static FilePreviewerToolbarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePreviewerToolbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePreviewerToolbarBinding) bind(obj, view, R.layout.file_previewer_toolbar);
    }
}
