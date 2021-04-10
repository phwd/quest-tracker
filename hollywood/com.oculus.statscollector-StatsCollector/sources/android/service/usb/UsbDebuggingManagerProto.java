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

public final class UsbDebuggingManagerProto extends GeneratedMessageLite<UsbDebuggingManagerProto, Builder> implements UsbDebuggingManagerProtoOrBuilder {
    public static final int CONNECTED_TO_ADB_FIELD_NUMBER = 1;
    private static final UsbDebuggingManagerProto DEFAULT_INSTANCE = new UsbDebuggingManagerProto();
    public static final int LAST_KEY_RECEIVED_FIELD_NUMBER = 2;
    private static volatile Parser<UsbDebuggingManagerProto> PARSER = null;
    public static final int SYSTEM_KEYS_FIELD_NUMBER = 4;
    public static final int USER_KEYS_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean connectedToAdb_ = false;
    private String lastKeyReceived_ = "";
    private String systemKeys_ = "";
    private String userKeys_ = "";

    private UsbDebuggingManagerProto() {
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public boolean hasConnectedToAdb() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public boolean getConnectedToAdb() {
        return this.connectedToAdb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectedToAdb(boolean value) {
        this.bitField0_ |= 1;
        this.connectedToAdb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnectedToAdb() {
        this.bitField0_ &= -2;
        this.connectedToAdb_ = false;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public boolean hasLastKeyReceived() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public String getLastKeyReceived() {
        return this.lastKeyReceived_;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public ByteString getLastKeyReceivedBytes() {
        return ByteString.copyFromUtf8(this.lastKeyReceived_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKeyReceived(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.lastKeyReceived_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastKeyReceived() {
        this.bitField0_ &= -3;
        this.lastKeyReceived_ = getDefaultInstance().getLastKeyReceived();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKeyReceivedBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.lastKeyReceived_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public boolean hasUserKeys() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public String getUserKeys() {
        return this.userKeys_;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public ByteString getUserKeysBytes() {
        return ByteString.copyFromUtf8(this.userKeys_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserKeys(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.userKeys_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserKeys() {
        this.bitField0_ &= -5;
        this.userKeys_ = getDefaultInstance().getUserKeys();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserKeysBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.userKeys_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public boolean hasSystemKeys() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public String getSystemKeys() {
        return this.systemKeys_;
    }

    @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
    public ByteString getSystemKeysBytes() {
        return ByteString.copyFromUtf8(this.systemKeys_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemKeys(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.systemKeys_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemKeys() {
        this.bitField0_ &= -9;
        this.systemKeys_ = getDefaultInstance().getSystemKeys();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemKeysBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.systemKeys_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.connectedToAdb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getLastKeyReceived());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getUserKeys());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getSystemKeys());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.connectedToAdb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getLastKeyReceived());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getUserKeys());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getSystemKeys());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbDebuggingManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDebuggingManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDebuggingManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDebuggingManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDebuggingManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDebuggingManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDebuggingManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbDebuggingManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDebuggingManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDebuggingManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDebuggingManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDebuggingManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDebuggingManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbDebuggingManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbDebuggingManagerProto, Builder> implements UsbDebuggingManagerProtoOrBuilder {
        private Builder() {
            super(UsbDebuggingManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public boolean hasConnectedToAdb() {
            return ((UsbDebuggingManagerProto) this.instance).hasConnectedToAdb();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public boolean getConnectedToAdb() {
            return ((UsbDebuggingManagerProto) this.instance).getConnectedToAdb();
        }

        public Builder setConnectedToAdb(boolean value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setConnectedToAdb(value);
            return this;
        }

        public Builder clearConnectedToAdb() {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).clearConnectedToAdb();
            return this;
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public boolean hasLastKeyReceived() {
            return ((UsbDebuggingManagerProto) this.instance).hasLastKeyReceived();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public String getLastKeyReceived() {
            return ((UsbDebuggingManagerProto) this.instance).getLastKeyReceived();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public ByteString getLastKeyReceivedBytes() {
            return ((UsbDebuggingManagerProto) this.instance).getLastKeyReceivedBytes();
        }

        public Builder setLastKeyReceived(String value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setLastKeyReceived(value);
            return this;
        }

        public Builder clearLastKeyReceived() {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).clearLastKeyReceived();
            return this;
        }

        public Builder setLastKeyReceivedBytes(ByteString value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setLastKeyReceivedBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public boolean hasUserKeys() {
            return ((UsbDebuggingManagerProto) this.instance).hasUserKeys();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public String getUserKeys() {
            return ((UsbDebuggingManagerProto) this.instance).getUserKeys();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public ByteString getUserKeysBytes() {
            return ((UsbDebuggingManagerProto) this.instance).getUserKeysBytes();
        }

        public Builder setUserKeys(String value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setUserKeys(value);
            return this;
        }

        public Builder clearUserKeys() {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).clearUserKeys();
            return this;
        }

        public Builder setUserKeysBytes(ByteString value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setUserKeysBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public boolean hasSystemKeys() {
            return ((UsbDebuggingManagerProto) this.instance).hasSystemKeys();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public String getSystemKeys() {
            return ((UsbDebuggingManagerProto) this.instance).getSystemKeys();
        }

        @Override // android.service.usb.UsbDebuggingManagerProtoOrBuilder
        public ByteString getSystemKeysBytes() {
            return ((UsbDebuggingManagerProto) this.instance).getSystemKeysBytes();
        }

        public Builder setSystemKeys(String value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setSystemKeys(value);
            return this;
        }

        public Builder clearSystemKeys() {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).clearSystemKeys();
            return this;
        }

        public Builder setSystemKeysBytes(ByteString value) {
            copyOnWrite();
            ((UsbDebuggingManagerProto) this.instance).setSystemKeysBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbDebuggingManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbDebuggingManagerProto other = (UsbDebuggingManagerProto) arg1;
                this.connectedToAdb_ = visitor.visitBoolean(hasConnectedToAdb(), this.connectedToAdb_, other.hasConnectedToAdb(), other.connectedToAdb_);
                this.lastKeyReceived_ = visitor.visitString(hasLastKeyReceived(), this.lastKeyReceived_, other.hasLastKeyReceived(), other.lastKeyReceived_);
                this.userKeys_ = visitor.visitString(hasUserKeys(), this.userKeys_, other.hasUserKeys(), other.userKeys_);
                this.systemKeys_ = visitor.visitString(hasSystemKeys(), this.systemKeys_, other.hasSystemKeys(), other.systemKeys_);
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
                            this.connectedToAdb_ = input.readBool();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.lastKeyReceived_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.userKeys_ = s2;
                        } else if (tag == 34) {
                            String s3 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.systemKeys_ = s3;
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
                    synchronized (UsbDebuggingManagerProto.class) {
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

    public static UsbDebuggingManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbDebuggingManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
