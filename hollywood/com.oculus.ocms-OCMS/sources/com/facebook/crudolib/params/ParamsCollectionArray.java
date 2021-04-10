package com.facebook.crudolib.params;

import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ParamsCollectionArray extends ParamsCollection {
    private final ArrayList<Object> mItems;

    ParamsCollectionArray(int i) {
        this.mItems = new ArrayList<>(i);
    }

    public int size() {
        return this.mItems.size();
    }

    @Nullable
    public Object get(int i) {
        return this.mItems.get(i);
    }

    public void add(@Nullable String str) {
        addInternal(str);
    }

    public void add(@Nullable Number number) {
        addInternal(number);
    }

    public void add(@Nullable Boolean bool) {
        addInternal(bool);
    }

    public void add(ParamsCollection paramsCollection) {
        addSubparamsPhaseOneInternal(paramsCollection);
        paramsCollection.attachToParent(this);
    }

    public void addAsRef(ParamsCollection paramsCollection) {
        addSubparamsPhaseOneInternal(paramsCollection);
        paramsCollection.attachAsRefToParent(this);
    }

    private void addSubparamsPhaseOneInternal(ParamsCollection paramsCollection) {
        Assertions.assertNotNull(paramsCollection, "subParams cannot be null!");
        throwIfNotExternallyMutable();
        paramsCollection.throwIfNotAttachable();
        addInternal(paramsCollection);
    }

    public ParamsCollectionMap acquireMapThenAdd() {
        ParamsCollectionMap acquireMap = getSourcePool().acquireMap();
        add(acquireMap);
        return acquireMap;
    }

    public ParamsCollectionArray acquireArrayThenAdd() {
        ParamsCollectionArray acquireArray = getSourcePool().acquireArray();
        add(acquireArray);
        return acquireArray;
    }

    private void addInternal(@Nullable Object obj) {
        throwIfNotExternallyMutable();
        this.mItems.add(obj);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onReleaseReferences() {
        int size = size();
        for (int i = 0; i < size; i++) {
            Object obj = get(i);
            if (obj instanceof ParamsCollection) {
                ((ParamsCollection) obj).releaseFromParent();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onRelease() {
        getSourcePool().release(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onClear() {
        this.mItems.clear();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onCompact(int i) {
        int size = this.mItems.size() - i;
        while (true) {
            int i2 = size - 1;
            if (size > 0) {
                ArrayList<Object> arrayList = this.mItems;
                arrayList.remove(arrayList.size() - 1);
                size = i2;
            } else {
                this.mItems.trimToSize();
                return;
            }
        }
    }
}
