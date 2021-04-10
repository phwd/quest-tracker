package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uU;
import X.AnonymousClass2NC;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewStub;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerGroupThreadContainingBlockedBinding;
import java.util.List;

public class GroupThreadBlockedView {
    public AnytimeTabletMessengerGroupThreadContainingBlockedBinding mBinding;
    public GroupThreadBlockedViewModel mGroupThreadBlockedViewModel;
    public boolean mIsShowing;
    public OnContinueClickListener mOnContinueClickListener;
    public OnLeaveGroupClickListener mOnLeaveGroupClickListener;
    public OnManageBlockedMembersClickListener mOnManageBlockedMembersClickListener;
    public OnManageSingleBlockClickListener mOnManageSingleBlockClickListener;
    public final MessengerPanelApp mPanelApp;
    public final AnonymousClass2NC mViewStubProxy;

    @FunctionalInterface
    public interface OnContinueClickListener {
        void onContinueClick();
    }

    @FunctionalInterface
    public interface OnLeaveGroupClickListener {
        void onLeaveGroupClick();
    }

    @FunctionalInterface
    public interface OnManageBlockedMembersClickListener {
        void onManageBlockedMembersClick(long j);
    }

    @FunctionalInterface
    public interface OnManageSingleBlockClickListener {
        void onManageSingleBlockClick(long j, String str);
    }

    public void destroy() {
        this.mGroupThreadBlockedViewModel = null;
        AnytimeTabletMessengerGroupThreadContainingBlockedBinding anytimeTabletMessengerGroupThreadContainingBlockedBinding = this.mBinding;
        if (anytimeTabletMessengerGroupThreadContainingBlockedBinding != null) {
            OCButton oCButton = anytimeTabletMessengerGroupThreadContainingBlockedBinding.groupThreadContainingBlockedManageBlockBtn;
            oCButton.mEventHandler = null;
            oCButton.setOnClickListener(null);
            OCButton oCButton2 = anytimeTabletMessengerGroupThreadContainingBlockedBinding.groupThreadContainingBlockedLeaveGroupBtn;
            oCButton2.mEventHandler = null;
            oCButton2.setOnClickListener(null);
            OCButton oCButton3 = anytimeTabletMessengerGroupThreadContainingBlockedBinding.groupThreadContainingBlockedContinueBtn;
            oCButton3.mEventHandler = null;
            oCButton3.setOnClickListener(null);
        }
    }

    public void hide() {
        this.mIsShowing = false;
        if (this.mViewStubProxy.A00 != null) {
            this.mBinding.groupThreadContainingBlockedView.setVisibility(8);
        }
    }

    public void show(MessengerThread messengerThread, List<MessengerParticipant> list) {
        this.mIsShowing = true;
        this.mGroupThreadBlockedViewModel.setBlockedParticipants(list);
        if (this.mViewStubProxy.A00 != null) {
            this.mBinding.groupThreadContainingBlockedView.setVisibility(0);
            bindButtons(messengerThread, list);
            return;
        }
        inflate(messengerThread, list);
    }

    private void bindContinueButton(MessengerThread messengerThread) {
        this.mBinding.groupThreadContainingBlockedContinueBtn.setOnClickListener(new View.OnClickListener(messengerThread) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$GroupThreadBlockedView$fK6rSDmnoN5Y78X_i2aU54eTUY2 */
            public final /* synthetic */ MessengerThread f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                GroupThreadBlockedView.this.lambda$bindContinueButton$4$GroupThreadBlockedView(this.f$1, view);
            }
        });
    }

    private void bindLeaveGroupButton(MessengerThread messengerThread) {
        this.mBinding.groupThreadContainingBlockedLeaveGroupBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$GroupThreadBlockedView$qz1vKUfLJOsh1udmjRBYfhqRNQQ2 */

            public final void onClick(View view) {
                GroupThreadBlockedView.this.lambda$bindLeaveGroupButton$3$GroupThreadBlockedView(view);
            }
        });
    }

    private void inflate(MessengerThread messengerThread, List<MessengerParticipant> list) {
        AnonymousClass2NC r2 = this.mViewStubProxy;
        $$Lambda$GroupThreadBlockedView$ws2SnmmpWh5Mx4MSRvWH2qr4E02 r0 = new ViewStub.OnInflateListener(messengerThread, list) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$GroupThreadBlockedView$ws2SnmmpWh5Mx4MSRvWH2qr4E02 */
            public final /* synthetic */ MessengerThread f$1;
            public final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onInflate(ViewStub viewStub, View view) {
                GroupThreadBlockedView.this.lambda$inflate$0$GroupThreadBlockedView(this.f$1, this.f$2, viewStub, view);
            }
        };
        ViewStub viewStub = r2.A02;
        if (viewStub != null) {
            r2.A01 = r0;
        }
        viewStub.setVisibility(0);
    }

    public boolean isShowing() {
        return this.mIsShowing;
    }

    public /* synthetic */ void lambda$bindContinueButton$4$GroupThreadBlockedView(MessengerThread messengerThread, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_GROUP_THREAD_CONTAINING_BLOCKED_VIEW_CONTINUE_BUTTON, SurfaceType.THREAD_VIEW);
        OnContinueClickListener onContinueClickListener = this.mOnContinueClickListener;
        if (onContinueClickListener != null) {
            onContinueClickListener.onContinueClick();
            this.mPanelApp.getAPIManager().mCurrentAPI.markThreadAsRead(messengerThread);
        }
    }

    public /* synthetic */ void lambda$bindLeaveGroupButton$3$GroupThreadBlockedView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_GROUP_THREAD_CONTAINING_BLOCKED_VIEW_LEAVE_GROUP_BUTTON, SurfaceType.THREAD_VIEW);
        OnLeaveGroupClickListener onLeaveGroupClickListener = this.mOnLeaveGroupClickListener;
        if (onLeaveGroupClickListener != null) {
            onLeaveGroupClickListener.onLeaveGroupClick();
        }
    }

    public /* synthetic */ void lambda$bindManageBlockButton$1$GroupThreadBlockedView(MessengerParticipant messengerParticipant, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_GROUP_THREAD_CONTAINING_BLOCKED_VIEW_MANAGE_BLOCK_BUTTON, SurfaceType.THREAD_VIEW);
        this.mOnManageSingleBlockClickListener.onManageSingleBlockClick(messengerParticipant.getParticipantId(), messengerParticipant.getName());
    }

    public /* synthetic */ void lambda$bindManageBlockButton$2$GroupThreadBlockedView(long j, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_GROUP_THREAD_CONTAINING_BLOCKED_VIEW_MANAGE_BLOCKED_MEMBERS_BUTTON, SurfaceType.THREAD_VIEW);
        this.mOnManageBlockedMembersClickListener.onManageBlockedMembersClick(j);
    }

    public GroupThreadBlockedView(MessengerPanelApp messengerPanelApp, AnonymousClass2NC r4, Resources resources) {
        GroupThreadBlockedViewModel groupThreadBlockedViewModel = new GroupThreadBlockedViewModel(resources);
        this.mGroupThreadBlockedViewModel = groupThreadBlockedViewModel;
        groupThreadBlockedViewModel.setApiType(messengerPanelApp.getAPIManager().mCurrentAPI.getType());
        this.mPanelApp = messengerPanelApp;
        this.mViewStubProxy = r4;
    }

    private void bindButtons(MessengerThread messengerThread, List<MessengerParticipant> list) {
        bindManageBlockButton(messengerThread.getThreadKey(), list);
        bindLeaveGroupButton(messengerThread);
        bindContinueButton(messengerThread);
    }

    private void bindManageBlockButton(long j, List<MessengerParticipant> list) {
        if (list.size() == 1) {
            this.mBinding.groupThreadContainingBlockedManageBlockBtn.setOnClickListener(new View.OnClickListener(list.get(0)) {
                /* class com.oculus.panelapp.messenger.views.$$Lambda$GroupThreadBlockedView$ubDOZd3ipxf5k4taSiS0ZgbUiR02 */
                public final /* synthetic */ MessengerParticipant f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    GroupThreadBlockedView.this.lambda$bindManageBlockButton$1$GroupThreadBlockedView(this.f$1, view);
                }
            });
        } else {
            this.mBinding.groupThreadContainingBlockedManageBlockBtn.setOnClickListener(new View.OnClickListener(j) {
                /* class com.oculus.panelapp.messenger.views.$$Lambda$GroupThreadBlockedView$ozKipnRoh8ZY7RDCPZ22gAtxnNU2 */
                public final /* synthetic */ long f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    GroupThreadBlockedView.this.lambda$bindManageBlockButton$2$GroupThreadBlockedView(this.f$1, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$inflate$0$GroupThreadBlockedView(MessengerThread messengerThread, List list, ViewStub viewStub, View view) {
        AnytimeTabletMessengerGroupThreadContainingBlockedBinding anytimeTabletMessengerGroupThreadContainingBlockedBinding = (AnytimeTabletMessengerGroupThreadContainingBlockedBinding) AnonymousClass1uU.A01(view);
        this.mBinding = anytimeTabletMessengerGroupThreadContainingBlockedBinding;
        anytimeTabletMessengerGroupThreadContainingBlockedBinding.setViewModel(this.mGroupThreadBlockedViewModel);
        AnytimeTabletMessengerGroupThreadContainingBlockedBinding anytimeTabletMessengerGroupThreadContainingBlockedBinding2 = this.mBinding;
        OCButton oCButton = anytimeTabletMessengerGroupThreadContainingBlockedBinding2.groupThreadContainingBlockedManageBlockBtn;
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        oCButton.mEventHandler = messengerPanelApp;
        anytimeTabletMessengerGroupThreadContainingBlockedBinding2.groupThreadContainingBlockedLeaveGroupBtn.mEventHandler = messengerPanelApp;
        anytimeTabletMessengerGroupThreadContainingBlockedBinding2.groupThreadContainingBlockedContinueBtn.mEventHandler = messengerPanelApp;
        bindButtons(messengerThread, list);
    }

    public void setOnContinueClickListener(OnContinueClickListener onContinueClickListener) {
        this.mOnContinueClickListener = onContinueClickListener;
    }

    public void setOnLeaveGroupClickListener(OnLeaveGroupClickListener onLeaveGroupClickListener) {
        this.mOnLeaveGroupClickListener = onLeaveGroupClickListener;
    }

    public void setOnManageBlockedMembersClickListener(OnManageBlockedMembersClickListener onManageBlockedMembersClickListener) {
        this.mOnManageBlockedMembersClickListener = onManageBlockedMembersClickListener;
    }

    public void setOnManageSingleBlockClickListener(OnManageSingleBlockClickListener onManageSingleBlockClickListener) {
        this.mOnManageSingleBlockClickListener = onManageSingleBlockClickListener;
    }
}
