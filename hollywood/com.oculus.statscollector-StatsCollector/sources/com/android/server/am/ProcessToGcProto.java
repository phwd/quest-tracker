package com.android.server.am;

import com.android.server.am.ProcessRecordProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ProcessToGcProto extends GeneratedMessageLite<ProcessToGcProto, Builder> implements ProcessToGcProtoOrBuilder {
    private static final ProcessToGcProto DEFAULT_INSTANCE = new ProcessToGcProto();
    public static final int LAST_GCED_MS_FIELD_NUMBER = 4;
    public static final int LAST_LOW_MEMORY_MS_FIELD_NUMBER = 5;
    public static final int NOW_UPTIME_MS_FIELD_NUMBER = 3;
    private static volatile Parser<ProcessToGcProto> PARSER = null;
    public static final int PROC_FIELD_NUMBER = 1;
    public static final int REPORT_LOW_MEMORY_FIELD_NUMBER = 2;
    private int bitField0_;
    private long lastGcedMs_ = 0;
    private long lastLowMemoryMs_ = 0;
    private long nowUptimeMs_ = 0;
    private ProcessRecordProto proc_;
    private boolean reportLowMemory_ = false;

    private ProcessToGcProto() {
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean hasProc() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public ProcessRecordProto getProc() {
        ProcessRecordProto processRecordProto = this.proc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProc(ProcessRecordProto value) {
        if (value != null) {
            this.proc_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProc(ProcessRecordProto.Builder builderForValue) {
        this.proc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProc(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.proc_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.proc_ = value;
        } else {
            this.proc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.proc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProc() {
        this.proc_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean hasReportLowMemory() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean getReportLowMemory() {
        return this.reportLowMemory_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportLowMemory(boolean value) {
        this.bitField0_ |= 2;
        this.reportLowMemory_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportLowMemory() {
        this.bitField0_ &= -3;
        this.reportLowMemory_ = false;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean hasNowUptimeMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public long getNowUptimeMs() {
        return this.nowUptimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNowUptimeMs(long value) {
        this.bitField0_ |= 4;
        this.nowUptimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNowUptimeMs() {
        this.bitField0_ &= -5;
        this.nowUptimeMs_ = 0;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean hasLastGcedMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public long getLastGcedMs() {
        return this.lastGcedMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastGcedMs(long value) {
        this.bitField0_ |= 8;
        this.lastGcedMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastGcedMs() {
        this.bitField0_ &= -9;
        this.lastGcedMs_ = 0;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public boolean hasLastLowMemoryMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ProcessToGcProtoOrBuilder
    public long getLastLowMemoryMs() {
        return this.lastLowMemoryMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastLowMemoryMs(long value) {
        this.bitField0_ |= 16;
        this.lastLowMemoryMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastLowMemoryMs() {
        this.bitField0_ &= -17;
        this.lastLowMemoryMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getProc());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.reportLowMemory_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.nowUptimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.lastGcedMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.lastLowMemoryMs_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getProc());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.reportLowMemory_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.nowUptimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.lastGcedMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.lastLowMemoryMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessToGcProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessToGcProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessToGcProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessToGcProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessToGcProto parseFrom(InputStream input) throws IOException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessToGcProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessToGcProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessToGcProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessToGcProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessToGcProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessToGcProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessToGcProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessToGcProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessToGcProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessToGcProto, Builder> implements ProcessToGcProtoOrBuilder {
        private Builder() {
            super(ProcessToGcProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean hasProc() {
            return ((ProcessToGcProto) this.instance).hasProc();
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public ProcessRecordProto getProc() {
            return ((ProcessToGcProto) this.instance).getProc();
        }

        public Builder setProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setProc((ProcessToGcProto) value);
            return this;
        }

        public Builder setProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setProc((ProcessToGcProto) builderForValue);
            return this;
        }

        public Builder mergeProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).mergeProc(value);
            return this;
        }

        public Builder clearProc() {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).clearProc();
            return this;
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean hasReportLowMemory() {
            return ((ProcessToGcProto) this.instance).hasReportLowMemory();
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean getReportLowMemory() {
            return ((ProcessToGcProto) this.instance).getReportLowMemory();
        }

        public Builder setReportLowMemory(boolean value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setReportLowMemory(value);
            return this;
        }

        public Builder clearReportLowMemory() {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).clearReportLowMemory();
            return this;
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean hasNowUptimeMs() {
            return ((ProcessToGcProto) this.instance).hasNowUptimeMs();
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public long getNowUptimeMs() {
            return ((ProcessToGcProto) this.instance).getNowUptimeMs();
        }

        public Builder setNowUptimeMs(long value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setNowUptimeMs(value);
            return this;
        }

        public Builder clearNowUptimeMs() {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).clearNowUptimeMs();
            return this;
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean hasLastGcedMs() {
            return ((ProcessToGcProto) this.instance).hasLastGcedMs();
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public long getLastGcedMs() {
            return ((ProcessToGcProto) this.instance).getLastGcedMs();
        }

        public Builder setLastGcedMs(long value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setLastGcedMs(value);
            return this;
        }

        public Builder clearLastGcedMs() {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).clearLastGcedMs();
            return this;
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public boolean hasLastLowMemoryMs() {
            return ((ProcessToGcProto) this.instance).hasLastLowMemoryMs();
        }

        @Override // com.android.server.am.ProcessToGcProtoOrBuilder
        public long getLastLowMemoryMs() {
            return ((ProcessToGcProto) this.instance).getLastLowMemoryMs();
        }

        public Builder setLastLowMemoryMs(long value) {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).setLastLowMemoryMs(value);
            return this;
        }

        public Builder clearLastLowMemoryMs() {
            copyOnWrite();
            ((ProcessToGcProto) this.instance).clearLastLowMemoryMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessToGcProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessToGcProto other = (ProcessToGcProto) arg1;
                this.proc_ = (ProcessRecordProto) visitor.visitMessage(this.proc_, other.proc_);
                this.reportLowMemory_ = visitor.visitBoolean(hasReportLowMemory(), this.reportLowMemory_, other.hasReportLowMemory(), other.reportLowMemory_);
                this.nowUptimeMs_ = visitor.visitLong(hasNowUptimeMs(), this.nowUptimeMs_, other.hasNowUptimeMs(), other.nowUptimeMs_);
                this.lastGcedMs_ = visitor.visitLong(hasLastGcedMs(), this.lastGcedMs_, other.hasLastGcedMs(), other.lastGcedMs_);
                this.lastLowMemoryMs_ = visitor.visitLong(hasLastLowMemoryMs(), this.lastLowMemoryMs_, other.hasLastLowMemoryMs(), other.lastLowMemoryMs_);
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
                            ProcessRecordProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ProcessRecordProto.Builder) this.proc_.toBuilder();
                            }
                            this.proc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.proc_);
                                this.proc_ = (ProcessRecordProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.reportLowMemory_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.nowUptimeMs_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.lastGcedMs_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.lastLowMemoryMs_ = input.readInt64();
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
                    synchronized (ProcessToGcProto.class) {
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

    public static ProcessToGcProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessToGcProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
