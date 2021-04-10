package com.android.server.wm;

import android.view.SurfaceProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowOrientationListenerProto extends GeneratedMessageLite<WindowOrientationListenerProto, Builder> implements WindowOrientationListenerProtoOrBuilder {
    private static final WindowOrientationListenerProto DEFAULT_INSTANCE = new WindowOrientationListenerProto();
    public static final int ENABLED_FIELD_NUMBER = 1;
    private static volatile Parser<WindowOrientationListenerProto> PARSER = null;
    public static final int ROTATION_FIELD_NUMBER = 2;
    private int bitField0_;
    private boolean enabled_ = false;
    private int rotation_ = 0;

    private WindowOrientationListenerProto() {
    }

    @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
    public boolean hasEnabled() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
    public boolean getEnabled() {
        return this.enabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(boolean value) {
        this.bitField0_ |= 1;
        this.enabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabled() {
        this.bitField0_ &= -2;
        this.enabled_ = false;
    }

    @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
    public boolean hasRotation() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
    public SurfaceProto.Rotation getRotation() {
        SurfaceProto.Rotation result = SurfaceProto.Rotation.forNumber(this.rotation_);
        return result == null ? SurfaceProto.Rotation.ROTATION_0 : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(SurfaceProto.Rotation value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.rotation_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotation() {
        this.bitField0_ &= -3;
        this.rotation_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.enabled_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.rotation_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.enabled_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.rotation_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowOrientationListenerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowOrientationListenerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowOrientationListenerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowOrientationListenerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowOrientationListenerProto parseFrom(InputStream input) throws IOException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowOrientationListenerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowOrientationListenerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowOrientationListenerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowOrientationListenerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowOrientationListenerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowOrientationListenerProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowOrientationListenerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowOrientationListenerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowOrientationListenerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowOrientationListenerProto, Builder> implements WindowOrientationListenerProtoOrBuilder {
        private Builder() {
            super(WindowOrientationListenerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
        public boolean hasEnabled() {
            return ((WindowOrientationListenerProto) this.instance).hasEnabled();
        }

        @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
        public boolean getEnabled() {
            return ((WindowOrientationListenerProto) this.instance).getEnabled();
        }

        public Builder setEnabled(boolean value) {
            copyOnWrite();
            ((WindowOrientationListenerProto) this.instance).setEnabled(value);
            return this;
        }

        public Builder clearEnabled() {
            copyOnWrite();
            ((WindowOrientationListenerProto) this.instance).clearEnabled();
            return this;
        }

        @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
        public boolean hasRotation() {
            return ((WindowOrientationListenerProto) this.instance).hasRotation();
        }

        @Override // com.android.server.wm.WindowOrientationListenerProtoOrBuilder
        public SurfaceProto.Rotation getRotation() {
            return ((WindowOrientationListenerProto) this.instance).getRotation();
        }

        public Builder setRotation(SurfaceProto.Rotation value) {
            copyOnWrite();
            ((WindowOrientationListenerProto) this.instance).setRotation(value);
            return this;
        }

        public Builder clearRotation() {
            copyOnWrite();
            ((WindowOrientationListenerProto) this.instance).clearRotation();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowOrientationListenerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowOrientationListenerProto other = (WindowOrientationListenerProto) arg1;
                this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, other.hasEnabled(), other.enabled_);
                this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
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
                            this.enabled_ = input.readBool();
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (SurfaceProto.Rotation.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.rotation_ = rawValue;
                            }
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
                    synchronized (WindowOrientationListenerProto.class) {
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

    public static WindowOrientationListenerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowOrientationListenerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
