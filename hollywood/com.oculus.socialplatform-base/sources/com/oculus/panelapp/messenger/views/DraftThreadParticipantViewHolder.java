package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantItemBinding;
import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import com.oculus.vrshell.panels.SoundType;

public class DraftThreadParticipantViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(DraftThreadParticipantViewHolder.class);
    public AnytimeTabletMessengerDraftThreadParticipantItemBinding mBinding;
    public MessengerPanelApp mPanelApp;

    public DraftThreadParticipantViewHolder(AnytimeTabletMessengerDraftThreadParticipantItemBinding anytimeTabletMessengerDraftThreadParticipantItemBinding, MessengerPanelApp messengerPanelApp) {
        super(anytimeTabletMessengerDraftThreadParticipantItemBinding.mRoot);
        this.mBinding = anytimeTabletMessengerDraftThreadParticipantItemBinding;
        this.mPanelApp = messengerPanelApp;
        setRemoveParticipantHaptics();
    }

    private void setRemoveParticipantHaptics() {
        this.mBinding.removeIndicator.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantViewHolder$qpcuT_5qsI1pK8hwoPsBgq6C1SU2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return DraftThreadParticipantViewHolder.this.lambda$setRemoveParticipantHaptics$1$DraftThreadParticipantViewHolder(view, motionEvent);
            }
        });
        this.mBinding.removeIndicator.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantViewHolder$JLPN9uXFWpB9b8UoX7DPPUA98yY2 */

            public final void onClick(View view) {
                DraftThreadParticipantViewHolder.this.lambda$setRemoveParticipantHaptics$2$DraftThreadParticipantViewHolder(view);
            }
        });
    }

    public void bindParticipant(MessengerContact messengerContact) {
        this.mBinding.participantName.setText(messengerContact.mUserName);
        this.mBinding.removeIndicator.setOnClickListener(new View.OnClickListener(messengerContact) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantViewHolder$FAiYAlkWeWUK2e_qzJBDGEhyIa42 */
            public final /* synthetic */ MessengerContact f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                DraftThreadParticipantViewHolder.this.lambda$bindParticipant$0$DraftThreadParticipantViewHolder(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindParticipant$0$DraftThreadParticipantViewHolder(MessengerContact messengerContact, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_DRAFT_THREAD_PARTICIPANT_REMOVE, SurfaceType.THREAD_VIEW);
        this.mPanelApp.getAPIManager().mDraftThread.removeParticipant(messengerContact);
    }

    public /* synthetic */ void lambda$setRemoveParticipantHaptics$2$DraftThreadParticipantViewHolder(View view) {
        this.mPanelApp.getAndroidPanelApp().mFrameCommandChannel.playAudio(SoundType.SELECT);
    }

    public /* synthetic */ boolean lambda$setRemoveParticipantHaptics$1$DraftThreadParticipantViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return false;
        }
        this.mPanelApp.getAndroidPanelApp().mFrameCommandChannel.playAudio(SoundType.HOVER);
        return false;
    }
}
