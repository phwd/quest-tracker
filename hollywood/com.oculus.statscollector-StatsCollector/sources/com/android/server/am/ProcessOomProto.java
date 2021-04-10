package com.android.server.am;

import android.app.ProcessStateEnum;
import android.content.ComponentNameProto;
import com.android.server.am.ProcessRecordProto;
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

public final class ProcessOomProto extends GeneratedMessageLite<ProcessOomProto, Builder> implements ProcessOomProtoOrBuilder {
    public static final int ACTIVITIES_FIELD_NUMBER = 5;
    public static final int ADJ_SOURCE_OBJECT_FIELD_NUMBER = 14;
    public static final int ADJ_SOURCE_PROC_FIELD_NUMBER = 13;
    public static final int ADJ_TARGET_COMPONENT_NAME_FIELD_NUMBER = 11;
    public static final int ADJ_TARGET_OBJECT_FIELD_NUMBER = 12;
    public static final int ADJ_TYPE_FIELD_NUMBER = 10;
    private static final ProcessOomProto DEFAULT_INSTANCE = new ProcessOomProto();
    public static final int DETAIL_FIELD_NUMBER = 15;
    public static final int NUM_FIELD_NUMBER = 2;
    public static final int OOM_ADJ_FIELD_NUMBER = 3;
    private static volatile Parser<ProcessOomProto> PARSER = null;
    public static final int PERSISTENT_FIELD_NUMBER = 1;
    public static final int PROC_FIELD_NUMBER = 9;
    public static final int SCHED_GROUP_FIELD_NUMBER = 4;
    public static final int SERVICES_FIELD_NUMBER = 6;
    public static final int STATE_FIELD_NUMBER = 7;
    public static final int TRIM_MEMORY_LEVEL_FIELD_NUMBER = 8;
    private int adjSourceCase_ = 0;
    private Object adjSource_;
    private int adjTargetCase_ = 0;
    private Object adjTarget_;
    private String adjType_ = "";
    private int bitField0_;
    private Detail detail_;
    private int foregroundCase_ = 0;
    private Object foreground_;
    private int num_ = 0;
    private String oomAdj_ = "";
    private boolean persistent_ = false;
    private ProcessRecordProto proc_;
    private int schedGroup_ = -1;
    private int state_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
    private int trimMemoryLevel_ = 0;

    public interface DetailOrBuilder extends MessageLiteOrBuilder {
        boolean getCached();

        int getCurAdj();

        int getCurRawAdj();

        ProcessStateEnum getCurrentState();

        boolean getEmpty();

        boolean getHasAboveClient();

        String getLastCachedPss();

        ByteString getLastCachedPssBytes();

        String getLastPss();

        ByteString getLastPssBytes();

        String getLastSwapPss();

        ByteString getLastSwapPssBytes();

        int getMaxAdj();

        Detail.CpuRunTime getServiceRunTime();

        int getSetAdj();

        int getSetRawAdj();

        ProcessStateEnum getSetState();

        boolean hasCached();

        boolean hasCurAdj();

        boolean hasCurRawAdj();

        boolean hasCurrentState();

        boolean hasEmpty();

        boolean hasHasAboveClient();

        boolean hasLastCachedPss();

        boolean hasLastPss();

        boolean hasLastSwapPss();

        boolean hasMaxAdj();

        boolean hasServiceRunTime();

        boolean hasSetAdj();

        boolean hasSetRawAdj();

        boolean hasSetState();
    }

    private ProcessOomProto() {
    }

    public enum SchedGroup implements Internal.EnumLite {
        SCHED_GROUP_UNKNOWN(-1),
        SCHED_GROUP_BACKGROUND(0),
        SCHED_GROUP_DEFAULT(1),
        SCHED_GROUP_TOP_APP(2),
        SCHED_GROUP_TOP_APP_BOUND(3),
        SCHED_GROUP_COMPOSITOR(15);
        
        public static final int SCHED_GROUP_BACKGROUND_VALUE = 0;
        public static final int SCHED_GROUP_COMPOSITOR_VALUE = 15;
        public static final int SCHED_GROUP_DEFAULT_VALUE = 1;
        public static final int SCHED_GROUP_TOP_APP_BOUND_VALUE = 3;
        public static final int SCHED_GROUP_TOP_APP_VALUE = 2;
        public static final int SCHED_GROUP_UNKNOWN_VALUE = -1;
        private static final Internal.EnumLiteMap<SchedGroup> internalValueMap = new Internal.EnumLiteMap<SchedGroup>() {
            /* class com.android.server.am.ProcessOomProto.SchedGroup.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SchedGroup findValueByNumber(int number) {
                return SchedGroup.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SchedGroup valueOf(int value2) {
            return forNumber(value2);
        }

        public static SchedGroup forNumber(int value2) {
            if (value2 == -1) {
                return SCHED_GROUP_UNKNOWN;
            }
            if (value2 == 0) {
                return SCHED_GROUP_BACKGROUND;
            }
            if (value2 == 1) {
                return SCHED_GROUP_DEFAULT;
            }
            if (value2 == 2) {
                return SCHED_GROUP_TOP_APP;
            }
            if (value2 == 3) {
                return SCHED_GROUP_TOP_APP_BOUND;
            }
            if (value2 != 15) {
                return null;
            }
            return SCHED_GROUP_COMPOSITOR;
        }

        public static Internal.EnumLiteMap<SchedGroup> internalGetValueMap() {
            return internalValueMap;
        }

        private SchedGroup(int value2) {
            this.value = value2;
        }
    }

    public static final class Detail extends GeneratedMessageLite<Detail, Builder> implements DetailOrBuilder {
        public static final int CACHED_FIELD_NUMBER = 12;
        public static final int CURRENT_STATE_FIELD_NUMBER = 7;
        public static final int CUR_ADJ_FIELD_NUMBER = 4;
        public static final int CUR_RAW_ADJ_FIELD_NUMBER = 2;
        private static final Detail DEFAULT_INSTANCE = new Detail();
        public static final int EMPTY_FIELD_NUMBER = 13;
        public static final int HAS_ABOVE_CLIENT_FIELD_NUMBER = 14;
        public static final int LAST_CACHED_PSS_FIELD_NUMBER = 11;
        public static final int LAST_PSS_FIELD_NUMBER = 9;
        public static final int LAST_SWAP_PSS_FIELD_NUMBER = 10;
        public static final int MAX_ADJ_FIELD_NUMBER = 1;
        private static volatile Parser<Detail> PARSER = null;
        public static final int SERVICE_RUN_TIME_FIELD_NUMBER = 15;
        public static final int SET_ADJ_FIELD_NUMBER = 5;
        public static final int SET_RAW_ADJ_FIELD_NUMBER = 3;
        public static final int SET_STATE_FIELD_NUMBER = 8;
        private int bitField0_;
        private boolean cached_ = false;
        private int curAdj_ = 0;
        private int curRawAdj_ = 0;
        private int currentState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
        private boolean empty_ = false;
        private boolean hasAboveClient_ = false;
        private String lastCachedPss_ = "";
        private String lastPss_ = "";
        private String lastSwapPss_ = "";
        private int maxAdj_ = 0;
        private CpuRunTime serviceRunTime_;
        private int setAdj_ = 0;
        private int setRawAdj_ = 0;
        private int setState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;

        public interface CpuRunTimeOrBuilder extends MessageLiteOrBuilder {
            long getOverMs();

            float getUltilization();

            long getUsedMs();

            boolean hasOverMs();

            boolean hasUltilization();

            boolean hasUsedMs();
        }

        private Detail() {
        }

        public static final class CpuRunTime extends GeneratedMessageLite<CpuRunTime, Builder> implements CpuRunTimeOrBuilder {
            private static final CpuRunTime DEFAULT_INSTANCE = new CpuRunTime();
            public static final int OVER_MS_FIELD_NUMBER = 1;
            private static volatile Parser<CpuRunTime> PARSER = null;
            public static final int ULTILIZATION_FIELD_NUMBER = 3;
            public static final int USED_MS_FIELD_NUMBER = 2;
            private int bitField0_;
            private long overMs_ = 0;
            private float ultilization_ = 0.0f;
            private long usedMs_ = 0;

            private CpuRunTime() {
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public boolean hasOverMs() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public long getOverMs() {
                return this.overMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setOverMs(long value) {
                this.bitField0_ |= 1;
                this.overMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearOverMs() {
                this.bitField0_ &= -2;
                this.overMs_ = 0;
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public boolean hasUsedMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public long getUsedMs() {
                return this.usedMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUsedMs(long value) {
                this.bitField0_ |= 2;
                this.usedMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUsedMs() {
                this.bitField0_ &= -3;
                this.usedMs_ = 0;
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public boolean hasUltilization() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
            public float getUltilization() {
                return this.ultilization_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setUltilization(float value) {
                this.bitField0_ |= 4;
                this.ultilization_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearUltilization() {
                this.bitField0_ &= -5;
                this.ultilization_ = 0.0f;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt64(1, this.overMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.usedMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeFloat(3, this.ultilization_);
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
                    size2 = 0 + CodedOutputStream.computeInt64Size(1, this.overMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.usedMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeFloatSize(3, this.ultilization_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static CpuRunTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CpuRunTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CpuRunTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CpuRunTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CpuRunTime parseFrom(InputStream input) throws IOException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CpuRunTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CpuRunTime parseDelimitedFrom(InputStream input) throws IOException {
                return (CpuRunTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static CpuRunTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CpuRunTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CpuRunTime parseFrom(CodedInputStream input) throws IOException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CpuRunTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CpuRunTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CpuRunTime prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<CpuRunTime, Builder> implements CpuRunTimeOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(CpuRunTime.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public boolean hasOverMs() {
                    return ((CpuRunTime) this.instance).hasOverMs();
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public long getOverMs() {
                    return ((CpuRunTime) this.instance).getOverMs();
                }

                public Builder setOverMs(long value) {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).setOverMs(value);
                    return this;
                }

                public Builder clearOverMs() {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).clearOverMs();
                    return this;
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public boolean hasUsedMs() {
                    return ((CpuRunTime) this.instance).hasUsedMs();
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public long getUsedMs() {
                    return ((CpuRunTime) this.instance).getUsedMs();
                }

                public Builder setUsedMs(long value) {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).setUsedMs(value);
                    return this;
                }

                public Builder clearUsedMs() {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).clearUsedMs();
                    return this;
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public boolean hasUltilization() {
                    return ((CpuRunTime) this.instance).hasUltilization();
                }

                @Override // com.android.server.am.ProcessOomProto.Detail.CpuRunTimeOrBuilder
                public float getUltilization() {
                    return ((CpuRunTime) this.instance).getUltilization();
                }

                public Builder setUltilization(float value) {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).setUltilization(value);
                    return this;
                }

                public Builder clearUltilization() {
                    copyOnWrite();
                    ((CpuRunTime) this.instance).clearUltilization();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new CpuRunTime();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        CpuRunTime other = (CpuRunTime) arg1;
                        this.overMs_ = visitor.visitLong(hasOverMs(), this.overMs_, other.hasOverMs(), other.overMs_);
                        this.usedMs_ = visitor.visitLong(hasUsedMs(), this.usedMs_, other.hasUsedMs(), other.usedMs_);
                        this.ultilization_ = visitor.visitFloat(hasUltilization(), this.ultilization_, other.hasUltilization(), other.ultilization_);
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
                                    this.overMs_ = input.readInt64();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.usedMs_ = input.readInt64();
                                } else if (tag == 29) {
                                    this.bitField0_ |= 4;
                                    this.ultilization_ = input.readFloat();
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
                            synchronized (CpuRunTime.class) {
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

            public static CpuRunTime getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CpuRunTime> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasMaxAdj() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public int getMaxAdj() {
            return this.maxAdj_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxAdj(int value) {
            this.bitField0_ |= 1;
            this.maxAdj_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxAdj() {
            this.bitField0_ &= -2;
            this.maxAdj_ = 0;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasCurRawAdj() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public int getCurRawAdj() {
            return this.curRawAdj_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCurRawAdj(int value) {
            this.bitField0_ |= 2;
            this.curRawAdj_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCurRawAdj() {
            this.bitField0_ &= -3;
            this.curRawAdj_ = 0;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasSetRawAdj() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public int getSetRawAdj() {
            return this.setRawAdj_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSetRawAdj(int value) {
            this.bitField0_ |= 4;
            this.setRawAdj_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSetRawAdj() {
            this.bitField0_ &= -5;
            this.setRawAdj_ = 0;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasCurAdj() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public int getCurAdj() {
            return this.curAdj_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCurAdj(int value) {
            this.bitField0_ |= 8;
            this.curAdj_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCurAdj() {
            this.bitField0_ &= -9;
            this.curAdj_ = 0;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasSetAdj() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public int getSetAdj() {
            return this.setAdj_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSetAdj(int value) {
            this.bitField0_ |= 16;
            this.setAdj_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSetAdj() {
            this.bitField0_ &= -17;
            this.setAdj_ = 0;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasCurrentState() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public ProcessStateEnum getCurrentState() {
            ProcessStateEnum result = ProcessStateEnum.forNumber(this.currentState_);
            return result == null ? ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCurrentState(ProcessStateEnum value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.currentState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCurrentState() {
            this.bitField0_ &= -33;
            this.currentState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasSetState() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public ProcessStateEnum getSetState() {
            ProcessStateEnum result = ProcessStateEnum.forNumber(this.setState_);
            return result == null ? ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSetState(ProcessStateEnum value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.setState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSetState() {
            this.bitField0_ &= -65;
            this.setState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasLastPss() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public String getLastPss() {
            return this.lastPss_;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public ByteString getLastPssBytes() {
            return ByteString.copyFromUtf8(this.lastPss_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastPss(String value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.lastPss_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastPss() {
            this.bitField0_ &= -129;
            this.lastPss_ = getDefaultInstance().getLastPss();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastPssBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.lastPss_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasLastSwapPss() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public String getLastSwapPss() {
            return this.lastSwapPss_;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public ByteString getLastSwapPssBytes() {
            return ByteString.copyFromUtf8(this.lastSwapPss_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastSwapPss(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.lastSwapPss_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastSwapPss() {
            this.bitField0_ &= -257;
            this.lastSwapPss_ = getDefaultInstance().getLastSwapPss();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastSwapPssBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.lastSwapPss_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasLastCachedPss() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public String getLastCachedPss() {
            return this.lastCachedPss_;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public ByteString getLastCachedPssBytes() {
            return ByteString.copyFromUtf8(this.lastCachedPss_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastCachedPss(String value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.lastCachedPss_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastCachedPss() {
            this.bitField0_ &= -513;
            this.lastCachedPss_ = getDefaultInstance().getLastCachedPss();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastCachedPssBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.lastCachedPss_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasCached() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean getCached() {
            return this.cached_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCached(boolean value) {
            this.bitField0_ |= 1024;
            this.cached_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCached() {
            this.bitField0_ &= -1025;
            this.cached_ = false;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasEmpty() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean getEmpty() {
            return this.empty_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEmpty(boolean value) {
            this.bitField0_ |= 2048;
            this.empty_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEmpty() {
            this.bitField0_ &= -2049;
            this.empty_ = false;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasHasAboveClient() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean getHasAboveClient() {
            return this.hasAboveClient_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHasAboveClient(boolean value) {
            this.bitField0_ |= 4096;
            this.hasAboveClient_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHasAboveClient() {
            this.bitField0_ &= -4097;
            this.hasAboveClient_ = false;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public boolean hasServiceRunTime() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
        public CpuRunTime getServiceRunTime() {
            CpuRunTime cpuRunTime = this.serviceRunTime_;
            return cpuRunTime == null ? CpuRunTime.getDefaultInstance() : cpuRunTime;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServiceRunTime(CpuRunTime value) {
            if (value != null) {
                this.serviceRunTime_ = value;
                this.bitField0_ |= 8192;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setServiceRunTime(CpuRunTime.Builder builderForValue) {
            this.serviceRunTime_ = (CpuRunTime) builderForValue.build();
            this.bitField0_ |= 8192;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeServiceRunTime(CpuRunTime value) {
            CpuRunTime cpuRunTime = this.serviceRunTime_;
            if (cpuRunTime == null || cpuRunTime == CpuRunTime.getDefaultInstance()) {
                this.serviceRunTime_ = value;
            } else {
                this.serviceRunTime_ = (CpuRunTime) ((CpuRunTime.Builder) CpuRunTime.newBuilder(this.serviceRunTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8192;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearServiceRunTime() {
            this.serviceRunTime_ = null;
            this.bitField0_ &= -8193;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.maxAdj_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.curRawAdj_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.setRawAdj_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.curAdj_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.setAdj_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(7, this.currentState_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(8, this.setState_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeString(9, getLastPss());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(10, getLastSwapPss());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeString(11, getLastCachedPss());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeBool(12, this.cached_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeBool(13, this.empty_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBool(14, this.hasAboveClient_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeMessage(15, getServiceRunTime());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.maxAdj_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.curRawAdj_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.setRawAdj_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.curAdj_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.setAdj_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(7, this.currentState_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(8, this.setState_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeStringSize(9, getLastPss());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(10, getLastSwapPss());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeStringSize(11, getLastCachedPss());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeBoolSize(12, this.cached_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeBoolSize(13, this.empty_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeBoolSize(14, this.hasAboveClient_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeMessageSize(15, getServiceRunTime());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Detail parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Detail parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Detail parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Detail parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Detail parseFrom(InputStream input) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Detail parseDelimitedFrom(InputStream input) throws IOException {
            return (Detail) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Detail parseFrom(CodedInputStream input) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Detail prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Detail, Builder> implements DetailOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Detail.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasMaxAdj() {
                return ((Detail) this.instance).hasMaxAdj();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public int getMaxAdj() {
                return ((Detail) this.instance).getMaxAdj();
            }

            public Builder setMaxAdj(int value) {
                copyOnWrite();
                ((Detail) this.instance).setMaxAdj(value);
                return this;
            }

            public Builder clearMaxAdj() {
                copyOnWrite();
                ((Detail) this.instance).clearMaxAdj();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasCurRawAdj() {
                return ((Detail) this.instance).hasCurRawAdj();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public int getCurRawAdj() {
                return ((Detail) this.instance).getCurRawAdj();
            }

            public Builder setCurRawAdj(int value) {
                copyOnWrite();
                ((Detail) this.instance).setCurRawAdj(value);
                return this;
            }

            public Builder clearCurRawAdj() {
                copyOnWrite();
                ((Detail) this.instance).clearCurRawAdj();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasSetRawAdj() {
                return ((Detail) this.instance).hasSetRawAdj();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public int getSetRawAdj() {
                return ((Detail) this.instance).getSetRawAdj();
            }

            public Builder setSetRawAdj(int value) {
                copyOnWrite();
                ((Detail) this.instance).setSetRawAdj(value);
                return this;
            }

            public Builder clearSetRawAdj() {
                copyOnWrite();
                ((Detail) this.instance).clearSetRawAdj();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasCurAdj() {
                return ((Detail) this.instance).hasCurAdj();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public int getCurAdj() {
                return ((Detail) this.instance).getCurAdj();
            }

            public Builder setCurAdj(int value) {
                copyOnWrite();
                ((Detail) this.instance).setCurAdj(value);
                return this;
            }

            public Builder clearCurAdj() {
                copyOnWrite();
                ((Detail) this.instance).clearCurAdj();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasSetAdj() {
                return ((Detail) this.instance).hasSetAdj();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public int getSetAdj() {
                return ((Detail) this.instance).getSetAdj();
            }

            public Builder setSetAdj(int value) {
                copyOnWrite();
                ((Detail) this.instance).setSetAdj(value);
                return this;
            }

            public Builder clearSetAdj() {
                copyOnWrite();
                ((Detail) this.instance).clearSetAdj();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasCurrentState() {
                return ((Detail) this.instance).hasCurrentState();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public ProcessStateEnum getCurrentState() {
                return ((Detail) this.instance).getCurrentState();
            }

            public Builder setCurrentState(ProcessStateEnum value) {
                copyOnWrite();
                ((Detail) this.instance).setCurrentState(value);
                return this;
            }

            public Builder clearCurrentState() {
                copyOnWrite();
                ((Detail) this.instance).clearCurrentState();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasSetState() {
                return ((Detail) this.instance).hasSetState();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public ProcessStateEnum getSetState() {
                return ((Detail) this.instance).getSetState();
            }

            public Builder setSetState(ProcessStateEnum value) {
                copyOnWrite();
                ((Detail) this.instance).setSetState(value);
                return this;
            }

            public Builder clearSetState() {
                copyOnWrite();
                ((Detail) this.instance).clearSetState();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasLastPss() {
                return ((Detail) this.instance).hasLastPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public String getLastPss() {
                return ((Detail) this.instance).getLastPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public ByteString getLastPssBytes() {
                return ((Detail) this.instance).getLastPssBytes();
            }

            public Builder setLastPss(String value) {
                copyOnWrite();
                ((Detail) this.instance).setLastPss(value);
                return this;
            }

            public Builder clearLastPss() {
                copyOnWrite();
                ((Detail) this.instance).clearLastPss();
                return this;
            }

            public Builder setLastPssBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setLastPssBytes(value);
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasLastSwapPss() {
                return ((Detail) this.instance).hasLastSwapPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public String getLastSwapPss() {
                return ((Detail) this.instance).getLastSwapPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public ByteString getLastSwapPssBytes() {
                return ((Detail) this.instance).getLastSwapPssBytes();
            }

            public Builder setLastSwapPss(String value) {
                copyOnWrite();
                ((Detail) this.instance).setLastSwapPss(value);
                return this;
            }

            public Builder clearLastSwapPss() {
                copyOnWrite();
                ((Detail) this.instance).clearLastSwapPss();
                return this;
            }

            public Builder setLastSwapPssBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setLastSwapPssBytes(value);
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasLastCachedPss() {
                return ((Detail) this.instance).hasLastCachedPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public String getLastCachedPss() {
                return ((Detail) this.instance).getLastCachedPss();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public ByteString getLastCachedPssBytes() {
                return ((Detail) this.instance).getLastCachedPssBytes();
            }

            public Builder setLastCachedPss(String value) {
                copyOnWrite();
                ((Detail) this.instance).setLastCachedPss(value);
                return this;
            }

            public Builder clearLastCachedPss() {
                copyOnWrite();
                ((Detail) this.instance).clearLastCachedPss();
                return this;
            }

            public Builder setLastCachedPssBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setLastCachedPssBytes(value);
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasCached() {
                return ((Detail) this.instance).hasCached();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean getCached() {
                return ((Detail) this.instance).getCached();
            }

            public Builder setCached(boolean value) {
                copyOnWrite();
                ((Detail) this.instance).setCached(value);
                return this;
            }

            public Builder clearCached() {
                copyOnWrite();
                ((Detail) this.instance).clearCached();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasEmpty() {
                return ((Detail) this.instance).hasEmpty();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean getEmpty() {
                return ((Detail) this.instance).getEmpty();
            }

            public Builder setEmpty(boolean value) {
                copyOnWrite();
                ((Detail) this.instance).setEmpty(value);
                return this;
            }

            public Builder clearEmpty() {
                copyOnWrite();
                ((Detail) this.instance).clearEmpty();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasHasAboveClient() {
                return ((Detail) this.instance).hasHasAboveClient();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean getHasAboveClient() {
                return ((Detail) this.instance).getHasAboveClient();
            }

            public Builder setHasAboveClient(boolean value) {
                copyOnWrite();
                ((Detail) this.instance).setHasAboveClient(value);
                return this;
            }

            public Builder clearHasAboveClient() {
                copyOnWrite();
                ((Detail) this.instance).clearHasAboveClient();
                return this;
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public boolean hasServiceRunTime() {
                return ((Detail) this.instance).hasServiceRunTime();
            }

            @Override // com.android.server.am.ProcessOomProto.DetailOrBuilder
            public CpuRunTime getServiceRunTime() {
                return ((Detail) this.instance).getServiceRunTime();
            }

            public Builder setServiceRunTime(CpuRunTime value) {
                copyOnWrite();
                ((Detail) this.instance).setServiceRunTime((Detail) value);
                return this;
            }

            public Builder setServiceRunTime(CpuRunTime.Builder builderForValue) {
                copyOnWrite();
                ((Detail) this.instance).setServiceRunTime((Detail) builderForValue);
                return this;
            }

            public Builder mergeServiceRunTime(CpuRunTime value) {
                copyOnWrite();
                ((Detail) this.instance).mergeServiceRunTime(value);
                return this;
            }

            public Builder clearServiceRunTime() {
                copyOnWrite();
                ((Detail) this.instance).clearServiceRunTime();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Detail();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Detail other = (Detail) arg1;
                    this.maxAdj_ = visitor.visitInt(hasMaxAdj(), this.maxAdj_, other.hasMaxAdj(), other.maxAdj_);
                    this.curRawAdj_ = visitor.visitInt(hasCurRawAdj(), this.curRawAdj_, other.hasCurRawAdj(), other.curRawAdj_);
                    this.setRawAdj_ = visitor.visitInt(hasSetRawAdj(), this.setRawAdj_, other.hasSetRawAdj(), other.setRawAdj_);
                    this.curAdj_ = visitor.visitInt(hasCurAdj(), this.curAdj_, other.hasCurAdj(), other.curAdj_);
                    this.setAdj_ = visitor.visitInt(hasSetAdj(), this.setAdj_, other.hasSetAdj(), other.setAdj_);
                    this.currentState_ = visitor.visitInt(hasCurrentState(), this.currentState_, other.hasCurrentState(), other.currentState_);
                    this.setState_ = visitor.visitInt(hasSetState(), this.setState_, other.hasSetState(), other.setState_);
                    this.lastPss_ = visitor.visitString(hasLastPss(), this.lastPss_, other.hasLastPss(), other.lastPss_);
                    this.lastSwapPss_ = visitor.visitString(hasLastSwapPss(), this.lastSwapPss_, other.hasLastSwapPss(), other.lastSwapPss_);
                    this.lastCachedPss_ = visitor.visitString(hasLastCachedPss(), this.lastCachedPss_, other.hasLastCachedPss(), other.lastCachedPss_);
                    this.cached_ = visitor.visitBoolean(hasCached(), this.cached_, other.hasCached(), other.cached_);
                    this.empty_ = visitor.visitBoolean(hasEmpty(), this.empty_, other.hasEmpty(), other.empty_);
                    this.hasAboveClient_ = visitor.visitBoolean(hasHasAboveClient(), this.hasAboveClient_, other.hasHasAboveClient(), other.hasAboveClient_);
                    this.serviceRunTime_ = (CpuRunTime) visitor.visitMessage(this.serviceRunTime_, other.serviceRunTime_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.maxAdj_ = input.readInt32();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.curRawAdj_ = input.readInt32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.setRawAdj_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.curAdj_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.setAdj_ = input.readInt32();
                                    break;
                                case 56:
                                    int rawValue = input.readEnum();
                                    if (ProcessStateEnum.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 32;
                                        this.currentState_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(7, rawValue);
                                        break;
                                    }
                                case 64:
                                    int rawValue2 = input.readEnum();
                                    if (ProcessStateEnum.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 64;
                                        this.setState_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(8, rawValue2);
                                        break;
                                    }
                                case 74:
                                    String s = input.readString();
                                    this.bitField0_ |= 128;
                                    this.lastPss_ = s;
                                    break;
                                case 82:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.lastSwapPss_ = s2;
                                    break;
                                case 90:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 512;
                                    this.lastCachedPss_ = s3;
                                    break;
                                case 96:
                                    this.bitField0_ |= 1024;
                                    this.cached_ = input.readBool();
                                    break;
                                case 104:
                                    this.bitField0_ |= 2048;
                                    this.empty_ = input.readBool();
                                    break;
                                case 112:
                                    this.bitField0_ |= 4096;
                                    this.hasAboveClient_ = input.readBool();
                                    break;
                                case 122:
                                    CpuRunTime.Builder subBuilder = null;
                                    if ((this.bitField0_ & 8192) == 8192) {
                                        subBuilder = (CpuRunTime.Builder) this.serviceRunTime_.toBuilder();
                                    }
                                    this.serviceRunTime_ = (CpuRunTime) input.readMessage(CpuRunTime.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.serviceRunTime_);
                                        this.serviceRunTime_ = (CpuRunTime) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 8192;
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (Detail.class) {
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

        public static Detail getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Detail> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public enum ForegroundCase implements Internal.EnumLite {
        ACTIVITIES(5),
        SERVICES(6),
        FOREGROUND_NOT_SET(0);
        
        private final int value;

        private ForegroundCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static ForegroundCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static ForegroundCase forNumber(int value2) {
            if (value2 == 0) {
                return FOREGROUND_NOT_SET;
            }
            if (value2 == 5) {
                return ACTIVITIES;
            }
            if (value2 != 6) {
                return null;
            }
            return SERVICES;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ForegroundCase getForegroundCase() {
        return ForegroundCase.forNumber(this.foregroundCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForeground() {
        this.foregroundCase_ = 0;
        this.foreground_ = null;
    }

    public enum AdjTargetCase implements Internal.EnumLite {
        ADJ_TARGET_COMPONENT_NAME(11),
        ADJ_TARGET_OBJECT(12),
        ADJTARGET_NOT_SET(0);
        
        private final int value;

        private AdjTargetCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static AdjTargetCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static AdjTargetCase forNumber(int value2) {
            if (value2 == 0) {
                return ADJTARGET_NOT_SET;
            }
            if (value2 == 11) {
                return ADJ_TARGET_COMPONENT_NAME;
            }
            if (value2 != 12) {
                return null;
            }
            return ADJ_TARGET_OBJECT;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public AdjTargetCase getAdjTargetCase() {
        return AdjTargetCase.forNumber(this.adjTargetCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjTarget() {
        this.adjTargetCase_ = 0;
        this.adjTarget_ = null;
    }

    public enum AdjSourceCase implements Internal.EnumLite {
        ADJ_SOURCE_PROC(13),
        ADJ_SOURCE_OBJECT(14),
        ADJSOURCE_NOT_SET(0);
        
        private final int value;

        private AdjSourceCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static AdjSourceCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static AdjSourceCase forNumber(int value2) {
            if (value2 == 0) {
                return ADJSOURCE_NOT_SET;
            }
            if (value2 == 13) {
                return ADJ_SOURCE_PROC;
            }
            if (value2 != 14) {
                return null;
            }
            return ADJ_SOURCE_OBJECT;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public AdjSourceCase getAdjSourceCase() {
        return AdjSourceCase.forNumber(this.adjSourceCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjSource() {
        this.adjSourceCase_ = 0;
        this.adjSource_ = null;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasPersistent() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean getPersistent() {
        return this.persistent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPersistent(boolean value) {
        this.bitField0_ |= 1;
        this.persistent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPersistent() {
        this.bitField0_ &= -2;
        this.persistent_ = false;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasNum() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public int getNum() {
        return this.num_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNum(int value) {
        this.bitField0_ |= 2;
        this.num_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNum() {
        this.bitField0_ &= -3;
        this.num_ = 0;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasOomAdj() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public String getOomAdj() {
        return this.oomAdj_;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ByteString getOomAdjBytes() {
        return ByteString.copyFromUtf8(this.oomAdj_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOomAdj(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.oomAdj_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOomAdj() {
        this.bitField0_ &= -5;
        this.oomAdj_ = getDefaultInstance().getOomAdj();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOomAdjBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.oomAdj_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasSchedGroup() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public SchedGroup getSchedGroup() {
        SchedGroup result = SchedGroup.forNumber(this.schedGroup_);
        return result == null ? SchedGroup.SCHED_GROUP_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSchedGroup(SchedGroup value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.schedGroup_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSchedGroup() {
        this.bitField0_ &= -9;
        this.schedGroup_ = -1;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasActivities() {
        return this.foregroundCase_ == 5;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean getActivities() {
        if (this.foregroundCase_ == 5) {
            return ((Boolean) this.foreground_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(boolean value) {
        this.foregroundCase_ = 5;
        this.foreground_ = Boolean.valueOf(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivities() {
        if (this.foregroundCase_ == 5) {
            this.foregroundCase_ = 0;
            this.foreground_ = null;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasServices() {
        return this.foregroundCase_ == 6;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean getServices() {
        if (this.foregroundCase_ == 6) {
            return ((Boolean) this.foreground_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServices(boolean value) {
        this.foregroundCase_ = 6;
        this.foreground_ = Boolean.valueOf(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServices() {
        if (this.foregroundCase_ == 6) {
            this.foregroundCase_ = 0;
            this.foreground_ = null;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ProcessStateEnum getState() {
        ProcessStateEnum result = ProcessStateEnum.forNumber(this.state_);
        return result == null ? ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(ProcessStateEnum value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -65;
        this.state_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasTrimMemoryLevel() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public int getTrimMemoryLevel() {
        return this.trimMemoryLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrimMemoryLevel(int value) {
        this.bitField0_ |= 128;
        this.trimMemoryLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTrimMemoryLevel() {
        this.bitField0_ &= -129;
        this.trimMemoryLevel_ = 0;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasProc() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ProcessRecordProto getProc() {
        ProcessRecordProto processRecordProto = this.proc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProc(ProcessRecordProto value) {
        if (value != null) {
            this.proc_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProc(ProcessRecordProto.Builder builderForValue) {
        this.proc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 256;
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
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProc() {
        this.proc_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasAdjType() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public String getAdjType() {
        return this.adjType_;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ByteString getAdjTypeBytes() {
        return ByteString.copyFromUtf8(this.adjType_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjType(String value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.adjType_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjType() {
        this.bitField0_ &= -513;
        this.adjType_ = getDefaultInstance().getAdjType();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjTypeBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.adjType_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasAdjTargetComponentName() {
        return this.adjTargetCase_ == 11;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ComponentNameProto getAdjTargetComponentName() {
        if (this.adjTargetCase_ == 11) {
            return (ComponentNameProto) this.adjTarget_;
        }
        return ComponentNameProto.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjTargetComponentName(ComponentNameProto value) {
        if (value != null) {
            this.adjTarget_ = value;
            this.adjTargetCase_ = 11;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjTargetComponentName(ComponentNameProto.Builder builderForValue) {
        this.adjTarget_ = builderForValue.build();
        this.adjTargetCase_ = 11;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAdjTargetComponentName(ComponentNameProto value) {
        if (this.adjTargetCase_ != 11 || this.adjTarget_ == ComponentNameProto.getDefaultInstance()) {
            this.adjTarget_ = value;
        } else {
            this.adjTarget_ = ((ComponentNameProto.Builder) ComponentNameProto.newBuilder((ComponentNameProto) this.adjTarget_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.adjTargetCase_ = 11;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjTargetComponentName() {
        if (this.adjTargetCase_ == 11) {
            this.adjTargetCase_ = 0;
            this.adjTarget_ = null;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasAdjTargetObject() {
        return this.adjTargetCase_ == 12;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public String getAdjTargetObject() {
        if (this.adjTargetCase_ == 12) {
            return (String) this.adjTarget_;
        }
        return "";
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ByteString getAdjTargetObjectBytes() {
        String ref = "";
        if (this.adjTargetCase_ == 12) {
            ref = (String) this.adjTarget_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjTargetObject(String value) {
        if (value != null) {
            this.adjTargetCase_ = 12;
            this.adjTarget_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjTargetObject() {
        if (this.adjTargetCase_ == 12) {
            this.adjTargetCase_ = 0;
            this.adjTarget_ = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjTargetObjectBytes(ByteString value) {
        if (value != null) {
            this.adjTargetCase_ = 12;
            this.adjTarget_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasAdjSourceProc() {
        return this.adjSourceCase_ == 13;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ProcessRecordProto getAdjSourceProc() {
        if (this.adjSourceCase_ == 13) {
            return (ProcessRecordProto) this.adjSource_;
        }
        return ProcessRecordProto.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjSourceProc(ProcessRecordProto value) {
        if (value != null) {
            this.adjSource_ = value;
            this.adjSourceCase_ = 13;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjSourceProc(ProcessRecordProto.Builder builderForValue) {
        this.adjSource_ = builderForValue.build();
        this.adjSourceCase_ = 13;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAdjSourceProc(ProcessRecordProto value) {
        if (this.adjSourceCase_ != 13 || this.adjSource_ == ProcessRecordProto.getDefaultInstance()) {
            this.adjSource_ = value;
        } else {
            this.adjSource_ = ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder((ProcessRecordProto) this.adjSource_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.adjSourceCase_ = 13;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjSourceProc() {
        if (this.adjSourceCase_ == 13) {
            this.adjSourceCase_ = 0;
            this.adjSource_ = null;
        }
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasAdjSourceObject() {
        return this.adjSourceCase_ == 14;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public String getAdjSourceObject() {
        if (this.adjSourceCase_ == 14) {
            return (String) this.adjSource_;
        }
        return "";
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public ByteString getAdjSourceObjectBytes() {
        String ref = "";
        if (this.adjSourceCase_ == 14) {
            ref = (String) this.adjSource_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjSourceObject(String value) {
        if (value != null) {
            this.adjSourceCase_ = 14;
            this.adjSource_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdjSourceObject() {
        if (this.adjSourceCase_ == 14) {
            this.adjSourceCase_ = 0;
            this.adjSource_ = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdjSourceObjectBytes(ByteString value) {
        if (value != null) {
            this.adjSourceCase_ = 14;
            this.adjSource_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public boolean hasDetail() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.am.ProcessOomProtoOrBuilder
    public Detail getDetail() {
        Detail detail = this.detail_;
        return detail == null ? Detail.getDefaultInstance() : detail;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetail(Detail value) {
        if (value != null) {
            this.detail_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetail(Detail.Builder builderForValue) {
        this.detail_ = (Detail) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDetail(Detail value) {
        Detail detail = this.detail_;
        if (detail == null || detail == Detail.getDefaultInstance()) {
            this.detail_ = value;
        } else {
            this.detail_ = (Detail) ((Detail.Builder) Detail.newBuilder(this.detail_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDetail() {
        this.detail_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.persistent_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.num_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getOomAdj());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.schedGroup_);
        }
        if (this.foregroundCase_ == 5) {
            output.writeBool(5, ((Boolean) this.foreground_).booleanValue());
        }
        if (this.foregroundCase_ == 6) {
            output.writeBool(6, ((Boolean) this.foreground_).booleanValue());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeEnum(7, this.state_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.trimMemoryLevel_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getProc());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeString(10, getAdjType());
        }
        if (this.adjTargetCase_ == 11) {
            output.writeMessage(11, (ComponentNameProto) this.adjTarget_);
        }
        if (this.adjTargetCase_ == 12) {
            output.writeString(12, getAdjTargetObject());
        }
        if (this.adjSourceCase_ == 13) {
            output.writeMessage(13, (ProcessRecordProto) this.adjSource_);
        }
        if (this.adjSourceCase_ == 14) {
            output.writeString(14, getAdjSourceObject());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(15, getDetail());
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.persistent_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.num_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getOomAdj());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.schedGroup_);
        }
        if (this.foregroundCase_ == 5) {
            size2 += CodedOutputStream.computeBoolSize(5, ((Boolean) this.foreground_).booleanValue());
        }
        if (this.foregroundCase_ == 6) {
            size2 += CodedOutputStream.computeBoolSize(6, ((Boolean) this.foreground_).booleanValue());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeEnumSize(7, this.state_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.trimMemoryLevel_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getProc());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeStringSize(10, getAdjType());
        }
        if (this.adjTargetCase_ == 11) {
            size2 += CodedOutputStream.computeMessageSize(11, (ComponentNameProto) this.adjTarget_);
        }
        if (this.adjTargetCase_ == 12) {
            size2 += CodedOutputStream.computeStringSize(12, getAdjTargetObject());
        }
        if (this.adjSourceCase_ == 13) {
            size2 += CodedOutputStream.computeMessageSize(13, (ProcessRecordProto) this.adjSource_);
        }
        if (this.adjSourceCase_ == 14) {
            size2 += CodedOutputStream.computeStringSize(14, getAdjSourceObject());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(15, getDetail());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessOomProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessOomProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessOomProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessOomProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessOomProto parseFrom(InputStream input) throws IOException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessOomProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessOomProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessOomProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessOomProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessOomProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessOomProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessOomProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessOomProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessOomProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessOomProto, Builder> implements ProcessOomProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(ProcessOomProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ForegroundCase getForegroundCase() {
            return ((ProcessOomProto) this.instance).getForegroundCase();
        }

        public Builder clearForeground() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearForeground();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public AdjTargetCase getAdjTargetCase() {
            return ((ProcessOomProto) this.instance).getAdjTargetCase();
        }

        public Builder clearAdjTarget() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjTarget();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public AdjSourceCase getAdjSourceCase() {
            return ((ProcessOomProto) this.instance).getAdjSourceCase();
        }

        public Builder clearAdjSource() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjSource();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasPersistent() {
            return ((ProcessOomProto) this.instance).hasPersistent();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean getPersistent() {
            return ((ProcessOomProto) this.instance).getPersistent();
        }

        public Builder setPersistent(boolean value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setPersistent(value);
            return this;
        }

        public Builder clearPersistent() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearPersistent();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasNum() {
            return ((ProcessOomProto) this.instance).hasNum();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public int getNum() {
            return ((ProcessOomProto) this.instance).getNum();
        }

        public Builder setNum(int value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setNum(value);
            return this;
        }

        public Builder clearNum() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearNum();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasOomAdj() {
            return ((ProcessOomProto) this.instance).hasOomAdj();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public String getOomAdj() {
            return ((ProcessOomProto) this.instance).getOomAdj();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ByteString getOomAdjBytes() {
            return ((ProcessOomProto) this.instance).getOomAdjBytes();
        }

        public Builder setOomAdj(String value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setOomAdj(value);
            return this;
        }

        public Builder clearOomAdj() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearOomAdj();
            return this;
        }

        public Builder setOomAdjBytes(ByteString value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setOomAdjBytes(value);
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasSchedGroup() {
            return ((ProcessOomProto) this.instance).hasSchedGroup();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public SchedGroup getSchedGroup() {
            return ((ProcessOomProto) this.instance).getSchedGroup();
        }

        public Builder setSchedGroup(SchedGroup value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setSchedGroup(value);
            return this;
        }

        public Builder clearSchedGroup() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearSchedGroup();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasActivities() {
            return ((ProcessOomProto) this.instance).hasActivities();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean getActivities() {
            return ((ProcessOomProto) this.instance).getActivities();
        }

        public Builder setActivities(boolean value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setActivities(value);
            return this;
        }

        public Builder clearActivities() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearActivities();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasServices() {
            return ((ProcessOomProto) this.instance).hasServices();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean getServices() {
            return ((ProcessOomProto) this.instance).getServices();
        }

        public Builder setServices(boolean value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setServices(value);
            return this;
        }

        public Builder clearServices() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearServices();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasState() {
            return ((ProcessOomProto) this.instance).hasState();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ProcessStateEnum getState() {
            return ((ProcessOomProto) this.instance).getState();
        }

        public Builder setState(ProcessStateEnum value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearState();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasTrimMemoryLevel() {
            return ((ProcessOomProto) this.instance).hasTrimMemoryLevel();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public int getTrimMemoryLevel() {
            return ((ProcessOomProto) this.instance).getTrimMemoryLevel();
        }

        public Builder setTrimMemoryLevel(int value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setTrimMemoryLevel(value);
            return this;
        }

        public Builder clearTrimMemoryLevel() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearTrimMemoryLevel();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasProc() {
            return ((ProcessOomProto) this.instance).hasProc();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ProcessRecordProto getProc() {
            return ((ProcessOomProto) this.instance).getProc();
        }

        public Builder setProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setProc((ProcessOomProto) value);
            return this;
        }

        public Builder setProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setProc((ProcessOomProto) builderForValue);
            return this;
        }

        public Builder mergeProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).mergeProc(value);
            return this;
        }

        public Builder clearProc() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearProc();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasAdjType() {
            return ((ProcessOomProto) this.instance).hasAdjType();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public String getAdjType() {
            return ((ProcessOomProto) this.instance).getAdjType();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ByteString getAdjTypeBytes() {
            return ((ProcessOomProto) this.instance).getAdjTypeBytes();
        }

        public Builder setAdjType(String value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjType(value);
            return this;
        }

        public Builder clearAdjType() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjType();
            return this;
        }

        public Builder setAdjTypeBytes(ByteString value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjTypeBytes(value);
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasAdjTargetComponentName() {
            return ((ProcessOomProto) this.instance).hasAdjTargetComponentName();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ComponentNameProto getAdjTargetComponentName() {
            return ((ProcessOomProto) this.instance).getAdjTargetComponentName();
        }

        public Builder setAdjTargetComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjTargetComponentName((ProcessOomProto) value);
            return this;
        }

        public Builder setAdjTargetComponentName(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjTargetComponentName((ProcessOomProto) builderForValue);
            return this;
        }

        public Builder mergeAdjTargetComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).mergeAdjTargetComponentName(value);
            return this;
        }

        public Builder clearAdjTargetComponentName() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjTargetComponentName();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasAdjTargetObject() {
            return ((ProcessOomProto) this.instance).hasAdjTargetObject();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public String getAdjTargetObject() {
            return ((ProcessOomProto) this.instance).getAdjTargetObject();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ByteString getAdjTargetObjectBytes() {
            return ((ProcessOomProto) this.instance).getAdjTargetObjectBytes();
        }

        public Builder setAdjTargetObject(String value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjTargetObject(value);
            return this;
        }

        public Builder clearAdjTargetObject() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjTargetObject();
            return this;
        }

        public Builder setAdjTargetObjectBytes(ByteString value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjTargetObjectBytes(value);
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasAdjSourceProc() {
            return ((ProcessOomProto) this.instance).hasAdjSourceProc();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ProcessRecordProto getAdjSourceProc() {
            return ((ProcessOomProto) this.instance).getAdjSourceProc();
        }

        public Builder setAdjSourceProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjSourceProc((ProcessOomProto) value);
            return this;
        }

        public Builder setAdjSourceProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjSourceProc((ProcessOomProto) builderForValue);
            return this;
        }

        public Builder mergeAdjSourceProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).mergeAdjSourceProc(value);
            return this;
        }

        public Builder clearAdjSourceProc() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjSourceProc();
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasAdjSourceObject() {
            return ((ProcessOomProto) this.instance).hasAdjSourceObject();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public String getAdjSourceObject() {
            return ((ProcessOomProto) this.instance).getAdjSourceObject();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public ByteString getAdjSourceObjectBytes() {
            return ((ProcessOomProto) this.instance).getAdjSourceObjectBytes();
        }

        public Builder setAdjSourceObject(String value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjSourceObject(value);
            return this;
        }

        public Builder clearAdjSourceObject() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearAdjSourceObject();
            return this;
        }

        public Builder setAdjSourceObjectBytes(ByteString value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setAdjSourceObjectBytes(value);
            return this;
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public boolean hasDetail() {
            return ((ProcessOomProto) this.instance).hasDetail();
        }

        @Override // com.android.server.am.ProcessOomProtoOrBuilder
        public Detail getDetail() {
            return ((ProcessOomProto) this.instance).getDetail();
        }

        public Builder setDetail(Detail value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setDetail((ProcessOomProto) value);
            return this;
        }

        public Builder setDetail(Detail.Builder builderForValue) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).setDetail((ProcessOomProto) builderForValue);
            return this;
        }

        public Builder mergeDetail(Detail value) {
            copyOnWrite();
            ((ProcessOomProto) this.instance).mergeDetail(value);
            return this;
        }

        public Builder clearDetail() {
            copyOnWrite();
            ((ProcessOomProto) this.instance).clearDetail();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessOomProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessOomProto other = (ProcessOomProto) arg1;
                this.persistent_ = visitor.visitBoolean(hasPersistent(), this.persistent_, other.hasPersistent(), other.persistent_);
                this.num_ = visitor.visitInt(hasNum(), this.num_, other.hasNum(), other.num_);
                this.oomAdj_ = visitor.visitString(hasOomAdj(), this.oomAdj_, other.hasOomAdj(), other.oomAdj_);
                this.schedGroup_ = visitor.visitInt(hasSchedGroup(), this.schedGroup_, other.hasSchedGroup(), other.schedGroup_);
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.trimMemoryLevel_ = visitor.visitInt(hasTrimMemoryLevel(), this.trimMemoryLevel_, other.hasTrimMemoryLevel(), other.trimMemoryLevel_);
                this.proc_ = (ProcessRecordProto) visitor.visitMessage(this.proc_, other.proc_);
                this.adjType_ = visitor.visitString(hasAdjType(), this.adjType_, other.hasAdjType(), other.adjType_);
                this.detail_ = (Detail) visitor.visitMessage(this.detail_, other.detail_);
                int i = AnonymousClass1.$SwitchMap$com$android$server$am$ProcessOomProto$ForegroundCase[other.getForegroundCase().ordinal()];
                boolean z = false;
                if (i == 1) {
                    this.foreground_ = visitor.visitOneofBoolean(this.foregroundCase_ == 5, this.foreground_, other.foreground_);
                } else if (i == 2) {
                    this.foreground_ = visitor.visitOneofBoolean(this.foregroundCase_ == 6, this.foreground_, other.foreground_);
                } else if (i == 3) {
                    visitor.visitOneofNotSet(this.foregroundCase_ != 0);
                }
                int i2 = AnonymousClass1.$SwitchMap$com$android$server$am$ProcessOomProto$AdjTargetCase[other.getAdjTargetCase().ordinal()];
                if (i2 == 1) {
                    this.adjTarget_ = visitor.visitOneofMessage(this.adjTargetCase_ == 11, this.adjTarget_, other.adjTarget_);
                } else if (i2 == 2) {
                    this.adjTarget_ = visitor.visitOneofString(this.adjTargetCase_ == 12, this.adjTarget_, other.adjTarget_);
                } else if (i2 == 3) {
                    visitor.visitOneofNotSet(this.adjTargetCase_ != 0);
                }
                int i3 = AnonymousClass1.$SwitchMap$com$android$server$am$ProcessOomProto$AdjSourceCase[other.getAdjSourceCase().ordinal()];
                if (i3 == 1) {
                    if (this.adjSourceCase_ == 13) {
                        z = true;
                    }
                    this.adjSource_ = visitor.visitOneofMessage(z, this.adjSource_, other.adjSource_);
                } else if (i3 == 2) {
                    if (this.adjSourceCase_ == 14) {
                        z = true;
                    }
                    this.adjSource_ = visitor.visitOneofString(z, this.adjSource_, other.adjSource_);
                } else if (i3 == 3) {
                    if (this.adjSourceCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i4 = other.foregroundCase_;
                    if (i4 != 0) {
                        this.foregroundCase_ = i4;
                    }
                    int i5 = other.adjTargetCase_;
                    if (i5 != 0) {
                        this.adjTargetCase_ = i5;
                    }
                    int i6 = other.adjSourceCase_;
                    if (i6 != 0) {
                        this.adjSourceCase_ = i6;
                    }
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.persistent_ = input.readBool();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.num_ = input.readInt32();
                                break;
                            case 26:
                                String s = input.readString();
                                this.bitField0_ = 4 | this.bitField0_;
                                this.oomAdj_ = s;
                                break;
                            case 32:
                                int rawValue = input.readEnum();
                                if (SchedGroup.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 8;
                                    this.schedGroup_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(4, rawValue);
                                    break;
                                }
                            case 40:
                                this.foregroundCase_ = 5;
                                this.foreground_ = Boolean.valueOf(input.readBool());
                                break;
                            case 48:
                                this.foregroundCase_ = 6;
                                this.foreground_ = Boolean.valueOf(input.readBool());
                                break;
                            case 56:
                                int rawValue2 = input.readEnum();
                                if (ProcessStateEnum.forNumber(rawValue2) != null) {
                                    this.bitField0_ |= 64;
                                    this.state_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue2);
                                    break;
                                }
                            case 64:
                                this.bitField0_ |= 128;
                                this.trimMemoryLevel_ = input.readInt32();
                                break;
                            case 74:
                                ProcessRecordProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder = (ProcessRecordProto.Builder) this.proc_.toBuilder();
                                }
                                this.proc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.proc_);
                                    this.proc_ = (ProcessRecordProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                String s2 = input.readString();
                                this.bitField0_ |= 512;
                                this.adjType_ = s2;
                                break;
                            case 90:
                                ComponentNameProto.Builder subBuilder2 = null;
                                if (this.adjTargetCase_ == 11) {
                                    subBuilder2 = (ComponentNameProto.Builder) ((ComponentNameProto) this.adjTarget_).toBuilder();
                                }
                                this.adjTarget_ = input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) ((ComponentNameProto) this.adjTarget_));
                                    this.adjTarget_ = subBuilder2.buildPartial();
                                }
                                this.adjTargetCase_ = 11;
                                break;
                            case 98:
                                String s3 = input.readString();
                                this.adjTargetCase_ = 12;
                                this.adjTarget_ = s3;
                                break;
                            case 106:
                                ProcessRecordProto.Builder subBuilder3 = null;
                                if (this.adjSourceCase_ == 13) {
                                    subBuilder3 = (ProcessRecordProto.Builder) ((ProcessRecordProto) this.adjSource_).toBuilder();
                                }
                                this.adjSource_ = input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) ((ProcessRecordProto) this.adjSource_));
                                    this.adjSource_ = subBuilder3.buildPartial();
                                }
                                this.adjSourceCase_ = 13;
                                break;
                            case 114:
                                String s4 = input.readString();
                                this.adjSourceCase_ = 14;
                                this.adjSource_ = s4;
                                break;
                            case 122:
                                Detail.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder4 = (Detail.Builder) this.detail_.toBuilder();
                                }
                                this.detail_ = (Detail) input.readMessage(Detail.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.detail_);
                                    this.detail_ = (Detail) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (ProcessOomProto.class) {
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

    /* renamed from: com.android.server.am.ProcessOomProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$server$am$ProcessOomProto$AdjSourceCase = new int[AdjSourceCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$android$server$am$ProcessOomProto$AdjTargetCase = new int[AdjTargetCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$android$server$am$ProcessOomProto$ForegroundCase = new int[ForegroundCase.values().length];

        static {
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjSourceCase[AdjSourceCase.ADJ_SOURCE_PROC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjSourceCase[AdjSourceCase.ADJ_SOURCE_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjSourceCase[AdjSourceCase.ADJSOURCE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjTargetCase[AdjTargetCase.ADJ_TARGET_COMPONENT_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjTargetCase[AdjTargetCase.ADJ_TARGET_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$AdjTargetCase[AdjTargetCase.ADJTARGET_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$ForegroundCase[ForegroundCase.ACTIVITIES.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$ForegroundCase[ForegroundCase.SERVICES.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$server$am$ProcessOomProto$ForegroundCase[ForegroundCase.FOREGROUND_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static ProcessOomProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessOomProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
