package com.facebook.graphservice.interfaces;

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

    @Nullable
    String getString(int i);

    ImmutableList<String> getStringList(int i);
}
