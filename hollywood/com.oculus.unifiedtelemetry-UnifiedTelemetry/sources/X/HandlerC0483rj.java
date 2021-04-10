package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.rj  reason: case insensitive filesystem */
public class HandlerC0483rj extends Handler {
    public final /* synthetic */ rh A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC0483rj(rh rhVar, Looper looper) {
        super(looper);
        this.A00 = rhVar;
    }

    public final void handleMessage(Message message) {
        long j;
        long j2;
        AbstractC0481qo qoVar;
        rh rhVar;
        String str;
        Throwable th;
        String str2;
        long currentTimeMillis;
        EnumC0500sT sTVar;
        int i = message.what;
        switch (i) {
            case 1:
                C0494sK sKVar = (C0494sK) message.obj;
                rhVar = this.A00;
                str = rh.A00(sKVar);
                qoVar = sKVar.A00;
                Long l = sKVar.A02;
                if (l == null) {
                    j2 = rhVar.A03.nextLong();
                } else {
                    j2 = l.longValue();
                }
                j = sKVar.A03.longValue();
                rh.A02(rhVar);
                C0490s6 s6Var = rhVar.A00.get(str);
                if (s6Var != null) {
                    rh.A03(rhVar, s6Var, EnumC0500sT.RESTART, j);
                    rhVar.A00.remove(str);
                    break;
                }
                break;
            case 2:
                C0494sK sKVar2 = (C0494sK) message.obj;
                rh rhVar2 = this.A00;
                String A002 = rh.A00(sKVar2);
                long longValue = sKVar2.A03.longValue();
                rh.A02(rhVar2);
                C0490s6 s6Var2 = rhVar2.A00.get(A002);
                if (s6Var2 != null) {
                    rhVar2.A00.remove(A002);
                    rh.A03(rhVar2, s6Var2, EnumC0500sT.EXPLICIT, longValue);
                    return;
                }
                return;
            case 3:
                C0494sK sKVar3 = (C0494sK) message.obj;
                rh rhVar3 = this.A00;
                String A003 = rh.A00(sKVar3);
                String str3 = sKVar3.A04;
                String str4 = sKVar3.A05;
                C0477qK qKVar = sKVar3.A01;
                long longValue2 = sKVar3.A03.longValue();
                rh.A02(rhVar3);
                C0490s6 s6Var3 = rhVar3.A00.get(A003);
                if (s6Var3 != null) {
                    s6Var3.A01.add(new C0510sh(str3, (int) (longValue2 - s6Var3.A04), str4, qKVar));
                    s6Var3.A00 = longValue2;
                    if (s6Var3.A01.size() >= 100) {
                        rh.A03(rhVar3, s6Var3, EnumC0500sT.ACTIONS_FULL, System.currentTimeMillis());
                        rhVar3.A00.remove(A003);
                        return;
                    }
                    return;
                }
                return;
            case 4:
                rh rhVar4 = this.A00;
                String A004 = rh.A00((C0494sK) message.obj);
                rh.A02(rhVar4);
                rhVar4.A00.get(A004);
                return;
            case 5:
                rh rhVar5 = this.A00;
                String A005 = rh.A00((C0494sK) message.obj);
                rh.A02(rhVar5);
                rhVar5.A00.remove(A005);
                return;
            case 6:
                rh rhVar6 = this.A00;
                rh.A02(rhVar6);
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<String, C0490s6> entry : rhVar6.A00.entrySet()) {
                    C0490s6 value = entry.getValue();
                    AbstractC0481qo qoVar2 = value.A06;
                    if (qoVar2.mShouldEndOnUserLeavingTheApp) {
                        sTVar = EnumC0500sT.SESSION_END;
                        currentTimeMillis = System.currentTimeMillis();
                    } else {
                        currentTimeMillis = System.currentTimeMillis();
                        long j3 = value.A00;
                        if (currentTimeMillis - j3 > ((long) Math.min(qoVar2.mSecondsToEndSinceLastUpdate, (int) AbstractC0481qo.MAX_TTL_SECONDS)) * 1000) {
                            sTVar = EnumC0500sT.TIMEOUT;
                        } else if (qoVar2.mIsPseudoEndEnabled) {
                            C0498sQ sQVar = new C0498sQ();
                            sQVar.A04 = qoVar2;
                            sQVar.A02 = value.A05;
                            sQVar.A00 = value.A03;
                            sQVar.A01 = value.A04;
                            sQVar.A03 = j3;
                            List<String> list = value.A02;
                            if (list != null) {
                                sQVar.A06 = new ArrayList(list);
                            }
                            List<C0510sh> list2 = value.A01;
                            if (list2 != null) {
                                ArrayList arrayList2 = new ArrayList();
                                for (C0510sh shVar : list2) {
                                    arrayList2.add(new C0510sh(shVar.A02, shVar.A00, shVar.A04, shVar.A01));
                                }
                                sQVar.A05 = arrayList2;
                            }
                            rh.A03(rhVar6, new C0490s6(sQVar), EnumC0500sT.SESSION_END, System.currentTimeMillis());
                        }
                    }
                    rh.A03(rhVar6, value, sTVar, currentTimeMillis);
                    arrayList.add(entry.getKey());
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    rhVar6.A00.remove(it.next());
                }
                try {
                    C0516sv svVar = rhVar6.A01;
                    Map<String, C0490s6> map = rhVar6.A00;
                    if (map.isEmpty()) {
                        svVar.A00.delete();
                        return;
                    }
                    File file = svVar.A00;
                    File createTempFile = File.createTempFile(AnonymousClass06.A04(file.getName(), "."), ".tmp", file.getParentFile());
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile), 1024));
                    try {
                        dataOutputStream.writeByte(1);
                        dataOutputStream.writeInt(map.size());
                        for (Map.Entry<String, C0490s6> entry2 : map.entrySet()) {
                            dataOutputStream.writeUTF(entry2.getKey());
                            C0490s6 value2 = entry2.getValue();
                            dataOutputStream.writeShort(1);
                            dataOutputStream.writeUTF(value2.A06.name);
                            dataOutputStream.writeShort(2);
                            dataOutputStream.writeLong(value2.A05);
                            dataOutputStream.writeShort(3);
                            dataOutputStream.writeInt(value2.A03);
                            dataOutputStream.writeShort(4);
                            dataOutputStream.writeLong(value2.A04);
                            dataOutputStream.writeShort(5);
                            dataOutputStream.writeLong(value2.A00);
                            List<String> list3 = value2.A02;
                            if (list3 != null) {
                                dataOutputStream.writeShort(6);
                                dataOutputStream.writeInt(list3.size());
                                for (String str5 : list3) {
                                    dataOutputStream.writeUTF(str5);
                                }
                            }
                            List<C0510sh> list4 = value2.A01;
                            if (list4 != null) {
                                dataOutputStream.writeShort(7);
                                dataOutputStream.writeInt(list4.size());
                                for (C0510sh shVar2 : list4) {
                                    dataOutputStream.writeShort(701);
                                    dataOutputStream.writeUTF(shVar2.A02);
                                    dataOutputStream.writeShort(702);
                                    dataOutputStream.writeInt(shVar2.A00);
                                    String str6 = shVar2.A04;
                                    if (str6 != null) {
                                        dataOutputStream.writeShort(703);
                                        dataOutputStream.writeUTF(str6);
                                    }
                                    C0477qK qKVar2 = shVar2.A01;
                                    if (qKVar2 != null) {
                                        dataOutputStream.writeShort(704);
                                        synchronized (qKVar2) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append('{');
                                            boolean z = true;
                                            for (Map.Entry<String, Object> entry3 : qKVar2.A00.entrySet()) {
                                                if (!z) {
                                                    sb.append(',');
                                                }
                                                sb.append('\"');
                                                sb.append(entry3.getKey());
                                                sb.append('\"');
                                                sb.append(':');
                                                if (entry3.getValue() instanceof String) {
                                                    sb.append('\"');
                                                    sb.append((String) entry3.getValue());
                                                    sb.append('\"');
                                                } else {
                                                    sb.append(entry3.getValue());
                                                }
                                                z = false;
                                            }
                                            sb.append('}');
                                            str2 = sb.toString();
                                        }
                                    } else {
                                        str2 = shVar2.A03;
                                        if (str2 != null) {
                                            dataOutputStream.writeShort(704);
                                        } else {
                                            dataOutputStream.writeShort(705);
                                        }
                                    }
                                    dataOutputStream.writeUTF(str2);
                                    dataOutputStream.writeShort(705);
                                }
                                continue;
                            }
                            dataOutputStream.writeShort(9);
                        }
                        dataOutputStream.close();
                        synchronized (svVar) {
                            try {
                                if (!createTempFile.renameTo(file)) {
                                    createTempFile.delete();
                                    throw new IOException("Failed to replace the current preference file!");
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        map.size();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream.close();
                        throw th;
                    }
                } catch (IOException e) {
                    Mu.A0A("FunnelLoggerImpl", e, "Failed to save funnels!");
                    return;
                }
            case 7:
                C0494sK sKVar4 = (C0494sK) message.obj;
                rhVar = this.A00;
                str = rh.A00(sKVar4);
                qoVar = sKVar4.A00;
                Long l2 = sKVar4.A02;
                if (l2 == null) {
                    j2 = rhVar.A03.nextLong();
                } else {
                    j2 = l2.longValue();
                }
                j = sKVar4.A03.longValue();
                if (qoVar.mShouldCreateNoopInstances) {
                    rh.A02(rhVar);
                    if (rhVar.A00.containsKey(str)) {
                        return;
                    }
                } else {
                    throw new IllegalStateException("Must enable noop funnels in the FunnelDefinition to use startFunnelIfNotStarted()");
                }
                break;
            case 8:
                C0494sK sKVar5 = (C0494sK) message.obj;
                rh rhVar7 = this.A00;
                String A006 = rh.A00(sKVar5);
                long longValue3 = sKVar5.A03.longValue();
                rh.A02(rhVar7);
                C0490s6 s6Var4 = rhVar7.A00.get(A006);
                if (s6Var4 != null) {
                    s6Var4.A00 = longValue3;
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException(AnonymousClass06.A01("Unknown what=", i));
        }
        rhVar.A00.put(str, new C0490s6(qoVar, j2, j));
    }
}
