package com.facebook.errorreporting.nightwatch;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NightwatchParser {
    private static final String TAG = "NightwatchParser";
    private final DataHistory batteryCapacityHistory = new DataHistory("battery_capacity");
    private final DataHistory batteryTemperatureHistory = new DataHistory("battery_temperature_10x");
    private final DataHistory connectionTypeHistory = new DataHistory("connection_type");
    private final DataHistory externalStorageMBHistory = new DataHistory("external_storage_mb");
    private final DataHistory fdCountHistory = new DataHistory("fd_count");
    private String logcat = "";
    private long mBackgroundToForegroundLastTimeMs = -1;
    private long mForegroundToBackgroundLastTimeMs = -1;
    private int mNightwatchCrashReason = -1;
    private int mNightwatchCrashStatus = -1;
    private long mNightwatchCrashTimeMs = 0;
    private int mNightwatchFb4aCrashReason = -1;
    private int mNightwatchFb4aCrashStatus = -1;
    private long mNightwatchFb4aCrashTimeMs = 0;
    private long mNightwatchFb4aCrashUptimeMs = 0;
    private long mNightwatchStartTimeMs = 0;
    private int mNightwatchWatcherCrashReason = -1;
    private int mNightwatchWatcherCrashStatus = -1;
    private long mNightwatchWatcherCrashTimeMs = 0;
    private byte mOomAdjAtKill = -1;
    private String mOomAdjHistoryAsString = "";
    private long mOomAdjLastTimeMs = 0;
    private String mOomScoreAdjFastHistoryAsString = "";
    private long mOomScoreAdjFastLastTimeMs = 0;
    private long mStallStatsChecksum = -1;
    @Nullable
    private TickInfo[] mTickInfoCircularBuffer = null;
    private int mTickInfoCount = -1;
    private TickInfo[] mTickInfoFastBuffer = new TickInfo[5];
    private int mTickInfoIndex = -1;
    private boolean mUseMmap = false;
    private int mVersion = -1;
    private byte mWatcherWaitState = -1;
    private final DataHistory memAvailableMBHistory = new DataHistory("mem_available_mb");
    private final DataHistory oomScoreAdjHistory = new DataHistory("oom_score_adj");
    private final DataHistory oomScoreHistory = new DataHistory("oom_score");
    private final DataHistory processCountHistory = new DataHistory("process_count");
    private final DataHistory processImportanceHistory = new DataHistory("process_importance");
    private final DataHistory rootStorageMBHistory = new DataHistory("root_storage_mb");
    private final DataHistory threadCountHistory = new DataHistory("thread_count");
    private final DataHistory trafficMobileRxMBHistory = new DataHistory("traffic_mobile_rx_mb");
    private final DataHistory trafficMobileTxMBHistory = new DataHistory("traffic_mobile_tx_mb");
    private final DataHistory trafficTotalRxMBHistory = new DataHistory("traffic_total_rx_mb");
    private final DataHistory trafficTotalTxMBHistory = new DataHistory("traffic_total_tx_mb");
    private int watchedFb4aPid = -1;

    public int getNightwatchCrashStatus() {
        return this.mNightwatchCrashStatus;
    }

    public int getNightwatchCrashReason() {
        return this.mNightwatchCrashReason;
    }

    public long getNightwatchCrashTimeMs() {
        return this.mNightwatchCrashTimeMs;
    }

    public static String getFormatVersion(File file) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                String formatVersion = getFormatVersion(dataInputStream);
                dataInputStream.close();
                return formatVersion;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException unused2) {
            return "";
        }
    }

    private static String getFormatVersion(DataInputStream dataInputStream) throws IOException {
        byte[] bArr = new byte[8];
        dataInputStream.read(bArr);
        return bArr[0] == 77 ? new String(bArr) : "";
    }

    private static String getFormatVersion(ByteBuffer byteBuffer) throws IOException {
        byte[] bArr = new byte[8];
        byteBuffer.get(bArr);
        return bArr[0] == 77 ? new String(bArr) : "";
    }

    @Nullable
    private static ByteBuffer createByteBufferFromFile(File file) {
        ByteBuffer byteBuffer = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[((int) file.length())];
                bufferedInputStream.read(bArr, 0, bArr.length);
                byteBuffer = ByteBuffer.wrap(bArr);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                bufferedInputStream.close();
                return byteBuffer;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (FileNotFoundException e) {
            BLog.e(TAG, e, "nightwatch log file %s not exist", file.getPath());
        } catch (IOException e2) {
            BLog.e(TAG, e2, "could not parse nightwatch log file %s", file.getPath());
        }
    }

    public void parse(File file) {
        ByteBuffer createByteBufferFromFile = createByteBufferFromFile(file);
        if (createByteBufferFromFile != null) {
            try {
                createByteBufferFromFile.mark();
                String trim = getFormatVersion(createByteBufferFromFile).trim();
                if (trim.startsWith("MMAP")) {
                    this.mUseMmap = true;
                    this.mVersion = Integer.valueOf(trim.substring(4)).intValue();
                    if (this.mVersion == 1) {
                        parse_mmap_001(createByteBufferFromFile);
                    } else if (this.mVersion == 2) {
                        parse_mmap_002(createByteBufferFromFile);
                    } else if (this.mVersion == 3) {
                        parse_mmap_003(createByteBufferFromFile);
                    } else if (this.mVersion == 4) {
                        parse_mmap_004(createByteBufferFromFile);
                    } else if (this.mVersion == 5) {
                        parse_mmap_005(createByteBufferFromFile);
                    } else if (this.mVersion == 6) {
                        parse_mmap_006(file, createByteBufferFromFile);
                    } else {
                        BLog.e(TAG, "unsupported nightwatch format %s", trim);
                    }
                } else {
                    this.mUseMmap = false;
                    createByteBufferFromFile.reset();
                    parse_nommap(createByteBufferFromFile);
                }
            } catch (IOException e) {
                BLog.e(TAG, e, "failed to parse nightwatch log file %s", file.getPath());
            } catch (BufferOverflowException e2) {
                BLog.e(TAG, e2, "failed to parse nightwatch log file %s", file.getPath());
            } catch (BufferUnderflowException e3) {
                BLog.e(TAG, e3, "failed to parse nighwatch log file %s", file.getPath());
            }
            BLog.i(TAG, "watcher signal %d", Integer.valueOf(this.mNightwatchCrashReason));
            BLog.i(TAG, "watcher status %d", Integer.valueOf(this.mNightwatchCrashStatus));
        }
    }

    private void parse_mmap_001(ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = byteBuffer.getLong();
            this.mOomAdjHistoryAsString = readByteAsHistory(byteBuffer);
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.from(byteBuffer);
            this.oomScoreAdjHistory.from(byteBuffer);
            this.processCountHistory.from(byteBuffer);
            this.batteryCapacityHistory.from(byteBuffer);
            this.memAvailableMBHistory.from(byteBuffer);
            this.fdCountHistory.from(byteBuffer);
            this.rootStorageMBHistory.from(byteBuffer);
            this.externalStorageMBHistory.from(byteBuffer);
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.mWatcherWaitState = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() + 3);
            this.batteryTemperatureHistory.from(byteBuffer);
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalRxMBHistory.from(byteBuffer);
            this.trafficTotalTxMBHistory.from(byteBuffer);
            this.trafficMobileRxMBHistory.from(byteBuffer);
            this.trafficMobileTxMBHistory.from(byteBuffer);
            this.connectionTypeHistory.from(byteBuffer);
            this.mOomScoreAdjFastLastTimeMs = byteBuffer.getLong();
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(byteBuffer);
            long j = byteBuffer.getLong();
            byte[] bArr = new byte[8192];
            byteBuffer.get(bArr);
            if (j == 0) {
                this.logcat = "";
            } else if (j <= 8192) {
                this.logcat = new String(bArr, 0, (int) j, Charset.defaultCharset());
            } else {
                int i5 = (int) (j % 8192);
                String str = new String(bArr, i5, 8192 - i5, Charset.defaultCharset());
                String str2 = new String(bArr, 0, i5, Charset.defaultCharset());
                this.logcat = str + str2;
            }
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + 2);
            this.threadCountHistory.from(byteBuffer);
            long j2 = this.mNightwatchFb4aCrashTimeMs;
            if (j2 != -1) {
                this.mNightwatchCrashTimeMs = j2;
            } else {
                long j3 = this.mNightwatchWatcherCrashTimeMs;
                if (j3 != -1) {
                    this.mNightwatchCrashTimeMs = j3;
                }
            }
            int i6 = this.mNightwatchFb4aCrashStatus;
            if (i6 != -1) {
                this.mNightwatchCrashStatus = i6;
            } else {
                int i7 = this.mNightwatchWatcherCrashStatus;
                if (i7 != -1) {
                    this.mNightwatchCrashStatus = i7;
                }
            }
            int i8 = this.mNightwatchFb4aCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
                return;
            }
            int i9 = this.mNightwatchWatcherCrashReason;
            if (i9 != -1) {
                this.mNightwatchCrashReason = i9;
            }
        } catch (Throwable th) {
            long j4 = this.mNightwatchFb4aCrashTimeMs;
            if (j4 != -1) {
                this.mNightwatchCrashTimeMs = j4;
            } else {
                long j5 = this.mNightwatchWatcherCrashTimeMs;
                if (j5 != -1) {
                    this.mNightwatchCrashTimeMs = j5;
                }
            }
            int i10 = this.mNightwatchFb4aCrashStatus;
            if (i10 != -1) {
                this.mNightwatchCrashStatus = i10;
            } else {
                int i11 = this.mNightwatchWatcherCrashStatus;
                if (i11 != -1) {
                    this.mNightwatchCrashStatus = i11;
                }
            }
            int i12 = this.mNightwatchFb4aCrashReason;
            if (i12 == -1) {
                int i13 = this.mNightwatchWatcherCrashReason;
                if (i13 != -1) {
                    this.mNightwatchCrashReason = i13;
                }
            } else {
                this.mNightwatchCrashReason = i12;
            }
            throw th;
        }
    }

    private void parse_mmap_002(ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = byteBuffer.getLong();
            this.mOomAdjHistoryAsString = readByteAsHistory(byteBuffer);
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.from(byteBuffer);
            this.oomScoreAdjHistory.from(byteBuffer);
            this.processCountHistory.from(byteBuffer);
            this.batteryCapacityHistory.from(byteBuffer);
            this.memAvailableMBHistory.from(byteBuffer);
            this.fdCountHistory.from(byteBuffer);
            this.rootStorageMBHistory.from(byteBuffer);
            this.externalStorageMBHistory.from(byteBuffer);
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.mWatcherWaitState = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() + 3);
            this.batteryTemperatureHistory.from(byteBuffer);
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalRxMBHistory.from(byteBuffer);
            this.trafficTotalTxMBHistory.from(byteBuffer);
            this.trafficMobileRxMBHistory.from(byteBuffer);
            this.trafficMobileTxMBHistory.from(byteBuffer);
            this.connectionTypeHistory.from(byteBuffer);
            this.mOomScoreAdjFastLastTimeMs = byteBuffer.getLong();
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(byteBuffer);
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + 2);
            this.threadCountHistory.from(byteBuffer);
            long j = this.mNightwatchFb4aCrashTimeMs;
            if (j != -1) {
                this.mNightwatchCrashTimeMs = j;
            } else {
                long j2 = this.mNightwatchWatcherCrashTimeMs;
                if (j2 != -1) {
                    this.mNightwatchCrashTimeMs = j2;
                }
            }
            int i5 = this.mNightwatchFb4aCrashStatus;
            if (i5 != -1) {
                this.mNightwatchCrashStatus = i5;
            } else {
                int i6 = this.mNightwatchWatcherCrashStatus;
                if (i6 != -1) {
                    this.mNightwatchCrashStatus = i6;
                }
            }
            int i7 = this.mNightwatchFb4aCrashReason;
            if (i7 != -1) {
                this.mNightwatchCrashReason = i7;
                return;
            }
            int i8 = this.mNightwatchWatcherCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
            }
        } catch (Throwable th) {
            long j3 = this.mNightwatchFb4aCrashTimeMs;
            if (j3 != -1) {
                this.mNightwatchCrashTimeMs = j3;
            } else {
                long j4 = this.mNightwatchWatcherCrashTimeMs;
                if (j4 != -1) {
                    this.mNightwatchCrashTimeMs = j4;
                }
            }
            int i9 = this.mNightwatchFb4aCrashStatus;
            if (i9 != -1) {
                this.mNightwatchCrashStatus = i9;
            } else {
                int i10 = this.mNightwatchWatcherCrashStatus;
                if (i10 != -1) {
                    this.mNightwatchCrashStatus = i10;
                }
            }
            int i11 = this.mNightwatchFb4aCrashReason;
            if (i11 == -1) {
                int i12 = this.mNightwatchWatcherCrashReason;
                if (i12 != -1) {
                    this.mNightwatchCrashReason = i12;
                }
            } else {
                this.mNightwatchCrashReason = i11;
            }
            throw th;
        }
    }

    private void parse_mmap_003(ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = byteBuffer.getLong();
            this.mOomAdjHistoryAsString = readByteAsHistory(byteBuffer);
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.from(byteBuffer);
            this.oomScoreAdjHistory.from(byteBuffer);
            this.processCountHistory.from(byteBuffer);
            this.batteryCapacityHistory.from(byteBuffer);
            this.memAvailableMBHistory.from(byteBuffer);
            this.fdCountHistory.from(byteBuffer);
            this.rootStorageMBHistory.from(byteBuffer);
            this.externalStorageMBHistory.from(byteBuffer);
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.mWatcherWaitState = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() + 3);
            this.batteryTemperatureHistory.from(byteBuffer);
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalRxMBHistory.from(byteBuffer);
            this.trafficTotalTxMBHistory.from(byteBuffer);
            this.trafficMobileRxMBHistory.from(byteBuffer);
            this.trafficMobileTxMBHistory.from(byteBuffer);
            this.connectionTypeHistory.from(byteBuffer);
            this.mOomScoreAdjFastLastTimeMs = byteBuffer.getLong();
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(byteBuffer);
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + 2);
            this.threadCountHistory.from(byteBuffer);
            this.mTickInfoCount = byteBuffer.getInt();
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = byteBuffer.getInt();
                this.mStallStatsChecksum = byteBuffer.getLong();
                for (int i5 = 0; i5 < this.mTickInfoCount; i5++) {
                    TickInfo tickInfo = new TickInfo();
                    tickInfo.actualUptimeMs = byteBuffer.getLong();
                    tickInfo.expectedUptimeMs = byteBuffer.getLong();
                    tickInfo.nextExpectedUptimeMs = byteBuffer.getLong();
                    tickInfo.relativeThreadTimeMs = byteBuffer.getLong();
                    this.mTickInfoCircularBuffer[i5] = tickInfo;
                }
            }
            long j = this.mNightwatchFb4aCrashTimeMs;
            if (j != -1) {
                this.mNightwatchCrashTimeMs = j;
            } else {
                long j2 = this.mNightwatchWatcherCrashTimeMs;
                if (j2 != -1) {
                    this.mNightwatchCrashTimeMs = j2;
                }
            }
            int i6 = this.mNightwatchFb4aCrashStatus;
            if (i6 != -1) {
                this.mNightwatchCrashStatus = i6;
            } else {
                int i7 = this.mNightwatchWatcherCrashStatus;
                if (i7 != -1) {
                    this.mNightwatchCrashStatus = i7;
                }
            }
            int i8 = this.mNightwatchFb4aCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
                return;
            }
            int i9 = this.mNightwatchWatcherCrashReason;
            if (i9 != -1) {
                this.mNightwatchCrashReason = i9;
            }
        } catch (Throwable th) {
            long j3 = this.mNightwatchFb4aCrashTimeMs;
            if (j3 != -1) {
                this.mNightwatchCrashTimeMs = j3;
            } else {
                long j4 = this.mNightwatchWatcherCrashTimeMs;
                if (j4 != -1) {
                    this.mNightwatchCrashTimeMs = j4;
                }
            }
            int i10 = this.mNightwatchFb4aCrashStatus;
            if (i10 != -1) {
                this.mNightwatchCrashStatus = i10;
            } else {
                int i11 = this.mNightwatchWatcherCrashStatus;
                if (i11 != -1) {
                    this.mNightwatchCrashStatus = i11;
                }
            }
            int i12 = this.mNightwatchFb4aCrashReason;
            if (i12 == -1) {
                int i13 = this.mNightwatchWatcherCrashReason;
                if (i13 != -1) {
                    this.mNightwatchCrashReason = i13;
                }
            } else {
                this.mNightwatchCrashReason = i12;
            }
            throw th;
        }
    }

    private void parse_mmap_004(ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashUptimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = byteBuffer.getLong();
            this.mOomAdjHistoryAsString = readByteAsHistory(byteBuffer);
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.from(byteBuffer);
            this.oomScoreAdjHistory.from(byteBuffer);
            this.processCountHistory.from(byteBuffer);
            this.batteryCapacityHistory.from(byteBuffer);
            this.memAvailableMBHistory.from(byteBuffer);
            this.fdCountHistory.from(byteBuffer);
            this.rootStorageMBHistory.from(byteBuffer);
            this.externalStorageMBHistory.from(byteBuffer);
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.mWatcherWaitState = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() + 3);
            this.batteryTemperatureHistory.from(byteBuffer);
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalRxMBHistory.from(byteBuffer);
            this.trafficTotalTxMBHistory.from(byteBuffer);
            this.trafficMobileRxMBHistory.from(byteBuffer);
            this.trafficMobileTxMBHistory.from(byteBuffer);
            this.connectionTypeHistory.from(byteBuffer);
            this.mOomScoreAdjFastLastTimeMs = byteBuffer.getLong();
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(byteBuffer);
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + 2);
            this.threadCountHistory.from(byteBuffer);
            this.mTickInfoCount = byteBuffer.getInt();
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = byteBuffer.getInt();
                this.mStallStatsChecksum = byteBuffer.getLong();
                for (int i5 = 0; i5 < this.mTickInfoCount; i5++) {
                    TickInfo tickInfo = new TickInfo();
                    tickInfo.actualUptimeMs = byteBuffer.getLong();
                    tickInfo.expectedUptimeMs = byteBuffer.getLong();
                    tickInfo.nextExpectedUptimeMs = byteBuffer.getLong();
                    tickInfo.relativeThreadTimeMs = byteBuffer.getLong();
                    this.mTickInfoCircularBuffer[i5] = tickInfo;
                }
            }
            long j = this.mNightwatchFb4aCrashTimeMs;
            if (j != -1) {
                this.mNightwatchCrashTimeMs = j;
            } else {
                long j2 = this.mNightwatchWatcherCrashTimeMs;
                if (j2 != -1) {
                    this.mNightwatchCrashTimeMs = j2;
                }
            }
            int i6 = this.mNightwatchFb4aCrashStatus;
            if (i6 != -1) {
                this.mNightwatchCrashStatus = i6;
            } else {
                int i7 = this.mNightwatchWatcherCrashStatus;
                if (i7 != -1) {
                    this.mNightwatchCrashStatus = i7;
                }
            }
            int i8 = this.mNightwatchFb4aCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
                return;
            }
            int i9 = this.mNightwatchWatcherCrashReason;
            if (i9 != -1) {
                this.mNightwatchCrashReason = i9;
            }
        } catch (Throwable th) {
            long j3 = this.mNightwatchFb4aCrashTimeMs;
            if (j3 != -1) {
                this.mNightwatchCrashTimeMs = j3;
            } else {
                long j4 = this.mNightwatchWatcherCrashTimeMs;
                if (j4 != -1) {
                    this.mNightwatchCrashTimeMs = j4;
                }
            }
            int i10 = this.mNightwatchFb4aCrashStatus;
            if (i10 != -1) {
                this.mNightwatchCrashStatus = i10;
            } else {
                int i11 = this.mNightwatchWatcherCrashStatus;
                if (i11 != -1) {
                    this.mNightwatchCrashStatus = i11;
                }
            }
            int i12 = this.mNightwatchFb4aCrashReason;
            if (i12 == -1) {
                int i13 = this.mNightwatchWatcherCrashReason;
                if (i13 != -1) {
                    this.mNightwatchCrashReason = i13;
                }
            } else {
                this.mNightwatchCrashReason = i12;
            }
            throw th;
        }
    }

    private void parse_mmap_005(ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            DataHistory.setNightwatcherStartTimeMs(this.mNightwatchStartTimeMs);
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashUptimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = byteBuffer.getLong();
            this.mOomAdjHistoryAsString = readByteAsHistory(byteBuffer);
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreHistory.from_005(byteBuffer);
            this.oomScoreAdjHistory.from_005(byteBuffer);
            this.processCountHistory.from_005(byteBuffer);
            this.batteryCapacityHistory.from_005(byteBuffer);
            this.memAvailableMBHistory.from_005(byteBuffer);
            this.fdCountHistory.from_005(byteBuffer);
            this.rootStorageMBHistory.from_005(byteBuffer);
            this.externalStorageMBHistory.from_005(byteBuffer);
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.mWatcherWaitState = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() + 3);
            this.batteryTemperatureHistory.from_005(byteBuffer);
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalRxMBHistory.from_005(byteBuffer);
            this.trafficTotalTxMBHistory.from_005(byteBuffer);
            this.trafficMobileRxMBHistory.from_005(byteBuffer);
            this.trafficMobileTxMBHistory.from_005(byteBuffer);
            this.connectionTypeHistory.from_005(byteBuffer);
            this.mOomScoreAdjFastLastTimeMs = byteBuffer.getLong();
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(byteBuffer);
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            this.processImportanceHistory.setValueAtStart(byteBuffer.getShort());
            this.threadCountHistory.from_005(byteBuffer);
            this.processImportanceHistory.from_005(byteBuffer);
            this.mTickInfoCount = byteBuffer.getInt();
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = byteBuffer.getInt();
                this.mStallStatsChecksum = byteBuffer.getLong();
                for (int i5 = 0; i5 < this.mTickInfoCount; i5++) {
                    TickInfo tickInfo = new TickInfo();
                    tickInfo.actualUptimeMs = byteBuffer.getLong();
                    tickInfo.expectedUptimeMs = byteBuffer.getLong();
                    tickInfo.nextExpectedUptimeMs = byteBuffer.getLong();
                    tickInfo.relativeThreadTimeMs = byteBuffer.getLong();
                    this.mTickInfoCircularBuffer[i5] = tickInfo;
                }
            }
            long j = this.mNightwatchFb4aCrashTimeMs;
            if (j != -1) {
                this.mNightwatchCrashTimeMs = j;
            } else {
                long j2 = this.mNightwatchWatcherCrashTimeMs;
                if (j2 != -1) {
                    this.mNightwatchCrashTimeMs = j2;
                }
            }
            int i6 = this.mNightwatchFb4aCrashStatus;
            if (i6 != -1) {
                this.mNightwatchCrashStatus = i6;
            } else {
                int i7 = this.mNightwatchWatcherCrashStatus;
                if (i7 != -1) {
                    this.mNightwatchCrashStatus = i7;
                }
            }
            int i8 = this.mNightwatchFb4aCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
                return;
            }
            int i9 = this.mNightwatchWatcherCrashReason;
            if (i9 != -1) {
                this.mNightwatchCrashReason = i9;
            }
        } catch (Throwable th) {
            long j3 = this.mNightwatchFb4aCrashTimeMs;
            if (j3 != -1) {
                this.mNightwatchCrashTimeMs = j3;
            } else {
                long j4 = this.mNightwatchWatcherCrashTimeMs;
                if (j4 != -1) {
                    this.mNightwatchCrashTimeMs = j4;
                }
            }
            int i10 = this.mNightwatchFb4aCrashStatus;
            if (i10 != -1) {
                this.mNightwatchCrashStatus = i10;
            } else {
                int i11 = this.mNightwatchWatcherCrashStatus;
                if (i11 != -1) {
                    this.mNightwatchCrashStatus = i11;
                }
            }
            int i12 = this.mNightwatchFb4aCrashReason;
            if (i12 == -1) {
                int i13 = this.mNightwatchWatcherCrashReason;
                if (i13 != -1) {
                    this.mNightwatchCrashReason = i13;
                }
            } else {
                this.mNightwatchCrashReason = i12;
            }
            throw th;
        }
    }

    private void parse_sub_second_level_mmap_006(File file) {
        ByteBuffer createByteBufferFromFile;
        File file2 = new File(file.getPath() + ".subsecond");
        if (file2.exists() && (createByteBufferFromFile = createByteBufferFromFile(file2)) != null) {
            try {
                createByteBufferFromFile.mark();
                this.mOomAdjLastTimeMs = createByteBufferFromFile.getLong();
                this.mOomAdjHistoryAsString = readByteAsHistory(createByteBufferFromFile);
                this.mOomScoreAdjFastLastTimeMs = createByteBufferFromFile.getLong();
                this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(createByteBufferFromFile);
                for (int i = 0; i < 5; i++) {
                    TickInfo tickInfo = new TickInfo();
                    tickInfo.actualUptimeMs = createByteBufferFromFile.getLong();
                    tickInfo.expectedUptimeMs = createByteBufferFromFile.getLong();
                    tickInfo.nextExpectedUptimeMs = createByteBufferFromFile.getLong();
                    tickInfo.relativeThreadTimeMs = createByteBufferFromFile.getLong();
                    this.mTickInfoFastBuffer[i] = tickInfo;
                }
                if (createByteBufferFromFile.remaining() > 0) {
                    this.mForegroundToBackgroundLastTimeMs = createByteBufferFromFile.getLong();
                    this.mBackgroundToForegroundLastTimeMs = createByteBufferFromFile.getLong();
                }
                BLog.i(TAG, "parsed nightwatch log file %s", file2.getPath());
            } catch (BufferOverflowException e) {
                BLog.e(TAG, e, "could not parse sub second mmap");
            }
        }
    }

    private void parse_second_level_mmap_006(File file) {
        ByteBuffer createByteBufferFromFile;
        File file2 = new File(file.getPath() + ".second");
        if (file2.exists() && (createByteBufferFromFile = createByteBufferFromFile(file2)) != null) {
            try {
                createByteBufferFromFile.mark();
                this.oomScoreHistory.fast_update_from(createByteBufferFromFile);
                this.oomScoreAdjHistory.fast_update_from(createByteBufferFromFile);
                this.processCountHistory.fast_update_from(createByteBufferFromFile);
                this.threadCountHistory.fast_update_from(createByteBufferFromFile);
                this.memAvailableMBHistory.fast_update_from(createByteBufferFromFile);
                this.processImportanceHistory.fast_update_from(createByteBufferFromFile);
                this.fdCountHistory.fast_update_from(createByteBufferFromFile);
                this.mTickInfoCount = createByteBufferFromFile.getInt();
                if (this.mTickInfoCount > 0) {
                    this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                    this.mTickInfoIndex = createByteBufferFromFile.getInt();
                    this.mStallStatsChecksum = createByteBufferFromFile.getLong();
                    for (int i = 0; i < this.mTickInfoCount; i++) {
                        TickInfo tickInfo = new TickInfo();
                        tickInfo.actualUptimeMs = createByteBufferFromFile.getLong();
                        tickInfo.expectedUptimeMs = createByteBufferFromFile.getLong();
                        tickInfo.nextExpectedUptimeMs = createByteBufferFromFile.getLong();
                        tickInfo.relativeThreadTimeMs = createByteBufferFromFile.getLong();
                        this.mTickInfoCircularBuffer[i] = tickInfo;
                    }
                }
                BLog.i(TAG, "parsed nightwatch log file %s", file2.getPath());
            } catch (BufferOverflowException e) {
                BLog.e(TAG, e, "failed to parse nightwatch log file %s", file2.getPath());
            }
        }
    }

    private void parse_mmap_006(File file, ByteBuffer byteBuffer) {
        try {
            this.mNightwatchStartTimeMs = byteBuffer.getLong();
            DataHistory.setNightwatcherStartTimeMs(this.mNightwatchStartTimeMs);
            this.mNightwatchFb4aCrashTimeMs = byteBuffer.getLong();
            this.mNightwatchFb4aCrashUptimeMs = byteBuffer.getLong();
            this.mNightwatchWatcherCrashTimeMs = byteBuffer.getLong();
            int i = byteBuffer.getInt();
            if (i != -1) {
                this.mNightwatchFb4aCrashStatus = i;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                this.mNightwatchFb4aCrashReason = i2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                this.mNightwatchWatcherCrashStatus = i3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int i4 = byteBuffer.getInt();
            if (i4 != -1) {
                this.mNightwatchWatcherCrashReason = i4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mWatcherWaitState = byteBuffer.get();
            this.mOomAdjAtKill = byteBuffer.get();
            this.batteryCapacityHistory.setValueAtStart((short) byteBuffer.get());
            byteBuffer.position(byteBuffer.position() + 1);
            this.processCountHistory.setValueAtStart(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + 2);
            this.oomScoreHistory.setValueAtStart(byteBuffer.getShort());
            this.oomScoreAdjHistory.setValueAtStart(byteBuffer.getShort());
            this.memAvailableMBHistory.setValueAtStart(byteBuffer.getShort());
            this.fdCountHistory.setValueAtStart(byteBuffer.getShort());
            this.rootStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.externalStorageMBHistory.setValueAtStart(byteBuffer.getShort());
            this.batteryTemperatureHistory.setValueAtStart(byteBuffer.getShort());
            this.connectionTypeHistory.setValueAtStart(byteBuffer.getShort());
            this.trafficTotalRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficTotalTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileRxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.trafficMobileTxMBHistory.setValueAtStart((long) byteBuffer.getInt());
            this.oomScoreHistory.slow_update_from(byteBuffer);
            this.oomScoreAdjHistory.slow_update_from(byteBuffer);
            this.processCountHistory.slow_update_from(byteBuffer);
            this.batteryCapacityHistory.from_005(byteBuffer);
            this.memAvailableMBHistory.slow_update_from(byteBuffer);
            this.fdCountHistory.slow_update_from(byteBuffer);
            this.rootStorageMBHistory.from_005(byteBuffer);
            this.externalStorageMBHistory.from_005(byteBuffer);
            this.batteryTemperatureHistory.from_005(byteBuffer);
            this.trafficTotalRxMBHistory.from_005(byteBuffer);
            this.trafficTotalTxMBHistory.from_005(byteBuffer);
            this.trafficMobileRxMBHistory.from_005(byteBuffer);
            this.trafficMobileTxMBHistory.from_005(byteBuffer);
            this.connectionTypeHistory.from_005(byteBuffer);
            this.watchedFb4aPid = byteBuffer.getInt();
            this.threadCountHistory.setValueAtStart(byteBuffer.getShort());
            this.processImportanceHistory.setValueAtStart(byteBuffer.getShort());
            this.threadCountHistory.slow_update_from(byteBuffer);
            this.processImportanceHistory.slow_update_from(byteBuffer);
            BLog.i(TAG, "parsed nightwatch log file %s", file.getPath());
        } catch (BufferOverflowException e) {
            BLog.e(TAG, e, "failed to parse nightwatch log file %s", file.getPath());
        }
        long j = this.mNightwatchFb4aCrashTimeMs;
        if (j != -1) {
            this.mNightwatchCrashTimeMs = j;
        } else {
            long j2 = this.mNightwatchWatcherCrashTimeMs;
            if (j2 != -1) {
                this.mNightwatchCrashTimeMs = j2;
            }
        }
        int i5 = this.mNightwatchFb4aCrashStatus;
        if (i5 != -1) {
            this.mNightwatchCrashStatus = i5;
        } else {
            int i6 = this.mNightwatchWatcherCrashStatus;
            if (i6 != -1) {
                this.mNightwatchCrashStatus = i6;
            }
        }
        int i7 = this.mNightwatchFb4aCrashReason;
        if (i7 != -1) {
            this.mNightwatchCrashReason = i7;
        } else {
            int i8 = this.mNightwatchWatcherCrashReason;
            if (i8 != -1) {
                this.mNightwatchCrashReason = i8;
            }
        }
        parse_sub_second_level_mmap_006(file);
        parse_second_level_mmap_006(file);
    }

    private static String readByteAsHistory(ByteBuffer byteBuffer) {
        long j = byteBuffer.getLong();
        if (j == -1) {
            return "";
        }
        byte[] bArr = {-1, -1, -1, -1, -1, -1, -1, -1};
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr[length] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < bArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(Byte.toString(bArr[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String readLogcat(long j, int i, byte[] bArr) {
        if (j == 0) {
            return "";
        }
        long j2 = (long) i;
        if (j <= j2) {
            return new String(bArr, 0, (int) j, Charset.defaultCharset());
        }
        int i2 = (int) (j % j2);
        String str = new String(bArr, i2, i - i2, Charset.defaultCharset());
        String str2 = new String(bArr, 0, i2, Charset.defaultCharset());
        return str + str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032 A[SYNTHETIC, Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049 A[SYNTHETIC, Splitter:B:17:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060 A[SYNTHETIC, Splitter:B:21:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parse_nommap(java.nio.ByteBuffer r6) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.errorreporting.nightwatch.NightwatchParser.parse_nommap(java.nio.ByteBuffer):void");
    }

    public Map<String, String> getEntries() {
        long j;
        long j2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = this.mNightwatchCrashStatus;
        if (i > -1) {
            linkedHashMap.put("nightwatch_status", String.valueOf(i));
        }
        int i2 = this.mNightwatchCrashReason;
        if (i2 > -1) {
            linkedHashMap.put("nightwatch_reason", String.valueOf(i2));
        }
        int i3 = 1;
        long max = max(this.mNightwatchCrashTimeMs, this.mOomScoreAdjFastLastTimeMs, this.mOomAdjLastTimeMs, this.oomScoreHistory.getLastTimeMs(), this.oomScoreAdjHistory.getLastTimeMs(), this.memAvailableMBHistory.getLastTimeMs(), this.threadCountHistory.getLastTimeMs(), this.processCountHistory.getLastTimeMs());
        if (max > -1) {
            linkedHashMap.put("nightwatch_crash_time_ms", String.valueOf(max));
        }
        if (!this.mUseMmap) {
            return linkedHashMap;
        }
        int i4 = this.mNightwatchFb4aCrashStatus;
        if (i4 > -1) {
            linkedHashMap.put("nightwatch_fb4a_status", String.valueOf(i4));
        }
        int i5 = this.mNightwatchFb4aCrashReason;
        if (i5 > -1) {
            linkedHashMap.put("nightwatch_fb4a_reason", String.valueOf(i5));
        }
        long j3 = this.mNightwatchFb4aCrashTimeMs;
        if (j3 > 0) {
            linkedHashMap.put("nightwatch_fb4a_crash_time_ms", String.valueOf(j3));
        }
        long j4 = this.mNightwatchFb4aCrashUptimeMs;
        if (j4 > 0) {
            linkedHashMap.put("nightwatch_fb4a_crash_uptime_ms", String.valueOf(j4));
        }
        int i6 = this.mNightwatchWatcherCrashStatus;
        if (i6 > -1) {
            linkedHashMap.put("nightwatch_watcher_status", String.valueOf(i6));
        }
        int i7 = this.mNightwatchWatcherCrashReason;
        if (i7 > -1) {
            linkedHashMap.put("nightwatch_watcher_reason", String.valueOf(i7));
        }
        long j5 = this.mNightwatchWatcherCrashTimeMs;
        if (j5 > 0) {
            linkedHashMap.put("nightwatch_watcher_crash_time_ms", String.valueOf(j5));
        }
        long j6 = this.mOomAdjLastTimeMs;
        if (j6 > 0) {
            linkedHashMap.put("nightwatch_oom_adj_last_time_ms", String.valueOf(j6));
        }
        byte b = this.mOomAdjAtKill;
        if (b != -1) {
            linkedHashMap.put("nightwatch_oom_adj_at_kill", String.valueOf((int) b));
        }
        if (!this.mOomAdjHistoryAsString.isEmpty()) {
            linkedHashMap.put("nightwatch_oom_adj_history", this.mOomAdjHistoryAsString);
        }
        long j7 = this.mOomScoreAdjFastLastTimeMs;
        if (j7 > 0) {
            linkedHashMap.put("nightwatch_oom_score_adj_fast_last_time_ms", String.valueOf(j7));
        }
        if (!this.mOomScoreAdjFastHistoryAsString.isEmpty()) {
            linkedHashMap.put("nightwatch_oom_score_adj_fast_history", this.mOomScoreAdjFastHistoryAsString);
        }
        long j8 = this.mForegroundToBackgroundLastTimeMs;
        if (j8 > 0) {
            linkedHashMap.put("nightwatch_foreground_to_background_last_time_ms", String.valueOf(j8));
        }
        long j9 = this.mBackgroundToForegroundLastTimeMs;
        if (j9 > 0) {
            linkedHashMap.put("nightwatch_background_to_foreground_last_time_ms", String.valueOf(j9));
        }
        long j10 = this.mNightwatchStartTimeMs;
        if (j10 != -1) {
            linkedHashMap.put("night_watch_start_time_ms", String.valueOf(j10));
        }
        byte b2 = this.mWatcherWaitState;
        if (b2 != -1) {
            linkedHashMap.put("night_watch_wait_state", String.valueOf((int) b2));
        }
        this.oomScoreHistory.getEntries(linkedHashMap);
        this.oomScoreAdjHistory.getEntries(linkedHashMap);
        this.threadCountHistory.getEntries(linkedHashMap);
        this.processCountHistory.getEntries(linkedHashMap);
        this.batteryCapacityHistory.getEntries(linkedHashMap);
        this.memAvailableMBHistory.getEntries(linkedHashMap);
        this.fdCountHistory.getEntries(linkedHashMap);
        this.rootStorageMBHistory.getEntries(linkedHashMap);
        this.externalStorageMBHistory.getEntries(linkedHashMap);
        this.batteryTemperatureHistory.getEntries(linkedHashMap);
        this.trafficTotalRxMBHistory.getEntries(linkedHashMap);
        this.trafficTotalTxMBHistory.getEntries(linkedHashMap);
        this.trafficMobileRxMBHistory.getEntries(linkedHashMap);
        this.trafficMobileTxMBHistory.getEntries(linkedHashMap);
        this.connectionTypeHistory.getEntries(linkedHashMap);
        this.processImportanceHistory.getEntries(linkedHashMap);
        if (!this.logcat.isEmpty()) {
            linkedHashMap.put("saved_logcat", this.logcat);
        }
        int i8 = this.watchedFb4aPid;
        if (i8 != -1) {
            linkedHashMap.put("watched_pid", String.valueOf(i8));
        }
        if (this.mTickInfoCircularBuffer != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("tickInfoCount", this.mTickInfoCount);
                jSONObject.put("nightwatchChecksum", this.mStallStatsChecksum);
                jSONObject.put("dumpUptimeMs", String.valueOf(this.mNightwatchFb4aCrashUptimeMs));
                long j11 = ((((((((((long) this.mTickInfoIndex) + 0) * 31) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].actualUptimeMs) * 31) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].expectedUptimeMs) * 31) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].nextExpectedUptimeMs) * 31) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].relativeThreadTimeMs;
                jSONObject.put("expectedChecksum", j11);
                jSONObject.put("checksumMatches", String.valueOf(j11 == this.mStallStatsChecksum));
                JSONArray jSONArray = new JSONArray();
                long j12 = 0;
                int i9 = 0;
                while (i9 < this.mTickInfoCount) {
                    TickInfo tickInfo = this.mTickInfoCircularBuffer[((this.mTickInfoIndex + i3) + i9) % this.mTickInfoCount];
                    if (tickInfo.actualUptimeMs > 0) {
                        long max2 = Math.max(tickInfo.actualUptimeMs, j12);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("actualUptimeMs", tickInfo.actualUptimeMs);
                        if (tickInfo.expectedUptimeMs != -1) {
                            j2 = max2;
                            jSONObject2.put("delayMs", tickInfo.actualUptimeMs - tickInfo.expectedUptimeMs);
                        } else {
                            j2 = max2;
                        }
                        jSONObject2.put("nextExpectedUptimeMs", tickInfo.nextExpectedUptimeMs);
                        if (tickInfo.relativeThreadTimeMs != -1) {
                            jSONObject2.put("relativeThreadTimeMs", tickInfo.relativeThreadTimeMs);
                        }
                        jSONArray.put(jSONObject2);
                        j12 = j2;
                    }
                    i9++;
                    i3 = 1;
                }
                int i10 = 0;
                while (i10 < this.mTickInfoFastBuffer.length) {
                    if (this.mTickInfoFastBuffer[i10].actualUptimeMs > j12) {
                        TickInfo tickInfo2 = this.mTickInfoFastBuffer[i10];
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("actualUptimeMs", tickInfo2.actualUptimeMs);
                        if (tickInfo2.expectedUptimeMs != -1) {
                            j = j12;
                            jSONObject3.put("delayMs", tickInfo2.actualUptimeMs - tickInfo2.expectedUptimeMs);
                        } else {
                            j = j12;
                        }
                        jSONObject3.put("nextExpectedUptimeMs", tickInfo2.nextExpectedUptimeMs);
                        if (tickInfo2.relativeThreadTimeMs != -1) {
                            jSONObject3.put("relativeThreadTimeMs", tickInfo2.relativeThreadTimeMs);
                        }
                        jSONArray.put(jSONObject3);
                    } else {
                        j = j12;
                    }
                    i10++;
                    j12 = j;
                }
                jSONObject.put("tickInfos", jSONArray);
                jSONObject.put("source", "UFAD mmap");
                linkedHashMap.put("stall_stats", jSONObject.toString());
            } catch (JSONException e) {
                BLog.e(TAG, "error serializing json", e);
                linkedHashMap.put("stall_stats", "\"JSON error\"");
            }
        }
        return linkedHashMap;
    }

    private static long max(long... jArr) {
        long j = Long.MIN_VALUE;
        for (long j2 : jArr) {
            if (j2 > j) {
                j = j2;
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    public static class TickInfo {
        long actualUptimeMs;
        long expectedUptimeMs;
        long nextExpectedUptimeMs;
        long relativeThreadTimeMs;

        private TickInfo() {
        }
    }
}
