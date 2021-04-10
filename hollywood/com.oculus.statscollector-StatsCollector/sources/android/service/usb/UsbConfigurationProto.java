package android.service.usb;

import android.service.usb.UsbInterfaceProto;
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

public final class UsbConfigurationProto extends GeneratedMessageLite<UsbConfigurationProto, Builder> implements UsbConfigurationProtoOrBuilder {
    public static final int ATTRIBUTES_FIELD_NUMBER = 3;
    private static final UsbConfigurationProto DEFAULT_INSTANCE = new UsbConfigurationProto();
    public static final int ID_FIELD_NUMBER = 1;
    public static final int INTERFACES_FIELD_NUMBER = 5;
    public static final int MAX_POWER_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<UsbConfigurationProto> PARSER;
    private int attributes_ = 0;
    private int bitField0_;
    private int id_ = 0;
    private Internal.ProtobufList<UsbInterfaceProto> interfaces_ = emptyProtobufList();
    private int maxPower_ = 0;
    private String name_ = "";

    private UsbConfigurationProto() {
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public int getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(int value) {
        this.bitField0_ |= 1;
        this.id_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -2;
        this.id_ = 0;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public boolean hasAttributes() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public int getAttributes() {
        return this.attributes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(int value) {
        this.bitField0_ |= 4;
        this.attributes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAttributes() {
        this.bitField0_ &= -5;
        this.attributes_ = 0;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public boolean hasMaxPower() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public int getMaxPower() {
        return this.maxPower_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxPower(int value) {
        this.bitField0_ |= 8;
        this.maxPower_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxPower() {
        this.bitField0_ &= -9;
        this.maxPower_ = 0;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public List<UsbInterfaceProto> getInterfacesList() {
        return this.interfaces_;
    }

    public List<? extends UsbInterfaceProtoOrBuilder> getInterfacesOrBuilderList() {
        return this.interfaces_;
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public int getInterfacesCount() {
        return this.interfaces_.size();
    }

    @Override // android.service.usb.UsbConfigurationProtoOrBuilder
    public UsbInterfaceProto getInterfaces(int index) {
        return this.interfaces_.get(index);
    }

    public UsbInterfaceProtoOrBuilder getInterfacesOrBuilder(int index) {
        return this.interfaces_.get(index);
    }

    private void ensureInterfacesIsMutable() {
        if (!this.interfaces_.isModifiable()) {
            this.interfaces_ = GeneratedMessageLite.mutableCopy(this.interfaces_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInterfaces(int index, UsbInterfaceProto value) {
        if (value != null) {
            ensureInterfacesIsMutable();
            this.interfaces_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInterfaces(int index, UsbInterfaceProto.Builder builderForValue) {
        ensureInterfacesIsMutable();
        this.interfaces_.set(index, (UsbInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInterfaces(UsbInterfaceProto value) {
        if (value != null) {
            ensureInterfacesIsMutable();
            this.interfaces_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInterfaces(int index, UsbInterfaceProto value) {
        if (value != null) {
            ensureInterfacesIsMutable();
            this.interfaces_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInterfaces(UsbInterfaceProto.Builder builderForValue) {
        ensureInterfacesIsMutable();
        this.interfaces_.add((UsbInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInterfaces(int index, UsbInterfaceProto.Builder builderForValue) {
        ensureInterfacesIsMutable();
        this.interfaces_.add(index, (UsbInterfaceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllInterfaces(Iterable<? extends UsbInterfaceProto> values) {
        ensureInterfacesIsMutable();
        AbstractMessageLite.addAll(values, this.interfaces_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInterfaces() {
        this.interfaces_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeInterfaces(int index) {
        ensureInterfacesIsMutable();
        this.interfaces_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeUInt32(3, this.attributes_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.maxPower_);
        }
        for (int i = 0; i < this.interfaces_.size(); i++) {
            output.writeMessage(5, this.interfaces_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeUInt32Size(3, this.attributes_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.maxPower_);
        }
        for (int i = 0; i < this.interfaces_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.interfaces_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbConfigurationProto parseFrom(InputStream input) throws IOException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbConfigurationProto, Builder> implements UsbConfigurationProtoOrBuilder {
        private Builder() {
            super(UsbConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public boolean hasId() {
            return ((UsbConfigurationProto) this.instance).hasId();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public int getId() {
            return ((UsbConfigurationProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).clearId();
            return this;
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public boolean hasName() {
            return ((UsbConfigurationProto) this.instance).hasName();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public String getName() {
            return ((UsbConfigurationProto) this.instance).getName();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public ByteString getNameBytes() {
            return ((UsbConfigurationProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public boolean hasAttributes() {
            return ((UsbConfigurationProto) this.instance).hasAttributes();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public int getAttributes() {
            return ((UsbConfigurationProto) this.instance).getAttributes();
        }

        public Builder setAttributes(int value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setAttributes(value);
            return this;
        }

        public Builder clearAttributes() {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).clearAttributes();
            return this;
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public boolean hasMaxPower() {
            return ((UsbConfigurationProto) this.instance).hasMaxPower();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public int getMaxPower() {
            return ((UsbConfigurationProto) this.instance).getMaxPower();
        }

        public Builder setMaxPower(int value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setMaxPower(value);
            return this;
        }

        public Builder clearMaxPower() {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).clearMaxPower();
            return this;
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public List<UsbInterfaceProto> getInterfacesList() {
            return Collections.unmodifiableList(((UsbConfigurationProto) this.instance).getInterfacesList());
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public int getInterfacesCount() {
            return ((UsbConfigurationProto) this.instance).getInterfacesCount();
        }

        @Override // android.service.usb.UsbConfigurationProtoOrBuilder
        public UsbInterfaceProto getInterfaces(int index) {
            return ((UsbConfigurationProto) this.instance).getInterfaces(index);
        }

        public Builder setInterfaces(int index, UsbInterfaceProto value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setInterfaces((UsbConfigurationProto) index, (int) value);
            return this;
        }

        public Builder setInterfaces(int index, UsbInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).setInterfaces((UsbConfigurationProto) index, (int) builderForValue);
            return this;
        }

        public Builder addInterfaces(UsbInterfaceProto value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).addInterfaces((UsbConfigurationProto) value);
            return this;
        }

        public Builder addInterfaces(int index, UsbInterfaceProto value) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).addInterfaces((UsbConfigurationProto) index, (int) value);
            return this;
        }

        public Builder addInterfaces(UsbInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).addInterfaces((UsbConfigurationProto) builderForValue);
            return this;
        }

        public Builder addInterfaces(int index, UsbInterfaceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).addInterfaces((UsbConfigurationProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllInterfaces(Iterable<? extends UsbInterfaceProto> values) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).addAllInterfaces(values);
            return this;
        }

        public Builder clearInterfaces() {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).clearInterfaces();
            return this;
        }

        public Builder removeInterfaces(int index) {
            copyOnWrite();
            ((UsbConfigurationProto) this.instance).removeInterfaces(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbConfigurationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.interfaces_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbConfigurationProto other = (UsbConfigurationProto) arg1;
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.attributes_ = visitor.visitInt(hasAttributes(), this.attributes_, other.hasAttributes(), other.attributes_);
                this.maxPower_ = visitor.visitInt(hasMaxPower(), this.maxPower_, other.hasMaxPower(), other.maxPower_);
                this.interfaces_ = visitor.visitList(this.interfaces_, other.interfaces_);
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
                            this.id_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.name_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.attributes_ = input.readUInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.maxPower_ = input.readInt32();
                        } else if (tag == 42) {
                            if (!this.interfaces_.isModifiable()) {
                                this.interfaces_ = GeneratedMessageLite.mutableCopy(this.interfaces_);
                            }
                            this.interfaces_.add((UsbInterfaceProto) input.readMessage(UsbInterfaceProto.parser(), extensionRegistry));
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
                    synchronized (UsbConfigurationProto.class) {
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

    public static UsbConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
