package com.facebook.mobileconfig.impl;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.namehandling.MobileConfigIdNameMappingLoader;
import com.facebook.ultralight.names.UltralightNames;
import com.oculus.util.constants.OculusConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.annotation.Nullable;

public class MobileConfigFilesOnDiskUtils {
    private static final String DATA_DIR_SUFFIX = ".data/";
    public static final String PARAMS_FILE_SUFFIX = "_params_map.txt";
    private static final String SESSIONLESS_DATA_DIR = "sessionless";
    public static final String SPEC_TO_HASH_FILENAME = "spec_to_param.txt";
    private static final String TAG = "MobileConfigFilesOnDiskUtils";

    public static void copySpecToHashToDisk(File file, String str, @Nullable AssetManager assetManager) {
        if (assetManager != null) {
            createSessionDirIfNeeded(file, str);
            File file2 = new File(getSpecToHashFilePathTmp(file, str));
            try {
                InputStream open = assetManager.open(SPEC_TO_HASH_FILENAME);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = open.read(bArr);
                            if (read != -1) {
                                fileOutputStream.write(bArr, 0, read);
                            } else {
                                fileOutputStream.close();
                                open.close();
                                return;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                }
            } catch (IOException unused3) {
                return;
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }

    public static boolean copyParamsMapToDisk(File file, String str, String str2) {
        createParamsMapsDirIfNeeded(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(getParamsMapFilePath(file, str)));
            try {
                fileOutputStream.write(str2.getBytes(), 0, str2.length());
                fileOutputStream.close();
                return true;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            BLog.e(TAG, "IOException while trying to copy params map", e);
            return false;
        }
    }

    public static boolean paramsMapFoundOnDisk(File file, String str) {
        if (str != null && !str.isEmpty()) {
            return new File(getParamsMapFilePath(file, str)).exists();
        }
        File file2 = new File(getParamsMapDirPath(file));
        return file2.isDirectory() && file2.list() != null && file2.list().length > 0;
    }

    public static boolean currentParamsMapFoundOnDisk(File file, String str) {
        int maxFolderIndex = getMaxFolderIndex(file, str);
        if (maxFolderIndex == -1) {
            BLog.d(TAG, "No params map on disk yet");
            return false;
        }
        String num = Integer.toString(maxFolderIndex);
        return new File(getDirectoryPathForSessionFromId(file, str, num) + "/params_map.txt").exists();
    }

    public static String readCurrentParamsMapFromDisk(File file, String str) {
        int maxFolderIndex = getMaxFolderIndex(file, str);
        if (maxFolderIndex == -1) {
            BLog.d(TAG, "No params map on disk yet");
            return "";
        }
        String num = Integer.toString(maxFolderIndex);
        try {
            return getFileContentAsString(getDirectoryPathForSessionFromId(file, str, num) + "/params_map.txt");
        } catch (IOException e) {
            BLog.e(TAG, "IO exception when trying to read params map from disk", e);
            return "";
        }
    }

    public static String parseSchemaHashLineFromFd(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
            try {
                String readLine = new BufferedReader(new InputStreamReader(autoCloseInputStream)).readLine();
                autoCloseInputStream.close();
                return readLine;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            BLog.e(TAG, "IO exception when trying to read params map for schema hash from disk", e);
            return "";
        }
    }

    public static String getSpecToHashFilePath(File file, String str) {
        return getDirectoryPathForSessionFromId(file, str, "") + "/" + SPEC_TO_HASH_FILENAME;
    }

    public static int getMaxFolderIndex(File file, String str) {
        int i;
        StringBuilder sb = new StringBuilder();
        if (str.isEmpty()) {
            str = SESSIONLESS_DATA_DIR;
        }
        sb.append(str);
        sb.append(UltralightNames.FQN_SEPARATOR);
        String sb2 = sb.toString();
        File[] listFiles = new File(file, MobileConfigIdNameMappingLoader.POSSIBLE_SUB_DIR).listFiles();
        int i2 = -1;
        for (File file2 : listFiles) {
            if (file2.isDirectory() && file2.getName().startsWith(sb2)) {
                try {
                    i = Integer.parseInt(file2.getName().substring(sb2.length(), file2.getName().indexOf(46)));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    BLog.e(TAG, "Found unusual subdirectory name", e);
                    i = -1;
                }
                if (i > i2) {
                    i2 = i;
                }
            }
        }
        return i2;
    }

    public static String getCurrentBufferPathPostfix(File file, String str) {
        return Integer.toString(getMaxFolderIndex(file, str));
    }

    public static String getNextBufferPathPostfix(File file, String str) {
        return Integer.toString(getMaxFolderIndex(file, str) + 1);
    }

    public static void deleteOlderBufferPathFolders(File file, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.isEmpty() ? SESSIONLESS_DATA_DIR : str);
        sb.append(UltralightNames.FQN_SEPARATOR);
        String sb2 = sb.toString();
        String currentBufferPathPostfix = getCurrentBufferPathPostfix(file, str);
        if (currentBufferPathPostfix.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
            BLog.d(TAG, "There was no previous data to delete");
            return;
        }
        String str2 = sb2 + currentBufferPathPostfix;
        File[] listFiles = new File(file, MobileConfigIdNameMappingLoader.POSSIBLE_SUB_DIR).listFiles();
        for (File file2 : listFiles) {
            if (file2.isDirectory() && file2.getName().startsWith(sb2) && !file2.getName().startsWith(str2)) {
                deleteDirectory(file2);
            }
        }
    }

    private static boolean deleteDirectory(File file) {
        boolean z;
        if (file.isDirectory()) {
            z = true;
            for (File file2 : file.listFiles()) {
                z = z && deleteDirectory(file2);
            }
        } else {
            z = true;
        }
        if (!z || !file.delete()) {
            return false;
        }
        return true;
    }

    public static boolean copyPrevDataToNextFolder(File file, String str) {
        int maxFolderIndex = getMaxFolderIndex(file, str);
        if (maxFolderIndex == -1) {
            BLog.d(TAG, "There was no previous data to copy");
            return false;
        }
        File file2 = new File(getDirectoryPathForSessionFromId(file, str, Integer.toString(maxFolderIndex)));
        File file3 = new File(getDirectoryPathForSessionFromId(file, str, Integer.toString(maxFolderIndex + 1)));
        String[] list = file2.list();
        if (list == null) {
            return false;
        }
        file3.mkdir();
        for (String str2 : list) {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(file2, str2));
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file3, str2));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                BLog.e(TAG, "IO exception while trying to copy files to new folder", e);
            }
        }
        return true;
    }

    public static String getFileContentAsString(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            } catch (Throwable unused) {
            }
        }
        throw th;
    }

    public static void writeFileContentFromString(String str, String str2) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(str2)));
        try {
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedWriter.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    private static String getSpecToHashFilePathTmp(File file, String str) {
        return getSpecToHashFilePath(file, str) + ".tmp";
    }

    private static String getDirectoryPathForSessionFromId(File file, String str) {
        return getDirectoryPathForSessionFromId(file, str, "");
    }

    private static String getDirectoryPathForSessionFromId(File file, String str, String str2) {
        String str3 = file.getAbsolutePath() + "/mobileconfig/";
        if (!str2.isEmpty()) {
            str2 = UltralightNames.FQN_SEPARATOR + str2;
        }
        if (str == null || str.isEmpty() || str.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
            return str3 + SESSIONLESS_DATA_DIR + str2 + DATA_DIR_SUFFIX;
        }
        return str3 + str + str2 + DATA_DIR_SUFFIX;
    }

    private static void createSessionDirIfNeeded(File file, String str) {
        new File(getDirectoryPathForSessionFromId(file, str)).mkdir();
    }

    private static String getParamsMapDirPath(File file) {
        return file.getAbsolutePath() + "/mobileconfig/params_maps";
    }

    private static void createParamsMapsDirIfNeeded(File file) {
        new File(getParamsMapDirPath(file)).mkdirs();
    }

    public static String getParamsMapFilePath(File file, String str) {
        return getParamsMapDirPath(file) + "/" + str + PARAMS_FILE_SUFFIX;
    }
}
