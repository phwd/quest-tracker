package com.android.server.wm;

import android.app.StatusBarManagerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class BarControllerProto extends GeneratedMessageLite<BarControllerProto, Builder> implements BarControllerProtoOrBuilder {
    private static final BarControllerProto DEFAULT_INSTANCE = new BarControllerProto();
    private static volatile Parser<BarControllerProto> PARSER = null;
    public static final int STATE_FIELD_NUMBER = 1;
    public static final int TRANSIENT_STATE_FIELD_NUMBER = 2;
    private int bitField0_;
    private int state_ = 0;
    private int transientState_ = 0;

    private BarControllerProto() {
    }

    @Override // com.android.server.wm.BarControllerProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.BarControllerProtoOrBuilder
    public StatusBarManagerProto.WindowState getState() {
        StatusBarManagerProto.WindowState result = StatusBarManagerProto.WindowState.forNumber(this.state_);
        return result == null ? StatusBarManagerProto.WindowState.WINDOW_STATE_SHOWING : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(StatusBarManagerProto.WindowState value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -2;
        this.state_ = 0;
    }

    @Override // com.android.server.wm.BarControllerProtoOrBuilder
    public boolean hasTransientState() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.BarControllerProtoOrBuilder
    public StatusBarManagerProto.TransientWindowState getTransientState() {
        StatusBarManagerProto.TransientWindowState result = StatusBarManagerProto.TransientWindowState.forNumber(this.transientState_);
        return result == null ? StatusBarManagerProto.TransientWindowState.TRANSIENT_BAR_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTransientState(StatusBarManagerProto.TransientWindowState value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.transientState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTransientState() {
        this.bitField0_ &= -3;
        this.transientState_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.state_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.transientState_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.state_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.transientState_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BarControllerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BarControllerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BarControllerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BarControllerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BarControllerProto parseFrom(InputStream input) throws IOException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BarControllerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BarControllerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BarControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BarControllerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BarControllerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BarControllerProto parseFrom(CodedInputStream input) throws IOException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BarControllerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BarControllerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BarControllerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BarControllerProto, Builder> implements BarControllerProtoOrBuilder {
        private Builder() {
            super(BarControllerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.BarControllerProtoOrBuilder
        public boolean hasState() {
            return ((BarControllerProto) this.instance).hasState();
        }

        @Override // com.android.server.wm.BarControllerProtoOrBuilder
        public StatusBarManagerProto.WindowState getState() {
            return ((BarControllerProto) this.instance).getState();
        }

        public Builder setState(StatusBarManagerProto.WindowState value) {
            copyOnWrite();
            ((BarControllerProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((BarControllerProto) this.instance).clearState();
            return this;
        }

        @Override // com.android.server.wm.BarControllerProtoOrBuilder
        public boolean hasTransientState() {
            return ((BarControllerProto) this.instance).hasTransientState();
        }

        @Override // com.android.server.wm.BarControllerProtoOrBuilder
        public StatusBarManagerProto.TransientWindowState getTransientState() {
            return ((BarControllerProto) this.instance).getTransientState();
        }

        public Builder setTransientState(StatusBarManagerProto.TransientWindowState value) {
            copyOnWrite();
            ((BarControllerProto) this.instance).setTransientState(value);
            return this;
        }

        public Builder clearTransientState() {
            copyOnWrite();
            ((BarControllerProto) this.instance).clearTransientState();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BarControllerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BarControllerProto other = (BarControllerProto) arg1;
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.transientState_ = visitor.visitInt(hasTransientState(), this.transientState_, other.hasTransientState(), other.transientState_);
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
                            int rawValue = input.readEnum();
                            if (StatusBarManagerProto.WindowState.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.state_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (StatusBarManagerProto.TransientWindowState.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.transientState_ = rawValue2;
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
                    synchronized (BarControllerProto.class) {
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

    public static BarControllerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BarControllerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
