package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleEmptyViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletEmptyBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton ctaButton;
    @NonNull
    public final ConstraintLayout emptyView;
    @NonNull
    public final ImageView image;
    @Bindable
    public PeopleEmptyViewModel mViewModel;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable PeopleEmptyViewModel peopleEmptyViewModel);

    @Nullable
    public PeopleEmptyViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleTabletEmptyBinding(Object obj, View view, int i, OCButton oCButton, ConstraintLayout constraintLayout, ImageView imageView, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.ctaButton = oCButton;
        this.emptyView = constraintLayout;
        this.image = imageView;
        this.subtitle = oCTextView;
        this.title = oCTextView2;
    }

    public static PeopleTabletEmptyBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletEmptyBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletEmptyBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_empty);
    }

    @NonNull
    public static PeopleTabletEmptyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletEmptyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletEmptyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletEmptyBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_empty, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletEmptyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletEmptyBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_empty, null, false);
    }
}
