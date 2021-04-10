package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call.StartMessengerCallView;
import com.oculus.socialplatform.R;

public abstract class StartMessengerCallViewBinding extends AnonymousClass1uW {
    @NonNull
    public final StartMessengerCallView startMessengerCall;
    @NonNull
    public final OCButton startMessengerCallPrimaryButton;
    @NonNull
    public final OCButton startMessengerCallSecondaryButton;
    @NonNull
    public final OCTextView startMessengerCallTitle;

    public StartMessengerCallViewBinding(Object obj, View view, int i, StartMessengerCallView startMessengerCallView, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView) {
        super(obj, view, i);
        this.startMessengerCall = startMessengerCallView;
        this.startMessengerCallPrimaryButton = oCButton;
        this.startMessengerCallSecondaryButton = oCButton2;
        this.startMessengerCallTitle = oCTextView;
    }

    public static StartMessengerCallViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static StartMessengerCallViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (StartMessengerCallViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.start_messenger_call_view);
    }

    @NonNull
    public static StartMessengerCallViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static StartMessengerCallViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static StartMessengerCallViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (StartMessengerCallViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.start_messenger_call_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static StartMessengerCallViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (StartMessengerCallViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.start_messenger_call_view, null, false);
    }
}
