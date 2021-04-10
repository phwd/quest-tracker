package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.FbPermissionEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"CatchGeneralException", "DefaultLocale"})
public class FbPermission {
    static final String ASSET_FILE = "fbpermissions.json";
    private static final String CERT_TYPE = "X.509";
    static final String KEY_ALGORITHM = "algorithm";
    private static final String KEY_PERMISSIONS = "permissions";
    private static final String KEY_SIGNATURES = "signatures";
    private static final String KEY_SINGLE_SIGNATURE = "signature";
    static final String KEY_VALUE = "value";
    private static FbPermission sInstance;
    private Reporter mReporter = new LocalReporter();

    private FbPermission() {
    }

    public static synchronized FbPermission get() {
        FbPermission fbPermission;
        synchronized (FbPermission.class) {
            if (sInstance == null) {
                sInstance = new FbPermission();
            }
            fbPermission = sInstance;
        }
        return fbPermission;
    }

    public static synchronized FbPermission get(Reporter reporter) {
        FbPermission reporter2;
        synchronized (FbPermission.class) {
            reporter2 = get().setReporter(reporter);
        }
        return reporter2;
    }

    @Deprecated
    public void enforceFbPermission(Context context, String str, String str2) {
        if (!canSkipFbPermissionCheck__DO_NOT_USE(context, str, str2) && !verifyFbPermission(context, str, str2)) {
            throw new SecurityException(String.format("FBPermission '%s' was not granted to package '%s'", str2, str));
        }
    }

    @Deprecated
    public boolean checkFbPermission(Context context, String str, String str2, boolean z) {
        if (canSkipFbPermissionCheck__DO_NOT_USE(context, str, str2)) {
            return true;
        }
        boolean verifyFbPermission = verifyFbPermission(context, str, str2);
        if (!verifyFbPermission) {
            logCheckResult(String.format("FBPermission '%s' was not granted to package '%s'", str2, str), z);
        }
        if (verifyFbPermission || z) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean isAppFbPermissionSigned(Context context, String str) {
        return doesAppHasFbPermissions(context, str);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean canSkipFbPermissionCheck__DO_NOT_USE(Context context, String str, String str2) {
        return AllFamilyTrustedSignatures.ALL_FAMILY_DEBUG.contains(AppVerifier.getSignatureFromPackageName(context, context.getPackageName())) && getAppFbPermissionsFromManifest(context, str).contains(str2);
    }

    protected static List<String> getAppFbPermissionsFromManifest(Context context, AppIdentity appIdentity) {
        ArrayList arrayList = new ArrayList();
        for (String str : AppVerifier.getPackageNamesFromUid(context, appIdentity.getUid())) {
            arrayList.addAll(getAppFbPermissionsFromManifest(context, str));
        }
        return arrayList;
    }

    @VisibleForTesting(otherwise = 2)
    protected static List<String> getAppFbPermissionsFromManifest(Context context, String str) {
        List<String> emptyList = Collections.emptyList();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(str, 128).metaData;
            if (bundle != null && bundle.size() > 0) {
                emptyList = new ArrayList<>();
                for (String str2 : bundle.keySet()) {
                    if (str2.matches(".*\\.fbpermission\\..*")) {
                        emptyList.add(str2);
                    }
                }
            }
            return emptyList;
        } catch (PackageManager.NameNotFoundException unused) {
            return Collections.emptyList();
        }
    }

    /* access modifiers changed from: package-private */
    public FbPermission setReporter(Reporter reporter) {
        this.mReporter = reporter;
        return this;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public Reporter getReporter() {
        return this.mReporter;
    }

    /* access modifiers changed from: protected */
    public boolean checkFbPermission(Context context, @Nullable AppIdentity appIdentity, String str, boolean z) {
        boolean verifyFbPermission = verifyFbPermission(context, appIdentity, str);
        if (!verifyFbPermission) {
            logCheckResult(String.format("FBPermission '%s' was not granted to %s", str, appIdentity), z);
        }
        if (verifyFbPermission || z) {
            return true;
        }
        return false;
    }

    private boolean verifyFbPermission(Context context, @Nullable AppIdentity appIdentity, String str) {
        if (appIdentity == null) {
            return false;
        }
        return verifyFbPermission(context, appIdentity.getUid(), str);
    }

    private boolean verifyFbPermission(Context context, int i, String str) {
        String[] packageNamesFromUid = AppVerifier.getPackageNamesFromUid(context, i);
        if (packageNamesFromUid.length > 1) {
            this.mReporter.report(String.format("UID '%d' is shared by multiple packages: %s", Integer.valueOf(i), Arrays.toString(packageNamesFromUid)));
        }
        boolean z = false;
        for (String str2 : packageNamesFromUid) {
            if (verifyFbPermission(context, str2, str)) {
                z = true;
            }
        }
        if (!z) {
            this.mReporter.report(String.format("FBPermission '%s' was not granted to UID '%d' (packages: '%s')", str, Integer.valueOf(i), Arrays.toString(packageNamesFromUid)));
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public boolean verifyFbPermission(Context context, String str, String str2) {
        if (!doesAppHasFbPermissions(context, str)) {
            return false;
        }
        FbPermissions fbPermissionsFromAsset = getFbPermissionsFromAsset(context, str, context.getPackageName());
        if (fbPermissionsFromAsset == null) {
            this.mReporter.report(String.format("Failed to read fb permissions from '%s' asset", str));
            return false;
        } else if (!fbPermissionsFromAsset.permissions.contains(str2)) {
            this.mReporter.report(String.format("Missing FBPermission '%s' in '%s' required by '%s'", str2, str, context.getPackageName()));
            return false;
        } else if (fbPermissionsFromAsset.signatures.isEmpty()) {
            this.mReporter.report(String.format("Missing signature entry while verifying '%s' from package '%s'", str2, str));
            return false;
        } else {
            try {
                String sha256Hash = AppVerifier.getSignatureFromPackageName(context, str).getSha256Hash();
                long versionCodeFromPackageName = AppVerifier.getVersionCodeFromPackageName(context, str);
                if (sha256Hash == null || versionCodeFromPackageName <= 0) {
                    this.mReporter.report(String.format("Invalid key hash or version code for package '%s' while verifying '%s'", str, str2));
                    return false;
                }
                for (FbPermissionsSignature fbPermissionsSignature : fbPermissionsFromAsset.signatures) {
                    String str3 = fbPermissionsSignature.algorithm;
                    String str4 = fbPermissionsSignature.value;
                    if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str3)) {
                        this.mReporter.report(String.format("Invalid signature for package '%s' while verifying '%s': algorithm(%s), value(%s)", str, str2, str3, str4));
                    } else if (verifyFbPermissionSignature(context, str, String.valueOf(versionCodeFromPackageName), sha256Hash, fbPermissionsFromAsset.permissions, str4, str3)) {
                        return true;
                    }
                }
                return false;
            } catch (SecurityException unused) {
                this.mReporter.report(String.format("Invalid developer key for package '%s' while verifying '%s'", str, str2));
                return false;
            }
        }
    }

    public static boolean doesAppHasFbPermissions(Context context, String str) {
        try {
            if (AllFamilyTrustedSignatures.ALL_FAMILY_DEBUG.contains(AppVerifier.getSignatureFromPackageName(context, context.getPackageName()))) {
                return !getAppFbPermissionsFromManifest(context, str).isEmpty();
            }
            String[] list = context.createPackageContext(str, 0).getAssets().list("");
            if (list == null) {
                return false;
            }
            for (String str2 : list) {
                if (str2.equals(ASSET_FILE)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException | IOException unused) {
        }
    }

    private void logCheckResult(String str, boolean z) {
        String str2;
        if (z) {
            str2 = String.format("%s; request is allowed for fail-open", str);
        } else {
            str2 = String.format("%s; request is denied for fail-close", str);
        }
        this.mReporter.report(str2);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public boolean verifyFbPermissionSignature(Context context, String str, String str2, String str3, Set<String> set, String str4, String str5) {
        String packageName = context.getPackageName();
        try {
            return doSignatureVerification(getPublicKey(context, packageName), binaryEncode(set, str, str2, str3, packageName), Base64.decode(str4, 10), str5);
        } catch (SecurityException unused) {
            this.mReporter.report("Failed to get provider package signature");
            return false;
        } catch (FbPermissionEncoder.EncoderException unused2) {
            this.mReporter.report("Failed to encode data using FbPermissionEncoder");
            return false;
        } catch (CertificateException unused3) {
            this.mReporter.report("Failed to get public key due to certificate exception");
            return false;
        } catch (InvalidKeyException unused4) {
            this.mReporter.report("Invalid public key");
            return false;
        } catch (NoSuchAlgorithmException unused5) {
            this.mReporter.report("Unsupported signature algorithm");
            return false;
        } catch (SignatureException unused6) {
            this.mReporter.report("Signature type wrong or improperly encoded");
            return false;
        }
    }

    private static byte[] binaryEncode(Set<String> set, String str, String str2, String str3, String str4) throws FbPermissionEncoder.EncoderException {
        FbPermissionEncoder fbPermissionEncoder = new FbPermissionEncoder();
        fbPermissionEncoder.update(set, (byte) 1);
        fbPermissionEncoder.update(str, (byte) 2);
        fbPermissionEncoder.update(str2, (byte) 3);
        fbPermissionEncoder.update(str3, (byte) 4);
        fbPermissionEncoder.update(str4, (byte) 5);
        return fbPermissionEncoder.getEncoded();
    }

    private static boolean doSignatureVerification(PublicKey publicKey, byte[] bArr, byte[] bArr2, String str) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        Signature instance = Signature.getInstance(str);
        instance.initVerify(publicKey);
        instance.update(bArr);
        return instance.verify(bArr2);
    }

    private static PublicKey getPublicKey(Context context, String str) throws CertificateException {
        return CertificateFactory.getInstance(CERT_TYPE).generateCertificate(new ByteArrayInputStream(AppVerifier.getRawSignature(context, str).toByteArray())).getPublicKey();
    }

    /* access modifiers changed from: package-private */
    public static class FbPermissions {
        public Set<String> permissions = new HashSet();
        public Set<FbPermissionsSignature> signatures = new HashSet();

        FbPermissions() {
        }
    }

    /* access modifiers changed from: package-private */
    public static class FbPermissionsSignature {
        public String algorithm;
        public String value;

        FbPermissionsSignature() {
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    @VisibleForTesting(otherwise = 2)
    public FbPermissions getFbPermissionsFromAsset(Context context, String str, String str2) {
        JSONArray jSONArray;
        FbPermissions fbPermissions = new FbPermissions();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.createPackageContext(str, 0).getAssets().open(ASSET_FILE)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            if (sb.length() == 0) {
                this.mReporter.report(String.format("Consumer app '%s' has an empty FbPermissions asset file", str));
                return null;
            }
            JSONObject jSONObject = new JSONObject(sb.toString());
            if (!jSONObject.has(str2)) {
                return null;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
            JSONArray jSONArray2 = jSONObject2.getJSONArray(KEY_PERMISSIONS);
            if (jSONArray2.length() == 0) {
                this.mReporter.report(String.format("Consumer app '%s' has an empty permissions list for '%s' provider", str, str2));
                return null;
            }
            for (int i = 0; i < jSONArray2.length(); i++) {
                fbPermissions.permissions.add(jSONArray2.getString(i));
            }
            if (jSONObject2.has(KEY_SIGNATURES)) {
                jSONArray = jSONObject2.getJSONArray(KEY_SIGNATURES);
            } else {
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(jSONObject2.getJSONObject(KEY_SINGLE_SIGNATURE));
                jSONArray = jSONArray3;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                FbPermissionsSignature fbPermissionsSignature = new FbPermissionsSignature();
                fbPermissionsSignature.algorithm = jSONObject3.getString(KEY_ALGORITHM);
                fbPermissionsSignature.value = jSONObject3.getString("value");
                fbPermissions.signatures.add(fbPermissionsSignature);
            }
            return fbPermissions;
        } catch (PackageManager.NameNotFoundException unused) {
            this.mReporter.report(String.format("Cannot create package context for '%s'", str));
            return null;
        } catch (IOException e) {
            this.mReporter.report(String.format("Failed to read FBPermission asset file from package '%s': %s", str, e.getMessage()));
            return null;
        } catch (JSONException e2) {
            this.mReporter.report(String.format("Failed to decode FBPermission asset file from package '%s' due to JSON exception: %s", str, e2.getMessage()));
            return null;
        }
    }
}
