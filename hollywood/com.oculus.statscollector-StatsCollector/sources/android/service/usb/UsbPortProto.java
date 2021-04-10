package android.service.usb;

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
import java.util.List;

public final class UsbPortProto extends GeneratedMessageLite<UsbPortProto, Builder> implements UsbPortProtoOrBuilder {
    private static final UsbPortProto DEFAULT_INSTANCE = new UsbPortProto();
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile Parser<UsbPortProto> PARSER = null;
    public static final int SUPPORTED_MODES_FIELD_NUMBER = 2;
    private static final Internal.ListAdapter.Converter<Integer, Mode> supportedModes_converter_ = new Internal.ListAdapter.Converter<Integer, Mode>() {
        /* class android.service.usb.UsbPortProto.AnonymousClass1 */

        public Mode convert(Integer from) {
            Mode result = Mode.forNumber(from.intValue());
            return result == null ? Mode.MODE_NONE : result;
        }
    };
    private int bitField0_;
    private String id_ = "";
    private Internal.IntList supportedModes_ = emptyIntList();

    private UsbPortProto() {
    }

    public enum Mode implements Internal.EnumLite {
        MODE_NONE(0),
        MODE_UFP(1),
        MODE_DFP(2),
        MODE_DRP(3),
        MODE_AUDIO_ACCESSORY(4),
        MODE_DEBUG_ACCESSORY(8);
        
        public static final int MODE_AUDIO_ACCESSORY_VALUE = 4;
        public static final int MODE_DEBUG_ACCESSORY_VALUE = 8;
        public static final int MODE_DFP_VALUE = 2;
        public static final int MODE_DRP_VALUE = 3;
        public static final int MODE_NONE_VALUE = 0;
        public static final int MODE_UFP_VALUE = 1;
        private static final Internal.EnumLiteMap<Mode> internalValueMap = new Internal.EnumLiteMap<Mode>() {
            /* class android.service.usb.UsbPortProto.Mode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Mode findValueByNumber(int number) {
                return Mode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Mode valueOf(int value2) {
            return forNumber(value2);
        }

        public static Mode forNumber(int value2) {
            if (value2 == 0) {
                return MODE_NONE;
            }
            if (value2 == 1) {
                return MODE_UFP;
            }
            if (value2 == 2) {
                return MODE_DFP;
            }
            if (value2 == 3) {
                return MODE_DRP;
            }
            if (value2 == 4) {
                return MODE_AUDIO_ACCESSORY;
            }
            if (value2 != 8) {
                return null;
            }
            return MODE_DEBUG_ACCESSORY;
        }

        public static Internal.EnumLiteMap<Mode> internalGetValueMap() {
            return internalValueMap;
        }

        private Mode(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -2;
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public List<Mode> getSupportedModesList() {
        return new Internal.ListAdapter(this.supportedModes_, supportedModes_converter_);
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public int getSupportedModesCount() {
        return this.supportedModes_.size();
    }

    @Override // android.service.usb.UsbPortProtoOrBuilder
    public Mode getSupportedModes(int index) {
        return supportedModes_converter_.convert(Integer.valueOf(this.supportedModes_.getInt(index)));
    }

    private void ensureSupportedModesIsMutable() {
        if (!this.supportedModes_.isModifiable()) {
            this.supportedModes_ = GeneratedMessageLite.mutableCopy(this.supportedModes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSupportedModes(int index, Mode value) {
        if (value != null) {
            ensureSupportedModesIsMutable();
            this.supportedModes_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSupportedModes(Mode value) {
        if (value != null) {
            ensureSupportedModesIsMutable();
            this.supportedModes_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSupportedModes(Iterable<? extends Mode> values) {
        ensureSupportedModesIsMutable();
        for (Mode value : values) {
            this.supportedModes_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSupportedModes() {
        this.supportedModes_ = emptyIntList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        for (int i = 0; i < this.supportedModes_.size(); i++) {
            output.writeEnum(2, this.supportedModes_.getInt(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        int dataSize = 0;
        for (int i = 0; i < this.supportedModes_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.supportedModes_.getInt(i));
        }
        int size3 = size2 + dataSize + (this.supportedModes_.size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbPortProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortProto parseFrom(InputStream input) throws IOException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbPortProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbPortProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbPortProto, Builder> implements UsbPortProtoOrBuilder {
        private Builder() {
            super(UsbPortProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public boolean hasId() {
            return ((UsbPortProto) this.instance).hasId();
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public String getId() {
            return ((UsbPortProto) this.instance).getId();
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public ByteString getIdBytes() {
            return ((UsbPortProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((UsbPortProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((UsbPortProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((UsbPortProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public List<Mode> getSupportedModesList() {
            return ((UsbPortProto) this.instance).getSupportedModesList();
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public int getSupportedModesCount() {
            return ((UsbPortProto) this.instance).getSupportedModesCount();
        }

        @Override // android.service.usb.UsbPortProtoOrBuilder
        public Mode getSupportedModes(int index) {
            return ((UsbPortProto) this.instance).getSupportedModes(index);
        }

        public Builder setSupportedModes(int index, Mode value) {
            copyOnWrite();
            ((UsbPortProto) this.instance).setSupportedModes(index, value);
            return this;
        }

        public Builder addSupportedModes(Mode value) {
            copyOnWrite();
            ((UsbPortProto) this.instance).addSupportedModes(value);
            return this;
        }

        public Builder addAllSupportedModes(Iterable<? extends Mode> values) {
            copyOnWrite();
            ((UsbPortProto) this.instance).addAllSupportedModes(values);
            return this;
        }

        public Builder clearSupportedModes() {
            copyOnWrite();
            ((UsbPortProto) this.instance).clearSupportedModes();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbPortProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.supportedModes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbPortProto other = (UsbPortProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.supportedModes_ = visitor.visitIntList(this.supportedModes_, other.supportedModes_);
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
                            this.id_ = s;
                        } else if (tag == 16) {
                            if (!this.supportedModes_.isModifiable()) {
                                this.supportedModes_ = GeneratedMessageLite.mutableCopy(this.supportedModes_);
                            }
                            int rawValue = input.readEnum();
                            if (Mode.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.supportedModes_.addInt(rawValue);
                            }
                        } else if (tag == 18) {
                            if (!this.supportedModes_.isModifiable()) {
                                this.supportedModes_ = GeneratedMessageLite.mutableCopy(this.supportedModes_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (Mode.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(2, rawValue2);
                                } else {
                                    this.supportedModes_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
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
                    synchronized (UsbPortProto.class) {
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

    public static UsbPortProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbPortProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
