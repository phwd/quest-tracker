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
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.views.SearchEditText;
import com.oculus.socialplatform.R;

public abstract class PeopleSearchFieldBinding extends AnonymousClass1uW {
    @NonNull
    public final ConstraintLayout searchBox;
    @NonNull
    public final OCButton searchClearBtn;
    @NonNull
    public final SearchEditText searchEditText;
    @NonNull
    public final ImageView searchIcon;

    public PeopleSearchFieldBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCButton oCButton, SearchEditText searchEditText2, ImageView imageView) {
        super(obj, view, i);
        this.searchBox = constraintLayout;
        this.searchClearBtn = oCButton;
        this.searchEditText = searchEditText2;
        this.searchIcon = imageView;
    }

    public static PeopleSearchFieldBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleSearchFieldBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleSearchFieldBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_search_field);
    }

    @NonNull
    public static PeopleSearchFieldBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleSearchFieldBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchFieldBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchFieldBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_field, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchFieldBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchFieldBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_field, null, false);
    }
}
