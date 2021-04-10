package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerMessageReactionsSummaryItemBinding extends AnonymousClass1uW {
    @Bindable
    public Drawable mReactionEmoji;
    @Bindable
    public String mReactionUsername;
    @NonNull
    public final ImageView reactionEmoji;
    @NonNull
    public final OCTextView reactionUsername;

    public abstract void setReactionEmoji(@Nullable Drawable drawable);

    public abstract void setReactionUsername(@Nullable String str);

    @Nullable
    public Drawable getReactionEmoji() {
        return this.mReactionEmoji;
    }

    @Nullable
    public String getReactionUsername() {
        return this.mReactionUsername;
    }

    public AnytimeTabletMessengerMessageReactionsSummaryItemBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView) {
        super(obj, view, i);
        this.reactionEmoji = imageView;
        this.reactionUsername = oCTextView;
    }

    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_message_reactions_summary_item);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_summary_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerMessageReactionsSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_message_reactions_summary_item, null, false);
    }
}
