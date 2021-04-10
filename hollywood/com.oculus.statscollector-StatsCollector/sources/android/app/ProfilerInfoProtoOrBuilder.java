package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProfilerInfoProtoOrBuilder extends MessageLiteOrBuilder {
    String getAgent();

    ByteString getAgentBytes();

    boolean getAutoStopProfiler();

    int getProfileFd();

    String getProfileFile();

    ByteString getProfileFileBytes();

    int getSamplingInterval();

    boolean getStreamingOutput();

    boolean hasAgent();

    boolean hasAutoStopProfiler();

    boolean hasProfileFd();

    boolean hasProfileFile();

    boolean hasSamplingInterval();

    boolean hasStreamingOutput();
}
