package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.MapMaker;

/* access modifiers changed from: package-private */
@Deprecated
public abstract class GenericMapMaker<K0, V0> {
    MapMaker.RemovalListener<K0, V0> removalListener;

    /* access modifiers changed from: package-private */
    public enum NullListener implements MapMaker.RemovalListener<Object, Object> {
        INSTANCE;

        @Override // com.google.common.collect.MapMaker.RemovalListener
        public void onRemoval(MapMaker.RemovalNotification<Object, Object> removalNotification) {
        }
    }

    GenericMapMaker() {
    }

    /* access modifiers changed from: package-private */
    public <K extends K0, V extends V0> MapMaker.RemovalListener<K, V> getRemovalListener() {
        return (MapMaker.RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }
}
