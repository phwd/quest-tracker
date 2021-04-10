package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.socialandroidbackpanel.views.application_invites.ApplicationInvitesView;
import com.oculus.panelapp.socialandroidbackpanel.views.application_invites.ApplicationInvitesViewModel;
import com.oculus.socialplatform.R;

public abstract class ApplicationInvitesViewBinding extends AnonymousClass1uW {
    @NonNull
    public final ApplicationInvitesView applicationInvitesView;
    @Bindable
    public ApplicationInvitesViewModel mApplicationInvitesViewModel;

    public abstract void setApplicationInvitesViewModel(@Nullable ApplicationInvitesViewModel applicationInvitesViewModel);

    @Nullable
    public ApplicationInvitesViewModel getApplicationInvitesViewModel() {
        return this.mApplicationInvitesViewModel;
    }

    public ApplicationInvitesViewBinding(Object obj, View view, int i, ApplicationInvitesView applicationInvitesView2) {
        super(obj, view, i);
        this.applicationInvitesView = applicationInvitesView2;
    }

    public static ApplicationInvitesViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static ApplicationInvitesViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ApplicationInvitesViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.application_invites_view);
    }

    @NonNull
    public static ApplicationInvitesViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static ApplicationInvitesViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static ApplicationInvitesViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ApplicationInvitesViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.application_invites_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static ApplicationInvitesViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ApplicationInvitesViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.application_invites_view, null, false);
    }
}
