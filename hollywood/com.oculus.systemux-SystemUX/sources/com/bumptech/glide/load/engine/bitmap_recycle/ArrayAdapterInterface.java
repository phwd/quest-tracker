package com.bumptech.glide.load.engine.bitmap_recycle;

/* access modifiers changed from: package-private */
public interface ArrayAdapterInterface<T> {
    int getArrayLength(T t);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i);
}
