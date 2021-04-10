package com.facebook.graphservice.tree;

import X.AbstractC01980Pf;
import X.C05400jG;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TreeBuilderJNI extends HybridClassBase implements AbstractC01980Pf {
    @DoNotStrip
    public final int mTypeTag;

    @DoNotStrip
    private native <T extends TreeJNI> T getResultJNI(Class<T> cls, int i);

    @DoNotStrip
    private native TreeJNI[] getTreeJNIListByAddingTreeToList(TreeJNI treeJNI, Class<?> cls, int i, Iterable<? extends TreeJNI> iterable);

    @DoNotStrip
    private native <T extends TreeBuilderJNI> TreeBuilderJNI setTreeBuilderJNI(String str, String str2, String str3, @Nullable T t);

    @DoNotStrip
    private native <T extends TreeBuilderJNI> TreeBuilderJNI setTreeBuilderJNIList(String str, String str2, String str3, @Nullable Iterable<? extends T> iterable);

    @DoNotStrip
    private native <T extends TreeJNI> TreeBuilderJNI setTreeJNI(int i, @Nullable T t);

    @DoNotStrip
    private native <T extends TreeJNI> TreeBuilderJNI setTreeJNI(String str, @Nullable T t);

    @DoNotStrip
    private native <T extends TreeJNI> TreeBuilderJNI setTreeJNIList(int i, @Nullable Iterable<? extends T> iterable);

    @DoNotStrip
    private native <T extends TreeJNI> TreeBuilderJNI setTreeJNIList(String str, @Nullable Iterable<? extends T> iterable);

    @DoNotStrip
    public final native boolean hasPrimaryKey();

    @DoNotStrip
    public final native TreeBuilderJNI setBoolean(int i, @Nullable Boolean bool);

    @DoNotStrip
    public final native TreeBuilderJNI setBoolean(String str, @Nullable Boolean bool);

    @DoNotStrip
    public final native TreeBuilderJNI setBooleanList(int i, @Nullable Iterable<Boolean> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setBooleanList(String str, @Nullable Iterable<Boolean> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setDouble(int i, @Nullable Double d);

    @DoNotStrip
    public final native TreeBuilderJNI setDouble(String str, @Nullable Double d);

    @DoNotStrip
    public final native TreeBuilderJNI setDoubleList(int i, @Nullable Iterable<Double> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setDoubleList(String str, @Nullable Iterable<Double> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setInt(int i, @Nullable Integer num);

    @DoNotStrip
    public final native TreeBuilderJNI setInt(String str, @Nullable Integer num);

    @DoNotStrip
    public final native TreeBuilderJNI setIntList(int i, @Nullable Iterable<Integer> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setIntList(String str, @Nullable Iterable<Integer> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setNull(int i);

    @DoNotStrip
    public final native TreeBuilderJNI setNull(String str);

    @DoNotStrip
    public final native TreeBuilderJNI setString(int i, @Nullable String str);

    @DoNotStrip
    public final native TreeBuilderJNI setString(String str, @Nullable String str2);

    @DoNotStrip
    public final native TreeBuilderJNI setStringList(int i, @Nullable Iterable<String> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setStringList(String str, @Nullable Iterable<String> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setTime(int i, @Nullable Long l);

    @DoNotStrip
    public final native TreeBuilderJNI setTime(String str, @Nullable Long l);

    @DoNotStrip
    public final native TreeBuilderJNI setTimeList(int i, @Nullable Iterable<Long> iterable);

    @DoNotStrip
    public final native TreeBuilderJNI setTimeList(String str, @Nullable Iterable<Long> iterable);

    static {
        C05400jG.A00("graphservice-jni-tree");
    }

    @DoNotStrip
    public TreeBuilderJNI(int i) {
        this.mTypeTag = i;
    }
}
