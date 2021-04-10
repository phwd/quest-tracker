package com.oculus.messengervr.fb;

import X.AbstractC06371Zh;
import X.AbstractC06511aN;
import X.AbstractC10551og;
import X.AbstractC12851yS;
import X.AbstractC13031yl;
import X.AbstractC13251zE;
import X.AbstractC13521zj;
import X.AbstractC136820a;
import X.AnonymousClass1Nt;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZX;
import X.AnonymousClass1Zb;
import X.AnonymousClass1a3;
import X.AnonymousClass1vb;
import X.AnonymousClass1vc;
import X.AnonymousClass219;
import X.AnonymousClass269;
import X.AnonymousClass26I;
import X.C06461Zy;
import X.C13391zW;
import X.C137220e;
import X.C138720t;
import X.C149126j;
import android.annotation.TargetApi;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mca.Mailbox;
import com.oculus.messengervr.common.utils.Triplet;
import com.oculus.messengervr.fb.utils.DebugUtil;
import com.oculus.messengervr.fbshared.models.FBMessengerThread;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ThreadListObservableUtil {
    public static /* synthetic */ int lambda$createThreadsObservable$10(int i) {
        return i + 10;
    }

    public static AbstractC136820a<Integer> createThreadListStoredProcedureChangesObservable(AbstractC13251zE<Mailbox> r2, AtomicBoolean atomicBoolean) {
        return new C13391zW(r2, new AbstractC13031yl(atomicBoolean) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$TU_eni48tlfV3V6aRHNF2oXiwZo2 */
            public final /* synthetic */ AtomicBoolean f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return AbstractC136820a.A01(new AnonymousClass1vc(this.f$0) {
                    /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$fYzLwzY4oUno0UnzdmSUkgdf9uM2 */
                    public final /* synthetic */ AtomicBoolean f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // X.AnonymousClass1vc
                    public final void subscribe(AnonymousClass1vb r3) {
                        ThreadListObservableUtil.lambda$createThreadListStoredProcedureChangesObservable$2(Mailbox.this, this.f$1, r3);
                    }
                });
            }
        });
    }

    public static AbstractC136820a<FBMessengerThread[]> createThreadsObservable(C06461Zy r1, AnonymousClass269 r2, int i, AtomicInteger atomicInteger) {
        return AbstractC136820a.A01(new AnonymousClass1vc(i, r2, atomicInteger, r1) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$w5WJd_8Tn_xT9Wq2wQlo5mbM482 */
            public final /* synthetic */ int f$0;
            public final /* synthetic */ AnonymousClass269 f$1;
            public final /* synthetic */ AtomicInteger f$2;
            public final /* synthetic */ C06461Zy f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AnonymousClass1vc
            public final void subscribe(AnonymousClass1vb r5) {
                ThreadListObservableUtil.lambda$createThreadsObservable$12(this.f$0, this.f$1, this.f$2, this.f$3, r5);
            }
        });
    }

    public static /* synthetic */ Triplet lambda$createThreadListObservable$4(C06461Zy r1, AnonymousClass269 r2, Integer num, Integer num2) throws Exception {
        return new Triplet(r1, r2, num);
    }

    public static /* synthetic */ void lambda$createThreadListStoredProcedureChangesObservable$0(AnonymousClass1vb r1, Set set) {
        DebugUtil.logStoredProcedureNames("VR_MESSENGER_API", set);
        if (set.contains(Constants.THREAD_LIST_STORED_PROCEDURE_NAME)) {
            r1.onNext(0);
        }
    }

    public static /* synthetic */ void lambda$createThreadListStoredProcedureChangesObservable$2(Mailbox mailbox, AtomicBoolean atomicBoolean, AnonymousClass1vb r4) throws Exception {
        $$Lambda$ThreadListObservableUtil$PolBwRGbMfAVWZIjjVvI7dfAetU2 r1 = new AnonymousClass1Nt() {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$PolBwRGbMfAVWZIjjVvI7dfAetU2 */

            @Override // X.AnonymousClass1Nt
            public final void onStoredProcedureChanged(Set set) {
                ThreadListObservableUtil.lambda$createThreadListStoredProcedureChangesObservable$0(AnonymousClass1vb.this, set);
            }
        };
        r4.A9i(new AbstractC06511aN(r1) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$Lnk9DB9_b4PCwUyFecNUYgwm4AM2 */
            public final /* synthetic */ AnonymousClass1Nt f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC06511aN
            public final void cancel() {
                Mailbox.this.mStoredProcedureChangedListeners.remove((Mailbox) this.f$1);
            }
        });
        mailbox.mStoredProcedureChangedListeners.add(r1);
        if (!atomicBoolean.compareAndSet(true, false)) {
            r4.onNext(0);
        }
    }

    public static /* synthetic */ void lambda$createThreadsObservable$11(int i, AnonymousClass1vb r8, AtomicInteger atomicInteger, C06461Zy r10, C149126j r11) {
        int i2;
        T t = r11.A00;
        if (t != null) {
            i2 = t.mResultSet.getCount();
        } else {
            i2 = 0;
        }
        if (t != null) {
            DebugUtil.logThreadList("VR_MESSENGER_API", t);
        }
        FBMessengerThread[] fBMessengerThreadArr = new FBMessengerThread[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
            builder.mThreadKey = Long.valueOf(t.mResultSet.getLong(i3, 4));
            builder.mThreadName = t.mResultSet.getString(i3, 5);
            builder.mLastActivityTimestampMs = Long.valueOf(t.mResultSet.getLong(i3, 2));
            builder.mSnippet = t.mResultSet.getString(i3, 3);
            builder.mThreadPictureUrl = t.mResultSet.getString(i3, 6);
            builder.mParticipantProfilePictureUrlsForThreadPicture = null;
            builder.mIsUnread = Boolean.valueOf(t.mResultSet.getBoolean(i3, 1));
            builder.mIsMuted = Boolean.valueOf(t.mResultSet.getBoolean(i3, 0));
            fBMessengerThreadArr[i3] = builder.build();
        }
        r8.onNext(fBMessengerThreadArr);
        if (i2 >= i || i2 != atomicInteger.get()) {
            int max = Math.max(atomicInteger.get(), ((int) Math.ceil((double) (((float) i2) / 10.0f))) * 10);
            if (atomicInteger.get() != max) {
                atomicInteger.set(max);
            }
            r8.onComplete();
            return;
        }
        $$Lambda$ThreadListObservableUtil$YyC709AZnbfu8_WDw8nAwX1SIFQ2 r0 = new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$YyC709AZnbfu8_WDw8nAwX1SIFQ2 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                AnonymousClass1vb.this.onComplete();
            }
        };
        AnonymousClass1Z6 r2 = r10.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r0);
        r2.A9T(new AnonymousClass1a3(r10, r1, -1));
        r8.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
        atomicInteger.updateAndGet($$Lambda$ThreadListObservableUtil$_LgJO3lMTNS7MM_jSLXCUKHJb6Q2.INSTANCE);
    }

    public static /* synthetic */ void lambda$createThreadsObservable$12(int i, AnonymousClass269 r4, AtomicInteger atomicInteger, C06461Zy r6, AnonymousClass1vb r7) throws Exception {
        $$Lambda$ThreadListObservableUtil$liwtbE_VsJV9yh0pMPy06desTXA2 r0 = new AnonymousClass1YZ(i, r7, atomicInteger, r6) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$liwtbE_VsJV9yh0pMPy06desTXA2 */
            public final /* synthetic */ int f$0;
            public final /* synthetic */ AnonymousClass1vb f$1;
            public final /* synthetic */ AtomicInteger f$2;
            public final /* synthetic */ C06461Zy f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                ThreadListObservableUtil.lambda$createThreadsObservable$11(this.f$0, this.f$1, this.f$2, this.f$3, (C149126j) obj);
            }
        };
        AnonymousClass1Z6 r2 = r4.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r0);
        r2.A9T(new AnonymousClass26I(r4, r1, i));
        r7.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static AbstractC13251zE<ArrayList<MessengerThread>> createPatchThreadPictureUrlsObservable(AnonymousClass269 r2, String str, Map<Long, Pair<String, String>> map, FBMessengerThread[] fBMessengerThreadArr) {
        return AbstractC136820a.A05(fBMessengerThreadArr).A0C(new AbstractC13031yl(str, map) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$j_IUfIrCbGNs_Vh3pZ32NJpa5Y2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ Map f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return AbstractC13251zE.A00(new AbstractC06371Zh(AnonymousClass269.this, this.f$1, this.f$2) {
                    /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$VsNiqmujZJ2cCvZbio9i3l6e_7U2 */
                    public final /* synthetic */ AnonymousClass269 f$1;
                    public final /* synthetic */ String f$2;
                    public final /* synthetic */ Map f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r5) {
                        ThreadListObservableUtil.lambda$createPatchThreadPictureUrlsObservable$14(MessengerThread.this, this.f$1, this.f$2, this.f$3, r5);
                    }
                }).A02();
            }
        }).A0H(new ArrayList(), $$Lambda$2AWp2UNi1mY6cDIZqfkAFWhQcU2.INSTANCE);
    }

    public static AbstractC136820a<MessengerThread[]> createThreadListObservable(AbstractC13251zE<ApiConfig> r4, AbstractC13251zE<C06461Zy> r5, AbstractC13251zE<AnonymousClass269> r6, AbstractC136820a<Integer> r7, AtomicInteger atomicInteger, Map<Long, Pair<String, String>> map, AbstractC136820a<Integer> r10) {
        AbstractC136820a A0E = AbstractC136820a.A02(r5.A02(), r6.A02(), r10, r7, $$Lambda$ThreadListObservableUtil$EHgs1FbzwntzQ5lplAfTbEgLN6Y2.INSTANCE).A0E(new AbstractC13031yl(atomicInteger) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$3SU6tA0NPMigjyZLdhAfPehfHnc2 */
            public final /* synthetic */ AtomicInteger f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Triplet triplet;
                return ThreadListObservableUtil.createThreadsObservable(triplet.first, triplet.second, ((Triplet) obj).third.intValue(), this.f$0);
            }
        });
        AbstractC136820a<ApiConfig> A02 = r4.A02();
        AbstractC136820a<AnonymousClass269> A022 = r6.A02();
        $$Lambda$iAzW9r3zjIaTHiiFZppFf6XCjY2 r1 = $$Lambda$iAzW9r3zjIaTHiiFZppFf6XCjY2.INSTANCE;
        AnonymousClass219.A01(A02, "source1 is null");
        AnonymousClass219.A01(A022, "source2 is null");
        AnonymousClass219.A01(A0E, "source3 is null");
        AnonymousClass219.A01(r1, "f is null");
        return AbstractC136820a.A04(new C138720t(r1), AbstractC13521zj.A00, A02, A022, A0E).A0D(new AbstractC13031yl(map) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$IIhiiL9LSdwqdMH4BlX2MuNdmSk2 */
            public final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Map map = this.f$0;
                return ((Triplet) obj).second;
            }
        });
    }

    public static /* synthetic */ void lambda$createPatchThreadPictureUrlsObservable$13(Map map, MessengerThread messengerThread, AbstractC10551og r5, Pair pair) {
        if (pair.equals(map.get(Long.valueOf(messengerThread.getThreadKey())))) {
            messengerThread.getThreadKey();
            r5.onSuccess(messengerThread);
            return;
        }
        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
        builder.mThreadKey = Long.valueOf(messengerThread.getThreadKey());
        builder.mThreadName = messengerThread.getThreadName();
        builder.mLastActivityTimestampMs = Long.valueOf(messengerThread.getLastActivityTimestampMs());
        builder.mSnippet = messengerThread.getSnippet();
        builder.mParticipantProfilePictureUrlsForThreadPicture = pair;
        builder.mIsUnread = Boolean.valueOf(messengerThread.isUnread());
        builder.mIsMuted = messengerThread.isMuted();
        FBMessengerThread build = builder.build();
        map.put(Long.valueOf(messengerThread.getThreadKey()), pair);
        messengerThread.getThreadKey();
        r5.onSuccess(build);
    }

    public static /* synthetic */ void lambda$createPatchThreadPictureUrlsObservable$14(MessengerThread messengerThread, AnonymousClass269 r4, String str, Map map, AbstractC10551og r7) throws Exception {
        if (messengerThread.getThreadPictureUrl() != null) {
            r7.onSuccess(messengerThread);
        } else {
            ThreadObservableUtil.loadAndPatchThreadPictureUrls(r4, messengerThread.getThreadKey(), str, new Consumer(map, messengerThread, r7) {
                /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$2vezAvd3O5EYplcFyh6AtXy8xeA2 */
                public final /* synthetic */ Map f$0;
                public final /* synthetic */ MessengerThread f$1;
                public final /* synthetic */ AbstractC10551og f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThreadListObservableUtil.lambda$createPatchThreadPictureUrlsObservable$13(this.f$0, this.f$1, this.f$2, (Pair) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$createThreadListObservable$6(AnonymousClass1vb r1, ArrayList arrayList) throws Exception {
        arrayList.size();
        r1.onNext(arrayList.toArray(new MessengerThread[0]));
    }

    public static /* synthetic */ void lambda$createThreadListObservable$7(FBMessengerThread[] fBMessengerThreadArr, Map map, AnonymousClass269 r3, ApiConfig apiConfig, AnonymousClass1vb r5) throws Exception {
        patchThreadsWithCachedThreadPictureUrls(fBMessengerThreadArr, map);
        r5.onNext(fBMessengerThreadArr);
        r5.A9q(createPatchThreadPictureUrlsObservable(r3, apiConfig.mUserId, map, fBMessengerThreadArr).A05(new AbstractC12851yS() {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadListObservableUtil$aXRGlLM6vKpGJ2g7E9d0kMoI02 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                ThreadListObservableUtil.lambda$createThreadListObservable$6(AnonymousClass1vb.this, (ArrayList) obj);
            }
        }, C137220e.A06));
    }

    public static FBMessengerThread[] patchThreadsWithCachedThreadPictureUrls(FBMessengerThread[] fBMessengerThreadArr, Map<Long, Pair<String, String>> map) {
        Pair<String, String> pair;
        if (!map.isEmpty()) {
            for (int i = 0; i < fBMessengerThreadArr.length; i++) {
                if (fBMessengerThreadArr[i].getThreadPictureUrl() == null && (pair = map.get(Long.valueOf(fBMessengerThreadArr[i].getThreadKey()))) != null) {
                    FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
                    FBMessengerThread fBMessengerThread = fBMessengerThreadArr[i];
                    builder.mThreadKey = Long.valueOf(fBMessengerThread.getThreadKey());
                    builder.mThreadName = fBMessengerThread.getThreadName();
                    builder.mLastActivityTimestampMs = Long.valueOf(fBMessengerThread.getLastActivityTimestampMs());
                    builder.mSnippet = fBMessengerThread.getSnippet();
                    builder.mThreadPictureUrl = fBMessengerThread.getThreadPictureUrl();
                    builder.mParticipantProfilePictureUrlsForThreadPicture = pair;
                    builder.mIsUnread = Boolean.valueOf(fBMessengerThread.isUnread());
                    builder.mIsMuted = fBMessengerThread.isMuted();
                    fBMessengerThreadArr[i] = builder.build();
                }
            }
        }
        return fBMessengerThreadArr;
    }
}
