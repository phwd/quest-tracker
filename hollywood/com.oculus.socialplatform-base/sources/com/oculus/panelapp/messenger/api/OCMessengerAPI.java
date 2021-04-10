package com.oculus.panelapp.messenger.api;

import X.AbstractC12271xB;
import X.AbstractC12361xL;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC13031yl;
import X.AbstractC13121yu;
import X.AbstractC13241zD;
import X.AbstractC13251zE;
import X.AbstractC13521zj;
import X.AbstractC136820a;
import X.AnonymousClass006;
import X.AnonymousClass1xU;
import X.AnonymousClass1y2;
import X.AnonymousClass210;
import X.AnonymousClass217;
import X.AnonymousClass219;
import X.AnonymousClass21S;
import X.C12581xp;
import X.C12971yf;
import X.C13081yq;
import X.C137220e;
import X.C138720t;
import android.app.Application;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.ocauth.UserAppScopedAuthHelper;
import com.oculus.messengervr.common.utils.Triplet;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.messengervr.oc.OcApiConfig;
import com.oculus.messengervr.oc.OcMessengerVrApi;
import com.oculus.messengervr.oc.OcThreadParticipantsBlockStatusQueryHandler;
import com.oculus.messengervr.oc.OcUserPictureUrlsQueryHandler;
import com.oculus.panelapp.messenger.fetchers.OCParticipantBlockStatusFetcher;
import com.oculus.panelapp.messenger.fetchers.OCParticipantPictureUrlsFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.OkHttpClient;

public final class OCMessengerAPI implements IMessengerAPI {
    public static final String TAG = "OCMessengerAPI";
    public static OcMessengerVrApi sOcMessengerVrApi;
    public final Application mApplication;
    public final UserAppScopedAuthHelper mAuthHelper;
    @Nullable
    public MessengerThread mCurrentThread;
    @Nullable
    public AbstractC12271xB mCurrentThreadAndMessagesAndParticipantsDisposable;
    public final AtomicBoolean mHasCurrentThreadInitialized;
    public AnonymousClass210<Boolean> mIsThreadUnreadSubject;
    public MailboxListener mMailboxListener;
    public final AnonymousClass210<Integer> mMessageListRequestedMessagesCountSubject;
    public final AnonymousClass1xU mMutationCompositeDisposable = new AnonymousClass1xU();
    public OkHttpClient mOkHttpClient;
    @Nullable
    public AbstractC12271xB mThreadListDisposable;
    public final AnonymousClass210<Integer> mThreadListRequestedThreadsCountSubject;
    public ThreadListener mThreadListener;
    public List<MessengerMessage> mThreadMessages;
    public List<MessengerParticipant> mThreadParticipants;
    @Nullable
    public AbstractC12271xB mThreadParticipantsDisposable;
    public List<MessengerThread> mThreads;
    public final String mUserID;
    public final AbstractC12271xB sOcMessengerVrApiReadyDisposable;

    private void autoMarkThreadAsRead(long j) {
        AnonymousClass210<Boolean> r4 = this.mIsThreadUnreadSubject;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        AbstractC12361xL r2 = C12581xp.A02;
        AnonymousClass219.A01(timeUnit, "unit is null");
        AnonymousClass219.A01(r2, "scheduler is null");
        AbstractC136820a A0D = new C13081yq(r4, timeUnit, r2).A0D(new AbstractC13031yl(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$uTje6pBk35ctiG250hoGyGgacyA2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return OCMessengerAPI.lambda$autoMarkThreadAsRead$15(this.f$0, (Boolean) obj);
            }
        });
        $$Lambda$OCMessengerAPI$eAKzDy3NkI04gB97neN_GDBq002 r0 = new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$eAKzDy3NkI04gB97neN_GDBq002 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                Log.e(OCMessengerAPI.TAG, AnonymousClass006.A06("Auto-marking thread read throws threadKey=", this.f$0), (Throwable) obj);
            }
        };
        AbstractC12851yS<Object> r3 = C137220e.A04;
        AbstractC12881yV r22 = C137220e.A03;
        this.mMutationCompositeDisposable.A1D(AbstractC136820a.A06(AbstractC136820a.A00(A0D, r3, r0, r22, r22), r3, C137220e.A06, r22, r3));
    }

    public static /* synthetic */ void lambda$new$3() throws Exception {
    }

    public static /* synthetic */ void lambda$null$14() throws Exception {
    }

    public static /* synthetic */ void lambda$sendMessage$9(MessengerActionCallback messengerActionCallback, long j, Throwable th) throws Exception {
        Log.e(TAG, "MessengerVrApi error: ", th);
        if (messengerActionCallback != null) {
            messengerActionCallback.onError(Long.toString(j), th.getMessage());
        }
    }

    private void onMailboxUpdate(List<MessengerThread> list) {
        this.mThreads = list;
        boolean z = true;
        if (this.mHasCurrentThreadInitialized.compareAndSet(false, true) && this.mCurrentThread == null && !list.isEmpty()) {
            long j = CurrentThreadKeyCache.get(this.mApplication.getApplicationContext(), MessengerAPIType.OC_CHATS);
            if (j != -1) {
                Iterator<MessengerThread> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getThreadKey() == j) {
                            updateCurrentThread(j, null);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (this.mCurrentThread == null && !z) {
                    updateCurrentThread(list.get(0).getThreadKey(), null);
                }
            }
            z = false;
            updateCurrentThread(list.get(0).getThreadKey(), null);
        }
        MailboxListener mailboxListener = this.mMailboxListener;
        if (mailboxListener != null) {
            mailboxListener.onMailboxUpdate(list);
        }
    }

    private void onThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list) {
        ThreadListener threadListener = this.mThreadListener;
        if (threadListener != null) {
            threadListener.onThreadUpdate(messengerThread, list);
        }
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void destroy() {
        AbstractC12271xB r0 = this.mThreadListDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        AbstractC12271xB r02 = this.mThreadParticipantsDisposable;
        if (r02 != null) {
            r02.dispose();
            this.mThreadParticipantsDisposable = null;
        }
        AbstractC12271xB r03 = this.mCurrentThreadAndMessagesAndParticipantsDisposable;
        if (r03 != null) {
            r03.dispose();
            this.mCurrentThreadAndMessagesAndParticipantsDisposable = null;
        }
        this.sOcMessengerVrApiReadyDisposable.dispose();
        this.mMutationCompositeDisposable.A01();
        this.mThreadListener = null;
        this.mMailboxListener = null;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public MessengerThread getCurrentThread() {
        return this.mCurrentThread;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public MessengerVrApi getInternalApi() {
        return sOcMessengerVrApi;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerMessage> getMessages() {
        return this.mThreadMessages;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerParticipant> getThreadParticipants() {
        return this.mThreadParticipants;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerThread> getThreads() {
        return this.mThreads;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public MessengerAPIType getType() {
        return MessengerAPIType.OC_CHATS;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public String getUserID() {
        return this.mUserID;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void initialize() {
        AbstractC12271xB r0 = this.mThreadListDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        this.mThreadListDisposable = AbstractC136820a.A06(sOcMessengerVrApi.getThreadListObservable(this.mThreadListRequestedThreadsCountSubject).A08(AnonymousClass1y2.A00()), new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$9xf3wpclJOJA4lqlKdq1HDX5282 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCMessengerAPI.this.lambda$initialize$5$OCMessengerAPI((MessengerThread[]) obj);
            }
        }, $$Lambda$OCMessengerAPI$id5ywavSJ_0BVNrj9ExnsX9Oew2.INSTANCE, C137220e.A03, C137220e.A04);
    }

    public /* synthetic */ OcApiConfig lambda$new$0$OCMessengerAPI(Application application, String str) throws Exception {
        OcApiConfig.Builder builder = new OcApiConfig.Builder();
        builder.mContext = application;
        builder.mUserId = this.mUserID;
        builder.mAccessToken = str;
        builder.mAppId = "1953748974690454";
        return builder.build();
    }

    public /* synthetic */ OcUserPictureUrlsQueryHandler lambda$new$1$OCMessengerAPI(OcApiConfig ocApiConfig) throws Exception {
        return new OCParticipantPictureUrlsFetcher(ocApiConfig.mAccessToken, this.mOkHttpClient);
    }

    public /* synthetic */ OcThreadParticipantsBlockStatusQueryHandler lambda$new$2$OCMessengerAPI(OcApiConfig ocApiConfig) throws Exception {
        return new OCParticipantBlockStatusFetcher(ocApiConfig.mAccessToken, this.mOkHttpClient);
    }

    public /* synthetic */ void lambda$sendMessage$8$OCMessengerAPI(MessengerActionCallback messengerActionCallback, MessengerActionCallback messengerActionCallback2, long j, String str) throws Exception {
        this.mMutationCompositeDisposable.A1D(AbstractC136820a.A06(new AnonymousClass217(sOcMessengerVrApi.getThreadObservable(Long.parseLong(str)), 1), new AbstractC12851yS(messengerActionCallback) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$EuYq6F28ORlwTmCSpkitTZubPgg2 */
            public final /* synthetic */ MessengerActionCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCMessengerAPI.this.lambda$null$7$OCMessengerAPI(this.f$1, (MessengerThread) obj);
            }
        }, C137220e.A06, C137220e.A03, C137220e.A04));
        if (messengerActionCallback2 != null) {
            messengerActionCallback2.onSuccess(Long.toString(j));
        }
    }

    public /* synthetic */ void lambda$updateCurrentThread$12$OCMessengerAPI(long j, Triplet triplet) throws Exception {
        CurrentThreadKeyCache.put(this.mApplication.getApplicationContext(), MessengerAPIType.OC_CHATS, j);
        A a = triplet.first;
        this.mCurrentThread = a;
        this.mIsThreadUnreadSubject.onNext(Boolean.valueOf(a.isUnread()));
        this.mThreadParticipants = Arrays.asList((Object[]) triplet.third);
        onThreadUpdate(getCurrentThread(), Arrays.asList((Object[]) triplet.second));
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void leaveGroupThread(MessengerThread messengerThread) {
        sOcMessengerVrApi.removeParticipantFromGroup(messengerThread.getThreadKey(), Long.valueOf(getUserID()).longValue());
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateCurrentThread(long j, @Nullable MessengerActionCallback messengerActionCallback) {
        this.mHasCurrentThreadInitialized.set(true);
        AbstractC12271xB r0 = this.mCurrentThreadAndMessagesAndParticipantsDisposable;
        if (r0 != null) {
            r0.dispose();
            this.mCurrentThreadAndMessagesAndParticipantsDisposable = null;
        }
        this.mIsThreadUnreadSubject = new AnonymousClass210<>();
        autoMarkThreadAsRead(j);
        AbstractC136820a<MessengerThread> threadObservable = sOcMessengerVrApi.getThreadObservable(j);
        AbstractC136820a<MessengerMessage[]> A0F = sOcMessengerVrApi.getMessageListObservable(j, this.mMessageListRequestedMessagesCountSubject).A0F(new MessengerMessage[0]);
        AbstractC136820a<MessengerParticipant[]> participantListObservable = sOcMessengerVrApi.getParticipantListObservable(j);
        $$Lambda$SpCATiFJMBoc7l72Dz5cUjZb4s02 r1 = $$Lambda$SpCATiFJMBoc7l72Dz5cUjZb4s02.INSTANCE;
        AnonymousClass219.A01(threadObservable, "source1 is null");
        AnonymousClass219.A01(A0F, "source2 is null");
        AnonymousClass219.A01(participantListObservable, "source3 is null");
        AnonymousClass219.A01(r1, "f is null");
        this.mCurrentThreadAndMessagesAndParticipantsDisposable = AbstractC136820a.A06(AbstractC136820a.A04(new C138720t(r1), AbstractC13521zj.A00, threadObservable, A0F, participantListObservable).A08(AnonymousClass1y2.A00()), new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$oJrOOD3109l8LW5PEqTaisWqpGs2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCMessengerAPI.this.lambda$updateCurrentThread$12$OCMessengerAPI(this.f$1, (Triplet) obj);
            }
        }, $$Lambda$OCMessengerAPI$CLO7wOKLEUPEBf5OAc72v25ByA42.INSTANCE, C137220e.A03, C137220e.A04);
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateMessageCount(int i) {
        if (i != this.mMessageListRequestedMessagesCountSubject.A0K().intValue()) {
            this.mMessageListRequestedMessagesCountSubject.onNext(Integer.valueOf(i));
        }
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateThreadCount(int i) {
        if (i != this.mThreadListRequestedThreadsCountSubject.A0K().intValue()) {
            this.mThreadListRequestedThreadsCountSubject.onNext(Integer.valueOf(i));
        }
    }

    public OCMessengerAPI(Application application, String str, OkHttpClient okHttpClient, UserAppScopedAuthHelper userAppScopedAuthHelper) {
        AnonymousClass210<Integer> r1 = new AnonymousClass210<>();
        AtomicReference<Object> atomicReference = r1.A04;
        AnonymousClass219.A01(5, "defaultValue is null");
        atomicReference.lazySet(5);
        this.mThreadListRequestedThreadsCountSubject = r1;
        AnonymousClass210<Integer> r12 = new AnonymousClass210<>();
        AtomicReference<Object> atomicReference2 = r12.A04;
        AnonymousClass219.A01(5, "defaultValue is null");
        atomicReference2.lazySet(5);
        this.mMessageListRequestedMessagesCountSubject = r12;
        this.mIsThreadUnreadSubject = new AnonymousClass210<>();
        this.mHasCurrentThreadInitialized = new AtomicBoolean(false);
        this.mThreads = new ArrayList();
        this.mThreadMessages = new ArrayList();
        this.mThreadParticipants = new ArrayList();
        this.mUserID = str;
        this.mOkHttpClient = okHttpClient;
        this.mAuthHelper = userAppScopedAuthHelper;
        this.mApplication = application;
        OcMessengerVrApi ocMessengerVrApi = sOcMessengerVrApi;
        if (ocMessengerVrApi == null) {
            AbstractC13251zE<R> A04 = userAppScopedAuthHelper.queryAccessToken().A04(new AbstractC13031yl(application) {
                /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$BsUew7U9LMG9xAKVbHxexZ_FJU2 */
                public final /* synthetic */ Application f$1;

                {
                    this.f$1 = r2;
                }

                @Override // X.AbstractC13031yl
                public final Object apply(Object obj) {
                    return OCMessengerAPI.this.lambda$new$0$OCMessengerAPI(this.f$1, (String) obj);
                }
            });
            ocMessengerVrApi = new OcMessengerVrApi((AbstractC13251zE<OcApiConfig>) A04, (AbstractC13251zE<OcUserPictureUrlsQueryHandler>) A04.A04(new AbstractC13031yl() {
                /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$9DOz6g0QxBRB0Bmh8H_TNjVGKg2 */

                @Override // X.AbstractC13031yl
                public final Object apply(Object obj) {
                    return OCMessengerAPI.this.lambda$new$1$OCMessengerAPI((OcApiConfig) obj);
                }
            }), (AbstractC13251zE<OcThreadParticipantsBlockStatusQueryHandler>) A04.A04(new AbstractC13031yl() {
                /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$mXdwjH3rhyPKkVYcmBo2bquAdo2 */

                @Override // X.AbstractC13031yl
                public final Object apply(Object obj) {
                    return OCMessengerAPI.this.lambda$new$2$OCMessengerAPI((OcApiConfig) obj);
                }
            }), (Integer) 3, (String) null);
            sOcMessengerVrApi = ocMessengerVrApi;
        }
        this.sOcMessengerVrApiReadyDisposable = ocMessengerVrApi.getApiReadyCompletable().A02($$Lambda$OCMessengerAPI$BOCVF5Fe_Jyiv1x0MKlUq4rgAHg2.INSTANCE, $$Lambda$OCMessengerAPI$8pmZauWFWv9rgMvy7CDp0CSRqF82.INSTANCE);
    }

    public static /* synthetic */ AbstractC13121yu lambda$autoMarkThreadAsRead$15(long j, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return sOcMessengerVrApi.markThreadAsRead(j).A00($$Lambda$OCMessengerAPI$BNaq5y5EFwN4J5vXLMluZTMH4DA2.INSTANCE).A01();
        }
        return AnonymousClass21S.A00;
    }

    public /* synthetic */ void lambda$initialize$5$OCMessengerAPI(MessengerThread[] messengerThreadArr) throws Exception {
        onMailboxUpdate(Arrays.asList(messengerThreadArr));
    }

    public /* synthetic */ void lambda$null$7$OCMessengerAPI(MessengerActionCallback messengerActionCallback, MessengerThread messengerThread) throws Exception {
        updateCurrentThread(messengerThread.getThreadKey(), messengerActionCallback);
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void markThreadAsRead(MessengerThread messengerThread) {
        messengerThread.getThreadKey();
        AbstractC13241zD markThreadAsRead = sOcMessengerVrApi.markThreadAsRead(messengerThread.getThreadKey());
        AbstractC12361xL A00 = AnonymousClass1y2.A00();
        AnonymousClass219.A01(A00, "scheduler is null");
        this.mMutationCompositeDisposable.A1D(new C12971yf(markThreadAsRead, A00).A02(new AbstractC12881yV() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$8ljnLNukzhtoXjRf1fmvA8hJJ02 */

            @Override // X.AbstractC12881yV
            public final void run() {
                MessengerThread.this.getThreadKey();
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$fzZ9L5fc9vggjsbvyNqsOChkgA02 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                Log.e(OCMessengerAPI.TAG, AnonymousClass006.A06("markThreadAsRead error: threadKey = ", MessengerThread.this.getThreadKey()), (Throwable) obj);
            }
        }));
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void setMailboxListener(MailboxListener mailboxListener) {
        this.mMailboxListener = mailboxListener;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void setThreadListener(ThreadListener threadListener) {
        this.mThreadListener = threadListener;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void sendMessage(String str, long j, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
        this.mMutationCompositeDisposable.A1D(sOcMessengerVrApi.sendMessage(j, str).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(messengerActionCallback2, messengerActionCallback, j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$wfMs95t6OGYFWNcFbB03IaAiU2 */
            public final /* synthetic */ MessengerActionCallback f$1;
            public final /* synthetic */ MessengerActionCallback f$2;
            public final /* synthetic */ long f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCMessengerAPI.this.lambda$sendMessage$8$OCMessengerAPI(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        }, new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$OCMessengerAPI$aBFeOieNB0Ra49RSEXCKpMabo2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                OCMessengerAPI.lambda$sendMessage$9(MessengerActionCallback.this, this.f$1, (Throwable) obj);
            }
        }));
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void sendMessage(String str, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
        MessengerThread messengerThread = this.mCurrentThread;
        if (messengerThread != null) {
            sendMessage(str, messengerThread.getThreadKey(), messengerActionCallback, messengerActionCallback2);
        }
    }
}
