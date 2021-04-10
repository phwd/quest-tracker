package com.facebook.gk.store;

public interface GatekeeperStoreLogger {
    void afterLoad();

    void afterSave();

    void beforeLoad();

    void beforeSave();
}
