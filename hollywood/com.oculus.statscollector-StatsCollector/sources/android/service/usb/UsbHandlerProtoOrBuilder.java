package android.service.usb;

import android.service.usb.UsbHandlerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbHandlerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAdbEnabled();

    boolean getAudioAccessoryConnected();

    boolean getConfigured();

    boolean getConnected();

    UsbAccessoryProto getCurrentAccessory();

    UsbHandlerProto.Function getCurrentFunctions(int i);

    boolean getCurrentFunctionsApplied();

    int getCurrentFunctionsCount();

    List<UsbHandlerProto.Function> getCurrentFunctionsList();

    boolean getHideUsbNotification();

    boolean getHostConnected();

    String getKernelFunctionList();

    ByteString getKernelFunctionListBytes();

    String getKernelState();

    ByteString getKernelStateBytes();

    boolean getScreenLocked();

    UsbHandlerProto.Function getScreenUnlockedFunctions(int i);

    int getScreenUnlockedFunctionsCount();

    List<UsbHandlerProto.Function> getScreenUnlockedFunctionsList();

    boolean getSinkPower();

    boolean getSourcePower();

    boolean getUsbCharging();

    boolean hasAdbEnabled();

    boolean hasAudioAccessoryConnected();

    boolean hasConfigured();

    boolean hasConnected();

    boolean hasCurrentAccessory();

    boolean hasCurrentFunctionsApplied();

    boolean hasHideUsbNotification();

    boolean hasHostConnected();

    boolean hasKernelFunctionList();

    boolean hasKernelState();

    boolean hasScreenLocked();

    boolean hasSinkPower();

    boolean hasSourcePower();

    boolean hasUsbCharging();
}
