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

public final class ProcrankProto extends GeneratedMessageLite<ProcrankProto, Builder> implements ProcrankProtoOrBuilder {
    private static final ProcrankProto DEFAULT_INSTANCE = new ProcrankProto();
    private static volatile Parser<ProcrankProto> PARSER = null;
    public static final int PROCESSES_FIELD_NUMBER = 1;
    public static final int SUMMARY_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList<Process> processes_ = emptyProtobufList();
    private Summary summary_;

    public interface ProcessOrBuilder extends MessageLiteOrBuilder {
        String getCmdline();

        ByteString getCmdlineBytes();

        int getPid();

        long getPss();

        long getPswap();

        long getRss();

        long getSwap();

        long getUss();

        long getUswap();

        long getVss();

        long getZswap();

        boolean hasCmdline();

        boolean hasPid();

        boolean hasPss();

        boolean hasPswap();

        boolean hasRss();

        boolean hasSwap();

        boolean hasUss();

        boolean hasUswap();

        boolean hasVss();

        boolean hasZswap();
    }

    public interface SummaryOrBuilder extends MessageLiteOrBuilder {
        Summary.Ram getRam();

        Process getTotal();

        Summary.Zram getZram();

        boolean hasRam();

        boolean hasTotal();

        boolean hasZram();
    }

    private ProcrankProto() {
    }

    public static final class Process extends GeneratedMessageLite<Process, Builder> implements ProcessOrBuilder {
        public static final int CMDLINE_FIELD_NUMBER = 10;
        private static final Process DEFAULT_INSTANCE = new Process();
        private static volatile Parser<Process> PARSER = null;
        public static final int PID_FIELD_NUMBER = 1;
        public static final int PSS_FIELD_NUMBER = 4;
        public static final int PSWAP_FIELD_NUMBER = 7;
        public static final int RSS_FIELD_NUMBER = 3;
        public static final int SWAP_FIELD_NUMBER = 6;
        public static final int USS_FIELD_NUMBER = 5;
        public static final int USWAP_FIELD_NUMBER = 8;
        public static final int VSS_FIELD_NUMBER = 2;
        public static final int ZSWAP_FIELD_NUMBER = 9;
        private int bitField0_;
        private String cmdline_ = "";
        private int pid_ = 0;
        private long pss_ = 0;
        private long pswap_ = 0;
        private long rss_ = 0;
        private long swap_ = 0;
        private long uss_ = 0;
        private long uswap_ = 0;
        private long vss_ = 0;
        private long zswap_ = 0;

        private Process() {
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasPid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public int getPid() {
            return this.pid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPid(int value) {
            this.bitField0_ |= 1;
            this.pid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPid() {
            this.bitField0_ &= -2;
            this.pid_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasVss() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getVss() {
            return this.vss_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVss(long value) {
            this.bitField0_ |= 2;
            this.vss_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVss() {
            this.bitField0_ &= -3;
            this.vss_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasRss() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getRss() {
            return this.rss_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRss(long value) {
            this.bitField0_ |= 4;
            this.rss_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRss() {
            this.bitField0_ &= -5;
            this.rss_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasPss() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getPss() {
            return this.pss_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPss(long value) {
            this.bitField0_ |= 8;
            this.pss_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPss() {
            this.bitField0_ &= -9;
            this.pss_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasUss() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getUss() {
            return this.uss_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUss(long value) {
            this.bitField0_ |= 16;
            this.uss_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUss() {
            this.bitField0_ &= -17;
            this.uss_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasSwap() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getSwap() {
            return this.swap_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSwap(long value) {
            this.bitField0_ |= 32;
            this.swap_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSwap() {
            this.bitField0_ &= -33;
            this.swap_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasPswap() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getPswap() {
            return this.pswap_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPswap(long value) {
            this.bitField0_ |= 64;
            this.pswap_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPswap() {
            this.bitField0_ &= -65;
            this.pswap_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasUswap() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getUswap() {
            return this.uswap_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUswap(long value) {
            this.bitField0_ |= 128;
            this.uswap_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUswap() {
            this.bitField0_ &= -129;
            this.uswap_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasZswap() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public long getZswap() {
            return this.zswap_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setZswap(long value) {
            this.bitField0_ |= 256;
            this.zswap_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearZswap() {
            this.bitField0_ &= -257;
            this.zswap_ = 0;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public boolean hasCmdline() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public String getCmdline() {
            return this.cmdline_;
        }

        @Override // android.os.ProcrankProto.ProcessOrBuilder
        public ByteString getCmdlineBytes() {
            return ByteString.copyFromUtf8(this.cmdline_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmdline(String value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.cmdline_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCmdline() {
            this.bitField0_ &= -513;
            this.cmdline_ = getDefaultInstance().getCmdline();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmdlineBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.cmdline_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.vss_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.rss_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.pss_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.uss_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.swap_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.pswap_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.uswap_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.zswap_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeString(10, getCmdline());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.vss_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.rss_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.pss_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.uss_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.swap_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.pswap_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.uswap_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.zswap_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeStringSize(10, getCmdline());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Process parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Process parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Process parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Process parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Process parseFrom(InputStream input) throws IOException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Process parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Process parseDelimitedFrom(InputStream input) throws IOException {
            return (Process) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Process parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Process) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Process parseFrom(CodedInputStream input) throws IOException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Process parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Process) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Process prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Process, Builder> implements ProcessOrBuilder {
            private Builder() {
                super(Process.DEFAULT_INSTANCE);
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasPid() {
                return ((Process) this.instance).hasPid();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public int getPid() {
                return ((Process) this.instance).getPid();
            }

            public Builder setPid(int value) {
                copyOnWrite();
                ((Process) this.instance).setPid(value);
                return this;
            }

            public Builder clearPid() {
                copyOnWrite();
                ((Process) this.instance).clearPid();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasVss() {
                return ((Process) this.instance).hasVss();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getVss() {
                return ((Process) this.instance).getVss();
            }

            public Builder setVss(long value) {
                copyOnWrite();
                ((Process) this.instance).setVss(value);
                return this;
            }

            public Builder clearVss() {
                copyOnWrite();
                ((Process) this.instance).clearVss();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasRss() {
                return ((Process) this.instance).hasRss();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getRss() {
                return ((Process) this.instance).getRss();
            }

            public Builder setRss(long value) {
                copyOnWrite();
                ((Process) this.instance).setRss(value);
                return this;
            }

            public Builder clearRss() {
                copyOnWrite();
                ((Process) this.instance).clearRss();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasPss() {
                return ((Process) this.instance).hasPss();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getPss() {
                return ((Process) this.instance).getPss();
            }

            public Builder setPss(long value) {
                copyOnWrite();
                ((Process) this.instance).setPss(value);
                return this;
            }

            public Builder clearPss() {
                copyOnWrite();
                ((Process) this.instance).clearPss();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasUss() {
                return ((Process) this.instance).hasUss();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getUss() {
                return ((Process) this.instance).getUss();
            }

            public Builder setUss(long value) {
                copyOnWrite();
                ((Process) this.instance).setUss(value);
                return this;
            }

            public Builder clearUss() {
                copyOnWrite();
                ((Process) this.instance).clearUss();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasSwap() {
                return ((Process) this.instance).hasSwap();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getSwap() {
                return ((Process) this.instance).getSwap();
            }

            public Builder setSwap(long value) {
                copyOnWrite();
                ((Process) this.instance).setSwap(value);
                return this;
            }

            public Builder clearSwap() {
                copyOnWrite();
                ((Process) this.instance).clearSwap();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasPswap() {
                return ((Process) this.instance).hasPswap();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getPswap() {
                return ((Process) this.instance).getPswap();
            }

            public Builder setPswap(long value) {
                copyOnWrite();
                ((Process) this.instance).setPswap(value);
                return this;
            }

            public Builder clearPswap() {
                copyOnWrite();
                ((Process) this.instance).clearPswap();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasUswap() {
                return ((Process) this.instance).hasUswap();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getUswap() {
                return ((Process) this.instance).getUswap();
            }

            public Builder setUswap(long value) {
                copyOnWrite();
                ((Process) this.instance).setUswap(value);
                return this;
            }

            public Builder clearUswap() {
                copyOnWrite();
                ((Process) this.instance).clearUswap();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasZswap() {
                return ((Process) this.instance).hasZswap();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public long getZswap() {
                return ((Process) this.instance).getZswap();
            }

            public Builder setZswap(long value) {
                copyOnWrite();
                ((Process) this.instance).setZswap(value);
                return this;
            }

            public Builder clearZswap() {
                copyOnWrite();
                ((Process) this.instance).clearZswap();
                return this;
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public boolean hasCmdline() {
                return ((Process) this.instance).hasCmdline();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public String getCmdline() {
                return ((Process) this.instance).getCmdline();
            }

            @Override // android.os.ProcrankProto.ProcessOrBuilder
            public ByteString getCmdlineBytes() {
                return ((Process) this.instance).getCmdlineBytes();
            }

            public Builder setCmdline(String value) {
                copyOnWrite();
                ((Process) this.instance).setCmdline(value);
                return this;
            }

            public Builder clearCmdline() {
                copyOnWrite();
                ((Process) this.instance).clearCmdline();
                return this;
            }

            public Builder setCmdlineBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setCmdlineBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Process();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Process other = (Process) arg1;
                    this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                    this.vss_ = visitor.visitLong(hasVss(), this.vss_, other.hasVss(), other.vss_);
                    this.rss_ = visitor.visitLong(hasRss(), this.rss_, other.hasRss(), other.rss_);
                    this.pss_ = visitor.visitLong(hasPss(), this.pss_, other.hasPss(), other.pss_);
                    this.uss_ = visitor.visitLong(hasUss(), this.uss_, other.hasUss(), other.uss_);
                    this.swap_ = visitor.visitLong(hasSwap(), this.swap_, other.hasSwap(), other.swap_);
                    this.pswap_ = visitor.visitLong(hasPswap(), this.pswap_, other.hasPswap(), other.pswap_);
                    this.uswap_ = visitor.visitLong(hasUswap(), this.uswap_, other.hasUswap(), other.uswap_);
                    this.zswap_ = visitor.visitLong(hasZswap(), this.zswap_, other.hasZswap(), other.zswap_);
                    this.cmdline_ = visitor.visitString(hasCmdline(), this.cmdline_, other.hasCmdline(), other.cmdline_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.pid_ = input.readInt32();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.vss_ = input.readInt64();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.rss_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.pss_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.uss_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.swap_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.pswap_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.uswap_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.zswap_ = input.readInt64();
                                    break;
                                case 82:
                                    String s = input.readString();
                                    this.bitField0_ |= 512;
                                    this.cmdline_ = s;
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
                        synchronized (Process.class) {
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

        public static Process getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Process> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Summary extends GeneratedMessageLite<Summary, Builder> implements SummaryOrBuilder {
        private static final Summary DEFAULT_INSTANCE = new Summary();
        private static volatile Parser<Summary> PARSER = null;
        public static final int RAM_FIELD_NUMBER = 3;
        public static final int TOTAL_FIELD_NUMBER = 1;
        public static final int ZRAM_FIELD_NUMBER = 2;
        private int bitField0_;
        private Ram ram_;
        private Process total_;
        private Zram zram_;

        public interface RamOrBuilder extends MessageLiteOrBuilder {
            String getRawText();

            ByteString getRawTextBytes();

            boolean hasRawText();
        }

        public interface ZramOrBuilder extends MessageLiteOrBuilder {
            String getRawText();

            ByteString getRawTextBytes();

            boolean hasRawText();
        }

        private Summary() {
        }

        public static final class Zram extends GeneratedMessageLite<Zram, Builder> implements ZramOrBuilder {
            private static final Zram DEFAULT_INSTANCE = new Zram();
            private static volatile Parser<Zram> PARSER = null;
            public static final int RAW_TEXT_FIELD_NUMBER = 1;
            private int bitField0_;
            private String rawText_ = "";

            private Zram() {
            }

            @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
            public boolean hasRawText() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
            public String getRawText() {
                return this.rawText_;
            }

            @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
            public ByteString getRawTextBytes() {
                return ByteString.copyFromUtf8(this.rawText_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRawText(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.rawText_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRawText() {
                this.bitField0_ &= -2;
                this.rawText_ = getDefaultInstance().getRawText();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRawTextBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.rawText_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getRawText());
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
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getRawText());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Zram parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Zram parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Zram parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Zram parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Zram parseFrom(InputStream input) throws IOException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Zram parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Zram parseDelimitedFrom(InputStream input) throws IOException {
                return (Zram) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Zram parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Zram) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Zram parseFrom(CodedInputStream input) throws IOException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Zram parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Zram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Zram prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Zram, Builder> implements ZramOrBuilder {
                private Builder() {
                    super(Zram.DEFAULT_INSTANCE);
                }

                @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
                public boolean hasRawText() {
                    return ((Zram) this.instance).hasRawText();
                }

                @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
                public String getRawText() {
                    return ((Zram) this.instance).getRawText();
                }

                @Override // android.os.ProcrankProto.Summary.ZramOrBuilder
                public ByteString getRawTextBytes() {
                    return ((Zram) this.instance).getRawTextBytes();
                }

                public Builder setRawText(String value) {
                    copyOnWrite();
                    ((Zram) this.instance).setRawText(value);
                    return this;
                }

                public Builder clearRawText() {
                    copyOnWrite();
                    ((Zram) this.instance).clearRawText();
                    return this;
                }

                public Builder setRawTextBytes(ByteString value) {
                    copyOnWrite();
                    ((Zram) this.instance).setRawTextBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Zram();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Zram other = (Zram) arg1;
                        this.rawText_ = visitor.visitString(hasRawText(), this.rawText_, other.hasRawText(), other.rawText_);
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
                                } else if (tag == 10) {
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.rawText_ = s;
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
                            synchronized (Zram.class) {
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

            public static Zram getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Zram> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Ram extends GeneratedMessageLite<Ram, Builder> implements RamOrBuilder {
            private static final Ram DEFAULT_INSTANCE = new Ram();
            private static volatile Parser<Ram> PARSER = null;
            public static final int RAW_TEXT_FIELD_NUMBER = 1;
            private int bitField0_;
            private String rawText_ = "";

            private Ram() {
            }

            @Override // android.os.ProcrankProto.Summary.RamOrBuilder
            public boolean hasRawText() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.ProcrankProto.Summary.RamOrBuilder
            public String getRawText() {
                return this.rawText_;
            }

            @Override // android.os.ProcrankProto.Summary.RamOrBuilder
            public ByteString getRawTextBytes() {
                return ByteString.copyFromUtf8(this.rawText_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRawText(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.rawText_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearRawText() {
                this.bitField0_ &= -2;
                this.rawText_ = getDefaultInstance().getRawText();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setRawTextBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.rawText_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getRawText());
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
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getRawText());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Ram parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Ram parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Ram parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Ram parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Ram parseFrom(InputStream input) throws IOException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Ram parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Ram parseDelimitedFrom(InputStream input) throws IOException {
                return (Ram) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Ram parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Ram) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Ram parseFrom(CodedInputStream input) throws IOException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Ram parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Ram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Ram prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Ram, Builder> implements RamOrBuilder {
                private Builder() {
                    super(Ram.DEFAULT_INSTANCE);
                }

                @Override // android.os.ProcrankProto.Summary.RamOrBuilder
                public boolean hasRawText() {
                    return ((Ram) this.instance).hasRawText();
                }

                @Override // android.os.ProcrankProto.Summary.RamOrBuilder
                public String getRawText() {
                    return ((Ram) this.instance).getRawText();
                }

                @Override // android.os.ProcrankProto.Summary.RamOrBuilder
                public ByteString getRawTextBytes() {
                    return ((Ram) this.instance).getRawTextBytes();
                }

                public Builder setRawText(String value) {
                    copyOnWrite();
                    ((Ram) this.instance).setRawText(value);
                    return this;
                }

                public Builder clearRawText() {
                    copyOnWrite();
                    ((Ram) this.instance).clearRawText();
                    return this;
                }

                public Builder setRawTextBytes(ByteString value) {
                    copyOnWrite();
                    ((Ram) this.instance).setRawTextBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Ram();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Ram other = (Ram) arg1;
                        this.rawText_ = visitor.visitString(hasRawText(), this.rawText_, other.hasRawText(), other.rawText_);
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
                                } else if (tag == 10) {
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.rawText_ = s;
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
                            synchronized (Ram.class) {
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

            public static Ram getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Ram> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public Process getTotal() {
            Process process = this.total_;
            return process == null ? Process.getDefaultInstance() : process;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(Process value) {
            if (value != null) {
                this.total_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(Process.Builder builderForValue) {
            this.total_ = (Process) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTotal(Process value) {
            Process process = this.total_;
            if (process == null || process == Process.getDefaultInstance()) {
                this.total_ = value;
            } else {
                this.total_ = (Process) ((Process.Builder) Process.newBuilder(this.total_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.total_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public boolean hasZram() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public Zram getZram() {
            Zram zram = this.zram_;
            return zram == null ? Zram.getDefaultInstance() : zram;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setZram(Zram value) {
            if (value != null) {
                this.zram_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setZram(Zram.Builder builderForValue) {
            this.zram_ = (Zram) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeZram(Zram value) {
            Zram zram = this.zram_;
            if (zram == null || zram == Zram.getDefaultInstance()) {
                this.zram_ = value;
            } else {
                this.zram_ = (Zram) ((Zram.Builder) Zram.newBuilder(this.zram_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearZram() {
            this.zram_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public boolean hasRam() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.ProcrankProto.SummaryOrBuilder
        public Ram getRam() {
            Ram ram = this.ram_;
            return ram == null ? Ram.getDefaultInstance() : ram;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRam(Ram value) {
            if (value != null) {
                this.ram_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRam(Ram.Builder builderForValue) {
            this.ram_ = (Ram) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeRam(Ram value) {
            Ram ram = this.ram_;
            if (ram == null || ram == Ram.getDefaultInstance()) {
                this.ram_ = value;
            } else {
                this.ram_ = (Ram) ((Ram.Builder) Ram.newBuilder(this.ram_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRam() {
            this.ram_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getTotal());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getZram());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getRam());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getTotal());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getZram());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getRam());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Summary parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Summary parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Summary parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Summary parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Summary parseFrom(InputStream input) throws IOException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Summary parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Summary parseDelimitedFrom(InputStream input) throws IOException {
            return (Summary) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Summary parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Summary) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Summary parseFrom(CodedInputStream input) throws IOException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Summary parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Summary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Summary prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Summary, Builder> implements SummaryOrBuilder {
            private Builder() {
                super(Summary.DEFAULT_INSTANCE);
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public boolean hasTotal() {
                return ((Summary) this.instance).hasTotal();
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public Process getTotal() {
                return ((Summary) this.instance).getTotal();
            }

            public Builder setTotal(Process value) {
                copyOnWrite();
                ((Summary) this.instance).setTotal((Summary) value);
                return this;
            }

            public Builder setTotal(Process.Builder builderForValue) {
                copyOnWrite();
                ((Summary) this.instance).setTotal((Summary) builderForValue);
                return this;
            }

            public Builder mergeTotal(Process value) {
                copyOnWrite();
                ((Summary) this.instance).mergeTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((Summary) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public boolean hasZram() {
                return ((Summary) this.instance).hasZram();
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public Zram getZram() {
                return ((Summary) this.instance).getZram();
            }

            public Builder setZram(Zram value) {
                copyOnWrite();
                ((Summary) this.instance).setZram((Summary) value);
                return this;
            }

            public Builder setZram(Zram.Builder builderForValue) {
                copyOnWrite();
                ((Summary) this.instance).setZram((Summary) builderForValue);
                return this;
            }

            public Builder mergeZram(Zram value) {
                copyOnWrite();
                ((Summary) this.instance).mergeZram(value);
                return this;
            }

            public Builder clearZram() {
                copyOnWrite();
                ((Summary) this.instance).clearZram();
                return this;
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public boolean hasRam() {
                return ((Summary) this.instance).hasRam();
            }

            @Override // android.os.ProcrankProto.SummaryOrBuilder
            public Ram getRam() {
                return ((Summary) this.instance).getRam();
            }

            public Builder setRam(Ram value) {
                copyOnWrite();
                ((Summary) this.instance).setRam((Summary) value);
                return this;
            }

            public Builder setRam(Ram.Builder builderForValue) {
                copyOnWrite();
                ((Summary) this.instance).setRam((Summary) builderForValue);
                return this;
            }

            public Builder mergeRam(Ram value) {
                copyOnWrite();
                ((Summary) this.instance).mergeRam(value);
                return this;
            }

            public Builder clearRam() {
                copyOnWrite();
                ((Summary) this.instance).clearRam();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Summary();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Summary other = (Summary) arg1;
                    this.total_ = (Process) visitor.visitMessage(this.total_, other.total_);
                    this.zram_ = (Zram) visitor.visitMessage(this.zram_, other.zram_);
                    this.ram_ = (Ram) visitor.visitMessage(this.ram_, other.ram_);
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
                                Process.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (Process.Builder) this.total_.toBuilder();
                                }
                                this.total_ = (Process) input.readMessage(Process.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.total_);
                                    this.total_ = (Process) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                Zram.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (Zram.Builder) this.zram_.toBuilder();
                                }
                                this.zram_ = (Zram) input.readMessage(Zram.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.zram_);
                                    this.zram_ = (Zram) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                Ram.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (Ram.Builder) this.ram_.toBuilder();
                                }
                                this.ram_ = (Ram) input.readMessage(Ram.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.ram_);
                                    this.ram_ = (Ram) subBuilder3.buildPartial();
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
                        synchronized (Summary.class) {
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

        public static Summary getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Summary> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.ProcrankProtoOrBuilder
    public List<Process> getProcessesList() {
        return this.processes_;
    }

    public List<? extends ProcessOrBuilder> getProcessesOrBuilderList() {
        return this.processes_;
    }

    @Override // android.os.ProcrankProtoOrBuilder
    public int getProcessesCount() {
        return this.processes_.size();
    }

    @Override // android.os.ProcrankProtoOrBuilder
    public Process getProcesses(int index) {
        return this.processes_.get(index);
    }

    public ProcessOrBuilder getProcessesOrBuilder(int index) {
        return this.processes_.get(index);
    }

    private void ensureProcessesIsMutable() {
        if (!this.processes_.isModifiable()) {
            this.processes_ = GeneratedMessageLite.mutableCopy(this.processes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcesses(int index, Process value) {
        if (value != null) {
            ensureProcessesIsMutable();
            this.processes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcesses(int index, Process.Builder builderForValue) {
        ensureProcessesIsMutable();
        this.processes_.set(index, (Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcesses(Process value) {
        if (value != null) {
            ensureProcessesIsMutable();
            this.processes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcesses(int index, Process value) {
        if (value != null) {
            ensureProcessesIsMutable();
            this.processes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcesses(Process.Builder builderForValue) {
        ensureProcessesIsMutable();
        this.processes_.add((Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcesses(int index, Process.Builder builderForValue) {
        ensureProcessesIsMutable();
        this.processes_.add(index, (Process) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProcesses(Iterable<? extends Process> values) {
        ensureProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.processes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcesses() {
        this.processes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProcesses(int index) {
        ensureProcessesIsMutable();
        this.processes_.remove(index);
    }

    @Override // android.os.ProcrankProtoOrBuilder
    public boolean hasSummary() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.ProcrankProtoOrBuilder
    public Summary getSummary() {
        Summary summary = this.summary_;
        return summary == null ? Summary.getDefaultInstance() : summary;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummary(Summary value) {
        if (value != null) {
            this.summary_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummary(Summary.Builder builderForValue) {
        this.summary_ = (Summary) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSummary(Summary value) {
        Summary summary = this.summary_;
        if (summary == null || summary == Summary.getDefaultInstance()) {
            this.summary_ = value;
        } else {
            this.summary_ = (Summary) ((Summary.Builder) Summary.newBuilder(this.summary_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSummary() {
        this.summary_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.processes_.size(); i++) {
            output.writeMessage(1, this.processes_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getSummary());
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
        for (int i = 0; i < this.processes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.processes_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getSummary());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcrankProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcrankProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcrankProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcrankProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcrankProto parseFrom(InputStream input) throws IOException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcrankProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcrankProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcrankProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcrankProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcrankProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcrankProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcrankProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcrankProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcrankProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcrankProto, Builder> implements ProcrankProtoOrBuilder {
        private Builder() {
            super(ProcrankProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.ProcrankProtoOrBuilder
        public List<Process> getProcessesList() {
            return Collections.unmodifiableList(((ProcrankProto) this.instance).getProcessesList());
        }

        @Override // android.os.ProcrankProtoOrBuilder
        public int getProcessesCount() {
            return ((ProcrankProto) this.instance).getProcessesCount();
        }

        @Override // android.os.ProcrankProtoOrBuilder
        public Process getProcesses(int index) {
            return ((ProcrankProto) this.instance).getProcesses(index);
        }

        public Builder setProcesses(int index, Process value) {
            copyOnWrite();
            ((ProcrankProto) this.instance).setProcesses((ProcrankProto) index, (int) value);
            return this;
        }

        public Builder setProcesses(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((ProcrankProto) this.instance).setProcesses((ProcrankProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcesses(Process value) {
            copyOnWrite();
            ((ProcrankProto) this.instance).addProcesses((ProcrankProto) value);
            return this;
        }

        public Builder addProcesses(int index, Process value) {
            copyOnWrite();
            ((ProcrankProto) this.instance).addProcesses((ProcrankProto) index, (int) value);
            return this;
        }

        public Builder addProcesses(Process.Builder builderForValue) {
            copyOnWrite();
            ((ProcrankProto) this.instance).addProcesses((ProcrankProto) builderForValue);
            return this;
        }

        public Builder addProcesses(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((ProcrankProto) this.instance).addProcesses((ProcrankProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcesses(Iterable<? extends Process> values) {
            copyOnWrite();
            ((ProcrankProto) this.instance).addAllProcesses(values);
            return this;
        }

        public Builder clearProcesses() {
            copyOnWrite();
            ((ProcrankProto) this.instance).clearProcesses();
            return this;
        }

        public Builder removeProcesses(int index) {
            copyOnWrite();
            ((ProcrankProto) this.instance).removeProcesses(index);
            return this;
        }

        @Override // android.os.ProcrankProtoOrBuilder
        public boolean hasSummary() {
            return ((ProcrankProto) this.instance).hasSummary();
        }

        @Override // android.os.ProcrankProtoOrBuilder
        public Summary getSummary() {
            return ((ProcrankProto) this.instance).getSummary();
        }

        public Builder setSummary(Summary value) {
            copyOnWrite();
            ((ProcrankProto) this.instance).setSummary((ProcrankProto) value);
            return this;
        }

        public Builder setSummary(Summary.Builder builderForValue) {
            copyOnWrite();
            ((ProcrankProto) this.instance).setSummary((ProcrankProto) builderForValue);
            return this;
        }

        public Builder mergeSummary(Summary value) {
            copyOnWrite();
            ((ProcrankProto) this.instance).mergeSummary(value);
            return this;
        }

        public Builder clearSummary() {
            copyOnWrite();
            ((ProcrankProto) this.instance).clearSummary();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcrankProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.processes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcrankProto other = (ProcrankProto) arg1;
                this.processes_ = visitor.visitList(this.processes_, other.processes_);
                this.summary_ = (Summary) visitor.visitMessage(this.summary_, other.summary_);
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
                            if (!this.processes_.isModifiable()) {
                                this.processes_ = GeneratedMessageLite.mutableCopy(this.processes_);
                            }
                            this.processes_.add((Process) input.readMessage(Process.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            Summary.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (Summary.Builder) this.summary_.toBuilder();
                            }
                            this.summary_ = (Summary) input.readMessage(Summary.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.summary_);
                                this.summary_ = (Summary) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (ProcrankProto.class) {
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

    public static ProcrankProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcrankProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
