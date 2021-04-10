package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.views.MessengerReactionsPill;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageItemContainerV2Binding extends AnonymousClass1uW {
    @Bindable
    public boolean mShouldShowSenderInfo;
    @NonNull
    public final ConstraintLayout messageItem;
    @NonNull
    public final ImageView profilePicThem;
    @NonNull
    public final OCButton profilePicThemButton;
    @NonNull
    public final MessengerReactionsPill reactionsPill;
    @NonNull
    public final LinearLayout reactionsPillWrapper;

    public abstract void setShouldShowSenderInfo(boolean z);

    public boolean getShouldShowSenderInfo() {
        return this.mShouldShowSenderInfo;
    }

    public AnytimeTabletMessengerMessageItemContainerV2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, OCButton oCButton, MessengerReactionsPill messengerReactionsPill, LinearLayout linearLayout) {
        super(obj, view, i);
        this.messageItem = constraintLayout;
        this.profilePicThem = imageView;
        this.profilePicThemButton = oCButton;
        this.reactionsPill = messengerReactionsPill;
        this.reactionsPillWrapper = linearLayout;
    }

    public static AnytimeTabletMessengerMessageItemContainerV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageItemContainerV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageItemContainerV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_item_container_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemContainerV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemContainerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemContainerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemContainerV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_container_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemContainerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemContainerV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_container_v2, null, false);
    }
}
