package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

public abstract class FilePickerDialogBinding extends ViewDataBinding {
    @NonNull
    public final BackToTopBinding backToTop;
    @NonNull
    public final OCButton cancelButton;
    @NonNull
    public final OCSelect categorySelector;
    @NonNull
    public final OCButton confirmButton;
    @NonNull
    public final OCRecyclerView filesList;
    @Bindable
    protected FilePickerViewModel mViewModel;
    @NonNull
    public final OCSelect orderingSelector;
    @NonNull
    public final FilePickerPreviewBinding previewLayout;

    public abstract void setViewModel(@Nullable FilePickerViewModel filePickerViewModel);

    protected FilePickerDialogBinding(Object obj, View view, int i, BackToTopBinding backToTopBinding, OCButton oCButton, OCSelect oCSelect, OCButton oCButton2, OCRecyclerView oCRecyclerView, OCSelect oCSelect2, FilePickerPreviewBinding filePickerPreviewBinding) {
        super(obj, view, i);
        this.backToTop = backToTopBinding;
        setContainedBinding(this.backToTop);
        this.cancelButton = oCButton;
        this.categorySelector = oCSelect;
        this.confirmButton = oCButton2;
        this.filesList = oCRecyclerView;
        this.orderingSelector = oCSelect2;
        this.previewLayout = filePickerPreviewBinding;
        setContainedBinding(this.previewLayout);
    }

    @Nullable
    public FilePickerViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FilePickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePickerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static FilePickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePickerDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_dialog, null, false, obj);
    }

    public static FilePickerDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePickerDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePickerDialogBinding) bind(obj, view, R.layout.file_picker_dialog);
    }
}
