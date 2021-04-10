package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class IncidentHeaderProto extends GeneratedMessageLite<IncidentHeaderProto, Builder> implements IncidentHeaderProtoOrBuilder {
    public static final int ALERT_ID_FIELD_NUMBER = 1;
    public static final int CONFIG_KEY_FIELD_NUMBER = 3;
    private static final IncidentHeaderProto DEFAULT_INSTANCE = new IncidentHeaderProto();
    private static volatile Parser<IncidentHeaderProto> PARSER = null;
    public static final int REASON_FIELD_NUMBER = 2;
    public static final int TRIGGER_DETAILS_FIELD_NUMBER = 4;
    private long alertId_ = 0;
    private int bitField0_;
    private StatsdConfigKey configKey_;
    private String reason_ = "";
    private ByteString triggerDetails_ = ByteString.EMPTY;

    public interface StatsdConfigKeyOrBuilder extends MessageLiteOrBuilder {
        long getId();

        int getUid();

        boolean hasId();

        boolean hasUid();
    }

    private IncidentHeaderProto() {
    }

    public static final class StatsdConfigKey extends GeneratedMessageLite<StatsdConfigKey, Builder> implements StatsdConfigKeyOrBuilder {
        private static final StatsdConfigKey DEFAULT_INSTANCE = new StatsdConfigKey();
        public static final int ID_FIELD_NUMBER = 2;
        private static volatile Parser<StatsdConfigKey> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private long id_ = 0;
        private int uid_ = 0;

        private StatsdConfigKey() {
        }

        @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
        public long getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(long value) {
            this.bitField0_ |= 2;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -3;
            this.id_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.id_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.id_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StatsdConfigKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatsdConfigKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatsdConfigKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatsdConfigKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatsdConfigKey parseFrom(InputStream input) throws IOException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfigKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatsdConfigKey parseDelimitedFrom(InputStream input) throws IOException {
            return (StatsdConfigKey) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfigKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfigKey) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatsdConfigKey parseFrom(CodedInputStream input) throws IOException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatsdConfigKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatsdConfigKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StatsdConfigKey prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StatsdConfigKey, Builder> implements StatsdConfigKeyOrBuilder {
            private Builder() {
                super(StatsdConfigKey.DEFAULT_INSTANCE);
            }

            @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
            public boolean hasUid() {
                return ((StatsdConfigKey) this.instance).hasUid();
            }

            @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
            public int getUid() {
                return ((StatsdConfigKey) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((StatsdConfigKey) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((StatsdConfigKey) this.instance).clearUid();
                return this;
            }

            @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
            public boolean hasId() {
                return ((StatsdConfigKey) this.instance).hasId();
            }

            @Override // android.os.IncidentHeaderProto.StatsdConfigKeyOrBuilder
            public long getId() {
                return ((StatsdConfigKey) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((StatsdConfigKey) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((StatsdConfigKey) this.instance).clearId();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StatsdConfigKey();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StatsdConfigKey other = (StatsdConfigKey) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.id_ = visitor.visitLong(hasId(), this.id_, other.hasId(), other.id_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.id_ = input.readInt64();
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
                        synchronized (StatsdConfigKey.class) {
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

        public static StatsdConfigKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StatsdConfigKey> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public boolean hasAlertId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public long getAlertId() {
        return this.alertId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlertId(long value) {
        this.bitField0_ |= 1;
        this.alertId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlertId() {
        this.bitField0_ &= -2;
        this.alertId_ = 0;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public boolean hasReason() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public String getReason() {
        return this.reason_;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public ByteString getReasonBytes() {
        return ByteString.copyFromUtf8(this.reason_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReason(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.reason_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReason() {
        this.bitField0_ &= -3;
        this.reason_ = getDefaultInstance().getReason();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReasonBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.reason_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public boolean hasConfigKey() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public StatsdConfigKey getConfigKey() {
        StatsdConfigKey statsdConfigKey = this.configKey_;
        return statsdConfigKey == null ? StatsdConfigKey.getDefaultInstance() : statsdConfigKey;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigKey(StatsdConfigKey value) {
        if (value != null) {
            this.configKey_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigKey(StatsdConfigKey.Builder builderForValue) {
        this.configKey_ = (StatsdConfigKey) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConfigKey(StatsdConfigKey value) {
        StatsdConfigKey statsdConfigKey = this.configKey_;
        if (statsdConfigKey == null || statsdConfigKey == StatsdConfigKey.getDefaultInstance()) {
            this.configKey_ = value;
        } else {
            this.configKey_ = (StatsdConfigKey) ((StatsdConfigKey.Builder) StatsdConfigKey.newBuilder(this.configKey_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigKey() {
        this.configKey_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public boolean hasTriggerDetails() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.IncidentHeaderProtoOrBuilder
    public ByteString getTriggerDetails() {
        return this.triggerDetails_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTriggerDetails(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.triggerDetails_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTriggerDetails() {
        this.bitField0_ &= -9;
        this.triggerDetails_ = getDefaultInstance().getTriggerDetails();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.alertId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getReason());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getConfigKey());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBytes(4, this.triggerDetails_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.alertId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getReason());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getConfigKey());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBytesSize(4, this.triggerDetails_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IncidentHeaderProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentHeaderProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentHeaderProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(InputStream input) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentHeaderProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IncidentHeaderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(CodedInputStream input) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IncidentHeaderProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IncidentHeaderProto, Builder> implements IncidentHeaderProtoOrBuilder {
        private Builder() {
            super(IncidentHeaderProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public boolean hasAlertId() {
            return ((IncidentHeaderProto) this.instance).hasAlertId();
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public long getAlertId() {
            return ((IncidentHeaderProto) this.instance).getAlertId();
        }

        public Builder setAlertId(long value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setAlertId(value);
            return this;
        }

        public Builder clearAlertId() {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).clearAlertId();
            return this;
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public boolean hasReason() {
            return ((IncidentHeaderProto) this.instance).hasReason();
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public String getReason() {
            return ((IncidentHeaderProto) this.instance).getReason();
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public ByteString getReasonBytes() {
            return ((IncidentHeaderProto) this.instance).getReasonBytes();
        }

        public Builder setReason(String value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setReason(value);
            return this;
        }

        public Builder clearReason() {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).clearReason();
            return this;
        }

        public Builder setReasonBytes(ByteString value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setReasonBytes(value);
            return this;
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public boolean hasConfigKey() {
            return ((IncidentHeaderProto) this.instance).hasConfigKey();
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public StatsdConfigKey getConfigKey() {
            return ((IncidentHeaderProto) this.instance).getConfigKey();
        }

        public Builder setConfigKey(StatsdConfigKey value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setConfigKey((IncidentHeaderProto) value);
            return this;
        }

        public Builder setConfigKey(StatsdConfigKey.Builder builderForValue) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setConfigKey((IncidentHeaderProto) builderForValue);
            return this;
        }

        public Builder mergeConfigKey(StatsdConfigKey value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).mergeConfigKey(value);
            return this;
        }

        public Builder clearConfigKey() {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).clearConfigKey();
            return this;
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public boolean hasTriggerDetails() {
            return ((IncidentHeaderProto) this.instance).hasTriggerDetails();
        }

        @Override // android.os.IncidentHeaderProtoOrBuilder
        public ByteString getTriggerDetails() {
            return ((IncidentHeaderProto) this.instance).getTriggerDetails();
        }

        public Builder setTriggerDetails(ByteString value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setTriggerDetails(value);
            return this;
        }

        public Builder clearTriggerDetails() {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).clearTriggerDetails();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IncidentHeaderProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IncidentHeaderProto other = (IncidentHeaderProto) arg1;
                this.alertId_ = visitor.visitLong(hasAlertId(), this.alertId_, other.hasAlertId(), other.alertId_);
                this.reason_ = visitor.visitString(hasReason(), this.reason_, other.hasReason(), other.reason_);
                this.configKey_ = (StatsdConfigKey) visitor.visitMessage(this.configKey_, other.configKey_);
                this.triggerDetails_ = visitor.visitByteString(hasTriggerDetails(), this.triggerDetails_, other.hasTriggerDetails(), other.triggerDetails_);
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
                            this.alertId_ = input.readInt64();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.reason_ = s;
                        } else if (tag == 26) {
                            StatsdConfigKey.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (StatsdConfigKey.Builder) this.configKey_.toBuilder();
                            }
                            this.configKey_ = (StatsdConfigKey) input.readMessage(StatsdConfigKey.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.configKey_);
                                this.configKey_ = (StatsdConfigKey) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.triggerDetails_ = input.readBytes();
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
                    synchronized (IncidentHeaderProto.class) {
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

    public static IncidentHeaderProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IncidentHeaderProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
