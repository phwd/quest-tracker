package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AlarmClockMetadataProto extends GeneratedMessageLite<AlarmClockMetadataProto, Builder> implements AlarmClockMetadataProtoOrBuilder {
    private static final AlarmClockMetadataProto DEFAULT_INSTANCE = new AlarmClockMetadataProto();
    public static final int IS_PENDING_SEND_FIELD_NUMBER = 2;
    private static volatile Parser<AlarmClockMetadataProto> PARSER = null;
    public static final int TRIGGER_TIME_MS_FIELD_NUMBER = 3;
    public static final int USER_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean isPendingSend_ = false;
    private long triggerTimeMs_ = 0;
    private int user_ = 0;

    private AlarmClockMetadataProto() {
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public boolean hasUser() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public int getUser() {
        return this.user_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUser(int value) {
        this.bitField0_ |= 1;
        this.user_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUser() {
        this.bitField0_ &= -2;
        this.user_ = 0;
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public boolean hasIsPendingSend() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public boolean getIsPendingSend() {
        return this.isPendingSend_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPendingSend(boolean value) {
        this.bitField0_ |= 2;
        this.isPendingSend_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPendingSend() {
        this.bitField0_ &= -3;
        this.isPendingSend_ = false;
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public boolean hasTriggerTimeMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
    public long getTriggerTimeMs() {
        return this.triggerTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTriggerTimeMs(long value) {
        this.bitField0_ |= 4;
        this.triggerTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTriggerTimeMs() {
        this.bitField0_ &= -5;
        this.triggerTimeMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.user_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isPendingSend_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.triggerTimeMs_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.user_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isPendingSend_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.triggerTimeMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AlarmClockMetadataProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmClockMetadataProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmClockMetadataProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmClockMetadataProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmClockMetadataProto parseFrom(InputStream input) throws IOException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockMetadataProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmClockMetadataProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlarmClockMetadataProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockMetadataProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockMetadataProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmClockMetadataProto parseFrom(CodedInputStream input) throws IOException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockMetadataProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockMetadataProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlarmClockMetadataProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlarmClockMetadataProto, Builder> implements AlarmClockMetadataProtoOrBuilder {
        private Builder() {
            super(AlarmClockMetadataProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public boolean hasUser() {
            return ((AlarmClockMetadataProto) this.instance).hasUser();
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public int getUser() {
            return ((AlarmClockMetadataProto) this.instance).getUser();
        }

        public Builder setUser(int value) {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).setUser(value);
            return this;
        }

        public Builder clearUser() {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).clearUser();
            return this;
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public boolean hasIsPendingSend() {
            return ((AlarmClockMetadataProto) this.instance).hasIsPendingSend();
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public boolean getIsPendingSend() {
            return ((AlarmClockMetadataProto) this.instance).getIsPendingSend();
        }

        public Builder setIsPendingSend(boolean value) {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).setIsPendingSend(value);
            return this;
        }

        public Builder clearIsPendingSend() {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).clearIsPendingSend();
            return this;
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public boolean hasTriggerTimeMs() {
            return ((AlarmClockMetadataProto) this.instance).hasTriggerTimeMs();
        }

        @Override // com.android.server.AlarmClockMetadataProtoOrBuilder
        public long getTriggerTimeMs() {
            return ((AlarmClockMetadataProto) this.instance).getTriggerTimeMs();
        }

        public Builder setTriggerTimeMs(long value) {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).setTriggerTimeMs(value);
            return this;
        }

        public Builder clearTriggerTimeMs() {
            copyOnWrite();
            ((AlarmClockMetadataProto) this.instance).clearTriggerTimeMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlarmClockMetadataProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlarmClockMetadataProto other = (AlarmClockMetadataProto) arg1;
                this.user_ = visitor.visitInt(hasUser(), this.user_, other.hasUser(), other.user_);
                this.isPendingSend_ = visitor.visitBoolean(hasIsPendingSend(), this.isPendingSend_, other.hasIsPendingSend(), other.isPendingSend_);
                this.triggerTimeMs_ = visitor.visitLong(hasTriggerTimeMs(), this.triggerTimeMs_, other.hasTriggerTimeMs(), other.triggerTimeMs_);
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
                            this.user_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isPendingSend_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.triggerTimeMs_ = input.readInt64();
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
                    synchronized (AlarmClockMetadataProto.class) {
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

    public static AlarmClockMetadataProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlarmClockMetadataProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
