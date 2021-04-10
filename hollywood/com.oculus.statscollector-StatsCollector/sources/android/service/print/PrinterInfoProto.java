package android.service.print;

import android.service.print.PrinterCapabilitiesProto;
import android.service.print.PrinterIdProto;
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

public final class PrinterInfoProto extends GeneratedMessageLite<PrinterInfoProto, Builder> implements PrinterInfoProtoOrBuilder {
    public static final int CAPABILITIES_FIELD_NUMBER = 5;
    private static final PrinterInfoProto DEFAULT_INSTANCE = new PrinterInfoProto();
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<PrinterInfoProto> PARSER = null;
    public static final int STATUS_FIELD_NUMBER = 3;
    private int bitField0_;
    private PrinterCapabilitiesProto capabilities_;
    private String description_ = "";
    private PrinterIdProto id_;
    private String name_ = "";
    private int status_ = 0;

    private PrinterInfoProto() {
    }

    public enum Status implements Internal.EnumLite {
        __STATUS_UNUSED(0),
        STATUS_IDLE(1),
        STATUS_BUSY(2),
        STATUS_UNAVAILABLE(3);
        
        public static final int STATUS_BUSY_VALUE = 2;
        public static final int STATUS_IDLE_VALUE = 1;
        public static final int STATUS_UNAVAILABLE_VALUE = 3;
        public static final int __STATUS_UNUSED_VALUE = 0;
        private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {
            /* class android.service.print.PrinterInfoProto.Status.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Status findValueByNumber(int number) {
                return Status.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Status valueOf(int value2) {
            return forNumber(value2);
        }

        public static Status forNumber(int value2) {
            if (value2 == 0) {
                return __STATUS_UNUSED;
            }
            if (value2 == 1) {
                return STATUS_IDLE;
            }
            if (value2 == 2) {
                return STATUS_BUSY;
            }
            if (value2 != 3) {
                return null;
            }
            return STATUS_UNAVAILABLE;
        }

        public static Internal.EnumLiteMap<Status> internalGetValueMap() {
            return internalValueMap;
        }

        private Status(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public PrinterIdProto getId() {
        PrinterIdProto printerIdProto = this.id_;
        return printerIdProto == null ? PrinterIdProto.getDefaultInstance() : printerIdProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(PrinterIdProto value) {
        if (value != null) {
            this.id_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(PrinterIdProto.Builder builderForValue) {
        this.id_ = (PrinterIdProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeId(PrinterIdProto value) {
        PrinterIdProto printerIdProto = this.id_;
        if (printerIdProto == null || printerIdProto == PrinterIdProto.getDefaultInstance()) {
            this.id_ = value;
        } else {
            this.id_ = (PrinterIdProto) ((PrinterIdProto.Builder) PrinterIdProto.newBuilder(this.id_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.id_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
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

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public boolean hasStatus() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public Status getStatus() {
        Status result = Status.forNumber(this.status_);
        return result == null ? Status.__STATUS_UNUSED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(Status value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.status_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.bitField0_ &= -5;
        this.status_ = 0;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescription(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.description_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDescription() {
        this.bitField0_ &= -9;
        this.description_ = getDefaultInstance().getDescription();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescriptionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.description_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public boolean hasCapabilities() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.print.PrinterInfoProtoOrBuilder
    public PrinterCapabilitiesProto getCapabilities() {
        PrinterCapabilitiesProto printerCapabilitiesProto = this.capabilities_;
        return printerCapabilitiesProto == null ? PrinterCapabilitiesProto.getDefaultInstance() : printerCapabilitiesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCapabilities(PrinterCapabilitiesProto value) {
        if (value != null) {
            this.capabilities_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCapabilities(PrinterCapabilitiesProto.Builder builderForValue) {
        this.capabilities_ = (PrinterCapabilitiesProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCapabilities(PrinterCapabilitiesProto value) {
        PrinterCapabilitiesProto printerCapabilitiesProto = this.capabilities_;
        if (printerCapabilitiesProto == null || printerCapabilitiesProto == PrinterCapabilitiesProto.getDefaultInstance()) {
            this.capabilities_ = value;
        } else {
            this.capabilities_ = (PrinterCapabilitiesProto) ((PrinterCapabilitiesProto.Builder) PrinterCapabilitiesProto.newBuilder(this.capabilities_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCapabilities() {
        this.capabilities_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.status_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getDescription());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getCapabilities());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.status_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getDescription());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getCapabilities());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrinterInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterInfoProto parseFrom(InputStream input) throws IOException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrinterInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrinterInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrinterInfoProto, Builder> implements PrinterInfoProtoOrBuilder {
        private Builder() {
            super(PrinterInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public boolean hasId() {
            return ((PrinterInfoProto) this.instance).hasId();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public PrinterIdProto getId() {
            return ((PrinterInfoProto) this.instance).getId();
        }

        public Builder setId(PrinterIdProto value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setId((PrinterInfoProto) value);
            return this;
        }

        public Builder setId(PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setId((PrinterInfoProto) builderForValue);
            return this;
        }

        public Builder mergeId(PrinterIdProto value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).mergeId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).clearId();
            return this;
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public boolean hasName() {
            return ((PrinterInfoProto) this.instance).hasName();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public String getName() {
            return ((PrinterInfoProto) this.instance).getName();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public ByteString getNameBytes() {
            return ((PrinterInfoProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public boolean hasStatus() {
            return ((PrinterInfoProto) this.instance).hasStatus();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public Status getStatus() {
            return ((PrinterInfoProto) this.instance).getStatus();
        }

        public Builder setStatus(Status value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).clearStatus();
            return this;
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public boolean hasDescription() {
            return ((PrinterInfoProto) this.instance).hasDescription();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public String getDescription() {
            return ((PrinterInfoProto) this.instance).getDescription();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public ByteString getDescriptionBytes() {
            return ((PrinterInfoProto) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setDescriptionBytes(value);
            return this;
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public boolean hasCapabilities() {
            return ((PrinterInfoProto) this.instance).hasCapabilities();
        }

        @Override // android.service.print.PrinterInfoProtoOrBuilder
        public PrinterCapabilitiesProto getCapabilities() {
            return ((PrinterInfoProto) this.instance).getCapabilities();
        }

        public Builder setCapabilities(PrinterCapabilitiesProto value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setCapabilities((PrinterInfoProto) value);
            return this;
        }

        public Builder setCapabilities(PrinterCapabilitiesProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).setCapabilities((PrinterInfoProto) builderForValue);
            return this;
        }

        public Builder mergeCapabilities(PrinterCapabilitiesProto value) {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).mergeCapabilities(value);
            return this;
        }

        public Builder clearCapabilities() {
            copyOnWrite();
            ((PrinterInfoProto) this.instance).clearCapabilities();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrinterInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrinterInfoProto other = (PrinterInfoProto) arg1;
                this.id_ = (PrinterIdProto) visitor.visitMessage(this.id_, other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.status_ = visitor.visitInt(hasStatus(), this.status_, other.hasStatus(), other.status_);
                this.description_ = visitor.visitString(hasDescription(), this.description_, other.hasDescription(), other.description_);
                this.capabilities_ = (PrinterCapabilitiesProto) visitor.visitMessage(this.capabilities_, other.capabilities_);
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
                            PrinterIdProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (PrinterIdProto.Builder) this.id_.toBuilder();
                            }
                            this.id_ = (PrinterIdProto) input.readMessage(PrinterIdProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.id_);
                                this.id_ = (PrinterIdProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.name_ = s;
                        } else if (tag == 24) {
                            int rawValue = input.readEnum();
                            if (Status.forNumber(rawValue) == null) {
                                super.mergeVarintField(3, rawValue);
                            } else {
                                this.bitField0_ |= 4;
                                this.status_ = rawValue;
                            }
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ |= 8;
                            this.description_ = s2;
                        } else if (tag == 42) {
                            PrinterCapabilitiesProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder2 = (PrinterCapabilitiesProto.Builder) this.capabilities_.toBuilder();
                            }
                            this.capabilities_ = (PrinterCapabilitiesProto) input.readMessage(PrinterCapabilitiesProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.capabilities_);
                                this.capabilities_ = (PrinterCapabilitiesProto) subBuilder2.buildPartial();
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
                    synchronized (PrinterInfoProto.class) {
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

    public static PrinterInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrinterInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
