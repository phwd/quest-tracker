package com.android.server.wm;

import android.view.RemoteAnimationTargetProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class RemoteAnimationAdapterWrapperProto extends GeneratedMessageLite<RemoteAnimationAdapterWrapperProto, Builder> implements RemoteAnimationAdapterWrapperProtoOrBuilder {
    private static final RemoteAnimationAdapterWrapperProto DEFAULT_INSTANCE = new RemoteAnimationAdapterWrapperProto();
    private static volatile Parser<RemoteAnimationAdapterWrapperProto> PARSER = null;
    public static final int TARGET_FIELD_NUMBER = 1;
    private int bitField0_;
    private RemoteAnimationTargetProto target_;

    private RemoteAnimationAdapterWrapperProto() {
    }

    @Override // com.android.server.wm.RemoteAnimationAdapterWrapperProtoOrBuilder
    public boolean hasTarget() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.RemoteAnimationAdapterWrapperProtoOrBuilder
    public RemoteAnimationTargetProto getTarget() {
        RemoteAnimationTargetProto remoteAnimationTargetProto = this.target_;
        return remoteAnimationTargetProto == null ? RemoteAnimationTargetProto.getDefaultInstance() : remoteAnimationTargetProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTarget(RemoteAnimationTargetProto value) {
        if (value != null) {
            this.target_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTarget(RemoteAnimationTargetProto.Builder builderForValue) {
        this.target_ = (RemoteAnimationTargetProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTarget(RemoteAnimationTargetProto value) {
        RemoteAnimationTargetProto remoteAnimationTargetProto = this.target_;
        if (remoteAnimationTargetProto == null || remoteAnimationTargetProto == RemoteAnimationTargetProto.getDefaultInstance()) {
            this.target_ = value;
        } else {
            this.target_ = (RemoteAnimationTargetProto) ((RemoteAnimationTargetProto.Builder) RemoteAnimationTargetProto.newBuilder(this.target_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTarget() {
        this.target_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getTarget());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getTarget());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(InputStream input) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RemoteAnimationAdapterWrapperProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationAdapterWrapperProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(CodedInputStream input) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RemoteAnimationAdapterWrapperProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RemoteAnimationAdapterWrapperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RemoteAnimationAdapterWrapperProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RemoteAnimationAdapterWrapperProto, Builder> implements RemoteAnimationAdapterWrapperProtoOrBuilder {
        private Builder() {
            super(RemoteAnimationAdapterWrapperProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.RemoteAnimationAdapterWrapperProtoOrBuilder
        public boolean hasTarget() {
            return ((RemoteAnimationAdapterWrapperProto) this.instance).hasTarget();
        }

        @Override // com.android.server.wm.RemoteAnimationAdapterWrapperProtoOrBuilder
        public RemoteAnimationTargetProto getTarget() {
            return ((RemoteAnimationAdapterWrapperProto) this.instance).getTarget();
        }

        public Builder setTarget(RemoteAnimationTargetProto value) {
            copyOnWrite();
            ((RemoteAnimationAdapterWrapperProto) this.instance).setTarget((RemoteAnimationAdapterWrapperProto) value);
            return this;
        }

        public Builder setTarget(RemoteAnimationTargetProto.Builder builderForValue) {
            copyOnWrite();
            ((RemoteAnimationAdapterWrapperProto) this.instance).setTarget((RemoteAnimationAdapterWrapperProto) builderForValue);
            return this;
        }

        public Builder mergeTarget(RemoteAnimationTargetProto value) {
            copyOnWrite();
            ((RemoteAnimationAdapterWrapperProto) this.instance).mergeTarget(value);
            return this;
        }

        public Builder clearTarget() {
            copyOnWrite();
            ((RemoteAnimationAdapterWrapperProto) this.instance).clearTarget();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RemoteAnimationAdapterWrapperProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RemoteAnimationAdapterWrapperProto other = (RemoteAnimationAdapterWrapperProto) arg1;
                this.target_ = (RemoteAnimationTargetProto) visitor.visitMessage(this.target_, other.target_);
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
                            RemoteAnimationTargetProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (RemoteAnimationTargetProto.Builder) this.target_.toBuilder();
                            }
                            this.target_ = (RemoteAnimationTargetProto) input.readMessage(RemoteAnimationTargetProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.target_);
                                this.target_ = (RemoteAnimationTargetProto) subBuilder.buildPartial();
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
                    synchronized (RemoteAnimationAdapterWrapperProto.class) {
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

    public static RemoteAnimationAdapterWrapperProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RemoteAnimationAdapterWrapperProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
