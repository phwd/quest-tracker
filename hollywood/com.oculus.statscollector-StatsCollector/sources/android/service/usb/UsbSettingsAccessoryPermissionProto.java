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

public final class UsbSettingsAccessoryPermissionProto extends GeneratedMessageLite<UsbSettingsAccessoryPermissionProto, Builder> implements UsbSettingsAccessoryPermissionProtoOrBuilder {
    public static final int ACCESSORY_DESCRIPTION_FIELD_NUMBER = 1;
    private static final UsbSettingsAccessoryPermissionProto DEFAULT_INSTANCE = new UsbSettingsAccessoryPermissionProto();
    private static volatile Parser<UsbSettingsAccessoryPermissionProto> PARSER = null;
    public static final int UIDS_FIELD_NUMBER = 2;
    private String accessoryDescription_ = "";
    private int bitField0_;
    private Internal.IntList uids_ = emptyIntList();

    private UsbSettingsAccessoryPermissionProto() {
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
    public boolean hasAccessoryDescription() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
    public String getAccessoryDescription() {
        return this.accessoryDescription_;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
    public ByteString getAccessoryDescriptionBytes() {
        return ByteString.copyFromUtf8(this.accessoryDescription_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryDescription(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.accessoryDescription_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAccessoryDescription() {
        this.bitField0_ &= -2;
        this.accessoryDescription_ = getDefaultInstance().getAccessoryDescription();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAccessoryDescriptionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.accessoryDescription_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
    public List<Integer> getUidsList() {
        return this.uids_;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
    public int getUidsCount() {
        return this.uids_.size();
    }

    @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
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
            output.writeString(1, getAccessoryDescription());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getAccessoryDescription());
        }
        int dataSize = 0;
        for (int i = 0; i < this.uids_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.uids_.getInt(i));
        }
        int size3 = size2 + dataSize + (getUidsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(InputStream input) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsAccessoryPermissionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPermissionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPermissionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPermissionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbSettingsAccessoryPermissionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbSettingsAccessoryPermissionProto, Builder> implements UsbSettingsAccessoryPermissionProtoOrBuilder {
        private Builder() {
            super(UsbSettingsAccessoryPermissionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public boolean hasAccessoryDescription() {
            return ((UsbSettingsAccessoryPermissionProto) this.instance).hasAccessoryDescription();
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public String getAccessoryDescription() {
            return ((UsbSettingsAccessoryPermissionProto) this.instance).getAccessoryDescription();
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public ByteString getAccessoryDescriptionBytes() {
            return ((UsbSettingsAccessoryPermissionProto) this.instance).getAccessoryDescriptionBytes();
        }

        public Builder setAccessoryDescription(String value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).setAccessoryDescription(value);
            return this;
        }

        public Builder clearAccessoryDescription() {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).clearAccessoryDescription();
            return this;
        }

        public Builder setAccessoryDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).setAccessoryDescriptionBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public List<Integer> getUidsList() {
            return Collections.unmodifiableList(((UsbSettingsAccessoryPermissionProto) this.instance).getUidsList());
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public int getUidsCount() {
            return ((UsbSettingsAccessoryPermissionProto) this.instance).getUidsCount();
        }

        @Override // android.service.usb.UsbSettingsAccessoryPermissionProtoOrBuilder
        public int getUids(int index) {
            return ((UsbSettingsAccessoryPermissionProto) this.instance).getUids(index);
        }

        public Builder setUids(int index, int value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).setUids(index, value);
            return this;
        }

        public Builder addUids(int value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).addUids(value);
            return this;
        }

        public Builder addAllUids(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).addAllUids(values);
            return this;
        }

        public Builder clearUids() {
            copyOnWrite();
            ((UsbSettingsAccessoryPermissionProto) this.instance).clearUids();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbSettingsAccessoryPermissionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.uids_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbSettingsAccessoryPermissionProto other = (UsbSettingsAccessoryPermissionProto) arg1;
                this.accessoryDescription_ = visitor.visitString(hasAccessoryDescription(), this.accessoryDescription_, other.hasAccessoryDescription(), other.accessoryDescription_);
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
                            this.accessoryDescription_ = s;
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
                    synchronized (UsbSettingsAccessoryPermissionProto.class) {
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

    public static UsbSettingsAccessoryPermissionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbSettingsAccessoryPermissionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
