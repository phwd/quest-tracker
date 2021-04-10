package com.android.server;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class IntentResolverProto extends GeneratedMessageLite<IntentResolverProto, Builder> implements IntentResolverProtoOrBuilder {
    public static final int BASE_MIME_TYPES_FIELD_NUMBER = 2;
    private static final IntentResolverProto DEFAULT_INSTANCE = new IntentResolverProto();
    public static final int FULL_MIME_TYPES_FIELD_NUMBER = 1;
    public static final int MIME_TYPED_ACTIONS_FIELD_NUMBER = 6;
    public static final int NON_DATA_ACTIONS_FIELD_NUMBER = 5;
    private static volatile Parser<IntentResolverProto> PARSER = null;
    public static final int SCHEMES_FIELD_NUMBER = 4;
    public static final int WILD_MIME_TYPES_FIELD_NUMBER = 3;
    private Internal.ProtobufList<ArrayMapEntry> baseMimeTypes_ = emptyProtobufList();
    private Internal.ProtobufList<ArrayMapEntry> fullMimeTypes_ = emptyProtobufList();
    private Internal.ProtobufList<ArrayMapEntry> mimeTypedActions_ = emptyProtobufList();
    private Internal.ProtobufList<ArrayMapEntry> nonDataActions_ = emptyProtobufList();
    private Internal.ProtobufList<ArrayMapEntry> schemes_ = emptyProtobufList();
    private Internal.ProtobufList<ArrayMapEntry> wildMimeTypes_ = emptyProtobufList();

    public interface ArrayMapEntryOrBuilder extends MessageLiteOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        String getValues(int i);

        ByteString getValuesBytes(int i);

        int getValuesCount();

        List<String> getValuesList();

        boolean hasKey();
    }

    private IntentResolverProto() {
    }

    public static final class ArrayMapEntry extends GeneratedMessageLite<ArrayMapEntry, Builder> implements ArrayMapEntryOrBuilder {
        private static final ArrayMapEntry DEFAULT_INSTANCE = new ArrayMapEntry();
        public static final int KEY_FIELD_NUMBER = 1;
        private static volatile Parser<ArrayMapEntry> PARSER = null;
        public static final int VALUES_FIELD_NUMBER = 2;
        private int bitField0_;
        private String key_ = "";
        private Internal.ProtobufList<String> values_ = GeneratedMessageLite.emptyProtobufList();

        private ArrayMapEntry() {
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public String getKey() {
            return this.key_;
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public ByteString getKeyBytes() {
            return ByteString.copyFromUtf8(this.key_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setKey(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.key_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearKey() {
            this.bitField0_ &= -2;
            this.key_ = getDefaultInstance().getKey();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setKeyBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.key_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public List<String> getValuesList() {
            return this.values_;
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public int getValuesCount() {
            return this.values_.size();
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public String getValues(int index) {
            return this.values_.get(index);
        }

        @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
        public ByteString getValuesBytes(int index) {
            return ByteString.copyFromUtf8(this.values_.get(index));
        }

        private void ensureValuesIsMutable() {
            if (!this.values_.isModifiable()) {
                this.values_ = GeneratedMessageLite.mutableCopy(this.values_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValues(int index, String value) {
            if (value != null) {
                ensureValuesIsMutable();
                this.values_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValues(String value) {
            if (value != null) {
                ensureValuesIsMutable();
                this.values_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllValues(Iterable<String> values) {
            ensureValuesIsMutable();
            AbstractMessageLite.addAll(values, this.values_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValues() {
            this.values_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addValuesBytes(ByteString value) {
            if (value != null) {
                ensureValuesIsMutable();
                this.values_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getKey());
            }
            for (int i = 0; i < this.values_.size(); i++) {
                output.writeString(2, this.values_.get(i));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getKey());
            }
            int dataSize = 0;
            for (int i = 0; i < this.values_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.values_.get(i));
            }
            int size3 = size2 + dataSize + (getValuesList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ArrayMapEntry parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ArrayMapEntry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ArrayMapEntry parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ArrayMapEntry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ArrayMapEntry parseFrom(InputStream input) throws IOException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ArrayMapEntry parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ArrayMapEntry parseDelimitedFrom(InputStream input) throws IOException {
            return (ArrayMapEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ArrayMapEntry parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ArrayMapEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ArrayMapEntry parseFrom(CodedInputStream input) throws IOException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ArrayMapEntry parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ArrayMapEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ArrayMapEntry prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ArrayMapEntry, Builder> implements ArrayMapEntryOrBuilder {
            private Builder() {
                super(ArrayMapEntry.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public boolean hasKey() {
                return ((ArrayMapEntry) this.instance).hasKey();
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public String getKey() {
                return ((ArrayMapEntry) this.instance).getKey();
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public ByteString getKeyBytes() {
                return ((ArrayMapEntry) this.instance).getKeyBytes();
            }

            public Builder setKey(String value) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).setKey(value);
                return this;
            }

            public Builder clearKey() {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).clearKey();
                return this;
            }

            public Builder setKeyBytes(ByteString value) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).setKeyBytes(value);
                return this;
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public List<String> getValuesList() {
                return Collections.unmodifiableList(((ArrayMapEntry) this.instance).getValuesList());
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public int getValuesCount() {
                return ((ArrayMapEntry) this.instance).getValuesCount();
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public String getValues(int index) {
                return ((ArrayMapEntry) this.instance).getValues(index);
            }

            @Override // com.android.server.IntentResolverProto.ArrayMapEntryOrBuilder
            public ByteString getValuesBytes(int index) {
                return ((ArrayMapEntry) this.instance).getValuesBytes(index);
            }

            public Builder setValues(int index, String value) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).setValues(index, value);
                return this;
            }

            public Builder addValues(String value) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).addValues(value);
                return this;
            }

            public Builder addAllValues(Iterable<String> values) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).addAllValues(values);
                return this;
            }

            public Builder clearValues() {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).clearValues();
                return this;
            }

            public Builder addValuesBytes(ByteString value) {
                copyOnWrite();
                ((ArrayMapEntry) this.instance).addValuesBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ArrayMapEntry();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.values_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ArrayMapEntry other = (ArrayMapEntry) arg1;
                    this.key_ = visitor.visitString(hasKey(), this.key_, other.hasKey(), other.key_);
                    this.values_ = visitor.visitList(this.values_, other.values_);
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
                                this.key_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                if (!this.values_.isModifiable()) {
                                    this.values_ = GeneratedMessageLite.mutableCopy(this.values_);
                                }
                                this.values_.add(s2);
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
                        synchronized (ArrayMapEntry.class) {
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

        public static ArrayMapEntry getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ArrayMapEntry> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getFullMimeTypesList() {
        return this.fullMimeTypes_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getFullMimeTypesOrBuilderList() {
        return this.fullMimeTypes_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getFullMimeTypesCount() {
        return this.fullMimeTypes_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getFullMimeTypes(int index) {
        return this.fullMimeTypes_.get(index);
    }

    public ArrayMapEntryOrBuilder getFullMimeTypesOrBuilder(int index) {
        return this.fullMimeTypes_.get(index);
    }

    private void ensureFullMimeTypesIsMutable() {
        if (!this.fullMimeTypes_.isModifiable()) {
            this.fullMimeTypes_ = GeneratedMessageLite.mutableCopy(this.fullMimeTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureFullMimeTypesIsMutable();
            this.fullMimeTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureFullMimeTypesIsMutable();
        this.fullMimeTypes_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFullMimeTypes(ArrayMapEntry value) {
        if (value != null) {
            ensureFullMimeTypesIsMutable();
            this.fullMimeTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFullMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureFullMimeTypesIsMutable();
            this.fullMimeTypes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFullMimeTypes(ArrayMapEntry.Builder builderForValue) {
        ensureFullMimeTypesIsMutable();
        this.fullMimeTypes_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFullMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureFullMimeTypesIsMutable();
        this.fullMimeTypes_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFullMimeTypes(Iterable<? extends ArrayMapEntry> values) {
        ensureFullMimeTypesIsMutable();
        AbstractMessageLite.addAll(values, this.fullMimeTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFullMimeTypes() {
        this.fullMimeTypes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFullMimeTypes(int index) {
        ensureFullMimeTypesIsMutable();
        this.fullMimeTypes_.remove(index);
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getBaseMimeTypesList() {
        return this.baseMimeTypes_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getBaseMimeTypesOrBuilderList() {
        return this.baseMimeTypes_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getBaseMimeTypesCount() {
        return this.baseMimeTypes_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getBaseMimeTypes(int index) {
        return this.baseMimeTypes_.get(index);
    }

    public ArrayMapEntryOrBuilder getBaseMimeTypesOrBuilder(int index) {
        return this.baseMimeTypes_.get(index);
    }

    private void ensureBaseMimeTypesIsMutable() {
        if (!this.baseMimeTypes_.isModifiable()) {
            this.baseMimeTypes_ = GeneratedMessageLite.mutableCopy(this.baseMimeTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBaseMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureBaseMimeTypesIsMutable();
            this.baseMimeTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBaseMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureBaseMimeTypesIsMutable();
        this.baseMimeTypes_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBaseMimeTypes(ArrayMapEntry value) {
        if (value != null) {
            ensureBaseMimeTypesIsMutable();
            this.baseMimeTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBaseMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureBaseMimeTypesIsMutable();
            this.baseMimeTypes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBaseMimeTypes(ArrayMapEntry.Builder builderForValue) {
        ensureBaseMimeTypesIsMutable();
        this.baseMimeTypes_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBaseMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureBaseMimeTypesIsMutable();
        this.baseMimeTypes_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBaseMimeTypes(Iterable<? extends ArrayMapEntry> values) {
        ensureBaseMimeTypesIsMutable();
        AbstractMessageLite.addAll(values, this.baseMimeTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBaseMimeTypes() {
        this.baseMimeTypes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBaseMimeTypes(int index) {
        ensureBaseMimeTypesIsMutable();
        this.baseMimeTypes_.remove(index);
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getWildMimeTypesList() {
        return this.wildMimeTypes_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getWildMimeTypesOrBuilderList() {
        return this.wildMimeTypes_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getWildMimeTypesCount() {
        return this.wildMimeTypes_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getWildMimeTypes(int index) {
        return this.wildMimeTypes_.get(index);
    }

    public ArrayMapEntryOrBuilder getWildMimeTypesOrBuilder(int index) {
        return this.wildMimeTypes_.get(index);
    }

    private void ensureWildMimeTypesIsMutable() {
        if (!this.wildMimeTypes_.isModifiable()) {
            this.wildMimeTypes_ = GeneratedMessageLite.mutableCopy(this.wildMimeTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWildMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureWildMimeTypesIsMutable();
            this.wildMimeTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWildMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureWildMimeTypesIsMutable();
        this.wildMimeTypes_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWildMimeTypes(ArrayMapEntry value) {
        if (value != null) {
            ensureWildMimeTypesIsMutable();
            this.wildMimeTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWildMimeTypes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureWildMimeTypesIsMutable();
            this.wildMimeTypes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWildMimeTypes(ArrayMapEntry.Builder builderForValue) {
        ensureWildMimeTypesIsMutable();
        this.wildMimeTypes_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWildMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureWildMimeTypesIsMutable();
        this.wildMimeTypes_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWildMimeTypes(Iterable<? extends ArrayMapEntry> values) {
        ensureWildMimeTypesIsMutable();
        AbstractMessageLite.addAll(values, this.wildMimeTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWildMimeTypes() {
        this.wildMimeTypes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWildMimeTypes(int index) {
        ensureWildMimeTypesIsMutable();
        this.wildMimeTypes_.remove(index);
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getSchemesList() {
        return this.schemes_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getSchemesOrBuilderList() {
        return this.schemes_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getSchemesCount() {
        return this.schemes_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getSchemes(int index) {
        return this.schemes_.get(index);
    }

    public ArrayMapEntryOrBuilder getSchemesOrBuilder(int index) {
        return this.schemes_.get(index);
    }

    private void ensureSchemesIsMutable() {
        if (!this.schemes_.isModifiable()) {
            this.schemes_ = GeneratedMessageLite.mutableCopy(this.schemes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSchemes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureSchemesIsMutable();
            this.schemes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSchemes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureSchemesIsMutable();
        this.schemes_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSchemes(ArrayMapEntry value) {
        if (value != null) {
            ensureSchemesIsMutable();
            this.schemes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSchemes(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureSchemesIsMutable();
            this.schemes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSchemes(ArrayMapEntry.Builder builderForValue) {
        ensureSchemesIsMutable();
        this.schemes_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSchemes(int index, ArrayMapEntry.Builder builderForValue) {
        ensureSchemesIsMutable();
        this.schemes_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSchemes(Iterable<? extends ArrayMapEntry> values) {
        ensureSchemesIsMutable();
        AbstractMessageLite.addAll(values, this.schemes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSchemes() {
        this.schemes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSchemes(int index) {
        ensureSchemesIsMutable();
        this.schemes_.remove(index);
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getNonDataActionsList() {
        return this.nonDataActions_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getNonDataActionsOrBuilderList() {
        return this.nonDataActions_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getNonDataActionsCount() {
        return this.nonDataActions_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getNonDataActions(int index) {
        return this.nonDataActions_.get(index);
    }

    public ArrayMapEntryOrBuilder getNonDataActionsOrBuilder(int index) {
        return this.nonDataActions_.get(index);
    }

    private void ensureNonDataActionsIsMutable() {
        if (!this.nonDataActions_.isModifiable()) {
            this.nonDataActions_ = GeneratedMessageLite.mutableCopy(this.nonDataActions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonDataActions(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureNonDataActionsIsMutable();
            this.nonDataActions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonDataActions(int index, ArrayMapEntry.Builder builderForValue) {
        ensureNonDataActionsIsMutable();
        this.nonDataActions_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNonDataActions(ArrayMapEntry value) {
        if (value != null) {
            ensureNonDataActionsIsMutable();
            this.nonDataActions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNonDataActions(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureNonDataActionsIsMutable();
            this.nonDataActions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNonDataActions(ArrayMapEntry.Builder builderForValue) {
        ensureNonDataActionsIsMutable();
        this.nonDataActions_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNonDataActions(int index, ArrayMapEntry.Builder builderForValue) {
        ensureNonDataActionsIsMutable();
        this.nonDataActions_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllNonDataActions(Iterable<? extends ArrayMapEntry> values) {
        ensureNonDataActionsIsMutable();
        AbstractMessageLite.addAll(values, this.nonDataActions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNonDataActions() {
        this.nonDataActions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeNonDataActions(int index) {
        ensureNonDataActionsIsMutable();
        this.nonDataActions_.remove(index);
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public List<ArrayMapEntry> getMimeTypedActionsList() {
        return this.mimeTypedActions_;
    }

    public List<? extends ArrayMapEntryOrBuilder> getMimeTypedActionsOrBuilderList() {
        return this.mimeTypedActions_;
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public int getMimeTypedActionsCount() {
        return this.mimeTypedActions_.size();
    }

    @Override // com.android.server.IntentResolverProtoOrBuilder
    public ArrayMapEntry getMimeTypedActions(int index) {
        return this.mimeTypedActions_.get(index);
    }

    public ArrayMapEntryOrBuilder getMimeTypedActionsOrBuilder(int index) {
        return this.mimeTypedActions_.get(index);
    }

    private void ensureMimeTypedActionsIsMutable() {
        if (!this.mimeTypedActions_.isModifiable()) {
            this.mimeTypedActions_ = GeneratedMessageLite.mutableCopy(this.mimeTypedActions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMimeTypedActions(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureMimeTypedActionsIsMutable();
            this.mimeTypedActions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMimeTypedActions(int index, ArrayMapEntry.Builder builderForValue) {
        ensureMimeTypedActionsIsMutable();
        this.mimeTypedActions_.set(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypedActions(ArrayMapEntry value) {
        if (value != null) {
            ensureMimeTypedActionsIsMutable();
            this.mimeTypedActions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypedActions(int index, ArrayMapEntry value) {
        if (value != null) {
            ensureMimeTypedActionsIsMutable();
            this.mimeTypedActions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypedActions(ArrayMapEntry.Builder builderForValue) {
        ensureMimeTypedActionsIsMutable();
        this.mimeTypedActions_.add((ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMimeTypedActions(int index, ArrayMapEntry.Builder builderForValue) {
        ensureMimeTypedActionsIsMutable();
        this.mimeTypedActions_.add(index, (ArrayMapEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMimeTypedActions(Iterable<? extends ArrayMapEntry> values) {
        ensureMimeTypedActionsIsMutable();
        AbstractMessageLite.addAll(values, this.mimeTypedActions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMimeTypedActions() {
        this.mimeTypedActions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeMimeTypedActions(int index) {
        ensureMimeTypedActionsIsMutable();
        this.mimeTypedActions_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.fullMimeTypes_.size(); i++) {
            output.writeMessage(1, this.fullMimeTypes_.get(i));
        }
        for (int i2 = 0; i2 < this.baseMimeTypes_.size(); i2++) {
            output.writeMessage(2, this.baseMimeTypes_.get(i2));
        }
        for (int i3 = 0; i3 < this.wildMimeTypes_.size(); i3++) {
            output.writeMessage(3, this.wildMimeTypes_.get(i3));
        }
        for (int i4 = 0; i4 < this.schemes_.size(); i4++) {
            output.writeMessage(4, this.schemes_.get(i4));
        }
        for (int i5 = 0; i5 < this.nonDataActions_.size(); i5++) {
            output.writeMessage(5, this.nonDataActions_.get(i5));
        }
        for (int i6 = 0; i6 < this.mimeTypedActions_.size(); i6++) {
            output.writeMessage(6, this.mimeTypedActions_.get(i6));
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
        for (int i = 0; i < this.fullMimeTypes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.fullMimeTypes_.get(i));
        }
        for (int i2 = 0; i2 < this.baseMimeTypes_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.baseMimeTypes_.get(i2));
        }
        for (int i3 = 0; i3 < this.wildMimeTypes_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.wildMimeTypes_.get(i3));
        }
        for (int i4 = 0; i4 < this.schemes_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.schemes_.get(i4));
        }
        for (int i5 = 0; i5 < this.nonDataActions_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.nonDataActions_.get(i5));
        }
        for (int i6 = 0; i6 < this.mimeTypedActions_.size(); i6++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.mimeTypedActions_.get(i6));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IntentResolverProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentResolverProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentResolverProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntentResolverProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntentResolverProto parseFrom(InputStream input) throws IOException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentResolverProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentResolverProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IntentResolverProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentResolverProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentResolverProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntentResolverProto parseFrom(CodedInputStream input) throws IOException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntentResolverProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntentResolverProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IntentResolverProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IntentResolverProto, Builder> implements IntentResolverProtoOrBuilder {
        private Builder() {
            super(IntentResolverProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getFullMimeTypesList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getFullMimeTypesList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getFullMimeTypesCount() {
            return ((IntentResolverProto) this.instance).getFullMimeTypesCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getFullMimeTypes(int index) {
            return ((IntentResolverProto) this.instance).getFullMimeTypes(index);
        }

        public Builder setFullMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setFullMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setFullMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setFullMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addFullMimeTypes(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addFullMimeTypes((IntentResolverProto) value);
            return this;
        }

        public Builder addFullMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addFullMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addFullMimeTypes(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addFullMimeTypes((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addFullMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addFullMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllFullMimeTypes(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllFullMimeTypes(values);
            return this;
        }

        public Builder clearFullMimeTypes() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearFullMimeTypes();
            return this;
        }

        public Builder removeFullMimeTypes(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeFullMimeTypes(index);
            return this;
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getBaseMimeTypesList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getBaseMimeTypesList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getBaseMimeTypesCount() {
            return ((IntentResolverProto) this.instance).getBaseMimeTypesCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getBaseMimeTypes(int index) {
            return ((IntentResolverProto) this.instance).getBaseMimeTypes(index);
        }

        public Builder setBaseMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setBaseMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setBaseMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setBaseMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBaseMimeTypes(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addBaseMimeTypes((IntentResolverProto) value);
            return this;
        }

        public Builder addBaseMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addBaseMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addBaseMimeTypes(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addBaseMimeTypes((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addBaseMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addBaseMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBaseMimeTypes(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllBaseMimeTypes(values);
            return this;
        }

        public Builder clearBaseMimeTypes() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearBaseMimeTypes();
            return this;
        }

        public Builder removeBaseMimeTypes(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeBaseMimeTypes(index);
            return this;
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getWildMimeTypesList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getWildMimeTypesList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getWildMimeTypesCount() {
            return ((IntentResolverProto) this.instance).getWildMimeTypesCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getWildMimeTypes(int index) {
            return ((IntentResolverProto) this.instance).getWildMimeTypes(index);
        }

        public Builder setWildMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setWildMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setWildMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setWildMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWildMimeTypes(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addWildMimeTypes((IntentResolverProto) value);
            return this;
        }

        public Builder addWildMimeTypes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addWildMimeTypes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addWildMimeTypes(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addWildMimeTypes((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addWildMimeTypes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addWildMimeTypes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWildMimeTypes(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllWildMimeTypes(values);
            return this;
        }

        public Builder clearWildMimeTypes() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearWildMimeTypes();
            return this;
        }

        public Builder removeWildMimeTypes(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeWildMimeTypes(index);
            return this;
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getSchemesList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getSchemesList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getSchemesCount() {
            return ((IntentResolverProto) this.instance).getSchemesCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getSchemes(int index) {
            return ((IntentResolverProto) this.instance).getSchemes(index);
        }

        public Builder setSchemes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setSchemes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setSchemes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setSchemes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSchemes(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addSchemes((IntentResolverProto) value);
            return this;
        }

        public Builder addSchemes(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addSchemes((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addSchemes(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addSchemes((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addSchemes(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addSchemes((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSchemes(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllSchemes(values);
            return this;
        }

        public Builder clearSchemes() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearSchemes();
            return this;
        }

        public Builder removeSchemes(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeSchemes(index);
            return this;
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getNonDataActionsList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getNonDataActionsList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getNonDataActionsCount() {
            return ((IntentResolverProto) this.instance).getNonDataActionsCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getNonDataActions(int index) {
            return ((IntentResolverProto) this.instance).getNonDataActions(index);
        }

        public Builder setNonDataActions(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setNonDataActions((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setNonDataActions(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setNonDataActions((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addNonDataActions(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addNonDataActions((IntentResolverProto) value);
            return this;
        }

        public Builder addNonDataActions(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addNonDataActions((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addNonDataActions(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addNonDataActions((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addNonDataActions(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addNonDataActions((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllNonDataActions(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllNonDataActions(values);
            return this;
        }

        public Builder clearNonDataActions() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearNonDataActions();
            return this;
        }

        public Builder removeNonDataActions(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeNonDataActions(index);
            return this;
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public List<ArrayMapEntry> getMimeTypedActionsList() {
            return Collections.unmodifiableList(((IntentResolverProto) this.instance).getMimeTypedActionsList());
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public int getMimeTypedActionsCount() {
            return ((IntentResolverProto) this.instance).getMimeTypedActionsCount();
        }

        @Override // com.android.server.IntentResolverProtoOrBuilder
        public ArrayMapEntry getMimeTypedActions(int index) {
            return ((IntentResolverProto) this.instance).getMimeTypedActions(index);
        }

        public Builder setMimeTypedActions(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setMimeTypedActions((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder setMimeTypedActions(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).setMimeTypedActions((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addMimeTypedActions(ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addMimeTypedActions((IntentResolverProto) value);
            return this;
        }

        public Builder addMimeTypedActions(int index, ArrayMapEntry value) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addMimeTypedActions((IntentResolverProto) index, (int) value);
            return this;
        }

        public Builder addMimeTypedActions(ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addMimeTypedActions((IntentResolverProto) builderForValue);
            return this;
        }

        public Builder addMimeTypedActions(int index, ArrayMapEntry.Builder builderForValue) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addMimeTypedActions((IntentResolverProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllMimeTypedActions(Iterable<? extends ArrayMapEntry> values) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).addAllMimeTypedActions(values);
            return this;
        }

        public Builder clearMimeTypedActions() {
            copyOnWrite();
            ((IntentResolverProto) this.instance).clearMimeTypedActions();
            return this;
        }

        public Builder removeMimeTypedActions(int index) {
            copyOnWrite();
            ((IntentResolverProto) this.instance).removeMimeTypedActions(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IntentResolverProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.fullMimeTypes_.makeImmutable();
                this.baseMimeTypes_.makeImmutable();
                this.wildMimeTypes_.makeImmutable();
                this.schemes_.makeImmutable();
                this.nonDataActions_.makeImmutable();
                this.mimeTypedActions_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IntentResolverProto other = (IntentResolverProto) arg1;
                this.fullMimeTypes_ = visitor.visitList(this.fullMimeTypes_, other.fullMimeTypes_);
                this.baseMimeTypes_ = visitor.visitList(this.baseMimeTypes_, other.baseMimeTypes_);
                this.wildMimeTypes_ = visitor.visitList(this.wildMimeTypes_, other.wildMimeTypes_);
                this.schemes_ = visitor.visitList(this.schemes_, other.schemes_);
                this.nonDataActions_ = visitor.visitList(this.nonDataActions_, other.nonDataActions_);
                this.mimeTypedActions_ = visitor.visitList(this.mimeTypedActions_, other.mimeTypedActions_);
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
                            if (!this.fullMimeTypes_.isModifiable()) {
                                this.fullMimeTypes_ = GeneratedMessageLite.mutableCopy(this.fullMimeTypes_);
                            }
                            this.fullMimeTypes_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.baseMimeTypes_.isModifiable()) {
                                this.baseMimeTypes_ = GeneratedMessageLite.mutableCopy(this.baseMimeTypes_);
                            }
                            this.baseMimeTypes_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.wildMimeTypes_.isModifiable()) {
                                this.wildMimeTypes_ = GeneratedMessageLite.mutableCopy(this.wildMimeTypes_);
                            }
                            this.wildMimeTypes_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.schemes_.isModifiable()) {
                                this.schemes_ = GeneratedMessageLite.mutableCopy(this.schemes_);
                            }
                            this.schemes_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.nonDataActions_.isModifiable()) {
                                this.nonDataActions_ = GeneratedMessageLite.mutableCopy(this.nonDataActions_);
                            }
                            this.nonDataActions_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.mimeTypedActions_.isModifiable()) {
                                this.mimeTypedActions_ = GeneratedMessageLite.mutableCopy(this.mimeTypedActions_);
                            }
                            this.mimeTypedActions_.add((ArrayMapEntry) input.readMessage(ArrayMapEntry.parser(), extensionRegistry));
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
                    synchronized (IntentResolverProto.class) {
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

    public static IntentResolverProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IntentResolverProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
