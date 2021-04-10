package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BeanPropertyMap implements Serializable, Iterable<SettableBeanProperty> {
    private static final long serialVersionUID = 1;
    private final Bucket[] _buckets;
    private final int _hashMask;
    private int _nextBucketIndex = 0;
    private final int _size;

    private static final int findSize(int i) {
        int i2 = 2;
        while (i2 < (i <= 32 ? i + i : i + (i >> 2))) {
            i2 += i2;
        }
        return i2;
    }

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        this._size = collection.size();
        int findSize = findSize(this._size);
        this._hashMask = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (SettableBeanProperty settableBeanProperty : collection) {
            String name = settableBeanProperty.getName();
            int hashCode = name.hashCode() & this._hashMask;
            Bucket bucket = bucketArr[hashCode];
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            bucketArr[hashCode] = new Bucket(bucket, name, settableBeanProperty, i);
        }
        this._buckets = bucketArr;
    }

    private BeanPropertyMap(Bucket[] bucketArr, int i, int i2) {
        this._buckets = bucketArr;
        this._size = i;
        this._hashMask = bucketArr.length - 1;
        this._nextBucketIndex = i2;
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        Bucket[] bucketArr = this._buckets;
        int length = bucketArr.length;
        Bucket[] bucketArr2 = new Bucket[length];
        System.arraycopy(bucketArr, 0, bucketArr2, 0, length);
        String name = settableBeanProperty.getName();
        if (find(settableBeanProperty.getName()) == null) {
            int hashCode = name.hashCode() & this._hashMask;
            Bucket bucket = bucketArr2[hashCode];
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            bucketArr2[hashCode] = new Bucket(bucket, name, settableBeanProperty, i);
            return new BeanPropertyMap(bucketArr2, this._size + 1, this._nextBucketIndex);
        }
        BeanPropertyMap beanPropertyMap = new BeanPropertyMap(bucketArr2, length, this._nextBucketIndex);
        beanPropertyMap.replace(settableBeanProperty);
        return beanPropertyMap;
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        JsonDeserializer<Object> unwrappingDeserializer;
        if (nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        Iterator<SettableBeanProperty> it = iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            SettableBeanProperty withName = next.withName(nameTransformer.transform(next.getName()));
            JsonDeserializer<Object> valueDeserializer = withName.getValueDeserializer();
            if (!(valueDeserializer == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer)) == valueDeserializer)) {
                withName = withName.withValueDeserializer(unwrappingDeserializer);
            }
            arrayList.add(withName);
        }
        return new BeanPropertyMap(arrayList);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        SettableBeanProperty[] propertiesInInsertionOrder = getPropertiesInInsertionOrder();
        int i = 0;
        for (SettableBeanProperty settableBeanProperty : propertiesInInsertionOrder) {
            if (settableBeanProperty != null) {
                int i2 = i + 1;
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(settableBeanProperty.getName());
                sb.append('(');
                sb.append(settableBeanProperty.getType());
                sb.append(')');
                i = i2;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // java.lang.Iterable
    public Iterator<SettableBeanProperty> iterator() {
        return new IteratorImpl(this._buckets);
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[this._nextBucketIndex];
        Bucket[] bucketArr = this._buckets;
        for (Bucket bucket : bucketArr) {
            for (; bucket != null; bucket = bucket.next) {
                settableBeanPropertyArr[bucket.index] = bucket.value;
            }
        }
        return settableBeanPropertyArr;
    }

    public int size() {
        return this._size;
    }

    public SettableBeanProperty find(String str) {
        int hashCode = str.hashCode() & this._hashMask;
        Bucket bucket = this._buckets[hashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return _findWithEquals(str, hashCode);
            }
        } while (bucket.key != str);
        return bucket.value;
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode();
        Bucket[] bucketArr = this._buckets;
        int length = hashCode & (bucketArr.length - 1);
        Bucket bucket = null;
        int i = -1;
        for (Bucket bucket2 = bucketArr[length]; bucket2 != null; bucket2 = bucket2.next) {
            if (i >= 0 || !bucket2.key.equals(name)) {
                bucket = new Bucket(bucket, bucket2.key, bucket2.value, bucket2.index);
            } else {
                i = bucket2.index;
            }
        }
        if (i >= 0) {
            this._buckets[length] = new Bucket(bucket, name, settableBeanProperty, i);
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't replace");
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode();
        Bucket[] bucketArr = this._buckets;
        int length = hashCode & (bucketArr.length - 1);
        Bucket bucket = null;
        boolean z = false;
        for (Bucket bucket2 = bucketArr[length]; bucket2 != null; bucket2 = bucket2.next) {
            if (z || !bucket2.key.equals(name)) {
                bucket = new Bucket(bucket, bucket2.key, bucket2.value, bucket2.index);
            } else {
                z = true;
            }
        }
        if (z) {
            this._buckets[length] = bucket;
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't remove");
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        for (Bucket bucket = this._buckets[i]; bucket != null; bucket = bucket.next) {
            if (str.equals(bucket.key)) {
                return bucket.value;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final class Bucket implements Serializable {
        private static final long serialVersionUID = 1;
        public final int index;
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty, int i) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
            this.index = i;
        }
    }

    /* access modifiers changed from: private */
    public static final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            this._buckets = bucketArr;
            int length = this._buckets.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i2 = i + 1;
                Bucket bucket = this._buckets[i];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    i = i2;
                    break;
                }
                i = i2;
            }
            this._nextBucketIndex = i;
        }

        public boolean hasNext() {
            return this._currentBucket != null;
        }

        @Override // java.util.Iterator
        public SettableBeanProperty next() {
            Bucket bucket = this._currentBucket;
            if (bucket != null) {
                Bucket bucket2 = bucket.next;
                while (bucket2 == null) {
                    int i = this._nextBucketIndex;
                    Bucket[] bucketArr = this._buckets;
                    if (i >= bucketArr.length) {
                        break;
                    }
                    this._nextBucketIndex = i + 1;
                    bucket2 = bucketArr[i];
                }
                this._currentBucket = bucket2;
                return bucket.value;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
