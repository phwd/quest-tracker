package android.net;

import android.net.NetworkRequestProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkRequestProtoOrBuilder extends MessageLiteOrBuilder {
    int getLegacyType();

    NetworkCapabilitiesProto getNetworkCapabilities();

    int getRequestId();

    NetworkRequestProto.Type getType();

    boolean hasLegacyType();

    boolean hasNetworkCapabilities();

    boolean hasRequestId();

    boolean hasType();
}
