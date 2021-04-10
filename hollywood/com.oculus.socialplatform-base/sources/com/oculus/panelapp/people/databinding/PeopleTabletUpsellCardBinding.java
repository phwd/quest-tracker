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
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class PeopleTabletUpsellCardBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton ctaButton;
    @NonNull
    public final ImageView icon;
    @Bindable
    public String mTitle;
    @NonNull
    public final ConstraintLayout peopleUpsellCard;
    @NonNull
    public final OCTextView title;

    public abstract void setTitle(@Nullable String str);

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public PeopleTabletUpsellCardBinding(Object obj, View view, int i, OCButton oCButton, ImageView imageView, ConstraintLayout constraintLayout, OCTextView oCTextView) {
        super(obj, view, i);
        this.ctaButton = oCButton;
        this.icon = imageView;
        this.peopleUpsellCard = constraintLayout;
        this.title = oCTextView;
    }

    public static PeopleTabletUpsellCardBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleTabletUpsellCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleTabletUpsellCardBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_tablet_upsell_card);
    }

    @NonNull
    public static PeopleTabletUpsellCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleTabletUpsellCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletUpsellCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletUpsellCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_upsell_card, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleTabletUpsellCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleTabletUpsellCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_tablet_upsell_card, null, false);
    }
}
