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

    public static Builder beginSection(long j, String str) {
        return getBuilder(j, BEGIN_SECTION_FLUSHER, str);
    }

    public static Builder endSection(long j) {
        return getBuilder(j, END_SECTION_FLUSHER, "");
    }

    private static Builder getBuilder(long j, Flusher flusher, String str) {
        if (!Systrace.isTracing(j)) {
            return NOOP_BUILDER;
        }
        return THREAD_LOCAL_BUILDERS.get().reset(j, flusher, str);
    }

    private static class NoopBuilder extends Builder {
        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, double d) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, int i) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, long j) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, Object obj) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
        }

        private NoopBuilder() {
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
        public RealBuilder reset(long j, Flusher flusher, String str) {
            this.mTag = j;
            this.mFlusher = flusher;
            this.mSectionName = str;
            this.mArgs.clear();
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
            this.mFlusher.flush(this.mTag, this.mSectionName, this.mArgs);
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, Object obj) {
            this.mArgs.add(str, String.valueOf(obj));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, int i) {
            this.mArgs.add(str, Integer.toString(i));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, long j) {
            this.mArgs.add(str, Long.toString(j));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, double d) {
            this.mArgs.add(str, Double.toString(d));
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static class ArgsList {
        private static final int DEFAULT_CAPACITY = 10;
        private String[] mData = new String[10];
        private int mSize = 0;

        public void add(String str, String str2) {
            if (this.mData.length - this.mSize < 2) {
                grow();
            }
            String[] strArr = this.mData;
            int i = this.mSize;
            this.mSize = i + 1;
            strArr[i] = str;
            int i2 = this.mSize;
            this.mSize = i2 + 1;
            strArr[i2] = str2;
        }

        public void clear() {
            for (int i = 0; i < this.mSize; i++) {
                this.mData[i] = null;
            }
            this.mSize = 0;
        }

        private void grow() {
            String[] strArr = this.mData;
            this.mData = new String[(strArr.length * 2)];
            System.arraycopy(strArr, 0, this.mData, 0, this.mSize);
        }
    }

    private static class BeginSectionFlusher implements Flusher {
        private BeginSectionFlusher() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Flusher
        public void flush(long j, String str, ArgsList argsList) {
            if (Systrace.isTracing(j)) {
                Systrace.beginSection(j, str, argsList.mData, argsList.mSize);
            }
        }
    }

    private static class EndSectionFlusher implements Flusher {
        private EndSectionFlusher() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Flusher
        public void flush(long j, String str, ArgsList argsList) {
            if (Systrace.isTracing(j)) {
                Systrace.endSection(j, str, argsList.mData, argsList.mSize);
            }
        }
    }
}
