package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageItemTextV2Binding extends AnonymousClass1uW {
    @Bindable
    public String mDisplayText;
    @Bindable
    public int mDisplayTextColor;
    @Bindable
    public Typeface mDisplayTextTypeface;
    @NonNull
    public final OCTextView messageText;

    public abstract void setDisplayText(@Nullable String str);

    public abstract void setDisplayTextColor(int i);

    public abstract void setDisplayTextTypeface(@Nullable Typeface typeface);

    @Nullable
    public String getDisplayText() {
        return this.mDisplayText;
    }

    public int getDisplayTextColor() {
        return this.mDisplayTextColor;
    }

    @Nullable
    public Typeface getDisplayTextTypeface() {
        return this.mDisplayTextTypeface;
    }

    public AnytimeTabletMessengerMessageItemTextV2Binding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.messageText = oCTextView;
    }

    public static AnytimeTabletMessengerMessageItemTextV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageItemTextV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageItemTextV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_item_text_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemTextV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageItemTextV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemTextV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemTextV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_text_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageItemTextV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageItemTextV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_item_text_v2, null, false);
    }
}
