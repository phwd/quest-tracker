package com.oculus.messengervr.fb;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC12981yg;
import X.AbstractC13031yl;
import X.AbstractC13251zE;
import X.AbstractC136820a;
import X.AnonymousClass1vb;
import X.AnonymousClass1vc;
import X.AnonymousClass1zJ;
import X.AnonymousClass20H;
import X.AnonymousClass20N;
import X.AnonymousClass219;
import X.AnonymousClass269;
import X.AnonymousClass26Z;
import X.AnonymousClass295;
import X.AnonymousClass296;
import X.C06461Zy;
import X.C13221zB;
import X.C13391zW;
import X.C13641zv;
import X.C137220e;
import X.C137720j;
import android.annotation.TargetApi;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.mca.MailboxMessengerVr$LoadThreadViewDataOptions;
import com.facebook.messengervr.msys.ChildResultSetUtils;
import com.facebook.messengervrcqljava.TempMessageList;
import com.facebook.msys.mci.CQLResultSet;
import com.oculus.messengervr.fb.rxmsys.RxMailboxCore;
import com.oculus.messengervr.fb.rxmsys.RxMailboxMessengerVr;
import com.oculus.messengervr.fb.utils.DebugUtil;
import com.oculus.messengervr.fbshared.models.FBMessengerMessage;
import com.oculus.messengervr.fbshared.models.FBXmaAttachment;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MessageListObservableUtil {
    public static /* synthetic */ FBMessengerMessage[] lambda$transformToMessengerMessage$5(ArrayList arrayList) throws Exception {
        return (FBMessengerMessage[]) arrayList.toArray(new FBMessengerMessage[0]);
    }

    public static AbstractC136820a<Pair<MessengerMessage[], Integer>> createMessageListObservable(AbstractC13251zE<C06461Zy> r2, AbstractC13251zE<AnonymousClass269> r3, AbstractC136820a<MessengerThread> r4, AbstractC136820a<Integer> r5) {
        return AbstractC136820a.A02(r2.A04($$Lambda$qrCu4vp17pB0p1AIlqjMiSyYpVU2.INSTANCE).A02(), r3.A04($$Lambda$G5VKWx1DBpa8sf3NImxOujJON02.INSTANCE).A02(), r4, r5, $$Lambda$MY_0ChY2lorKxhPZduCYSmi3GY2.INSTANCE).A0E($$Lambda$MessageListObservableUtil$PbEMrtRnUBesed4oDvwdOAx8SL82.INSTANCE);
    }

    public static AbstractC13251zE<FBMessengerMessage> createMessengerMessage(TempMessageList tempMessageList, int i) {
        FBMessengerMessage.Builder builder = new FBMessengerMessage.Builder();
        builder.mMessageId = tempMessageList.mResultSet.getString(i, 15);
        builder.mText = tempMessageList.mResultSet.getString(i, 54);
        builder.mSenderId = Long.valueOf(tempMessageList.mResultSet.getLong(i, 52));
        builder.mSenderName = tempMessageList.mResultSet.getString(i, 24);
        builder.mSenderProfilePictureUrl = tempMessageList.mResultSet.getString(i, 28);
        builder.mTimestampMs = Long.valueOf(tempMessageList.mResultSet.getLong(i, 58));
        builder.mIsAdminMessage = Boolean.valueOf(tempMessageList.mResultSet.getBoolean(i, 9));
        builder.mDisplayedContentTypes = Long.valueOf(tempMessageList.mResultSet.getLong(i, 77));
        builder.mHotEmojiSize = tempMessageList.mResultSet.getNullableInteger(i, 8);
        CQLResultSet reactionListFromTempMessageListNative = ChildResultSetUtils.getReactionListFromTempMessageListNative(tempMessageList, i);
        if (reactionListFromTempMessageListNative != null) {
            AnonymousClass295 r4 = new AnonymousClass295(reactionListFromTempMessageListNative);
            MessengerReaction[] messengerReactionArr = new MessengerReaction[r4.mResultSet.getCount()];
            for (int i2 = 0; i2 < r4.mResultSet.getCount(); i2++) {
                MessengerReaction.Builder builder2 = new MessengerReaction.Builder();
                builder2.mActorId = Long.valueOf(r4.mResultSet.getLong(i2, 0));
                builder2.mActorProfilePictureUrl = r4.mResultSet.getString(i2, 1);
                builder2.mActorName = r4.mResultSet.getString(i2, 2);
                builder2.mMessageId = r4.mResultSet.getString(i2, 3);
                builder2.mReaction = r4.mResultSet.getString(i2, 4);
                builder2.mTimestampMs = Long.valueOf(r4.mResultSet.getLong(i2, 5));
                messengerReactionArr[i2] = builder2.build();
            }
            builder.mReactions = messengerReactionArr;
        }
        return setAttachment(tempMessageList, i, builder).A04($$Lambda$t6mzxmR6T59eQX4D2u3fXtK_n9A2.INSTANCE);
    }

    public static /* synthetic */ void lambda$createMessagesObservable$1(AnonymousClass1vb r2, int i, FBMessengerMessage[] fBMessengerMessageArr) throws Exception {
        DebugUtil.logMessageList("VR_MESSENGER_API", fBMessengerMessageArr);
        r2.onNext(new Pair(fBMessengerMessageArr, Integer.valueOf(i)));
    }

    public static /* synthetic */ void lambda$createMessagesObservable$3(AnonymousClass26Z r8, int i, RxMailboxCore rxMailboxCore, MessengerThread messengerThread, AnonymousClass1vb r12) throws Exception {
        int i2;
        AbstractC12271xB A02;
        TempMessageList tempMessageList = (TempMessageList) Objects.requireNonNull(r8.A01, "messageList should not be null");
        int count = tempMessageList.mResultSet.getCount();
        Number number = r8.A04;
        if (number != null) {
            i2 = number.intValue();
        } else {
            i2 = -1;
        }
        Number number2 = r8.A03;
        if (number2 != null) {
            number2.intValue();
        }
        C13641zv r3 = new C13641zv(new AnonymousClass1zJ(transformToMessengerMessage(tempMessageList), new AbstractC12851yS(i) {
            /* class com.oculus.messengervr.fb.$$Lambda$MessageListObservableUtil$edTUI5fQ_2nE92QBcdyudJTGojA2 */
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MessageListObservableUtil.lambda$createMessagesObservable$1(AnonymousClass1vb.this, this.f$1, (FBMessengerMessage[]) obj);
            }
        }));
        if (i2 == 4 || i2 == 3 || count >= i) {
            A02 = r3.A02(new AbstractC12881yV() {
                /* class com.oculus.messengervr.fb.$$Lambda$cNfhxNuUxARS4fAumC4Dz3GJOcQ2 */

                @Override // X.AbstractC12881yV
                public final void run() {
                    AnonymousClass1vb.this.onComplete();
                }
            }, new AbstractC12851yS() {
                /* class com.oculus.messengervr.fb.$$Lambda$VmLVXQu1wNscSYZdRZXpqdZdNg2 */

                @Override // X.AbstractC12851yS
                public final void accept(Object obj) {
                    AnonymousClass1vb.this.onError((Throwable) obj);
                }
            });
        } else {
            AbstractC13251zE<Boolean> fetchMessagesPage = rxMailboxCore.fetchMessagesPage(Long.valueOf(messengerThread.getThreadKey()), null, -1);
            $$Lambda$MessageListObservableUtil$pQ7L0cxdBRnL6MYIhrmRv6P5Ddc2 r0 = $$Lambda$MessageListObservableUtil$pQ7L0cxdBRnL6MYIhrmRv6P5Ddc2.INSTANCE;
            AnonymousClass219.A01(r0, "onSuccess is null");
            A02 = new C13221zB(new AbstractC12981yg[]{r3, new C13641zv(new AnonymousClass1zJ(fetchMessagesPage, r0))}).A02(new AbstractC12881yV() {
                /* class com.oculus.messengervr.fb.$$Lambda$cNfhxNuUxARS4fAumC4Dz3GJOcQ2 */

                @Override // X.AbstractC12881yV
                public final void run() {
                    AnonymousClass1vb.this.onComplete();
                }
            }, new AbstractC12851yS() {
                /* class com.oculus.messengervr.fb.$$Lambda$VmLVXQu1wNscSYZdRZXpqdZdNg2 */

                @Override // X.AbstractC12851yS
                public final void accept(Object obj) {
                    AnonymousClass1vb.this.onError((Throwable) obj);
                }
            });
        }
        r12.A9q(A02);
    }

    public static AbstractC13251zE<FBMessengerMessage.Builder> setAttachment(TempMessageList tempMessageList, int i, FBMessengerMessage.Builder builder) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(i, builder) {
            /* class com.oculus.messengervr.fb.$$Lambda$MessageListObservableUtil$we_8v5ptIy5L12WcgmN_DkOnFgo2 */
            public final /* synthetic */ int f$1;
            public final /* synthetic */ FBMessengerMessage.Builder f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r4) {
                MessageListObservableUtil.lambda$setAttachment$6(TempMessageList.this, this.f$1, this.f$2, r4);
            }
        });
    }

    public static AbstractC13251zE<FBMessengerMessage[]> transformToMessengerMessage(TempMessageList tempMessageList) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < tempMessageList.mResultSet.getCount(); i++) {
            arrayList.add(createMessengerMessage(tempMessageList, i).A02());
        }
        C137720j r3 = new C137720j(arrayList);
        AbstractC13031yl<Object, Object> r1 = C137220e.A08;
        AnonymousClass219.A01(r1, "mapper is null");
        return new AnonymousClass20N(r3, r1, AnonymousClass20H.BOUNDARY).A0H(new ArrayList(), $$Lambda$swZqavy4WV2JMOg93K55lrHIFTs2.INSTANCE).A04($$Lambda$MessageListObservableUtil$0IMTnft2cZx7dmqdbm2HN84x3pk2.INSTANCE);
    }

    public static AbstractC136820a<Pair<MessengerMessage[], Integer>> createMessagesObservable(RxMailboxCore rxMailboxCore, RxMailboxMessengerVr rxMailboxMessengerVr, MessengerThread messengerThread, int i) {
        messengerThread.getThreadKey();
        MailboxMessengerVr$LoadThreadViewDataOptions mailboxMessengerVr$LoadThreadViewDataOptions = new MailboxMessengerVr$LoadThreadViewDataOptions();
        mailboxMessengerVr$LoadThreadViewDataOptions.withMessageList = true;
        mailboxMessengerVr$LoadThreadViewDataOptions.limitOlder = i;
        return new C13391zW(rxMailboxMessengerVr.loadThreadViewDataWithOptions(0, messengerThread.getThreadKey(), messengerThread.getThreadKey(), mailboxMessengerVr$LoadThreadViewDataOptions), new AbstractC13031yl(i, rxMailboxCore, messengerThread) {
            /* class com.oculus.messengervr.fb.$$Lambda$MessageListObservableUtil$JiPqosCKGOAmJ7oOxKhR6BlJXrA2 */
            public final /* synthetic */ int f$0;
            public final /* synthetic */ RxMailboxCore f$1;
            public final /* synthetic */ MessengerThread f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return AbstractC136820a.A01(new AnonymousClass1vc(this.f$0, this.f$1, this.f$2) {
                    /* class com.oculus.messengervr.fb.$$Lambda$MessageListObservableUtil$lVOTA16jJ8CnO04WKRTXmTxaK82 */
                    public final /* synthetic */ int f$1;
                    public final /* synthetic */ RxMailboxCore f$2;
                    public final /* synthetic */ MessengerThread f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // X.AnonymousClass1vc
                    public final void subscribe(AnonymousClass1vb r5) {
                        MessageListObservableUtil.lambda$createMessagesObservable$3(AnonymousClass26Z.this, this.f$1, this.f$2, this.f$3, r5);
                    }
                });
            }
        });
    }

    public static /* synthetic */ void lambda$setAttachment$6(TempMessageList tempMessageList, int i, FBMessengerMessage.Builder builder, AbstractC10551og r9) throws Exception {
        AnonymousClass296 r7;
        FBXmaAttachment.Builder builder2;
        CQLResultSet tempMessageAttachmentListFromTempMessageListNative = ChildResultSetUtils.getTempMessageAttachmentListFromTempMessageListNative(tempMessageList, i);
        if (tempMessageAttachmentListFromTempMessageListNative != null) {
            r7 = new AnonymousClass296(tempMessageAttachmentListFromTempMessageListNative);
        } else {
            r7 = null;
        }
        ArrayList arrayList = new ArrayList();
        if (r7 != null) {
            for (int i2 = 0; i2 < r7.mResultSet.getCount(); i2++) {
                builder.mAttachmentType = Integer.valueOf(r7.mResultSet.getInteger(i2, 8)).intValue();
                int integer = r7.mResultSet.getInteger(i2, 8);
                if (integer != 0) {
                    if (integer == 1) {
                        arrayList.add(r7.mResultSet.getString(i2, 22));
                        builder.mStickerPreviewWidth = ((Number) Optional.ofNullable(r7.mResultSet.getNullableInteger(i2, 27)).orElse(0)).intValue();
                        builder.mStickerPreviewHeight = ((Number) Optional.ofNullable(r7.mResultSet.getNullableInteger(i2, 28)).orElse(0)).intValue();
                    } else if (integer == 2) {
                        arrayList.add(r7.mResultSet.getString(i2, 22));
                    } else if (integer == 7) {
                        builder2 = new FBXmaAttachment.Builder();
                        builder2.mAttachmentFbid = r7.mResultSet.getString(i2, 7);
                        builder2.mTitleText = r7.mResultSet.getString(i2, 48);
                        builder2.mSubtitleText = r7.mResultSet.getString(i2, 49);
                        builder2.mFaviconUrl = r7.mResultSet.getString(i2, 52);
                        builder2.mPreviewUrl = r7.mResultSet.getString(i2, 22);
                        builder2.mPreviewHeight = r7.mResultSet.getNullableInteger(i2, 28);
                        builder2.mPreviewWidth = r7.mResultSet.getNullableInteger(i2, 27);
                        builder2.mDefaultCtaType = r7.mResultSet.getString(i2, 34);
                        builder2.mDefaultCtaActionUrl = r7.mResultSet.getString(i2, 66);
                        builder2.mDefaultCtaActionContentBlob = r7.mResultSet.getString(i2, 65);
                        builder2.mDefaultCtaTargetId = r7.mResultSet.getNullableLong(i2, 67);
                    }
                } else {
                    builder2 = new FBXmaAttachment.Builder();
                    builder2.mTitleText = r7.mResultSet.getString(i2, 48);
                    builder2.mSubtitleText = r7.mResultSet.getString(i2, 49);
                }
                builder.mXmaAttachment = builder2.build();
            }
        }
        builder.mAttachmentImageUrls = (String[]) arrayList.toArray(new String[0]);
        r9.onSuccess(builder);
    }

    public static /* synthetic */ void lambda$createMessagesObservable$2(Boolean bool) throws Exception {
    }
}
