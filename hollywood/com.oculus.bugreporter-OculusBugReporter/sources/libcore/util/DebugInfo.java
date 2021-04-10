package libcore.util;

import java.util.ArrayList;
import java.util.List;

public class DebugInfo {
    private final List<DebugEntry> entries = new ArrayList();

    public DebugInfo addStringEntry(String key, String value) {
        this.entries.add(new DebugEntry(key, value));
        return this;
    }

    public DebugInfo addStringEntry(String key, int value) {
        addStringEntry(key, Integer.toString(value));
        return this;
    }

    public List<DebugEntry> getDebugEntries() {
        return this.entries;
    }

    public DebugEntry getDebugEntry(String key) {
        for (DebugEntry entry : getDebugEntries()) {
            if (key.equals(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }

    public static class DebugEntry {
        private final String key;
        private final String stringValue;

        public DebugEntry(String key2, String stringValue2) {
            this.key = key2;
            this.stringValue = stringValue2;
        }

        public String getKey() {
            return this.key;
        }

        public String getStringValue() {
            return this.stringValue;
        }
    }
}
