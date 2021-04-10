package com.facebook.datasource;

import javax.annotation.Nonnull;

public abstract class BaseDataSubscriber<T> implements DataSubscriber<T> {
    @Override // com.facebook.datasource.DataSubscriber
    public void onCancellation(@Nonnull DataSource<T> dataSource) {
    }

    /* access modifiers changed from: protected */
    public abstract void onFailureImpl(@Nonnull DataSource<T> dataSource);

    /* access modifiers changed from: protected */
    public abstract void onNewResultImpl(@Nonnull DataSource<T> dataSource);

    @Override // com.facebook.datasource.DataSubscriber
    public void onProgressUpdate(@Nonnull DataSource<T> dataSource) {
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onNewResult(@Nonnull DataSource<T> dataSource) {
        boolean isFinished = dataSource.isFinished();
        try {
            onNewResultImpl(dataSource);
        } finally {
            if (isFinished) {
                dataSource.close();
            }
        }
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onFailure(@Nonnull DataSource<T> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }
}
