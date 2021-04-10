package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OcsidenavBinding extends AnonymousClass1uW {
    @Bindable
    public Drawable mBackground;
    @Bindable
    public String mTitle;
    @NonNull
    public final OCRecyclerView recyclerView;
    @NonNull
    public final OCTextView title;

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setTitle(@Nullable String str);

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public OcsidenavBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView, OCTextView oCTextView) {
        super(obj, view, i);
        this.recyclerView = oCRecyclerView;
        this.title = oCTextView;
    }

    public static OcsidenavBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcsidenavBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcsidenavBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocsidenav);
    }

    @NonNull
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcsidenavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav, null, false);
    }
}
