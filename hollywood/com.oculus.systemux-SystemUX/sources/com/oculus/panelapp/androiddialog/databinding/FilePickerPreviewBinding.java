package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

public abstract class FilePickerPreviewBinding extends ViewDataBinding {
    @Bindable
    protected FilePickerViewModel mViewModel;
    @NonNull
    public final View previewBackground;
    @NonNull
    public final ImageView previewEmptyImage;
    @NonNull
    public final OCTextView previewEmptyText;
    @NonNull
    public final OCTextView previewFileDate;
    @NonNull
    public final OCTextView previewFileDateTitle;
    @NonNull
    public final OCTextView previewFileDimensions;
    @NonNull
    public final OCTextView previewFileDimensionsTitle;
    @NonNull
    public final OCTextView previewFileDurationTitle;
    @NonNull
    public final OCTextView previewFileName;
    @NonNull
    public final OCTextView previewFileTypeAndSize;
    @NonNull
    public final ImageView previewImage;
    @NonNull
    public final CardView previewImageContainer;

    public abstract void setViewModel(@Nullable FilePickerViewModel filePickerViewModel);

    protected FilePickerPreviewBinding(Object obj, View view, int i, View view2, ImageView imageView, OCTextView oCTextView, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4, OCTextView oCTextView5, OCTextView oCTextView6, OCTextView oCTextView7, OCTextView oCTextView8, ImageView imageView2, CardView cardView) {
        super(obj, view, i);
        this.previewBackground = view2;
        this.previewEmptyImage = imageView;
        this.previewEmptyText = oCTextView;
        this.previewFileDate = oCTextView2;
        this.previewFileDateTitle = oCTextView3;
        this.previewFileDimensions = oCTextView4;
        this.previewFileDimensionsTitle = oCTextView5;
        this.previewFileDurationTitle = oCTextView6;
        this.previewFileName = oCTextView7;
        this.previewFileTypeAndSize = oCTextView8;
        this.previewImage = imageView2;
        this.previewImageContainer = cardView;
    }

    @Nullable
    public FilePickerViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FilePickerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FilePickerPreviewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_preview, viewGroup, z, obj);
    }

    @NonNull
    public static FilePickerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FilePickerPreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FilePickerPreviewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.file_picker_preview, null, false, obj);
    }

    public static FilePickerPreviewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FilePickerPreviewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FilePickerPreviewBinding) bind(obj, view, R.layout.file_picker_preview);
    }
}
