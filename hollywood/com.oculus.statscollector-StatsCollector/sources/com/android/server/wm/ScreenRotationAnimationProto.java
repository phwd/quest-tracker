package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ScreenRotationAnimationProto extends GeneratedMessageLite<ScreenRotationAnimationProto, Builder> implements ScreenRotationAnimationProtoOrBuilder {
    public static final int ANIMATION_RUNNING_FIELD_NUMBER = 2;
    private static final ScreenRotationAnimationProto DEFAULT_INSTANCE = new ScreenRotationAnimationProto();
    private static volatile Parser<ScreenRotationAnimationProto> PARSER = null;
    public static final int STARTED_FIELD_NUMBER = 1;
    private boolean animationRunning_ = false;
    private int bitField0_;
    private boolean started_ = false;

    private ScreenRotationAnimationProto() {
    }

    @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
    public boolean hasStarted() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
    public boolean getStarted() {
        return this.started_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStarted(boolean value) {
        this.bitField0_ |= 1;
        this.started_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStarted() {
        this.bitField0_ &= -2;
        this.started_ = false;
    }

    @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
    public boolean hasAnimationRunning() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
    public boolean getAnimationRunning() {
        return this.animationRunning_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationRunning(boolean value) {
        this.bitField0_ |= 2;
        this.animationRunning_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimationRunning() {
        this.bitField0_ &= -3;
        this.animationRunning_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.started_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.animationRunning_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.started_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.animationRunning_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ScreenRotationAnimationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ScreenRotationAnimationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ScreenRotationAnimationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ScreenRotationAnimationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ScreenRotationAnimationProto parseFrom(InputStream input) throws IOException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ScreenRotationAnimationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ScreenRotationAnimationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ScreenRotationAnimationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ScreenRotationAnimationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ScreenRotationAnimationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ScreenRotationAnimationProto parseFrom(CodedInputStream input) throws IOException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ScreenRotationAnimationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ScreenRotationAnimationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ScreenRotationAnimationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ScreenRotationAnimationProto, Builder> implements ScreenRotationAnimationProtoOrBuilder {
        private Builder() {
            super(ScreenRotationAnimationProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
        public boolean hasStarted() {
            return ((ScreenRotationAnimationProto) this.instance).hasStarted();
        }

        @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
        public boolean getStarted() {
            return ((ScreenRotationAnimationProto) this.instance).getStarted();
        }

        public Builder setStarted(boolean value) {
            copyOnWrite();
            ((ScreenRotationAnimationProto) this.instance).setStarted(value);
            return this;
        }

        public Builder clearStarted() {
            copyOnWrite();
            ((ScreenRotationAnimationProto) this.instance).clearStarted();
            return this;
        }

        @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
        public boolean hasAnimationRunning() {
            return ((ScreenRotationAnimationProto) this.instance).hasAnimationRunning();
        }

        @Override // com.android.server.wm.ScreenRotationAnimationProtoOrBuilder
        public boolean getAnimationRunning() {
            return ((ScreenRotationAnimationProto) this.instance).getAnimationRunning();
        }

        public Builder setAnimationRunning(boolean value) {
            copyOnWrite();
            ((ScreenRotationAnimationProto) this.instance).setAnimationRunning(value);
            return this;
        }

        public Builder clearAnimationRunning() {
            copyOnWrite();
            ((ScreenRotationAnimationProto) this.instance).clearAnimationRunning();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ScreenRotationAnimationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ScreenRotationAnimationProto other = (ScreenRotationAnimationProto) arg1;
                this.started_ = visitor.visitBoolean(hasStarted(), this.started_, other.hasStarted(), other.started_);
                this.animationRunning_ = visitor.visitBoolean(hasAnimationRunning(), this.animationRunning_, other.hasAnimationRunning(), other.animationRunning_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.started_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.animationRunning_ = input.readBool();
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
                    synchronized (ScreenRotationAnimationProto.class) {
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

    public static ScreenRotationAnimationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ScreenRotationAnimationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
