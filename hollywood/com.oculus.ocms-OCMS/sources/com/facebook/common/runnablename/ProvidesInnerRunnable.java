package com.facebook.common.runnablename;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ProvidesInnerRunnable {
    Object getInnerRunnable();
}
