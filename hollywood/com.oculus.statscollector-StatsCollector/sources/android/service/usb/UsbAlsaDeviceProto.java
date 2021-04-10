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

public final class UsbAlsaDeviceProto extends GeneratedMessageLite<UsbAlsaDeviceProto, Builder> implements UsbAlsaDeviceProtoOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 6;
    public static final int CARD_FIELD_NUMBER = 1;
    private static final UsbAlsaDeviceProto DEFAULT_INSTANCE = new UsbAlsaDeviceProto();
    public static final int DEVICE_FIELD_NUMBER = 2;
    public static final int HAS_CAPTURE_FIELD_NUMBER = 5;
    public static final int HAS_PLAYBACK_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser<UsbAlsaDeviceProto> PARSER;
    private String address_ = "";
    private int bitField0_;
    private int card_ = 0;
    private int device_ = 0;
    private boolean hasCapture_ = false;
    private boolean hasPlayback_ = false;
    private String name_ = "";

    private UsbAlsaDeviceProto() {
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasCard() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
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

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasDevice() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
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

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -5;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasHasPlayback() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean getHasPlayback() {
        return this.hasPlayback_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasPlayback(boolean value) {
        this.bitField0_ |= 8;
        this.hasPlayback_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasPlayback() {
        this.bitField0_ &= -9;
        this.hasPlayback_ = false;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasHasCapture() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean getHasCapture() {
        return this.hasCapture_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasCapture(boolean value) {
        this.bitField0_ |= 16;
        this.hasCapture_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasCapture() {
        this.bitField0_ &= -17;
        this.hasCapture_ = false;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public boolean hasAddress() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public String getAddress() {
        return this.address_;
    }

    @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
    public ByteString getAddressBytes() {
        return ByteString.copyFromUtf8(this.address_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAddress(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.address_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAddress() {
        this.bitField0_ &= -33;
        this.address_ = getDefaultInstance().getAddress();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAddressBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.address_ = value.toStringUtf8();
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
            output.writeString(3, getName());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.hasPlayback_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.hasCapture_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getAddress());
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
            size2 += CodedOutputStream.computeStringSize(3, getName());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.hasPlayback_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.hasCapture_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getAddress());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbAlsaDeviceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAlsaDeviceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAlsaDeviceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAlsaDeviceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAlsaDeviceProto parseFrom(InputStream input) throws IOException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaDeviceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAlsaDeviceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbAlsaDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaDeviceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAlsaDeviceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaDeviceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbAlsaDeviceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbAlsaDeviceProto, Builder> implements UsbAlsaDeviceProtoOrBuilder {
        private Builder() {
            super(UsbAlsaDeviceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasCard() {
            return ((UsbAlsaDeviceProto) this.instance).hasCard();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public int getCard() {
            return ((UsbAlsaDeviceProto) this.instance).getCard();
        }

        public Builder setCard(int value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setCard(value);
            return this;
        }

        public Builder clearCard() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearCard();
            return this;
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasDevice() {
            return ((UsbAlsaDeviceProto) this.instance).hasDevice();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public int getDevice() {
            return ((UsbAlsaDeviceProto) this.instance).getDevice();
        }

        public Builder setDevice(int value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setDevice(value);
            return this;
        }

        public Builder clearDevice() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearDevice();
            return this;
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasName() {
            return ((UsbAlsaDeviceProto) this.instance).hasName();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public String getName() {
            return ((UsbAlsaDeviceProto) this.instance).getName();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public ByteString getNameBytes() {
            return ((UsbAlsaDeviceProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasHasPlayback() {
            return ((UsbAlsaDeviceProto) this.instance).hasHasPlayback();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean getHasPlayback() {
            return ((UsbAlsaDeviceProto) this.instance).getHasPlayback();
        }

        public Builder setHasPlayback(boolean value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setHasPlayback(value);
            return this;
        }

        public Builder clearHasPlayback() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearHasPlayback();
            return this;
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasHasCapture() {
            return ((UsbAlsaDeviceProto) this.instance).hasHasCapture();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean getHasCapture() {
            return ((UsbAlsaDeviceProto) this.instance).getHasCapture();
        }

        public Builder setHasCapture(boolean value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setHasCapture(value);
            return this;
        }

        public Builder clearHasCapture() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearHasCapture();
            return this;
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public boolean hasAddress() {
            return ((UsbAlsaDeviceProto) this.instance).hasAddress();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public String getAddress() {
            return ((UsbAlsaDeviceProto) this.instance).getAddress();
        }

        @Override // android.service.usb.UsbAlsaDeviceProtoOrBuilder
        public ByteString getAddressBytes() {
            return ((UsbAlsaDeviceProto) this.instance).getAddressBytes();
        }

        public Builder setAddress(String value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setAddress(value);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).clearAddress();
            return this;
        }

        public Builder setAddressBytes(ByteString value) {
            copyOnWrite();
            ((UsbAlsaDeviceProto) this.instance).setAddressBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbAlsaDeviceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbAlsaDeviceProto other = (UsbAlsaDeviceProto) arg1;
                this.card_ = visitor.visitInt(hasCard(), this.card_, other.hasCard(), other.card_);
                this.device_ = visitor.visitInt(hasDevice(), this.device_, other.hasDevice(), other.device_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.hasPlayback_ = visitor.visitBoolean(hasHasPlayback(), this.hasPlayback_, other.hasHasPlayback(), other.hasPlayback_);
                this.hasCapture_ = visitor.visitBoolean(hasHasCapture(), this.hasCapture_, other.hasHasCapture(), other.hasCapture_);
                this.address_ = visitor.visitString(hasAddress(), this.address_, other.hasAddress(), other.address_);
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
                            this.name_ = s;
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.hasPlayback_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.hasCapture_ = input.readBool();
                        } else if (tag == 50) {
                            String s2 = input.readString();
                            this.bitField0_ |= 32;
                            this.address_ = s2;
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
                    synchronized (UsbAlsaDeviceProto.class) {
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

    public static UsbAlsaDeviceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbAlsaDeviceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
