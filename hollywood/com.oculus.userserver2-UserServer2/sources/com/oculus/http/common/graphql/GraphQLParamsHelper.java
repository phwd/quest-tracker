package com.oculus.http.common.graphql;

import X.C0246hr;
import android.os.StrictMode;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GraphQLParamsHelper {
    public static final C0246hr GSON_CONVERTER = new C0246hr();
    public static final String KEY_CLIENT_MUTATION_ID = "client_mutation_id";

    public static class Data<T> {
        public T data;
    }

    public static class Input<T> {
        public T input;

        public Input(T t) {
            this.input = t;
        }
    }

    /* JADX INFO: finally extract failed */
    public static String A00(Map<String, ?> map) {
        C0246hr hrVar = GSON_CONVERTER;
        ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
        Set<Map.Entry<String, ?>> entrySet = map.entrySet();
        if (entrySet instanceof Collection) {
            ImmutableMap.Builder.A00(builder, builder.A00 + entrySet.size());
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            builder.put(it.next());
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            UUID randomUUID = UUID.randomUUID();
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            builder.put(KEY_CLIENT_MUTATION_ID, randomUUID.toString());
            return hrVar.A05(new Input(builder.build()));
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }
}
