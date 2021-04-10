package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageReactionsItemBinding extends AnonymousClass1uW {
    @Bindable
    public MessengerReaction mReaction;
    @NonNull
    public final OCTextView reactionListEmoji;
    @NonNull
    public final OCTextView reactionListUserName;

    public abstract void setReaction(@Nullable MessengerReaction messengerReaction);

    @Nullable
    public MessengerReaction getReaction() {
        return this.mReaction;
    }

    public AnytimeTabletMessengerMessageReactionsItemBinding(Object obj, View view, int i, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.reactionListEmoji = oCTextView;
        this.reactionListUserName = oCTextView2;
    }

    public static AnytimeTabletMessengerMessageReactionsItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageReactionsItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_reactions_item);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_item, null, false);
    }
}
