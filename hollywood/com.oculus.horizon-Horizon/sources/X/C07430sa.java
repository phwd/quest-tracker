package X;

import android.app.Notification;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat$CarExtender;
import androidx.core.graphics.drawable.IconCompat;
import com.facebook.FacebookSdk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0sa  reason: invalid class name and case insensitive filesystem */
public final class C07430sa implements AnonymousClass03Y {
    public final Notification.Builder A00;
    public final Bundle A01 = new Bundle();
    public final AnonymousClass03h A02;
    public final List<Bundle> A03 = new ArrayList();

    public C07430sa(AnonymousClass03h r23) {
        Notification.Builder builder;
        int i;
        Bundle bundle;
        Bundle[] bundleArr;
        int i2;
        Notification.Action.Builder builder2;
        Bundle bundle2;
        Icon icon;
        this.A02 = r23;
        int i3 = Build.VERSION.SDK_INT;
        Context context = r23.A0B;
        if (i3 >= 26) {
            builder = new Notification.Builder(context, r23.A02);
        } else {
            builder = new Notification.Builder(context);
        }
        this.A00 = builder;
        Notification notification = r23.A09;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(r23.A0E).setContentText(r23.A0D).setContentInfo(null).setContentIntent(r23.A0A).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE) != 0).setLargeIcon(r23.A00).setNumber(0).setProgress(r23.A08, r23.A07, false);
        this.A00.setSubText(null).setUsesChronometer(false).setPriority(r23.A06);
        Iterator<AnonymousClass03d> it = r23.A0F.iterator();
        while (it.hasNext()) {
            AnonymousClass03d next = it.next();
            IconCompat A002 = next.A00();
            if (Build.VERSION.SDK_INT >= 23) {
                if (A002 != null) {
                    icon = A002.A0G();
                } else {
                    icon = null;
                }
                builder2 = new Notification.Action.Builder(icon, next.A02, next.A01);
            } else {
                if (A002 != null) {
                    i2 = A002.A0D();
                } else {
                    i2 = 0;
                }
                builder2 = new Notification.Action.Builder(i2, next.A02, next.A01);
            }
            AnonymousClass045[] r0 = next.A09;
            if (r0 != null) {
                for (RemoteInput remoteInput : AnonymousClass045.A00(r0)) {
                    builder2.addRemoteInput(remoteInput);
                }
            }
            Bundle bundle3 = next.A07;
            if (bundle3 != null) {
                bundle2 = new Bundle(bundle3);
            } else {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android.support.allowGeneratedReplies", next.A03);
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 24) {
                builder2.setAllowGeneratedReplies(next.A03);
            }
            int i5 = next.A06;
            bundle2.putInt("android.support.action.semanticAction", i5);
            if (i4 >= 28) {
                builder2.setSemanticAction(i5);
            }
            if (i4 >= 29) {
                builder2.setContextual(next.A08);
            }
            bundle2.putBoolean("android.support.action.showsUserInterface", next.A04);
            builder2.addExtras(bundle2);
            this.A00.addAction(builder2.build());
        }
        Bundle bundle4 = r23.A0C;
        if (bundle4 != null) {
            this.A01.putAll(bundle4);
        }
        this.A00.setShowWhen(r23.A0H);
        this.A00.setLocalOnly(false).setGroup(null).setGroupSummary(false).setSortKey(null);
        this.A00.setCategory(null).setColor(r23.A05).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        Iterator<String> it2 = r23.A0G.iterator();
        while (it2.hasNext()) {
            this.A00.addPerson(it2.next());
        }
        if (r23.A03.size() > 0) {
            Bundle bundle5 = r23.A0C;
            if (bundle5 == null) {
                bundle5 = new Bundle();
                r23.A0C = bundle5;
            }
            Bundle bundle6 = bundle5.getBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER);
            bundle6 = bundle6 == null ? new Bundle() : bundle6;
            Bundle bundle7 = new Bundle();
            int i6 = 0;
            while (true) {
                ArrayList<AnonymousClass03d> arrayList = r23.A03;
                if (i6 >= arrayList.size()) {
                    break;
                }
                String num = Integer.toString(i6);
                AnonymousClass03d r8 = arrayList.get(i6);
                Bundle bundle8 = new Bundle();
                IconCompat A003 = r8.A00();
                if (A003 != null) {
                    i = A003.A0D();
                } else {
                    i = 0;
                }
                bundle8.putInt("icon", i);
                bundle8.putCharSequence("title", r8.A02);
                bundle8.putParcelable("actionIntent", r8.A01);
                Bundle bundle9 = r8.A07;
                if (bundle9 != null) {
                    bundle = new Bundle(bundle9);
                } else {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android.support.allowGeneratedReplies", r8.A03);
                bundle8.putBundle("extras", bundle);
                AnonymousClass045[] r5 = r8.A09;
                if (r5 == null) {
                    bundleArr = null;
                } else {
                    int length = r5.length;
                    bundleArr = new Bundle[length];
                    for (int i7 = 0; i7 < length; i7++) {
                        AnonymousClass045 r2 = r5[i7];
                        Bundle bundle10 = new Bundle();
                        bundle10.putString("resultKey", r2.A03);
                        bundle10.putCharSequence("label", r2.A02);
                        bundle10.putCharSequenceArray("choices", r2.A05);
                        bundle10.putBoolean("allowFreeFormInput", r2.A04);
                        bundle10.putBundle("extras", r2.A01);
                        bundleArr[i7] = bundle10;
                    }
                }
                bundle8.putParcelableArray("remoteInputs", bundleArr);
                bundle8.putBoolean("showsUserInterface", r8.A04);
                bundle8.putInt("semanticAction", r8.A06);
                bundle7.putBundle(num, bundle8);
                i6++;
            }
            bundle6.putBundle(NotificationCompat$CarExtender.EXTRA_INVISIBLE_ACTIONS, bundle7);
            Bundle bundle11 = r23.A0C;
            if (bundle11 == null) {
                bundle11 = new Bundle();
                r23.A0C = bundle11;
            }
            bundle11.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
            this.A01.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.A00.setExtras(r23.A0C).setRemoteInputHistory(null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.A00.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(r23.A02)) {
                this.A00.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00.setAllowSystemGeneratedContextualActions(r23.A04);
            this.A00.setBubbleMetadata(AnonymousClass03g.A00());
        }
    }

    public final Notification A00() {
        RemoteViews remoteViews;
        Notification.Builder builder;
        AnonymousClass03h r4 = this.A02;
        AnonymousClass03p r3 = r4.A01;
        if (r3 != null) {
            r3.apply(this);
            remoteViews = r3.makeContentView(this);
        } else {
            remoteViews = null;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 || i >= 24) {
            builder = this.A00;
        } else {
            builder = this.A00;
            builder.setExtras(this.A01);
        }
        Notification build = builder.build();
        if (remoteViews != null) {
            build.contentView = remoteViews;
        }
        if (r3 != null) {
            RemoteViews makeBigContentView = r3.makeBigContentView(this);
            if (makeBigContentView != null) {
                build.bigContentView = makeBigContentView;
            }
            RemoteViews makeHeadsUpContentView = r4.A01.makeHeadsUpContentView(this);
            if (makeHeadsUpContentView != null) {
                build.headsUpContentView = makeHeadsUpContentView;
            }
            Bundle bundle = build.extras;
            if (bundle != null) {
                r3.addCompatExtras(bundle);
            }
        }
        return build;
    }

    @Override // X.AnonymousClass03Y
    public final Notification.Builder A3C() {
        return this.A00;
    }
}
