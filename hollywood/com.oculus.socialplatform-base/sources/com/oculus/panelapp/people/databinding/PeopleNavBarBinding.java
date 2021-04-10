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
import com.oculus.panelapp.people.views.PeopleNavBarViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleNavBarBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton friendRequestsButton;
    @NonNull
    public final OCButton friendsButton;
    @Bindable
    public PeopleNavBarViewModel mViewModel;
    @NonNull
    public final ConstraintLayout peopleNavBar;
    @NonNull
    public final OCButton peopleNearbyButton;
    @NonNull
    public final OCButton suggestedButton;

    public abstract void setViewModel(@Nullable PeopleNavBarViewModel peopleNavBarViewModel);

    @Nullable
    public PeopleNavBarViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleNavBarBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2, ConstraintLayout constraintLayout, OCButton oCButton3, OCButton oCButton4) {
        super(obj, view, i);
        this.friendRequestsButton = oCButton;
        this.friendsButton = oCButton2;
        this.peopleNavBar = constraintLayout;
        this.peopleNearbyButton = oCButton3;
        this.suggestedButton = oCButton4;
    }

    public static PeopleNavBarBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleNavBarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleNavBarBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_nav_bar);
    }

    @NonNull
    public static PeopleNavBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleNavBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleNavBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleNavBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_nav_bar, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleNavBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleNavBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_nav_bar, null, false);
    }
}
