package com.facebook.graphservice.interfaces;

import java.util.List;

public interface TreeBuilder {
    @Deprecated
    Tree getResult();

    <T extends Tree> T getResult(Class<T> cls, int i);

    <T extends Tree> List<T> getTreeListByAddingTreeToList(T t, Iterable<T> iterable);

    TreeBuilder setBoolean(int i, Boolean bool);

    TreeBuilder setBoolean(String str, Boolean bool);

    TreeBuilder setBooleanList(int i, Iterable<Boolean> iterable);

    TreeBuilder setBooleanList(String str, Iterable<Boolean> iterable);

    TreeBuilder setDouble(int i, Double d);

    TreeBuilder setDouble(String str, Double d);

    TreeBuilder setDoubleList(int i, Iterable<Double> iterable);

    TreeBuilder setDoubleList(String str, Iterable<Double> iterable);

    TreeBuilder setInt(int i, Integer num);

    TreeBuilder setInt(String str, Integer num);

    TreeBuilder setIntList(int i, Iterable<Integer> iterable);

    TreeBuilder setIntList(String str, Iterable<Integer> iterable);

    TreeBuilder setNull(int i);

    TreeBuilder setNull(String str);

    <T extends Tree> TreeBuilder setPaginableTreeList(String str, PaginableList<? extends T> paginableList);

    TreeBuilder setString(int i, String str);

    TreeBuilder setString(String str, String str2);

    TreeBuilder setStringList(int i, Iterable<String> iterable);

    TreeBuilder setStringList(String str, Iterable<String> iterable);

    TreeBuilder setTime(int i, Long l);

    TreeBuilder setTime(String str, Long l);

    TreeBuilder setTimeList(int i, Iterable<Long> iterable);

    TreeBuilder setTimeList(String str, Iterable<Long> iterable);

    <T extends Tree> TreeBuilder setTree(int i, T t);

    <T extends Tree> TreeBuilder setTree(String str, T t);

    <T extends TreeBuilder> TreeBuilder setTreeBuilder(String str, String str2, String str3, T t);

    <T extends TreeBuilder> TreeBuilder setTreeBuilderList(String str, String str2, String str3, Iterable<? extends T> iterable);

    <T extends Tree> TreeBuilder setTreeList(int i, Iterable<? extends T> iterable);

    <T extends Tree> TreeBuilder setTreeList(String str, Iterable<? extends T> iterable);
}
