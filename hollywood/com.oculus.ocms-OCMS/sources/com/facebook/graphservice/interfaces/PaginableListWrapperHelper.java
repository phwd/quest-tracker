package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableList;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PaginableListWrapperHelper {

    public interface TypeConverter<T, K> {
        K convert(T t);
    }

    public static <T, K> PaginableList<T> wrap(PaginableList<K> paginableList, TypeConverter<K, T> typeConverter) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<K> list = paginableList.getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            builder.add((Object) typeConverter.convert(list.get(i)));
        }
        return PaginableList.withMetadata(builder.build(), paginableList);
    }
}
