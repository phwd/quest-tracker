package com.android.service;

import com.android.service.NetworkWatchlistAppResultProto;
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

public final class NetworkWatchlistReportProto extends GeneratedMessageLite<NetworkWatchlistReportProto, Builder> implements NetworkWatchlistReportProtoOrBuilder {
    public static final int APP_RESULT_FIELD_NUMBER = 3;
    private static final NetworkWatchlistReportProto DEFAULT_INSTANCE = new NetworkWatchlistReportProto();
    private static volatile Parser<NetworkWatchlistReportProto> PARSER = null;
    public static final int REPORT_VERSION_FIELD_NUMBER = 1;
    public static final int WATCHLIST_CONFIG_HASH_FIELD_NUMBER = 2;
    private Internal.ProtobufList<NetworkWatchlistAppResultProto> appResult_ = emptyProtobufList();
    private int bitField0_;
    private int reportVersion_ = 0;
    private String watchlistConfigHash_ = "";

    private NetworkWatchlistReportProto() {
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public boolean hasReportVersion() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public int getReportVersion() {
        return this.reportVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportVersion(int value) {
        this.bitField0_ |= 1;
        this.reportVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportVersion() {
        this.bitField0_ &= -2;
        this.reportVersion_ = 0;
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public boolean hasWatchlistConfigHash() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public String getWatchlistConfigHash() {
        return this.watchlistConfigHash_;
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public ByteString getWatchlistConfigHashBytes() {
        return ByteString.copyFromUtf8(this.watchlistConfigHash_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWatchlistConfigHash(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.watchlistConfigHash_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWatchlistConfigHash() {
        this.bitField0_ &= -3;
        this.watchlistConfigHash_ = getDefaultInstance().getWatchlistConfigHash();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWatchlistConfigHashBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.watchlistConfigHash_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public List<NetworkWatchlistAppResultProto> getAppResultList() {
        return this.appResult_;
    }

    public List<? extends NetworkWatchlistAppResultProtoOrBuilder> getAppResultOrBuilderList() {
        return this.appResult_;
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public int getAppResultCount() {
        return this.appResult_.size();
    }

    @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
    public NetworkWatchlistAppResultProto getAppResult(int index) {
        return this.appResult_.get(index);
    }

    public NetworkWatchlistAppResultProtoOrBuilder getAppResultOrBuilder(int index) {
        return this.appResult_.get(index);
    }

    private void ensureAppResultIsMutable() {
        if (!this.appResult_.isModifiable()) {
            this.appResult_ = GeneratedMessageLite.mutableCopy(this.appResult_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppResult(int index, NetworkWatchlistAppResultProto value) {
        if (value != null) {
            ensureAppResultIsMutable();
            this.appResult_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppResult(int index, NetworkWatchlistAppResultProto.Builder builderForValue) {
        ensureAppResultIsMutable();
        this.appResult_.set(index, (NetworkWatchlistAppResultProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppResult(NetworkWatchlistAppResultProto value) {
        if (value != null) {
            ensureAppResultIsMutable();
            this.appResult_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppResult(int index, NetworkWatchlistAppResultProto value) {
        if (value != null) {
            ensureAppResultIsMutable();
            this.appResult_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppResult(NetworkWatchlistAppResultProto.Builder builderForValue) {
        ensureAppResultIsMutable();
        this.appResult_.add((NetworkWatchlistAppResultProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppResult(int index, NetworkWatchlistAppResultProto.Builder builderForValue) {
        ensureAppResultIsMutable();
        this.appResult_.add(index, (NetworkWatchlistAppResultProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAppResult(Iterable<? extends NetworkWatchlistAppResultProto> values) {
        ensureAppResultIsMutable();
        AbstractMessageLite.addAll(values, this.appResult_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppResult() {
        this.appResult_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAppResult(int index) {
        ensureAppResultIsMutable();
        this.appResult_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getWatchlistConfigHash());
        }
        for (int i = 0; i < this.appResult_.size(); i++) {
            output.writeMessage(3, this.appResult_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getWatchlistConfigHash());
        }
        for (int i = 0; i < this.appResult_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.appResult_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NetworkWatchlistReportProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkWatchlistReportProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkWatchlistReportProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkWatchlistReportProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkWatchlistReportProto parseFrom(InputStream input) throws IOException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistReportProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkWatchlistReportProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkWatchlistReportProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistReportProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistReportProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkWatchlistReportProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkWatchlistReportProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkWatchlistReportProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkWatchlistReportProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkWatchlistReportProto, Builder> implements NetworkWatchlistReportProtoOrBuilder {
        private Builder() {
            super(NetworkWatchlistReportProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public boolean hasReportVersion() {
            return ((NetworkWatchlistReportProto) this.instance).hasReportVersion();
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public int getReportVersion() {
            return ((NetworkWatchlistReportProto) this.instance).getReportVersion();
        }

        public Builder setReportVersion(int value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).setReportVersion(value);
            return this;
        }

        public Builder clearReportVersion() {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).clearReportVersion();
            return this;
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public boolean hasWatchlistConfigHash() {
            return ((NetworkWatchlistReportProto) this.instance).hasWatchlistConfigHash();
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public String getWatchlistConfigHash() {
            return ((NetworkWatchlistReportProto) this.instance).getWatchlistConfigHash();
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public ByteString getWatchlistConfigHashBytes() {
            return ((NetworkWatchlistReportProto) this.instance).getWatchlistConfigHashBytes();
        }

        public Builder setWatchlistConfigHash(String value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).setWatchlistConfigHash(value);
            return this;
        }

        public Builder clearWatchlistConfigHash() {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).clearWatchlistConfigHash();
            return this;
        }

        public Builder setWatchlistConfigHashBytes(ByteString value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).setWatchlistConfigHashBytes(value);
            return this;
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public List<NetworkWatchlistAppResultProto> getAppResultList() {
            return Collections.unmodifiableList(((NetworkWatchlistReportProto) this.instance).getAppResultList());
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public int getAppResultCount() {
            return ((NetworkWatchlistReportProto) this.instance).getAppResultCount();
        }

        @Override // com.android.service.NetworkWatchlistReportProtoOrBuilder
        public NetworkWatchlistAppResultProto getAppResult(int index) {
            return ((NetworkWatchlistReportProto) this.instance).getAppResult(index);
        }

        public Builder setAppResult(int index, NetworkWatchlistAppResultProto value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).setAppResult((NetworkWatchlistReportProto) index, (int) value);
            return this;
        }

        public Builder setAppResult(int index, NetworkWatchlistAppResultProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).setAppResult((NetworkWatchlistReportProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAppResult(NetworkWatchlistAppResultProto value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).addAppResult((NetworkWatchlistReportProto) value);
            return this;
        }

        public Builder addAppResult(int index, NetworkWatchlistAppResultProto value) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).addAppResult((NetworkWatchlistReportProto) index, (int) value);
            return this;
        }

        public Builder addAppResult(NetworkWatchlistAppResultProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).addAppResult((NetworkWatchlistReportProto) builderForValue);
            return this;
        }

        public Builder addAppResult(int index, NetworkWatchlistAppResultProto.Builder builderForValue) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).addAppResult((NetworkWatchlistReportProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAppResult(Iterable<? extends NetworkWatchlistAppResultProto> values) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).addAllAppResult(values);
            return this;
        }

        public Builder clearAppResult() {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).clearAppResult();
            return this;
        }

        public Builder removeAppResult(int index) {
            copyOnWrite();
            ((NetworkWatchlistReportProto) this.instance).removeAppResult(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkWatchlistReportProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.appResult_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkWatchlistReportProto other = (NetworkWatchlistReportProto) arg1;
                this.reportVersion_ = visitor.visitInt(hasReportVersion(), this.reportVersion_, other.hasReportVersion(), other.reportVersion_);
                this.watchlistConfigHash_ = visitor.visitString(hasWatchlistConfigHash(), this.watchlistConfigHash_, other.hasWatchlistConfigHash(), other.watchlistConfigHash_);
                this.appResult_ = visitor.visitList(this.appResult_, other.appResult_);
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
                            this.reportVersion_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.watchlistConfigHash_ = s;
                        } else if (tag == 26) {
                            if (!this.appResult_.isModifiable()) {
                                this.appResult_ = GeneratedMessageLite.mutableCopy(this.appResult_);
                            }
                            this.appResult_.add((NetworkWatchlistAppResultProto) input.readMessage(NetworkWatchlistAppResultProto.parser(), extensionRegistry));
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
                    synchronized (NetworkWatchlistReportProto.class) {
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

    public static NetworkWatchlistReportProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkWatchlistReportProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
