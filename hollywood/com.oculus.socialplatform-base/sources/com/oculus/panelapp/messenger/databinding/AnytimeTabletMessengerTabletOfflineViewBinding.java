package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerTabletOfflineViewBinding extends AnonymousClass1uW {
    @NonNull
    public final ImageView image;
    @NonNull
    public final OCButton messengerTabletOfflineButton;
    @NonNull
    public final OCTextView messengerTabletOfflineTitle;

    public AnytimeTabletMessengerTabletOfflineViewBinding(Object obj, View view, int i, ImageView imageView, OCButton oCButton, OCTextView oCTextView) {
        super(obj, view, i);
        this.image = imageView;
        this.messengerTabletOfflineButton = oCButton;
        this.messengerTabletOfflineTitle = oCTextView;
    }

    public static AnytimeTabletMessengerTabletOfflineViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerTabletOfflineViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerTabletOfflineViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_tablet_offline_view);
    }

    @NonNull
    public static AnytimeTabletMessengerTabletOfflineViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerTabletOfflineViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerTabletOfflineViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerTabletOfflineViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_tablet_offline_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerTabletOfflineViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerTabletOfflineViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_tablet_offline_view, null, false);
    }
}
