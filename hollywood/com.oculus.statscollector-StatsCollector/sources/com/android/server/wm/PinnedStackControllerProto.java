package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PinnedStackControllerProto extends GeneratedMessageLite<PinnedStackControllerProto, Builder> implements PinnedStackControllerProtoOrBuilder {
    public static final int DEFAULT_BOUNDS_FIELD_NUMBER = 1;
    private static final PinnedStackControllerProto DEFAULT_INSTANCE = new PinnedStackControllerProto();
    public static final int MOVEMENT_BOUNDS_FIELD_NUMBER = 2;
    private static volatile Parser<PinnedStackControllerProto> PARSER;
    private int bitField0_;
    private RectProto defaultBounds_;
    private RectProto movementBounds_;

    private PinnedStackControllerProto() {
    }

    @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
    public boolean hasDefaultBounds() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
    public RectProto getDefaultBounds() {
        RectProto rectProto = this.defaultBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultBounds(RectProto value) {
        if (value != null) {
            this.defaultBounds_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultBounds(RectProto.Builder builderForValue) {
        this.defaultBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDefaultBounds(RectProto value) {
        RectProto rectProto = this.defaultBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.defaultBounds_ = value;
        } else {
            this.defaultBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.defaultBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDefaultBounds() {
        this.defaultBounds_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
    public boolean hasMovementBounds() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
    public RectProto getMovementBounds() {
        RectProto rectProto = this.movementBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMovementBounds(RectProto value) {
        if (value != null) {
            this.movementBounds_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMovementBounds(RectProto.Builder builderForValue) {
        this.movementBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMovementBounds(RectProto value) {
        RectProto rectProto = this.movementBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.movementBounds_ = value;
        } else {
            this.movementBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.movementBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMovementBounds() {
        this.movementBounds_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getDefaultBounds());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getMovementBounds());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getDefaultBounds());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getMovementBounds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PinnedStackControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PinnedStackControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PinnedStackControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PinnedStackControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PinnedStackControllerProto parseFrom(InputStream input) throws IOException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PinnedStackControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PinnedStackControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PinnedStackControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PinnedStackControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PinnedStackControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PinnedStackControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PinnedStackControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PinnedStackControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PinnedStackControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PinnedStackControllerProto, Builder> implements PinnedStackControllerProtoOrBuilder {
        private Builder() {
            super(PinnedStackControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
        public boolean hasDefaultBounds() {
            return ((PinnedStackControllerProto) this.instance).hasDefaultBounds();
        }

        @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
        public RectProto getDefaultBounds() {
            return ((PinnedStackControllerProto) this.instance).getDefaultBounds();
        }

        public Builder setDefaultBounds(RectProto value) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).setDefaultBounds((PinnedStackControllerProto) value);
            return this;
        }

        public Builder setDefaultBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).setDefaultBounds((PinnedStackControllerProto) builderForValue);
            return this;
        }

        public Builder mergeDefaultBounds(RectProto value) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).mergeDefaultBounds(value);
            return this;
        }

        public Builder clearDefaultBounds() {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).clearDefaultBounds();
            return this;
        }

        @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
        public boolean hasMovementBounds() {
            return ((PinnedStackControllerProto) this.instance).hasMovementBounds();
        }

        @Override // com.android.server.wm.PinnedStackControllerProtoOrBuilder
        public RectProto getMovementBounds() {
            return ((PinnedStackControllerProto) this.instance).getMovementBounds();
        }

        public Builder setMovementBounds(RectProto value) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).setMovementBounds((PinnedStackControllerProto) value);
            return this;
        }

        public Builder setMovementBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).setMovementBounds((PinnedStackControllerProto) builderForValue);
            return this;
        }

        public Builder mergeMovementBounds(RectProto value) {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).mergeMovementBounds(value);
            return this;
        }

        public Builder clearMovementBounds() {
            copyOnWrite();
            ((PinnedStackControllerProto) this.instance).clearMovementBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PinnedStackControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PinnedStackControllerProto other = (PinnedStackControllerProto) arg1;
                this.defaultBounds_ = (RectProto) visitor.visitMessage(this.defaultBounds_, other.defaultBounds_);
                this.movementBounds_ = (RectProto) visitor.visitMessage(this.movementBounds_, other.movementBounds_);
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
                            RectProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (RectProto.Builder) this.defaultBounds_.toBuilder();
                            }
                            this.defaultBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.defaultBounds_);
                                this.defaultBounds_ = (RectProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            RectProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (RectProto.Builder) this.movementBounds_.toBuilder();
                            }
                            this.movementBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.movementBounds_);
                                this.movementBounds_ = (RectProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (PinnedStackControllerProto.class) {
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

    public static PinnedStackControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PinnedStackControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
