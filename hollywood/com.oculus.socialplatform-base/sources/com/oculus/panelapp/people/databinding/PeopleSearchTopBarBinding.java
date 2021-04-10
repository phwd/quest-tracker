package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.views.PeopleSearchField;
import com.oculus.socialplatform.R;

public abstract class PeopleSearchTopBarBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton backButton;
    @NonNull
    public final ConstraintLayout container;
    @NonNull
    public final PeopleSearchField searchBox;

    public PeopleSearchTopBarBinding(Object obj, View view, int i, OCButton oCButton, ConstraintLayout constraintLayout, PeopleSearchField peopleSearchField) {
        super(obj, view, i);
        this.backButton = oCButton;
        this.container = constraintLayout;
        this.searchBox = peopleSearchField;
    }

    public static PeopleSearchTopBarBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleSearchTopBarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleSearchTopBarBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_search_top_bar);
    }

    @NonNull
    public static PeopleSearchTopBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleSearchTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchTopBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_top_bar, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchTopBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchTopBarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_top_bar, null, false);
    }
}
