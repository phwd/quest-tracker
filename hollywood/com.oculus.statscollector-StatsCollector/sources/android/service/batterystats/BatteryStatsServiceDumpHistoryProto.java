package android.service.batterystats;

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

public final class BatteryStatsServiceDumpHistoryProto extends GeneratedMessageLite<BatteryStatsServiceDumpHistoryProto, Builder> implements BatteryStatsServiceDumpHistoryProtoOrBuilder {
    public static final int CSV_LINES_FIELD_NUMBER = 6;
    private static final BatteryStatsServiceDumpHistoryProto DEFAULT_INSTANCE = new BatteryStatsServiceDumpHistoryProto();
    public static final int END_PLATFORM_VERSION_FIELD_NUMBER = 4;
    public static final int KEYS_FIELD_NUMBER = 5;
    public static final int PARCEL_VERSION_FIELD_NUMBER = 2;
    private static volatile Parser<BatteryStatsServiceDumpHistoryProto> PARSER = null;
    public static final int REPORT_VERSION_FIELD_NUMBER = 1;
    public static final int START_PLATFORM_VERSION_FIELD_NUMBER = 3;
    private int bitField0_;
    private Internal.ProtobufList<String> csvLines_ = GeneratedMessageLite.emptyProtobufList();
    private String endPlatformVersion_ = "";
    private Internal.ProtobufList<Key> keys_ = emptyProtobufList();
    private long parcelVersion_ = 0;
    private int reportVersion_ = 0;
    private String startPlatformVersion_ = "";

    public interface KeyOrBuilder extends MessageLiteOrBuilder {
        int getIndex();

        String getTag();

        ByteString getTagBytes();

        int getUid();

        boolean hasIndex();

        boolean hasTag();

        boolean hasUid();
    }

    private BatteryStatsServiceDumpHistoryProto() {
    }

    public static final class Key extends GeneratedMessageLite<Key, Builder> implements KeyOrBuilder {
        private static final Key DEFAULT_INSTANCE = new Key();
        public static final int INDEX_FIELD_NUMBER = 1;
        private static volatile Parser<Key> PARSER = null;
        public static final int TAG_FIELD_NUMBER = 3;
        public static final int UID_FIELD_NUMBER = 2;
        private int bitField0_;
        private int index_ = 0;
        private String tag_ = "";
        private int uid_ = 0;

        private Key() {
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public boolean hasIndex() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public int getIndex() {
            return this.index_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIndex(int value) {
            this.bitField0_ |= 1;
            this.index_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIndex() {
            this.bitField0_ &= -2;
            this.index_ = 0;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 2;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -3;
            this.uid_ = 0;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public String getTag() {
            return this.tag_;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
        public ByteString getTagBytes() {
            return ByteString.copyFromUtf8(this.tag_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTag(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.tag_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTag() {
            this.bitField0_ &= -5;
            this.tag_ = getDefaultInstance().getTag();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTagBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.tag_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.index_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getTag());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.index_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getTag());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Key parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Key parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Key parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Key parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Key parseFrom(InputStream input) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Key parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Key parseDelimitedFrom(InputStream input) throws IOException {
            return (Key) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Key parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Key) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Key parseFrom(CodedInputStream input) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Key parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Key prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Key, Builder> implements KeyOrBuilder {
            private Builder() {
                super(Key.DEFAULT_INSTANCE);
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public boolean hasIndex() {
                return ((Key) this.instance).hasIndex();
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public int getIndex() {
                return ((Key) this.instance).getIndex();
            }

            public Builder setIndex(int value) {
                copyOnWrite();
                ((Key) this.instance).setIndex(value);
                return this;
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((Key) this.instance).clearIndex();
                return this;
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public boolean hasUid() {
                return ((Key) this.instance).hasUid();
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public int getUid() {
                return ((Key) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((Key) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((Key) this.instance).clearUid();
                return this;
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public boolean hasTag() {
                return ((Key) this.instance).hasTag();
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public String getTag() {
                return ((Key) this.instance).getTag();
            }

            @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProto.KeyOrBuilder
            public ByteString getTagBytes() {
                return ((Key) this.instance).getTagBytes();
            }

            public Builder setTag(String value) {
                copyOnWrite();
                ((Key) this.instance).setTag(value);
                return this;
            }

            public Builder clearTag() {
                copyOnWrite();
                ((Key) this.instance).clearTag();
                return this;
            }

            public Builder setTagBytes(ByteString value) {
                copyOnWrite();
                ((Key) this.instance).setTagBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Key();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Key other = (Key) arg1;
                    this.index_ = visitor.visitInt(hasIndex(), this.index_, other.hasIndex(), other.index_);
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
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
                                this.bitField0_ |= 1;
                                this.index_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.uid_ = input.readInt32();
                            } else if (tag == 26) {
                                String s = input.readString();
                                this.bitField0_ |= 4;
                                this.tag_ = s;
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
                        synchronized (Key.class) {
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

        public static Key getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Key> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public boolean hasReportVersion() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public int getReportVersion() {
        return this.reportVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportVersion(int value) {
        this.bitField0_ |= 1;
        this.reportVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportVersion() {
        this.bitField0_ &= -2;
        this.reportVersion_ = 0;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public boolean hasParcelVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public long getParcelVersion() {
        return this.parcelVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParcelVersion(long value) {
        this.bitField0_ |= 2;
        this.parcelVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParcelVersion() {
        this.bitField0_ &= -3;
        this.parcelVersion_ = 0;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public boolean hasStartPlatformVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public String getStartPlatformVersion() {
        return this.startPlatformVersion_;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public ByteString getStartPlatformVersionBytes() {
        return ByteString.copyFromUtf8(this.startPlatformVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartPlatformVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.startPlatformVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartPlatformVersion() {
        this.bitField0_ &= -5;
        this.startPlatformVersion_ = getDefaultInstance().getStartPlatformVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartPlatformVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.startPlatformVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public boolean hasEndPlatformVersion() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public String getEndPlatformVersion() {
        return this.endPlatformVersion_;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public ByteString getEndPlatformVersionBytes() {
        return ByteString.copyFromUtf8(this.endPlatformVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndPlatformVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.endPlatformVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndPlatformVersion() {
        this.bitField0_ &= -9;
        this.endPlatformVersion_ = getDefaultInstance().getEndPlatformVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndPlatformVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.endPlatformVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public List<Key> getKeysList() {
        return this.keys_;
    }

    public List<? extends KeyOrBuilder> getKeysOrBuilderList() {
        return this.keys_;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public int getKeysCount() {
        return this.keys_.size();
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public Key getKeys(int index) {
        return this.keys_.get(index);
    }

    public KeyOrBuilder getKeysOrBuilder(int index) {
        return this.keys_.get(index);
    }

    private void ensureKeysIsMutable() {
        if (!this.keys_.isModifiable()) {
            this.keys_ = GeneratedMessageLite.mutableCopy(this.keys_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeys(int index, Key value) {
        if (value != null) {
            ensureKeysIsMutable();
            this.keys_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeys(int index, Key.Builder builderForValue) {
        ensureKeysIsMutable();
        this.keys_.set(index, (Key) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeys(Key value) {
        if (value != null) {
            ensureKeysIsMutable();
            this.keys_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeys(int index, Key value) {
        if (value != null) {
            ensureKeysIsMutable();
            this.keys_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeys(Key.Builder builderForValue) {
        ensureKeysIsMutable();
        this.keys_.add((Key) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addKeys(int index, Key.Builder builderForValue) {
        ensureKeysIsMutable();
        this.keys_.add(index, (Key) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllKeys(Iterable<? extends Key> values) {
        ensureKeysIsMutable();
        AbstractMessageLite.addAll(values, this.keys_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeys() {
        this.keys_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeKeys(int index) {
        ensureKeysIsMutable();
        this.keys_.remove(index);
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public List<String> getCsvLinesList() {
        return this.csvLines_;
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public int getCsvLinesCount() {
        return this.csvLines_.size();
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public String getCsvLines(int index) {
        return this.csvLines_.get(index);
    }

    @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
    public ByteString getCsvLinesBytes(int index) {
        return ByteString.copyFromUtf8(this.csvLines_.get(index));
    }

    private void ensureCsvLinesIsMutable() {
        if (!this.csvLines_.isModifiable()) {
            this.csvLines_ = GeneratedMessageLite.mutableCopy(this.csvLines_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCsvLines(int index, String value) {
        if (value != null) {
            ensureCsvLinesIsMutable();
            this.csvLines_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCsvLines(String value) {
        if (value != null) {
            ensureCsvLinesIsMutable();
            this.csvLines_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCsvLines(Iterable<String> values) {
        ensureCsvLinesIsMutable();
        AbstractMessageLite.addAll(values, this.csvLines_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCsvLines() {
        this.csvLines_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCsvLinesBytes(ByteString value) {
        if (value != null) {
            ensureCsvLinesIsMutable();
            this.csvLines_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.parcelVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getStartPlatformVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getEndPlatformVersion());
        }
        for (int i = 0; i < this.keys_.size(); i++) {
            output.writeMessage(5, this.keys_.get(i));
        }
        for (int i2 = 0; i2 < this.csvLines_.size(); i2++) {
            output.writeString(6, this.csvLines_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.parcelVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getStartPlatformVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getEndPlatformVersion());
        }
        for (int i = 0; i < this.keys_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.keys_.get(i));
        }
        int dataSize = 0;
        for (int i2 = 0; i2 < this.csvLines_.size(); i2++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.csvLines_.get(i2));
        }
        int size3 = size2 + dataSize + (getCsvLinesList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(InputStream input) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsServiceDumpHistoryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpHistoryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(CodedInputStream input) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsServiceDumpHistoryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsServiceDumpHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatteryStatsServiceDumpHistoryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatteryStatsServiceDumpHistoryProto, Builder> implements BatteryStatsServiceDumpHistoryProtoOrBuilder {
        private Builder() {
            super(BatteryStatsServiceDumpHistoryProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public boolean hasReportVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).hasReportVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public int getReportVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getReportVersion();
        }

        public Builder setReportVersion(int value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setReportVersion(value);
            return this;
        }

        public Builder clearReportVersion() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearReportVersion();
            return this;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public boolean hasParcelVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).hasParcelVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public long getParcelVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getParcelVersion();
        }

        public Builder setParcelVersion(long value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setParcelVersion(value);
            return this;
        }

        public Builder clearParcelVersion() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearParcelVersion();
            return this;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public boolean hasStartPlatformVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).hasStartPlatformVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public String getStartPlatformVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getStartPlatformVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public ByteString getStartPlatformVersionBytes() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getStartPlatformVersionBytes();
        }

        public Builder setStartPlatformVersion(String value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setStartPlatformVersion(value);
            return this;
        }

        public Builder clearStartPlatformVersion() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearStartPlatformVersion();
            return this;
        }

        public Builder setStartPlatformVersionBytes(ByteString value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setStartPlatformVersionBytes(value);
            return this;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public boolean hasEndPlatformVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).hasEndPlatformVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public String getEndPlatformVersion() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getEndPlatformVersion();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public ByteString getEndPlatformVersionBytes() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getEndPlatformVersionBytes();
        }

        public Builder setEndPlatformVersion(String value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setEndPlatformVersion(value);
            return this;
        }

        public Builder clearEndPlatformVersion() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearEndPlatformVersion();
            return this;
        }

        public Builder setEndPlatformVersionBytes(ByteString value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setEndPlatformVersionBytes(value);
            return this;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public List<Key> getKeysList() {
            return Collections.unmodifiableList(((BatteryStatsServiceDumpHistoryProto) this.instance).getKeysList());
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public int getKeysCount() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getKeysCount();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public Key getKeys(int index) {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getKeys(index);
        }

        public Builder setKeys(int index, Key value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setKeys((BatteryStatsServiceDumpHistoryProto) index, (int) value);
            return this;
        }

        public Builder setKeys(int index, Key.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setKeys((BatteryStatsServiceDumpHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addKeys(Key value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addKeys((BatteryStatsServiceDumpHistoryProto) value);
            return this;
        }

        public Builder addKeys(int index, Key value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addKeys((BatteryStatsServiceDumpHistoryProto) index, (int) value);
            return this;
        }

        public Builder addKeys(Key.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addKeys((BatteryStatsServiceDumpHistoryProto) builderForValue);
            return this;
        }

        public Builder addKeys(int index, Key.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addKeys((BatteryStatsServiceDumpHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllKeys(Iterable<? extends Key> values) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addAllKeys(values);
            return this;
        }

        public Builder clearKeys() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearKeys();
            return this;
        }

        public Builder removeKeys(int index) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).removeKeys(index);
            return this;
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public List<String> getCsvLinesList() {
            return Collections.unmodifiableList(((BatteryStatsServiceDumpHistoryProto) this.instance).getCsvLinesList());
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public int getCsvLinesCount() {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getCsvLinesCount();
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public String getCsvLines(int index) {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getCsvLines(index);
        }

        @Override // android.service.batterystats.BatteryStatsServiceDumpHistoryProtoOrBuilder
        public ByteString getCsvLinesBytes(int index) {
            return ((BatteryStatsServiceDumpHistoryProto) this.instance).getCsvLinesBytes(index);
        }

        public Builder setCsvLines(int index, String value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).setCsvLines(index, value);
            return this;
        }

        public Builder addCsvLines(String value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addCsvLines(value);
            return this;
        }

        public Builder addAllCsvLines(Iterable<String> values) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addAllCsvLines(values);
            return this;
        }

        public Builder clearCsvLines() {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).clearCsvLines();
            return this;
        }

        public Builder addCsvLinesBytes(ByteString value) {
            copyOnWrite();
            ((BatteryStatsServiceDumpHistoryProto) this.instance).addCsvLinesBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatteryStatsServiceDumpHistoryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.keys_.makeImmutable();
                this.csvLines_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatteryStatsServiceDumpHistoryProto other = (BatteryStatsServiceDumpHistoryProto) arg1;
                this.reportVersion_ = visitor.visitInt(hasReportVersion(), this.reportVersion_, other.hasReportVersion(), other.reportVersion_);
                this.parcelVersion_ = visitor.visitLong(hasParcelVersion(), this.parcelVersion_, other.hasParcelVersion(), other.parcelVersion_);
                this.startPlatformVersion_ = visitor.visitString(hasStartPlatformVersion(), this.startPlatformVersion_, other.hasStartPlatformVersion(), other.startPlatformVersion_);
                this.endPlatformVersion_ = visitor.visitString(hasEndPlatformVersion(), this.endPlatformVersion_, other.hasEndPlatformVersion(), other.endPlatformVersion_);
                this.keys_ = visitor.visitList(this.keys_, other.keys_);
                this.csvLines_ = visitor.visitList(this.csvLines_, other.csvLines_);
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
                            this.reportVersion_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.parcelVersion_ = input.readInt64();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.startPlatformVersion_ = s;
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.endPlatformVersion_ = s2;
                        } else if (tag == 42) {
                            if (!this.keys_.isModifiable()) {
                                this.keys_ = GeneratedMessageLite.mutableCopy(this.keys_);
                            }
                            this.keys_.add((Key) input.readMessage(Key.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            String s3 = input.readString();
                            if (!this.csvLines_.isModifiable()) {
                                this.csvLines_ = GeneratedMessageLite.mutableCopy(this.csvLines_);
                            }
                            this.csvLines_.add(s3);
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
                    synchronized (BatteryStatsServiceDumpHistoryProto.class) {
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

    public static BatteryStatsServiceDumpHistoryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatteryStatsServiceDumpHistoryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
