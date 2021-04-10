package android.stats.mediametrics;

import com.android.os.AtomsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class Mediametrics {

    public interface AudioPolicyDataOrBuilder extends MessageLiteOrBuilder {
        String getActiveDevice();

        ByteString getActiveDeviceBytes();

        String getActivePackage();

        ByteString getActivePackageBytes();

        int getActiveSession();

        String getActiveSource();

        ByteString getActiveSourceBytes();

        String getRequestDevice();

        ByteString getRequestDeviceBytes();

        String getRequestPackage();

        ByteString getRequestPackageBytes();

        int getRequestSession();

        String getRequestSource();

        ByteString getRequestSourceBytes();

        int getStatus();

        boolean hasActiveDevice();

        boolean hasActivePackage();

        boolean hasActiveSession();

        boolean hasActiveSource();

        boolean hasRequestDevice();

        boolean hasRequestPackage();

        boolean hasRequestSession();

        boolean hasRequestSource();

        boolean hasStatus();
    }

    public interface AudioRecordDataOrBuilder extends MessageLiteOrBuilder {
        String getAttributes();

        ByteString getAttributesBytes();

        long getChannelMask();

        int getChannels();

        int getCount();

        long getCreatedMillis();

        long getDurationMillis();

        String getEncoding();

        ByteString getEncodingBytes();

        int getErrorCode();

        String getErrorFunction();

        ByteString getErrorFunctionBytes();

        int getFrameCount();

        int getLatency();

        int getPortId();

        int getSamplerate();

        String getSource();

        ByteString getSourceBytes();

        long getStartCount();

        boolean hasAttributes();

        boolean hasChannelMask();

        boolean hasChannels();

        boolean hasCount();

        boolean hasCreatedMillis();

        boolean hasDurationMillis();

        boolean hasEncoding();

        boolean hasErrorCode();

        boolean hasErrorFunction();

        boolean hasFrameCount();

        boolean hasLatency();

        boolean hasPortId();

        boolean hasSamplerate();

        boolean hasSource();

        boolean hasStartCount();
    }

    public interface AudioThreadDataOrBuilder extends MessageLiteOrBuilder {
        long getActiveMillis();

        long getChannelMask();

        long getDurationMillis();

        String getEncoding();

        ByteString getEncodingBytes();

        int getFrameCount();

        int getFramecount();

        int getId();

        String getInputDevice();

        ByteString getInputDeviceBytes();

        double getIoJitterMeanMillis();

        double getIoJitterStddevMillis();

        double getLatencyMeanMillis();

        String getLatencyMillisHist();

        ByteString getLatencyMillisHistBytes();

        double getLatencyStddevMillis();

        String getOutputDevice();

        ByteString getOutputDeviceBytes();

        long getOverruns();

        int getPortId();

        double getProcessTimeMeanMillis();

        double getProcessTimeStddevMillis();

        int getSampleRate();

        int getSamplerate();

        double getTimestampJitterMeanMillis();

        double getTimestampJitterStddevMillis();

        String getType();

        ByteString getTypeBytes();

        long getUnderruns();

        String getWarmupMillisHist();

        ByteString getWarmupMillisHistBytes();

        String getWorkMillisHist();

        ByteString getWorkMillisHistBytes();

        boolean hasActiveMillis();

        boolean hasChannelMask();

        boolean hasDurationMillis();

        boolean hasEncoding();

        boolean hasFrameCount();

        boolean hasFramecount();

        boolean hasId();

        boolean hasInputDevice();

        boolean hasIoJitterMeanMillis();

        boolean hasIoJitterStddevMillis();

        boolean hasLatencyMeanMillis();

        boolean hasLatencyMillisHist();

        boolean hasLatencyStddevMillis();

        boolean hasOutputDevice();

        boolean hasOverruns();

        boolean hasPortId();

        boolean hasProcessTimeMeanMillis();

        boolean hasProcessTimeStddevMillis();

        boolean hasSampleRate();

        boolean hasSamplerate();

        boolean hasTimestampJitterMeanMillis();

        boolean hasTimestampJitterStddevMillis();

        boolean hasType();

        boolean hasUnderruns();

        boolean hasWarmupMillisHist();

        boolean hasWorkMillisHist();
    }

    public interface AudioTrackDataOrBuilder extends MessageLiteOrBuilder {
        String getAttributes();

        ByteString getAttributesBytes();

        long getChannelMask();

        String getContentType();

        ByteString getContentTypeBytes();

        String getEncoding();

        ByteString getEncodingBytes();

        int getFrameCount();

        int getPortId();

        int getSampleRate();

        int getStartupGlitch();

        String getStreamType();

        ByteString getStreamTypeBytes();

        String getTrackUsage();

        ByteString getTrackUsageBytes();

        int getUnderrunFrames();

        boolean hasAttributes();

        boolean hasChannelMask();

        boolean hasContentType();

        boolean hasEncoding();

        boolean hasFrameCount();

        boolean hasPortId();

        boolean hasSampleRate();

        boolean hasStartupGlitch();

        boolean hasStreamType();

        boolean hasTrackUsage();

        boolean hasUnderrunFrames();
    }

    public interface CodecDataOrBuilder extends MessageLiteOrBuilder {
        String getCodec();

        ByteString getCodecBytes();

        int getCrypto();

        int getEncoder();

        int getErrorCode();

        String getErrorState();

        ByteString getErrorStateBytes();

        int getHeight();

        long getLatencyAvg();

        long getLatencyCount();

        long getLatencyMax();

        long getLatencyMin();

        long getLatencyUnknown();

        int getLevel();

        int getMaxHeight();

        int getMaxWidth();

        String getMime();

        ByteString getMimeBytes();

        String getMode();

        ByteString getModeBytes();

        int getProfile();

        int getRotation();

        int getSecure();

        int getWidth();

        boolean hasCodec();

        boolean hasCrypto();

        boolean hasEncoder();

        boolean hasErrorCode();

        boolean hasErrorState();

        boolean hasHeight();

        boolean hasLatencyAvg();

        boolean hasLatencyCount();

        boolean hasLatencyMax();

        boolean hasLatencyMin();

        boolean hasLatencyUnknown();

        boolean hasLevel();

        boolean hasMaxHeight();

        boolean hasMaxWidth();

        boolean hasMime();

        boolean hasMode();

        boolean hasProfile();

        boolean hasRotation();

        boolean hasSecure();

        boolean hasWidth();
    }

    public interface ExtractorDataOrBuilder extends MessageLiteOrBuilder {
        String getFormat();

        ByteString getFormatBytes();

        String getMime();

        ByteString getMimeBytes();

        int getTracks();

        boolean hasFormat();

        boolean hasMime();

        boolean hasTracks();
    }

    public interface NuPlayerDataOrBuilder extends MessageLiteOrBuilder {
        String getAudioCodec();

        ByteString getAudioCodecBytes();

        String getAudioMime();

        ByteString getAudioMimeBytes();

        String getDataSourceType();

        ByteString getDataSourceTypeBytes();

        long getDurationMillis();

        int getError();

        int getErrorCode();

        String getErrorState();

        ByteString getErrorStateBytes();

        double getFramerate();

        long getFrames();

        long getFramesDropped();

        long getFramesDroppedStartup();

        int getHeight();

        long getPlayingMillis();

        int getRebufferAtExit();

        long getRebufferingMillis();

        int getRebuffers();

        String getVideoCodec();

        ByteString getVideoCodecBytes();

        String getVideoMime();

        ByteString getVideoMimeBytes();

        String getWhichPlayer();

        ByteString getWhichPlayerBytes();

        int getWidth();

        boolean hasAudioCodec();

        boolean hasAudioMime();

        boolean hasDataSourceType();

        boolean hasDurationMillis();

        boolean hasError();

        boolean hasErrorCode();

        boolean hasErrorState();

        boolean hasFramerate();

        boolean hasFrames();

        boolean hasFramesDropped();

        boolean hasFramesDroppedStartup();

        boolean hasHeight();

        boolean hasPlayingMillis();

        boolean hasRebufferAtExit();

        boolean hasRebufferingMillis();

        boolean hasRebuffers();

        boolean hasVideoCodec();

        boolean hasVideoMime();

        boolean hasWhichPlayer();

        boolean hasWidth();
    }

    public interface RecorderDataOrBuilder extends MessageLiteOrBuilder {
        int getAudioBitrate();

        int getAudioChannels();

        String getAudioMime();

        ByteString getAudioMimeBytes();

        int getAudioSamplerate();

        int getAudioTimescale();

        int getCaptureFps();

        double getCaptureFpsEnable();

        long getDurationMillis();

        int getFramerate();

        int getHeight();

        int getIframeInterval();

        int getMovieTimescale();

        int getPausedCount();

        long getPausedMillis();

        int getRotation();

        int getVideoBitrate();

        int getVideoLevel();

        String getVideoMime();

        ByteString getVideoMimeBytes();

        int getVideoProfile();

        int getVideoTimescale();

        int getWidth();

        boolean hasAudioBitrate();

        boolean hasAudioChannels();

        boolean hasAudioMime();

        boolean hasAudioSamplerate();

        boolean hasAudioTimescale();

        boolean hasCaptureFps();

        boolean hasCaptureFpsEnable();

        boolean hasDurationMillis();

        boolean hasFramerate();

        boolean hasHeight();

        boolean hasIframeInterval();

        boolean hasMovieTimescale();

        boolean hasPausedCount();

        boolean hasPausedMillis();

        boolean hasRotation();

        boolean hasVideoBitrate();

        boolean hasVideoLevel();

        boolean hasVideoMime();

        boolean hasVideoProfile();

        boolean hasVideoTimescale();

        boolean hasWidth();
    }

    private Mediametrics() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class AudioPolicyData extends GeneratedMessageLite<AudioPolicyData, Builder> implements AudioPolicyDataOrBuilder {
        public static final int ACTIVE_DEVICE_FIELD_NUMBER = 9;
        public static final int ACTIVE_PACKAGE_FIELD_NUMBER = 7;
        public static final int ACTIVE_SESSION_FIELD_NUMBER = 8;
        public static final int ACTIVE_SOURCE_FIELD_NUMBER = 6;
        private static final AudioPolicyData DEFAULT_INSTANCE = new AudioPolicyData();
        private static volatile Parser<AudioPolicyData> PARSER = null;
        public static final int REQUEST_DEVICE_FIELD_NUMBER = 5;
        public static final int REQUEST_PACKAGE_FIELD_NUMBER = 3;
        public static final int REQUEST_SESSION_FIELD_NUMBER = 4;
        public static final int REQUEST_SOURCE_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 1;
        private String activeDevice_ = "";
        private String activePackage_ = "";
        private int activeSession_ = 0;
        private String activeSource_ = "";
        private int bitField0_;
        private String requestDevice_ = "";
        private String requestPackage_ = "";
        private int requestSession_ = 0;
        private String requestSource_ = "";
        private int status_ = 0;

        private AudioPolicyData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStatus(int value) {
            this.bitField0_ |= 1;
            this.status_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStatus() {
            this.bitField0_ &= -2;
            this.status_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasRequestSource() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getRequestSource() {
            return this.requestSource_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getRequestSourceBytes() {
            return ByteString.copyFromUtf8(this.requestSource_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestSource(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.requestSource_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequestSource() {
            this.bitField0_ &= -3;
            this.requestSource_ = getDefaultInstance().getRequestSource();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestSourceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.requestSource_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasRequestPackage() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getRequestPackage() {
            return this.requestPackage_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getRequestPackageBytes() {
            return ByteString.copyFromUtf8(this.requestPackage_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.requestPackage_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequestPackage() {
            this.bitField0_ &= -5;
            this.requestPackage_ = getDefaultInstance().getRequestPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.requestPackage_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasRequestSession() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public int getRequestSession() {
            return this.requestSession_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestSession(int value) {
            this.bitField0_ |= 8;
            this.requestSession_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequestSession() {
            this.bitField0_ &= -9;
            this.requestSession_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasRequestDevice() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getRequestDevice() {
            return this.requestDevice_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getRequestDeviceBytes() {
            return ByteString.copyFromUtf8(this.requestDevice_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestDevice(String value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.requestDevice_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequestDevice() {
            this.bitField0_ &= -17;
            this.requestDevice_ = getDefaultInstance().getRequestDevice();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequestDeviceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.requestDevice_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasActiveSource() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getActiveSource() {
            return this.activeSource_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getActiveSourceBytes() {
            return ByteString.copyFromUtf8(this.activeSource_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveSource(String value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.activeSource_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveSource() {
            this.bitField0_ &= -33;
            this.activeSource_ = getDefaultInstance().getActiveSource();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveSourceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.activeSource_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasActivePackage() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getActivePackage() {
            return this.activePackage_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getActivePackageBytes() {
            return ByteString.copyFromUtf8(this.activePackage_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActivePackage(String value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.activePackage_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActivePackage() {
            this.bitField0_ &= -65;
            this.activePackage_ = getDefaultInstance().getActivePackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActivePackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.activePackage_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasActiveSession() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public int getActiveSession() {
            return this.activeSession_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveSession(int value) {
            this.bitField0_ |= 128;
            this.activeSession_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveSession() {
            this.bitField0_ &= -129;
            this.activeSession_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public boolean hasActiveDevice() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public String getActiveDevice() {
            return this.activeDevice_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
        public ByteString getActiveDeviceBytes() {
            return ByteString.copyFromUtf8(this.activeDevice_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveDevice(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.activeDevice_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveDevice() {
            this.bitField0_ &= -257;
            this.activeDevice_ = getDefaultInstance().getActiveDevice();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveDeviceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.activeDevice_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.status_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getRequestSource());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getRequestPackage());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.requestSession_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeString(5, getRequestDevice());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeString(6, getActiveSource());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeString(7, getActivePackage());
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.activeSession_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getActiveDevice());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.status_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getRequestSource());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getRequestPackage());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.requestSession_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeStringSize(5, getRequestDevice());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeStringSize(6, getActiveSource());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeStringSize(7, getActivePackage());
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.activeSession_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getActiveDevice());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AudioPolicyData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioPolicyData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioPolicyData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioPolicyData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioPolicyData parseFrom(InputStream input) throws IOException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioPolicyData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioPolicyData parseDelimitedFrom(InputStream input) throws IOException {
            return (AudioPolicyData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioPolicyData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioPolicyData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioPolicyData parseFrom(CodedInputStream input) throws IOException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioPolicyData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioPolicyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AudioPolicyData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AudioPolicyData, Builder> implements AudioPolicyDataOrBuilder {
            private Builder() {
                super(AudioPolicyData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasStatus() {
                return ((AudioPolicyData) this.instance).hasStatus();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public int getStatus() {
                return ((AudioPolicyData) this.instance).getStatus();
            }

            public Builder setStatus(int value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setStatus(value);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearStatus();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasRequestSource() {
                return ((AudioPolicyData) this.instance).hasRequestSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getRequestSource() {
                return ((AudioPolicyData) this.instance).getRequestSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getRequestSourceBytes() {
                return ((AudioPolicyData) this.instance).getRequestSourceBytes();
            }

            public Builder setRequestSource(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestSource(value);
                return this;
            }

            public Builder clearRequestSource() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearRequestSource();
                return this;
            }

            public Builder setRequestSourceBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestSourceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasRequestPackage() {
                return ((AudioPolicyData) this.instance).hasRequestPackage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getRequestPackage() {
                return ((AudioPolicyData) this.instance).getRequestPackage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getRequestPackageBytes() {
                return ((AudioPolicyData) this.instance).getRequestPackageBytes();
            }

            public Builder setRequestPackage(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestPackage(value);
                return this;
            }

            public Builder clearRequestPackage() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearRequestPackage();
                return this;
            }

            public Builder setRequestPackageBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestPackageBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasRequestSession() {
                return ((AudioPolicyData) this.instance).hasRequestSession();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public int getRequestSession() {
                return ((AudioPolicyData) this.instance).getRequestSession();
            }

            public Builder setRequestSession(int value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestSession(value);
                return this;
            }

            public Builder clearRequestSession() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearRequestSession();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasRequestDevice() {
                return ((AudioPolicyData) this.instance).hasRequestDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getRequestDevice() {
                return ((AudioPolicyData) this.instance).getRequestDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getRequestDeviceBytes() {
                return ((AudioPolicyData) this.instance).getRequestDeviceBytes();
            }

            public Builder setRequestDevice(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestDevice(value);
                return this;
            }

            public Builder clearRequestDevice() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearRequestDevice();
                return this;
            }

            public Builder setRequestDeviceBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setRequestDeviceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasActiveSource() {
                return ((AudioPolicyData) this.instance).hasActiveSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getActiveSource() {
                return ((AudioPolicyData) this.instance).getActiveSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getActiveSourceBytes() {
                return ((AudioPolicyData) this.instance).getActiveSourceBytes();
            }

            public Builder setActiveSource(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActiveSource(value);
                return this;
            }

            public Builder clearActiveSource() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearActiveSource();
                return this;
            }

            public Builder setActiveSourceBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActiveSourceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasActivePackage() {
                return ((AudioPolicyData) this.instance).hasActivePackage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getActivePackage() {
                return ((AudioPolicyData) this.instance).getActivePackage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getActivePackageBytes() {
                return ((AudioPolicyData) this.instance).getActivePackageBytes();
            }

            public Builder setActivePackage(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActivePackage(value);
                return this;
            }

            public Builder clearActivePackage() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearActivePackage();
                return this;
            }

            public Builder setActivePackageBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActivePackageBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasActiveSession() {
                return ((AudioPolicyData) this.instance).hasActiveSession();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public int getActiveSession() {
                return ((AudioPolicyData) this.instance).getActiveSession();
            }

            public Builder setActiveSession(int value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActiveSession(value);
                return this;
            }

            public Builder clearActiveSession() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearActiveSession();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public boolean hasActiveDevice() {
                return ((AudioPolicyData) this.instance).hasActiveDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public String getActiveDevice() {
                return ((AudioPolicyData) this.instance).getActiveDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioPolicyDataOrBuilder
            public ByteString getActiveDeviceBytes() {
                return ((AudioPolicyData) this.instance).getActiveDeviceBytes();
            }

            public Builder setActiveDevice(String value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActiveDevice(value);
                return this;
            }

            public Builder clearActiveDevice() {
                copyOnWrite();
                ((AudioPolicyData) this.instance).clearActiveDevice();
                return this;
            }

            public Builder setActiveDeviceBytes(ByteString value) {
                copyOnWrite();
                ((AudioPolicyData) this.instance).setActiveDeviceBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AudioPolicyData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AudioPolicyData other = (AudioPolicyData) arg1;
                    this.status_ = visitor.visitInt(hasStatus(), this.status_, other.hasStatus(), other.status_);
                    this.requestSource_ = visitor.visitString(hasRequestSource(), this.requestSource_, other.hasRequestSource(), other.requestSource_);
                    this.requestPackage_ = visitor.visitString(hasRequestPackage(), this.requestPackage_, other.hasRequestPackage(), other.requestPackage_);
                    this.requestSession_ = visitor.visitInt(hasRequestSession(), this.requestSession_, other.hasRequestSession(), other.requestSession_);
                    this.requestDevice_ = visitor.visitString(hasRequestDevice(), this.requestDevice_, other.hasRequestDevice(), other.requestDevice_);
                    this.activeSource_ = visitor.visitString(hasActiveSource(), this.activeSource_, other.hasActiveSource(), other.activeSource_);
                    this.activePackage_ = visitor.visitString(hasActivePackage(), this.activePackage_, other.hasActivePackage(), other.activePackage_);
                    this.activeSession_ = visitor.visitInt(hasActiveSession(), this.activeSession_, other.hasActiveSession(), other.activeSession_);
                    this.activeDevice_ = visitor.visitString(hasActiveDevice(), this.activeDevice_, other.hasActiveDevice(), other.activeDevice_);
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
                                this.status_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.requestSource_ = s;
                            } else if (tag == 26) {
                                String s2 = input.readString();
                                this.bitField0_ |= 4;
                                this.requestPackage_ = s2;
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.requestSession_ = input.readInt32();
                            } else if (tag == 42) {
                                String s3 = input.readString();
                                this.bitField0_ |= 16;
                                this.requestDevice_ = s3;
                            } else if (tag == 50) {
                                String s4 = input.readString();
                                this.bitField0_ = 32 | this.bitField0_;
                                this.activeSource_ = s4;
                            } else if (tag == 58) {
                                String s5 = input.readString();
                                this.bitField0_ = 64 | this.bitField0_;
                                this.activePackage_ = s5;
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.activeSession_ = input.readInt32();
                            } else if (tag == 74) {
                                String s6 = input.readString();
                                this.bitField0_ |= 256;
                                this.activeDevice_ = s6;
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
                        synchronized (AudioPolicyData.class) {
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

        public static AudioPolicyData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AudioPolicyData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AudioRecordData extends GeneratedMessageLite<AudioRecordData, Builder> implements AudioRecordDataOrBuilder {
        public static final int ATTRIBUTES_FIELD_NUMBER = 13;
        public static final int CHANNELS_FIELD_NUMBER = 5;
        public static final int CHANNEL_MASK_FIELD_NUMBER = 14;
        public static final int COUNT_FIELD_NUMBER = 8;
        public static final int CREATED_MILLIS_FIELD_NUMBER = 6;
        private static final AudioRecordData DEFAULT_INSTANCE = new AudioRecordData();
        public static final int DURATION_MILLIS_FIELD_NUMBER = 7;
        public static final int ENCODING_FIELD_NUMBER = 1;
        public static final int ERROR_CODE_FIELD_NUMBER = 9;
        public static final int ERROR_FUNCTION_FIELD_NUMBER = 10;
        public static final int FRAME_COUNT_FIELD_NUMBER = 12;
        public static final int LATENCY_FIELD_NUMBER = 3;
        private static volatile Parser<AudioRecordData> PARSER = null;
        public static final int PORT_ID_FIELD_NUMBER = 11;
        public static final int SAMPLERATE_FIELD_NUMBER = 4;
        public static final int SOURCE_FIELD_NUMBER = 2;
        public static final int START_COUNT_FIELD_NUMBER = 15;
        private String attributes_ = "";
        private int bitField0_;
        private long channelMask_ = 0;
        private int channels_ = 0;
        private int count_ = 0;
        private long createdMillis_ = 0;
        private long durationMillis_ = 0;
        private String encoding_ = "";
        private int errorCode_ = 0;
        private String errorFunction_ = "";
        private int frameCount_ = 0;
        private int latency_ = 0;
        private int portId_ = 0;
        private int samplerate_ = 0;
        private String source_ = "";
        private long startCount_ = 0;

        private AudioRecordData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasEncoding() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public String getEncoding() {
            return this.encoding_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public ByteString getEncodingBytes() {
            return ByteString.copyFromUtf8(this.encoding_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncoding(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.encoding_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEncoding() {
            this.bitField0_ &= -2;
            this.encoding_ = getDefaultInstance().getEncoding();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncodingBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.encoding_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasSource() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public String getSource() {
            return this.source_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public ByteString getSourceBytes() {
            return ByteString.copyFromUtf8(this.source_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSource(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.source_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSource() {
            this.bitField0_ &= -3;
            this.source_ = getDefaultInstance().getSource();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSourceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.source_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasLatency() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getLatency() {
            return this.latency_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatency(int value) {
            this.bitField0_ |= 4;
            this.latency_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatency() {
            this.bitField0_ &= -5;
            this.latency_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasSamplerate() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getSamplerate() {
            return this.samplerate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSamplerate(int value) {
            this.bitField0_ |= 8;
            this.samplerate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSamplerate() {
            this.bitField0_ &= -9;
            this.samplerate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasChannels() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getChannels() {
            return this.channels_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannels(int value) {
            this.bitField0_ |= 16;
            this.channels_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannels() {
            this.bitField0_ &= -17;
            this.channels_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasCreatedMillis() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public long getCreatedMillis() {
            return this.createdMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCreatedMillis(long value) {
            this.bitField0_ |= 32;
            this.createdMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCreatedMillis() {
            this.bitField0_ &= -33;
            this.createdMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasDurationMillis() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public long getDurationMillis() {
            return this.durationMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMillis(long value) {
            this.bitField0_ |= 64;
            this.durationMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMillis() {
            this.bitField0_ &= -65;
            this.durationMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 128;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -129;
            this.count_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasErrorCode() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorCode(int value) {
            this.bitField0_ |= 256;
            this.errorCode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorCode() {
            this.bitField0_ &= -257;
            this.errorCode_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasErrorFunction() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public String getErrorFunction() {
            return this.errorFunction_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public ByteString getErrorFunctionBytes() {
            return ByteString.copyFromUtf8(this.errorFunction_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorFunction(String value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.errorFunction_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorFunction() {
            this.bitField0_ &= -513;
            this.errorFunction_ = getDefaultInstance().getErrorFunction();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorFunctionBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.errorFunction_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasPortId() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getPortId() {
            return this.portId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPortId(int value) {
            this.bitField0_ |= 1024;
            this.portId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPortId() {
            this.bitField0_ &= -1025;
            this.portId_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasFrameCount() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public int getFrameCount() {
            return this.frameCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFrameCount(int value) {
            this.bitField0_ |= 2048;
            this.frameCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFrameCount() {
            this.bitField0_ &= -2049;
            this.frameCount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasAttributes() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public String getAttributes() {
            return this.attributes_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public ByteString getAttributesBytes() {
            return ByteString.copyFromUtf8(this.attributes_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAttributes(String value) {
            if (value != null) {
                this.bitField0_ |= 4096;
                this.attributes_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAttributes() {
            this.bitField0_ &= -4097;
            this.attributes_ = getDefaultInstance().getAttributes();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAttributesBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4096;
                this.attributes_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasChannelMask() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public long getChannelMask() {
            return this.channelMask_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannelMask(long value) {
            this.bitField0_ |= 8192;
            this.channelMask_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannelMask() {
            this.bitField0_ &= -8193;
            this.channelMask_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public boolean hasStartCount() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
        public long getStartCount() {
            return this.startCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartCount(long value) {
            this.bitField0_ |= 16384;
            this.startCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartCount() {
            this.bitField0_ &= -16385;
            this.startCount_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getEncoding());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getSource());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.latency_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.samplerate_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.channels_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.createdMillis_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.durationMillis_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.count_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.errorCode_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeString(10, getErrorFunction());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.portId_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(12, this.frameCount_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeString(13, getAttributes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt64(14, this.channelMask_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt64(15, this.startCount_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getEncoding());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getSource());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.latency_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.samplerate_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.channels_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.createdMillis_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.durationMillis_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.count_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.errorCode_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeStringSize(10, getErrorFunction());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.portId_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(12, this.frameCount_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeStringSize(13, getAttributes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt64Size(14, this.channelMask_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt64Size(15, this.startCount_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AudioRecordData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioRecordData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioRecordData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioRecordData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioRecordData parseFrom(InputStream input) throws IOException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioRecordData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioRecordData parseDelimitedFrom(InputStream input) throws IOException {
            return (AudioRecordData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioRecordData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioRecordData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioRecordData parseFrom(CodedInputStream input) throws IOException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioRecordData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioRecordData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AudioRecordData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AudioRecordData, Builder> implements AudioRecordDataOrBuilder {
            private Builder() {
                super(AudioRecordData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasEncoding() {
                return ((AudioRecordData) this.instance).hasEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public String getEncoding() {
                return ((AudioRecordData) this.instance).getEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public ByteString getEncodingBytes() {
                return ((AudioRecordData) this.instance).getEncodingBytes();
            }

            public Builder setEncoding(String value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setEncoding(value);
                return this;
            }

            public Builder clearEncoding() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearEncoding();
                return this;
            }

            public Builder setEncodingBytes(ByteString value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setEncodingBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasSource() {
                return ((AudioRecordData) this.instance).hasSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public String getSource() {
                return ((AudioRecordData) this.instance).getSource();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public ByteString getSourceBytes() {
                return ((AudioRecordData) this.instance).getSourceBytes();
            }

            public Builder setSource(String value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setSource(value);
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearSource();
                return this;
            }

            public Builder setSourceBytes(ByteString value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setSourceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasLatency() {
                return ((AudioRecordData) this.instance).hasLatency();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getLatency() {
                return ((AudioRecordData) this.instance).getLatency();
            }

            public Builder setLatency(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setLatency(value);
                return this;
            }

            public Builder clearLatency() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearLatency();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasSamplerate() {
                return ((AudioRecordData) this.instance).hasSamplerate();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getSamplerate() {
                return ((AudioRecordData) this.instance).getSamplerate();
            }

            public Builder setSamplerate(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setSamplerate(value);
                return this;
            }

            public Builder clearSamplerate() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearSamplerate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasChannels() {
                return ((AudioRecordData) this.instance).hasChannels();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getChannels() {
                return ((AudioRecordData) this.instance).getChannels();
            }

            public Builder setChannels(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setChannels(value);
                return this;
            }

            public Builder clearChannels() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearChannels();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasCreatedMillis() {
                return ((AudioRecordData) this.instance).hasCreatedMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public long getCreatedMillis() {
                return ((AudioRecordData) this.instance).getCreatedMillis();
            }

            public Builder setCreatedMillis(long value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setCreatedMillis(value);
                return this;
            }

            public Builder clearCreatedMillis() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearCreatedMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasDurationMillis() {
                return ((AudioRecordData) this.instance).hasDurationMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public long getDurationMillis() {
                return ((AudioRecordData) this.instance).getDurationMillis();
            }

            public Builder setDurationMillis(long value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setDurationMillis(value);
                return this;
            }

            public Builder clearDurationMillis() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearDurationMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasCount() {
                return ((AudioRecordData) this.instance).hasCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getCount() {
                return ((AudioRecordData) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasErrorCode() {
                return ((AudioRecordData) this.instance).hasErrorCode();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getErrorCode() {
                return ((AudioRecordData) this.instance).getErrorCode();
            }

            public Builder setErrorCode(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setErrorCode(value);
                return this;
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearErrorCode();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasErrorFunction() {
                return ((AudioRecordData) this.instance).hasErrorFunction();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public String getErrorFunction() {
                return ((AudioRecordData) this.instance).getErrorFunction();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public ByteString getErrorFunctionBytes() {
                return ((AudioRecordData) this.instance).getErrorFunctionBytes();
            }

            public Builder setErrorFunction(String value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setErrorFunction(value);
                return this;
            }

            public Builder clearErrorFunction() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearErrorFunction();
                return this;
            }

            public Builder setErrorFunctionBytes(ByteString value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setErrorFunctionBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasPortId() {
                return ((AudioRecordData) this.instance).hasPortId();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getPortId() {
                return ((AudioRecordData) this.instance).getPortId();
            }

            public Builder setPortId(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setPortId(value);
                return this;
            }

            public Builder clearPortId() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearPortId();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasFrameCount() {
                return ((AudioRecordData) this.instance).hasFrameCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public int getFrameCount() {
                return ((AudioRecordData) this.instance).getFrameCount();
            }

            public Builder setFrameCount(int value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setFrameCount(value);
                return this;
            }

            public Builder clearFrameCount() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearFrameCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasAttributes() {
                return ((AudioRecordData) this.instance).hasAttributes();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public String getAttributes() {
                return ((AudioRecordData) this.instance).getAttributes();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public ByteString getAttributesBytes() {
                return ((AudioRecordData) this.instance).getAttributesBytes();
            }

            public Builder setAttributes(String value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setAttributes(value);
                return this;
            }

            public Builder clearAttributes() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearAttributes();
                return this;
            }

            public Builder setAttributesBytes(ByteString value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setAttributesBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasChannelMask() {
                return ((AudioRecordData) this.instance).hasChannelMask();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public long getChannelMask() {
                return ((AudioRecordData) this.instance).getChannelMask();
            }

            public Builder setChannelMask(long value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setChannelMask(value);
                return this;
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearChannelMask();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public boolean hasStartCount() {
                return ((AudioRecordData) this.instance).hasStartCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioRecordDataOrBuilder
            public long getStartCount() {
                return ((AudioRecordData) this.instance).getStartCount();
            }

            public Builder setStartCount(long value) {
                copyOnWrite();
                ((AudioRecordData) this.instance).setStartCount(value);
                return this;
            }

            public Builder clearStartCount() {
                copyOnWrite();
                ((AudioRecordData) this.instance).clearStartCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AudioRecordData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AudioRecordData other = (AudioRecordData) arg1;
                    this.encoding_ = visitor.visitString(hasEncoding(), this.encoding_, other.hasEncoding(), other.encoding_);
                    this.source_ = visitor.visitString(hasSource(), this.source_, other.hasSource(), other.source_);
                    this.latency_ = visitor.visitInt(hasLatency(), this.latency_, other.hasLatency(), other.latency_);
                    this.samplerate_ = visitor.visitInt(hasSamplerate(), this.samplerate_, other.hasSamplerate(), other.samplerate_);
                    this.channels_ = visitor.visitInt(hasChannels(), this.channels_, other.hasChannels(), other.channels_);
                    this.createdMillis_ = visitor.visitLong(hasCreatedMillis(), this.createdMillis_, other.hasCreatedMillis(), other.createdMillis_);
                    this.durationMillis_ = visitor.visitLong(hasDurationMillis(), this.durationMillis_, other.hasDurationMillis(), other.durationMillis_);
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                    this.errorCode_ = visitor.visitInt(hasErrorCode(), this.errorCode_, other.hasErrorCode(), other.errorCode_);
                    this.errorFunction_ = visitor.visitString(hasErrorFunction(), this.errorFunction_, other.hasErrorFunction(), other.errorFunction_);
                    this.portId_ = visitor.visitInt(hasPortId(), this.portId_, other.hasPortId(), other.portId_);
                    this.frameCount_ = visitor.visitInt(hasFrameCount(), this.frameCount_, other.hasFrameCount(), other.frameCount_);
                    this.attributes_ = visitor.visitString(hasAttributes(), this.attributes_, other.hasAttributes(), other.attributes_);
                    this.channelMask_ = visitor.visitLong(hasChannelMask(), this.channelMask_, other.hasChannelMask(), other.channelMask_);
                    this.startCount_ = visitor.visitLong(hasStartCount(), this.startCount_, other.hasStartCount(), other.startCount_);
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
                                    this.encoding_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.source_ = s2;
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.latency_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.samplerate_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.channels_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.createdMillis_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.durationMillis_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.count_ = input.readInt32();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.errorCode_ = input.readInt32();
                                    break;
                                case 82:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 512;
                                    this.errorFunction_ = s3;
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.portId_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.frameCount_ = input.readInt32();
                                    break;
                                case 106:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 4096;
                                    this.attributes_ = s4;
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.channelMask_ = input.readInt64();
                                    break;
                                case 120:
                                    this.bitField0_ |= 16384;
                                    this.startCount_ = input.readInt64();
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
                        synchronized (AudioRecordData.class) {
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

        public static AudioRecordData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AudioRecordData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AudioThreadData extends GeneratedMessageLite<AudioThreadData, Builder> implements AudioThreadDataOrBuilder {
        public static final int ACTIVE_MILLIS_FIELD_NUMBER = 9;
        public static final int CHANNEL_MASK_FIELD_NUMBER = 14;
        private static final AudioThreadData DEFAULT_INSTANCE = new AudioThreadData();
        public static final int DURATION_MILLIS_FIELD_NUMBER = 10;
        public static final int ENCODING_FIELD_NUMBER = 15;
        public static final int FRAMECOUNT_FIELD_NUMBER = 2;
        public static final int FRAME_COUNT_FIELD_NUMBER = 16;
        public static final int ID_FIELD_NUMBER = 11;
        public static final int INPUT_DEVICE_FIELD_NUMBER = 18;
        public static final int IO_JITTER_MEAN_MILLIS_FIELD_NUMBER = 19;
        public static final int IO_JITTER_STDDEV_MILLIS_FIELD_NUMBER = 20;
        public static final int LATENCY_MEAN_MILLIS_FIELD_NUMBER = 25;
        public static final int LATENCY_MILLIS_HIST_FIELD_NUMBER = 5;
        public static final int LATENCY_STDDEV_MILLIS_FIELD_NUMBER = 26;
        public static final int OUTPUT_DEVICE_FIELD_NUMBER = 17;
        public static final int OVERRUNS_FIELD_NUMBER = 8;
        private static volatile Parser<AudioThreadData> PARSER = null;
        public static final int PORT_ID_FIELD_NUMBER = 12;
        public static final int PROCESS_TIME_MEAN_MILLIS_FIELD_NUMBER = 21;
        public static final int PROCESS_TIME_STDDEV_MILLIS_FIELD_NUMBER = 22;
        public static final int SAMPLERATE_FIELD_NUMBER = 3;
        public static final int SAMPLE_RATE_FIELD_NUMBER = 13;
        public static final int TIMESTAMP_JITTER_MEAN_MILLIS_FIELD_NUMBER = 23;
        public static final int TIMESTAMP_JITTER_STDDEV_MILLIS_FIELD_NUMBER = 24;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int UNDERRUNS_FIELD_NUMBER = 7;
        public static final int WARMUP_MILLIS_HIST_FIELD_NUMBER = 6;
        public static final int WORK_MILLIS_HIST_FIELD_NUMBER = 4;
        private long activeMillis_ = 0;
        private int bitField0_;
        private long channelMask_ = 0;
        private long durationMillis_ = 0;
        private String encoding_ = "";
        private int frameCount_ = 0;
        private int framecount_ = 0;
        private int id_ = 0;
        private String inputDevice_ = "";
        private double ioJitterMeanMillis_ = 0.0d;
        private double ioJitterStddevMillis_ = 0.0d;
        private double latencyMeanMillis_ = 0.0d;
        private String latencyMillisHist_ = "";
        private double latencyStddevMillis_ = 0.0d;
        private String outputDevice_ = "";
        private long overruns_ = 0;
        private int portId_ = 0;
        private double processTimeMeanMillis_ = 0.0d;
        private double processTimeStddevMillis_ = 0.0d;
        private int sampleRate_ = 0;
        private int samplerate_ = 0;
        private double timestampJitterMeanMillis_ = 0.0d;
        private double timestampJitterStddevMillis_ = 0.0d;
        private String type_ = "";
        private long underruns_ = 0;
        private String warmupMillisHist_ = "";
        private String workMillisHist_ = "";

        private AudioThreadData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getType() {
            return this.type_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getTypeBytes() {
            return ByteString.copyFromUtf8(this.type_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.type_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -2;
            this.type_ = getDefaultInstance().getType();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTypeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.type_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasFramecount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getFramecount() {
            return this.framecount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFramecount(int value) {
            this.bitField0_ |= 2;
            this.framecount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFramecount() {
            this.bitField0_ &= -3;
            this.framecount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasSamplerate() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getSamplerate() {
            return this.samplerate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSamplerate(int value) {
            this.bitField0_ |= 4;
            this.samplerate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSamplerate() {
            this.bitField0_ &= -5;
            this.samplerate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasWorkMillisHist() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getWorkMillisHist() {
            return this.workMillisHist_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getWorkMillisHistBytes() {
            return ByteString.copyFromUtf8(this.workMillisHist_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkMillisHist(String value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.workMillisHist_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWorkMillisHist() {
            this.bitField0_ &= -9;
            this.workMillisHist_ = getDefaultInstance().getWorkMillisHist();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWorkMillisHistBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.workMillisHist_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasLatencyMillisHist() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getLatencyMillisHist() {
            return this.latencyMillisHist_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getLatencyMillisHistBytes() {
            return ByteString.copyFromUtf8(this.latencyMillisHist_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMillisHist(String value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.latencyMillisHist_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyMillisHist() {
            this.bitField0_ &= -17;
            this.latencyMillisHist_ = getDefaultInstance().getLatencyMillisHist();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMillisHistBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.latencyMillisHist_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasWarmupMillisHist() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getWarmupMillisHist() {
            return this.warmupMillisHist_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getWarmupMillisHistBytes() {
            return ByteString.copyFromUtf8(this.warmupMillisHist_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWarmupMillisHist(String value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.warmupMillisHist_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWarmupMillisHist() {
            this.bitField0_ &= -33;
            this.warmupMillisHist_ = getDefaultInstance().getWarmupMillisHist();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWarmupMillisHistBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.warmupMillisHist_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasUnderruns() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public long getUnderruns() {
            return this.underruns_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnderruns(long value) {
            this.bitField0_ |= 64;
            this.underruns_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnderruns() {
            this.bitField0_ &= -65;
            this.underruns_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasOverruns() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public long getOverruns() {
            return this.overruns_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOverruns(long value) {
            this.bitField0_ |= 128;
            this.overruns_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOverruns() {
            this.bitField0_ &= -129;
            this.overruns_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasActiveMillis() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public long getActiveMillis() {
            return this.activeMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveMillis(long value) {
            this.bitField0_ |= 256;
            this.activeMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveMillis() {
            this.bitField0_ &= -257;
            this.activeMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasDurationMillis() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public long getDurationMillis() {
            return this.durationMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMillis(long value) {
            this.bitField0_ |= 512;
            this.durationMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMillis() {
            this.bitField0_ &= -513;
            this.durationMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1024;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -1025;
            this.id_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasPortId() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getPortId() {
            return this.portId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPortId(int value) {
            this.bitField0_ |= 2048;
            this.portId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPortId() {
            this.bitField0_ &= -2049;
            this.portId_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasSampleRate() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getSampleRate() {
            return this.sampleRate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSampleRate(int value) {
            this.bitField0_ |= 4096;
            this.sampleRate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSampleRate() {
            this.bitField0_ &= -4097;
            this.sampleRate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasChannelMask() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public long getChannelMask() {
            return this.channelMask_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannelMask(long value) {
            this.bitField0_ |= 8192;
            this.channelMask_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannelMask() {
            this.bitField0_ &= -8193;
            this.channelMask_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasEncoding() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getEncoding() {
            return this.encoding_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getEncodingBytes() {
            return ByteString.copyFromUtf8(this.encoding_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncoding(String value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.encoding_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEncoding() {
            this.bitField0_ &= -16385;
            this.encoding_ = getDefaultInstance().getEncoding();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncodingBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.encoding_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasFrameCount() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public int getFrameCount() {
            return this.frameCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFrameCount(int value) {
            this.bitField0_ |= 32768;
            this.frameCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFrameCount() {
            this.bitField0_ &= -32769;
            this.frameCount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasOutputDevice() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getOutputDevice() {
            return this.outputDevice_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getOutputDeviceBytes() {
            return ByteString.copyFromUtf8(this.outputDevice_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOutputDevice(String value) {
            if (value != null) {
                this.bitField0_ |= 65536;
                this.outputDevice_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOutputDevice() {
            this.bitField0_ &= -65537;
            this.outputDevice_ = getDefaultInstance().getOutputDevice();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOutputDeviceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 65536;
                this.outputDevice_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasInputDevice() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public String getInputDevice() {
            return this.inputDevice_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public ByteString getInputDeviceBytes() {
            return ByteString.copyFromUtf8(this.inputDevice_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInputDevice(String value) {
            if (value != null) {
                this.bitField0_ |= 131072;
                this.inputDevice_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInputDevice() {
            this.bitField0_ &= -131073;
            this.inputDevice_ = getDefaultInstance().getInputDevice();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInputDeviceBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 131072;
                this.inputDevice_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasIoJitterMeanMillis() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getIoJitterMeanMillis() {
            return this.ioJitterMeanMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIoJitterMeanMillis(double value) {
            this.bitField0_ |= 262144;
            this.ioJitterMeanMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIoJitterMeanMillis() {
            this.bitField0_ &= -262145;
            this.ioJitterMeanMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasIoJitterStddevMillis() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getIoJitterStddevMillis() {
            return this.ioJitterStddevMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIoJitterStddevMillis(double value) {
            this.bitField0_ |= 524288;
            this.ioJitterStddevMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIoJitterStddevMillis() {
            this.bitField0_ &= -524289;
            this.ioJitterStddevMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasProcessTimeMeanMillis() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getProcessTimeMeanMillis() {
            return this.processTimeMeanMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessTimeMeanMillis(double value) {
            this.bitField0_ |= 1048576;
            this.processTimeMeanMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessTimeMeanMillis() {
            this.bitField0_ &= -1048577;
            this.processTimeMeanMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasProcessTimeStddevMillis() {
            return (this.bitField0_ & 2097152) == 2097152;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getProcessTimeStddevMillis() {
            return this.processTimeStddevMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessTimeStddevMillis(double value) {
            this.bitField0_ |= 2097152;
            this.processTimeStddevMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessTimeStddevMillis() {
            this.bitField0_ &= -2097153;
            this.processTimeStddevMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasTimestampJitterMeanMillis() {
            return (this.bitField0_ & 4194304) == 4194304;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getTimestampJitterMeanMillis() {
            return this.timestampJitterMeanMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimestampJitterMeanMillis(double value) {
            this.bitField0_ |= 4194304;
            this.timestampJitterMeanMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimestampJitterMeanMillis() {
            this.bitField0_ &= -4194305;
            this.timestampJitterMeanMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasTimestampJitterStddevMillis() {
            return (this.bitField0_ & 8388608) == 8388608;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getTimestampJitterStddevMillis() {
            return this.timestampJitterStddevMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimestampJitterStddevMillis(double value) {
            this.bitField0_ |= 8388608;
            this.timestampJitterStddevMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimestampJitterStddevMillis() {
            this.bitField0_ &= -8388609;
            this.timestampJitterStddevMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasLatencyMeanMillis() {
            return (this.bitField0_ & 16777216) == 16777216;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getLatencyMeanMillis() {
            return this.latencyMeanMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMeanMillis(double value) {
            this.bitField0_ |= 16777216;
            this.latencyMeanMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyMeanMillis() {
            this.bitField0_ &= -16777217;
            this.latencyMeanMillis_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public boolean hasLatencyStddevMillis() {
            return (this.bitField0_ & 33554432) == 33554432;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
        public double getLatencyStddevMillis() {
            return this.latencyStddevMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyStddevMillis(double value) {
            this.bitField0_ |= 33554432;
            this.latencyStddevMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyStddevMillis() {
            this.bitField0_ &= -33554433;
            this.latencyStddevMillis_ = 0.0d;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getType());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.framecount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.samplerate_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeString(4, getWorkMillisHist());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeString(5, getLatencyMillisHist());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeString(6, getWarmupMillisHist());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.underruns_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.overruns_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.activeMillis_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.durationMillis_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.id_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(12, this.portId_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.sampleRate_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt64(14, this.channelMask_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeString(15, getEncoding());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt32(16, this.frameCount_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeString(17, getOutputDevice());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeString(18, getInputDevice());
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeDouble(19, this.ioJitterMeanMillis_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeDouble(20, this.ioJitterStddevMillis_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeDouble(21, this.processTimeMeanMillis_);
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                output.writeDouble(22, this.processTimeStddevMillis_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                output.writeDouble(23, this.timestampJitterMeanMillis_);
            }
            if ((this.bitField0_ & 8388608) == 8388608) {
                output.writeDouble(24, this.timestampJitterStddevMillis_);
            }
            if ((this.bitField0_ & 16777216) == 16777216) {
                output.writeDouble(25, this.latencyMeanMillis_);
            }
            if ((this.bitField0_ & 33554432) == 33554432) {
                output.writeDouble(26, this.latencyStddevMillis_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getType());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.framecount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.samplerate_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeStringSize(4, getWorkMillisHist());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeStringSize(5, getLatencyMillisHist());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeStringSize(6, getWarmupMillisHist());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.underruns_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.overruns_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.activeMillis_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.durationMillis_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.id_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(12, this.portId_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.sampleRate_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt64Size(14, this.channelMask_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeStringSize(15, getEncoding());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt32Size(16, this.frameCount_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeStringSize(17, getOutputDevice());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeStringSize(18, getInputDevice());
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeDoubleSize(19, this.ioJitterMeanMillis_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeDoubleSize(20, this.ioJitterStddevMillis_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeDoubleSize(21, this.processTimeMeanMillis_);
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                size2 += CodedOutputStream.computeDoubleSize(22, this.processTimeStddevMillis_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                size2 += CodedOutputStream.computeDoubleSize(23, this.timestampJitterMeanMillis_);
            }
            if ((this.bitField0_ & 8388608) == 8388608) {
                size2 += CodedOutputStream.computeDoubleSize(24, this.timestampJitterStddevMillis_);
            }
            if ((this.bitField0_ & 16777216) == 16777216) {
                size2 += CodedOutputStream.computeDoubleSize(25, this.latencyMeanMillis_);
            }
            if ((this.bitField0_ & 33554432) == 33554432) {
                size2 += CodedOutputStream.computeDoubleSize(26, this.latencyStddevMillis_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AudioThreadData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioThreadData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioThreadData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioThreadData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioThreadData parseFrom(InputStream input) throws IOException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioThreadData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioThreadData parseDelimitedFrom(InputStream input) throws IOException {
            return (AudioThreadData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioThreadData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioThreadData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioThreadData parseFrom(CodedInputStream input) throws IOException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioThreadData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioThreadData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AudioThreadData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AudioThreadData, Builder> implements AudioThreadDataOrBuilder {
            private Builder() {
                super(AudioThreadData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasType() {
                return ((AudioThreadData) this.instance).hasType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getType() {
                return ((AudioThreadData) this.instance).getType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getTypeBytes() {
                return ((AudioThreadData) this.instance).getTypeBytes();
            }

            public Builder setType(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearType();
                return this;
            }

            public Builder setTypeBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setTypeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasFramecount() {
                return ((AudioThreadData) this.instance).hasFramecount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getFramecount() {
                return ((AudioThreadData) this.instance).getFramecount();
            }

            public Builder setFramecount(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setFramecount(value);
                return this;
            }

            public Builder clearFramecount() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearFramecount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasSamplerate() {
                return ((AudioThreadData) this.instance).hasSamplerate();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getSamplerate() {
                return ((AudioThreadData) this.instance).getSamplerate();
            }

            public Builder setSamplerate(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setSamplerate(value);
                return this;
            }

            public Builder clearSamplerate() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearSamplerate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasWorkMillisHist() {
                return ((AudioThreadData) this.instance).hasWorkMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getWorkMillisHist() {
                return ((AudioThreadData) this.instance).getWorkMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getWorkMillisHistBytes() {
                return ((AudioThreadData) this.instance).getWorkMillisHistBytes();
            }

            public Builder setWorkMillisHist(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setWorkMillisHist(value);
                return this;
            }

            public Builder clearWorkMillisHist() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearWorkMillisHist();
                return this;
            }

            public Builder setWorkMillisHistBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setWorkMillisHistBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasLatencyMillisHist() {
                return ((AudioThreadData) this.instance).hasLatencyMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getLatencyMillisHist() {
                return ((AudioThreadData) this.instance).getLatencyMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getLatencyMillisHistBytes() {
                return ((AudioThreadData) this.instance).getLatencyMillisHistBytes();
            }

            public Builder setLatencyMillisHist(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setLatencyMillisHist(value);
                return this;
            }

            public Builder clearLatencyMillisHist() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearLatencyMillisHist();
                return this;
            }

            public Builder setLatencyMillisHistBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setLatencyMillisHistBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasWarmupMillisHist() {
                return ((AudioThreadData) this.instance).hasWarmupMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getWarmupMillisHist() {
                return ((AudioThreadData) this.instance).getWarmupMillisHist();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getWarmupMillisHistBytes() {
                return ((AudioThreadData) this.instance).getWarmupMillisHistBytes();
            }

            public Builder setWarmupMillisHist(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setWarmupMillisHist(value);
                return this;
            }

            public Builder clearWarmupMillisHist() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearWarmupMillisHist();
                return this;
            }

            public Builder setWarmupMillisHistBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setWarmupMillisHistBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasUnderruns() {
                return ((AudioThreadData) this.instance).hasUnderruns();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public long getUnderruns() {
                return ((AudioThreadData) this.instance).getUnderruns();
            }

            public Builder setUnderruns(long value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setUnderruns(value);
                return this;
            }

            public Builder clearUnderruns() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearUnderruns();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasOverruns() {
                return ((AudioThreadData) this.instance).hasOverruns();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public long getOverruns() {
                return ((AudioThreadData) this.instance).getOverruns();
            }

            public Builder setOverruns(long value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setOverruns(value);
                return this;
            }

            public Builder clearOverruns() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearOverruns();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasActiveMillis() {
                return ((AudioThreadData) this.instance).hasActiveMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public long getActiveMillis() {
                return ((AudioThreadData) this.instance).getActiveMillis();
            }

            public Builder setActiveMillis(long value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setActiveMillis(value);
                return this;
            }

            public Builder clearActiveMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearActiveMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasDurationMillis() {
                return ((AudioThreadData) this.instance).hasDurationMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public long getDurationMillis() {
                return ((AudioThreadData) this.instance).getDurationMillis();
            }

            public Builder setDurationMillis(long value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setDurationMillis(value);
                return this;
            }

            public Builder clearDurationMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearDurationMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasId() {
                return ((AudioThreadData) this.instance).hasId();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getId() {
                return ((AudioThreadData) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearId();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasPortId() {
                return ((AudioThreadData) this.instance).hasPortId();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getPortId() {
                return ((AudioThreadData) this.instance).getPortId();
            }

            public Builder setPortId(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setPortId(value);
                return this;
            }

            public Builder clearPortId() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearPortId();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasSampleRate() {
                return ((AudioThreadData) this.instance).hasSampleRate();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getSampleRate() {
                return ((AudioThreadData) this.instance).getSampleRate();
            }

            public Builder setSampleRate(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setSampleRate(value);
                return this;
            }

            public Builder clearSampleRate() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearSampleRate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasChannelMask() {
                return ((AudioThreadData) this.instance).hasChannelMask();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public long getChannelMask() {
                return ((AudioThreadData) this.instance).getChannelMask();
            }

            public Builder setChannelMask(long value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setChannelMask(value);
                return this;
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearChannelMask();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasEncoding() {
                return ((AudioThreadData) this.instance).hasEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getEncoding() {
                return ((AudioThreadData) this.instance).getEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getEncodingBytes() {
                return ((AudioThreadData) this.instance).getEncodingBytes();
            }

            public Builder setEncoding(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setEncoding(value);
                return this;
            }

            public Builder clearEncoding() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearEncoding();
                return this;
            }

            public Builder setEncodingBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setEncodingBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasFrameCount() {
                return ((AudioThreadData) this.instance).hasFrameCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public int getFrameCount() {
                return ((AudioThreadData) this.instance).getFrameCount();
            }

            public Builder setFrameCount(int value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setFrameCount(value);
                return this;
            }

            public Builder clearFrameCount() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearFrameCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasOutputDevice() {
                return ((AudioThreadData) this.instance).hasOutputDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getOutputDevice() {
                return ((AudioThreadData) this.instance).getOutputDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getOutputDeviceBytes() {
                return ((AudioThreadData) this.instance).getOutputDeviceBytes();
            }

            public Builder setOutputDevice(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setOutputDevice(value);
                return this;
            }

            public Builder clearOutputDevice() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearOutputDevice();
                return this;
            }

            public Builder setOutputDeviceBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setOutputDeviceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasInputDevice() {
                return ((AudioThreadData) this.instance).hasInputDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public String getInputDevice() {
                return ((AudioThreadData) this.instance).getInputDevice();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public ByteString getInputDeviceBytes() {
                return ((AudioThreadData) this.instance).getInputDeviceBytes();
            }

            public Builder setInputDevice(String value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setInputDevice(value);
                return this;
            }

            public Builder clearInputDevice() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearInputDevice();
                return this;
            }

            public Builder setInputDeviceBytes(ByteString value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setInputDeviceBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasIoJitterMeanMillis() {
                return ((AudioThreadData) this.instance).hasIoJitterMeanMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getIoJitterMeanMillis() {
                return ((AudioThreadData) this.instance).getIoJitterMeanMillis();
            }

            public Builder setIoJitterMeanMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setIoJitterMeanMillis(value);
                return this;
            }

            public Builder clearIoJitterMeanMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearIoJitterMeanMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasIoJitterStddevMillis() {
                return ((AudioThreadData) this.instance).hasIoJitterStddevMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getIoJitterStddevMillis() {
                return ((AudioThreadData) this.instance).getIoJitterStddevMillis();
            }

            public Builder setIoJitterStddevMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setIoJitterStddevMillis(value);
                return this;
            }

            public Builder clearIoJitterStddevMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearIoJitterStddevMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasProcessTimeMeanMillis() {
                return ((AudioThreadData) this.instance).hasProcessTimeMeanMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getProcessTimeMeanMillis() {
                return ((AudioThreadData) this.instance).getProcessTimeMeanMillis();
            }

            public Builder setProcessTimeMeanMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setProcessTimeMeanMillis(value);
                return this;
            }

            public Builder clearProcessTimeMeanMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearProcessTimeMeanMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasProcessTimeStddevMillis() {
                return ((AudioThreadData) this.instance).hasProcessTimeStddevMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getProcessTimeStddevMillis() {
                return ((AudioThreadData) this.instance).getProcessTimeStddevMillis();
            }

            public Builder setProcessTimeStddevMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setProcessTimeStddevMillis(value);
                return this;
            }

            public Builder clearProcessTimeStddevMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearProcessTimeStddevMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasTimestampJitterMeanMillis() {
                return ((AudioThreadData) this.instance).hasTimestampJitterMeanMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getTimestampJitterMeanMillis() {
                return ((AudioThreadData) this.instance).getTimestampJitterMeanMillis();
            }

            public Builder setTimestampJitterMeanMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setTimestampJitterMeanMillis(value);
                return this;
            }

            public Builder clearTimestampJitterMeanMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearTimestampJitterMeanMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasTimestampJitterStddevMillis() {
                return ((AudioThreadData) this.instance).hasTimestampJitterStddevMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getTimestampJitterStddevMillis() {
                return ((AudioThreadData) this.instance).getTimestampJitterStddevMillis();
            }

            public Builder setTimestampJitterStddevMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setTimestampJitterStddevMillis(value);
                return this;
            }

            public Builder clearTimestampJitterStddevMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearTimestampJitterStddevMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasLatencyMeanMillis() {
                return ((AudioThreadData) this.instance).hasLatencyMeanMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getLatencyMeanMillis() {
                return ((AudioThreadData) this.instance).getLatencyMeanMillis();
            }

            public Builder setLatencyMeanMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setLatencyMeanMillis(value);
                return this;
            }

            public Builder clearLatencyMeanMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearLatencyMeanMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public boolean hasLatencyStddevMillis() {
                return ((AudioThreadData) this.instance).hasLatencyStddevMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioThreadDataOrBuilder
            public double getLatencyStddevMillis() {
                return ((AudioThreadData) this.instance).getLatencyStddevMillis();
            }

            public Builder setLatencyStddevMillis(double value) {
                copyOnWrite();
                ((AudioThreadData) this.instance).setLatencyStddevMillis(value);
                return this;
            }

            public Builder clearLatencyStddevMillis() {
                copyOnWrite();
                ((AudioThreadData) this.instance).clearLatencyStddevMillis();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AudioThreadData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AudioThreadData other = (AudioThreadData) arg1;
                    this.type_ = visitor.visitString(hasType(), this.type_, other.hasType(), other.type_);
                    this.framecount_ = visitor.visitInt(hasFramecount(), this.framecount_, other.hasFramecount(), other.framecount_);
                    this.samplerate_ = visitor.visitInt(hasSamplerate(), this.samplerate_, other.hasSamplerate(), other.samplerate_);
                    this.workMillisHist_ = visitor.visitString(hasWorkMillisHist(), this.workMillisHist_, other.hasWorkMillisHist(), other.workMillisHist_);
                    this.latencyMillisHist_ = visitor.visitString(hasLatencyMillisHist(), this.latencyMillisHist_, other.hasLatencyMillisHist(), other.latencyMillisHist_);
                    this.warmupMillisHist_ = visitor.visitString(hasWarmupMillisHist(), this.warmupMillisHist_, other.hasWarmupMillisHist(), other.warmupMillisHist_);
                    this.underruns_ = visitor.visitLong(hasUnderruns(), this.underruns_, other.hasUnderruns(), other.underruns_);
                    this.overruns_ = visitor.visitLong(hasOverruns(), this.overruns_, other.hasOverruns(), other.overruns_);
                    this.activeMillis_ = visitor.visitLong(hasActiveMillis(), this.activeMillis_, other.hasActiveMillis(), other.activeMillis_);
                    this.durationMillis_ = visitor.visitLong(hasDurationMillis(), this.durationMillis_, other.hasDurationMillis(), other.durationMillis_);
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.portId_ = visitor.visitInt(hasPortId(), this.portId_, other.hasPortId(), other.portId_);
                    this.sampleRate_ = visitor.visitInt(hasSampleRate(), this.sampleRate_, other.hasSampleRate(), other.sampleRate_);
                    this.channelMask_ = visitor.visitLong(hasChannelMask(), this.channelMask_, other.hasChannelMask(), other.channelMask_);
                    this.encoding_ = visitor.visitString(hasEncoding(), this.encoding_, other.hasEncoding(), other.encoding_);
                    this.frameCount_ = visitor.visitInt(hasFrameCount(), this.frameCount_, other.hasFrameCount(), other.frameCount_);
                    this.outputDevice_ = visitor.visitString(hasOutputDevice(), this.outputDevice_, other.hasOutputDevice(), other.outputDevice_);
                    this.inputDevice_ = visitor.visitString(hasInputDevice(), this.inputDevice_, other.hasInputDevice(), other.inputDevice_);
                    this.ioJitterMeanMillis_ = visitor.visitDouble(hasIoJitterMeanMillis(), this.ioJitterMeanMillis_, other.hasIoJitterMeanMillis(), other.ioJitterMeanMillis_);
                    this.ioJitterStddevMillis_ = visitor.visitDouble(hasIoJitterStddevMillis(), this.ioJitterStddevMillis_, other.hasIoJitterStddevMillis(), other.ioJitterStddevMillis_);
                    this.processTimeMeanMillis_ = visitor.visitDouble(hasProcessTimeMeanMillis(), this.processTimeMeanMillis_, other.hasProcessTimeMeanMillis(), other.processTimeMeanMillis_);
                    this.processTimeStddevMillis_ = visitor.visitDouble(hasProcessTimeStddevMillis(), this.processTimeStddevMillis_, other.hasProcessTimeStddevMillis(), other.processTimeStddevMillis_);
                    this.timestampJitterMeanMillis_ = visitor.visitDouble(hasTimestampJitterMeanMillis(), this.timestampJitterMeanMillis_, other.hasTimestampJitterMeanMillis(), other.timestampJitterMeanMillis_);
                    this.timestampJitterStddevMillis_ = visitor.visitDouble(hasTimestampJitterStddevMillis(), this.timestampJitterStddevMillis_, other.hasTimestampJitterStddevMillis(), other.timestampJitterStddevMillis_);
                    this.latencyMeanMillis_ = visitor.visitDouble(hasLatencyMeanMillis(), this.latencyMeanMillis_, other.hasLatencyMeanMillis(), other.latencyMeanMillis_);
                    this.latencyStddevMillis_ = visitor.visitDouble(hasLatencyStddevMillis(), this.latencyStddevMillis_, other.hasLatencyStddevMillis(), other.latencyStddevMillis_);
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
                                    this.type_ = s;
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.framecount_ = input.readInt32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.samplerate_ = input.readInt32();
                                    break;
                                case 34:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 8;
                                    this.workMillisHist_ = s2;
                                    break;
                                case 42:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 16;
                                    this.latencyMillisHist_ = s3;
                                    break;
                                case 50:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 32;
                                    this.warmupMillisHist_ = s4;
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.underruns_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.overruns_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.activeMillis_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.durationMillis_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.id_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.portId_ = input.readInt32();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.sampleRate_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.channelMask_ = input.readInt64();
                                    break;
                                case 122:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 16384;
                                    this.encoding_ = s5;
                                    break;
                                case 128:
                                    this.bitField0_ |= 32768;
                                    this.frameCount_ = input.readInt32();
                                    break;
                                case 138:
                                    String s6 = input.readString();
                                    this.bitField0_ |= 65536;
                                    this.outputDevice_ = s6;
                                    break;
                                case 146:
                                    String s7 = input.readString();
                                    this.bitField0_ |= 131072;
                                    this.inputDevice_ = s7;
                                    break;
                                case 153:
                                    this.bitField0_ |= 262144;
                                    this.ioJitterMeanMillis_ = input.readDouble();
                                    break;
                                case 161:
                                    this.bitField0_ |= 524288;
                                    this.ioJitterStddevMillis_ = input.readDouble();
                                    break;
                                case 169:
                                    this.bitField0_ |= 1048576;
                                    this.processTimeMeanMillis_ = input.readDouble();
                                    break;
                                case 177:
                                    this.bitField0_ |= 2097152;
                                    this.processTimeStddevMillis_ = input.readDouble();
                                    break;
                                case AtomsProto.Atom.SYSTEM_SERVER_WATCHDOG_OCCURRED_FIELD_NUMBER /*{ENCODED_INT: 185}*/:
                                    this.bitField0_ |= 4194304;
                                    this.timestampJitterMeanMillis_ = input.readDouble();
                                    break;
                                case AtomsProto.Atom.MEDIAMETRICS_AUDIOTHREAD_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 193}*/:
                                    this.bitField0_ |= 8388608;
                                    this.timestampJitterStddevMillis_ = input.readDouble();
                                    break;
                                case 201:
                                    this.bitField0_ |= 16777216;
                                    this.latencyMeanMillis_ = input.readDouble();
                                    break;
                                case AtomsProto.Atom.CONTENT_CAPTURE_FLUSHED_FIELD_NUMBER /*{ENCODED_INT: 209}*/:
                                    this.bitField0_ |= 33554432;
                                    this.latencyStddevMillis_ = input.readDouble();
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
                        synchronized (AudioThreadData.class) {
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

        public static AudioThreadData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AudioThreadData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AudioTrackData extends GeneratedMessageLite<AudioTrackData, Builder> implements AudioTrackDataOrBuilder {
        public static final int ATTRIBUTES_FIELD_NUMBER = 11;
        public static final int CHANNEL_MASK_FIELD_NUMBER = 5;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
        private static final AudioTrackData DEFAULT_INSTANCE = new AudioTrackData();
        public static final int ENCODING_FIELD_NUMBER = 9;
        public static final int FRAME_COUNT_FIELD_NUMBER = 10;
        private static volatile Parser<AudioTrackData> PARSER = null;
        public static final int PORT_ID_FIELD_NUMBER = 8;
        public static final int SAMPLE_RATE_FIELD_NUMBER = 4;
        public static final int STARTUP_GLITCH_FIELD_NUMBER = 7;
        public static final int STREAM_TYPE_FIELD_NUMBER = 1;
        public static final int TRACK_USAGE_FIELD_NUMBER = 3;
        public static final int UNDERRUN_FRAMES_FIELD_NUMBER = 6;
        private String attributes_ = "";
        private int bitField0_;
        private long channelMask_ = 0;
        private String contentType_ = "";
        private String encoding_ = "";
        private int frameCount_ = 0;
        private int portId_ = 0;
        private int sampleRate_ = 0;
        private int startupGlitch_ = 0;
        private String streamType_ = "";
        private String trackUsage_ = "";
        private int underrunFrames_ = 0;

        private AudioTrackData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasStreamType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public String getStreamType() {
            return this.streamType_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public ByteString getStreamTypeBytes() {
            return ByteString.copyFromUtf8(this.streamType_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStreamType(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.streamType_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStreamType() {
            this.bitField0_ &= -2;
            this.streamType_ = getDefaultInstance().getStreamType();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStreamTypeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.streamType_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasContentType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public String getContentType() {
            return this.contentType_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public ByteString getContentTypeBytes() {
            return ByteString.copyFromUtf8(this.contentType_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setContentType(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.contentType_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearContentType() {
            this.bitField0_ &= -3;
            this.contentType_ = getDefaultInstance().getContentType();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setContentTypeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.contentType_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasTrackUsage() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public String getTrackUsage() {
            return this.trackUsage_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public ByteString getTrackUsageBytes() {
            return ByteString.copyFromUtf8(this.trackUsage_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackUsage(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.trackUsage_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTrackUsage() {
            this.bitField0_ &= -5;
            this.trackUsage_ = getDefaultInstance().getTrackUsage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTrackUsageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.trackUsage_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasSampleRate() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public int getSampleRate() {
            return this.sampleRate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSampleRate(int value) {
            this.bitField0_ |= 8;
            this.sampleRate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSampleRate() {
            this.bitField0_ &= -9;
            this.sampleRate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasChannelMask() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public long getChannelMask() {
            return this.channelMask_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannelMask(long value) {
            this.bitField0_ |= 16;
            this.channelMask_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannelMask() {
            this.bitField0_ &= -17;
            this.channelMask_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasUnderrunFrames() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public int getUnderrunFrames() {
            return this.underrunFrames_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnderrunFrames(int value) {
            this.bitField0_ |= 32;
            this.underrunFrames_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnderrunFrames() {
            this.bitField0_ &= -33;
            this.underrunFrames_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasStartupGlitch() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public int getStartupGlitch() {
            return this.startupGlitch_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartupGlitch(int value) {
            this.bitField0_ |= 64;
            this.startupGlitch_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartupGlitch() {
            this.bitField0_ &= -65;
            this.startupGlitch_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasPortId() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public int getPortId() {
            return this.portId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPortId(int value) {
            this.bitField0_ |= 128;
            this.portId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPortId() {
            this.bitField0_ &= -129;
            this.portId_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasEncoding() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public String getEncoding() {
            return this.encoding_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public ByteString getEncodingBytes() {
            return ByteString.copyFromUtf8(this.encoding_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncoding(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.encoding_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEncoding() {
            this.bitField0_ &= -257;
            this.encoding_ = getDefaultInstance().getEncoding();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncodingBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.encoding_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasFrameCount() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public int getFrameCount() {
            return this.frameCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFrameCount(int value) {
            this.bitField0_ |= 512;
            this.frameCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFrameCount() {
            this.bitField0_ &= -513;
            this.frameCount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public boolean hasAttributes() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public String getAttributes() {
            return this.attributes_;
        }

        @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
        public ByteString getAttributesBytes() {
            return ByteString.copyFromUtf8(this.attributes_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAttributes(String value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.attributes_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAttributes() {
            this.bitField0_ &= -1025;
            this.attributes_ = getDefaultInstance().getAttributes();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAttributesBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.attributes_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getStreamType());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getContentType());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getTrackUsage());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.sampleRate_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.channelMask_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.underrunFrames_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.startupGlitch_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.portId_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getEncoding());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(10, this.frameCount_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeString(11, getAttributes());
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getStreamType());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getContentType());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getTrackUsage());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.sampleRate_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.channelMask_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.underrunFrames_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.startupGlitch_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.portId_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getEncoding());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(10, this.frameCount_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeStringSize(11, getAttributes());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AudioTrackData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioTrackData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioTrackData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AudioTrackData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AudioTrackData parseFrom(InputStream input) throws IOException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioTrackData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioTrackData parseDelimitedFrom(InputStream input) throws IOException {
            return (AudioTrackData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioTrackData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioTrackData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AudioTrackData parseFrom(CodedInputStream input) throws IOException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AudioTrackData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AudioTrackData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AudioTrackData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AudioTrackData, Builder> implements AudioTrackDataOrBuilder {
            private Builder() {
                super(AudioTrackData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasStreamType() {
                return ((AudioTrackData) this.instance).hasStreamType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public String getStreamType() {
                return ((AudioTrackData) this.instance).getStreamType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public ByteString getStreamTypeBytes() {
                return ((AudioTrackData) this.instance).getStreamTypeBytes();
            }

            public Builder setStreamType(String value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setStreamType(value);
                return this;
            }

            public Builder clearStreamType() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearStreamType();
                return this;
            }

            public Builder setStreamTypeBytes(ByteString value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setStreamTypeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasContentType() {
                return ((AudioTrackData) this.instance).hasContentType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public String getContentType() {
                return ((AudioTrackData) this.instance).getContentType();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public ByteString getContentTypeBytes() {
                return ((AudioTrackData) this.instance).getContentTypeBytes();
            }

            public Builder setContentType(String value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setContentType(value);
                return this;
            }

            public Builder clearContentType() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearContentType();
                return this;
            }

            public Builder setContentTypeBytes(ByteString value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setContentTypeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasTrackUsage() {
                return ((AudioTrackData) this.instance).hasTrackUsage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public String getTrackUsage() {
                return ((AudioTrackData) this.instance).getTrackUsage();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public ByteString getTrackUsageBytes() {
                return ((AudioTrackData) this.instance).getTrackUsageBytes();
            }

            public Builder setTrackUsage(String value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setTrackUsage(value);
                return this;
            }

            public Builder clearTrackUsage() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearTrackUsage();
                return this;
            }

            public Builder setTrackUsageBytes(ByteString value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setTrackUsageBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasSampleRate() {
                return ((AudioTrackData) this.instance).hasSampleRate();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public int getSampleRate() {
                return ((AudioTrackData) this.instance).getSampleRate();
            }

            public Builder setSampleRate(int value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setSampleRate(value);
                return this;
            }

            public Builder clearSampleRate() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearSampleRate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasChannelMask() {
                return ((AudioTrackData) this.instance).hasChannelMask();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public long getChannelMask() {
                return ((AudioTrackData) this.instance).getChannelMask();
            }

            public Builder setChannelMask(long value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setChannelMask(value);
                return this;
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearChannelMask();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasUnderrunFrames() {
                return ((AudioTrackData) this.instance).hasUnderrunFrames();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public int getUnderrunFrames() {
                return ((AudioTrackData) this.instance).getUnderrunFrames();
            }

            public Builder setUnderrunFrames(int value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setUnderrunFrames(value);
                return this;
            }

            public Builder clearUnderrunFrames() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearUnderrunFrames();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasStartupGlitch() {
                return ((AudioTrackData) this.instance).hasStartupGlitch();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public int getStartupGlitch() {
                return ((AudioTrackData) this.instance).getStartupGlitch();
            }

            public Builder setStartupGlitch(int value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setStartupGlitch(value);
                return this;
            }

            public Builder clearStartupGlitch() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearStartupGlitch();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasPortId() {
                return ((AudioTrackData) this.instance).hasPortId();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public int getPortId() {
                return ((AudioTrackData) this.instance).getPortId();
            }

            public Builder setPortId(int value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setPortId(value);
                return this;
            }

            public Builder clearPortId() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearPortId();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasEncoding() {
                return ((AudioTrackData) this.instance).hasEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public String getEncoding() {
                return ((AudioTrackData) this.instance).getEncoding();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public ByteString getEncodingBytes() {
                return ((AudioTrackData) this.instance).getEncodingBytes();
            }

            public Builder setEncoding(String value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setEncoding(value);
                return this;
            }

            public Builder clearEncoding() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearEncoding();
                return this;
            }

            public Builder setEncodingBytes(ByteString value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setEncodingBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasFrameCount() {
                return ((AudioTrackData) this.instance).hasFrameCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public int getFrameCount() {
                return ((AudioTrackData) this.instance).getFrameCount();
            }

            public Builder setFrameCount(int value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setFrameCount(value);
                return this;
            }

            public Builder clearFrameCount() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearFrameCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public boolean hasAttributes() {
                return ((AudioTrackData) this.instance).hasAttributes();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public String getAttributes() {
                return ((AudioTrackData) this.instance).getAttributes();
            }

            @Override // android.stats.mediametrics.Mediametrics.AudioTrackDataOrBuilder
            public ByteString getAttributesBytes() {
                return ((AudioTrackData) this.instance).getAttributesBytes();
            }

            public Builder setAttributes(String value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setAttributes(value);
                return this;
            }

            public Builder clearAttributes() {
                copyOnWrite();
                ((AudioTrackData) this.instance).clearAttributes();
                return this;
            }

            public Builder setAttributesBytes(ByteString value) {
                copyOnWrite();
                ((AudioTrackData) this.instance).setAttributesBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AudioTrackData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AudioTrackData other = (AudioTrackData) arg1;
                    this.streamType_ = visitor.visitString(hasStreamType(), this.streamType_, other.hasStreamType(), other.streamType_);
                    this.contentType_ = visitor.visitString(hasContentType(), this.contentType_, other.hasContentType(), other.contentType_);
                    this.trackUsage_ = visitor.visitString(hasTrackUsage(), this.trackUsage_, other.hasTrackUsage(), other.trackUsage_);
                    this.sampleRate_ = visitor.visitInt(hasSampleRate(), this.sampleRate_, other.hasSampleRate(), other.sampleRate_);
                    this.channelMask_ = visitor.visitLong(hasChannelMask(), this.channelMask_, other.hasChannelMask(), other.channelMask_);
                    this.underrunFrames_ = visitor.visitInt(hasUnderrunFrames(), this.underrunFrames_, other.hasUnderrunFrames(), other.underrunFrames_);
                    this.startupGlitch_ = visitor.visitInt(hasStartupGlitch(), this.startupGlitch_, other.hasStartupGlitch(), other.startupGlitch_);
                    this.portId_ = visitor.visitInt(hasPortId(), this.portId_, other.hasPortId(), other.portId_);
                    this.encoding_ = visitor.visitString(hasEncoding(), this.encoding_, other.hasEncoding(), other.encoding_);
                    this.frameCount_ = visitor.visitInt(hasFrameCount(), this.frameCount_, other.hasFrameCount(), other.frameCount_);
                    this.attributes_ = visitor.visitString(hasAttributes(), this.attributes_, other.hasAttributes(), other.attributes_);
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
                                    this.streamType_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.contentType_ = s2;
                                    break;
                                case 26:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 4;
                                    this.trackUsage_ = s3;
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.sampleRate_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.channelMask_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.underrunFrames_ = input.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.startupGlitch_ = input.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.portId_ = input.readInt32();
                                    break;
                                case 74:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.encoding_ = s4;
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.frameCount_ = input.readInt32();
                                    break;
                                case 90:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 1024;
                                    this.attributes_ = s5;
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
                        synchronized (AudioTrackData.class) {
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

        public static AudioTrackData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AudioTrackData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class CodecData extends GeneratedMessageLite<CodecData, Builder> implements CodecDataOrBuilder {
        public static final int CODEC_FIELD_NUMBER = 1;
        public static final int CRYPTO_FIELD_NUMBER = 9;
        private static final CodecData DEFAULT_INSTANCE = new CodecData();
        public static final int ENCODER_FIELD_NUMBER = 4;
        public static final int ERROR_CODE_FIELD_NUMBER = 14;
        public static final int ERROR_STATE_FIELD_NUMBER = 15;
        public static final int HEIGHT_FIELD_NUMBER = 7;
        public static final int LATENCY_AVG_FIELD_NUMBER = 18;
        public static final int LATENCY_COUNT_FIELD_NUMBER = 19;
        public static final int LATENCY_MAX_FIELD_NUMBER = 16;
        public static final int LATENCY_MIN_FIELD_NUMBER = 17;
        public static final int LATENCY_UNKNOWN_FIELD_NUMBER = 20;
        public static final int LEVEL_FIELD_NUMBER = 11;
        public static final int MAX_HEIGHT_FIELD_NUMBER = 13;
        public static final int MAX_WIDTH_FIELD_NUMBER = 12;
        public static final int MIME_FIELD_NUMBER = 2;
        public static final int MODE_FIELD_NUMBER = 3;
        private static volatile Parser<CodecData> PARSER = null;
        public static final int PROFILE_FIELD_NUMBER = 10;
        public static final int ROTATION_FIELD_NUMBER = 8;
        public static final int SECURE_FIELD_NUMBER = 5;
        public static final int WIDTH_FIELD_NUMBER = 6;
        private int bitField0_;
        private String codec_ = "";
        private int crypto_ = 0;
        private int encoder_ = 0;
        private int errorCode_ = 0;
        private String errorState_ = "";
        private int height_ = 0;
        private long latencyAvg_ = 0;
        private long latencyCount_ = 0;
        private long latencyMax_ = 0;
        private long latencyMin_ = 0;
        private long latencyUnknown_ = 0;
        private int level_ = 0;
        private int maxHeight_ = 0;
        private int maxWidth_ = 0;
        private String mime_ = "";
        private String mode_ = "";
        private int profile_ = 0;
        private int rotation_ = 0;
        private int secure_ = 0;
        private int width_ = 0;

        private CodecData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasCodec() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public String getCodec() {
            return this.codec_;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public ByteString getCodecBytes() {
            return ByteString.copyFromUtf8(this.codec_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCodec(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.codec_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCodec() {
            this.bitField0_ &= -2;
            this.codec_ = getDefaultInstance().getCodec();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCodecBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.codec_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasMime() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public String getMime() {
            return this.mime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public ByteString getMimeBytes() {
            return ByteString.copyFromUtf8(this.mime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMime(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.mime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMime() {
            this.bitField0_ &= -3;
            this.mime_ = getDefaultInstance().getMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.mime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasMode() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public String getMode() {
            return this.mode_;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public ByteString getModeBytes() {
            return ByteString.copyFromUtf8(this.mode_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMode(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.mode_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMode() {
            this.bitField0_ &= -5;
            this.mode_ = getDefaultInstance().getMode();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setModeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.mode_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasEncoder() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getEncoder() {
            return this.encoder_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEncoder(int value) {
            this.bitField0_ |= 8;
            this.encoder_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEncoder() {
            this.bitField0_ &= -9;
            this.encoder_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasSecure() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getSecure() {
            return this.secure_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSecure(int value) {
            this.bitField0_ |= 16;
            this.secure_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSecure() {
            this.bitField0_ &= -17;
            this.secure_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasWidth() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getWidth() {
            return this.width_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWidth(int value) {
            this.bitField0_ |= 32;
            this.width_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWidth() {
            this.bitField0_ &= -33;
            this.width_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasHeight() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getHeight() {
            return this.height_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHeight(int value) {
            this.bitField0_ |= 64;
            this.height_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHeight() {
            this.bitField0_ &= -65;
            this.height_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasRotation() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getRotation() {
            return this.rotation_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRotation(int value) {
            this.bitField0_ |= 128;
            this.rotation_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRotation() {
            this.bitField0_ &= -129;
            this.rotation_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasCrypto() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getCrypto() {
            return this.crypto_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCrypto(int value) {
            this.bitField0_ |= 256;
            this.crypto_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCrypto() {
            this.bitField0_ &= -257;
            this.crypto_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasProfile() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getProfile() {
            return this.profile_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProfile(int value) {
            this.bitField0_ |= 512;
            this.profile_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProfile() {
            this.bitField0_ &= -513;
            this.profile_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLevel() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getLevel() {
            return this.level_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLevel(int value) {
            this.bitField0_ |= 1024;
            this.level_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLevel() {
            this.bitField0_ &= -1025;
            this.level_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasMaxWidth() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getMaxWidth() {
            return this.maxWidth_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxWidth(int value) {
            this.bitField0_ |= 2048;
            this.maxWidth_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxWidth() {
            this.bitField0_ &= -2049;
            this.maxWidth_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasMaxHeight() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getMaxHeight() {
            return this.maxHeight_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxHeight(int value) {
            this.bitField0_ |= 4096;
            this.maxHeight_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxHeight() {
            this.bitField0_ &= -4097;
            this.maxHeight_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasErrorCode() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorCode(int value) {
            this.bitField0_ |= 8192;
            this.errorCode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorCode() {
            this.bitField0_ &= -8193;
            this.errorCode_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasErrorState() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public String getErrorState() {
            return this.errorState_;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public ByteString getErrorStateBytes() {
            return ByteString.copyFromUtf8(this.errorState_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorState(String value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.errorState_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorState() {
            this.bitField0_ &= -16385;
            this.errorState_ = getDefaultInstance().getErrorState();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorStateBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.errorState_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLatencyMax() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public long getLatencyMax() {
            return this.latencyMax_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMax(long value) {
            this.bitField0_ |= 32768;
            this.latencyMax_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyMax() {
            this.bitField0_ &= -32769;
            this.latencyMax_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLatencyMin() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public long getLatencyMin() {
            return this.latencyMin_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMin(long value) {
            this.bitField0_ |= 65536;
            this.latencyMin_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyMin() {
            this.bitField0_ &= -65537;
            this.latencyMin_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLatencyAvg() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public long getLatencyAvg() {
            return this.latencyAvg_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyAvg(long value) {
            this.bitField0_ |= 131072;
            this.latencyAvg_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyAvg() {
            this.bitField0_ &= -131073;
            this.latencyAvg_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLatencyCount() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public long getLatencyCount() {
            return this.latencyCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyCount(long value) {
            this.bitField0_ |= 262144;
            this.latencyCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyCount() {
            this.bitField0_ &= -262145;
            this.latencyCount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public boolean hasLatencyUnknown() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
        public long getLatencyUnknown() {
            return this.latencyUnknown_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyUnknown(long value) {
            this.bitField0_ |= 524288;
            this.latencyUnknown_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyUnknown() {
            this.bitField0_ &= -524289;
            this.latencyUnknown_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getCodec());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getMode());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.encoder_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.secure_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.width_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.height_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.rotation_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.crypto_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(10, this.profile_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(11, this.level_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(12, this.maxWidth_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.maxHeight_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(14, this.errorCode_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeString(15, getErrorState());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt64(16, this.latencyMax_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt64(17, this.latencyMin_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt64(18, this.latencyAvg_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt64(19, this.latencyCount_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt64(20, this.latencyUnknown_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getCodec());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getMode());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.encoder_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.secure_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.width_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.height_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.rotation_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.crypto_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(10, this.profile_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.level_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(12, this.maxWidth_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.maxHeight_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(14, this.errorCode_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeStringSize(15, getErrorState());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt64Size(16, this.latencyMax_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt64Size(17, this.latencyMin_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt64Size(18, this.latencyAvg_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt64Size(19, this.latencyCount_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt64Size(20, this.latencyUnknown_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static CodecData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodecData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodecData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodecData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodecData parseFrom(InputStream input) throws IOException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodecData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodecData parseDelimitedFrom(InputStream input) throws IOException {
            return (CodecData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CodecData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodecData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodecData parseFrom(CodedInputStream input) throws IOException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodecData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodecData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CodecData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CodecData, Builder> implements CodecDataOrBuilder {
            private Builder() {
                super(CodecData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasCodec() {
                return ((CodecData) this.instance).hasCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public String getCodec() {
                return ((CodecData) this.instance).getCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public ByteString getCodecBytes() {
                return ((CodecData) this.instance).getCodecBytes();
            }

            public Builder setCodec(String value) {
                copyOnWrite();
                ((CodecData) this.instance).setCodec(value);
                return this;
            }

            public Builder clearCodec() {
                copyOnWrite();
                ((CodecData) this.instance).clearCodec();
                return this;
            }

            public Builder setCodecBytes(ByteString value) {
                copyOnWrite();
                ((CodecData) this.instance).setCodecBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasMime() {
                return ((CodecData) this.instance).hasMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public String getMime() {
                return ((CodecData) this.instance).getMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public ByteString getMimeBytes() {
                return ((CodecData) this.instance).getMimeBytes();
            }

            public Builder setMime(String value) {
                copyOnWrite();
                ((CodecData) this.instance).setMime(value);
                return this;
            }

            public Builder clearMime() {
                copyOnWrite();
                ((CodecData) this.instance).clearMime();
                return this;
            }

            public Builder setMimeBytes(ByteString value) {
                copyOnWrite();
                ((CodecData) this.instance).setMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasMode() {
                return ((CodecData) this.instance).hasMode();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public String getMode() {
                return ((CodecData) this.instance).getMode();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public ByteString getModeBytes() {
                return ((CodecData) this.instance).getModeBytes();
            }

            public Builder setMode(String value) {
                copyOnWrite();
                ((CodecData) this.instance).setMode(value);
                return this;
            }

            public Builder clearMode() {
                copyOnWrite();
                ((CodecData) this.instance).clearMode();
                return this;
            }

            public Builder setModeBytes(ByteString value) {
                copyOnWrite();
                ((CodecData) this.instance).setModeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasEncoder() {
                return ((CodecData) this.instance).hasEncoder();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getEncoder() {
                return ((CodecData) this.instance).getEncoder();
            }

            public Builder setEncoder(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setEncoder(value);
                return this;
            }

            public Builder clearEncoder() {
                copyOnWrite();
                ((CodecData) this.instance).clearEncoder();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasSecure() {
                return ((CodecData) this.instance).hasSecure();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getSecure() {
                return ((CodecData) this.instance).getSecure();
            }

            public Builder setSecure(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setSecure(value);
                return this;
            }

            public Builder clearSecure() {
                copyOnWrite();
                ((CodecData) this.instance).clearSecure();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasWidth() {
                return ((CodecData) this.instance).hasWidth();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getWidth() {
                return ((CodecData) this.instance).getWidth();
            }

            public Builder setWidth(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setWidth(value);
                return this;
            }

            public Builder clearWidth() {
                copyOnWrite();
                ((CodecData) this.instance).clearWidth();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasHeight() {
                return ((CodecData) this.instance).hasHeight();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getHeight() {
                return ((CodecData) this.instance).getHeight();
            }

            public Builder setHeight(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setHeight(value);
                return this;
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((CodecData) this.instance).clearHeight();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasRotation() {
                return ((CodecData) this.instance).hasRotation();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getRotation() {
                return ((CodecData) this.instance).getRotation();
            }

            public Builder setRotation(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setRotation(value);
                return this;
            }

            public Builder clearRotation() {
                copyOnWrite();
                ((CodecData) this.instance).clearRotation();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasCrypto() {
                return ((CodecData) this.instance).hasCrypto();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getCrypto() {
                return ((CodecData) this.instance).getCrypto();
            }

            public Builder setCrypto(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setCrypto(value);
                return this;
            }

            public Builder clearCrypto() {
                copyOnWrite();
                ((CodecData) this.instance).clearCrypto();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasProfile() {
                return ((CodecData) this.instance).hasProfile();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getProfile() {
                return ((CodecData) this.instance).getProfile();
            }

            public Builder setProfile(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setProfile(value);
                return this;
            }

            public Builder clearProfile() {
                copyOnWrite();
                ((CodecData) this.instance).clearProfile();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLevel() {
                return ((CodecData) this.instance).hasLevel();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getLevel() {
                return ((CodecData) this.instance).getLevel();
            }

            public Builder setLevel(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setLevel(value);
                return this;
            }

            public Builder clearLevel() {
                copyOnWrite();
                ((CodecData) this.instance).clearLevel();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasMaxWidth() {
                return ((CodecData) this.instance).hasMaxWidth();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getMaxWidth() {
                return ((CodecData) this.instance).getMaxWidth();
            }

            public Builder setMaxWidth(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setMaxWidth(value);
                return this;
            }

            public Builder clearMaxWidth() {
                copyOnWrite();
                ((CodecData) this.instance).clearMaxWidth();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasMaxHeight() {
                return ((CodecData) this.instance).hasMaxHeight();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getMaxHeight() {
                return ((CodecData) this.instance).getMaxHeight();
            }

            public Builder setMaxHeight(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setMaxHeight(value);
                return this;
            }

            public Builder clearMaxHeight() {
                copyOnWrite();
                ((CodecData) this.instance).clearMaxHeight();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasErrorCode() {
                return ((CodecData) this.instance).hasErrorCode();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public int getErrorCode() {
                return ((CodecData) this.instance).getErrorCode();
            }

            public Builder setErrorCode(int value) {
                copyOnWrite();
                ((CodecData) this.instance).setErrorCode(value);
                return this;
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((CodecData) this.instance).clearErrorCode();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasErrorState() {
                return ((CodecData) this.instance).hasErrorState();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public String getErrorState() {
                return ((CodecData) this.instance).getErrorState();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public ByteString getErrorStateBytes() {
                return ((CodecData) this.instance).getErrorStateBytes();
            }

            public Builder setErrorState(String value) {
                copyOnWrite();
                ((CodecData) this.instance).setErrorState(value);
                return this;
            }

            public Builder clearErrorState() {
                copyOnWrite();
                ((CodecData) this.instance).clearErrorState();
                return this;
            }

            public Builder setErrorStateBytes(ByteString value) {
                copyOnWrite();
                ((CodecData) this.instance).setErrorStateBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLatencyMax() {
                return ((CodecData) this.instance).hasLatencyMax();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public long getLatencyMax() {
                return ((CodecData) this.instance).getLatencyMax();
            }

            public Builder setLatencyMax(long value) {
                copyOnWrite();
                ((CodecData) this.instance).setLatencyMax(value);
                return this;
            }

            public Builder clearLatencyMax() {
                copyOnWrite();
                ((CodecData) this.instance).clearLatencyMax();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLatencyMin() {
                return ((CodecData) this.instance).hasLatencyMin();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public long getLatencyMin() {
                return ((CodecData) this.instance).getLatencyMin();
            }

            public Builder setLatencyMin(long value) {
                copyOnWrite();
                ((CodecData) this.instance).setLatencyMin(value);
                return this;
            }

            public Builder clearLatencyMin() {
                copyOnWrite();
                ((CodecData) this.instance).clearLatencyMin();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLatencyAvg() {
                return ((CodecData) this.instance).hasLatencyAvg();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public long getLatencyAvg() {
                return ((CodecData) this.instance).getLatencyAvg();
            }

            public Builder setLatencyAvg(long value) {
                copyOnWrite();
                ((CodecData) this.instance).setLatencyAvg(value);
                return this;
            }

            public Builder clearLatencyAvg() {
                copyOnWrite();
                ((CodecData) this.instance).clearLatencyAvg();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLatencyCount() {
                return ((CodecData) this.instance).hasLatencyCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public long getLatencyCount() {
                return ((CodecData) this.instance).getLatencyCount();
            }

            public Builder setLatencyCount(long value) {
                copyOnWrite();
                ((CodecData) this.instance).setLatencyCount(value);
                return this;
            }

            public Builder clearLatencyCount() {
                copyOnWrite();
                ((CodecData) this.instance).clearLatencyCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public boolean hasLatencyUnknown() {
                return ((CodecData) this.instance).hasLatencyUnknown();
            }

            @Override // android.stats.mediametrics.Mediametrics.CodecDataOrBuilder
            public long getLatencyUnknown() {
                return ((CodecData) this.instance).getLatencyUnknown();
            }

            public Builder setLatencyUnknown(long value) {
                copyOnWrite();
                ((CodecData) this.instance).setLatencyUnknown(value);
                return this;
            }

            public Builder clearLatencyUnknown() {
                copyOnWrite();
                ((CodecData) this.instance).clearLatencyUnknown();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CodecData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CodecData other = (CodecData) arg1;
                    this.codec_ = visitor.visitString(hasCodec(), this.codec_, other.hasCodec(), other.codec_);
                    this.mime_ = visitor.visitString(hasMime(), this.mime_, other.hasMime(), other.mime_);
                    this.mode_ = visitor.visitString(hasMode(), this.mode_, other.hasMode(), other.mode_);
                    this.encoder_ = visitor.visitInt(hasEncoder(), this.encoder_, other.hasEncoder(), other.encoder_);
                    this.secure_ = visitor.visitInt(hasSecure(), this.secure_, other.hasSecure(), other.secure_);
                    this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                    this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                    this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
                    this.crypto_ = visitor.visitInt(hasCrypto(), this.crypto_, other.hasCrypto(), other.crypto_);
                    this.profile_ = visitor.visitInt(hasProfile(), this.profile_, other.hasProfile(), other.profile_);
                    this.level_ = visitor.visitInt(hasLevel(), this.level_, other.hasLevel(), other.level_);
                    this.maxWidth_ = visitor.visitInt(hasMaxWidth(), this.maxWidth_, other.hasMaxWidth(), other.maxWidth_);
                    this.maxHeight_ = visitor.visitInt(hasMaxHeight(), this.maxHeight_, other.hasMaxHeight(), other.maxHeight_);
                    this.errorCode_ = visitor.visitInt(hasErrorCode(), this.errorCode_, other.hasErrorCode(), other.errorCode_);
                    this.errorState_ = visitor.visitString(hasErrorState(), this.errorState_, other.hasErrorState(), other.errorState_);
                    this.latencyMax_ = visitor.visitLong(hasLatencyMax(), this.latencyMax_, other.hasLatencyMax(), other.latencyMax_);
                    this.latencyMin_ = visitor.visitLong(hasLatencyMin(), this.latencyMin_, other.hasLatencyMin(), other.latencyMin_);
                    this.latencyAvg_ = visitor.visitLong(hasLatencyAvg(), this.latencyAvg_, other.hasLatencyAvg(), other.latencyAvg_);
                    this.latencyCount_ = visitor.visitLong(hasLatencyCount(), this.latencyCount_, other.hasLatencyCount(), other.latencyCount_);
                    this.latencyUnknown_ = visitor.visitLong(hasLatencyUnknown(), this.latencyUnknown_, other.hasLatencyUnknown(), other.latencyUnknown_);
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
                                    this.codec_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.mime_ = s2;
                                    break;
                                case 26:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 4;
                                    this.mode_ = s3;
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.encoder_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.secure_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.width_ = input.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.height_ = input.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.rotation_ = input.readInt32();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.crypto_ = input.readInt32();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.profile_ = input.readInt32();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.level_ = input.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.maxWidth_ = input.readInt32();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.maxHeight_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.errorCode_ = input.readInt32();
                                    break;
                                case 122:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 16384;
                                    this.errorState_ = s4;
                                    break;
                                case 128:
                                    this.bitField0_ |= 32768;
                                    this.latencyMax_ = input.readInt64();
                                    break;
                                case 136:
                                    this.bitField0_ |= 65536;
                                    this.latencyMin_ = input.readInt64();
                                    break;
                                case 144:
                                    this.bitField0_ |= 131072;
                                    this.latencyAvg_ = input.readInt64();
                                    break;
                                case 152:
                                    this.bitField0_ |= 262144;
                                    this.latencyCount_ = input.readInt64();
                                    break;
                                case 160:
                                    this.bitField0_ |= 524288;
                                    this.latencyUnknown_ = input.readInt64();
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
                        synchronized (CodecData.class) {
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

        public static CodecData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CodecData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ExtractorData extends GeneratedMessageLite<ExtractorData, Builder> implements ExtractorDataOrBuilder {
        private static final ExtractorData DEFAULT_INSTANCE = new ExtractorData();
        public static final int FORMAT_FIELD_NUMBER = 1;
        public static final int MIME_FIELD_NUMBER = 2;
        private static volatile Parser<ExtractorData> PARSER = null;
        public static final int TRACKS_FIELD_NUMBER = 3;
        private int bitField0_;
        private String format_ = "";
        private String mime_ = "";
        private int tracks_ = 0;

        private ExtractorData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public boolean hasFormat() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public String getFormat() {
            return this.format_;
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public ByteString getFormatBytes() {
            return ByteString.copyFromUtf8(this.format_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFormat(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.format_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFormat() {
            this.bitField0_ &= -2;
            this.format_ = getDefaultInstance().getFormat();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFormatBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.format_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public boolean hasMime() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public String getMime() {
            return this.mime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public ByteString getMimeBytes() {
            return ByteString.copyFromUtf8(this.mime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMime(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.mime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMime() {
            this.bitField0_ &= -3;
            this.mime_ = getDefaultInstance().getMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.mime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public boolean hasTracks() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
        public int getTracks() {
            return this.tracks_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTracks(int value) {
            this.bitField0_ |= 4;
            this.tracks_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTracks() {
            this.bitField0_ &= -5;
            this.tracks_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getFormat());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.tracks_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getFormat());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.tracks_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ExtractorData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExtractorData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExtractorData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExtractorData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExtractorData parseFrom(InputStream input) throws IOException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExtractorData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExtractorData parseDelimitedFrom(InputStream input) throws IOException {
            return (ExtractorData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ExtractorData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExtractorData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExtractorData parseFrom(CodedInputStream input) throws IOException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExtractorData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExtractorData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExtractorData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ExtractorData, Builder> implements ExtractorDataOrBuilder {
            private Builder() {
                super(ExtractorData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public boolean hasFormat() {
                return ((ExtractorData) this.instance).hasFormat();
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public String getFormat() {
                return ((ExtractorData) this.instance).getFormat();
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public ByteString getFormatBytes() {
                return ((ExtractorData) this.instance).getFormatBytes();
            }

            public Builder setFormat(String value) {
                copyOnWrite();
                ((ExtractorData) this.instance).setFormat(value);
                return this;
            }

            public Builder clearFormat() {
                copyOnWrite();
                ((ExtractorData) this.instance).clearFormat();
                return this;
            }

            public Builder setFormatBytes(ByteString value) {
                copyOnWrite();
                ((ExtractorData) this.instance).setFormatBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public boolean hasMime() {
                return ((ExtractorData) this.instance).hasMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public String getMime() {
                return ((ExtractorData) this.instance).getMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public ByteString getMimeBytes() {
                return ((ExtractorData) this.instance).getMimeBytes();
            }

            public Builder setMime(String value) {
                copyOnWrite();
                ((ExtractorData) this.instance).setMime(value);
                return this;
            }

            public Builder clearMime() {
                copyOnWrite();
                ((ExtractorData) this.instance).clearMime();
                return this;
            }

            public Builder setMimeBytes(ByteString value) {
                copyOnWrite();
                ((ExtractorData) this.instance).setMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public boolean hasTracks() {
                return ((ExtractorData) this.instance).hasTracks();
            }

            @Override // android.stats.mediametrics.Mediametrics.ExtractorDataOrBuilder
            public int getTracks() {
                return ((ExtractorData) this.instance).getTracks();
            }

            public Builder setTracks(int value) {
                copyOnWrite();
                ((ExtractorData) this.instance).setTracks(value);
                return this;
            }

            public Builder clearTracks() {
                copyOnWrite();
                ((ExtractorData) this.instance).clearTracks();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ExtractorData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ExtractorData other = (ExtractorData) arg1;
                    this.format_ = visitor.visitString(hasFormat(), this.format_, other.hasFormat(), other.format_);
                    this.mime_ = visitor.visitString(hasMime(), this.mime_, other.hasMime(), other.mime_);
                    this.tracks_ = visitor.visitInt(hasTracks(), this.tracks_, other.hasTracks(), other.tracks_);
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
                                this.format_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.mime_ = s2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.tracks_ = input.readInt32();
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
                        synchronized (ExtractorData.class) {
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

        public static ExtractorData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExtractorData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class NuPlayerData extends GeneratedMessageLite<NuPlayerData, Builder> implements NuPlayerDataOrBuilder {
        public static final int AUDIO_CODEC_FIELD_NUMBER = 10;
        public static final int AUDIO_MIME_FIELD_NUMBER = 9;
        public static final int DATA_SOURCE_TYPE_FIELD_NUMBER = 16;
        private static final NuPlayerData DEFAULT_INSTANCE = new NuPlayerData();
        public static final int DURATION_MILLIS_FIELD_NUMBER = 11;
        public static final int ERROR_CODE_FIELD_NUMBER = 14;
        public static final int ERROR_FIELD_NUMBER = 13;
        public static final int ERROR_STATE_FIELD_NUMBER = 15;
        public static final int FRAMERATE_FIELD_NUMBER = 8;
        public static final int FRAMES_DROPPED_FIELD_NUMBER = 7;
        public static final int FRAMES_DROPPED_STARTUP_FIELD_NUMBER = 20;
        public static final int FRAMES_FIELD_NUMBER = 6;
        public static final int HEIGHT_FIELD_NUMBER = 5;
        private static volatile Parser<NuPlayerData> PARSER = null;
        public static final int PLAYING_MILLIS_FIELD_NUMBER = 12;
        public static final int REBUFFERING_MILLIS_FIELD_NUMBER = 17;
        public static final int REBUFFERS_FIELD_NUMBER = 18;
        public static final int REBUFFER_AT_EXIT_FIELD_NUMBER = 19;
        public static final int VIDEO_CODEC_FIELD_NUMBER = 3;
        public static final int VIDEO_MIME_FIELD_NUMBER = 2;
        public static final int WHICHPLAYER_FIELD_NUMBER = 1;
        public static final int WIDTH_FIELD_NUMBER = 4;
        private String audioCodec_ = "";
        private String audioMime_ = "";
        private int bitField0_;
        private String dataSourceType_ = "";
        private long durationMillis_ = 0;
        private int errorCode_ = 0;
        private String errorState_ = "";
        private int error_ = 0;
        private double framerate_ = 0.0d;
        private long framesDroppedStartup_ = 0;
        private long framesDropped_ = 0;
        private long frames_ = 0;
        private int height_ = 0;
        private long playingMillis_ = 0;
        private int rebufferAtExit_ = 0;
        private long rebufferingMillis_ = 0;
        private int rebuffers_ = 0;
        private String videoCodec_ = "";
        private String videoMime_ = "";
        private String whichPlayer_ = "";
        private int width_ = 0;

        private NuPlayerData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasWhichPlayer() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getWhichPlayer() {
            return this.whichPlayer_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getWhichPlayerBytes() {
            return ByteString.copyFromUtf8(this.whichPlayer_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhichPlayer(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.whichPlayer_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhichPlayer() {
            this.bitField0_ &= -2;
            this.whichPlayer_ = getDefaultInstance().getWhichPlayer();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhichPlayerBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.whichPlayer_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasVideoMime() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getVideoMime() {
            return this.videoMime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getVideoMimeBytes() {
            return ByteString.copyFromUtf8(this.videoMime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoMime(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.videoMime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoMime() {
            this.bitField0_ &= -3;
            this.videoMime_ = getDefaultInstance().getVideoMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.videoMime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasVideoCodec() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getVideoCodec() {
            return this.videoCodec_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getVideoCodecBytes() {
            return ByteString.copyFromUtf8(this.videoCodec_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoCodec(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.videoCodec_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoCodec() {
            this.bitField0_ &= -5;
            this.videoCodec_ = getDefaultInstance().getVideoCodec();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoCodecBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.videoCodec_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasWidth() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getWidth() {
            return this.width_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWidth(int value) {
            this.bitField0_ |= 8;
            this.width_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWidth() {
            this.bitField0_ &= -9;
            this.width_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasHeight() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getHeight() {
            return this.height_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHeight(int value) {
            this.bitField0_ |= 16;
            this.height_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHeight() {
            this.bitField0_ &= -17;
            this.height_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasFrames() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getFrames() {
            return this.frames_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFrames(long value) {
            this.bitField0_ |= 32;
            this.frames_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFrames() {
            this.bitField0_ &= -33;
            this.frames_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasFramesDropped() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getFramesDropped() {
            return this.framesDropped_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFramesDropped(long value) {
            this.bitField0_ |= 64;
            this.framesDropped_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFramesDropped() {
            this.bitField0_ &= -65;
            this.framesDropped_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasFramerate() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public double getFramerate() {
            return this.framerate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFramerate(double value) {
            this.bitField0_ |= 128;
            this.framerate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFramerate() {
            this.bitField0_ &= -129;
            this.framerate_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasAudioMime() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getAudioMime() {
            return this.audioMime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getAudioMimeBytes() {
            return ByteString.copyFromUtf8(this.audioMime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioMime(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.audioMime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioMime() {
            this.bitField0_ &= -257;
            this.audioMime_ = getDefaultInstance().getAudioMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.audioMime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasAudioCodec() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getAudioCodec() {
            return this.audioCodec_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getAudioCodecBytes() {
            return ByteString.copyFromUtf8(this.audioCodec_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioCodec(String value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.audioCodec_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioCodec() {
            this.bitField0_ &= -513;
            this.audioCodec_ = getDefaultInstance().getAudioCodec();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioCodecBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.audioCodec_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasDurationMillis() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getDurationMillis() {
            return this.durationMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMillis(long value) {
            this.bitField0_ |= 1024;
            this.durationMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMillis() {
            this.bitField0_ &= -1025;
            this.durationMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasPlayingMillis() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getPlayingMillis() {
            return this.playingMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPlayingMillis(long value) {
            this.bitField0_ |= 2048;
            this.playingMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPlayingMillis() {
            this.bitField0_ &= -2049;
            this.playingMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasError() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getError() {
            return this.error_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setError(int value) {
            this.bitField0_ |= 4096;
            this.error_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearError() {
            this.bitField0_ &= -4097;
            this.error_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasErrorCode() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorCode(int value) {
            this.bitField0_ |= 8192;
            this.errorCode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorCode() {
            this.bitField0_ &= -8193;
            this.errorCode_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasErrorState() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getErrorState() {
            return this.errorState_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getErrorStateBytes() {
            return ByteString.copyFromUtf8(this.errorState_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorState(String value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.errorState_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorState() {
            this.bitField0_ &= -16385;
            this.errorState_ = getDefaultInstance().getErrorState();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorStateBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16384;
                this.errorState_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasDataSourceType() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public String getDataSourceType() {
            return this.dataSourceType_;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public ByteString getDataSourceTypeBytes() {
            return ByteString.copyFromUtf8(this.dataSourceType_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDataSourceType(String value) {
            if (value != null) {
                this.bitField0_ |= 32768;
                this.dataSourceType_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDataSourceType() {
            this.bitField0_ &= -32769;
            this.dataSourceType_ = getDefaultInstance().getDataSourceType();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDataSourceTypeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 32768;
                this.dataSourceType_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasRebufferingMillis() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getRebufferingMillis() {
            return this.rebufferingMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRebufferingMillis(long value) {
            this.bitField0_ |= 65536;
            this.rebufferingMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRebufferingMillis() {
            this.bitField0_ &= -65537;
            this.rebufferingMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasRebuffers() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getRebuffers() {
            return this.rebuffers_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRebuffers(int value) {
            this.bitField0_ |= 131072;
            this.rebuffers_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRebuffers() {
            this.bitField0_ &= -131073;
            this.rebuffers_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasRebufferAtExit() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public int getRebufferAtExit() {
            return this.rebufferAtExit_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRebufferAtExit(int value) {
            this.bitField0_ |= 262144;
            this.rebufferAtExit_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRebufferAtExit() {
            this.bitField0_ &= -262145;
            this.rebufferAtExit_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public boolean hasFramesDroppedStartup() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
        public long getFramesDroppedStartup() {
            return this.framesDroppedStartup_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFramesDroppedStartup(long value) {
            this.bitField0_ |= 524288;
            this.framesDroppedStartup_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFramesDroppedStartup() {
            this.bitField0_ &= -524289;
            this.framesDroppedStartup_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getWhichPlayer());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getVideoMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getVideoCodec());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.width_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.height_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.frames_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.framesDropped_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeDouble(8, this.framerate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getAudioMime());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeString(10, getAudioCodec());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(11, this.durationMillis_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt64(12, this.playingMillis_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.error_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(14, this.errorCode_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeString(15, getErrorState());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeString(16, getDataSourceType());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt64(17, this.rebufferingMillis_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt32(18, this.rebuffers_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt32(19, this.rebufferAtExit_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt64(20, this.framesDroppedStartup_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getWhichPlayer());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getVideoMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getVideoCodec());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.width_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.height_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.frames_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.framesDropped_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeDoubleSize(8, this.framerate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getAudioMime());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeStringSize(10, getAudioCodec());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(11, this.durationMillis_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt64Size(12, this.playingMillis_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.error_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(14, this.errorCode_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeStringSize(15, getErrorState());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeStringSize(16, getDataSourceType());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt64Size(17, this.rebufferingMillis_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt32Size(18, this.rebuffers_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt32Size(19, this.rebufferAtExit_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt64Size(20, this.framesDroppedStartup_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static NuPlayerData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NuPlayerData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NuPlayerData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NuPlayerData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NuPlayerData parseFrom(InputStream input) throws IOException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NuPlayerData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NuPlayerData parseDelimitedFrom(InputStream input) throws IOException {
            return (NuPlayerData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static NuPlayerData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NuPlayerData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NuPlayerData parseFrom(CodedInputStream input) throws IOException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NuPlayerData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NuPlayerData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NuPlayerData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<NuPlayerData, Builder> implements NuPlayerDataOrBuilder {
            private Builder() {
                super(NuPlayerData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasWhichPlayer() {
                return ((NuPlayerData) this.instance).hasWhichPlayer();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getWhichPlayer() {
                return ((NuPlayerData) this.instance).getWhichPlayer();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getWhichPlayerBytes() {
                return ((NuPlayerData) this.instance).getWhichPlayerBytes();
            }

            public Builder setWhichPlayer(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setWhichPlayer(value);
                return this;
            }

            public Builder clearWhichPlayer() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearWhichPlayer();
                return this;
            }

            public Builder setWhichPlayerBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setWhichPlayerBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasVideoMime() {
                return ((NuPlayerData) this.instance).hasVideoMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getVideoMime() {
                return ((NuPlayerData) this.instance).getVideoMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getVideoMimeBytes() {
                return ((NuPlayerData) this.instance).getVideoMimeBytes();
            }

            public Builder setVideoMime(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setVideoMime(value);
                return this;
            }

            public Builder clearVideoMime() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearVideoMime();
                return this;
            }

            public Builder setVideoMimeBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setVideoMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasVideoCodec() {
                return ((NuPlayerData) this.instance).hasVideoCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getVideoCodec() {
                return ((NuPlayerData) this.instance).getVideoCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getVideoCodecBytes() {
                return ((NuPlayerData) this.instance).getVideoCodecBytes();
            }

            public Builder setVideoCodec(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setVideoCodec(value);
                return this;
            }

            public Builder clearVideoCodec() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearVideoCodec();
                return this;
            }

            public Builder setVideoCodecBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setVideoCodecBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasWidth() {
                return ((NuPlayerData) this.instance).hasWidth();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getWidth() {
                return ((NuPlayerData) this.instance).getWidth();
            }

            public Builder setWidth(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setWidth(value);
                return this;
            }

            public Builder clearWidth() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearWidth();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasHeight() {
                return ((NuPlayerData) this.instance).hasHeight();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getHeight() {
                return ((NuPlayerData) this.instance).getHeight();
            }

            public Builder setHeight(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setHeight(value);
                return this;
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearHeight();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasFrames() {
                return ((NuPlayerData) this.instance).hasFrames();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getFrames() {
                return ((NuPlayerData) this.instance).getFrames();
            }

            public Builder setFrames(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setFrames(value);
                return this;
            }

            public Builder clearFrames() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearFrames();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasFramesDropped() {
                return ((NuPlayerData) this.instance).hasFramesDropped();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getFramesDropped() {
                return ((NuPlayerData) this.instance).getFramesDropped();
            }

            public Builder setFramesDropped(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setFramesDropped(value);
                return this;
            }

            public Builder clearFramesDropped() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearFramesDropped();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasFramerate() {
                return ((NuPlayerData) this.instance).hasFramerate();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public double getFramerate() {
                return ((NuPlayerData) this.instance).getFramerate();
            }

            public Builder setFramerate(double value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setFramerate(value);
                return this;
            }

            public Builder clearFramerate() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearFramerate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasAudioMime() {
                return ((NuPlayerData) this.instance).hasAudioMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getAudioMime() {
                return ((NuPlayerData) this.instance).getAudioMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getAudioMimeBytes() {
                return ((NuPlayerData) this.instance).getAudioMimeBytes();
            }

            public Builder setAudioMime(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setAudioMime(value);
                return this;
            }

            public Builder clearAudioMime() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearAudioMime();
                return this;
            }

            public Builder setAudioMimeBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setAudioMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasAudioCodec() {
                return ((NuPlayerData) this.instance).hasAudioCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getAudioCodec() {
                return ((NuPlayerData) this.instance).getAudioCodec();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getAudioCodecBytes() {
                return ((NuPlayerData) this.instance).getAudioCodecBytes();
            }

            public Builder setAudioCodec(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setAudioCodec(value);
                return this;
            }

            public Builder clearAudioCodec() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearAudioCodec();
                return this;
            }

            public Builder setAudioCodecBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setAudioCodecBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasDurationMillis() {
                return ((NuPlayerData) this.instance).hasDurationMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getDurationMillis() {
                return ((NuPlayerData) this.instance).getDurationMillis();
            }

            public Builder setDurationMillis(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setDurationMillis(value);
                return this;
            }

            public Builder clearDurationMillis() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearDurationMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasPlayingMillis() {
                return ((NuPlayerData) this.instance).hasPlayingMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getPlayingMillis() {
                return ((NuPlayerData) this.instance).getPlayingMillis();
            }

            public Builder setPlayingMillis(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setPlayingMillis(value);
                return this;
            }

            public Builder clearPlayingMillis() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearPlayingMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasError() {
                return ((NuPlayerData) this.instance).hasError();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getError() {
                return ((NuPlayerData) this.instance).getError();
            }

            public Builder setError(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setError(value);
                return this;
            }

            public Builder clearError() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearError();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasErrorCode() {
                return ((NuPlayerData) this.instance).hasErrorCode();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getErrorCode() {
                return ((NuPlayerData) this.instance).getErrorCode();
            }

            public Builder setErrorCode(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setErrorCode(value);
                return this;
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearErrorCode();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasErrorState() {
                return ((NuPlayerData) this.instance).hasErrorState();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getErrorState() {
                return ((NuPlayerData) this.instance).getErrorState();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getErrorStateBytes() {
                return ((NuPlayerData) this.instance).getErrorStateBytes();
            }

            public Builder setErrorState(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setErrorState(value);
                return this;
            }

            public Builder clearErrorState() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearErrorState();
                return this;
            }

            public Builder setErrorStateBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setErrorStateBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasDataSourceType() {
                return ((NuPlayerData) this.instance).hasDataSourceType();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public String getDataSourceType() {
                return ((NuPlayerData) this.instance).getDataSourceType();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public ByteString getDataSourceTypeBytes() {
                return ((NuPlayerData) this.instance).getDataSourceTypeBytes();
            }

            public Builder setDataSourceType(String value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setDataSourceType(value);
                return this;
            }

            public Builder clearDataSourceType() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearDataSourceType();
                return this;
            }

            public Builder setDataSourceTypeBytes(ByteString value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setDataSourceTypeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasRebufferingMillis() {
                return ((NuPlayerData) this.instance).hasRebufferingMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getRebufferingMillis() {
                return ((NuPlayerData) this.instance).getRebufferingMillis();
            }

            public Builder setRebufferingMillis(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setRebufferingMillis(value);
                return this;
            }

            public Builder clearRebufferingMillis() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearRebufferingMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasRebuffers() {
                return ((NuPlayerData) this.instance).hasRebuffers();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getRebuffers() {
                return ((NuPlayerData) this.instance).getRebuffers();
            }

            public Builder setRebuffers(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setRebuffers(value);
                return this;
            }

            public Builder clearRebuffers() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearRebuffers();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasRebufferAtExit() {
                return ((NuPlayerData) this.instance).hasRebufferAtExit();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public int getRebufferAtExit() {
                return ((NuPlayerData) this.instance).getRebufferAtExit();
            }

            public Builder setRebufferAtExit(int value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setRebufferAtExit(value);
                return this;
            }

            public Builder clearRebufferAtExit() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearRebufferAtExit();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public boolean hasFramesDroppedStartup() {
                return ((NuPlayerData) this.instance).hasFramesDroppedStartup();
            }

            @Override // android.stats.mediametrics.Mediametrics.NuPlayerDataOrBuilder
            public long getFramesDroppedStartup() {
                return ((NuPlayerData) this.instance).getFramesDroppedStartup();
            }

            public Builder setFramesDroppedStartup(long value) {
                copyOnWrite();
                ((NuPlayerData) this.instance).setFramesDroppedStartup(value);
                return this;
            }

            public Builder clearFramesDroppedStartup() {
                copyOnWrite();
                ((NuPlayerData) this.instance).clearFramesDroppedStartup();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new NuPlayerData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    NuPlayerData other = (NuPlayerData) arg1;
                    this.whichPlayer_ = visitor.visitString(hasWhichPlayer(), this.whichPlayer_, other.hasWhichPlayer(), other.whichPlayer_);
                    this.videoMime_ = visitor.visitString(hasVideoMime(), this.videoMime_, other.hasVideoMime(), other.videoMime_);
                    this.videoCodec_ = visitor.visitString(hasVideoCodec(), this.videoCodec_, other.hasVideoCodec(), other.videoCodec_);
                    this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                    this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                    this.frames_ = visitor.visitLong(hasFrames(), this.frames_, other.hasFrames(), other.frames_);
                    this.framesDropped_ = visitor.visitLong(hasFramesDropped(), this.framesDropped_, other.hasFramesDropped(), other.framesDropped_);
                    this.framerate_ = visitor.visitDouble(hasFramerate(), this.framerate_, other.hasFramerate(), other.framerate_);
                    this.audioMime_ = visitor.visitString(hasAudioMime(), this.audioMime_, other.hasAudioMime(), other.audioMime_);
                    this.audioCodec_ = visitor.visitString(hasAudioCodec(), this.audioCodec_, other.hasAudioCodec(), other.audioCodec_);
                    this.durationMillis_ = visitor.visitLong(hasDurationMillis(), this.durationMillis_, other.hasDurationMillis(), other.durationMillis_);
                    this.playingMillis_ = visitor.visitLong(hasPlayingMillis(), this.playingMillis_, other.hasPlayingMillis(), other.playingMillis_);
                    this.error_ = visitor.visitInt(hasError(), this.error_, other.hasError(), other.error_);
                    this.errorCode_ = visitor.visitInt(hasErrorCode(), this.errorCode_, other.hasErrorCode(), other.errorCode_);
                    this.errorState_ = visitor.visitString(hasErrorState(), this.errorState_, other.hasErrorState(), other.errorState_);
                    this.dataSourceType_ = visitor.visitString(hasDataSourceType(), this.dataSourceType_, other.hasDataSourceType(), other.dataSourceType_);
                    this.rebufferingMillis_ = visitor.visitLong(hasRebufferingMillis(), this.rebufferingMillis_, other.hasRebufferingMillis(), other.rebufferingMillis_);
                    this.rebuffers_ = visitor.visitInt(hasRebuffers(), this.rebuffers_, other.hasRebuffers(), other.rebuffers_);
                    this.rebufferAtExit_ = visitor.visitInt(hasRebufferAtExit(), this.rebufferAtExit_, other.hasRebufferAtExit(), other.rebufferAtExit_);
                    this.framesDroppedStartup_ = visitor.visitLong(hasFramesDroppedStartup(), this.framesDroppedStartup_, other.hasFramesDroppedStartup(), other.framesDroppedStartup_);
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
                                    this.whichPlayer_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.videoMime_ = s2;
                                    break;
                                case 26:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 4;
                                    this.videoCodec_ = s3;
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.width_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.height_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.frames_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.framesDropped_ = input.readInt64();
                                    break;
                                case 65:
                                    this.bitField0_ |= 128;
                                    this.framerate_ = input.readDouble();
                                    break;
                                case 74:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.audioMime_ = s4;
                                    break;
                                case 82:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 512;
                                    this.audioCodec_ = s5;
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.durationMillis_ = input.readInt64();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.playingMillis_ = input.readInt64();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.error_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.errorCode_ = input.readInt32();
                                    break;
                                case 122:
                                    String s6 = input.readString();
                                    this.bitField0_ |= 16384;
                                    this.errorState_ = s6;
                                    break;
                                case 130:
                                    String s7 = input.readString();
                                    this.bitField0_ |= 32768;
                                    this.dataSourceType_ = s7;
                                    break;
                                case 136:
                                    this.bitField0_ |= 65536;
                                    this.rebufferingMillis_ = input.readInt64();
                                    break;
                                case 144:
                                    this.bitField0_ |= 131072;
                                    this.rebuffers_ = input.readInt32();
                                    break;
                                case 152:
                                    this.bitField0_ |= 262144;
                                    this.rebufferAtExit_ = input.readInt32();
                                    break;
                                case 160:
                                    this.bitField0_ |= 524288;
                                    this.framesDroppedStartup_ = input.readInt64();
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
                        synchronized (NuPlayerData.class) {
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

        public static NuPlayerData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NuPlayerData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class RecorderData extends GeneratedMessageLite<RecorderData, Builder> implements RecorderDataOrBuilder {
        public static final int AUDIO_BITRATE_FIELD_NUMBER = 14;
        public static final int AUDIO_CHANNELS_FIELD_NUMBER = 15;
        public static final int AUDIO_MIME_FIELD_NUMBER = 1;
        public static final int AUDIO_SAMPLERATE_FIELD_NUMBER = 16;
        public static final int AUDIO_TIMESCALE_FIELD_NUMBER = 18;
        public static final int CAPTURE_FPS_ENABLE_FIELD_NUMBER = 10;
        public static final int CAPTURE_FPS_FIELD_NUMBER = 9;
        private static final RecorderData DEFAULT_INSTANCE = new RecorderData();
        public static final int DURATION_MILLIS_FIELD_NUMBER = 11;
        public static final int FRAMERATE_FIELD_NUMBER = 8;
        public static final int HEIGHT_FIELD_NUMBER = 6;
        public static final int IFRAME_INTERVAL_FIELD_NUMBER = 21;
        public static final int MOVIE_TIMESCALE_FIELD_NUMBER = 17;
        private static volatile Parser<RecorderData> PARSER = null;
        public static final int PAUSED_COUNT_FIELD_NUMBER = 13;
        public static final int PAUSED_MILLIS_FIELD_NUMBER = 12;
        public static final int ROTATION_FIELD_NUMBER = 7;
        public static final int VIDEO_BITRATE_FIELD_NUMBER = 20;
        public static final int VIDEO_LEVEL_FIELD_NUMBER = 4;
        public static final int VIDEO_MIME_FIELD_NUMBER = 2;
        public static final int VIDEO_PROFILE_FIELD_NUMBER = 3;
        public static final int VIDEO_TIMESCALE_FIELD_NUMBER = 19;
        public static final int WIDTH_FIELD_NUMBER = 5;
        private int audioBitrate_ = 0;
        private int audioChannels_ = 0;
        private String audioMime_ = "";
        private int audioSamplerate_ = 0;
        private int audioTimescale_ = 0;
        private int bitField0_;
        private double captureFpsEnable_ = 0.0d;
        private int captureFps_ = 0;
        private long durationMillis_ = 0;
        private int framerate_ = 0;
        private int height_ = 0;
        private int iframeInterval_ = 0;
        private int movieTimescale_ = 0;
        private int pausedCount_ = 0;
        private long pausedMillis_ = 0;
        private int rotation_ = 0;
        private int videoBitrate_ = 0;
        private int videoLevel_ = 0;
        private String videoMime_ = "";
        private int videoProfile_ = 0;
        private int videoTimescale_ = 0;
        private int width_ = 0;

        private RecorderData() {
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasAudioMime() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public String getAudioMime() {
            return this.audioMime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public ByteString getAudioMimeBytes() {
            return ByteString.copyFromUtf8(this.audioMime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioMime(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.audioMime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioMime() {
            this.bitField0_ &= -2;
            this.audioMime_ = getDefaultInstance().getAudioMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.audioMime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasVideoMime() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public String getVideoMime() {
            return this.videoMime_;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public ByteString getVideoMimeBytes() {
            return ByteString.copyFromUtf8(this.videoMime_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoMime(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.videoMime_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoMime() {
            this.bitField0_ &= -3;
            this.videoMime_ = getDefaultInstance().getVideoMime();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoMimeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.videoMime_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasVideoProfile() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getVideoProfile() {
            return this.videoProfile_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoProfile(int value) {
            this.bitField0_ |= 4;
            this.videoProfile_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoProfile() {
            this.bitField0_ &= -5;
            this.videoProfile_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasVideoLevel() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getVideoLevel() {
            return this.videoLevel_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoLevel(int value) {
            this.bitField0_ |= 8;
            this.videoLevel_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoLevel() {
            this.bitField0_ &= -9;
            this.videoLevel_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasWidth() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getWidth() {
            return this.width_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWidth(int value) {
            this.bitField0_ |= 16;
            this.width_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWidth() {
            this.bitField0_ &= -17;
            this.width_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasHeight() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getHeight() {
            return this.height_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHeight(int value) {
            this.bitField0_ |= 32;
            this.height_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHeight() {
            this.bitField0_ &= -33;
            this.height_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasRotation() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getRotation() {
            return this.rotation_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRotation(int value) {
            this.bitField0_ |= 64;
            this.rotation_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRotation() {
            this.bitField0_ &= -65;
            this.rotation_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasFramerate() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getFramerate() {
            return this.framerate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFramerate(int value) {
            this.bitField0_ |= 128;
            this.framerate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFramerate() {
            this.bitField0_ &= -129;
            this.framerate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasCaptureFps() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getCaptureFps() {
            return this.captureFps_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCaptureFps(int value) {
            this.bitField0_ |= 256;
            this.captureFps_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCaptureFps() {
            this.bitField0_ &= -257;
            this.captureFps_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasCaptureFpsEnable() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public double getCaptureFpsEnable() {
            return this.captureFpsEnable_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCaptureFpsEnable(double value) {
            this.bitField0_ |= 512;
            this.captureFpsEnable_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCaptureFpsEnable() {
            this.bitField0_ &= -513;
            this.captureFpsEnable_ = 0.0d;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasDurationMillis() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public long getDurationMillis() {
            return this.durationMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMillis(long value) {
            this.bitField0_ |= 1024;
            this.durationMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMillis() {
            this.bitField0_ &= -1025;
            this.durationMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasPausedMillis() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public long getPausedMillis() {
            return this.pausedMillis_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPausedMillis(long value) {
            this.bitField0_ |= 2048;
            this.pausedMillis_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPausedMillis() {
            this.bitField0_ &= -2049;
            this.pausedMillis_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasPausedCount() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getPausedCount() {
            return this.pausedCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPausedCount(int value) {
            this.bitField0_ |= 4096;
            this.pausedCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPausedCount() {
            this.bitField0_ &= -4097;
            this.pausedCount_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasAudioBitrate() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getAudioBitrate() {
            return this.audioBitrate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioBitrate(int value) {
            this.bitField0_ |= 8192;
            this.audioBitrate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioBitrate() {
            this.bitField0_ &= -8193;
            this.audioBitrate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasAudioChannels() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getAudioChannels() {
            return this.audioChannels_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioChannels(int value) {
            this.bitField0_ |= 16384;
            this.audioChannels_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioChannels() {
            this.bitField0_ &= -16385;
            this.audioChannels_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasAudioSamplerate() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getAudioSamplerate() {
            return this.audioSamplerate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioSamplerate(int value) {
            this.bitField0_ |= 32768;
            this.audioSamplerate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioSamplerate() {
            this.bitField0_ &= -32769;
            this.audioSamplerate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasMovieTimescale() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getMovieTimescale() {
            return this.movieTimescale_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMovieTimescale(int value) {
            this.bitField0_ |= 65536;
            this.movieTimescale_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMovieTimescale() {
            this.bitField0_ &= -65537;
            this.movieTimescale_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasAudioTimescale() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getAudioTimescale() {
            return this.audioTimescale_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAudioTimescale(int value) {
            this.bitField0_ |= 131072;
            this.audioTimescale_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAudioTimescale() {
            this.bitField0_ &= -131073;
            this.audioTimescale_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasVideoTimescale() {
            return (this.bitField0_ & 262144) == 262144;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getVideoTimescale() {
            return this.videoTimescale_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoTimescale(int value) {
            this.bitField0_ |= 262144;
            this.videoTimescale_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoTimescale() {
            this.bitField0_ &= -262145;
            this.videoTimescale_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasVideoBitrate() {
            return (this.bitField0_ & 524288) == 524288;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getVideoBitrate() {
            return this.videoBitrate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVideoBitrate(int value) {
            this.bitField0_ |= 524288;
            this.videoBitrate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVideoBitrate() {
            this.bitField0_ &= -524289;
            this.videoBitrate_ = 0;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public boolean hasIframeInterval() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
        public int getIframeInterval() {
            return this.iframeInterval_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIframeInterval(int value) {
            this.bitField0_ |= 1048576;
            this.iframeInterval_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIframeInterval() {
            this.bitField0_ &= -1048577;
            this.iframeInterval_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getAudioMime());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getVideoMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.videoProfile_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.videoLevel_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.width_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.height_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.rotation_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.framerate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.captureFps_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeDouble(10, this.captureFpsEnable_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt64(11, this.durationMillis_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt64(12, this.pausedMillis_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.pausedCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(14, this.audioBitrate_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt32(15, this.audioChannels_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt32(16, this.audioSamplerate_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt32(17, this.movieTimescale_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeInt32(18, this.audioTimescale_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeInt32(19, this.videoTimescale_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeInt32(20, this.videoBitrate_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeInt32(21, this.iframeInterval_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getAudioMime());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getVideoMime());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.videoProfile_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.videoLevel_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.width_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.height_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.rotation_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.framerate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.captureFps_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeDoubleSize(10, this.captureFpsEnable_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeInt64Size(11, this.durationMillis_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt64Size(12, this.pausedMillis_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.pausedCount_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(14, this.audioBitrate_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt32Size(15, this.audioChannels_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeInt32Size(16, this.audioSamplerate_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeInt32Size(17, this.movieTimescale_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeInt32Size(18, this.audioTimescale_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeInt32Size(19, this.videoTimescale_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeInt32Size(20, this.videoBitrate_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeInt32Size(21, this.iframeInterval_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static RecorderData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RecorderData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RecorderData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RecorderData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RecorderData parseFrom(InputStream input) throws IOException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RecorderData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RecorderData parseDelimitedFrom(InputStream input) throws IOException {
            return (RecorderData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static RecorderData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecorderData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RecorderData parseFrom(CodedInputStream input) throws IOException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RecorderData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecorderData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RecorderData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RecorderData, Builder> implements RecorderDataOrBuilder {
            private Builder() {
                super(RecorderData.DEFAULT_INSTANCE);
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasAudioMime() {
                return ((RecorderData) this.instance).hasAudioMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public String getAudioMime() {
                return ((RecorderData) this.instance).getAudioMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public ByteString getAudioMimeBytes() {
                return ((RecorderData) this.instance).getAudioMimeBytes();
            }

            public Builder setAudioMime(String value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioMime(value);
                return this;
            }

            public Builder clearAudioMime() {
                copyOnWrite();
                ((RecorderData) this.instance).clearAudioMime();
                return this;
            }

            public Builder setAudioMimeBytes(ByteString value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasVideoMime() {
                return ((RecorderData) this.instance).hasVideoMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public String getVideoMime() {
                return ((RecorderData) this.instance).getVideoMime();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public ByteString getVideoMimeBytes() {
                return ((RecorderData) this.instance).getVideoMimeBytes();
            }

            public Builder setVideoMime(String value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoMime(value);
                return this;
            }

            public Builder clearVideoMime() {
                copyOnWrite();
                ((RecorderData) this.instance).clearVideoMime();
                return this;
            }

            public Builder setVideoMimeBytes(ByteString value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoMimeBytes(value);
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasVideoProfile() {
                return ((RecorderData) this.instance).hasVideoProfile();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getVideoProfile() {
                return ((RecorderData) this.instance).getVideoProfile();
            }

            public Builder setVideoProfile(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoProfile(value);
                return this;
            }

            public Builder clearVideoProfile() {
                copyOnWrite();
                ((RecorderData) this.instance).clearVideoProfile();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasVideoLevel() {
                return ((RecorderData) this.instance).hasVideoLevel();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getVideoLevel() {
                return ((RecorderData) this.instance).getVideoLevel();
            }

            public Builder setVideoLevel(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoLevel(value);
                return this;
            }

            public Builder clearVideoLevel() {
                copyOnWrite();
                ((RecorderData) this.instance).clearVideoLevel();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasWidth() {
                return ((RecorderData) this.instance).hasWidth();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getWidth() {
                return ((RecorderData) this.instance).getWidth();
            }

            public Builder setWidth(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setWidth(value);
                return this;
            }

            public Builder clearWidth() {
                copyOnWrite();
                ((RecorderData) this.instance).clearWidth();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasHeight() {
                return ((RecorderData) this.instance).hasHeight();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getHeight() {
                return ((RecorderData) this.instance).getHeight();
            }

            public Builder setHeight(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setHeight(value);
                return this;
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((RecorderData) this.instance).clearHeight();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasRotation() {
                return ((RecorderData) this.instance).hasRotation();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getRotation() {
                return ((RecorderData) this.instance).getRotation();
            }

            public Builder setRotation(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setRotation(value);
                return this;
            }

            public Builder clearRotation() {
                copyOnWrite();
                ((RecorderData) this.instance).clearRotation();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasFramerate() {
                return ((RecorderData) this.instance).hasFramerate();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getFramerate() {
                return ((RecorderData) this.instance).getFramerate();
            }

            public Builder setFramerate(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setFramerate(value);
                return this;
            }

            public Builder clearFramerate() {
                copyOnWrite();
                ((RecorderData) this.instance).clearFramerate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasCaptureFps() {
                return ((RecorderData) this.instance).hasCaptureFps();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getCaptureFps() {
                return ((RecorderData) this.instance).getCaptureFps();
            }

            public Builder setCaptureFps(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setCaptureFps(value);
                return this;
            }

            public Builder clearCaptureFps() {
                copyOnWrite();
                ((RecorderData) this.instance).clearCaptureFps();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasCaptureFpsEnable() {
                return ((RecorderData) this.instance).hasCaptureFpsEnable();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public double getCaptureFpsEnable() {
                return ((RecorderData) this.instance).getCaptureFpsEnable();
            }

            public Builder setCaptureFpsEnable(double value) {
                copyOnWrite();
                ((RecorderData) this.instance).setCaptureFpsEnable(value);
                return this;
            }

            public Builder clearCaptureFpsEnable() {
                copyOnWrite();
                ((RecorderData) this.instance).clearCaptureFpsEnable();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasDurationMillis() {
                return ((RecorderData) this.instance).hasDurationMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public long getDurationMillis() {
                return ((RecorderData) this.instance).getDurationMillis();
            }

            public Builder setDurationMillis(long value) {
                copyOnWrite();
                ((RecorderData) this.instance).setDurationMillis(value);
                return this;
            }

            public Builder clearDurationMillis() {
                copyOnWrite();
                ((RecorderData) this.instance).clearDurationMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasPausedMillis() {
                return ((RecorderData) this.instance).hasPausedMillis();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public long getPausedMillis() {
                return ((RecorderData) this.instance).getPausedMillis();
            }

            public Builder setPausedMillis(long value) {
                copyOnWrite();
                ((RecorderData) this.instance).setPausedMillis(value);
                return this;
            }

            public Builder clearPausedMillis() {
                copyOnWrite();
                ((RecorderData) this.instance).clearPausedMillis();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasPausedCount() {
                return ((RecorderData) this.instance).hasPausedCount();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getPausedCount() {
                return ((RecorderData) this.instance).getPausedCount();
            }

            public Builder setPausedCount(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setPausedCount(value);
                return this;
            }

            public Builder clearPausedCount() {
                copyOnWrite();
                ((RecorderData) this.instance).clearPausedCount();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasAudioBitrate() {
                return ((RecorderData) this.instance).hasAudioBitrate();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getAudioBitrate() {
                return ((RecorderData) this.instance).getAudioBitrate();
            }

            public Builder setAudioBitrate(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioBitrate(value);
                return this;
            }

            public Builder clearAudioBitrate() {
                copyOnWrite();
                ((RecorderData) this.instance).clearAudioBitrate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasAudioChannels() {
                return ((RecorderData) this.instance).hasAudioChannels();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getAudioChannels() {
                return ((RecorderData) this.instance).getAudioChannels();
            }

            public Builder setAudioChannels(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioChannels(value);
                return this;
            }

            public Builder clearAudioChannels() {
                copyOnWrite();
                ((RecorderData) this.instance).clearAudioChannels();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasAudioSamplerate() {
                return ((RecorderData) this.instance).hasAudioSamplerate();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getAudioSamplerate() {
                return ((RecorderData) this.instance).getAudioSamplerate();
            }

            public Builder setAudioSamplerate(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioSamplerate(value);
                return this;
            }

            public Builder clearAudioSamplerate() {
                copyOnWrite();
                ((RecorderData) this.instance).clearAudioSamplerate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasMovieTimescale() {
                return ((RecorderData) this.instance).hasMovieTimescale();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getMovieTimescale() {
                return ((RecorderData) this.instance).getMovieTimescale();
            }

            public Builder setMovieTimescale(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setMovieTimescale(value);
                return this;
            }

            public Builder clearMovieTimescale() {
                copyOnWrite();
                ((RecorderData) this.instance).clearMovieTimescale();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasAudioTimescale() {
                return ((RecorderData) this.instance).hasAudioTimescale();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getAudioTimescale() {
                return ((RecorderData) this.instance).getAudioTimescale();
            }

            public Builder setAudioTimescale(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setAudioTimescale(value);
                return this;
            }

            public Builder clearAudioTimescale() {
                copyOnWrite();
                ((RecorderData) this.instance).clearAudioTimescale();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasVideoTimescale() {
                return ((RecorderData) this.instance).hasVideoTimescale();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getVideoTimescale() {
                return ((RecorderData) this.instance).getVideoTimescale();
            }

            public Builder setVideoTimescale(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoTimescale(value);
                return this;
            }

            public Builder clearVideoTimescale() {
                copyOnWrite();
                ((RecorderData) this.instance).clearVideoTimescale();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasVideoBitrate() {
                return ((RecorderData) this.instance).hasVideoBitrate();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getVideoBitrate() {
                return ((RecorderData) this.instance).getVideoBitrate();
            }

            public Builder setVideoBitrate(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setVideoBitrate(value);
                return this;
            }

            public Builder clearVideoBitrate() {
                copyOnWrite();
                ((RecorderData) this.instance).clearVideoBitrate();
                return this;
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public boolean hasIframeInterval() {
                return ((RecorderData) this.instance).hasIframeInterval();
            }

            @Override // android.stats.mediametrics.Mediametrics.RecorderDataOrBuilder
            public int getIframeInterval() {
                return ((RecorderData) this.instance).getIframeInterval();
            }

            public Builder setIframeInterval(int value) {
                copyOnWrite();
                ((RecorderData) this.instance).setIframeInterval(value);
                return this;
            }

            public Builder clearIframeInterval() {
                copyOnWrite();
                ((RecorderData) this.instance).clearIframeInterval();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new RecorderData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    RecorderData other = (RecorderData) arg1;
                    this.audioMime_ = visitor.visitString(hasAudioMime(), this.audioMime_, other.hasAudioMime(), other.audioMime_);
                    this.videoMime_ = visitor.visitString(hasVideoMime(), this.videoMime_, other.hasVideoMime(), other.videoMime_);
                    this.videoProfile_ = visitor.visitInt(hasVideoProfile(), this.videoProfile_, other.hasVideoProfile(), other.videoProfile_);
                    this.videoLevel_ = visitor.visitInt(hasVideoLevel(), this.videoLevel_, other.hasVideoLevel(), other.videoLevel_);
                    this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                    this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                    this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
                    this.framerate_ = visitor.visitInt(hasFramerate(), this.framerate_, other.hasFramerate(), other.framerate_);
                    this.captureFps_ = visitor.visitInt(hasCaptureFps(), this.captureFps_, other.hasCaptureFps(), other.captureFps_);
                    this.captureFpsEnable_ = visitor.visitDouble(hasCaptureFpsEnable(), this.captureFpsEnable_, other.hasCaptureFpsEnable(), other.captureFpsEnable_);
                    this.durationMillis_ = visitor.visitLong(hasDurationMillis(), this.durationMillis_, other.hasDurationMillis(), other.durationMillis_);
                    this.pausedMillis_ = visitor.visitLong(hasPausedMillis(), this.pausedMillis_, other.hasPausedMillis(), other.pausedMillis_);
                    this.pausedCount_ = visitor.visitInt(hasPausedCount(), this.pausedCount_, other.hasPausedCount(), other.pausedCount_);
                    this.audioBitrate_ = visitor.visitInt(hasAudioBitrate(), this.audioBitrate_, other.hasAudioBitrate(), other.audioBitrate_);
                    this.audioChannels_ = visitor.visitInt(hasAudioChannels(), this.audioChannels_, other.hasAudioChannels(), other.audioChannels_);
                    this.audioSamplerate_ = visitor.visitInt(hasAudioSamplerate(), this.audioSamplerate_, other.hasAudioSamplerate(), other.audioSamplerate_);
                    this.movieTimescale_ = visitor.visitInt(hasMovieTimescale(), this.movieTimescale_, other.hasMovieTimescale(), other.movieTimescale_);
                    this.audioTimescale_ = visitor.visitInt(hasAudioTimescale(), this.audioTimescale_, other.hasAudioTimescale(), other.audioTimescale_);
                    this.videoTimescale_ = visitor.visitInt(hasVideoTimescale(), this.videoTimescale_, other.hasVideoTimescale(), other.videoTimescale_);
                    this.videoBitrate_ = visitor.visitInt(hasVideoBitrate(), this.videoBitrate_, other.hasVideoBitrate(), other.videoBitrate_);
                    this.iframeInterval_ = visitor.visitInt(hasIframeInterval(), this.iframeInterval_, other.hasIframeInterval(), other.iframeInterval_);
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
                                    this.audioMime_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.videoMime_ = s2;
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.videoProfile_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.videoLevel_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.width_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.height_ = input.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.rotation_ = input.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.framerate_ = input.readInt32();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.captureFps_ = input.readInt32();
                                    break;
                                case 81:
                                    this.bitField0_ |= 512;
                                    this.captureFpsEnable_ = input.readDouble();
                                    break;
                                case 88:
                                    this.bitField0_ |= 1024;
                                    this.durationMillis_ = input.readInt64();
                                    break;
                                case 96:
                                    this.bitField0_ |= 2048;
                                    this.pausedMillis_ = input.readInt64();
                                    break;
                                case 104:
                                    this.bitField0_ |= 4096;
                                    this.pausedCount_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.audioBitrate_ = input.readInt32();
                                    break;
                                case 120:
                                    this.bitField0_ |= 16384;
                                    this.audioChannels_ = input.readInt32();
                                    break;
                                case 128:
                                    this.bitField0_ |= 32768;
                                    this.audioSamplerate_ = input.readInt32();
                                    break;
                                case 136:
                                    this.bitField0_ |= 65536;
                                    this.movieTimescale_ = input.readInt32();
                                    break;
                                case 144:
                                    this.bitField0_ |= 131072;
                                    this.audioTimescale_ = input.readInt32();
                                    break;
                                case 152:
                                    this.bitField0_ |= 262144;
                                    this.videoTimescale_ = input.readInt32();
                                    break;
                                case 160:
                                    this.bitField0_ |= 524288;
                                    this.videoBitrate_ = input.readInt32();
                                    break;
                                case 168:
                                    this.bitField0_ |= 1048576;
                                    this.iframeInterval_ = input.readInt32();
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
                        synchronized (RecorderData.class) {
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

        public static RecorderData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RecorderData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
