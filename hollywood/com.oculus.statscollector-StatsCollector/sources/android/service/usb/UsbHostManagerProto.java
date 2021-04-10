package android.service.usb;

import android.content.ComponentNameProto;
import android.service.usb.UsbConnectionRecordProto;
import android.service.usb.UsbDeviceProto;
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

public final class UsbHostManagerProto extends GeneratedMessageLite<UsbHostManagerProto, Builder> implements UsbHostManagerProtoOrBuilder {
    public static final int CONNECTIONS_FIELD_NUMBER = 4;
    private static final UsbHostManagerProto DEFAULT_INSTANCE = new UsbHostManagerProto();
    public static final int DEFAULT_USB_HOST_CONNECTION_HANDLER_FIELD_NUMBER = 1;
    public static final int DEVICES_FIELD_NUMBER = 2;
    public static final int NUM_CONNECTS_FIELD_NUMBER = 3;
    private static volatile Parser<UsbHostManagerProto> PARSER;
    private int bitField0_;
    private Internal.ProtobufList<UsbConnectionRecordProto> connections_ = emptyProtobufList();
    private ComponentNameProto defaultUsbHostConnectionHandler_;
    private Internal.ProtobufList<UsbDeviceProto> devices_ = emptyProtobufList();
    private int numConnects_ = 0;

    private UsbHostManagerProto() {
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public boolean hasDefaultUsbHostConnectionHandler() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public ComponentNameProto getDefaultUsbHostConnectionHandler() {
        ComponentNameProto componentNameProto = this.defaultUsbHostConnectionHandler_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultUsbHostConnectionHandler(ComponentNameProto value) {
        if (value != null) {
            this.defaultUsbHostConnectionHandler_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultUsbHostConnectionHandler(ComponentNameProto.Builder builderForValue) {
        this.defaultUsbHostConnectionHandler_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDefaultUsbHostConnectionHandler(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.defaultUsbHostConnectionHandler_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.defaultUsbHostConnectionHandler_ = value;
        } else {
            this.defaultUsbHostConnectionHandler_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.defaultUsbHostConnectionHandler_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDefaultUsbHostConnectionHandler() {
        this.defaultUsbHostConnectionHandler_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public List<UsbDeviceProto> getDevicesList() {
        return this.devices_;
    }

    public List<? extends UsbDeviceProtoOrBuilder> getDevicesOrBuilderList() {
        return this.devices_;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public int getDevicesCount() {
        return this.devices_.size();
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public UsbDeviceProto getDevices(int index) {
        return this.devices_.get(index);
    }

    public UsbDeviceProtoOrBuilder getDevicesOrBuilder(int index) {
        return this.devices_.get(index);
    }

    private void ensureDevicesIsMutable() {
        if (!this.devices_.isModifiable()) {
            this.devices_ = GeneratedMessageLite.mutableCopy(this.devices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevices(int index, UsbDeviceProto value) {
        if (value != null) {
            ensureDevicesIsMutable();
            this.devices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevices(int index, UsbDeviceProto.Builder builderForValue) {
        ensureDevicesIsMutable();
        this.devices_.set(index, (UsbDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevices(UsbDeviceProto value) {
        if (value != null) {
            ensureDevicesIsMutable();
            this.devices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevices(int index, UsbDeviceProto value) {
        if (value != null) {
            ensureDevicesIsMutable();
            this.devices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevices(UsbDeviceProto.Builder builderForValue) {
        ensureDevicesIsMutable();
        this.devices_.add((UsbDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDevices(int index, UsbDeviceProto.Builder builderForValue) {
        ensureDevicesIsMutable();
        this.devices_.add(index, (UsbDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDevices(Iterable<? extends UsbDeviceProto> values) {
        ensureDevicesIsMutable();
        AbstractMessageLite.addAll(values, this.devices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDevices() {
        this.devices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDevices(int index) {
        ensureDevicesIsMutable();
        this.devices_.remove(index);
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public boolean hasNumConnects() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public int getNumConnects() {
        return this.numConnects_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumConnects(int value) {
        this.bitField0_ |= 2;
        this.numConnects_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumConnects() {
        this.bitField0_ &= -3;
        this.numConnects_ = 0;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public List<UsbConnectionRecordProto> getConnectionsList() {
        return this.connections_;
    }

    public List<? extends UsbConnectionRecordProtoOrBuilder> getConnectionsOrBuilderList() {
        return this.connections_;
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public int getConnectionsCount() {
        return this.connections_.size();
    }

    @Override // android.service.usb.UsbHostManagerProtoOrBuilder
    public UsbConnectionRecordProto getConnections(int index) {
        return this.connections_.get(index);
    }

    public UsbConnectionRecordProtoOrBuilder getConnectionsOrBuilder(int index) {
        return this.connections_.get(index);
    }

    private void ensureConnectionsIsMutable() {
        if (!this.connections_.isModifiable()) {
            this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnections(int index, UsbConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnections(int index, UsbConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.set(index, (UsbConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(UsbConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(int index, UsbConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(UsbConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.add((UsbConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(int index, UsbConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.add(index, (UsbConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConnections(Iterable<? extends UsbConnectionRecordProto> values) {
        ensureConnectionsIsMutable();
        AbstractMessageLite.addAll(values, this.connections_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnections() {
        this.connections_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeConnections(int index) {
        ensureConnectionsIsMutable();
        this.connections_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getDefaultUsbHostConnectionHandler());
        }
        for (int i = 0; i < this.devices_.size(); i++) {
            output.writeMessage(2, this.devices_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(3, this.numConnects_);
        }
        for (int i2 = 0; i2 < this.connections_.size(); i2++) {
            output.writeMessage(4, this.connections_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getDefaultUsbHostConnectionHandler());
        }
        for (int i = 0; i < this.devices_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.devices_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(3, this.numConnects_);
        }
        for (int i2 = 0; i2 < this.connections_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.connections_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbHostManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbHostManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbHostManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbHostManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbHostManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHostManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbHostManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbHostManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHostManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHostManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbHostManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbHostManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbHostManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbHostManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbHostManagerProto, Builder> implements UsbHostManagerProtoOrBuilder {
        private Builder() {
            super(UsbHostManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public boolean hasDefaultUsbHostConnectionHandler() {
            return ((UsbHostManagerProto) this.instance).hasDefaultUsbHostConnectionHandler();
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public ComponentNameProto getDefaultUsbHostConnectionHandler() {
            return ((UsbHostManagerProto) this.instance).getDefaultUsbHostConnectionHandler();
        }

        public Builder setDefaultUsbHostConnectionHandler(ComponentNameProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setDefaultUsbHostConnectionHandler((UsbHostManagerProto) value);
            return this;
        }

        public Builder setDefaultUsbHostConnectionHandler(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setDefaultUsbHostConnectionHandler((UsbHostManagerProto) builderForValue);
            return this;
        }

        public Builder mergeDefaultUsbHostConnectionHandler(ComponentNameProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).mergeDefaultUsbHostConnectionHandler(value);
            return this;
        }

        public Builder clearDefaultUsbHostConnectionHandler() {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).clearDefaultUsbHostConnectionHandler();
            return this;
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public List<UsbDeviceProto> getDevicesList() {
            return Collections.unmodifiableList(((UsbHostManagerProto) this.instance).getDevicesList());
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public int getDevicesCount() {
            return ((UsbHostManagerProto) this.instance).getDevicesCount();
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public UsbDeviceProto getDevices(int index) {
            return ((UsbHostManagerProto) this.instance).getDevices(index);
        }

        public Builder setDevices(int index, UsbDeviceProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setDevices((UsbHostManagerProto) index, (int) value);
            return this;
        }

        public Builder setDevices(int index, UsbDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setDevices((UsbHostManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDevices(UsbDeviceProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addDevices((UsbHostManagerProto) value);
            return this;
        }

        public Builder addDevices(int index, UsbDeviceProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addDevices((UsbHostManagerProto) index, (int) value);
            return this;
        }

        public Builder addDevices(UsbDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addDevices((UsbHostManagerProto) builderForValue);
            return this;
        }

        public Builder addDevices(int index, UsbDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addDevices((UsbHostManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDevices(Iterable<? extends UsbDeviceProto> values) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addAllDevices(values);
            return this;
        }

        public Builder clearDevices() {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).clearDevices();
            return this;
        }

        public Builder removeDevices(int index) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).removeDevices(index);
            return this;
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public boolean hasNumConnects() {
            return ((UsbHostManagerProto) this.instance).hasNumConnects();
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public int getNumConnects() {
            return ((UsbHostManagerProto) this.instance).getNumConnects();
        }

        public Builder setNumConnects(int value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setNumConnects(value);
            return this;
        }

        public Builder clearNumConnects() {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).clearNumConnects();
            return this;
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public List<UsbConnectionRecordProto> getConnectionsList() {
            return Collections.unmodifiableList(((UsbHostManagerProto) this.instance).getConnectionsList());
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public int getConnectionsCount() {
            return ((UsbHostManagerProto) this.instance).getConnectionsCount();
        }

        @Override // android.service.usb.UsbHostManagerProtoOrBuilder
        public UsbConnectionRecordProto getConnections(int index) {
            return ((UsbHostManagerProto) this.instance).getConnections(index);
        }

        public Builder setConnections(int index, UsbConnectionRecordProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setConnections((UsbHostManagerProto) index, (int) value);
            return this;
        }

        public Builder setConnections(int index, UsbConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).setConnections((UsbHostManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addConnections(UsbConnectionRecordProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addConnections((UsbHostManagerProto) value);
            return this;
        }

        public Builder addConnections(int index, UsbConnectionRecordProto value) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addConnections((UsbHostManagerProto) index, (int) value);
            return this;
        }

        public Builder addConnections(UsbConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addConnections((UsbHostManagerProto) builderForValue);
            return this;
        }

        public Builder addConnections(int index, UsbConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addConnections((UsbHostManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllConnections(Iterable<? extends UsbConnectionRecordProto> values) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).addAllConnections(values);
            return this;
        }

        public Builder clearConnections() {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).clearConnections();
            return this;
        }

        public Builder removeConnections(int index) {
            copyOnWrite();
            ((UsbHostManagerProto) this.instance).removeConnections(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbHostManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.devices_.makeImmutable();
                this.connections_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbHostManagerProto other = (UsbHostManagerProto) arg1;
                this.defaultUsbHostConnectionHandler_ = (ComponentNameProto) visitor.visitMessage(this.defaultUsbHostConnectionHandler_, other.defaultUsbHostConnectionHandler_);
                this.devices_ = visitor.visitList(this.devices_, other.devices_);
                this.numConnects_ = visitor.visitInt(hasNumConnects(), this.numConnects_, other.hasNumConnects(), other.numConnects_);
                this.connections_ = visitor.visitList(this.connections_, other.connections_);
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
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.defaultUsbHostConnectionHandler_.toBuilder();
                            }
                            this.defaultUsbHostConnectionHandler_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.defaultUsbHostConnectionHandler_);
                                this.defaultUsbHostConnectionHandler_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            if (!this.devices_.isModifiable()) {
                                this.devices_ = GeneratedMessageLite.mutableCopy(this.devices_);
                            }
                            this.devices_.add((UsbDeviceProto) input.readMessage(UsbDeviceProto.parser(), extensionRegistry));
                        } else if (tag == 24) {
                            this.bitField0_ |= 2;
                            this.numConnects_ = input.readInt32();
                        } else if (tag == 34) {
                            if (!this.connections_.isModifiable()) {
                                this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
                            }
                            this.connections_.add((UsbConnectionRecordProto) input.readMessage(UsbConnectionRecordProto.parser(), extensionRegistry));
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
                    synchronized (UsbHostManagerProto.class) {
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

    public static UsbHostManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbHostManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
