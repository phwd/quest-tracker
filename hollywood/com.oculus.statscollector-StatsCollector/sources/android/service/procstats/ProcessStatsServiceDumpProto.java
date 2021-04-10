package android.service.procstats;

import android.service.procstats.ProcessStatsSectionProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ProcessStatsServiceDumpProto extends GeneratedMessageLite<ProcessStatsServiceDumpProto, Builder> implements ProcessStatsServiceDumpProtoOrBuilder {
    private static final ProcessStatsServiceDumpProto DEFAULT_INSTANCE = new ProcessStatsServiceDumpProto();
    private static volatile Parser<ProcessStatsServiceDumpProto> PARSER = null;
    public static final int PROCSTATS_NOW_FIELD_NUMBER = 1;
    public static final int PROCSTATS_OVER_24HRS_FIELD_NUMBER = 3;
    public static final int PROCSTATS_OVER_3HRS_FIELD_NUMBER = 2;
    private int bitField0_;
    private ProcessStatsSectionProto procstatsNow_;
    private ProcessStatsSectionProto procstatsOver24Hrs_;
    private ProcessStatsSectionProto procstatsOver3Hrs_;

    private ProcessStatsServiceDumpProto() {
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public boolean hasProcstatsNow() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public ProcessStatsSectionProto getProcstatsNow() {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsNow_;
        return processStatsSectionProto == null ? ProcessStatsSectionProto.getDefaultInstance() : processStatsSectionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsNow(ProcessStatsSectionProto value) {
        if (value != null) {
            this.procstatsNow_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsNow(ProcessStatsSectionProto.Builder builderForValue) {
        this.procstatsNow_ = (ProcessStatsSectionProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcstatsNow(ProcessStatsSectionProto value) {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsNow_;
        if (processStatsSectionProto == null || processStatsSectionProto == ProcessStatsSectionProto.getDefaultInstance()) {
            this.procstatsNow_ = value;
        } else {
            this.procstatsNow_ = (ProcessStatsSectionProto) ((ProcessStatsSectionProto.Builder) ProcessStatsSectionProto.newBuilder(this.procstatsNow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcstatsNow() {
        this.procstatsNow_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public boolean hasProcstatsOver3Hrs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public ProcessStatsSectionProto getProcstatsOver3Hrs() {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsOver3Hrs_;
        return processStatsSectionProto == null ? ProcessStatsSectionProto.getDefaultInstance() : processStatsSectionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsOver3Hrs(ProcessStatsSectionProto value) {
        if (value != null) {
            this.procstatsOver3Hrs_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsOver3Hrs(ProcessStatsSectionProto.Builder builderForValue) {
        this.procstatsOver3Hrs_ = (ProcessStatsSectionProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcstatsOver3Hrs(ProcessStatsSectionProto value) {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsOver3Hrs_;
        if (processStatsSectionProto == null || processStatsSectionProto == ProcessStatsSectionProto.getDefaultInstance()) {
            this.procstatsOver3Hrs_ = value;
        } else {
            this.procstatsOver3Hrs_ = (ProcessStatsSectionProto) ((ProcessStatsSectionProto.Builder) ProcessStatsSectionProto.newBuilder(this.procstatsOver3Hrs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcstatsOver3Hrs() {
        this.procstatsOver3Hrs_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public boolean hasProcstatsOver24Hrs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
    public ProcessStatsSectionProto getProcstatsOver24Hrs() {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsOver24Hrs_;
        return processStatsSectionProto == null ? ProcessStatsSectionProto.getDefaultInstance() : processStatsSectionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsOver24Hrs(ProcessStatsSectionProto value) {
        if (value != null) {
            this.procstatsOver24Hrs_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstatsOver24Hrs(ProcessStatsSectionProto.Builder builderForValue) {
        this.procstatsOver24Hrs_ = (ProcessStatsSectionProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcstatsOver24Hrs(ProcessStatsSectionProto value) {
        ProcessStatsSectionProto processStatsSectionProto = this.procstatsOver24Hrs_;
        if (processStatsSectionProto == null || processStatsSectionProto == ProcessStatsSectionProto.getDefaultInstance()) {
            this.procstatsOver24Hrs_ = value;
        } else {
            this.procstatsOver24Hrs_ = (ProcessStatsSectionProto) ((ProcessStatsSectionProto.Builder) ProcessStatsSectionProto.newBuilder(this.procstatsOver24Hrs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcstatsOver24Hrs() {
        this.procstatsOver24Hrs_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getProcstatsNow());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getProcstatsOver3Hrs());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getProcstatsOver24Hrs());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getProcstatsNow());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getProcstatsOver3Hrs());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getProcstatsOver24Hrs());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessStatsServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsServiceDumpProto, Builder> implements ProcessStatsServiceDumpProtoOrBuilder {
        private Builder() {
            super(ProcessStatsServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public boolean hasProcstatsNow() {
            return ((ProcessStatsServiceDumpProto) this.instance).hasProcstatsNow();
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public ProcessStatsSectionProto getProcstatsNow() {
            return ((ProcessStatsServiceDumpProto) this.instance).getProcstatsNow();
        }

        public Builder setProcstatsNow(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsNow((ProcessStatsServiceDumpProto) value);
            return this;
        }

        public Builder setProcstatsNow(ProcessStatsSectionProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsNow((ProcessStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeProcstatsNow(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).mergeProcstatsNow(value);
            return this;
        }

        public Builder clearProcstatsNow() {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).clearProcstatsNow();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public boolean hasProcstatsOver3Hrs() {
            return ((ProcessStatsServiceDumpProto) this.instance).hasProcstatsOver3Hrs();
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public ProcessStatsSectionProto getProcstatsOver3Hrs() {
            return ((ProcessStatsServiceDumpProto) this.instance).getProcstatsOver3Hrs();
        }

        public Builder setProcstatsOver3Hrs(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsOver3Hrs((ProcessStatsServiceDumpProto) value);
            return this;
        }

        public Builder setProcstatsOver3Hrs(ProcessStatsSectionProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsOver3Hrs((ProcessStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeProcstatsOver3Hrs(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).mergeProcstatsOver3Hrs(value);
            return this;
        }

        public Builder clearProcstatsOver3Hrs() {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).clearProcstatsOver3Hrs();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public boolean hasProcstatsOver24Hrs() {
            return ((ProcessStatsServiceDumpProto) this.instance).hasProcstatsOver24Hrs();
        }

        @Override // android.service.procstats.ProcessStatsServiceDumpProtoOrBuilder
        public ProcessStatsSectionProto getProcstatsOver24Hrs() {
            return ((ProcessStatsServiceDumpProto) this.instance).getProcstatsOver24Hrs();
        }

        public Builder setProcstatsOver24Hrs(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsOver24Hrs((ProcessStatsServiceDumpProto) value);
            return this;
        }

        public Builder setProcstatsOver24Hrs(ProcessStatsSectionProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).setProcstatsOver24Hrs((ProcessStatsServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeProcstatsOver24Hrs(ProcessStatsSectionProto value) {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).mergeProcstatsOver24Hrs(value);
            return this;
        }

        public Builder clearProcstatsOver24Hrs() {
            copyOnWrite();
            ((ProcessStatsServiceDumpProto) this.instance).clearProcstatsOver24Hrs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsServiceDumpProto other = (ProcessStatsServiceDumpProto) arg1;
                this.procstatsNow_ = (ProcessStatsSectionProto) visitor.visitMessage(this.procstatsNow_, other.procstatsNow_);
                this.procstatsOver3Hrs_ = (ProcessStatsSectionProto) visitor.visitMessage(this.procstatsOver3Hrs_, other.procstatsOver3Hrs_);
                this.procstatsOver24Hrs_ = (ProcessStatsSectionProto) visitor.visitMessage(this.procstatsOver24Hrs_, other.procstatsOver24Hrs_);
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
                            ProcessStatsSectionProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ProcessStatsSectionProto.Builder) this.procstatsNow_.toBuilder();
                            }
                            this.procstatsNow_ = (ProcessStatsSectionProto) input.readMessage(ProcessStatsSectionProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.procstatsNow_);
                                this.procstatsNow_ = (ProcessStatsSectionProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            ProcessStatsSectionProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (ProcessStatsSectionProto.Builder) this.procstatsOver3Hrs_.toBuilder();
                            }
                            this.procstatsOver3Hrs_ = (ProcessStatsSectionProto) input.readMessage(ProcessStatsSectionProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.procstatsOver3Hrs_);
                                this.procstatsOver3Hrs_ = (ProcessStatsSectionProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            ProcessStatsSectionProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (ProcessStatsSectionProto.Builder) this.procstatsOver24Hrs_.toBuilder();
                            }
                            this.procstatsOver24Hrs_ = (ProcessStatsSectionProto) input.readMessage(ProcessStatsSectionProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.procstatsOver24Hrs_);
                                this.procstatsOver24Hrs_ = (ProcessStatsSectionProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (ProcessStatsServiceDumpProto.class) {
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

    public static ProcessStatsServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
