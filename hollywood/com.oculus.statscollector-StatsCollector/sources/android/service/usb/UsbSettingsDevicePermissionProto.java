package android.service.usb;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class UsbSettingsDevicePermissionProto extends GeneratedMessageLite<UsbSettingsDevicePermissionProto, Builder> implements UsbSettingsDevicePermissionProtoOrBuilder {
    private static final UsbSettingsDevicePermissionProto DEFAULT_INSTANCE = new UsbSettingsDevicePermissionProto();
    public static final int DEVICE_NAME_FIELD_NUMBER = 1;
    private static volatile Parser<UsbSettingsDevicePermissionProto> PARSER = null;
    public static final int UIDS_FIELD_NUMBER = 2;
    private int bitField0_;
    private String deviceName_ = "";
    private Internal.IntList uids_ = emptyIntList();

    private UsbSettingsDevicePermissionProto() {
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public boolean hasDeviceName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public String getDeviceName() {
        return this.deviceName_;
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public ByteString getDeviceNameBytes() {
        return ByteString.copyFromUtf8(this.deviceName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.deviceName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceName() {
        this.bitField0_ &= -2;
        this.deviceName_ = getDefaultInstance().getDeviceName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.deviceName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public List<Integer> getUidsList() {
        return this.uids_;
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public int getUidsCount() {
        return this.uids_.size();
    }

    @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
    public int getUids(int index) {
        return this.uids_.getInt(index);
    }

    private void ensureUidsIsMutable() {
        if (!this.uids_.isModifiable()) {
            this.uids_ = GeneratedMessageLite.mutableCopy(this.uids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUids(int index, int value) {
        ensureUidsIsMutable();
        this.uids_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUids(int value) {
        ensureUidsIsMutable();
        this.uids_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUids(Iterable<? extends Integer> values) {
        ensureUidsIsMutable();
        AbstractMessageLite.addAll(values, this.uids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUids() {
        this.uids_ = emptyIntList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getDeviceName());
        }
        for (int i = 0; i < this.uids_.size(); i++) {
            output.writeInt32(2, this.uids_.getInt(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getDeviceName());
        }
        int dataSize = 0;
        for (int i = 0; i < this.uids_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.uids_.getInt(i));
        }
        int size3 = size2 + dataSize + (getUidsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbSettingsDevicePermissionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(InputStream input) throws IOException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsDevicePermissionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbSettingsDevicePermissionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePermissionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePermissionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePermissionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbSettingsDevicePermissionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbSettingsDevicePermissionProto, Builder> implements UsbSettingsDevicePermissionProtoOrBuilder {
        private Builder() {
            super(UsbSettingsDevicePermissionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public boolean hasDeviceName() {
            return ((UsbSettingsDevicePermissionProto) this.instance).hasDeviceName();
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public String getDeviceName() {
            return ((UsbSettingsDevicePermissionProto) this.instance).getDeviceName();
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public ByteString getDeviceNameBytes() {
            return ((UsbSettingsDevicePermissionProto) this.instance).getDeviceNameBytes();
        }

        public Builder setDeviceName(String value) {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).setDeviceName(value);
            return this;
        }

        public Builder clearDeviceName() {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).clearDeviceName();
            return this;
        }

        public Builder setDeviceNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).setDeviceNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public List<Integer> getUidsList() {
            return Collections.unmodifiableList(((UsbSettingsDevicePermissionProto) this.instance).getUidsList());
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public int getUidsCount() {
            return ((UsbSettingsDevicePermissionProto) this.instance).getUidsCount();
        }

        @Override // android.service.usb.UsbSettingsDevicePermissionProtoOrBuilder
        public int getUids(int index) {
            return ((UsbSettingsDevicePermissionProto) this.instance).getUids(index);
        }

        public Builder setUids(int index, int value) {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).setUids(index, value);
            return this;
        }

        public Builder addUids(int value) {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).addUids(value);
            return this;
        }

        public Builder addAllUids(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).addAllUids(values);
            return this;
        }

        public Builder clearUids() {
            copyOnWrite();
            ((UsbSettingsDevicePermissionProto) this.instance).clearUids();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbSettingsDevicePermissionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.uids_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbSettingsDevicePermissionProto other = (UsbSettingsDevicePermissionProto) arg1;
                this.deviceName_ = visitor.visitString(hasDeviceName(), this.deviceName_, other.hasDeviceName(), other.deviceName_);
                this.uids_ = visitor.visitIntList(this.uids_, other.uids_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.deviceName_ = s;
                        } else if (tag == 16) {
                            if (!this.uids_.isModifiable()) {
                                this.uids_ = GeneratedMessageLite.mutableCopy(this.uids_);
                            }
                            this.uids_.addInt(input.readInt32());
                        } else if (tag == 18) {
                            int limit = input.pushLimit(input.readRawVarint32());
                            if (!this.uids_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.uids_ = GeneratedMessageLite.mutableCopy(this.uids_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.uids_.addInt(input.readInt32());
                            }
                            input.popLimit(limit);
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
                    synchronized (UsbSettingsDevicePermissionProto.class) {
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

    public static UsbSettingsDevicePermissionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbSettingsDevicePermissionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
