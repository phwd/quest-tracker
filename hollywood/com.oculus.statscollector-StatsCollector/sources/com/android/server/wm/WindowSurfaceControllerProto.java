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

public final class WindowSurfaceControllerProto extends GeneratedMessageLite<WindowSurfaceControllerProto, Builder> implements WindowSurfaceControllerProtoOrBuilder {
    private static final WindowSurfaceControllerProto DEFAULT_INSTANCE = new WindowSurfaceControllerProto();
    public static final int LAYER_FIELD_NUMBER = 2;
    private static volatile Parser<WindowSurfaceControllerProto> PARSER = null;
    public static final int SHOWN_FIELD_NUMBER = 1;
    private int bitField0_;
    private int layer_ = 0;
    private boolean shown_ = false;

    private WindowSurfaceControllerProto() {
    }

    @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
    public boolean hasShown() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
    public boolean getShown() {
        return this.shown_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShown(boolean value) {
        this.bitField0_ |= 1;
        this.shown_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShown() {
        this.bitField0_ &= -2;
        this.shown_ = false;
    }

    @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
    public boolean hasLayer() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
    public int getLayer() {
        return this.layer_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLayer(int value) {
        this.bitField0_ |= 2;
        this.layer_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLayer() {
        this.bitField0_ &= -3;
        this.layer_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.shown_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.layer_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.shown_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.layer_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowSurfaceControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowSurfaceControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowSurfaceControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowSurfaceControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowSurfaceControllerProto parseFrom(InputStream input) throws IOException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowSurfaceControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowSurfaceControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowSurfaceControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowSurfaceControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowSurfaceControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowSurfaceControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowSurfaceControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowSurfaceControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowSurfaceControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowSurfaceControllerProto, Builder> implements WindowSurfaceControllerProtoOrBuilder {
        private Builder() {
            super(WindowSurfaceControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
        public boolean hasShown() {
            return ((WindowSurfaceControllerProto) this.instance).hasShown();
        }

        @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
        public boolean getShown() {
            return ((WindowSurfaceControllerProto) this.instance).getShown();
        }

        public Builder setShown(boolean value) {
            copyOnWrite();
            ((WindowSurfaceControllerProto) this.instance).setShown(value);
            return this;
        }

        public Builder clearShown() {
            copyOnWrite();
            ((WindowSurfaceControllerProto) this.instance).clearShown();
            return this;
        }

        @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
        public boolean hasLayer() {
            return ((WindowSurfaceControllerProto) this.instance).hasLayer();
        }

        @Override // com.android.server.wm.WindowSurfaceControllerProtoOrBuilder
        public int getLayer() {
            return ((WindowSurfaceControllerProto) this.instance).getLayer();
        }

        public Builder setLayer(int value) {
            copyOnWrite();
            ((WindowSurfaceControllerProto) this.instance).setLayer(value);
            return this;
        }

        public Builder clearLayer() {
            copyOnWrite();
            ((WindowSurfaceControllerProto) this.instance).clearLayer();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowSurfaceControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowSurfaceControllerProto other = (WindowSurfaceControllerProto) arg1;
                this.shown_ = visitor.visitBoolean(hasShown(), this.shown_, other.hasShown(), other.shown_);
                this.layer_ = visitor.visitInt(hasLayer(), this.layer_, other.hasLayer(), other.layer_);
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
                            this.shown_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.layer_ = input.readInt32();
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
                    synchronized (WindowSurfaceControllerProto.class) {
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

    public static WindowSurfaceControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowSurfaceControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
