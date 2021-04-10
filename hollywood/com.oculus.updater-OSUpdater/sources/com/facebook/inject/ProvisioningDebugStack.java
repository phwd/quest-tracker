package com.facebook.inject;

import com.google.inject.Key;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
public class ProvisioningDebugStack {
    private static ThreadLocal<List<Object>> providerStack = new ThreadLocal<List<Object>>() {
        /* class com.facebook.inject.ProvisioningDebugStack.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public List<Object> initialValue() {
            return new ArrayList();
        }
    };

    /* access modifiers changed from: package-private */
    public enum StackType {
        PROVIDER_GET,
        INSTANCE_GET,
        INJECT_COMPONENT
    }

    ProvisioningDebugStack() {
    }

    /* access modifiers changed from: package-private */
    public static class StackEntry {
        final Key<?> key;
        final StackType stackType;

        StackEntry(StackType stackType2, Key<?> key2) {
            this.stackType = stackType2;
            this.key = key2;
        }
    }

    public static void push(StackType stackType, Key<?> key) {
        List<Object> list = providerStack.get();
        list.add(stackType);
        list.add(key);
    }

    public static void pop() {
        List<Object> list = providerStack.get();
        list.remove(list.size() - 1);
        list.remove(list.size() - 1);
    }

    public static List<StackEntry> getStackEntries() {
        List<Object> list = providerStack.get();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i += 2) {
            arrayList.add(new StackEntry((StackType) list.get(i), (Key) list.get(i + 1)));
        }
        return arrayList;
    }
}
