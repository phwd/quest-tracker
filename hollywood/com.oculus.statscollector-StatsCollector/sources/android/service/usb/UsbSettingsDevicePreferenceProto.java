package android.service.usb;

import android.service.usb.UsbDeviceFilterProto;
import android.service.usb.UserPackageProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbSettingsDevicePreferenceProto extends GeneratedMessageLite<UsbSettingsDevicePreferenceProto, Builder> implements UsbSettingsDevicePreferenceProtoOrBuilder {
    private static final UsbSettingsDevicePreferenceProto DEFAULT_INSTANCE = new UsbSettingsDevicePreferenceProto();
    public static final int FILTER_FIELD_NUMBER = 1;
    private static volatile Parser<UsbSettingsDevicePreferenceProto> PARSER = null;
    public static final int USER_PACKAGE_FIELD_NUMBER = 2;
    private int bitField0_;
    private UsbDeviceFilterProto filter_;
    private UserPackageProto userPackage_;

    private UsbSettingsDevicePreferenceProto() {
    }

    @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
    public boolean hasFilter() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
    public UsbDeviceFilterProto getFilter() {
        UsbDeviceFilterProto usbDeviceFilterProto = this.filter_;
        return usbDeviceFilterProto == null ? UsbDeviceFilterProto.getDefaultInstance() : usbDeviceFilterProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilter(UsbDeviceFilterProto value) {
        if (value != null) {
            this.filter_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilter(UsbDeviceFilterProto.Builder builderForValue) {
        this.filter_ = (UsbDeviceFilterProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFilter(UsbDeviceFilterProto value) {
        UsbDeviceFilterProto usbDeviceFilterProto = this.filter_;
        if (usbDeviceFilterProto == null || usbDeviceFilterProto == UsbDeviceFilterProto.getDefaultInstance()) {
            this.filter_ = value;
        } else {
            this.filter_ = (UsbDeviceFilterProto) ((UsbDeviceFilterProto.Builder) UsbDeviceFilterProto.newBuilder(this.filter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilter() {
        this.filter_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
    public boolean hasUserPackage() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
    public UserPackageProto getUserPackage() {
        UserPackageProto userPackageProto = this.userPackage_;
        return userPackageProto == null ? UserPackageProto.getDefaultInstance() : userPackageProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserPackage(UserPackageProto value) {
        if (value != null) {
            this.userPackage_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserPackage(UserPackageProto.Builder builderForValue) {
        this.userPackage_ = (UserPackageProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUserPackage(UserPackageProto value) {
        UserPackageProto userPackageProto = this.userPackage_;
        if (userPackageProto == null || userPackageProto == UserPackageProto.getDefaultInstance()) {
            this.userPackage_ = value;
        } else {
            this.userPackage_ = (UserPackageProto) ((UserPackageProto.Builder) UserPackageProto.newBuilder(this.userPackage_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserPackage() {
        this.userPackage_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getFilter());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getUserPackage());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getFilter());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getUserPackage());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(InputStream input) throws IOException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsDevicePreferenceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbSettingsDevicePreferenceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePreferenceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePreferenceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsDevicePreferenceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsDevicePreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbSettingsDevicePreferenceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbSettingsDevicePreferenceProto, Builder> implements UsbSettingsDevicePreferenceProtoOrBuilder {
        private Builder() {
            super(UsbSettingsDevicePreferenceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
        public boolean hasFilter() {
            return ((UsbSettingsDevicePreferenceProto) this.instance).hasFilter();
        }

        @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
        public UsbDeviceFilterProto getFilter() {
            return ((UsbSettingsDevicePreferenceProto) this.instance).getFilter();
        }

        public Builder setFilter(UsbDeviceFilterProto value) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).setFilter((UsbSettingsDevicePreferenceProto) value);
            return this;
        }

        public Builder setFilter(UsbDeviceFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).setFilter((UsbSettingsDevicePreferenceProto) builderForValue);
            return this;
        }

        public Builder mergeFilter(UsbDeviceFilterProto value) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).mergeFilter(value);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).clearFilter();
            return this;
        }

        @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
        public boolean hasUserPackage() {
            return ((UsbSettingsDevicePreferenceProto) this.instance).hasUserPackage();
        }

        @Override // android.service.usb.UsbSettingsDevicePreferenceProtoOrBuilder
        public UserPackageProto getUserPackage() {
            return ((UsbSettingsDevicePreferenceProto) this.instance).getUserPackage();
        }

        public Builder setUserPackage(UserPackageProto value) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).setUserPackage((UsbSettingsDevicePreferenceProto) value);
            return this;
        }

        public Builder setUserPackage(UserPackageProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).setUserPackage((UsbSettingsDevicePreferenceProto) builderForValue);
            return this;
        }

        public Builder mergeUserPackage(UserPackageProto value) {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).mergeUserPackage(value);
            return this;
        }

        public Builder clearUserPackage() {
            copyOnWrite();
            ((UsbSettingsDevicePreferenceProto) this.instance).clearUserPackage();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbSettingsDevicePreferenceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbSettingsDevicePreferenceProto other = (UsbSettingsDevicePreferenceProto) arg1;
                this.filter_ = (UsbDeviceFilterProto) visitor.visitMessage(this.filter_, other.filter_);
                this.userPackage_ = (UserPackageProto) visitor.visitMessage(this.userPackage_, other.userPackage_);
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
                            UsbDeviceFilterProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (UsbDeviceFilterProto.Builder) this.filter_.toBuilder();
                            }
                            this.filter_ = (UsbDeviceFilterProto) input.readMessage(UsbDeviceFilterProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.filter_);
                                this.filter_ = (UsbDeviceFilterProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            UserPackageProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (UserPackageProto.Builder) this.userPackage_.toBuilder();
                            }
                            this.userPackage_ = (UserPackageProto) input.readMessage(UserPackageProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.userPackage_);
                                this.userPackage_ = (UserPackageProto) subBuilder2.buildPartial();
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
                    synchronized (UsbSettingsDevicePreferenceProto.class) {
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

    public static UsbSettingsDevicePreferenceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbSettingsDevicePreferenceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
