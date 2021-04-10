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
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerReactionsPillViewModel;
import com.oculus.socialplatform.R;

public abstract class MessengerReactionsPillBinding extends AnonymousClass1uW {
    @NonNull
    public final ImageView firstReaction;
    @Bindable
    public MessengerReactionsPillViewModel mViewModel;
    @NonNull
    public final LinearLayout reactionsPill;
    @NonNull
    public final ImageView secondReaction;
    @NonNull
    public final ImageView thirdReaction;
    @NonNull
    public final OCTextView totalCount;

    public abstract void setViewModel(@Nullable MessengerReactionsPillViewModel messengerReactionsPillViewModel);

    @Nullable
    public MessengerReactionsPillViewModel getViewModel() {
        return this.mViewModel;
    }

    public MessengerReactionsPillBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, ImageView imageView3, OCTextView oCTextView) {
        super(obj, view, i);
        this.firstReaction = imageView;
        this.reactionsPill = linearLayout;
        this.secondReaction = imageView2;
        this.thirdReaction = imageView3;
        this.totalCount = oCTextView;
    }

    public static MessengerReactionsPillBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static MessengerReactionsPillBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MessengerReactionsPillBinding) AnonymousClass1uW.bind(obj, view, R.layout.messenger_reactions_pill);
    }

    @NonNull
    public static MessengerReactionsPillBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static MessengerReactionsPillBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static MessengerReactionsPillBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessengerReactionsPillBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.messenger_reactions_pill, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static MessengerReactionsPillBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (MessengerReactionsPillBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.messenger_reactions_pill, null, false);
    }
}
