package com.oculus.partystatemanager;

public interface IPartyServiceDelegate {
    void onUserInParty(long j);

    void onUserNotInParty();
}
