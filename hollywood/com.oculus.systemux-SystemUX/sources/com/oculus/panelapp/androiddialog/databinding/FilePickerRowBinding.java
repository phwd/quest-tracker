package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;

public abstract class FilePickerRowBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout container;
    @NonNull
    public final OCTextView fileName;
    @NonNull
    public final ImageView image;
    @NonNull
    public final View selectedIndicator;

    protected FilePickerRowBinding(Object obj, View view, int i, LinearLayout linearLayout, OCTextView oCTextView, ImageView imageView, View view2) {
        super(obj, view, i);
        this.container = linearLayout;
        this.fileName = oCTextView;
        this.image = imageView;
        this.selectedIndicator = view2;
    }

    @NonNull
    public static FilePickerRowBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerRowBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePickerRowBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_row, viewGroup, z, obj);
    }

    @NonNull
    public static FilePickerRowBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerRowBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePickerRowBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_row, null, false, obj);
    }

    public static FilePickerRowBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePickerRowBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePickerRowBinding) bind(obj, view, R.layout.file_picker_row);
    }
}
