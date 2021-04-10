package android.service;

import com.google.protobuf.Internal;

public enum UsbEndPointDirection implements Internal.EnumLite {
    USB_ENDPOINT_DIR_OUT(0),
    USB_ENDPOINT_DIR_IN(128);
    
    public static final int USB_ENDPOINT_DIR_IN_VALUE = 128;
    public static final int USB_ENDPOINT_DIR_OUT_VALUE = 0;
    private static final Internal.EnumLiteMap<UsbEndPointDirection> internalValueMap = new Internal.EnumLiteMap<UsbEndPointDirection>() {
        /* class android.service.UsbEndPointDirection.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UsbEndPointDirection findValueByNumber(int number) {
            return UsbEndPointDirection.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UsbEndPointDirection valueOf(int value2) {
        return forNumber(value2);
    }

    public static UsbEndPointDirection forNumber(int value2) {
        if (value2 == 0) {
            return USB_ENDPOINT_DIR_OUT;
        }
        if (value2 != 128) {
            return null;
        }
        return USB_ENDPOINT_DIR_IN;
    }

    public static Internal.EnumLiteMap<UsbEndPointDirection> internalGetValueMap() {
        return internalValueMap;
    }

    private UsbEndPointDirection(int value2) {
        this.value = value2;
    }
}
