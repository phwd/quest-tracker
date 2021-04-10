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
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerReactionsSummaryViewModel;
import com.oculus.socialplatform.R;

public abstract class MessageReactionsSummaryPopupBinding extends AnonymousClass1uW {
    @NonNull
    public final AnytimeTabletMessengerMessageReactionsSummaryItemBinding firstReaction;
    @Bindable
    public MessengerReactionsSummaryViewModel mViewModel;
    @NonNull
    public final OCTextView moreReactions;
    @NonNull
    public final LinearLayout reactionsSummaryView;
    @NonNull
    public final AnytimeTabletMessengerMessageReactionsSummaryItemBinding secondReaction;
    @NonNull
    public final AnytimeTabletMessengerMessageReactionsSummaryItemBinding thirdReaction;

    public abstract void setViewModel(@Nullable MessengerReactionsSummaryViewModel messengerReactionsSummaryViewModel);

    @Nullable
    public MessengerReactionsSummaryViewModel getViewModel() {
        return this.mViewModel;
    }

    public MessageReactionsSummaryPopupBinding(Object obj, View view, int i, AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding, OCTextView oCTextView, LinearLayout linearLayout, AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding2, AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding3) {
        super(obj, view, i);
        this.firstReaction = anytimeTabletMessengerMessageReactionsSummaryItemBinding;
        setContainedBinding(anytimeTabletMessengerMessageReactionsSummaryItemBinding);
        this.moreReactions = oCTextView;
        this.reactionsSummaryView = linearLayout;
        this.secondReaction = anytimeTabletMessengerMessageReactionsSummaryItemBinding2;
        setContainedBinding(anytimeTabletMessengerMessageReactionsSummaryItemBinding2);
        this.thirdReaction = anytimeTabletMessengerMessageReactionsSummaryItemBinding3;
        setContainedBinding(anytimeTabletMessengerMessageReactionsSummaryItemBinding3);
    }

    public static MessageReactionsSummaryPopupBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static MessageReactionsSummaryPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MessageReactionsSummaryPopupBinding) AnonymousClass1uW.bind(obj, view, R.layout.message_reactions_summary_popup);
    }

    @NonNull
    public static MessageReactionsSummaryPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static MessageReactionsSummaryPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static MessageReactionsSummaryPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessageReactionsSummaryPopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.message_reactions_summary_popup, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static MessageReactionsSummaryPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessageReactionsSummaryPopupBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.message_reactions_summary_popup, null, false);
    }
}
