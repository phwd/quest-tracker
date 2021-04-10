package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadItemBinding;
import com.oculus.vrshell.panels.SoundType;

public class DraftThreadViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(DraftThreadViewHolder.class);

    public DraftThreadViewHolder(AnytimeTabletMessengerDraftThreadItemBinding anytimeTabletMessengerDraftThreadItemBinding, MessengerPanelApp messengerPanelApp) {
        super(anytimeTabletMessengerDraftThreadItemBinding.mRoot);
        anytimeTabletMessengerDraftThreadItemBinding.threadItem.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadViewHolder$OOt9NFj0HKKaSlESVHsGFxwN782 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return DraftThreadViewHolder.lambda$new$0(MessengerPanelApp.this, view, motionEvent);
            }
        });
        anytimeTabletMessengerDraftThreadItemBinding.threadItem.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadViewHolder$9BWFHrsZNRu7GxUQk4QA2Qczd2o2 */

            public final void onClick(View view) {
                DraftThreadViewHolder.lambda$new$1(MessengerPanelApp.this, view);
            }
        });
        anytimeTabletMessengerDraftThreadItemBinding.threadItem.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadViewHolder$eguPK5XWY97vzIZ6fy9xKaLS2A2 */

            public final void onClick(View view) {
                DraftThreadViewHolder.lambda$new$2(MessengerPanelApp.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$new$1(MessengerPanelApp messengerPanelApp, View view) {
        messengerPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_DRAFT_LIST_ITEM, SurfaceType.THREAD_VIEW);
        messengerPanelApp.getAndroidPanelApp().mFrameCommandChannel.playAudio(SoundType.SELECT);
    }

    public static /* synthetic */ boolean lambda$new$0(MessengerPanelApp messengerPanelApp, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return false;
        }
        messengerPanelApp.getAndroidPanelApp().mFrameCommandChannel.playAudio(SoundType.HOVER);
        return false;
    }

    public static /* synthetic */ void lambda$new$2(MessengerPanelApp messengerPanelApp, View view) {
        messengerPanelApp.getAPIManager().updateDraftThread(null);
        MessengerThread currentThread = messengerPanelApp.getAPIManager().mCurrentAPI.getCurrentThread();
        if (currentThread != null) {
            messengerPanelApp.getAPIManager().mCurrentAPI.updateCurrentThread(currentThread.getThreadKey(), null);
        }
    }
}
