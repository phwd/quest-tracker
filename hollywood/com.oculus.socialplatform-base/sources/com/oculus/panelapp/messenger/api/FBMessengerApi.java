package com.oculus.panelapp.messenger.api;

import X.AbstractC12271xB;
import X.AbstractC12361xL;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC13031yl;
import X.AbstractC13121yu;
import X.AbstractC13241zD;
import X.AbstractC13251zE;
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
import android.app.Application;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.oculus.common.fbauth.FBAuthManager;
import com.oculus.common.socialtablet.fbauth.FBAccountManager;
import com.oculus.common.socialtablet.fbauth.FBUser;
import com.oculus.messengervr.fb.ApiConfig;
import com.oculus.messengervr.fb.FbMessengerVrApi;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;

public final class FBMessengerApi implements IMessengerAPI {
    public static final String TAG = "FBMessengerApi";
    public static FbMessengerVrApi sFbMessengerVrApi;
    @Nullable
    public static FBUser sFbUser;
    public final Application mApplication;
    @Nullable
    public MessengerThread mCurrentThread;
    @Nullable
    public AbstractC12271xB mCurrentThreadAndParticipantsDisposable;
    public final AbstractC12271xB mFbMessengerVrApiReadyDisposable;
    public final AtomicBoolean mHasCurrentThreadInitialized = new AtomicBoolean(false);
    public final AtomicBoolean mIsFirstCurrentThreadUpdate = new AtomicBoolean(true);
    @Nullable
    public AnonymousClass210<Long> mLastMessageTimestampMsSubject;
    @Nullable
    public AnonymousClass210<Long> mLastReadWatermarkTimestampMsSubject;
    @Nullable
    public MailboxListener mMailboxListener;
    public final AnonymousClass210<Integer> mMessageListRequestedMessagesCountSubject;
    @Nullable
    public AbstractC12271xB mMessagesDisposable;
    public final AnonymousClass1xU mMutationCompositeDisposable = new AnonymousClass1xU();
    @Nullable
    public AbstractC12271xB mThreadListDisposable;
    public final AnonymousClass210<Integer> mThreadListRequestedThreadsCountSubject;
    @Nullable
    public ThreadListener mThreadListener;
    public List<MessengerMessage> mThreadMessages;
    public List<MessengerParticipant> mThreadParticipants;
    @Nullable
    public AbstractC12271xB mThreadParticipantsDisposable;
    @Nullable
    public AbstractC12271xB mThreadReadReceiptDisposable;
    public List<MessengerThread> mThreads;

    public /* synthetic */ void lambda$updateCurrentThread$15$FBMessengerApi(MessengerActionCallback messengerActionCallback, long j, MessengerMessage[] messengerMessageArr) throws Exception {
        int length = messengerMessageArr.length;
        if (this.mCurrentThread != null) {
            if (length > 0) {
                MessengerMessage messengerMessage = messengerMessageArr[length - 1];
                if (messengerMessage != null) {
                    this.mLastMessageTimestampMsSubject.onNext(Long.valueOf(messengerMessage.getTimestampMs()));
                }
                if (this.mIsFirstCurrentThreadUpdate.get() && messengerActionCallback != null) {
                    messengerActionCallback.onSuccess(String.valueOf(j));
                }
            } else if (this.mIsFirstCurrentThreadUpdate.get() && messengerActionCallback != null) {
                messengerActionCallback.onError(String.valueOf(j), "Failed to get messages");
            }
            this.mIsFirstCurrentThreadUpdate.set(false);
            onThreadUpdate(getCurrentThread(), Arrays.asList(messengerMessageArr));
        }
    }

    private void autoMarkThreadAsRead(long j) {
        AbstractC12271xB r0 = this.mThreadReadReceiptDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        AbstractC136820a A03 = AbstractC136820a.A03((AbstractC13121yu) Objects.requireNonNull(this.mLastMessageTimestampMsSubject, "mLastMessageTimestampMsSubject should not be null"), (AbstractC13121yu) Objects.requireNonNull(this.mLastReadWatermarkTimestampMsSubject, "mLastReadWatermarkTimestampMsSubject should not be null"), $$Lambda$o5xL9Vg8_IG_N5Rsn9dr1SCNSA2.INSTANCE);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        AbstractC12361xL r2 = C12581xp.A02;
        AnonymousClass219.A01(timeUnit, "unit is null");
        AnonymousClass219.A01(r2, "scheduler is null");
        AbstractC136820a A0E = new C13081yq(A03, timeUnit, r2).A0E(new AbstractC13031yl(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$KcpXVVli7p6bQPvTmRo1884mIk2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return FBMessengerApi.lambda$autoMarkThreadAsRead$11(this.f$0, (Pair) obj);
            }
        });
        $$Lambda$FBMessengerApi$mEXVgjH3Sd9mYlFNdnQDr41yts2 r02 = new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$mEXVgjH3Sd9mYlFNdnQDr41yts2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                Log.e(FBMessengerApi.TAG, AnonymousClass006.A06("createMarkThreadReadObservableIfNewMessage throws threadKey=", this.f$0), (Throwable) obj);
            }
        };
        AbstractC12851yS<Object> r3 = C137220e.A04;
        AbstractC12881yV r22 = C137220e.A03;
        this.mThreadReadReceiptDisposable = AbstractC136820a.A06(AbstractC136820a.A00(A0E, r3, r02, r22, r22), r3, C137220e.A06, r22, r3);
    }

    public static /* synthetic */ AbstractC13121yu lambda$autoMarkThreadAsRead$11(long j, Pair pair) throws Exception {
        if (((Number) pair.first).longValue() > ((Number) pair.second).longValue()) {
            return sFbMessengerVrApi.markThreadAsRead(j).A00($$Lambda$FBMessengerApi$fDXL6rFp4gFU6j4qLwT8pxJyZJw2.INSTANCE).A01();
        }
        return AnonymousClass21S.A00;
    }

    public static /* synthetic */ ApiConfig lambda$new$0(Application application, Pair pair) throws Exception {
        FBUser fBUser = (FBUser) pair.first;
        sFbUser = fBUser;
        ApiConfig.Builder builder = new ApiConfig.Builder();
        builder.mContext = application;
        builder.mAppId = "581956559359077";
        builder.mAppName = FBAppConfig.MESSENGER_VR_APP_NAME;
        builder.mClientToken = FBAppConfig.MESSENGER_VR_CLIENT_TOKEN;
        builder.mUserId = fBUser.mUserId;
        builder.mAccessToken = (String) pair.second;
        return builder.build();
    }

    public static /* synthetic */ void lambda$new$1() throws Exception {
    }

    public static /* synthetic */ void lambda$null$10() throws Exception {
    }

    public static /* synthetic */ void lambda$sendMessage$7(MessengerActionCallback messengerActionCallback, long j, Throwable th) throws Exception {
        Log.e(TAG, "MessengerVrApi error: ", th);
        if (messengerActionCallback != null) {
            messengerActionCallback.onError(Long.toString(j), th.getMessage());
        }
    }

    private void onMailboxUpdate(List<MessengerThread> list) {
        this.mThreads = list;
        boolean z = true;
        if (this.mHasCurrentThreadInitialized.compareAndSet(false, true) && this.mCurrentThread == null && !list.isEmpty()) {
            long j = CurrentThreadKeyCache.get(this.mApplication.getApplicationContext(), MessengerAPIType.FB_MSYS);
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
        AbstractC12271xB r02 = this.mMessagesDisposable;
        if (r02 != null) {
            r02.dispose();
        }
        AbstractC12271xB r03 = this.mThreadReadReceiptDisposable;
        if (r03 != null) {
            r03.dispose();
        }
        AbstractC12271xB r1 = this.mCurrentThreadAndParticipantsDisposable;
        if (r1 != null) {
            r1.dispose();
            this.mCurrentThreadAndParticipantsDisposable = null;
        }
        this.mThreadListener = null;
        this.mMailboxListener = null;
        this.mFbMessengerVrApiReadyDisposable.dispose();
        this.mMutationCompositeDisposable.A01();
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public MessengerThread getCurrentThread() {
        return this.mCurrentThread;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public MessengerVrApi getInternalApi() {
        return sFbMessengerVrApi;
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
        return MessengerAPIType.FB_MSYS;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public String getUserID() {
        FBUser fBUser = sFbUser;
        if (fBUser != null) {
            return fBUser.mUserId;
        }
        return null;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void initialize() {
        AbstractC12271xB r0 = this.mThreadListDisposable;
        if (r0 != null) {
            r0.dispose();
        }
        this.mThreadListDisposable = AbstractC136820a.A06(sFbMessengerVrApi.getThreadListObservable(this.mThreadListRequestedThreadsCountSubject).A08(AnonymousClass1y2.A00()), new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$LDpDVlJ_9otFvwKJo7QBEhok6Tw2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.this.lambda$initialize$3$FBMessengerApi((MessengerThread[]) obj);
            }
        }, $$Lambda$FBMessengerApi$PZTbGxkotL8Cp9NcqjOPSNEpY2.INSTANCE, C137220e.A03, C137220e.A04);
    }

    public /* synthetic */ void lambda$sendMessage$6$FBMessengerApi(long j, MessengerActionCallback messengerActionCallback, MessengerActionCallback messengerActionCallback2, String str) throws Exception {
        this.mMutationCompositeDisposable.A1D(AbstractC136820a.A06(new AnonymousClass217(sFbMessengerVrApi.getThreadObservable(j), 1), new AbstractC12851yS(messengerActionCallback) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$Xo41ZWyqYf4HeDQST3546N9xQA2 */
            public final /* synthetic */ MessengerActionCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.this.lambda$null$5$FBMessengerApi(this.f$1, (MessengerThread) obj);
            }
        }, C137220e.A06, C137220e.A03, C137220e.A04));
        if (messengerActionCallback2 != null) {
            messengerActionCallback2.onSuccess(Long.toString(j));
        }
    }

    public /* synthetic */ void lambda$updateCurrentThread$13$FBMessengerApi(long j, Pair pair) throws Exception {
        CurrentThreadKeyCache.put(this.mApplication.getApplicationContext(), MessengerAPIType.FB_MSYS, j);
        MessengerThread messengerThread = (MessengerThread) pair.first;
        this.mCurrentThread = messengerThread;
        messengerThread.getLastReadWatermarkTimestampMs().ifPresent(new Consumer() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$4zxf2IwM83OTus1mJbqCOXsDqws2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass210.this.onNext(obj);
            }
        });
        this.mThreadParticipants = Arrays.asList((Object[]) pair.second);
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void leaveGroupThread(MessengerThread messengerThread) {
        sFbMessengerVrApi.removeParticipantFromGroup(messengerThread.getThreadKey(), Long.valueOf(getUserID()).longValue());
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void markThreadAsRead(MessengerThread messengerThread) {
        AbstractC13241zD markThreadAsRead = sFbMessengerVrApi.markThreadAsRead(messengerThread.getThreadKey());
        AbstractC12361xL A00 = AnonymousClass1y2.A00();
        AnonymousClass219.A01(A00, "scheduler is null");
        this.mMutationCompositeDisposable.A1D(new C12971yf(markThreadAsRead, A00).A02(new AbstractC12881yV() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$NK39aARtMqhqb6pOw1gv5ljCh502 */

            @Override // X.AbstractC12881yV
            public final void run() {
                MessengerThread.this.getThreadKey();
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$tXFJVpZxSaPNgm9Y4vN6uFyASM2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                Log.e(FBMessengerApi.TAG, AnonymousClass006.A06("markThreadAsRead error: threadKey = ", MessengerThread.this.getThreadKey()), (Throwable) obj);
            }
        }));
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateCurrentThread(long j, @Nullable MessengerActionCallback messengerActionCallback) {
        this.mHasCurrentThreadInitialized.set(true);
        this.mIsFirstCurrentThreadUpdate.set(true);
        this.mCurrentThread = null;
        AbstractC12271xB r0 = this.mCurrentThreadAndParticipantsDisposable;
        if (r0 != null) {
            r0.dispose();
            this.mCurrentThreadAndParticipantsDisposable = null;
        }
        AbstractC12271xB r02 = this.mMessagesDisposable;
        if (r02 != null) {
            r02.dispose();
            this.mMessagesDisposable = null;
        }
        this.mLastMessageTimestampMsSubject = new AnonymousClass210<>();
        this.mLastReadWatermarkTimestampMsSubject = new AnonymousClass210<>();
        autoMarkThreadAsRead(j);
        AbstractC136820a A08 = AbstractC136820a.A03(sFbMessengerVrApi.getThreadObservable(j), sFbMessengerVrApi.getParticipantListObservable(j).A0F(new MessengerParticipant[0]), $$Lambda$c64Vzk94MyYORYmDob3rPElTps2.INSTANCE).A08(AnonymousClass1y2.A00());
        $$Lambda$FBMessengerApi$kZVlmkA39bYt9RkaMq7ZL_vVvh02 r1 = new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$kZVlmkA39bYt9RkaMq7ZL_vVvh02 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.this.lambda$updateCurrentThread$13$FBMessengerApi(this.f$1, (Pair) obj);
            }
        };
        $$Lambda$FBMessengerApi$lamL1VsOmnCVw5QOVqjbomT4pDQ2 r03 = new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$lamL1VsOmnCVw5QOVqjbomT4pDQ2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                Log.e(FBMessengerApi.TAG, AnonymousClass006.A06("thread and participants disposable throws threadKey=", this.f$0), (Throwable) obj);
            }
        };
        AbstractC12881yV r4 = C137220e.A03;
        AbstractC12851yS<Object> r3 = C137220e.A04;
        this.mCurrentThreadAndParticipantsDisposable = AbstractC136820a.A06(A08, r1, r03, r4, r3);
        this.mMessagesDisposable = AbstractC136820a.A06(sFbMessengerVrApi.getMessageListObservable(j, this.mMessageListRequestedMessagesCountSubject).A08(AnonymousClass1y2.A00()), new AbstractC12851yS(messengerActionCallback, j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$APdWCPK2YXB3AIktWGgnI22uHRI2 */
            public final /* synthetic */ MessengerActionCallback f$1;
            public final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.this.lambda$updateCurrentThread$15$FBMessengerApi(this.f$1, this.f$2, (MessengerMessage[]) obj);
            }
        }, $$Lambda$FBMessengerApi$BNoIvZ5rDCynVs45sUo0U2AYbmE2.INSTANCE, r4, r3);
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

    public FBMessengerApi(Application application, OkHttpClient okHttpClient) {
        AnonymousClass210<Integer> r1 = new AnonymousClass210<>();
        AtomicReference<Object> atomicReference = r1.A04;
        AnonymousClass219.A01(8, "defaultValue is null");
        atomicReference.lazySet(8);
        this.mThreadListRequestedThreadsCountSubject = r1;
        AnonymousClass210<Integer> r12 = new AnonymousClass210<>();
        AtomicReference<Object> atomicReference2 = r12.A04;
        AnonymousClass219.A01(5, "defaultValue is null");
        atomicReference2.lazySet(5);
        this.mMessageListRequestedMessagesCountSubject = r12;
        this.mLastMessageTimestampMsSubject = new AnonymousClass210<>();
        this.mLastReadWatermarkTimestampMsSubject = new AnonymousClass210<>();
        this.mThreads = new ArrayList();
        this.mThreadMessages = new ArrayList();
        this.mThreadParticipants = new ArrayList();
        this.mApplication = application;
        FbMessengerVrApi fbMessengerVrApi = sFbMessengerVrApi;
        if (fbMessengerVrApi == null) {
            AbstractC13251zE A04 = AbstractC13251zE.A01(FBAccountManager.queryUser(), FBAuthManager.queryAccessToken(), $$Lambda$2Y29_NmIqqohra5X0yTcejSkU2.INSTANCE).A04(new AbstractC13031yl(application) {
                /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$VszSHmizW5V8OW5IIkLdz5ufmkg2 */
                public final /* synthetic */ Application f$0;

                {
                    this.f$0 = r1;
                }

                @Override // X.AbstractC13031yl
                public final Object apply(Object obj) {
                    return FBMessengerApi.lambda$new$0(this.f$0, (Pair) obj);
                }
            });
            UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance(application);
            fbMessengerVrApi = new FbMessengerVrApi(okHttpClient, A04, new VrMessengerMqttAnalyticsLogger(instance), new VrMessengerMsysAnalyticsLogger(instance), 3, null);
            sFbMessengerVrApi = fbMessengerVrApi;
        }
        this.mFbMessengerVrApiReadyDisposable = fbMessengerVrApi.getApiReadyCompletable().A02($$Lambda$FBMessengerApi$QU5jA8NIdvu9crjVn8Jjo6YVX02.INSTANCE, $$Lambda$FBMessengerApi$pGxp85xLChTkTVP8BEF4ZzP8OWE2.INSTANCE);
    }

    public /* synthetic */ void lambda$initialize$3$FBMessengerApi(MessengerThread[] messengerThreadArr) throws Exception {
        onMailboxUpdate(Arrays.asList(messengerThreadArr));
    }

    public /* synthetic */ void lambda$null$5$FBMessengerApi(MessengerActionCallback messengerActionCallback, MessengerThread messengerThread) throws Exception {
        updateCurrentThread(messengerThread.getThreadKey(), messengerActionCallback);
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
        this.mMutationCompositeDisposable.A1D(sFbMessengerVrApi.sendMessage(j, str).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(j, messengerActionCallback2, messengerActionCallback) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$MbZXkCTdkWZRO8Pr25ZfWhEjUc2 */
            public final /* synthetic */ long f$1;
            public final /* synthetic */ MessengerActionCallback f$2;
            public final /* synthetic */ MessengerActionCallback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r4;
                this.f$3 = r5;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.this.lambda$sendMessage$6$FBMessengerApi(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        }, new AbstractC12851yS(j) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$FBMessengerApi$LJGqAyD8nfXCJ0_EEGw7h44Ha9A2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBMessengerApi.lambda$sendMessage$7(MessengerActionCallback.this, this.f$1, (Throwable) obj);
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
