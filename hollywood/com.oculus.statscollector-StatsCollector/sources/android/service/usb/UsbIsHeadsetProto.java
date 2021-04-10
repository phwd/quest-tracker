package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbIsHeadsetProto extends GeneratedMessageLite<UsbIsHeadsetProto, Builder> implements UsbIsHeadsetProtoOrBuilder {
    private static final UsbIsHeadsetProto DEFAULT_INSTANCE = new UsbIsHeadsetProto();
    public static final int IN_FIELD_NUMBER = 1;
    public static final int OUT_FIELD_NUMBER = 2;
    private static volatile Parser<UsbIsHeadsetProto> PARSER;
    private int bitField0_;
    private boolean in_ = false;
    private boolean out_ = false;

    private UsbIsHeadsetProto() {
    }

    @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
    public boolean hasIn() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
    public boolean getIn() {
        return this.in_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIn(boolean value) {
        this.bitField0_ |= 1;
        this.in_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIn() {
        this.bitField0_ &= -2;
        this.in_ = false;
    }

    @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
    public boolean hasOut() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
    public boolean getOut() {
        return this.out_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOut(boolean value) {
        this.bitField0_ |= 2;
        this.out_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOut() {
        this.bitField0_ &= -3;
        this.out_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.in_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.out_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.in_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.out_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbIsHeadsetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbIsHeadsetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbIsHeadsetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbIsHeadsetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbIsHeadsetProto parseFrom(InputStream input) throws IOException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbIsHeadsetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbIsHeadsetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbIsHeadsetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbIsHeadsetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbIsHeadsetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbIsHeadsetProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbIsHeadsetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbIsHeadsetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbIsHeadsetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbIsHeadsetProto, Builder> implements UsbIsHeadsetProtoOrBuilder {
        private Builder() {
            super(UsbIsHeadsetProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
        public boolean hasIn() {
            return ((UsbIsHeadsetProto) this.instance).hasIn();
        }

        @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
        public boolean getIn() {
            return ((UsbIsHeadsetProto) this.instance).getIn();
        }

        public Builder setIn(boolean value) {
            copyOnWrite();
            ((UsbIsHeadsetProto) this.instance).setIn(value);
            return this;
        }

        public Builder clearIn() {
            copyOnWrite();
            ((UsbIsHeadsetProto) this.instance).clearIn();
            return this;
        }

        @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
        public boolean hasOut() {
            return ((UsbIsHeadsetProto) this.instance).hasOut();
        }

        @Override // android.service.usb.UsbIsHeadsetProtoOrBuilder
        public boolean getOut() {
            return ((UsbIsHeadsetProto) this.instance).getOut();
        }

        public Builder setOut(boolean value) {
            copyOnWrite();
            ((UsbIsHeadsetProto) this.instance).setOut(value);
            return this;
        }

        public Builder clearOut() {
            copyOnWrite();
            ((UsbIsHeadsetProto) this.instance).clearOut();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbIsHeadsetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbIsHeadsetProto other = (UsbIsHeadsetProto) arg1;
                this.in_ = visitor.visitBoolean(hasIn(), this.in_, other.hasIn(), other.in_);
                this.out_ = visitor.visitBoolean(hasOut(), this.out_, other.hasOut(), other.out_);
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
                            this.in_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.out_ = input.readBool();
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
                    synchronized (UsbIsHeadsetProto.class) {
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

    public static UsbIsHeadsetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbIsHeadsetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
