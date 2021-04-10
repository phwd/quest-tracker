package com.facebook.errorreporting.nightwatch;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.systrace.Systrace;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NightwatchParser {
    private static final String TAG = NightwatchParser.class.getSimpleName();
    private final DataHistory batteryCapacityHistory = new DataHistory("battery_capacity");
    private final DataHistory batteryTemperatureHistory = new DataHistory("battery_temperature_10x");
    private final DataHistory connectionTypeHistory = new DataHistory("connection_type");
    private final DataHistory externalStorageMBHistory = new DataHistory("external_storage_mb");
    private final DataHistory fdCountHistory = new DataHistory("fd_count");
    private String logcat = "";
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

    public static String getFormatVersion(File logFile) {
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(logFile));
            try {
                String formatVersion = getFormatVersion(reader);
                reader.close();
                return formatVersion;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException e) {
            return "";
        }
    }

    private static String getFormatVersion(DataInputStream reader) throws IOException {
        byte[] buffer = new byte[8];
        reader.read(buffer);
        if (buffer[0] == 77) {
            return new String(buffer);
        }
        return "";
    }

    public void parse(File logFile) {
        try {
            DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(logFile)));
            try {
                reader.mark(0);
                String version = getFormatVersion(reader).trim();
                if (version.startsWith("MMAP")) {
                    this.mUseMmap = true;
                    this.mVersion = Integer.valueOf(version.substring(4)).intValue();
                    if (this.mVersion == 1) {
                        parse_mmap_001(reader);
                    } else if (this.mVersion == 2) {
                        parse_mmap_002(reader);
                    } else if (this.mVersion == 3) {
                        parse_mmap_003(reader);
                    } else if (this.mVersion == 4) {
                        parse_mmap_004(reader);
                    } else if (this.mVersion == 5) {
                        parse_mmap_005(reader);
                    } else {
                        BLog.e(TAG, "unsupported nightwatch format %s", version);
                    }
                } else {
                    this.mUseMmap = false;
                    reader.reset();
                    parse_nommap(reader);
                }
                reader.close();
                BLog.i(TAG, "watcher signal %d", Integer.valueOf(this.mNightwatchCrashReason));
                BLog.i(TAG, "watcher status %d", Integer.valueOf(this.mNightwatchCrashStatus));
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException e) {
        }
    }

    private void parse_mmap_001(DataInputStream reader) throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        try {
            this.mNightwatchStartTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchWatcherCrashTimeMs = Long.reverseBytes(reader.readLong());
            int nextInt = Integer.reverseBytes(reader.readInt());
            if (nextInt != -1) {
                this.mNightwatchFb4aCrashStatus = nextInt;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int nextInt2 = Integer.reverseBytes(reader.readInt());
            if (nextInt2 != -1) {
                this.mNightwatchFb4aCrashReason = nextInt2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int nextInt3 = Integer.reverseBytes(reader.readInt());
            if (nextInt3 != -1) {
                this.mNightwatchWatcherCrashStatus = nextInt3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int nextInt4 = Integer.reverseBytes(reader.readInt());
            if (nextInt4 != -1) {
                this.mNightwatchWatcherCrashReason = nextInt4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomAdjHistoryAsString = readByteAsHistory(reader);
            this.mOomAdjAtKill = reader.readByte();
            this.batteryCapacityHistory.setValueAtStart((short) reader.readByte());
            this.processCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreAdjHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.memAvailableMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.fdCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.rootStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.externalStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.from(reader);
            this.oomScoreAdjHistory.from(reader);
            this.processCountHistory.from(reader);
            this.batteryCapacityHistory.from(reader);
            this.memAvailableMBHistory.from(reader);
            this.fdCountHistory.from(reader);
            this.rootStorageMBHistory.from(reader);
            this.externalStorageMBHistory.from(reader);
            this.batteryTemperatureHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.connectionTypeHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.mWatcherWaitState = reader.readByte();
            reader.skip(3);
            this.batteryTemperatureHistory.from(reader);
            this.trafficTotalRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalRxMBHistory.from(reader);
            this.trafficTotalTxMBHistory.from(reader);
            this.trafficMobileRxMBHistory.from(reader);
            this.trafficMobileTxMBHistory.from(reader);
            this.connectionTypeHistory.from(reader);
            this.mOomScoreAdjFastLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(reader);
            long logcatTotalBytesWritten = Long.reverseBytes(reader.readLong());
            byte[] logcatBuffer = new byte[8192];
            reader.read(logcatBuffer);
            if (logcatTotalBytesWritten == 0) {
                this.logcat = "";
            } else if (logcatTotalBytesWritten <= Systrace.TRACE_TAG_REACT_JAVA_BRIDGE) {
                this.logcat = new String(logcatBuffer, 0, (int) logcatTotalBytesWritten, Charset.defaultCharset());
            } else {
                int logcatHead = (int) (logcatTotalBytesWritten % Systrace.TRACE_TAG_REACT_JAVA_BRIDGE);
                String head = new String(logcatBuffer, logcatHead, 8192 - logcatHead, Charset.defaultCharset());
                this.logcat = head + new String(logcatBuffer, 0, logcatHead, Charset.defaultCharset());
            }
            this.watchedFb4aPid = Integer.reverseBytes(reader.readInt());
            this.threadCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            reader.skipBytes(2);
            this.threadCountHistory.from(reader);
            if (i == 0) {
                if (this.mNightwatchWatcherCrashTimeMs != -1) {
                    this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
                }
            }
            if (i2 == i3) {
                if (this.mNightwatchWatcherCrashStatus != -1) {
                    this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
                }
            }
            if (i4 == i5) {
                if (this.mNightwatchWatcherCrashReason != -1) {
                    this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
                }
            }
        } finally {
            if (this.mNightwatchFb4aCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchFb4aCrashTimeMs;
            } else if (this.mNightwatchWatcherCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
            }
            if (this.mNightwatchFb4aCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchFb4aCrashStatus;
            } else if (this.mNightwatchWatcherCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
            }
            if (this.mNightwatchFb4aCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchFb4aCrashReason;
            } else if (this.mNightwatchWatcherCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
            }
        }
    }

    private void parse_mmap_002(DataInputStream reader) throws IOException {
        int i;
        int i2;
        int i3;
        try {
            this.mNightwatchStartTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchWatcherCrashTimeMs = Long.reverseBytes(reader.readLong());
            int nextInt = Integer.reverseBytes(reader.readInt());
            if (nextInt != -1) {
                this.mNightwatchFb4aCrashStatus = nextInt;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int nextInt2 = Integer.reverseBytes(reader.readInt());
            if (nextInt2 != -1) {
                this.mNightwatchFb4aCrashReason = nextInt2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int nextInt3 = Integer.reverseBytes(reader.readInt());
            if (nextInt3 != -1) {
                this.mNightwatchWatcherCrashStatus = nextInt3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int nextInt4 = Integer.reverseBytes(reader.readInt());
            if (nextInt4 != -1) {
                this.mNightwatchWatcherCrashReason = nextInt4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomAdjHistoryAsString = readByteAsHistory(reader);
            this.mOomAdjAtKill = reader.readByte();
            this.batteryCapacityHistory.setValueAtStart((short) reader.readByte());
            this.processCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreAdjHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.memAvailableMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.fdCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.rootStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.externalStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.from(reader);
            this.oomScoreAdjHistory.from(reader);
            this.processCountHistory.from(reader);
            this.batteryCapacityHistory.from(reader);
            this.memAvailableMBHistory.from(reader);
            this.fdCountHistory.from(reader);
            this.rootStorageMBHistory.from(reader);
            this.externalStorageMBHistory.from(reader);
            this.batteryTemperatureHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.connectionTypeHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.mWatcherWaitState = reader.readByte();
            reader.skip(3);
            this.batteryTemperatureHistory.from(reader);
            this.trafficTotalRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalRxMBHistory.from(reader);
            this.trafficTotalTxMBHistory.from(reader);
            this.trafficMobileRxMBHistory.from(reader);
            this.trafficMobileTxMBHistory.from(reader);
            this.connectionTypeHistory.from(reader);
            this.mOomScoreAdjFastLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(reader);
            this.watchedFb4aPid = Integer.reverseBytes(reader.readInt());
            this.threadCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            reader.skip(2);
            this.threadCountHistory.from(reader);
            if (i == 0) {
                if (this.mNightwatchWatcherCrashTimeMs != -1) {
                    this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
                }
            }
            if (i2 == -1) {
                if (this.mNightwatchWatcherCrashStatus != -1) {
                    this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
                }
            }
            if (i3 == -1) {
                if (this.mNightwatchWatcherCrashReason != -1) {
                    this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
                }
            }
        } finally {
            if (this.mNightwatchFb4aCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchFb4aCrashTimeMs;
            } else if (this.mNightwatchWatcherCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
            }
            if (this.mNightwatchFb4aCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchFb4aCrashStatus;
            } else if (this.mNightwatchWatcherCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
            }
            if (this.mNightwatchFb4aCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchFb4aCrashReason;
            } else if (this.mNightwatchWatcherCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
            }
        }
    }

    private void parse_mmap_003(DataInputStream reader) throws IOException {
        int i;
        int i2;
        int i3;
        try {
            this.mNightwatchStartTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchWatcherCrashTimeMs = Long.reverseBytes(reader.readLong());
            int nextInt = Integer.reverseBytes(reader.readInt());
            if (nextInt != -1) {
                this.mNightwatchFb4aCrashStatus = nextInt;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int nextInt2 = Integer.reverseBytes(reader.readInt());
            if (nextInt2 != -1) {
                this.mNightwatchFb4aCrashReason = nextInt2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int nextInt3 = Integer.reverseBytes(reader.readInt());
            if (nextInt3 != -1) {
                this.mNightwatchWatcherCrashStatus = nextInt3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int nextInt4 = Integer.reverseBytes(reader.readInt());
            if (nextInt4 != -1) {
                this.mNightwatchWatcherCrashReason = nextInt4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomAdjHistoryAsString = readByteAsHistory(reader);
            this.mOomAdjAtKill = reader.readByte();
            this.batteryCapacityHistory.setValueAtStart((short) reader.readByte());
            this.processCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreAdjHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.memAvailableMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.fdCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.rootStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.externalStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.from(reader);
            this.oomScoreAdjHistory.from(reader);
            this.processCountHistory.from(reader);
            this.batteryCapacityHistory.from(reader);
            this.memAvailableMBHistory.from(reader);
            this.fdCountHistory.from(reader);
            this.rootStorageMBHistory.from(reader);
            this.externalStorageMBHistory.from(reader);
            this.batteryTemperatureHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.connectionTypeHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.mWatcherWaitState = reader.readByte();
            reader.skip(3);
            this.batteryTemperatureHistory.from(reader);
            this.trafficTotalRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalRxMBHistory.from(reader);
            this.trafficTotalTxMBHistory.from(reader);
            this.trafficMobileRxMBHistory.from(reader);
            this.trafficMobileTxMBHistory.from(reader);
            this.connectionTypeHistory.from(reader);
            this.mOomScoreAdjFastLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(reader);
            this.watchedFb4aPid = Integer.reverseBytes(reader.readInt());
            this.threadCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            reader.skip(2);
            this.threadCountHistory.from(reader);
            this.mTickInfoCount = Integer.reverseBytes(reader.readInt());
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = Integer.reverseBytes(reader.readInt());
                this.mStallStatsChecksum = Long.reverseBytes(reader.readLong());
                for (int i4 = 0; i4 < this.mTickInfoCount; i4++) {
                    TickInfo tick = new TickInfo();
                    tick.actualUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.expectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.nextExpectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.relativeThreadTimeMs = Long.reverseBytes(reader.readLong());
                    this.mTickInfoCircularBuffer[i4] = tick;
                }
            }
            if (i == 0) {
                if (this.mNightwatchWatcherCrashTimeMs != -1) {
                    this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
                }
            }
            if (i2 == -1) {
                if (this.mNightwatchWatcherCrashStatus != -1) {
                    this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
                }
            }
            if (i3 == -1) {
                if (this.mNightwatchWatcherCrashReason != -1) {
                    this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
                }
            }
        } finally {
            if (this.mNightwatchFb4aCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchFb4aCrashTimeMs;
            } else if (this.mNightwatchWatcherCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
            }
            if (this.mNightwatchFb4aCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchFb4aCrashStatus;
            } else if (this.mNightwatchWatcherCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
            }
            if (this.mNightwatchFb4aCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchFb4aCrashReason;
            } else if (this.mNightwatchWatcherCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
            }
        }
    }

    private void parse_mmap_004(DataInputStream reader) throws IOException {
        int i;
        int i2;
        int i3;
        try {
            this.mNightwatchStartTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashUptimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchWatcherCrashTimeMs = Long.reverseBytes(reader.readLong());
            int nextInt = Integer.reverseBytes(reader.readInt());
            if (nextInt != -1) {
                this.mNightwatchFb4aCrashStatus = nextInt;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int nextInt2 = Integer.reverseBytes(reader.readInt());
            if (nextInt2 != -1) {
                this.mNightwatchFb4aCrashReason = nextInt2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int nextInt3 = Integer.reverseBytes(reader.readInt());
            if (nextInt3 != -1) {
                this.mNightwatchWatcherCrashStatus = nextInt3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int nextInt4 = Integer.reverseBytes(reader.readInt());
            if (nextInt4 != -1) {
                this.mNightwatchWatcherCrashReason = nextInt4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomAdjHistoryAsString = readByteAsHistory(reader);
            this.mOomAdjAtKill = reader.readByte();
            this.batteryCapacityHistory.setValueAtStart((short) reader.readByte());
            this.processCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreAdjHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.memAvailableMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.fdCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.rootStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.externalStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.from(reader);
            this.oomScoreAdjHistory.from(reader);
            this.processCountHistory.from(reader);
            this.batteryCapacityHistory.from(reader);
            this.memAvailableMBHistory.from(reader);
            this.fdCountHistory.from(reader);
            this.rootStorageMBHistory.from(reader);
            this.externalStorageMBHistory.from(reader);
            this.batteryTemperatureHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.connectionTypeHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.mWatcherWaitState = reader.readByte();
            reader.skip(3);
            this.batteryTemperatureHistory.from(reader);
            this.trafficTotalRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalRxMBHistory.from(reader);
            this.trafficTotalTxMBHistory.from(reader);
            this.trafficMobileRxMBHistory.from(reader);
            this.trafficMobileTxMBHistory.from(reader);
            this.connectionTypeHistory.from(reader);
            this.mOomScoreAdjFastLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(reader);
            this.watchedFb4aPid = Integer.reverseBytes(reader.readInt());
            this.threadCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            reader.skip(2);
            this.threadCountHistory.from(reader);
            this.mTickInfoCount = Integer.reverseBytes(reader.readInt());
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = Integer.reverseBytes(reader.readInt());
                this.mStallStatsChecksum = Long.reverseBytes(reader.readLong());
                for (int i4 = 0; i4 < this.mTickInfoCount; i4++) {
                    TickInfo tick = new TickInfo();
                    tick.actualUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.expectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.nextExpectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.relativeThreadTimeMs = Long.reverseBytes(reader.readLong());
                    this.mTickInfoCircularBuffer[i4] = tick;
                }
            }
            if (i == 0) {
                if (this.mNightwatchWatcherCrashTimeMs != -1) {
                    this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
                }
            }
            if (i2 == -1) {
                if (this.mNightwatchWatcherCrashStatus != -1) {
                    this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
                }
            }
            if (i3 == -1) {
                if (this.mNightwatchWatcherCrashReason != -1) {
                    this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
                }
            }
        } finally {
            if (this.mNightwatchFb4aCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchFb4aCrashTimeMs;
            } else if (this.mNightwatchWatcherCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
            }
            if (this.mNightwatchFb4aCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchFb4aCrashStatus;
            } else if (this.mNightwatchWatcherCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
            }
            if (this.mNightwatchFb4aCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchFb4aCrashReason;
            } else if (this.mNightwatchWatcherCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
            }
        }
    }

    private void parse_mmap_005(DataInputStream reader) throws IOException {
        int i;
        int i2;
        int i3;
        try {
            this.mNightwatchStartTimeMs = Long.reverseBytes(reader.readLong());
            DataHistory.setNightwatcherStartTimeMs(this.mNightwatchStartTimeMs);
            this.mNightwatchFb4aCrashTimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchFb4aCrashUptimeMs = Long.reverseBytes(reader.readLong());
            this.mNightwatchWatcherCrashTimeMs = Long.reverseBytes(reader.readLong());
            int nextInt = Integer.reverseBytes(reader.readInt());
            if (nextInt != -1) {
                this.mNightwatchFb4aCrashStatus = nextInt;
                BLog.i(TAG, "nightwatch fb4a crash status %d", Integer.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            int nextInt2 = Integer.reverseBytes(reader.readInt());
            if (nextInt2 != -1) {
                this.mNightwatchFb4aCrashReason = nextInt2;
                BLog.i(TAG, "nightwatch fb4a crash reason %d", Integer.valueOf(this.mNightwatchFb4aCrashReason));
            }
            int nextInt3 = Integer.reverseBytes(reader.readInt());
            if (nextInt3 != -1) {
                this.mNightwatchWatcherCrashStatus = nextInt3;
                BLog.i(TAG, "nightwatch watcher crash status %d", Integer.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            int nextInt4 = Integer.reverseBytes(reader.readInt());
            if (nextInt4 != -1) {
                this.mNightwatchWatcherCrashReason = nextInt4;
                BLog.i(TAG, "nightwatch watcher crash reason %d", Integer.valueOf(this.mNightwatchWatcherCrashReason));
            }
            this.mOomAdjLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomAdjHistoryAsString = readByteAsHistory(reader);
            this.mOomAdjAtKill = reader.readByte();
            this.batteryCapacityHistory.setValueAtStart((short) reader.readByte());
            this.processCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreAdjHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.memAvailableMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.fdCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.rootStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.externalStorageMBHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.oomScoreHistory.from_005(reader);
            this.oomScoreAdjHistory.from_005(reader);
            this.processCountHistory.from_005(reader);
            this.batteryCapacityHistory.from_005(reader);
            this.memAvailableMBHistory.from_005(reader);
            this.fdCountHistory.from_005(reader);
            this.rootStorageMBHistory.from_005(reader);
            this.externalStorageMBHistory.from_005(reader);
            this.batteryTemperatureHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.connectionTypeHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.mWatcherWaitState = reader.readByte();
            reader.skip(3);
            this.batteryTemperatureHistory.from_005(reader);
            this.trafficTotalRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileRxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficMobileTxMBHistory.setValueAtStart((long) Integer.reverseBytes(reader.readInt()));
            this.trafficTotalRxMBHistory.from_005(reader);
            this.trafficTotalTxMBHistory.from_005(reader);
            this.trafficMobileRxMBHistory.from_005(reader);
            this.trafficMobileTxMBHistory.from_005(reader);
            this.connectionTypeHistory.from_005(reader);
            this.mOomScoreAdjFastLastTimeMs = Long.reverseBytes(reader.readLong());
            this.mOomScoreAdjFastHistoryAsString = DataHistory.readShortAsHistory(reader);
            this.watchedFb4aPid = Integer.reverseBytes(reader.readInt());
            this.threadCountHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.processImportanceHistory.setValueAtStart(Short.reverseBytes(reader.readShort()));
            this.threadCountHistory.from_005(reader);
            this.processImportanceHistory.from_005(reader);
            this.mTickInfoCount = Integer.reverseBytes(reader.readInt());
            if (this.mTickInfoCount > 0) {
                this.mTickInfoCircularBuffer = new TickInfo[this.mTickInfoCount];
                this.mTickInfoIndex = Integer.reverseBytes(reader.readInt());
                this.mStallStatsChecksum = Long.reverseBytes(reader.readLong());
                for (int i4 = 0; i4 < this.mTickInfoCount; i4++) {
                    TickInfo tick = new TickInfo();
                    tick.actualUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.expectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.nextExpectedUptimeMs = Long.reverseBytes(reader.readLong());
                    tick.relativeThreadTimeMs = Long.reverseBytes(reader.readLong());
                    this.mTickInfoCircularBuffer[i4] = tick;
                }
            }
            if (i == 0) {
                if (this.mNightwatchWatcherCrashTimeMs != -1) {
                    this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
                }
            }
            if (i2 == -1) {
                if (this.mNightwatchWatcherCrashStatus != -1) {
                    this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
                }
            }
            if (i3 == -1) {
                if (this.mNightwatchWatcherCrashReason != -1) {
                    this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
                }
            }
        } finally {
            if (this.mNightwatchFb4aCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchFb4aCrashTimeMs;
            } else if (this.mNightwatchWatcherCrashTimeMs != -1) {
                this.mNightwatchCrashTimeMs = this.mNightwatchWatcherCrashTimeMs;
            }
            if (this.mNightwatchFb4aCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchFb4aCrashStatus;
            } else if (this.mNightwatchWatcherCrashStatus != -1) {
                this.mNightwatchCrashStatus = this.mNightwatchWatcherCrashStatus;
            }
            if (this.mNightwatchFb4aCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchFb4aCrashReason;
            } else if (this.mNightwatchWatcherCrashReason != -1) {
                this.mNightwatchCrashReason = this.mNightwatchWatcherCrashReason;
            }
        }
    }

    private static String readByteAsHistory(DataInputStream reader) throws IOException {
        long history = Long.reverseBytes(reader.readLong());
        if (history == -1) {
            return "";
        }
        byte[] historyItems = {-1, -1, -1, -1, -1, -1, -1, -1};
        for (int i = historyItems.length - 1; i >= 0; i--) {
            historyItems[i] = (byte) ((int) (255 & history));
            history >>= 8;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i2 = 0; i2 < historyItems.length; i2++) {
            if (i2 > 0) {
                sb.append(",");
            }
            sb.append(Byte.toString(historyItems[i2]));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String readLogcat(long logcatTotalBytesWritten, int logcatBufferSize, byte[] logcatBuffer) {
        if (logcatTotalBytesWritten == 0) {
            return "";
        }
        if (logcatTotalBytesWritten <= ((long) logcatBufferSize)) {
            return new String(logcatBuffer, 0, (int) logcatTotalBytesWritten, Charset.defaultCharset());
        }
        int logcatHead = (int) (logcatTotalBytesWritten % ((long) logcatBufferSize));
        String head = new String(logcatBuffer, logcatHead, logcatBufferSize - logcatHead, Charset.defaultCharset());
        return head + new String(logcatBuffer, 0, logcatHead, Charset.defaultCharset());
    }

    private void parse_nommap(DataInputStream fr) throws IOException {
        String statusStr = null;
        String reasonStr = null;
        String timestampStr = null;
        byte[] buffer = new byte[256];
        int num = fr.read(buffer);
        if (num > 0) {
            String[] fields = new String(buffer, 0, num).split("\\s");
            if (fields.length > 0) {
                statusStr = fields[0];
                if (fields.length > 1) {
                    reasonStr = fields[1];
                    if (fields.length > 2) {
                        timestampStr = fields[2];
                    }
                }
            }
        }
        if (statusStr != null) {
            try {
                this.mNightwatchCrashStatus = Integer.parseInt(statusStr);
                BLog.d(TAG, "Crash status ingested is %d", Integer.valueOf(this.mNightwatchCrashStatus));
            } catch (NumberFormatException e) {
            }
        }
        if (reasonStr != null) {
            try {
                this.mNightwatchCrashReason = Integer.parseInt(reasonStr);
                BLog.d(TAG, "Crash reason ingested is %d", Integer.valueOf(this.mNightwatchCrashReason));
            } catch (NumberFormatException e2) {
            }
        }
        if (timestampStr != null) {
            try {
                this.mNightwatchCrashTimeMs = Long.parseLong(timestampStr);
                BLog.d(TAG, "Crash time ingested is %d", Long.valueOf(this.mNightwatchCrashTimeMs));
            } catch (NumberFormatException e3) {
            }
        }
    }

    public Map<String, String> getEntries() {
        Map<String, String> entryMap = new LinkedHashMap<>();
        if (this.mNightwatchCrashStatus > -1) {
            entryMap.put("nightwatch_status", String.valueOf(this.mNightwatchCrashStatus));
        }
        if (this.mNightwatchCrashReason > -1) {
            entryMap.put("nightwatch_reason", String.valueOf(this.mNightwatchCrashReason));
        }
        if (this.mNightwatchCrashTimeMs > 0) {
            entryMap.put("nightwatch_crash_time_ms", String.valueOf(this.mNightwatchCrashTimeMs));
        }
        if (this.mUseMmap) {
            if (this.mNightwatchFb4aCrashStatus > -1) {
                entryMap.put("nightwatch_fb4a_status", String.valueOf(this.mNightwatchFb4aCrashStatus));
            }
            if (this.mNightwatchFb4aCrashReason > -1) {
                entryMap.put("nightwatch_fb4a_reason", String.valueOf(this.mNightwatchFb4aCrashReason));
            }
            if (this.mNightwatchFb4aCrashTimeMs > 0) {
                entryMap.put("nightwatch_fb4a_crash_time_ms", String.valueOf(this.mNightwatchFb4aCrashTimeMs));
            }
            if (this.mNightwatchFb4aCrashUptimeMs > 0) {
                entryMap.put("nightwatch_fb4a_crash_uptime_ms", String.valueOf(this.mNightwatchFb4aCrashUptimeMs));
            }
            if (this.mNightwatchWatcherCrashStatus > -1) {
                entryMap.put("nightwatch_watcher_status", String.valueOf(this.mNightwatchWatcherCrashStatus));
            }
            if (this.mNightwatchWatcherCrashReason > -1) {
                entryMap.put("nightwatch_watcher_reason", String.valueOf(this.mNightwatchWatcherCrashReason));
            }
            if (this.mNightwatchWatcherCrashTimeMs > 0) {
                entryMap.put("nightwatch_watcher_crash_time_ms", String.valueOf(this.mNightwatchWatcherCrashTimeMs));
            }
            if (this.mOomAdjLastTimeMs > 0) {
                entryMap.put("nightwatch_oom_adj_last_time_ms", String.valueOf(this.mOomAdjLastTimeMs));
            }
            if (this.mOomAdjAtKill != -1) {
                entryMap.put("nightwatch_oom_adj_at_kill", String.valueOf((int) this.mOomAdjAtKill));
            }
            if (!this.mOomAdjHistoryAsString.isEmpty()) {
                entryMap.put("nightwatch_oom_adj_history", this.mOomAdjHistoryAsString);
            }
            if (this.mOomScoreAdjFastLastTimeMs > 0) {
                entryMap.put("nightwatch_oom_score_adj_fast_last_time_ms", String.valueOf(this.mOomScoreAdjFastLastTimeMs));
            }
            if (!this.mOomScoreAdjFastHistoryAsString.isEmpty()) {
                entryMap.put("nightwatch_oom_score_adj_fast_history", this.mOomScoreAdjFastHistoryAsString);
            }
            if (this.mNightwatchStartTimeMs != -1) {
                entryMap.put("night_watch_start_time_ms", String.valueOf(this.mNightwatchStartTimeMs));
            }
            if (this.mWatcherWaitState != -1) {
                entryMap.put("night_watch_wait_state", String.valueOf((int) this.mWatcherWaitState));
            }
            this.oomScoreHistory.getEntries(entryMap);
            this.oomScoreAdjHistory.getEntries(entryMap);
            this.threadCountHistory.getEntries(entryMap);
            this.processCountHistory.getEntries(entryMap);
            this.batteryCapacityHistory.getEntries(entryMap);
            this.memAvailableMBHistory.getEntries(entryMap);
            this.fdCountHistory.getEntries(entryMap);
            this.rootStorageMBHistory.getEntries(entryMap);
            this.externalStorageMBHistory.getEntries(entryMap);
            this.batteryTemperatureHistory.getEntries(entryMap);
            this.trafficTotalRxMBHistory.getEntries(entryMap);
            this.trafficTotalTxMBHistory.getEntries(entryMap);
            this.trafficMobileRxMBHistory.getEntries(entryMap);
            this.trafficMobileTxMBHistory.getEntries(entryMap);
            this.connectionTypeHistory.getEntries(entryMap);
            this.processImportanceHistory.getEntries(entryMap);
            if (!this.logcat.isEmpty()) {
                entryMap.put("saved_logcat", this.logcat);
            }
            if (this.watchedFb4aPid != -1) {
                entryMap.put("watched_pid", String.valueOf(this.watchedFb4aPid));
            }
            if (this.mTickInfoCircularBuffer != null) {
                try {
                    JSONObject stallStats = new JSONObject();
                    stallStats.put("tick_info_count", this.mTickInfoCount);
                    stallStats.put("nightwatch_checksum", this.mStallStatsChecksum);
                    long checksum = (31 * ((31 * ((31 * ((31 * ((31 * 0) + ((long) this.mTickInfoIndex))) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].actualUptimeMs)) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].expectedUptimeMs)) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].nextExpectedUptimeMs)) + this.mTickInfoCircularBuffer[this.mTickInfoIndex].relativeThreadTimeMs;
                    stallStats.put("expected_checksum", checksum);
                    JSONArray tickInfos = new JSONArray();
                    for (int i = 0; i < this.mTickInfoCount; i++) {
                        TickInfo tick = this.mTickInfoCircularBuffer[((this.mTickInfoIndex + 1) + i) % this.mTickInfoCount];
                        if (tick.actualUptimeMs > 0) {
                            JSONObject jsonTick = new JSONObject();
                            jsonTick.put("actual_uptime_ms", tick.actualUptimeMs);
                            if (tick.expectedUptimeMs != -1) {
                                jsonTick.put("delay_ms", tick.actualUptimeMs - tick.expectedUptimeMs);
                            }
                            jsonTick.put("next_expected_uptime_ms", tick.nextExpectedUptimeMs);
                            if (tick.relativeThreadTimeMs != -1) {
                                jsonTick.put("relative_thread_time_ms", tick.relativeThreadTimeMs);
                            }
                            tickInfos.put(jsonTick);
                        }
                    }
                    stallStats.put("tick_infos", tickInfos);
                    entryMap.put("stall_stats", stallStats.toString());
                    entryMap.put("stall_stats_valid", String.valueOf(checksum == this.mStallStatsChecksum));
                } catch (JSONException e) {
                    BLog.e(TAG, "error serializing json", e);
                    entryMap.put("stall_stats", "\"JSON error\"");
                }
            }
        }
        return entryMap;
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
