package com.oculus.unifiedtelemetry.collectors;

import X.AnonymousClass06;
import X.Mu;
import android.os.PersistableBundle;
import android.os.SystemClock;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class Event {
    public static final String TAG = "Event";
    public final PersistableBundle mContent = new PersistableBundle();
    public final String mName;

    @Nullable
    public static Event A00(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Event event = new Event(jSONObject.getString("event_name"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("event_content");
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (obj instanceof String) {
                    event.A06(next, (String) obj);
                } else if (obj instanceof Integer) {
                    event.A02(next, ((Number) obj).intValue());
                } else if (obj instanceof Double) {
                    event.mContent.putDouble(next, ((Number) obj).doubleValue());
                } else if (obj instanceof Long) {
                    event.A03(next, ((Number) obj).longValue());
                } else if (obj instanceof Boolean) {
                    event.mContent.putBoolean(next, ((Boolean) obj).booleanValue());
                } else {
                    Mu.A05(TAG, "could not set key=%s value=%s", next, String.valueOf(obj));
                }
            }
            return event;
        } catch (JSONException e) {
            Mu.A02(TAG, "could not create serialized event", e);
            return null;
        }
    }

    @Nullable
    public final String A01() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_name", this.mName);
            JSONObject jSONObject2 = new JSONObject();
            for (String str : this.mContent.keySet()) {
                jSONObject2.put(str, this.mContent.get(str));
            }
            jSONObject.put("event_content", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            Mu.A02(TAG, "could not serialize event", e);
            return null;
        }
    }

    public final void A02(String str, int i) {
        this.mContent.putInt(str, i);
    }

    public final void A03(String str, long j) {
        this.mContent.putLong(str, j);
    }

    public final void A04(String str, SampledMetric sampledMetric) {
        this.mContent.putInt(AnonymousClass06.A04(str, "_min"), sampledMetric.mMin);
        this.mContent.putInt(AnonymousClass06.A04(str, "_max"), sampledMetric.mMax);
        this.mContent.putInt(AnonymousClass06.A04(str, "_total"), sampledMetric.mTotal);
        this.mContent.putInt(AnonymousClass06.A04(str, "_total_square"), sampledMetric.mTotalSquare);
    }

    public final void A05(String str, SampledString sampledString) {
        LinkedList linkedList = new LinkedList();
        PriorityQueue priorityQueue = new PriorityQueue(11, new Comparator<Map.Entry<String, Integer>>() {
            /* class com.oculus.unifiedtelemetry.collectors.SampledString.AnonymousClass1 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            @Override // java.util.Comparator
            public final int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                Map.Entry<String, Integer> entry3 = entry;
                Map.Entry<String, Integer> entry4 = entry2;
                if (entry3.getValue().equals(entry4.getValue())) {
                    return entry4.getKey().compareTo(entry3.getKey());
                }
                return entry3.getValue().intValue() - entry4.getValue().intValue();
            }
        });
        for (Map.Entry<String, Integer> entry : sampledString.mStringFrequency.entrySet()) {
            priorityQueue.offer(entry);
            if (priorityQueue.size() > sampledString.mK) {
                priorityQueue.poll();
            }
        }
        while (!priorityQueue.isEmpty()) {
            linkedList.add(0, ((Map.Entry) priorityQueue.poll()).getKey());
        }
        String[] strArr = (String[]) linkedList.toArray(new String[0]);
        for (int i = 0; i < sampledString.mK; i++) {
            try {
                PersistableBundle persistableBundle = this.mContent;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("_");
                sb.append(i + 1);
                persistableBundle.putString(sb.toString(), strArr[i]);
            } catch (ArrayIndexOutOfBoundsException unused) {
                PersistableBundle persistableBundle2 = this.mContent;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("_");
                sb2.append(i + 1);
                persistableBundle2.putString(sb2.toString(), "");
            }
        }
    }

    public final void A06(String str, String str2) {
        this.mContent.putString(str, str2);
    }

    public Event(String str) {
        this.mName = str;
        A03("realtime_ms", SystemClock.elapsedRealtime());
        A03("uptime_ms", SystemClock.uptimeMillis());
    }
}
