package com.oculus.panelapp.people.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.google.android.flexbox.FlexboxLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleQuickMessagePopupViewModel;
import com.oculus.socialplatform.R;

public abstract class PeopleQuickMessagePopupBinding extends AnonymousClass1uW {
    @NonNull
    public final ConstraintLayout container;
    @NonNull
    public final OCTextView contextMenuTitle;
    @NonNull
    public final OCButton ctaButton;
    @NonNull
    public final OCSpinner ctaLoadingSpinner;
    @NonNull
    public final View loadingContainer;
    @NonNull
    public final OCTextView loadingText;
    @Bindable
    public PeopleQuickMessagePopupViewModel mViewModel;
    @NonNull
    public final OCButton quickMessage1;
    @NonNull
    public final OCButton quickMessage2;
    @NonNull
    public final OCButton quickMessage3;
    @NonNull
    public final OCButton quickMessage4;
    @NonNull
    public final FlexboxLayout quickMessageWrapper;
    @NonNull
    public final OCTextView successText;

    public abstract void setViewModel(@Nullable PeopleQuickMessagePopupViewModel peopleQuickMessagePopupViewModel);

    @Nullable
    public PeopleQuickMessagePopupViewModel getViewModel() {
        return this.mViewModel;
    }

    public PeopleQuickMessagePopupBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCTextView oCTextView, OCButton oCButton, OCSpinner oCSpinner, View view2, OCTextView oCTextView2, OCButton oCButton2, OCButton oCButton3, OCButton oCButton4, OCButton oCButton5, FlexboxLayout flexboxLayout, OCTextView oCTextView3) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.contextMenuTitle = oCTextView;
        this.ctaButton = oCButton;
        this.ctaLoadingSpinner = oCSpinner;
        this.loadingContainer = view2;
        this.loadingText = oCTextView2;
        this.quickMessage1 = oCButton2;
        this.quickMessage2 = oCButton3;
        this.quickMessage3 = oCButton4;
        this.quickMessage4 = oCButton5;
        this.quickMessageWrapper = flexboxLayout;
        this.successText = oCTextView3;
    }

    public static PeopleQuickMessagePopupBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PeopleQuickMessagePopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PeopleQuickMessagePopupBinding) AnonymousClass1uW.bind(obj, view, R.layout.people_quick_message_popup);
    }

    @NonNull
    public static PeopleQuickMessagePopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PeopleQuickMessagePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PeopleQuickMessagePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleQuickMessagePopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_quick_message_popup, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PeopleQuickMessagePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PeopleQuickMessagePopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.people_quick_message_popup, null, false);
    }
}
