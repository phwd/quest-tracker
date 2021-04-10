package android.server.location;

import com.google.protobuf.Internal;

public enum NfwProtocolStack implements Internal.EnumLite {
    CTRL_PLANE(0),
    SUPL(1),
    IMS(10),
    SIM(11),
    OTHER_PROTOCOL_STACK(100);
    
    public static final int CTRL_PLANE_VALUE = 0;
    public static final int IMS_VALUE = 10;
    public static final int OTHER_PROTOCOL_STACK_VALUE = 100;
    public static final int SIM_VALUE = 11;
    public static final int SUPL_VALUE = 1;
    private static final Internal.EnumLiteMap<NfwProtocolStack> internalValueMap = new Internal.EnumLiteMap<NfwProtocolStack>() {
        /* class android.server.location.NfwProtocolStack.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NfwProtocolStack findValueByNumber(int number) {
            return NfwProtocolStack.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static NfwProtocolStack valueOf(int value2) {
        return forNumber(value2);
    }

    public static NfwProtocolStack forNumber(int value2) {
        if (value2 == 0) {
            return CTRL_PLANE;
        }
        if (value2 == 1) {
            return SUPL;
        }
        if (value2 == 10) {
            return IMS;
        }
        if (value2 == 11) {
            return SIM;
        }
        if (value2 != 100) {
            return null;
        }
        return OTHER_PROTOCOL_STACK;
    }

    public static Internal.EnumLiteMap<NfwProtocolStack> internalGetValueMap() {
        return internalValueMap;
    }

    private NfwProtocolStack(int value2) {
        this.value = value2;
    }
}
