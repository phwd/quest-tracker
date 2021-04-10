package android.service.usb;

import android.service.usb.UsbPortInfoProto;
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

public final class UsbPortManagerProto extends GeneratedMessageLite<UsbPortManagerProto, Builder> implements UsbPortManagerProtoOrBuilder {
    private static final UsbPortManagerProto DEFAULT_INSTANCE = new UsbPortManagerProto();
    public static final int IS_SIMULATION_ACTIVE_FIELD_NUMBER = 1;
    private static volatile Parser<UsbPortManagerProto> PARSER = null;
    public static final int USB_PORTS_FIELD_NUMBER = 2;
    private int bitField0_;
    private boolean isSimulationActive_ = false;
    private Internal.ProtobufList<UsbPortInfoProto> usbPorts_ = emptyProtobufList();

    private UsbPortManagerProto() {
    }

    @Override // android.service.usb.UsbPortManagerProtoOrBuilder
    public boolean hasIsSimulationActive() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbPortManagerProtoOrBuilder
    public boolean getIsSimulationActive() {
        return this.isSimulationActive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSimulationActive(boolean value) {
        this.bitField0_ |= 1;
        this.isSimulationActive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSimulationActive() {
        this.bitField0_ &= -2;
        this.isSimulationActive_ = false;
    }

    @Override // android.service.usb.UsbPortManagerProtoOrBuilder
    public List<UsbPortInfoProto> getUsbPortsList() {
        return this.usbPorts_;
    }

    public List<? extends UsbPortInfoProtoOrBuilder> getUsbPortsOrBuilderList() {
        return this.usbPorts_;
    }

    @Override // android.service.usb.UsbPortManagerProtoOrBuilder
    public int getUsbPortsCount() {
        return this.usbPorts_.size();
    }

    @Override // android.service.usb.UsbPortManagerProtoOrBuilder
    public UsbPortInfoProto getUsbPorts(int index) {
        return this.usbPorts_.get(index);
    }

    public UsbPortInfoProtoOrBuilder getUsbPortsOrBuilder(int index) {
        return this.usbPorts_.get(index);
    }

    private void ensureUsbPortsIsMutable() {
        if (!this.usbPorts_.isModifiable()) {
            this.usbPorts_ = GeneratedMessageLite.mutableCopy(this.usbPorts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsbPorts(int index, UsbPortInfoProto value) {
        if (value != null) {
            ensureUsbPortsIsMutable();
            this.usbPorts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsbPorts(int index, UsbPortInfoProto.Builder builderForValue) {
        ensureUsbPortsIsMutable();
        this.usbPorts_.set(index, (UsbPortInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsbPorts(UsbPortInfoProto value) {
        if (value != null) {
            ensureUsbPortsIsMutable();
            this.usbPorts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsbPorts(int index, UsbPortInfoProto value) {
        if (value != null) {
            ensureUsbPortsIsMutable();
            this.usbPorts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsbPorts(UsbPortInfoProto.Builder builderForValue) {
        ensureUsbPortsIsMutable();
        this.usbPorts_.add((UsbPortInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsbPorts(int index, UsbPortInfoProto.Builder builderForValue) {
        ensureUsbPortsIsMutable();
        this.usbPorts_.add(index, (UsbPortInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUsbPorts(Iterable<? extends UsbPortInfoProto> values) {
        ensureUsbPortsIsMutable();
        AbstractMessageLite.addAll(values, this.usbPorts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsbPorts() {
        this.usbPorts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUsbPorts(int index) {
        ensureUsbPortsIsMutable();
        this.usbPorts_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isSimulationActive_);
        }
        for (int i = 0; i < this.usbPorts_.size(); i++) {
            output.writeMessage(2, this.usbPorts_.get(i));
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isSimulationActive_);
        }
        for (int i = 0; i < this.usbPorts_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.usbPorts_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbPortManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbPortManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbPortManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbPortManagerProto, Builder> implements UsbPortManagerProtoOrBuilder {
        private Builder() {
            super(UsbPortManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbPortManagerProtoOrBuilder
        public boolean hasIsSimulationActive() {
            return ((UsbPortManagerProto) this.instance).hasIsSimulationActive();
        }

        @Override // android.service.usb.UsbPortManagerProtoOrBuilder
        public boolean getIsSimulationActive() {
            return ((UsbPortManagerProto) this.instance).getIsSimulationActive();
        }

        public Builder setIsSimulationActive(boolean value) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).setIsSimulationActive(value);
            return this;
        }

        public Builder clearIsSimulationActive() {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).clearIsSimulationActive();
            return this;
        }

        @Override // android.service.usb.UsbPortManagerProtoOrBuilder
        public List<UsbPortInfoProto> getUsbPortsList() {
            return Collections.unmodifiableList(((UsbPortManagerProto) this.instance).getUsbPortsList());
        }

        @Override // android.service.usb.UsbPortManagerProtoOrBuilder
        public int getUsbPortsCount() {
            return ((UsbPortManagerProto) this.instance).getUsbPortsCount();
        }

        @Override // android.service.usb.UsbPortManagerProtoOrBuilder
        public UsbPortInfoProto getUsbPorts(int index) {
            return ((UsbPortManagerProto) this.instance).getUsbPorts(index);
        }

        public Builder setUsbPorts(int index, UsbPortInfoProto value) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).setUsbPorts((UsbPortManagerProto) index, (int) value);
            return this;
        }

        public Builder setUsbPorts(int index, UsbPortInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).setUsbPorts((UsbPortManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUsbPorts(UsbPortInfoProto value) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).addUsbPorts((UsbPortManagerProto) value);
            return this;
        }

        public Builder addUsbPorts(int index, UsbPortInfoProto value) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).addUsbPorts((UsbPortManagerProto) index, (int) value);
            return this;
        }

        public Builder addUsbPorts(UsbPortInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).addUsbPorts((UsbPortManagerProto) builderForValue);
            return this;
        }

        public Builder addUsbPorts(int index, UsbPortInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).addUsbPorts((UsbPortManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUsbPorts(Iterable<? extends UsbPortInfoProto> values) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).addAllUsbPorts(values);
            return this;
        }

        public Builder clearUsbPorts() {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).clearUsbPorts();
            return this;
        }

        public Builder removeUsbPorts(int index) {
            copyOnWrite();
            ((UsbPortManagerProto) this.instance).removeUsbPorts(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbPortManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.usbPorts_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbPortManagerProto other = (UsbPortManagerProto) arg1;
                this.isSimulationActive_ = visitor.visitBoolean(hasIsSimulationActive(), this.isSimulationActive_, other.hasIsSimulationActive(), other.isSimulationActive_);
                this.usbPorts_ = visitor.visitList(this.usbPorts_, other.usbPorts_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.isSimulationActive_ = input.readBool();
                        } else if (tag == 18) {
                            if (!this.usbPorts_.isModifiable()) {
                                this.usbPorts_ = GeneratedMessageLite.mutableCopy(this.usbPorts_);
                            }
                            this.usbPorts_.add((UsbPortInfoProto) input.readMessage(UsbPortInfoProto.parser(), extensionRegistry));
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
                    synchronized (UsbPortManagerProto.class) {
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

    public static UsbPortManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbPortManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
