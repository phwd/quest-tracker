package com.facebook.crudolib.prefs;

import android.util.Base64OutputStream;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.stetho.common.Utf8Charset;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* access modifiers changed from: package-private */
@NullsafeStrict
@ThreadSafe
public class LightSharedPreferencesStorage {
    private static final int BUF_SIZE = 512;
    private static final String TAG = "LightSharedPreferencesStorage";
    public static final int VERSION = 1;
    private static final Map<LightSharedPreferencesStorage, Map<String, Object>> sSharedPrefsToCommitToDisk = new HashMap();
    private static int sSharedPrefsWriteMode = 1;
    private final Object mLock = new Object();
    private final File mPrefFile;

    LightSharedPreferencesStorage(File file) {
        this.mPrefFile = file;
    }

    public void tryLoadSharedPreference(Map<String, Object> map) {
        if (this.mPrefFile.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.mPrefFile), 512));
                try {
                    parseDataInput(dataInputStream, map);
                    dataInputStream.close();
                    return;
                } catch (Throwable unused) {
                }
            } catch (UnknownVersionException | IOException | ArrayStoreException e) {
                BLog.e(LightSharedPreferencesStorage.class, e, "Failed to read or parse SharedPreferences from: %s; Raw file: %s", this.mPrefFile.getAbsolutePath(), tryReadRawFile());
                this.mPrefFile.delete();
                return;
            }
        } else {
            return;
        }
        throw th;
    }

    private void parseDataInput(DataInputStream dataInputStream, Map<String, Object> map) throws IOException, UnknownVersionException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if (readUnsignedByte == 1) {
            int readInt = dataInputStream.readInt();
            while (true) {
                int i = readInt - 1;
                if (readInt > 0) {
                    int readUnsignedByte2 = dataInputStream.readUnsignedByte();
                    String readUTF = dataInputStream.readUTF();
                    switch (readUnsignedByte2) {
                        case 0:
                            map.put(readUTF, Boolean.valueOf(dataInputStream.readBoolean()));
                            break;
                        case 1:
                            map.put(readUTF, Integer.valueOf(dataInputStream.readInt()));
                            break;
                        case 2:
                            map.put(readUTF, Long.valueOf(dataInputStream.readLong()));
                            break;
                        case 3:
                            map.put(readUTF, Float.valueOf(dataInputStream.readFloat()));
                            break;
                        case 4:
                            map.put(readUTF, Double.valueOf(dataInputStream.readDouble()));
                            break;
                        case 5:
                            map.put(readUTF, dataInputStream.readUTF());
                            break;
                        case 6:
                            map.put(readUTF, readStringSet(dataInputStream));
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported type with ordinal: " + readUnsignedByte2);
                    }
                    readInt = i;
                } else {
                    return;
                }
            }
        } else {
            throw new UnknownVersionException("Expected version 1; got " + readUnsignedByte);
        }
    }

    @VisibleForTesting
    private static Set<String> readStringSet(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        HashSet hashSet = new HashSet(readInt);
        while (true) {
            int i = readInt - 1;
            if (readInt <= 0) {
                return hashSet;
            }
            hashSet.add(dataInputStream.readUTF());
            readInt = i;
        }
    }

    public void saveSharedPreference(Map<String, Object> map) throws IOException {
        if (sSharedPrefsWriteMode == 1) {
            saveSharedPreferenceInternal(map);
            return;
        }
        synchronized (LightSharedPreferencesStorage.class) {
            sSharedPrefsToCommitToDisk.put(this, map);
        }
    }

    private void saveSharedPreferenceInternal(Map<String, Object> map) throws IOException {
        File createTempFile = File.createTempFile(this.mPrefFile.getName() + ".", ".tmp", this.mPrefFile.getParentFile());
        dumpDataToFile(createTempFile, map);
        synchronized (this.mLock) {
            if (!createTempFile.renameTo(this.mPrefFile)) {
                createTempFile.delete();
                throw new IOException("Failed to replace the current preference file!");
            }
        }
    }

    public static synchronized void setSharedPrefsWriteMode(int i) {
        synchronized (LightSharedPreferencesStorage.class) {
            sSharedPrefsWriteMode = i;
            if (sSharedPrefsWriteMode != 0) {
                sSharedPrefsWriteMode = i;
                for (Map.Entry<LightSharedPreferencesStorage, Map<String, Object>> entry : sSharedPrefsToCommitToDisk.entrySet()) {
                    try {
                        entry.getKey().saveSharedPreferenceInternal(entry.getValue());
                    } catch (IOException e) {
                        BLog.w(TAG, "Could not write shared preferences to disk!", e);
                    }
                }
                sSharedPrefsToCommitToDisk.clear();
            }
        }
    }

    private static void dumpDataToFile(File file, Map<String, Object> map) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file), 512));
        try {
            dataOutputStream.writeByte(1);
            dataOutputStream.writeInt(map.size());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                int valueType = ValueType.getValueType(value);
                dataOutputStream.write(valueType);
                dataOutputStream.writeUTF(entry.getKey());
                switch (valueType) {
                    case 0:
                        dataOutputStream.writeBoolean(((Boolean) value).booleanValue());
                        break;
                    case 1:
                        dataOutputStream.writeInt(((Integer) value).intValue());
                        break;
                    case 2:
                        dataOutputStream.writeLong(((Long) value).longValue());
                        break;
                    case 3:
                        dataOutputStream.writeFloat(((Float) value).floatValue());
                        break;
                    case 4:
                        dataOutputStream.writeDouble(((Double) value).doubleValue());
                        break;
                    case 5:
                        dataOutputStream.writeUTF((String) value);
                        break;
                    case 6:
                        writeStringSet(dataOutputStream, (Set) value);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported type with ordinal: " + valueType);
                }
            }
        } finally {
            dataOutputStream.close();
        }
    }

    static void writeStringSet(DataOutputStream dataOutputStream, Set<String> set) throws IOException {
        dataOutputStream.writeInt(set.size());
        for (String str : set) {
            dataOutputStream.writeUTF(str);
        }
    }

    private static void fsync(FileOutputStream fileOutputStream) throws IOException {
        if (fileOutputStream != null) {
            fileOutputStream.getFD().sync();
        }
    }

    public String tryReadRawFile() {
        try {
            return readRawFile();
        } catch (IOException e) {
            String message = e.getMessage() != null ? e.getMessage() : "description N/A";
            return "[I/O error: " + message + "]";
        }
    }

    private String readRawFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.mPrefFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) this.mPrefFile.length());
        Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 0);
        try {
            copy(fileInputStream, base64OutputStream, new byte[1024]);
            base64OutputStream.close();
            return byteArrayOutputStream.toString(Utf8Charset.NAME);
        } finally {
            fileInputStream.close();
            base64OutputStream.close();
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
