package android.service;

import com.google.protobuf.Internal;

public enum UsbConnectionRecordMode implements Internal.EnumLite {
    USB_CONNECTION_RECORD_MODE_CONNECT(0),
    USB_CONNECTION_RECORD_MODE_CONNECT_BADPARSE(1),
    USB_CONNECTION_RECORD_MODE_CONNECT_BADDEVICE(2),
    USB_CONNECTION_RECORD_MODE_DISCONNECT(-1);
    
    public static final int USB_CONNECTION_RECORD_MODE_CONNECT_BADDEVICE_VALUE = 2;
    public static final int USB_CONNECTION_RECORD_MODE_CONNECT_BADPARSE_VALUE = 1;
    public static final int USB_CONNECTION_RECORD_MODE_CONNECT_VALUE = 0;
    public static final int USB_CONNECTION_RECORD_MODE_DISCONNECT_VALUE = -1;
    private static final Internal.EnumLiteMap<UsbConnectionRecordMode> internalValueMap = new Internal.EnumLiteMap<UsbConnectionRecordMode>() {
        /* class android.service.UsbConnectionRecordMode.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UsbConnectionRecordMode findValueByNumber(int number) {
            return UsbConnectionRecordMode.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UsbConnectionRecordMode valueOf(int value2) {
        return forNumber(value2);
    }

    public static UsbConnectionRecordMode forNumber(int value2) {
        if (value2 == -1) {
            return USB_CONNECTION_RECORD_MODE_DISCONNECT;
        }
        if (value2 == 0) {
            return USB_CONNECTION_RECORD_MODE_CONNECT;
        }
        if (value2 == 1) {
            return USB_CONNECTION_RECORD_MODE_CONNECT_BADPARSE;
        }
        if (value2 != 2) {
            return null;
        }
        return USB_CONNECTION_RECORD_MODE_CONNECT_BADDEVICE;
    }

    public static Internal.EnumLiteMap<UsbConnectionRecordMode> internalGetValueMap() {
        return internalValueMap;
    }

    private UsbConnectionRecordMode(int value2) {
        this.value = value2;
    }
}
