package com.android.server;

import com.android.server.ForceAppStandbyTrackerProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ForceAppStandbyTrackerProtoOrBuilder extends MessageLiteOrBuilder {
    int getActiveUids(int i);

    int getActiveUidsCount();

    List<Integer> getActiveUidsList();

    ForceAppStandbyTrackerProto.ExemptedPackage getExemptedPackages(int i);

    int getExemptedPackagesCount();

    List<ForceAppStandbyTrackerProto.ExemptedPackage> getExemptedPackagesList();

    boolean getForceAllAppsStandby();

    boolean getForceAllAppsStandbyForSmallBattery();

    int getForegroundUids(int i);

    int getForegroundUidsCount();

    List<Integer> getForegroundUidsList();

    boolean getIsPluggedIn();

    boolean getIsSmallBatteryDevice();

    int getPowerSaveUserWhitelistAppIds(int i);

    int getPowerSaveUserWhitelistAppIdsCount();

    List<Integer> getPowerSaveUserWhitelistAppIdsList();

    int getPowerSaveWhitelistAppIds(int i);

    int getPowerSaveWhitelistAppIdsCount();

    List<Integer> getPowerSaveWhitelistAppIdsList();

    ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackages getRunAnyInBackgroundRestrictedPackages(int i);

    int getRunAnyInBackgroundRestrictedPackagesCount();

    List<ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackages> getRunAnyInBackgroundRestrictedPackagesList();

    StatLoggerProto getStats();

    int getTempPowerSaveWhitelistAppIds(int i);

    int getTempPowerSaveWhitelistAppIdsCount();

    List<Integer> getTempPowerSaveWhitelistAppIdsList();

    boolean hasForceAllAppsStandby();

    boolean hasForceAllAppsStandbyForSmallBattery();

    boolean hasIsPluggedIn();

    boolean hasIsSmallBatteryDevice();

    boolean hasStats();
}
