package android.content;

import android.os.PersistableBundleProto;
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

public final class ClipDescriptionProto extends GeneratedMessageLite<ClipDescriptionProto, Builder> implements ClipDescriptionProtoOrBuilder {
    private static final ClipDescriptionProto DEFAULT_INSTANCE = new ClipDescriptionProto();
    public static final int EXTRAS_FIELD_NUMBER = 3;
    public static final int LABEL_FIELD_NUMBER = 2;
    public static final int MIME_TYPES_FIELD_NUMBER = 1;
    private static volatile Parser<ClipDescriptionProto> PARSER = null;
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 4;
    private int bitField0_;
    private PersistableBundleProto extras_;
    private String label_ = "";
    private Internal.ProtobufList<String> mimeTypes_ = GeneratedMessageLite.emptyProtobufList();
    private long timestampMs_ = 0;

    private ClipDescriptionProto() {
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public List<String> getMimeTypesList() {
        return this.mimeTypes_;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public int getMimeTypesCount() {
        return this.mimeTypes_.size();
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public String getMimeTypes(int index) {
        return this.mimeTypes_.get(index);
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public ByteString getMimeTypesBytes(int index) {
        return ByteString.copyFromUtf8(this.mimeTypes_.get(index));
    }

    private void ensureMimeTypesIsMutable() {
        if (!this.mimeTypes_.isModifiable()) {
            this.mimeTypes_ = GeneratedMessageLite.mutableCopy(this.mimeTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMimeTypes(int index, String value) {
        if (value != null) {
            ensureMimeTypesIsMutable();
            this.mimeTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypes(String value) {
        if (value != null) {
            ensureMimeTypesIsMutable();
            this.mimeTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMimeTypes(Iterable<String> values) {
        ensureMimeTypesIsMutable();
        AbstractMessageLite.addAll(values, this.mimeTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMimeTypes() {
        this.mimeTypes_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypesBytes(ByteString value) {
        if (value != null) {
            ensureMimeTypesIsMutable();
            this.mimeTypes_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public String getLabel() {
        return this.label_;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public ByteString getLabelBytes() {
        return ByteString.copyFromUtf8(this.label_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabel(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.label_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLabel() {
        this.bitField0_ &= -2;
        this.label_ = getDefaultInstance().getLabel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.label_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public boolean hasExtras() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public PersistableBundleProto getExtras() {
        PersistableBundleProto persistableBundleProto = this.extras_;
        return persistableBundleProto == null ? PersistableBundleProto.getDefaultInstance() : persistableBundleProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExtras(PersistableBundleProto value) {
        if (value != null) {
            this.extras_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExtras(PersistableBundleProto.Builder builderForValue) {
        this.extras_ = (PersistableBundleProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeExtras(PersistableBundleProto value) {
        PersistableBundleProto persistableBundleProto = this.extras_;
        if (persistableBundleProto == null || persistableBundleProto == PersistableBundleProto.getDefaultInstance()) {
            this.extras_ = value;
        } else {
            this.extras_ = (PersistableBundleProto) ((PersistableBundleProto.Builder) PersistableBundleProto.newBuilder(this.extras_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearExtras() {
        this.extras_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public boolean hasTimestampMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.ClipDescriptionProtoOrBuilder
    public long getTimestampMs() {
        return this.timestampMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimestampMs(long value) {
        this.bitField0_ |= 4;
        this.timestampMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimestampMs() {
        this.bitField0_ &= -5;
        this.timestampMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.mimeTypes_.size(); i++) {
            output.writeString(1, this.mimeTypes_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(2, getLabel());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getExtras());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(4, this.timestampMs_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.mimeTypes_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.mimeTypes_.get(i));
        }
        int size2 = 0 + dataSize + (getMimeTypesList().size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeStringSize(2, getLabel());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getExtras());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(4, this.timestampMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ClipDescriptionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ClipDescriptionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ClipDescriptionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ClipDescriptionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ClipDescriptionProto parseFrom(InputStream input) throws IOException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDescriptionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ClipDescriptionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ClipDescriptionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDescriptionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDescriptionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ClipDescriptionProto parseFrom(CodedInputStream input) throws IOException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ClipDescriptionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ClipDescriptionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ClipDescriptionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ClipDescriptionProto, Builder> implements ClipDescriptionProtoOrBuilder {
        private Builder() {
            super(ClipDescriptionProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public List<String> getMimeTypesList() {
            return Collections.unmodifiableList(((ClipDescriptionProto) this.instance).getMimeTypesList());
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public int getMimeTypesCount() {
            return ((ClipDescriptionProto) this.instance).getMimeTypesCount();
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public String getMimeTypes(int index) {
            return ((ClipDescriptionProto) this.instance).getMimeTypes(index);
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public ByteString getMimeTypesBytes(int index) {
            return ((ClipDescriptionProto) this.instance).getMimeTypesBytes(index);
        }

        public Builder setMimeTypes(int index, String value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setMimeTypes(index, value);
            return this;
        }

        public Builder addMimeTypes(String value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).addMimeTypes(value);
            return this;
        }

        public Builder addAllMimeTypes(Iterable<String> values) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).addAllMimeTypes(values);
            return this;
        }

        public Builder clearMimeTypes() {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).clearMimeTypes();
            return this;
        }

        public Builder addMimeTypesBytes(ByteString value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).addMimeTypesBytes(value);
            return this;
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public boolean hasLabel() {
            return ((ClipDescriptionProto) this.instance).hasLabel();
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public String getLabel() {
            return ((ClipDescriptionProto) this.instance).getLabel();
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public ByteString getLabelBytes() {
            return ((ClipDescriptionProto) this.instance).getLabelBytes();
        }

        public Builder setLabel(String value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setLabel(value);
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).clearLabel();
            return this;
        }

        public Builder setLabelBytes(ByteString value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setLabelBytes(value);
            return this;
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public boolean hasExtras() {
            return ((ClipDescriptionProto) this.instance).hasExtras();
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public PersistableBundleProto getExtras() {
            return ((ClipDescriptionProto) this.instance).getExtras();
        }

        public Builder setExtras(PersistableBundleProto value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setExtras((ClipDescriptionProto) value);
            return this;
        }

        public Builder setExtras(PersistableBundleProto.Builder builderForValue) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setExtras((ClipDescriptionProto) builderForValue);
            return this;
        }

        public Builder mergeExtras(PersistableBundleProto value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).mergeExtras(value);
            return this;
        }

        public Builder clearExtras() {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).clearExtras();
            return this;
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public boolean hasTimestampMs() {
            return ((ClipDescriptionProto) this.instance).hasTimestampMs();
        }

        @Override // android.content.ClipDescriptionProtoOrBuilder
        public long getTimestampMs() {
            return ((ClipDescriptionProto) this.instance).getTimestampMs();
        }

        public Builder setTimestampMs(long value) {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).setTimestampMs(value);
            return this;
        }

        public Builder clearTimestampMs() {
            copyOnWrite();
            ((ClipDescriptionProto) this.instance).clearTimestampMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ClipDescriptionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.mimeTypes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ClipDescriptionProto other = (ClipDescriptionProto) arg1;
                this.mimeTypes_ = visitor.visitList(this.mimeTypes_, other.mimeTypes_);
                this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                this.extras_ = (PersistableBundleProto) visitor.visitMessage(this.extras_, other.extras_);
                this.timestampMs_ = visitor.visitLong(hasTimestampMs(), this.timestampMs_, other.hasTimestampMs(), other.timestampMs_);
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
                            String s = input.readString();
                            if (!this.mimeTypes_.isModifiable()) {
                                this.mimeTypes_ = GeneratedMessageLite.mutableCopy(this.mimeTypes_);
                            }
                            this.mimeTypes_.add(s);
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 1;
                            this.label_ = s2;
                        } else if (tag == 26) {
                            PersistableBundleProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (PersistableBundleProto.Builder) this.extras_.toBuilder();
                            }
                            this.extras_ = (PersistableBundleProto) input.readMessage(PersistableBundleProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.extras_);
                                this.extras_ = (PersistableBundleProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.timestampMs_ = input.readInt64();
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
                    synchronized (ClipDescriptionProto.class) {
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

    public static ClipDescriptionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClipDescriptionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
