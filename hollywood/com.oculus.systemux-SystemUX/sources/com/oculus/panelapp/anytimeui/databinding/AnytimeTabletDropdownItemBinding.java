package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletDropdownItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView dropdownIcon;
    @NonNull
    public final ConstraintLayout dropdownItem;
    @NonNull
    public final OCTextView dropdownText;

    protected AnytimeTabletDropdownItemBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, OCTextView oCTextView) {
        super(obj, view, i);
        this.dropdownIcon = imageView;
        this.dropdownItem = constraintLayout;
        this.dropdownText = oCTextView;
    }

    @NonNull
    public static AnytimeTabletDropdownItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletDropdownItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletDropdownItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_dropdown_item, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletDropdownItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletDropdownItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletDropdownItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_dropdown_item, null, false, obj);
    }

    public static AnytimeTabletDropdownItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletDropdownItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletDropdownItemBinding) bind(obj, view, R.layout.anytime_tablet_dropdown_item);
    }
}
