package com.oculus.messengervr.oc;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC13031yl;
import X.AbstractC13191z2;
import X.AbstractC13251zE;
import X.AbstractC13521zj;
import X.AbstractC136820a;
import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass1vb;
import X.AnonymousClass1vc;
import X.AnonymousClass215;
import X.AnonymousClass219;
import X.AnonymousClass21H;
import X.C12731yE;
import X.C138420q;
import X.C138920v;
import X.EnumC139220y;
import android.annotation.TargetApi;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.manager.MessengerManagerJNI;
import com.oculus.messenger.models.Participant;
import com.oculus.messenger.models.Thread;
import com.oculus.messengervr.common.utils.Quadruplet;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.oc.OcMessengerManagerUpdateResult;
import com.oculus.messengervr.oc.ThreadObservableUtil;
import com.oculus.messengervr.oc.models.OcMessengerParticipant;
import com.oculus.messengervr.oc.models.OcMessengerThread;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ThreadObservableUtil {
    public static AbstractC13251zE<Pair<OcMessengerThread, MessengerParticipant[]>> createPatchedThreadAndParticipantsSingle(@Nullable Thread thread, long j, String str, Map<String, String> map, Optional<OcThreadParticipantsBlockStatusQueryHandler> optional) {
        OcMessengerParticipant[] messengerParticipants = getMessengerParticipants(thread);
        OcMessengerThread.Builder builder = new OcMessengerThread.Builder();
        builder.mOcThread = thread;
        builder.mViewerId = str;
        if (thread != null) {
            builder.mPatchedThreadPictureUrls = patchThreadWithCachedUserPictureUrls(messengerParticipants, str, map);
        } else {
            builder.mPatchedThreadKey = j;
        }
        OcMessengerThread build = builder.build();
        if (!optional.isPresent() || thread == null) {
            return new C12731yE(new Pair(build, messengerParticipants));
        }
        return OcGraphQLQueryObservableUtil.createQueryParticipantsBlockStatusSingle(optional.get(), Long.toString(build.getThreadKey())).A04(new AbstractC13031yl(messengerParticipants, thread, str, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$pp_ZuuQLqpZc4X1GYHlkUb_M3wQ2 */
            public final /* synthetic */ OcMessengerParticipant[] f$1;
            public final /* synthetic */ Thread f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ Map f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return ThreadObservableUtil.lambda$createPatchedThreadAndParticipantsSingle$8(OcMessengerThread.this, this.f$1, this.f$2, this.f$3, this.f$4, (Set) obj);
            }
        });
    }

    public static MessengerManagerJNI.GetThreadCallback createThreadAndParticipantsObserverCallback(final OcApiConfig ocApiConfig, final Optional<OcUserPictureUrlsQueryHandler> optional, final Optional<OcThreadParticipantsBlockStatusQueryHandler> optional2, final Map<String, String> map, final long j, final Consumer<Pair<MessengerThread, MessengerParticipant[]>> consumer, final Consumer<Throwable> consumer2, final Consumer<AbstractC12271xB> consumer3) {
        return new MessengerManagerJNI.GetThreadCallback() {
            /* class com.oculus.messengervr.oc.ThreadObservableUtil.AnonymousClass1 */

            public static /* synthetic */ void lambda$onResult$2(Consumer consumer, Optional optional, OcApiConfig ocApiConfig, Map map, Pair pair) throws Exception {
                consumer.accept(new Pair(pair.first, pair.second));
                if (optional.isPresent()) {
                    ThreadObservableUtil.createPatchedThreadPictureUrlsObservable((OcUserPictureUrlsQueryHandler) optional.get(), ocApiConfig.mUserId, map, (OcMessengerThread) pair.first).A05(new AbstractC12851yS(consumer, pair) {
                        /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$1$GHOGRVgSilenLeTUoQwBAeV4aSo2 */
                        public final /* synthetic */ Consumer f$0;
                        public final /* synthetic */ Pair f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        @Override // X.AbstractC12851yS
                        public final void accept(Object obj) {
                            ThreadObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, this.f$1, (MessengerThread) obj);
                        }
                    }, $$Lambda$ThreadObservableUtil$1$Nc7LCMgpNeur5L80AagK1OKgSjU2.INSTANCE);
                }
            }

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadCallback
            public void onResult(@Nullable Thread thread, @Nullable String str) {
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get thread: %s", str);
                    Consumer consumer = r2;
                    if (AnonymousClass006.A07("Failed to get thread: ", str) == null) {
                        str = "";
                    }
                    consumer.accept(new Throwable(str));
                    return;
                }
                r10.accept(ThreadObservableUtil.createPatchedThreadAndParticipantsSingle(thread, r12, r14.mUserId, r17, r16).A05(new AbstractC12851yS(r8, r15, r14, r17) {
                    /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$1$FI2GygV9ReZoyD4bYDLka4Tqy02 */
                    public final /* synthetic */ Consumer f$0;
                    public final /* synthetic */ Optional f$1;
                    public final /* synthetic */ OcApiConfig f$2;
                    public final /* synthetic */ Map f$3;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // X.AbstractC12851yS
                    public final void accept(Object obj) {
                        ThreadObservableUtil.AnonymousClass1.lambda$onResult$2(this.f$0, this.f$1, this.f$2, this.f$3, (Pair) obj);
                    }
                }, $$Lambda$ThreadObservableUtil$1$2qHnegcotmV5BpMZoBPpJxxkgPI2.INSTANCE));
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, Pair pair, MessengerThread messengerThread) throws Exception {
                messengerThread.getThreadKey();
                consumer.accept(new Pair(messengerThread, pair.second));
            }
        };
    }

    public static OcMessengerParticipant[] getMessengerParticipants(@Nullable Thread thread) {
        if (thread == null) {
            return new OcMessengerParticipant[0];
        }
        Participant[] participantArr = thread.mParticipants;
        OcMessengerParticipant[] ocMessengerParticipantArr = new OcMessengerParticipant[participantArr.length];
        for (int i = 0; i < ocMessengerParticipantArr.length; i++) {
            OcMessengerParticipant.Builder builder = new OcMessengerParticipant.Builder();
            builder.mOcParticipant = participantArr[i];
            ocMessengerParticipantArr[i] = builder.build();
        }
        return ocMessengerParticipantArr;
    }

    public static /* synthetic */ Pair lambda$createPatchedThreadAndParticipantsSingle$8(OcMessengerThread ocMessengerThread, OcMessengerParticipant[] ocMessengerParticipantArr, Thread thread, String str, Map map, Set set) throws Exception {
        for (int i = 0; i < ocMessengerParticipantArr.length; i++) {
            OcMessengerParticipant ocMessengerParticipant = ocMessengerParticipantArr[i];
            OcMessengerParticipant.Builder newBuilder = OcMessengerParticipant.newBuilder(ocMessengerParticipant);
            newBuilder.mPatchedIsUserBlocked = Boolean.valueOf(set.contains(Long.toString(ocMessengerParticipant.getParticipantId())));
            ocMessengerParticipantArr[i] = newBuilder.build();
        }
        OcMessengerThread.Builder builder = new OcMessengerThread.Builder();
        builder.mOcThread = thread;
        builder.mViewerId = str;
        builder.mPatchedThreadPictureUrls = patchThreadWithCachedUserPictureUrls(ocMessengerParticipantArr, str, map);
        return new Pair(builder.build(), ocMessengerParticipantArr);
    }

    public static AbstractC13251zE<MessengerThread> createPatchedThreadPictureUrlsObservable(OcUserPictureUrlsQueryHandler ocUserPictureUrlsQueryHandler, String str, Map<String, String> map, OcMessengerThread ocMessengerThread) {
        return OcGraphQLQueryObservableUtil.createQueryNamesAndProfilePicsSingle(ocUserPictureUrlsQueryHandler, (Set) ocMessengerThread.mParticipantIds.stream().filter(new Predicate(str) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$S1Y6HnGaaJKqH5RQkxKjoHFk_fI2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return !((String) obj).equals(this.f$0);
            }
        }).limit(2).collect(Collectors.toSet()), map, ocMessengerThread.getThreadKey()).A04(new AbstractC13031yl(str) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$2s8JU3dGFsAIqdWK2Qq3D7dM1A82 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return ThreadObservableUtil.lambda$createPatchedThreadPictureUrlsObservable$10(OcMessengerThread.this, this.f$1, (Map) obj);
            }
        });
    }

    public static AbstractC136820a<OcMessengerManagerUpdateResult> createThreadUpdatesObservable(AbstractC136820a<OcMessengerManagerUpdateResult> r1, long j) {
        C138920v r2 = new C138920v(r1, new AbstractC13191z2(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$jI3Vazo_q41Lz70b5Nt0wUjYvI2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13191z2
            public final boolean test(Object obj) {
                return ThreadObservableUtil.lambda$createThreadUpdatesObservable$7(this.f$0, (OcMessengerManagerUpdateResult) obj);
            }
        });
        OcMessengerManagerUpdateResult.Builder builder = new OcMessengerManagerUpdateResult.Builder();
        builder.mUpdateResultType = OcMessengerManagerUpdateResult.UpdateResultType.NONE;
        return r2.A0F(builder.build());
    }

    public static /* synthetic */ void lambda$createThreadAndParticipantsObservable$0(long j, OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
    }

    public static /* synthetic */ Quadruplet lambda$createThreadAndParticipantsObservable$1(OcApiConfig ocApiConfig, Optional optional, Optional optional2, MessengerManagerJNI messengerManagerJNI, OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
        return new Quadruplet(ocApiConfig, optional, optional2, messengerManagerJNI);
    }

    public static /* synthetic */ void lambda$createThreadAndParticipantsObservable$2(MessengerManagerJNI messengerManagerJNI, final long j, final OcApiConfig ocApiConfig, final Optional optional, final Optional optional2, final Map map, AnonymousClass1vb r18) throws Exception {
        final $$Lambda$RScOxI8FDJ37he0uI1meYp1uaQ2 r8 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$RScOxI8FDJ37he0uI1meYp1uaQ2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.onNext(obj);
            }
        };
        final $$Lambda$E8Bq8zVrC7A_wWRHoZ2h7hCpTbk2 r2 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$E8Bq8zVrC7A_wWRHoZ2h7hCpTbk2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.onError((Throwable) obj);
            }
        };
        final $$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 r10 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.A9q((AbstractC12271xB) obj);
            }
        };
        messengerManagerJNI.getThread(j, 0, new MessengerManagerJNI.GetThreadCallback() {
            /* class com.oculus.messengervr.oc.ThreadObservableUtil.AnonymousClass1 */

            public static /* synthetic */ void lambda$onResult$2(Consumer consumer, Optional optional, OcApiConfig ocApiConfig, Map map, Pair pair) throws Exception {
                consumer.accept(new Pair(pair.first, pair.second));
                if (optional.isPresent()) {
                    ThreadObservableUtil.createPatchedThreadPictureUrlsObservable((OcUserPictureUrlsQueryHandler) optional.get(), ocApiConfig.mUserId, map, (OcMessengerThread) pair.first).A05(new AbstractC12851yS(consumer, pair) {
                        /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$1$GHOGRVgSilenLeTUoQwBAeV4aSo2 */
                        public final /* synthetic */ Consumer f$0;
                        public final /* synthetic */ Pair f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        @Override // X.AbstractC12851yS
                        public final void accept(Object obj) {
                            ThreadObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, this.f$1, (MessengerThread) obj);
                        }
                    }, $$Lambda$ThreadObservableUtil$1$Nc7LCMgpNeur5L80AagK1OKgSjU2.INSTANCE);
                }
            }

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadCallback
            public void onResult(@Nullable Thread thread, @Nullable String str) {
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get thread: %s", str);
                    Consumer consumer = r2;
                    if (AnonymousClass006.A07("Failed to get thread: ", str) == null) {
                        str = "";
                    }
                    consumer.accept(new Throwable(str));
                    return;
                }
                r10.accept(ThreadObservableUtil.createPatchedThreadAndParticipantsSingle(thread, j, ocApiConfig.mUserId, map, optional2).A05(new AbstractC12851yS(r8, optional, ocApiConfig, map) {
                    /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$1$FI2GygV9ReZoyD4bYDLka4Tqy02 */
                    public final /* synthetic */ Consumer f$0;
                    public final /* synthetic */ Optional f$1;
                    public final /* synthetic */ OcApiConfig f$2;
                    public final /* synthetic */ Map f$3;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // X.AbstractC12851yS
                    public final void accept(Object obj) {
                        ThreadObservableUtil.AnonymousClass1.lambda$onResult$2(this.f$0, this.f$1, this.f$2, this.f$3, (Pair) obj);
                    }
                }, $$Lambda$ThreadObservableUtil$1$2qHnegcotmV5BpMZoBPpJxxkgPI2.INSTANCE));
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, Pair pair, MessengerThread messengerThread) throws Exception {
                messengerThread.getThreadKey();
                consumer.accept(new Pair(messengerThread, pair.second));
            }
        });
    }

    public static /* synthetic */ void lambda$createThreadAndParticipantsObservable$4(long j, AbstractC12271xB r2) throws Exception {
    }

    public static /* synthetic */ boolean lambda$createThreadUpdatesObservable$7(long j, OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
        if (ocMessengerManagerUpdateResult.mThreadKey != j || !Constants.THREAD_UPDATE_RESULT_TYPES.contains(ocMessengerManagerUpdateResult.mUpdateResultType)) {
            return false;
        }
        return true;
    }

    public static List<String> patchThreadWithCachedUserPictureUrls(MessengerParticipant[] messengerParticipantArr, String str, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (MessengerParticipant messengerParticipant : messengerParticipantArr) {
            String l = Long.toString(messengerParticipant.getParticipantId());
            if (!l.equals(str)) {
                map.get(l);
                arrayList.add(map.get(l));
                if (arrayList.size() >= 2) {
                    break;
                }
            }
        }
        return arrayList;
    }

    public static AbstractC136820a<Pair<MessengerThread, MessengerParticipant[]>> createThreadAndParticipantsObservable(AbstractC13251zE<OcApiConfig> r7, AbstractC13251zE<Optional<OcUserPictureUrlsQueryHandler>> r8, AbstractC13251zE<Optional<OcThreadParticipantsBlockStatusQueryHandler>> r9, AbstractC13251zE<MessengerManagerJNI> r10, AbstractC136820a<OcMessengerManagerUpdateResult> r11, Map<String, String> map, long j) {
        AbstractC136820a<OcMessengerManagerUpdateResult> createThreadUpdatesObservable = createThreadUpdatesObservable(r11, j);
        AbstractC136820a<OcApiConfig> A02 = r7.A02();
        AbstractC136820a<Optional<OcUserPictureUrlsQueryHandler>> A022 = r8.A02();
        AbstractC136820a<Optional<OcThreadParticipantsBlockStatusQueryHandler>> A023 = r9.A02();
        AbstractC136820a<MessengerManagerJNI> A024 = r10.A02();
        AbstractC136820a<OcMessengerManagerUpdateResult> A0A = createThreadUpdatesObservable.A0A(new AbstractC12851yS(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$BLby71kXoHf7Sp5OzYfCxvG0SyI2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
            }
        });
        $$Lambda$ThreadObservableUtil$ulY1wuYKtT6EGpIyZjptDr6PbM2 r1 = $$Lambda$ThreadObservableUtil$ulY1wuYKtT6EGpIyZjptDr6PbM2.INSTANCE;
        AnonymousClass219.A01(A02, "source1 is null");
        AnonymousClass219.A01(A022, "source2 is null");
        AnonymousClass219.A01(A023, "source3 is null");
        AnonymousClass219.A01(A024, "source4 is null");
        AnonymousClass219.A01(A0A, "source5 is null");
        AnonymousClass219.A01(r1, "f is null");
        return new AnonymousClass21H(AbstractC136820a.A04(new C138420q(r1), AbstractC13521zj.A00, A02, A022, A023, A024, A0A).A0E(new AbstractC13031yl(j, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$aXrcGeDmvPXUgcL044UZjnB6UN82 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Map f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Quadruplet quadruplet;
                return AbstractC136820a.A01(new AnonymousClass1vc(this.f$0, quadruplet.first, quadruplet.second, ((Quadruplet) obj).third, this.f$1) {
                    /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$hIGjA_zEraMHEzOrL8m6Iw5Sn82 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ OcApiConfig f$2;
                    public final /* synthetic */ Optional f$3;
                    public final /* synthetic */ Optional f$4;
                    public final /* synthetic */ Map f$5;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                        this.f$3 = r5;
                        this.f$4 = r6;
                        this.f$5 = r7;
                    }

                    @Override // X.AnonymousClass1vc
                    public final void subscribe(AnonymousClass1vb r9) {
                        ThreadObservableUtil.lambda$createThreadAndParticipantsObservable$2(MessengerManagerJNI.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9);
                    }
                });
            }
        }).A0B(new AbstractC12851yS(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$jiNoRjKbMUld8fbkTk_jxN9v8Sk2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
            }
        }, new AbstractC12881yV(j) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadObservableUtil$_nd7EwhILyvklNSqyBCL6A73Lds2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
            }
        }).A09($$Lambda$ThreadObservableUtil$431rHZRBOiLUb_Ve7G6QGT5_2C02.INSTANCE).A0I());
    }

    public static /* synthetic */ MessengerThread lambda$createPatchedThreadPictureUrlsObservable$10(OcMessengerThread ocMessengerThread, String str, Map map) throws Exception {
        if (map.isEmpty() || ocMessengerThread.getThreadPictureUrl() != null) {
            return ocMessengerThread;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : ocMessengerThread.mParticipantIds) {
            if (!str2.equals(str) && map.containsKey(str2) && arrayList.size() < 2) {
                Pair pair = (Pair) map.get(str2);
                arrayList.add(pair.second);
                arrayList2.add(pair.first);
            }
        }
        if (arrayList.isEmpty() || arrayList.parallelStream().noneMatch($$Lambda$ysW_fvT8H1f4ly8te8Kd64ujJxw2.INSTANCE)) {
            return ocMessengerThread;
        }
        OcMessengerThread.Builder newBuilder = OcMessengerThread.newBuilder(ocMessengerThread);
        newBuilder.mPatchedThreadPictureUrls = arrayList;
        newBuilder.mPatchedThreadName = TextUtils.join(",", arrayList2);
        newBuilder.mViewerId = str;
        return newBuilder.build();
    }

    public static /* synthetic */ boolean lambda$createPatchedThreadPictureUrlsObservable$9(String str, String str2) {
        return !str2.equals(str);
    }

    public static /* synthetic */ void lambda$createThreadAndParticipantsObservable$6(AnonymousClass215 r3) throws Exception {
        if (!r3.A01() && EnumC139220y.isError(r3.A00)) {
            AnonymousClass0MD.A07(Constants.LOG_TAG, "ThreadAndParticipantsObservable throw:", r3.A00());
        }
    }

    public static /* synthetic */ void lambda$createThreadAndParticipantsObservable$5(long j) throws Exception {
    }
}
