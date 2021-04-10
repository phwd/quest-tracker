package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uU;
import X.AnonymousClass2NC;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewStub;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.IMessengerAPI;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerOneOnOneBlockedThreadBinding;
import com.oculus.panelapp.messenger.util.ThreadHelper;
import java.util.List;

public class OneOnOneThreadBlockedView {
    public AnytimeTabletMessengerOneOnOneBlockedThreadBinding mBinding;
    public OnReportClickListener mOnReportClickListener;
    public OnUnblockClickListener mOnUnblockClickListener;
    public final MessengerPanelApp mPanelApp;
    public OneOnOneThreadBlockedViewModel mViewModel;
    public final AnonymousClass2NC mViewStubProxy;

    public interface OnReportClickListener {
        void onReportClick(String str, long j, String str2, long j2);
    }

    public interface OnUnblockClickListener {
        void onUnblockClick(String str, long j, String str2);
    }

    public void destroy() {
        this.mViewModel = null;
        AnytimeTabletMessengerOneOnOneBlockedThreadBinding anytimeTabletMessengerOneOnOneBlockedThreadBinding = this.mBinding;
        if (anytimeTabletMessengerOneOnOneBlockedThreadBinding != null) {
            OCButton oCButton = anytimeTabletMessengerOneOnOneBlockedThreadBinding.oneOnOneBlockedThreadReportBtn;
            oCButton.mEventHandler = null;
            oCButton.setOnClickListener(null);
            OCButton oCButton2 = anytimeTabletMessengerOneOnOneBlockedThreadBinding.oneOnOneBlockedThreadUnblockBtn;
            oCButton2.mEventHandler = null;
            oCButton2.setOnClickListener(null);
        }
    }

    public void show(List<MessengerParticipant> list, long j) {
        MessengerPanelApp messengerPanelApp;
        DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC;
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        String userID = iMessengerAPI.getUserID();
        MessengerParticipant otherThreadParticipant = ThreadHelper.getOtherThreadParticipant(userID, list);
        this.mViewModel.setBlockedParticipant(otherThreadParticipant);
        long participantId = otherThreadParticipant.getParticipantId();
        String name = otherThreadParticipant.getName();
        if (this.mViewStubProxy.A00 != null) {
            this.mBinding.oneOnOneBlockedThreadView.setVisibility(0);
            bindUnblockButton(userID, participantId, name);
            bindReportButton(userID, participantId, name, j);
        } else {
            inflate(userID, participantId, name, j);
        }
        OneOnOneThreadBlockedViewModel oneOnOneThreadBlockedViewModel = this.mViewModel;
        if (iMessengerAPI.getType() == MessengerAPIType.OC_CHATS) {
            messengerPanelApp = this.mPanelApp;
            deviceConfigSocialPlatformMC = DeviceConfigSocialPlatformMC.MESSENGER_VR_ENABLE_OC_REPORT_FLOW;
        } else {
            messengerPanelApp = this.mPanelApp;
            deviceConfigSocialPlatformMC = DeviceConfigSocialPlatformMC.MESSENGER_VR_ENABLE_FB_REPORT_FLOW;
        }
        oneOnOneThreadBlockedViewModel.setReportButtonVisibility(messengerPanelApp.getDeviceConfig(deviceConfigSocialPlatformMC));
    }

    private void bindReportButton(String str, long j, String str2, long j2) {
        this.mBinding.oneOnOneBlockedThreadReportBtn.setOnClickListener(new View.OnClickListener(str, j, str2, j2) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$OneOnOneThreadBlockedView$a45svArQLFOXnnLQUB5vALmmXEs2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ long f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
                this.f$4 = r6;
            }

            public final void onClick(View view) {
                OneOnOneThreadBlockedView.this.lambda$bindReportButton$2$OneOnOneThreadBlockedView(this.f$1, this.f$2, this.f$3, this.f$4, view);
            }
        });
    }

    private void bindUnblockButton(String str, long j, String str2) {
        this.mBinding.oneOnOneBlockedThreadUnblockBtn.setOnClickListener(new View.OnClickListener(str, j, str2) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$OneOnOneThreadBlockedView$49Dcs9lqcKvCd8dAJoCUxK5j9Ds2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
            }

            public final void onClick(View view) {
                OneOnOneThreadBlockedView.this.lambda$bindUnblockButton$1$OneOnOneThreadBlockedView(this.f$1, this.f$2, this.f$3, view);
            }
        });
    }

    private void inflate(String str, long j, String str2, long j2) {
        AnonymousClass2NC r0 = this.mViewStubProxy;
        $$Lambda$OneOnOneThreadBlockedView$SqOy1v4iSxKe1jCUhVpnFv8f5bI2 r2 = new ViewStub.OnInflateListener(str, j, str2, j2) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$OneOnOneThreadBlockedView$SqOy1v4iSxKe1jCUhVpnFv8f5bI2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ long f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
                this.f$4 = r6;
            }

            public final void onInflate(ViewStub viewStub, View view) {
                OneOnOneThreadBlockedView.this.lambda$inflate$0$OneOnOneThreadBlockedView(this.f$1, this.f$2, this.f$3, this.f$4, viewStub, view);
            }
        };
        ViewStub viewStub = r0.A02;
        if (viewStub != null) {
            r0.A01 = r2;
        }
        viewStub.setVisibility(0);
        AnytimeTabletMessengerOneOnOneBlockedThreadBinding anytimeTabletMessengerOneOnOneBlockedThreadBinding = this.mBinding;
        OCButton oCButton = anytimeTabletMessengerOneOnOneBlockedThreadBinding.oneOnOneBlockedThreadUnblockBtn;
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        oCButton.mEventHandler = messengerPanelApp;
        anytimeTabletMessengerOneOnOneBlockedThreadBinding.oneOnOneBlockedThreadReportBtn.mEventHandler = messengerPanelApp;
    }

    public void hide() {
        if (this.mViewStubProxy.A00 != null) {
            this.mBinding.oneOnOneBlockedThreadView.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$bindReportButton$2$OneOnOneThreadBlockedView(String str, long j, String str2, long j2, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_ONE_ON_ONE_THREAD_BLOCKED_VIEW_REPORT_BUTTON, SurfaceType.THREAD_VIEW);
        OnReportClickListener onReportClickListener = this.mOnReportClickListener;
        if (onReportClickListener != null) {
            onReportClickListener.onReportClick(str, j, str2, j2);
        }
    }

    public /* synthetic */ void lambda$bindUnblockButton$1$OneOnOneThreadBlockedView(String str, long j, String str2, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_ONE_ON_ONE_THREAD_BLOCKED_VIEW_UNBLOCK_BUTTON, SurfaceType.THREAD_VIEW);
        OnUnblockClickListener onUnblockClickListener = this.mOnUnblockClickListener;
        if (onUnblockClickListener != null) {
            onUnblockClickListener.onUnblockClick(str, j, str2);
        }
    }

    public OneOnOneThreadBlockedView(MessengerPanelApp messengerPanelApp, AnonymousClass2NC r3, Resources resources) {
        this.mViewModel = new OneOnOneThreadBlockedViewModel(resources);
        this.mPanelApp = messengerPanelApp;
        this.mViewStubProxy = r3;
    }

    public /* synthetic */ void lambda$inflate$0$OneOnOneThreadBlockedView(String str, long j, String str2, long j2, ViewStub viewStub, View view) {
        AnytimeTabletMessengerOneOnOneBlockedThreadBinding anytimeTabletMessengerOneOnOneBlockedThreadBinding = (AnytimeTabletMessengerOneOnOneBlockedThreadBinding) AnonymousClass1uU.A01(view);
        this.mBinding = anytimeTabletMessengerOneOnOneBlockedThreadBinding;
        anytimeTabletMessengerOneOnOneBlockedThreadBinding.setViewModel(this.mViewModel);
        bindUnblockButton(str, j, str2);
        bindReportButton(str, j, str2, j2);
    }

    public void setOnReportClickListener(OnReportClickListener onReportClickListener) {
        this.mOnReportClickListener = onReportClickListener;
    }

    public void setOnUnblockClickListener(OnUnblockClickListener onUnblockClickListener) {
        this.mOnUnblockClickListener = onUnblockClickListener;
    }
}
