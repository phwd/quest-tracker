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

public final class PsProto extends GeneratedMessageLite<PsProto, Builder> implements PsProtoOrBuilder {
    private static final PsProto DEFAULT_INSTANCE = new PsProto();
    private static volatile Parser<PsProto> PARSER = null;
    public static final int PROCESSES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Process> processes_ = emptyProtobufList();

    public interface ProcessOrBuilder extends MessageLiteOrBuilder {
        String getAddr();

        ByteString getAddrBytes();

        String getCmd();

        ByteString getCmdBytes();

        String getLabel();

        ByteString getLabelBytes();

        int getNi();

        Process.Policy getPcy();

        int getPid();

        int getPpid();

        int getPri();

        int getRss();

        String getRtprio();

        ByteString getRtprioBytes();

        Process.ProcessStateCode getS();

        Process.SchedulingPolicy getSch();

        int getTid();

        String getTime();

        ByteString getTimeBytes();

        String getUser();

        ByteString getUserBytes();

        int getVsz();

        String getWchan();

        ByteString getWchanBytes();

        boolean hasAddr();

        boolean hasCmd();

        boolean hasLabel();

        boolean hasNi();

        boolean hasPcy();

        boolean hasPid();

        boolean hasPpid();

        boolean hasPri();

        boolean hasRss();

        boolean hasRtprio();

        boolean hasS();

        boolean hasSch();

        boolean hasTid();

        boolean hasTime();

        boolean hasUser();

        boolean hasVsz();

        boolean hasWchan();
    }

    private PsProto() {
    }

    public static final class Process extends GeneratedMessageLite<Process, Builder> implements ProcessOrBuilder {
        public static final int ADDR_FIELD_NUMBER = 9;
        public static final int CMD_FIELD_NUMBER = 17;
        private static final Process DEFAULT_INSTANCE = new Process();
        public static final int LABEL_FIELD_NUMBER = 1;
        public static final int NI_FIELD_NUMBER = 12;
        private static volatile Parser<Process> PARSER = null;
        public static final int PCY_FIELD_NUMBER = 15;
        public static final int PID_FIELD_NUMBER = 3;
        public static final int PPID_FIELD_NUMBER = 5;
        public static final int PRI_FIELD_NUMBER = 11;
        public static final int RSS_FIELD_NUMBER = 7;
        public static final int RTPRIO_FIELD_NUMBER = 13;
        public static final int SCH_FIELD_NUMBER = 14;
        public static final int S_FIELD_NUMBER = 10;
        public static final int TID_FIELD_NUMBER = 4;
        public static final int TIME_FIELD_NUMBER = 16;
        public static final int USER_FIELD_NUMBER = 2;
        public static final int VSZ_FIELD_NUMBER = 6;
        public static final int WCHAN_FIELD_NUMBER = 8;
        private String addr_ = "";
        private int bitField0_;
        private String cmd_ = "";
        private String label_ = "";
        private int ni_ = 0;
        private int pcy_ = 0;
        private int pid_ = 0;
        private int ppid_ = 0;
        private int pri_ = 0;
        private int rss_ = 0;
        private String rtprio_ = "";
        private int s_ = 0;
        private int sch_ = 0;
        private int tid_ = 0;
        private String time_ = "";
        private String user_ = "";
        private int vsz_ = 0;
        private String wchan_ = "";

        private Process() {
        }

        public enum ProcessStateCode implements Internal.EnumLite {
            STATE_UNKNOWN(0),
            STATE_D(1),
            STATE_R(2),
            STATE_S(3),
            STATE_T(4),
            STATE_TRACING(5),
            STATE_X(6),
            STATE_Z(7);
            
            public static final int STATE_D_VALUE = 1;
            public static final int STATE_R_VALUE = 2;
            public static final int STATE_S_VALUE = 3;
            public static final int STATE_TRACING_VALUE = 5;
            public static final int STATE_T_VALUE = 4;
            public static final int STATE_UNKNOWN_VALUE = 0;
            public static final int STATE_X_VALUE = 6;
            public static final int STATE_Z_VALUE = 7;
            private static final Internal.EnumLiteMap<ProcessStateCode> internalValueMap = new Internal.EnumLiteMap<ProcessStateCode>() {
                /* class android.os.PsProto.Process.ProcessStateCode.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ProcessStateCode findValueByNumber(int number) {
                    return ProcessStateCode.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ProcessStateCode valueOf(int value2) {
                return forNumber(value2);
            }

            public static ProcessStateCode forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return STATE_UNKNOWN;
                    case 1:
                        return STATE_D;
                    case 2:
                        return STATE_R;
                    case 3:
                        return STATE_S;
                    case 4:
                        return STATE_T;
                    case 5:
                        return STATE_TRACING;
                    case 6:
                        return STATE_X;
                    case 7:
                        return STATE_Z;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<ProcessStateCode> internalGetValueMap() {
                return internalValueMap;
            }

            private ProcessStateCode(int value2) {
                this.value = value2;
            }
        }

        public enum SchedulingPolicy implements Internal.EnumLite {
            SCH_OTHER(0),
            SCH_FIFO(1),
            SCH_RR(2),
            SCH_BATCH(3),
            SCH_ISO(4),
            SCH_IDLE(5);
            
            public static final int SCH_BATCH_VALUE = 3;
            public static final int SCH_FIFO_VALUE = 1;
            public static final int SCH_IDLE_VALUE = 5;
            public static final int SCH_ISO_VALUE = 4;
            public static final SchedulingPolicy SCH_NORMAL = SCH_OTHER;
            public static final int SCH_NORMAL_VALUE = 0;
            public static final int SCH_OTHER_VALUE = 0;
            public static final int SCH_RR_VALUE = 2;
            private static final Internal.EnumLiteMap<SchedulingPolicy> internalValueMap = new Internal.EnumLiteMap<SchedulingPolicy>() {
                /* class android.os.PsProto.Process.SchedulingPolicy.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public SchedulingPolicy findValueByNumber(int number) {
                    return SchedulingPolicy.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static SchedulingPolicy valueOf(int value2) {
                return forNumber(value2);
            }

            public static SchedulingPolicy forNumber(int value2) {
                if (value2 == 0) {
                    return SCH_OTHER;
                }
                if (value2 == 1) {
                    return SCH_FIFO;
                }
                if (value2 == 2) {
                    return SCH_RR;
                }
                if (value2 == 3) {
                    return SCH_BATCH;
                }
                if (value2 == 4) {
                    return SCH_ISO;
                }
                if (value2 != 5) {
                    return null;
                }
                return SCH_IDLE;
            }

            public static Internal.EnumLiteMap<SchedulingPolicy> internalGetValueMap() {
                return internalValueMap;
            }

            private SchedulingPolicy(int value2) {
                this.value = value2;
            }
        }

        public enum Policy implements Internal.EnumLite {
            POLICY_UNKNOWN(0),
            POLICY_FG(1),
            POLICY_BG(2),
            POLICY_TA(3);
            
            public static final int POLICY_BG_VALUE = 2;
            public static final int POLICY_FG_VALUE = 1;
            public static final int POLICY_TA_VALUE = 3;
            public static final int POLICY_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<Policy> internalValueMap = new Internal.EnumLiteMap<Policy>() {
                /* class android.os.PsProto.Process.Policy.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Policy findValueByNumber(int number) {
                    return Policy.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Policy valueOf(int value2) {
                return forNumber(value2);
            }

            public static Policy forNumber(int value2) {
                if (value2 == 0) {
                    return POLICY_UNKNOWN;
                }
                if (value2 == 1) {
                    return POLICY_FG;
                }
                if (value2 == 2) {
                    return POLICY_BG;
                }
                if (value2 != 3) {
                    return null;
                }
                return POLICY_TA;
            }

            public static Internal.EnumLiteMap<Policy> internalGetValueMap() {
                return internalValueMap;
            }

            private Policy(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasLabel() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getLabel() {
            return this.label_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getLabelBytes() {
            return ByteString.copyFromUtf8(this.label_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabel(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.label_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLabel() {
            this.bitField0_ &= -2;
            this.label_ = getDefaultInstance().getLabel();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabelBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.label_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasUser() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getUser() {
            return this.user_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getUserBytes() {
            return ByteString.copyFromUtf8(this.user_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUser(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.user_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUser() {
            this.bitField0_ &= -3;
            this.user_ = getDefaultInstance().getUser();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.user_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasPid() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getPid() {
            return this.pid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPid(int value) {
            this.bitField0_ |= 4;
            this.pid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPid() {
            this.bitField0_ &= -5;
            this.pid_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasTid() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getTid() {
            return this.tid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTid(int value) {
            this.bitField0_ |= 8;
            this.tid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTid() {
            this.bitField0_ &= -9;
            this.tid_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasPpid() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getPpid() {
            return this.ppid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPpid(int value) {
            this.bitField0_ |= 16;
            this.ppid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPpid() {
            this.bitField0_ &= -17;
            this.ppid_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasVsz() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getVsz() {
            return this.vsz_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVsz(int value) {
            this.bitField0_ |= 32;
            this.vsz_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVsz() {
            this.bitField0_ &= -33;
            this.vsz_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasRss() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getRss() {
            return this.rss_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRss(int value) {
            this.bitField0_ |= 64;
            this.rss_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRss() {
            this.bitField0_ &= -65;
            this.rss_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasWchan() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getWchan() {
            return this.wchan_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getWchanBytes() {
            return ByteString.copyFromUtf8(this.wchan_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWchan(String value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.wchan_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWchan() {
            this.bitField0_ &= -129;
            this.wchan_ = getDefaultInstance().getWchan();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWchanBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.wchan_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasAddr() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getAddr() {
            return this.addr_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getAddrBytes() {
            return ByteString.copyFromUtf8(this.addr_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAddr(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.addr_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAddr() {
            this.bitField0_ &= -257;
            this.addr_ = getDefaultInstance().getAddr();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAddrBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.addr_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasS() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ProcessStateCode getS() {
            ProcessStateCode result = ProcessStateCode.forNumber(this.s_);
            return result == null ? ProcessStateCode.STATE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setS(ProcessStateCode value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.s_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearS() {
            this.bitField0_ &= -513;
            this.s_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasPri() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getPri() {
            return this.pri_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPri(int value) {
            this.bitField0_ |= 1024;
            this.pri_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPri() {
            this.bitField0_ &= -1025;
            this.pri_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasNi() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public int getNi() {
            return this.ni_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNi(int value) {
            this.bitField0_ |= 2048;
            this.ni_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNi() {
            this.bitField0_ &= -2049;
            this.ni_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasRtprio() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getRtprio() {
            return this.rtprio_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getRtprioBytes() {
            return ByteString.copyFromUtf8(this.rtprio_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRtprio(String value) {
            if (value != null) {
                this.bitField0_ |= 4096;
                this.rtprio_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRtprio() {
            this.bitField0_ &= -4097;
            this.rtprio_ = getDefaultInstance().getRtprio();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRtprioBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4096;
                this.rtprio_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasSch() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public SchedulingPolicy getSch() {
            SchedulingPolicy result = SchedulingPolicy.forNumber(this.sch_);
            return result == null ? SchedulingPolicy.SCH_OTHER : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSch(SchedulingPolicy value) {
            if (value != null) {
                this.bitField0_ |= 8192;
                this.sch_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSch() {
            this.bitField0_ &= -8193;
            this.sch_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasPcy() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public Policy getPcy() {
            Policy result = Policy.forNumber(this.pcy_);
            return result == null ? Policy.POLICY_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPcy(Policy value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.pcy_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPcy() {
            this.bitField0_ &= -16385;
            this.pcy_ = 0;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasTime() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getTime() {
            return this.time_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getTimeBytes() {
            return ByteString.copyFromUtf8(this.time_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTime(String value) {
            if (value != null) {
                this.bitField0_ |= 32768;
                this.time_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTime() {
            this.bitField0_ &= -32769;
            this.time_ = getDefaultInstance().getTime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 32768;
                this.time_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public boolean hasCmd() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public String getCmd() {
            return this.cmd_;
        }

        @Override // android.os.PsProto.ProcessOrBuilder
        public ByteString getCmdBytes() {
            return ByteString.copyFromUtf8(this.cmd_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmd(String value) {
            if (value != null) {
                this.bitField0_ |= 65536;
                this.cmd_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCmd() {
            this.bitField0_ &= -65537;
            this.cmd_ = getDefaultInstance().getCmd();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmdBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 65536;
                this.cmd_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getLabel());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getUser());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.pid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.tid_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.ppid_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.vsz_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.rss_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeString(8, getWchan());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getAddr());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeEnum(10, this.s_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.pri_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeSInt32(12, this.ni_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeString(13, getRtprio());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeEnum(14, this.sch_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeEnum(15, this.pcy_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeString(16, getTime());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeString(17, getCmd());
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getLabel());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getUser());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.pid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.tid_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.ppid_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.vsz_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.rss_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeStringSize(8, getWchan());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getAddr());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeEnumSize(10, this.s_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.pri_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeSInt32Size(12, this.ni_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeStringSize(13, getRtprio());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeEnumSize(14, this.sch_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeEnumSize(15, this.pcy_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeStringSize(16, getTime());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeStringSize(17, getCmd());
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

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasLabel() {
                return ((Process) this.instance).hasLabel();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getLabel() {
                return ((Process) this.instance).getLabel();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getLabelBytes() {
                return ((Process) this.instance).getLabelBytes();
            }

            public Builder setLabel(String value) {
                copyOnWrite();
                ((Process) this.instance).setLabel(value);
                return this;
            }

            public Builder clearLabel() {
                copyOnWrite();
                ((Process) this.instance).clearLabel();
                return this;
            }

            public Builder setLabelBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setLabelBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasUser() {
                return ((Process) this.instance).hasUser();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getUser() {
                return ((Process) this.instance).getUser();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getUserBytes() {
                return ((Process) this.instance).getUserBytes();
            }

            public Builder setUser(String value) {
                copyOnWrite();
                ((Process) this.instance).setUser(value);
                return this;
            }

            public Builder clearUser() {
                copyOnWrite();
                ((Process) this.instance).clearUser();
                return this;
            }

            public Builder setUserBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setUserBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasPid() {
                return ((Process) this.instance).hasPid();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
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

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasTid() {
                return ((Process) this.instance).hasTid();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getTid() {
                return ((Process) this.instance).getTid();
            }

            public Builder setTid(int value) {
                copyOnWrite();
                ((Process) this.instance).setTid(value);
                return this;
            }

            public Builder clearTid() {
                copyOnWrite();
                ((Process) this.instance).clearTid();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasPpid() {
                return ((Process) this.instance).hasPpid();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getPpid() {
                return ((Process) this.instance).getPpid();
            }

            public Builder setPpid(int value) {
                copyOnWrite();
                ((Process) this.instance).setPpid(value);
                return this;
            }

            public Builder clearPpid() {
                copyOnWrite();
                ((Process) this.instance).clearPpid();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasVsz() {
                return ((Process) this.instance).hasVsz();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getVsz() {
                return ((Process) this.instance).getVsz();
            }

            public Builder setVsz(int value) {
                copyOnWrite();
                ((Process) this.instance).setVsz(value);
                return this;
            }

            public Builder clearVsz() {
                copyOnWrite();
                ((Process) this.instance).clearVsz();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasRss() {
                return ((Process) this.instance).hasRss();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getRss() {
                return ((Process) this.instance).getRss();
            }

            public Builder setRss(int value) {
                copyOnWrite();
                ((Process) this.instance).setRss(value);
                return this;
            }

            public Builder clearRss() {
                copyOnWrite();
                ((Process) this.instance).clearRss();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasWchan() {
                return ((Process) this.instance).hasWchan();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getWchan() {
                return ((Process) this.instance).getWchan();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getWchanBytes() {
                return ((Process) this.instance).getWchanBytes();
            }

            public Builder setWchan(String value) {
                copyOnWrite();
                ((Process) this.instance).setWchan(value);
                return this;
            }

            public Builder clearWchan() {
                copyOnWrite();
                ((Process) this.instance).clearWchan();
                return this;
            }

            public Builder setWchanBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setWchanBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasAddr() {
                return ((Process) this.instance).hasAddr();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getAddr() {
                return ((Process) this.instance).getAddr();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getAddrBytes() {
                return ((Process) this.instance).getAddrBytes();
            }

            public Builder setAddr(String value) {
                copyOnWrite();
                ((Process) this.instance).setAddr(value);
                return this;
            }

            public Builder clearAddr() {
                copyOnWrite();
                ((Process) this.instance).clearAddr();
                return this;
            }

            public Builder setAddrBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setAddrBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasS() {
                return ((Process) this.instance).hasS();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ProcessStateCode getS() {
                return ((Process) this.instance).getS();
            }

            public Builder setS(ProcessStateCode value) {
                copyOnWrite();
                ((Process) this.instance).setS(value);
                return this;
            }

            public Builder clearS() {
                copyOnWrite();
                ((Process) this.instance).clearS();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasPri() {
                return ((Process) this.instance).hasPri();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getPri() {
                return ((Process) this.instance).getPri();
            }

            public Builder setPri(int value) {
                copyOnWrite();
                ((Process) this.instance).setPri(value);
                return this;
            }

            public Builder clearPri() {
                copyOnWrite();
                ((Process) this.instance).clearPri();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasNi() {
                return ((Process) this.instance).hasNi();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public int getNi() {
                return ((Process) this.instance).getNi();
            }

            public Builder setNi(int value) {
                copyOnWrite();
                ((Process) this.instance).setNi(value);
                return this;
            }

            public Builder clearNi() {
                copyOnWrite();
                ((Process) this.instance).clearNi();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasRtprio() {
                return ((Process) this.instance).hasRtprio();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getRtprio() {
                return ((Process) this.instance).getRtprio();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getRtprioBytes() {
                return ((Process) this.instance).getRtprioBytes();
            }

            public Builder setRtprio(String value) {
                copyOnWrite();
                ((Process) this.instance).setRtprio(value);
                return this;
            }

            public Builder clearRtprio() {
                copyOnWrite();
                ((Process) this.instance).clearRtprio();
                return this;
            }

            public Builder setRtprioBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setRtprioBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasSch() {
                return ((Process) this.instance).hasSch();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public SchedulingPolicy getSch() {
                return ((Process) this.instance).getSch();
            }

            public Builder setSch(SchedulingPolicy value) {
                copyOnWrite();
                ((Process) this.instance).setSch(value);
                return this;
            }

            public Builder clearSch() {
                copyOnWrite();
                ((Process) this.instance).clearSch();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasPcy() {
                return ((Process) this.instance).hasPcy();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public Policy getPcy() {
                return ((Process) this.instance).getPcy();
            }

            public Builder setPcy(Policy value) {
                copyOnWrite();
                ((Process) this.instance).setPcy(value);
                return this;
            }

            public Builder clearPcy() {
                copyOnWrite();
                ((Process) this.instance).clearPcy();
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasTime() {
                return ((Process) this.instance).hasTime();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getTime() {
                return ((Process) this.instance).getTime();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getTimeBytes() {
                return ((Process) this.instance).getTimeBytes();
            }

            public Builder setTime(String value) {
                copyOnWrite();
                ((Process) this.instance).setTime(value);
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((Process) this.instance).clearTime();
                return this;
            }

            public Builder setTimeBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setTimeBytes(value);
                return this;
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public boolean hasCmd() {
                return ((Process) this.instance).hasCmd();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public String getCmd() {
                return ((Process) this.instance).getCmd();
            }

            @Override // android.os.PsProto.ProcessOrBuilder
            public ByteString getCmdBytes() {
                return ((Process) this.instance).getCmdBytes();
            }

            public Builder setCmd(String value) {
                copyOnWrite();
                ((Process) this.instance).setCmd(value);
                return this;
            }

            public Builder clearCmd() {
                copyOnWrite();
                ((Process) this.instance).clearCmd();
                return this;
            }

            public Builder setCmdBytes(ByteString value) {
                copyOnWrite();
                ((Process) this.instance).setCmdBytes(value);
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
                    this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                    this.user_ = visitor.visitString(hasUser(), this.user_, other.hasUser(), other.user_);
                    this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                    this.tid_ = visitor.visitInt(hasTid(), this.tid_, other.hasTid(), other.tid_);
                    this.ppid_ = visitor.visitInt(hasPpid(), this.ppid_, other.hasPpid(), other.ppid_);
                    this.vsz_ = visitor.visitInt(hasVsz(), this.vsz_, other.hasVsz(), other.vsz_);
                    this.rss_ = visitor.visitInt(hasRss(), this.rss_, other.hasRss(), other.rss_);
                    this.wchan_ = visitor.visitString(hasWchan(), this.wchan_, other.hasWchan(), other.wchan_);
                    this.addr_ = visitor.visitString(hasAddr(), this.addr_, other.hasAddr(), other.addr_);
                    this.s_ = visitor.visitInt(hasS(), this.s_, other.hasS(), other.s_);
                    this.pri_ = visitor.visitInt(hasPri(), this.pri_, other.hasPri(), other.pri_);
                    this.ni_ = visitor.visitInt(hasNi(), this.ni_, other.hasNi(), other.ni_);
                    this.rtprio_ = visitor.visitString(hasRtprio(), this.rtprio_, other.hasRtprio(), other.rtprio_);
                    this.sch_ = visitor.visitInt(hasSch(), this.sch_, other.hasSch(), other.sch_);
                    this.pcy_ = visitor.visitInt(hasPcy(), this.pcy_, other.hasPcy(), other.pcy_);
                    this.time_ = visitor.visitString(hasTime(), this.time_, other.hasTime(), other.time_);
                    this.cmd_ = visitor.visitString(hasCmd(), this.cmd_, other.hasCmd(), other.cmd_);
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
                                case 10:
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.label_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.user_ = s2;
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.pid_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.tid_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.ppid_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.vsz_ = input.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.rss_ = input.readInt32();
                                    break;
                                case 66:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 128;
                                    this.wchan_ = s3;
                                    break;
                                case 74:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.addr_ = s4;
                                    break;
                                case 80:
                                    int rawValue = input.readEnum();
                                    if (ProcessStateCode.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 512;
                                        this.s_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(10, rawValue);
                                        break;
                                    }
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.pri_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.ni_ = input.readSInt32();
                                    break;
                                case 106:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 4096;
                                    this.rtprio_ = s5;
                                    break;
                                case 112:
                                    int rawValue2 = input.readEnum();
                                    if (SchedulingPolicy.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 8192;
                                        this.sch_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(14, rawValue2);
                                        break;
                                    }
                                case 120:
                                    int rawValue3 = input.readEnum();
                                    if (Policy.forNumber(rawValue3) != null) {
                                        this.bitField0_ |= 16384;
                                        this.pcy_ = rawValue3;
                                        break;
                                    } else {
                                        super.mergeVarintField(15, rawValue3);
                                        break;
                                    }
                                case 130:
                                    String s6 = input.readString();
                                    this.bitField0_ |= 32768;
                                    this.time_ = s6;
                                    break;
                                case 138:
                                    String s7 = input.readString();
                                    this.bitField0_ |= 65536;
                                    this.cmd_ = s7;
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

    @Override // android.os.PsProtoOrBuilder
    public List<Process> getProcessesList() {
        return this.processes_;
    }

    public List<? extends ProcessOrBuilder> getProcessesOrBuilderList() {
        return this.processes_;
    }

    @Override // android.os.PsProtoOrBuilder
    public int getProcessesCount() {
        return this.processes_.size();
    }

    @Override // android.os.PsProtoOrBuilder
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

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.processes_.size(); i++) {
            output.writeMessage(1, this.processes_.get(i));
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
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PsProto parseFrom(InputStream input) throws IOException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PsProto parseFrom(CodedInputStream input) throws IOException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PsProto, Builder> implements PsProtoOrBuilder {
        private Builder() {
            super(PsProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.PsProtoOrBuilder
        public List<Process> getProcessesList() {
            return Collections.unmodifiableList(((PsProto) this.instance).getProcessesList());
        }

        @Override // android.os.PsProtoOrBuilder
        public int getProcessesCount() {
            return ((PsProto) this.instance).getProcessesCount();
        }

        @Override // android.os.PsProtoOrBuilder
        public Process getProcesses(int index) {
            return ((PsProto) this.instance).getProcesses(index);
        }

        public Builder setProcesses(int index, Process value) {
            copyOnWrite();
            ((PsProto) this.instance).setProcesses((PsProto) index, (int) value);
            return this;
        }

        public Builder setProcesses(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((PsProto) this.instance).setProcesses((PsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcesses(Process value) {
            copyOnWrite();
            ((PsProto) this.instance).addProcesses((PsProto) value);
            return this;
        }

        public Builder addProcesses(int index, Process value) {
            copyOnWrite();
            ((PsProto) this.instance).addProcesses((PsProto) index, (int) value);
            return this;
        }

        public Builder addProcesses(Process.Builder builderForValue) {
            copyOnWrite();
            ((PsProto) this.instance).addProcesses((PsProto) builderForValue);
            return this;
        }

        public Builder addProcesses(int index, Process.Builder builderForValue) {
            copyOnWrite();
            ((PsProto) this.instance).addProcesses((PsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcesses(Iterable<? extends Process> values) {
            copyOnWrite();
            ((PsProto) this.instance).addAllProcesses(values);
            return this;
        }

        public Builder clearProcesses() {
            copyOnWrite();
            ((PsProto) this.instance).clearProcesses();
            return this;
        }

        public Builder removeProcesses(int index) {
            copyOnWrite();
            ((PsProto) this.instance).removeProcesses(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.processes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.processes_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.processes_, ((PsProto) arg1).processes_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                    synchronized (PsProto.class) {
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

    public static PsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
