package com.facebook.common.runnablename;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ProvidesRunnableName extends ProvidesInnerRunnable {
    String getRunnableName();
}
