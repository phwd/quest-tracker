package android.service.usb;

import android.service.usb.UsbAccessoryFilterProto;
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

public final class UsbSettingsAccessoryPreferenceProto extends GeneratedMessageLite<UsbSettingsAccessoryPreferenceProto, Builder> implements UsbSettingsAccessoryPreferenceProtoOrBuilder {
    private static final UsbSettingsAccessoryPreferenceProto DEFAULT_INSTANCE = new UsbSettingsAccessoryPreferenceProto();
    public static final int FILTER_FIELD_NUMBER = 1;
    private static volatile Parser<UsbSettingsAccessoryPreferenceProto> PARSER = null;
    public static final int USER_PACKAGE_FIELD_NUMBER = 2;
    private int bitField0_;
    private UsbAccessoryFilterProto filter_;
    private UserPackageProto userPackage_;

    private UsbSettingsAccessoryPreferenceProto() {
    }

    @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
    public boolean hasFilter() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
    public UsbAccessoryFilterProto getFilter() {
        UsbAccessoryFilterProto usbAccessoryFilterProto = this.filter_;
        return usbAccessoryFilterProto == null ? UsbAccessoryFilterProto.getDefaultInstance() : usbAccessoryFilterProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilter(UsbAccessoryFilterProto value) {
        if (value != null) {
            this.filter_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilter(UsbAccessoryFilterProto.Builder builderForValue) {
        this.filter_ = (UsbAccessoryFilterProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFilter(UsbAccessoryFilterProto value) {
        UsbAccessoryFilterProto usbAccessoryFilterProto = this.filter_;
        if (usbAccessoryFilterProto == null || usbAccessoryFilterProto == UsbAccessoryFilterProto.getDefaultInstance()) {
            this.filter_ = value;
        } else {
            this.filter_ = (UsbAccessoryFilterProto) ((UsbAccessoryFilterProto.Builder) UsbAccessoryFilterProto.newBuilder(this.filter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilter() {
        this.filter_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
    public boolean hasUserPackage() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
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

    public static UsbSettingsAccessoryPreferenceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(InputStream input) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsAccessoryPreferenceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPreferenceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbSettingsAccessoryPreferenceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbSettingsAccessoryPreferenceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbSettingsAccessoryPreferenceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbSettingsAccessoryPreferenceProto, Builder> implements UsbSettingsAccessoryPreferenceProtoOrBuilder {
        private Builder() {
            super(UsbSettingsAccessoryPreferenceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
        public boolean hasFilter() {
            return ((UsbSettingsAccessoryPreferenceProto) this.instance).hasFilter();
        }

        @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
        public UsbAccessoryFilterProto getFilter() {
            return ((UsbSettingsAccessoryPreferenceProto) this.instance).getFilter();
        }

        public Builder setFilter(UsbAccessoryFilterProto value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).setFilter((UsbSettingsAccessoryPreferenceProto) value);
            return this;
        }

        public Builder setFilter(UsbAccessoryFilterProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).setFilter((UsbSettingsAccessoryPreferenceProto) builderForValue);
            return this;
        }

        public Builder mergeFilter(UsbAccessoryFilterProto value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).mergeFilter(value);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).clearFilter();
            return this;
        }

        @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
        public boolean hasUserPackage() {
            return ((UsbSettingsAccessoryPreferenceProto) this.instance).hasUserPackage();
        }

        @Override // android.service.usb.UsbSettingsAccessoryPreferenceProtoOrBuilder
        public UserPackageProto getUserPackage() {
            return ((UsbSettingsAccessoryPreferenceProto) this.instance).getUserPackage();
        }

        public Builder setUserPackage(UserPackageProto value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).setUserPackage((UsbSettingsAccessoryPreferenceProto) value);
            return this;
        }

        public Builder setUserPackage(UserPackageProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).setUserPackage((UsbSettingsAccessoryPreferenceProto) builderForValue);
            return this;
        }

        public Builder mergeUserPackage(UserPackageProto value) {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).mergeUserPackage(value);
            return this;
        }

        public Builder clearUserPackage() {
            copyOnWrite();
            ((UsbSettingsAccessoryPreferenceProto) this.instance).clearUserPackage();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbSettingsAccessoryPreferenceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbSettingsAccessoryPreferenceProto other = (UsbSettingsAccessoryPreferenceProto) arg1;
                this.filter_ = (UsbAccessoryFilterProto) visitor.visitMessage(this.filter_, other.filter_);
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
                            UsbAccessoryFilterProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (UsbAccessoryFilterProto.Builder) this.filter_.toBuilder();
                            }
                            this.filter_ = (UsbAccessoryFilterProto) input.readMessage(UsbAccessoryFilterProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.filter_);
                                this.filter_ = (UsbAccessoryFilterProto) subBuilder.buildPartial();
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
                    synchronized (UsbSettingsAccessoryPreferenceProto.class) {
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

    public static UsbSettingsAccessoryPreferenceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbSettingsAccessoryPreferenceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
