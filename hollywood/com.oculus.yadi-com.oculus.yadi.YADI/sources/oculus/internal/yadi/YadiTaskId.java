package oculus.internal.yadi;

public class YadiTaskId implements Comparable<YadiTaskId> {
    public final String name;
    public long priority;

    public YadiTaskId(int i, String str) {
        this.name = str;
        this.priority = ((((long) i) & 127) << 56) | System.currentTimeMillis();
    }

    public int compareTo(YadiTaskId yadiTaskId) {
        int i = ((this.priority - yadiTaskId.priority) > 0 ? 1 : ((this.priority - yadiTaskId.priority) == 0 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof YadiTaskId)) {
            return false;
        }
        return this.name.equals(((YadiTaskId) obj).name);
    }
}
