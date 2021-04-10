package com.facebook.mobileconfig;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FBMobileConfigTableOptimized extends FBMobileConfigTable {
    private int boolsMetaOffset;
    private int boolsOffset;
    private int doublesMetaOffset;
    private int doublesOffset;
    private final Map<Integer, Integer> epInfo = new HashMap();
    private int intsMetaOffset;
    private int intsOffset;
    private int loggingIdsOffset;
    private int longsMetaOffset;
    private int longsOffset;
    private int stringsMetaOffset;
    private int stringsOffset;

    @Nullable
    public static FBMobileConfigTableOptimized getRootAsFBMobileConfigTableOptimized(ByteBuffer byteBuffer) {
        FBMobileConfigTableOptimized fBMobileConfigTableOptimized = (FBMobileConfigTableOptimized) getRootAsFBMobileConfigTable(byteBuffer, new FBMobileConfigTableOptimized());
        try {
            if (fBMobileConfigTableOptimized.magic() == 123456) {
                if (fBMobileConfigTableOptimized.magic2() == 123456 || fBMobileConfigTableOptimized.magic2() == 0) {
                    if (!fBMobileConfigTableOptimized.initilizeOffsets()) {
                        return null;
                    }
                    return fBMobileConfigTableOptimized;
                }
            }
            BLog.i("FBMobileConfigTableOptimized", "Magic number does not match!  Got magic:%s magic2:%s", Integer.valueOf(fBMobileConfigTableOptimized.magic()), Integer.valueOf(fBMobileConfigTableOptimized.magic2()));
            return null;
        } catch (IndexOutOfBoundsException e) {
            BLog.w("FBMobileConfigTableOptimized", "Corrupted file, unexpected fbs offset", e);
            return null;
        }
    }

    private boolean initilizeOffsets() {
        this.boolsOffset = __offset(6);
        this.boolsMetaOffset = __offset(8);
        this.longsOffset = __offset(10);
        this.longsMetaOffset = __offset(12);
        this.intsOffset = __offset(14);
        this.intsMetaOffset = __offset(16);
        this.doublesOffset = __offset(18);
        this.doublesMetaOffset = __offset(20);
        this.stringsOffset = __offset(22);
        this.stringsMetaOffset = __offset(24);
        this.loggingIdsOffset = __offset(26);
        int emergencyPushInfoLength = emergencyPushInfoLength();
        if (emergencyPushInfoLength > 50) {
            BLog.i("FBMobileConfigTableOptimized", "Probably corrupted mctable data, epInfoLen:%s", Integer.valueOf(emergencyPushInfoLength));
            return false;
        }
        for (int i = 0; i < emergencyPushInfoLength; i++) {
            FBMobileConfigEPInfo emergencyPushInfo = emergencyPushInfo(i);
            if (emergencyPushInfo != null) {
                this.epInfo.put(Integer.valueOf(emergencyPushInfo.configIndex()), Integer.valueOf(emergencyPushInfo.version()));
            }
        }
        return true;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public byte bools(int i) {
        if (this.boolsOffset != 0) {
            return this.bb.get(__vector(this.boolsOffset) + (i * 1));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public int boolsMeta(int i) {
        if (this.boolsMetaOffset != 0) {
            return this.bb.getInt(__vector(this.boolsMetaOffset) + (i * 4));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public long longs(int i) {
        if (this.longsOffset != 0) {
            return this.bb.getLong(__vector(this.longsOffset) + (i * 8));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public int longsMeta(int i) {
        if (this.longsMetaOffset != 0) {
            return this.bb.getInt(__vector(this.longsMetaOffset) + (i * 4));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public double doubles(int i) {
        if (this.doublesOffset != 0) {
            return this.bb.getDouble(__vector(this.doublesOffset) + (i * 8));
        }
        return 0.0d;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public int doublesMeta(int i) {
        if (this.doublesMetaOffset != 0) {
            return this.bb.getInt(__vector(this.doublesMetaOffset) + (i * 4));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    @Nullable
    public String strings(int i) {
        int i2 = this.stringsOffset;
        if (i2 != 0) {
            return __string(__vector(i2) + (i * 4));
        }
        return null;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    public int stringsMeta(int i) {
        if (this.stringsMetaOffset != 0) {
            return this.bb.getInt(__vector(this.stringsMetaOffset) + (i * 4));
        }
        return 0;
    }

    @Override // com.facebook.mobileconfig.FBMobileConfigTable
    @Nullable
    public String loggingIds(int i) {
        int i2 = this.loggingIdsOffset;
        if (i2 != 0) {
            return __string(__vector(i2) + (i * 4));
        }
        return null;
    }
}
