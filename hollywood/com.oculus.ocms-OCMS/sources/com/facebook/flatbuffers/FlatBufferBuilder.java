package com.facebook.flatbuffers;

import android.util.SparseArray;
import com.facebook.flatbuffers.Flattenable;
import com.facebook.flatbuffers.util.MapEntrySetToKeyListAdapter;
import com.facebook.flatbuffers.util.MapEntrySetToValueListAdapter;
import com.facebook.infer.annotation.Assertions;
import com.facebook.stetho.common.Utf8Charset;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

public class FlatBufferBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int INITIAL_FLATBUFFER_SIZE = 128;
    static final int MAX_GROWTH = 65536;
    static final Charset utf8charset = Charset.forName(Utf8Charset.NAME);
    ByteBuffer bb;
    boolean building_object;
    int minalign;
    int num_vtables;
    int object_start;
    int space;
    @Nullable
    int[] vtable;
    int vtable_length;
    int[] vtables;

    @Immutable
    public static final class Reference {
        final int value;

        public Reference(int i) {
            this.value = i;
        }
    }

    public static ByteBuffer createEmptyFlatBuffer() {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(16);
        flatBufferBuilder.startObject(0);
        flatBufferBuilder.finish(flatBufferBuilder.endObject());
        byte[] sizedByteArray = flatBufferBuilder.sizedByteArray();
        if (sizedByteArray.length == 16) {
            return ByteBuffer.wrap(sizedByteArray);
        }
        throw new IllegalStateException();
    }

    public FlatBufferBuilder(int i) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_length = 0;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.building_object = false;
        i = i <= 0 ? 1 : i;
        this.space = i;
        this.bb = newByteBuffer(i);
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_length = 0;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.building_object = false;
        this.bb = byteBuffer;
        this.bb.clear();
        this.bb.order(ByteOrder.LITTLE_ENDIAN);
        this.space = this.bb.capacity();
    }

    private static ByteBuffer newByteBuffer(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        return allocate;
    }

    private static ByteBuffer growByteBuffer(ByteBuffer byteBuffer, int i) {
        ByteBuffer byteBuffer2;
        int capacity = byteBuffer.capacity();
        if ((-1073741824 & capacity) == 0) {
            int max = Math.max(Math.min(capacity, 65536), i - capacity) + capacity;
            byteBuffer.position(0);
            try {
                byteBuffer2 = newByteBuffer(max);
            } catch (OutOfMemoryError unused) {
                max = i;
                byteBuffer2 = newByteBuffer(i);
            }
            byteBuffer2.position(max - capacity);
            byteBuffer2.put(byteBuffer);
            return byteBuffer2;
        }
        throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
    }

    /* access modifiers changed from: protected */
    public void clearVtables() {
        this.vtables = new int[16];
        this.num_vtables = 0;
    }

    public int size() {
        return offset();
    }

    /* access modifiers changed from: protected */
    public int offset() {
        return this.bb.capacity() - this.space;
    }

    /* access modifiers changed from: protected */
    public int getMinalign() {
        return this.minalign;
    }

    private void pad(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.bb;
            int i3 = this.space - 1;
            this.space = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    /* access modifiers changed from: protected */
    public void prep(int i, int i2) {
        if (i > this.minalign) {
            this.minalign = i;
        }
        int capacity = ((((this.bb.capacity() - this.space) + i2) ^ -1) + 1) & (i - 1);
        int i3 = i + capacity + i2;
        int capacity2 = this.bb.capacity() - this.space;
        while (this.space < i3) {
            int capacity3 = this.bb.capacity();
            this.bb = growByteBuffer(this.bb, i3 + capacity2);
            this.space += this.bb.capacity() - capacity3;
        }
        pad(capacity);
    }

    /* access modifiers changed from: protected */
    public void copyBytes(byte[] bArr, int i, int i2) {
        ByteBuffer byteBuffer = this.bb;
        int i3 = this.space - i2;
        this.space = i3;
        byteBuffer.position(i3);
        this.bb.put(bArr, i, i2);
    }

    private void putByte(byte b) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 1;
        this.space = i;
        byteBuffer.put(i, b);
    }

    private void putShort(short s) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 2;
        this.space = i;
        byteBuffer.putShort(i, s);
    }

    private void putInt(int i) {
        ByteBuffer byteBuffer = this.bb;
        int i2 = this.space - 4;
        this.space = i2;
        byteBuffer.putInt(i2, i);
    }

    private void putLong(long j) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 8;
        this.space = i;
        byteBuffer.putLong(i, j);
    }

    private void putFloat(float f) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 4;
        this.space = i;
        byteBuffer.putFloat(i, f);
    }

    private void putDouble(double d) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 8;
        this.space = i;
        byteBuffer.putDouble(i, d);
    }

    /* access modifiers changed from: protected */
    public void addByte(byte b) {
        prep(1, 0);
        putByte(b);
    }

    /* access modifiers changed from: protected */
    public void addShort(short s) {
        prep(2, 0);
        putShort(s);
    }

    /* access modifiers changed from: protected */
    public void addInt(int i) {
        prep(4, 0);
        putInt(i);
    }

    /* access modifiers changed from: protected */
    public void addLong(long j) {
        prep(8, 0);
        putLong(j);
    }

    /* access modifiers changed from: protected */
    public void addFloat(float f) {
        prep(4, 0);
        putFloat(f);
    }

    /* access modifiers changed from: protected */
    public void addDouble(double d) {
        prep(8, 0);
        putDouble(d);
    }

    /* access modifiers changed from: protected */
    public void addNullOffset() {
        prep(4, 0);
        putInt(0);
    }

    /* access modifiers changed from: protected */
    public void addOffset(int i) {
        prep(4, 0);
        putInt((offset() - i) + 4);
    }

    private void startVector(int i, int i2, int i3) {
        notNested();
        int i4 = i * i2;
        prep(4, i4);
        prep(i3, i4);
    }

    private int endVector(int i) {
        putInt(i);
        return offset();
    }

    public int insertSubBufferReference(ByteBuffer byteBuffer, int i) {
        notNested();
        int limit = byteBuffer.limit();
        prep(limit, 0);
        ByteBuffer byteBuffer2 = this.bb;
        int i2 = this.space - limit;
        this.space = i2;
        byteBuffer2.position(i2);
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        if (asReadOnlyBuffer.position() == 0) {
            this.bb.put(asReadOnlyBuffer);
            return offset() - i;
        }
        throw new IllegalStateException("Expected to be position 0");
    }

    public int createFlattenableReference(@Nullable Flattenable flattenable) {
        if (flattenable == null) {
            return 0;
        }
        return flattenable.flattenToBuffer(this);
    }

    public <T> int createFlattenableWithFlattenerReference(@Nullable T t, Flattener<T> flattener) {
        if (t == null) {
            return 0;
        }
        return flattener.flattenToBuffer(t, this);
    }

    public int createVirtualFlattenableReference(Flattenable flattenable, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        if (flattenable == null) {
            return 0;
        }
        int virtualFlattenableType = virtualFlattenableResolver.getVirtualFlattenableType(flattenable);
        if (virtualFlattenableType != -1) {
            int flattenToBuffer = flattenable.flattenToBuffer(this);
            startObject(2);
            addInt(0, virtualFlattenableType, 0);
            addReference(1, flattenToBuffer);
            return endObject();
        }
        throw new RuntimeException("Could not resolve reference type for " + flattenable.getClass().getSimpleName());
    }

    public int createReferenceForReferenceArray(int[] iArr, boolean z) {
        if (iArr == null) {
            return 0;
        }
        if (iArr.length == 0 && z) {
            return 0;
        }
        startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (iArr[length] == 0) {
                addNullOffset();
            } else {
                addOffset(iArr[length]);
            }
        }
        return endVector(iArr.length);
    }

    public <T extends Flattenable> int createFlattenableListReference(List<T> list, boolean z) {
        if (list == null || (list.size() == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[list.size()];
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            T next = it.next();
            int i2 = i + 1;
            iArr[i] = next != null ? next.flattenToBuffer(this) : 0;
            i = i2;
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <T extends Flattenable> int createFlattenableListReference(List<T> list) {
        return createFlattenableListReference(list, true);
    }

    public <T> int createFlattenableWithFlattenerListReference(List<T> list, Flattener<T> flattener, boolean z) {
        if (list == null || (list.size() == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[list.size()];
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            T next = it.next();
            int i2 = i + 1;
            iArr[i] = next != null ? flattener.flattenToBuffer(next, this) : 0;
            i = i2;
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <T> int createFlattenableWithFlattenerListReference(List<T> list, Flattener<T> flattener) {
        return createFlattenableWithFlattenerListReference(list, flattener, true);
    }

    public <T extends Flattenable> int createVirtualFlattenableListReference(List<T> list, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        int i = 0;
        if (list == null || (list.size() == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[list.size()];
        for (T t : list) {
            iArr[i] = createVirtualFlattenableReference(t, virtualFlattenableResolver);
            i++;
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <T extends Flattenable> int createVirtualFlattenableListReference(List<T> list, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        return createVirtualFlattenableListReference(list, virtualFlattenableResolver, true);
    }

    private void notNested() {
        if (this.building_object) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    private void Nested(int i) {
        if (i != offset()) {
            throw new AssertionError("FlatBuffers: struct must be serialized inline.");
        }
    }

    public void startObject(int i) {
        notNested();
        int[] iArr = this.vtable;
        if (iArr == null || i > iArr.length) {
            this.vtable = new int[i];
        } else {
            Arrays.fill(iArr, 0, i, 0);
        }
        this.vtable_length = i;
        this.object_start = offset();
        this.building_object = true;
    }

    public void addByte(int i, byte b, int i2) {
        if (b != i2) {
            addByte(b);
            slot(i);
        }
    }

    public void addShort(int i, short s, int i2) {
        if (s != i2) {
            addShort(s);
            slot(i);
        }
    }

    public void addInt(int i, int i2, int i3) {
        if (i2 != i3) {
            addInt(i2);
            slot(i);
        }
    }

    public void addLong(int i, long j, long j2) {
        if (j != j2) {
            addLong(j);
            slot(i);
        }
    }

    public void addFloat(int i, float f, float f2) {
        if (f != f2) {
            addFloat(f);
            slot(i);
        }
    }

    public void addDouble(int i, double d, double d2) {
        if (d != d2) {
            addDouble(d);
            slot(i);
        }
    }

    public void addBoolean(int i, boolean z) {
        if (z) {
            addByte(i, (byte) 1, 0);
        }
    }

    public int createEnumStringReference(Enum r1) {
        if (r1 != null) {
            return createEnumStringReference(r1.name());
        }
        return 0;
    }

    public int createEnumStringReference(String str) {
        return createStringReference(normalizeEnumString(str));
    }

    @Nullable
    public static String normalizeEnumString(@Nullable String str) {
        if (str == null || "UNSET_OR_UNRECOGNIZED_ENUM_VALUE".equals(str)) {
            return null;
        }
        return str.toUpperCase(Locale.US);
    }

    public int createStringReference(@Nullable String str) {
        if (str == null) {
            return 0;
        }
        byte[] bytes = str.getBytes(utf8charset);
        addByte((byte) 0);
        startVector(1, bytes.length, 1);
        ByteBuffer byteBuffer = this.bb;
        int length = this.space - bytes.length;
        this.space = length;
        byteBuffer.position(length);
        this.bb.put(bytes, 0, bytes.length);
        return endVector(bytes.length);
    }

    public int createByteArrayReference(byte[] bArr, boolean z) {
        if (bArr == null || (bArr.length == 0 && z)) {
            return 0;
        }
        startVector(1, bArr.length, 1);
        ByteBuffer byteBuffer = this.bb;
        int length = this.space - bArr.length;
        this.space = length;
        byteBuffer.position(length);
        this.bb.put(bArr, 0, bArr.length);
        return endVector(bArr.length);
    }

    public int createByteArrayReference(byte[] bArr) {
        return createByteArrayReference(bArr, true);
    }

    public int createArrayReference(byte[] bArr) {
        return createByteArrayReference(bArr);
    }

    public int createShortArrayReference(short[] sArr, boolean z) {
        if (sArr == null) {
            return 0;
        }
        if (sArr.length == 0 && z) {
            return 0;
        }
        startVector(2, sArr.length, 2);
        for (int length = sArr.length - 1; length >= 0; length--) {
            putShort(sArr[length]);
        }
        return endVector(sArr.length);
    }

    public int createShortArrayReference(short[] sArr) {
        return createShortArrayReference(sArr, true);
    }

    public int createArrayReference(short[] sArr) {
        return createShortArrayReference(sArr);
    }

    public int createIntArrayReference(int[] iArr, boolean z) {
        if (iArr == null) {
            return 0;
        }
        if (iArr.length == 0 && z) {
            return 0;
        }
        startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            putInt(iArr[length]);
        }
        return endVector(iArr.length);
    }

    public int createIntArrayReference(int[] iArr) {
        return createIntArrayReference(iArr, true);
    }

    public int createArrayReference(int[] iArr) {
        return createIntArrayReference(iArr);
    }

    public int createLongArrayReference(long[] jArr, boolean z) {
        if (jArr == null) {
            return 0;
        }
        if (jArr.length == 0 && z) {
            return 0;
        }
        startVector(8, jArr.length, 8);
        for (int length = jArr.length - 1; length >= 0; length--) {
            putLong(jArr[length]);
        }
        return endVector(jArr.length);
    }

    public int createLongArrayReference(long[] jArr) {
        return createLongArrayReference(jArr, true);
    }

    public int createArrayReference(long[] jArr) {
        return createLongArrayReference(jArr);
    }

    public int createFloatArrayReference(float[] fArr, boolean z) {
        if (fArr == null) {
            return 0;
        }
        if (fArr.length == 0 && z) {
            return 0;
        }
        startVector(4, fArr.length, 4);
        for (int length = fArr.length - 1; length >= 0; length--) {
            putFloat(fArr[length]);
        }
        return endVector(fArr.length);
    }

    public int createFloatArrayReference(float[] fArr) {
        return createFloatArrayReference(fArr, true);
    }

    public int createArrayReference(float[] fArr) {
        return createFloatArrayReference(fArr);
    }

    public int createDoubleArrayReference(double[] dArr, boolean z) {
        if (dArr == null) {
            return 0;
        }
        if (dArr.length == 0 && z) {
            return 0;
        }
        startVector(8, dArr.length, 8);
        for (int length = dArr.length - 1; length >= 0; length--) {
            putDouble(dArr[length]);
        }
        return endVector(dArr.length);
    }

    public int createDoubleArrayReference(double[] dArr) {
        return createDoubleArrayReference(dArr, true);
    }

    public int createArrayReference(double[] dArr) {
        return createDoubleArrayReference(dArr);
    }

    public int createBooleanArrayReference(boolean[] zArr, boolean z) {
        if (zArr == null) {
            return 0;
        }
        if (zArr.length == 0 && z) {
            return 0;
        }
        startVector(1, zArr.length, 1);
        for (int length = zArr.length - 1; length >= 0; length--) {
            putByte(zArr[length] ? (byte) 1 : 0);
        }
        return endVector(zArr.length);
    }

    public int createBooleanArrayReference(boolean[] zArr) {
        return createBooleanArrayReference(zArr, true);
    }

    public int createArrayReference(boolean[] zArr) {
        return createBooleanArrayReference(zArr);
    }

    public int createStringArrayReference(String[] strArr, boolean z) {
        if (strArr == null || (strArr.length == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            iArr[i] = createStringReference(strArr[i]);
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public int createStringArrayReference(String[] strArr) {
        return createStringArrayReference(strArr, true);
    }

    public int createArrayReference(String[] strArr) {
        return createStringArrayReference(strArr);
    }

    public <T extends Enum> int createEnumStringArrayReference(T[] tArr, boolean z) {
        int length;
        if (tArr == null || ((length = tArr.length) == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (tArr[i] == null) {
                iArr[i] = 0;
            } else {
                iArr[i] = createStringReference(tArr[i].name());
            }
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <T extends Enum> int createEnumStringArrayReference(T[] tArr) {
        return createEnumStringArrayReference(tArr, true);
    }

    public <T extends Enum> int createArrayReference(T[] tArr) {
        return createEnumStringArrayReference(tArr);
    }

    public <F extends Flattenable> int createFlattenableArrayReference(F[] fArr, boolean z) {
        int length;
        if (fArr == null || ((length = fArr.length) == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (fArr[i] == null) {
                iArr[i] = 0;
            } else {
                iArr[i] = createFlattenableReference(fArr[i]);
            }
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <F extends Flattenable> int createFlattenableArrayReference(F[] fArr) {
        return createFlattenableArrayReference(fArr, true);
    }

    public <F extends Flattenable> int createArrayReference(F[] fArr) {
        return createFlattenableArrayReference(fArr);
    }

    public <T, FL extends Flattener<T>> int createFlattenableWithFlattenerArrayReference(T[] tArr, FL fl, boolean z) {
        int length;
        if (tArr == null || ((length = tArr.length) == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (tArr[i] == null) {
                iArr[i] = 0;
            } else {
                iArr[i] = createFlattenableWithFlattenerReference(tArr[i], fl);
            }
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <T, FL extends Flattener<T>> int createFlattenableWithFlattenerArrayReference(T[] tArr, FL fl) {
        return createFlattenableWithFlattenerArrayReference(tArr, fl, true);
    }

    public <T, FL extends Flattener<T>> int createArrayReference(T[] tArr, FL fl) {
        return createFlattenableWithFlattenerArrayReference(tArr, fl);
    }

    public <F extends Flattenable> int createVirtualFlattenableArrayReference(F[] fArr, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        int length;
        if (fArr == null || ((length = fArr.length) == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (fArr[i] == null) {
                iArr[i] = 0;
            } else {
                iArr[i] = createVirtualFlattenableReference(fArr[i], virtualFlattenableResolver);
            }
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <F extends Flattenable> int createVirtualFlattenableArrayReference(F[] fArr, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        return createVirtualFlattenableArrayReference(fArr, virtualFlattenableResolver, true);
    }

    public <F extends Flattenable> int createArrayReference(F[] fArr, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        return createVirtualFlattenableArrayReference(fArr, virtualFlattenableResolver);
    }

    public int createIntegerListReference(List<Integer> list, boolean z) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        if (size == 0 && z) {
            return 0;
        }
        startVector(4, size, 4);
        for (int i = size - 1; i >= 0; i--) {
            putInt(list.get(i).intValue());
        }
        return endVector(size);
    }

    public int createIntegerListReference(List<Integer> list) {
        return createIntegerListReference(list, true);
    }

    public int createLongListReference(List<Long> list, boolean z) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        if (size == 0 && z) {
            return 0;
        }
        startVector(8, size, 8);
        for (int i = size - 1; i >= 0; i--) {
            putLong(list.get(i).longValue());
        }
        return endVector(size);
    }

    public int createLongListReference(List<Long> list) {
        return createLongListReference(list, true);
    }

    public int createDoubleListReference(List<Double> list, boolean z) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        if (size == 0 && z) {
            return 0;
        }
        startVector(8, size, 8);
        for (int i = size - 1; i >= 0; i--) {
            putDouble(list.get(i).doubleValue());
        }
        return endVector(size);
    }

    public int createDoubleListReference(List<Double> list) {
        return createDoubleListReference(list, true);
    }

    private int createMapReference(int i, int i2) {
        startObject(2);
        addReference(0, i);
        addReference(1, i2);
        return endObject();
    }

    public <F extends Flattenable> int createStringToFlattenableMapReference(Map<String, F> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<String, F>> entrySet = map.entrySet();
        return createMapReference(createStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <T, F extends Flattener<T>> int createStringToFlattenableWithFlattenerMapReference(Map<String, T> map, F f, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<String, T>> entrySet = map.entrySet();
        return createMapReference(createStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableWithFlattenerListReference(new MapEntrySetToValueListAdapter(entrySet), f));
    }

    public <F extends Flattenable> int createStringToVirtualFlattenableMapReference(Map<String, F> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<String, F>> entrySet = map.entrySet();
        return createMapReference(createStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createVirtualFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet), virtualFlattenableResolver));
    }

    public <E extends Enum> int createStringToEnumStringMapReference(Map<String, E> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<String, E>> entrySet = map.entrySet();
        return createMapReference(createStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createEnumStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <E extends Enum> int createStringToStringMapReference(Map<String, String> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        return createMapReference(createStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <E extends Enum, F extends Flattenable> int createEnumStringToFlattenableMapReference(Map<E, F> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<E, F>> entrySet = map.entrySet();
        return createMapReference(createEnumStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <E extends Enum, T, F extends Flattener<T>> int createEnumStringToFlattenableWithFlattenerMapReference(Map<E, T> map, F f, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<E, T>> entrySet = map.entrySet();
        return createMapReference(createEnumStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableWithFlattenerListReference(new MapEntrySetToValueListAdapter(entrySet), f));
    }

    public <E extends Enum, F extends Flattenable> int createEnumStringToVirtualFlattenableMapReference(Map<E, F> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<E, F>> entrySet = map.entrySet();
        return createMapReference(createEnumStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createVirtualFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet), virtualFlattenableResolver));
    }

    public <E1 extends Enum, E2 extends Enum> int createEnumStringToEnumStringMapReference(Map<E1, E2> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<E1, E2>> entrySet = map.entrySet();
        return createMapReference(createEnumStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createEnumStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <E extends Enum> int createEnumStringToStringMapReference(Map<E, String> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<E, String>> entrySet = map.entrySet();
        return createMapReference(createEnumStringListReference(new MapEntrySetToKeyListAdapter(entrySet)), createStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F1 extends Flattenable, F2 extends Flattenable> int createFlattenableToFlattenableMapReference(Map<F1, F2> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F1, F2>> entrySet = map.entrySet();
        return createMapReference(createFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F extends Flattenable, T, FL extends Flattener<T>> int createFlattenableToFlattenableWithFlattenerMapReference(Map<F, T> map, FL fl, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, T>> entrySet = map.entrySet();
        return createMapReference(createFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet)), createFlattenableWithFlattenerListReference(new MapEntrySetToValueListAdapter(entrySet), fl));
    }

    public <FK extends Flattenable, FV extends Flattenable> int createFlattenableToVirtualFlattenableMapReference(Map<FK, FV> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<FK, FV>> entrySet = map.entrySet();
        return createMapReference(createFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet)), createVirtualFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet), virtualFlattenableResolver));
    }

    public <F extends Flattenable, E extends Enum> int createFlattenableToEnumStringMapReference(Map<F, E> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, E>> entrySet = map.entrySet();
        return createMapReference(createFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet)), createEnumStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F extends Flattenable> int createFlattenableToStringMapReference(Map<F, String> map, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, String>> entrySet = map.entrySet();
        return createMapReference(createFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet)), createStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F1 extends Flattenable, F2 extends Flattenable> int createVirtualFlattenableToFlattenableMapReference(Map<F1, F2> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F1, F2>> entrySet = map.entrySet();
        return createMapReference(createVirtualFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet), virtualFlattenableResolver), createFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F extends Flattenable, T, FL extends Flattener<T>> int createVirtualFlattenableToFlattenableWithFlattenerMapReference(Map<F, T> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, FL fl, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, T>> entrySet = map.entrySet();
        return createMapReference(createVirtualFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet), virtualFlattenableResolver), createFlattenableWithFlattenerListReference(new MapEntrySetToValueListAdapter(entrySet), fl));
    }

    public <FK extends Flattenable, FV extends Flattenable> int createVirtualFlattenableToVirtualFlattenableMapReference(Map<FK, FV> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver2, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<FK, FV>> entrySet = map.entrySet();
        return createMapReference(createVirtualFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet), virtualFlattenableResolver), createVirtualFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet), virtualFlattenableResolver2));
    }

    public <F extends Flattenable, E extends Enum> int createVirtualFlattenableToEnumStringMapReference(Map<F, E> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, E>> entrySet = map.entrySet();
        return createMapReference(createVirtualFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet), virtualFlattenableResolver), createEnumStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <F extends Flattenable> int createVirtualFlattenableToStringMapReference(Map<F, String> map, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<F, String>> entrySet = map.entrySet();
        return createMapReference(createVirtualFlattenableListReference(new MapEntrySetToKeyListAdapter(entrySet), virtualFlattenableResolver), createStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <T, FL extends Flattener<T>, F extends Flattenable> int createFlattenableWithFlattenerToFlattenableMapReference(Map<T, F> map, FL fl, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<T, F>> entrySet = map.entrySet();
        return createMapReference(createFlattenableWithFlattenerListReference(new MapEntrySetToKeyListAdapter(entrySet), fl), createFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <TK, FLK extends Flattener<TK>, TV, FLV extends Flattener<TV>> int createFlattenableWithFlattenerToFlattenableWithFlattenerMapReference(Map<TK, TV> map, FLK flk, FLV flv, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<TK, TV>> entrySet = map.entrySet();
        return createMapReference(createFlattenableWithFlattenerListReference(new MapEntrySetToKeyListAdapter(entrySet), flk), createFlattenableWithFlattenerListReference(new MapEntrySetToValueListAdapter(entrySet), flv));
    }

    public <TK, FLK extends Flattener<TK>, FV extends Flattenable> int createFlattenableWithFlattenerToVirtualFlattenableMapReference(Map<TK, FV> map, FLK flk, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<TK, FV>> entrySet = map.entrySet();
        return createMapReference(createFlattenableWithFlattenerListReference(new MapEntrySetToKeyListAdapter(entrySet), flk), createVirtualFlattenableListReference(new MapEntrySetToValueListAdapter(entrySet), virtualFlattenableResolver));
    }

    public <T, FL extends Flattener<T>, E extends Enum> int createFlattenableWithFlattenerToEnumStringMapReference(Map<T, E> map, FL fl, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<T, E>> entrySet = map.entrySet();
        return createMapReference(createFlattenableWithFlattenerListReference(new MapEntrySetToKeyListAdapter(entrySet), fl), createEnumStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public <T, FL extends Flattener<T>, E extends Enum> int createFlattenableWithFlattenerToStringMapReference(Map<T, String> map, FL fl, boolean z) {
        if (map == null) {
            return 0;
        }
        if (map.isEmpty() && z) {
            return 0;
        }
        Set<Map.Entry<T, String>> entrySet = map.entrySet();
        return createMapReference(createFlattenableWithFlattenerListReference(new MapEntrySetToKeyListAdapter(entrySet), fl), createStringListReference(new MapEntrySetToValueListAdapter(entrySet)));
    }

    public int createStringListReference(List<String> list, boolean z) {
        int i = 0;
        if (list == null || (list.size() == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[list.size()];
        for (String str : list) {
            iArr[i] = createStringReference(str);
            i++;
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public int createStringListReference(List<String> list) {
        return createStringListReference(list, true);
    }

    public <E extends Enum> int createEnumStringListReference(List<E> list, boolean z) {
        int size;
        if (list == null || ((size = list.size()) == 0 && z)) {
            return 0;
        }
        int[] iArr = new int[size];
        int i = 0;
        for (E e : list) {
            if (e == null) {
                iArr[i] = 0;
                i++;
            } else {
                iArr[i] = createEnumStringReference(e.name());
                i++;
            }
        }
        return createReferenceForReferenceArray(iArr, z);
    }

    public <E extends Enum> int createEnumStringListReference(List<E> list) {
        return createEnumStringListReference(list, true);
    }

    public void addReference(int i, int i2) {
        if (i2 != 0) {
            addOffset(i2);
            slot(i);
        }
    }

    public void addStruct(int i, int i2) {
        if (i2 != 0) {
            Nested(i2);
            slot(i);
        }
    }

    private void slot(int i) {
        Assertions.assertNotNull(this.vtable);
        Assertions.assertCondition(i < this.vtable_length);
        this.vtable[i] = offset();
    }

    public int endObject() {
        int i;
        addInt(0);
        int offset = offset();
        for (int i2 = this.vtable_length - 1; i2 >= 0; i2--) {
            int[] iArr = this.vtable;
            int i3 = iArr[i2] != 0 ? offset - iArr[i2] : 0;
            if (i3 <= 32767) {
                addShort((short) i3);
            } else {
                throw new RuntimeException("Object size exceeded 32K limit during flatbuffer serialzation");
            }
        }
        addShort((short) (offset - this.object_start));
        addShort((short) ((this.vtable_length + 2) * 2));
        int i4 = 0;
        loop1:
        while (true) {
            if (i4 >= this.num_vtables) {
                i = 0;
                break;
            }
            int capacity = this.bb.capacity() - this.vtables[i4];
            int i5 = this.space;
            short s = this.bb.getShort(capacity);
            if (s == this.bb.getShort(i5)) {
                for (int i6 = 2; i6 < s; i6 += 2) {
                    if (this.bb.getShort(capacity + i6) == this.bb.getShort(i5 + i6)) {
                    }
                }
                i = this.vtables[i4];
                break loop1;
            }
            i4++;
        }
        if (i != 0) {
            this.space = this.bb.capacity() - offset;
            this.bb.putInt(this.space, i - offset);
        } else {
            int i7 = this.num_vtables;
            int[] iArr2 = this.vtables;
            if (i7 == iArr2.length) {
                this.vtables = Arrays.copyOf(iArr2, i7 * 2);
            }
            int[] iArr3 = this.vtables;
            int i8 = this.num_vtables;
            this.num_vtables = i8 + 1;
            iArr3[i8] = offset();
            ByteBuffer byteBuffer = this.bb;
            byteBuffer.putInt(byteBuffer.capacity() - offset, offset() - offset);
        }
        this.building_object = false;
        this.vtable_length = 0;
        return offset;
    }

    public int createObjectFromItems(int i, SparseArray<Object> sparseArray) {
        startObject(i);
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2);
            Object valueAt = sparseArray.valueAt(i2);
            if (valueAt instanceof Reference) {
                addReference(keyAt, ((Reference) valueAt).value);
            } else if (valueAt instanceof Integer) {
                addInt(keyAt, ((Integer) valueAt).intValue(), 0);
            } else if (valueAt instanceof Long) {
                addLong(keyAt, ((Long) valueAt).longValue(), 0);
            } else if (valueAt instanceof Boolean) {
                addBoolean(keyAt, ((Boolean) valueAt).booleanValue());
            } else if (valueAt instanceof Double) {
                addDouble(keyAt, ((Double) valueAt).doubleValue(), 0.0d);
            } else {
                Assertions.assertUnreachable("Invalid Object " + valueAt.getClass().getSimpleName());
            }
        }
        return endObject();
    }

    public void finish(int i) {
        finishInternal(i, "FLAT");
    }

    private void finishInternal(int i, String str) {
        prep(this.minalign, 8);
        if (str.length() == 4) {
            for (int i2 = 3; i2 >= 0; i2--) {
                addByte((byte) str.charAt(i2));
            }
            addOffset(i);
            this.bb.position(this.space);
            return;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }

    public ByteBuffer dataBuffer() {
        return this.bb;
    }

    public int dataStart() {
        return this.space;
    }

    public int sizedByteArray(byte[] bArr, int i) {
        int i2 = this.space;
        int capacity = this.bb.capacity() - this.space;
        byte[] bArr2 = new byte[capacity];
        this.bb.position(i2);
        this.bb.get(bArr);
        return capacity;
    }

    private byte[] sizedByteArray(int i, int i2) {
        byte[] bArr = new byte[i2];
        this.bb.position(i);
        this.bb.get(bArr);
        return bArr;
    }

    public byte[] sizedByteArray() {
        return sizedByteArray(this.space, this.bb.capacity() - this.space);
    }

    @Nullable
    public static byte[] flatten(Flattenable flattenable) {
        return flatten(flattenable, null);
    }

    @Nullable
    public static byte[] flatten(Flattenable flattenable, @Nullable Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int i;
        if (virtualFlattenableResolver != null && virtualFlattenableResolver.getVirtualFlattenableType(flattenable) == -1) {
            return null;
        }
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(2048);
        if (virtualFlattenableResolver == null) {
            i = flatBufferBuilder.createFlattenableReference(flattenable);
        } else {
            i = flatBufferBuilder.createVirtualFlattenableReference(flattenable, virtualFlattenableResolver);
        }
        if (i <= 0) {
            return null;
        }
        flatBufferBuilder.finish(i);
        return flatBufferBuilder.sizedByteArray();
    }
}
