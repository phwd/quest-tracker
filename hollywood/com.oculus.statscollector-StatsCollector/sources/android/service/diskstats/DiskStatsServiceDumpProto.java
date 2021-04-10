package android.service.diskstats;

import android.service.diskstats.DiskStatsCachedValuesProto;
import android.service.diskstats.DiskStatsFreeSpaceProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class DiskStatsServiceDumpProto extends GeneratedMessageLite<DiskStatsServiceDumpProto, Builder> implements DiskStatsServiceDumpProtoOrBuilder {
    public static final int BENCHMARKED_WRITE_SPEED_KBPS_FIELD_NUMBER = 7;
    public static final int CACHED_FOLDER_SIZES_FIELD_NUMBER = 6;
    private static final DiskStatsServiceDumpProto DEFAULT_INSTANCE = new DiskStatsServiceDumpProto();
    public static final int ENCRYPTION_FIELD_NUMBER = 5;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    public static final int HAS_TEST_ERROR_FIELD_NUMBER = 1;
    private static volatile Parser<DiskStatsServiceDumpProto> PARSER = null;
    public static final int PARTITIONS_FREE_SPACE_FIELD_NUMBER = 4;
    public static final int WRITE_512B_LATENCY_MILLIS_FIELD_NUMBER = 3;
    private int benchmarkedWriteSpeedKbps_ = 0;
    private int bitField0_;
    private DiskStatsCachedValuesProto cachedFolderSizes_;
    private int encryption_ = 0;
    private String errorMessage_ = "";
    private boolean hasTestError_ = false;
    private Internal.ProtobufList<DiskStatsFreeSpaceProto> partitionsFreeSpace_ = emptyProtobufList();
    private int write512BLatencyMillis_ = 0;

    private DiskStatsServiceDumpProto() {
    }

    public enum EncryptionType implements Internal.EnumLite {
        ENCRYPTION_UNKNOWN(0),
        ENCRYPTION_NONE(1),
        ENCRYPTION_FULL_DISK(2),
        ENCRYPTION_FILE_BASED(3);
        
        public static final int ENCRYPTION_FILE_BASED_VALUE = 3;
        public static final int ENCRYPTION_FULL_DISK_VALUE = 2;
        public static final int ENCRYPTION_NONE_VALUE = 1;
        public static final int ENCRYPTION_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<EncryptionType> internalValueMap = new Internal.EnumLiteMap<EncryptionType>() {
            /* class android.service.diskstats.DiskStatsServiceDumpProto.EncryptionType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EncryptionType findValueByNumber(int number) {
                return EncryptionType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static EncryptionType valueOf(int value2) {
            return forNumber(value2);
        }

        public static EncryptionType forNumber(int value2) {
            if (value2 == 0) {
                return ENCRYPTION_UNKNOWN;
            }
            if (value2 == 1) {
                return ENCRYPTION_NONE;
            }
            if (value2 == 2) {
                return ENCRYPTION_FULL_DISK;
            }
            if (value2 != 3) {
                return null;
            }
            return ENCRYPTION_FILE_BASED;
        }

        public static Internal.EnumLiteMap<EncryptionType> internalGetValueMap() {
            return internalValueMap;
        }

        private EncryptionType(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasHasTestError() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean getHasTestError() {
        return this.hasTestError_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasTestError(boolean value) {
        this.bitField0_ |= 1;
        this.hasTestError_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasTestError() {
        this.bitField0_ &= -2;
        this.hasTestError_ = false;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasErrorMessage() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public String getErrorMessage() {
        return this.errorMessage_;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public ByteString getErrorMessageBytes() {
        return ByteString.copyFromUtf8(this.errorMessage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setErrorMessage(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.errorMessage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearErrorMessage() {
        this.bitField0_ &= -3;
        this.errorMessage_ = getDefaultInstance().getErrorMessage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setErrorMessageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.errorMessage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasWrite512BLatencyMillis() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public int getWrite512BLatencyMillis() {
        return this.write512BLatencyMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWrite512BLatencyMillis(int value) {
        this.bitField0_ |= 4;
        this.write512BLatencyMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWrite512BLatencyMillis() {
        this.bitField0_ &= -5;
        this.write512BLatencyMillis_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public List<DiskStatsFreeSpaceProto> getPartitionsFreeSpaceList() {
        return this.partitionsFreeSpace_;
    }

    public List<? extends DiskStatsFreeSpaceProtoOrBuilder> getPartitionsFreeSpaceOrBuilderList() {
        return this.partitionsFreeSpace_;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public int getPartitionsFreeSpaceCount() {
        return this.partitionsFreeSpace_.size();
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public DiskStatsFreeSpaceProto getPartitionsFreeSpace(int index) {
        return this.partitionsFreeSpace_.get(index);
    }

    public DiskStatsFreeSpaceProtoOrBuilder getPartitionsFreeSpaceOrBuilder(int index) {
        return this.partitionsFreeSpace_.get(index);
    }

    private void ensurePartitionsFreeSpaceIsMutable() {
        if (!this.partitionsFreeSpace_.isModifiable()) {
            this.partitionsFreeSpace_ = GeneratedMessageLite.mutableCopy(this.partitionsFreeSpace_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto value) {
        if (value != null) {
            ensurePartitionsFreeSpaceIsMutable();
            this.partitionsFreeSpace_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto.Builder builderForValue) {
        ensurePartitionsFreeSpaceIsMutable();
        this.partitionsFreeSpace_.set(index, (DiskStatsFreeSpaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPartitionsFreeSpace(DiskStatsFreeSpaceProto value) {
        if (value != null) {
            ensurePartitionsFreeSpaceIsMutable();
            this.partitionsFreeSpace_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto value) {
        if (value != null) {
            ensurePartitionsFreeSpaceIsMutable();
            this.partitionsFreeSpace_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPartitionsFreeSpace(DiskStatsFreeSpaceProto.Builder builderForValue) {
        ensurePartitionsFreeSpaceIsMutable();
        this.partitionsFreeSpace_.add((DiskStatsFreeSpaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto.Builder builderForValue) {
        ensurePartitionsFreeSpaceIsMutable();
        this.partitionsFreeSpace_.add(index, (DiskStatsFreeSpaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPartitionsFreeSpace(Iterable<? extends DiskStatsFreeSpaceProto> values) {
        ensurePartitionsFreeSpaceIsMutable();
        AbstractMessageLite.addAll(values, this.partitionsFreeSpace_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPartitionsFreeSpace() {
        this.partitionsFreeSpace_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePartitionsFreeSpace(int index) {
        ensurePartitionsFreeSpaceIsMutable();
        this.partitionsFreeSpace_.remove(index);
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasEncryption() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public EncryptionType getEncryption() {
        EncryptionType result = EncryptionType.forNumber(this.encryption_);
        return result == null ? EncryptionType.ENCRYPTION_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEncryption(EncryptionType value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.encryption_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEncryption() {
        this.bitField0_ &= -9;
        this.encryption_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasCachedFolderSizes() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public DiskStatsCachedValuesProto getCachedFolderSizes() {
        DiskStatsCachedValuesProto diskStatsCachedValuesProto = this.cachedFolderSizes_;
        return diskStatsCachedValuesProto == null ? DiskStatsCachedValuesProto.getDefaultInstance() : diskStatsCachedValuesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedFolderSizes(DiskStatsCachedValuesProto value) {
        if (value != null) {
            this.cachedFolderSizes_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedFolderSizes(DiskStatsCachedValuesProto.Builder builderForValue) {
        this.cachedFolderSizes_ = (DiskStatsCachedValuesProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCachedFolderSizes(DiskStatsCachedValuesProto value) {
        DiskStatsCachedValuesProto diskStatsCachedValuesProto = this.cachedFolderSizes_;
        if (diskStatsCachedValuesProto == null || diskStatsCachedValuesProto == DiskStatsCachedValuesProto.getDefaultInstance()) {
            this.cachedFolderSizes_ = value;
        } else {
            this.cachedFolderSizes_ = (DiskStatsCachedValuesProto) ((DiskStatsCachedValuesProto.Builder) DiskStatsCachedValuesProto.newBuilder(this.cachedFolderSizes_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCachedFolderSizes() {
        this.cachedFolderSizes_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public boolean hasBenchmarkedWriteSpeedKbps() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
    public int getBenchmarkedWriteSpeedKbps() {
        return this.benchmarkedWriteSpeedKbps_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBenchmarkedWriteSpeedKbps(int value) {
        this.bitField0_ |= 32;
        this.benchmarkedWriteSpeedKbps_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBenchmarkedWriteSpeedKbps() {
        this.bitField0_ &= -33;
        this.benchmarkedWriteSpeedKbps_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.hasTestError_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getErrorMessage());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.write512BLatencyMillis_);
        }
        for (int i = 0; i < this.partitionsFreeSpace_.size(); i++) {
            output.writeMessage(4, this.partitionsFreeSpace_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(5, this.encryption_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getCachedFolderSizes());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(7, this.benchmarkedWriteSpeedKbps_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.hasTestError_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getErrorMessage());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.write512BLatencyMillis_);
        }
        for (int i = 0; i < this.partitionsFreeSpace_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.partitionsFreeSpace_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(5, this.encryption_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getCachedFolderSizes());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(7, this.benchmarkedWriteSpeedKbps_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DiskStatsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DiskStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DiskStatsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DiskStatsServiceDumpProto, Builder> implements DiskStatsServiceDumpProtoOrBuilder {
        private Builder() {
            super(DiskStatsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasHasTestError() {
            return ((DiskStatsServiceDumpProto) this.instance).hasHasTestError();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean getHasTestError() {
            return ((DiskStatsServiceDumpProto) this.instance).getHasTestError();
        }

        public Builder setHasTestError(boolean value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setHasTestError(value);
            return this;
        }

        public Builder clearHasTestError() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearHasTestError();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasErrorMessage() {
            return ((DiskStatsServiceDumpProto) this.instance).hasErrorMessage();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public String getErrorMessage() {
            return ((DiskStatsServiceDumpProto) this.instance).getErrorMessage();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public ByteString getErrorMessageBytes() {
            return ((DiskStatsServiceDumpProto) this.instance).getErrorMessageBytes();
        }

        public Builder setErrorMessage(String value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setErrorMessage(value);
            return this;
        }

        public Builder clearErrorMessage() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearErrorMessage();
            return this;
        }

        public Builder setErrorMessageBytes(ByteString value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setErrorMessageBytes(value);
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasWrite512BLatencyMillis() {
            return ((DiskStatsServiceDumpProto) this.instance).hasWrite512BLatencyMillis();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public int getWrite512BLatencyMillis() {
            return ((DiskStatsServiceDumpProto) this.instance).getWrite512BLatencyMillis();
        }

        public Builder setWrite512BLatencyMillis(int value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setWrite512BLatencyMillis(value);
            return this;
        }

        public Builder clearWrite512BLatencyMillis() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearWrite512BLatencyMillis();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public List<DiskStatsFreeSpaceProto> getPartitionsFreeSpaceList() {
            return Collections.unmodifiableList(((DiskStatsServiceDumpProto) this.instance).getPartitionsFreeSpaceList());
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public int getPartitionsFreeSpaceCount() {
            return ((DiskStatsServiceDumpProto) this.instance).getPartitionsFreeSpaceCount();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public DiskStatsFreeSpaceProto getPartitionsFreeSpace(int index) {
            return ((DiskStatsServiceDumpProto) this.instance).getPartitionsFreeSpace(index);
        }

        public Builder setPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setPartitionsFreeSpace((DiskStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setPartitionsFreeSpace((DiskStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPartitionsFreeSpace(DiskStatsFreeSpaceProto value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).addPartitionsFreeSpace((DiskStatsServiceDumpProto) value);
            return this;
        }

        public Builder addPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).addPartitionsFreeSpace((DiskStatsServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPartitionsFreeSpace(DiskStatsFreeSpaceProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).addPartitionsFreeSpace((DiskStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPartitionsFreeSpace(int index, DiskStatsFreeSpaceProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).addPartitionsFreeSpace((DiskStatsServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPartitionsFreeSpace(Iterable<? extends DiskStatsFreeSpaceProto> values) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).addAllPartitionsFreeSpace(values);
            return this;
        }

        public Builder clearPartitionsFreeSpace() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearPartitionsFreeSpace();
            return this;
        }

        public Builder removePartitionsFreeSpace(int index) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).removePartitionsFreeSpace(index);
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasEncryption() {
            return ((DiskStatsServiceDumpProto) this.instance).hasEncryption();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public EncryptionType getEncryption() {
            return ((DiskStatsServiceDumpProto) this.instance).getEncryption();
        }

        public Builder setEncryption(EncryptionType value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setEncryption(value);
            return this;
        }

        public Builder clearEncryption() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearEncryption();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasCachedFolderSizes() {
            return ((DiskStatsServiceDumpProto) this.instance).hasCachedFolderSizes();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public DiskStatsCachedValuesProto getCachedFolderSizes() {
            return ((DiskStatsServiceDumpProto) this.instance).getCachedFolderSizes();
        }

        public Builder setCachedFolderSizes(DiskStatsCachedValuesProto value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setCachedFolderSizes((DiskStatsServiceDumpProto) value);
            return this;
        }

        public Builder setCachedFolderSizes(DiskStatsCachedValuesProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setCachedFolderSizes((DiskStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeCachedFolderSizes(DiskStatsCachedValuesProto value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).mergeCachedFolderSizes(value);
            return this;
        }

        public Builder clearCachedFolderSizes() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearCachedFolderSizes();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public boolean hasBenchmarkedWriteSpeedKbps() {
            return ((DiskStatsServiceDumpProto) this.instance).hasBenchmarkedWriteSpeedKbps();
        }

        @Override // android.service.diskstats.DiskStatsServiceDumpProtoOrBuilder
        public int getBenchmarkedWriteSpeedKbps() {
            return ((DiskStatsServiceDumpProto) this.instance).getBenchmarkedWriteSpeedKbps();
        }

        public Builder setBenchmarkedWriteSpeedKbps(int value) {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).setBenchmarkedWriteSpeedKbps(value);
            return this;
        }

        public Builder clearBenchmarkedWriteSpeedKbps() {
            copyOnWrite();
            ((DiskStatsServiceDumpProto) this.instance).clearBenchmarkedWriteSpeedKbps();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DiskStatsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.partitionsFreeSpace_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DiskStatsServiceDumpProto other = (DiskStatsServiceDumpProto) arg1;
                this.hasTestError_ = visitor.visitBoolean(hasHasTestError(), this.hasTestError_, other.hasHasTestError(), other.hasTestError_);
                this.errorMessage_ = visitor.visitString(hasErrorMessage(), this.errorMessage_, other.hasErrorMessage(), other.errorMessage_);
                this.write512BLatencyMillis_ = visitor.visitInt(hasWrite512BLatencyMillis(), this.write512BLatencyMillis_, other.hasWrite512BLatencyMillis(), other.write512BLatencyMillis_);
                this.partitionsFreeSpace_ = visitor.visitList(this.partitionsFreeSpace_, other.partitionsFreeSpace_);
                this.encryption_ = visitor.visitInt(hasEncryption(), this.encryption_, other.hasEncryption(), other.encryption_);
                this.cachedFolderSizes_ = (DiskStatsCachedValuesProto) visitor.visitMessage(this.cachedFolderSizes_, other.cachedFolderSizes_);
                this.benchmarkedWriteSpeedKbps_ = visitor.visitInt(hasBenchmarkedWriteSpeedKbps(), this.benchmarkedWriteSpeedKbps_, other.hasBenchmarkedWriteSpeedKbps(), other.benchmarkedWriteSpeedKbps_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.hasTestError_ = input.readBool();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.errorMessage_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.write512BLatencyMillis_ = input.readInt32();
                        } else if (tag == 34) {
                            if (!this.partitionsFreeSpace_.isModifiable()) {
                                this.partitionsFreeSpace_ = GeneratedMessageLite.mutableCopy(this.partitionsFreeSpace_);
                            }
                            this.partitionsFreeSpace_.add((DiskStatsFreeSpaceProto) input.readMessage(DiskStatsFreeSpaceProto.parser(), extensionRegistry));
                        } else if (tag == 40) {
                            int rawValue = input.readEnum();
                            if (EncryptionType.forNumber(rawValue) == null) {
                                super.mergeVarintField(5, rawValue);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.encryption_ = rawValue;
                            }
                        } else if (tag == 50) {
                            DiskStatsCachedValuesProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder = (DiskStatsCachedValuesProto.Builder) this.cachedFolderSizes_.toBuilder();
                            }
                            this.cachedFolderSizes_ = (DiskStatsCachedValuesProto) input.readMessage(DiskStatsCachedValuesProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.cachedFolderSizes_);
                                this.cachedFolderSizes_ = (DiskStatsCachedValuesProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 16;
                        } else if (tag == 56) {
                            this.bitField0_ |= 32;
                            this.benchmarkedWriteSpeedKbps_ = input.readInt32();
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (DiskStatsServiceDumpProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static DiskStatsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiskStatsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
