package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.XMABubbleViewModel;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerXmaItemV2Binding extends AnonymousClass1uW {
    @NonNull
    public final ImageView favicon;
    @Bindable
    public XMABubbleViewModel mXmaBubbleViewModel;
    @NonNull
    public final OCTextView messageText;
    @NonNull
    public final ConstraintLayout messageWithXma;
    @NonNull
    public final OCTextView subtitleText;
    @NonNull
    public final OCTextView titleText;
    @NonNull
    public final ConstraintLayout xmaItem;

    public abstract void setXmaBubbleViewModel(@Nullable XMABubbleViewModel xMABubbleViewModel);

    @Nullable
    public XMABubbleViewModel getXmaBubbleViewModel() {
        return this.mXmaBubbleViewModel;
    }

    public AnytimeTabletMessengerXmaItemV2Binding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView, ConstraintLayout constraintLayout, OCTextView oCTextView2, OCTextView oCTextView3, ConstraintLayout constraintLayout2) {
        super(obj, view, i);
        this.favicon = imageView;
        this.messageText = oCTextView;
        this.messageWithXma = constraintLayout;
        this.subtitleText = oCTextView2;
        this.titleText = oCTextView3;
        this.xmaItem = constraintLayout2;
    }

    public static AnytimeTabletMessengerXmaItemV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerXmaItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerXmaItemV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_xma_item_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerXmaItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerXmaItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerXmaItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerXmaItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_xma_item_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerXmaItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerXmaItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_xma_item_v2, null, false);
    }
}
