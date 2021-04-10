package android.service.procstats;

import android.service.procstats.PackageServiceOperationStatsProto;
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

public final class PackageServiceStatsProto extends GeneratedMessageLite<PackageServiceStatsProto, Builder> implements PackageServiceStatsProtoOrBuilder {
    private static final PackageServiceStatsProto DEFAULT_INSTANCE = new PackageServiceStatsProto();
    public static final int OPERATION_STATS_FIELD_NUMBER = 2;
    private static volatile Parser<PackageServiceStatsProto> PARSER = null;
    public static final int SERVICE_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private Internal.ProtobufList<PackageServiceOperationStatsProto> operationStats_ = emptyProtobufList();
    private String serviceName_ = "";

    private PackageServiceStatsProto() {
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public boolean hasServiceName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public String getServiceName() {
        return this.serviceName_;
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public ByteString getServiceNameBytes() {
        return ByteString.copyFromUtf8(this.serviceName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.serviceName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServiceName() {
        this.bitField0_ &= -2;
        this.serviceName_ = getDefaultInstance().getServiceName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.serviceName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public List<PackageServiceOperationStatsProto> getOperationStatsList() {
        return this.operationStats_;
    }

    public List<? extends PackageServiceOperationStatsProtoOrBuilder> getOperationStatsOrBuilderList() {
        return this.operationStats_;
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public int getOperationStatsCount() {
        return this.operationStats_.size();
    }

    @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
    public PackageServiceOperationStatsProto getOperationStats(int index) {
        return this.operationStats_.get(index);
    }

    public PackageServiceOperationStatsProtoOrBuilder getOperationStatsOrBuilder(int index) {
        return this.operationStats_.get(index);
    }

    private void ensureOperationStatsIsMutable() {
        if (!this.operationStats_.isModifiable()) {
            this.operationStats_ = GeneratedMessageLite.mutableCopy(this.operationStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperationStats(int index, PackageServiceOperationStatsProto value) {
        if (value != null) {
            ensureOperationStatsIsMutable();
            this.operationStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperationStats(int index, PackageServiceOperationStatsProto.Builder builderForValue) {
        ensureOperationStatsIsMutable();
        this.operationStats_.set(index, (PackageServiceOperationStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOperationStats(PackageServiceOperationStatsProto value) {
        if (value != null) {
            ensureOperationStatsIsMutable();
            this.operationStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOperationStats(int index, PackageServiceOperationStatsProto value) {
        if (value != null) {
            ensureOperationStatsIsMutable();
            this.operationStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOperationStats(PackageServiceOperationStatsProto.Builder builderForValue) {
        ensureOperationStatsIsMutable();
        this.operationStats_.add((PackageServiceOperationStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOperationStats(int index, PackageServiceOperationStatsProto.Builder builderForValue) {
        ensureOperationStatsIsMutable();
        this.operationStats_.add(index, (PackageServiceOperationStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOperationStats(Iterable<? extends PackageServiceOperationStatsProto> values) {
        ensureOperationStatsIsMutable();
        AbstractMessageLite.addAll(values, this.operationStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOperationStats() {
        this.operationStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeOperationStats(int index) {
        ensureOperationStatsIsMutable();
        this.operationStats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getServiceName());
        }
        for (int i = 0; i < this.operationStats_.size(); i++) {
            output.writeMessage(2, this.operationStats_.get(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getServiceName());
        }
        for (int i = 0; i < this.operationStats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.operationStats_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageServiceStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceStatsProto parseFrom(InputStream input) throws IOException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageServiceStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageServiceStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageServiceStatsProto, Builder> implements PackageServiceStatsProtoOrBuilder {
        private Builder() {
            super(PackageServiceStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public boolean hasServiceName() {
            return ((PackageServiceStatsProto) this.instance).hasServiceName();
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public String getServiceName() {
            return ((PackageServiceStatsProto) this.instance).getServiceName();
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public ByteString getServiceNameBytes() {
            return ((PackageServiceStatsProto) this.instance).getServiceNameBytes();
        }

        public Builder setServiceName(String value) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).setServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).clearServiceName();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).setServiceNameBytes(value);
            return this;
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public List<PackageServiceOperationStatsProto> getOperationStatsList() {
            return Collections.unmodifiableList(((PackageServiceStatsProto) this.instance).getOperationStatsList());
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public int getOperationStatsCount() {
            return ((PackageServiceStatsProto) this.instance).getOperationStatsCount();
        }

        @Override // android.service.procstats.PackageServiceStatsProtoOrBuilder
        public PackageServiceOperationStatsProto getOperationStats(int index) {
            return ((PackageServiceStatsProto) this.instance).getOperationStats(index);
        }

        public Builder setOperationStats(int index, PackageServiceOperationStatsProto value) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).setOperationStats((PackageServiceStatsProto) index, (int) value);
            return this;
        }

        public Builder setOperationStats(int index, PackageServiceOperationStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).setOperationStats((PackageServiceStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addOperationStats(PackageServiceOperationStatsProto value) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).addOperationStats((PackageServiceStatsProto) value);
            return this;
        }

        public Builder addOperationStats(int index, PackageServiceOperationStatsProto value) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).addOperationStats((PackageServiceStatsProto) index, (int) value);
            return this;
        }

        public Builder addOperationStats(PackageServiceOperationStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).addOperationStats((PackageServiceStatsProto) builderForValue);
            return this;
        }

        public Builder addOperationStats(int index, PackageServiceOperationStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).addOperationStats((PackageServiceStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllOperationStats(Iterable<? extends PackageServiceOperationStatsProto> values) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).addAllOperationStats(values);
            return this;
        }

        public Builder clearOperationStats() {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).clearOperationStats();
            return this;
        }

        public Builder removeOperationStats(int index) {
            copyOnWrite();
            ((PackageServiceStatsProto) this.instance).removeOperationStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageServiceStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.operationStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageServiceStatsProto other = (PackageServiceStatsProto) arg1;
                this.serviceName_ = visitor.visitString(hasServiceName(), this.serviceName_, other.hasServiceName(), other.serviceName_);
                this.operationStats_ = visitor.visitList(this.operationStats_, other.operationStats_);
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
                            this.serviceName_ = s;
                        } else if (tag == 18) {
                            if (!this.operationStats_.isModifiable()) {
                                this.operationStats_ = GeneratedMessageLite.mutableCopy(this.operationStats_);
                            }
                            this.operationStats_.add((PackageServiceOperationStatsProto) input.readMessage(PackageServiceOperationStatsProto.parser(), extensionRegistry));
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
                    synchronized (PackageServiceStatsProto.class) {
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

    public static PackageServiceStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageServiceStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
