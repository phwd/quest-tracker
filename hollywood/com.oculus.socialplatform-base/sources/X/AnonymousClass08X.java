package X;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@RequiresApi(26)
/* renamed from: X.08X  reason: invalid class name */
public class AnonymousClass08X implements ActionMode.Callback {
    public Class<?> A00;
    public Method A01;
    public boolean A02;
    public boolean A03 = false;
    public final TextView A04;
    public final ActionMode.Callback A05;

    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.A05.onActionItemClicked(actionMode, menuItem);
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.A05.onCreateActionMode(actionMode, menu);
    }

    public final void onDestroyActionMode(ActionMode actionMode) {
        this.A05.onDestroyActionMode(actionMode);
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Method declaredMethod;
        boolean z;
        String str;
        TextView textView = this.A04;
        Context context = textView.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.A03) {
            this.A03 = true;
            try {
                Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.A00 = cls;
                this.A01 = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.A02 = true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                this.A00 = null;
                this.A01 = null;
                this.A02 = false;
            }
        }
        try {
            if (!this.A02 || !this.A00.isInstance(menu)) {
                declaredMethod = menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
            } else {
                declaredMethod = this.A01;
            }
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    declaredMethod.invoke(menu, Integer.valueOf(size));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0)) {
                    String packageName = context.getPackageName();
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (packageName.equals(activityInfo.packageName) || (activityInfo.exported && ((str = activityInfo.permission) == null || context.checkSelfPermission(str) == 0))) {
                        arrayList.add(resolveInfo);
                    }
                }
            }
            for (int i = 0; i < arrayList.size(); i++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) arrayList.get(i);
                MenuItem add = menu.add(0, 0, i + 100, resolveInfo2.loadLabel(packageManager));
                Intent type = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
                if ((textView instanceof Editable) && textView.onCheckIsTextEditor()) {
                    z = true;
                    if (textView.isEnabled()) {
                        Intent putExtra = type.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !z);
                        ActivityInfo activityInfo2 = resolveInfo2.activityInfo;
                        add.setIntent(putExtra.setClassName(activityInfo2.packageName, activityInfo2.name)).setShowAsAction(1);
                    }
                }
                z = false;
                Intent putExtra2 = type.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !z);
                ActivityInfo activityInfo22 = resolveInfo2.activityInfo;
                add.setIntent(putExtra2.setClassName(activityInfo22.packageName, activityInfo22.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
        }
        return this.A05.onPrepareActionMode(actionMode, menu);
    }

    public AnonymousClass08X(ActionMode.Callback callback, TextView textView) {
        this.A05 = callback;
        this.A04 = textView;
    }
}
