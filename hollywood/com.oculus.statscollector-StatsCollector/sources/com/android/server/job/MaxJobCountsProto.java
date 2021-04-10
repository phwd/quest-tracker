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

public final class MaxJobCountsProto extends GeneratedMessageLite<MaxJobCountsProto, Builder> implements MaxJobCountsProtoOrBuilder {
    private static final MaxJobCountsProto DEFAULT_INSTANCE = new MaxJobCountsProto();
    public static final int MAX_BG_FIELD_NUMBER = 2;
    public static final int MIN_BG_FIELD_NUMBER = 3;
    private static volatile Parser<MaxJobCountsProto> PARSER = null;
    public static final int TOTAL_JOBS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int maxBg_ = 0;
    private int minBg_ = 0;
    private int totalJobs_ = 0;

    private MaxJobCountsProto() {
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public boolean hasTotalJobs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public int getTotalJobs() {
        return this.totalJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalJobs(int value) {
        this.bitField0_ |= 1;
        this.totalJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalJobs() {
        this.bitField0_ &= -2;
        this.totalJobs_ = 0;
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public boolean hasMaxBg() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public int getMaxBg() {
        return this.maxBg_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxBg(int value) {
        this.bitField0_ |= 2;
        this.maxBg_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxBg() {
        this.bitField0_ &= -3;
        this.maxBg_ = 0;
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public boolean hasMinBg() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
    public int getMinBg() {
        return this.minBg_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinBg(int value) {
        this.bitField0_ |= 4;
        this.minBg_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinBg() {
        this.bitField0_ &= -5;
        this.minBg_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.totalJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.maxBg_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.minBg_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.totalJobs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.maxBg_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.minBg_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MaxJobCountsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MaxJobCountsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MaxJobCountsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MaxJobCountsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MaxJobCountsProto parseFrom(InputStream input) throws IOException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MaxJobCountsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MaxJobCountsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MaxJobCountsProto parseFrom(CodedInputStream input) throws IOException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MaxJobCountsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MaxJobCountsProto, Builder> implements MaxJobCountsProtoOrBuilder {
        private Builder() {
            super(MaxJobCountsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public boolean hasTotalJobs() {
            return ((MaxJobCountsProto) this.instance).hasTotalJobs();
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public int getTotalJobs() {
            return ((MaxJobCountsProto) this.instance).getTotalJobs();
        }

        public Builder setTotalJobs(int value) {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).setTotalJobs(value);
            return this;
        }

        public Builder clearTotalJobs() {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).clearTotalJobs();
            return this;
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public boolean hasMaxBg() {
            return ((MaxJobCountsProto) this.instance).hasMaxBg();
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public int getMaxBg() {
            return ((MaxJobCountsProto) this.instance).getMaxBg();
        }

        public Builder setMaxBg(int value) {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).setMaxBg(value);
            return this;
        }

        public Builder clearMaxBg() {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).clearMaxBg();
            return this;
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public boolean hasMinBg() {
            return ((MaxJobCountsProto) this.instance).hasMinBg();
        }

        @Override // com.android.server.job.MaxJobCountsProtoOrBuilder
        public int getMinBg() {
            return ((MaxJobCountsProto) this.instance).getMinBg();
        }

        public Builder setMinBg(int value) {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).setMinBg(value);
            return this;
        }

        public Builder clearMinBg() {
            copyOnWrite();
            ((MaxJobCountsProto) this.instance).clearMinBg();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MaxJobCountsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MaxJobCountsProto other = (MaxJobCountsProto) arg1;
                this.totalJobs_ = visitor.visitInt(hasTotalJobs(), this.totalJobs_, other.hasTotalJobs(), other.totalJobs_);
                this.maxBg_ = visitor.visitInt(hasMaxBg(), this.maxBg_, other.hasMaxBg(), other.maxBg_);
                this.minBg_ = visitor.visitInt(hasMinBg(), this.minBg_, other.hasMinBg(), other.minBg_);
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
                            this.totalJobs_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.maxBg_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.minBg_ = input.readInt32();
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
                    synchronized (MaxJobCountsProto.class) {
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

    public static MaxJobCountsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MaxJobCountsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
