package defpackage;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.C1725IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: Gp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0406Gp0 {

    /* renamed from: a  reason: collision with root package name */
    public final Notification.Builder f8113a;
    public final C0223Dp0 b;
    public RemoteViews c;
    public RemoteViews d;
    public final List e = new ArrayList();
    public final Bundle f = new Bundle();

    public C0406Gp0(C0223Dp0 dp0) {
        List<String> list;
        Bundle bundle;
        Bundle bundle2;
        this.b = dp0;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8113a = new Notification.Builder(dp0.f7913a, dp0.A);
        } else {
            this.f8113a = new Notification.Builder(dp0.f7913a);
        }
        Notification notification = dp0.C;
        this.f8113a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(dp0.e).setContentText(dp0.f).setContentInfo(dp0.i).setContentIntent(dp0.g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) == 0 ? false : true).setLargeIcon(dp0.h).setNumber(0).setProgress(dp0.n, dp0.o, dp0.p);
        this.f8113a.setSubText(dp0.m).setUsesChronometer(false).setPriority(dp0.j);
        Iterator it = dp0.b.iterator();
        while (it.hasNext()) {
            C0101Bp0 bp0 = (C0101Bp0) it.next();
            C1725IconCompat a2 = bp0.a();
            Notification.Action.Builder builder = new Notification.Action.Builder(a2 != null ? a2.g() : null, bp0.j, bp0.k);
            FL0[] fl0Arr = bp0.c;
            if (fl0Arr != null) {
                int length = fl0Arr.length;
                RemoteInput[] remoteInputArr = new RemoteInput[length];
                if (fl0Arr.length <= 0) {
                    for (int i = 0; i < length; i++) {
                        builder.addRemoteInput(remoteInputArr[i]);
                    }
                } else {
                    FL0 fl0 = fl0Arr[0];
                    throw null;
                }
            }
            if (bp0.f7760a != null) {
                bundle2 = new Bundle(bp0.f7760a);
            } else {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android.support.allowGeneratedReplies", bp0.e);
            int i2 = Build.VERSION.SDK_INT;
            builder.setAllowGeneratedReplies(bp0.e);
            bundle2.putInt("android.support.action.semanticAction", bp0.g);
            if (i2 >= 28) {
                builder.setSemanticAction(bp0.g);
            }
            if (i2 >= 29) {
                builder.setContextual(bp0.h);
            }
            bundle2.putBoolean("android.support.action.showsUserInterface", bp0.f);
            builder.addExtras(bundle2);
            this.f8113a.addAction(builder.build());
        }
        Bundle bundle3 = dp0.u;
        if (bundle3 != null) {
            this.f.putAll(bundle3);
        }
        int i3 = Build.VERSION.SDK_INT;
        this.c = dp0.y;
        this.d = dp0.z;
        this.f8113a.setShowWhen(dp0.k);
        this.f8113a.setLocalOnly(dp0.s).setGroup(dp0.q).setGroupSummary(dp0.r).setSortKey(null);
        this.f8113a.setCategory(dp0.t).setColor(dp0.v).setVisibility(dp0.w).setPublicVersion(dp0.x).setSound(notification.sound, notification.audioAttributes);
        if (i3 < 28) {
            list = a(b(dp0.c), dp0.D);
        } else {
            list = dp0.D;
        }
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                this.f8113a.addPerson(str);
            }
        }
        if (dp0.d.size() > 0) {
            if (dp0.u == null) {
                dp0.u = new Bundle();
            }
            Bundle bundle4 = dp0.u.getBundle("android.car.EXTENSIONS");
            bundle4 = bundle4 == null ? new Bundle() : bundle4;
            Bundle bundle5 = new Bundle(bundle4);
            Bundle bundle6 = new Bundle();
            for (int i4 = 0; i4 < dp0.d.size(); i4++) {
                String num = Integer.toString(i4);
                C0101Bp0 bp02 = (C0101Bp0) dp0.d.get(i4);
                Object obj = AbstractC0467Hp0.f8183a;
                Bundle bundle7 = new Bundle();
                C1725IconCompat a3 = bp02.a();
                bundle7.putInt("icon", a3 != null ? a3.c() : 0);
                bundle7.putCharSequence("title", bp02.j);
                bundle7.putParcelable("actionIntent", bp02.k);
                if (bp02.f7760a != null) {
                    bundle = new Bundle(bp02.f7760a);
                } else {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android.support.allowGeneratedReplies", bp02.e);
                bundle7.putBundle("extras", bundle);
                bundle7.putParcelableArray("remoteInputs", AbstractC0467Hp0.a(bp02.c));
                bundle7.putBoolean("showsUserInterface", bp02.f);
                bundle7.putInt("semanticAction", bp02.g);
                bundle6.putBundle(num, bundle7);
            }
            bundle4.putBundle("invisible_actions", bundle6);
            bundle5.putBundle("invisible_actions", bundle6);
            if (dp0.u == null) {
                dp0.u = new Bundle();
            }
            dp0.u.putBundle("android.car.EXTENSIONS", bundle4);
            this.f.putBundle("android.car.EXTENSIONS", bundle5);
        }
        int i5 = Build.VERSION.SDK_INT;
        this.f8113a.setExtras(dp0.u).setRemoteInputHistory(null);
        RemoteViews remoteViews = dp0.y;
        if (remoteViews != null) {
            this.f8113a.setCustomContentView(remoteViews);
        }
        RemoteViews remoteViews2 = dp0.z;
        if (remoteViews2 != null) {
            this.f8113a.setCustomBigContentView(remoteViews2);
        }
        if (i5 >= 26) {
            this.f8113a.setBadgeIconType(0).setSettingsText(null).setShortcutId(null).setTimeoutAfter(0).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(dp0.A)) {
                this.f8113a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i5 >= 28) {
            Iterator it2 = dp0.c.iterator();
            if (it2.hasNext()) {
                C5859z.a(it2.next());
                throw null;
            }
        }
        if (i5 >= 29) {
            this.f8113a.setAllowSystemGeneratedContextualActions(dp0.B);
            this.f8113a.setBubbleMetadata(null);
        }
    }

    public static List a(List list, List list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        C5271va vaVar = new C5271va(list2.size() + list.size());
        vaVar.addAll(list);
        vaVar.addAll(list2);
        return new ArrayList(vaVar);
    }

    public static List b(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        C5859z.a(it.next());
        throw null;
    }
}
