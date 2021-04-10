package com.oculus.panellib;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Iterator;

public class Qpl {
    public static final int ACTION_SUCCESS = 2;
    public static final long AUTOSET_TIMESTAMP = -1;
    public static final int DEFAULT_INSTANCE_KEY = 0;
    public static final long NativeModuleHandle = 0;
    public static final int QPL_MARKER_INIT = 51707905;
    public static final String QPL_RANGE_END_SUFFIX = "_end";
    public static final String QPL_RANGE_START_SUFFIX = "_start";
    public static ArrayList<MarkerCall> markerAnnotateCalls = new ArrayList<>();
    public static ArrayList<MarkerCall> markerEndCalls = new ArrayList<>();
    public static ArrayList<MarkerPointCall> markerPointCalls = new ArrayList<>();
    public static ArrayList<MarkerCall> markerStartCalls = new ArrayList<>();
    public static ArrayList<MarkerCall> markerTagCalls = new ArrayList<>();

    public static class MarkerCall {
        public int actionId;
        public int instanceKey;
        public String key;
        public int markerId;
        public long timestamp;
        public String value;
    }

    public static class MarkerPointCall {
        public int instanceKey;
        public int markerId;
        public String name;
        public long timestamp;
    }

    public static native void nativeMarkerAnnotate(long j, int i, String str, String str2, int i2);

    public static native void nativeMarkerEnd(long j, int i, int i2, int i3, long j2);

    public static native void nativeMarkerPoint(long j, int i, String str, int i2, long j2);

    public static native void nativeMarkerStart(long j, int i, int i2, long j2);

    public static native void nativeMarkerTag(long j, int i, String str, int i2);

    public static long computeTimestamp(long j) {
        if (j == -1) {
            return System.nanoTime() / 1000000;
        }
        return j;
    }

    public static void flush() {
        ArrayList<MarkerCall> arrayList;
        ArrayList<MarkerPointCall> arrayList2;
        ArrayList<MarkerCall> arrayList3;
        ArrayList<MarkerCall> arrayList4;
        ArrayList<MarkerCall> arrayList5;
        ArrayList<MarkerCall> arrayList6 = new ArrayList<>();
        synchronized (markerStartCalls) {
            arrayList = markerStartCalls;
            markerStartCalls = arrayList6;
        }
        Iterator<MarkerCall> it = arrayList.iterator();
        while (it.hasNext()) {
            MarkerCall next = it.next();
            nativeMarkerStart(NativeModuleHandle, next.markerId, next.instanceKey, next.timestamp);
        }
        ArrayList<MarkerPointCall> arrayList7 = new ArrayList<>();
        synchronized (markerPointCalls) {
            arrayList2 = markerPointCalls;
            markerPointCalls = arrayList7;
        }
        Iterator<MarkerPointCall> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            MarkerPointCall next2 = it2.next();
            nativeMarkerPoint(NativeModuleHandle, next2.markerId, next2.name, next2.instanceKey, next2.timestamp);
        }
        ArrayList<MarkerCall> arrayList8 = new ArrayList<>();
        synchronized (markerTagCalls) {
            arrayList3 = markerTagCalls;
            markerTagCalls = arrayList8;
        }
        Iterator<MarkerCall> it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            MarkerCall next3 = it3.next();
            nativeMarkerTag(NativeModuleHandle, next3.markerId, next3.value, next3.instanceKey);
        }
        ArrayList<MarkerCall> arrayList9 = new ArrayList<>();
        synchronized (markerAnnotateCalls) {
            arrayList4 = markerAnnotateCalls;
            markerAnnotateCalls = arrayList9;
        }
        Iterator<MarkerCall> it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            MarkerCall next4 = it4.next();
            nativeMarkerAnnotate(NativeModuleHandle, next4.markerId, next4.key, next4.value, next4.instanceKey);
        }
        ArrayList<MarkerCall> arrayList10 = new ArrayList<>();
        synchronized (markerEndCalls) {
            arrayList5 = markerEndCalls;
            markerEndCalls = arrayList10;
        }
        Iterator<MarkerCall> it5 = arrayList5.iterator();
        while (it5.hasNext()) {
            MarkerCall next5 = it5.next();
            nativeMarkerEnd(NativeModuleHandle, next5.markerId, next5.actionId, next5.instanceKey, next5.timestamp);
        }
    }

    public static void markerAnnotate(int i, String str, String str2) {
        markerAnnotate(i, str, str2, 0);
    }

    public static void markerAnnotate(int i, String str, String str2, int i2) {
        long j = NativeModuleHandle;
        if (j != 0) {
            nativeMarkerAnnotate(j, i, str, str2, i2);
            return;
        }
        MarkerCall markerCall = new MarkerCall();
        markerCall.markerId = i;
        markerCall.instanceKey = i2;
        markerCall.key = str;
        markerCall.value = str2;
        synchronized (markerAnnotateCalls) {
            markerAnnotateCalls.add(markerCall);
        }
    }

    public static void markerEnd(int i) {
        markerEnd(i, 2, 0, -1);
    }

    public static void markerEnd(int i, int i2, int i3, long j) {
        long j2 = NativeModuleHandle;
        if (j2 != 0) {
            nativeMarkerEnd(j2, i, i2, i3, computeTimestamp(j));
            return;
        }
        MarkerCall markerCall = new MarkerCall();
        markerCall.markerId = i;
        markerCall.instanceKey = i3;
        markerCall.actionId = i2;
        markerCall.timestamp = computeTimestamp(j);
        synchronized (markerEndCalls) {
            markerEndCalls.add(markerCall);
        }
    }

    public static void markerPoint(int i, String str) {
        markerPoint(i, str, 0, -1);
    }

    public static void markerPoint(int i, String str, int i2) {
        markerPoint(i, str, i2, -1);
    }

    public static void markerPoint(int i, String str, int i2, long j) {
        long j2 = NativeModuleHandle;
        if (j2 != 0) {
            nativeMarkerPoint(j2, i, str, i2, computeTimestamp(j));
            return;
        }
        MarkerPointCall markerPointCall = new MarkerPointCall();
        markerPointCall.markerId = i;
        markerPointCall.name = str;
        markerPointCall.instanceKey = i2;
        markerPointCall.timestamp = computeTimestamp(j);
        synchronized (markerPointCalls) {
            markerPointCalls.add(markerPointCall);
        }
    }

    public static void markerPointEnd(int i, String str) {
        markerPointEnd(i, str, 0);
    }

    public static void markerPointEnd(int i, String str, int i2) {
        markerPoint(i, AnonymousClass006.A07(str, QPL_RANGE_END_SUFFIX), i2, -1);
    }

    public static void markerPointStart(int i, String str) {
        markerPointStart(i, str, 0);
    }

    public static void markerPointStart(int i, String str, int i2) {
        markerPoint(i, AnonymousClass006.A07(str, QPL_RANGE_START_SUFFIX), i2, -1);
    }

    public static void markerStart(int i) {
        markerStart(i, 0, -1);
    }

    public static void markerStart(int i, int i2, long j) {
        long j2 = NativeModuleHandle;
        if (j2 != 0) {
            nativeMarkerStart(j2, i, i2, computeTimestamp(j));
            return;
        }
        MarkerCall markerCall = new MarkerCall();
        markerCall.markerId = i;
        markerCall.instanceKey = i2;
        markerCall.timestamp = computeTimestamp(j);
        synchronized (markerStartCalls) {
            markerStartCalls.add(markerCall);
        }
    }

    public static void markerTag(int i, String str) {
        markerTag(i, str, 0);
    }

    public static void markerTag(int i, String str, int i2) {
        long j = NativeModuleHandle;
        if (j != 0) {
            nativeMarkerTag(j, i, str, i2);
            return;
        }
        MarkerCall markerCall = new MarkerCall();
        markerCall.markerId = i;
        markerCall.instanceKey = i2;
        markerCall.value = str;
        synchronized (markerTagCalls) {
            markerTagCalls.add(markerCall);
        }
    }
}
