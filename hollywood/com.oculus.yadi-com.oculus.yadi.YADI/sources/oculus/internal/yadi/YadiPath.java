package oculus.internal.yadi;

import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.UserHandle;
import android.system.StructStat;
import android.util.Log;
import com.oculus.os.yadi.RemoteResource;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class YadiPath {
    private static volatile MountInfo EXT_STORAGE;
    private static volatile Map<Long, List<MountInfo>> MOUNTS;
    private static final Pattern OCTAL = Pattern.compile("(\\\\[0-7]{3})");

    static {
        refreshMountInfo();
    }

    /* access modifiers changed from: package-private */
    public static boolean isInvalidTmpFile(String str, RemoteResource remoteResource) {
        StringBuilder sb = new StringBuilder();
        sb.append(remoteResource.resourceId);
        sb.append("-");
        return str.startsWith(sb.toString()) && str.endsWith(".tmp") && !str.equals(forDownload(remoteResource));
    }

    static String forDownload(String str, String str2) {
        return str + "-" + str2 + ".tmp";
    }

    static String forDownload(RemoteResource remoteResource) {
        return forDownload(remoteResource.resourceId, Utils.hexString(remoteResource.digest));
    }

    static boolean isRelativePath(String str, String str2) {
        return str2.startsWith(str) && (str2.length() == str.length() || str2.charAt(str.length()) == '/');
    }

    static MountInfo mountPointForFile(File file) throws IOException {
        String file2 = file.toString();
        MountInfo mountInfo = null;
        StructStat structStat = null;
        IOException e = null;
        while (file != null && structStat == null) {
            try {
                structStat = Os.stat(file.toString());
            } catch (IOException e2) {
                e = e2;
            }
            file = file.getParentFile();
        }
        if (structStat != null) {
            Map<Long, List<MountInfo>> map = MOUNTS;
            for (MountInfo mountInfo2 : map.getOrDefault(Long.valueOf(structStat.st_dev), map.getOrDefault(Long.valueOf(structStat.st_rdev), Collections.EMPTY_LIST))) {
                if (isRelativePath(mountInfo2.mountPoint, file2) && (mountInfo == null || mountInfo2.mountPoint.startsWith(mountInfo.mountPoint))) {
                    mountInfo = mountInfo2;
                }
            }
            return mountInfo;
        }
        throw e;
    }

    static String handleSdcardFsObbAbsurdity(String str) {
        String[] split = str.split("/", 4);
        return maybeGetUserIdFromObbPath(split).map(new Function(split) {
            /* class oculus.internal.yadi.$$Lambda$YadiPath$Das6x0rJ3sZkm5FFZV9yRQTBwZs */
            private final /* synthetic */ String[] f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YadiPath.lambda$handleSdcardFsObbAbsurdity$0(this.f$0, (Integer) obj);
            }
        }).orElse(str);
    }

    static /* synthetic */ String lambda$handleSdcardFsObbAbsurdity$0(String[] strArr, Integer num) {
        return "/" + strArr[3];
    }

    private static Optional<Integer> maybeGetUserIdFromObbPath(String[] strArr) {
        if (strArr.length < 4) {
            return Optional.empty();
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            if ("Android".equals(strArr[2]) && ("obb".equals(strArr[3]) || strArr[3].startsWith("obb/"))) {
                return Optional.of(Integer.valueOf(parseInt));
            }
        } catch (NumberFormatException unused) {
        }
        return Optional.empty();
    }

    static MountInfo resolveBindMount(MountInfo mountInfo) {
        if ("/".equals(mountInfo.root)) {
            return mountInfo;
        }
        for (MountInfo mountInfo2 : MOUNTS.get(Long.valueOf(mountInfo.devId))) {
            if ("/".equals(mountInfo2.root)) {
                return mountInfo2;
            }
        }
        return mountInfo;
    }

    static File directFile(File file) throws IOException {
        MountInfo mountPointForFile = mountPointForFile(file);
        if (mountPointForFile == null || !mountPointForFile.isEmulatedOrBound()) {
            return file;
        }
        MountInfo resolveBindMount = resolveBindMount(mountPointForFile);
        String substring = file.toString().substring(mountPointForFile.mountPoint.length());
        if ("sdcardfs".equals(mountPointForFile.fsType)) {
            String handleSdcardFsObbAbsurdity = handleSdcardFsObbAbsurdity(substring);
            if ("/".equals(mountPointForFile.root)) {
                return new File(resolveBindMount.mountSource + handleSdcardFsObbAbsurdity);
            }
            return new File(resolveBindMount.mountSource + mountPointForFile.root + handleSdcardFsObbAbsurdity);
        }
        return new File(resolveBindMount.mountPoint + mountPointForFile.root + substring);
    }

    static String removeOctalChars(String str) {
        Matcher matcher = OCTAL.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            int i = 0;
            for (int i2 = 1; i2 < 4; i2++) {
                i = (i << 3) + Character.digit(matcher.group(0).charAt(i2), 10);
            }
            matcher.appendReplacement(stringBuffer, new String(Character.toChars(i)));
        }
        return stringBuffer == null ? str : matcher.appendTail(stringBuffer).toString();
    }

    static boolean isExternalStorage(File file) throws IOException {
        return mountPointForFile(file) == EXT_STORAGE;
    }

    static File forAsset(UserHandle userHandle, ApplicationInfo applicationInfo, Uri uri) {
        if ("sandbox".equalsIgnoreCase(uri.getScheme())) {
            String host = uri.getHost();
            File file = null;
            int identifier = userHandle.getIdentifier();
            if (identifier < 0) {
                identifier = UserHandle.myUserId();
            }
            ApplicationInfo applicationInfo2 = new ApplicationInfo(applicationInfo);
            applicationInfo2.initForUser(identifier);
            Environment.UserEnvironment userEnvironment = new Environment.UserEnvironment(identifier);
            if ("app".equalsIgnoreCase(host)) {
                file = new File(applicationInfo2.sourceDir).getParentFile();
            } else if ("app-priv".equalsIgnoreCase(host)) {
                file = new File(applicationInfo2.dataDir);
            } else if ("app-pub".equalsIgnoreCase(host)) {
                file = userEnvironment.buildExternalStorageAppFilesDirs(applicationInfo2.packageName)[0];
            } else if ("app-ext".equalsIgnoreCase(host)) {
                file = userEnvironment.buildExternalStorageAppObbDirs(applicationInfo2.packageName)[0];
            } else if ("app-media".equalsIgnoreCase(host)) {
                file = userEnvironment.buildExternalStorageAppMediaDirs(applicationInfo2.packageName)[0];
            } else if ("shared".equalsIgnoreCase(host)) {
                file = userEnvironment.getExternalStorageDirectory();
            }
            if (file != null) {
                for (String str : uri.getPathSegments()) {
                    if (str.indexOf(47) == -1 && str.indexOf(0) == -1) {
                        file = new File(file, str);
                    } else {
                        throw new IllegalArgumentException("Illegal character in path");
                    }
                }
                return file;
            }
            throw new IllegalArgumentException("Unknown host: " + host);
        }
        throw new IllegalArgumentException("Invalid URI");
    }

    public static void refreshMountInfo() {
        MountInfo mountInfo;
        MOUNTS = readMountInfo();
        try {
            mountInfo = mountPointForFile(Environment.getExternalStorageDirectory());
        } catch (Exception e) {
            Log.e("YadiPath", "Exception loading external storage stat", e);
            mountInfo = null;
        }
        EXT_STORAGE = mountInfo;
    }

    private static Pattern createMountInfoPattern() {
        return Pattern.compile("^(\\d+) (\\d+) (\\d+):(\\d+) (\\S+) (\\S+) (\\S+) (?:(?:\\w+)(?::\\S+)\\s)*- (\\S+) (\\S+) \\S+$", 0);
    }

    static MountInfo parseMountInfoLine(String str, Pattern pattern) throws IOException {
        if (pattern == null) {
            pattern = createMountInfoPattern();
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return new MountInfo(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), (long) Integer.parseInt(matcher.group(3)), (long) Integer.parseInt(matcher.group(4)), removeOctalChars(matcher.group(5)), matcher.group(8), removeOctalChars(matcher.group(6)), removeOctalChars(matcher.group(9)));
        }
        throw new IOException("Unable to parse mountinfo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.Map<java.lang.Long, java.util.List<oculus.internal.yadi.YadiPath.MountInfo>> readMountInfo() {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "/proc/self/mountinfo"
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x004f }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x004f }
            r3.<init>(r1)     // Catch:{ Exception -> 0x004f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x004f }
            java.util.regex.Pattern r1 = createMountInfoPattern()     // Catch:{ all -> 0x0043 }
        L_0x0015:
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x003f
            oculus.internal.yadi.YadiPath$MountInfo r3 = parseMountInfoLine(r3, r1)     // Catch:{ all -> 0x0043 }
            long r4 = r3.devId     // Catch:{ all -> 0x0043 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x0043 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ all -> 0x0043 }
            if (r4 != 0) goto L_0x0032
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0043 }
            r4.<init>()     // Catch:{ all -> 0x0043 }
        L_0x0032:
            r4.add(r3)     // Catch:{ all -> 0x0043 }
            long r5 = r3.devId     // Catch:{ all -> 0x0043 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0043 }
            r0.put(r3, r4)     // Catch:{ all -> 0x0043 }
            goto L_0x0015
        L_0x003f:
            r2.close()
            goto L_0x0057
        L_0x0043:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x004e:
            throw r3
        L_0x004f:
            r1 = move-exception
            java.lang.String r2 = "YadiPath"
            java.lang.String r3 = "Exception reading mount info"
            android.util.Log.e(r2, r3, r1)
        L_0x0057:
            oculus.internal.yadi.-$$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o r1 = oculus.internal.yadi.$$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o.INSTANCE
            r0.replaceAll(r1)
            java.util.Map r0 = java.util.Collections.unmodifiableMap(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.YadiPath.readMountInfo():java.util.Map");
    }

    /* access modifiers changed from: package-private */
    public static class MountInfo {
        final long devId;
        final long devMajor;
        final String fsType;
        final int mountId;
        final String mountPoint;
        final String mountSource;
        final int parentId;
        final String root;

        public String toString() {
            return "MountInfo{at=" + this.mountPoint + " from=" + this.mountSource + " on=" + this.devId + " type=" + this.fsType + "}";
        }

        public boolean isEmulatedOrBound() {
            return "sdcardfs".equals(this.fsType) || !"/".equals(this.root);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MountInfo)) {
                return false;
            }
            MountInfo mountInfo = (MountInfo) obj;
            return mountInfo.mountId == this.mountId && mountInfo.parentId == this.parentId && mountInfo.devMajor == this.devMajor && mountInfo.devId == this.devId && Objects.equals(mountInfo.root, this.root) && Objects.equals(mountInfo.fsType, this.fsType) && Objects.equals(mountInfo.mountPoint, this.mountPoint) && Objects.equals(mountInfo.mountSource, this.mountSource);
        }

        MountInfo(int i, int i2, long j, long j2, String str, String str2, String str3, String str4) {
            this.mountId = i;
            this.parentId = i2;
            this.devId = Os.makedev(j, j2);
            this.devMajor = j;
            this.root = str;
            this.fsType = str2;
            this.mountPoint = str3;
            this.mountSource = str4;
        }
    }
}
