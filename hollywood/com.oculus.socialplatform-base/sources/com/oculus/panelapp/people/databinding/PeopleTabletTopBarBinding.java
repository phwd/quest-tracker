package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleTabletTopBarViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletTopBarBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton connectionsButton;
    @NonNull
    public final ConstraintLayout container;
    @Bindable
    public PeopleTabletTopBarViewModel mViewModel;
    @NonNull
    public final OCTextView peopleTitle;
    @NonNull
    public final OCButton searchButton;

    public abstract void setViewModel(@Nullable PeopleTabletTopBarViewModel peopleTabletTopBarViewModel);

    @Nullable
    public PeopleTabletTopBarViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleTabletTopBarBinding(Object obj, View view, int i, OCButton oCButton, ConstraintLayout constraintLayout, OCTextView oCTextView, OCButton oCButton2) {
        super(obj, view, i);
        this.connectionsButton = oCButton;
        this.container = constraintLayout;
        this.peopleTitle = oCTextView;
        this.searchButton = oCButton2;
    }

    public static PeopleTabletTopBarBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletTopBarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletTopBarBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_top_bar);
    }

    @NonNull
    public static PeopleTabletTopBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletTopBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_top_bar, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletTopBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_top_bar, null, false);
    }
}
