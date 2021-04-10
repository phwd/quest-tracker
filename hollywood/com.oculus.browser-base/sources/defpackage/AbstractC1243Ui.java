package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: Ui  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1243Ui {
    public static BookmarkId a(C0695Li li, Tab tab, View$OnClickListenerC5098uY0 uy0, Activity activity, boolean z) {
        String str;
        C4076oY0 oy0;
        String str2;
        String title = tab.getTitle();
        String n = tab.n();
        BookmarkId e = e();
        BookmarkBridge.BookmarkItem c = e != null ? li.c(e) : null;
        if (e == null || c == null || c.g || !c.d) {
            e = li.d();
        }
        Objects.requireNonNull(li);
        Object obj = ThreadUtils.f10596a;
        BookmarkId bookmarkId = (BookmarkId) N.Mg53Jgou(li.b, li, e, N.MywxQQ$n(li.b, li, e.getId(), e.getType()), TextUtils.isEmpty(title) ? n : title, n);
        if (bookmarkId == null) {
            Object[] objArr = new Object[4];
            objArr[0] = e;
            objArr[1] = li.d();
            objArr[2] = li.d();
            if (c == null) {
                str2 = "null";
            } else {
                str2 = c.a() + " " + c.g + " " + c.d;
            }
            objArr[3] = str2;
            AbstractC1220Ua0.a("BookmarkUtils", "Failed to add bookmarks: parentTypeAndId %s, defaultFolderTypeAndId %s, mobileFolderTypeAndId %s, parentEditable Managed isFolder %s,", objArr);
            NU0.f8549a.p("enhanced_bookmark_last_used_parent_folder", li.d().toString());
        }
        if (bookmarkId == null) {
            oy0 = C4076oY0.c(activity.getString(R.string.f48130_resource_name_obfuscated_RES_2131952130), new C1060Ri(), 1, 0);
            oy0.i = false;
            AbstractC3535lK0.a("EnhancedBookmarks.AddingFailed");
        } else {
            BookmarkBridge.BookmarkItem c2 = li.c(li.c(bookmarkId).e);
            if (c2 == null) {
                str = "";
            } else {
                str = c2.f10620a;
            }
            C1182Ti ti = new C1182Ti(activity, bookmarkId);
            if (e() != null) {
                oy0 = C4076oY0.c(str, ti, 0, 0);
                oy0.c = activity.getString(R.string.f48160_resource_name_obfuscated_RES_2131952133);
            } else if (z) {
                oy0 = C4076oY0.c(activity.getString(R.string.f48140_resource_name_obfuscated_RES_2131952131, new Object[]{AbstractC0456Hk.f8178a.b}), ti, 0, 0);
            } else {
                oy0 = C4076oY0.c(activity.getString(R.string.f48150_resource_name_obfuscated_RES_2131952132), ti, 0, 0);
            }
            oy0.i = false;
            oy0.d = activity.getString(R.string.f48060_resource_name_obfuscated_RES_2131952123);
            oy0.e = null;
        }
        uy0.l(oy0);
        return bookmarkId;
    }

    public static BookmarkId b(String str, String str2, View$OnClickListenerC5098uY0 uy0, BookmarkBridge bookmarkBridge, Context context) {
        Objects.requireNonNull(bookmarkBridge);
        Object obj = ThreadUtils.f10596a;
        BookmarkId bookmarkId = (BookmarkId) N.MPWBoFyN(bookmarkBridge.b, bookmarkBridge, str2, str);
        if (bookmarkId != null) {
            uy0.l(C4076oY0.c(context.getString(R.string.f59970_resource_name_obfuscated_RES_2131953314), new C1121Si(), 0, 37));
            Um1.a(Profile.b()).notifyEvent("read_later_article_saved");
        }
        return bookmarkId;
    }

    public static Drawable c(Context context, int i) {
        if (i == 2) {
            return AbstractC2417ep1.f(context, R.drawable.f32550_resource_name_obfuscated_RES_2131231295, d(i));
        }
        return C0636Ki1.b(context, R.drawable.f30280_resource_name_obfuscated_RES_2131231068, d(i));
    }

    public static int d(int i) {
        return i == 2 ? R.color.f11230_resource_name_obfuscated_RES_2131099813 : R.color.f11390_resource_name_obfuscated_RES_2131099829;
    }

    public static BookmarkId e() {
        PU0 pu0 = NU0.f8549a;
        pu0.f8694a.a("enhanced_bookmark_last_used_parent_folder");
        if (!AbstractC3983nz.f10523a.contains("enhanced_bookmark_last_used_parent_folder")) {
            return null;
        }
        String i = pu0.i("enhanced_bookmark_last_used_parent_folder", null);
        int i2 = 0;
        long j = -1;
        if (TextUtils.isEmpty(i)) {
            return new BookmarkId(-1, 0);
        }
        char charAt = i.charAt(0);
        if (charAt == 'p' || charAt == 'r') {
            if (charAt == 'p') {
                i2 = 1;
            } else if (charAt == 'r') {
                i2 = 2;
            }
            i = i.substring(1);
        }
        try {
            j = Long.parseLong(i);
        } catch (NumberFormatException e) {
            Log.e("BookmarkId", "Error parsing url to extract the bookmark folder id.", e);
        }
        return new BookmarkId(j, i2);
    }

    public static void f(Activity activity, BookmarkId bookmarkId) {
        String str;
        ComponentName componentName;
        Object obj = ThreadUtils.f10596a;
        Context applicationContext = activity == null ? ContextUtils.getApplicationContext() : activity;
        String str2 = "chrome-native://bookmarks/";
        if (bookmarkId == null) {
            str = NU0.f8549a.i("enhanced_bookmark_last_used_url", str2);
        } else {
            Uri.Builder buildUpon = Uri.parse("chrome-native://bookmarks/folder/").buildUpon();
            buildUpon.appendPath(bookmarkId.toString());
            str = buildUpon.build().toString();
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str;
        }
        if (DeviceFormFactor.a(applicationContext)) {
            if (activity == null) {
                componentName = null;
            } else {
                componentName = activity.getComponentName();
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent.putExtra("com.android.browser.application_id", applicationContext.getApplicationContext().getPackageName());
            intent.setFlags(268435456);
            intent.putExtra("com.google.chrome.transition_type", 2);
            if (componentName != null) {
                AbstractActivityC2601fu.K1(intent, componentName);
            } else {
                intent.setClass(applicationContext.getApplicationContext(), Lr.class);
            }
            S20.y(intent, null);
            return;
        }
        Intent intent2 = new Intent(applicationContext, AbstractActivityC5975zi.class);
        intent2.setData(Uri.parse(str2));
        if (activity != null) {
            intent2.putExtra("org.chromium.chrome.browser.parent_component", activity.getComponentName());
            activity.startActivity(intent2);
            return;
        }
        intent2.addFlags(268435456);
        S20.y(intent2, null);
    }

    public static void g(Context context, BookmarkId bookmarkId) {
        AbstractC3535lK0.a("MobileBookmarkManagerEditBookmark");
        Intent intent = new Intent(context, AbstractActivityC0634Ki.class);
        intent.putExtra("BookmarkEditActivity.BookmarkId", bookmarkId.toString());
        context.startActivity(intent);
    }
}
