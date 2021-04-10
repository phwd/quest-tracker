package defpackage;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: Fn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0340Fn {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0711Lp0 f8040a;
    public Resources b;

    public C0340Fn(AbstractC0711Lp0 lp0, AbstractC0279En en, Resources resources) {
        this.f8040a = lp0;
        this.b = resources;
    }

    public void a() {
        Iterator it = new ArrayList(Arrays.asList(C2763gr.f10027a)).iterator();
        while (it.hasNext()) {
            ((C0771Mp0) this.f8040a).b.deleteNotificationChannel((String) it.next());
        }
    }

    public final void b(Collection collection, Collection collection2, boolean z) {
        C0157Cn cn;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            C0218Dn dn = (C0218Dn) AbstractC2421er.f9885a.get((String) it.next());
            if (dn != null) {
                NotificationChannelGroup a2 = dn.a(this.b);
                hashMap.put(a2.getId(), a2);
            }
        }
        Iterator it2 = collection2.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            if (MX0.e(str) || TextUtils.equals(str, "default_channel_id")) {
                cn = null;
            } else {
                cn = (C0157Cn) AbstractC2592fr.f9960a.get(str);
                if (cn == null) {
                    throw new IllegalStateException(AbstractC2531fV.f("Could not initialize channel: ", str));
                }
            }
            if (cn != null) {
                NotificationChannelGroup a3 = ((C0218Dn) AbstractC2421er.f9885a.get(cn.d)).a(this.b);
                NotificationChannel notificationChannel = new NotificationChannel(cn.f7839a, this.b.getString(cn.b), cn.c);
                notificationChannel.setGroup(cn.d);
                notificationChannel.setShowBadge(cn.e);
                if (cn.f) {
                    notificationChannel.setSound(null, new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
                }
                if (!z) {
                    notificationChannel.setImportance(0);
                }
                hashMap.put(a3.getId(), a3);
                hashMap2.put(notificationChannel.getId(), notificationChannel);
            }
        }
        Collection<NotificationChannelGroup> values = hashMap.values();
        AbstractC0711Lp0 lp0 = this.f8040a;
        lp0.getClass();
        for (NotificationChannelGroup notificationChannelGroup : values) {
            ((C0771Mp0) lp0).b.createNotificationChannelGroup(notificationChannelGroup);
        }
        Collection<NotificationChannel> values2 = hashMap2.values();
        AbstractC0711Lp0 lp02 = this.f8040a;
        lp02.getClass();
        for (NotificationChannel notificationChannel2 : values2) {
            ((C0771Mp0) lp02).b.createNotificationChannel(notificationChannel2);
        }
    }

    public void c() {
        HashSet hashSet = new HashSet();
        for (String str : AbstractC2592fr.b) {
            hashSet.add(((C0157Cn) AbstractC2592fr.f9960a.get(str)).d);
        }
        b(hashSet, AbstractC2592fr.b, true);
    }
}
