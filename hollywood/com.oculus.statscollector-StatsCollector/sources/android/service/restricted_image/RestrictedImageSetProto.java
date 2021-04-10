package android.service.restricted_image;

import android.service.restricted_image.RestrictedImageProto;
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

public final class RestrictedImageSetProto extends GeneratedMessageLite<RestrictedImageSetProto, Builder> implements RestrictedImageSetProtoOrBuilder {
    public static final int CATEGORY_FIELD_NUMBER = 1;
    private static final RestrictedImageSetProto DEFAULT_INSTANCE = new RestrictedImageSetProto();
    public static final int IMAGES_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    private static volatile Parser<RestrictedImageSetProto> PARSER;
    private int bitField0_;
    private String category_ = "";
    private Internal.ProtobufList<RestrictedImageProto> images_ = emptyProtobufList();
    private ByteString metadata_ = ByteString.EMPTY;

    private RestrictedImageSetProto() {
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public boolean hasCategory() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public String getCategory() {
        return this.category_;
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public ByteString getCategoryBytes() {
        return ByteString.copyFromUtf8(this.category_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategory(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.category_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCategory() {
        this.bitField0_ &= -2;
        this.category_ = getDefaultInstance().getCategory();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCategoryBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.category_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public List<RestrictedImageProto> getImagesList() {
        return this.images_;
    }

    public List<? extends RestrictedImageProtoOrBuilder> getImagesOrBuilderList() {
        return this.images_;
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public int getImagesCount() {
        return this.images_.size();
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public RestrictedImageProto getImages(int index) {
        return this.images_.get(index);
    }

    public RestrictedImageProtoOrBuilder getImagesOrBuilder(int index) {
        return this.images_.get(index);
    }

    private void ensureImagesIsMutable() {
        if (!this.images_.isModifiable()) {
            this.images_ = GeneratedMessageLite.mutableCopy(this.images_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImages(int index, RestrictedImageProto value) {
        if (value != null) {
            ensureImagesIsMutable();
            this.images_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImages(int index, RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.set(index, (RestrictedImageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImages(RestrictedImageProto value) {
        if (value != null) {
            ensureImagesIsMutable();
            this.images_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImages(int index, RestrictedImageProto value) {
        if (value != null) {
            ensureImagesIsMutable();
            this.images_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImages(RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.add((RestrictedImageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImages(int index, RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.add(index, (RestrictedImageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllImages(Iterable<? extends RestrictedImageProto> values) {
        ensureImagesIsMutable();
        AbstractMessageLite.addAll(values, this.images_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImages() {
        this.images_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeImages(int index) {
        ensureImagesIsMutable();
        this.images_.remove(index);
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public boolean hasMetadata() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
    public ByteString getMetadata() {
        return this.metadata_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMetadata(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.metadata_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMetadata() {
        this.bitField0_ &= -3;
        this.metadata_ = getDefaultInstance().getMetadata();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getCategory());
        }
        for (int i = 0; i < this.images_.size(); i++) {
            output.writeMessage(2, this.images_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBytes(3, this.metadata_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getCategory());
        }
        for (int i = 0; i < this.images_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.images_.get(i));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBytesSize(3, this.metadata_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RestrictedImageSetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageSetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageSetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageSetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImageSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RestrictedImageSetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImageSetProto, Builder> implements RestrictedImageSetProtoOrBuilder {
        private Builder() {
            super(RestrictedImageSetProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public boolean hasCategory() {
            return ((RestrictedImageSetProto) this.instance).hasCategory();
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public String getCategory() {
            return ((RestrictedImageSetProto) this.instance).getCategory();
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public ByteString getCategoryBytes() {
            return ((RestrictedImageSetProto) this.instance).getCategoryBytes();
        }

        public Builder setCategory(String value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setCategory(value);
            return this;
        }

        public Builder clearCategory() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearCategory();
            return this;
        }

        public Builder setCategoryBytes(ByteString value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setCategoryBytes(value);
            return this;
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public List<RestrictedImageProto> getImagesList() {
            return Collections.unmodifiableList(((RestrictedImageSetProto) this.instance).getImagesList());
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public int getImagesCount() {
            return ((RestrictedImageSetProto) this.instance).getImagesCount();
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public RestrictedImageProto getImages(int index) {
            return ((RestrictedImageSetProto) this.instance).getImages(index);
        }

        public Builder setImages(int index, RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setImages((RestrictedImageSetProto) index, (int) value);
            return this;
        }

        public Builder setImages(int index, RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setImages((RestrictedImageSetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addImages(RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages((RestrictedImageSetProto) value);
            return this;
        }

        public Builder addImages(int index, RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages((RestrictedImageSetProto) index, (int) value);
            return this;
        }

        public Builder addImages(RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages((RestrictedImageSetProto) builderForValue);
            return this;
        }

        public Builder addImages(int index, RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages((RestrictedImageSetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllImages(Iterable<? extends RestrictedImageProto> values) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addAllImages(values);
            return this;
        }

        public Builder clearImages() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearImages();
            return this;
        }

        public Builder removeImages(int index) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).removeImages(index);
            return this;
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public boolean hasMetadata() {
            return ((RestrictedImageSetProto) this.instance).hasMetadata();
        }

        @Override // android.service.restricted_image.RestrictedImageSetProtoOrBuilder
        public ByteString getMetadata() {
            return ((RestrictedImageSetProto) this.instance).getMetadata();
        }

        public Builder setMetadata(ByteString value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearMetadata();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RestrictedImageSetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.images_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RestrictedImageSetProto other = (RestrictedImageSetProto) arg1;
                this.category_ = visitor.visitString(hasCategory(), this.category_, other.hasCategory(), other.category_);
                this.images_ = visitor.visitList(this.images_, other.images_);
                this.metadata_ = visitor.visitByteString(hasMetadata(), this.metadata_, other.hasMetadata(), other.metadata_);
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
                            this.bitField0_ |= 1;
                            this.category_ = s;
                        } else if (tag == 18) {
                            if (!this.images_.isModifiable()) {
                                this.images_ = GeneratedMessageLite.mutableCopy(this.images_);
                            }
                            this.images_.add((RestrictedImageProto) input.readMessage(RestrictedImageProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            this.bitField0_ |= 2;
                            this.metadata_ = input.readBytes();
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
                    synchronized (RestrictedImageSetProto.class) {
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

    public static RestrictedImageSetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImageSetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
