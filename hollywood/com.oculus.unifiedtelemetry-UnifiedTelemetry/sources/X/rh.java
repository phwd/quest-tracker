package X;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.logging.funnel.FunnelLoggerManager;
import com.oculus.logging.funnel.OculusFunnelDefinition;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class rh implements AbstractC0479qV {
    public Map<String, C0490s6> A00 = new HashMap();
    public final C0516sv A01;
    public final HandlerC0483rj A02;
    public final Random A03;
    public final FunnelLoggerManager.AlwaysOnSampler A04;
    public final FunnelLoggerManager.OculusFunnelEventSender A05;
    public volatile boolean A06 = false;

    @Override // X.AbstractC0479qV
    public final synchronized void A1E(AbstractC0481qo qoVar, long j, String str, @Nullable String str2, @Nullable C0477qK qKVar) {
        long currentTimeMillis = System.currentTimeMillis();
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A02 = Long.valueOf(j);
        sWVar.A04 = str;
        sWVar.A05 = str2;
        sWVar.A01 = qKVar;
        sWVar.A03 = Long.valueOf(currentTimeMillis);
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(3, sKVar));
    }

    @Override // X.AbstractC0479qV
    public final synchronized void A1F(AbstractC0481qo qoVar, String str, @Nullable String str2, @Nullable C0477qK qKVar) {
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A04 = str;
        sWVar.A05 = str2;
        sWVar.A01 = qKVar;
        sWVar.A03 = Long.valueOf(System.currentTimeMillis());
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(3, sKVar));
    }

    @Override // X.AbstractC0479qV
    public final synchronized void A1w(AbstractC0481qo qoVar) {
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A03 = Long.valueOf(System.currentTimeMillis());
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(2, sKVar));
    }

    @Override // X.AbstractC0479qV
    public final synchronized void A1x(AbstractC0481qo qoVar, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A02 = Long.valueOf(j);
        sWVar.A03 = Long.valueOf(currentTimeMillis);
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(2, sKVar));
    }

    @Override // X.AbstractC0479qV
    public final synchronized void A5M(AbstractC0481qo qoVar) {
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A03 = Long.valueOf(System.currentTimeMillis());
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(1, sKVar));
    }

    @Override // X.AbstractC0479qV
    public final synchronized void A5N(AbstractC0481qo qoVar, long j) {
        A01(qoVar);
        C0503sW sWVar = new C0503sW();
        sWVar.A00 = qoVar;
        sWVar.A02 = Long.valueOf(j);
        sWVar.A03 = Long.valueOf(System.currentTimeMillis());
        C0494sK sKVar = new C0494sK(sWVar);
        HandlerC0483rj rjVar = this.A02;
        rjVar.sendMessage(rjVar.obtainMessage(1, sKVar));
    }

    public static String A00(C0494sK sKVar) {
        Long l = sKVar.A02;
        if (l == null) {
            return sKVar.A00.name;
        }
        AbstractC0481qo qoVar = sKVar.A00;
        long longValue = l.longValue();
        StringBuilder sb = new StringBuilder();
        sb.append(qoVar.name);
        sb.append(":");
        sb.append(longValue);
        return sb.toString();
    }

    public static void A01(AbstractC0481qo qoVar) {
        if (qoVar == null) {
            throw new IllegalArgumentException("FunnelDefinition is null, expecting non-null value");
        }
    }

    public rh(FunnelLoggerManager.OculusFunnelEventSender oculusFunnelEventSender, FunnelLoggerManager.AlwaysOnSampler alwaysOnSampler, C0521tH tHVar, C0516sv svVar) {
        this.A05 = oculusFunnelEventSender;
        this.A04 = alwaysOnSampler;
        this.A03 = new Random();
        Looper looper = tHVar.A00;
        if (looper == null) {
            HandlerThread handlerThread = new HandlerThread("funnellogger-worker", 10);
            handlerThread.start();
            looper = handlerThread.getLooper();
            tHVar.A00 = looper;
        }
        this.A02 = new HandlerC0483rj(this, looper);
        this.A01 = svVar;
    }

    public static void A03(rh rhVar, C0490s6 s6Var, EnumC0500sT sTVar, long j) {
        Map<String, Object> map;
        String obj;
        long j2 = s6Var.A04;
        s6Var.A01.add(new C0510sh("funnel_end", (int) (j - j2), sTVar.tag(), (C0477qK) null));
        s6Var.A00 = j;
        OculusLoggingEvent A3S = rhVar.A05.mEventFactory.A3S("funnel_analytics");
        AbstractC0481qo qoVar = s6Var.A06;
        A3S.A15("name", qoVar.name);
        A3S.A13("funnel_id", qoVar.id);
        A3S.A14("instance_id", s6Var.A05);
        A3S.A13("sampling_rate", s6Var.A03);
        A3S.A14("start_time", j2);
        if (A3S.A2R() != null) {
            YE A2R = A3S.A2R();
            if (A2R != null) {
                YF A012 = ((MF) A2R).A01.A01();
                A2R.A0C(NotificationCompat$WearableExtender.KEY_ACTIONS, A012);
                for (C0510sh shVar : s6Var.A01) {
                    YE A022 = A012.A01.A02();
                    YF.A00(A012, A022);
                    YE.A00(A022, "name", shVar.A02);
                    YE.A00(A022, "relative_time", Integer.valueOf(shVar.A00));
                    YE.A00(A022, "tag", shVar.A04);
                    C0477qK qKVar = shVar.A01;
                    if (qKVar != null) {
                        synchronized (qKVar) {
                            map = qKVar.A00;
                        }
                        YE A023 = ((MF) A022).A01.A02();
                        A022.A0C("payload", A023);
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            Object value = entry.getValue();
                            String key = entry.getKey();
                            if (value == null) {
                                obj = "null";
                            } else {
                                obj = value.toString();
                            }
                            YE.A00(A023, key, obj);
                        }
                    }
                }
                if (s6Var.A02 != null) {
                    YF A013 = ((MF) A2R).A01.A01();
                    A2R.A0C("tags", A013);
                    for (String str : s6Var.A02) {
                        YF.A01(A013, str);
                    }
                }
            }
        } else {
            try {
                JSONArray jSONArray = new JSONArray();
                for (C0510sh shVar2 : s6Var.A01) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", shVar2.A02);
                    jSONObject.put("relative_time", shVar2.A00);
                    jSONObject.put("tag", shVar2.A04);
                    jSONArray.put(jSONObject);
                }
                A3S.A15(NotificationCompat$WearableExtender.KEY_ACTIONS, jSONArray.toString());
            } catch (JSONException e) {
                Mu.A02(FunnelLoggerManager.OculusFunnelEventSender.TAG, "Exception while appending actions", e);
            }
            List<String> list = s6Var.A02;
            if (list != null) {
                A3S.A15("tags", new JSONArray((Collection) list).toString());
            }
        }
        A3S.A3Q();
    }

    public static void A02(rh rhVar) {
        HashMap hashMap;
        if (!rhVar.A06) {
            try {
                C0516sv svVar = rhVar.A01;
                synchronized (svVar) {
                    hashMap = new HashMap();
                    File file = svVar.A00;
                    if (file.exists()) {
                        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file), 1024));
                        try {
                            byte readByte = dataInputStream.readByte();
                            if (readByte != 1) {
                                Mu.A05("FunnelBackupStorageFileImpl", "Expected version %d, found version %d", (byte) 1, Byte.valueOf(readByte));
                            } else {
                                int readInt = dataInputStream.readInt();
                                for (int i = 0; i < readInt; i++) {
                                    String readUTF = dataInputStream.readUTF();
                                    C0498sQ sQVar = new C0498sQ();
                                    while (true) {
                                        short readShort = dataInputStream.readShort();
                                        switch (readShort) {
                                            case 1:
                                                sQVar.A04 = OculusFunnelDefinition.sFunnelDefinitions.get(dataInputStream.readUTF());
                                            case 2:
                                                sQVar.A02 = dataInputStream.readLong();
                                            case 3:
                                                sQVar.A00 = dataInputStream.readInt();
                                            case 4:
                                                sQVar.A01 = dataInputStream.readLong();
                                            case 5:
                                                sQVar.A03 = dataInputStream.readLong();
                                            case 6:
                                                int readInt2 = dataInputStream.readInt();
                                                ArrayList arrayList = new ArrayList(readInt2);
                                                for (int i2 = 0; i2 < readInt2; i2++) {
                                                    arrayList.add(dataInputStream.readUTF());
                                                }
                                                sQVar.A06 = arrayList;
                                            case 7:
                                                int readInt3 = dataInputStream.readInt();
                                                ArrayList arrayList2 = new ArrayList(readInt3);
                                                for (int i3 = 0; i3 < readInt3; i3++) {
                                                    String str = null;
                                                    String str2 = null;
                                                    String str3 = null;
                                                    int i4 = 0;
                                                    while (true) {
                                                        short readShort2 = dataInputStream.readShort();
                                                        switch (readShort2) {
                                                            case 701:
                                                                str = dataInputStream.readUTF();
                                                            case 702:
                                                                i4 = dataInputStream.readInt();
                                                            case 703:
                                                                str2 = dataInputStream.readUTF();
                                                            case 704:
                                                                str3 = dataInputStream.readUTF();
                                                            case 705:
                                                                arrayList2.add(new C0510sh(str, i4, str2, str3));
                                                            default:
                                                                throw new IllegalStateException(AnonymousClass06.A02("Unsupported type ", readShort2, " while loading funnels"));
                                                        }
                                                    }
                                                }
                                                sQVar.A05 = arrayList2;
                                            case 8:
                                            default:
                                                throw new IllegalStateException(AnonymousClass06.A02("Unsupported type ", readShort, " while loading funnels"));
                                            case 9:
                                                C0490s6 s6Var = new C0490s6(sQVar);
                                                if (s6Var.A06 != null) {
                                                    hashMap.put(readUTF, s6Var);
                                                } else {
                                                    Mu.A07("FunnelBackupStorageFileImpl", "Parsed funnel instance with null FunnelDefinition for key: %s", readUTF);
                                                }
                                        }
                                    }
                                }
                                dataInputStream.close();
                                hashMap.size();
                            }
                        } finally {
                            dataInputStream.close();
                        }
                    }
                }
                if (!hashMap.isEmpty()) {
                    rhVar.A00 = hashMap;
                }
            } catch (IOException e) {
                Mu.A0A("FunnelLoggerImpl", e, "Failed to load funnels");
            } catch (Throwable th) {
                rhVar.A06 = true;
                throw th;
            }
            rhVar.A06 = true;
        }
    }
}
