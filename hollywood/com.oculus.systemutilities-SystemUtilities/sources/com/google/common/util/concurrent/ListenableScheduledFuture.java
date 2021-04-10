package com.google.common.util.concurrent;

import java.util.concurrent.ScheduledFuture;

public interface ListenableScheduledFuture<V> extends ListenableFuture<V>, ScheduledFuture<V> {
}
