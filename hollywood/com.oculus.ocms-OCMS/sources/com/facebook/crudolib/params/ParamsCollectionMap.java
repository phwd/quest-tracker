package com.facebook.crudolib.params;

import android.text.TextUtils;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
public final class ParamsCollectionMap extends ParamsCollection {
    private final ArrayList<Object> mPairs;
    private int mSize;

    ParamsCollectionMap(int i) {
        this.mPairs = new ArrayList<>(i * 2);
    }

    public int size() {
        return this.mSize;
    }

    public String getKey(int i) {
        throwIfOutOfBounds(i);
        return (String) this.mPairs.get(i * 2);
    }

    @Nullable
    public Object getValue(int i) {
        throwIfOutOfBounds(i);
        return this.mPairs.get((i * 2) + 1);
    }

    private void throwIfOutOfBounds(int i) {
        if (i < 0 || i >= this.mSize) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
    }

    public void add(String str, @Nullable String str2) {
        addInternal(str, str2);
    }

    public void add(String str, @Nullable Number number) {
        addInternal(str, number);
    }

    public void add(String str, @Nullable Boolean bool) {
        addInternal(str, bool);
    }

    public void add(String str, ParamsCollection paramsCollection) {
        addSubparamsPhaseOneInternal(str, paramsCollection);
        paramsCollection.attachToParent(this);
    }

    public void addAsRef(String str, ParamsCollection paramsCollection) {
        addSubparamsPhaseOneInternal(str, paramsCollection);
        paramsCollection.attachAsRefToParent(this);
    }

    private void addSubparamsPhaseOneInternal(String str, ParamsCollection paramsCollection) {
        Assertions.assertNotNull(paramsCollection, "subParams cannot be null!");
        throwIfNotAddable(str);
        paramsCollection.throwIfNotAttachable();
        addInternal(str, paramsCollection);
    }

    public void remove(String str) {
        for (int i = 0; i < this.mSize; i++) {
            if (getKey(i).equals(str)) {
                int i2 = i * 2;
                this.mPairs.remove(i2 + 1);
                this.mPairs.remove(i2);
                this.mSize--;
                return;
            }
        }
    }

    public ParamsCollectionMap acquireMapThenAdd(String str) {
        ParamsCollectionMap acquireMap = getSourcePool().acquireMap();
        add(str, acquireMap);
        return acquireMap;
    }

    public ParamsCollectionArray acquireArrayThenAdd(String str) {
        ParamsCollectionArray acquireArray = getSourcePool().acquireArray();
        add(str, acquireArray);
        return acquireArray;
    }

    private void throwIfNotAddable(String str) {
        throwIfNotExternallyMutable();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("key=" + str);
        }
    }

    private void addInternal(String str, @Nullable Object obj) {
        throwIfNotAddable(str);
        this.mPairs.add(str);
        this.mPairs.add(obj);
        this.mSize++;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onReleaseReferences() {
        for (int i = 0; i < this.mSize; i++) {
            Object value = getValue(i);
            if (value instanceof ParamsCollection) {
                ((ParamsCollection) value).releaseFromParent();
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
        this.mPairs.clear();
        this.mSize = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.crudolib.params.ParamsCollection
    public void onCompact(int i) {
        int i2 = this.mSize - i;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                ArrayList<Object> arrayList = this.mPairs;
                arrayList.remove(arrayList.size() - 1);
                ArrayList<Object> arrayList2 = this.mPairs;
                arrayList2.remove(arrayList2.size() - 1);
                i2 = i3;
            } else {
                this.mPairs.trimToSize();
                return;
            }
        }
    }
}
