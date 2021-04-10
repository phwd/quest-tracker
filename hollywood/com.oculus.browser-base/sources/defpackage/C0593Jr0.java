package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.chromium.chrome.browser.tab.Tab;
import org.json.JSONArray;

/* renamed from: Jr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0593Jr0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8316a;
    public final String[] b;

    public C0593Jr0(Context context, Tab tab) {
        String k = T51.k(tab);
        if (TextUtils.isEmpty(k)) {
            this.f8316a = "";
            this.b = null;
            return;
        }
        String[] b2 = b(context, k);
        this.b = b2;
        if (b2 == null) {
            this.f8316a = "";
        } else {
            this.f8316a = k;
        }
    }

    public static String[] b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            int length = signatureArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                instance.update(signatureArr[i].toByteArray());
                byte[] digest = instance.digest();
                strArr[i] = digest == null ? null : Base64.encodeToString(digest, 0);
            }
            Arrays.sort(strArr);
            return strArr;
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public String a() {
        if (c()) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : this.b) {
            jSONArray.put(str);
        }
        return new JSONArray().put(this.f8316a).put(jSONArray).toString();
    }

    public boolean c() {
        return TextUtils.isEmpty(this.f8316a) || this.b == null;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C0593Jr0)) {
            return false;
        }
        C0593Jr0 jr0 = (C0593Jr0) obj;
        if (!this.f8316a.equals(jr0.f8316a) || !Arrays.equals(this.b, jr0.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.f8316a, this.b});
    }

    public String toString() {
        return a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r0 = r3;
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        r7.f8316a = r0;
        r7.b = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[ExcHandler: JSONException (unused org.json.JSONException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0593Jr0(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = ""
            r7.<init>()
            r1 = 0
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0042, all -> 0x003c }
            r2.<init>(r8)     // Catch:{ JSONException -> 0x0042, all -> 0x003c }
            int r8 = r2.length()     // Catch:{ JSONException -> 0x0042, all -> 0x003c }
            r3 = 2
            if (r8 != r3) goto L_0x0037
            r8 = 0
            java.lang.String r3 = r2.getString(r8)     // Catch:{ JSONException -> 0x0042, all -> 0x003c }
            r4 = 1
            org.json.JSONArray r2 = r2.getJSONArray(r4)     // Catch:{ JSONException -> 0x0042, all -> 0x0034 }
            int r4 = r2.length()     // Catch:{ JSONException -> 0x0042, all -> 0x0034 }
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ JSONException -> 0x0042, all -> 0x0034 }
        L_0x0022:
            if (r8 >= r4) goto L_0x0031
            java.lang.String r6 = r2.getString(r8)     // Catch:{ JSONException -> 0x0042, all -> 0x002d }
            r5[r8] = r6     // Catch:{ JSONException -> 0x0042, all -> 0x002d }
            int r8 = r8 + 1
            goto L_0x0022
        L_0x002d:
            r8 = move-exception
            r0 = r3
            r1 = r5
            goto L_0x003d
        L_0x0031:
            r0 = r3
            r1 = r5
            goto L_0x0037
        L_0x0034:
            r8 = move-exception
            r0 = r3
            goto L_0x003d
        L_0x0037:
            r7.f8316a = r0
            r7.b = r1
            goto L_0x0046
        L_0x003c:
            r8 = move-exception
        L_0x003d:
            r7.f8316a = r0
            r7.b = r1
            throw r8
        L_0x0042:
            r7.f8316a = r0
            r7.b = r1
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0593Jr0.<init>(java.lang.String):void");
    }

    public C0593Jr0() {
        this.f8316a = "";
        this.b = null;
    }
}
