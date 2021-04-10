package com.oculus.messengervr.oc;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC12271xB;
import X.AbstractC12761yH;
import X.AbstractC12851yS;
import X.AbstractC13031yl;
import X.AbstractC13251zE;
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
import X.C138920v;
import X.EnumC139220y;
import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.manager.MessengerManagerJNI;
import com.oculus.messenger.models.Message;
import com.oculus.messengervr.common.utils.Triplet;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.oc.MessageListObservableUtil;
import com.oculus.messengervr.oc.OcMessengerManagerUpdateResult;
import com.oculus.messengervr.oc.models.OcMessengerMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MessageListObservableUtil {
    public static MessengerManagerJNI.GetMessagesCallback createMessageListObserverCallback(final Optional<OcUserPictureUrlsQueryHandler> optional, final Consumer<MessengerMessage[]> consumer, final Consumer<Throwable> consumer2, final Consumer<AbstractC12271xB> consumer3, long j, final Map<String, String> map) {
        return new MessengerManagerJNI.GetMessagesCallback() {
            /* class com.oculus.messengervr.oc.MessageListObservableUtil.AnonymousClass1 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetMessagesCallback
            public void onResult(@Nullable Message[] messageArr, int i, @Nullable String str) {
                String str2 = "";
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get messages: %s", str);
                    Consumer consumer = r1;
                    if (AnonymousClass006.A07("Failed to get messages: ", str) != null) {
                        str2 = str;
                    }
                    consumer.accept(new Throwable(str2));
                } else if (messageArr != null && messageArr.length > 0) {
                    OcMessengerMessage[] patchMessageListWithCachedSendersProfilePicUrls = MessageListObservableUtil.patchMessageListWithCachedSendersProfilePicUrls(messageArr, r11);
                    r3.accept(patchMessageListWithCachedSendersProfilePicUrls);
                    if (r4.isPresent()) {
                        r5.accept(MessageListObservableUtil.createMessageListWithPatchedSendersProfilePictureUrlsObservable((OcUserPictureUrlsQueryHandler) r4.get(), r11, patchMessageListWithCachedSendersProfilePicUrls).A05(new AbstractC12851yS(r3) {
                            /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$1$8ZmGMC1WJGZiwmZrxeDhMF2x8uI2 */
                            public final /* synthetic */ Consumer f$0;

                            {
                                this.f$0 = r1;
                            }

                            @Override // X.AbstractC12851yS
                            public final void accept(Object obj) {
                                MessageListObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, (ArrayList) obj);
                            }
                        }, $$Lambda$MessageListObservableUtil$1$XTORXXJ53owez2ZJF2jZUN5pg4A2.INSTANCE));
                    }
                }
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, ArrayList arrayList) throws Exception {
                arrayList.size();
                consumer.accept(arrayList.toArray(new MessengerMessage[0]));
            }
        };
    }

    public static OcMessengerMessage[] patchMessageListWithCachedSendersProfilePicUrls(Message[] messageArr, Map<String, String> map) {
        int length = messageArr.length;
        OcMessengerMessage[] ocMessengerMessageArr = new OcMessengerMessage[length];
        int i = 0;
        if (map.isEmpty()) {
            while (i < length) {
                OcMessengerMessage.Builder builder = new OcMessengerMessage.Builder();
                builder.mOcMessage = messageArr[(length - 1) - i];
                ocMessengerMessageArr[i] = builder.build();
                i++;
            }
        } else {
            while (i < length) {
                Message message = messageArr[(length - 1) - i];
                OcMessengerMessage.Builder builder2 = new OcMessengerMessage.Builder();
                builder2.mOcMessage = message;
                builder2.mPatchedSenderProfilePictureUrl = map.get(Long.toString(message.mActorId));
                ocMessengerMessageArr[i] = builder2.build();
                i++;
            }
        }
        return ocMessengerMessageArr;
    }

    public static AbstractC13251zE<ArrayList<MessengerMessage>> createMessageListWithPatchedSendersProfilePictureUrlsObservable(OcUserPictureUrlsQueryHandler ocUserPictureUrlsQueryHandler, Map<String, String> map, OcMessengerMessage[] ocMessengerMessageArr) {
        HashSet hashSet = new HashSet();
        for (OcMessengerMessage ocMessengerMessage : ocMessengerMessageArr) {
            hashSet.add(Long.toString(ocMessengerMessage.getSenderId()));
        }
        return AbstractC136820a.A05(ocMessengerMessageArr).A0C(new AbstractC13031yl(hashSet, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$0P_LiEF0251n2f2kyHBDksTBP002 */
            public final /* synthetic */ Set f$1;
            public final /* synthetic */ Map f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Map map;
                return new C13401zX(OcGraphQLQueryObservableUtil.createQueryProfilePicsSingle(OcUserPictureUrlsQueryHandler.this, this.f$1, map), new AbstractC13031yl(this.f$2) {
                    /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$C8HHGELdDRG0pi0amkmtXJEBcA2 */
                    public final /* synthetic */ Map f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // X.AbstractC13031yl
                    public final Object apply(Object obj) {
                        return MessageListObservableUtil.lambda$createMessageListWithPatchedSendersProfilePictureUrlsObservable$10(OcMessengerMessage.this, this.f$1, (Map) obj);
                    }
                }).A02();
            }
        }).A0H(new ArrayList(), $$Lambda$nm7Pke63TcF0zuK2cJAj9XSoORQ2.INSTANCE);
    }

    public static AbstractC136820a<OcMessengerManagerUpdateResult> createMessageUpdatesObservable(AbstractC136820a<OcMessengerManagerUpdateResult> r3) {
        $$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82 r1 = $$Lambda$MessageListObservableUtil$qR7FPsoPiq8CH538jvPL10SKrX82.INSTANCE;
        AnonymousClass219.A01(r1, "predicate is null");
        C138920v r2 = new C138920v(r3, r1);
        OcMessengerManagerUpdateResult.Builder builder = new OcMessengerManagerUpdateResult.Builder();
        builder.mUpdateResultType = OcMessengerManagerUpdateResult.UpdateResultType.NONE;
        return r2.A0F(builder.build());
    }

    public static /* synthetic */ Triplet lambda$createMessageListObservable$2(Optional optional, MessengerManagerJNI messengerManagerJNI, Integer num, OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
        return new Triplet(optional, messengerManagerJNI, num);
    }

    public static /* synthetic */ void lambda$createMessageListObservable$6() throws Exception {
    }

    public static AbstractC136820a<MessengerMessage[]> createMessageListObservable(AbstractC13251zE<OcApiConfig> r6, AbstractC13251zE<Optional<OcUserPictureUrlsQueryHandler>> r7, AbstractC13251zE<MessengerManagerJNI> r8, AbstractC136820a<Integer> r9, AbstractC136820a<OcMessengerManagerUpdateResult> r10, long j, Map<String, String> map) {
        AbstractC136820a<OcMessengerManagerUpdateResult> createMessageUpdatesObservable = createMessageUpdatesObservable(r10);
        AbstractC136820a<Optional<OcUserPictureUrlsQueryHandler>> A02 = r7.A02();
        AbstractC136820a<MessengerManagerJNI> A022 = r8.A02();
        AbstractC136820a<Integer> A0A = r9.A0A($$Lambda$MessageListObservableUtil$fSUeHQPG7z6HTuy0gMAMdZdM42.INSTANCE);
        AbstractC13031yl<Object, Object> r1 = C137220e.A08;
        AnonymousClass219.A01(r1, "keySelector is null");
        return new AnonymousClass21H(AbstractC136820a.A02(A02, A022, new C138320p(A0A, r1, AnonymousClass219.A00), createMessageUpdatesObservable.A0A($$Lambda$MessageListObservableUtil$BWKbb5snBT5SDnix3KtNwycssU42.INSTANCE), $$Lambda$MessageListObservableUtil$BKUhgCJEv4F2wiSCSytgsIZm5F02.INSTANCE).A0E(new AbstractC13031yl(j, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$NxOby3lpPmQoXT2Mc3lKPaL3FtA2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Map f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                Triplet triplet;
                return AbstractC136820a.A01(new AnonymousClass1vc(this.f$0, triplet.third, (Triplet) obj, this.f$1) {
                    /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$i82vJkoANKn8kgbNbovaaTX9Xk2 */
                    public final /* synthetic */ long f$1;
                    public final /* synthetic */ Integer f$2;
                    public final /* synthetic */ Triplet f$3;
                    public final /* synthetic */ Map f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                        this.f$3 = r5;
                        this.f$4 = r6;
                    }

                    @Override // X.AnonymousClass1vc
                    public final void subscribe(AnonymousClass1vb r8) {
                        MessageListObservableUtil.lambda$createMessageListObservable$3(MessengerManagerJNI.this, this.f$1, this.f$2, this.f$3, this.f$4, r8);
                    }
                });
            }
        }).A0G(TimeUnit.MILLISECONDS).A0B($$Lambda$MessageListObservableUtil$WIR9uRXvnuQOjhQ3tGPig7UCl9w2.INSTANCE, $$Lambda$MessageListObservableUtil$S5uTTW4XtAJblrlElmHnNBB1XI02.INSTANCE).A09($$Lambda$MessageListObservableUtil$fsUTSWCaWc8_fnnAkxk000CCRiU2.INSTANCE).A0I());
    }

    public static /* synthetic */ void lambda$createMessageListObservable$3(MessengerManagerJNI messengerManagerJNI, long j, Integer num, Triplet triplet, final Map map, AnonymousClass1vb r12) throws Exception {
        int intValue = num.intValue();
        final A a = triplet.first;
        final $$Lambda$tL_KRxZ5OCq4XpAnk5vGYeFW1E82 r3 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$tL_KRxZ5OCq4XpAnk5vGYeFW1E82 */

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
        final $$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 r5 = new Consumer() {
            /* class com.oculus.messengervr.oc.$$Lambda$po7ucShQdAeplaGlZ6BGEc4v1o2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AnonymousClass1vb.this.A9q((AbstractC12271xB) obj);
            }
        };
        messengerManagerJNI.getThreadMessages(j, intValue, 0, new MessengerManagerJNI.GetMessagesCallback() {
            /* class com.oculus.messengervr.oc.MessageListObservableUtil.AnonymousClass1 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetMessagesCallback
            public void onResult(@Nullable Message[] messageArr, int i, @Nullable String str) {
                String str2 = "";
                if (str != null) {
                    AnonymousClass0MD.A09(Constants.LOG_TAG, "Failed to get messages: %s", str);
                    Consumer consumer = r1;
                    if (AnonymousClass006.A07("Failed to get messages: ", str) != null) {
                        str2 = str;
                    }
                    consumer.accept(new Throwable(str2));
                } else if (messageArr != null && messageArr.length > 0) {
                    OcMessengerMessage[] patchMessageListWithCachedSendersProfilePicUrls = MessageListObservableUtil.patchMessageListWithCachedSendersProfilePicUrls(messageArr, map);
                    r3.accept(patchMessageListWithCachedSendersProfilePicUrls);
                    if (a.isPresent()) {
                        r5.accept(MessageListObservableUtil.createMessageListWithPatchedSendersProfilePictureUrlsObservable((OcUserPictureUrlsQueryHandler) a.get(), map, patchMessageListWithCachedSendersProfilePicUrls).A05(new AbstractC12851yS(r3) {
                            /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$1$8ZmGMC1WJGZiwmZrxeDhMF2x8uI2 */
                            public final /* synthetic */ Consumer f$0;

                            {
                                this.f$0 = r1;
                            }

                            @Override // X.AbstractC12851yS
                            public final void accept(Object obj) {
                                MessageListObservableUtil.AnonymousClass1.lambda$onResult$0(this.f$0, (ArrayList) obj);
                            }
                        }, $$Lambda$MessageListObservableUtil$1$XTORXXJ53owez2ZJF2jZUN5pg4A2.INSTANCE));
                    }
                }
            }

            public static /* synthetic */ void lambda$onResult$0(Consumer consumer, ArrayList arrayList) throws Exception {
                arrayList.size();
                consumer.accept(arrayList.toArray(new MessengerMessage[0]));
            }
        });
    }

    public static /* synthetic */ void lambda$createMessageListObservable$7(AnonymousClass215 r3) throws Exception {
        if (!r3.A01() && EnumC139220y.isError(r3.A00)) {
            AnonymousClass0MD.A07(Constants.LOG_TAG, "MessageListObservable throw:", r3.A00());
        }
    }

    public static /* synthetic */ AbstractC12761yH lambda$createMessageListWithPatchedSendersProfilePictureUrlsObservable$10(OcMessengerMessage ocMessengerMessage, Map map, Map map2) throws Exception {
        if (!map2.isEmpty()) {
            return AbstractC13251zE.A00(new AbstractC06371Zh(map2, map) {
                /* class com.oculus.messengervr.oc.$$Lambda$MessageListObservableUtil$FKi_U7eX_4vH_0IiVzE_07v7XMk2 */
                public final /* synthetic */ Map f$1;
                public final /* synthetic */ Map f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // X.AbstractC06371Zh
                public final void subscribe(AbstractC10551og r4) {
                    MessageListObservableUtil.lambda$createMessageListWithPatchedSendersProfilePictureUrlsObservable$9(OcMessengerMessage.this, this.f$1, this.f$2, r4);
                }
            });
        }
        AnonymousClass219.A01(ocMessengerMessage, "value is null");
        return new C12731yE(ocMessengerMessage);
    }

    public static /* synthetic */ void lambda$createMessageListWithPatchedSendersProfilePictureUrlsObservable$9(OcMessengerMessage ocMessengerMessage, Map map, Map map2, AbstractC10551og r5) throws Exception {
        String str;
        String l = Long.toString(ocMessengerMessage.getSenderId());
        if (map.containsKey(l) && (str = (String) map2.get(l)) != null && !str.isEmpty()) {
            OcMessengerMessage.Builder newBuilder = OcMessengerMessage.newBuilder(ocMessengerMessage);
            newBuilder.mPatchedSenderProfilePictureUrl = str;
            ocMessengerMessage = newBuilder.build();
        }
        r5.onSuccess(ocMessengerMessage);
    }

    public static /* synthetic */ void lambda$createMessageListObservable$0(Integer num) throws Exception {
    }

    public static /* synthetic */ void lambda$createMessageListObservable$1(OcMessengerManagerUpdateResult ocMessengerManagerUpdateResult) throws Exception {
    }

    public static /* synthetic */ void lambda$createMessageListObservable$5(AbstractC12271xB r0) throws Exception {
    }
}
