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
import com.oculus.panelapp.people.views.PeopleUserCardViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletPeopleUserCardBinding extends AnonymousClass1uW {
    @NonNull
    public final View cardHoverOverlay;
    @NonNull
    public final OCButton ctaButton;
    @NonNull
    public final OCButton ctaLeftButton;
    @NonNull
    public final OCSpinner ctaLoadingSpinner;
    @NonNull
    public final OCButton ctaRightButton;
    @NonNull
    public final View ctaRow;
    @NonNull
    public final OCTextView lastActiveTime;
    @Bindable
    public PeopleUserCardViewModel mViewModel;
    @NonNull
    public final ConstraintLayout peopleUserCard;
    @NonNull
    public final ImageView presenceIcon;
    @NonNull
    public final ImageView profilePhoto;
    @NonNull
    public final OCButton secondaryCtaButton;
    @NonNull
    public final OCSpinner secondaryCtaButtonLoadingSpinner;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView username;

    public abstract void setViewModel(@Nullable PeopleUserCardViewModel peopleUserCardViewModel);

    @Nullable
    public PeopleUserCardViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleTabletPeopleUserCardBinding(Object obj, View view, int i, View view2, OCButton oCButton, OCButton oCButton2, OCSpinner oCSpinner, OCButton oCButton3, View view3, OCTextView oCTextView, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, OCButton oCButton4, OCSpinner oCSpinner2, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.cardHoverOverlay = view2;
        this.ctaButton = oCButton;
        this.ctaLeftButton = oCButton2;
        this.ctaLoadingSpinner = oCSpinner;
        this.ctaRightButton = oCButton3;
        this.ctaRow = view3;
        this.lastActiveTime = oCTextView;
        this.peopleUserCard = constraintLayout;
        this.presenceIcon = imageView;
        this.profilePhoto = imageView2;
        this.secondaryCtaButton = oCButton4;
        this.secondaryCtaButtonLoadingSpinner = oCSpinner2;
        this.subtitle = oCTextView2;
        this.username = oCTextView3;
    }

    public static PeopleTabletPeopleUserCardBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletPeopleUserCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletPeopleUserCardBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_people_user_card);
    }

    @NonNull
    public static PeopleTabletPeopleUserCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletPeopleUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleUserCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_user_card, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletPeopleUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletPeopleUserCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_people_user_card, null, false);
    }
}
