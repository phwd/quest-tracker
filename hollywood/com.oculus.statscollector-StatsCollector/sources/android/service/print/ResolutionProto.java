package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ResolutionProto extends GeneratedMessageLite<ResolutionProto, Builder> implements ResolutionProtoOrBuilder {
    private static final ResolutionProto DEFAULT_INSTANCE = new ResolutionProto();
    public static final int HORIZONTAL_DPI_FIELD_NUMBER = 3;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int LABEL_FIELD_NUMBER = 2;
    private static volatile Parser<ResolutionProto> PARSER = null;
    public static final int VERTICAL_DPI_FIELD_NUMBER = 4;
    private int bitField0_;
    private int horizontalDpi_ = 0;
    private String id_ = "";
    private String label_ = "";
    private int verticalDpi_ = 0;

    private ResolutionProto() {
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
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

    @Override // android.service.print.ResolutionProtoOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public String getLabel() {
        return this.label_;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public ByteString getLabelBytes() {
        return ByteString.copyFromUtf8(this.label_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabel(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.label_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLabel() {
        this.bitField0_ &= -3;
        this.label_ = getDefaultInstance().getLabel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.label_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public boolean hasHorizontalDpi() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public int getHorizontalDpi() {
        return this.horizontalDpi_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHorizontalDpi(int value) {
        this.bitField0_ |= 4;
        this.horizontalDpi_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHorizontalDpi() {
        this.bitField0_ &= -5;
        this.horizontalDpi_ = 0;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public boolean hasVerticalDpi() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.ResolutionProtoOrBuilder
    public int getVerticalDpi() {
        return this.verticalDpi_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVerticalDpi(int value) {
        this.bitField0_ |= 8;
        this.verticalDpi_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVerticalDpi() {
        this.bitField0_ &= -9;
        this.verticalDpi_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getLabel());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.horizontalDpi_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.verticalDpi_);
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
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getLabel());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.horizontalDpi_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.verticalDpi_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ResolutionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResolutionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResolutionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResolutionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResolutionProto parseFrom(InputStream input) throws IOException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResolutionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResolutionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ResolutionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ResolutionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResolutionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResolutionProto parseFrom(CodedInputStream input) throws IOException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResolutionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResolutionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResolutionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ResolutionProto, Builder> implements ResolutionProtoOrBuilder {
        private Builder() {
            super(ResolutionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public boolean hasId() {
            return ((ResolutionProto) this.instance).hasId();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public String getId() {
            return ((ResolutionProto) this.instance).getId();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public ByteString getIdBytes() {
            return ((ResolutionProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((ResolutionProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public boolean hasLabel() {
            return ((ResolutionProto) this.instance).hasLabel();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public String getLabel() {
            return ((ResolutionProto) this.instance).getLabel();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public ByteString getLabelBytes() {
            return ((ResolutionProto) this.instance).getLabelBytes();
        }

        public Builder setLabel(String value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setLabel(value);
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((ResolutionProto) this.instance).clearLabel();
            return this;
        }

        public Builder setLabelBytes(ByteString value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setLabelBytes(value);
            return this;
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public boolean hasHorizontalDpi() {
            return ((ResolutionProto) this.instance).hasHorizontalDpi();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public int getHorizontalDpi() {
            return ((ResolutionProto) this.instance).getHorizontalDpi();
        }

        public Builder setHorizontalDpi(int value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setHorizontalDpi(value);
            return this;
        }

        public Builder clearHorizontalDpi() {
            copyOnWrite();
            ((ResolutionProto) this.instance).clearHorizontalDpi();
            return this;
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public boolean hasVerticalDpi() {
            return ((ResolutionProto) this.instance).hasVerticalDpi();
        }

        @Override // android.service.print.ResolutionProtoOrBuilder
        public int getVerticalDpi() {
            return ((ResolutionProto) this.instance).getVerticalDpi();
        }

        public Builder setVerticalDpi(int value) {
            copyOnWrite();
            ((ResolutionProto) this.instance).setVerticalDpi(value);
            return this;
        }

        public Builder clearVerticalDpi() {
            copyOnWrite();
            ((ResolutionProto) this.instance).clearVerticalDpi();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ResolutionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ResolutionProto other = (ResolutionProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                this.horizontalDpi_ = visitor.visitInt(hasHorizontalDpi(), this.horizontalDpi_, other.hasHorizontalDpi(), other.horizontalDpi_);
                this.verticalDpi_ = visitor.visitInt(hasVerticalDpi(), this.verticalDpi_, other.hasVerticalDpi(), other.verticalDpi_);
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
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.label_ = s2;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.horizontalDpi_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.verticalDpi_ = input.readInt32();
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
                    synchronized (ResolutionProto.class) {
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

    public static ResolutionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResolutionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
