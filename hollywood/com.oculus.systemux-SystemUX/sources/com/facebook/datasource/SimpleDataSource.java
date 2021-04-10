package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;
import java.util.Map;

public class SimpleDataSource<T> extends AbstractDataSource<T> {
    private SimpleDataSource() {
    }

    public static <T> SimpleDataSource<T> create() {
        return new SimpleDataSource<>();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.facebook.datasource.SimpleDataSource<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setResult(T t, boolean z, Map<String, Object> map) {
        return super.setResult(Preconditions.checkNotNull(t), z, map);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.facebook.datasource.SimpleDataSource<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean setResult(T t) {
        return super.setResult(Preconditions.checkNotNull(t), true, null);
    }

    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setFailure(Throwable th) {
        return super.setFailure((Throwable) Preconditions.checkNotNull(th));
    }

    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setProgress(float f) {
        return super.setProgress(f);
    }
}
