package android.service.usb;

import android.service.usb.UsbAlsaManagerProto;
import android.service.usb.UsbDeviceManagerProto;
import android.service.usb.UsbHostManagerProto;
import android.service.usb.UsbPortManagerProto;
import android.service.usb.UsbSettingsManagerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbServiceDumpProto extends GeneratedMessageLite<UsbServiceDumpProto, Builder> implements UsbServiceDumpProtoOrBuilder {
    public static final int ALSA_MANAGER_FIELD_NUMBER = 4;
    private static final UsbServiceDumpProto DEFAULT_INSTANCE = new UsbServiceDumpProto();
    public static final int DEVICE_MANAGER_FIELD_NUMBER = 1;
    public static final int HOST_MANAGER_FIELD_NUMBER = 2;
    private static volatile Parser<UsbServiceDumpProto> PARSER = null;
    public static final int PORT_MANAGER_FIELD_NUMBER = 3;
    public static final int SETTINGS_MANAGER_FIELD_NUMBER = 5;
    private UsbAlsaManagerProto alsaManager_;
    private int bitField0_;
    private UsbDeviceManagerProto deviceManager_;
    private UsbHostManagerProto hostManager_;
    private UsbPortManagerProto portManager_;
    private UsbSettingsManagerProto settingsManager_;

    private UsbServiceDumpProto() {
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public boolean hasDeviceManager() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public UsbDeviceManagerProto getDeviceManager() {
        UsbDeviceManagerProto usbDeviceManagerProto = this.deviceManager_;
        return usbDeviceManagerProto == null ? UsbDeviceManagerProto.getDefaultInstance() : usbDeviceManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceManager(UsbDeviceManagerProto value) {
        if (value != null) {
            this.deviceManager_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceManager(UsbDeviceManagerProto.Builder builderForValue) {
        this.deviceManager_ = (UsbDeviceManagerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDeviceManager(UsbDeviceManagerProto value) {
        UsbDeviceManagerProto usbDeviceManagerProto = this.deviceManager_;
        if (usbDeviceManagerProto == null || usbDeviceManagerProto == UsbDeviceManagerProto.getDefaultInstance()) {
            this.deviceManager_ = value;
        } else {
            this.deviceManager_ = (UsbDeviceManagerProto) ((UsbDeviceManagerProto.Builder) UsbDeviceManagerProto.newBuilder(this.deviceManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceManager() {
        this.deviceManager_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public boolean hasHostManager() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public UsbHostManagerProto getHostManager() {
        UsbHostManagerProto usbHostManagerProto = this.hostManager_;
        return usbHostManagerProto == null ? UsbHostManagerProto.getDefaultInstance() : usbHostManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostManager(UsbHostManagerProto value) {
        if (value != null) {
            this.hostManager_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHostManager(UsbHostManagerProto.Builder builderForValue) {
        this.hostManager_ = (UsbHostManagerProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHostManager(UsbHostManagerProto value) {
        UsbHostManagerProto usbHostManagerProto = this.hostManager_;
        if (usbHostManagerProto == null || usbHostManagerProto == UsbHostManagerProto.getDefaultInstance()) {
            this.hostManager_ = value;
        } else {
            this.hostManager_ = (UsbHostManagerProto) ((UsbHostManagerProto.Builder) UsbHostManagerProto.newBuilder(this.hostManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHostManager() {
        this.hostManager_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public boolean hasPortManager() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public UsbPortManagerProto getPortManager() {
        UsbPortManagerProto usbPortManagerProto = this.portManager_;
        return usbPortManagerProto == null ? UsbPortManagerProto.getDefaultInstance() : usbPortManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPortManager(UsbPortManagerProto value) {
        if (value != null) {
            this.portManager_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPortManager(UsbPortManagerProto.Builder builderForValue) {
        this.portManager_ = (UsbPortManagerProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePortManager(UsbPortManagerProto value) {
        UsbPortManagerProto usbPortManagerProto = this.portManager_;
        if (usbPortManagerProto == null || usbPortManagerProto == UsbPortManagerProto.getDefaultInstance()) {
            this.portManager_ = value;
        } else {
            this.portManager_ = (UsbPortManagerProto) ((UsbPortManagerProto.Builder) UsbPortManagerProto.newBuilder(this.portManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPortManager() {
        this.portManager_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public boolean hasAlsaManager() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public UsbAlsaManagerProto getAlsaManager() {
        UsbAlsaManagerProto usbAlsaManagerProto = this.alsaManager_;
        return usbAlsaManagerProto == null ? UsbAlsaManagerProto.getDefaultInstance() : usbAlsaManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlsaManager(UsbAlsaManagerProto value) {
        if (value != null) {
            this.alsaManager_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlsaManager(UsbAlsaManagerProto.Builder builderForValue) {
        this.alsaManager_ = (UsbAlsaManagerProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAlsaManager(UsbAlsaManagerProto value) {
        UsbAlsaManagerProto usbAlsaManagerProto = this.alsaManager_;
        if (usbAlsaManagerProto == null || usbAlsaManagerProto == UsbAlsaManagerProto.getDefaultInstance()) {
            this.alsaManager_ = value;
        } else {
            this.alsaManager_ = (UsbAlsaManagerProto) ((UsbAlsaManagerProto.Builder) UsbAlsaManagerProto.newBuilder(this.alsaManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlsaManager() {
        this.alsaManager_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public boolean hasSettingsManager() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
    public UsbSettingsManagerProto getSettingsManager() {
        UsbSettingsManagerProto usbSettingsManagerProto = this.settingsManager_;
        return usbSettingsManagerProto == null ? UsbSettingsManagerProto.getDefaultInstance() : usbSettingsManagerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsManager(UsbSettingsManagerProto value) {
        if (value != null) {
            this.settingsManager_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsManager(UsbSettingsManagerProto.Builder builderForValue) {
        this.settingsManager_ = (UsbSettingsManagerProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSettingsManager(UsbSettingsManagerProto value) {
        UsbSettingsManagerProto usbSettingsManagerProto = this.settingsManager_;
        if (usbSettingsManagerProto == null || usbSettingsManagerProto == UsbSettingsManagerProto.getDefaultInstance()) {
            this.settingsManager_ = value;
        } else {
            this.settingsManager_ = (UsbSettingsManagerProto) ((UsbSettingsManagerProto.Builder) UsbSettingsManagerProto.newBuilder(this.settingsManager_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingsManager() {
        this.settingsManager_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getDeviceManager());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getHostManager());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getPortManager());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getAlsaManager());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getSettingsManager());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getDeviceManager());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getHostManager());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getPortManager());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getAlsaManager());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getSettingsManager());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbServiceDumpProto, Builder> implements UsbServiceDumpProtoOrBuilder {
        private Builder() {
            super(UsbServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public boolean hasDeviceManager() {
            return ((UsbServiceDumpProto) this.instance).hasDeviceManager();
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public UsbDeviceManagerProto getDeviceManager() {
            return ((UsbServiceDumpProto) this.instance).getDeviceManager();
        }

        public Builder setDeviceManager(UsbDeviceManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setDeviceManager((UsbServiceDumpProto) value);
            return this;
        }

        public Builder setDeviceManager(UsbDeviceManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setDeviceManager((UsbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeDeviceManager(UsbDeviceManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).mergeDeviceManager(value);
            return this;
        }

        public Builder clearDeviceManager() {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).clearDeviceManager();
            return this;
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public boolean hasHostManager() {
            return ((UsbServiceDumpProto) this.instance).hasHostManager();
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public UsbHostManagerProto getHostManager() {
            return ((UsbServiceDumpProto) this.instance).getHostManager();
        }

        public Builder setHostManager(UsbHostManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setHostManager((UsbServiceDumpProto) value);
            return this;
        }

        public Builder setHostManager(UsbHostManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setHostManager((UsbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeHostManager(UsbHostManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).mergeHostManager(value);
            return this;
        }

        public Builder clearHostManager() {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).clearHostManager();
            return this;
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public boolean hasPortManager() {
            return ((UsbServiceDumpProto) this.instance).hasPortManager();
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public UsbPortManagerProto getPortManager() {
            return ((UsbServiceDumpProto) this.instance).getPortManager();
        }

        public Builder setPortManager(UsbPortManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setPortManager((UsbServiceDumpProto) value);
            return this;
        }

        public Builder setPortManager(UsbPortManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setPortManager((UsbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergePortManager(UsbPortManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).mergePortManager(value);
            return this;
        }

        public Builder clearPortManager() {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).clearPortManager();
            return this;
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public boolean hasAlsaManager() {
            return ((UsbServiceDumpProto) this.instance).hasAlsaManager();
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public UsbAlsaManagerProto getAlsaManager() {
            return ((UsbServiceDumpProto) this.instance).getAlsaManager();
        }

        public Builder setAlsaManager(UsbAlsaManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setAlsaManager((UsbServiceDumpProto) value);
            return this;
        }

        public Builder setAlsaManager(UsbAlsaManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setAlsaManager((UsbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeAlsaManager(UsbAlsaManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).mergeAlsaManager(value);
            return this;
        }

        public Builder clearAlsaManager() {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).clearAlsaManager();
            return this;
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public boolean hasSettingsManager() {
            return ((UsbServiceDumpProto) this.instance).hasSettingsManager();
        }

        @Override // android.service.usb.UsbServiceDumpProtoOrBuilder
        public UsbSettingsManagerProto getSettingsManager() {
            return ((UsbServiceDumpProto) this.instance).getSettingsManager();
        }

        public Builder setSettingsManager(UsbSettingsManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setSettingsManager((UsbServiceDumpProto) value);
            return this;
        }

        public Builder setSettingsManager(UsbSettingsManagerProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).setSettingsManager((UsbServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeSettingsManager(UsbSettingsManagerProto value) {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).mergeSettingsManager(value);
            return this;
        }

        public Builder clearSettingsManager() {
            copyOnWrite();
            ((UsbServiceDumpProto) this.instance).clearSettingsManager();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbServiceDumpProto other = (UsbServiceDumpProto) arg1;
                this.deviceManager_ = (UsbDeviceManagerProto) visitor.visitMessage(this.deviceManager_, other.deviceManager_);
                this.hostManager_ = (UsbHostManagerProto) visitor.visitMessage(this.hostManager_, other.hostManager_);
                this.portManager_ = (UsbPortManagerProto) visitor.visitMessage(this.portManager_, other.portManager_);
                this.alsaManager_ = (UsbAlsaManagerProto) visitor.visitMessage(this.alsaManager_, other.alsaManager_);
                this.settingsManager_ = (UsbSettingsManagerProto) visitor.visitMessage(this.settingsManager_, other.settingsManager_);
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
                            UsbDeviceManagerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (UsbDeviceManagerProto.Builder) this.deviceManager_.toBuilder();
                            }
                            this.deviceManager_ = (UsbDeviceManagerProto) input.readMessage(UsbDeviceManagerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.deviceManager_);
                                this.deviceManager_ = (UsbDeviceManagerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            UsbHostManagerProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (UsbHostManagerProto.Builder) this.hostManager_.toBuilder();
                            }
                            this.hostManager_ = (UsbHostManagerProto) input.readMessage(UsbHostManagerProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.hostManager_);
                                this.hostManager_ = (UsbHostManagerProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            UsbPortManagerProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (UsbPortManagerProto.Builder) this.portManager_.toBuilder();
                            }
                            this.portManager_ = (UsbPortManagerProto) input.readMessage(UsbPortManagerProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.portManager_);
                                this.portManager_ = (UsbPortManagerProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            UsbAlsaManagerProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (UsbAlsaManagerProto.Builder) this.alsaManager_.toBuilder();
                            }
                            this.alsaManager_ = (UsbAlsaManagerProto) input.readMessage(UsbAlsaManagerProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.alsaManager_);
                                this.alsaManager_ = (UsbAlsaManagerProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 42) {
                            UsbSettingsManagerProto.Builder subBuilder5 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder5 = (UsbSettingsManagerProto.Builder) this.settingsManager_.toBuilder();
                            }
                            this.settingsManager_ = (UsbSettingsManagerProto) input.readMessage(UsbSettingsManagerProto.parser(), extensionRegistry);
                            if (subBuilder5 != null) {
                                subBuilder5.mergeFrom((GeneratedMessageLite) this.settingsManager_);
                                this.settingsManager_ = (UsbSettingsManagerProto) subBuilder5.buildPartial();
                            }
                            this.bitField0_ |= 16;
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
                    synchronized (UsbServiceDumpProto.class) {
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

    public static UsbServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
