package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import com.oculus.browser.R;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Clipboard implements ClipboardManager.OnPrimaryClipChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public static Clipboard f11015a;
    public final Context b = ContextUtils.getApplicationContext();
    public ClipboardManager c;
    public long d;
    public C4654rv e;

    public Clipboard() {
        ClipboardManager clipboardManager = (ClipboardManager) ContextUtils.getApplicationContext().getSystemService("clipboard");
        this.c = clipboardManager;
        clipboardManager.addPrimaryClipChangedListener(this);
    }

    public static Clipboard getInstance() {
        if (f11015a == null) {
            f11015a = new Clipboard();
        }
        return f11015a;
    }

    public String a(ClipData clipData) {
        ClipDescription description = clipData.getDescription();
        if (description.hasMimeType("text/html")) {
            return clipData.getItemAt(0).getHtmlText();
        }
        if (description.hasMimeType("text/plain")) {
            CharSequence text = clipData.getItemAt(0).getText();
            if (!(text instanceof Spanned)) {
                return null;
            }
            Spanned spanned = (Spanned) text;
            boolean z = true;
            Class[] clsArr = {CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
            int i = 0;
            while (true) {
                if (i >= 3) {
                    z = false;
                    break;
                }
                if (spanned.nextSpanTransition(-1, spanned.length(), clsArr[i]) < spanned.length()) {
                    break;
                }
                i++;
            }
            if (z) {
                return Html.toHtml(spanned, 0);
            }
        }
        return null;
    }

    public void b(String str) {
        if (e(ClipData.newPlainText("url", str))) {
            C1184Ti1.a(this.b, R.string.f54130_resource_name_obfuscated_RES_2131952730, 0).b.show();
        }
    }

    public Uri c() {
        try {
            ClipData primaryClip = this.c.getPrimaryClip();
            if (primaryClip == null) {
                return null;
            }
            if (primaryClip.getItemCount() == 0) {
                return null;
            }
            ClipDescription description = primaryClip.getDescription();
            if (description == null) {
                return null;
            }
            if (!description.hasMimeType("image/*")) {
                return null;
            }
            return primaryClip.getItemAt(0).getUri();
        } catch (Exception unused) {
            return null;
        }
    }

    public final void clear() {
        e(ClipData.newPlainText(null, null));
    }

    public void d(Uri uri) {
        if (uri == null) {
            f();
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if ((i == 26 || i == 27) && this.e != null) {
            NU0.f8549a.p("Chrome.Clipboard.SharedUri", uri.toString());
            for (PackageInfo packageInfo : this.b.getPackageManager().getInstalledPackages(0)) {
                this.b.grantUriPermission(packageInfo.packageName, uri, 1);
            }
        }
        C4484qv qvVar = new C4484qv(this, uri);
        Executor executor = AbstractC2032cb.f9616a;
        qvVar.f();
        ((ExecutorC1463Ya) executor).execute(qvVar.e);
    }

    public final boolean e(ClipData clipData) {
        P21 p21;
        try {
            if (Build.MANUFACTURER.toLowerCase(Locale.US).equals("google")) {
                p21 = null;
            } else {
                StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
                p21 = new P21(threadPolicy);
            }
            try {
                this.c.setPrimaryClip(clipData);
                if (p21 != null) {
                    p21.close();
                }
                return true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (Exception unused) {
            f();
            return false;
        }
    }

    public final void f() {
        C1184Ti1.b(this.b, this.b.getString(R.string.f50220_resource_name_obfuscated_RES_2131952339), 0).b.show();
    }

    public final String getCoercedText() {
        try {
            return this.c.getPrimaryClip().getItemAt(0).coerceToText(this.b).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public final String getHTMLText() {
        try {
            return a(this.c.getPrimaryClip());
        } catch (Exception unused) {
            return null;
        }
    }

    public Bitmap getImage() {
        Bitmap bitmap;
        Object obj = ThreadUtils.f10596a;
        try {
            Uri c2 = c();
            if (c2 == null) {
                return null;
            }
            ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
            if (Build.VERSION.SDK_INT >= 28) {
                bitmap = C2812h7.a(contentResolver, c2);
            } else {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, c2);
            }
            return !(bitmap != null && (bitmap.getConfig() == Bitmap.Config.ARGB_8888 || bitmap.getConfig() == Bitmap.Config.ALPHA_8)) ? bitmap.copy(Bitmap.Config.ARGB_8888, false) : bitmap;
        } catch (IOException | SecurityException unused) {
            return null;
        }
    }

    public final String getImageUriString() {
        Uri c2 = c();
        if (c2 == null) {
            return null;
        }
        return c2.toString();
    }

    public void onPrimaryClipChanged() {
        AbstractC3535lK0.a("MobileClipboardChanged");
        int i = Build.VERSION.SDK_INT;
        if ((i == 26 || i == 27) && this.e != null) {
            PU0 pu0 = NU0.f8549a;
            Uri uri = null;
            String i2 = pu0.i("Chrome.Clipboard.SharedUri", null);
            if (!TextUtils.isEmpty(i2)) {
                uri = Uri.parse(i2);
            }
            if (uri != null && !uri.equals(Uri.EMPTY) && !uri.equals(c())) {
                this.b.revokeUriPermission(uri, 1);
                Objects.requireNonNull(this.e);
                pu0.l("Chrome.Clipboard.SharedUri");
            }
        }
        long j = this.d;
        if (j != 0) {
            N.M3YqItLq(j, this);
        }
    }

    public final void setHTMLText(String str, String str2) {
        e(ClipData.newHtmlText("html", str2, str));
    }

    public void setImage(byte[] bArr, String str) {
        C4654rv rvVar = this.e;
        if (rvVar != null) {
            C4313pv pvVar = new C4313pv(this);
            Objects.requireNonNull(rvVar);
            AbstractC1667aU0.b(bArr, str, pvVar);
        }
    }

    public final void setNativePtr(long j) {
        this.d = j;
    }

    public void setText(String str) {
        e(ClipData.newPlainText("text", str));
    }
}
