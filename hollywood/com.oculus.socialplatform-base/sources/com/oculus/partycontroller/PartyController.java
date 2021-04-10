package com.oculus.partycontroller;

import android.os.Bundle;
import com.facebook.inject.RequiresBinding;
import com.oculus.platforminitexception.PlatformInitException;
import java.util.Set;

@RequiresBinding
public interface PartyController {
    long getCurrentPartyId();

    boolean getHasParticipantRecentlySpoken(long j);

    Set<String> getMutedUsersAsSet();

    float getPartyChatVolumeX() throws PlatformInitException;

    boolean getPartyIsUsingMicrophone();

    String getPartyMicrophoneState();

    String getSystemVoipStateX() throws PlatformInitException, IllegalArgumentException;

    void setPartyChatVolumeX(float f) throws PlatformInitException;

    void setPartyIsUsingMicrophone(boolean z);

    void setPartyMicrophoneState(String str);

    int setPerPersonMuteAction(long j, int i);

    String setSystemVoipMicrophoneMutedX(int i);

    Bundle startPartyChat(long j);

    Bundle stopPartyChat();
}
