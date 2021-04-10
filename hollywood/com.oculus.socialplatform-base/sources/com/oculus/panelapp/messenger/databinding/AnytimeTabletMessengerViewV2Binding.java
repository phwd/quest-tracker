package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import X.AnonymousClass2NC;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.ComposeText;
import com.oculus.panelapp.messenger.views.MessengerFacepile;
import com.oculus.panelapp.messenger.views.MessengerView;
import com.oculus.panelapp.messenger.views.MessengerViewModel;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerViewV2Binding extends AnonymousClass1uW {
    @NonNull
    public final OCButton audioInputButton;
    @NonNull
    public final Guideline composeBarGuideline;
    @NonNull
    public final HorizontalScrollView composeBarScrollView;
    @NonNull
    public final ComposeText composeTextInput;
    @NonNull
    public final OCTextView composerBlockedView;
    @NonNull
    public final Group composerCollapsedInputGroup;
    @NonNull
    public final Group composerExpandedInputGroup;
    @NonNull
    public final Group currentThreadHeader;
    @NonNull
    public final Group draftThreadHeader;
    @NonNull
    public final OCRecyclerView draftThreadParticipantList;
    @NonNull
    public final OCTextView draftThreadPartipantEntryPrompt;
    @NonNull
    public final MessengerFacepile facepileThreadIcon;
    @NonNull
    public final AnonymousClass2NC groupThreadContainingBlockedStub;
    @NonNull
    public final Guideline headerGuideline;
    @NonNull
    public final AnonymousClass2NC leaveChatConfirmationStub;
    @NonNull
    public final View leftbarBackground;
    @NonNull
    public final Guideline leftbarGuideline;
    @NonNull
    public final Group leftbarView;
    @NonNull
    public final SocialTabletSideNav leftnav;
    @NonNull
    public final Guideline leftnavGuideline;
    @Bindable
    public MessengerViewModel mMessengerViewModel;
    @NonNull
    public final OCRecyclerView messageList;
    @NonNull
    public final AnonymousClass2NC messengerTabletOfflineViewStub;
    @NonNull
    public final OCTextView messengerTabletTitle;
    @NonNull
    public final MessengerView messengerView;
    @NonNull
    public final AnonymousClass2NC oneOnOneBlockedThreadStub;
    @NonNull
    public final OCButton quickReply0;
    @NonNull
    public final OCButton quickReply1;
    @NonNull
    public final OCButton quickReply2;
    @NonNull
    public final OCButton quickReply3;
    @NonNull
    public final OCButton quickReply4;
    @NonNull
    public final OCButton sendButton;
    @NonNull
    public final OCButton startParty;
    @NonNull
    public final OCSpinner threadAddToPartySpinner;
    @NonNull
    public final OCButton threadContextMenuButton;
    @NonNull
    public final OCButton threadCreate;
    @NonNull
    public final OCRecyclerView threadList;
    @NonNull
    public final AnonymousClass2NC threadListNullStateViewStub;
    @NonNull
    public final OCTextView threadTitle;

    public abstract void setMessengerViewModel(@Nullable MessengerViewModel messengerViewModel);

    @Nullable
    public MessengerViewModel getMessengerViewModel() {
        return this.mMessengerViewModel;
    }

    public AnytimeTabletMessengerViewV2Binding(Object obj, View view, int i, OCButton oCButton, Guideline guideline, HorizontalScrollView horizontalScrollView, ComposeText composeText, OCTextView oCTextView, Group group, Group group2, Group group3, Group group4, OCRecyclerView oCRecyclerView, OCTextView oCTextView2, MessengerFacepile messengerFacepile, AnonymousClass2NC r17, Guideline guideline2, AnonymousClass2NC r19, View view2, Guideline guideline3, Group group5, SocialTabletSideNav socialTabletSideNav, Guideline guideline4, OCRecyclerView oCRecyclerView2, AnonymousClass2NC r26, OCTextView oCTextView3, MessengerView messengerView2, AnonymousClass2NC r29, OCButton oCButton2, OCButton oCButton3, OCButton oCButton4, OCButton oCButton5, OCButton oCButton6, OCButton oCButton7, OCButton oCButton8, OCSpinner oCSpinner, OCButton oCButton9, OCButton oCButton10, OCRecyclerView oCRecyclerView3, AnonymousClass2NC r41, OCTextView oCTextView4) {
        super(obj, view, i);
        this.audioInputButton = oCButton;
        this.composeBarGuideline = guideline;
        this.composeBarScrollView = horizontalScrollView;
        this.composeTextInput = composeText;
        this.composerBlockedView = oCTextView;
        this.composerCollapsedInputGroup = group;
        this.composerExpandedInputGroup = group2;
        this.currentThreadHeader = group3;
        this.draftThreadHeader = group4;
        this.draftThreadParticipantList = oCRecyclerView;
        this.draftThreadPartipantEntryPrompt = oCTextView2;
        this.facepileThreadIcon = messengerFacepile;
        this.groupThreadContainingBlockedStub = r17;
        this.headerGuideline = guideline2;
        this.leaveChatConfirmationStub = r19;
        this.leftbarBackground = view2;
        this.leftbarGuideline = guideline3;
        this.leftbarView = group5;
        this.leftnav = socialTabletSideNav;
        this.leftnavGuideline = guideline4;
        this.messageList = oCRecyclerView2;
        this.messengerTabletOfflineViewStub = r26;
        this.messengerTabletTitle = oCTextView3;
        this.messengerView = messengerView2;
        this.oneOnOneBlockedThreadStub = r29;
        this.quickReply0 = oCButton2;
        this.quickReply1 = oCButton3;
        this.quickReply2 = oCButton4;
        this.quickReply3 = oCButton5;
        this.quickReply4 = oCButton6;
        this.sendButton = oCButton7;
        this.startParty = oCButton8;
        this.threadAddToPartySpinner = oCSpinner;
        this.threadContextMenuButton = oCButton9;
        this.threadCreate = oCButton10;
        this.threadList = oCRecyclerView3;
        this.threadListNullStateViewStub = r41;
        this.threadTitle = oCTextView4;
    }

    public static AnytimeTabletMessengerViewV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerViewV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_view_v2);
    }

    @NonNull
    public static AnytimeTabletMessengerViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerViewV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_view_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerViewV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_view_v2, null, false);
    }
}
