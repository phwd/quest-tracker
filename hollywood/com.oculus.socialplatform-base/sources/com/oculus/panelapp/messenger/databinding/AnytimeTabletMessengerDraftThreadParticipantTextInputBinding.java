package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.panelapp.messenger.views.ComposeText;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerDraftThreadParticipantTextInputBinding extends AnonymousClass1uW {
    @NonNull
    public final ComposeText draftThreadPartipantEntryTextInput;

    public AnytimeTabletMessengerDraftThreadParticipantTextInputBinding(Object obj, View view, int i, ComposeText composeText) {
        super(obj, view, i);
        this.draftThreadPartipantEntryTextInput = composeText;
    }

    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerDraftThreadParticipantTextInputBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_draft_thread_participant_text_input);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadParticipantTextInputBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_participant_text_input, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerDraftThreadParticipantTextInputBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerDraftThreadParticipantTextInputBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_draft_thread_participant_text_input, null, false);
    }
}
