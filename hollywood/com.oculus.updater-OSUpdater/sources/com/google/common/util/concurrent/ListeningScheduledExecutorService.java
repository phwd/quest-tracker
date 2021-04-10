package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.ScheduledExecutorService;

@Beta
@GwtIncompatible
public interface ListeningScheduledExecutorService extends ListeningExecutorService, ScheduledExecutorService {
}
