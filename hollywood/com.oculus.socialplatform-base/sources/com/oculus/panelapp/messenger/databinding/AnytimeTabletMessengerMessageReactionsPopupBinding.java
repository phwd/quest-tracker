package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageReactionsPopupBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton closeButton;
    @NonNull
    public final OCTextView messageReactionsPopupHeader;
    @NonNull
    public final OCRecyclerView reactionsList;

    public AnytimeTabletMessengerMessageReactionsPopupBinding(Object obj, View view, int i, OCButton oCButton, OCTextView oCTextView, OCRecyclerView oCRecyclerView) {
        super(obj, view, i);
        this.closeButton = oCButton;
        this.messageReactionsPopupHeader = oCTextView;
        this.reactionsList = oCRecyclerView;
    }

    public static AnytimeTabletMessengerMessageReactionsPopupBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageReactionsPopupBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_reactions_popup);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsPopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_popup, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsPopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_popup, null, false);
    }
}
