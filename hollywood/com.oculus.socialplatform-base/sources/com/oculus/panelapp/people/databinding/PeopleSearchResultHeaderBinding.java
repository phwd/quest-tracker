package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class PeopleSearchResultHeaderBinding extends AnonymousClass1uW {
    @Bindable
    public String mTitle;
    @NonNull
    public final OCTextView peopleTabletHeader;

    public abstract void setTitle(@Nullable String str);

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public PeopleSearchResultHeaderBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.peopleTabletHeader = oCTextView;
    }

    public static PeopleSearchResultHeaderBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleSearchResultHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleSearchResultHeaderBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_search_result_header);
    }

    @NonNull
    public static PeopleSearchResultHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleSearchResultHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchResultHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchResultHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_result_header, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleSearchResultHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleSearchResultHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_search_result_header, null, false);
    }
}
