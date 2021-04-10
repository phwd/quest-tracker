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

public abstract class AnytimeTabletMessengerDraftThreadParticipantItemBinding extends AnonymousClass1uW {
    @NonNull
    public final LinearLayout participantBubble;
    @NonNull
    public final OCTextView participantName;
    @NonNull
    public final ImageView removeIndicator;

    public AnytimeTabletMessengerDraftThreadParticipantItemBinding(Object obj, View view, int i, LinearLayout linearLayout, OCTextView oCTextView, ImageView imageView) {
        super(obj, view, i);
        this.participantBubble = linearLayout;
        this.participantName = oCTextView;
        this.removeIndicator = imageView;
    }

    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerDraftThreadParticipantItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_draft_thread_participant_item);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadParticipantItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_participant_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadParticipantItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_participant_item, null, false);
    }
}
