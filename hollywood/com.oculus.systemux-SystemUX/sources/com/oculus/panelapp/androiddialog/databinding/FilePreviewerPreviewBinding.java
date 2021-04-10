package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerViewModel;

public abstract class FilePreviewerPreviewBinding extends ViewDataBinding {
    @Bindable
    protected FilePreviewerViewModel mViewModel;
    @NonNull
    public final ImageView previewImage;
    @NonNull
    public final OCTextView previewerEmptyText;

    public abstract void setViewModel(@Nullable FilePreviewerViewModel filePreviewerViewModel);

    protected FilePreviewerPreviewBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView) {
        super(obj, view, i);
        this.previewImage = imageView;
        this.previewerEmptyText = oCTextView;
    }

    @Nullable
    public FilePreviewerViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FilePreviewerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePreviewerPreviewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_preview, viewGroup, z, obj);
    }

    @NonNull
    public static FilePreviewerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePreviewerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePreviewerPreviewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_previewer_preview, null, false, obj);
    }

    public static FilePreviewerPreviewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePreviewerPreviewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePreviewerPreviewBinding) bind(obj, view, R.layout.file_previewer_preview);
    }
}
