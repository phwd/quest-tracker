package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerFacepile;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerThreadItemV2Binding extends AnonymousClass1uW {
    @Bindable
    public MessengerThread mThread;
    @NonNull
    public final MessengerFacepile threadFacepile;
    @NonNull
    public final LinearLayout threadInfo;
    @NonNull
    public final LinearLayout threadItem;
    @NonNull
    public final OCTextView threadSnippet;
    @NonNull
    public final OCTextView threadTitle;
    @NonNull
    public final View unreadIndicator;

    public abstract void setThread(@Nullable MessengerThread messengerThread);

    @Nullable
    public MessengerThread getThread() {
        return this.mThread;
    }

    public AnytimeTabletMessengerThreadItemV2Binding(Object obj, View view, int i, MessengerFacepile messengerFacepile, LinearLayout linearLayout, LinearLayout linearLayout2, OCTextView oCTextView, OCTextView oCTextView2, View view2) {
        super(obj, view, i);
        this.threadFacepile = messengerFacepile;
        this.threadInfo = linearLayout;
        this.threadItem = linearLayout2;
        this.threadSnippet = oCTextView;
        this.threadTitle = oCTextView2;
        this.unreadIndicator = view2;
    }

    public static AnytimeTabletMessengerThreadItemV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerThreadItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerThreadItemV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_thread_item_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerThreadItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerThreadItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerThreadItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerThreadItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_thread_item_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerThreadItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerThreadItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_thread_item_v2, null, false);
    }
}
