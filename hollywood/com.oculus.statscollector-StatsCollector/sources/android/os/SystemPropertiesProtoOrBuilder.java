package android.os;

import android.os.SystemPropertiesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SystemPropertiesProtoOrBuilder extends MessageLiteOrBuilder {
    SystemPropertiesProto.AacDrc getAacDrc();

    SystemPropertiesProto.Aaudio getAaudio();

    int getAfFastTrackMultiplier();

    SystemPropertiesProto.Camera getCamera();

    SystemPropertiesProto.DalvikVm getDalvikVm();

    boolean getDrm64BitEnabled();

    boolean getDrmServiceEnabled();

    boolean getDumpstateDryRun();

    SystemPropertiesProto.Property getExtraProperties(int i);

    int getExtraPropertiesCount();

    List<SystemPropertiesProto.Property> getExtraPropertiesList();

    String getGsmSimOperatorNumeric();

    ByteString getGsmSimOperatorNumericBytes();

    boolean getHalInstrumentationEnable();

    SystemPropertiesProto.InitSvc getInitSvc();

    boolean getKeyguardNoRequireSim();

    String getLibcDebugMallocOptions();

    ByteString getLibcDebugMallocOptionsBytes();

    String getLibcDebugMallocProgram();

    ByteString getLibcDebugMallocProgramBytes();

    SystemPropertiesProto.Log getLog();

    boolean getMediaMediadrmserviceEnable();

    boolean getMediaRecorderShowManufacturerAndModel();

    SystemPropertiesProto.Persist getPersist();

    SystemPropertiesProto.PmDexopt getPmDexopt();

    SystemPropertiesProto.Ro getRo();

    String getSendbugPreferredDomain();

    ByteString getSendbugPreferredDomainBytes();

    int getServiceBootanimExit();

    SystemPropertiesProto.Sys getSys();

    int getTelephonyLteOnCdmaDevice();

    int getTombstonedMaxTombstoneCount();

    String getVoldDecrypt();

    ByteString getVoldDecryptBytes();

    int getVoldPostFsDataDone();

    int getVtsNativeServerOn();

    String getWifiDirectInterface();

    ByteString getWifiDirectInterfaceBytes();

    String getWifiInterface();

    ByteString getWifiInterfaceBytes();

    boolean hasAacDrc();

    boolean hasAaudio();

    boolean hasAfFastTrackMultiplier();

    boolean hasCamera();

    boolean hasDalvikVm();

    boolean hasDrm64BitEnabled();

    boolean hasDrmServiceEnabled();

    boolean hasDumpstateDryRun();

    boolean hasGsmSimOperatorNumeric();

    boolean hasHalInstrumentationEnable();

    boolean hasInitSvc();

    boolean hasKeyguardNoRequireSim();

    boolean hasLibcDebugMallocOptions();

    boolean hasLibcDebugMallocProgram();

    boolean hasLog();

    boolean hasMediaMediadrmserviceEnable();

    boolean hasMediaRecorderShowManufacturerAndModel();

    boolean hasPersist();

    boolean hasPmDexopt();

    boolean hasRo();

    boolean hasSendbugPreferredDomain();

    boolean hasServiceBootanimExit();

    boolean hasSys();

    boolean hasTelephonyLteOnCdmaDevice();

    boolean hasTombstonedMaxTombstoneCount();

    boolean hasVoldDecrypt();

    boolean hasVoldPostFsDataDone();

    boolean hasVtsNativeServerOn();

    boolean hasWifiDirectInterface();

    boolean hasWifiInterface();
}
