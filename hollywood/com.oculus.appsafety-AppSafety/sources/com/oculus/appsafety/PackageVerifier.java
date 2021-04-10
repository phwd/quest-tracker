package com.oculus.appsafety;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import com.oculus.license.License;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.LicenseManager;
import com.oculus.os.PackageMetadata;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;
import java.util.concurrent.Callable;
import oculus.internal.Gatekeeper;
import oculus.internal.license.ProvisionalLicenseFactory;
import oculus.internal.license.signing.KeyStoreLicenseSigner;
import oculus.internal.license.signing.LicenseSigner;
import oculus.internal.pkgtelemetry.Package;
import oculus.internal.pkgtelemetry.PackageMetadataCollector;

public final class PackageVerifier {
    private static final boolean DEBUG = false;
    private static final String LICENSE_MANAGER = "oculus.software.license_manager";
    private static final String TAG = PackageVerifier.class.getSimpleName();
    private static final String UNOFFICIAL_APPS = "oculus.software.unofficial_apps";
    private Handler mHandler;
    private LicenseManager mLicenseManager = LicenseManager.getInstance();
    private LicenseSigner mLicenseSigner = KeyStoreLicenseSigner.create();
    private ProvisionalLicenseFactory mProvisionalLicenseFactory = ProvisionalLicenseFactory.create(this.mLicenseSigner);

    public PackageVerifier(Handler handler) {
        this.mHandler = handler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ec, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00f0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00f1, code lost:
        android.util.Log.e(com.oculus.appsafety.PackageVerifier.TAG, "Failed to evaluate current licenses for " + r15.getPackageIdentifier(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x00b1, code lost:
        if (android.os.SystemProperties.getBoolean("ovr.licensing.force", false) != false) goto L_0x00b6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f0 A[ExcHandler: RemoteException (r0v23 'e' android.os.RemoteException A[CUSTOM_DECLARE]), Splitter:B:15:0x00d6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean verifyPackage(android.content.Context r23, android.net.Uri r24, java.lang.String r25, java.lang.String r26, java.util.Set<java.lang.String> r27, int r28) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appsafety.PackageVerifier.verifyPackage(android.content.Context, android.net.Uri, java.lang.String, java.lang.String, java.util.Set, int):boolean");
    }

    public static class PackageTelemetrySender implements Callable<Void> {
        Context mContext;
        Package mPkg;
        long mProcessingTimeMs;

        public PackageTelemetrySender(Context context, Package pkg, long processingTimeMs) {
            this.mContext = context;
            this.mPkg = pkg;
            this.mProcessingTimeMs = processingTimeMs;
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            PersistableBundle extras = new PersistableBundle();
            byte[] androidManifestHash = null;
            byte[] packageManifestHash = null;
            boolean useUploaderGk = new Gatekeeper(24).isEnabled();
            if (useUploaderGk) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    if (this.mPkg.getAndroidManifest() != null) {
                        androidManifestHash = md.digest(this.mPkg.getAndroidManifest());
                    }
                    if (this.mPkg.getPackageManifest() != null) {
                        md.reset();
                        packageManifestHash = md.digest(this.mPkg.getPackageManifest());
                    }
                } catch (NoSuchAlgorithmException e) {
                    return null;
                }
            }
            this.mPkg.toTelemetryExtras(extras, androidManifestHash, packageManifestHash);
            if (extras.getLong(Package.FIRST_INSTALL_TIME) == 0) {
                extras.remove(Package.FIRST_INSTALL_TIME);
            }
            if (extras.getLong(Package.LAST_UPDATE_TIME) == 0) {
                extras.remove(Package.LAST_UPDATE_TIME);
            }
            extras.putLong("processing_time_ms", this.mProcessingTimeMs);
            UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(new AnalyticsEvent(PackageVerifier.TAG, LoggingHelper.PACKAGE_TELEMETRY, extras, (PersistableBundle) null), false);
            if (useUploaderGk) {
                PackagePartsUploadJobService.uploadTelemetryParts(this.mContext, new PackageTelemetry(this.mPkg, this.mProcessingTimeMs, androidManifestHash, packageManifestHash));
            }
            return null;
        }
    }

    public static Package collectPackageTelemetry(Context context, File apkSource, String installerPackage, int installFlags, List<PackageMetadataCollector.CollectionError> errors) {
        try {
            PackageMetadata packageMetadata = PackageMetadataCollector.createFromPackage(apkSource, errors);
            Package.Builder builder = new Package.Builder().setPackageIdentifier(packageMetadata.packageIdentifier).setAndroidManifest(packageMetadata.androidManifest).setPackageManifest(packageMetadata.packageManifest).setSignatures(packageMetadata.signatures).setInstallerPackage(installerPackage).setInstallFlags(installFlags).setSourceStampDisposition(packageMetadata.sourceStampDisposition).setErrors(errors);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageMetadata.packageIdentifier, 0);
                builder.setFirstInstallTime(packageInfo.firstInstallTime).setLastUpdateTime(packageInfo.lastUpdateTime);
            } catch (PackageManager.NameNotFoundException e) {
                builder.setFirstInstallTime(System.currentTimeMillis());
            }
            return builder.build();
        } catch (Exception e2) {
            Log.e(TAG, "Failed to parse APK", e2);
            errors.add(new PackageMetadataCollector.CollectionError(PackageMetadataCollector.CollectionError.FAILED_TO_PARSE_APK, e2.getMessage()));
            return new Package.Builder().setPackageIdentifier("unknown").setErrors(errors).build();
        }
    }

    private License issueProvisionalLicense(PackageMetadata packageMetadata) throws IOException, SignatureException {
        byte[] blob = this.mProvisionalLicenseFactory.generate(packageMetadata, "PackageVerifier");
        this.mLicenseManager.registerLicenseSigner(this.mLicenseSigner.getCertificate());
        return this.mLicenseManager.install(blob);
    }
}
