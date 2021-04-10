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
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.UserViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletPeopleListItemBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton ctaLeftButton;
    @NonNull
    public final OCSpinner ctaLoadingSpinner;
    @NonNull
    public final OCButton ctaMiddleButton;
    @NonNull
    public final OCButton ctaOverflowButton;
    @NonNull
    public final View ctaRow;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final OCTextView lastActiveTime;
    @Bindable
    public UserViewModel mViewModel;
    @NonNull
    public final OCTextView name;
    @NonNull
    public final ConstraintLayout peopleListItem;
    @NonNull
    public final ImageView presenceIcon;
    @NonNull
    public final OCTextView subtitle;

    public abstract void setViewModel(@Nullable UserViewModel userViewModel);

    @Nullable
    public UserViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleTabletPeopleListItemBinding(Object obj, View view, int i, OCButton oCButton, OCSpinner oCSpinner, OCButton oCButton2, OCButton oCButton3, View view2, ImageView imageView, OCTextView oCTextView, OCTextView oCTextView2, ConstraintLayout constraintLayout, ImageView imageView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.ctaLeftButton = oCButton;
        this.ctaLoadingSpinner = oCSpinner;
        this.ctaMiddleButton = oCButton2;
        this.ctaOverflowButton = oCButton3;
        this.ctaRow = view2;
        this.icon = imageView;
        this.lastActiveTime = oCTextView;
        this.name = oCTextView2;
        this.peopleListItem = constraintLayout;
        this.presenceIcon = imageView2;
        this.subtitle = oCTextView3;
    }

    public static PeopleTabletPeopleListItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletPeopleListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletPeopleListItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_people_list_item);
    }

    @NonNull
    public static PeopleTabletPeopleListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletPeopleListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_list_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_list_item, null, false);
    }
}
