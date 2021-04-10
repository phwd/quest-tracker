package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerAdminMessageItemV2Binding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView adminText;
    @Bindable
    public MessengerMessage mMessage;

    public abstract void setMessage(@Nullable MessengerMessage messengerMessage);

    @Nullable
    public MessengerMessage getMessage() {
        return this.mMessage;
    }

    public AnytimeTabletMessengerAdminMessageItemV2Binding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.adminText = oCTextView;
    }

    public static AnytimeTabletMessengerAdminMessageItemV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerAdminMessageItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerAdminMessageItemV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_admin_message_item_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerAdminMessageItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerAdminMessageItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerAdminMessageItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerAdminMessageItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_admin_message_item_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerAdminMessageItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerAdminMessageItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_admin_message_item_v2, null, false);
    }
}
