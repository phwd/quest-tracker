package com.android.server.job;

import com.android.server.job.MaxJobCountsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class MaxJobCountsPerMemoryTrimLevelProto extends GeneratedMessageLite<MaxJobCountsPerMemoryTrimLevelProto, Builder> implements MaxJobCountsPerMemoryTrimLevelProtoOrBuilder {
    public static final int CRITICAL_FIELD_NUMBER = 4;
    private static final MaxJobCountsPerMemoryTrimLevelProto DEFAULT_INSTANCE = new MaxJobCountsPerMemoryTrimLevelProto();
    public static final int LOW_FIELD_NUMBER = 3;
    public static final int MODERATE_FIELD_NUMBER = 2;
    public static final int NORMAL_FIELD_NUMBER = 1;
    private static volatile Parser<MaxJobCountsPerMemoryTrimLevelProto> PARSER;
    private int bitField0_;
    private MaxJobCountsProto critical_;
    private MaxJobCountsProto low_;
    private MaxJobCountsProto moderate_;
    private MaxJobCountsProto normal_;

    private MaxJobCountsPerMemoryTrimLevelProto() {
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public boolean hasNormal() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public MaxJobCountsProto getNormal() {
        MaxJobCountsProto maxJobCountsProto = this.normal_;
        return maxJobCountsProto == null ? MaxJobCountsProto.getDefaultInstance() : maxJobCountsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(MaxJobCountsProto value) {
        if (value != null) {
            this.normal_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNormal(MaxJobCountsProto.Builder builderForValue) {
        this.normal_ = (MaxJobCountsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNormal(MaxJobCountsProto value) {
        MaxJobCountsProto maxJobCountsProto = this.normal_;
        if (maxJobCountsProto == null || maxJobCountsProto == MaxJobCountsProto.getDefaultInstance()) {
            this.normal_ = value;
        } else {
            this.normal_ = (MaxJobCountsProto) ((MaxJobCountsProto.Builder) MaxJobCountsProto.newBuilder(this.normal_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNormal() {
        this.normal_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public boolean hasModerate() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public MaxJobCountsProto getModerate() {
        MaxJobCountsProto maxJobCountsProto = this.moderate_;
        return maxJobCountsProto == null ? MaxJobCountsProto.getDefaultInstance() : maxJobCountsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModerate(MaxJobCountsProto value) {
        if (value != null) {
            this.moderate_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModerate(MaxJobCountsProto.Builder builderForValue) {
        this.moderate_ = (MaxJobCountsProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeModerate(MaxJobCountsProto value) {
        MaxJobCountsProto maxJobCountsProto = this.moderate_;
        if (maxJobCountsProto == null || maxJobCountsProto == MaxJobCountsProto.getDefaultInstance()) {
            this.moderate_ = value;
        } else {
            this.moderate_ = (MaxJobCountsProto) ((MaxJobCountsProto.Builder) MaxJobCountsProto.newBuilder(this.moderate_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModerate() {
        this.moderate_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public boolean hasLow() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public MaxJobCountsProto getLow() {
        MaxJobCountsProto maxJobCountsProto = this.low_;
        return maxJobCountsProto == null ? MaxJobCountsProto.getDefaultInstance() : maxJobCountsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLow(MaxJobCountsProto value) {
        if (value != null) {
            this.low_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLow(MaxJobCountsProto.Builder builderForValue) {
        this.low_ = (MaxJobCountsProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLow(MaxJobCountsProto value) {
        MaxJobCountsProto maxJobCountsProto = this.low_;
        if (maxJobCountsProto == null || maxJobCountsProto == MaxJobCountsProto.getDefaultInstance()) {
            this.low_ = value;
        } else {
            this.low_ = (MaxJobCountsProto) ((MaxJobCountsProto.Builder) MaxJobCountsProto.newBuilder(this.low_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLow() {
        this.low_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public boolean hasCritical() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
    public MaxJobCountsProto getCritical() {
        MaxJobCountsProto maxJobCountsProto = this.critical_;
        return maxJobCountsProto == null ? MaxJobCountsProto.getDefaultInstance() : maxJobCountsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCritical(MaxJobCountsProto value) {
        if (value != null) {
            this.critical_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCritical(MaxJobCountsProto.Builder builderForValue) {
        this.critical_ = (MaxJobCountsProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCritical(MaxJobCountsProto value) {
        MaxJobCountsProto maxJobCountsProto = this.critical_;
        if (maxJobCountsProto == null || maxJobCountsProto == MaxJobCountsProto.getDefaultInstance()) {
            this.critical_ = value;
        } else {
            this.critical_ = (MaxJobCountsProto) ((MaxJobCountsProto.Builder) MaxJobCountsProto.newBuilder(this.critical_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCritical() {
        this.critical_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getNormal());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getModerate());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getLow());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getCritical());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getNormal());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getModerate());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getLow());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getCritical());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(InputStream input) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(CodedInputStream input) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MaxJobCountsPerMemoryTrimLevelProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MaxJobCountsPerMemoryTrimLevelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MaxJobCountsPerMemoryTrimLevelProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MaxJobCountsPerMemoryTrimLevelProto, Builder> implements MaxJobCountsPerMemoryTrimLevelProtoOrBuilder {
        private Builder() {
            super(MaxJobCountsPerMemoryTrimLevelProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public boolean hasNormal() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).hasNormal();
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public MaxJobCountsProto getNormal() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).getNormal();
        }

        public Builder setNormal(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setNormal((MaxJobCountsPerMemoryTrimLevelProto) value);
            return this;
        }

        public Builder setNormal(MaxJobCountsProto.Builder builderForValue) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setNormal((MaxJobCountsPerMemoryTrimLevelProto) builderForValue);
            return this;
        }

        public Builder mergeNormal(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).mergeNormal(value);
            return this;
        }

        public Builder clearNormal() {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).clearNormal();
            return this;
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public boolean hasModerate() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).hasModerate();
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public MaxJobCountsProto getModerate() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).getModerate();
        }

        public Builder setModerate(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setModerate((MaxJobCountsPerMemoryTrimLevelProto) value);
            return this;
        }

        public Builder setModerate(MaxJobCountsProto.Builder builderForValue) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setModerate((MaxJobCountsPerMemoryTrimLevelProto) builderForValue);
            return this;
        }

        public Builder mergeModerate(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).mergeModerate(value);
            return this;
        }

        public Builder clearModerate() {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).clearModerate();
            return this;
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public boolean hasLow() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).hasLow();
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public MaxJobCountsProto getLow() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).getLow();
        }

        public Builder setLow(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setLow((MaxJobCountsPerMemoryTrimLevelProto) value);
            return this;
        }

        public Builder setLow(MaxJobCountsProto.Builder builderForValue) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setLow((MaxJobCountsPerMemoryTrimLevelProto) builderForValue);
            return this;
        }

        public Builder mergeLow(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).mergeLow(value);
            return this;
        }

        public Builder clearLow() {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).clearLow();
            return this;
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public boolean hasCritical() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).hasCritical();
        }

        @Override // com.android.server.job.MaxJobCountsPerMemoryTrimLevelProtoOrBuilder
        public MaxJobCountsProto getCritical() {
            return ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).getCritical();
        }

        public Builder setCritical(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setCritical((MaxJobCountsPerMemoryTrimLevelProto) value);
            return this;
        }

        public Builder setCritical(MaxJobCountsProto.Builder builderForValue) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).setCritical((MaxJobCountsPerMemoryTrimLevelProto) builderForValue);
            return this;
        }

        public Builder mergeCritical(MaxJobCountsProto value) {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).mergeCritical(value);
            return this;
        }

        public Builder clearCritical() {
            copyOnWrite();
            ((MaxJobCountsPerMemoryTrimLevelProto) this.instance).clearCritical();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MaxJobCountsPerMemoryTrimLevelProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MaxJobCountsPerMemoryTrimLevelProto other = (MaxJobCountsPerMemoryTrimLevelProto) arg1;
                this.normal_ = (MaxJobCountsProto) visitor.visitMessage(this.normal_, other.normal_);
                this.moderate_ = (MaxJobCountsProto) visitor.visitMessage(this.moderate_, other.moderate_);
                this.low_ = (MaxJobCountsProto) visitor.visitMessage(this.low_, other.low_);
                this.critical_ = (MaxJobCountsProto) visitor.visitMessage(this.critical_, other.critical_);
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
                        } else if (tag == 10) {
                            MaxJobCountsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (MaxJobCountsProto.Builder) this.normal_.toBuilder();
                            }
                            this.normal_ = (MaxJobCountsProto) input.readMessage(MaxJobCountsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.normal_);
                                this.normal_ = (MaxJobCountsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            MaxJobCountsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (MaxJobCountsProto.Builder) this.moderate_.toBuilder();
                            }
                            this.moderate_ = (MaxJobCountsProto) input.readMessage(MaxJobCountsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.moderate_);
                                this.moderate_ = (MaxJobCountsProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            MaxJobCountsProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (MaxJobCountsProto.Builder) this.low_.toBuilder();
                            }
                            this.low_ = (MaxJobCountsProto) input.readMessage(MaxJobCountsProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.low_);
                                this.low_ = (MaxJobCountsProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            MaxJobCountsProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (MaxJobCountsProto.Builder) this.critical_.toBuilder();
                            }
                            this.critical_ = (MaxJobCountsProto) input.readMessage(MaxJobCountsProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.critical_);
                                this.critical_ = (MaxJobCountsProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
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
                    synchronized (MaxJobCountsPerMemoryTrimLevelProto.class) {
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

    public static MaxJobCountsPerMemoryTrimLevelProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MaxJobCountsPerMemoryTrimLevelProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
