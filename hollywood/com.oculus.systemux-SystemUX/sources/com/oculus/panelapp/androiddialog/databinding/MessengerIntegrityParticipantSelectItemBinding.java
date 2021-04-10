package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerThreadParticipant;

public abstract class MessengerIntegrityParticipantSelectItemBinding extends ViewDataBinding {
    @Bindable
    protected MessengerThreadParticipant mParticipant;
    @NonNull
    public final LinearLayout participantItem;
    @NonNull
    public final SimpleDraweeView participantPhoto;
    @NonNull
    public final OCTextView participantShortName;

    public abstract void setParticipant(@Nullable MessengerThreadParticipant messengerThreadParticipant);

    protected MessengerIntegrityParticipantSelectItemBinding(Object obj, View view, int i, LinearLayout linearLayout, SimpleDraweeView simpleDraweeView, OCTextView oCTextView) {
        super(obj, view, i);
        this.participantItem = linearLayout;
        this.participantPhoto = simpleDraweeView;
        this.participantShortName = oCTextView;
    }

    @Nullable
    public MessengerThreadParticipant getParticipant() {
        return this.mParticipant;
    }

    @NonNull
    public static MessengerIntegrityParticipantSelectItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MessengerIntegrityParticipantSelectItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MessengerIntegrityParticipantSelectItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.messenger_integrity_participant_select_item, viewGroup, z, obj);
    }

    @NonNull
    public static MessengerIntegrityParticipantSelectItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MessengerIntegrityParticipantSelectItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MessengerIntegrityParticipantSelectItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.messenger_integrity_participant_select_item, null, false, obj);
    }

    public static MessengerIntegrityParticipantSelectItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MessengerIntegrityParticipantSelectItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MessengerIntegrityParticipantSelectItemBinding) bind(obj, view, R.layout.messenger_integrity_participant_select_item);
    }
}
