package com.oculus.panelapp.bugreporter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCCheckbox;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCScrollView;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.DescriptionViewModel;
import com.oculus.panelapp.bugreporter.R;
import com.oculus.panelapp.bugreporter.common.TextInputView;

public abstract class BugReportDescriptionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton cancelButton;
    @NonNull
    public final OCTextView categoryLabel;
    @NonNull
    public final OCSelect categorySelector;
    @NonNull
    public final OCCheckbox checkbox;
    @NonNull
    public final OCTextView checkboxLabel;
    @NonNull
    public final OCButton continueButton;
    @NonNull
    public final TextInputView description;
    @NonNull
    public final OCTextView descriptionHint;
    @NonNull
    public final OCTextView descriptionLabel;
    @Bindable
    protected DescriptionViewModel mViewModel;
    @NonNull
    public final ImageView preselectedPhoto;
    @NonNull
    public final ImageView screenshot;
    @NonNull
    public final OCScrollView scrollview;
    @NonNull
    public final OCLink supportText;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable DescriptionViewModel descriptionViewModel);

    protected BugReportDescriptionViewBinding(Object obj, View view, int i, OCButton oCButton, OCTextView oCTextView, OCSelect oCSelect, OCCheckbox oCCheckbox, OCTextView oCTextView2, OCButton oCButton2, TextInputView textInputView, OCTextView oCTextView3, OCTextView oCTextView4, ImageView imageView, ImageView imageView2, OCScrollView oCScrollView, OCLink oCLink, OCTextView oCTextView5) {
        super(obj, view, i);
        this.cancelButton = oCButton;
        this.categoryLabel = oCTextView;
        this.categorySelector = oCSelect;
        this.checkbox = oCCheckbox;
        this.checkboxLabel = oCTextView2;
        this.continueButton = oCButton2;
        this.description = textInputView;
        this.descriptionHint = oCTextView3;
        this.descriptionLabel = oCTextView4;
        this.preselectedPhoto = imageView;
        this.screenshot = imageView2;
        this.scrollview = oCScrollView;
        this.supportText = oCLink;
        this.title = oCTextView5;
    }

    @Nullable
    public DescriptionViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static BugReportDescriptionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportDescriptionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportDescriptionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_description_view, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportDescriptionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportDescriptionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportDescriptionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_description_view, null, false, obj);
    }

    public static BugReportDescriptionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportDescriptionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportDescriptionViewBinding) bind(obj, view, R.layout.bug_report_description_view);
    }
}
