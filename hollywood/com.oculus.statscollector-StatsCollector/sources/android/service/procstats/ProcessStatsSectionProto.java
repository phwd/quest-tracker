package android.service.procstats;

import android.service.procstats.ProcessStatsAvailablePagesProto;
import android.service.procstats.ProcessStatsPackageProto;
import android.service.procstats.ProcessStatsProto;
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

public final class ProcessStatsSectionProto extends GeneratedMessageLite<ProcessStatsSectionProto, Builder> implements ProcessStatsSectionProtoOrBuilder {
    public static final int AVAILABLE_PAGES_FIELD_NUMBER = 10;
    private static final ProcessStatsSectionProto DEFAULT_INSTANCE = new ProcessStatsSectionProto();
    public static final int END_REALTIME_MS_FIELD_NUMBER = 2;
    public static final int END_UPTIME_MS_FIELD_NUMBER = 4;
    public static final int HAS_SWAPPED_PSS_FIELD_NUMBER = 6;
    public static final int PACKAGE_STATS_FIELD_NUMBER = 9;
    private static volatile Parser<ProcessStatsSectionProto> PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 8;
    public static final int RUNTIME_FIELD_NUMBER = 5;
    public static final int START_REALTIME_MS_FIELD_NUMBER = 1;
    public static final int START_UPTIME_MS_FIELD_NUMBER = 3;
    public static final int STATUS_FIELD_NUMBER = 7;
    private static final Internal.ListAdapter.Converter<Integer, Status> status_converter_ = new Internal.ListAdapter.Converter<Integer, Status>() {
        /* class android.service.procstats.ProcessStatsSectionProto.AnonymousClass1 */

        public Status convert(Integer from) {
            Status result = Status.forNumber(from.intValue());
            return result == null ? Status.STATUS_UNKNOWN : result;
        }
    };
    private Internal.ProtobufList<ProcessStatsAvailablePagesProto> availablePages_ = emptyProtobufList();
    private int bitField0_;
    private long endRealtimeMs_ = 0;
    private long endUptimeMs_ = 0;
    private boolean hasSwappedPss_ = false;
    private Internal.ProtobufList<ProcessStatsPackageProto> packageStats_ = emptyProtobufList();
    private Internal.ProtobufList<ProcessStatsProto> processStats_ = emptyProtobufList();
    private String runtime_ = "";
    private long startRealtimeMs_ = 0;
    private long startUptimeMs_ = 0;
    private Internal.IntList status_ = emptyIntList();

    private ProcessStatsSectionProto() {
    }

    public enum Status implements Internal.EnumLite {
        STATUS_UNKNOWN(0),
        STATUS_COMPLETE(1),
        STATUS_PARTIAL(2),
        STATUS_SHUTDOWN(3),
        STATUS_SYSPROPS(4);
        
        public static final int STATUS_COMPLETE_VALUE = 1;
        public static final int STATUS_PARTIAL_VALUE = 2;
        public static final int STATUS_SHUTDOWN_VALUE = 3;
        public static final int STATUS_SYSPROPS_VALUE = 4;
        public static final int STATUS_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {
            /* class android.service.procstats.ProcessStatsSectionProto.Status.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Status findValueByNumber(int number) {
                return Status.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Status valueOf(int value2) {
            return forNumber(value2);
        }

        public static Status forNumber(int value2) {
            if (value2 == 0) {
                return STATUS_UNKNOWN;
            }
            if (value2 == 1) {
                return STATUS_COMPLETE;
            }
            if (value2 == 2) {
                return STATUS_PARTIAL;
            }
            if (value2 == 3) {
                return STATUS_SHUTDOWN;
            }
            if (value2 != 4) {
                return null;
            }
            return STATUS_SYSPROPS;
        }

        public static Internal.EnumLiteMap<Status> internalGetValueMap() {
            return internalValueMap;
        }

        private Status(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasStartRealtimeMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public long getStartRealtimeMs() {
        return this.startRealtimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartRealtimeMs(long value) {
        this.bitField0_ |= 1;
        this.startRealtimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartRealtimeMs() {
        this.bitField0_ &= -2;
        this.startRealtimeMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasEndRealtimeMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public long getEndRealtimeMs() {
        return this.endRealtimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndRealtimeMs(long value) {
        this.bitField0_ |= 2;
        this.endRealtimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndRealtimeMs() {
        this.bitField0_ &= -3;
        this.endRealtimeMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasStartUptimeMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public long getStartUptimeMs() {
        return this.startUptimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartUptimeMs(long value) {
        this.bitField0_ |= 4;
        this.startUptimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartUptimeMs() {
        this.bitField0_ &= -5;
        this.startUptimeMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasEndUptimeMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public long getEndUptimeMs() {
        return this.endUptimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndUptimeMs(long value) {
        this.bitField0_ |= 8;
        this.endUptimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndUptimeMs() {
        this.bitField0_ &= -9;
        this.endUptimeMs_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasRuntime() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public String getRuntime() {
        return this.runtime_;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public ByteString getRuntimeBytes() {
        return ByteString.copyFromUtf8(this.runtime_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRuntime(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.runtime_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRuntime() {
        this.bitField0_ &= -17;
        this.runtime_ = getDefaultInstance().getRuntime();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRuntimeBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.runtime_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean hasHasSwappedPss() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public boolean getHasSwappedPss() {
        return this.hasSwappedPss_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasSwappedPss(boolean value) {
        this.bitField0_ |= 32;
        this.hasSwappedPss_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasSwappedPss() {
        this.bitField0_ &= -33;
        this.hasSwappedPss_ = false;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public List<Status> getStatusList() {
        return new Internal.ListAdapter(this.status_, status_converter_);
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public int getStatusCount() {
        return this.status_.size();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public Status getStatus(int index) {
        return status_converter_.convert(Integer.valueOf(this.status_.getInt(index)));
    }

    private void ensureStatusIsMutable() {
        if (!this.status_.isModifiable()) {
            this.status_ = GeneratedMessageLite.mutableCopy(this.status_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(int index, Status value) {
        if (value != null) {
            ensureStatusIsMutable();
            this.status_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStatus(Status value) {
        if (value != null) {
            ensureStatusIsMutable();
            this.status_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStatus(Iterable<? extends Status> values) {
        ensureStatusIsMutable();
        for (Status value : values) {
            this.status_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.status_ = emptyIntList();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public List<ProcessStatsAvailablePagesProto> getAvailablePagesList() {
        return this.availablePages_;
    }

    public List<? extends ProcessStatsAvailablePagesProtoOrBuilder> getAvailablePagesOrBuilderList() {
        return this.availablePages_;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public int getAvailablePagesCount() {
        return this.availablePages_.size();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public ProcessStatsAvailablePagesProto getAvailablePages(int index) {
        return this.availablePages_.get(index);
    }

    public ProcessStatsAvailablePagesProtoOrBuilder getAvailablePagesOrBuilder(int index) {
        return this.availablePages_.get(index);
    }

    private void ensureAvailablePagesIsMutable() {
        if (!this.availablePages_.isModifiable()) {
            this.availablePages_ = GeneratedMessageLite.mutableCopy(this.availablePages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAvailablePages(int index, ProcessStatsAvailablePagesProto value) {
        if (value != null) {
            ensureAvailablePagesIsMutable();
            this.availablePages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAvailablePages(int index, ProcessStatsAvailablePagesProto.Builder builderForValue) {
        ensureAvailablePagesIsMutable();
        this.availablePages_.set(index, (ProcessStatsAvailablePagesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAvailablePages(ProcessStatsAvailablePagesProto value) {
        if (value != null) {
            ensureAvailablePagesIsMutable();
            this.availablePages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAvailablePages(int index, ProcessStatsAvailablePagesProto value) {
        if (value != null) {
            ensureAvailablePagesIsMutable();
            this.availablePages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAvailablePages(ProcessStatsAvailablePagesProto.Builder builderForValue) {
        ensureAvailablePagesIsMutable();
        this.availablePages_.add((ProcessStatsAvailablePagesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAvailablePages(int index, ProcessStatsAvailablePagesProto.Builder builderForValue) {
        ensureAvailablePagesIsMutable();
        this.availablePages_.add(index, (ProcessStatsAvailablePagesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAvailablePages(Iterable<? extends ProcessStatsAvailablePagesProto> values) {
        ensureAvailablePagesIsMutable();
        AbstractMessageLite.addAll(values, this.availablePages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAvailablePages() {
        this.availablePages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAvailablePages(int index) {
        ensureAvailablePagesIsMutable();
        this.availablePages_.remove(index);
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public List<ProcessStatsProto> getProcessStatsList() {
        return this.processStats_;
    }

    public List<? extends ProcessStatsProtoOrBuilder> getProcessStatsOrBuilderList() {
        return this.processStats_;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public int getProcessStatsCount() {
        return this.processStats_.size();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public ProcessStatsProto getProcessStats(int index) {
        return this.processStats_.get(index);
    }

    public ProcessStatsProtoOrBuilder getProcessStatsOrBuilder(int index) {
        return this.processStats_.get(index);
    }

    private void ensureProcessStatsIsMutable() {
        if (!this.processStats_.isModifiable()) {
            this.processStats_ = GeneratedMessageLite.mutableCopy(this.processStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessStats(int index, ProcessStatsProto value) {
        if (value != null) {
            ensureProcessStatsIsMutable();
            this.processStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
        ensureProcessStatsIsMutable();
        this.processStats_.set(index, (ProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessStats(ProcessStatsProto value) {
        if (value != null) {
            ensureProcessStatsIsMutable();
            this.processStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessStats(int index, ProcessStatsProto value) {
        if (value != null) {
            ensureProcessStatsIsMutable();
            this.processStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessStats(ProcessStatsProto.Builder builderForValue) {
        ensureProcessStatsIsMutable();
        this.processStats_.add((ProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
        ensureProcessStatsIsMutable();
        this.processStats_.add(index, (ProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProcessStats(Iterable<? extends ProcessStatsProto> values) {
        ensureProcessStatsIsMutable();
        AbstractMessageLite.addAll(values, this.processStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessStats() {
        this.processStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProcessStats(int index) {
        ensureProcessStatsIsMutable();
        this.processStats_.remove(index);
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public List<ProcessStatsPackageProto> getPackageStatsList() {
        return this.packageStats_;
    }

    public List<? extends ProcessStatsPackageProtoOrBuilder> getPackageStatsOrBuilderList() {
        return this.packageStats_;
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public int getPackageStatsCount() {
        return this.packageStats_.size();
    }

    @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
    public ProcessStatsPackageProto getPackageStats(int index) {
        return this.packageStats_.get(index);
    }

    public ProcessStatsPackageProtoOrBuilder getPackageStatsOrBuilder(int index) {
        return this.packageStats_.get(index);
    }

    private void ensurePackageStatsIsMutable() {
        if (!this.packageStats_.isModifiable()) {
            this.packageStats_ = GeneratedMessageLite.mutableCopy(this.packageStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageStats(int index, ProcessStatsPackageProto value) {
        if (value != null) {
            ensurePackageStatsIsMutable();
            this.packageStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageStats(int index, ProcessStatsPackageProto.Builder builderForValue) {
        ensurePackageStatsIsMutable();
        this.packageStats_.set(index, (ProcessStatsPackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageStats(ProcessStatsPackageProto value) {
        if (value != null) {
            ensurePackageStatsIsMutable();
            this.packageStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageStats(int index, ProcessStatsPackageProto value) {
        if (value != null) {
            ensurePackageStatsIsMutable();
            this.packageStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageStats(ProcessStatsPackageProto.Builder builderForValue) {
        ensurePackageStatsIsMutable();
        this.packageStats_.add((ProcessStatsPackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageStats(int index, ProcessStatsPackageProto.Builder builderForValue) {
        ensurePackageStatsIsMutable();
        this.packageStats_.add(index, (ProcessStatsPackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackageStats(Iterable<? extends ProcessStatsPackageProto> values) {
        ensurePackageStatsIsMutable();
        AbstractMessageLite.addAll(values, this.packageStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageStats() {
        this.packageStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackageStats(int index) {
        ensurePackageStatsIsMutable();
        this.packageStats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.startRealtimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.endRealtimeMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.startUptimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.endUptimeMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getRuntime());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.hasSwappedPss_);
        }
        for (int i = 0; i < this.status_.size(); i++) {
            output.writeEnum(7, this.status_.getInt(i));
        }
        for (int i2 = 0; i2 < this.processStats_.size(); i2++) {
            output.writeMessage(8, this.processStats_.get(i2));
        }
        for (int i3 = 0; i3 < this.packageStats_.size(); i3++) {
            output.writeMessage(9, this.packageStats_.get(i3));
        }
        for (int i4 = 0; i4 < this.availablePages_.size(); i4++) {
            output.writeMessage(10, this.availablePages_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startRealtimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.endRealtimeMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.startUptimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.endUptimeMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getRuntime());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.hasSwappedPss_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.status_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.status_.getInt(i));
        }
        int size3 = size2 + dataSize + (this.status_.size() * 1);
        for (int i2 = 0; i2 < this.processStats_.size(); i2++) {
            size3 += CodedOutputStream.computeMessageSize(8, this.processStats_.get(i2));
        }
        for (int i3 = 0; i3 < this.packageStats_.size(); i3++) {
            size3 += CodedOutputStream.computeMessageSize(9, this.packageStats_.get(i3));
        }
        for (int i4 = 0; i4 < this.availablePages_.size(); i4++) {
            size3 += CodedOutputStream.computeMessageSize(10, this.availablePages_.get(i4));
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static ProcessStatsSectionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsSectionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsSectionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsSectionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsSectionProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsSectionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsSectionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsSectionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsSectionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsSectionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsSectionProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsSectionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsSectionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsSectionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsSectionProto, Builder> implements ProcessStatsSectionProtoOrBuilder {
        private Builder() {
            super(ProcessStatsSectionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasStartRealtimeMs() {
            return ((ProcessStatsSectionProto) this.instance).hasStartRealtimeMs();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public long getStartRealtimeMs() {
            return ((ProcessStatsSectionProto) this.instance).getStartRealtimeMs();
        }

        public Builder setStartRealtimeMs(long value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setStartRealtimeMs(value);
            return this;
        }

        public Builder clearStartRealtimeMs() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearStartRealtimeMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasEndRealtimeMs() {
            return ((ProcessStatsSectionProto) this.instance).hasEndRealtimeMs();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public long getEndRealtimeMs() {
            return ((ProcessStatsSectionProto) this.instance).getEndRealtimeMs();
        }

        public Builder setEndRealtimeMs(long value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setEndRealtimeMs(value);
            return this;
        }

        public Builder clearEndRealtimeMs() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearEndRealtimeMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasStartUptimeMs() {
            return ((ProcessStatsSectionProto) this.instance).hasStartUptimeMs();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public long getStartUptimeMs() {
            return ((ProcessStatsSectionProto) this.instance).getStartUptimeMs();
        }

        public Builder setStartUptimeMs(long value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setStartUptimeMs(value);
            return this;
        }

        public Builder clearStartUptimeMs() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearStartUptimeMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasEndUptimeMs() {
            return ((ProcessStatsSectionProto) this.instance).hasEndUptimeMs();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public long getEndUptimeMs() {
            return ((ProcessStatsSectionProto) this.instance).getEndUptimeMs();
        }

        public Builder setEndUptimeMs(long value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setEndUptimeMs(value);
            return this;
        }

        public Builder clearEndUptimeMs() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearEndUptimeMs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasRuntime() {
            return ((ProcessStatsSectionProto) this.instance).hasRuntime();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public String getRuntime() {
            return ((ProcessStatsSectionProto) this.instance).getRuntime();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public ByteString getRuntimeBytes() {
            return ((ProcessStatsSectionProto) this.instance).getRuntimeBytes();
        }

        public Builder setRuntime(String value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setRuntime(value);
            return this;
        }

        public Builder clearRuntime() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearRuntime();
            return this;
        }

        public Builder setRuntimeBytes(ByteString value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setRuntimeBytes(value);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean hasHasSwappedPss() {
            return ((ProcessStatsSectionProto) this.instance).hasHasSwappedPss();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public boolean getHasSwappedPss() {
            return ((ProcessStatsSectionProto) this.instance).getHasSwappedPss();
        }

        public Builder setHasSwappedPss(boolean value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setHasSwappedPss(value);
            return this;
        }

        public Builder clearHasSwappedPss() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearHasSwappedPss();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public List<Status> getStatusList() {
            return ((ProcessStatsSectionProto) this.instance).getStatusList();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public int getStatusCount() {
            return ((ProcessStatsSectionProto) this.instance).getStatusCount();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public Status getStatus(int index) {
            return ((ProcessStatsSectionProto) this.instance).getStatus(index);
        }

        public Builder setStatus(int index, Status value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setStatus(index, value);
            return this;
        }

        public Builder addStatus(Status value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addStatus(value);
            return this;
        }

        public Builder addAllStatus(Iterable<? extends Status> values) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAllStatus(values);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearStatus();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public List<ProcessStatsAvailablePagesProto> getAvailablePagesList() {
            return Collections.unmodifiableList(((ProcessStatsSectionProto) this.instance).getAvailablePagesList());
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public int getAvailablePagesCount() {
            return ((ProcessStatsSectionProto) this.instance).getAvailablePagesCount();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public ProcessStatsAvailablePagesProto getAvailablePages(int index) {
            return ((ProcessStatsSectionProto) this.instance).getAvailablePages(index);
        }

        public Builder setAvailablePages(int index, ProcessStatsAvailablePagesProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setAvailablePages((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder setAvailablePages(int index, ProcessStatsAvailablePagesProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setAvailablePages((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAvailablePages(ProcessStatsAvailablePagesProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAvailablePages((ProcessStatsSectionProto) value);
            return this;
        }

        public Builder addAvailablePages(int index, ProcessStatsAvailablePagesProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAvailablePages((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder addAvailablePages(ProcessStatsAvailablePagesProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAvailablePages((ProcessStatsSectionProto) builderForValue);
            return this;
        }

        public Builder addAvailablePages(int index, ProcessStatsAvailablePagesProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAvailablePages((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAvailablePages(Iterable<? extends ProcessStatsAvailablePagesProto> values) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAllAvailablePages(values);
            return this;
        }

        public Builder clearAvailablePages() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearAvailablePages();
            return this;
        }

        public Builder removeAvailablePages(int index) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).removeAvailablePages(index);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public List<ProcessStatsProto> getProcessStatsList() {
            return Collections.unmodifiableList(((ProcessStatsSectionProto) this.instance).getProcessStatsList());
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public int getProcessStatsCount() {
            return ((ProcessStatsSectionProto) this.instance).getProcessStatsCount();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public ProcessStatsProto getProcessStats(int index) {
            return ((ProcessStatsSectionProto) this.instance).getProcessStats(index);
        }

        public Builder setProcessStats(int index, ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setProcessStats((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder setProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setProcessStats((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcessStats(ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addProcessStats((ProcessStatsSectionProto) value);
            return this;
        }

        public Builder addProcessStats(int index, ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addProcessStats((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder addProcessStats(ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addProcessStats((ProcessStatsSectionProto) builderForValue);
            return this;
        }

        public Builder addProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addProcessStats((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcessStats(Iterable<? extends ProcessStatsProto> values) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAllProcessStats(values);
            return this;
        }

        public Builder clearProcessStats() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearProcessStats();
            return this;
        }

        public Builder removeProcessStats(int index) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).removeProcessStats(index);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public List<ProcessStatsPackageProto> getPackageStatsList() {
            return Collections.unmodifiableList(((ProcessStatsSectionProto) this.instance).getPackageStatsList());
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public int getPackageStatsCount() {
            return ((ProcessStatsSectionProto) this.instance).getPackageStatsCount();
        }

        @Override // android.service.procstats.ProcessStatsSectionProtoOrBuilder
        public ProcessStatsPackageProto getPackageStats(int index) {
            return ((ProcessStatsSectionProto) this.instance).getPackageStats(index);
        }

        public Builder setPackageStats(int index, ProcessStatsPackageProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setPackageStats((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder setPackageStats(int index, ProcessStatsPackageProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).setPackageStats((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackageStats(ProcessStatsPackageProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addPackageStats((ProcessStatsSectionProto) value);
            return this;
        }

        public Builder addPackageStats(int index, ProcessStatsPackageProto value) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addPackageStats((ProcessStatsSectionProto) index, (int) value);
            return this;
        }

        public Builder addPackageStats(ProcessStatsPackageProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addPackageStats((ProcessStatsSectionProto) builderForValue);
            return this;
        }

        public Builder addPackageStats(int index, ProcessStatsPackageProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addPackageStats((ProcessStatsSectionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackageStats(Iterable<? extends ProcessStatsPackageProto> values) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).addAllPackageStats(values);
            return this;
        }

        public Builder clearPackageStats() {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).clearPackageStats();
            return this;
        }

        public Builder removePackageStats(int index) {
            copyOnWrite();
            ((ProcessStatsSectionProto) this.instance).removePackageStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsSectionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.status_.makeImmutable();
                this.availablePages_.makeImmutable();
                this.processStats_.makeImmutable();
                this.packageStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsSectionProto other = (ProcessStatsSectionProto) arg1;
                this.startRealtimeMs_ = visitor.visitLong(hasStartRealtimeMs(), this.startRealtimeMs_, other.hasStartRealtimeMs(), other.startRealtimeMs_);
                this.endRealtimeMs_ = visitor.visitLong(hasEndRealtimeMs(), this.endRealtimeMs_, other.hasEndRealtimeMs(), other.endRealtimeMs_);
                this.startUptimeMs_ = visitor.visitLong(hasStartUptimeMs(), this.startUptimeMs_, other.hasStartUptimeMs(), other.startUptimeMs_);
                this.endUptimeMs_ = visitor.visitLong(hasEndUptimeMs(), this.endUptimeMs_, other.hasEndUptimeMs(), other.endUptimeMs_);
                this.runtime_ = visitor.visitString(hasRuntime(), this.runtime_, other.hasRuntime(), other.runtime_);
                this.hasSwappedPss_ = visitor.visitBoolean(hasHasSwappedPss(), this.hasSwappedPss_, other.hasHasSwappedPss(), other.hasSwappedPss_);
                this.status_ = visitor.visitIntList(this.status_, other.status_);
                this.availablePages_ = visitor.visitList(this.availablePages_, other.availablePages_);
                this.processStats_ = visitor.visitList(this.processStats_, other.processStats_);
                this.packageStats_ = visitor.visitList(this.packageStats_, other.packageStats_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.startRealtimeMs_ = input.readInt64();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.endRealtimeMs_ = input.readInt64();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.startUptimeMs_ = input.readInt64();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.endUptimeMs_ = input.readInt64();
                                break;
                            case 42:
                                String s = input.readString();
                                this.bitField0_ |= 16;
                                this.runtime_ = s;
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.hasSwappedPss_ = input.readBool();
                                break;
                            case 56:
                                if (!this.status_.isModifiable()) {
                                    this.status_ = GeneratedMessageLite.mutableCopy(this.status_);
                                }
                                int rawValue = input.readEnum();
                                if (Status.forNumber(rawValue) != null) {
                                    this.status_.addInt(rawValue);
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue);
                                    break;
                                }
                            case 58:
                                if (!this.status_.isModifiable()) {
                                    this.status_ = GeneratedMessageLite.mutableCopy(this.status_);
                                }
                                int oldLimit = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue2 = input.readEnum();
                                    if (Status.forNumber(rawValue2) == null) {
                                        super.mergeVarintField(7, rawValue2);
                                    } else {
                                        this.status_.addInt(rawValue2);
                                    }
                                }
                                input.popLimit(oldLimit);
                                break;
                            case 66:
                                if (!this.processStats_.isModifiable()) {
                                    this.processStats_ = GeneratedMessageLite.mutableCopy(this.processStats_);
                                }
                                this.processStats_.add((ProcessStatsProto) input.readMessage(ProcessStatsProto.parser(), extensionRegistry));
                                break;
                            case 74:
                                if (!this.packageStats_.isModifiable()) {
                                    this.packageStats_ = GeneratedMessageLite.mutableCopy(this.packageStats_);
                                }
                                this.packageStats_.add((ProcessStatsPackageProto) input.readMessage(ProcessStatsPackageProto.parser(), extensionRegistry));
                                break;
                            case 82:
                                if (!this.availablePages_.isModifiable()) {
                                    this.availablePages_ = GeneratedMessageLite.mutableCopy(this.availablePages_);
                                }
                                this.availablePages_.add((ProcessStatsAvailablePagesProto) input.readMessage(ProcessStatsAvailablePagesProto.parser(), extensionRegistry));
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (ProcessStatsSectionProto.class) {
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

    public static ProcessStatsSectionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsSectionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
