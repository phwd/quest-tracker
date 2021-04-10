package com.android.server.am;

import com.android.server.am.ActiveServicesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ActivityManagerServiceDumpServicesProto extends GeneratedMessageLite<ActivityManagerServiceDumpServicesProto, Builder> implements ActivityManagerServiceDumpServicesProtoOrBuilder {
    public static final int ACTIVE_SERVICES_FIELD_NUMBER = 1;
    private static final ActivityManagerServiceDumpServicesProto DEFAULT_INSTANCE = new ActivityManagerServiceDumpServicesProto();
    private static volatile Parser<ActivityManagerServiceDumpServicesProto> PARSER;
    private ActiveServicesProto activeServices_;
    private int bitField0_;

    private ActivityManagerServiceDumpServicesProto() {
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpServicesProtoOrBuilder
    public boolean hasActiveServices() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpServicesProtoOrBuilder
    public ActiveServicesProto getActiveServices() {
        ActiveServicesProto activeServicesProto = this.activeServices_;
        return activeServicesProto == null ? ActiveServicesProto.getDefaultInstance() : activeServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveServices(ActiveServicesProto value) {
        if (value != null) {
            this.activeServices_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveServices(ActiveServicesProto.Builder builderForValue) {
        this.activeServices_ = (ActiveServicesProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActiveServices(ActiveServicesProto value) {
        ActiveServicesProto activeServicesProto = this.activeServices_;
        if (activeServicesProto == null || activeServicesProto == ActiveServicesProto.getDefaultInstance()) {
            this.activeServices_ = value;
        } else {
            this.activeServices_ = (ActiveServicesProto) ((ActiveServicesProto.Builder) ActiveServicesProto.newBuilder(this.activeServices_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveServices() {
        this.activeServices_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getActiveServices());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getActiveServices());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpServicesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpServicesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpServicesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpServicesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityManagerServiceDumpServicesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityManagerServiceDumpServicesProto, Builder> implements ActivityManagerServiceDumpServicesProtoOrBuilder {
        private Builder() {
            super(ActivityManagerServiceDumpServicesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpServicesProtoOrBuilder
        public boolean hasActiveServices() {
            return ((ActivityManagerServiceDumpServicesProto) this.instance).hasActiveServices();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpServicesProtoOrBuilder
        public ActiveServicesProto getActiveServices() {
            return ((ActivityManagerServiceDumpServicesProto) this.instance).getActiveServices();
        }

        public Builder setActiveServices(ActiveServicesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpServicesProto) this.instance).setActiveServices((ActivityManagerServiceDumpServicesProto) value);
            return this;
        }

        public Builder setActiveServices(ActiveServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpServicesProto) this.instance).setActiveServices((ActivityManagerServiceDumpServicesProto) builderForValue);
            return this;
        }

        public Builder mergeActiveServices(ActiveServicesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpServicesProto) this.instance).mergeActiveServices(value);
            return this;
        }

        public Builder clearActiveServices() {
            copyOnWrite();
            ((ActivityManagerServiceDumpServicesProto) this.instance).clearActiveServices();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityManagerServiceDumpServicesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityManagerServiceDumpServicesProto other = (ActivityManagerServiceDumpServicesProto) arg1;
                this.activeServices_ = (ActiveServicesProto) visitor.visitMessage(this.activeServices_, other.activeServices_);
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
                            ActiveServicesProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ActiveServicesProto.Builder) this.activeServices_.toBuilder();
                            }
                            this.activeServices_ = (ActiveServicesProto) input.readMessage(ActiveServicesProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.activeServices_);
                                this.activeServices_ = (ActiveServicesProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (ActivityManagerServiceDumpServicesProto.class) {
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

    public static ActivityManagerServiceDumpServicesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityManagerServiceDumpServicesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
