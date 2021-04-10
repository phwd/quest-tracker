package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ProfilerInfoProto extends GeneratedMessageLite<ProfilerInfoProto, Builder> implements ProfilerInfoProtoOrBuilder {
    public static final int AGENT_FIELD_NUMBER = 6;
    public static final int AUTO_STOP_PROFILER_FIELD_NUMBER = 4;
    private static final ProfilerInfoProto DEFAULT_INSTANCE = new ProfilerInfoProto();
    private static volatile Parser<ProfilerInfoProto> PARSER = null;
    public static final int PROFILE_FD_FIELD_NUMBER = 2;
    public static final int PROFILE_FILE_FIELD_NUMBER = 1;
    public static final int SAMPLING_INTERVAL_FIELD_NUMBER = 3;
    public static final int STREAMING_OUTPUT_FIELD_NUMBER = 5;
    private String agent_ = "";
    private boolean autoStopProfiler_ = false;
    private int bitField0_;
    private int profileFd_ = 0;
    private String profileFile_ = "";
    private int samplingInterval_ = 0;
    private boolean streamingOutput_ = false;

    private ProfilerInfoProto() {
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasProfileFile() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public String getProfileFile() {
        return this.profileFile_;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public ByteString getProfileFileBytes() {
        return ByteString.copyFromUtf8(this.profileFile_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileFile(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.profileFile_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfileFile() {
        this.bitField0_ &= -2;
        this.profileFile_ = getDefaultInstance().getProfileFile();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileFileBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.profileFile_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasProfileFd() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public int getProfileFd() {
        return this.profileFd_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileFd(int value) {
        this.bitField0_ |= 2;
        this.profileFd_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfileFd() {
        this.bitField0_ &= -3;
        this.profileFd_ = 0;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasSamplingInterval() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public int getSamplingInterval() {
        return this.samplingInterval_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSamplingInterval(int value) {
        this.bitField0_ |= 4;
        this.samplingInterval_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSamplingInterval() {
        this.bitField0_ &= -5;
        this.samplingInterval_ = 0;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasAutoStopProfiler() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean getAutoStopProfiler() {
        return this.autoStopProfiler_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAutoStopProfiler(boolean value) {
        this.bitField0_ |= 8;
        this.autoStopProfiler_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAutoStopProfiler() {
        this.bitField0_ &= -9;
        this.autoStopProfiler_ = false;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasStreamingOutput() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean getStreamingOutput() {
        return this.streamingOutput_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStreamingOutput(boolean value) {
        this.bitField0_ |= 16;
        this.streamingOutput_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStreamingOutput() {
        this.bitField0_ &= -17;
        this.streamingOutput_ = false;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public boolean hasAgent() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public String getAgent() {
        return this.agent_;
    }

    @Override // android.app.ProfilerInfoProtoOrBuilder
    public ByteString getAgentBytes() {
        return ByteString.copyFromUtf8(this.agent_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAgent(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.agent_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAgent() {
        this.bitField0_ &= -33;
        this.agent_ = getDefaultInstance().getAgent();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAgentBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.agent_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getProfileFile());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.profileFd_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.samplingInterval_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.autoStopProfiler_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.streamingOutput_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getAgent());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getProfileFile());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.profileFd_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.samplingInterval_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.autoStopProfiler_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.streamingOutput_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getAgent());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProfilerInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProfilerInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProfilerInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProfilerInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProfilerInfoProto parseFrom(InputStream input) throws IOException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProfilerInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProfilerInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProfilerInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProfilerInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProfilerInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProfilerInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProfilerInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProfilerInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProfilerInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProfilerInfoProto, Builder> implements ProfilerInfoProtoOrBuilder {
        private Builder() {
            super(ProfilerInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasProfileFile() {
            return ((ProfilerInfoProto) this.instance).hasProfileFile();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public String getProfileFile() {
            return ((ProfilerInfoProto) this.instance).getProfileFile();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public ByteString getProfileFileBytes() {
            return ((ProfilerInfoProto) this.instance).getProfileFileBytes();
        }

        public Builder setProfileFile(String value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setProfileFile(value);
            return this;
        }

        public Builder clearProfileFile() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearProfileFile();
            return this;
        }

        public Builder setProfileFileBytes(ByteString value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setProfileFileBytes(value);
            return this;
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasProfileFd() {
            return ((ProfilerInfoProto) this.instance).hasProfileFd();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public int getProfileFd() {
            return ((ProfilerInfoProto) this.instance).getProfileFd();
        }

        public Builder setProfileFd(int value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setProfileFd(value);
            return this;
        }

        public Builder clearProfileFd() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearProfileFd();
            return this;
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasSamplingInterval() {
            return ((ProfilerInfoProto) this.instance).hasSamplingInterval();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public int getSamplingInterval() {
            return ((ProfilerInfoProto) this.instance).getSamplingInterval();
        }

        public Builder setSamplingInterval(int value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setSamplingInterval(value);
            return this;
        }

        public Builder clearSamplingInterval() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearSamplingInterval();
            return this;
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasAutoStopProfiler() {
            return ((ProfilerInfoProto) this.instance).hasAutoStopProfiler();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean getAutoStopProfiler() {
            return ((ProfilerInfoProto) this.instance).getAutoStopProfiler();
        }

        public Builder setAutoStopProfiler(boolean value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setAutoStopProfiler(value);
            return this;
        }

        public Builder clearAutoStopProfiler() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearAutoStopProfiler();
            return this;
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasStreamingOutput() {
            return ((ProfilerInfoProto) this.instance).hasStreamingOutput();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean getStreamingOutput() {
            return ((ProfilerInfoProto) this.instance).getStreamingOutput();
        }

        public Builder setStreamingOutput(boolean value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setStreamingOutput(value);
            return this;
        }

        public Builder clearStreamingOutput() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearStreamingOutput();
            return this;
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public boolean hasAgent() {
            return ((ProfilerInfoProto) this.instance).hasAgent();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public String getAgent() {
            return ((ProfilerInfoProto) this.instance).getAgent();
        }

        @Override // android.app.ProfilerInfoProtoOrBuilder
        public ByteString getAgentBytes() {
            return ((ProfilerInfoProto) this.instance).getAgentBytes();
        }

        public Builder setAgent(String value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setAgent(value);
            return this;
        }

        public Builder clearAgent() {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).clearAgent();
            return this;
        }

        public Builder setAgentBytes(ByteString value) {
            copyOnWrite();
            ((ProfilerInfoProto) this.instance).setAgentBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProfilerInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProfilerInfoProto other = (ProfilerInfoProto) arg1;
                this.profileFile_ = visitor.visitString(hasProfileFile(), this.profileFile_, other.hasProfileFile(), other.profileFile_);
                this.profileFd_ = visitor.visitInt(hasProfileFd(), this.profileFd_, other.hasProfileFd(), other.profileFd_);
                this.samplingInterval_ = visitor.visitInt(hasSamplingInterval(), this.samplingInterval_, other.hasSamplingInterval(), other.samplingInterval_);
                this.autoStopProfiler_ = visitor.visitBoolean(hasAutoStopProfiler(), this.autoStopProfiler_, other.hasAutoStopProfiler(), other.autoStopProfiler_);
                this.streamingOutput_ = visitor.visitBoolean(hasStreamingOutput(), this.streamingOutput_, other.hasStreamingOutput(), other.streamingOutput_);
                this.agent_ = visitor.visitString(hasAgent(), this.agent_, other.hasAgent(), other.agent_);
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
                            this.profileFile_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.profileFd_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.samplingInterval_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.autoStopProfiler_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.streamingOutput_ = input.readBool();
                        } else if (tag == 50) {
                            String s2 = input.readString();
                            this.bitField0_ = 32 | this.bitField0_;
                            this.agent_ = s2;
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
                    synchronized (ProfilerInfoProto.class) {
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

    public static ProfilerInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProfilerInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
