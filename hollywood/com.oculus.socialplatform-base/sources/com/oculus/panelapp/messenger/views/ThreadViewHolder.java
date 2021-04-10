package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerActionCallback;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadItemV2Binding;

public class ThreadViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(ThreadViewHolder.class);
    public AnytimeTabletMessengerThreadItemV2Binding mBinding;
    public MessengerPanelApp mPanelApp;

    public ThreadViewHolder(AnytimeTabletMessengerThreadItemV2Binding anytimeTabletMessengerThreadItemV2Binding, MessengerPanelApp messengerPanelApp) {
        super(anytimeTabletMessengerThreadItemV2Binding.mRoot);
        this.mBinding = anytimeTabletMessengerThreadItemV2Binding;
        this.mPanelApp = messengerPanelApp;
        setThreadItemHaptics();
    }

    private void setThreadItemHaptics() {
        this.mBinding.threadItem.setAddStatesFromChildren(true);
        this.mBinding.threadItem.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadViewHolder$eKke5xOB7HA65rcTcoXwpRiYYRU2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return ThreadViewHolder.this.lambda$setThreadItemHaptics$1$ThreadViewHolder(view, motionEvent);
            }
        });
        this.mBinding.threadFacepile.setOnHoverListener($$Lambda$ThreadViewHolder$8vf2p2HaH8OuMaZBbRMG3fX_Aqw2.INSTANCE);
    }

    public void bindThread(MessengerThread messengerThread) {
        LinearLayout linearLayout;
        boolean z;
        this.mBinding.setThread(messengerThread);
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread() != null && messengerThread.getThreadKey() == this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread().getThreadKey() && this.mPanelApp.getAPIManager().mDraftThread == null) {
            linearLayout = this.mBinding.threadItem;
            z = true;
        } else {
            linearLayout = this.mBinding.threadItem;
            z = false;
        }
        linearLayout.setSelected(z);
        this.mBinding.threadItem.setOnClickListener(new View.OnClickListener(messengerThread) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadViewHolder$fg5gdexrRex1yVvrpsLoF4e57Y82 */
            public final /* synthetic */ MessengerThread f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ThreadViewHolder.this.lambda$bindThread$0$ThreadViewHolder(this.f$1, view);
            }
        });
        this.mBinding.setThread(messengerThread);
        this.mBinding.threadFacepile.setIcon(this.mPanelApp, messengerThread);
    }

    public void onViewRecycled() {
        this.mBinding.threadFacepile.destroy(this.mPanelApp);
    }

    public static /* synthetic */ boolean lambda$setThreadItemHaptics$2(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            view.setHovered(true);
            return false;
        }
        if (actionMasked == 10) {
            view.setHovered(false);
        }
        return false;
    }

    public /* synthetic */ void lambda$bindThread$0$ThreadViewHolder(MessengerThread messengerThread, View view) {
        messengerThread.getThreadKey();
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_LIST_ITEM, SurfaceType.THREAD_VIEW);
        this.mPanelApp.onButtonClick();
        this.mPanelApp.getAPIManager().updateDraftThread(null);
        final long currentTimeMillis = System.currentTimeMillis();
        this.mPanelApp.getAPIManager().mCurrentAPI.updateCurrentThread(messengerThread.getThreadKey(), new MessengerActionCallback() {
            /* class com.oculus.panelapp.messenger.views.ThreadViewHolder.AnonymousClass1 */

            @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
            public void onError(String str, String str2) {
                Log.e(ThreadViewHolder.TAG, "Update current thread failed (from thread list)");
                ThreadViewHolder.this.mPanelApp.getLogger().logActionFailure(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.THREAD_LIST_ITEM, str2, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
            }

            @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
            public void onSuccess(String str) {
                ThreadViewHolder.this.mPanelApp.getLogger().logActionSuccess(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.THREAD_LIST_ITEM, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
            }
        });
    }

    public /* synthetic */ boolean lambda$setThreadItemHaptics$1$ThreadViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mPanelApp.onButtonEnter();
        }
        this.mBinding.threadFacepile.dispatchGenericMotionEvent(motionEvent);
        return false;
    }
}
