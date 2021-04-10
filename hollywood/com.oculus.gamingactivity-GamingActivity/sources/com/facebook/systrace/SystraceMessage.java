package com.facebook.systrace;

public final class SystraceMessage {
    private static final Flusher BEGIN_SECTION_FLUSHER = new BeginSectionFlusher();
    private static final Flusher END_SECTION_FLUSHER = new EndSectionFlusher();
    private static final Builder NOOP_BUILDER = new NoopBuilder();
    private static final ThreadLocal<RealBuilder> THREAD_LOCAL_BUILDERS = new ThreadLocal<RealBuilder>() {
        /* class com.facebook.systrace.SystraceMessage.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public RealBuilder initialValue() {
            return new RealBuilder();
        }
    };

    public static abstract class Builder {
        public abstract Builder arg(String str, double d);

        public abstract Builder arg(String str, int i);

        public abstract Builder arg(String str, long j);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    /* access modifiers changed from: private */
    public interface Flusher {
        void flush(long j, String str, ArgsList argsList);
    }

    public static Builder beginSection(long tag, String sectionName) {
        return getBuilder(tag, BEGIN_SECTION_FLUSHER, sectionName);
    }

    public static Builder endSection(long tag) {
        return getBuilder(tag, END_SECTION_FLUSHER, "");
    }

    private static Builder getBuilder(long tag, Flusher flusher, String sectionName) {
        if (!Systrace.isTracing(tag)) {
            return NOOP_BUILDER;
        }
        return THREAD_LOCAL_BUILDERS.get().reset(tag, flusher, sectionName);
    }

    private static class NoopBuilder extends Builder {
        private NoopBuilder() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, Object value) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, int value) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, long value) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, double value) {
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static class RealBuilder extends Builder {
        ArgsList mArgs;
        Flusher mFlusher;
        String mSectionName;
        long mTag;

        private RealBuilder() {
            this.mArgs = new ArgsList();
        }

        /* access modifiers changed from: package-private */
        public RealBuilder reset(long tag, Flusher flusher, String sectionName) {
            this.mTag = tag;
            this.mFlusher = flusher;
            this.mSectionName = sectionName;
            this.mArgs.clear();
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
            this.mFlusher.flush(this.mTag, this.mSectionName, this.mArgs);
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, Object value) {
            this.mArgs.add(key, String.valueOf(value));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, int value) {
            this.mArgs.add(key, Integer.toString(value));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, long value) {
            this.mArgs.add(key, Long.toString(value));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, double value) {
            this.mArgs.add(key, Double.toString(value));
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static class ArgsList {
        private static final int DEFAULT_CAPACITY = 10;
        private String[] mData = new String[10];
        private int mSize = 0;

        public void add(String key, String value) {
            if (this.mData.length - this.mSize < 2) {
                grow();
            }
            String[] strArr = this.mData;
            int i = this.mSize;
            this.mSize = i + 1;
            strArr[i] = key;
            String[] strArr2 = this.mData;
            int i2 = this.mSize;
            this.mSize = i2 + 1;
            strArr2[i2] = value;
        }

        public void clear() {
            for (int i = 0; i < this.mSize; i++) {
                this.mData[i] = null;
            }
            this.mSize = 0;
        }

        private void grow() {
            String[] oldArray = this.mData;
            this.mData = new String[(oldArray.length * 2)];
            System.arraycopy(oldArray, 0, this.mData, 0, this.mSize);
        }
    }

    private static class BeginSectionFlusher implements Flusher {
        private BeginSectionFlusher() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Flusher
        public void flush(long tag, String sectionName, ArgsList args) {
            if (Systrace.isTracing(tag)) {
                Systrace.beginSection(tag, sectionName, args.mData, args.mSize);
            }
        }
    }

    private static class EndSectionFlusher implements Flusher {
        private EndSectionFlusher() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Flusher
        public void flush(long tag, String sectionName, ArgsList args) {
            if (Systrace.isTracing(tag)) {
                Systrace.endSection(tag, sectionName, args.mData, args.mSize);
            }
        }
    }
}
