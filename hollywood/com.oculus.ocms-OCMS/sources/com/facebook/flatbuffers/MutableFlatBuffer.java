package com.facebook.flatbuffers;

import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.foreach.RandomAccessList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.Flattenable;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class MutableFlatBuffer {
    private static final int ACCESSOR_ENUM_STRING_LIST = 4;
    private static final int ACCESSOR_FLATTENABLE_LIST = 5;
    private static final int ACCESSOR_INTEGER_LIST = 0;
    private static final int ACCESSOR_LONG_LIST = 1;
    private static final int ACCESSOR_STRING_LIST = 2;
    @VisibleForTesting
    static final double DEFAULT_VIEWMODEL_DOUBLE = 0.0d;
    @VisibleForTesting
    static final int DEFAULT_VIEWMODEL_INT = 0;
    private static volatile boolean sGlobalDebugEnabledFlag = false;
    private final Object DEBUG_LOG_LOCK = new Object();
    @Nullable
    private volatile FlatBuffer.FlattenableListAccessor flattenableListAccessor;
    private final ByteBuffer mBaseBuffer;
    @Nullable
    private final FlatBufferCorruptionHandler mCorruptionHandler;
    @Nullable
    @VisibleForTesting
    volatile IndexAccessTracker mIndexAccessTracker;
    @GuardedBy("this")
    @Nullable
    private SparseArray<Object> mMemoizedReferences;
    @Nullable
    private volatile DeltaBuffer mMutationBuffer;
    private boolean mMutationSupported;
    @Nullable
    private SparseArray<Object> mTags;

    public interface FlatBufferCorruptionHandler {
        void handleFlatBufferCorruption(Exception exc);
    }

    public static final class Tag {
        public static final int DEBUG_SOURCE = 5;
        public static final int DEBUG_VERBOSE_LOG = 6;
        public static final int FEED_DEDUP_KEY = 1;
        public static final int FEED_FETCH_TIME = 3;
        public static final int FEED_SORT_KEY = 2;
        public static final int FEED_TYPE = 0;
        public static final int FROM_JSON = 4;
    }

    public MutableFlatBuffer(ByteBuffer byteBuffer, @Nullable ByteBuffer byteBuffer2, boolean z, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        this.mBaseBuffer = byteBuffer.duplicate();
        this.mBaseBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.mMutationSupported = z;
        this.mCorruptionHandler = flatBufferCorruptionHandler;
        if (!this.mMutationSupported) {
            return;
        }
        if (byteBuffer2 != null) {
            try {
                this.mMutationBuffer = new DeltaBuffer(byteBuffer2);
            } catch (Exception e) {
                throw handleCorruptionAndThrow(e);
            }
        } else {
            this.mMutationBuffer = new DeltaBuffer(this.mBaseBuffer.capacity());
        }
    }

    public static void setGlobalDebugEnabled(boolean z) {
        sGlobalDebugEnabledFlag = z;
    }

    public void markDebugSource(String str) {
        if (sGlobalDebugEnabledFlag) {
            Preconditions.checkNotNull(str);
            synchronized (this.DEBUG_LOG_LOCK) {
                addTag(5, StringFormatUtil.formatStrLocaleSafe("[%s @ %d] %s", Thread.currentThread().getName(), Long.valueOf(SystemClock.elapsedRealtime()), str));
            }
        }
    }

    public void markDebugSource(String str, Flattenable flattenable) {
        if (sGlobalDebugEnabledFlag) {
            Preconditions.checkNotNull(flattenable);
            synchronized (this.DEBUG_LOG_LOCK) {
                markDebugSource(str);
                markDebugHistory("Converted from " + flattenable.getClass().getName());
            }
        }
    }

    public void markDebugHistory(String str) {
        ArrayList arrayList;
        if (sGlobalDebugEnabledFlag) {
            synchronized (this.DEBUG_LOG_LOCK) {
                Object tag = getTag(6);
                if (tag instanceof ArrayList) {
                    arrayList = (ArrayList) tag;
                } else {
                    arrayList = new ArrayList();
                    addTag(6, arrayList);
                }
                if (arrayList.size() >= 50) {
                    arrayList.clear();
                    arrayList.add("TRUNCATED");
                }
                arrayList.add(StringFormatUtil.formatStrLocaleSafe("[%s @ %d] %s", Thread.currentThread().getName(), Long.valueOf(SystemClock.elapsedRealtime()), str));
            }
        }
    }

    private void markSetterCalled(String str, int i, int i2) {
        if (sGlobalDebugEnabledFlag) {
            markDebugHistory(StringFormatUtil.formatStrLocaleSafe("Set %s, position (%d,%d)", str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public String getDebugLogs() {
        if (!sGlobalDebugEnabledFlag) {
            return "";
        }
        synchronized (this.DEBUG_LOG_LOCK) {
            Object tag = getTag(5);
            if (!(tag instanceof String)) {
                return "";
            }
            Object tag2 = getTag(6);
            StringBuilder sb = new StringBuilder();
            sb.append("Source: ");
            sb.append(tag);
            if (tag2 instanceof ArrayList) {
                sb.append("\nHistory:");
                Iterator it = ((ArrayList) tag2).iterator();
                while (it.hasNext()) {
                    sb.append("\n");
                    sb.append((String) it.next());
                }
            }
            return sb.toString();
        }
    }

    @Nullable
    private ByteBuffer duplicateBuffer(@Nullable ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        duplicate.position(0);
        return duplicate;
    }

    private void logIndexAccess(int i, int i2) {
        IndexAccessTracker indexAccessTracker = this.mIndexAccessTracker;
        if (indexAccessTracker != null && i < this.mBaseBuffer.capacity()) {
            indexAccessTracker.logIndexAccess(i, i2);
        }
    }

    public ByteBuffer getBaseBuffer() {
        return (ByteBuffer) Preconditions.checkNotNull(duplicateBuffer(this.mBaseBuffer));
    }

    public boolean isMutationBufferChanged() {
        if (this.mMutationBuffer != null) {
            return this.mMutationBuffer.hasChanged();
        }
        return false;
    }

    @Nullable
    public ByteBuffer getMutationBuffer() {
        if (this.mMutationBuffer == null) {
            return null;
        }
        try {
            return duplicateBuffer(this.mMutationBuffer.getByteBuffer());
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public boolean isMutationSupported() {
        return this.mMutationSupported;
    }

    public void fixupRootObjectPosition(int i) {
        this.mBaseBuffer.putInt(0, i);
        this.mMutationSupported = true;
    }

    public synchronized void addTag(int i, Object obj) {
        if (this.mTags == null) {
            this.mTags = new SparseArray<>();
        }
        this.mTags.put(i, obj);
    }

    @Nullable
    public synchronized Object getTag(int i) {
        if (this.mTags == null) {
            return null;
        }
        return this.mTags.get(i);
    }

    public synchronized void copyTagsTo(MutableFlatBuffer mutableFlatBuffer) {
        if (this.mTags != null) {
            for (int i = 0; i < this.mTags.size(); i++) {
                mutableFlatBuffer.addTag(this.mTags.keyAt(i), this.mTags.valueAt(i));
            }
        }
    }

    public boolean hasField(int i, int i2) {
        try {
            if (this.mMutationBuffer != null && i >= this.mBaseBuffer.capacity()) {
                Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                if (FlatBuffer.getOffsetForField(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2) != 0) {
                    return true;
                }
                return false;
            } else if (FlatBuffer.getOffsetForField(this.mBaseBuffer, i, i2) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public byte getByte(int i, int i2, byte b) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getByte(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getByte(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, b);
                }
            }
            return FlatBuffer.getByte(this.mBaseBuffer, i, i2, b);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setByte(int i, int i2, byte b) {
        markSetterCalled("Byte", i, i2);
        getOrCreateMutationBuffer().putByte(i, i2, b);
    }

    public short getShort(int i, int i2, short s) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getShort(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getShort(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, s);
                }
            }
            return FlatBuffer.getShort(this.mBaseBuffer, i, i2, s);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setShort(int i, int i2, short s) {
        markSetterCalled("Short", i, i2);
        getOrCreateMutationBuffer().putShort(i, i2, s);
    }

    public int getInt(int i, int i2, int i3) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getInt(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getInt(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, i3);
                }
            }
            return FlatBuffer.getInt(this.mBaseBuffer, i, i2, i3);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setInt(int i, int i2, int i3) {
        markSetterCalled("Integer", i, i2);
        getOrCreateMutationBuffer().putInt(i, i2, i3);
    }

    public long getLong(int i, int i2, long j) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getLong(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getLong(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, j);
                }
            }
            return FlatBuffer.getLong(this.mBaseBuffer, i, i2, j);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setLong(int i, int i2, long j) {
        markSetterCalled("Long", i, i2);
        getOrCreateMutationBuffer().putLong(i, i2, j);
    }

    public float getFloat(int i, int i2, float f) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getFloat(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getFloat(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, f);
                }
            }
            return FlatBuffer.getFloat(this.mBaseBuffer, i, i2, f);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setFloat(int i, int i2, float f) {
        markSetterCalled("Float", i, i2);
        getOrCreateMutationBuffer().putFloat(i, i2, f);
    }

    public double getDouble(int i, int i2, double d) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getDouble(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getDouble(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, d);
                }
            }
            return FlatBuffer.getDouble(this.mBaseBuffer, i, i2, d);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setDouble(int i, int i2, double d) {
        markSetterCalled("Double", i, i2);
        getOrCreateMutationBuffer().putDouble(i, i2, d);
    }

    public boolean getBoolean(int i, int i2) {
        if (i == 0) {
            return false;
        }
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getBoolean(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.getBoolean(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.getBoolean(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public void setBoolean(int i, int i2, boolean z) {
        markSetterCalled("Boolean", i, i2);
        getOrCreateMutationBuffer().putBoolean(i, i2, z);
    }

    @Nullable
    public String resolveEnumString(int i, int i2) {
        return resolveStringReference(i, i2);
    }

    @Nullable
    public String resolveStringReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getString(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.resolveStringReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.resolveStringReference(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public int peekStringLength(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    String string = this.mMutationBuffer.getString(i, i2);
                    if (string == null) {
                        return 0;
                    }
                    return string.length();
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.peekStringLength(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.peekStringLength(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public boolean appendString(int i, int i2, Appendable appendable) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    String string = this.mMutationBuffer.getString(i, i2);
                    if (string == null) {
                        return false;
                    }
                    appendImpl(string, appendable);
                    return true;
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.appendString(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, appendable);
                }
            }
            return FlatBuffer.appendString(this.mBaseBuffer, i, i2, appendable);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    private static void appendImpl(String str, Appendable appendable) {
        try {
            appendable.append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setString(int i, int i2, @Nullable String str) {
        markSetterCalled("String", i, i2);
        getOrCreateMutationBuffer().putString(i, i2, str);
    }

    public int getNumFieldsFromVTable(int i) {
        if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
            return FlatBuffer.getNumFieldsFromVTable(this.mBaseBuffer, i);
        }
        Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
        return FlatBuffer.getNumFieldsFromVTable(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i));
    }

    @Nullable
    public byte[] resolveByteArrayReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.resolveByteArrayReference(this.mBaseBuffer, i, i2);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.resolveByteArrayReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public ByteBuffer resolveByteBufferReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.resolveByteBufferReference(this.mBaseBuffer, i, i2);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.resolveByteBufferReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public int[] resolveIntArrayReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.resolveIntArrayReference(this.mBaseBuffer, i, i2);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.resolveIntArrayReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Iterator<Integer> resolveIntegerListReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    int extensionReference = this.mMutationBuffer.getExtensionReference(i, i2);
                    if (extensionReference == 0) {
                        return null;
                    }
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(extensionReference);
                    return FlatBuffer.resolveIntegerListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(extensionReference));
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition2 = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.resolveIntegerListReference(extensionForPosition2.contentBuffer, extensionForPosition2.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.resolveIntegerListReference(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Iterator<Long> resolveLongListReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    int extensionReference = this.mMutationBuffer.getExtensionReference(i, i2);
                    if (extensionReference == 0) {
                        return null;
                    }
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(extensionReference);
                    return FlatBuffer.resolveLongListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(extensionReference));
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition2 = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.resolveLongListReference(extensionForPosition2.contentBuffer, extensionForPosition2.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.resolveLongListReference(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Iterator<Double> resolveDoubleListReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    int extensionReference = this.mMutationBuffer.getExtensionReference(i, i2);
                    if (extensionReference == 0) {
                        return null;
                    }
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(extensionReference);
                    return FlatBuffer.resolveDoubleListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(extensionReference));
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition2 = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.resolveDoubleListReference(extensionForPosition2.contentBuffer, extensionForPosition2.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.resolveDoubleListReference(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Iterator<String> resolveStringListReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    int extensionReference = this.mMutationBuffer.getExtensionReference(i, i2);
                    if (extensionReference == 0) {
                        return null;
                    }
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(extensionReference);
                    return FlatBuffer.resolveStringListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(extensionReference));
                } else if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition2 = this.mMutationBuffer.getExtensionForPosition(i);
                    return FlatBuffer.resolveStringListReference(extensionForPosition2.contentBuffer, extensionForPosition2.convertToContentBufferPosition(i), i2);
                }
            }
            return FlatBuffer.resolveStringListReference(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public <T extends Enum> Iterator<T> resolveEnumStringListReference(int i, int i2, Class<T> cls) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.resolveEnumStringListReference(this.mBaseBuffer, i, i2, cls);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.resolveEnumStringListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2, cls);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Iterator<String> resolveStringDefStringListReference(int i, int i2) {
        try {
            logIndexAccess(i, i2);
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.resolveStringDefStringListReference(this.mBaseBuffer, i, i2);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.resolveStringDefStringListReference(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public int getReferencePosition(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        try {
            if (this.mMutationBuffer != null) {
                if (this.mMutationBuffer.hasDelta(i, i2)) {
                    return this.mMutationBuffer.getExtensionReference(i, i2);
                }
                if (i >= this.mBaseBuffer.capacity()) {
                    Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                    int referencePosition = FlatBuffer.getReferencePosition(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2);
                    if (referencePosition == 0) {
                        return 0;
                    }
                    return extensionForPosition.convertToGlobalPosition(referencePosition);
                }
            }
            return FlatBuffer.getReferencePosition(this.mBaseBuffer, i, i2);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public <T extends Flattenable> T resolveFlattenableReference(int i, int i2, T t) {
        int referencePosition = getReferencePosition(i, i2);
        if (referencePosition != 0) {
            return (T) resolveFlattenableAt(referencePosition, t);
        }
        return null;
    }

    public void setFlattenable(int i, int i2, @Nullable Flattenable flattenable) {
        setExtension(i, i2, flattenable != null ? createBytesFromFlattenable(flattenable) : null);
    }

    public <T extends Flattenable> void setFlattenableList(int i, int i2, @Nullable List<T> list) {
        setExtension(i, i2, list != null ? createBytesFromFlattenableList(list) : null);
    }

    public <T extends MutableFlattenable> void setFlattenableListDiff(int i, int i2, @Nullable List<T> list) {
        DeltaBuffer orCreateMutationBuffer = getOrCreateMutationBuffer();
        if (list == null) {
            orCreateMutationBuffer.putAbsoluteReferenceList(i, i2, null);
            return;
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            T t = list.get(i3);
            if (t == null) {
                iArr[i3] = 0;
            } else if (t.getMutableFlatBuffer() != this) {
                Extension appendExtension = orCreateMutationBuffer.appendExtension(createBytesFromFlattenable(t));
                iArr[i3] = appendExtension.convertToGlobalPosition(FlatBuffer.getRootObjectPosition(appendExtension.contentBuffer));
            } else {
                iArr[i3] = t.getPositionInMutableFlatBuffer();
            }
        }
        orCreateMutationBuffer.putAbsoluteReferenceList(i, i2, iArr);
    }

    public void setStringList(int i, int i2, @Nullable List<String> list) {
        setExtension(i, i2, list != null ? createBytesFromStringList(list) : null);
    }

    public void setIntList(int i, int i2, @Nullable List<Integer> list) {
        setExtension(i, i2, list != null ? createBytesFromIntList(list) : null);
    }

    public void setLongList(int i, int i2, @Nullable List<Long> list) {
        setExtension(i, i2, list != null ? createBytesFromLongList(list) : null);
    }

    public void setDoubleList(int i, int i2, @Nullable List<Double> list) {
        setExtension(i, i2, list != null ? createBytesFromDoubleList(list) : null);
    }

    public void setExtension(int i, int i2, @Nullable byte[] bArr) {
        try {
            markSetterCalled("Extension", i, i2);
            getOrCreateMutationBuffer().putExtension(i, i2, bArr);
        } catch (IndexOutOfBoundsException | BufferOverflowException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    private static byte[] createBytesFromFlattenable(Flattenable flattenable) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flattenable.flattenToBuffer(flatBufferBuilder));
        return flatBufferBuilder.sizedByteArray();
    }

    private static <T extends Flattenable> byte[] createBytesFromFlattenableList(List<T> list) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flatBufferBuilder.createFlattenableListReference(list, false));
        return flatBufferBuilder.sizedByteArray();
    }

    private static byte[] createBytesFromStringList(List<String> list) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flatBufferBuilder.createStringListReference(list, false));
        return flatBufferBuilder.sizedByteArray();
    }

    private static byte[] createBytesFromIntList(List<Integer> list) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flatBufferBuilder.createIntegerListReference(list, false));
        return flatBufferBuilder.sizedByteArray();
    }

    private static byte[] createBytesFromLongList(List<Long> list) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flatBufferBuilder.createLongListReference(list, false));
        return flatBufferBuilder.sizedByteArray();
    }

    private static byte[] createBytesFromDoubleList(List<Double> list) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
        flatBufferBuilder.finish(flatBufferBuilder.createDoubleListReference(list, false));
        return flatBufferBuilder.sizedByteArray();
    }

    @Nullable
    public Flattenable resolveVirtualFlattenableReference(int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(i, i2);
        if (referencePosition != 0) {
            return resolveFlattenable(referencePosition, virtualFlattenableResolver);
        }
        return null;
    }

    @Nullable
    public <T extends Flattenable> Iterator<T> resolveFlattenableListReference(int i, int i2, Class<T> cls) {
        int referencePosition = getReferencePosition(i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return new MutableFlattenableIterator(this, FlatBuffer.getVectorStart(referencePosition), getVectorLength(referencePosition), cls);
    }

    @Nullable
    public <T extends Flattenable> Iterator<T> resolveFlattenableListReference(int i, int i2, T t) {
        int referencePosition = getReferencePosition(i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return new MutableFlattenableIterator(this, FlatBuffer.getVectorStart(referencePosition), getVectorLength(referencePosition), (CloneableShim) t);
    }

    @Nullable
    public <T extends Flattenable> Iterator<T> resolveVirtualFlattenableListReference(int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return new MutableFlattenableIterator(this, FlatBuffer.getVectorStart(referencePosition), getVectorLength(referencePosition), virtualFlattenableResolver);
    }

    @Nullable
    public <T extends Flattenable> T resolveRootFlattenable(Class<T> cls) {
        try {
            int rootObjectPosition = FlatBuffer.getRootObjectPosition(this.mBaseBuffer);
            if (rootObjectPosition <= 0) {
                return null;
            }
            return (T) resolveFlattenableAt(rootObjectPosition, cls);
        } catch (Exception e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public <T extends Flattenable> T resolveRootFlattenable(T t) {
        try {
            int rootObjectPosition = FlatBuffer.getRootObjectPosition(this.mBaseBuffer);
            if (rootObjectPosition <= 0) {
                return null;
            }
            return (T) resolveFlattenableAt(rootObjectPosition, t);
        } catch (Exception e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public <T extends Flattenable> T resolveFlattenableAt(int i, Class<T> cls) {
        try {
            T newInstance = cls.newInstance();
            try {
                newInstance.initFromMutableFlatBuffer(this, i);
                return newInstance;
            } catch (IndexOutOfBoundsException e) {
                throw handleCorruptionAndThrow(e);
            }
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Illegal access for root object:" + cls.getSimpleName(), e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Not able to create root object:" + cls.getSimpleName(), e3);
        }
    }

    @Nullable
    public <T extends Flattenable> T resolveFlattenableAt(int i, T t) {
        try {
            t.initFromMutableFlatBuffer(this, i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Flattenable resolveFlattenable(int i, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        if (i <= 0) {
            return null;
        }
        int i2 = getInt(i, 0, 0);
        int referencePosition = getReferencePosition(i, 1);
        Flattenable resolveVirtualFlattenableType = virtualFlattenableResolver.resolveVirtualFlattenableType(i2);
        if (resolveVirtualFlattenableType == null) {
            return null;
        }
        try {
            resolveVirtualFlattenableType.initFromMutableFlatBuffer(this, referencePosition);
            return resolveVirtualFlattenableType;
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public Flattenable resolveRootFlattenable(Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        try {
            return resolveFlattenable(FlatBuffer.getRootObjectPosition(this.mBaseBuffer), virtualFlattenableResolver);
        } catch (Exception e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    @Nullable
    public static <T extends Flattenable> T resolveRootFlattenable(ByteBuffer byteBuffer, Class<T> cls, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return (T) resolveRootFlattenable(byteBuffer, (Class) cls, (ByteBuffer) null, false, flatBufferCorruptionHandler);
    }

    @Nullable
    public static <T extends Flattenable> T resolveRootFlattenable(ByteBuffer byteBuffer, T t, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return (T) resolveRootFlattenable(byteBuffer, (Flattenable) t, (ByteBuffer) null, false, flatBufferCorruptionHandler);
    }

    @Nullable
    public static <T extends Flattenable> T resolveRootFlattenable(ByteBuffer byteBuffer, Class<T> cls, @Nullable ByteBuffer byteBuffer2, boolean z, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return (T) new MutableFlatBuffer(byteBuffer, byteBuffer2, z, flatBufferCorruptionHandler).resolveRootFlattenable(cls);
    }

    @Nullable
    public static <T extends Flattenable> T resolveRootFlattenable(ByteBuffer byteBuffer, T t, @Nullable ByteBuffer byteBuffer2, boolean z, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return (T) new MutableFlatBuffer(byteBuffer, byteBuffer2, z, flatBufferCorruptionHandler).resolveRootFlattenable(t);
    }

    @Nullable
    public static Flattenable resolveRootFlattenable(ByteBuffer byteBuffer, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return resolveRootFlattenable(byteBuffer, virtualFlattenableResolver, (ByteBuffer) null, false, flatBufferCorruptionHandler);
    }

    @Nullable
    public static Flattenable resolveRootFlattenable(ByteBuffer byteBuffer, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, @Nullable ByteBuffer byteBuffer2, boolean z, @Nullable FlatBufferCorruptionHandler flatBufferCorruptionHandler) {
        return new MutableFlatBuffer(byteBuffer, byteBuffer2, z, flatBufferCorruptionHandler).resolveRootFlattenable(virtualFlattenableResolver);
    }

    public int getVectorLength(int i) {
        if (i == 0) {
            return 0;
        }
        try {
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return FlatBuffer.getVectorLength(this.mBaseBuffer, i);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return FlatBuffer.getVectorLength(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i));
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    public int getVectorLengthOrThrow(int i) {
        if (i != 0) {
            return getVectorLength(i);
        }
        throw new NullPointerException();
    }

    public short getShort(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        return getShort(i, i2, 0);
    }

    public int getInt(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        return getInt(i, i2, 0);
    }

    public boolean getBooleanOrThrow(int i, int i2) {
        if (i != 0) {
            return getBoolean(i, i2);
        }
        throw new NullPointerException();
    }

    public byte getByteOrThrow(int i, int i2) {
        if (i != 0) {
            return getByte(i, i2, (byte) 0);
        }
        throw new NullPointerException();
    }

    public short getShortOrThrow(int i, int i2) {
        if (i != 0) {
            return getShort(i, i2, 0);
        }
        throw new NullPointerException();
    }

    public int getIntOrThrow(int i, int i2) {
        if (i != 0) {
            return getInt(i, i2, 0);
        }
        throw new NullPointerException();
    }

    public long getLongOrThrow(int i, int i2) {
        if (i != 0) {
            return getLong(i, i2, 0);
        }
        throw new NullPointerException();
    }

    public double getDoubleOrThrow(int i, int i2) {
        if (i != 0) {
            return getDouble(i, i2, DEFAULT_VIEWMODEL_DOUBLE);
        }
        throw new NullPointerException();
    }

    @Nullable
    public ByteBuffer resolveByteBufferReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            return resolveByteBufferReference(i, i2);
        }
        throw new NullPointerException();
    }

    @Nullable
    public Iterator<Integer> resolveIntegerListReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            return resolveIntegerListReference(i, i2);
        }
        throw new NullPointerException();
    }

    @Nullable
    public Iterator<Long> resolveLongListReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            return resolveLongListReference(i, i2);
        }
        throw new NullPointerException();
    }

    @Nullable
    public Iterator<String> resolveStringListReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            return resolveStringListReference(i, i2);
        }
        throw new NullPointerException();
    }

    @Nullable
    public <T extends Enum> Iterator<T> resolveEnumStringListReferenceOrThrow(int i, int i2, Class<T> cls) {
        if (i != 0) {
            return resolveEnumStringListReference(i, i2, cls);
        }
        throw new NullPointerException();
    }

    @Nullable
    public Iterator<String> resolveStringDefStringListReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            return resolveStringDefStringListReference(i, i2);
        }
        throw new NullPointerException();
    }

    @Nullable
    public <T extends Flattenable> Iterator<T> resolveFlattenableListReferenceOrThrow(int i, int i2, Class<T> cls) {
        if (i != 0) {
            return resolveFlattenableListReference(i, i2, cls);
        }
        throw new NullPointerException();
    }

    @Nullable
    public String memoizeStringReferenceOrThrow(int i, int i2) {
        if (i != 0) {
            int referencePosition = getReferencePosition(i, i2);
            String str = (String) getOrMemoize(referencePosition, null);
            return str == null ? (String) getOrMemoize(referencePosition, resolveStringReference(i, i2)) : str;
        }
        throw new NullPointerException();
    }

    @Nullable
    public <T extends Enum<T>> T memoizeEnumStringReferenceOrThrow(int i, int i2, Class<T> cls, T t) {
        int referencePositionOrThrow = getReferencePositionOrThrow(i, i2);
        if (referencePositionOrThrow == 0) {
            return t;
        }
        T t2 = (T) ((Enum) getOrMemoize(referencePositionOrThrow, null));
        if (t2 != null) {
            return t2;
        }
        String resolveEnumString = resolveEnumString(i, i2);
        return resolveEnumString == null ? t : (T) ((Enum) getOrMemoize(referencePositionOrThrow, Enum.valueOf(cls, resolveEnumString.toUpperCase(Locale.US))));
    }

    @Nullable
    public <T extends Flattenable> T memoizeFlattenableReferenceOrThrow(int i, int i2, Class<T> cls) {
        int referencePositionOrThrow = getReferencePositionOrThrow(i, i2);
        if (referencePositionOrThrow == 0) {
            return null;
        }
        T t = (T) ((Flattenable) getOrMemoize(referencePositionOrThrow, null));
        return t == null ? (T) ((Flattenable) getOrMemoize(referencePositionOrThrow, resolveFlattenableAt(referencePositionOrThrow, cls))) : t;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.facebook.flatbuffers.MutableFlatBuffer */
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    private <T> RandomAccessList<T> memoizeListOrThrow(int i, int i2, Class<T> cls, int i3) {
        ByteBuffer byteBuffer;
        Object obj;
        int referencePositionOrThrow = getReferencePositionOrThrow(i, i2);
        if (referencePositionOrThrow == 0) {
            return null;
        }
        RandomAccessList<T> randomAccessList = (RandomAccessList) getOrMemoize(referencePositionOrThrow, null);
        if (randomAccessList != null) {
            return randomAccessList;
        }
        try {
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                byteBuffer = this.mBaseBuffer;
            } else {
                Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                byteBuffer = extensionForPosition.contentBuffer;
                i = extensionForPosition.convertToContentBufferPosition(i);
            }
            if (i3 == 0) {
                obj = FlatBuffer.resolveIntegerList(byteBuffer, i, i2);
            } else if (i3 == 1) {
                obj = FlatBuffer.resolveLongList(byteBuffer, i, i2);
            } else if (i3 == 2) {
                obj = FlatBuffer.resolveStringList(byteBuffer, i, i2);
            } else if (i3 == 4) {
                obj = FlatBuffer.resolveEnumStringList(byteBuffer, i, i2, cls);
            } else if (i3 == 5) {
                obj = FlatBuffer.resolveList(byteBuffer, i, i2, getFlattenableListAccessor(), cls);
            } else {
                throw new UnsupportedOperationException();
            }
            return (RandomAccessList) getOrMemoize(referencePositionOrThrow, obj);
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    private FlatBuffer.FlattenableListAccessor getFlattenableListAccessor() {
        if (this.flattenableListAccessor == null) {
            this.flattenableListAccessor = new FlatBuffer.FlattenableListAccessor() {
                /* class com.facebook.flatbuffers.MutableFlatBuffer.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.facebook.flatbuffers.FlatBuffer.FlattenableListAccessor
                public void initialize(Flattenable flattenable, ByteBuffer byteBuffer, int i) {
                    flattenable.initFromMutableFlatBuffer(MutableFlatBuffer.this, i);
                }
            };
        }
        return this.flattenableListAccessor;
    }

    @Nullable
    public RandomAccessList<Integer> memoizeIntegerListOrThrow(int i, int i2) {
        return memoizeListOrThrow(i, i2, Integer.class, 0);
    }

    @Nullable
    public RandomAccessList<Long> memoizeLongListOrThrow(int i, int i2) {
        return memoizeListOrThrow(i, i2, Long.class, 1);
    }

    @Nullable
    public RandomAccessList<String> memoizeStringListOrThrow(int i, int i2) {
        return memoizeListOrThrow(i, i2, String.class, 2);
    }

    @Nullable
    public <T extends Enum> RandomAccessList<T> memoizeEnumStringListOrThrow(int i, int i2, Class<T> cls) {
        return memoizeListOrThrow(i, i2, cls, 4);
    }

    @Nullable
    public <T extends Flattenable> RandomAccessList<T> memoizeFlattenableListOrThrow(int i, int i2, Class<T> cls) {
        return memoizeListOrThrow(i, i2, cls, 5);
    }

    public int getReferencePositionOrThrow(int i, int i2) {
        if (i != 0) {
            return getReferencePosition(i, i2);
        }
        throw new NullPointerException();
    }

    public int getVectorReferencePositionOrThrow(int i, int i2) {
        if (i != 0) {
            try {
                if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                    return FlatBuffer.getVectorReferencePosition(this.mBaseBuffer, i, i2);
                }
                Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
                return extensionForPosition.convertToGlobalPosition(FlatBuffer.getVectorReferencePosition(extensionForPosition.contentBuffer, extensionForPosition.convertToContentBufferPosition(i), i2));
            } catch (IndexOutOfBoundsException e) {
                throw handleCorruptionAndThrow(e);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public int getInt(int i) {
        try {
            if (this.mMutationBuffer == null || i < this.mBaseBuffer.capacity()) {
                return this.mBaseBuffer.getInt(i);
            }
            Extension extensionForPosition = this.mMutationBuffer.getExtensionForPosition(i);
            return extensionForPosition.contentBuffer.getInt(extensionForPosition.convertToContentBufferPosition(i));
        } catch (IndexOutOfBoundsException e) {
            throw handleCorruptionAndThrow(e);
        }
    }

    private synchronized DeltaBuffer getOrCreateMutationBuffer() {
        if (this.mMutationSupported) {
            if (this.mMutationBuffer == null) {
                this.mMutationBuffer = new DeltaBuffer(this.mBaseBuffer.capacity());
            }
        } else {
            throw new UnsupportedOperationException("Mutation support is not turned on.");
        }
        return this.mMutationBuffer;
    }

    @Nullable
    private <T> T getOrMemoize(int i, @Nullable T t) {
        T t2;
        if (i != 0) {
            synchronized (this) {
                SparseArray<Object> sparseArray = this.mMemoizedReferences;
                if (sparseArray != null && (t2 = (T) sparseArray.get(i)) != null) {
                    return t2;
                }
                if (t == null) {
                    return null;
                }
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                    this.mMemoizedReferences = sparseArray;
                }
                sparseArray.put(i, t);
                return t;
            }
        } else if (t == null || (t instanceof String)) {
            return t;
        } else {
            throw new UnsupportedOperationException("Cannot memoize non-null non-String at 0 offset");
        }
    }

    public void setIndexAccessTracker(@Nullable IndexAccessTracker indexAccessTracker) {
        this.mIndexAccessTracker = indexAccessTracker;
    }

    /* access modifiers changed from: private */
    public static class MutableFlattenableIterator<T extends Flattenable> implements Iterator<T> {
        @Nullable
        private final Class<T> clazz;
        private int current;
        @Nullable
        private final CloneableShim instance;
        private final int length;
        private final MutableFlatBuffer mb;
        @Nullable
        private final Flattenable.VirtualFlattenableResolver resolver;
        private final int start;

        MutableFlattenableIterator(MutableFlatBuffer mutableFlatBuffer, int i, int i2, Class<T> cls) {
            this.mb = mutableFlatBuffer;
            this.start = i;
            this.length = i2;
            this.clazz = cls;
            this.resolver = null;
            this.instance = null;
            this.current = 0;
        }

        MutableFlattenableIterator(MutableFlatBuffer mutableFlatBuffer, int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
            this.mb = mutableFlatBuffer;
            this.start = i;
            this.length = i2;
            this.clazz = null;
            this.resolver = virtualFlattenableResolver;
            this.instance = null;
            this.current = 0;
        }

        MutableFlattenableIterator(MutableFlatBuffer mutableFlatBuffer, int i, int i2, CloneableShim cloneableShim) {
            this.mb = mutableFlatBuffer;
            this.start = i;
            this.length = i2;
            this.clazz = null;
            this.resolver = null;
            this.instance = cloneableShim;
            this.current = 0;
        }

        public boolean hasNext() {
            return this.current < this.length;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.facebook.flatbuffers.MutableFlatBuffer */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        @Nullable
        public T next() {
            int i = this.current;
            if (i < 0 || i >= this.length) {
                throw new NoSuchElementException("Out of bound for iteration");
            }
            int i2 = this.start + (i * 4);
            this.current = i + 1;
            int i3 = this.mb.getInt(i2);
            if (i3 == 0) {
                return null;
            }
            Class<T> cls = this.clazz;
            if (cls != null) {
                return (T) this.mb.resolveFlattenableAt(i2 + i3, cls);
            }
            Flattenable.VirtualFlattenableResolver virtualFlattenableResolver = this.resolver;
            if (virtualFlattenableResolver != null) {
                return (T) this.mb.resolveFlattenable(i2 + i3, virtualFlattenableResolver);
            }
            CloneableShim cloneableShim = this.instance;
            if (cloneableShim != null) {
                Object obj = cloneableShim;
                if (this.current != this.length) {
                    obj = cloneableShim.shallowCopy();
                }
                return (T) this.mb.resolveFlattenableAt(i2 + i3, (Flattenable) obj);
            }
            throw new RuntimeException("Either clazz or resolver should be provided");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private FlatBufferCorruptionException handleCorruptionAndThrow(Exception exc) {
        FlatBufferCorruptionHandler flatBufferCorruptionHandler = this.mCorruptionHandler;
        if (flatBufferCorruptionHandler != null) {
            flatBufferCorruptionHandler.handleFlatBufferCorruption(exc);
        }
        throw new FlatBufferCorruptionException(getDebugLogs(), exc);
    }
}
