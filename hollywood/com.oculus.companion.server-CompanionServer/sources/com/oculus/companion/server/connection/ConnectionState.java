package com.oculus.companion.server.connection;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionState {
    public static final Object secureLock = new Object();
    public final AtomicBoolean isAuthenticated = new AtomicBoolean(false);
    public volatile boolean isClaimedByUser = false;
    public final AtomicBoolean secureConnection = new AtomicBoolean(false);
}
