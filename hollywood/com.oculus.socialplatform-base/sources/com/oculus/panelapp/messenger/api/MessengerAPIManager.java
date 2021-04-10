package com.oculus.panelapp.messenger.api;

import X.AbstractC12851yS;
import X.AbstractC136820a;
import X.AnonymousClass1xU;
import X.AnonymousClass1y2;
import X.AnonymousClass217;
import X.C137220e;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.panelapp.messenger.api.models.DraftThread;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessengerAPIManager {
    public static final String TAG = "MessengerAPIManager";
    @Nullable
    public IMessengerAPI mCurrentAPI;
    @Nullable
    public DraftThread mDraftThread;
    public List<DraftThreadListener> mDraftThreadListeners = new ArrayList();
    public List<MailboxListener> mMailboxListeners = new ArrayList();
    public final AnonymousClass1xU mMutationCompositeDisposable = new AnonymousClass1xU();
    public List<ThreadListener> mThreadListeners = new ArrayList();

    public interface DraftThreadListener {
        void onDraftThreadUpdate(DraftThread draftThread);
    }

    private void createThread(String str, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
        DraftThread draftThread = this.mDraftThread;
        if (draftThread == null || !draftThread.canSend()) {
            Log.e(TAG, "Message does not meet requirements to send");
            return;
        }
        if (this.mDraftThread.mParticipants.size() == 1) {
            this.mCurrentAPI.sendMessage(str, Long.parseLong(this.mDraftThread.mParticipants.iterator().next().mUserId), messengerActionCallback, messengerActionCallback2);
        } else {
            List list = (List) this.mDraftThread.mParticipants.stream().map($$Lambda$MessengerAPIManager$T9HIbS7XGXArn9rZXwEOB_5zQXc2.INSTANCE).collect(Collectors.toList());
            list.add(this.mCurrentAPI.getUserID());
            this.mMutationCompositeDisposable.A1D(this.mCurrentAPI.getInternalApi().createGroupThreadWithInitialMessage((List) list.stream().map($$Lambda$MessengerAPIManager$HFpTjGC9wpRfkQSvJXDmWgIWiTY2.INSTANCE).collect(Collectors.toList()), str).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(messengerActionCallback2) {
                /* class com.oculus.panelapp.messenger.api.$$Lambda$MessengerAPIManager$oJKxTQtO6r4zb0Fgz1YxtqlMXRQ2 */
                public final /* synthetic */ MessengerActionCallback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // X.AbstractC12851yS
                public final void accept(Object obj) {
                    MessengerAPIManager.this.lambda$createThread$3$MessengerAPIManager(this.f$1, (MessengerThread) obj);
                }
            }, C137220e.A06));
        }
        updateDraftThread(null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyMailboxUpdate(List<MessengerThread> list) {
        for (MailboxListener mailboxListener : this.mMailboxListeners) {
            mailboxListener.onMailboxUpdate(list);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list) {
        for (ThreadListener threadListener : this.mThreadListeners) {
            threadListener.onThreadUpdate(messengerThread, list);
        }
    }

    public void destroy() {
        IMessengerAPI iMessengerAPI = this.mCurrentAPI;
        if (iMessengerAPI != null) {
            iMessengerAPI.setThreadListener(null);
            this.mCurrentAPI.setMailboxListener(null);
            this.mCurrentAPI.destroy();
        }
        this.mMailboxListeners.clear();
        this.mThreadListeners.clear();
        this.mDraftThreadListeners.clear();
        this.mMutationCompositeDisposable.A01();
    }

    public IMessengerAPI getAPI() {
        return this.mCurrentAPI;
    }

    @Nullable
    public DraftThread getDraftThread() {
        return this.mDraftThread;
    }

    public /* synthetic */ void lambda$createThread$3$MessengerAPIManager(MessengerActionCallback messengerActionCallback, MessengerThread messengerThread) throws Exception {
        this.mCurrentAPI.updateCurrentThread(messengerThread.getThreadKey(), null);
        this.mMutationCompositeDisposable.A1D(AbstractC136820a.A06(new AnonymousClass217(this.mCurrentAPI.getInternalApi().getThreadObservable(messengerThread.getThreadKey()), 2).A08(AnonymousClass1y2.A00()), new AbstractC12851yS(messengerActionCallback) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$MessengerAPIManager$r7KyFTHnQHeQ_fQBzXSYLDb8BWs2 */
            public final /* synthetic */ MessengerActionCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MessengerAPIManager.this.lambda$null$2$MessengerAPIManager(this.f$1, (MessengerThread) obj);
            }
        }, C137220e.A06, C137220e.A03, C137220e.A04));
    }

    public /* synthetic */ void lambda$null$2$MessengerAPIManager(MessengerActionCallback messengerActionCallback, MessengerThread messengerThread) throws Exception {
        this.mCurrentAPI.updateCurrentThread(messengerThread.getThreadKey(), messengerActionCallback);
    }

    public void registerCurrentThreadListener(ThreadListener threadListener) {
        IMessengerAPI iMessengerAPI = this.mCurrentAPI;
        if (!(iMessengerAPI == null || iMessengerAPI.getCurrentThread() == null)) {
            threadListener.onThreadUpdate(this.mCurrentAPI.getCurrentThread(), this.mCurrentAPI.getMessages());
        }
        this.mThreadListeners.add(threadListener);
    }

    public void registerDraftThreadListener(DraftThreadListener draftThreadListener) {
        this.mDraftThreadListeners.add(draftThreadListener);
    }

    public void registerMailboxListener(MailboxListener mailboxListener) {
        IMessengerAPI iMessengerAPI = this.mCurrentAPI;
        if (iMessengerAPI != null) {
            mailboxListener.onMailboxUpdate(iMessengerAPI.getThreads());
        }
        this.mMailboxListeners.add(mailboxListener);
    }

    public void removeCurrentThreadListener(ThreadListener threadListener) {
        if (this.mThreadListeners.contains(threadListener)) {
            this.mThreadListeners.remove(threadListener);
        }
    }

    public void removeDraftThreadListener(DraftThreadListener draftThreadListener) {
        if (this.mDraftThreadListeners.contains(draftThreadListener)) {
            this.mDraftThreadListeners.remove(draftThreadListener);
        }
    }

    public void removeMailboxListener(MailboxListener mailboxListener) {
        if (this.mMailboxListeners.contains(mailboxListener)) {
            this.mMailboxListeners.remove(mailboxListener);
        }
    }

    public void sendMessage(String str, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
        IMessengerAPI iMessengerAPI = this.mCurrentAPI;
        if (iMessengerAPI == null) {
            Log.e(TAG, "No api currently set");
        } else if (this.mDraftThread != null) {
            createThread(str, messengerActionCallback, messengerActionCallback2);
        } else {
            iMessengerAPI.sendMessage(str, messengerActionCallback, messengerActionCallback2);
        }
    }

    public void updateAPI(IMessengerAPI iMessengerAPI) {
        this.mCurrentAPI = iMessengerAPI;
        iMessengerAPI.setMailboxListener(new MailboxListener() {
            /* class com.oculus.panelapp.messenger.api.MessengerAPIManager.AnonymousClass1 */

            @Override // com.oculus.panelapp.messenger.api.MailboxListener
            public void onMailboxUpdate(List<MessengerThread> list) {
                MessengerAPIManager.this.notifyMailboxUpdate(list);
            }
        });
        iMessengerAPI.setThreadListener(new ThreadListener() {
            /* class com.oculus.panelapp.messenger.api.MessengerAPIManager.AnonymousClass2 */

            @Override // com.oculus.panelapp.messenger.api.ThreadListener
            public void onThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list) {
                MessengerAPIManager.this.notifyThreadUpdate(messengerThread, list);
            }
        });
        notifyMailboxUpdate(iMessengerAPI.getThreads());
        if (iMessengerAPI.getCurrentThread() != null) {
            notifyThreadUpdate(iMessengerAPI.getCurrentThread(), iMessengerAPI.getMessages());
        }
    }

    public void updateDraftThread(DraftThread draftThread) {
        this.mDraftThread = draftThread;
        for (DraftThreadListener draftThreadListener : this.mDraftThreadListeners) {
            draftThreadListener.onDraftThreadUpdate(draftThread);
        }
    }
}
