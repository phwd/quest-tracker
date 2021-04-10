package android.os;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class IncidentMetadata extends GeneratedMessageLite<IncidentMetadata, Builder> implements IncidentMetadataOrBuilder {
    private static final IncidentMetadata DEFAULT_INSTANCE = new IncidentMetadata();
    public static final int DEST_FIELD_NUMBER = 3;
    private static volatile Parser<IncidentMetadata> PARSER = null;
    public static final int REPORT_ID_FIELD_NUMBER = 1;
    public static final int REQUEST_SIZE_FIELD_NUMBER = 4;
    public static final int SECTIONS_FIELD_NUMBER = 6;
    public static final int SEQUENCE_NUMBER_FIELD_NUMBER = 2;
    public static final int USE_DROPBOX_FIELD_NUMBER = 5;
    private int bitField0_;
    private int dest_ = 0;
    private long reportId_ = 0;
    private int requestSize_ = 0;
    private Internal.ProtobufList<SectionStats> sections_ = emptyProtobufList();
    private int sequenceNumber_ = 0;
    private boolean useDropbox_ = false;

    public interface SectionStatsOrBuilder extends MessageLiteOrBuilder {
        long getDumpDurationMs();

        int getDumpSizeBytes();

        String getErrorMsg();

        ByteString getErrorMsgBytes();

        long getExecDurationMs();

        int getId();

        boolean getIsTruncated();

        int getReportSizeBytes();

        boolean getSuccess();

        boolean getTimedOut();

        boolean hasDumpDurationMs();

        boolean hasDumpSizeBytes();

        boolean hasErrorMsg();

        boolean hasExecDurationMs();

        boolean hasId();

        boolean hasIsTruncated();

        boolean hasReportSizeBytes();

        boolean hasSuccess();

        boolean hasTimedOut();
    }

    private IncidentMetadata() {
    }

    public enum Destination implements Internal.EnumLite {
        AUTOMATIC(0),
        EXPLICIT(1),
        LOCAL(2);
        
        public static final int AUTOMATIC_VALUE = 0;
        public static final int EXPLICIT_VALUE = 1;
        public static final int LOCAL_VALUE = 2;
        private static final Internal.EnumLiteMap<Destination> internalValueMap = new Internal.EnumLiteMap<Destination>() {
            /* class android.os.IncidentMetadata.Destination.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Destination findValueByNumber(int number) {
                return Destination.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Destination valueOf(int value2) {
            return forNumber(value2);
        }

        public static Destination forNumber(int value2) {
            if (value2 == 0) {
                return AUTOMATIC;
            }
            if (value2 == 1) {
                return EXPLICIT;
            }
            if (value2 != 2) {
                return null;
            }
            return LOCAL;
        }

        public static Internal.EnumLiteMap<Destination> internalGetValueMap() {
            return internalValueMap;
        }

        private Destination(int value2) {
            this.value = value2;
        }
    }

    public static final class SectionStats extends GeneratedMessageLite<SectionStats, Builder> implements SectionStatsOrBuilder {
        private static final SectionStats DEFAULT_INSTANCE = new SectionStats();
        public static final int DUMP_DURATION_MS_FIELD_NUMBER = 6;
        public static final int DUMP_SIZE_BYTES_FIELD_NUMBER = 5;
        public static final int ERROR_MSG_FIELD_NUMBER = 9;
        public static final int EXEC_DURATION_MS_FIELD_NUMBER = 4;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int IS_TRUNCATED_FIELD_NUMBER = 8;
        private static volatile Parser<SectionStats> PARSER = null;
        public static final int REPORT_SIZE_BYTES_FIELD_NUMBER = 3;
        public static final int SUCCESS_FIELD_NUMBER = 2;
        public static final int TIMED_OUT_FIELD_NUMBER = 7;
        private int bitField0_;
        private long dumpDurationMs_ = 0;
        private int dumpSizeBytes_ = 0;
        private String errorMsg_ = "";
        private long execDurationMs_ = 0;
        private int id_ = 0;
        private boolean isTruncated_ = false;
        private int reportSizeBytes_ = 0;
        private boolean success_ = false;
        private boolean timedOut_ = false;

        private SectionStats() {
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasSuccess() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean getSuccess() {
            return this.success_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSuccess(boolean value) {
            this.bitField0_ |= 2;
            this.success_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSuccess() {
            this.bitField0_ &= -3;
            this.success_ = false;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasReportSizeBytes() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public int getReportSizeBytes() {
            return this.reportSizeBytes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReportSizeBytes(int value) {
            this.bitField0_ |= 4;
            this.reportSizeBytes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReportSizeBytes() {
            this.bitField0_ &= -5;
            this.reportSizeBytes_ = 0;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasExecDurationMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public long getExecDurationMs() {
            return this.execDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExecDurationMs(long value) {
            this.bitField0_ |= 8;
            this.execDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExecDurationMs() {
            this.bitField0_ &= -9;
            this.execDurationMs_ = 0;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasDumpSizeBytes() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public int getDumpSizeBytes() {
            return this.dumpSizeBytes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDumpSizeBytes(int value) {
            this.bitField0_ |= 16;
            this.dumpSizeBytes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDumpSizeBytes() {
            this.bitField0_ &= -17;
            this.dumpSizeBytes_ = 0;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasDumpDurationMs() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public long getDumpDurationMs() {
            return this.dumpDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDumpDurationMs(long value) {
            this.bitField0_ |= 32;
            this.dumpDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDumpDurationMs() {
            this.bitField0_ &= -33;
            this.dumpDurationMs_ = 0;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasTimedOut() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean getTimedOut() {
            return this.timedOut_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimedOut(boolean value) {
            this.bitField0_ |= 64;
            this.timedOut_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimedOut() {
            this.bitField0_ &= -65;
            this.timedOut_ = false;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasIsTruncated() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean getIsTruncated() {
            return this.isTruncated_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsTruncated(boolean value) {
            this.bitField0_ |= 128;
            this.isTruncated_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsTruncated() {
            this.bitField0_ &= -129;
            this.isTruncated_ = false;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public boolean hasErrorMsg() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public String getErrorMsg() {
            return this.errorMsg_;
        }

        @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
        public ByteString getErrorMsgBytes() {
            return ByteString.copyFromUtf8(this.errorMsg_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorMsg(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.errorMsg_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorMsg() {
            this.bitField0_ &= -257;
            this.errorMsg_ = getDefaultInstance().getErrorMsg();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorMsgBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.errorMsg_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.success_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.reportSizeBytes_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.execDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.dumpSizeBytes_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.dumpDurationMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBool(7, this.timedOut_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.isTruncated_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getErrorMsg());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.success_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.reportSizeBytes_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.execDurationMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.dumpSizeBytes_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.dumpDurationMs_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBoolSize(7, this.timedOut_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.isTruncated_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getErrorMsg());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SectionStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SectionStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SectionStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SectionStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SectionStats parseFrom(InputStream input) throws IOException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SectionStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SectionStats parseDelimitedFrom(InputStream input) throws IOException {
            return (SectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SectionStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SectionStats parseFrom(CodedInputStream input) throws IOException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SectionStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SectionStats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SectionStats, Builder> implements SectionStatsOrBuilder {
            private Builder() {
                super(SectionStats.DEFAULT_INSTANCE);
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasId() {
                return ((SectionStats) this.instance).hasId();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public int getId() {
                return ((SectionStats) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((SectionStats) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((SectionStats) this.instance).clearId();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasSuccess() {
                return ((SectionStats) this.instance).hasSuccess();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean getSuccess() {
                return ((SectionStats) this.instance).getSuccess();
            }

            public Builder setSuccess(boolean value) {
                copyOnWrite();
                ((SectionStats) this.instance).setSuccess(value);
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((SectionStats) this.instance).clearSuccess();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasReportSizeBytes() {
                return ((SectionStats) this.instance).hasReportSizeBytes();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public int getReportSizeBytes() {
                return ((SectionStats) this.instance).getReportSizeBytes();
            }

            public Builder setReportSizeBytes(int value) {
                copyOnWrite();
                ((SectionStats) this.instance).setReportSizeBytes(value);
                return this;
            }

            public Builder clearReportSizeBytes() {
                copyOnWrite();
                ((SectionStats) this.instance).clearReportSizeBytes();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasExecDurationMs() {
                return ((SectionStats) this.instance).hasExecDurationMs();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public long getExecDurationMs() {
                return ((SectionStats) this.instance).getExecDurationMs();
            }

            public Builder setExecDurationMs(long value) {
                copyOnWrite();
                ((SectionStats) this.instance).setExecDurationMs(value);
                return this;
            }

            public Builder clearExecDurationMs() {
                copyOnWrite();
                ((SectionStats) this.instance).clearExecDurationMs();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasDumpSizeBytes() {
                return ((SectionStats) this.instance).hasDumpSizeBytes();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public int getDumpSizeBytes() {
                return ((SectionStats) this.instance).getDumpSizeBytes();
            }

            public Builder setDumpSizeBytes(int value) {
                copyOnWrite();
                ((SectionStats) this.instance).setDumpSizeBytes(value);
                return this;
            }

            public Builder clearDumpSizeBytes() {
                copyOnWrite();
                ((SectionStats) this.instance).clearDumpSizeBytes();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasDumpDurationMs() {
                return ((SectionStats) this.instance).hasDumpDurationMs();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public long getDumpDurationMs() {
                return ((SectionStats) this.instance).getDumpDurationMs();
            }

            public Builder setDumpDurationMs(long value) {
                copyOnWrite();
                ((SectionStats) this.instance).setDumpDurationMs(value);
                return this;
            }

            public Builder clearDumpDurationMs() {
                copyOnWrite();
                ((SectionStats) this.instance).clearDumpDurationMs();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasTimedOut() {
                return ((SectionStats) this.instance).hasTimedOut();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean getTimedOut() {
                return ((SectionStats) this.instance).getTimedOut();
            }

            public Builder setTimedOut(boolean value) {
                copyOnWrite();
                ((SectionStats) this.instance).setTimedOut(value);
                return this;
            }

            public Builder clearTimedOut() {
                copyOnWrite();
                ((SectionStats) this.instance).clearTimedOut();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasIsTruncated() {
                return ((SectionStats) this.instance).hasIsTruncated();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean getIsTruncated() {
                return ((SectionStats) this.instance).getIsTruncated();
            }

            public Builder setIsTruncated(boolean value) {
                copyOnWrite();
                ((SectionStats) this.instance).setIsTruncated(value);
                return this;
            }

            public Builder clearIsTruncated() {
                copyOnWrite();
                ((SectionStats) this.instance).clearIsTruncated();
                return this;
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public boolean hasErrorMsg() {
                return ((SectionStats) this.instance).hasErrorMsg();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public String getErrorMsg() {
                return ((SectionStats) this.instance).getErrorMsg();
            }

            @Override // android.os.IncidentMetadata.SectionStatsOrBuilder
            public ByteString getErrorMsgBytes() {
                return ((SectionStats) this.instance).getErrorMsgBytes();
            }

            public Builder setErrorMsg(String value) {
                copyOnWrite();
                ((SectionStats) this.instance).setErrorMsg(value);
                return this;
            }

            public Builder clearErrorMsg() {
                copyOnWrite();
                ((SectionStats) this.instance).clearErrorMsg();
                return this;
            }

            public Builder setErrorMsgBytes(ByteString value) {
                copyOnWrite();
                ((SectionStats) this.instance).setErrorMsgBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SectionStats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SectionStats other = (SectionStats) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.success_ = visitor.visitBoolean(hasSuccess(), this.success_, other.hasSuccess(), other.success_);
                    this.reportSizeBytes_ = visitor.visitInt(hasReportSizeBytes(), this.reportSizeBytes_, other.hasReportSizeBytes(), other.reportSizeBytes_);
                    this.execDurationMs_ = visitor.visitLong(hasExecDurationMs(), this.execDurationMs_, other.hasExecDurationMs(), other.execDurationMs_);
                    this.dumpSizeBytes_ = visitor.visitInt(hasDumpSizeBytes(), this.dumpSizeBytes_, other.hasDumpSizeBytes(), other.dumpSizeBytes_);
                    this.dumpDurationMs_ = visitor.visitLong(hasDumpDurationMs(), this.dumpDurationMs_, other.hasDumpDurationMs(), other.dumpDurationMs_);
                    this.timedOut_ = visitor.visitBoolean(hasTimedOut(), this.timedOut_, other.hasTimedOut(), other.timedOut_);
                    this.isTruncated_ = visitor.visitBoolean(hasIsTruncated(), this.isTruncated_, other.hasIsTruncated(), other.isTruncated_);
                    this.errorMsg_ = visitor.visitString(hasErrorMsg(), this.errorMsg_, other.hasErrorMsg(), other.errorMsg_);
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
                                this.id_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.success_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.reportSizeBytes_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.execDurationMs_ = input.readInt64();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.dumpSizeBytes_ = input.readInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.dumpDurationMs_ = input.readInt64();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.timedOut_ = input.readBool();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.isTruncated_ = input.readBool();
                            } else if (tag == 74) {
                                String s = input.readString();
                                this.bitField0_ |= 256;
                                this.errorMsg_ = s;
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
                        synchronized (SectionStats.class) {
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

        public static SectionStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SectionStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean hasReportId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public long getReportId() {
        return this.reportId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportId(long value) {
        this.bitField0_ |= 1;
        this.reportId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportId() {
        this.bitField0_ &= -2;
        this.reportId_ = 0;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean hasSequenceNumber() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public int getSequenceNumber() {
        return this.sequenceNumber_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSequenceNumber(int value) {
        this.bitField0_ |= 2;
        this.sequenceNumber_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSequenceNumber() {
        this.bitField0_ &= -3;
        this.sequenceNumber_ = 0;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean hasDest() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public Destination getDest() {
        Destination result = Destination.forNumber(this.dest_);
        return result == null ? Destination.AUTOMATIC : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDest(Destination value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.dest_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDest() {
        this.bitField0_ &= -5;
        this.dest_ = 0;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean hasRequestSize() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public int getRequestSize() {
        return this.requestSize_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequestSize(int value) {
        this.bitField0_ |= 8;
        this.requestSize_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequestSize() {
        this.bitField0_ &= -9;
        this.requestSize_ = 0;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean hasUseDropbox() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public boolean getUseDropbox() {
        return this.useDropbox_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUseDropbox(boolean value) {
        this.bitField0_ |= 16;
        this.useDropbox_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUseDropbox() {
        this.bitField0_ &= -17;
        this.useDropbox_ = false;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public List<SectionStats> getSectionsList() {
        return this.sections_;
    }

    public List<? extends SectionStatsOrBuilder> getSectionsOrBuilderList() {
        return this.sections_;
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public int getSectionsCount() {
        return this.sections_.size();
    }

    @Override // android.os.IncidentMetadataOrBuilder
    public SectionStats getSections(int index) {
        return this.sections_.get(index);
    }

    public SectionStatsOrBuilder getSectionsOrBuilder(int index) {
        return this.sections_.get(index);
    }

    private void ensureSectionsIsMutable() {
        if (!this.sections_.isModifiable()) {
            this.sections_ = GeneratedMessageLite.mutableCopy(this.sections_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSections(int index, SectionStats value) {
        if (value != null) {
            ensureSectionsIsMutable();
            this.sections_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSections(int index, SectionStats.Builder builderForValue) {
        ensureSectionsIsMutable();
        this.sections_.set(index, (SectionStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSections(SectionStats value) {
        if (value != null) {
            ensureSectionsIsMutable();
            this.sections_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSections(int index, SectionStats value) {
        if (value != null) {
            ensureSectionsIsMutable();
            this.sections_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSections(SectionStats.Builder builderForValue) {
        ensureSectionsIsMutable();
        this.sections_.add((SectionStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSections(int index, SectionStats.Builder builderForValue) {
        ensureSectionsIsMutable();
        this.sections_.add(index, (SectionStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSections(Iterable<? extends SectionStats> values) {
        ensureSectionsIsMutable();
        AbstractMessageLite.addAll(values, this.sections_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSections() {
        this.sections_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSections(int index) {
        ensureSectionsIsMutable();
        this.sections_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.reportId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.sequenceNumber_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.dest_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.requestSize_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.useDropbox_);
        }
        for (int i = 0; i < this.sections_.size(); i++) {
            output.writeMessage(6, this.sections_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.reportId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.sequenceNumber_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.dest_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.requestSize_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.useDropbox_);
        }
        for (int i = 0; i < this.sections_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.sections_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IncidentMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentMetadata parseFrom(InputStream input) throws IOException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentMetadata parseDelimitedFrom(InputStream input) throws IOException {
        return (IncidentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentMetadata parseFrom(CodedInputStream input) throws IOException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IncidentMetadata prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IncidentMetadata, Builder> implements IncidentMetadataOrBuilder {
        private Builder() {
            super(IncidentMetadata.DEFAULT_INSTANCE);
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean hasReportId() {
            return ((IncidentMetadata) this.instance).hasReportId();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public long getReportId() {
            return ((IncidentMetadata) this.instance).getReportId();
        }

        public Builder setReportId(long value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setReportId(value);
            return this;
        }

        public Builder clearReportId() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearReportId();
            return this;
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean hasSequenceNumber() {
            return ((IncidentMetadata) this.instance).hasSequenceNumber();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public int getSequenceNumber() {
            return ((IncidentMetadata) this.instance).getSequenceNumber();
        }

        public Builder setSequenceNumber(int value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setSequenceNumber(value);
            return this;
        }

        public Builder clearSequenceNumber() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearSequenceNumber();
            return this;
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean hasDest() {
            return ((IncidentMetadata) this.instance).hasDest();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public Destination getDest() {
            return ((IncidentMetadata) this.instance).getDest();
        }

        public Builder setDest(Destination value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setDest(value);
            return this;
        }

        public Builder clearDest() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearDest();
            return this;
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean hasRequestSize() {
            return ((IncidentMetadata) this.instance).hasRequestSize();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public int getRequestSize() {
            return ((IncidentMetadata) this.instance).getRequestSize();
        }

        public Builder setRequestSize(int value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setRequestSize(value);
            return this;
        }

        public Builder clearRequestSize() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearRequestSize();
            return this;
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean hasUseDropbox() {
            return ((IncidentMetadata) this.instance).hasUseDropbox();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public boolean getUseDropbox() {
            return ((IncidentMetadata) this.instance).getUseDropbox();
        }

        public Builder setUseDropbox(boolean value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setUseDropbox(value);
            return this;
        }

        public Builder clearUseDropbox() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearUseDropbox();
            return this;
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public List<SectionStats> getSectionsList() {
            return Collections.unmodifiableList(((IncidentMetadata) this.instance).getSectionsList());
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public int getSectionsCount() {
            return ((IncidentMetadata) this.instance).getSectionsCount();
        }

        @Override // android.os.IncidentMetadataOrBuilder
        public SectionStats getSections(int index) {
            return ((IncidentMetadata) this.instance).getSections(index);
        }

        public Builder setSections(int index, SectionStats value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setSections((IncidentMetadata) index, (int) value);
            return this;
        }

        public Builder setSections(int index, SectionStats.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).setSections((IncidentMetadata) index, (int) builderForValue);
            return this;
        }

        public Builder addSections(SectionStats value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).addSections((IncidentMetadata) value);
            return this;
        }

        public Builder addSections(int index, SectionStats value) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).addSections((IncidentMetadata) index, (int) value);
            return this;
        }

        public Builder addSections(SectionStats.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).addSections((IncidentMetadata) builderForValue);
            return this;
        }

        public Builder addSections(int index, SectionStats.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).addSections((IncidentMetadata) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSections(Iterable<? extends SectionStats> values) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).addAllSections(values);
            return this;
        }

        public Builder clearSections() {
            copyOnWrite();
            ((IncidentMetadata) this.instance).clearSections();
            return this;
        }

        public Builder removeSections(int index) {
            copyOnWrite();
            ((IncidentMetadata) this.instance).removeSections(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IncidentMetadata();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.sections_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IncidentMetadata other = (IncidentMetadata) arg1;
                this.reportId_ = visitor.visitLong(hasReportId(), this.reportId_, other.hasReportId(), other.reportId_);
                this.sequenceNumber_ = visitor.visitInt(hasSequenceNumber(), this.sequenceNumber_, other.hasSequenceNumber(), other.sequenceNumber_);
                this.dest_ = visitor.visitInt(hasDest(), this.dest_, other.hasDest(), other.dest_);
                this.requestSize_ = visitor.visitInt(hasRequestSize(), this.requestSize_, other.hasRequestSize(), other.requestSize_);
                this.useDropbox_ = visitor.visitBoolean(hasUseDropbox(), this.useDropbox_, other.hasUseDropbox(), other.useDropbox_);
                this.sections_ = visitor.visitList(this.sections_, other.sections_);
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
                            this.reportId_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.sequenceNumber_ = input.readInt32();
                        } else if (tag == 24) {
                            int rawValue = input.readEnum();
                            if (Destination.forNumber(rawValue) == null) {
                                super.mergeVarintField(3, rawValue);
                            } else {
                                this.bitField0_ |= 4;
                                this.dest_ = rawValue;
                            }
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.requestSize_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.useDropbox_ = input.readBool();
                        } else if (tag == 50) {
                            if (!this.sections_.isModifiable()) {
                                this.sections_ = GeneratedMessageLite.mutableCopy(this.sections_);
                            }
                            this.sections_.add((SectionStats) input.readMessage(SectionStats.parser(), extensionRegistry));
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
                    synchronized (IncidentMetadata.class) {
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

    public static IncidentMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IncidentMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
