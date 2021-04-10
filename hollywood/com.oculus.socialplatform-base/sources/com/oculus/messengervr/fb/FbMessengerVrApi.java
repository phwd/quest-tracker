package com.oculus.messengervr.fb;

import X.AbstractC06371Zh;
import X.AbstractC06511aN;
import X.AbstractC10551og;
import X.AbstractC12761yH;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC12951yd;
import X.AbstractC13031yl;
import X.AbstractC13121yu;
import X.AbstractC13191z2;
import X.AbstractC13231zC;
import X.AbstractC13241zD;
import X.AbstractC13251zE;
import X.AbstractC13331zP;
import X.AbstractC136820a;
import X.AnonymousClass0MD;
import X.AnonymousClass1Kt;
import X.AnonymousClass1Kw;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZN;
import X.AnonymousClass1ZO;
import X.AnonymousClass1ZQ;
import X.AnonymousClass1ZR;
import X.AnonymousClass1ZX;
import X.AnonymousClass1Zb;
import X.AnonymousClass1a0;
import X.AnonymousClass1a1;
import X.AnonymousClass1a2;
import X.AnonymousClass1a4;
import X.AnonymousClass1a5;
import X.AnonymousClass1xU;
import X.AnonymousClass1y7;
import X.AnonymousClass1z5;
import X.AnonymousClass20Q;
import X.AnonymousClass20S;
import X.AnonymousClass218;
import X.AnonymousClass219;
import X.AnonymousClass269;
import X.AnonymousClass26D;
import X.AnonymousClass26E;
import X.AnonymousClass26Z;
import X.AnonymousClass292;
import X.AnonymousClass293;
import X.AnonymousClass299;
import X.C06441Zw;
import X.C06461Zy;
import X.C06471Zz;
import X.C12701yB;
import X.C12931yb;
import X.C13141yw;
import X.C13151yx;
import X.C13171yz;
import X.C13261zF;
import X.C13401zX;
import X.C13621zt;
import X.C13641zv;
import X.C137220e;
import X.C138620s;
import X.C138820u;
import X.C138920v;
import android.annotation.TargetApi;
import android.app.Application;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Cleanup;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.mca.MailboxMessengerVr$LoadThreadViewDataOptions;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.Analytics;
import com.facebook.msys.mci.Execution;
import com.facebook.msys.util.NotificationScope;
import com.oculus.messengervr.common.utils.MutationObservableUtil;
import com.oculus.messengervr.fb.utils.DebugUtil;
import com.oculus.messengervr.fbshared.models.FBMessengerContact;
import com.oculus.messengervr.fbshared.models.FBMessengerThread;
import com.oculus.messengervr.interfaces.MessengerContact;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.util.device.DeviceUtils;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import okhttp3.OkHttpClient;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FbMessengerVrApi implements MessengerVrApi {
    public final AbstractC13251zE<ApiConfig> mApiConfigSingle;
    @Nullable
    public AbstractC13241zD mApiReadyCompletable;
    public final AnonymousClass1xU mCompositeDisposable;
    public final AtomicBoolean mIsMsysDatabaseNewlyCreated;
    public final AbstractC13251zE<C06461Zy> mMailboxCoreSingle;
    public final AbstractC13251zE<AnonymousClass269> mMailboxMessengerVrSingle;
    public final AbstractC13251zE<Mailbox> mMailboxSingle;
    public final C12701yB<VrMsysMqttClientCallbacks> mMqttClientCallbacksSingleSubject;
    public final OkHttpClient mOkHttpClient;
    public final AbstractC136820a<Integer> mThreadListStoredProcedureChangesObservable;
    public final Map<Long, AbstractC136820a<MessengerThread>> mThreadObservablesCache;
    public final Map<Long, Pair<String, String>> mThreadPictureUrlsCache;
    public final Map<Long, AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>>> mThreadViewDataObservablesCache;
    public final AtomicInteger mTotalLocalThreadsCount;

    private synchronized AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>> getThreadViewDataObservable(long j) {
        return (AbstractC136820a) Objects.requireNonNull(this.mThreadViewDataObservablesCache.computeIfAbsent(Long.valueOf(j), new Function(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$pTMug2nqO1X6o2M20XVM5m9zcb42 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FbMessengerVrApi.this.lambda$getThreadViewDataObservable$50$FbMessengerVrApi(this.f$1, (Long) obj);
            }
        }), "getThreadViewDataObservable should not return null.");
    }

    public static /* synthetic */ void lambda$createGroupThreadWithInitialMessage$36(C06461Zy r6, List list, AnonymousClass269 r8, String str, AbstractC10551og r10) throws Exception {
        $$Lambda$FbMessengerVrApi$ivuCN4Ub3yqGsVT6JVs_ruBAjbY2 r3 = new AnonymousClass1YZ(list, r8, r6, str, r10) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$ivuCN4Ub3yqGsVT6JVs_ruBAjbY2 */
            public final /* synthetic */ List f$0;
            public final /* synthetic */ AnonymousClass269 f$1;
            public final /* synthetic */ C06461Zy f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ AbstractC10551og f$4;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                FbMessengerVrApi.lambda$createGroupThreadWithInitialMessage$35(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Number) obj);
            }
        };
        AnonymousClass1Z6 r2 = r6.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r3);
        r2.A9T(new AnonymousClass1a1(r6, r1, list));
        r10.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$getContactListObservable$24(AnonymousClass269 r6, int i, AbstractC10551og r8) throws Exception {
        $$Lambda$FbMessengerVrApi$OZcSVmyG7yrOPRM8Y7di0ZlAbnQ2 r1 = new AnonymousClass1YZ(i, r8) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$OZcSVmyG7yrOPRM8Y7di0ZlAbnQ2 */
            public final /* synthetic */ int f$0;
            public final /* synthetic */ AbstractC10551og f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                FbMessengerVrApi.lambda$getContactListObservable$23(this.f$0, this.f$1, (AnonymousClass293) obj);
            }
        };
        AnonymousClass1Z6 r0 = r6.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new AnonymousClass26D(r6, r3, i, 1, 1));
        r8.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$muteThread$42(C06461Zy r7, long j, Date date, AnonymousClass292 r11) throws Exception {
        $$Lambda$FbMessengerVrApi$jnsYKwstC_UHJXrcBUXj6hr5TfY2 r1 = new AnonymousClass1YZ(j, date, r11) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$jnsYKwstC_UHJXrcBUXj6hr5TfY2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Date f$1;
            public final /* synthetic */ AnonymousClass292 f$2;

            {
                this.f$0 = r1;
                this.f$1 = r3;
                this.f$2 = r4;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$2.onComplete();
            }
        };
        AnonymousClass1Z6 r0 = r7.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new C06441Zw(r7, r3, j, date));
        r11.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$new$0(AnonymousClass1Kt r0) {
    }

    public static /* synthetic */ void lambda$removeParticipantFromGroup$39(C06461Zy r11, long j, long j2, AbstractC10551og r16) throws Exception {
        Long valueOf = Long.valueOf(j);
        Long valueOf2 = Long.valueOf(j2);
        $$Lambda$FbMessengerVrApi$301V5NpjQsKrGNBW_36XIVUkC82 r5 = new AnonymousClass1YZ(j, j2, r16) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$301V5NpjQsKrGNBW_36XIVUkC82 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ long f$1;
            public final /* synthetic */ AbstractC10551og f$2;

            {
                this.f$0 = r1;
                this.f$1 = r3;
                this.f$2 = r5;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$2.onSuccess(obj);
            }
        };
        AnonymousClass1Z6 r2 = r11.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r5);
        r2.A9T(new AnonymousClass1a2(r11, r1, valueOf, valueOf2));
        r16.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$sendMessage$28(C06461Zy r6, long j, String str, AbstractC10551og r10) throws Exception {
        $$Lambda$FbMessengerVrApi$DFFuhrxGKr5k2ZOJC1SOKW_NUU2 r1 = new AnonymousClass1YZ(j, r10) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$DFFuhrxGKr5k2ZOJC1SOKW_NUU2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ AbstractC10551og f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$1.onSuccess(obj);
            }
        };
        AnonymousClass1Z6 r0 = r6.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new AnonymousClass1a0(r6, r3, j, str));
        r10.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$sendStickerMessage$31(C06461Zy r6, long j, long j2, AbstractC10551og r11) throws Exception {
        $$Lambda$FbMessengerVrApi$D6foempkJL4KlLWT_HLLHBy999g2 r1 = new AnonymousClass1YZ(j, r11) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$D6foempkJL4KlLWT_HLLHBy999g2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ AbstractC10551og f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$1.onSuccess(obj);
            }
        };
        AnonymousClass1Z6 r0 = r6.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new C06471Zz(r6, r3, j, j2));
        r11.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$createGroupThreadWithInitialMessage$34(long j, C06461Zy r8, String str, AbstractC10551og r10, AnonymousClass26Z r11) {
        AnonymousClass299 r4 = r11.A02;
        r4.mResultSet.getLong(0, 0);
        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
        builder.mThreadKey = Long.valueOf(r4.mResultSet.getLong(0, 0));
        builder.mThreadName = r4.mResultSet.getString(0, 23);
        builder.mLastActivityTimestampMs = Long.valueOf(r4.mResultSet.getLong(0, 8));
        builder.mThreadPictureUrl = r4.mResultSet.getString(0, 5);
        builder.mIsUnread = Boolean.valueOf(r4.mResultSet.getBoolean(0, 10));
        builder.mIsMuted = false;
        FBMessengerThread build = builder.build();
        long threadKey = build.getThreadKey();
        $$Lambda$FbMessengerVrApi$bI1f3q6zW3r4HwK7yJQItS06gs2 r1 = new AnonymousClass1YZ(j, build, r10) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$bI1f3q6zW3r4HwK7yJQItS06gs2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ FBMessengerThread f$1;
            public final /* synthetic */ AbstractC10551og f$2;

            {
                this.f$0 = r1;
                this.f$1 = r3;
                this.f$2 = r4;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$2.onSuccess(this.f$1);
            }
        };
        AnonymousClass1Z6 r0 = r8.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new AnonymousClass1a0(r8, r3, threadKey, str));
        r10.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ void lambda$getContactListObservable$23(int i, AbstractC10551og r10, AnonymousClass293 r11) {
        r11.mResultSet.getCount();
        DebugUtil.logContactList("VR_MESSENGER_API", r11);
        FBMessengerContact[] fBMessengerContactArr = new FBMessengerContact[r11.mResultSet.getCount()];
        for (int i2 = 0; i2 < r11.mResultSet.getCount(); i2++) {
            fBMessengerContactArr[i2] = new FBMessengerContact(r11.mResultSet.getLong(i2, 1), r11.mResultSet.getString(i2, 5), r11.mResultSet.getString(i2, 6), r11.mResultSet.getInteger(i2, 0));
        }
        r10.onSuccess(fBMessengerContactArr);
    }

    public static /* synthetic */ void lambda$getMessageListObservable$15(long j, MessengerMessage[] messengerMessageArr) throws Exception {
    }

    public static /* synthetic */ void lambda$markThreadAsRead$48(C06461Zy r3, long j, AnonymousClass292 r6) throws Exception {
        $$Lambda$FbMessengerVrApi$fz4uEtkg8dkSCYzr1uDdYjh6WA82 r0 = new AnonymousClass1YZ(j, r6) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$fz4uEtkg8dkSCYzr1uDdYjh6WA82 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ AnonymousClass292 f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$1.onComplete();
            }
        };
        AnonymousClass1Z6 r2 = r3.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r0);
        r2.A9T(new AnonymousClass1a5(r3, r1, j));
        r6.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ AbstractC13241zD lambda$markThreadAsRead$49(long j, C06461Zy r4) {
        return new C12931yb(new AbstractC12951yd(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$uKs6ywSdQ67mJ4LsaTcu85BXV3M2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r4) {
                FbMessengerVrApi.lambda$markThreadAsRead$48(C06461Zy.this, this.f$1, r4);
            }
        });
    }

    public static /* synthetic */ AbstractC13241zD lambda$muteThread$43(long j, Date date, C06461Zy r5) {
        return new C12931yb(new AbstractC12951yd(j, date) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$UVRACyIfGcLtpOCAFoHCVP5gQc2 */
            public final /* synthetic */ long f$1;
            public final /* synthetic */ Date f$2;

            {
                this.f$1 = r2;
                this.f$2 = r4;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r5) {
                FbMessengerVrApi.lambda$muteThread$42(C06461Zy.this, this.f$1, this.f$2, r5);
            }
        });
    }

    public static /* synthetic */ C06461Zy lambda$new$4(Mailbox mailbox) throws Exception {
        return new C06461Zy(mailbox);
    }

    public static /* synthetic */ AnonymousClass269 lambda$new$5(Mailbox mailbox) throws Exception {
        return new AnonymousClass269(mailbox);
    }

    public static /* synthetic */ void lambda$unmuteThread$45(C06461Zy r3, long j, AnonymousClass292 r6) throws Exception {
        $$Lambda$FbMessengerVrApi$rqF6V7aWBA_pzpx971q7eVXyhgw2 r0 = new AnonymousClass1YZ(j, r6) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$rqF6V7aWBA_pzpx971q7eVXyhgw2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ AnonymousClass292 f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$1.onComplete();
            }
        };
        AnonymousClass1Z6 r2 = r3.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r0);
        r2.A9T(new AnonymousClass1a4(r3, r1, j));
        r6.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ AbstractC13241zD lambda$unmuteThread$46(long j, C06461Zy r4) {
        return new C12931yb(new AbstractC12951yd(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$vSkLK9dRBX4wDdClIkEKpzslUYg2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12951yd
            public final void subscribe(AnonymousClass292 r4) {
                FbMessengerVrApi.lambda$unmuteThread$45(C06461Zy.this, this.f$1, r4);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<MessengerThread> createGroupThreadWithInitialMessage(List<Long> list, String str) {
        return MutationObservableUtil.wrapMutationSingle(AbstractC13251zE.A01(this.mMailboxCoreSingle, this.mMailboxMessengerVrSingle, $$Lambda$SQYDVix3r6cbfKPRYwMGmLQp002.INSTANCE), this.mCompositeDisposable, new Function(list, str) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$F9howQV2Rvo2bYS7_xQAlpU1Y8Q2 */
            public final /* synthetic */ List f$0;
            public final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Pair pair;
                return AbstractC13251zE.A00(new AbstractC06371Zh(this.f$0, (AnonymousClass269) ((Pair) obj).second, this.f$1) {
                    /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$0jj17qQWz6VODbJPjrXu0O_V5dc2 */
                    public final /* synthetic */ List f$1;
                    public final /* synthetic */ AnonymousClass269 f$2;
                    public final /* synthetic */ String f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r5) {
                        FbMessengerVrApi.lambda$createGroupThreadWithInitialMessage$36(C06461Zy.this, this.f$1, this.f$2, this.f$3, r5);
                    }
                });
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD getApiReadyCompletable() {
        return this.mApiReadyCompletable;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerContact[]> getContactListObservable(AbstractC136820a<Integer> r5) {
        AbstractC136820a A03 = AbstractC136820a.A03(this.mMailboxMessengerVrSingle.A02(), (AbstractC13121yu) r5.A0J($$Lambda$FbMessengerVrApi$Ee80Bhitl53vP3XsA3hIevMPZ1c2.INSTANCE), $$Lambda$gleTn0PKyKdhFw3Of_dKmrHnoG42.INSTANCE);
        $$Lambda$FbMessengerVrApi$STsKgyyTvpzFFXV9UBWYRNG37g2 r2 = $$Lambda$FbMessengerVrApi$STsKgyyTvpzFFXV9UBWYRNG37g2.INSTANCE;
        AnonymousClass219.A01(r2, "mapper is null");
        return (AbstractC136820a) new AnonymousClass20S(A03, r2).A0J($$Lambda$FbMessengerVrApi$2s6qOgavzsOzd3z0Fig4Yca3ASU2.INSTANCE);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerMessage[]> getMessageListObservable(long j, AbstractC136820a<Integer> r7) {
        return (AbstractC136820a) MessageListObservableUtil.createMessageListObservable(this.mMailboxCoreSingle, this.mMailboxMessengerVrSingle, getThreadObservable(j), (AbstractC136820a) r7.A0J($$Lambda$FbMessengerVrApi$ETUqMqaDeYnuhPuu7jfyo7YYZTk2.INSTANCE)).A0E(new AbstractC13031yl(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$sW4tGJ3OedbiY155xDfBa2GVho2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return FbMessengerVrApi.this.lambda$getMessageListObservable$17$FbMessengerVrApi(this.f$1, (Pair) obj);
            }
        }).A0J($$Lambda$FbMessengerVrApi$2YAi3V3Dz_HnZNYfSuMvQeJg_02.INSTANCE);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerThread[]> getThreadListObservable(AbstractC136820a<Integer> r9) {
        return (AbstractC136820a) ThreadListObservableUtil.createThreadListObservable(this.mApiConfigSingle, this.mMailboxCoreSingle, this.mMailboxMessengerVrSingle, this.mThreadListStoredProcedureChangesObservable, this.mTotalLocalThreadsCount, this.mThreadPictureUrlsCache, (AbstractC136820a) r9.A0J($$Lambda$FbMessengerVrApi$t7aY0pyxyOjmlZn2ymvb6se54LQ2.INSTANCE)).A0J($$Lambda$FbMessengerVrApi$rWwCb_svQvQSzhD5wfM_E3EKlEM2.INSTANCE);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerThread> getThreadObservable(long j) {
        return (AbstractC136820a) Objects.requireNonNull(this.mThreadObservablesCache.computeIfAbsent(Long.valueOf(j), new Function(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$50bDeB4yRtTAwgh_4OqlyD5DpBg2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FbMessengerVrApi.this.lambda$getThreadObservable$13$FbMessengerVrApi(this.f$1, (Long) obj);
            }
        }), "getThreadObservable should not return null.");
    }

    public /* synthetic */ AbstractC13121yu lambda$getMessageListObservable$17$FbMessengerVrApi(long j, Pair pair) throws Exception {
        MessengerMessage[] messengerMessageArr = (MessengerMessage[]) pair.first;
        if (messengerMessageArr.length != 0) {
            return new C138820u(messengerMessageArr);
        }
        AbstractC13231zC<MessengerMessage[]> fetchMessageList = GraphQLUtil.fetchMessageList(this.mOkHttpClient, this.mApiConfigSingle.A06().mAccessToken, j, ((Number) pair.second).intValue());
        $$Lambda$FbMessengerVrApi$Li1xwKGmwxZRy92JZHIon1aM2ms2 r6 = new AbstractC12851yS(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$Li1xwKGmwxZRy92JZHIon1aM2ms2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
            }
        };
        AbstractC12851yS<Object> r5 = C137220e.A04;
        AbstractC12881yV r8 = C137220e.A03;
        C13141yw r3 = new C13141yw(fetchMessageList, r5, r6, r5, r8, r8, r8);
        $$Lambda$FbMessengerVrApi$19Ysjy2YRClXB1Rxi566P7Sn1Y2 r62 = $$Lambda$FbMessengerVrApi$19Ysjy2YRClXB1Rxi566P7Sn1Y2.INSTANCE;
        AnonymousClass219.A01(r62, "onError is null");
        C13151yx r1 = new C13151yx(new C13141yw(r3, r5, r5, r62, r8, r8, r8), new AnonymousClass218(new MessengerMessage[0]));
        if (r1 instanceof AbstractC13331zP) {
            return ((AbstractC13331zP) r1).A3G();
        }
        return new AnonymousClass1z5(r1);
    }

    public /* synthetic */ AbstractC136820a lambda$getThreadViewDataObservable$50$FbMessengerVrApi(long j, Long l) {
        return (AbstractC136820a) ThreadObservableUtil.createThreadViewDataObservable(this.mApiConfigSingle, this.mMailboxMessengerVrSingle, j).A0J($$Lambda$vR4qkj3KSQpLchy1G9jDRnsE0vE2.INSTANCE);
    }

    public /* synthetic */ void lambda$logout$8$FbMessengerVrApi(Void r2) {
        this.mMqttClientCallbacksSingleSubject.A06().destroy();
    }

    public /* synthetic */ void lambda$logoutAndDeleteData$9$FbMessengerVrApi(Void r2) {
        this.mMqttClientCallbacksSingleSubject.A06().destroy();
    }

    public /* synthetic */ AbstractC12761yH lambda$new$3$FbMessengerVrApi(AnonymousClass1Kw r19, String str, Analytics analytics, ApiConfig apiConfig) throws Exception {
        Application application = apiConfig.mContext;
        String str2 = apiConfig.mAppId;
        String str3 = apiConfig.mAppName;
        String str4 = apiConfig.mUserId;
        String str5 = apiConfig.mAccessToken;
        String str6 = (String) Optional.ofNullable(apiConfig.mDeviceId).orElseGet(new Supplier() {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$6FHWC2FUf8qS61RQdXoxlFMGYc2 */

            @Override // java.util.function.Supplier
            public final Object get() {
                return DeviceUtils.getDeviceId(ApiConfig.this.mContext);
            }
        });
        VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks = new VrMsysMqttClientCallbacks(application, str2, str3, str4, str5, str6, r19);
        Execution.initialize();
        this.mMqttClientCallbacksSingleSubject.onSuccess(vrMsysMqttClientCallbacks);
        return VrMsysMailboxCreator.createMailboxWaitOnDbOpenSingle(application, str2, str3, apiConfig.mClientToken, str4, str5, str6, vrMsysMqttClientCallbacks, str, new Consumer() {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$2fjmyrCsiT1e09yJLxdJ3T5KMqw2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FbMessengerVrApi.this.lambda$new$2$FbMessengerVrApi((Boolean) obj);
            }
        }, analytics);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    @Cleanup
    public void logout() {
        this.mCompositeDisposable.A01();
        this.mThreadPictureUrlsCache.clear();
        Mailbox A06 = this.mMailboxSingle.A06();
        AnonymousClass1Z6 r0 = A06.mSynchronousMailboxProvider;
        if (r0 == null) {
            r0 = new AnonymousClass1ZR(A06);
            A06.mSynchronousMailboxProvider = r0;
        }
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        NotificationScope A00 = A06.mNotificationCenterCallbackManager.A00("MCAMailboxDidShutdownNotification", new AnonymousClass1ZN(A06, r3));
        r3.A04("MCAMailboxDidShutdownNotification", A00);
        Execution.executePossiblySync(new AnonymousClass1ZO(A06, A00), 1);
        r3.AA9(new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$ClD4ch1gmOZpAe2F7QwuU0CKT7o2 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                FbMessengerVrApi.this.lambda$logout$8$FbMessengerVrApi(null);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    @Cleanup
    public void logoutAndDeleteData() {
        this.mCompositeDisposable.A01();
        this.mThreadPictureUrlsCache.clear();
        Mailbox A06 = this.mMailboxSingle.A06();
        AnonymousClass1Z6 r0 = A06.mSynchronousMailboxProvider;
        if (r0 == null) {
            r0 = new AnonymousClass1ZR(A06);
            A06.mSynchronousMailboxProvider = r0;
        }
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        NotificationScope A00 = A06.mNotificationCenterCallbackManager.A00("MCAMailboxDidShutdownNotification", new AnonymousClass1ZN(A06, r3));
        r3.A04("MCAMailboxDidShutdownNotification", A00);
        Execution.executePossiblySync(new AnonymousClass1ZQ(A06, A00), 1);
        r3.AA9(new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$tm1NhgFO_6pnNpFKxjj60WblI1M2 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                FbMessengerVrApi.this.lambda$logoutAndDeleteData$9$FbMessengerVrApi(null);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD markThreadAsRead(long j) {
        return MutationObservableUtil.wrapMutationSingleToCompletable(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$UH2MdFTDrOVwA6lb4J6Kgh4E6og2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FbMessengerVrApi.lambda$markThreadAsRead$49(this.f$0, (C06461Zy) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD muteThread(long j, @Nullable Date date) {
        return MutationObservableUtil.wrapMutationSingleToCompletable(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j, date) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$R6XZlrsELT2V5TUZRyZrJDnw02 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Date f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FbMessengerVrApi.lambda$muteThread$43(this.f$0, this.f$1, (C06461Zy) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<Boolean> removeParticipantFromGroup(long j, long j2) {
        return MutationObservableUtil.wrapMutationSingle(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j, j2) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$3o_8hjcrupoEeM_TO_IyDd4l7442 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ long f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC13251zE.A00(new AbstractC06371Zh(this.f$0, this.f$1) {
                    /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$Yf_54XPZPOGkHQBdUXkYJ66DqMU2 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r7) {
                        FbMessengerVrApi.lambda$removeParticipantFromGroup$39(C06461Zy.this, this.f$1, this.f$2, r7);
                    }
                });
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<String> sendMessage(long j, String str) {
        return MutationObservableUtil.wrapMutationSingle(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j, str) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$kQynsY0rNvVox4Onnayi3s6Emw2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC13251zE.A00(new AbstractC06371Zh(this.f$0, this.f$1) {
                    /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$kyBD2nhYdu0nyfOC97S696UKBAo2 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r5) {
                        FbMessengerVrApi.lambda$sendMessage$28(C06461Zy.this, this.f$1, this.f$2, r5);
                    }
                });
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13251zE<String> sendStickerMessage(long j, long j2) {
        return MutationObservableUtil.wrapMutationSingle(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j, j2) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$0W4cKl3Nj2q6IBz9Ut1tcF_Qj42 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ long f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC13251zE.A00(new AbstractC06371Zh(this.f$0, this.f$1) {
                    /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$2ERpuLLqSMqJOGlSSLmzSxfP02 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                    }

                    @Override // X.AbstractC06371Zh
                    public final void subscribe(AbstractC10551og r7) {
                        FbMessengerVrApi.lambda$sendStickerMessage$31(C06461Zy.this, this.f$1, this.f$2, r7);
                    }
                });
            }
        });
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public void startNetworking() {
        this.mCompositeDisposable.A1D(this.mMqttClientCallbacksSingleSubject.A05($$Lambda$FbMessengerVrApi$KXnVUODahBwEIwW3LmqOyzJe1vs2.INSTANCE, C137220e.A06));
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public void stopNetworking() {
        this.mCompositeDisposable.A1D(this.mMqttClientCallbacksSingleSubject.A05($$Lambda$FbMessengerVrApi$xs1x7ipcgc9bISl4z0cUIms_Ixo2.INSTANCE, C137220e.A06));
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC13241zD unmuteThread(long j) {
        return MutationObservableUtil.wrapMutationSingleToCompletable(this.mMailboxCoreSingle, this.mCompositeDisposable, new Function(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$N5jjnXNwxzTVjBa26GlBSPsieok2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FbMessengerVrApi.lambda$unmuteThread$46(this.f$0, (C06461Zy) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$createGroupThreadWithInitialMessage$35(List list, AnonymousClass269 r16, C06461Zy r17, String str, AbstractC10551og r19, Number number) {
        long longValue = number.longValue();
        MailboxMessengerVr$LoadThreadViewDataOptions mailboxMessengerVr$LoadThreadViewDataOptions = new MailboxMessengerVr$LoadThreadViewDataOptions();
        $$Lambda$FbMessengerVrApi$3ynyTK3EqDvzNa4GT85toNwSr3g2 r10 = new AnonymousClass1YZ(longValue, r17, str, r19) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$3ynyTK3EqDvzNa4GT85toNwSr3g2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ C06461Zy f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ AbstractC10551og f$3;

            {
                this.f$0 = r1;
                this.f$1 = r3;
                this.f$2 = r4;
                this.f$3 = r5;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                FbMessengerVrApi.lambda$createGroupThreadWithInitialMessage$34(this.f$0, this.f$1, this.f$2, this.f$3, (AnonymousClass26Z) obj);
            }
        };
        AnonymousClass1Z6 r0 = r16.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r10);
        r0.A9T(new AnonymousClass26E(r16, r3, 0, longValue, longValue, mailboxMessengerVr$LoadThreadViewDataOptions));
        r19.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public static /* synthetic */ MessengerParticipant[] lambda$getParticipantListObservable$19(Optional optional) throws Exception {
        return (MessengerParticipant[]) ((Pair) optional.get()).second;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerVrApi
    public AbstractC136820a<MessengerParticipant[]> getParticipantListObservable(long j) {
        AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>> threadViewDataObservable = getThreadViewDataObservable(j);
        $$Lambda$mPDMI05n14f3T3SzH2VzdqTTKbc2 r1 = $$Lambda$mPDMI05n14f3T3SzH2VzdqTTKbc2.INSTANCE;
        AnonymousClass219.A01(r1, "predicate is null");
        C138920v r2 = new C138920v(threadViewDataObservable, r1);
        $$Lambda$FbMessengerVrApi$qzLfZNZECOyr5acQbSnkS23aigk2 r12 = $$Lambda$FbMessengerVrApi$qzLfZNZECOyr5acQbSnkS23aigk2.INSTANCE;
        AnonymousClass219.A01(r12, "mapper is null");
        return new C138620s(r2, r12);
    }

    public /* synthetic */ AbstractC13231zC lambda$getThreadObservable$12$FbMessengerVrApi(long j, Optional optional) throws Exception {
        if (optional.isPresent()) {
            Object obj = ((Pair) optional.get()).first;
            AnonymousClass219.A01(obj, "item is null");
            return new AnonymousClass1y7(obj);
        }
        AbstractC13231zC<MessengerThread> fetchThreadData = GraphQLUtil.fetchThreadData(this.mOkHttpClient, this.mApiConfigSingle.A06().mAccessToken, j);
        $$Lambda$FbMessengerVrApi$pD3_s70qXeJwW7lxoATaiTO4IaU2 r4 = $$Lambda$FbMessengerVrApi$pD3_s70qXeJwW7lxoATaiTO4IaU2.INSTANCE;
        AbstractC12851yS<Object> r3 = C137220e.A04;
        AnonymousClass219.A01(r4, "onSubscribe is null");
        AbstractC12881yV r6 = C137220e.A03;
        C13141yw r1 = new C13141yw(fetchThreadData, r3, r4, r3, r6, r6, r6);
        $$Lambda$FbMessengerVrApi$c7IFa_HD__o4vNcwDunzdmc1r82 r11 = $$Lambda$FbMessengerVrApi$c7IFa_HD__o4vNcwDunzdmc1r82.INSTANCE;
        AnonymousClass219.A01(r11, "onError is null");
        C13141yw r7 = new C13141yw(r1, r3, r3, r11, r6, r6, r6);
        AbstractC13191z2<Object> r12 = C137220e.A09;
        AnonymousClass219.A01(r12, "predicate is null");
        return new C13171yz(r7, r12);
    }

    public /* synthetic */ AbstractC136820a lambda$getThreadObservable$13$FbMessengerVrApi(long j, Long l) {
        return (AbstractC136820a) new AnonymousClass20Q(getThreadViewDataObservable(j), new AbstractC13031yl(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$ePPWeo8HWcp5vQ2xRK4OTfyBU2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return FbMessengerVrApi.this.lambda$getThreadObservable$12$FbMessengerVrApi(this.f$1, (Optional) obj);
            }
        }).A0J($$Lambda$cbyaTxMwzdgOkV9NrGlCVbjYc2.INSTANCE);
    }

    public /* synthetic */ void lambda$new$2$FbMessengerVrApi(Boolean bool) {
        this.mIsMsysDatabaseNewlyCreated.set(bool.booleanValue());
    }

    public FbMessengerVrApi(OkHttpClient okHttpClient, AbstractC13251zE<ApiConfig> r5, AnonymousClass1Kw r6, Analytics analytics, @Nullable Integer num, @Nullable String str) {
        this.mCompositeDisposable = new AnonymousClass1xU();
        this.mMqttClientCallbacksSingleSubject = new C12701yB<>();
        this.mIsMsysDatabaseNewlyCreated = new AtomicBoolean(false);
        this.mThreadViewDataObservablesCache = Collections.synchronizedMap(new HashMap());
        this.mThreadObservablesCache = Collections.synchronizedMap(new HashMap());
        this.mThreadPictureUrlsCache = new HashMap();
        this.mTotalLocalThreadsCount = new AtomicInteger(10);
        if (num != null) {
            AnonymousClass0MD.A00(num.intValue());
        }
        this.mOkHttpClient = okHttpClient;
        C13261zF r2 = new C13261zF(r5);
        this.mApiConfigSingle = r2;
        C13261zF r1 = new C13261zF(new C13401zX(r2, new AbstractC13031yl(r6, str, analytics) {
            /* class com.oculus.messengervr.fb.$$Lambda$FbMessengerVrApi$hm4x11LEPAd6KJ8kNgJnhqBUE7g2 */
            public final /* synthetic */ AnonymousClass1Kw f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ Analytics f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return FbMessengerVrApi.this.lambda$new$3$FbMessengerVrApi(this.f$1, this.f$2, this.f$3, (ApiConfig) obj);
            }
        }));
        this.mMailboxSingle = r1;
        this.mMailboxCoreSingle = new C13261zF(r1.A04($$Lambda$FbMessengerVrApi$GiFFQdPcR6VBGZx3uUJ15fg6zx42.INSTANCE));
        this.mMailboxMessengerVrSingle = new C13261zF(this.mMailboxSingle.A04($$Lambda$FbMessengerVrApi$bLjqkCoaS3nelJl70w67x6NNpYQ2.INSTANCE));
        this.mApiReadyCompletable = new C13621zt(new C13641zv(this.mMailboxSingle));
        this.mThreadListStoredProcedureChangesObservable = (AbstractC136820a) ThreadListObservableUtil.createThreadListStoredProcedureChangesObservable(this.mMailboxSingle, this.mIsMsysDatabaseNewlyCreated).A0J($$Lambda$1nAnCmKYRzugDmDnf2XPfgr86k2.INSTANCE);
    }

    @Deprecated
    public FbMessengerVrApi(OkHttpClient okHttpClient, AbstractC13251zE<ApiConfig> r9, @Nullable Integer num, @Nullable String str) {
        this(okHttpClient, r9, $$Lambda$FbMessengerVrApi$0s9Xk3bTfeMFyNaeKMYQ6ClU2.INSTANCE, new VrMsysAnalytics(), num, str);
    }
}
