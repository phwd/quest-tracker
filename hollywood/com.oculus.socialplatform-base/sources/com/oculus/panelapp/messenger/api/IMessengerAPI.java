package com.oculus.panelapp.messenger.api;

import androidx.annotation.Nullable;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import java.util.List;

public interface IMessengerAPI {
    void destroy();

    MessengerThread getCurrentThread();

    @Nullable
    MessengerVrApi getInternalApi();

    List<MessengerMessage> getMessages();

    List<MessengerParticipant> getThreadParticipants();

    List<MessengerThread> getThreads();

    MessengerAPIType getType();

    String getUserID();

    void initialize();

    void leaveGroupThread(MessengerThread messengerThread);

    void markThreadAsRead(MessengerThread messengerThread);

    void sendMessage(String str, long j, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2);

    void sendMessage(String str, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2);

    void setMailboxListener(MailboxListener mailboxListener);

    void setThreadListener(ThreadListener threadListener);

    void updateCurrentThread(long j, @Nullable MessengerActionCallback messengerActionCallback);

    void updateMessageCount(int i);

    void updateThreadCount(int i);
}
