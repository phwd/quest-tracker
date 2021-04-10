package android.service;

import com.google.protobuf.Internal;

public enum UsbEndPointType implements Internal.EnumLite {
    USB_ENDPOINT_TYPE_XFER_CONTROL(0),
    USB_ENDPOINT_TYPE_XFER_ISOC(1),
    USB_ENDPOINT_TYPE_XFER_BULK(2),
    USB_ENDPOINT_TYPE_XFER_INT(3);
    
    public static final int USB_ENDPOINT_TYPE_XFER_BULK_VALUE = 2;
    public static final int USB_ENDPOINT_TYPE_XFER_CONTROL_VALUE = 0;
    public static final int USB_ENDPOINT_TYPE_XFER_INT_VALUE = 3;
    public static final int USB_ENDPOINT_TYPE_XFER_ISOC_VALUE = 1;
    private static final Internal.EnumLiteMap<UsbEndPointType> internalValueMap = new Internal.EnumLiteMap<UsbEndPointType>() {
        /* class android.service.UsbEndPointType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UsbEndPointType findValueByNumber(int number) {
            return UsbEndPointType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UsbEndPointType valueOf(int value2) {
        return forNumber(value2);
    }

    public static UsbEndPointType forNumber(int value2) {
        if (value2 == 0) {
            return USB_ENDPOINT_TYPE_XFER_CONTROL;
        }
        if (value2 == 1) {
            return USB_ENDPOINT_TYPE_XFER_ISOC;
        }
        if (value2 == 2) {
            return USB_ENDPOINT_TYPE_XFER_BULK;
        }
        if (value2 != 3) {
            return null;
        }
        return USB_ENDPOINT_TYPE_XFER_INT;
    }

    public static Internal.EnumLiteMap<UsbEndPointType> internalGetValueMap() {
        return internalValueMap;
    }

    private UsbEndPointType(int value2) {
        this.value = value2;
    }
}
