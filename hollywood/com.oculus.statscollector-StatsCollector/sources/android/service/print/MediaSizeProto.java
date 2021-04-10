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

public final class MediaSizeProto extends GeneratedMessageLite<MediaSizeProto, Builder> implements MediaSizeProtoOrBuilder {
    private static final MediaSizeProto DEFAULT_INSTANCE = new MediaSizeProto();
    public static final int HEIGHT_MILS_FIELD_NUMBER = 3;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int LABEL_FIELD_NUMBER = 2;
    private static volatile Parser<MediaSizeProto> PARSER = null;
    public static final int WIDTH_MILS_FIELD_NUMBER = 4;
    private int bitField0_;
    private int heightMils_ = 0;
    private String id_ = "";
    private String label_ = "";
    private int widthMils_ = 0;

    private MediaSizeProto() {
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
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

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public String getLabel() {
        return this.label_;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
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

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public boolean hasHeightMils() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public int getHeightMils() {
        return this.heightMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeightMils(int value) {
        this.bitField0_ |= 4;
        this.heightMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeightMils() {
        this.bitField0_ &= -5;
        this.heightMils_ = 0;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public boolean hasWidthMils() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.MediaSizeProtoOrBuilder
    public int getWidthMils() {
        return this.widthMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWidthMils(int value) {
        this.bitField0_ |= 8;
        this.widthMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWidthMils() {
        this.bitField0_ &= -9;
        this.widthMils_ = 0;
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
            output.writeInt32(3, this.heightMils_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.widthMils_);
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
            size2 += CodedOutputStream.computeInt32Size(3, this.heightMils_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.widthMils_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MediaSizeProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MediaSizeProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MediaSizeProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MediaSizeProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MediaSizeProto parseFrom(InputStream input) throws IOException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MediaSizeProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MediaSizeProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MediaSizeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MediaSizeProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MediaSizeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MediaSizeProto parseFrom(CodedInputStream input) throws IOException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MediaSizeProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MediaSizeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MediaSizeProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MediaSizeProto, Builder> implements MediaSizeProtoOrBuilder {
        private Builder() {
            super(MediaSizeProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public boolean hasId() {
            return ((MediaSizeProto) this.instance).hasId();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public String getId() {
            return ((MediaSizeProto) this.instance).getId();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public ByteString getIdBytes() {
            return ((MediaSizeProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((MediaSizeProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public boolean hasLabel() {
            return ((MediaSizeProto) this.instance).hasLabel();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public String getLabel() {
            return ((MediaSizeProto) this.instance).getLabel();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public ByteString getLabelBytes() {
            return ((MediaSizeProto) this.instance).getLabelBytes();
        }

        public Builder setLabel(String value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setLabel(value);
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((MediaSizeProto) this.instance).clearLabel();
            return this;
        }

        public Builder setLabelBytes(ByteString value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setLabelBytes(value);
            return this;
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public boolean hasHeightMils() {
            return ((MediaSizeProto) this.instance).hasHeightMils();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public int getHeightMils() {
            return ((MediaSizeProto) this.instance).getHeightMils();
        }

        public Builder setHeightMils(int value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setHeightMils(value);
            return this;
        }

        public Builder clearHeightMils() {
            copyOnWrite();
            ((MediaSizeProto) this.instance).clearHeightMils();
            return this;
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public boolean hasWidthMils() {
            return ((MediaSizeProto) this.instance).hasWidthMils();
        }

        @Override // android.service.print.MediaSizeProtoOrBuilder
        public int getWidthMils() {
            return ((MediaSizeProto) this.instance).getWidthMils();
        }

        public Builder setWidthMils(int value) {
            copyOnWrite();
            ((MediaSizeProto) this.instance).setWidthMils(value);
            return this;
        }

        public Builder clearWidthMils() {
            copyOnWrite();
            ((MediaSizeProto) this.instance).clearWidthMils();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MediaSizeProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MediaSizeProto other = (MediaSizeProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                this.heightMils_ = visitor.visitInt(hasHeightMils(), this.heightMils_, other.hasHeightMils(), other.heightMils_);
                this.widthMils_ = visitor.visitInt(hasWidthMils(), this.widthMils_, other.hasWidthMils(), other.widthMils_);
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
                            this.heightMils_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.widthMils_ = input.readInt32();
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
                    synchronized (MediaSizeProto.class) {
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

    public static MediaSizeProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MediaSizeProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
