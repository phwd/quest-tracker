package android.service.restricted_image;

import android.service.restricted_image.RestrictedImageSetProto;
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

public final class RestrictedImagesDumpProto extends GeneratedMessageLite<RestrictedImagesDumpProto, Builder> implements RestrictedImagesDumpProtoOrBuilder {
    private static final RestrictedImagesDumpProto DEFAULT_INSTANCE = new RestrictedImagesDumpProto();
    private static volatile Parser<RestrictedImagesDumpProto> PARSER = null;
    public static final int SETS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<RestrictedImageSetProto> sets_ = emptyProtobufList();

    private RestrictedImagesDumpProto() {
    }

    @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
    public List<RestrictedImageSetProto> getSetsList() {
        return this.sets_;
    }

    public List<? extends RestrictedImageSetProtoOrBuilder> getSetsOrBuilderList() {
        return this.sets_;
    }

    @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
    public int getSetsCount() {
        return this.sets_.size();
    }

    @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
    public RestrictedImageSetProto getSets(int index) {
        return this.sets_.get(index);
    }

    public RestrictedImageSetProtoOrBuilder getSetsOrBuilder(int index) {
        return this.sets_.get(index);
    }

    private void ensureSetsIsMutable() {
        if (!this.sets_.isModifiable()) {
            this.sets_ = GeneratedMessageLite.mutableCopy(this.sets_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSets(int index, RestrictedImageSetProto value) {
        if (value != null) {
            ensureSetsIsMutable();
            this.sets_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSets(int index, RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.set(index, (RestrictedImageSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSets(RestrictedImageSetProto value) {
        if (value != null) {
            ensureSetsIsMutable();
            this.sets_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSets(int index, RestrictedImageSetProto value) {
        if (value != null) {
            ensureSetsIsMutable();
            this.sets_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSets(RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.add((RestrictedImageSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSets(int index, RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.add(index, (RestrictedImageSetProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSets(Iterable<? extends RestrictedImageSetProto> values) {
        ensureSetsIsMutable();
        AbstractMessageLite.addAll(values, this.sets_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSets() {
        this.sets_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSets(int index) {
        ensureSetsIsMutable();
        this.sets_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.sets_.size(); i++) {
            output.writeMessage(1, this.sets_.get(i));
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
        for (int i = 0; i < this.sets_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.sets_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RestrictedImagesDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImagesDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImagesDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImagesDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RestrictedImagesDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImagesDumpProto, Builder> implements RestrictedImagesDumpProtoOrBuilder {
        private Builder() {
            super(RestrictedImagesDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
        public List<RestrictedImageSetProto> getSetsList() {
            return Collections.unmodifiableList(((RestrictedImagesDumpProto) this.instance).getSetsList());
        }

        @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
        public int getSetsCount() {
            return ((RestrictedImagesDumpProto) this.instance).getSetsCount();
        }

        @Override // android.service.restricted_image.RestrictedImagesDumpProtoOrBuilder
        public RestrictedImageSetProto getSets(int index) {
            return ((RestrictedImagesDumpProto) this.instance).getSets(index);
        }

        public Builder setSets(int index, RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).setSets((RestrictedImagesDumpProto) index, (int) value);
            return this;
        }

        public Builder setSets(int index, RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).setSets((RestrictedImagesDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSets(RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets((RestrictedImagesDumpProto) value);
            return this;
        }

        public Builder addSets(int index, RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets((RestrictedImagesDumpProto) index, (int) value);
            return this;
        }

        public Builder addSets(RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets((RestrictedImagesDumpProto) builderForValue);
            return this;
        }

        public Builder addSets(int index, RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets((RestrictedImagesDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSets(Iterable<? extends RestrictedImageSetProto> values) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addAllSets(values);
            return this;
        }

        public Builder clearSets() {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).clearSets();
            return this;
        }

        public Builder removeSets(int index) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).removeSets(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RestrictedImagesDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.sets_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.sets_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.sets_, ((RestrictedImagesDumpProto) arg1).sets_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.sets_.isModifiable()) {
                                this.sets_ = GeneratedMessageLite.mutableCopy(this.sets_);
                            }
                            this.sets_.add((RestrictedImageSetProto) input.readMessage(RestrictedImageSetProto.parser(), extensionRegistry));
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
                    synchronized (RestrictedImagesDumpProto.class) {
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

    public static RestrictedImagesDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImagesDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
