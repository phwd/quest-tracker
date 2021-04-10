package defpackage;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextClassification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: H3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H3 implements F3 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f8132a = new HashMap();

    public static View.OnClickListener c(CharSequence charSequence, PendingIntent pendingIntent) {
        if (TextUtils.isEmpty(charSequence) || pendingIntent == null) {
            return null;
        }
        return new G3(pendingIntent);
    }

    public static final /* synthetic */ void d(PendingIntent pendingIntent) {
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            AbstractC1220Ua0.a("MenuItemProvider", "Error creating OnClickListener from PendingIntent", e);
        }
    }

    public static List e(Context context, TextClassification textClassification) {
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RemoteAction remoteAction : textClassification.getActions()) {
            arrayList.add(remoteAction.getIcon().loadDrawable(context));
        }
        return arrayList;
    }

    public void a(Context context, Menu menu, TextClassification textClassification, List list) {
        if (textClassification != null) {
            int size = textClassification.getActions().size();
            if (size > 0) {
                MenuItem findItem = menu.findItem(16908353);
                if (textClassification.getActions().get(0).shouldShowIcon()) {
                    findItem.setIcon(list == null ? null : (Drawable) list.get(0));
                } else {
                    findItem.setIcon((Drawable) null);
                }
            }
            for (int i = 1; i < size; i++) {
                RemoteAction remoteAction = textClassification.getActions().get(i);
                View.OnClickListener c = c(remoteAction.getTitle(), remoteAction.getActionIntent());
                if (c != null) {
                    MenuItem add = menu.add(16908353, 0, i + 50, remoteAction.getTitle());
                    add.setContentDescription(remoteAction.getContentDescription());
                    if (remoteAction.shouldShowIcon()) {
                        add.setIcon(list == null ? null : (Drawable) list.get(i));
                    }
                    add.setShowAsAction(1);
                    this.f8132a.put(add, c);
                }
            }
        }
    }

    public void b() {
        this.f8132a.clear();
    }

    public void f(MenuItem menuItem, View view) {
        View.OnClickListener onClickListener = (View.OnClickListener) this.f8132a.get(menuItem);
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
