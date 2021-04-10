package com.facebook.quicklog.utils;

public interface UtilsFactory {
    LogProxy getLogProxy();

    ProcessProxy getProcessProxy();

    IntToIntMap newIntToIntMap();

    IntToIntMap newIntToIntMap(int i);

    IntToLongMap newIntToLongMap();

    IntToLongMap newIntToLongMap(int i);

    <E> IntToObjectMap<E> newIntToObjectMap();

    <E> IntToObjectMap<E> newIntToObjectMap(int i);
}
