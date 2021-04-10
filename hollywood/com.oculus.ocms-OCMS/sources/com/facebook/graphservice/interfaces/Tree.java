package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Expensive;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface Tree {

    @DoNotStrip
    @Nullsafe(Nullsafe.Mode.LOCAL)
    public enum DeepEqualGuess {
        TRUE,
        FALSE,
        UNKNOWN
    }

    @DoNotStrip
    public enum FieldType {
        NONE,
        BOOL,
        DOUBLE,
        INT,
        TIME,
        STRING,
        DYNAMIC,
        BOOL_LIST,
        DOUBLE_LIST,
        INT_LIST,
        TIME_LIST,
        STRING_LIST,
        TREE,
        TREE_LIST
    }

    ImmutableList<String> getAliases();

    @Nullable
    Boolean getBoolean(String str);

    ImmutableList<Boolean> getBooleanList(int i);

    ImmutableList<Boolean> getBooleanList(String str);

    boolean getBooleanValue(int i);

    boolean getBooleanValue(String str);

    @Nullable
    Double getDouble(String str);

    ImmutableList<Double> getDoubleList(int i);

    ImmutableList<Double> getDoubleList(String str);

    double getDoubleValue(int i);

    double getDoubleValue(String str);

    FieldType getFieldType(String str);

    ImmutableList<String> getFollowUpLabelList();

    @Nullable
    Integer getInt(String str);

    ImmutableList<Integer> getIntList(int i);

    ImmutableList<Integer> getIntList(String str);

    int getIntValue(int i);

    int getIntValue(String str);

    <T extends Tree> PaginableList<T> getPaginableTreeList(String str);

    @Nullable
    String getString(int i);

    @Nullable
    String getString(String str);

    ImmutableList<String> getStringList(int i);

    ImmutableList<String> getStringList(String str);

    @Nullable
    Long getTime(String str);

    ImmutableList<Long> getTimeList(int i);

    ImmutableList<Long> getTimeList(String str);

    long getTimeValue(int i);

    long getTimeValue(String str);

    @Nullable
    <T extends Tree> T getTree(int i);

    @Nullable
    <T extends Tree> T getTree(String str);

    <T extends Tree> ImmutableList<T> getTreeList(int i);

    <T extends Tree> ImmutableList<T> getTreeList(String str);

    @Nullable
    String getTypeName();

    int getTypeTag();

    boolean isDeepEqual(Tree tree);

    DeepEqualGuess isDeepEqualBestGuess(Tree tree);

    boolean isValid();

    @Expensive
    String toExpensiveHumanReadableDebugString();
}
