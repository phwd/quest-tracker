package android.util;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class TextLogEntry extends GeneratedMessageLite<TextLogEntry, Builder> implements TextLogEntryOrBuilder {
    private static final TextLogEntry DEFAULT_INSTANCE = new TextLogEntry();
    public static final int LOG_FIELD_NUMBER = 8;
    public static final int NANOSEC_FIELD_NUMBER = 2;
    private static volatile Parser<TextLogEntry> PARSER = null;
    public static final int PID_FIELD_NUMBER = 5;
    public static final int PRIORITY_FIELD_NUMBER = 3;
    public static final int SEC_FIELD_NUMBER = 1;
    public static final int TAG_FIELD_NUMBER = 7;
    public static final int TID_FIELD_NUMBER = 6;
    public static final int UID_FIELD_NUMBER = 4;
    private int bitField0_;
    private String log_ = "";
    private long nanosec_ = 0;
    private int pid_ = 0;
    private int priority_ = 0;
    private long sec_ = 0;
    private String tag_ = "";
    private int tid_ = 0;
    private int uid_ = 0;

    private TextLogEntry() {
    }

    public enum LogPriority implements Internal.EnumLite {
        LOG_UNKNOWN(0),
        LOG_DEFAULT(1),
        LOG_VERBOSE(2),
        LOG_DEBUG(3),
        LOG_INFO(4),
        LOG_WARN(5),
        LOG_ERROR(6),
        LOG_FATAL(7),
        LOG_SILENT(8);
        
        public static final int LOG_DEBUG_VALUE = 3;
        public static final int LOG_DEFAULT_VALUE = 1;
        public static final int LOG_ERROR_VALUE = 6;
        public static final int LOG_FATAL_VALUE = 7;
        public static final int LOG_INFO_VALUE = 4;
        public static final int LOG_SILENT_VALUE = 8;
        public static final int LOG_UNKNOWN_VALUE = 0;
        public static final int LOG_VERBOSE_VALUE = 2;
        public static final int LOG_WARN_VALUE = 5;
        private static final Internal.EnumLiteMap<LogPriority> internalValueMap = new Internal.EnumLiteMap<LogPriority>() {
            /* class android.util.TextLogEntry.LogPriority.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LogPriority findValueByNumber(int number) {
                return LogPriority.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LogPriority valueOf(int value2) {
            return forNumber(value2);
        }

        public static LogPriority forNumber(int value2) {
            switch (value2) {
                case 0:
                    return LOG_UNKNOWN;
                case 1:
                    return LOG_DEFAULT;
                case 2:
                    return LOG_VERBOSE;
                case 3:
                    return LOG_DEBUG;
                case 4:
                    return LOG_INFO;
                case 5:
                    return LOG_WARN;
                case 6:
                    return LOG_ERROR;
                case 7:
                    return LOG_FATAL;
                case 8:
                    return LOG_SILENT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LogPriority> internalGetValueMap() {
            return internalValueMap;
        }

        private LogPriority(int value2) {
            this.value = value2;
        }
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasSec() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public long getSec() {
        return this.sec_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSec(long value) {
        this.bitField0_ |= 1;
        this.sec_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSec() {
        this.bitField0_ &= -2;
        this.sec_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasNanosec() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public long getNanosec() {
        return this.nanosec_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNanosec(long value) {
        this.bitField0_ |= 2;
        this.nanosec_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNanosec() {
        this.bitField0_ &= -3;
        this.nanosec_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasPriority() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public LogPriority getPriority() {
        LogPriority result = LogPriority.forNumber(this.priority_);
        return result == null ? LogPriority.LOG_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriority(LogPriority value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.priority_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriority() {
        this.bitField0_ &= -5;
        this.priority_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 8;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -9;
        this.uid_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 16;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -17;
        this.pid_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasTid() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public int getTid() {
        return this.tid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTid(int value) {
        this.bitField0_ |= 32;
        this.tid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTid() {
        this.bitField0_ &= -33;
        this.tid_ = 0;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -65;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.util.TextLogEntryOrBuilder
    public boolean hasLog() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public String getLog() {
        return this.log_;
    }

    @Override // android.util.TextLogEntryOrBuilder
    public ByteString getLogBytes() {
        return ByteString.copyFromUtf8(this.log_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLog(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.log_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLog() {
        this.bitField0_ &= -129;
        this.log_ = getDefaultInstance().getLog();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLogBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.log_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeUInt64(1, this.sec_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeUInt64(2, this.nanosec_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.priority_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.uid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.pid_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.tid_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getTag());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getLog());
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
            size2 = 0 + CodedOutputStream.computeUInt64Size(1, this.sec_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeUInt64Size(2, this.nanosec_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.priority_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.uid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.pid_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.tid_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getTag());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getLog());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static TextLogEntry parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TextLogEntry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TextLogEntry parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TextLogEntry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TextLogEntry parseFrom(InputStream input) throws IOException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TextLogEntry parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TextLogEntry parseDelimitedFrom(InputStream input) throws IOException {
        return (TextLogEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static TextLogEntry parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TextLogEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TextLogEntry parseFrom(CodedInputStream input) throws IOException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TextLogEntry parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TextLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TextLogEntry prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TextLogEntry, Builder> implements TextLogEntryOrBuilder {
        private Builder() {
            super(TextLogEntry.DEFAULT_INSTANCE);
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasSec() {
            return ((TextLogEntry) this.instance).hasSec();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public long getSec() {
            return ((TextLogEntry) this.instance).getSec();
        }

        public Builder setSec(long value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setSec(value);
            return this;
        }

        public Builder clearSec() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearSec();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasNanosec() {
            return ((TextLogEntry) this.instance).hasNanosec();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public long getNanosec() {
            return ((TextLogEntry) this.instance).getNanosec();
        }

        public Builder setNanosec(long value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setNanosec(value);
            return this;
        }

        public Builder clearNanosec() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearNanosec();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasPriority() {
            return ((TextLogEntry) this.instance).hasPriority();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public LogPriority getPriority() {
            return ((TextLogEntry) this.instance).getPriority();
        }

        public Builder setPriority(LogPriority value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setPriority(value);
            return this;
        }

        public Builder clearPriority() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearPriority();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasUid() {
            return ((TextLogEntry) this.instance).hasUid();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public int getUid() {
            return ((TextLogEntry) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearUid();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasPid() {
            return ((TextLogEntry) this.instance).hasPid();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public int getPid() {
            return ((TextLogEntry) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearPid();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasTid() {
            return ((TextLogEntry) this.instance).hasTid();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public int getTid() {
            return ((TextLogEntry) this.instance).getTid();
        }

        public Builder setTid(int value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setTid(value);
            return this;
        }

        public Builder clearTid() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearTid();
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasTag() {
            return ((TextLogEntry) this.instance).hasTag();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public String getTag() {
            return ((TextLogEntry) this.instance).getTag();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public ByteString getTagBytes() {
            return ((TextLogEntry) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setTagBytes(value);
            return this;
        }

        @Override // android.util.TextLogEntryOrBuilder
        public boolean hasLog() {
            return ((TextLogEntry) this.instance).hasLog();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public String getLog() {
            return ((TextLogEntry) this.instance).getLog();
        }

        @Override // android.util.TextLogEntryOrBuilder
        public ByteString getLogBytes() {
            return ((TextLogEntry) this.instance).getLogBytes();
        }

        public Builder setLog(String value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setLog(value);
            return this;
        }

        public Builder clearLog() {
            copyOnWrite();
            ((TextLogEntry) this.instance).clearLog();
            return this;
        }

        public Builder setLogBytes(ByteString value) {
            copyOnWrite();
            ((TextLogEntry) this.instance).setLogBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new TextLogEntry();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                TextLogEntry other = (TextLogEntry) arg1;
                this.sec_ = visitor.visitLong(hasSec(), this.sec_, other.hasSec(), other.sec_);
                this.nanosec_ = visitor.visitLong(hasNanosec(), this.nanosec_, other.hasNanosec(), other.nanosec_);
                this.priority_ = visitor.visitInt(hasPriority(), this.priority_, other.hasPriority(), other.priority_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.tid_ = visitor.visitInt(hasTid(), this.tid_, other.hasTid(), other.tid_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.log_ = visitor.visitString(hasLog(), this.log_, other.hasLog(), other.log_);
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
                            this.sec_ = input.readUInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.nanosec_ = input.readUInt64();
                        } else if (tag == 24) {
                            int rawValue = input.readEnum();
                            if (LogPriority.forNumber(rawValue) == null) {
                                super.mergeVarintField(3, rawValue);
                            } else {
                                this.bitField0_ |= 4;
                                this.priority_ = rawValue;
                            }
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.uid_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.pid_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.tid_ = input.readInt32();
                        } else if (tag == 58) {
                            String s = input.readString();
                            this.bitField0_ |= 64;
                            this.tag_ = s;
                        } else if (tag == 66) {
                            String s2 = input.readString();
                            this.bitField0_ |= 128;
                            this.log_ = s2;
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
                    synchronized (TextLogEntry.class) {
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

    public static TextLogEntry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TextLogEntry> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
