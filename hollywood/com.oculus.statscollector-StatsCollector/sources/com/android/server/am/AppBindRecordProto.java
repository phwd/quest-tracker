package com.android.server.am;

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

public final class AppBindRecordProto extends GeneratedMessageLite<AppBindRecordProto, Builder> implements AppBindRecordProtoOrBuilder {
    public static final int CLIENT_PROC_NAME_FIELD_NUMBER = 2;
    public static final int CONNECTIONS_FIELD_NUMBER = 3;
    private static final AppBindRecordProto DEFAULT_INSTANCE = new AppBindRecordProto();
    private static volatile Parser<AppBindRecordProto> PARSER = null;
    public static final int SERVICE_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private String clientProcName_ = "";
    private Internal.ProtobufList<String> connections_ = GeneratedMessageLite.emptyProtobufList();
    private String serviceName_ = "";

    private AppBindRecordProto() {
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public boolean hasServiceName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public String getServiceName() {
        return this.serviceName_;
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
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

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public boolean hasClientProcName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public String getClientProcName() {
        return this.clientProcName_;
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public ByteString getClientProcNameBytes() {
        return ByteString.copyFromUtf8(this.clientProcName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClientProcName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.clientProcName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClientProcName() {
        this.bitField0_ &= -3;
        this.clientProcName_ = getDefaultInstance().getClientProcName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClientProcNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.clientProcName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public List<String> getConnectionsList() {
        return this.connections_;
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public int getConnectionsCount() {
        return this.connections_.size();
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public String getConnections(int index) {
        return this.connections_.get(index);
    }

    @Override // com.android.server.am.AppBindRecordProtoOrBuilder
    public ByteString getConnectionsBytes(int index) {
        return ByteString.copyFromUtf8(this.connections_.get(index));
    }

    private void ensureConnectionsIsMutable() {
        if (!this.connections_.isModifiable()) {
            this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnections(int index, String value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(String value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConnections(Iterable<String> values) {
        ensureConnectionsIsMutable();
        AbstractMessageLite.addAll(values, this.connections_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnections() {
        this.connections_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnectionsBytes(ByteString value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getServiceName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getClientProcName());
        }
        for (int i = 0; i < this.connections_.size(); i++) {
            output.writeString(3, this.connections_.get(i));
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
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getClientProcName());
        }
        int dataSize = 0;
        for (int i = 0; i < this.connections_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.connections_.get(i));
        }
        int size3 = size2 + dataSize + (getConnectionsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppBindRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppBindRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppBindRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppBindRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppBindRecordProto parseFrom(InputStream input) throws IOException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppBindRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppBindRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppBindRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppBindRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppBindRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppBindRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppBindRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppBindRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppBindRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppBindRecordProto, Builder> implements AppBindRecordProtoOrBuilder {
        private Builder() {
            super(AppBindRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public boolean hasServiceName() {
            return ((AppBindRecordProto) this.instance).hasServiceName();
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public String getServiceName() {
            return ((AppBindRecordProto) this.instance).getServiceName();
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public ByteString getServiceNameBytes() {
            return ((AppBindRecordProto) this.instance).getServiceNameBytes();
        }

        public Builder setServiceName(String value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).setServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).clearServiceName();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).setServiceNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public boolean hasClientProcName() {
            return ((AppBindRecordProto) this.instance).hasClientProcName();
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public String getClientProcName() {
            return ((AppBindRecordProto) this.instance).getClientProcName();
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public ByteString getClientProcNameBytes() {
            return ((AppBindRecordProto) this.instance).getClientProcNameBytes();
        }

        public Builder setClientProcName(String value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).setClientProcName(value);
            return this;
        }

        public Builder clearClientProcName() {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).clearClientProcName();
            return this;
        }

        public Builder setClientProcNameBytes(ByteString value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).setClientProcNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public List<String> getConnectionsList() {
            return Collections.unmodifiableList(((AppBindRecordProto) this.instance).getConnectionsList());
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public int getConnectionsCount() {
            return ((AppBindRecordProto) this.instance).getConnectionsCount();
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public String getConnections(int index) {
            return ((AppBindRecordProto) this.instance).getConnections(index);
        }

        @Override // com.android.server.am.AppBindRecordProtoOrBuilder
        public ByteString getConnectionsBytes(int index) {
            return ((AppBindRecordProto) this.instance).getConnectionsBytes(index);
        }

        public Builder setConnections(int index, String value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).setConnections(index, value);
            return this;
        }

        public Builder addConnections(String value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).addConnections(value);
            return this;
        }

        public Builder addAllConnections(Iterable<String> values) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).addAllConnections(values);
            return this;
        }

        public Builder clearConnections() {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).clearConnections();
            return this;
        }

        public Builder addConnectionsBytes(ByteString value) {
            copyOnWrite();
            ((AppBindRecordProto) this.instance).addConnectionsBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppBindRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.connections_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppBindRecordProto other = (AppBindRecordProto) arg1;
                this.serviceName_ = visitor.visitString(hasServiceName(), this.serviceName_, other.hasServiceName(), other.serviceName_);
                this.clientProcName_ = visitor.visitString(hasClientProcName(), this.clientProcName_, other.hasClientProcName(), other.clientProcName_);
                this.connections_ = visitor.visitList(this.connections_, other.connections_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
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
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.clientProcName_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            if (!this.connections_.isModifiable()) {
                                this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
                            }
                            this.connections_.add(s3);
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
                    synchronized (AppBindRecordProto.class) {
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

    public static AppBindRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppBindRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
