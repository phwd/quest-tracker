package com.oculus.android.exoplayer2.upstream;

import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;

public final class PriorityDataSourceFactory implements DataSource.Factory {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamFactory;

    public PriorityDataSourceFactory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager2, int i) {
        this.upstreamFactory = factory;
        this.priorityTaskManager = priorityTaskManager2;
        this.priority = i;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource.Factory
    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}
