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
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerDraftThreadItemBinding extends AnonymousClass1uW {
    @NonNull
    public final ImageView closeIndicator;
    @NonNull
    public final OCTextView draftText;
    @NonNull
    public final ImageView facepileLeftIcon;
    @NonNull
    public final LinearLayout threadItem;

    public AnytimeTabletMessengerDraftThreadItemBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView, ImageView imageView2, LinearLayout linearLayout) {
        super(obj, view, i);
        this.closeIndicator = imageView;
        this.draftText = oCTextView;
        this.facepileLeftIcon = imageView2;
        this.threadItem = linearLayout;
    }

    public static AnytimeTabletMessengerDraftThreadItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerDraftThreadItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerDraftThreadItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_draft_thread_item);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_item, null, false);
    }
}
