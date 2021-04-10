package com.oculus.messengervr.interfaces;

import X.AbstractC13241zD;
import X.AbstractC13251zE;
import X.AbstractC136820a;
import com.facebook.infer.annotation.Cleanup;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Date;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MessengerVrApi {

    public enum ApiType {
        FB_MSYS,
        OC_CHATS
    }

    AbstractC13251zE<MessengerThread> createGroupThreadWithInitialMessage(List<Long> list, String str);

    AbstractC13241zD getApiReadyCompletable();

    AbstractC136820a<MessengerContact[]> getContactListObservable(AbstractC136820a<Integer> v);

    AbstractC136820a<MessengerMessage[]> getMessageListObservable(long j, AbstractC136820a<Integer> v);

    AbstractC136820a<MessengerParticipant[]> getParticipantListObservable(long j);

    AbstractC136820a<MessengerThread[]> getThreadListObservable(AbstractC136820a<Integer> v);

    AbstractC136820a<MessengerThread> getThreadObservable(long j);

    @Cleanup
    void logout();

    @Cleanup
    void logoutAndDeleteData();

    AbstractC13241zD markThreadAsRead(long j);

    AbstractC13241zD muteThread(long j, Date date);

    AbstractC13251zE<Boolean> removeParticipantFromGroup(long j, long j2);

    AbstractC13251zE<String> sendMessage(long j, String str);

    AbstractC13251zE<String> sendStickerMessage(long j, long j2);

    void startNetworking();

    void stopNetworking();

    AbstractC13241zD unmuteThread(long j);
}
