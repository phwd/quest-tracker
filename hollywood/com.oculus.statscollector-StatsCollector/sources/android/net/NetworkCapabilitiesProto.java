package android.net;

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
import java.util.List;

public final class NetworkCapabilitiesProto extends GeneratedMessageLite<NetworkCapabilitiesProto, Builder> implements NetworkCapabilitiesProtoOrBuilder {
    public static final int CAN_REPORT_SIGNAL_STRENGTH_FIELD_NUMBER = 6;
    public static final int CAPABILITIES_FIELD_NUMBER = 2;
    private static final NetworkCapabilitiesProto DEFAULT_INSTANCE = new NetworkCapabilitiesProto();
    public static final int LINK_DOWN_BANDWIDTH_KBPS_FIELD_NUMBER = 4;
    public static final int LINK_UP_BANDWIDTH_KBPS_FIELD_NUMBER = 3;
    public static final int NETWORK_SPECIFIER_FIELD_NUMBER = 5;
    private static volatile Parser<NetworkCapabilitiesProto> PARSER = null;
    public static final int SIGNAL_STRENGTH_FIELD_NUMBER = 7;
    public static final int TRANSPORTS_FIELD_NUMBER = 1;
    private static final Internal.ListAdapter.Converter<Integer, NetCapability> capabilities_converter_ = new Internal.ListAdapter.Converter<Integer, NetCapability>() {
        /* class android.net.NetworkCapabilitiesProto.AnonymousClass2 */

        public NetCapability convert(Integer from) {
            NetCapability result = NetCapability.forNumber(from.intValue());
            return result == null ? NetCapability.NET_CAPABILITY_MMS : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, Transport> transports_converter_ = new Internal.ListAdapter.Converter<Integer, Transport>() {
        /* class android.net.NetworkCapabilitiesProto.AnonymousClass1 */

        public Transport convert(Integer from) {
            Transport result = Transport.forNumber(from.intValue());
            return result == null ? Transport.TRANSPORT_CELLULAR : result;
        }
    };
    private int bitField0_;
    private boolean canReportSignalStrength_ = false;
    private Internal.IntList capabilities_ = emptyIntList();
    private int linkDownBandwidthKbps_ = 0;
    private int linkUpBandwidthKbps_ = 0;
    private String networkSpecifier_ = "";
    private int signalStrength_ = 0;
    private Internal.IntList transports_ = emptyIntList();

    private NetworkCapabilitiesProto() {
    }

    public enum Transport implements Internal.EnumLite {
        TRANSPORT_CELLULAR(0),
        TRANSPORT_WIFI(1),
        TRANSPORT_BLUETOOTH(2),
        TRANSPORT_ETHERNET(3),
        TRANSPORT_VPN(4),
        TRANSPORT_WIFI_AWARE(5),
        TRANSPORT_LOWPAN(6);
        
        public static final int TRANSPORT_BLUETOOTH_VALUE = 2;
        public static final int TRANSPORT_CELLULAR_VALUE = 0;
        public static final int TRANSPORT_ETHERNET_VALUE = 3;
        public static final int TRANSPORT_LOWPAN_VALUE = 6;
        public static final int TRANSPORT_VPN_VALUE = 4;
        public static final int TRANSPORT_WIFI_AWARE_VALUE = 5;
        public static final int TRANSPORT_WIFI_VALUE = 1;
        private static final Internal.EnumLiteMap<Transport> internalValueMap = new Internal.EnumLiteMap<Transport>() {
            /* class android.net.NetworkCapabilitiesProto.Transport.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Transport findValueByNumber(int number) {
                return Transport.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Transport valueOf(int value2) {
            return forNumber(value2);
        }

        public static Transport forNumber(int value2) {
            switch (value2) {
                case 0:
                    return TRANSPORT_CELLULAR;
                case 1:
                    return TRANSPORT_WIFI;
                case 2:
                    return TRANSPORT_BLUETOOTH;
                case 3:
                    return TRANSPORT_ETHERNET;
                case 4:
                    return TRANSPORT_VPN;
                case 5:
                    return TRANSPORT_WIFI_AWARE;
                case 6:
                    return TRANSPORT_LOWPAN;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Transport> internalGetValueMap() {
            return internalValueMap;
        }

        private Transport(int value2) {
            this.value = value2;
        }
    }

    public enum NetCapability implements Internal.EnumLite {
        NET_CAPABILITY_MMS(0),
        NET_CAPABILITY_SUPL(1),
        NET_CAPABILITY_DUN(2),
        NET_CAPABILITY_FOTA(3),
        NET_CAPABILITY_IMS(4),
        NET_CAPABILITY_CBS(5),
        NET_CAPABILITY_WIFI_P2P(6),
        NET_CAPABILITY_IA(7),
        NET_CAPABILITY_RCS(8),
        NET_CAPABILITY_XCAP(9),
        NET_CAPABILITY_EIMS(10),
        NET_CAPABILITY_NOT_METERED(11),
        NET_CAPABILITY_INTERNET(12),
        NET_CAPABILITY_NOT_RESTRICTED(13),
        NET_CAPABILITY_TRUSTED(14),
        NET_CAPABILITY_NOT_VPN(15),
        NET_CAPABILITY_VALIDATED(16),
        NET_CAPABILITY_CAPTIVE_PORTAL(17),
        NET_CAPABILITY_NOT_ROAMING(18),
        NET_CAPABILITY_FOREGROUND(19);
        
        public static final int NET_CAPABILITY_CAPTIVE_PORTAL_VALUE = 17;
        public static final int NET_CAPABILITY_CBS_VALUE = 5;
        public static final int NET_CAPABILITY_DUN_VALUE = 2;
        public static final int NET_CAPABILITY_EIMS_VALUE = 10;
        public static final int NET_CAPABILITY_FOREGROUND_VALUE = 19;
        public static final int NET_CAPABILITY_FOTA_VALUE = 3;
        public static final int NET_CAPABILITY_IA_VALUE = 7;
        public static final int NET_CAPABILITY_IMS_VALUE = 4;
        public static final int NET_CAPABILITY_INTERNET_VALUE = 12;
        public static final int NET_CAPABILITY_MMS_VALUE = 0;
        public static final int NET_CAPABILITY_NOT_METERED_VALUE = 11;
        public static final int NET_CAPABILITY_NOT_RESTRICTED_VALUE = 13;
        public static final int NET_CAPABILITY_NOT_ROAMING_VALUE = 18;
        public static final int NET_CAPABILITY_NOT_VPN_VALUE = 15;
        public static final int NET_CAPABILITY_RCS_VALUE = 8;
        public static final int NET_CAPABILITY_SUPL_VALUE = 1;
        public static final int NET_CAPABILITY_TRUSTED_VALUE = 14;
        public static final int NET_CAPABILITY_VALIDATED_VALUE = 16;
        public static final int NET_CAPABILITY_WIFI_P2P_VALUE = 6;
        public static final int NET_CAPABILITY_XCAP_VALUE = 9;
        private static final Internal.EnumLiteMap<NetCapability> internalValueMap = new Internal.EnumLiteMap<NetCapability>() {
            /* class android.net.NetworkCapabilitiesProto.NetCapability.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NetCapability findValueByNumber(int number) {
                return NetCapability.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NetCapability valueOf(int value2) {
            return forNumber(value2);
        }

        public static NetCapability forNumber(int value2) {
            switch (value2) {
                case 0:
                    return NET_CAPABILITY_MMS;
                case 1:
                    return NET_CAPABILITY_SUPL;
                case 2:
                    return NET_CAPABILITY_DUN;
                case 3:
                    return NET_CAPABILITY_FOTA;
                case 4:
                    return NET_CAPABILITY_IMS;
                case 5:
                    return NET_CAPABILITY_CBS;
                case 6:
                    return NET_CAPABILITY_WIFI_P2P;
                case 7:
                    return NET_CAPABILITY_IA;
                case 8:
                    return NET_CAPABILITY_RCS;
                case 9:
                    return NET_CAPABILITY_XCAP;
                case 10:
                    return NET_CAPABILITY_EIMS;
                case 11:
                    return NET_CAPABILITY_NOT_METERED;
                case 12:
                    return NET_CAPABILITY_INTERNET;
                case 13:
                    return NET_CAPABILITY_NOT_RESTRICTED;
                case 14:
                    return NET_CAPABILITY_TRUSTED;
                case 15:
                    return NET_CAPABILITY_NOT_VPN;
                case 16:
                    return NET_CAPABILITY_VALIDATED;
                case 17:
                    return NET_CAPABILITY_CAPTIVE_PORTAL;
                case 18:
                    return NET_CAPABILITY_NOT_ROAMING;
                case 19:
                    return NET_CAPABILITY_FOREGROUND;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NetCapability> internalGetValueMap() {
            return internalValueMap;
        }

        private NetCapability(int value2) {
            this.value = value2;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public List<Transport> getTransportsList() {
        return new Internal.ListAdapter(this.transports_, transports_converter_);
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public int getTransportsCount() {
        return this.transports_.size();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public Transport getTransports(int index) {
        return transports_converter_.convert(Integer.valueOf(this.transports_.getInt(index)));
    }

    private void ensureTransportsIsMutable() {
        if (!this.transports_.isModifiable()) {
            this.transports_ = GeneratedMessageLite.mutableCopy(this.transports_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTransports(int index, Transport value) {
        if (value != null) {
            ensureTransportsIsMutable();
            this.transports_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTransports(Transport value) {
        if (value != null) {
            ensureTransportsIsMutable();
            this.transports_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTransports(Iterable<? extends Transport> values) {
        ensureTransportsIsMutable();
        for (Transport value : values) {
            this.transports_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTransports() {
        this.transports_ = emptyIntList();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public List<NetCapability> getCapabilitiesList() {
        return new Internal.ListAdapter(this.capabilities_, capabilities_converter_);
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public int getCapabilitiesCount() {
        return this.capabilities_.size();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public NetCapability getCapabilities(int index) {
        return capabilities_converter_.convert(Integer.valueOf(this.capabilities_.getInt(index)));
    }

    private void ensureCapabilitiesIsMutable() {
        if (!this.capabilities_.isModifiable()) {
            this.capabilities_ = GeneratedMessageLite.mutableCopy(this.capabilities_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCapabilities(int index, NetCapability value) {
        if (value != null) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCapabilities(NetCapability value) {
        if (value != null) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCapabilities(Iterable<? extends NetCapability> values) {
        ensureCapabilitiesIsMutable();
        for (NetCapability value : values) {
            this.capabilities_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCapabilities() {
        this.capabilities_ = emptyIntList();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean hasLinkUpBandwidthKbps() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public int getLinkUpBandwidthKbps() {
        return this.linkUpBandwidthKbps_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLinkUpBandwidthKbps(int value) {
        this.bitField0_ |= 1;
        this.linkUpBandwidthKbps_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLinkUpBandwidthKbps() {
        this.bitField0_ &= -2;
        this.linkUpBandwidthKbps_ = 0;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean hasLinkDownBandwidthKbps() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public int getLinkDownBandwidthKbps() {
        return this.linkDownBandwidthKbps_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLinkDownBandwidthKbps(int value) {
        this.bitField0_ |= 2;
        this.linkDownBandwidthKbps_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLinkDownBandwidthKbps() {
        this.bitField0_ &= -3;
        this.linkDownBandwidthKbps_ = 0;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean hasNetworkSpecifier() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public String getNetworkSpecifier() {
        return this.networkSpecifier_;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public ByteString getNetworkSpecifierBytes() {
        return ByteString.copyFromUtf8(this.networkSpecifier_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkSpecifier(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkSpecifier_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetworkSpecifier() {
        this.bitField0_ &= -5;
        this.networkSpecifier_ = getDefaultInstance().getNetworkSpecifier();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkSpecifierBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.networkSpecifier_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean hasCanReportSignalStrength() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean getCanReportSignalStrength() {
        return this.canReportSignalStrength_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanReportSignalStrength(boolean value) {
        this.bitField0_ |= 8;
        this.canReportSignalStrength_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanReportSignalStrength() {
        this.bitField0_ &= -9;
        this.canReportSignalStrength_ = false;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public boolean hasSignalStrength() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.net.NetworkCapabilitiesProtoOrBuilder
    public int getSignalStrength() {
        return this.signalStrength_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSignalStrength(int value) {
        this.bitField0_ |= 16;
        this.signalStrength_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSignalStrength() {
        this.bitField0_ &= -17;
        this.signalStrength_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.transports_.size(); i++) {
            output.writeEnum(1, this.transports_.getInt(i));
        }
        for (int i2 = 0; i2 < this.capabilities_.size(); i2++) {
            output.writeEnum(2, this.capabilities_.getInt(i2));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(3, this.linkUpBandwidthKbps_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(4, this.linkDownBandwidthKbps_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(5, getNetworkSpecifier());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(6, this.canReportSignalStrength_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeSInt32(7, this.signalStrength_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.transports_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.transports_.getInt(i));
        }
        int size2 = 0 + dataSize + (this.transports_.size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.capabilities_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeEnumSizeNoTag(this.capabilities_.getInt(i2));
        }
        int size3 = size2 + dataSize2 + (this.capabilities_.size() * 1);
        if ((this.bitField0_ & 1) == 1) {
            size3 += CodedOutputStream.computeInt32Size(3, this.linkUpBandwidthKbps_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size3 += CodedOutputStream.computeInt32Size(4, this.linkDownBandwidthKbps_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size3 += CodedOutputStream.computeStringSize(5, getNetworkSpecifier());
        }
        if ((this.bitField0_ & 8) == 8) {
            size3 += CodedOutputStream.computeBoolSize(6, this.canReportSignalStrength_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size3 += CodedOutputStream.computeSInt32Size(7, this.signalStrength_);
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static NetworkCapabilitiesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkCapabilitiesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkCapabilitiesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NetworkCapabilitiesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NetworkCapabilitiesProto parseFrom(InputStream input) throws IOException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkCapabilitiesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkCapabilitiesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NetworkCapabilitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkCapabilitiesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkCapabilitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NetworkCapabilitiesProto parseFrom(CodedInputStream input) throws IOException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NetworkCapabilitiesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NetworkCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NetworkCapabilitiesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkCapabilitiesProto, Builder> implements NetworkCapabilitiesProtoOrBuilder {
        private Builder() {
            super(NetworkCapabilitiesProto.DEFAULT_INSTANCE);
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public List<Transport> getTransportsList() {
            return ((NetworkCapabilitiesProto) this.instance).getTransportsList();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public int getTransportsCount() {
            return ((NetworkCapabilitiesProto) this.instance).getTransportsCount();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public Transport getTransports(int index) {
            return ((NetworkCapabilitiesProto) this.instance).getTransports(index);
        }

        public Builder setTransports(int index, Transport value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setTransports(index, value);
            return this;
        }

        public Builder addTransports(Transport value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).addTransports(value);
            return this;
        }

        public Builder addAllTransports(Iterable<? extends Transport> values) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).addAllTransports(values);
            return this;
        }

        public Builder clearTransports() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearTransports();
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public List<NetCapability> getCapabilitiesList() {
            return ((NetworkCapabilitiesProto) this.instance).getCapabilitiesList();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public int getCapabilitiesCount() {
            return ((NetworkCapabilitiesProto) this.instance).getCapabilitiesCount();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public NetCapability getCapabilities(int index) {
            return ((NetworkCapabilitiesProto) this.instance).getCapabilities(index);
        }

        public Builder setCapabilities(int index, NetCapability value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setCapabilities(index, value);
            return this;
        }

        public Builder addCapabilities(NetCapability value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).addCapabilities(value);
            return this;
        }

        public Builder addAllCapabilities(Iterable<? extends NetCapability> values) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).addAllCapabilities(values);
            return this;
        }

        public Builder clearCapabilities() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearCapabilities();
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean hasLinkUpBandwidthKbps() {
            return ((NetworkCapabilitiesProto) this.instance).hasLinkUpBandwidthKbps();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public int getLinkUpBandwidthKbps() {
            return ((NetworkCapabilitiesProto) this.instance).getLinkUpBandwidthKbps();
        }

        public Builder setLinkUpBandwidthKbps(int value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setLinkUpBandwidthKbps(value);
            return this;
        }

        public Builder clearLinkUpBandwidthKbps() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearLinkUpBandwidthKbps();
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean hasLinkDownBandwidthKbps() {
            return ((NetworkCapabilitiesProto) this.instance).hasLinkDownBandwidthKbps();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public int getLinkDownBandwidthKbps() {
            return ((NetworkCapabilitiesProto) this.instance).getLinkDownBandwidthKbps();
        }

        public Builder setLinkDownBandwidthKbps(int value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setLinkDownBandwidthKbps(value);
            return this;
        }

        public Builder clearLinkDownBandwidthKbps() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearLinkDownBandwidthKbps();
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean hasNetworkSpecifier() {
            return ((NetworkCapabilitiesProto) this.instance).hasNetworkSpecifier();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public String getNetworkSpecifier() {
            return ((NetworkCapabilitiesProto) this.instance).getNetworkSpecifier();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public ByteString getNetworkSpecifierBytes() {
            return ((NetworkCapabilitiesProto) this.instance).getNetworkSpecifierBytes();
        }

        public Builder setNetworkSpecifier(String value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setNetworkSpecifier(value);
            return this;
        }

        public Builder clearNetworkSpecifier() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearNetworkSpecifier();
            return this;
        }

        public Builder setNetworkSpecifierBytes(ByteString value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setNetworkSpecifierBytes(value);
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean hasCanReportSignalStrength() {
            return ((NetworkCapabilitiesProto) this.instance).hasCanReportSignalStrength();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean getCanReportSignalStrength() {
            return ((NetworkCapabilitiesProto) this.instance).getCanReportSignalStrength();
        }

        public Builder setCanReportSignalStrength(boolean value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setCanReportSignalStrength(value);
            return this;
        }

        public Builder clearCanReportSignalStrength() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearCanReportSignalStrength();
            return this;
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public boolean hasSignalStrength() {
            return ((NetworkCapabilitiesProto) this.instance).hasSignalStrength();
        }

        @Override // android.net.NetworkCapabilitiesProtoOrBuilder
        public int getSignalStrength() {
            return ((NetworkCapabilitiesProto) this.instance).getSignalStrength();
        }

        public Builder setSignalStrength(int value) {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).setSignalStrength(value);
            return this;
        }

        public Builder clearSignalStrength() {
            copyOnWrite();
            ((NetworkCapabilitiesProto) this.instance).clearSignalStrength();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NetworkCapabilitiesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.transports_.makeImmutable();
                this.capabilities_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NetworkCapabilitiesProto other = (NetworkCapabilitiesProto) arg1;
                this.transports_ = visitor.visitIntList(this.transports_, other.transports_);
                this.capabilities_ = visitor.visitIntList(this.capabilities_, other.capabilities_);
                this.linkUpBandwidthKbps_ = visitor.visitInt(hasLinkUpBandwidthKbps(), this.linkUpBandwidthKbps_, other.hasLinkUpBandwidthKbps(), other.linkUpBandwidthKbps_);
                this.linkDownBandwidthKbps_ = visitor.visitInt(hasLinkDownBandwidthKbps(), this.linkDownBandwidthKbps_, other.hasLinkDownBandwidthKbps(), other.linkDownBandwidthKbps_);
                this.networkSpecifier_ = visitor.visitString(hasNetworkSpecifier(), this.networkSpecifier_, other.hasNetworkSpecifier(), other.networkSpecifier_);
                this.canReportSignalStrength_ = visitor.visitBoolean(hasCanReportSignalStrength(), this.canReportSignalStrength_, other.hasCanReportSignalStrength(), other.canReportSignalStrength_);
                this.signalStrength_ = visitor.visitInt(hasSignalStrength(), this.signalStrength_, other.hasSignalStrength(), other.signalStrength_);
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
                            if (!this.transports_.isModifiable()) {
                                this.transports_ = GeneratedMessageLite.mutableCopy(this.transports_);
                            }
                            int rawValue = input.readEnum();
                            if (Transport.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.transports_.addInt(rawValue);
                            }
                        } else if (tag == 10) {
                            if (!this.transports_.isModifiable()) {
                                this.transports_ = GeneratedMessageLite.mutableCopy(this.transports_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (Transport.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(1, rawValue2);
                                } else {
                                    this.transports_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
                        } else if (tag == 16) {
                            if (!this.capabilities_.isModifiable()) {
                                this.capabilities_ = GeneratedMessageLite.mutableCopy(this.capabilities_);
                            }
                            int rawValue3 = input.readEnum();
                            if (NetCapability.forNumber(rawValue3) == null) {
                                super.mergeVarintField(2, rawValue3);
                            } else {
                                this.capabilities_.addInt(rawValue3);
                            }
                        } else if (tag == 18) {
                            if (!this.capabilities_.isModifiable()) {
                                this.capabilities_ = GeneratedMessageLite.mutableCopy(this.capabilities_);
                            }
                            int oldLimit2 = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue4 = input.readEnum();
                                if (NetCapability.forNumber(rawValue4) == null) {
                                    super.mergeVarintField(2, rawValue4);
                                } else {
                                    this.capabilities_.addInt(rawValue4);
                                }
                            }
                            input.popLimit(oldLimit2);
                        } else if (tag == 24) {
                            this.bitField0_ |= 1;
                            this.linkUpBandwidthKbps_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 2;
                            this.linkDownBandwidthKbps_ = input.readInt32();
                        } else if (tag == 42) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.networkSpecifier_ = s;
                        } else if (tag == 48) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.canReportSignalStrength_ = input.readBool();
                        } else if (tag == 56) {
                            this.bitField0_ |= 16;
                            this.signalStrength_ = input.readSInt32();
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
                    synchronized (NetworkCapabilitiesProto.class) {
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

    public static NetworkCapabilitiesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NetworkCapabilitiesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
