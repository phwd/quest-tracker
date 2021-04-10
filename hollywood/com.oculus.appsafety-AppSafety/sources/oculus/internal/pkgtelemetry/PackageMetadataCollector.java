package oculus.internal.pkgtelemetry;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.res.AssetManager;
import android.util.Log;
import com.oculus.os.PackageMetadata;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import oculus.internal.SourceStampVerifier;

public class PackageMetadataCollector {
    public static int INCLUDE_ANDROID_MANIFEST = 1;
    public static int INCLUDE_EVERYTHING = (((INCLUDE_ANDROID_MANIFEST | INCLUDE_PACKAGE_MANIFEST) | INCLUDE_SIGNATURES) | INCLUDE_SOURCE_STAMP_DISPOSITION);
    public static int INCLUDE_PACKAGE_MANIFEST = 2;
    public static int INCLUDE_SIGNATURES = 4;
    public static int INCLUDE_SOURCE_STAMP_DISPOSITION = 8;
    public static final String TAG = "PackageMetadataCollector";

    public static PackageMetadata createFromPackage(PackageManager pm, String packageIdentifier) throws PackageManager.NameNotFoundException, PackageParser.PackageParserException {
        return createFromPackage(pm.getPackageInfo(packageIdentifier, 0));
    }

    public static PackageMetadata createFromPackage(PackageManager pm, String packageIdentifier, int flags) throws PackageManager.NameNotFoundException, PackageParser.PackageParserException {
        return createFromPackage(pm.getPackageInfo(packageIdentifier, 0), flags);
    }

    public static PackageMetadata createFromPackage(PackageInfo packageInfo) throws PackageParser.PackageParserException {
        return createFromPackage(packageInfo, INCLUDE_EVERYTHING);
    }

    public static PackageMetadata createFromPackage(PackageInfo packageInfo, int flags) throws PackageParser.PackageParserException {
        return createFromPackage(new File(packageInfo.applicationInfo.sourceDir), packageInfo, packageInfo.packageName, null, flags);
    }

    public static PackageMetadata createFromPackage(File f, List<CollectionError> errors) throws PackageParser.PackageParserException {
        return createFromPackage(f, errors, INCLUDE_EVERYTHING);
    }

    public static PackageMetadata createFromPackage(File f, List<CollectionError> errors, int flags) throws PackageParser.PackageParserException {
        PackageParser.Package pkg = new PackageParser().parsePackage(f, 0);
        return createFromPackage(new File(pkg.baseCodePath), null, pkg.packageName, errors, flags);
    }

    private static PackageMetadata createFromPackage(File f, PackageInfo packageInfo, String packageIdentifier, List<CollectionError> errors, int flags) {
        byte[] androidManifest;
        if (errors == null) {
            errors = new ArrayList();
        }
        AssetManager assets = new AssetManager();
        assets.addAssetPath(f.getAbsolutePath());
        PackageMetadata.Signature[] signatures = null;
        if ((INCLUDE_ANDROID_MANIFEST & flags) != 0) {
            androidManifest = loadNonAsset(assets, "AndroidManifest.xml", errors);
        } else {
            androidManifest = null;
        }
        byte[] packageManifest = (INCLUDE_PACKAGE_MANIFEST & flags) != 0 ? loadPackageManifest(f, errors) : null;
        if ((INCLUDE_SIGNATURES & flags) != 0) {
            signatures = loadSignatures(f, packageInfo, errors);
        }
        PackageMetadata.Builder builder = new PackageMetadata.Builder().setPackageIdentifier(packageIdentifier).setAndroidManifest(androidManifest).setPackageManifest(packageManifest).setSignatures(signatures);
        if (packageInfo != null) {
            builder.setFirstInstallTime(packageInfo.firstInstallTime).setLastUpdateTime(packageInfo.lastUpdateTime);
        }
        if ((INCLUDE_SOURCE_STAMP_DISPOSITION & flags) != 0) {
            builder.setSourceStampDisposition(PackageMetadata.SourceStampDisposition.fromValue(SourceStampVerifier.verify(f).value));
        }
        return builder.build();
    }

    public static final class CollectionError {
        public static final String FAILED_TO_PARSE_APK = "failed_to_parse_apk";
        public static final String LOAD_NON_ASSET_FAILURE = "load_non_asset_failure";
        public static final String LOAD_PACKAGE_MANIFEST_FAILURE = "load_package_manifest_failure";
        public static final String UNRECOGNIZED_MIME_TYPE = "unrecognized_mime_type";
        public static final String V1_SIGNATURE_CERT_PARSING_FAILURE = "v1_signature_cert_parsing_failure";
        public static final String V1_SIGNATURE_FAILED_TO_ENCODE_CERTIFICATE = "v1_signature_failed_to_encode_certificate";
        public static final String V1_SIGNATURE_FAILED_TO_READ_APK = "v1_signature_failed_to_read_apk";
        public static final String V1_SIGNATURE_GOT_UNKNOWN_ALGORITHM = "v1_signature_got_unknown_algorithm";
        public static final String V1_SIGNATURE_UNEXPECTED = "v1_signature_unexpected";
        public static final String V2_SIGNATURE_FAILED_TO_ENCODE_CERTIFICATE = "v2_signature_failed_to_encode_certificate";
        public static final String V2_SIGNATURE_FAILED_TO_READ_APK = "v2_signature_failed_to_read_apk";
        public static final String V2_SIGNATURE_GOT_UNKNOWN_ALGORITHM = "v2_signature_got_unknown_algorithm";
        public static final String V2_SIGNATURE_NOT_FOUND = "v2_signature_not_found";
        public static final String V2_SIGNATURE_NOT_VERIFIED = "v2_signature_not_verified";
        public static final String V2_SIGNATURE_UNEXPECTED = "v2_signature_unexpected";
        public final String message;
        public final String name;

        public CollectionError(String name2, String message2) {
            this.name = name2;
            this.message = message2;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof CollectionError)) {
                return false;
            }
            CollectionError o = (CollectionError) other;
            if (!Objects.equals(this.name, o.name) || !Objects.equals(this.message, o.message)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(this.name, this.message);
        }
    }

    private static byte[] loadNonAsset(AssetManager assets, String path, List<CollectionError> errors) {
        try {
            return readAll(assets.openNonAsset(path));
        } catch (IOException ex) {
            Log.e(TAG, "Failed to read " + path, ex);
            errors.add(new CollectionError(CollectionError.LOAD_NON_ASSET_FAILURE, ex.getMessage()));
            return null;
        }
    }

    private static byte[] loadPackageManifest(File apkFile, List<CollectionError> errors) {
        try {
            JarFile jarFile = new JarFile(apkFile, false);
            ZipEntry manifestEntry = jarFile.getEntry(JarFile.MANIFEST_NAME);
            if (manifestEntry != null) {
                return readAll(jarFile.getInputStream(manifestEntry));
            }
            errors.add(new CollectionError(CollectionError.LOAD_PACKAGE_MANIFEST_FAILURE, apkFile.getPath() + ": not jar-signed"));
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Failed to read META-INF/MANIFEST.MF", e);
            errors.add(new CollectionError(CollectionError.LOAD_PACKAGE_MANIFEST_FAILURE, e.getMessage()));
            return null;
        }
    }

    private static byte[] readAll(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        while (true) {
            int count = is.read(buffer);
            if (count == -1) {
                return baos.toByteArray();
            }
            baos.write(buffer, 0, count);
        }
    }

    private static PackageMetadata.Signature[] loadSignatures(File apkFile, PackageInfo packageInfo, List<CollectionError> errors) {
        List<PackageMetadata.Signature> signatures = new ArrayList<>();
        loadV2Signatures(signatures, apkFile, packageInfo, errors);
        loadV1Signatures(signatures, apkFile, errors);
        return (PackageMetadata.Signature[]) signatures.toArray(new PackageMetadata.Signature[signatures.size()]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x014e A[SYNTHETIC, Splitter:B:43:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void loadV2Signatures(java.util.List<com.oculus.os.PackageMetadata.Signature> r22, java.io.File r23, android.content.pm.PackageInfo r24, java.util.List<oculus.internal.pkgtelemetry.PackageMetadataCollector.CollectionError> r25) {
        /*
        // Method dump skipped, instructions count: 413
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.pkgtelemetry.PackageMetadataCollector.loadV2Signatures(java.util.List, java.io.File, android.content.pm.PackageInfo, java.util.List):void");
    }

    private static boolean packageDeclaredAs3dof(PackageInfo packageInfo) {
        if (!(packageInfo == null || packageInfo.reqFeatures == null)) {
            FeatureInfo[] featureInfoArr = packageInfo.reqFeatures;
            for (FeatureInfo fi : featureInfoArr) {
                if ("android.hardware.vr.headtracking".equals(fi.name) && (fi.flags & 1) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String[] splitName = fileName.split("\\.");
        return splitName[splitName.length - 1];
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x012a A[Catch:{ ParsingException -> 0x016d, IOException -> 0x0168, NoSuchAlgorithmException -> 0x0164, CertificateEncodingException -> 0x0160, Exception -> 0x015c }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x014d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void loadV1Signatures(java.util.List<com.oculus.os.PackageMetadata.Signature> r18, java.io.File r19, java.util.List<oculus.internal.pkgtelemetry.PackageMetadataCollector.CollectionError> r20) {
        /*
        // Method dump skipped, instructions count: 515
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.pkgtelemetry.PackageMetadataCollector.loadV1Signatures(java.util.List, java.io.File, java.util.List):void");
    }
}
