package X;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.crudolib.prefs.LightSharedPreferences;
import com.facebook.msys.mca.Mailbox;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1YS  reason: invalid class name */
public class AnonymousClass1YS implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06251Yg A00;

    public AnonymousClass1YS(C06251Yg r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1YZ
    public final void onCompletion(Mailbox mailbox) {
        AtomicBoolean atomicBoolean;
        HashMap hashMap;
        int i;
        Mailbox mailbox2 = mailbox;
        C06251Yg r2 = this.A00;
        Context context = r2.A03.A01.A00;
        C03850of A002 = AnonymousClass1RO.A00(context);
        C03850of.A00(A002);
        AnonymousClass0p4 r3 = new AnonymousClass0p4(A002);
        if (!r3.A02) {
            Map<String, Object> map = r3.A00;
            map.put("mailbox_has_init", true);
            C03850of r7 = r3.A03;
            int i2 = r7.A01;
            if (i2 != 0 && Looper.myLooper() == Looper.getMainLooper()) {
                if (i2 == 1) {
                    AnonymousClass0MD.A06("LightSharedPreferencesImpl", "commit is called on the main thread.");
                } else {
                    throw new IllegalStateException("commit is called on the main thread.");
                }
            }
            synchronized (r3) {
                if (!r3.A02) {
                    r3.A02 = true;
                } else {
                    throw new RuntimeException("Trying to freeze an editor that is already frozen!");
                }
            }
            try {
                AnonymousClass0He<String> r9 = new AnonymousClass0He();
                Object obj = r7.A03;
                synchronized (obj) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value == C03850of.A0B) {
                            r7.A05.remove(key);
                        } else if (value != null) {
                            Map<String, Object> map2 = r7.A05;
                            if (!value.equals(map2.get(key))) {
                                map2.put(key, value);
                            }
                        } else {
                            throw null;
                        }
                        r9.add(key);
                    }
                    atomicBoolean = r7.A08;
                    boolean z = false;
                    if (!r9.isEmpty()) {
                        z = true;
                    }
                    atomicBoolean.compareAndSet(false, z);
                }
                map.clear();
                if (!r9.isEmpty()) {
                    synchronized (r7) {
                        Throwable th = new Throwable("commit stack");
                        for (String str : r9) {
                            Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> map3 = r7.A06.get(str);
                            if (map3 != null) {
                                for (Map.Entry<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> entry2 : map3.entrySet()) {
                                    entry2.getKey();
                                    entry2.getValue().post(new AnonymousClass0Lm(r7, th));
                                }
                            }
                        }
                        r7.A00 = null;
                    }
                    if (atomicBoolean.get()) {
                        synchronized (obj) {
                            atomicBoolean.set(false);
                            hashMap = new HashMap(r7.A05);
                        }
                        try {
                            AnonymousClass0Lo r92 = r7.A02;
                            File file = r92.A00;
                            File createTempFile = File.createTempFile(AnonymousClass006.A07(file.getName(), "."), ".tmp", file.getParentFile());
                            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile), 512));
                            try {
                                dataOutputStream.writeByte(1);
                                dataOutputStream.writeInt(hashMap.size());
                                for (Map.Entry entry3 : hashMap.entrySet()) {
                                    String str2 = (String) entry3.getKey();
                                    Object value2 = entry3.getValue();
                                    if (value2 instanceof Boolean) {
                                        i = 0;
                                    } else if (value2 instanceof Integer) {
                                        i = 1;
                                    } else if (value2 instanceof Long) {
                                        i = 2;
                                    } else if (value2 instanceof Float) {
                                        i = 3;
                                    } else if (value2 instanceof Double) {
                                        i = 4;
                                    } else if (value2 instanceof String) {
                                        i = 5;
                                    } else if (value2 instanceof Set) {
                                        i = 6;
                                    } else {
                                        StringBuilder sb = new StringBuilder("Unsupported type: ");
                                        sb.append(value2.getClass());
                                        throw new IllegalArgumentException(sb.toString());
                                    }
                                    dataOutputStream.write(i);
                                    dataOutputStream.writeUTF(str2);
                                    switch (i) {
                                        case 0:
                                            dataOutputStream.writeBoolean(((Boolean) value2).booleanValue());
                                            break;
                                        case 1:
                                            dataOutputStream.writeInt(((Integer) value2).intValue());
                                            break;
                                        case 2:
                                            dataOutputStream.writeLong(((Long) value2).longValue());
                                            break;
                                        case 3:
                                            dataOutputStream.writeFloat(((Float) value2).floatValue());
                                            break;
                                        case 4:
                                            dataOutputStream.writeDouble(((Double) value2).doubleValue());
                                            break;
                                        case 5:
                                            dataOutputStream.writeUTF((String) value2);
                                            break;
                                        case 6:
                                            Set<String> set = (Set) value2;
                                            dataOutputStream.writeInt(set.size());
                                            for (String str3 : set) {
                                                dataOutputStream.writeUTF(str3);
                                            }
                                            break;
                                        default:
                                            throw new IllegalArgumentException(AnonymousClass006.A03("Unsupported type with ordinal: ", i));
                                    }
                                }
                                dataOutputStream.close();
                                synchronized (r92.A01) {
                                    if (!createTempFile.renameTo(file)) {
                                        createTempFile.delete();
                                        throw new IOException("Failed to replace the current preference file!");
                                    }
                                }
                            } catch (Throwable th2) {
                                dataOutputStream.close();
                                throw th2;
                            }
                        } catch (IOException e) {
                            AnonymousClass0MD.A08("LightSharedPreferencesImpl", "Commit to disk failed.", e);
                        }
                    }
                }
                AnonymousClass0p4.A00(r3);
                LinkedList linkedList = new LinkedList();
                synchronized (r2) {
                    if (mailbox2 != null) {
                        r2.A02 = mailbox2;
                        Set<AnonymousClass1YZ<Mailbox>> set2 = r2.A04;
                        linkedList.addAll(set2);
                        set2.clear();
                        if (r2.A01 == null) {
                            Context context2 = new AnonymousClass1YV(context).A00;
                            if ("com_facebook_msys_config_plugins_interfaces_flipper_FlipperPluginInterfaceSpec".hashCode() != -577314835 || !"com_facebook_msys_config_plugins_interfaces_flipper_FlipperPluginInterfaceSpec".equals("com_facebook_msys_config_plugins_interfaces_flipper_FlipperPluginInterfaceSpec")) {
                                throw new RuntimeException(AnonymousClass006.A07("com_facebook_msys_config_plugins_interfaces_flipper_FlipperPluginInterfaceSpec", " is not a known interface name"));
                            }
                            r2.A01 = new AnonymousClass1YU(new AnonymousClass1YT(context2));
                        }
                        AnonymousClass1YY.A00.getAndIncrement();
                    } else {
                        throw null;
                    }
                }
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    ((AnonymousClass1YZ) it.next()).onCompletion(mailbox2);
                }
                r2.A05 = false;
            } catch (Throwable th3) {
                AnonymousClass0p4.A00(r3);
                throw th3;
            }
        } else {
            throw new ConcurrentModificationException("Editors shouldn't be modified during commit!");
        }
    }
}
