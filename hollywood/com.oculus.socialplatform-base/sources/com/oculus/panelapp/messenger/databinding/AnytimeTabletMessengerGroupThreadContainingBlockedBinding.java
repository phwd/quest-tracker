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
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.GroupThreadBlockedViewModel;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerGroupThreadContainingBlockedBinding extends AnonymousClass1uW {
    @NonNull
    public final Guideline bottomButtonGuideline;
    @NonNull
    public final View groupThreadContainingBlocked;
    @NonNull
    public final OCButton groupThreadContainingBlockedContinueBtn;
    @NonNull
    public final ImageView groupThreadContainingBlockedInfoIcon;
    @NonNull
    public final OCButton groupThreadContainingBlockedLeaveGroupBtn;
    @NonNull
    public final OCButton groupThreadContainingBlockedManageBlockBtn;
    @NonNull
    public final OCTextView groupThreadContainingBlockedMessageBody;
    @NonNull
    public final OCTextView groupThreadContainingBlockedMessageTitle;
    @NonNull
    public final Space groupThreadContainingBlockedMessageTopBuffer;
    @NonNull
    public final View groupThreadContainingBlockedOutline;
    @NonNull
    public final ConstraintLayout groupThreadContainingBlockedView;
    @Bindable
    public GroupThreadBlockedViewModel mViewModel;

    public abstract void setViewModel(@Nullable GroupThreadBlockedViewModel groupThreadBlockedViewModel);

    @Nullable
    public GroupThreadBlockedViewModel getViewModel() {
        return this.mViewModel;
    }

    public AnytimeTabletMessengerGroupThreadContainingBlockedBinding(Object obj, View view, int i, Guideline guideline, View view2, OCButton oCButton, ImageView imageView, OCButton oCButton2, OCButton oCButton3, OCTextView oCTextView, OCTextView oCTextView2, Space space, View view3, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.bottomButtonGuideline = guideline;
        this.groupThreadContainingBlocked = view2;
        this.groupThreadContainingBlockedContinueBtn = oCButton;
        this.groupThreadContainingBlockedInfoIcon = imageView;
        this.groupThreadContainingBlockedLeaveGroupBtn = oCButton2;
        this.groupThreadContainingBlockedManageBlockBtn = oCButton3;
        this.groupThreadContainingBlockedMessageBody = oCTextView;
        this.groupThreadContainingBlockedMessageTitle = oCTextView2;
        this.groupThreadContainingBlockedMessageTopBuffer = space;
        this.groupThreadContainingBlockedOutline = view3;
        this.groupThreadContainingBlockedView = constraintLayout;
    }

    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerGroupThreadContainingBlockedBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_group_thread_containing_blocked);
    }

    @NonNull
    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerGroupThreadContainingBlockedBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_group_thread_containing_blocked, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerGroupThreadContainingBlockedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerGroupThreadContainingBlockedBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_group_thread_containing_blocked, null, false);
    }
}
