package com.oculus.common.ocui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;

public abstract class OcdropdownBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout container;
    @NonNull
    public final OCRecyclerView contextMenuList;
    @NonNull
    public final OCTextView contextMenuTitle;

    protected OcdropdownBinding(Object obj, View view, int i, LinearLayout linearLayout, OCRecyclerView oCRecyclerView, OCTextView oCTextView) {
        super(obj, view, i);
        this.container = linearLayout;
        this.contextMenuList = oCRecyclerView;
        this.contextMenuTitle = oCTextView;
    }

    @NonNull
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcdropdownBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocdropdown, viewGroup, z, obj);
    }

    @NonNull
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcdropdownBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocdropdown, null, false, obj);
    }

    public static OcdropdownBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcdropdownBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcdropdownBinding) bind(obj, view, R.layout.ocdropdown);
    }
}
