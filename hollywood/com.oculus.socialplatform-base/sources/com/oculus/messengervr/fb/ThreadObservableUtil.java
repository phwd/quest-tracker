package com.oculus.messengervr.fb;

import X.AbstractC06511aN;
import X.AbstractC13031yl;
import X.AbstractC13251zE;
import X.AbstractC136820a;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZX;
import X.AnonymousClass1Zb;
import X.AnonymousClass1vb;
import X.AnonymousClass1vc;
import X.AnonymousClass269;
import X.AnonymousClass26O;
import X.AnonymousClass26Q;
import X.AnonymousClass26R;
import X.AnonymousClass28B;
import X.AnonymousClass294;
import X.AnonymousClass299;
import X.C13391zW;
import X.C149126j;
import android.annotation.TargetApi;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.core.mca.MailboxCore$MessageLoadMoreState;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.mca.MailboxMessengerVr$ThreadViewDataObserverCallback;
import com.facebook.messengervrcqljava.TempMessageList;
import com.oculus.messengervr.fb.ThreadObservableUtil;
import com.oculus.messengervr.fbshared.models.FBMessengerParticipant;
import com.oculus.messengervr.fbshared.models.FBMessengerThread;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ThreadObservableUtil {
    public static void patchThreadPictureUrls(AnonymousClass294 r7, String str, Consumer<Pair<String, String>> consumer) {
        String[] strArr = new String[2];
        int i = 0;
        for (int i2 = 0; i2 < r7.mResultSet.getCount() && i < 2; i2++) {
            if (!String.valueOf(r7.mResultSet.getLong(i2, 0)).equals(str) && r7.mResultSet.getString(i2, 5) != null) {
                strArr[i] = r7.mResultSet.getString(i2, 5);
                i++;
            }
        }
        consumer.accept(new Pair<>(strArr[0], strArr[1]));
    }

    public static AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>> createThreadViewDataObservable(AbstractC13251zE<ApiConfig> r1, AbstractC13251zE<AnonymousClass269> r2, long j) {
        return new C13391zW(AbstractC13251zE.A01(r1, r2, $$Lambda$dSWr9czCoRrHIlUqtjtPo_z82uA2.INSTANCE), new AbstractC13031yl(j) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$wko5uA_RWodTBqK76iICWNfjTw2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Pair pair;
                return AbstractC136820a.A01(new AnonymousClass1vc(this.f$0, (AnonymousClass269) pair.second, (ApiConfig) ((Pair) obj).first) {
                    /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$VcVceluOnsz3dP1k9718g0W6UKE2 */
                    public final /* synthetic */ long f$0;
                    public final /* synthetic */ AnonymousClass269 f$1;
                    public final /* synthetic */ ApiConfig f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r3;
                        this.f$2 = r4;
                    }

                    @Override // X.AnonymousClass1vc
                    public final void subscribe(AnonymousClass1vb r5) {
                        ThreadObservableUtil.lambda$createThreadViewDataObservable$2(this.f$0, this.f$1, this.f$2, r5);
                    }
                });
            }
        });
    }

    public static MailboxMessengerVr$ThreadViewDataObserverCallback createThreadViewDataObserverCallback(final String str, AnonymousClass269 r2, final long j, final Consumer<Optional<Pair<MessengerThread, MessengerParticipant[]>>> consumer) {
        return new MailboxMessengerVr$ThreadViewDataObserverCallback() {
            /* class com.oculus.messengervr.fb.ThreadObservableUtil.AnonymousClass1 */

            public static /* synthetic */ void lambda$callback$0(AnonymousClass299 r4, Consumer consumer, MessengerParticipant[] messengerParticipantArr, Pair pair) {
                FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
                builder.mThreadKey = Long.valueOf(r4.mResultSet.getLong(0, 0));
                builder.mThreadName = r4.mResultSet.getString(0, 23);
                builder.mLastActivityTimestampMs = Long.valueOf(r4.mResultSet.getLong(0, 8));
                builder.mParticipantProfilePictureUrlsForThreadPicture = pair;
                builder.mIsUnread = Boolean.valueOf(r4.mResultSet.getBoolean(0, 10));
                builder.mLastReadWatermarkTimestampMs = Long.valueOf(r4.mResultSet.getLong(0, 9));
                consumer.accept(Optional.of(new Pair(builder.build(), messengerParticipantArr)));
            }

            @Override // com.facebook.messengervr.mca.MailboxMessengerVr$ThreadViewDataObserverCallback
            public void callback(AnonymousClass299 r6, AnonymousClass294 r7, @Nullable TempMessageList tempMessageList, @MailboxCore$MessageLoadMoreState int i, @MailboxCore$MessageLoadMoreState int i2) {
                Consumer consumer;
                Optional of;
                if (r6.mResultSet.getCount() == 0) {
                    consumer = r0;
                    of = Optional.empty();
                } else {
                    r6.mResultSet.getLong(0, 0);
                    r6.mResultSet.getNullableLong(0, 2);
                    MessengerParticipant[] transformToMessengerParticipant = ThreadObservableUtil.transformToMessengerParticipant(r7);
                    if (r6.mResultSet.getString(0, 5) != null) {
                        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
                        builder.mThreadKey = Long.valueOf(r6.mResultSet.getLong(0, 0));
                        builder.mThreadName = r6.mResultSet.getString(0, 23);
                        builder.mLastActivityTimestampMs = Long.valueOf(r6.mResultSet.getLong(0, 8));
                        builder.mThreadPictureUrl = r6.mResultSet.getString(0, 5);
                        builder.mIsUnread = Boolean.valueOf(r6.mResultSet.getBoolean(0, 10));
                        builder.mLastReadWatermarkTimestampMs = Long.valueOf(r6.mResultSet.getLong(0, 9));
                        FBMessengerThread build = builder.build();
                        consumer = r0;
                        of = Optional.of(new Pair(build, transformToMessengerParticipant));
                    } else {
                        ThreadObservableUtil.patchThreadPictureUrls(r7, r1, new Consumer(r0, transformToMessengerParticipant) {
                            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$1$9BIEEy3rfVpWvNdCUcgqjH4TOP02 */
                            public final /* synthetic */ Consumer f$1;
                            public final /* synthetic */ MessengerParticipant[] f$2;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                            }

                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ThreadObservableUtil.AnonymousClass1.lambda$callback$0(AnonymousClass299.this, this.f$1, this.f$2, (Pair) obj);
                            }
                        });
                        return;
                    }
                }
                consumer.accept(of);
            }
        };
    }

    public static /* synthetic */ void lambda$createThreadViewDataObservable$2(final long j, AnonymousClass269 r12, ApiConfig apiConfig, AnonymousClass1vb r14) throws Exception {
        AtomicReference atomicReference = new AtomicReference();
        final String str = apiConfig.mUserId;
        final $$Lambda$Z6VziGNc4pd46_HGyym7UVVx_qg2 r0 = new Consumer() {
            /* class com.oculus.messengervr.fb.$$Lambda$Z6VziGNc4pd46_HGyym7UVVx_qg2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.onNext(obj);
            }
        };
        AnonymousClass1 r9 = new MailboxMessengerVr$ThreadViewDataObserverCallback() {
            /* class com.oculus.messengervr.fb.ThreadObservableUtil.AnonymousClass1 */

            public static /* synthetic */ void lambda$callback$0(AnonymousClass299 r4, Consumer consumer, MessengerParticipant[] messengerParticipantArr, Pair pair) {
                FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
                builder.mThreadKey = Long.valueOf(r4.mResultSet.getLong(0, 0));
                builder.mThreadName = r4.mResultSet.getString(0, 23);
                builder.mLastActivityTimestampMs = Long.valueOf(r4.mResultSet.getLong(0, 8));
                builder.mParticipantProfilePictureUrlsForThreadPicture = pair;
                builder.mIsUnread = Boolean.valueOf(r4.mResultSet.getBoolean(0, 10));
                builder.mLastReadWatermarkTimestampMs = Long.valueOf(r4.mResultSet.getLong(0, 9));
                consumer.accept(Optional.of(new Pair(builder.build(), messengerParticipantArr)));
            }

            @Override // com.facebook.messengervr.mca.MailboxMessengerVr$ThreadViewDataObserverCallback
            public void callback(AnonymousClass299 r6, AnonymousClass294 r7, @Nullable TempMessageList tempMessageList, @MailboxCore$MessageLoadMoreState int i, @MailboxCore$MessageLoadMoreState int i2) {
                Consumer consumer;
                Optional of;
                if (r6.mResultSet.getCount() == 0) {
                    consumer = r0;
                    of = Optional.empty();
                } else {
                    r6.mResultSet.getLong(0, 0);
                    r6.mResultSet.getNullableLong(0, 2);
                    MessengerParticipant[] transformToMessengerParticipant = ThreadObservableUtil.transformToMessengerParticipant(r7);
                    if (r6.mResultSet.getString(0, 5) != null) {
                        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
                        builder.mThreadKey = Long.valueOf(r6.mResultSet.getLong(0, 0));
                        builder.mThreadName = r6.mResultSet.getString(0, 23);
                        builder.mLastActivityTimestampMs = Long.valueOf(r6.mResultSet.getLong(0, 8));
                        builder.mThreadPictureUrl = r6.mResultSet.getString(0, 5);
                        builder.mIsUnread = Boolean.valueOf(r6.mResultSet.getBoolean(0, 10));
                        builder.mLastReadWatermarkTimestampMs = Long.valueOf(r6.mResultSet.getLong(0, 9));
                        FBMessengerThread build = builder.build();
                        consumer = r0;
                        of = Optional.of(new Pair(build, transformToMessengerParticipant));
                    } else {
                        ThreadObservableUtil.patchThreadPictureUrls(r7, str, new Consumer(r0, transformToMessengerParticipant) {
                            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$1$9BIEEy3rfVpWvNdCUcgqjH4TOP02 */
                            public final /* synthetic */ Consumer f$1;
                            public final /* synthetic */ MessengerParticipant[] f$2;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                            }

                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ThreadObservableUtil.AnonymousClass1.lambda$callback$0(AnonymousClass299.this, this.f$1, this.f$2, (Pair) obj);
                            }
                        });
                        return;
                    }
                }
                consumer.accept(of);
            }
        };
        AnonymousClass1Z6 r02 = r12.A00;
        AnonymousClass1Zb r10 = new AnonymousClass1Zb(r02);
        r02.A9T(new AnonymousClass26Q(r12, j, j, r9, r10));
        r10.AA9(new AnonymousClass1YZ(j, atomicReference) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$7447fqb7E5VhDQpSuE8zRGXjGbI2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ AtomicReference f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                this.f$1.set(obj);
            }
        });
        r14.A9i(new AbstractC06511aN(atomicReference, j) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$c1EeK9qVzEQZbR6OfzHj1DYZNg2 */
            public final /* synthetic */ AtomicReference f$1;
            public final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC06511aN
            public final void cancel() {
                ThreadObservableUtil.lambda$createThreadViewDataObservable$1(AnonymousClass1ZX.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$loadAndPatchThreadPictureUrls$4(long j, String str, Consumer consumer, C149126j r4) {
        T t = r4.A00;
        if (t != null) {
            patchThreadPictureUrls(t, str, consumer);
        } else {
            consumer.accept(new Pair(null, null));
        }
    }

    public static void loadAndPatchThreadPictureUrls(AnonymousClass269 r3, long j, String str, Consumer<Pair<String, String>> consumer) {
        $$Lambda$ThreadObservableUtil$sk6p0R6Ks2QBFcZav2ehY46CfY2 r0 = new AnonymousClass1YZ(j, str, consumer) {
            /* class com.oculus.messengervr.fb.$$Lambda$ThreadObservableUtil$sk6p0R6Ks2QBFcZav2ehY46CfY2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ Consumer f$2;

            {
                this.f$0 = r1;
                this.f$1 = r3;
                this.f$2 = r4;
            }

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                ThreadObservableUtil.lambda$loadAndPatchThreadPictureUrls$4(this.f$0, this.f$1, this.f$2, (C149126j) obj);
            }
        };
        AnonymousClass1Z6 r2 = r3.A00;
        AnonymousClass1Zb r1 = new AnonymousClass1Zb(r2);
        r1.A02(r0);
        r2.A9T(new AnonymousClass26O(r3, r1, j));
    }

    public static MessengerParticipant[] transformToMessengerParticipant(AnonymousClass294 r5) {
        FBMessengerParticipant[] fBMessengerParticipantArr = new FBMessengerParticipant[r5.mResultSet.getCount()];
        for (int i = 0; i < r5.mResultSet.getCount(); i++) {
            FBMessengerParticipant.Builder builder = new FBMessengerParticipant.Builder();
            builder.mParticipantId = r5.mResultSet.getLong(i, 0);
            builder.mName = r5.mResultSet.getString(i, 2);
            builder.mNickname = r5.mResultSet.getString(i, 3);
            builder.mProfilePictureUrl = r5.mResultSet.getString(i, 5);
            builder.setBlockedByViewerStatus(r5.mResultSet.getNullableInteger(i, 4));
            fBMessengerParticipantArr[i] = builder.build();
        }
        return fBMessengerParticipantArr;
    }

    public static /* synthetic */ void lambda$createThreadViewDataObservable$1(AnonymousClass1ZX r1, AtomicReference atomicReference, long j) throws Exception {
        r1.cancel();
        AnonymousClass28B r4 = (AnonymousClass28B) atomicReference.get();
        if (r4 != null) {
            AnonymousClass269 r3 = r4.A00;
            AnonymousClass1Z6 r2 = r3.A00;
            r2.A9T(new AnonymousClass26R(r3, r4, new AnonymousClass1Zb(r2)));
        }
    }
}
