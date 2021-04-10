package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.people.views.PeopleSearchView;
import com.oculus.panelapp.people.views.PeopleView;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletMainViewBinding extends AnonymousClass1uW {
    @Bindable
    public boolean mShowSearch;
    @NonNull
    public final PeopleSearchView peopleSearchView;
    @NonNull
    public final PeopleView peopleView;

    public abstract void setShowSearch(boolean z);

    public boolean getShowSearch() {
        return this.mShowSearch;
    }

    public PeopleTabletMainViewBinding(Object obj, View view, int i, PeopleSearchView peopleSearchView2, PeopleView peopleView2) {
        super(obj, view, i);
        this.peopleSearchView = peopleSearchView2;
        this.peopleView = peopleView2;
    }

    public static PeopleTabletMainViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletMainViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletMainViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_main_view);
    }

    @NonNull
    public static PeopleTabletMainViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletMainViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletMainViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletMainViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_main_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletMainViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletMainViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_main_view, null, false);
    }
}
