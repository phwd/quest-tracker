package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PrinterIdProto extends GeneratedMessageLite<PrinterIdProto, Builder> implements PrinterIdProtoOrBuilder {
    private static final PrinterIdProto DEFAULT_INSTANCE = new PrinterIdProto();
    public static final int LOCAL_ID_FIELD_NUMBER = 2;
    private static volatile Parser<PrinterIdProto> PARSER = null;
    public static final int SERVICE_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private String localId_ = "";
    private ComponentNameProto serviceName_;

    private PrinterIdProto() {
    }

    @Override // android.service.print.PrinterIdProtoOrBuilder
    public boolean hasServiceName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrinterIdProtoOrBuilder
    public ComponentNameProto getServiceName() {
        ComponentNameProto componentNameProto = this.serviceName_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceName(ComponentNameProto value) {
        if (value != null) {
            this.serviceName_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceName(ComponentNameProto.Builder builderForValue) {
        this.serviceName_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeServiceName(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.serviceName_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.serviceName_ = value;
        } else {
            this.serviceName_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.serviceName_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServiceName() {
        this.serviceName_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.PrinterIdProtoOrBuilder
    public boolean hasLocalId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrinterIdProtoOrBuilder
    public String getLocalId() {
        return this.localId_;
    }

    @Override // android.service.print.PrinterIdProtoOrBuilder
    public ByteString getLocalIdBytes() {
        return ByteString.copyFromUtf8(this.localId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocalId(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.localId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLocalId() {
        this.bitField0_ &= -3;
        this.localId_ = getDefaultInstance().getLocalId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocalIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.localId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getServiceName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getLocalId());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getServiceName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getLocalId());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrinterIdProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterIdProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterIdProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterIdProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterIdProto parseFrom(InputStream input) throws IOException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterIdProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterIdProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrinterIdProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterIdProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterIdProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterIdProto parseFrom(CodedInputStream input) throws IOException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterIdProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterIdProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrinterIdProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrinterIdProto, Builder> implements PrinterIdProtoOrBuilder {
        private Builder() {
            super(PrinterIdProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrinterIdProtoOrBuilder
        public boolean hasServiceName() {
            return ((PrinterIdProto) this.instance).hasServiceName();
        }

        @Override // android.service.print.PrinterIdProtoOrBuilder
        public ComponentNameProto getServiceName() {
            return ((PrinterIdProto) this.instance).getServiceName();
        }

        public Builder setServiceName(ComponentNameProto value) {
            copyOnWrite();
            ((PrinterIdProto) this.instance).setServiceName((PrinterIdProto) value);
            return this;
        }

        public Builder setServiceName(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterIdProto) this.instance).setServiceName((PrinterIdProto) builderForValue);
            return this;
        }

        public Builder mergeServiceName(ComponentNameProto value) {
            copyOnWrite();
            ((PrinterIdProto) this.instance).mergeServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((PrinterIdProto) this.instance).clearServiceName();
            return this;
        }

        @Override // android.service.print.PrinterIdProtoOrBuilder
        public boolean hasLocalId() {
            return ((PrinterIdProto) this.instance).hasLocalId();
        }

        @Override // android.service.print.PrinterIdProtoOrBuilder
        public String getLocalId() {
            return ((PrinterIdProto) this.instance).getLocalId();
        }

        @Override // android.service.print.PrinterIdProtoOrBuilder
        public ByteString getLocalIdBytes() {
            return ((PrinterIdProto) this.instance).getLocalIdBytes();
        }

        public Builder setLocalId(String value) {
            copyOnWrite();
            ((PrinterIdProto) this.instance).setLocalId(value);
            return this;
        }

        public Builder clearLocalId() {
            copyOnWrite();
            ((PrinterIdProto) this.instance).clearLocalId();
            return this;
        }

        public Builder setLocalIdBytes(ByteString value) {
            copyOnWrite();
            ((PrinterIdProto) this.instance).setLocalIdBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrinterIdProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrinterIdProto other = (PrinterIdProto) arg1;
                this.serviceName_ = (ComponentNameProto) visitor.visitMessage(this.serviceName_, other.serviceName_);
                this.localId_ = visitor.visitString(hasLocalId(), this.localId_, other.hasLocalId(), other.localId_);
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
                                subBuilder = (ComponentNameProto.Builder) this.serviceName_.toBuilder();
                            }
                            this.serviceName_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.serviceName_);
                                this.serviceName_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.localId_ = s;
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
                    synchronized (PrinterIdProto.class) {
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

    public static PrinterIdProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrinterIdProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
