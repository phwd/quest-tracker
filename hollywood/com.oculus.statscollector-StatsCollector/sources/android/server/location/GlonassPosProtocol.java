package android.server.location;

import com.google.protobuf.Internal;

public enum GlonassPosProtocol implements Internal.EnumLite {
    RRC_CPLANE(1),
    RRLP_CPLANE(2),
    LPP_UPLANE(4);
    
    public static final int LPP_UPLANE_VALUE = 4;
    public static final int RRC_CPLANE_VALUE = 1;
    public static final int RRLP_CPLANE_VALUE = 2;
    private static final Internal.EnumLiteMap<GlonassPosProtocol> internalValueMap = new Internal.EnumLiteMap<GlonassPosProtocol>() {
        /* class android.server.location.GlonassPosProtocol.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GlonassPosProtocol findValueByNumber(int number) {
            return GlonassPosProtocol.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GlonassPosProtocol valueOf(int value2) {
        return forNumber(value2);
    }

    public static GlonassPosProtocol forNumber(int value2) {
        if (value2 == 1) {
            return RRC_CPLANE;
        }
        if (value2 == 2) {
            return RRLP_CPLANE;
        }
        if (value2 != 4) {
            return null;
        }
        return LPP_UPLANE;
    }

    public static Internal.EnumLiteMap<GlonassPosProtocol> internalGetValueMap() {
        return internalValueMap;
    }

    private GlonassPosProtocol(int value2) {
        this.value = value2;
    }
}
