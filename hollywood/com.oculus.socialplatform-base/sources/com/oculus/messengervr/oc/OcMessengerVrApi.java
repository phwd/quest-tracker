package com.oculus.messengervr.oc;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC12761yH;
import X.AbstractC12881yV;
import X.AbstractC12951yd;
import X.AbstractC12981yg;
import X.AbstractC13031yl;
import X.AbstractC13241zD;
import X.AbstractC13251zE;
import X.AbstractC136820a;
import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass1xU;
import X.AnonymousClass1z3;
import X.AnonymousClass1z9;
import X.AnonymousClass219;
import X.AnonymousClass21D;
import X.AnonymousClass21F;
import X.AnonymousClass21H;
import X.AnonymousClass292;
import X.C12731yE;
import X.C12931yb;
import X.C13051yn;
import X.C13261zF;
import X.C13391zW;
import X.C13401zX;
import X.C13621zt;
import X.C13641zv;
import X.C13661zx;
import X.C137220e;
import X.C138620s;
import X.C138820u;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Cleanup;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.manager.MessengerManagerJNI;
import com.oculus.messenger.models.Thread;
import com.oculus.messengervr.common.utils.MutationObservableUtil;
import com.oculus.messengervr.interfaces.MessengerContact;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.messengervr.oc.models.OcMessengerThread;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcMessengerVrApi implements MessengerVrApi {
    @Nullable
    public AbstractC13241zD mApiReadyCompletable;
    public final AnonymousClass1xU mCompositeDisposable;
    public Handler mHandler;
    public HandlerThread mHandlerThread;
    public final AbstractC13251zE<MessengerManagerJNI> mMessengerManagerSingle;
    public AbstractC136820a<OcMessengerManagerUpdateResult> mMessengerManagerUpdatesObservable;
    public final AbstractC13251zE<OcApiConfig> mOcApiConfigSingle;
    public final AbstractC13251zE<Optional<OcThreadParticipantsBlockStatusQueryHandler>> mOcThreadParticipantsBlockStatusQueryHandlerSingle;
    public final AbstractC13251zE<Optional<OcUserPictureUrlsQueryHandler>> mOcUserPictureUrlsQueryHandlerSingle;
    public final Map<String, String> mThreadParticipantsPictureUrlsCache;
    public final Map<Long, AbstractC136820a<MessengerMessage[]>> messageListObservablesCache;
    public final Map<Long, AbstractC136820a<Pair<MessengerThread, MessengerParticipant[]>>> threadObservablesCache;

    private synchronized AbstractC136820a<Pair<MessengerThread, MessengerParticipant[]>> getThreadAndParticipantsObservable(long j) {
        return (AbstractC136820a) Objects.requireNonNull(this.threadObservablesCache.computeIfAbsent(Long.valueOf(j), new Function(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$zpzFKh4ENcbyg7TISmWRCpsavH42 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$getThreadAndParticipantsObservable$24$OcMessengerVrApi(this.f$1, (Long) obj);
            }
        }), "getThreadAndParticipantsObservable should not return null.");
    }

    public static /* synthetic */ void lambda$markThreadAsRead$21(Object obj) throws Exception {
    }

    public static /* synthetic */ void lambda$markThreadAsRead$22(Throwable th) throws Exception {
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerContact[]> getContactListObservable(AbstractC136820a<Integer> r3) {
        return new C138820u(new MessengerContact[0]);
    }

    public /* synthetic */ void lambda$markThreadAsRead$18$OcMessengerVrApi(MessengerManagerJNI messengerManagerJNI, long j, MessengerMessage[] messengerMessageArr, final AnonymousClass292 r11) throws Exception {
        messengerManagerJNI.markThreadRead(j, messengerMessageArr[messengerMessageArr.length - 1].getTimestampMs(), new MessengerManagerJNI.MutationCallback() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass6 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.MutationCallback
            public void onResult(boolean z, @Nullable String str) {
                if (z) {
                    r11.onComplete();
                }
            }
        });
    }

    public /* synthetic */ void lambda$muteThread$14$OcMessengerVrApi(MessengerManagerJNI messengerManagerJNI, final long j, int i, final Date date, final AnonymousClass292 r12) throws Exception {
        messengerManagerJNI.muteThread(j, i, new MessengerManagerJNI.MutationCallback() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass4 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.MutationCallback
            public void onResult(boolean z, @Nullable String str) {
                String str2;
                if (str == null || str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = AnonymousClass006.A09(" (", str, ")");
                }
                if (!z) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Muting thread failed (threadKey = %d)%s", Long.valueOf(j), str2);
                } else {
                    r12.onComplete();
                }
            }
        });
    }

    public /* synthetic */ AbstractC13241zD lambda$muteThread$15$OcMessengerVrApi(long j, int i, Date date, MessengerManagerJNI messengerManagerJNI) {
        return new C12931yb(new AbstractC12951yd(messengerManagerJNI, j, i, date) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$n2cCVNxlsboFhUPfjeZyUB2zEA2 */
            public final /* synthetic */ MessengerManagerJNI f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ int f$3;
            public final /* synthetic */ Date f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
                this.f$4 = r6;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r8) {
                OcMessengerVrApi.this.lambda$muteThread$14$OcMessengerVrApi(this.f$1, this.f$2, this.f$3, this.f$4, r8);
            }
        });
    }

    public /* synthetic */ AbstractC13251zE lambda$removeParticipantFromGroup$13$OcMessengerVrApi(long j, long j2, MessengerManagerJNI messengerManagerJNI) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(messengerManagerJNI, j, j2) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$SNmbbvZGTvquXpztvWMG7REmLo2 */
            public final /* synthetic */ MessengerManagerJNI f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ long f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r8) {
                OcMessengerVrApi.this.lambda$removeParticipantFromGroup$12$OcMessengerVrApi(this.f$1, this.f$2, this.f$3, r8);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    @Cleanup
    public void logoutAndDeleteData() {
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD muteThread(long j, Date date) {
        int intExact;
        if (date == null) {
            intExact = -1;
        } else {
            long time = date.getTime() - new Date().getTime();
            if (time <= 0) {
                return AnonymousClass1z3.A00;
            }
            intExact = Math.toIntExact(time / 1000);
        }
        return MutationObservableUtil.wrapMutationSingleToCompletable(this.mMessengerManagerSingle, this.mCompositeDisposable, new Function(j, Integer.valueOf(intExact).intValue(), date) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$cGeKWlTu0u7UC0OMEzFn7jOasI2 */
            public final /* synthetic */ long f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ Date f$3;

            {
                this.f$1 = r2;
                this.f$2 = r4;
                this.f$3 = r5;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$muteThread$15$OcMessengerVrApi(this.f$1, this.f$2, this.f$3, (MessengerManagerJNI) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<String> sendStickerMessage(long j, long j2) {
        AnonymousClass219.A01(null, "value is null");
        return new C12731yE(null);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public void startNetworking() {
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public void stopNetworking() {
    }

    public static AbstractC136820a<OcMessengerManagerUpdateResult> createMessengerManagerUpdatesObservable(AbstractC13251zE<MessengerManagerJNI> r3) {
        $$Lambda$OcMessengerVrApi$cb2kemoaa2cNxP3PoY3d1PSSAoA2 r2 = $$Lambda$OcMessengerVrApi$cb2kemoaa2cNxP3PoY3d1PSSAoA2.INSTANCE;
        AnonymousClass219.A01(r2, "mapper is null");
        AbstractC136820a A0A = new C13391zW(r3, r2).A0A($$Lambda$OcMessengerVrApi$GZ4C4oC3Cv7izQsNQhHJ5blf3sY2.INSTANCE);
        AtomicReference atomicReference = new AtomicReference();
        return new AnonymousClass21H(new AnonymousClass21D(new AnonymousClass21F(atomicReference), A0A, atomicReference));
    }

    public static /* synthetic */ MessengerParticipant[] lambda$getParticipantListObservable$7(Pair pair) throws Exception {
        return (MessengerParticipant[]) pair.second;
    }

    public static /* synthetic */ MessengerThread lambda$getThreadObservable$5(Pair pair) throws Exception {
        return (MessengerThread) pair.first;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<MessengerThread> createGroupThreadWithInitialMessage(List<Long> list, String str) {
        return MutationObservableUtil.wrapMutationSingle(AbstractC13251zE.A01(this.mMessengerManagerSingle, this.mOcApiConfigSingle, $$Lambda$pbM32ds33mDZtgeo5w6GTboCBI2.INSTANCE), this.mCompositeDisposable, new Function(list, str) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$RYtRRcvMjER8e541VV5ZvCTFE2 */
            public final /* synthetic */ List f$1;
            public final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$createGroupThreadWithInitialMessage$11$OcMessengerVrApi(this.f$1, this.f$2, (Pair) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD getApiReadyCompletable() {
        return this.mApiReadyCompletable;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerMessage[]> getMessageListObservable(long j, AbstractC136820a<Integer> r6) {
        return this.messageListObservablesCache.computeIfAbsent(Long.valueOf(j), new Function(r6, j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$FnsnfULlKlyp1YpxlZScLTWRKI2 */
            public final /* synthetic */ AbstractC136820a f$1;
            public final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$getMessageListObservable$6$OcMessengerVrApi(this.f$1, this.f$2, (Long) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerThread[]> getThreadListObservable(AbstractC136820a<Integer> r7) {
        return ThreadListObservableUtil.createThreadListObservable(this.mOcApiConfigSingle, this.mOcUserPictureUrlsQueryHandlerSingle, this.mMessengerManagerSingle, r7, this.mMessengerManagerUpdatesObservable, this.mThreadParticipantsPictureUrlsCache);
    }

    public /* synthetic */ AbstractC13251zE lambda$createGroupThreadWithInitialMessage$11$OcMessengerVrApi(List list, String str, Pair pair) {
        return AbstractC13251zE.A00(new AbstractC06371Zh((MessengerManagerJNI) pair.first, list, str, (OcApiConfig) pair.second) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$fTEZPITO4UbgEJP_9p8lzCdq1v02 */
            public final /* synthetic */ MessengerManagerJNI f$1;
            public final /* synthetic */ List f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ OcApiConfig f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r7) {
                OcMessengerVrApi.this.lambda$createGroupThreadWithInitialMessage$10$OcMessengerVrApi(this.f$1, this.f$2, this.f$3, this.f$4, r7);
            }
        });
    }

    public /* synthetic */ AbstractC136820a lambda$getMessageListObservable$6$OcMessengerVrApi(AbstractC136820a r9, long j, Long l) {
        return MessageListObservableUtil.createMessageListObservable(this.mOcApiConfigSingle, this.mOcUserPictureUrlsQueryHandlerSingle, this.mMessengerManagerSingle, r9, this.mMessengerManagerUpdatesObservable, j, this.mThreadParticipantsPictureUrlsCache);
    }

    public /* synthetic */ AbstractC136820a lambda$getThreadAndParticipantsObservable$24$OcMessengerVrApi(long j, Long l) {
        return ThreadObservableUtil.createThreadAndParticipantsObservable(this.mOcApiConfigSingle, this.mOcUserPictureUrlsQueryHandlerSingle, this.mOcThreadParticipantsBlockStatusQueryHandlerSingle, this.mMessengerManagerSingle, this.mMessengerManagerUpdatesObservable, this.mThreadParticipantsPictureUrlsCache, j);
    }

    public /* synthetic */ AbstractC12981yg lambda$markThreadAsRead$19$OcMessengerVrApi(long j, Pair pair) throws Exception {
        return new C12931yb(new AbstractC12951yd((MessengerManagerJNI) pair.first, j, (MessengerMessage[]) pair.second) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$ZpiFlRchWBEnfq__OoZC_wIFXw2 */
            public final /* synthetic */ MessengerManagerJNI f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ MessengerMessage[] f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r7) {
                OcMessengerVrApi.this.lambda$markThreadAsRead$18$OcMessengerVrApi(this.f$1, this.f$2, this.f$3, r7);
            }
        });
    }

    public /* synthetic */ AbstractC12761yH lambda$new$4$OcMessengerVrApi(OcApiConfig ocApiConfig) throws Exception {
        return AbstractC13251zE.A00(new AbstractC06371Zh(ocApiConfig) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$gl8KnJPKGwLyxSxWZDXPc_mzw2 */
            public final /* synthetic */ OcApiConfig f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r3) {
                OcMessengerVrApi.this.lambda$new$3$OcMessengerVrApi(this.f$1, r3);
            }
        });
    }

    public /* synthetic */ void lambda$removeParticipantFromGroup$12$OcMessengerVrApi(MessengerManagerJNI messengerManagerJNI, final long j, final long j2, final AbstractC10551og r18) throws Exception {
        messengerManagerJNI.removeParticipantFromThread(j, j2, new MessengerManagerJNI.MutationCallback() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass3 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.MutationCallback
            public void onResult(boolean z, @Nullable String str) {
                String str2;
                if (str == null || str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = AnonymousClass006.A09(" (", str, ")");
                }
                Long valueOf = Long.valueOf(j);
                Long valueOf2 = Long.valueOf(j2);
                Boolean valueOf3 = Boolean.valueOf(z);
                AnonymousClass0MD.A09(Constants.LOG_TAG, "Removing participant from thread finished: threadKey = %d, participantId = %d, is successful = %b%s", valueOf, valueOf2, valueOf3, str2);
                r18.onSuccess(valueOf3);
            }
        });
    }

    public /* synthetic */ void lambda$unmuteThread$16$OcMessengerVrApi(MessengerManagerJNI messengerManagerJNI, final long j, final AnonymousClass292 r6) throws Exception {
        messengerManagerJNI.muteThread(j, 0, new MessengerManagerJNI.MutationCallback() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass5 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.MutationCallback
            public void onResult(boolean z, @Nullable String str) {
                String str2;
                if (str == null || str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = AnonymousClass006.A09(" (", str, ")");
                }
                if (!z) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Unmuting thread failed (threadKey = %d)%s", Long.valueOf(j), str2);
                } else {
                    r6.onComplete();
                }
            }
        });
    }

    public /* synthetic */ AbstractC13241zD lambda$unmuteThread$17$OcMessengerVrApi(long j, MessengerManagerJNI messengerManagerJNI) {
        return new C12931yb(new AbstractC12951yd(messengerManagerJNI, j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$GTGajvBP4QTG3pwptLyyYQFIY102 */
            public final /* synthetic */ MessengerManagerJNI f$1;
            public final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r5) {
                OcMessengerVrApi.this.lambda$unmuteThread$16$OcMessengerVrApi(this.f$1, this.f$2, r5);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    @Cleanup
    public void logout() {
        this.mCompositeDisposable.A01();
        this.threadObservablesCache.clear();
        this.messageListObservablesCache.clear();
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD markThreadAsRead(long j) {
        AbstractC136820a<MessengerMessage[]> r0 = this.messageListObservablesCache.get(Long.valueOf(j));
        if (r0 == null) {
            return AnonymousClass1z3.A00;
        }
        AnonymousClass21H r5 = new AnonymousClass21H(new AnonymousClass1z9(AbstractC13251zE.A01(this.mMessengerManagerSingle, new C13051yn(r0), $$Lambda$MbFgUO_J1Sw8W7qKphpSdDFvIuE2.INSTANCE), new AbstractC13031yl(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$Z3seESRrAOtVj8PLOIE1l_4K5Hk2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$markThreadAsRead$19$OcMessengerVrApi(this.f$1, (Pair) obj);
            }
        }).A00(new AbstractC12881yV(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$DoJr7VkcUo7FjgPFOwQSwbPfeU2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
            }
        }).A01().A0I());
        this.mCompositeDisposable.A1D(AbstractC136820a.A06(r5, $$Lambda$OcMessengerVrApi$Kd_rkeaj0Fv0hPqAdaIteDYmfMg2.INSTANCE, $$Lambda$OcMessengerVrApi$VnEAi0K0ioPSNf5Qyd2HVAezo2.INSTANCE, new AbstractC12881yV(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$7NnceALUcUJs29MU14NJjv3XzV02 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
            }
        }, C137220e.A04));
        return new C13661zx(r5);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<Boolean> removeParticipantFromGroup(long j, long j2) {
        return MutationObservableUtil.wrapMutationSingle(this.mMessengerManagerSingle, this.mCompositeDisposable, new Function(j, j2) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$yTTQDIY5rALjwsctJ6P1AenRin02 */
            public final /* synthetic */ long f$1;
            public final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$removeParticipantFromGroup$13$OcMessengerVrApi(this.f$1, this.f$2, (MessengerManagerJNI) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<String> sendMessage(long j, String str) {
        return MutationObservableUtil.wrapMutationSingle(this.mMessengerManagerSingle, this.mCompositeDisposable, new Function(j, str) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$irZjkeTCcgu85KEXlgwCLYv8Ro2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC13251zE.A00(new AbstractC06371Zh(this.f$0, this.f$1) {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$UwYmj9MrDsaFsL13DW01JoEqITQ2 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r5) {
                        OcMessengerVrApi.lambda$sendMessage$8(MessengerManagerJNI.this, this.f$1, this.f$2, r5);
                    }
                });
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD unmuteThread(long j) {
        return MutationObservableUtil.wrapMutationSingleToCompletable(this.mMessengerManagerSingle, this.mCompositeDisposable, new Function(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$8CgUIGzHyOH9NcXOaoNI4uOG4k2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$unmuteThread$17$OcMessengerVrApi(this.f$1, (MessengerManagerJNI) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$sendMessage$8(MessengerManagerJNI messengerManagerJNI, long j, String str, AbstractC10551og r4) throws Exception {
        if (messengerManagerJNI.sendMessage(j, str)) {
            r4.onSuccess(Long.toString(j));
        } else {
            r4.onError(new Throwable(AnonymousClass006.A06("Failed to send message on threadKey = ", j)));
        }
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerParticipant[]> getParticipantListObservable(long j) {
        AbstractC136820a<Pair<MessengerThread, MessengerParticipant[]>> threadAndParticipantsObservable = getThreadAndParticipantsObservable(j);
        $$Lambda$OcMessengerVrApi$UhNnUKAD0bvL0UHFNwN4g2feA2 r1 = $$Lambda$OcMessengerVrApi$UhNnUKAD0bvL0UHFNwN4g2feA2.INSTANCE;
        AnonymousClass219.A01(r1, "mapper is null");
        return new C138620s(threadAndParticipantsObservable, r1);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerThread> getThreadObservable(long j) {
        AbstractC136820a<Pair<MessengerThread, MessengerParticipant[]>> threadAndParticipantsObservable = getThreadAndParticipantsObservable(j);
        $$Lambda$OcMessengerVrApi$RK8Mzz_3cF8jy0_Z9JmcKjtJkU2 r1 = $$Lambda$OcMessengerVrApi$RK8Mzz_3cF8jy0_Z9JmcKjtJkU2.INSTANCE;
        AnonymousClass219.A01(r1, "mapper is null");
        return new C138620s(threadAndParticipantsObservable, r1);
    }

    public /* synthetic */ void lambda$createGroupThreadWithInitialMessage$10$OcMessengerVrApi(MessengerManagerJNI messengerManagerJNI, final List list, String str, final OcApiConfig ocApiConfig, final AbstractC10551og r7) throws Exception {
        messengerManagerJNI.createGroupThread(list.stream().mapToLong($$Lambda$ELHKvd8JMVRD8rbALqYPKbDX2mM2.INSTANCE).toArray(), str, new MessengerManagerJNI.GetThreadCallback() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass2 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadCallback
            public void onResult(@Nullable Thread thread, @Nullable String str) {
                String str2;
                if (str == null || str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = AnonymousClass006.A07(": ", str);
                }
                if (thread == null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "createGroupThread failed (participantContactIds = %s)%s", list, str2);
                    return;
                }
                OcMessengerThread.Builder builder = new OcMessengerThread.Builder();
                builder.mOcThread = thread;
                builder.mViewerId = ocApiConfig.mUserId;
                r7.onSuccess(builder.build());
            }
        });
    }

    public /* synthetic */ void lambda$new$3$OcMessengerVrApi(OcApiConfig ocApiConfig, AbstractC10551og r11) throws Exception {
        ocApiConfig.getUserAgent();
        MessengerManagerJNI messengerManagerJNI = new MessengerManagerJNI(ocApiConfig.getUserAgent());
        messengerManagerJNI.init(Long.valueOf(ocApiConfig.mUserId).longValue(), ocApiConfig.mAccessToken, ocApiConfig.mAppId, ocApiConfig.getDeviceId(), ocApiConfig.getDbsPath(), new Executor() {
            /* class com.oculus.messengervr.oc.OcMessengerVrApi.AnonymousClass1 */

            public void execute(Runnable runnable) {
                OcMessengerVrApi.this.mHandler.post(runnable);
            }
        });
        r11.onSuccess(messengerManagerJNI);
    }

    public static /* synthetic */ void lambda$createMessengerManagerUpdatesObservable$27(OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
    }

    public static /* synthetic */ void lambda$markThreadAsRead$20(long j) throws Exception {
    }

    public static /* synthetic */ void lambda$markThreadAsRead$23(long j) throws Exception {
    }

    @SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    public OcMessengerVrApi(AbstractC13251zE<OcApiConfig> r7, AbstractC13251zE<OcUserPictureUrlsQueryHandler> r8, AbstractC13251zE<OcThreadParticipantsBlockStatusQueryHandler> r9, @Nullable Integer num, @Nullable String str) {
        this(r7, num, str, r8.A04($$Lambda$OcMessengerVrApi$mrxCVK4OVoLQfsJ3rerRPw1Wycc2.INSTANCE), r9.A04($$Lambda$OcMessengerVrApi$J66h22_bdX0Kkr4Vbuz2bHNDeXE2.INSTANCE));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OcMessengerVrApi(X.AbstractC13251zE<com.oculus.messengervr.oc.OcApiConfig> r7, X.AbstractC13251zE<com.oculus.messengervr.oc.OcUserPictureUrlsQueryHandler> r8, @androidx.annotation.Nullable java.lang.Integer r9, @androidx.annotation.Nullable java.lang.String r10) {
        /*
            r6 = this;
            com.oculus.messengervr.oc.-$$Lambda$OcMessengerVrApi$x5b16HOc_8VPbgZ07bNqSOKBU4g2 r0 = com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$x5b16HOc_8VPbgZ07bNqSOKBU4g2.INSTANCE
            X.1zE r4 = r8.A04(r0)
            r0 = 0
            java.util.Optional r1 = java.util.Optional.ofNullable(r0)
            java.lang.String r0 = "value is null"
            X.AnonymousClass219.A01(r1, r0)
            X.1yE r5 = new X.1yE
            r5.<init>(r1)
            r1 = r7
            r0 = r6
            r3 = r10
            r2 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.messengervr.oc.OcMessengerVrApi.<init>(X.1zE, X.1zE, java.lang.Integer, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OcMessengerVrApi(X.AbstractC13251zE<com.oculus.messengervr.oc.OcApiConfig> r7, @androidx.annotation.Nullable java.lang.Integer r8, @androidx.annotation.Nullable java.lang.String r9) {
        /*
            r6 = this;
            r2 = 0
            java.util.Optional r0 = java.util.Optional.ofNullable(r2)
            java.lang.String r1 = "value is null"
            X.AnonymousClass219.A01(r0, r1)
            X.1yE r4 = new X.1yE
            r4.<init>(r0)
            java.util.Optional r0 = java.util.Optional.ofNullable(r2)
            X.AnonymousClass219.A01(r0, r1)
            X.1yE r5 = new X.1yE
            r5.<init>(r0)
            r1 = r7
            r0 = r6
            r3 = r9
            r2 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.messengervr.oc.OcMessengerVrApi.<init>(X.1zE, java.lang.Integer, java.lang.String):void");
    }

    @SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    public OcMessengerVrApi(AbstractC13251zE<OcApiConfig> r4, @Nullable Integer num, @Nullable String str, AbstractC13251zE<Optional<OcUserPictureUrlsQueryHandler>> r7, AbstractC13251zE<Optional<OcThreadParticipantsBlockStatusQueryHandler>> r8) {
        this.mCompositeDisposable = new AnonymousClass1xU();
        this.threadObservablesCache = Collections.synchronizedMap(new HashMap());
        this.messageListObservablesCache = Collections.synchronizedMap(new HashMap());
        this.mThreadParticipantsPictureUrlsCache = Collections.synchronizedMap(new HashMap());
        if (num != null) {
            AnonymousClass0MD.A00(num.intValue());
        }
        this.mOcApiConfigSingle = new C13261zF(r4);
        this.mOcUserPictureUrlsQueryHandlerSingle = new C13261zF(r7);
        this.mOcThreadParticipantsBlockStatusQueryHandlerSingle = new C13261zF(r8);
        HandlerThread handlerThread = new HandlerThread("ChatsWorkerThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        C13261zF r0 = new C13261zF(new C13401zX(this.mOcApiConfigSingle, new AbstractC13031yl() {
            /* class com.oculus.messengervr.oc.$$Lambda$OcMessengerVrApi$i_lhYCE8ChTRGFkTbdaNucWxQfM2 */

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return OcMessengerVrApi.this.lambda$new$4$OcMessengerVrApi((OcApiConfig) obj);
            }
        }));
        this.mMessengerManagerSingle = r0;
        this.mApiReadyCompletable = new C13621zt(new C13641zv(r0));
        this.mMessengerManagerUpdatesObservable = createMessengerManagerUpdatesObservable(this.mMessengerManagerSingle);
    }
}
