package com.oculus.panelapp.parties.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
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
import com.oculus.socialplatform.R;

public abstract class InviteToPartyCtaButtonBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton inviteToPartyCtaButton;
    @NonNull
    public final ConstraintLayout inviteToPartyCtaButtonLayout;
    @NonNull
    public final OCSpinner inviteToPartyCtaButtonSpinner;
    @NonNull
    public final ImageView inviteToPartyCtaIcon;
    @NonNull
    public final OCTextView inviteToPartyCtaText;
    @Bindable
    public Drawable mCtaIcon;
    @Bindable
    public String mCtaText;
    @Bindable
    public boolean mIsLoading;

    public abstract void setCtaIcon(@Nullable Drawable drawable);

    public abstract void setCtaText(@Nullable String str);

    public abstract void setIsLoading(boolean z);

    @Nullable
    public Drawable getCtaIcon() {
        return this.mCtaIcon;
    }

    @Nullable
    public String getCtaText() {
        return this.mCtaText;
    }

    public boolean getIsLoading() {
        return this.mIsLoading;
    }

    public InviteToPartyCtaButtonBinding(Object obj, View view, int i, OCButton oCButton, ConstraintLayout constraintLayout, OCSpinner oCSpinner, ImageView imageView, OCTextView oCTextView) {
        super(obj, view, i);
        this.inviteToPartyCtaButton = oCButton;
        this.inviteToPartyCtaButtonLayout = constraintLayout;
        this.inviteToPartyCtaButtonSpinner = oCSpinner;
        this.inviteToPartyCtaIcon = imageView;
        this.inviteToPartyCtaText = oCTextView;
    }

    public static InviteToPartyCtaButtonBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static InviteToPartyCtaButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (InviteToPartyCtaButtonBinding) AnonymousClass1uW.bind(obj, view, R.layout.invite_to_party_cta_button);
    }

    @NonNull
    public static InviteToPartyCtaButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static InviteToPartyCtaButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static InviteToPartyCtaButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (InviteToPartyCtaButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.invite_to_party_cta_button, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static InviteToPartyCtaButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (InviteToPartyCtaButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.invite_to_party_cta_button, null, false);
    }
}
