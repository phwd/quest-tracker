package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.socialplatform.R;

public abstract class MessengerFacepileBinding extends AnonymousClass1uW {
    @NonNull
    public final ImageView facepileLeftIcon;
    @NonNull
    public final View facepileLeftIconBorder;
    @NonNull
    public final ImageView facepileRightIcon;
    @Bindable
    public boolean mIsFacepileThreadIcon;
    @NonNull
    public final FrameLayout messengerFacepileThreadIcon;

    public abstract void setIsFacepileThreadIcon(boolean z);

    public boolean getIsFacepileThreadIcon() {
        return this.mIsFacepileThreadIcon;
    }

    public MessengerFacepileBinding(Object obj, View view, int i, ImageView imageView, View view2, ImageView imageView2, FrameLayout frameLayout) {
        super(obj, view, i);
        this.facepileLeftIcon = imageView;
        this.facepileLeftIconBorder = view2;
        this.facepileRightIcon = imageView2;
        this.messengerFacepileThreadIcon = frameLayout;
    }

    public static MessengerFacepileBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static MessengerFacepileBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MessengerFacepileBinding) AnonymousClass1uW.bind(obj, view, R.layout.messenger_facepile);
    }

    @NonNull
    public static MessengerFacepileBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static MessengerFacepileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static MessengerFacepileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessengerFacepileBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.messenger_facepile, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static MessengerFacepileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessengerFacepileBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.messenger_facepile, null, false);
    }
}
