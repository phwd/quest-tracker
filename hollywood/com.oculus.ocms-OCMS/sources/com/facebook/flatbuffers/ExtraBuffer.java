package com.facebook.flatbuffers;

import android.util.SparseArray;
import com.facebook.flatbuffers.DeltaBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

public final class ExtraBuffer {
    private static final String EXTRA_BUFFER_IDENTIFIER = "EXTR";
    @Nullable
    private ByteBuffer mByteBuffer;
    @Nullable
    private SparseArray<DeltaBuffer.DeltaIndex> mExtraIndices;
    @Nullable
    private MutableFlatBuffer mImmutableFlatBuffer;
    private final Object mLock = new Object();
    @Nullable
    private SparseArray<Extra> mScratchExtra;

    protected ExtraBuffer() {
    }

    protected ExtraBuffer(ByteBuffer byteBuffer) {
        initFromByteBuffer(byteBuffer);
    }

    private void initFromByteBuffer(ByteBuffer byteBuffer) {
        this.mByteBuffer = byteBuffer != null ? byteBuffer.duplicate() : null;
        this.mImmutableFlatBuffer = null;
        ByteBuffer byteBuffer2 = this.mByteBuffer;
        if (byteBuffer2 != null) {
            byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
            int i = 0;
            while (i < 4) {
                if (this.mByteBuffer.get(i) == ((byte) EXTRA_BUFFER_IDENTIFIER.charAt(i))) {
                    i++;
                } else {
                    throw new RuntimeException("Extra buffer header is invalid");
                }
            }
            this.mExtraIndices = new SparseArray<>();
            int i2 = this.mByteBuffer.getInt(i);
            int i3 = i + 4;
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = this.mByteBuffer.getInt(i3);
                int i6 = i3 + 4;
                DeltaBuffer.DeltaIndex deltaIndex = new DeltaBuffer.DeltaIndex(this.mByteBuffer, i6);
                i3 = i6 + 16;
                this.mExtraIndices.put(i5, deltaIndex);
            }
            return;
        }
        this.mExtraIndices = null;
    }

    @Nullable
    private MutableFlatBuffer getImmutableFlatBuffer() {
        ByteBuffer byteBuffer;
        if (this.mImmutableFlatBuffer == null && (byteBuffer = this.mByteBuffer) != null) {
            this.mImmutableFlatBuffer = new MutableFlatBuffer(byteBuffer, null, false, null);
        }
        return this.mImmutableFlatBuffer;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ByteBuffer getByteBuffer() {
        ByteBuffer byteBuffer;
        synchronized (this.mLock) {
            byteBuffer = this.mByteBuffer;
        }
        return byteBuffer;
    }

    /* access modifiers changed from: protected */
    public <T extends Extra> T getExtra(int i, Flattenable flattenable, Class<T> cls) {
        DeltaBuffer.DeltaIndex deltaIndex;
        synchronized (this.mLock) {
            if (this.mScratchExtra != null) {
                int indexOfKey = this.mScratchExtra.indexOfKey(i);
                if (indexOfKey >= 0) {
                    return (T) this.mScratchExtra.valueAt(indexOfKey);
                }
            } else {
                this.mScratchExtra = new SparseArray<>();
            }
            try {
                T newInstance = cls.newInstance();
                if (!(this.mExtraIndices == null || (deltaIndex = this.mExtraIndices.get(i)) == null || deltaIndex.pivot == 0)) {
                    if (getImmutableFlatBuffer() == null) {
                        throw new RuntimeException("mByteBuffer should not be null.");
                    } else if (newInstance instanceof Flattenable) {
                        ((Flattenable) newInstance).initFromMutableFlatBuffer(getImmutableFlatBuffer(), deltaIndex.pivot);
                    } else {
                        throw new RuntimeException("object should implement Flattenable to save persistent states.");
                    }
                }
                newInstance.setBaseObject(flattenable);
                this.mScratchExtra.put(i, newInstance);
                return newInstance;
            } catch (InstantiationException e) {
                throw new RuntimeException("Not able to create object", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Access to constructor denied", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasChanged() {
        synchronized (this.mLock) {
            if (this.mScratchExtra != null) {
                if (this.mScratchExtra.size() != 0) {
                    for (int i = 0; i < this.mScratchExtra.size(); i++) {
                        Extra valueAt = this.mScratchExtra.valueAt(i);
                        if (valueAt == null) {
                            return true;
                        }
                        if (valueAt.isPersistentDataChanged() && (valueAt instanceof Flattenable)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean saveScratch() {
        synchronized (this.mLock) {
            if (this.mScratchExtra != null) {
                if (this.mScratchExtra.size() != 0) {
                    SparseArray sparseArray = new SparseArray();
                    FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(256);
                    for (int i = 0; i < this.mScratchExtra.size(); i++) {
                        int keyAt = this.mScratchExtra.keyAt(i);
                        Extra valueAt = this.mScratchExtra.valueAt(i);
                        if (valueAt == null) {
                            sparseArray.put(keyAt, new DeltaBuffer.DeltaIndex(0, 0, 0, 0));
                        } else if (valueAt.isPersistentDataChanged() && (valueAt instanceof Flattenable)) {
                            flatBufferBuilder.clearVtables();
                            int offset = flatBufferBuilder.offset();
                            sparseArray.put(keyAt, new DeltaBuffer.DeltaIndex(flatBufferBuilder.offset(), flatBufferBuilder.offset() - offset, flatBufferBuilder.createFlattenableReference((Flattenable) valueAt), 0));
                            valueAt.clearPersistentDataChanged();
                        }
                    }
                    if (sparseArray.size() == 0) {
                        return false;
                    }
                    if (this.mExtraIndices != null) {
                        if (this.mByteBuffer != null) {
                            for (int i2 = 0; i2 < this.mExtraIndices.size(); i2++) {
                                int keyAt2 = this.mExtraIndices.keyAt(i2);
                                if (sparseArray.indexOfKey(keyAt2) < 0) {
                                    DeltaBuffer.DeltaIndex valueAt2 = this.mExtraIndices.valueAt(i2);
                                    if (valueAt2.pivot == 0) {
                                        sparseArray.put(keyAt2, valueAt2);
                                    } else {
                                        flatBufferBuilder.prep(valueAt2.getAlign(), valueAt2.size);
                                        flatBufferBuilder.copyBytes(this.mByteBuffer.array(), valueAt2.start, valueAt2.size);
                                        int offset2 = flatBufferBuilder.offset();
                                        sparseArray.put(keyAt2, new DeltaBuffer.DeltaIndex(offset2, valueAt2.size, offset2 - (valueAt2.pivot - valueAt2.start), valueAt2.type));
                                    }
                                }
                            }
                        } else {
                            throw new RuntimeException("mByteBuffer for ExtraBuffer should not be null");
                        }
                    }
                    flatBufferBuilder.prep(4, 0);
                    flatBufferBuilder.prep(flatBufferBuilder.getMinalign(), ((sparseArray.size() * 4) + 2) * 4);
                    for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                        int keyAt3 = sparseArray.keyAt(i3);
                        ((DeltaBuffer.DeltaIndex) sparseArray.valueAt(i3)).writeToBuffer(flatBufferBuilder);
                        flatBufferBuilder.addInt(keyAt3);
                    }
                    flatBufferBuilder.addInt(sparseArray.size());
                    for (int i4 = 3; i4 >= 0; i4--) {
                        flatBufferBuilder.addByte((byte) EXTRA_BUFFER_IDENTIFIER.charAt(i4));
                    }
                    initFromByteBuffer(ByteBuffer.wrap(flatBufferBuilder.sizedByteArray()));
                    return true;
                }
            }
            return false;
        }
    }
}
