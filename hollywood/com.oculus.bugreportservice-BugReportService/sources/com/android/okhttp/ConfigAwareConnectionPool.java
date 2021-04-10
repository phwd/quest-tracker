package com.android.okhttp;

import libcore.net.event.NetworkEventDispatcher;
import libcore.net.event.NetworkEventListener;

public class ConfigAwareConnectionPool {
    private static final long CONNECTION_POOL_KEEP_ALIVE_DURATION_MS;
    private static final int CONNECTION_POOL_MAX_IDLE_CONNECTIONS;
    private static final ConfigAwareConnectionPool instance = new ConfigAwareConnectionPool();
    private ConnectionPool connectionPool;
    private final NetworkEventDispatcher networkEventDispatcher = NetworkEventDispatcher.getInstance();
    private boolean networkEventListenerRegistered;

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        CONNECTION_POOL_KEEP_ALIVE_DURATION_MS = property2 != null ? Long.parseLong(property2) : 300000;
        if (property != null && !Boolean.parseBoolean(property)) {
            CONNECTION_POOL_MAX_IDLE_CONNECTIONS = 0;
        } else if (property3 != null) {
            CONNECTION_POOL_MAX_IDLE_CONNECTIONS = Integer.parseInt(property3);
        } else {
            CONNECTION_POOL_MAX_IDLE_CONNECTIONS = 5;
        }
    }

    private ConfigAwareConnectionPool() {
    }

    public static ConfigAwareConnectionPool getInstance() {
        return instance;
    }

    public synchronized ConnectionPool get() {
        if (this.connectionPool == null) {
            if (!this.networkEventListenerRegistered) {
                this.networkEventDispatcher.addListener(new NetworkEventListener() {
                    /* class com.android.okhttp.ConfigAwareConnectionPool.AnonymousClass1 */
                });
                this.networkEventListenerRegistered = true;
            }
            this.connectionPool = new ConnectionPool(CONNECTION_POOL_MAX_IDLE_CONNECTIONS, CONNECTION_POOL_KEEP_ALIVE_DURATION_MS);
        }
        return this.connectionPool;
    }
}
