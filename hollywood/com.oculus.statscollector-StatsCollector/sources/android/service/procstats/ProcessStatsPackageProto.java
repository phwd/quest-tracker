package android.service.procstats;

import android.service.procstats.PackageAssociationProcessStatsProto;
import android.service.procstats.PackageServiceStatsProto;
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

public final class ProcessStatsPackageProto extends GeneratedMessageLite<ProcessStatsPackageProto, Builder> implements ProcessStatsPackageProtoOrBuilder {
    public static final int ASSOCIATION_STATS_FIELD_NUMBER = 6;
    private static final ProcessStatsPackageProto DEFAULT_INSTANCE = new ProcessStatsPackageProto();
    public static final int PACKAGE_FIELD_NUMBER = 1;
    private static volatile Parser<ProcessStatsPackageProto> PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 4;
    public static final int SERVICE_STATS_FIELD_NUMBER = 5;
    public static final int UID_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 3;
    private Internal.ProtobufList<PackageAssociationProcessStatsProto> associationStats_ = emptyProtobufList();
    private int bitField0_;
    private String package_ = "";
    private Internal.ProtobufList<ProcessStatsProto> processStats_ = emptyProtobufList();
    private Internal.ProtobufList<PackageServiceStatsProto> serviceStats_ = emptyProtobufList();
    private int uid_ = 0;
    private long version_ = 0;

    private ProcessStatsPackageProto() {
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public boolean hasPackage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public String getPackage() {
        return this.package_;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public ByteString getPackageBytes() {
        return ByteString.copyFromUtf8(this.package_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.package_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackage() {
        this.bitField0_ &= -2;
        this.package_ = getDefaultInstance().getPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.package_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 2;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -3;
        this.uid_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public long getVersion() {
        return this.version_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(long value) {
        this.bitField0_ |= 4;
        this.version_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -5;
        this.version_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public List<ProcessStatsProto> getProcessStatsList() {
        return this.processStats_;
    }

    public List<? extends ProcessStatsProtoOrBuilder> getProcessStatsOrBuilderList() {
        return this.processStats_;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public int getProcessStatsCount() {
        return this.processStats_.size();
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
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

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public List<PackageServiceStatsProto> getServiceStatsList() {
        return this.serviceStats_;
    }

    public List<? extends PackageServiceStatsProtoOrBuilder> getServiceStatsOrBuilderList() {
        return this.serviceStats_;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public int getServiceStatsCount() {
        return this.serviceStats_.size();
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public PackageServiceStatsProto getServiceStats(int index) {
        return this.serviceStats_.get(index);
    }

    public PackageServiceStatsProtoOrBuilder getServiceStatsOrBuilder(int index) {
        return this.serviceStats_.get(index);
    }

    private void ensureServiceStatsIsMutable() {
        if (!this.serviceStats_.isModifiable()) {
            this.serviceStats_ = GeneratedMessageLite.mutableCopy(this.serviceStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceStats(int index, PackageServiceStatsProto value) {
        if (value != null) {
            ensureServiceStatsIsMutable();
            this.serviceStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceStats(int index, PackageServiceStatsProto.Builder builderForValue) {
        ensureServiceStatsIsMutable();
        this.serviceStats_.set(index, (PackageServiceStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServiceStats(PackageServiceStatsProto value) {
        if (value != null) {
            ensureServiceStatsIsMutable();
            this.serviceStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServiceStats(int index, PackageServiceStatsProto value) {
        if (value != null) {
            ensureServiceStatsIsMutable();
            this.serviceStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServiceStats(PackageServiceStatsProto.Builder builderForValue) {
        ensureServiceStatsIsMutable();
        this.serviceStats_.add((PackageServiceStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addServiceStats(int index, PackageServiceStatsProto.Builder builderForValue) {
        ensureServiceStatsIsMutable();
        this.serviceStats_.add(index, (PackageServiceStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllServiceStats(Iterable<? extends PackageServiceStatsProto> values) {
        ensureServiceStatsIsMutable();
        AbstractMessageLite.addAll(values, this.serviceStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServiceStats() {
        this.serviceStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeServiceStats(int index) {
        ensureServiceStatsIsMutable();
        this.serviceStats_.remove(index);
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public List<PackageAssociationProcessStatsProto> getAssociationStatsList() {
        return this.associationStats_;
    }

    public List<? extends PackageAssociationProcessStatsProtoOrBuilder> getAssociationStatsOrBuilderList() {
        return this.associationStats_;
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public int getAssociationStatsCount() {
        return this.associationStats_.size();
    }

    @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
    public PackageAssociationProcessStatsProto getAssociationStats(int index) {
        return this.associationStats_.get(index);
    }

    public PackageAssociationProcessStatsProtoOrBuilder getAssociationStatsOrBuilder(int index) {
        return this.associationStats_.get(index);
    }

    private void ensureAssociationStatsIsMutable() {
        if (!this.associationStats_.isModifiable()) {
            this.associationStats_ = GeneratedMessageLite.mutableCopy(this.associationStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAssociationStats(int index, PackageAssociationProcessStatsProto value) {
        if (value != null) {
            ensureAssociationStatsIsMutable();
            this.associationStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAssociationStats(int index, PackageAssociationProcessStatsProto.Builder builderForValue) {
        ensureAssociationStatsIsMutable();
        this.associationStats_.set(index, (PackageAssociationProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAssociationStats(PackageAssociationProcessStatsProto value) {
        if (value != null) {
            ensureAssociationStatsIsMutable();
            this.associationStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAssociationStats(int index, PackageAssociationProcessStatsProto value) {
        if (value != null) {
            ensureAssociationStatsIsMutable();
            this.associationStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAssociationStats(PackageAssociationProcessStatsProto.Builder builderForValue) {
        ensureAssociationStatsIsMutable();
        this.associationStats_.add((PackageAssociationProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAssociationStats(int index, PackageAssociationProcessStatsProto.Builder builderForValue) {
        ensureAssociationStatsIsMutable();
        this.associationStats_.add(index, (PackageAssociationProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAssociationStats(Iterable<? extends PackageAssociationProcessStatsProto> values) {
        ensureAssociationStatsIsMutable();
        AbstractMessageLite.addAll(values, this.associationStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAssociationStats() {
        this.associationStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAssociationStats(int index) {
        ensureAssociationStatsIsMutable();
        this.associationStats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.version_);
        }
        for (int i = 0; i < this.processStats_.size(); i++) {
            output.writeMessage(4, this.processStats_.get(i));
        }
        for (int i2 = 0; i2 < this.serviceStats_.size(); i2++) {
            output.writeMessage(5, this.serviceStats_.get(i2));
        }
        for (int i3 = 0; i3 < this.associationStats_.size(); i3++) {
            output.writeMessage(6, this.associationStats_.get(i3));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.version_);
        }
        for (int i = 0; i < this.processStats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.processStats_.get(i));
        }
        for (int i2 = 0; i2 < this.serviceStats_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.serviceStats_.get(i2));
        }
        for (int i3 = 0; i3 < this.associationStats_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.associationStats_.get(i3));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessStatsPackageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsPackageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsPackageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsPackageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsPackageProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsPackageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsPackageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsPackageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsPackageProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsPackageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsPackageProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsPackageProto, Builder> implements ProcessStatsPackageProtoOrBuilder {
        private Builder() {
            super(ProcessStatsPackageProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public boolean hasPackage() {
            return ((ProcessStatsPackageProto) this.instance).hasPackage();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public String getPackage() {
            return ((ProcessStatsPackageProto) this.instance).getPackage();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public ByteString getPackageBytes() {
            return ((ProcessStatsPackageProto) this.instance).getPackageBytes();
        }

        public Builder setPackage(String value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setPackage(value);
            return this;
        }

        public Builder clearPackage() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearPackage();
            return this;
        }

        public Builder setPackageBytes(ByteString value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setPackageBytes(value);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public boolean hasUid() {
            return ((ProcessStatsPackageProto) this.instance).hasUid();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public int getUid() {
            return ((ProcessStatsPackageProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearUid();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public boolean hasVersion() {
            return ((ProcessStatsPackageProto) this.instance).hasVersion();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public long getVersion() {
            return ((ProcessStatsPackageProto) this.instance).getVersion();
        }

        public Builder setVersion(long value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearVersion();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public List<ProcessStatsProto> getProcessStatsList() {
            return Collections.unmodifiableList(((ProcessStatsPackageProto) this.instance).getProcessStatsList());
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public int getProcessStatsCount() {
            return ((ProcessStatsPackageProto) this.instance).getProcessStatsCount();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public ProcessStatsProto getProcessStats(int index) {
            return ((ProcessStatsPackageProto) this.instance).getProcessStats(index);
        }

        public Builder setProcessStats(int index, ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setProcessStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder setProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setProcessStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcessStats(ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addProcessStats((ProcessStatsPackageProto) value);
            return this;
        }

        public Builder addProcessStats(int index, ProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addProcessStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder addProcessStats(ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addProcessStats((ProcessStatsPackageProto) builderForValue);
            return this;
        }

        public Builder addProcessStats(int index, ProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addProcessStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcessStats(Iterable<? extends ProcessStatsProto> values) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAllProcessStats(values);
            return this;
        }

        public Builder clearProcessStats() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearProcessStats();
            return this;
        }

        public Builder removeProcessStats(int index) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).removeProcessStats(index);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public List<PackageServiceStatsProto> getServiceStatsList() {
            return Collections.unmodifiableList(((ProcessStatsPackageProto) this.instance).getServiceStatsList());
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public int getServiceStatsCount() {
            return ((ProcessStatsPackageProto) this.instance).getServiceStatsCount();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public PackageServiceStatsProto getServiceStats(int index) {
            return ((ProcessStatsPackageProto) this.instance).getServiceStats(index);
        }

        public Builder setServiceStats(int index, PackageServiceStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setServiceStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder setServiceStats(int index, PackageServiceStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setServiceStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addServiceStats(PackageServiceStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addServiceStats((ProcessStatsPackageProto) value);
            return this;
        }

        public Builder addServiceStats(int index, PackageServiceStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addServiceStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder addServiceStats(PackageServiceStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addServiceStats((ProcessStatsPackageProto) builderForValue);
            return this;
        }

        public Builder addServiceStats(int index, PackageServiceStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addServiceStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllServiceStats(Iterable<? extends PackageServiceStatsProto> values) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAllServiceStats(values);
            return this;
        }

        public Builder clearServiceStats() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearServiceStats();
            return this;
        }

        public Builder removeServiceStats(int index) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).removeServiceStats(index);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public List<PackageAssociationProcessStatsProto> getAssociationStatsList() {
            return Collections.unmodifiableList(((ProcessStatsPackageProto) this.instance).getAssociationStatsList());
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public int getAssociationStatsCount() {
            return ((ProcessStatsPackageProto) this.instance).getAssociationStatsCount();
        }

        @Override // android.service.procstats.ProcessStatsPackageProtoOrBuilder
        public PackageAssociationProcessStatsProto getAssociationStats(int index) {
            return ((ProcessStatsPackageProto) this.instance).getAssociationStats(index);
        }

        public Builder setAssociationStats(int index, PackageAssociationProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setAssociationStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder setAssociationStats(int index, PackageAssociationProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).setAssociationStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAssociationStats(PackageAssociationProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAssociationStats((ProcessStatsPackageProto) value);
            return this;
        }

        public Builder addAssociationStats(int index, PackageAssociationProcessStatsProto value) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAssociationStats((ProcessStatsPackageProto) index, (int) value);
            return this;
        }

        public Builder addAssociationStats(PackageAssociationProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAssociationStats((ProcessStatsPackageProto) builderForValue);
            return this;
        }

        public Builder addAssociationStats(int index, PackageAssociationProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAssociationStats((ProcessStatsPackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAssociationStats(Iterable<? extends PackageAssociationProcessStatsProto> values) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).addAllAssociationStats(values);
            return this;
        }

        public Builder clearAssociationStats() {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).clearAssociationStats();
            return this;
        }

        public Builder removeAssociationStats(int index) {
            copyOnWrite();
            ((ProcessStatsPackageProto) this.instance).removeAssociationStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsPackageProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.processStats_.makeImmutable();
                this.serviceStats_.makeImmutable();
                this.associationStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsPackageProto other = (ProcessStatsPackageProto) arg1;
                this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.version_ = visitor.visitLong(hasVersion(), this.version_, other.hasVersion(), other.version_);
                this.processStats_ = visitor.visitList(this.processStats_, other.processStats_);
                this.serviceStats_ = visitor.visitList(this.serviceStats_, other.serviceStats_);
                this.associationStats_ = visitor.visitList(this.associationStats_, other.associationStats_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.package_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.uid_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.version_ = input.readInt64();
                        } else if (tag == 34) {
                            if (!this.processStats_.isModifiable()) {
                                this.processStats_ = GeneratedMessageLite.mutableCopy(this.processStats_);
                            }
                            this.processStats_.add((ProcessStatsProto) input.readMessage(ProcessStatsProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.serviceStats_.isModifiable()) {
                                this.serviceStats_ = GeneratedMessageLite.mutableCopy(this.serviceStats_);
                            }
                            this.serviceStats_.add((PackageServiceStatsProto) input.readMessage(PackageServiceStatsProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.associationStats_.isModifiable()) {
                                this.associationStats_ = GeneratedMessageLite.mutableCopy(this.associationStats_);
                            }
                            this.associationStats_.add((PackageAssociationProcessStatsProto) input.readMessage(PackageAssociationProcessStatsProto.parser(), extensionRegistry));
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
                    synchronized (ProcessStatsPackageProto.class) {
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

    public static ProcessStatsPackageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsPackageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
