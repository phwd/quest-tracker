package com.facebook.internal;

import com.facebook.FacebookException;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionMapper {

    public interface Collection<T> {
        Object get(T t);

        Iterator<T> keyIterator();

        void set(T t, Object obj, OnErrorListener onErrorListener);
    }

    public interface OnErrorListener {
        void onError(FacebookException facebookException);
    }

    public interface OnMapValueCompleteListener extends OnErrorListener {
        void onComplete(Object obj);
    }

    public interface OnMapperCompleteListener extends OnErrorListener {
        void onComplete();
    }

    public interface ValueMapper {
        void mapValue(Object obj, OnMapValueCompleteListener onMapValueCompleteListener);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.facebook.internal.CollectionMapper$Collection<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void iterate(final Collection<T> collection, ValueMapper valueMapper, final OnMapperCompleteListener onMapperCompleteListener) {
        final Mutable mutable = new Mutable(false);
        final Mutable mutable2 = new Mutable(1);
        final AnonymousClass1 r4 = new OnMapperCompleteListener() {
            /* class com.facebook.internal.CollectionMapper.AnonymousClass1 */

            @Override // com.facebook.internal.CollectionMapper.OnMapperCompleteListener
            public void onComplete() {
                if (!mutable.value.booleanValue()) {
                    Mutable mutable = mutable2;
                    T t = (T) Integer.valueOf(mutable.value.intValue() - 1);
                    mutable.value = t;
                    if (t.intValue() == 0) {
                        onMapperCompleteListener.onComplete();
                    }
                }
            }

            @Override // com.facebook.internal.CollectionMapper.OnErrorListener
            public void onError(FacebookException facebookException) {
                Mutable mutable = mutable;
                if (!mutable.value.booleanValue()) {
                    mutable.value = (T) true;
                    onMapperCompleteListener.onError(facebookException);
                }
            }
        };
        Iterator keyIterator = collection.keyIterator();
        LinkedList linkedList = new LinkedList();
        while (keyIterator.hasNext()) {
            linkedList.add(keyIterator.next());
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            final Object next = it.next();
            Object obj = collection.get(next);
            AnonymousClass2 r1 = new OnMapValueCompleteListener() {
                /* class com.facebook.internal.CollectionMapper.AnonymousClass2 */

                @Override // com.facebook.internal.CollectionMapper.OnMapValueCompleteListener
                public void onComplete(Object obj) {
                    collection.set(next, obj, r4);
                    r4.onComplete();
                }

                @Override // com.facebook.internal.CollectionMapper.OnErrorListener
                public void onError(FacebookException facebookException) {
                    r4.onError(facebookException);
                }
            };
            mutable2.value = (T) Integer.valueOf(mutable2.value.intValue() + 1);
            valueMapper.mapValue(obj, r1);
        }
        r4.onComplete();
    }
}
