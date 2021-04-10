package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletPeopleHorizontalUsersViewBinding extends AnonymousClass1uW {
    @NonNull
    public final OCRecyclerView horizontalRecycler;

    public PeopleTabletPeopleHorizontalUsersViewBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView) {
        super(obj, view, i);
        this.horizontalRecycler = oCRecyclerView;
    }

    public static PeopleTabletPeopleHorizontalUsersViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletPeopleHorizontalUsersViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletPeopleHorizontalUsersViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_people_horizontal_users_view);
    }

    @NonNull
    public static PeopleTabletPeopleHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletPeopleHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleHorizontalUsersViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_horizontal_users_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleHorizontalUsersViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_horizontal_users_view, null, false);
    }
}
