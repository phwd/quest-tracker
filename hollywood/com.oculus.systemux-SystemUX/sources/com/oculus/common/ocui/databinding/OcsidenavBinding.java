package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;

public abstract class OcsidenavBinding extends ViewDataBinding {
    @Bindable
    protected Drawable mBackground;
    @Bindable
    protected String mTitle;
    @NonNull
    public final OCRecyclerView recyclerView;
    @NonNull
    public final OCTextView title;

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setTitle(@Nullable String str);

    protected OcsidenavBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView, OCTextView oCTextView) {
        super(obj, view, i);
        this.recyclerView = oCRecyclerView;
        this.title = oCTextView;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @NonNull
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcsidenavBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocsidenav, viewGroup, z, obj);
    }

    @NonNull
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcsidenavBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocsidenav, null, false, obj);
    }

    public static OcsidenavBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcsidenavBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcsidenavBinding) bind(obj, view, R.layout.ocsidenav);
    }
}
