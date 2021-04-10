package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerLeaveChatConfirmationBinding extends AnonymousClass1uW {
    @NonNull
    public final Guideline bottomButtonGuideline;
    @NonNull
    public final OCButton cancelLeaveChatBtn;
    @NonNull
    public final OCButton confirmLeaveChatBtn;
    @NonNull
    public final ConstraintLayout leaveChatConfirmation;
    @NonNull
    public final OCTextView leaveChatConfirmationBody;
    @NonNull
    public final OCTextView leaveChatConfirmationHeader;

    public AnytimeTabletMessengerLeaveChatConfirmationBinding(Object obj, View view, int i, Guideline guideline, OCButton oCButton, OCButton oCButton2, ConstraintLayout constraintLayout, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.bottomButtonGuideline = guideline;
        this.cancelLeaveChatBtn = oCButton;
        this.confirmLeaveChatBtn = oCButton2;
        this.leaveChatConfirmation = constraintLayout;
        this.leaveChatConfirmationBody = oCTextView;
        this.leaveChatConfirmationHeader = oCTextView2;
    }

    public static AnytimeTabletMessengerLeaveChatConfirmationBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerLeaveChatConfirmationBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerLeaveChatConfirmationBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_leave_chat_confirmation);
    }

    @NonNull
    public static AnytimeTabletMessengerLeaveChatConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerLeaveChatConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerLeaveChatConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerLeaveChatConfirmationBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_leave_chat_confirmation, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerLeaveChatConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerLeaveChatConfirmationBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_leave_chat_confirmation, null, false);
    }
}
