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

public final class UsbMidiDeviceProto extends GeneratedMessageLite<UsbMidiDeviceProto, Builder> implements UsbMidiDeviceProtoOrBuilder {
    public static final int CARD_FIELD_NUMBER = 1;
    private static final UsbMidiDeviceProto DEFAULT_INSTANCE = new UsbMidiDeviceProto();
    public static final int DEVICE_ADDRESS_FIELD_NUMBER = 3;
    public static final int DEVICE_FIELD_NUMBER = 2;
    private static volatile Parser<UsbMidiDeviceProto> PARSER;
    private int bitField0_;
    private int card_ = 0;
    private String deviceAddress_ = "";
    private int device_ = 0;

    private UsbMidiDeviceProto() {
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public boolean hasCard() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public int getCard() {
        return this.card_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCard(int value) {
        this.bitField0_ |= 1;
        this.card_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCard() {
        this.bitField0_ &= -2;
        this.card_ = 0;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public boolean hasDevice() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public int getDevice() {
        return this.device_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevice(int value) {
        this.bitField0_ |= 2;
        this.device_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevice() {
        this.bitField0_ &= -3;
        this.device_ = 0;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public boolean hasDeviceAddress() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public String getDeviceAddress() {
        return this.deviceAddress_;
    }

    @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
    public ByteString getDeviceAddressBytes() {
        return ByteString.copyFromUtf8(this.deviceAddress_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAddress(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.deviceAddress_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceAddress() {
        this.bitField0_ &= -5;
        this.deviceAddress_ = getDefaultInstance().getDeviceAddress();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAddressBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.deviceAddress_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.card_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.device_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getDeviceAddress());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.card_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.device_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getDeviceAddress());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbMidiDeviceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbMidiDeviceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbMidiDeviceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbMidiDeviceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbMidiDeviceProto parseFrom(InputStream input) throws IOException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbMidiDeviceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbMidiDeviceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbMidiDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbMidiDeviceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbMidiDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbMidiDeviceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbMidiDeviceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbMidiDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbMidiDeviceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbMidiDeviceProto, Builder> implements UsbMidiDeviceProtoOrBuilder {
        private Builder() {
            super(UsbMidiDeviceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public boolean hasCard() {
            return ((UsbMidiDeviceProto) this.instance).hasCard();
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public int getCard() {
            return ((UsbMidiDeviceProto) this.instance).getCard();
        }

        public Builder setCard(int value) {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).setCard(value);
            return this;
        }

        public Builder clearCard() {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).clearCard();
            return this;
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public boolean hasDevice() {
            return ((UsbMidiDeviceProto) this.instance).hasDevice();
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public int getDevice() {
            return ((UsbMidiDeviceProto) this.instance).getDevice();
        }

        public Builder setDevice(int value) {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).setDevice(value);
            return this;
        }

        public Builder clearDevice() {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).clearDevice();
            return this;
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public boolean hasDeviceAddress() {
            return ((UsbMidiDeviceProto) this.instance).hasDeviceAddress();
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public String getDeviceAddress() {
            return ((UsbMidiDeviceProto) this.instance).getDeviceAddress();
        }

        @Override // android.service.usb.UsbMidiDeviceProtoOrBuilder
        public ByteString getDeviceAddressBytes() {
            return ((UsbMidiDeviceProto) this.instance).getDeviceAddressBytes();
        }

        public Builder setDeviceAddress(String value) {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).setDeviceAddress(value);
            return this;
        }

        public Builder clearDeviceAddress() {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).clearDeviceAddress();
            return this;
        }

        public Builder setDeviceAddressBytes(ByteString value) {
            copyOnWrite();
            ((UsbMidiDeviceProto) this.instance).setDeviceAddressBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbMidiDeviceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbMidiDeviceProto other = (UsbMidiDeviceProto) arg1;
                this.card_ = visitor.visitInt(hasCard(), this.card_, other.hasCard(), other.card_);
                this.device_ = visitor.visitInt(hasDevice(), this.device_, other.hasDevice(), other.device_);
                this.deviceAddress_ = visitor.visitString(hasDeviceAddress(), this.deviceAddress_, other.hasDeviceAddress(), other.deviceAddress_);
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
                            this.card_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.device_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.deviceAddress_ = s;
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
                    synchronized (UsbMidiDeviceProto.class) {
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

    public static UsbMidiDeviceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbMidiDeviceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
