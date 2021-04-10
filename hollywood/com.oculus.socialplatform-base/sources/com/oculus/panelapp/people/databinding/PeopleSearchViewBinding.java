package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.people.views.PeopleSearchTopBar;
import com.oculus.panelapp.people.views.PeopleSearchViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleSearchViewBinding extends AnonymousClass1uW {
    @NonNull
    public final ProgressBar loadingSpinner;
    @Bindable
    public PeopleSearchViewModel mViewModel;
    @NonNull
    public final OCRecyclerView peopleList;
    @NonNull
    public final PeopleSearchTopBar peopleSearchTopBar;

    public abstract void setViewModel(@Nullable PeopleSearchViewModel peopleSearchViewModel);

    @Nullable
    public PeopleSearchViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleSearchViewBinding(Object obj, View view, int i, ProgressBar progressBar, OCRecyclerView oCRecyclerView, PeopleSearchTopBar peopleSearchTopBar2) {
        super(obj, view, i);
        this.loadingSpinner = progressBar;
        this.peopleList = oCRecyclerView;
        this.peopleSearchTopBar = peopleSearchTopBar2;
    }

    public static PeopleSearchViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleSearchViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleSearchViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_search_view);
    }

    @NonNull
    public static PeopleSearchViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleSearchViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_view, null, false);
    }
}
