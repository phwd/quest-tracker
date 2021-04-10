package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageItemImageV2Binding extends AnonymousClass1uW {
    @NonNull
    public final ImageView imageAttachment;

    public AnytimeTabletMessengerMessageItemImageV2Binding(Object obj, View view, int i, ImageView imageView) {
        super(obj, view, i);
        this.imageAttachment = imageView;
    }

    public static AnytimeTabletMessengerMessageItemImageV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageItemImageV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageItemImageV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_item_image_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemImageV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemImageV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemImageV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemImageV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_image_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemImageV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemImageV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_image_v2, null, false);
    }
}
