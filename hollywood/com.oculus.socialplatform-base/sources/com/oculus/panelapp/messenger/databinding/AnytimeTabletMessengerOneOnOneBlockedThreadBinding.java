package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.OneOnOneThreadBlockedViewModel;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerOneOnOneBlockedThreadBinding extends AnonymousClass1uW {
    @Bindable
    public OneOnOneThreadBlockedViewModel mViewModel;
    @NonNull
    public final OCButton oneOnOneBlockedThreadReportBtn;
    @NonNull
    public final OCButton oneOnOneBlockedThreadUnblockBtn;
    @NonNull
    public final ConstraintLayout oneOnOneBlockedThreadView;
    @NonNull
    public final ImageView oneOnOneThreadBlockedInfoIcon;
    @NonNull
    public final OCTextView oneOnOneThreadBlockedMessageBody;
    @NonNull
    public final View oneOnOneThreadBlockedMessageOutline;
    @NonNull
    public final OCTextView oneOnOneThreadBlockedMessageTitle;
    @NonNull
    public final Space oneOnOneThreadBlockedMessageTopBuffer;

    public abstract void setViewModel(@Nullable OneOnOneThreadBlockedViewModel oneOnOneThreadBlockedViewModel);

    @Nullable
    public OneOnOneThreadBlockedViewModel getViewModel() {
        return this.mViewModel;
    }

    public AnytimeTabletMessengerOneOnOneBlockedThreadBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2, ConstraintLayout constraintLayout, ImageView imageView, OCTextView oCTextView, View view2, OCTextView oCTextView2, Space space) {
        super(obj, view, i);
        this.oneOnOneBlockedThreadReportBtn = oCButton;
        this.oneOnOneBlockedThreadUnblockBtn = oCButton2;
        this.oneOnOneBlockedThreadView = constraintLayout;
        this.oneOnOneThreadBlockedInfoIcon = imageView;
        this.oneOnOneThreadBlockedMessageBody = oCTextView;
        this.oneOnOneThreadBlockedMessageOutline = view2;
        this.oneOnOneThreadBlockedMessageTitle = oCTextView2;
        this.oneOnOneThreadBlockedMessageTopBuffer = space;
    }

    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerOneOnOneBlockedThreadBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_one_on_one_blocked_thread);
    }

    @NonNull
    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerOneOnOneBlockedThreadBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_one_on_one_blocked_thread, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerOneOnOneBlockedThreadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerOneOnOneBlockedThreadBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_one_on_one_blocked_thread, null, false);
    }
}
