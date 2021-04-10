package android.service.usb;

import android.service.usb.UsbDebuggingManagerProto;
import android.service.usb.UsbHandlerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbDeviceManagerProto extends GeneratedMessageLite<UsbDeviceManagerProto, Builder> implements UsbDeviceManagerProtoOrBuilder {
    public static final int DEBUGGING_MANAGER_FIELD_NUMBER = 2;
    private static final UsbDeviceManagerProto DEFAULT_INSTANCE = new UsbDeviceManagerProto();
    public static final int HANDLER_FIELD_NUMBER = 1;
    private static volatile Parser<UsbDeviceManagerProto> PARSER;
    private int bitField0_;
    private UsbDebuggingManagerProto debuggingManager_;
    private UsbHandlerProto handler_;

    private UsbDeviceManagerProto() {
    }

    @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
    public boolean hasHandler() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
    public UsbHandlerProto getHandler() {
        UsbHandlerProto usbHandlerProto = this.handler_;
        return usbHandlerProto == null ? UsbHandlerProto.getDefaultInstance() : usbHandlerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHandler(UsbHandlerProto value) {
        if (value != null) {
            this.handler_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHandler(UsbHandlerProto.Builder builderForValue) {
        this.handler_ = (UsbHandlerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHandler(UsbHandlerProto value) {
        UsbHandlerProto usbHandlerProto = this.handler_;
        if (usbHandlerProto == null || usbHandlerProto == UsbHandlerProto.getDefaultInstance()) {
            this.handler_ = value;
        } else {
            this.handler_ = (UsbHandlerProto) ((UsbHandlerProto.Builder) UsbHandlerProto.newBuilder(this.handler_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHandler() {
        this.handler_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
    public boolean hasDebuggingManager() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
    public UsbDebuggingManagerProto getDebuggingManager() {
        UsbDebuggingManagerProto usbDebuggingManagerProto = this.debuggingManager_;
        return usbDebuggingManagerProto == null ? UsbDebuggingManagerProto.getDefaultInstance() : usbDebuggingManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebuggingManager(UsbDebuggingManagerProto value) {
        if (value != null) {
            this.debuggingManager_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebuggingManager(UsbDebuggingManagerProto.Builder builderForValue) {
        this.debuggingManager_ = (UsbDebuggingManagerProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDebuggingManager(UsbDebuggingManagerProto value) {
        UsbDebuggingManagerProto usbDebuggingManagerProto = this.debuggingManager_;
        if (usbDebuggingManagerProto == null || usbDebuggingManagerProto == UsbDebuggingManagerProto.getDefaultInstance()) {
            this.debuggingManager_ = value;
        } else {
            this.debuggingManager_ = (UsbDebuggingManagerProto) ((UsbDebuggingManagerProto.Builder) UsbDebuggingManagerProto.newBuilder(this.debuggingManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDebuggingManager() {
        this.debuggingManager_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getHandler());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getDebuggingManager());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getHandler());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getDebuggingManager());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbDeviceManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbDeviceManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbDeviceManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbDeviceManagerProto, Builder> implements UsbDeviceManagerProtoOrBuilder {
        private Builder() {
            super(UsbDeviceManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
        public boolean hasHandler() {
            return ((UsbDeviceManagerProto) this.instance).hasHandler();
        }

        @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
        public UsbHandlerProto getHandler() {
            return ((UsbDeviceManagerProto) this.instance).getHandler();
        }

        public Builder setHandler(UsbHandlerProto value) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).setHandler((UsbDeviceManagerProto) value);
            return this;
        }

        public Builder setHandler(UsbHandlerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).setHandler((UsbDeviceManagerProto) builderForValue);
            return this;
        }

        public Builder mergeHandler(UsbHandlerProto value) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).mergeHandler(value);
            return this;
        }

        public Builder clearHandler() {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).clearHandler();
            return this;
        }

        @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
        public boolean hasDebuggingManager() {
            return ((UsbDeviceManagerProto) this.instance).hasDebuggingManager();
        }

        @Override // android.service.usb.UsbDeviceManagerProtoOrBuilder
        public UsbDebuggingManagerProto getDebuggingManager() {
            return ((UsbDeviceManagerProto) this.instance).getDebuggingManager();
        }

        public Builder setDebuggingManager(UsbDebuggingManagerProto value) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).setDebuggingManager((UsbDeviceManagerProto) value);
            return this;
        }

        public Builder setDebuggingManager(UsbDebuggingManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).setDebuggingManager((UsbDeviceManagerProto) builderForValue);
            return this;
        }

        public Builder mergeDebuggingManager(UsbDebuggingManagerProto value) {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).mergeDebuggingManager(value);
            return this;
        }

        public Builder clearDebuggingManager() {
            copyOnWrite();
            ((UsbDeviceManagerProto) this.instance).clearDebuggingManager();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbDeviceManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbDeviceManagerProto other = (UsbDeviceManagerProto) arg1;
                this.handler_ = (UsbHandlerProto) visitor.visitMessage(this.handler_, other.handler_);
                this.debuggingManager_ = (UsbDebuggingManagerProto) visitor.visitMessage(this.debuggingManager_, other.debuggingManager_);
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
                            UsbHandlerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (UsbHandlerProto.Builder) this.handler_.toBuilder();
                            }
                            this.handler_ = (UsbHandlerProto) input.readMessage(UsbHandlerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.handler_);
                                this.handler_ = (UsbHandlerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            UsbDebuggingManagerProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (UsbDebuggingManagerProto.Builder) this.debuggingManager_.toBuilder();
                            }
                            this.debuggingManager_ = (UsbDebuggingManagerProto) input.readMessage(UsbDebuggingManagerProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.debuggingManager_);
                                this.debuggingManager_ = (UsbDebuggingManagerProto) subBuilder2.buildPartial();
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
                    synchronized (UsbDeviceManagerProto.class) {
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

    public static UsbDeviceManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbDeviceManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
