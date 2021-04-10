package com.android.server.job;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class JobStatusShortInfoProto extends GeneratedMessageLite<JobStatusShortInfoProto, Builder> implements JobStatusShortInfoProtoOrBuilder {
    public static final int BATTERY_NAME_FIELD_NUMBER = 3;
    public static final int CALLING_UID_FIELD_NUMBER = 1;
    private static final JobStatusShortInfoProto DEFAULT_INSTANCE = new JobStatusShortInfoProto();
    public static final int JOB_ID_FIELD_NUMBER = 2;
    private static volatile Parser<JobStatusShortInfoProto> PARSER;
    private String batteryName_ = "";
    private int bitField0_;
    private int callingUid_ = 0;
    private int jobId_ = 0;

    private JobStatusShortInfoProto() {
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public boolean hasCallingUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public int getCallingUid() {
        return this.callingUid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCallingUid(int value) {
        this.bitField0_ |= 1;
        this.callingUid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCallingUid() {
        this.bitField0_ &= -2;
        this.callingUid_ = 0;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public boolean hasJobId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public int getJobId() {
        return this.jobId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobId(int value) {
        this.bitField0_ |= 2;
        this.jobId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJobId() {
        this.bitField0_ &= -3;
        this.jobId_ = 0;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public boolean hasBatteryName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public String getBatteryName() {
        return this.batteryName_;
    }

    @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
    public ByteString getBatteryNameBytes() {
        return ByteString.copyFromUtf8(this.batteryName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryName(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.batteryName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryName() {
        this.bitField0_ &= -5;
        this.batteryName_ = getDefaultInstance().getBatteryName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.batteryName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.callingUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.jobId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getBatteryName());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.callingUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.jobId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getBatteryName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static JobStatusShortInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobStatusShortInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobStatusShortInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobStatusShortInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobStatusShortInfoProto parseFrom(InputStream input) throws IOException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusShortInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobStatusShortInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobStatusShortInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusShortInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusShortInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobStatusShortInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobStatusShortInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobStatusShortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobStatusShortInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobStatusShortInfoProto, Builder> implements JobStatusShortInfoProtoOrBuilder {
        private Builder() {
            super(JobStatusShortInfoProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public boolean hasCallingUid() {
            return ((JobStatusShortInfoProto) this.instance).hasCallingUid();
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public int getCallingUid() {
            return ((JobStatusShortInfoProto) this.instance).getCallingUid();
        }

        public Builder setCallingUid(int value) {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).setCallingUid(value);
            return this;
        }

        public Builder clearCallingUid() {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).clearCallingUid();
            return this;
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public boolean hasJobId() {
            return ((JobStatusShortInfoProto) this.instance).hasJobId();
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public int getJobId() {
            return ((JobStatusShortInfoProto) this.instance).getJobId();
        }

        public Builder setJobId(int value) {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).setJobId(value);
            return this;
        }

        public Builder clearJobId() {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).clearJobId();
            return this;
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public boolean hasBatteryName() {
            return ((JobStatusShortInfoProto) this.instance).hasBatteryName();
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public String getBatteryName() {
            return ((JobStatusShortInfoProto) this.instance).getBatteryName();
        }

        @Override // com.android.server.job.JobStatusShortInfoProtoOrBuilder
        public ByteString getBatteryNameBytes() {
            return ((JobStatusShortInfoProto) this.instance).getBatteryNameBytes();
        }

        public Builder setBatteryName(String value) {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).setBatteryName(value);
            return this;
        }

        public Builder clearBatteryName() {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).clearBatteryName();
            return this;
        }

        public Builder setBatteryNameBytes(ByteString value) {
            copyOnWrite();
            ((JobStatusShortInfoProto) this.instance).setBatteryNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobStatusShortInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                JobStatusShortInfoProto other = (JobStatusShortInfoProto) arg1;
                this.callingUid_ = visitor.visitInt(hasCallingUid(), this.callingUid_, other.hasCallingUid(), other.callingUid_);
                this.jobId_ = visitor.visitInt(hasJobId(), this.jobId_, other.hasJobId(), other.jobId_);
                this.batteryName_ = visitor.visitString(hasBatteryName(), this.batteryName_, other.hasBatteryName(), other.batteryName_);
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
                            this.callingUid_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.jobId_ = input.readInt32();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.batteryName_ = s;
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
                    synchronized (JobStatusShortInfoProto.class) {
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

    public static JobStatusShortInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobStatusShortInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
