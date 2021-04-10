package com.facebook.graphservice.tree;

import X.C05400jG;
import androidx.annotation.VisibleForTesting;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.annotation.Nullable;

@DoNotStrip
public class TreeJNI extends HybridClassBase implements Tree {
    public static final int NO_TYPE_TAG = 0;
    public static final Charset UTF_8 = Charset.forName("UTF8");
    @Nullable
    public final int[] mSetFields;
    @DoNotStrip
    public final int mTypeTag;

    @DoNotStrip
    private native ImmutableList<Boolean> getBooleanListNative(int i);

    @DoNotStrip
    private native ImmutableList<Boolean> getBooleanListNative(String str);

    @DoNotStrip
    @Nullable
    private native Boolean getBooleanNative(String str);

    @DoNotStrip
    private native boolean getBooleanValueNative(int i);

    @DoNotStrip
    private native boolean getBooleanValueNative(String str);

    @DoNotStrip
    private native ImmutableList<Double> getDoubleListNative(int i);

    @DoNotStrip
    private native ImmutableList<Double> getDoubleListNative(String str);

    @DoNotStrip
    @Nullable
    private native Double getDoubleNative(String str);

    @DoNotStrip
    private native double getDoubleValueNative(int i);

    @DoNotStrip
    private native double getDoubleValueNative(String str);

    @DoNotStrip
    private native ImmutableList<String> getFollowUpLabelListNative();

    @DoNotStrip
    private native ImmutableList<Integer> getIntListNative(int i);

    @DoNotStrip
    private native ImmutableList<Integer> getIntListNative(String str);

    @DoNotStrip
    @Nullable
    private native Integer getIntNative(String str);

    @DoNotStrip
    private native int getIntValueNative(int i);

    @DoNotStrip
    private native int getIntValueNative(String str);

    @DoNotStrip
    private native ImmutableList<String> getStringListNative(int i);

    @DoNotStrip
    private native ImmutableList<String> getStringListNative(String str);

    @DoNotStrip
    @Nullable
    private native String getStringNative(int i);

    @DoNotStrip
    @Nullable
    private native String getStringNative(String str);

    @DoNotStrip
    private native ImmutableList<Long> getTimeListNative(int i);

    @DoNotStrip
    private native ImmutableList<Long> getTimeListNative(String str);

    @DoNotStrip
    @Nullable
    private native Long getTimeNative(String str);

    @DoNotStrip
    private native long getTimeValueNative(int i);

    @DoNotStrip
    private native long getTimeValueNative(String str);

    @DoNotStrip
    @Nullable
    private native <T extends TreeJNI> T[] getTreeArrayNative(int i, Class<T> cls, int i2);

    @DoNotStrip
    @Nullable
    private native <T extends TreeJNI> T[] getTreeArrayNative(String str, Class<T> cls, int i);

    @DoNotStrip
    @Nullable
    private native <T extends TreeJNI> T getTreeNative(int i, Class<T> cls, int i2);

    @DoNotStrip
    @Nullable
    private native <T extends TreeJNI> T getTreeNative(String str, Class<T> cls, int i);

    @DoNotStrip
    @Nullable
    private native <T extends TreeJNI> T rerootNative(String str, Class<T> cls, int i);

    @DoNotStrip
    public final native boolean fulfillsType(String str);

    @DoNotStrip
    public final native ImmutableList<String> getAliases();

    @DoNotStrip
    @Nullable
    public native Boolean getBooleanVariable(String str);

    @DoNotStrip
    @VisibleForTesting
    public final native ImmutableList<String> getCanonicals();

    @DoNotStrip
    public final native Tree.FieldType getFieldType(String str);

    @DoNotStrip
    @Nullable
    public native String getTypeName();

    @DoNotStrip
    public final native boolean hasFieldValue(int i);

    @DoNotStrip
    public final native boolean hasFieldValue(String str);

    @DoNotStrip
    public final native boolean hasPrimaryKey();

    @DoNotStrip
    @VisibleForTesting
    public native boolean isDeepEqual(TreeJNI treeJNI);

    @DoNotStrip
    public native Tree.DeepEqualGuess isDeepEqualBestGuess(TreeJNI treeJNI);

    @DoNotStrip
    @Nullable
    public final native <T extends TreeJNI> T reinterpret(Class<T> cls, int i);

    @DoNotStrip
    public native String toExpensiveHumanReadableDebugString();

    @DoNotStrip
    public native String toString();

    static {
        C05400jG.A00("graphservice-jni-tree");
    }

    @Nullable
    @VisibleForTesting
    public static final <T extends TreeJNI> T[] filterNullArrayEntries(@Nullable T[] tArr) {
        if (tArr == null) {
            return null;
        }
        int length = tArr.length;
        int i = 0;
        for (T t : tArr) {
            if (t == null) {
                i++;
            }
        }
        if (i == 0) {
            return tArr;
        }
        T[] tArr2 = (T[]) new TreeJNI[(length - i)];
        int i2 = 0;
        for (T t2 : tArr) {
            if (t2 != null) {
                tArr2[i2] = t2;
                i2++;
            }
        }
        return tArr2;
    }

    private boolean isFieldUnset(int i) {
        int[] iArr = this.mSetFields;
        if (iArr == null || Arrays.binarySearch(iArr, i) >= 0) {
            return false;
        }
        return true;
    }

    public int getFieldCacheIndex(int i) {
        int[] iArr = this.mSetFields;
        if (iArr == null) {
            return -1;
        }
        return Arrays.binarySearch(iArr, i);
    }

    @DoNotStrip
    public TreeJNI(int i, @Nullable int[] iArr) {
        this.mTypeTag = i;
        this.mSetFields = iArr;
        if (iArr != null) {
            Arrays.sort(iArr);
        }
    }

    @DoNotStrip
    @Nullable
    public final Boolean getBoolean(String str) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return getBooleanNative(str);
    }

    @DoNotStrip
    @Nullable
    public final Double getDouble(String str) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return getDoubleNative(str);
    }

    @DoNotStrip
    public final ImmutableList<String> getFollowUpLabelList() {
        return getFollowUpLabelListNative();
    }

    @DoNotStrip
    @Nullable
    public final Integer getInt(String str) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return getIntNative(str);
    }

    @DoNotStrip
    @Nullable
    public final Long getTime(String str) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return getTimeNative(str);
    }

    public final <T extends TreeJNI> ImmutableList<T> getTreeList(int i, Class<T> cls, int i2) {
        TreeJNI[] treeArray = getTreeArray(i, cls, i2);
        if (treeArray != null) {
            return ImmutableList.copyOf(treeArray);
        }
        return ImmutableList.of();
    }

    @DoNotStrip
    @Nullable
    public final <T extends TreeJNI> T reroot(String str, Class<T> cls, int i) {
        return (T) rerootNative(str, cls, i);
    }

    @DoNotStrip
    @Nullable
    private <T extends TreeJNI> T[] getTreeArray(int i, Class<T> cls, int i2) {
        if (isFieldUnset(i)) {
            return null;
        }
        return (T[]) filterNullArrayEntries(getTreeArrayNative(i, cls, i2));
    }

    @DoNotStrip
    @Nullable
    private <T extends TreeJNI> T[] getTreeArray(String str, Class<T> cls, int i) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return (T[]) filterNullArrayEntries(getTreeArrayNative(str, cls, i));
    }

    @DoNotStrip
    public final ImmutableList<Boolean> getBooleanList(int i) {
        if (isFieldUnset(i)) {
            return ImmutableList.of();
        }
        return getBooleanListNative(i);
    }

    @DoNotStrip
    public final ImmutableList<Boolean> getBooleanList(String str) {
        if (isFieldUnset(str.hashCode())) {
            return ImmutableList.of();
        }
        return getBooleanListNative(str);
    }

    @DoNotStrip
    public final boolean getBooleanValue(int i) {
        if (isFieldUnset(i)) {
            return false;
        }
        return getBooleanValueNative(i);
    }

    @DoNotStrip
    public final boolean getBooleanValue(String str) {
        if (isFieldUnset(str.hashCode())) {
            return false;
        }
        return getBooleanValueNative(str);
    }

    @DoNotStrip
    public final ImmutableList<Double> getDoubleList(int i) {
        if (isFieldUnset(i)) {
            return ImmutableList.of();
        }
        return getDoubleListNative(i);
    }

    @DoNotStrip
    public final ImmutableList<Double> getDoubleList(String str) {
        if (isFieldUnset(str.hashCode())) {
            return ImmutableList.of();
        }
        return getDoubleListNative(str);
    }

    @DoNotStrip
    public final double getDoubleValue(int i) {
        if (isFieldUnset(i)) {
            return 0.0d;
        }
        return getDoubleValueNative(i);
    }

    @DoNotStrip
    public final double getDoubleValue(String str) {
        if (isFieldUnset(str.hashCode())) {
            return 0.0d;
        }
        return getDoubleValueNative(str);
    }

    @DoNotStrip
    public final ImmutableList<Integer> getIntList(int i) {
        if (isFieldUnset(i)) {
            return ImmutableList.of();
        }
        return getIntListNative(i);
    }

    @DoNotStrip
    public final ImmutableList<Integer> getIntList(String str) {
        if (isFieldUnset(str.hashCode())) {
            return ImmutableList.of();
        }
        return getIntListNative(str);
    }

    @DoNotStrip
    public final int getIntValue(int i) {
        if (isFieldUnset(i)) {
            return 0;
        }
        return getIntValueNative(i);
    }

    @DoNotStrip
    public final int getIntValue(String str) {
        if (isFieldUnset(str.hashCode())) {
            return 0;
        }
        return getIntValueNative(str);
    }

    @Override // com.facebook.graphservice.interfaces.Tree
    @DoNotStrip
    @Nullable
    public final String getString(int i) {
        if (isFieldUnset(i)) {
            return null;
        }
        return getStringNative(i);
    }

    @DoNotStrip
    @Nullable
    public final String getString(String str) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return getStringNative(str);
    }

    @Override // com.facebook.graphservice.interfaces.Tree
    @DoNotStrip
    public final ImmutableList<String> getStringList(int i) {
        if (isFieldUnset(i)) {
            return ImmutableList.of();
        }
        return getStringListNative(i);
    }

    @DoNotStrip
    public final ImmutableList<String> getStringList(String str) {
        if (isFieldUnset(str.hashCode())) {
            return ImmutableList.of();
        }
        return getStringListNative(str);
    }

    @DoNotStrip
    public final ImmutableList<Long> getTimeList(int i) {
        if (isFieldUnset(i)) {
            return ImmutableList.of();
        }
        return getTimeListNative(i);
    }

    @DoNotStrip
    public final ImmutableList<Long> getTimeList(String str) {
        if (isFieldUnset(str.hashCode())) {
            return ImmutableList.of();
        }
        return getTimeListNative(str);
    }

    @DoNotStrip
    public final long getTimeValue(int i) {
        if (isFieldUnset(i)) {
            return 0;
        }
        return getTimeValueNative(i);
    }

    @DoNotStrip
    public final long getTimeValue(String str) {
        if (isFieldUnset(str.hashCode())) {
            return 0;
        }
        return getTimeValueNative(str);
    }

    @DoNotStrip
    @Nullable
    public final <T extends TreeJNI> T getTree(int i, Class<T> cls, int i2) {
        if (isFieldUnset(i)) {
            return null;
        }
        return (T) getTreeNative(i, cls, i2);
    }

    @DoNotStrip
    @Nullable
    public final <T extends TreeJNI> T getTree(String str, Class<T> cls, int i) {
        if (isFieldUnset(str.hashCode())) {
            return null;
        }
        return (T) getTreeNative(str, cls, i);
    }
}
