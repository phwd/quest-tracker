package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.views.PeopleNavBar;
import com.oculus.panelapp.people.views.PeopleTabletTopBar;
import com.oculus.panelapp.people.views.PeopleViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleViewBinding extends AnonymousClass1uW {
    @NonNull
    public final Guideline headerGuideline;
    @NonNull
    public final ProgressBar loadingSpinner;
    @Bindable
    public PeopleViewModel mViewModel;
    @NonNull
    public final OCRecyclerView peopleList;
    @NonNull
    public final PeopleNavBar peopleNavBar;
    @NonNull
    public final PeopleTabletTopBar peopleTabletTopBar;
    @NonNull
    public final SocialTabletSideNav peopleViewSideNav;

    public abstract void setViewModel(@Nullable PeopleViewModel peopleViewModel);

    @Nullable
    public PeopleViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleViewBinding(Object obj, View view, int i, Guideline guideline, ProgressBar progressBar, OCRecyclerView oCRecyclerView, PeopleNavBar peopleNavBar2, PeopleTabletTopBar peopleTabletTopBar2, SocialTabletSideNav socialTabletSideNav) {
        super(obj, view, i);
        this.headerGuideline = guideline;
        this.loadingSpinner = progressBar;
        this.peopleList = oCRecyclerView;
        this.peopleNavBar = peopleNavBar2;
        this.peopleTabletTopBar = peopleTabletTopBar2;
        this.peopleViewSideNav = socialTabletSideNav;
    }

    public static PeopleViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_view);
    }

    @NonNull
    public static PeopleViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_view, null, false);
    }
}
