package com.oculus.messengervr.oc;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC12271xB;
import X.AbstractC12761yH;
import X.AbstractC12851yS;
import X.AbstractC13031yl;
import X.AbstractC13121yu;
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
import X.C13401zX;
import X.C137220e;
import X.C138320p;
import X.C138420q;
import X.C138920v;
import X.EnumC139220y;
import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.manager.MessengerManagerJNI;
import com.oculus.messenger.models.Participant;
import com.oculus.messenger.models.Thread;
import com.oculus.messengervr.common.utils.Quadruplet;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.oc.OcMessengerManagerUpdateResult;
import com.oculus.messengervr.oc.ThreadListObservableUtil;
import com.oculus.messengervr.oc.models.OcMessengerThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ThreadListObservableUtil {
    public static AbstractC13121yu<MessengerThread[]> createThreadListObservableSource(OcApiConfig ocApiConfig, Optional<OcUserPictureUrlsQueryHandler> optional, MessengerManagerJNI messengerManagerJNI, int i, Map<String, String> map) {
        return AbstractC136820a.A01(new AnonymousClass1vc(i, ocApiConfig, optional, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$AVrv8gr3LZd2sbXucyEOFuBfe082 */
            public final /* synthetic */ int f$1;
            public final /* synthetic */ OcApiConfig f$2;
            public final /* synthetic */ Optional f$3;
            public final /* synthetic */ Map f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // X.AnonymousClass1vc
            public final void subscribe(AnonymousClass1vb r7) {
                ThreadListObservableUtil.lambda$createThreadListObservableSource$8(MessengerManagerJNI.this, this.f$1, this.f$2, this.f$3, this.f$4, r7);
            }
        });
    }

    public static MessengerManagerJNI.GetThreadsCallback createThreadListObserverCallback(final OcApiConfig ocApiConfig, final Optional<OcUserPictureUrlsQueryHandler> optional, final Map<String, String> map, final Consumer<MessengerThread[]> consumer, final Consumer<Throwable> consumer2, final Consumer<AbstractC12271xB> consumer3) {
        return new MessengerManagerJNI.GetThreadsCallback() {
            /* class com.oculus.messengervr.oc.ThreadListObservableUtil.AnonymousClass1 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadsCallback
            public void onResult(@Nullable Thread[] threadArr, int i, @Nullable String str) {
                String str2 = "";
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get threads: %s", str);
                    Consumer consumer = r1;
                    if (AnonymousClass006.A07("Failed to get messages: ", str) != null) {
                        str2 = str;
                    }
                    consumer.accept(new Throwable(str2));
                } else if (threadArr != null && threadArr.length > 0) {
                    OcMessengerThread[] patchThreadsWithCachedUserPictureUrls = ThreadListObservableUtil.patchThreadsWithCachedUserPictureUrls(threadArr, r9.mUserId, r11);
                    r4.accept(patchThreadsWithCachedUserPictureUrls);
                    if (r10.isPresent()) {
                        r6.accept(ThreadListObservableUtil.createPatchedThreadsPictureUrlsObservable((OcUserPictureUrlsQueryHandler) r10.get(), r9.mUserId, r11, patchThreadsWithCachedUserPictureUrls).A05(new AbstractC12851yS(r4) {
                            /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$1$HHX6TCxNJt7kJcUaxbBDpcoVls2 */
                            public final /* synthetic */ Consumer f$0;

                            {
                                this.f$0 = r1;
                            }

                            @Override // X.AbstractC12851yS
                            public final void accept(Object obj) {
                                ThreadListObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, (ArrayList) obj);
                            }
                        }, $$Lambda$ThreadListObservableUtil$1$WYQ27U26rC5fjzvkQ1uXfKLsGsA2.INSTANCE));
                    }
                }
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, ArrayList arrayList) throws Exception {
                arrayList.size();
                consumer.accept(arrayList.toArray(new MessengerThread[0]));
            }
        };
    }

    public static OcMessengerThread[] patchThreadsWithCachedUserPictureUrls(Thread[] threadArr, String str, Map<String, String> map) {
        int length = threadArr.length;
        OcMessengerThread[] ocMessengerThreadArr = new OcMessengerThread[length];
        int i = 0;
        if (map.isEmpty()) {
            while (i < length) {
                OcMessengerThread.Builder builder = new OcMessengerThread.Builder();
                builder.mOcThread = threadArr[i];
                builder.mViewerId = str;
                ocMessengerThreadArr[i] = builder.build();
                i++;
            }
        } else {
            while (i < length) {
                ArrayList arrayList = new ArrayList();
                for (Participant participant : threadArr[i].mParticipants) {
                    String l = Long.toString(participant.mParticipantId);
                    if (!l.equals(str)) {
                        arrayList.add(map.get(l));
                        if (arrayList.size() >= 2) {
                            break;
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    arrayList.parallelStream().noneMatch($$Lambda$ysW_fvT8H1f4ly8te8Kd64ujJxw2.INSTANCE);
                }
                OcMessengerThread.Builder builder2 = new OcMessengerThread.Builder();
                builder2.mOcThread = threadArr[i];
                builder2.mViewerId = str;
                builder2.mPatchedThreadPictureUrls = arrayList;
                ocMessengerThreadArr[i] = builder2.build();
                i++;
            }
        }
        return ocMessengerThreadArr;
    }

    public static AbstractC13251zE<ArrayList<MessengerThread>> createPatchedThreadsPictureUrlsObservable(OcUserPictureUrlsQueryHandler ocUserPictureUrlsQueryHandler, String str, Map<String, String> map, OcMessengerThread[] ocMessengerThreadArr) {
        HashSet hashSet = new HashSet();
        for (OcMessengerThread ocMessengerThread : ocMessengerThreadArr) {
            hashSet.addAll((Collection) ocMessengerThread.mParticipantIds.stream().filter(new Predicate(str) {
                /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$0gyQ5QBUnuFv1Ubmz_q228mrUhI2 */
                public final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return !((String) obj).equals(this.f$0);
                }
            }).limit(2).collect(Collectors.toList()));
        }
        return AbstractC136820a.A05(ocMessengerThreadArr).A0C(new AbstractC13031yl(hashSet, map, str) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$PNeZgOCfKdUtR1oZgQzPQYTUlM2 */
            public final /* synthetic */ Set f$1;
            public final /* synthetic */ Map f$2;
            public final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Map map;
                return new C13401zX(OcGraphQLQueryObservableUtil.createQueryProfilePicsSingle(OcUserPictureUrlsQueryHandler.this, this.f$1, map), new AbstractC13031yl(this.f$3, this.f$2) {
                    /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$WtA_2DWEYiucmpPcvF7mdoLrBJU2 */
                    public final /* synthetic */ String f$1;
                    public final /* synthetic */ Map f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    @Override // X.AbstractC13031yl
                    public final Object apply(Object obj) {
                        return ThreadListObservableUtil.lambda$createPatchedThreadsPictureUrlsObservable$11(OcMessengerThread.this, this.f$1, this.f$2, (Map) obj);
                    }
                }).A02();
            }
        }).A0H(new ArrayList(), $$Lambda$2AWp2UNi1mY6cDIZqfkAFWhQcU2.INSTANCE);
    }

    public static AbstractC136820a<OcMessengerManagerUpdateResult> createThreadListUpdatesObservable(AbstractC136820a<OcMessengerManagerUpdateResult> r3) {
        $$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2 r1 = $$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2.INSTANCE;
        AnonymousClass219.A01(r1, "predicate is null");
        C138920v r2 = new C138920v(r3, r1);
        OcMessengerManagerUpdateResult.Builder builder = new OcMessengerManagerUpdateResult.Builder();
        builder.mUpdateResultType = OcMessengerManagerUpdateResult.UpdateResultType.NONE;
        return r2.A0F(builder.build());
    }

    public static /* synthetic */ Quadruplet lambda$createThreadListObservable$2(OcApiConfig ocApiConfig, Optional optional, MessengerManagerJNI messengerManagerJNI, Integer num, OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
        return new Quadruplet(ocApiConfig, optional, messengerManagerJNI, num);
    }

    public static /* synthetic */ void lambda$createThreadListObservable$5() throws Exception {
    }

    public static /* synthetic */ void lambda$createThreadListObservableSource$8(MessengerManagerJNI messengerManagerJNI, int i, final OcApiConfig ocApiConfig, final Optional optional, final Map map, AnonymousClass1vb r12) throws Exception {
        final $$Lambda$Pa6xNt31CR6PP0QkLOaTzoDbuWk2 r4 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$Pa6xNt31CR6PP0QkLOaTzoDbuWk2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.onNext(obj);
            }
        };
        final $$Lambda$E8Bq8zVrC7A_wWRHoZ2h7hCpTbk2 r1 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$E8Bq8zVrC7A_wWRHoZ2h7hCpTbk2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.onError((Throwable) obj);
            }
        };
        final $$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 r6 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.A9q((AbstractC12271xB) obj);
            }
        };
        messengerManagerJNI.getThreads(i, 1, 0, new MessengerManagerJNI.GetThreadsCallback() {
            /* class com.oculus.messengervr.oc.ThreadListObservableUtil.AnonymousClass1 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadsCallback
            public void onResult(@Nullable Thread[] threadArr, int i, @Nullable String str) {
                String str2 = "";
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get threads: %s", str);
                    Consumer consumer = r1;
                    if (AnonymousClass006.A07("Failed to get messages: ", str) != null) {
                        str2 = str;
                    }
                    consumer.accept(new Throwable(str2));
                } else if (threadArr != null && threadArr.length > 0) {
                    OcMessengerThread[] patchThreadsWithCachedUserPictureUrls = ThreadListObservableUtil.patchThreadsWithCachedUserPictureUrls(threadArr, ocApiConfig.mUserId, map);
                    r4.accept(patchThreadsWithCachedUserPictureUrls);
                    if (optional.isPresent()) {
                        r6.accept(ThreadListObservableUtil.createPatchedThreadsPictureUrlsObservable((OcUserPictureUrlsQueryHandler) optional.get(), ocApiConfig.mUserId, map, patchThreadsWithCachedUserPictureUrls).A05(new AbstractC12851yS(r4) {
                            /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$1$HHX6TCxNJt7kJcUaxbBDpcoVls2 */
                            public final /* synthetic */ Consumer f$0;

                            {
                                this.f$0 = r1;
                            }

                            @Override // X.AbstractC12851yS
                            public final void accept(Object obj) {
                                ThreadListObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, (ArrayList) obj);
                            }
                        }, $$Lambda$ThreadListObservableUtil$1$WYQ27U26rC5fjzvkQ1uXfKLsGsA2.INSTANCE));
                    }
                }
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, ArrayList arrayList) throws Exception {
                arrayList.size();
                consumer.accept(arrayList.toArray(new MessengerThread[0]));
            }
        });
    }

    public static AbstractC136820a<MessengerThread[]> createThreadListObservable(AbstractC13251zE<OcApiConfig> r6, AbstractC13251zE<Optional<OcUserPictureUrlsQueryHandler>> r7, AbstractC13251zE<MessengerManagerJNI> r8, AbstractC136820a<Integer> r9, AbstractC136820a<OcMessengerManagerUpdateResult> r10, Map<String, String> map) {
        AbstractC136820a<OcMessengerManagerUpdateResult> createThreadListUpdatesObservable = createThreadListUpdatesObservable(r10);
        AbstractC136820a<OcApiConfig> A02 = r6.A02();
        AbstractC136820a<Optional<OcUserPictureUrlsQueryHandler>> A022 = r7.A02();
        AbstractC136820a<MessengerManagerJNI> A023 = r8.A02();
        AbstractC136820a<Integer> A0A = r9.A0A($$Lambda$ThreadListObservableUtil$aVE8aI8GJqfV1PraypC2ydjGzM2.INSTANCE);
        AbstractC13031yl<Object, Object> r1 = C137220e.A08;
        AnonymousClass219.A01(r1, "keySelector is null");
        C138320p r72 = new C138320p(A0A, r1, AnonymousClass219.A00);
        AbstractC136820a<OcMessengerManagerUpdateResult> A0A2 = createThreadListUpdatesObservable.A0A($$Lambda$ThreadListObservableUtil$QtpvQ23lGpaFfNso4X4UAQKAt182.INSTANCE);
        $$Lambda$ThreadListObservableUtil$HtL71NK7gMeFlBz_ui2dB6CZbdk2 r12 = $$Lambda$ThreadListObservableUtil$HtL71NK7gMeFlBz_ui2dB6CZbdk2.INSTANCE;
        AnonymousClass219.A01(A02, "source1 is null");
        AnonymousClass219.A01(A022, "source2 is null");
        AnonymousClass219.A01(A023, "source3 is null");
        AnonymousClass219.A01(r72, "source4 is null");
        AnonymousClass219.A01(A0A2, "source5 is null");
        AnonymousClass219.A01(r12, "f is null");
        return new AnonymousClass21H(AbstractC136820a.A04(new C138420q(r12), AbstractC13521zj.A00, A02, A022, A023, r72, A0A2).A0E(new AbstractC13031yl(map) {
            /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$KnXwOqig_0mH6BzbKRGrz3D0c2 */
            public final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Quadruplet quadruplet;
                return ThreadListObservableUtil.createThreadListObservableSource(quadruplet.first, quadruplet.second, quadruplet.third, ((Quadruplet) obj).forth.intValue(), this.f$0);
            }
        }).A0G(TimeUnit.MILLISECONDS).A0B($$Lambda$ThreadListObservableUtil$EgCUxYpOuvf7oAkoMDaPV5wsxs2.INSTANCE, $$Lambda$ThreadListObservableUtil$gDj2BkqiIZKCJpnqlfniImthJI2.INSTANCE).A09($$Lambda$ThreadListObservableUtil$yjkfrKxJsljggmNahsIwj4gOYc2.INSTANCE).A0I());
    }

    public static /* synthetic */ void lambda$createPatchedThreadsPictureUrlsObservable$10(OcMessengerThread ocMessengerThread, String str, Map map, Map map2, AbstractC10551og r9) throws Exception {
        if (ocMessengerThread.getThreadPictureUrl() == null) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : ocMessengerThread.mParticipantIds) {
                if (!str2.equals(str) && map.containsKey(str2) && arrayList.size() < 2) {
                    arrayList.add(map2.get(str2));
                }
            }
            if (!arrayList.isEmpty() && !arrayList.parallelStream().noneMatch($$Lambda$ysW_fvT8H1f4ly8te8Kd64ujJxw2.INSTANCE)) {
                OcMessengerThread.Builder newBuilder = OcMessengerThread.newBuilder(ocMessengerThread);
                newBuilder.mPatchedThreadPictureUrls = arrayList;
                newBuilder.mViewerId = str;
                ocMessengerThread = newBuilder.build();
            }
        }
        r9.onSuccess(ocMessengerThread);
    }

    public static /* synthetic */ AbstractC12761yH lambda$createPatchedThreadsPictureUrlsObservable$11(OcMessengerThread ocMessengerThread, String str, Map map, Map map2) throws Exception {
        if (!map2.isEmpty()) {
            return AbstractC13251zE.A00(new AbstractC06371Zh(str, map2, map) {
                /* class com.oculus.messengervr.oc.$$Lambda$ThreadListObservableUtil$2zbtm1tFTJjVb4oBGXE904QcYE2 */
                public final /* synthetic */ String f$1;
                public final /* synthetic */ Map f$2;
                public final /* synthetic */ Map f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // X.AbstractC06371Zh
                public final void subscribe(AbstractC10551og r5) {
                    ThreadListObservableUtil.lambda$createPatchedThreadsPictureUrlsObservable$10(OcMessengerThread.this, this.f$1, this.f$2, this.f$3, r5);
                }
            });
        }
        AnonymousClass219.A01(ocMessengerThread, "value is null");
        return new C12731yE(ocMessengerThread);
    }

    public static /* synthetic */ boolean lambda$createPatchedThreadsPictureUrlsObservable$9(String str, String str2) {
        return !str2.equals(str);
    }

    public static /* synthetic */ void lambda$createThreadListObservable$6(AnonymousClass215 r3) throws Exception {
        if (!r3.A01() && EnumC139220y.isError(r3.A00)) {
            AnonymousClass0MD.A07(Constants.LOG_TAG, "ThreadListObservable throw:", r3.A00());
        }
    }

    public static /* synthetic */ void lambda$createThreadListObservable$0(Integer num) throws Exception {
    }

    public static /* synthetic */ void lambda$createThreadListObservable$1(OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
    }

    public static /* synthetic */ void lambda$createThreadListObservable$4(AbstractC12271xB r0) throws Exception {
    }
}
