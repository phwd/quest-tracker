package com.oculus.partystatemanager;

import android.os.Bundle;

public interface IPartiesNativeModuleManager {
    boolean callNativeGetIsUsingMicrophone();

    void callNativeGetSystemVoipData(boolean z, Bundle bundle);

    void callNativePartyChatStart(long j);

    void callNativePartyChatStop();

    void callNativeSetIsUsingMicrophone(boolean z);

    void callNativeSetSystemVoipDisabled(boolean z, String str);

    void callNativeSetSystemVoipPassthrough(boolean z);

    void callNativeStartOrResume(long j);

    long getCurrentPartyId();

    boolean getHasParticipantRecentlySpoken(long j);

    long[] getMutedUsers();

    float getPartyChatVolumeX();

    String getSystemVoipMicrophoneMutedX();

    String getSystemVoipStatusX();

    void maybeShowMicrophoneMutedToast();

    void maybeShowPartyVoipConnectedToast(boolean z);

    void setPartyChatVolumeX(float f);

    int setPerPersonMuteAction(long j, int i);

    String setSystemVoipMicrophoneMutedX(int i);

    void showPartyChatResume();

    void showPartyChatSuspend();
}
