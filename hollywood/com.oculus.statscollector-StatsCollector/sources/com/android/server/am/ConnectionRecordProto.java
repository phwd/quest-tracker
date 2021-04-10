package com.android.server.am;

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
import java.util.List;

public final class ConnectionRecordProto extends GeneratedMessageLite<ConnectionRecordProto, Builder> implements ConnectionRecordProtoOrBuilder {
    private static final ConnectionRecordProto DEFAULT_INSTANCE = new ConnectionRecordProto();
    public static final int FLAGS_FIELD_NUMBER = 3;
    public static final int HEX_HASH_FIELD_NUMBER = 1;
    private static volatile Parser<ConnectionRecordProto> PARSER = null;
    public static final int SERVICE_NAME_FIELD_NUMBER = 4;
    public static final int USER_ID_FIELD_NUMBER = 2;
    private static final Internal.ListAdapter.Converter<Integer, Flag> flags_converter_ = new Internal.ListAdapter.Converter<Integer, Flag>() {
        /* class com.android.server.am.ConnectionRecordProto.AnonymousClass1 */

        public Flag convert(Integer from) {
            Flag result = Flag.forNumber(from.intValue());
            return result == null ? Flag.AUTO_CREATE : result;
        }
    };
    private int bitField0_;
    private Internal.IntList flags_ = emptyIntList();
    private String hexHash_ = "";
    private String serviceName_ = "";
    private int userId_ = 0;

    private ConnectionRecordProto() {
    }

    public enum Flag implements Internal.EnumLite {
        AUTO_CREATE(0),
        DEBUG_UNBIND(1),
        NOT_FG(2),
        IMPORTANT_BG(3),
        ABOVE_CLIENT(4),
        ALLOW_OOM_MANAGEMENT(5),
        WAIVE_PRIORITY(6),
        IMPORTANT(7),
        ADJUST_WITH_ACTIVITY(8),
        FG_SERVICE_WHILE_AWAKE(9),
        FG_SERVICE(10),
        TREAT_LIKE_ACTIVITY(11),
        VISIBLE(12),
        SHOWING_UI(13),
        NOT_VISIBLE(14),
        DEAD(15),
        NOT_PERCEPTIBLE(16),
        INCLUDE_CAPABILITIES(17);
        
        public static final int ABOVE_CLIENT_VALUE = 4;
        public static final int ADJUST_WITH_ACTIVITY_VALUE = 8;
        public static final int ALLOW_OOM_MANAGEMENT_VALUE = 5;
        public static final int AUTO_CREATE_VALUE = 0;
        public static final int DEAD_VALUE = 15;
        public static final int DEBUG_UNBIND_VALUE = 1;
        public static final int FG_SERVICE_VALUE = 10;
        public static final int FG_SERVICE_WHILE_AWAKE_VALUE = 9;
        public static final int IMPORTANT_BG_VALUE = 3;
        public static final int IMPORTANT_VALUE = 7;
        public static final int INCLUDE_CAPABILITIES_VALUE = 17;
        public static final int NOT_FG_VALUE = 2;
        public static final int NOT_PERCEPTIBLE_VALUE = 16;
        public static final int NOT_VISIBLE_VALUE = 14;
        public static final int SHOWING_UI_VALUE = 13;
        public static final int TREAT_LIKE_ACTIVITY_VALUE = 11;
        public static final int VISIBLE_VALUE = 12;
        public static final int WAIVE_PRIORITY_VALUE = 6;
        private static final Internal.EnumLiteMap<Flag> internalValueMap = new Internal.EnumLiteMap<Flag>() {
            /* class com.android.server.am.ConnectionRecordProto.Flag.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Flag findValueByNumber(int number) {
                return Flag.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Flag valueOf(int value2) {
            return forNumber(value2);
        }

        public static Flag forNumber(int value2) {
            switch (value2) {
                case 0:
                    return AUTO_CREATE;
                case 1:
                    return DEBUG_UNBIND;
                case 2:
                    return NOT_FG;
                case 3:
                    return IMPORTANT_BG;
                case 4:
                    return ABOVE_CLIENT;
                case 5:
                    return ALLOW_OOM_MANAGEMENT;
                case 6:
                    return WAIVE_PRIORITY;
                case 7:
                    return IMPORTANT;
                case 8:
                    return ADJUST_WITH_ACTIVITY;
                case 9:
                    return FG_SERVICE_WHILE_AWAKE;
                case 10:
                    return FG_SERVICE;
                case 11:
                    return TREAT_LIKE_ACTIVITY;
                case 12:
                    return VISIBLE;
                case 13:
                    return SHOWING_UI;
                case 14:
                    return NOT_VISIBLE;
                case 15:
                    return DEAD;
                case 16:
                    return NOT_PERCEPTIBLE;
                case 17:
                    return INCLUDE_CAPABILITIES;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Flag> internalGetValueMap() {
            return internalValueMap;
        }

        private Flag(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public boolean hasHexHash() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public String getHexHash() {
        return this.hexHash_;
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public ByteString getHexHashBytes() {
        return ByteString.copyFromUtf8(this.hexHash_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHash(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.hexHash_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHexHash() {
        this.bitField0_ &= -2;
        this.hexHash_ = getDefaultInstance().getHexHash();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHexHashBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.hexHash_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 2;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -3;
        this.userId_ = 0;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public List<Flag> getFlagsList() {
        return new Internal.ListAdapter(this.flags_, flags_converter_);
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public int getFlagsCount() {
        return this.flags_.size();
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public Flag getFlags(int index) {
        return flags_converter_.convert(Integer.valueOf(this.flags_.getInt(index)));
    }

    private void ensureFlagsIsMutable() {
        if (!this.flags_.isModifiable()) {
            this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int index, Flag value) {
        if (value != null) {
            ensureFlagsIsMutable();
            this.flags_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFlags(Flag value) {
        if (value != null) {
            ensureFlagsIsMutable();
            this.flags_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFlags(Iterable<? extends Flag> values) {
        ensureFlagsIsMutable();
        for (Flag value : values) {
            this.flags_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.flags_ = emptyIntList();
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public boolean hasServiceName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public String getServiceName() {
        return this.serviceName_;
    }

    @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
    public ByteString getServiceNameBytes() {
        return ByteString.copyFromUtf8(this.serviceName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceName(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.serviceName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServiceName() {
        this.bitField0_ &= -5;
        this.serviceName_ = getDefaultInstance().getServiceName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServiceNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.serviceName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getHexHash());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.userId_);
        }
        for (int i = 0; i < this.flags_.size(); i++) {
            output.writeEnum(3, this.flags_.getInt(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(4, getServiceName());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getHexHash());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.userId_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.flags_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.flags_.getInt(i));
        }
        int size3 = size2 + dataSize + (this.flags_.size() * 1);
        if ((this.bitField0_ & 4) == 4) {
            size3 += CodedOutputStream.computeStringSize(4, getServiceName());
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static ConnectionRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConnectionRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConnectionRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConnectionRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConnectionRecordProto parseFrom(InputStream input) throws IOException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConnectionRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConnectionRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConnectionRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConnectionRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConnectionRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConnectionRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConnectionRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConnectionRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConnectionRecordProto, Builder> implements ConnectionRecordProtoOrBuilder {
        private Builder() {
            super(ConnectionRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public boolean hasHexHash() {
            return ((ConnectionRecordProto) this.instance).hasHexHash();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public String getHexHash() {
            return ((ConnectionRecordProto) this.instance).getHexHash();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public ByteString getHexHashBytes() {
            return ((ConnectionRecordProto) this.instance).getHexHashBytes();
        }

        public Builder setHexHash(String value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setHexHash(value);
            return this;
        }

        public Builder clearHexHash() {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).clearHexHash();
            return this;
        }

        public Builder setHexHashBytes(ByteString value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setHexHashBytes(value);
            return this;
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public boolean hasUserId() {
            return ((ConnectionRecordProto) this.instance).hasUserId();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public int getUserId() {
            return ((ConnectionRecordProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public List<Flag> getFlagsList() {
            return ((ConnectionRecordProto) this.instance).getFlagsList();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public int getFlagsCount() {
            return ((ConnectionRecordProto) this.instance).getFlagsCount();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public Flag getFlags(int index) {
            return ((ConnectionRecordProto) this.instance).getFlags(index);
        }

        public Builder setFlags(int index, Flag value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setFlags(index, value);
            return this;
        }

        public Builder addFlags(Flag value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).addFlags(value);
            return this;
        }

        public Builder addAllFlags(Iterable<? extends Flag> values) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).addAllFlags(values);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public boolean hasServiceName() {
            return ((ConnectionRecordProto) this.instance).hasServiceName();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public String getServiceName() {
            return ((ConnectionRecordProto) this.instance).getServiceName();
        }

        @Override // com.android.server.am.ConnectionRecordProtoOrBuilder
        public ByteString getServiceNameBytes() {
            return ((ConnectionRecordProto) this.instance).getServiceNameBytes();
        }

        public Builder setServiceName(String value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).clearServiceName();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            copyOnWrite();
            ((ConnectionRecordProto) this.instance).setServiceNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConnectionRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.flags_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConnectionRecordProto other = (ConnectionRecordProto) arg1;
                this.hexHash_ = visitor.visitString(hasHexHash(), this.hexHash_, other.hasHexHash(), other.hexHash_);
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.flags_ = visitor.visitIntList(this.flags_, other.flags_);
                this.serviceName_ = visitor.visitString(hasServiceName(), this.serviceName_, other.hasServiceName(), other.serviceName_);
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
                            this.hexHash_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.userId_ = input.readInt32();
                        } else if (tag == 24) {
                            if (!this.flags_.isModifiable()) {
                                this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                            }
                            int rawValue = input.readEnum();
                            if (Flag.forNumber(rawValue) == null) {
                                super.mergeVarintField(3, rawValue);
                            } else {
                                this.flags_.addInt(rawValue);
                            }
                        } else if (tag == 26) {
                            if (!this.flags_.isModifiable()) {
                                this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (Flag.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(3, rawValue2);
                                } else {
                                    this.flags_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.serviceName_ = s2;
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
                    synchronized (ConnectionRecordProto.class) {
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

    public static ConnectionRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConnectionRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
