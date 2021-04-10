package android.service.battery;

import android.os.BatteryHealthEnum;
import android.os.BatteryPluggedStateEnum;
import android.os.BatteryStatusEnum;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BatteryServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAreUpdatesStopped();

    int getChargeCounter();

    BatteryHealthEnum getHealth();

    boolean getIsPresent();

    int getLevel();

    int getMaxChargingCurrent();

    int getMaxChargingVoltage();

    BatteryPluggedStateEnum getPlugged();

    int getScale();

    BatteryStatusEnum getStatus();

    String getTechnology();

    ByteString getTechnologyBytes();

    int getTemperature();

    int getVoltage();

    boolean hasAreUpdatesStopped();

    boolean hasChargeCounter();

    boolean hasHealth();

    boolean hasIsPresent();

    boolean hasLevel();

    boolean hasMaxChargingCurrent();

    boolean hasMaxChargingVoltage();

    boolean hasPlugged();

    boolean hasScale();

    boolean hasStatus();

    boolean hasTechnology();

    boolean hasTemperature();

    boolean hasVoltage();
}
