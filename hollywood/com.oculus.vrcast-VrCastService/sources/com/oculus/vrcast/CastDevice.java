package com.oculus.vrcast;

public abstract class CastDevice {
    public DiscoverProtocol mDiscoverProtocol;

    public enum State {
        FOUND,
        CONNECTING_TO_PEER,
        CONNECTION_INITIATED,
        CONNECTION_SUCCESS,
        STARTING_SESSION,
        CASTING,
        DISCONNECTING,
        INVALID
    }

    public abstract String getId();

    public abstract String getModelName();

    public abstract String getName();

    public abstract State getState();

    public abstract void setState(State state);

    public boolean showInList() {
        return true;
    }

    protected CastDevice() {
    }
}
