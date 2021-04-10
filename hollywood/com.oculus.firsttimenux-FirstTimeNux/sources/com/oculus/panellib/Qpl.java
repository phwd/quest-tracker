package com.oculus.panellib;

import java.util.ArrayList;
import java.util.Iterator;

public class Qpl {
    public static final int ACTION_SUCCESS = 2;
    public static final long AUTOSET_TIMESTAMP = -1;
    public static final int DEFAULT_INSTANCE_KEY = 0;
    private static long NativeModuleHandle = 0;
    public static final int QPL_MARKER_INIT = 51707905;
    public static final String QPL_RANGE_END_SUFFIX = "_end";
    public static final String QPL_RANGE_START_SUFFIX = "_start";
    private static ArrayList<MarkerCall> markerAnnotateCalls = new ArrayList<>();
    private static ArrayList<MarkerCall> markerEndCalls = new ArrayList<>();
    private static ArrayList<MarkerPointCall> markerPointCalls = new ArrayList<>();
    private static ArrayList<MarkerCall> markerStartCalls = new ArrayList<>();
    private static ArrayList<MarkerCall> markerTagCalls = new ArrayList<>();

    private static native void nativeMarkerAnnotate(long j, int i, String str, String str2, int i2);

    private static native void nativeMarkerEnd(long j, int i, int i2, int i3, long j2);

    private static native void nativeMarkerPoint(long j, int i, String str, int i2, long j2);

    private static native void nativeMarkerStart(long j, int i, int i2, long j2);

    private static native void nativeMarkerTag(long j, int i, String str, int i2);

    /* access modifiers changed from: package-private */
    public static class MarkerPointCall {
        int instanceKey;
        int markerId;
        String name;
        long timestamp;

        MarkerPointCall() {
        }
    }

    /* access modifiers changed from: package-private */
    public static class MarkerCall {
        int actionId;
        int instanceKey;
        String key;
        int markerId;
        long timestamp;
        String value;

        MarkerCall() {
        }
    }

    private static long computeTimestamp(long timestamp) {
        return timestamp == -1 ? System.nanoTime() / 1000000 : timestamp;
    }

    public static void markerStart(int markerId) {
        markerStart(markerId, 0, -1);
    }

    public static void markerStart(int markerId, int instanceKey, long timestamp) {
        if (NativeModuleHandle != 0) {
            nativeMarkerStart(NativeModuleHandle, markerId, instanceKey, computeTimestamp(timestamp));
            return;
        }
        MarkerCall call = new MarkerCall();
        call.markerId = markerId;
        call.instanceKey = instanceKey;
        call.timestamp = computeTimestamp(timestamp);
        synchronized (markerStartCalls) {
            markerStartCalls.add(call);
        }
    }

    public static void markerEnd(int markerId) {
        markerEnd(markerId, 2, 0, -1);
    }

    public static void markerEnd(int markerId, int actionId, int instanceKey, long timestamp) {
        if (NativeModuleHandle != 0) {
            nativeMarkerEnd(NativeModuleHandle, markerId, actionId, instanceKey, computeTimestamp(timestamp));
            return;
        }
        MarkerCall call = new MarkerCall();
        call.markerId = markerId;
        call.instanceKey = instanceKey;
        call.actionId = actionId;
        call.timestamp = computeTimestamp(timestamp);
        synchronized (markerEndCalls) {
            markerEndCalls.add(call);
        }
    }

    public static void markerTag(int markerId, String tag) {
        markerTag(markerId, tag, 0);
    }

    public static void markerTag(int markerId, String tag, int instanceKey) {
        if (NativeModuleHandle != 0) {
            nativeMarkerTag(NativeModuleHandle, markerId, tag, instanceKey);
            return;
        }
        MarkerCall call = new MarkerCall();
        call.markerId = markerId;
        call.instanceKey = instanceKey;
        call.value = tag;
        synchronized (markerTagCalls) {
            markerTagCalls.add(call);
        }
    }

    public static void markerAnnotate(int markerId, String annotationKey, String annotationValue) {
        markerAnnotate(markerId, annotationKey, annotationValue, 0);
    }

    public static void markerAnnotate(int markerId, String annotationKey, String annotationValue, int instanceKey) {
        if (NativeModuleHandle != 0) {
            nativeMarkerAnnotate(NativeModuleHandle, markerId, annotationKey, annotationValue, instanceKey);
            return;
        }
        MarkerCall call = new MarkerCall();
        call.markerId = markerId;
        call.instanceKey = instanceKey;
        call.key = annotationKey;
        call.value = annotationValue;
        synchronized (markerAnnotateCalls) {
            markerAnnotateCalls.add(call);
        }
    }

    public static void markerPoint(int markerId, String name, int instanceKey, long timestamp) {
        if (NativeModuleHandle != 0) {
            nativeMarkerPoint(NativeModuleHandle, markerId, name, instanceKey, computeTimestamp(timestamp));
            return;
        }
        MarkerPointCall call = new MarkerPointCall();
        call.markerId = markerId;
        call.name = name;
        call.instanceKey = instanceKey;
        call.timestamp = computeTimestamp(timestamp);
        synchronized (markerPointCalls) {
            markerPointCalls.add(call);
        }
    }

    public static void markerPoint(int markerId, String name) {
        markerPoint(markerId, name, 0, -1);
    }

    public static void markerPoint(int markerId, String name, int instanceKey) {
        markerPoint(markerId, name, instanceKey, -1);
    }

    public static void markerPointStart(int markerId, String name, int instanceKey) {
        markerPoint(markerId, name + QPL_RANGE_START_SUFFIX, instanceKey, -1);
    }

    public static void markerPointStart(int markerId, String name) {
        markerPointStart(markerId, name, 0);
    }

    public static void markerPointEnd(int markerId, String name, int instanceKey) {
        markerPoint(markerId, name + QPL_RANGE_END_SUFFIX, instanceKey, -1);
    }

    public static void markerPointEnd(int markerId, String name) {
        markerPointEnd(markerId, name, 0);
    }

    public static void flush() {
        ArrayList<MarkerCall> tmp;
        ArrayList<MarkerPointCall> tmp2;
        ArrayList<MarkerCall> tmp3;
        ArrayList<MarkerCall> tmp4;
        ArrayList<MarkerCall> tmp5;
        ArrayList<MarkerCall> markerStartCallsCopy = new ArrayList<>();
        synchronized (markerStartCalls) {
            tmp = markerStartCalls;
            markerStartCalls = markerStartCallsCopy;
        }
        Iterator<MarkerCall> it = tmp.iterator();
        while (it.hasNext()) {
            MarkerCall call = it.next();
            nativeMarkerStart(NativeModuleHandle, call.markerId, call.instanceKey, call.timestamp);
        }
        ArrayList<MarkerPointCall> markerPointCallsCopy = new ArrayList<>();
        synchronized (markerPointCalls) {
            tmp2 = markerPointCalls;
            markerPointCalls = markerPointCallsCopy;
        }
        Iterator<MarkerPointCall> it2 = tmp2.iterator();
        while (it2.hasNext()) {
            MarkerPointCall call2 = it2.next();
            nativeMarkerPoint(NativeModuleHandle, call2.markerId, call2.name, call2.instanceKey, call2.timestamp);
        }
        ArrayList<MarkerCall> markerTagCallsCopy = new ArrayList<>();
        synchronized (markerTagCalls) {
            tmp3 = markerTagCalls;
            markerTagCalls = markerTagCallsCopy;
        }
        Iterator<MarkerCall> it3 = tmp3.iterator();
        while (it3.hasNext()) {
            MarkerCall call3 = it3.next();
            nativeMarkerTag(NativeModuleHandle, call3.markerId, call3.value, call3.instanceKey);
        }
        ArrayList<MarkerCall> markerAnnotateCallsCopy = new ArrayList<>();
        synchronized (markerAnnotateCalls) {
            tmp4 = markerAnnotateCalls;
            markerAnnotateCalls = markerAnnotateCallsCopy;
        }
        Iterator<MarkerCall> it4 = tmp4.iterator();
        while (it4.hasNext()) {
            MarkerCall call4 = it4.next();
            nativeMarkerAnnotate(NativeModuleHandle, call4.markerId, call4.key, call4.value, call4.instanceKey);
        }
        ArrayList<MarkerCall> markerEndCallsCopy = new ArrayList<>();
        synchronized (markerEndCalls) {
            tmp5 = markerEndCalls;
            markerEndCalls = markerEndCallsCopy;
        }
        Iterator<MarkerCall> it5 = tmp5.iterator();
        while (it5.hasNext()) {
            MarkerCall call5 = it5.next();
            nativeMarkerEnd(NativeModuleHandle, call5.markerId, call5.actionId, call5.instanceKey, call5.timestamp);
        }
    }
}
