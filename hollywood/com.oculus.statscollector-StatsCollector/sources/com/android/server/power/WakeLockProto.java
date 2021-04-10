package com.android.server.power;

import android.os.WakeLockLevelEnum;
import android.os.WorkSourceProto;
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

public final class WakeLockProto extends GeneratedMessageLite<WakeLockProto, Builder> implements WakeLockProtoOrBuilder {
    public static final int ACQ_MS_FIELD_NUMBER = 5;
    private static final WakeLockProto DEFAULT_INSTANCE = new WakeLockProto();
    public static final int FLAGS_FIELD_NUMBER = 3;
    public static final int IS_DISABLED_FIELD_NUMBER = 4;
    public static final int IS_NOTIFIED_LONG_FIELD_NUMBER = 6;
    public static final int LOCK_LEVEL_FIELD_NUMBER = 1;
    private static volatile Parser<WakeLockProto> PARSER = null;
    public static final int PID_FIELD_NUMBER = 8;
    public static final int TAG_FIELD_NUMBER = 2;
    public static final int UID_FIELD_NUMBER = 7;
    public static final int WORK_SOURCE_FIELD_NUMBER = 9;
    private long acqMs_ = 0;
    private int bitField0_;
    private WakeLockFlagsProto flags_;
    private boolean isDisabled_ = false;
    private boolean isNotifiedLong_ = false;
    private int lockLevel_ = 1;
    private int pid_ = 0;
    private String tag_ = "";
    private int uid_ = 0;
    private WorkSourceProto workSource_;

    public interface WakeLockFlagsProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsAcquireCausesWakeup();

        boolean getIsOnAfterRelease();

        boolean hasIsAcquireCausesWakeup();

        boolean hasIsOnAfterRelease();
    }

    private WakeLockProto() {
    }

    public static final class WakeLockFlagsProto extends GeneratedMessageLite<WakeLockFlagsProto, Builder> implements WakeLockFlagsProtoOrBuilder {
        private static final WakeLockFlagsProto DEFAULT_INSTANCE = new WakeLockFlagsProto();
        public static final int IS_ACQUIRE_CAUSES_WAKEUP_FIELD_NUMBER = 1;
        public static final int IS_ON_AFTER_RELEASE_FIELD_NUMBER = 2;
        private static volatile Parser<WakeLockFlagsProto> PARSER;
        private int bitField0_;
        private boolean isAcquireCausesWakeup_ = false;
        private boolean isOnAfterRelease_ = false;

        private WakeLockFlagsProto() {
        }

        @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
        public boolean hasIsAcquireCausesWakeup() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
        public boolean getIsAcquireCausesWakeup() {
            return this.isAcquireCausesWakeup_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsAcquireCausesWakeup(boolean value) {
            this.bitField0_ |= 1;
            this.isAcquireCausesWakeup_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsAcquireCausesWakeup() {
            this.bitField0_ &= -2;
            this.isAcquireCausesWakeup_ = false;
        }

        @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
        public boolean hasIsOnAfterRelease() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
        public boolean getIsOnAfterRelease() {
            return this.isOnAfterRelease_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsOnAfterRelease(boolean value) {
            this.bitField0_ |= 2;
            this.isOnAfterRelease_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsOnAfterRelease() {
            this.bitField0_ &= -3;
            this.isOnAfterRelease_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isAcquireCausesWakeup_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isOnAfterRelease_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isAcquireCausesWakeup_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isOnAfterRelease_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WakeLockFlagsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeLockFlagsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeLockFlagsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeLockFlagsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeLockFlagsProto parseFrom(InputStream input) throws IOException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLockFlagsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeLockFlagsProto parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeLockFlagsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLockFlagsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLockFlagsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeLockFlagsProto parseFrom(CodedInputStream input) throws IOException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeLockFlagsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeLockFlagsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WakeLockFlagsProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeLockFlagsProto, Builder> implements WakeLockFlagsProtoOrBuilder {
            private Builder() {
                super(WakeLockFlagsProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
            public boolean hasIsAcquireCausesWakeup() {
                return ((WakeLockFlagsProto) this.instance).hasIsAcquireCausesWakeup();
            }

            @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
            public boolean getIsAcquireCausesWakeup() {
                return ((WakeLockFlagsProto) this.instance).getIsAcquireCausesWakeup();
            }

            public Builder setIsAcquireCausesWakeup(boolean value) {
                copyOnWrite();
                ((WakeLockFlagsProto) this.instance).setIsAcquireCausesWakeup(value);
                return this;
            }

            public Builder clearIsAcquireCausesWakeup() {
                copyOnWrite();
                ((WakeLockFlagsProto) this.instance).clearIsAcquireCausesWakeup();
                return this;
            }

            @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
            public boolean hasIsOnAfterRelease() {
                return ((WakeLockFlagsProto) this.instance).hasIsOnAfterRelease();
            }

            @Override // com.android.server.power.WakeLockProto.WakeLockFlagsProtoOrBuilder
            public boolean getIsOnAfterRelease() {
                return ((WakeLockFlagsProto) this.instance).getIsOnAfterRelease();
            }

            public Builder setIsOnAfterRelease(boolean value) {
                copyOnWrite();
                ((WakeLockFlagsProto) this.instance).setIsOnAfterRelease(value);
                return this;
            }

            public Builder clearIsOnAfterRelease() {
                copyOnWrite();
                ((WakeLockFlagsProto) this.instance).clearIsOnAfterRelease();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WakeLockFlagsProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WakeLockFlagsProto other = (WakeLockFlagsProto) arg1;
                    this.isAcquireCausesWakeup_ = visitor.visitBoolean(hasIsAcquireCausesWakeup(), this.isAcquireCausesWakeup_, other.hasIsAcquireCausesWakeup(), other.isAcquireCausesWakeup_);
                    this.isOnAfterRelease_ = visitor.visitBoolean(hasIsOnAfterRelease(), this.isOnAfterRelease_, other.hasIsOnAfterRelease(), other.isOnAfterRelease_);
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
                                this.isAcquireCausesWakeup_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isOnAfterRelease_ = input.readBool();
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
                        synchronized (WakeLockFlagsProto.class) {
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

        public static WakeLockFlagsProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeLockFlagsProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasLockLevel() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public WakeLockLevelEnum getLockLevel() {
        WakeLockLevelEnum result = WakeLockLevelEnum.forNumber(this.lockLevel_);
        return result == null ? WakeLockLevelEnum.PARTIAL_WAKE_LOCK : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockLevel(WakeLockLevelEnum value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.lockLevel_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockLevel() {
        this.bitField0_ &= -2;
        this.lockLevel_ = 1;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -3;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public WakeLockFlagsProto getFlags() {
        WakeLockFlagsProto wakeLockFlagsProto = this.flags_;
        return wakeLockFlagsProto == null ? WakeLockFlagsProto.getDefaultInstance() : wakeLockFlagsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(WakeLockFlagsProto value) {
        if (value != null) {
            this.flags_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(WakeLockFlagsProto.Builder builderForValue) {
        this.flags_ = (WakeLockFlagsProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFlags(WakeLockFlagsProto value) {
        WakeLockFlagsProto wakeLockFlagsProto = this.flags_;
        if (wakeLockFlagsProto == null || wakeLockFlagsProto == WakeLockFlagsProto.getDefaultInstance()) {
            this.flags_ = value;
        } else {
            this.flags_ = (WakeLockFlagsProto) ((WakeLockFlagsProto.Builder) WakeLockFlagsProto.newBuilder(this.flags_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.flags_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasIsDisabled() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean getIsDisabled() {
        return this.isDisabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDisabled(boolean value) {
        this.bitField0_ |= 8;
        this.isDisabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDisabled() {
        this.bitField0_ &= -9;
        this.isDisabled_ = false;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasAcqMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public long getAcqMs() {
        return this.acqMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAcqMs(long value) {
        this.bitField0_ |= 16;
        this.acqMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAcqMs() {
        this.bitField0_ &= -17;
        this.acqMs_ = 0;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasIsNotifiedLong() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean getIsNotifiedLong() {
        return this.isNotifiedLong_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsNotifiedLong(boolean value) {
        this.bitField0_ |= 32;
        this.isNotifiedLong_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsNotifiedLong() {
        this.bitField0_ &= -33;
        this.isNotifiedLong_ = false;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 64;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -65;
        this.uid_ = 0;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 128;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -129;
        this.pid_ = 0;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public boolean hasWorkSource() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.power.WakeLockProtoOrBuilder
    public WorkSourceProto getWorkSource() {
        WorkSourceProto workSourceProto = this.workSource_;
        return workSourceProto == null ? WorkSourceProto.getDefaultInstance() : workSourceProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSource(WorkSourceProto value) {
        if (value != null) {
            this.workSource_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSource(WorkSourceProto.Builder builderForValue) {
        this.workSource_ = (WorkSourceProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWorkSource(WorkSourceProto value) {
        WorkSourceProto workSourceProto = this.workSource_;
        if (workSourceProto == null || workSourceProto == WorkSourceProto.getDefaultInstance()) {
            this.workSource_ = value;
        } else {
            this.workSource_ = (WorkSourceProto) ((WorkSourceProto.Builder) WorkSourceProto.newBuilder(this.workSource_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWorkSource() {
        this.workSource_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.lockLevel_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getFlags());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isDisabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.acqMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.isNotifiedLong_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.uid_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.pid_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getWorkSource());
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.lockLevel_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getFlags());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isDisabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.acqMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isNotifiedLong_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.uid_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.pid_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getWorkSource());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WakeLockProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WakeLockProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WakeLockProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WakeLockProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WakeLockProto parseFrom(InputStream input) throws IOException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeLockProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WakeLockProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WakeLockProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeLockProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeLockProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WakeLockProto parseFrom(CodedInputStream input) throws IOException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WakeLockProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WakeLockProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WakeLockProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WakeLockProto, Builder> implements WakeLockProtoOrBuilder {
        private Builder() {
            super(WakeLockProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasLockLevel() {
            return ((WakeLockProto) this.instance).hasLockLevel();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public WakeLockLevelEnum getLockLevel() {
            return ((WakeLockProto) this.instance).getLockLevel();
        }

        public Builder setLockLevel(WakeLockLevelEnum value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setLockLevel(value);
            return this;
        }

        public Builder clearLockLevel() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearLockLevel();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasTag() {
            return ((WakeLockProto) this.instance).hasTag();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public String getTag() {
            return ((WakeLockProto) this.instance).getTag();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public ByteString getTagBytes() {
            return ((WakeLockProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasFlags() {
            return ((WakeLockProto) this.instance).hasFlags();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public WakeLockFlagsProto getFlags() {
            return ((WakeLockProto) this.instance).getFlags();
        }

        public Builder setFlags(WakeLockFlagsProto value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setFlags((WakeLockProto) value);
            return this;
        }

        public Builder setFlags(WakeLockFlagsProto.Builder builderForValue) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setFlags((WakeLockProto) builderForValue);
            return this;
        }

        public Builder mergeFlags(WakeLockFlagsProto value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).mergeFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasIsDisabled() {
            return ((WakeLockProto) this.instance).hasIsDisabled();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean getIsDisabled() {
            return ((WakeLockProto) this.instance).getIsDisabled();
        }

        public Builder setIsDisabled(boolean value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setIsDisabled(value);
            return this;
        }

        public Builder clearIsDisabled() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearIsDisabled();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasAcqMs() {
            return ((WakeLockProto) this.instance).hasAcqMs();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public long getAcqMs() {
            return ((WakeLockProto) this.instance).getAcqMs();
        }

        public Builder setAcqMs(long value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setAcqMs(value);
            return this;
        }

        public Builder clearAcqMs() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearAcqMs();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasIsNotifiedLong() {
            return ((WakeLockProto) this.instance).hasIsNotifiedLong();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean getIsNotifiedLong() {
            return ((WakeLockProto) this.instance).getIsNotifiedLong();
        }

        public Builder setIsNotifiedLong(boolean value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setIsNotifiedLong(value);
            return this;
        }

        public Builder clearIsNotifiedLong() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearIsNotifiedLong();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasUid() {
            return ((WakeLockProto) this.instance).hasUid();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public int getUid() {
            return ((WakeLockProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasPid() {
            return ((WakeLockProto) this.instance).hasPid();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public int getPid() {
            return ((WakeLockProto) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearPid();
            return this;
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public boolean hasWorkSource() {
            return ((WakeLockProto) this.instance).hasWorkSource();
        }

        @Override // com.android.server.power.WakeLockProtoOrBuilder
        public WorkSourceProto getWorkSource() {
            return ((WakeLockProto) this.instance).getWorkSource();
        }

        public Builder setWorkSource(WorkSourceProto value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setWorkSource((WakeLockProto) value);
            return this;
        }

        public Builder setWorkSource(WorkSourceProto.Builder builderForValue) {
            copyOnWrite();
            ((WakeLockProto) this.instance).setWorkSource((WakeLockProto) builderForValue);
            return this;
        }

        public Builder mergeWorkSource(WorkSourceProto value) {
            copyOnWrite();
            ((WakeLockProto) this.instance).mergeWorkSource(value);
            return this;
        }

        public Builder clearWorkSource() {
            copyOnWrite();
            ((WakeLockProto) this.instance).clearWorkSource();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WakeLockProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WakeLockProto other = (WakeLockProto) arg1;
                this.lockLevel_ = visitor.visitInt(hasLockLevel(), this.lockLevel_, other.hasLockLevel(), other.lockLevel_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.flags_ = (WakeLockFlagsProto) visitor.visitMessage(this.flags_, other.flags_);
                this.isDisabled_ = visitor.visitBoolean(hasIsDisabled(), this.isDisabled_, other.hasIsDisabled(), other.isDisabled_);
                this.acqMs_ = visitor.visitLong(hasAcqMs(), this.acqMs_, other.hasAcqMs(), other.acqMs_);
                this.isNotifiedLong_ = visitor.visitBoolean(hasIsNotifiedLong(), this.isNotifiedLong_, other.hasIsNotifiedLong(), other.isNotifiedLong_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.workSource_ = (WorkSourceProto) visitor.visitMessage(this.workSource_, other.workSource_);
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
                            int rawValue = input.readEnum();
                            if (WakeLockLevelEnum.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.lockLevel_ = rawValue;
                            }
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.tag_ = s;
                        } else if (tag == 26) {
                            WakeLockFlagsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (WakeLockFlagsProto.Builder) this.flags_.toBuilder();
                            }
                            this.flags_ = (WakeLockFlagsProto) input.readMessage(WakeLockFlagsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.flags_);
                                this.flags_ = (WakeLockFlagsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.isDisabled_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.acqMs_ = input.readInt64();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.isNotifiedLong_ = input.readBool();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.uid_ = input.readInt32();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.pid_ = input.readInt32();
                        } else if (tag == 74) {
                            WorkSourceProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 256) == 256) {
                                subBuilder2 = (WorkSourceProto.Builder) this.workSource_.toBuilder();
                            }
                            this.workSource_ = (WorkSourceProto) input.readMessage(WorkSourceProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.workSource_);
                                this.workSource_ = (WorkSourceProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 256;
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
                    synchronized (WakeLockProto.class) {
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

    public static WakeLockProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WakeLockProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
