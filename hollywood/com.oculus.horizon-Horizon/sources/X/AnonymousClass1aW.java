package X;

import com.oculus.deviceconfigclient.ConfigStorageCache;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/* renamed from: X.1aW  reason: invalid class name */
public final class AnonymousClass1aW {
    public static String A01(File file) {
        int A00 = A00(file, "");
        if (A00 != -1) {
            try {
                return A04(AnonymousClass006.A05(A03(file, "", Integer.toString(A00)), "/params_map.txt"));
            } catch (IOException e) {
                AnonymousClass0NO.A0B("MobileConfigFilesOnDiskUtils", "IO exception when trying to read params map from disk", e);
            }
        }
        return "";
    }

    public static String A04(String str) throws IOException {
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

    public static boolean A06(File file) {
        int A00 = A00(file, "");
        if (A00 == -1) {
            return false;
        }
        return new File(AnonymousClass006.A05(A03(file, "", Integer.toString(A00)), "/params_map.txt")).exists();
    }

    public static boolean A09(File file, String str) {
        if (str != null && !str.isEmpty()) {
            return new File(A02(file, str)).exists();
        }
        File file2 = new File(AnonymousClass006.A05(file.getAbsolutePath(), "/mobileconfig/params_maps"));
        if (!file2.isDirectory() || file2.list() == null || file2.list().length <= 0) {
            return false;
        }
        return true;
    }

    public static int A00(File file, String str) {
        int i;
        if (str.isEmpty()) {
            str = ConfigStorageCache.PARAM_SESSIONLESS_JSON_KEY;
        }
        String A05 = AnonymousClass006.A05(str, "_");
        File[] listFiles = new File(file, "mobileconfig").listFiles();
        int i2 = -1;
        for (File file2 : listFiles) {
            if (file2.isDirectory() && file2.getName().startsWith(A05)) {
                try {
                    i = Integer.parseInt(file2.getName().substring(A05.length(), file2.getName().indexOf(46)));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    AnonymousClass0NO.A0B("MobileConfigFilesOnDiskUtils", "Found unusual subdirectory name", e);
                    i = -1;
                }
                if (i > i2) {
                    i2 = i;
                }
            }
        }
        return i2;
    }

    public static String A02(File file, String str) {
        return AnonymousClass006.A08(AnonymousClass006.A05(file.getAbsolutePath(), "/mobileconfig/params_maps"), "/", str, "_params_map.txt");
    }

    public static String A03(File file, String str, String str2) {
        String A05 = AnonymousClass006.A05(file.getAbsolutePath(), "/mobileconfig/");
        if (!str2.isEmpty()) {
            str2 = AnonymousClass006.A05("_", str2);
        }
        if (str == null || str.isEmpty() || str.equals("0")) {
            return AnonymousClass006.A08(A05, ConfigStorageCache.PARAM_SESSIONLESS_JSON_KEY, str2, ".data/");
        }
        return AnonymousClass006.A08(A05, str, str2, ".data/");
    }

    public static void A05(File file, String str) {
        String str2;
        if (str.isEmpty()) {
            str2 = ConfigStorageCache.PARAM_SESSIONLESS_JSON_KEY;
        } else {
            str2 = str;
        }
        String A05 = AnonymousClass006.A05(str2, "_");
        String num = Integer.toString(A00(file, str));
        if (!num.equals("0")) {
            String A052 = AnonymousClass006.A05(A05, num);
            File[] listFiles = new File(file, "mobileconfig").listFiles();
            for (File file2 : listFiles) {
                if (file2.isDirectory() && file2.getName().startsWith(A05) && !file2.getName().startsWith(A052)) {
                    A07(file2);
                }
            }
        }
    }

    public static boolean A07(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            boolean z = true;
            for (File file2 : listFiles) {
                if (z) {
                    z = true;
                    if (A07(file2)) {
                    }
                }
                z = false;
            }
            if (!z) {
                return false;
            }
        }
        if (file.delete()) {
            return true;
        }
        return false;
    }

    public static boolean A08(File file, String str) {
        int A00 = A00(file, str);
        if (A00 != -1) {
            File file2 = new File(A03(file, str, Integer.toString(A00)));
            File file3 = new File(A03(file, str, Integer.toString(A00 + 1)));
            String[] list = file2.list();
            if (list != null) {
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
                        AnonymousClass0NO.A0B("MobileConfigFilesOnDiskUtils", "IO exception while trying to copy files to new folder", e);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
