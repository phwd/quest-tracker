package android.media;

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

public final class AudioAttributesProto extends GeneratedMessageLite<AudioAttributesProto, Builder> implements AudioAttributesProtoOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
    private static final AudioAttributesProto DEFAULT_INSTANCE = new AudioAttributesProto();
    public static final int FLAGS_FIELD_NUMBER = 3;
    private static volatile Parser<AudioAttributesProto> PARSER = null;
    public static final int TAGS_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int contentType_ = 0;
    private int flags_ = 0;
    private Internal.ProtobufList<String> tags_ = GeneratedMessageLite.emptyProtobufList();
    private int usage_ = 0;

    private AudioAttributesProto() {
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public boolean hasUsage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public Usage getUsage() {
        Usage result = Usage.forNumber(this.usage_);
        return result == null ? Usage.USAGE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsage(Usage value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.usage_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsage() {
        this.bitField0_ &= -2;
        this.usage_ = 0;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public boolean hasContentType() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public ContentType getContentType() {
        ContentType result = ContentType.forNumber(this.contentType_);
        return result == null ? ContentType.CONTENT_TYPE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentType(ContentType value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.contentType_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentType() {
        this.bitField0_ &= -3;
        this.contentType_ = 0;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -5;
        this.flags_ = 0;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public List<String> getTagsList() {
        return this.tags_;
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public int getTagsCount() {
        return this.tags_.size();
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public String getTags(int index) {
        return this.tags_.get(index);
    }

    @Override // android.media.AudioAttributesProtoOrBuilder
    public ByteString getTagsBytes(int index) {
        return ByteString.copyFromUtf8(this.tags_.get(index));
    }

    private void ensureTagsIsMutable() {
        if (!this.tags_.isModifiable()) {
            this.tags_ = GeneratedMessageLite.mutableCopy(this.tags_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTags(int index, String value) {
        if (value != null) {
            ensureTagsIsMutable();
            this.tags_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTags(String value) {
        if (value != null) {
            ensureTagsIsMutable();
            this.tags_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTags(Iterable<String> values) {
        ensureTagsIsMutable();
        AbstractMessageLite.addAll(values, this.tags_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTags() {
        this.tags_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTagsBytes(ByteString value) {
        if (value != null) {
            ensureTagsIsMutable();
            this.tags_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.usage_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.contentType_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.flags_);
        }
        for (int i = 0; i < this.tags_.size(); i++) {
            output.writeString(4, this.tags_.get(i));
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.usage_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.contentType_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.flags_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.tags_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.tags_.get(i));
        }
        int size3 = size2 + dataSize + (getTagsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AudioAttributesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AudioAttributesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AudioAttributesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AudioAttributesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AudioAttributesProto parseFrom(InputStream input) throws IOException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AudioAttributesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AudioAttributesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AudioAttributesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AudioAttributesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AudioAttributesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AudioAttributesProto parseFrom(CodedInputStream input) throws IOException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AudioAttributesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AudioAttributesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AudioAttributesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AudioAttributesProto, Builder> implements AudioAttributesProtoOrBuilder {
        private Builder() {
            super(AudioAttributesProto.DEFAULT_INSTANCE);
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public boolean hasUsage() {
            return ((AudioAttributesProto) this.instance).hasUsage();
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public Usage getUsage() {
            return ((AudioAttributesProto) this.instance).getUsage();
        }

        public Builder setUsage(Usage value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).setUsage(value);
            return this;
        }

        public Builder clearUsage() {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).clearUsage();
            return this;
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public boolean hasContentType() {
            return ((AudioAttributesProto) this.instance).hasContentType();
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public ContentType getContentType() {
            return ((AudioAttributesProto) this.instance).getContentType();
        }

        public Builder setContentType(ContentType value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).setContentType(value);
            return this;
        }

        public Builder clearContentType() {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).clearContentType();
            return this;
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public boolean hasFlags() {
            return ((AudioAttributesProto) this.instance).hasFlags();
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public int getFlags() {
            return ((AudioAttributesProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).clearFlags();
            return this;
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public List<String> getTagsList() {
            return Collections.unmodifiableList(((AudioAttributesProto) this.instance).getTagsList());
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public int getTagsCount() {
            return ((AudioAttributesProto) this.instance).getTagsCount();
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public String getTags(int index) {
            return ((AudioAttributesProto) this.instance).getTags(index);
        }

        @Override // android.media.AudioAttributesProtoOrBuilder
        public ByteString getTagsBytes(int index) {
            return ((AudioAttributesProto) this.instance).getTagsBytes(index);
        }

        public Builder setTags(int index, String value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).setTags(index, value);
            return this;
        }

        public Builder addTags(String value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).addTags(value);
            return this;
        }

        public Builder addAllTags(Iterable<String> values) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).addAllTags(values);
            return this;
        }

        public Builder clearTags() {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).clearTags();
            return this;
        }

        public Builder addTagsBytes(ByteString value) {
            copyOnWrite();
            ((AudioAttributesProto) this.instance).addTagsBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AudioAttributesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.tags_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AudioAttributesProto other = (AudioAttributesProto) arg1;
                this.usage_ = visitor.visitInt(hasUsage(), this.usage_, other.hasUsage(), other.usage_);
                this.contentType_ = visitor.visitInt(hasContentType(), this.contentType_, other.hasContentType(), other.contentType_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.tags_ = visitor.visitList(this.tags_, other.tags_);
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
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (Usage.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.usage_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (ContentType.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.contentType_ = rawValue2;
                            }
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.flags_ = input.readInt32();
                        } else if (tag == 34) {
                            String s = input.readString();
                            if (!this.tags_.isModifiable()) {
                                this.tags_ = GeneratedMessageLite.mutableCopy(this.tags_);
                            }
                            this.tags_.add(s);
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
                    synchronized (AudioAttributesProto.class) {
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

    public static AudioAttributesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AudioAttributesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
