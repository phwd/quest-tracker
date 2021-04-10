package android.util;

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

public final class EventLogTag extends GeneratedMessageLite<EventLogTag, Builder> implements EventLogTagOrBuilder {
    private static final EventLogTag DEFAULT_INSTANCE = new EventLogTag();
    private static volatile Parser<EventLogTag> PARSER = null;
    public static final int TAG_NAME_FIELD_NUMBER = 2;
    public static final int TAG_NUMBER_FIELD_NUMBER = 1;
    public static final int VALUE_DESCRIPTORS_FIELD_NUMBER = 3;
    private int bitField0_;
    private String tagName_ = "";
    private int tagNumber_ = 0;
    private Internal.ProtobufList<ValueDescriptor> valueDescriptors_ = emptyProtobufList();

    public interface ValueDescriptorOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        ValueDescriptor.DataType getType();

        ValueDescriptor.DataUnit getUnit();

        boolean hasName();

        boolean hasType();

        boolean hasUnit();
    }

    private EventLogTag() {
    }

    public static final class ValueDescriptor extends GeneratedMessageLite<ValueDescriptor, Builder> implements ValueDescriptorOrBuilder {
        private static final ValueDescriptor DEFAULT_INSTANCE = new ValueDescriptor();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ValueDescriptor> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 2;
        public static final int UNIT_FIELD_NUMBER = 3;
        private int bitField0_;
        private String name_ = "";
        private int type_ = 0;
        private int unit_ = 0;

        private ValueDescriptor() {
        }

        public enum DataType implements Internal.EnumLite {
            UNKNOWN(0),
            INT(1),
            LONG(2),
            STRING(3),
            LIST(4),
            FLOAT(5);
            
            public static final int FLOAT_VALUE = 5;
            public static final int INT_VALUE = 1;
            public static final int LIST_VALUE = 4;
            public static final int LONG_VALUE = 2;
            public static final int STRING_VALUE = 3;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<DataType> internalValueMap = new Internal.EnumLiteMap<DataType>() {
                /* class android.util.EventLogTag.ValueDescriptor.DataType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DataType findValueByNumber(int number) {
                    return DataType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static DataType valueOf(int value2) {
                return forNumber(value2);
            }

            public static DataType forNumber(int value2) {
                if (value2 == 0) {
                    return UNKNOWN;
                }
                if (value2 == 1) {
                    return INT;
                }
                if (value2 == 2) {
                    return LONG;
                }
                if (value2 == 3) {
                    return STRING;
                }
                if (value2 == 4) {
                    return LIST;
                }
                if (value2 != 5) {
                    return null;
                }
                return FLOAT;
            }

            public static Internal.EnumLiteMap<DataType> internalGetValueMap() {
                return internalValueMap;
            }

            private DataType(int value2) {
                this.value = value2;
            }
        }

        public enum DataUnit implements Internal.EnumLite {
            UNSET(0),
            OBJECTS(1),
            BYTES(2),
            MILLISECONDS(3),
            ALLOCATIONS(4),
            ID(5),
            PERCENT(6),
            SECONDS(115);
            
            public static final int ALLOCATIONS_VALUE = 4;
            public static final int BYTES_VALUE = 2;
            public static final int ID_VALUE = 5;
            public static final int MILLISECONDS_VALUE = 3;
            public static final int OBJECTS_VALUE = 1;
            public static final int PERCENT_VALUE = 6;
            public static final int SECONDS_VALUE = 115;
            public static final int UNSET_VALUE = 0;
            private static final Internal.EnumLiteMap<DataUnit> internalValueMap = new Internal.EnumLiteMap<DataUnit>() {
                /* class android.util.EventLogTag.ValueDescriptor.DataUnit.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DataUnit findValueByNumber(int number) {
                    return DataUnit.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static DataUnit valueOf(int value2) {
                return forNumber(value2);
            }

            public static DataUnit forNumber(int value2) {
                if (value2 == 115) {
                    return SECONDS;
                }
                switch (value2) {
                    case 0:
                        return UNSET;
                    case 1:
                        return OBJECTS;
                    case 2:
                        return BYTES;
                    case 3:
                        return MILLISECONDS;
                    case 4:
                        return ALLOCATIONS;
                    case 5:
                        return ID;
                    case 6:
                        return PERCENT;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<DataUnit> internalGetValueMap() {
                return internalValueMap;
            }

            private DataUnit(int value2) {
                this.value = value2;
            }
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public DataType getType() {
            DataType result = DataType.forNumber(this.type_);
            return result == null ? DataType.UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(DataType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.type_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -3;
            this.type_ = 0;
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public boolean hasUnit() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
        public DataUnit getUnit() {
            DataUnit result = DataUnit.forNumber(this.unit_);
            return result == null ? DataUnit.UNSET : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnit(DataUnit value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.unit_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnit() {
            this.bitField0_ &= -5;
            this.unit_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.type_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.unit_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.type_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.unit_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ValueDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValueDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValueDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValueDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValueDescriptor parseFrom(InputStream input) throws IOException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueDescriptor parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValueDescriptor parseDelimitedFrom(InputStream input) throws IOException {
            return (ValueDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueDescriptor parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValueDescriptor parseFrom(CodedInputStream input) throws IOException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValueDescriptor parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValueDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ValueDescriptor prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ValueDescriptor, Builder> implements ValueDescriptorOrBuilder {
            private Builder() {
                super(ValueDescriptor.DEFAULT_INSTANCE);
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public boolean hasName() {
                return ((ValueDescriptor) this.instance).hasName();
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public String getName() {
                return ((ValueDescriptor) this.instance).getName();
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public ByteString getNameBytes() {
                return ((ValueDescriptor) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((ValueDescriptor) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((ValueDescriptor) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((ValueDescriptor) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public boolean hasType() {
                return ((ValueDescriptor) this.instance).hasType();
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public DataType getType() {
                return ((ValueDescriptor) this.instance).getType();
            }

            public Builder setType(DataType value) {
                copyOnWrite();
                ((ValueDescriptor) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((ValueDescriptor) this.instance).clearType();
                return this;
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public boolean hasUnit() {
                return ((ValueDescriptor) this.instance).hasUnit();
            }

            @Override // android.util.EventLogTag.ValueDescriptorOrBuilder
            public DataUnit getUnit() {
                return ((ValueDescriptor) this.instance).getUnit();
            }

            public Builder setUnit(DataUnit value) {
                copyOnWrite();
                ((ValueDescriptor) this.instance).setUnit(value);
                return this;
            }

            public Builder clearUnit() {
                copyOnWrite();
                ((ValueDescriptor) this.instance).clearUnit();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ValueDescriptor();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ValueDescriptor other = (ValueDescriptor) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                    this.unit_ = visitor.visitInt(hasUnit(), this.unit_, other.hasUnit(), other.unit_);
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
                                this.name_ = s;
                            } else if (tag == 16) {
                                int rawValue = input.readEnum();
                                if (DataType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(2, rawValue);
                                } else {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.type_ = rawValue;
                                }
                            } else if (tag == 24) {
                                int rawValue2 = input.readEnum();
                                if (DataUnit.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(3, rawValue2);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.unit_ = rawValue2;
                                }
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
                        synchronized (ValueDescriptor.class) {
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

        public static ValueDescriptor getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ValueDescriptor> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.util.EventLogTagOrBuilder
    public boolean hasTagNumber() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.util.EventLogTagOrBuilder
    public int getTagNumber() {
        return this.tagNumber_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagNumber(int value) {
        this.bitField0_ |= 1;
        this.tagNumber_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTagNumber() {
        this.bitField0_ &= -2;
        this.tagNumber_ = 0;
    }

    @Override // android.util.EventLogTagOrBuilder
    public boolean hasTagName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.util.EventLogTagOrBuilder
    public String getTagName() {
        return this.tagName_;
    }

    @Override // android.util.EventLogTagOrBuilder
    public ByteString getTagNameBytes() {
        return ByteString.copyFromUtf8(this.tagName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tagName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTagName() {
        this.bitField0_ &= -3;
        this.tagName_ = getDefaultInstance().getTagName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tagName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.util.EventLogTagOrBuilder
    public List<ValueDescriptor> getValueDescriptorsList() {
        return this.valueDescriptors_;
    }

    public List<? extends ValueDescriptorOrBuilder> getValueDescriptorsOrBuilderList() {
        return this.valueDescriptors_;
    }

    @Override // android.util.EventLogTagOrBuilder
    public int getValueDescriptorsCount() {
        return this.valueDescriptors_.size();
    }

    @Override // android.util.EventLogTagOrBuilder
    public ValueDescriptor getValueDescriptors(int index) {
        return this.valueDescriptors_.get(index);
    }

    public ValueDescriptorOrBuilder getValueDescriptorsOrBuilder(int index) {
        return this.valueDescriptors_.get(index);
    }

    private void ensureValueDescriptorsIsMutable() {
        if (!this.valueDescriptors_.isModifiable()) {
            this.valueDescriptors_ = GeneratedMessageLite.mutableCopy(this.valueDescriptors_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValueDescriptors(int index, ValueDescriptor value) {
        if (value != null) {
            ensureValueDescriptorsIsMutable();
            this.valueDescriptors_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValueDescriptors(int index, ValueDescriptor.Builder builderForValue) {
        ensureValueDescriptorsIsMutable();
        this.valueDescriptors_.set(index, (ValueDescriptor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValueDescriptors(ValueDescriptor value) {
        if (value != null) {
            ensureValueDescriptorsIsMutable();
            this.valueDescriptors_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValueDescriptors(int index, ValueDescriptor value) {
        if (value != null) {
            ensureValueDescriptorsIsMutable();
            this.valueDescriptors_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValueDescriptors(ValueDescriptor.Builder builderForValue) {
        ensureValueDescriptorsIsMutable();
        this.valueDescriptors_.add((ValueDescriptor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addValueDescriptors(int index, ValueDescriptor.Builder builderForValue) {
        ensureValueDescriptorsIsMutable();
        this.valueDescriptors_.add(index, (ValueDescriptor) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllValueDescriptors(Iterable<? extends ValueDescriptor> values) {
        ensureValueDescriptorsIsMutable();
        AbstractMessageLite.addAll(values, this.valueDescriptors_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearValueDescriptors() {
        this.valueDescriptors_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeValueDescriptors(int index) {
        ensureValueDescriptorsIsMutable();
        this.valueDescriptors_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeUInt32(1, this.tagNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getTagName());
        }
        for (int i = 0; i < this.valueDescriptors_.size(); i++) {
            output.writeMessage(3, this.valueDescriptors_.get(i));
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
            size2 = 0 + CodedOutputStream.computeUInt32Size(1, this.tagNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getTagName());
        }
        for (int i = 0; i < this.valueDescriptors_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.valueDescriptors_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static EventLogTag parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EventLogTag parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EventLogTag parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EventLogTag parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EventLogTag parseFrom(InputStream input) throws IOException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTag parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EventLogTag parseDelimitedFrom(InputStream input) throws IOException {
        return (EventLogTag) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTag parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTag) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EventLogTag parseFrom(CodedInputStream input) throws IOException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTag parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTag) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EventLogTag prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<EventLogTag, Builder> implements EventLogTagOrBuilder {
        private Builder() {
            super(EventLogTag.DEFAULT_INSTANCE);
        }

        @Override // android.util.EventLogTagOrBuilder
        public boolean hasTagNumber() {
            return ((EventLogTag) this.instance).hasTagNumber();
        }

        @Override // android.util.EventLogTagOrBuilder
        public int getTagNumber() {
            return ((EventLogTag) this.instance).getTagNumber();
        }

        public Builder setTagNumber(int value) {
            copyOnWrite();
            ((EventLogTag) this.instance).setTagNumber(value);
            return this;
        }

        public Builder clearTagNumber() {
            copyOnWrite();
            ((EventLogTag) this.instance).clearTagNumber();
            return this;
        }

        @Override // android.util.EventLogTagOrBuilder
        public boolean hasTagName() {
            return ((EventLogTag) this.instance).hasTagName();
        }

        @Override // android.util.EventLogTagOrBuilder
        public String getTagName() {
            return ((EventLogTag) this.instance).getTagName();
        }

        @Override // android.util.EventLogTagOrBuilder
        public ByteString getTagNameBytes() {
            return ((EventLogTag) this.instance).getTagNameBytes();
        }

        public Builder setTagName(String value) {
            copyOnWrite();
            ((EventLogTag) this.instance).setTagName(value);
            return this;
        }

        public Builder clearTagName() {
            copyOnWrite();
            ((EventLogTag) this.instance).clearTagName();
            return this;
        }

        public Builder setTagNameBytes(ByteString value) {
            copyOnWrite();
            ((EventLogTag) this.instance).setTagNameBytes(value);
            return this;
        }

        @Override // android.util.EventLogTagOrBuilder
        public List<ValueDescriptor> getValueDescriptorsList() {
            return Collections.unmodifiableList(((EventLogTag) this.instance).getValueDescriptorsList());
        }

        @Override // android.util.EventLogTagOrBuilder
        public int getValueDescriptorsCount() {
            return ((EventLogTag) this.instance).getValueDescriptorsCount();
        }

        @Override // android.util.EventLogTagOrBuilder
        public ValueDescriptor getValueDescriptors(int index) {
            return ((EventLogTag) this.instance).getValueDescriptors(index);
        }

        public Builder setValueDescriptors(int index, ValueDescriptor value) {
            copyOnWrite();
            ((EventLogTag) this.instance).setValueDescriptors((EventLogTag) index, (int) value);
            return this;
        }

        public Builder setValueDescriptors(int index, ValueDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTag) this.instance).setValueDescriptors((EventLogTag) index, (int) builderForValue);
            return this;
        }

        public Builder addValueDescriptors(ValueDescriptor value) {
            copyOnWrite();
            ((EventLogTag) this.instance).addValueDescriptors((EventLogTag) value);
            return this;
        }

        public Builder addValueDescriptors(int index, ValueDescriptor value) {
            copyOnWrite();
            ((EventLogTag) this.instance).addValueDescriptors((EventLogTag) index, (int) value);
            return this;
        }

        public Builder addValueDescriptors(ValueDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTag) this.instance).addValueDescriptors((EventLogTag) builderForValue);
            return this;
        }

        public Builder addValueDescriptors(int index, ValueDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTag) this.instance).addValueDescriptors((EventLogTag) index, (int) builderForValue);
            return this;
        }

        public Builder addAllValueDescriptors(Iterable<? extends ValueDescriptor> values) {
            copyOnWrite();
            ((EventLogTag) this.instance).addAllValueDescriptors(values);
            return this;
        }

        public Builder clearValueDescriptors() {
            copyOnWrite();
            ((EventLogTag) this.instance).clearValueDescriptors();
            return this;
        }

        public Builder removeValueDescriptors(int index) {
            copyOnWrite();
            ((EventLogTag) this.instance).removeValueDescriptors(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new EventLogTag();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.valueDescriptors_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                EventLogTag other = (EventLogTag) arg1;
                this.tagNumber_ = visitor.visitInt(hasTagNumber(), this.tagNumber_, other.hasTagNumber(), other.tagNumber_);
                this.tagName_ = visitor.visitString(hasTagName(), this.tagName_, other.hasTagName(), other.tagName_);
                this.valueDescriptors_ = visitor.visitList(this.valueDescriptors_, other.valueDescriptors_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.tagNumber_ = input.readUInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.tagName_ = s;
                        } else if (tag == 26) {
                            if (!this.valueDescriptors_.isModifiable()) {
                                this.valueDescriptors_ = GeneratedMessageLite.mutableCopy(this.valueDescriptors_);
                            }
                            this.valueDescriptors_.add((ValueDescriptor) input.readMessage(ValueDescriptor.parser(), extensionRegistry));
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
                    synchronized (EventLogTag.class) {
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

    public static EventLogTag getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EventLogTag> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
