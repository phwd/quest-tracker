package com.facebook.flatbuffers;

import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.ThreadSafe;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@ThreadSafe
public final class DeltaBuffer {
    private static final int APPENDED_OBJECT_NO_POSITION = -1;
    private static final String DELTA_BUFFER_IDENTIFIER = "DELT";
    private static final String LOG_TAG = "DeltaBuffer";
    private static final Object UNSET_MARKER = new Object();
    @Nullable
    private final ByteBuffer mByteBuffer;
    @Nullable
    private final SparseArray<SparseArray<DeltaIndex>> mDeltaIndices;
    @GuardedBy("mLock")
    @Nullable
    private SparseArray<Extension> mExtensions;
    @GuardedBy("mLock")
    private boolean mHasChanged;
    private final Object mLock;
    @GuardedBy("mLock")
    private int mNextExtensionOrigin;
    @GuardedBy("mLock")
    @Nullable
    private SparseArray<SparseArray<Object>> mScratchDelta;

    protected DeltaBuffer(int i) {
        this.mLock = new Object();
        this.mHasChanged = false;
        this.mByteBuffer = null;
        this.mDeltaIndices = null;
        this.mNextExtensionOrigin = i;
    }

    protected DeltaBuffer(ByteBuffer byteBuffer) {
        SparseArray<Extension> sparseArray;
        SparseArray<SparseArray<DeltaIndex>> sparseArray2;
        this.mLock = new Object();
        this.mHasChanged = false;
        this.mByteBuffer = byteBuffer != null ? byteBuffer.duplicate() : null;
        ByteBuffer byteBuffer2 = this.mByteBuffer;
        if (byteBuffer2 != null) {
            byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
            int i = 0;
            while (i < 4) {
                if (this.mByteBuffer.get(i) == ((byte) DELTA_BUFFER_IDENTIFIER.charAt(i))) {
                    i++;
                } else {
                    throw new RuntimeException("Delta buffer header is invalid");
                }
            }
            this.mNextExtensionOrigin = this.mByteBuffer.getInt(i);
            int i2 = i + 4;
            int i3 = this.mByteBuffer.getInt(i2);
            int i4 = i2 + 4;
            if (i3 > 0) {
                sparseArray2 = new SparseArray<>();
                int i5 = i4;
                sparseArray = null;
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = this.mByteBuffer.getInt(i5);
                    int i8 = i5 + 4;
                    int i9 = this.mByteBuffer.getInt(i8);
                    int i10 = i8 + 4;
                    DeltaIndex deltaIndex = new DeltaIndex(this.mByteBuffer, i10);
                    i5 = i10 + 16;
                    addDeltaIndex(sparseArray2, i7, i9, deltaIndex);
                    if (deltaIndex.type == 1) {
                        Extension readFromBuffer = Extension.readFromBuffer(this.mByteBuffer, deltaIndex.pivot);
                        sparseArray = sparseArray == null ? new SparseArray<>() : sparseArray;
                        sparseArray.put(readFromBuffer.origin, readFromBuffer);
                    }
                }
                this.mDeltaIndices = sparseArray2;
                this.mExtensions = sparseArray;
                this.mScratchDelta = null;
            }
        }
        sparseArray = null;
        sparseArray2 = null;
        this.mDeltaIndices = sparseArray2;
        this.mExtensions = sparseArray;
        this.mScratchDelta = null;
    }

    @VisibleForTesting
    static int numDeltaItems(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        return byteBuffer.getInt(8);
    }

    private static void addDeltaIndex(SparseArray<SparseArray<DeltaIndex>> sparseArray, int i, int i2, @Nullable DeltaIndex deltaIndex) {
        SparseArray<DeltaIndex> sparseArray2 = sparseArray.get(i);
        if (sparseArray2 == null) {
            sparseArray2 = new SparseArray<>();
            sparseArray.put(i, sparseArray2);
        }
        sparseArray2.put(i2, deltaIndex);
    }

    /* access modifiers changed from: protected */
    public boolean hasDelta(int i, int i2) {
        SparseArray<DeltaIndex> sparseArray;
        SparseArray<Object> sparseArray2;
        synchronized (this.mLock) {
            if (this.mScratchDelta != null && (sparseArray2 = this.mScratchDelta.get(i)) != null && sparseArray2.indexOfKey(i2) >= 0) {
                return true;
            }
            if (this.mDeltaIndices == null || (sparseArray = this.mDeltaIndices.get(i)) == null || sparseArray.indexOfKey(i2) < 0) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public int getInt(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Integer) scratchValue).intValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0;
            }
            return this.mByteBuffer.getInt(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Boolean) scratchValue).booleanValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            boolean z = false;
            if (deltaIndex == null || this.mByteBuffer == null) {
                return false;
            }
            if (this.mByteBuffer.get(deltaIndex.pivot) == 1) {
                z = true;
            }
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public long getLong(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Long) scratchValue).longValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0;
            }
            return this.mByteBuffer.getLong(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String getString(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return (String) scratchValue;
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return null;
            }
            return FlatBuffer.getString(this.mByteBuffer, deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public int getExtensionReference(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            int i3 = 0;
            if (scratchValue != UNSET_MARKER) {
                if (scratchValue instanceof Extension) {
                    i3 = ((Extension) scratchValue).pivot;
                }
                return i3;
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || deltaIndex.type != 1 || this.mByteBuffer == null) {
                return 0;
            }
            return Extension.getPivot(this.mByteBuffer, deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public Extension getExtensionForPosition(int i) {
        Extension extensionForPositionOrNull = getExtensionForPositionOrNull(i);
        if (extensionForPositionOrNull != null) {
            return extensionForPositionOrNull;
        }
        throw new IndexOutOfBoundsException("No extension for position " + i);
    }

    @Nullable
    private Extension getExtensionForPositionOrNull(int i) {
        synchronized (this.mLock) {
            if (this.mExtensions == null) {
                return null;
            }
            int indexOfKeyOrNextLowest = SparseArrayUtil.indexOfKeyOrNextLowest(this.mExtensions, i);
            if (indexOfKeyOrNextLowest < 0) {
                return null;
            }
            Extension valueAt = this.mExtensions.valueAt(indexOfKeyOrNextLowest);
            if (!valueAt.containsPosition(i)) {
                valueAt = null;
            }
            return valueAt;
        }
    }

    /* access modifiers changed from: protected */
    public byte getByte(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Byte) scratchValue).byteValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0;
            }
            return this.mByteBuffer.get(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public short getShort(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Short) scratchValue).shortValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0;
            }
            return this.mByteBuffer.getShort(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public float getFloat(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Float) scratchValue).floatValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0.0f;
            }
            return this.mByteBuffer.getFloat(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public double getDouble(int i, int i2) {
        synchronized (this.mLock) {
            Object scratchValue = getScratchValue(i, i2);
            if (scratchValue != UNSET_MARKER) {
                return ((Double) scratchValue).doubleValue();
            }
            DeltaIndex deltaIndex = getDeltaIndex(i, i2);
            if (deltaIndex == null || this.mByteBuffer == null) {
                return 0.0d;
            }
            return this.mByteBuffer.getDouble(deltaIndex.pivot);
        }
    }

    /* access modifiers changed from: protected */
    public void putBoolean(int i, int i2, boolean z) {
        putScratchValue(i, i2, Boolean.valueOf(z));
    }

    /* access modifiers changed from: protected */
    public void putInt(int i, int i2, int i3) {
        putScratchValue(i, i2, Integer.valueOf(i3));
    }

    /* access modifiers changed from: protected */
    public void putString(int i, int i2, @Nullable String str) {
        putScratchValue(i, i2, str);
    }

    /* access modifiers changed from: protected */
    public void putByte(int i, int i2, byte b) {
        putScratchValue(i, i2, Byte.valueOf(b));
    }

    /* access modifiers changed from: protected */
    public void putShort(int i, int i2, short s) {
        putScratchValue(i, i2, Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    public void putLong(int i, int i2, long j) {
        putScratchValue(i, i2, Long.valueOf(j));
    }

    /* access modifiers changed from: protected */
    public void putFloat(int i, int i2, float f) {
        putScratchValue(i, i2, Float.valueOf(f));
    }

    /* access modifiers changed from: protected */
    public void putDouble(int i, int i2, double d) {
        putScratchValue(i, i2, Double.valueOf(d));
    }

    /* access modifiers changed from: protected */
    public void putAbsoluteReferenceList(int i, int i2, @Nullable int[] iArr) {
        int i3;
        synchronized (this.mLock) {
            Extension extension = null;
            int i4 = 0;
            if (iArr != null) {
                int length = iArr.length;
                Extension createExtension = createExtension(new byte[((length + 1) * 4)]);
                ByteBuffer byteBuffer = createExtension.contentBuffer;
                byteBuffer.putInt(0, length);
                int i5 = 0;
                while (i5 < length) {
                    int i6 = iArr[i5];
                    i5++;
                    int i7 = i5 * 4;
                    if (i6 == 0) {
                        i3 = 0;
                    } else {
                        i3 = i6 - createExtension.convertToGlobalPosition(i7);
                    }
                    byteBuffer.putInt(i7, i3);
                }
                extension = createExtension;
            }
            int extensionReference = getExtensionReference(i, i2);
            if (extensionReference != 0) {
                Extension extensionForPosition = getExtensionForPosition(extensionReference);
                int i8 = extensionForPosition.offset;
                int i9 = extensionForPosition.contentBuffer.getInt(i8);
                while (i4 < i9) {
                    i4++;
                    int i10 = (i4 * 4) + i8;
                    int convertToGlobalPosition = extensionForPosition.convertToGlobalPosition(i10) + extensionForPosition.contentBuffer.getInt(i10);
                    Extension extensionForPositionOrNull = getExtensionForPositionOrNull(convertToGlobalPosition);
                    if (extensionForPositionOrNull != null && !arrayContains(iArr, convertToGlobalPosition)) {
                        recursivelyMarkExtensionsAsDeleted(extensionForPositionOrNull);
                    }
                }
            }
            putExtension(i, i2, extension);
        }
    }

    /* access modifiers changed from: protected */
    public void putExtension(int i, int i2, @Nullable byte[] bArr) {
        Extension extension;
        synchronized (this.mLock) {
            if (bArr != null) {
                try {
                    extension = createExtension(bArr);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                extension = null;
            }
            putExtension(i, i2, extension);
        }
    }

    /* access modifiers changed from: protected */
    public Extension appendExtension(byte[] bArr) {
        Extension createExtension;
        synchronized (this.mLock) {
            Preconditions.checkNotNull(bArr);
            createExtension = createExtension(bArr);
            int i = 0;
            while (hasDelta(-1, i)) {
                i++;
            }
            putScratchValue(-1, i, createExtension);
        }
        return createExtension;
    }

    private void putExtension(int i, int i2, @Nullable Extension extension) {
        Extension extensionForPositionOrNull;
        int extensionReference = getExtensionReference(i, i2);
        if (extensionReference != 0) {
            recursivelyMarkExtensionsAsDeleted(getExtensionForPosition(extensionReference));
        }
        if (!(extension == null || (extensionForPositionOrNull = getExtensionForPositionOrNull(i)) == null || !extensionForPositionOrNull.isDeleted())) {
            extension.markAsDeleted();
        }
        putScratchValue(i, i2, extension);
    }

    private Extension createExtension(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        Extension create = Extension.create(wrap, this.mNextExtensionOrigin);
        long j = ((long) this.mNextExtensionOrigin) + ((long) create.length);
        int i = (int) j;
        if (j == ((long) i)) {
            this.mNextExtensionOrigin = i;
            if (this.mExtensions == null) {
                this.mExtensions = new SparseArray<>();
            }
            this.mExtensions.put(create.origin, create);
            return create;
        }
        BLog.e(LOG_TAG, "Too many mutations! Overflow of global position space.");
        throw new BufferOverflowException();
    }

    @GuardedBy("mLock")
    private void recursivelyMarkExtensionsAsDeleted(Extension extension) {
        extension.markAsDeleted();
        SparseArray<SparseArray<Object>> sparseArray = this.mScratchDelta;
        if (sparseArray != null) {
            int indexOfKeyOrNextHighest = SparseArrayUtil.indexOfKeyOrNextHighest(sparseArray, extension.origin);
            while (indexOfKeyOrNextHighest < this.mScratchDelta.size() && this.mScratchDelta.keyAt(indexOfKeyOrNextHighest) < extension.end()) {
                SparseArray<Object> valueAt = this.mScratchDelta.valueAt(indexOfKeyOrNextHighest);
                for (int i = 0; i < valueAt.size(); i++) {
                    Object valueAt2 = valueAt.valueAt(i);
                    if (valueAt2 instanceof Extension) {
                        recursivelyMarkExtensionsAsDeleted((Extension) valueAt2);
                    }
                }
                indexOfKeyOrNextHighest++;
            }
        }
        SparseArray<SparseArray<DeltaIndex>> sparseArray2 = this.mDeltaIndices;
        if (!(sparseArray2 == null || this.mByteBuffer == null)) {
            int indexOfKeyOrNextHighest2 = SparseArrayUtil.indexOfKeyOrNextHighest(sparseArray2, extension.origin);
            while (indexOfKeyOrNextHighest2 < this.mDeltaIndices.size() && this.mDeltaIndices.keyAt(indexOfKeyOrNextHighest2) < extension.end()) {
                SparseArray<DeltaIndex> valueAt3 = this.mDeltaIndices.valueAt(indexOfKeyOrNextHighest2);
                for (int i2 = 0; i2 < valueAt3.size(); i2++) {
                    DeltaIndex valueAt4 = valueAt3.valueAt(i2);
                    if (valueAt4.type == 1) {
                        recursivelyMarkExtensionsAsDeleted(getExtensionForPosition(Extension.getPivot(this.mByteBuffer, valueAt4.pivot)));
                    }
                }
                indexOfKeyOrNextHighest2++;
            }
        }
    }

    private void putScratchValue(int i, int i2, @Nullable Object obj) {
        synchronized (this.mLock) {
            if (this.mScratchDelta == null) {
                this.mScratchDelta = new SparseArray<>();
            }
            SparseArray<Object> sparseArray = this.mScratchDelta.get(i);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                this.mScratchDelta.put(i, sparseArray);
            }
            sparseArray.put(i2, obj);
            this.mHasChanged = true;
        }
    }

    @GuardedBy("mLock")
    private Object getScratchValue(int i, int i2) {
        SparseArray<SparseArray<Object>> sparseArray = this.mScratchDelta;
        if (sparseArray == null) {
            return UNSET_MARKER;
        }
        SparseArray<Object> sparseArray2 = sparseArray.get(i);
        if (sparseArray2 == null) {
            return UNSET_MARKER;
        }
        return sparseArray2.get(i2, UNSET_MARKER);
    }

    @GuardedBy("mLock")
    @Nullable
    private DeltaIndex getDeltaIndex(int i, int i2) {
        SparseArray<DeltaIndex> sparseArray;
        DeltaIndex deltaIndex;
        SparseArray<SparseArray<DeltaIndex>> sparseArray2 = this.mDeltaIndices;
        if (sparseArray2 == null || (sparseArray = sparseArray2.get(i)) == null || (deltaIndex = sparseArray.get(i2)) == null || deltaIndex.pivot == 0) {
            return null;
        }
        return deltaIndex;
    }

    /* access modifiers changed from: protected */
    public boolean hasChanged() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mHasChanged;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ByteBuffer getByteBuffer() {
        int i;
        synchronized (this.mLock) {
            this.mHasChanged = false;
            if (this.mScratchDelta != null) {
                if (this.mScratchDelta.size() != 0) {
                    SparseArray sparseArray = new SparseArray();
                    FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(256);
                    for (int i2 = 0; i2 < this.mScratchDelta.size(); i2++) {
                        int keyAt = this.mScratchDelta.keyAt(i2);
                        Extension extensionForPositionOrNull = getExtensionForPositionOrNull(keyAt);
                        if (extensionForPositionOrNull == null || !extensionForPositionOrNull.isDeleted()) {
                            SparseArray<Object> valueAt = this.mScratchDelta.valueAt(i2);
                            for (int i3 = 0; i3 < valueAt.size(); i3++) {
                                int keyAt2 = valueAt.keyAt(i3);
                                Object valueAt2 = valueAt.valueAt(i3);
                                if (valueAt2 == null) {
                                    addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(0, 0, 0, 0));
                                } else {
                                    int i4 = 1;
                                    if (valueAt2 instanceof Integer) {
                                        flatBufferBuilder.addInt(((Integer) valueAt2).intValue());
                                    } else {
                                        if (valueAt2 instanceof Boolean) {
                                            flatBufferBuilder.addByte(((Boolean) valueAt2).booleanValue() ? (byte) 1 : 0);
                                        } else {
                                            if (valueAt2 instanceof Long) {
                                                flatBufferBuilder.addLong(((Long) valueAt2).longValue());
                                            } else {
                                                if (valueAt2 instanceof String) {
                                                    int offset = flatBufferBuilder.offset();
                                                    flatBufferBuilder.createStringReference((String) valueAt2);
                                                    i = flatBufferBuilder.offset() - offset;
                                                } else if (valueAt2 instanceof Byte) {
                                                    flatBufferBuilder.addByte(((Byte) valueAt2).byteValue());
                                                } else if (valueAt2 instanceof Short) {
                                                    flatBufferBuilder.addShort(((Short) valueAt2).shortValue());
                                                    i = 2;
                                                } else if (valueAt2 instanceof Float) {
                                                    flatBufferBuilder.addFloat(((Float) valueAt2).floatValue());
                                                } else if (valueAt2 instanceof Double) {
                                                    flatBufferBuilder.addDouble(((Double) valueAt2).doubleValue());
                                                } else if (!(valueAt2 instanceof Extension)) {
                                                    throw new RuntimeException("Type not supported in DeltaBuffer:" + valueAt2.getClass().getSimpleName());
                                                } else if (!((Extension) valueAt2).isDeleted()) {
                                                    int offset2 = flatBufferBuilder.offset();
                                                    Extension.writeToBuffer((Extension) valueAt2, flatBufferBuilder);
                                                    i = flatBufferBuilder.offset() - offset2;
                                                    int offset3 = flatBufferBuilder.offset();
                                                    addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(offset3, i, offset3, i4));
                                                }
                                                i4 = 0;
                                                int offset32 = flatBufferBuilder.offset();
                                                addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(offset32, i, offset32, i4));
                                            }
                                            i = 8;
                                            i4 = 0;
                                            int offset322 = flatBufferBuilder.offset();
                                            addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(offset322, i, offset322, i4));
                                        }
                                        i = 1;
                                        i4 = 0;
                                        int offset3222 = flatBufferBuilder.offset();
                                        addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(offset3222, i, offset3222, i4));
                                    }
                                    i = 4;
                                    i4 = 0;
                                    int offset32222 = flatBufferBuilder.offset();
                                    addDeltaIndex(sparseArray, keyAt, keyAt2, new DeltaIndex(offset32222, i, offset32222, i4));
                                }
                            }
                            continue;
                        }
                    }
                    if (this.mDeltaIndices != null) {
                        if (this.mByteBuffer != null) {
                            for (int i5 = 0; i5 < this.mDeltaIndices.size(); i5++) {
                                int keyAt3 = this.mDeltaIndices.keyAt(i5);
                                Extension extensionForPositionOrNull2 = getExtensionForPositionOrNull(keyAt3);
                                if (extensionForPositionOrNull2 == null || !extensionForPositionOrNull2.isDeleted()) {
                                    SparseArray sparseArray2 = (SparseArray) sparseArray.get(keyAt3);
                                    SparseArray<DeltaIndex> valueAt3 = this.mDeltaIndices.valueAt(i5);
                                    for (int i6 = 0; i6 < valueAt3.size(); i6++) {
                                        int keyAt4 = valueAt3.keyAt(i6);
                                        if (sparseArray2 == null || sparseArray2.indexOfKey(keyAt4) < 0) {
                                            if (!isOrphanedListItem(keyAt3, keyAt4)) {
                                                DeltaIndex valueAt4 = valueAt3.valueAt(i6);
                                                if (valueAt4.pivot == 0) {
                                                    addDeltaIndex(sparseArray, keyAt3, keyAt4, valueAt4);
                                                } else {
                                                    flatBufferBuilder.prep(valueAt4.getAlign(), valueAt4.size);
                                                    flatBufferBuilder.copyBytes(this.mByteBuffer.array(), valueAt4.start, valueAt4.size);
                                                    int offset4 = flatBufferBuilder.offset();
                                                    addDeltaIndex(sparseArray, keyAt3, keyAt4, new DeltaIndex(offset4, valueAt4.size, offset4 - (valueAt4.pivot - valueAt4.start), valueAt4.type));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new RuntimeException("mByteBuffer for DeltaBuffer should not be null");
                        }
                    }
                    int i7 = 0;
                    for (int i8 = 0; i8 < sparseArray.size(); i8++) {
                        i7 += ((SparseArray) sparseArray.valueAt(i8)).size();
                    }
                    flatBufferBuilder.prep(4, 0);
                    flatBufferBuilder.prep(flatBufferBuilder.getMinalign(), (i7 * 24) + 8 + 4);
                    for (int i9 = 0; i9 < sparseArray.size(); i9++) {
                        int keyAt5 = sparseArray.keyAt(i9);
                        SparseArray sparseArray3 = (SparseArray) sparseArray.valueAt(i9);
                        for (int i10 = 0; i10 < sparseArray3.size(); i10++) {
                            int keyAt6 = sparseArray3.keyAt(i10);
                            ((DeltaIndex) sparseArray3.valueAt(i10)).writeToBuffer(flatBufferBuilder);
                            flatBufferBuilder.addInt(keyAt6);
                            flatBufferBuilder.addInt(keyAt5);
                        }
                    }
                    flatBufferBuilder.addInt(i7);
                    flatBufferBuilder.addInt(this.mNextExtensionOrigin);
                    for (int i11 = 3; i11 >= 0; i11--) {
                        flatBufferBuilder.addByte((byte) DELTA_BUFFER_IDENTIFIER.charAt(i11));
                    }
                    return ByteBuffer.wrap(flatBufferBuilder.sizedByteArray());
                }
            }
            return this.mByteBuffer;
        }
    }

    private boolean isOrphanedListItem(int i, int i2) {
        int extensionReference;
        if (i == -1 && (extensionReference = getExtensionReference(-1, i2)) != 0 && getExtensionForPosition(extensionReference).isDeleted()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public static class DeltaIndex {
        public static final int SIZE = 16;
        public static final int TYPE_EXTENSION = 1;
        public static final int TYPE_SCALAR = 0;
        public final int pivot;
        public final int size;
        public final int start;
        public final int type;

        public DeltaIndex(int i, int i2, int i3, int i4) {
            this.start = i;
            this.size = i2;
            this.pivot = i3;
            this.type = i4;
        }

        public DeltaIndex(ByteBuffer byteBuffer, int i) {
            this.size = byteBuffer.getInt(i + 4);
            if (this.size == 0) {
                this.start = 0;
                this.pivot = 0;
                this.type = 0;
            } else {
                this.start = byteBuffer.getInt(i) + i;
                int i2 = i + 8;
                this.pivot = i2 + byteBuffer.getInt(i2);
                this.type = byteBuffer.get(i + 12);
            }
            if (this.start >= byteBuffer.limit() || this.start + this.size > byteBuffer.limit() || this.pivot >= byteBuffer.limit()) {
                throw new IndexOutOfBoundsException(StringFormatUtil.formatStrLocaleSafe("DeltaIndex out of bound, limit=%d, start=%d, size=%d, pivot=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(this.start), Integer.valueOf(this.size), Integer.valueOf(this.pivot)));
            }
        }

        public int getAlign() {
            int i = this.size;
            if (i == 0) {
                return 0;
            }
            int i2 = 1;
            if (i != 1) {
                i2 = 2;
                if (i != 2) {
                    i2 = 4;
                    if (i != 4) {
                        return 8;
                    }
                }
            }
            return i2;
        }

        public void writeToBuffer(FlatBufferBuilder flatBufferBuilder) {
            if (this.size == 0) {
                flatBufferBuilder.addInt(0);
                flatBufferBuilder.addOffset(0);
                flatBufferBuilder.addInt(0);
                flatBufferBuilder.addOffset(0);
                return;
            }
            flatBufferBuilder.addInt(this.type);
            flatBufferBuilder.addOffset(this.pivot);
            flatBufferBuilder.addInt(this.size);
            flatBufferBuilder.addOffset(this.start);
        }
    }

    private static boolean arrayContains(@Nullable int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
