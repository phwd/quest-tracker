package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uU;
import X.AnonymousClass2NC;
import android.view.View;
import android.view.ViewStub;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerLeaveChatConfirmationBinding;

public class LeaveChatConfirmationView {
    public AnytimeTabletMessengerLeaveChatConfirmationBinding mBinding;
    public OnCancelClickListener mOnCancelClickListener;
    public OnLeaveClickListener mOnLeaveClickListener;
    public final MessengerPanelApp mPanelApp;
    public final AnonymousClass2NC mViewStubProxy;

    @FunctionalInterface
    public interface OnCancelClickListener {
        void onCancelClick();
    }

    @FunctionalInterface
    public interface OnLeaveClickListener {
        void onLeaveClick();
    }

    private void initializeButtons() {
        AnytimeTabletMessengerLeaveChatConfirmationBinding anytimeTabletMessengerLeaveChatConfirmationBinding = this.mBinding;
        OCButton oCButton = anytimeTabletMessengerLeaveChatConfirmationBinding.cancelLeaveChatBtn;
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        oCButton.mEventHandler = messengerPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeaveChatConfirmationView$cDmTGdf0jS_TQg5CjkxonjJ3xog2 */

            public final void onClick(View view) {
                LeaveChatConfirmationView.this.lambda$initializeButtons$1$LeaveChatConfirmationView(view);
            }
        });
        OCButton oCButton2 = anytimeTabletMessengerLeaveChatConfirmationBinding.confirmLeaveChatBtn;
        oCButton2.mEventHandler = messengerPanelApp;
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeaveChatConfirmationView$2o4mqC0xrbe_qnQmKJOouhhw5zo2 */

            public final void onClick(View view) {
                LeaveChatConfirmationView.this.lambda$initializeButtons$2$LeaveChatConfirmationView(view);
            }
        });
    }

    public void destroy() {
        AnytimeTabletMessengerLeaveChatConfirmationBinding anytimeTabletMessengerLeaveChatConfirmationBinding = this.mBinding;
        if (anytimeTabletMessengerLeaveChatConfirmationBinding != null) {
            OCButton oCButton = anytimeTabletMessengerLeaveChatConfirmationBinding.cancelLeaveChatBtn;
            oCButton.mEventHandler = null;
            oCButton.setOnClickListener(null);
            OCButton oCButton2 = anytimeTabletMessengerLeaveChatConfirmationBinding.confirmLeaveChatBtn;
            oCButton2.mEventHandler = null;
            oCButton2.setOnClickListener(null);
        }
    }

    public void hide() {
        if (this.mViewStubProxy.A00 != null) {
            this.mBinding.leaveChatConfirmation.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$initializeButtons$1$LeaveChatConfirmationView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_LEAVE_CHAT_CONFIRMATION_CANCEL_BUTTON, SurfaceType.THREAD_VIEW);
        OnCancelClickListener onCancelClickListener = this.mOnCancelClickListener;
        if (onCancelClickListener != null) {
            onCancelClickListener.onCancelClick();
        }
    }

    public /* synthetic */ void lambda$initializeButtons$2$LeaveChatConfirmationView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_LEAVE_CHAT_CONFIRMATION_LEAVE_BUTTON, SurfaceType.THREAD_VIEW);
        OnLeaveClickListener onLeaveClickListener = this.mOnLeaveClickListener;
        if (onLeaveClickListener != null) {
            onLeaveClickListener.onLeaveClick();
        }
    }

    public void show() {
        AnonymousClass2NC r2 = this.mViewStubProxy;
        boolean z = false;
        if (r2.A00 != null) {
            z = true;
        }
        if (z) {
            this.mBinding.leaveChatConfirmation.setVisibility(0);
        } else {
            r2.A02.setVisibility(0);
        }
    }

    public LeaveChatConfirmationView(MessengerPanelApp messengerPanelApp, AnonymousClass2NC r4) {
        this.mPanelApp = messengerPanelApp;
        this.mViewStubProxy = r4;
        $$Lambda$LeaveChatConfirmationView$7g4PA1Qgu6CZ2EBlPI4PEJywSX42 r1 = new ViewStub.OnInflateListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeaveChatConfirmationView$7g4PA1Qgu6CZ2EBlPI4PEJywSX42 */

            public final void onInflate(ViewStub viewStub, View view) {
                LeaveChatConfirmationView.this.lambda$new$0$LeaveChatConfirmationView(viewStub, view);
            }
        };
        if (r4.A02 != null) {
            r4.A01 = r1;
        }
    }

    public /* synthetic */ void lambda$new$0$LeaveChatConfirmationView(ViewStub viewStub, View view) {
        this.mBinding = (AnytimeTabletMessengerLeaveChatConfirmationBinding) AnonymousClass1uU.A01(view);
        initializeButtons();
    }

    public void setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.mOnCancelClickListener = onCancelClickListener;
    }

    public void setOnLeaveClickListener(OnLeaveClickListener onLeaveClickListener) {
        this.mOnLeaveClickListener = onLeaveClickListener;
    }
}
