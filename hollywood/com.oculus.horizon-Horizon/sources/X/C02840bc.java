package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"CatchGeneralException", "DefaultLocale"})
/* renamed from: X.0bc  reason: invalid class name and case insensitive filesystem */
public final class C02840bc {
    public static C02840bc A01;
    public AnonymousClass0b1 A00 = new C04580iA();

    public static void A03(C02840bc r3, String str, boolean z) {
        Object[] objArr;
        String str2;
        if (z) {
            objArr = new Object[]{str};
            str2 = "%s; request is allowed for fail-open";
        } else {
            objArr = new Object[]{str};
            str2 = "%s; request is denied for fail-close";
        }
        r3.A00.report(String.format(str2, objArr));
    }

    public static boolean A04(Context context, String str) {
        try {
            if (C02780bN.A10.contains(AnonymousClass0bU.A03(context, context.getPackageName()))) {
                return !A02(context, str).isEmpty();
            }
            String[] list = context.createPackageContext(str, 0).getAssets().list("");
            if (list == null) {
                return false;
            }
            for (String str2 : list) {
                if (str2.equals("fbpermissions.json")) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException | IOException unused) {
        }
    }

    public static synchronized C02840bc A00() {
        C02840bc r0;
        synchronized (C02840bc.class) {
            r0 = A01;
            if (r0 == null) {
                r0 = new C02840bc();
                A01 = r0;
            }
        }
        return r0;
    }

    public static synchronized C02840bc A01(AnonymousClass0b1 r2) {
        C02840bc A002;
        synchronized (C02840bc.class) {
            A002 = A00();
            A002.A00 = r2;
        }
        return A002;
    }

    @Deprecated
    public static final boolean A05(Context context, String str, String str2) {
        if (!C02780bN.A10.contains(AnonymousClass0bU.A03(context, context.getPackageName())) || !A02(context, str).contains(str2)) {
            return false;
        }
        return true;
    }

    @VisibleForTesting(otherwise = 2)
    public static final boolean A06(C02840bc r19, Context context, String str, String str2) {
        AnonymousClass0b1 r5;
        Object[] objArr;
        String str3;
        AnonymousClass0b1 r2;
        Object[] objArr2;
        String str4;
        JSONArray jSONArray;
        AnonymousClass0b1 r1;
        String str5;
        if (A04(context, str)) {
            String packageName = context.getPackageName();
            C02820ba r9 = new C02820ba();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.createPackageContext(str, 0).getAssets().open("fbpermissions.json")));
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
                    r19.A00.report(String.format("Consumer app '%s' has an empty FbPermissions asset file", str));
                } else {
                    JSONObject jSONObject = new JSONObject(sb.toString());
                    if (jSONObject.has(packageName)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(packageName);
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("permissions");
                        if (jSONArray2.length() == 0) {
                            r19.A00.report(String.format("Consumer app '%s' has an empty permissions list for '%s' provider", str, packageName));
                        } else {
                            for (int i = 0; i < jSONArray2.length(); i++) {
                                r9.A00.add(jSONArray2.getString(i));
                            }
                            if (jSONObject2.has("signatures")) {
                                jSONArray = jSONObject2.getJSONArray("signatures");
                            } else {
                                jSONArray = new JSONArray();
                                jSONArray.put(jSONObject2.getJSONObject("signature"));
                            }
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                                C02830bb r22 = new C02830bb();
                                r22.A00 = jSONObject3.getString("algorithm");
                                r22.A01 = jSONObject3.getString("value");
                                r9.A01.add(r22);
                            }
                            if (!r9.A00.contains(str2)) {
                                r5 = r19.A00;
                                objArr = new Object[]{str2, str, context.getPackageName()};
                                str3 = "Missing FBPermission '%s' in '%s' required by '%s'";
                            } else if (r9.A01.isEmpty()) {
                                r5 = r19.A00;
                                objArr = new Object[]{str2, str};
                                str3 = "Missing signature entry while verifying '%s' from package '%s'";
                            } else {
                                try {
                                    String str6 = AnonymousClass0bU.A03(context, str).sha256Hash;
                                    long A002 = AnonymousClass0bU.A00(context, str);
                                    if (str6 == null || A002 <= 0) {
                                        r5 = r19.A00;
                                        objArr = new Object[]{str, str2};
                                        str3 = "Invalid key hash or version code for package '%s' while verifying '%s'";
                                    } else {
                                        for (C02830bb r12 : r9.A01) {
                                            String str7 = r12.A00;
                                            String str8 = r12.A01;
                                            if (TextUtils.isEmpty(str8) || TextUtils.isEmpty(str7)) {
                                                r19.A00.report(String.format("Invalid signature for package '%s' while verifying '%s': algorithm(%s), value(%s)", str, str2, str7, str8));
                                            } else {
                                                String valueOf = String.valueOf(A002);
                                                Set<String> set = r9.A00;
                                                String packageName2 = context.getPackageName();
                                                try {
                                                    PublicKey publicKey = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(AnonymousClass0bU.A02(AnonymousClass0bU.A01(context, packageName2)).toByteArray())).getPublicKey();
                                                    C02860be r122 = new C02860be();
                                                    if (r122.A00 < 255) {
                                                        TreeSet treeSet = new TreeSet(set);
                                                        if (treeSet.size() <= 255) {
                                                            ArrayList arrayList = new ArrayList();
                                                            Iterator it = treeSet.iterator();
                                                            while (it.hasNext()) {
                                                                byte[] bytes = ((String) it.next()).getBytes(C02860be.A02);
                                                                if (bytes.length <= 255) {
                                                                    arrayList.add(bytes);
                                                                } else {
                                                                    throw new C02850bd("String size (UTF-8 encoded) cannot exceed 255");
                                                                }
                                                            }
                                                            r122.A01.write(1);
                                                            r122.A01.write(treeSet.size() & 255);
                                                            Iterator it2 = arrayList.iterator();
                                                            while (it2.hasNext()) {
                                                                byte[] bArr = (byte[]) it2.next();
                                                                ByteArrayOutputStream byteArrayOutputStream = r122.A01;
                                                                int length = bArr.length;
                                                                byteArrayOutputStream.write(length & 255);
                                                                r122.A01.write(bArr, 0, length);
                                                            }
                                                            r122.A00++;
                                                            r122.A00(str, (byte) 2);
                                                            r122.A00(valueOf, (byte) 3);
                                                            r122.A00(str6, (byte) 4);
                                                            r122.A00(packageName2, (byte) 5);
                                                            byte[] byteArray = r122.A01.toByteArray();
                                                            byteArray[1] = (byte) (r122.A00 & 255);
                                                            byte[] decode = Base64.decode(str8, 10);
                                                            Signature instance = Signature.getInstance(str7);
                                                            instance.initVerify(publicKey);
                                                            instance.update(byteArray);
                                                            if (instance.verify(decode)) {
                                                                return true;
                                                            }
                                                        } else {
                                                            throw new C02850bd("Collection size (duplicates removed) cannot exceed 255");
                                                        }
                                                    } else {
                                                        throw new C02850bd("Total number of entries cannot exceed 255");
                                                    }
                                                } catch (SecurityException unused) {
                                                    r1 = r19.A00;
                                                    str5 = "Failed to get provider package signature";
                                                    r1.report(str5);
                                                } catch (C02850bd unused2) {
                                                    r1 = r19.A00;
                                                    str5 = "Failed to encode data using FbPermissionEncoder";
                                                    r1.report(str5);
                                                } catch (CertificateException unused3) {
                                                    r1 = r19.A00;
                                                    str5 = "Failed to get public key due to certificate exception";
                                                    r1.report(str5);
                                                } catch (InvalidKeyException unused4) {
                                                    r1 = r19.A00;
                                                    str5 = "Invalid public key";
                                                    r1.report(str5);
                                                } catch (NoSuchAlgorithmException unused5) {
                                                    r1 = r19.A00;
                                                    str5 = "Unsupported signature algorithm";
                                                    r1.report(str5);
                                                } catch (SignatureException unused6) {
                                                    r1 = r19.A00;
                                                    str5 = "Signature type wrong or improperly encoded";
                                                    r1.report(str5);
                                                }
                                            }
                                        }
                                    }
                                } catch (SecurityException unused7) {
                                    r19.A00.report(String.format("Invalid developer key for package '%s' while verifying '%s'", str, str2));
                                    return false;
                                }
                            }
                            r5.report(String.format(str3, objArr));
                            return false;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException unused8) {
                r19.A00.report(String.format("Cannot create package context for '%s'", str));
            } catch (IOException e) {
                r2 = r19.A00;
                objArr2 = new Object[]{str, e.getMessage()};
                str4 = "Failed to read FBPermission asset file from package '%s': %s";
                r2.report(String.format(str4, objArr2));
            } catch (JSONException e2) {
                r2 = r19.A00;
                objArr2 = new Object[]{str, e2.getMessage()};
                str4 = "Failed to decode FBPermission asset file from package '%s' due to JSON exception: %s";
                r2.report(String.format(str4, objArr2));
            }
            r5 = r19.A00;
            objArr = new Object[]{str};
            str3 = "Failed to read fb permissions from '%s' asset";
            r5.report(String.format(str3, objArr));
            return false;
        }
        return false;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:18:0x003d */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.VisibleForTesting(otherwise = 2)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> A02(android.content.Context r4, java.lang.String r5) {
        /*
            java.util.List r3 = java.util.Collections.emptyList()
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003e }
            r0 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r0 = r1.getApplicationInfo(r5, r0)     // Catch:{ NameNotFoundException -> 0x003e }
            android.os.Bundle r1 = r0.metaData
            if (r1 == 0) goto L_0x003d
            int r0 = r1.size()
            if (r0 <= 0) goto L_0x003d
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Set r0 = r1.keySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0025:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r1 = r2.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = ".*\\.fbpermission\\..*"
            boolean r0 = r1.matches(r0)
            if (r0 == 0) goto L_0x0025
            r3.add(r1)
            goto L_0x0025
        L_0x003d:
            return r3
        L_0x003e:
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02840bc.A02(android.content.Context, java.lang.String):java.util.List");
    }
}
