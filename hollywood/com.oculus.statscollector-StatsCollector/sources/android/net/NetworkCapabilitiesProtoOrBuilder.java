package android.net;

import android.net.NetworkCapabilitiesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkCapabilitiesProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getCanReportSignalStrength();

    NetworkCapabilitiesProto.NetCapability getCapabilities(int i);

    int getCapabilitiesCount();

    List<NetworkCapabilitiesProto.NetCapability> getCapabilitiesList();

    int getLinkDownBandwidthKbps();

    int getLinkUpBandwidthKbps();

    String getNetworkSpecifier();

    ByteString getNetworkSpecifierBytes();

    int getSignalStrength();

    NetworkCapabilitiesProto.Transport getTransports(int i);

    int getTransportsCount();

    List<NetworkCapabilitiesProto.Transport> getTransportsList();

    boolean hasCanReportSignalStrength();

    boolean hasLinkDownBandwidthKbps();

    boolean hasLinkUpBandwidthKbps();

    boolean hasNetworkSpecifier();

    boolean hasSignalStrength();
}
