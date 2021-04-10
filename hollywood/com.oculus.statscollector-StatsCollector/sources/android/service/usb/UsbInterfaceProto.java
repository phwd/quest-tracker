package android.service.usb;

import android.service.usb.UsbEndPointProto;
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

public final class UsbInterfaceProto extends GeneratedMessageLite<UsbInterfaceProto, Builder> implements UsbInterfaceProtoOrBuilder {
    public static final int ALTERNATE_SETTINGS_FIELD_NUMBER = 2;
    public static final int CLASS_FIELD_NUMBER = 4;
    private static final UsbInterfaceProto DEFAULT_INSTANCE = new UsbInterfaceProto();
    public static final int ENDPOINTS_FIELD_NUMBER = 7;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser<UsbInterfaceProto> PARSER = null;
    public static final int PROTOCOL_FIELD_NUMBER = 6;
    public static final int SUBCLASS_FIELD_NUMBER = 5;
    private int alternateSettings_ = 0;
    private int bitField0_;
    private int class__ = 0;
    private Internal.ProtobufList<UsbEndPointProto> endpoints_ = emptyProtobufList();
    private int id_ = 0;
    private String name_ = "";
    private int protocol_ = 0;
    private int subclass_ = 0;

    private UsbInterfaceProto() {
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
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

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasAlternateSettings() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public int getAlternateSettings() {
        return this.alternateSettings_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlternateSettings(int value) {
        this.bitField0_ |= 2;
        this.alternateSettings_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlternateSettings() {
        this.bitField0_ &= -3;
        this.alternateSettings_ = 0;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
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

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasClass_() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public int getClass_() {
        return this.class__;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClass_(int value) {
        this.bitField0_ |= 8;
        this.class__ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClass_() {
        this.bitField0_ &= -9;
        this.class__ = 0;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasSubclass() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public int getSubclass() {
        return this.subclass_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubclass(int value) {
        this.bitField0_ |= 16;
        this.subclass_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSubclass() {
        this.bitField0_ &= -17;
        this.subclass_ = 0;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public boolean hasProtocol() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public int getProtocol() {
        return this.protocol_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProtocol(int value) {
        this.bitField0_ |= 32;
        this.protocol_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProtocol() {
        this.bitField0_ &= -33;
        this.protocol_ = 0;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public List<UsbEndPointProto> getEndpointsList() {
        return this.endpoints_;
    }

    public List<? extends UsbEndPointProtoOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override // android.service.usb.UsbInterfaceProtoOrBuilder
    public UsbEndPointProto getEndpoints(int index) {
        return this.endpoints_.get(index);
    }

    public UsbEndPointProtoOrBuilder getEndpointsOrBuilder(int index) {
        return this.endpoints_.get(index);
    }

    private void ensureEndpointsIsMutable() {
        if (!this.endpoints_.isModifiable()) {
            this.endpoints_ = GeneratedMessageLite.mutableCopy(this.endpoints_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndpoints(int index, UsbEndPointProto value) {
        if (value != null) {
            ensureEndpointsIsMutable();
            this.endpoints_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndpoints(int index, UsbEndPointProto.Builder builderForValue) {
        ensureEndpointsIsMutable();
        this.endpoints_.set(index, (UsbEndPointProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEndpoints(UsbEndPointProto value) {
        if (value != null) {
            ensureEndpointsIsMutable();
            this.endpoints_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEndpoints(int index, UsbEndPointProto value) {
        if (value != null) {
            ensureEndpointsIsMutable();
            this.endpoints_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEndpoints(UsbEndPointProto.Builder builderForValue) {
        ensureEndpointsIsMutable();
        this.endpoints_.add((UsbEndPointProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEndpoints(int index, UsbEndPointProto.Builder builderForValue) {
        ensureEndpointsIsMutable();
        this.endpoints_.add(index, (UsbEndPointProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEndpoints(Iterable<? extends UsbEndPointProto> values) {
        ensureEndpointsIsMutable();
        AbstractMessageLite.addAll(values, this.endpoints_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndpoints() {
        this.endpoints_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEndpoints(int index) {
        ensureEndpointsIsMutable();
        this.endpoints_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.alternateSettings_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getName());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.class__);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.subclass_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.protocol_);
        }
        for (int i = 0; i < this.endpoints_.size(); i++) {
            output.writeMessage(7, this.endpoints_.get(i));
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
            size2 += CodedOutputStream.computeInt32Size(2, this.alternateSettings_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getName());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.class__);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.subclass_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.protocol_);
        }
        for (int i = 0; i < this.endpoints_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.endpoints_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbInterfaceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbInterfaceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbInterfaceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbInterfaceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbInterfaceProto parseFrom(InputStream input) throws IOException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbInterfaceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbInterfaceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbInterfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbInterfaceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbInterfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbInterfaceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbInterfaceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbInterfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbInterfaceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbInterfaceProto, Builder> implements UsbInterfaceProtoOrBuilder {
        private Builder() {
            super(UsbInterfaceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasId() {
            return ((UsbInterfaceProto) this.instance).hasId();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getId() {
            return ((UsbInterfaceProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearId();
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasAlternateSettings() {
            return ((UsbInterfaceProto) this.instance).hasAlternateSettings();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getAlternateSettings() {
            return ((UsbInterfaceProto) this.instance).getAlternateSettings();
        }

        public Builder setAlternateSettings(int value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setAlternateSettings(value);
            return this;
        }

        public Builder clearAlternateSettings() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearAlternateSettings();
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasName() {
            return ((UsbInterfaceProto) this.instance).hasName();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public String getName() {
            return ((UsbInterfaceProto) this.instance).getName();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public ByteString getNameBytes() {
            return ((UsbInterfaceProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasClass_() {
            return ((UsbInterfaceProto) this.instance).hasClass_();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getClass_() {
            return ((UsbInterfaceProto) this.instance).getClass_();
        }

        public Builder setClass_(int value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setClass_(value);
            return this;
        }

        public Builder clearClass_() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearClass_();
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasSubclass() {
            return ((UsbInterfaceProto) this.instance).hasSubclass();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getSubclass() {
            return ((UsbInterfaceProto) this.instance).getSubclass();
        }

        public Builder setSubclass(int value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setSubclass(value);
            return this;
        }

        public Builder clearSubclass() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearSubclass();
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public boolean hasProtocol() {
            return ((UsbInterfaceProto) this.instance).hasProtocol();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getProtocol() {
            return ((UsbInterfaceProto) this.instance).getProtocol();
        }

        public Builder setProtocol(int value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setProtocol(value);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearProtocol();
            return this;
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public List<UsbEndPointProto> getEndpointsList() {
            return Collections.unmodifiableList(((UsbInterfaceProto) this.instance).getEndpointsList());
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public int getEndpointsCount() {
            return ((UsbInterfaceProto) this.instance).getEndpointsCount();
        }

        @Override // android.service.usb.UsbInterfaceProtoOrBuilder
        public UsbEndPointProto getEndpoints(int index) {
            return ((UsbInterfaceProto) this.instance).getEndpoints(index);
        }

        public Builder setEndpoints(int index, UsbEndPointProto value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setEndpoints((UsbInterfaceProto) index, (int) value);
            return this;
        }

        public Builder setEndpoints(int index, UsbEndPointProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).setEndpoints((UsbInterfaceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEndpoints(UsbEndPointProto value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).addEndpoints((UsbInterfaceProto) value);
            return this;
        }

        public Builder addEndpoints(int index, UsbEndPointProto value) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).addEndpoints((UsbInterfaceProto) index, (int) value);
            return this;
        }

        public Builder addEndpoints(UsbEndPointProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).addEndpoints((UsbInterfaceProto) builderForValue);
            return this;
        }

        public Builder addEndpoints(int index, UsbEndPointProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).addEndpoints((UsbInterfaceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends UsbEndPointProto> values) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).addAllEndpoints(values);
            return this;
        }

        public Builder clearEndpoints() {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).clearEndpoints();
            return this;
        }

        public Builder removeEndpoints(int index) {
            copyOnWrite();
            ((UsbInterfaceProto) this.instance).removeEndpoints(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbInterfaceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.endpoints_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbInterfaceProto other = (UsbInterfaceProto) arg1;
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.alternateSettings_ = visitor.visitInt(hasAlternateSettings(), this.alternateSettings_, other.hasAlternateSettings(), other.alternateSettings_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.class__ = visitor.visitInt(hasClass_(), this.class__, other.hasClass_(), other.class__);
                this.subclass_ = visitor.visitInt(hasSubclass(), this.subclass_, other.hasSubclass(), other.subclass_);
                this.protocol_ = visitor.visitInt(hasProtocol(), this.protocol_, other.hasProtocol(), other.protocol_);
                this.endpoints_ = visitor.visitList(this.endpoints_, other.endpoints_);
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
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.alternateSettings_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.name_ = s;
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.class__ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.subclass_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.protocol_ = input.readInt32();
                        } else if (tag == 58) {
                            if (!this.endpoints_.isModifiable()) {
                                this.endpoints_ = GeneratedMessageLite.mutableCopy(this.endpoints_);
                            }
                            this.endpoints_.add((UsbEndPointProto) input.readMessage(UsbEndPointProto.parser(), extensionRegistry));
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
                    synchronized (UsbInterfaceProto.class) {
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

    public static UsbInterfaceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbInterfaceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
