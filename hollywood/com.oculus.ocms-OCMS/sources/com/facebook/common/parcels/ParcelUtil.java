package com.facebook.common.parcels;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.util.TriState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class ParcelUtil {
    public static void writeBool(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static boolean readBool(Parcel parcel) {
        return parcel.readInt() == 1;
    }

    public static <T> void writeImmutableSet(Parcel parcel, @Nullable ImmutableSet<T> immutableSet) {
        writeSet(parcel, immutableSet);
    }

    @Nullable
    public static <T> ImmutableSet<T> readImmutableSet(Parcel parcel, ClassLoader classLoader) {
        Set readSet = readSet(parcel, classLoader);
        if (readSet == null) {
            return null;
        }
        return ImmutableSet.copyOf((Collection) readSet);
    }

    public static <T> void writeSet(Parcel parcel, @Nullable Set<T> set) {
        if (set == null) {
            parcel.writeList(null);
            return;
        }
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.addAll(set);
        parcel.writeList(newArrayList);
    }

    @Nullable
    public static <T> Set<T> readSet(Parcel parcel) {
        return readSet(parcel, List.class.getClassLoader());
    }

    @Nullable
    public static <T> Set<T> readSet(Parcel parcel, ClassLoader classLoader) {
        ArrayList readArrayList = parcel.readArrayList(classLoader);
        if (readArrayList == null) {
            return null;
        }
        return Sets.newHashSet(readArrayList);
    }

    public static void writeStringMap(Parcel parcel, @Nullable Map<String, String> map) {
        ArrayList newArrayList = Lists.newArrayList();
        ArrayList newArrayList2 = Lists.newArrayList();
        if (map == null) {
            parcel.writeStringList(newArrayList);
            parcel.writeStringList(newArrayList2);
            return;
        }
        for (String str : map.keySet()) {
            newArrayList.add(str);
            newArrayList2.add(map.get(str));
        }
        parcel.writeStringList(newArrayList);
        parcel.writeStringList(newArrayList2);
    }

    public static void writeDate(Parcel parcel, @Nullable Date date) {
        if (date == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeLong(date.getTime());
    }

    @Nullable
    public static Date readDate(Parcel parcel) {
        if (parcel.readInt() == 1) {
            return new Date(parcel.readLong());
        }
        return null;
    }

    public static void writeCalendar(Parcel parcel, @Nullable Calendar calendar) {
        if (calendar == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeLong(calendar.getTimeInMillis());
    }

    @Nullable
    public static Calendar readCalendar(Parcel parcel) {
        if (parcel.readInt() != 1) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(parcel.readLong());
        return instance;
    }

    public static void writeIntegerObject(Parcel parcel, @Nullable Integer num) {
        if (num == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(num.intValue());
    }

    @Nullable
    public static Integer readIntegerObject(Parcel parcel) {
        if (parcel.readInt() == 1) {
            return Integer.valueOf(parcel.readInt());
        }
        return null;
    }

    public static void writeLongObject(Parcel parcel, @Nullable Long l) {
        if (l == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeLong(l.longValue());
    }

    @Nullable
    public static Long readLongObject(Parcel parcel) {
        if (parcel.readInt() == 1) {
            return Long.valueOf(parcel.readLong());
        }
        return null;
    }

    public static void writeDoubleObject(Parcel parcel, @Nullable Double d) {
        if (d == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeDouble(d.doubleValue());
    }

    @Nullable
    public static Double readDoubleObject(Parcel parcel) {
        if (parcel.readInt() == 1) {
            return Double.valueOf(parcel.readDouble());
        }
        return null;
    }

    public static void writeBoolObject(Parcel parcel, @Nullable Boolean bool) {
        if (bool == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        writeBool(parcel, bool.booleanValue());
    }

    @Nullable
    public static Boolean readBoolObject(Parcel parcel) {
        if (parcel.readInt() == 1) {
            return Boolean.valueOf(readBool(parcel));
        }
        return null;
    }

    public static void writeBigDecimal(Parcel parcel, @Nullable BigDecimal bigDecimal) {
        parcel.writeString(bigDecimal != null ? bigDecimal.toString() : null);
    }

    @Nullable
    public static BigDecimal readBigDecimal(Parcel parcel) {
        try {
            String readString = parcel.readString();
            if (readString == null) {
                return null;
            }
            return new BigDecimal(readString);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static void writeTriState(Parcel parcel, @Nullable TriState triState) {
        if (triState == null) {
            parcel.writeInt(0);
            parcel.writeInt(0);
        } else if (triState == TriState.UNSET) {
            parcel.writeInt(0);
            parcel.writeInt(1);
        } else if (triState == TriState.YES) {
            parcel.writeInt(1);
            parcel.writeInt(1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(0);
        }
    }

    @Nullable
    public static TriState readTriState(Parcel parcel) {
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        if (readInt == 0) {
            if (readInt2 == 0) {
                return null;
            }
            return TriState.UNSET;
        } else if (readInt2 == 0) {
            return TriState.NO;
        } else {
            return TriState.YES;
        }
    }

    public static <T extends Parcelable> void writeOptional(Parcel parcel, @Nullable Optional<T> optional, int i) {
        if (optional == null) {
            parcel.writeInt(0);
        } else if (!optional.isPresent()) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(2);
            parcel.writeParcelable(optional.get(), i);
        }
    }

    @Nullable
    public static <T extends Parcelable> Optional<T> readOptional(Parcel parcel, Class<T> cls) {
        int readInt = parcel.readInt();
        if (readInt == 0) {
            return null;
        }
        if (readInt == 1) {
            return Optional.absent();
        }
        if (readInt == 2) {
            return Optional.of(parcel.readParcelable(cls.getClassLoader()));
        }
        throw new IllegalStateException("Invalid state of the parcel to read Optional from: " + readInt);
    }

    public static void writeIntegerOptional(Parcel parcel, @Nullable Optional<Integer> optional) {
        if (optional == null) {
            parcel.writeInt(0);
        } else if (!optional.isPresent()) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(2);
            writeIntegerObject(parcel, optional.get());
        }
    }

    @Nullable
    public static Optional<Integer> readIntegerOptional(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == 0) {
            return null;
        }
        if (readInt == 1) {
            return Optional.absent();
        }
        if (readInt == 2) {
            return Optional.of(readIntegerObject(parcel));
        }
        throw new IllegalStateException("Invalid state of the parcel to read Optional from: " + readInt);
    }

    public static void writeGregorianCalendar(Parcel parcel, @Nullable GregorianCalendar gregorianCalendar) {
        parcel.writeByte(gregorianCalendar != null ? (byte) 1 : 0);
        if (gregorianCalendar != null) {
            parcel.writeString(gregorianCalendar.getTimeZone().getID());
            parcel.writeLong(gregorianCalendar.getTimeInMillis());
        }
    }

    @Nullable
    public static GregorianCalendar readGregorianCalendar(Parcel parcel) {
        if (parcel.readByte() == 0) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone(parcel.readString()));
        gregorianCalendar.setTimeInMillis(parcel.readLong());
        return gregorianCalendar;
    }

    public static void readStringMap(Parcel parcel, Map<String, String> map) {
        ArrayList newArrayList = Lists.newArrayList();
        ArrayList newArrayList2 = Lists.newArrayList();
        parcel.readStringList(newArrayList);
        parcel.readStringList(newArrayList2);
        for (int i = 0; i < newArrayList.size(); i++) {
            map.put(newArrayList.get(i), newArrayList2.get(i));
        }
    }

    public static <V extends Parcelable> void writeMap(Parcel parcel, @Nullable Map<String, V> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        Bundle bundle = new Bundle();
        ArrayList newArrayList = Lists.newArrayList();
        Set<Map.Entry<String, V>> entrySet = map.entrySet();
        parcel.writeInt(entrySet.size());
        for (Map.Entry<String, V> entry : entrySet) {
            String key = entry.getKey();
            newArrayList.add(key);
            bundle.putParcelable(key, entry.getValue());
        }
        parcel.writeStringList(newArrayList);
        parcel.writeBundle(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Map<java.lang.String, V extends android.os.Parcelable> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <V extends Parcelable> void readMap(Parcel parcel, Map<String, V> map, Class<V> cls) {
        if (parcel.readInt() != -1) {
            ArrayList newArrayList = Lists.newArrayList();
            parcel.readStringList(newArrayList);
            Bundle readBundle = parcel.readBundle(cls.getClassLoader());
            if (!(map == 0 || readBundle == null || readBundle.isEmpty())) {
                for (String str : newArrayList) {
                    map.put(str, readBundle.getParcelable(str));
                }
            }
        }
    }

    public static <V extends Parcelable> ImmutableMap<String, V> readImmutableMap(Parcel parcel, Class<V> cls) {
        HashMap hashMap = new HashMap();
        readMap(parcel, hashMap, cls);
        return ImmutableMap.copyOf(hashMap);
    }

    public static <K, V> ImmutableMap<K, V> readImmutableMap(Parcel parcel) {
        HashMap hashMap = new HashMap();
        parcel.readMap(hashMap, ParcelUtil.class.getClassLoader());
        return ImmutableMap.copyOf(hashMap);
    }

    public static <V extends Parcelable> void writeMappedList(Parcel parcel, Map<String, ArrayList<V>> map) {
        Bundle bundle = new Bundle();
        ArrayList newArrayList = Lists.newArrayList();
        for (Map.Entry<String, ArrayList<V>> entry : map.entrySet()) {
            String key = entry.getKey();
            newArrayList.add(key);
            bundle.putParcelableArrayList(key, entry.getValue());
        }
        parcel.writeStringList(newArrayList);
        parcel.writeBundle(bundle);
    }

    public static <V extends Parcelable> void readMappedList(Parcel parcel, Map<String, ArrayList<V>> map) {
        if (map != null) {
            ArrayList newArrayList = Lists.newArrayList();
            parcel.readStringList(newArrayList);
            Bundle readBundle = parcel.readBundle(ParcelUtil.class.getClassLoader());
            if (!(readBundle == null || readBundle.isEmpty())) {
                for (String str : newArrayList) {
                    map.put(str, readBundle.getParcelableArrayList(str));
                }
            }
        }
    }

    public static <V extends Parcelable> ImmutableMap<String, ImmutableList<V>> readImmutableMapWithImmutableList(Parcel parcel) {
        HashMap hashMap = new HashMap();
        readMappedList(parcel, hashMap);
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), ImmutableList.copyOf((Collection) entry.getValue()));
        }
        return ImmutableMap.copyOf(hashMap2);
    }

    public static <V extends Parcelable> void writeImmutableMapWithImmutableList(Parcel parcel, ImmutableMap<String, ImmutableList<V>> immutableMap) {
        HashMap hashMap = new HashMap();
        UnmodifiableIterator<Map.Entry<String, ImmutableList<V>>> it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ImmutableList<V>> next = it.next();
            hashMap.put(next.getKey(), new ArrayList(next.getValue()));
        }
        writeMappedList(parcel, hashMap);
    }

    public static <K extends Parcelable, V extends Enum> ImmutableMap<K, ImmutableList<V>> readImmutableMapWithImmutableEnumList(Parcel parcel) {
        HashMap hashMap = new HashMap();
        parcel.readMap(hashMap, ParcelUtil.class.getClassLoader());
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        for (Map.Entry entry : hashMap.entrySet()) {
            builder.put(entry.getKey(), ImmutableList.copyOf((Collection) entry.getValue()));
        }
        return builder.build();
    }

    public static <K extends Parcelable, V extends Enum> void writeImmutableMapWithImmutableEnumList(Parcel parcel, ImmutableMap<K, ImmutableList<V>> immutableMap) {
        HashMap hashMap = new HashMap();
        UnmodifiableIterator<Map.Entry<K, ImmutableList<V>>> it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, ImmutableList<V>> next = it.next();
            hashMap.put(next.getKey(), new ArrayList(next.getValue()));
        }
        parcel.writeMap(hashMap);
    }

    public static <T> ImmutableList<T> readImmutableTypedList(Parcel parcel, Parcelable.Creator<T> creator) {
        ArrayList newArrayList = Lists.newArrayList();
        parcel.readTypedList(newArrayList, creator);
        return ImmutableList.copyOf((Collection) newArrayList);
    }

    @Nullable
    public static <T extends Parcelable> ImmutableList<T> readNullableImmutableTypedList(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() == 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        parcel.readTypedList(arrayList, creator);
        return ImmutableList.copyOf((Collection) arrayList);
    }

    public static <T extends Parcelable> void writeNullableImmutableTypedList(Parcel parcel, @Nullable ImmutableList<T> immutableList) {
        int i = immutableList == null ? 1 : 0;
        parcel.writeInt(i);
        if (i != 1) {
            parcel.writeTypedList(immutableList);
        }
    }

    public static <T extends Parcelable> void writeNullableImmutableList(Parcel parcel, @Nullable ImmutableList<T> immutableList) {
        parcel.writeList(immutableList);
    }

    @Nullable
    public static <T extends Parcelable> ImmutableList<T> readNullableImmutableList(Parcel parcel, Class<T> cls) {
        ArrayList readArrayList = parcel.readArrayList(cls.getClassLoader());
        if (readArrayList != null) {
            return ImmutableList.copyOf((Collection) readArrayList);
        }
        return null;
    }

    public static ImmutableList<String> readImmutableStringList(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        return ImmutableList.copyOf((Collection) arrayList);
    }

    public static void writeImmutableStringList(Parcel parcel, ImmutableList<String> immutableList) {
        Preconditions.checkNotNull(immutableList);
        parcel.writeStringList(immutableList);
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcel, Class<T> cls) {
        return (T) parcel.readParcelable(cls.getClassLoader());
    }

    public static <T extends Enum<T>> void writeEnum(Parcel parcel, @Nullable T t) {
        if (t == null) {
            parcel.writeString(null);
        } else {
            parcel.writeString(t.name());
        }
    }

    @Nullable
    public static <T extends Enum<T>> T readEnum(Parcel parcel, Class<T> cls) {
        String readString = parcel.readString();
        if (readString == null) {
            return null;
        }
        return (T) Enum.valueOf(cls, readString);
    }

    public static <T extends Enum<T>> void writeEnumList(Parcel parcel, ImmutableList<T> immutableList) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<T> it = immutableList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().name());
        }
        parcel.writeStringList(arrayList);
    }

    public static <T extends Enum<T>> ImmutableList<T> readEnumList(Parcel parcel, Class<T> cls) {
        ArrayList<String> arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            arrayList2.add(Enum.valueOf(cls, str));
        }
        return ImmutableList.copyOf((Collection) arrayList2);
    }

    public static void writeNullableJsonNode(Parcel parcel, @Nullable JsonNode jsonNode) {
        if (jsonNode == null) {
            parcel.writeString(null);
        } else {
            writeJsonNode(parcel, jsonNode);
        }
    }

    @Nullable
    public static JsonNode readNullableJsonNode(Parcel parcel) {
        try {
            return readJsonNode(parcel);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void writeJsonNode(Parcel parcel, JsonNode jsonNode) {
        parcel.writeString(jsonNode.toString());
    }

    public static JsonNode readJsonNode(Parcel parcel) throws IOException {
        return new ObjectMapper().readTree(parcel.readString());
    }

    public static void writeJSONObject(Parcel parcel, @Nullable JSONObject jSONObject) {
        parcel.writeString(jSONObject != null ? jSONObject.toString() : null);
    }

    @Nullable
    public static JSONObject readJSONObject(Parcel parcel) {
        try {
            String readString = parcel.readString();
            if (readString == null) {
                return null;
            }
            return new JSONObject(readString);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void writeNullableStringList(Parcel parcel, @Nullable List<String> list) {
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeStringList(list);
    }

    @Nullable
    public static ImmutableList<String> readNullableImmutableStringList(Parcel parcel) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return readImmutableStringList(parcel);
    }

    @Nullable
    public static <T> T readNext(Parcel parcel) {
        return (T) parcel.readValue(ParcelUtil.class.getClassLoader());
    }

    public static ImmutableList<?> readRest(Parcel parcel) {
        if (parcel.dataAvail() == 0) {
            return ImmutableList.of();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        while (parcel.dataAvail() > 0) {
            builder.add(readNext(parcel));
        }
        return builder.build();
    }

    public static Uri readUri(Parcel parcel) {
        return (Uri) readParcelable(parcel, Uri.class);
    }

    public static <V extends Parcelable> void writeMultimap(Parcel parcel, Multimap<String, V> multimap) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Collection<V>> entry : multimap.asMap().entrySet()) {
            hashMap.put(entry.getKey(), new ArrayList(entry.getValue()));
        }
        writeMappedList(parcel, hashMap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.Multimap<java.lang.String, V extends android.os.Parcelable> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <V extends Parcelable> void readMultimap(Parcel parcel, Multimap<String, V> multimap) {
        HashMap hashMap = new HashMap();
        readMappedList(parcel, hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            multimap.putAll(entry.getKey(), (Iterable) entry.getValue());
        }
    }

    public static <K extends Parcelable, V extends Parcelable> void writeParcelableMultimap(Parcel parcel, Multimap<K, V> multimap) {
        Map<K, Collection<V>> asMap = multimap.asMap();
        parcel.writeInt(asMap.size());
        for (Map.Entry<K, Collection<V>> entry : asMap.entrySet()) {
            parcel.writeParcelable(entry.getKey(), 0);
            parcel.writeList(Lists.newArrayList(entry.getValue()));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.google.common.collect.Multimap<K extends android.os.Parcelable, V extends android.os.Parcelable> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <K extends Parcelable, V extends Parcelable> void readParcelableMultimap(Parcel parcel, Multimap<K, V> multimap) {
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            Parcelable readParcelable = parcel.readParcelable(ParcelUtil.class.getClassLoader());
            ArrayList newArrayList = Lists.newArrayList();
            parcel.readList(newArrayList, ParcelUtil.class.getClassLoader());
            multimap.putAll(readParcelable, newArrayList);
        }
    }
}
