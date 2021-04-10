package com.android.server.am;

import com.android.internal.app.procstats.Processstats;
import com.android.os.AtomsProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class MemInfoDumpProto extends GeneratedMessageLite<MemInfoDumpProto, Builder> implements MemInfoDumpProtoOrBuilder {
    public static final int APP_PROCESSES_FIELD_NUMBER = 4;
    public static final int CACHED_KERNEL_KB_FIELD_NUMBER = 11;
    public static final int CACHED_PSS_KB_FIELD_NUMBER = 10;
    private static final MemInfoDumpProto DEFAULT_INSTANCE = new MemInfoDumpProto();
    public static final int ELAPSED_REALTIME_MS_FIELD_NUMBER = 2;
    public static final int FREE_KB_FIELD_NUMBER = 12;
    public static final int IS_HIGH_END_GFX_FIELD_NUMBER = 28;
    public static final int IS_LOW_RAM_DEVICE_FIELD_NUMBER = 27;
    public static final int KSM_SHARED_KB_FIELD_NUMBER = 20;
    public static final int KSM_SHARING_KB_FIELD_NUMBER = 19;
    public static final int KSM_UNSHARED_KB_FIELD_NUMBER = 21;
    public static final int KSM_VOLATILE_KB_FIELD_NUMBER = 22;
    public static final int LOST_RAM_KB_FIELD_NUMBER = 15;
    public static final int NATIVE_PROCESSES_FIELD_NUMBER = 3;
    public static final int OOM_KB_FIELD_NUMBER = 25;
    private static volatile Parser<MemInfoDumpProto> PARSER = null;
    public static final int RESTORE_LIMIT_KB_FIELD_NUMBER = 26;
    public static final int STATUS_FIELD_NUMBER = 9;
    public static final int TOTAL_PSS_BY_CATEGORY_FIELD_NUMBER = 7;
    public static final int TOTAL_PSS_BY_OOM_ADJUSTMENT_FIELD_NUMBER = 6;
    public static final int TOTAL_PSS_BY_PROCESS_FIELD_NUMBER = 5;
    public static final int TOTAL_RAM_KB_FIELD_NUMBER = 8;
    public static final int TOTAL_ZRAM_KB_FIELD_NUMBER = 16;
    public static final int TOTAL_ZRAM_SWAP_KB_FIELD_NUMBER = 18;
    public static final int TUNING_LARGE_MB_FIELD_NUMBER = 24;
    public static final int TUNING_MB_FIELD_NUMBER = 23;
    public static final int UPTIME_DURATION_MS_FIELD_NUMBER = 1;
    public static final int USED_KERNEL_KB_FIELD_NUMBER = 14;
    public static final int USED_PSS_KB_FIELD_NUMBER = 13;
    public static final int ZRAM_PHYSICAL_USED_IN_SWAP_KB_FIELD_NUMBER = 17;
    private Internal.ProtobufList<AppData> appProcesses_ = emptyProtobufList();
    private int bitField0_;
    private long cachedKernelKb_ = 0;
    private long cachedPssKb_ = 0;
    private long elapsedRealtimeMs_ = 0;
    private long freeKb_ = 0;
    private boolean isHighEndGfx_ = false;
    private boolean isLowRamDevice_ = false;
    private long ksmSharedKb_ = 0;
    private long ksmSharingKb_ = 0;
    private long ksmUnsharedKb_ = 0;
    private long ksmVolatileKb_ = 0;
    private long lostRamKb_ = 0;
    private Internal.ProtobufList<ProcessMemory> nativeProcesses_ = emptyProtobufList();
    private long oomKb_ = 0;
    private long restoreLimitKb_ = 0;
    private int status_ = 0;
    private Internal.ProtobufList<MemItem> totalPssByCategory_ = emptyProtobufList();
    private Internal.ProtobufList<MemItem> totalPssByOomAdjustment_ = emptyProtobufList();
    private Internal.ProtobufList<MemItem> totalPssByProcess_ = emptyProtobufList();
    private long totalRamKb_ = 0;
    private long totalZramKb_ = 0;
    private long totalZramSwapKb_ = 0;
    private int tuningLargeMb_ = 0;
    private int tuningMb_ = 0;
    private long uptimeDurationMs_ = 0;
    private long usedKernelKb_ = 0;
    private long usedPssKb_ = 0;
    private long zramPhysicalUsedInSwapKb_ = 0;

    public interface AppDataOrBuilder extends MessageLiteOrBuilder {
        String getAssetAllocations();

        ByteString getAssetAllocationsBytes();

        AppData.ObjectStats getObjects();

        ProcessMemory getProcessMemory();

        AppData.SqlStats getSql();

        String getUnreachableMemory();

        ByteString getUnreachableMemoryBytes();

        boolean hasAssetAllocations();

        boolean hasObjects();

        boolean hasProcessMemory();

        boolean hasSql();

        boolean hasUnreachableMemory();
    }

    public interface MemItemOrBuilder extends MessageLiteOrBuilder {
        boolean getHasActivities();

        int getId();

        boolean getIsProc();

        String getLabel();

        ByteString getLabelBytes();

        long getPssKb();

        MemItem getSubItems(int i);

        int getSubItemsCount();

        List<MemItem> getSubItemsList();

        long getSwapPssKb();

        String getTag();

        ByteString getTagBytes();

        boolean hasHasActivities();

        boolean hasId();

        boolean hasIsProc();

        boolean hasLabel();

        boolean hasPssKb();

        boolean hasSwapPssKb();

        boolean hasTag();
    }

    public interface ProcessMemoryOrBuilder extends MessageLiteOrBuilder {
        ProcessMemory.AppSummary getAppSummary();

        ProcessMemory.MemoryInfo getDalvikDetails(int i);

        int getDalvikDetailsCount();

        List<ProcessMemory.MemoryInfo> getDalvikDetailsList();

        ProcessMemory.HeapInfo getDalvikHeap();

        ProcessMemory.HeapInfo getNativeHeap();

        ProcessMemory.MemoryInfo getOtherHeaps(int i);

        int getOtherHeapsCount();

        List<ProcessMemory.MemoryInfo> getOtherHeapsList();

        int getPid();

        String getProcessName();

        ByteString getProcessNameBytes();

        ProcessMemory.HeapInfo getTotalHeap();

        ProcessMemory.MemoryInfo getUnknownHeap();

        boolean hasAppSummary();

        boolean hasDalvikHeap();

        boolean hasNativeHeap();

        boolean hasPid();

        boolean hasProcessName();

        boolean hasTotalHeap();

        boolean hasUnknownHeap();
    }

    private MemInfoDumpProto() {
    }

    public static final class ProcessMemory extends GeneratedMessageLite<ProcessMemory, Builder> implements ProcessMemoryOrBuilder {
        public static final int APP_SUMMARY_FIELD_NUMBER = 9;
        public static final int DALVIK_DETAILS_FIELD_NUMBER = 8;
        public static final int DALVIK_HEAP_FIELD_NUMBER = 4;
        private static final ProcessMemory DEFAULT_INSTANCE = new ProcessMemory();
        public static final int NATIVE_HEAP_FIELD_NUMBER = 3;
        public static final int OTHER_HEAPS_FIELD_NUMBER = 5;
        private static volatile Parser<ProcessMemory> PARSER = null;
        public static final int PID_FIELD_NUMBER = 1;
        public static final int PROCESS_NAME_FIELD_NUMBER = 2;
        public static final int TOTAL_HEAP_FIELD_NUMBER = 7;
        public static final int UNKNOWN_HEAP_FIELD_NUMBER = 6;
        private AppSummary appSummary_;
        private int bitField0_;
        private Internal.ProtobufList<MemoryInfo> dalvikDetails_ = emptyProtobufList();
        private HeapInfo dalvikHeap_;
        private HeapInfo nativeHeap_;
        private Internal.ProtobufList<MemoryInfo> otherHeaps_ = emptyProtobufList();
        private int pid_ = 0;
        private String processName_ = "";
        private HeapInfo totalHeap_;
        private MemoryInfo unknownHeap_;

        public interface AppSummaryOrBuilder extends MessageLiteOrBuilder {
            int getCodePssKb();

            int getGraphicsPssKb();

            int getJavaHeapPssKb();

            int getNativeHeapPssKb();

            int getPrivateOtherPssKb();

            int getStackPssKb();

            int getSystemPssKb();

            AppSummary.TotalSwapCase getTotalSwapCase();

            int getTotalSwapKb();

            int getTotalSwapPss();

            boolean hasCodePssKb();

            boolean hasGraphicsPssKb();

            boolean hasJavaHeapPssKb();

            boolean hasNativeHeapPssKb();

            boolean hasPrivateOtherPssKb();

            boolean hasStackPssKb();

            boolean hasSystemPssKb();

            boolean hasTotalSwapKb();

            boolean hasTotalSwapPss();
        }

        public interface HeapInfoOrBuilder extends MessageLiteOrBuilder {
            int getHeapAllocKb();

            int getHeapFreeKb();

            int getHeapSizeKb();

            MemoryInfo getMemInfo();

            boolean hasHeapAllocKb();

            boolean hasHeapFreeKb();

            boolean hasHeapSizeKb();

            boolean hasMemInfo();
        }

        public interface MemoryInfoOrBuilder extends MessageLiteOrBuilder {
            int getCleanPssKb();

            MemoryInfo.DirtySwapCase getDirtySwapCase();

            int getDirtySwapKb();

            int getDirtySwapPssKb();

            String getName();

            ByteString getNameBytes();

            int getPrivateCleanKb();

            int getPrivateDirtyKb();

            int getSharedCleanKb();

            int getSharedDirtyKb();

            int getTotalPssKb();

            boolean hasCleanPssKb();

            boolean hasDirtySwapKb();

            boolean hasDirtySwapPssKb();

            boolean hasName();

            boolean hasPrivateCleanKb();

            boolean hasPrivateDirtyKb();

            boolean hasSharedCleanKb();

            boolean hasSharedDirtyKb();

            boolean hasTotalPssKb();
        }

        private ProcessMemory() {
        }

        public static final class MemoryInfo extends GeneratedMessageLite<MemoryInfo, Builder> implements MemoryInfoOrBuilder {
            public static final int CLEAN_PSS_KB_FIELD_NUMBER = 3;
            private static final MemoryInfo DEFAULT_INSTANCE = new MemoryInfo();
            public static final int DIRTY_SWAP_KB_FIELD_NUMBER = 8;
            public static final int DIRTY_SWAP_PSS_KB_FIELD_NUMBER = 9;
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<MemoryInfo> PARSER = null;
            public static final int PRIVATE_CLEAN_KB_FIELD_NUMBER = 7;
            public static final int PRIVATE_DIRTY_KB_FIELD_NUMBER = 5;
            public static final int SHARED_CLEAN_KB_FIELD_NUMBER = 6;
            public static final int SHARED_DIRTY_KB_FIELD_NUMBER = 4;
            public static final int TOTAL_PSS_KB_FIELD_NUMBER = 2;
            private int bitField0_;
            private int cleanPssKb_ = 0;
            private int dirtySwapCase_ = 0;
            private Object dirtySwap_;
            private String name_ = "";
            private int privateCleanKb_ = 0;
            private int privateDirtyKb_ = 0;
            private int sharedCleanKb_ = 0;
            private int sharedDirtyKb_ = 0;
            private int totalPssKb_ = 0;

            private MemoryInfo() {
            }

            public enum DirtySwapCase implements Internal.EnumLite {
                DIRTY_SWAP_KB(8),
                DIRTY_SWAP_PSS_KB(9),
                DIRTYSWAP_NOT_SET(0);
                
                private final int value;

                private DirtySwapCase(int value2) {
                    this.value = value2;
                }

                @Deprecated
                public static DirtySwapCase valueOf(int value2) {
                    return forNumber(value2);
                }

                public static DirtySwapCase forNumber(int value2) {
                    if (value2 == 0) {
                        return DIRTYSWAP_NOT_SET;
                    }
                    if (value2 == 8) {
                        return DIRTY_SWAP_KB;
                    }
                    if (value2 != 9) {
                        return null;
                    }
                    return DIRTY_SWAP_PSS_KB;
                }

                @Override // com.google.protobuf.Internal.EnumLite
                public int getNumber() {
                    return this.value;
                }
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public DirtySwapCase getDirtySwapCase() {
                return DirtySwapCase.forNumber(this.dirtySwapCase_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDirtySwap() {
                this.dirtySwapCase_ = 0;
                this.dirtySwap_ = null;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setName(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearName() {
                this.bitField0_ &= -2;
                this.name_ = getDefaultInstance().getName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasTotalPssKb() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getTotalPssKb() {
                return this.totalPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTotalPssKb(int value) {
                this.bitField0_ |= 2;
                this.totalPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTotalPssKb() {
                this.bitField0_ &= -3;
                this.totalPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasCleanPssKb() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getCleanPssKb() {
                return this.cleanPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCleanPssKb(int value) {
                this.bitField0_ |= 4;
                this.cleanPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCleanPssKb() {
                this.bitField0_ &= -5;
                this.cleanPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasSharedDirtyKb() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getSharedDirtyKb() {
                return this.sharedDirtyKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSharedDirtyKb(int value) {
                this.bitField0_ |= 8;
                this.sharedDirtyKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSharedDirtyKb() {
                this.bitField0_ &= -9;
                this.sharedDirtyKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasPrivateDirtyKb() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getPrivateDirtyKb() {
                return this.privateDirtyKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPrivateDirtyKb(int value) {
                this.bitField0_ |= 16;
                this.privateDirtyKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPrivateDirtyKb() {
                this.bitField0_ &= -17;
                this.privateDirtyKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasSharedCleanKb() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getSharedCleanKb() {
                return this.sharedCleanKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSharedCleanKb(int value) {
                this.bitField0_ |= 32;
                this.sharedCleanKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSharedCleanKb() {
                this.bitField0_ &= -33;
                this.sharedCleanKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasPrivateCleanKb() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getPrivateCleanKb() {
                return this.privateCleanKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPrivateCleanKb(int value) {
                this.bitField0_ |= 64;
                this.privateCleanKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPrivateCleanKb() {
                this.bitField0_ &= -65;
                this.privateCleanKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasDirtySwapKb() {
                return this.dirtySwapCase_ == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getDirtySwapKb() {
                if (this.dirtySwapCase_ == 8) {
                    return ((Integer) this.dirtySwap_).intValue();
                }
                return 0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDirtySwapKb(int value) {
                this.dirtySwapCase_ = 8;
                this.dirtySwap_ = Integer.valueOf(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDirtySwapKb() {
                if (this.dirtySwapCase_ == 8) {
                    this.dirtySwapCase_ = 0;
                    this.dirtySwap_ = null;
                }
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public boolean hasDirtySwapPssKb() {
                return this.dirtySwapCase_ == 9;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
            public int getDirtySwapPssKb() {
                if (this.dirtySwapCase_ == 9) {
                    return ((Integer) this.dirtySwap_).intValue();
                }
                return 0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDirtySwapPssKb(int value) {
                this.dirtySwapCase_ = 9;
                this.dirtySwap_ = Integer.valueOf(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDirtySwapPssKb() {
                if (this.dirtySwapCase_ == 9) {
                    this.dirtySwapCase_ = 0;
                    this.dirtySwap_ = null;
                }
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getName());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.totalPssKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.cleanPssKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.sharedDirtyKb_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeInt32(5, this.privateDirtyKb_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt32(6, this.sharedCleanKb_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeInt32(7, this.privateCleanKb_);
                }
                if (this.dirtySwapCase_ == 8) {
                    output.writeInt32(8, ((Integer) this.dirtySwap_).intValue());
                }
                if (this.dirtySwapCase_ == 9) {
                    output.writeInt32(9, ((Integer) this.dirtySwap_).intValue());
                }
                this.unknownFields.writeTo(output);
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.totalPssKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.cleanPssKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.sharedDirtyKb_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeInt32Size(5, this.privateDirtyKb_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt32Size(6, this.sharedCleanKb_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeInt32Size(7, this.privateCleanKb_);
                }
                if (this.dirtySwapCase_ == 8) {
                    size2 += CodedOutputStream.computeInt32Size(8, ((Integer) this.dirtySwap_).intValue());
                }
                if (this.dirtySwapCase_ == 9) {
                    size2 += CodedOutputStream.computeInt32Size(9, ((Integer) this.dirtySwap_).intValue());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static MemoryInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static MemoryInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static MemoryInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static MemoryInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static MemoryInfo parseFrom(InputStream input) throws IOException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static MemoryInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static MemoryInfo parseDelimitedFrom(InputStream input) throws IOException {
                return (MemoryInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static MemoryInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MemoryInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static MemoryInfo parseFrom(CodedInputStream input) throws IOException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static MemoryInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MemoryInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(MemoryInfo prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<MemoryInfo, Builder> implements MemoryInfoOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(MemoryInfo.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public DirtySwapCase getDirtySwapCase() {
                    return ((MemoryInfo) this.instance).getDirtySwapCase();
                }

                public Builder clearDirtySwap() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearDirtySwap();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasName() {
                    return ((MemoryInfo) this.instance).hasName();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public String getName() {
                    return ((MemoryInfo) this.instance).getName();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public ByteString getNameBytes() {
                    return ((MemoryInfo) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setNameBytes(value);
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasTotalPssKb() {
                    return ((MemoryInfo) this.instance).hasTotalPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getTotalPssKb() {
                    return ((MemoryInfo) this.instance).getTotalPssKb();
                }

                public Builder setTotalPssKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setTotalPssKb(value);
                    return this;
                }

                public Builder clearTotalPssKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearTotalPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasCleanPssKb() {
                    return ((MemoryInfo) this.instance).hasCleanPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getCleanPssKb() {
                    return ((MemoryInfo) this.instance).getCleanPssKb();
                }

                public Builder setCleanPssKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setCleanPssKb(value);
                    return this;
                }

                public Builder clearCleanPssKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearCleanPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasSharedDirtyKb() {
                    return ((MemoryInfo) this.instance).hasSharedDirtyKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getSharedDirtyKb() {
                    return ((MemoryInfo) this.instance).getSharedDirtyKb();
                }

                public Builder setSharedDirtyKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setSharedDirtyKb(value);
                    return this;
                }

                public Builder clearSharedDirtyKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearSharedDirtyKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasPrivateDirtyKb() {
                    return ((MemoryInfo) this.instance).hasPrivateDirtyKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getPrivateDirtyKb() {
                    return ((MemoryInfo) this.instance).getPrivateDirtyKb();
                }

                public Builder setPrivateDirtyKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setPrivateDirtyKb(value);
                    return this;
                }

                public Builder clearPrivateDirtyKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearPrivateDirtyKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasSharedCleanKb() {
                    return ((MemoryInfo) this.instance).hasSharedCleanKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getSharedCleanKb() {
                    return ((MemoryInfo) this.instance).getSharedCleanKb();
                }

                public Builder setSharedCleanKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setSharedCleanKb(value);
                    return this;
                }

                public Builder clearSharedCleanKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearSharedCleanKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasPrivateCleanKb() {
                    return ((MemoryInfo) this.instance).hasPrivateCleanKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getPrivateCleanKb() {
                    return ((MemoryInfo) this.instance).getPrivateCleanKb();
                }

                public Builder setPrivateCleanKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setPrivateCleanKb(value);
                    return this;
                }

                public Builder clearPrivateCleanKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearPrivateCleanKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasDirtySwapKb() {
                    return ((MemoryInfo) this.instance).hasDirtySwapKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getDirtySwapKb() {
                    return ((MemoryInfo) this.instance).getDirtySwapKb();
                }

                public Builder setDirtySwapKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setDirtySwapKb(value);
                    return this;
                }

                public Builder clearDirtySwapKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearDirtySwapKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public boolean hasDirtySwapPssKb() {
                    return ((MemoryInfo) this.instance).hasDirtySwapPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.MemoryInfoOrBuilder
                public int getDirtySwapPssKb() {
                    return ((MemoryInfo) this.instance).getDirtySwapPssKb();
                }

                public Builder setDirtySwapPssKb(int value) {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).setDirtySwapPssKb(value);
                    return this;
                }

                public Builder clearDirtySwapPssKb() {
                    copyOnWrite();
                    ((MemoryInfo) this.instance).clearDirtySwapPssKb();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                boolean z = true;
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new MemoryInfo();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        MemoryInfo other = (MemoryInfo) arg1;
                        this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                        this.totalPssKb_ = visitor.visitInt(hasTotalPssKb(), this.totalPssKb_, other.hasTotalPssKb(), other.totalPssKb_);
                        this.cleanPssKb_ = visitor.visitInt(hasCleanPssKb(), this.cleanPssKb_, other.hasCleanPssKb(), other.cleanPssKb_);
                        this.sharedDirtyKb_ = visitor.visitInt(hasSharedDirtyKb(), this.sharedDirtyKb_, other.hasSharedDirtyKb(), other.sharedDirtyKb_);
                        this.privateDirtyKb_ = visitor.visitInt(hasPrivateDirtyKb(), this.privateDirtyKb_, other.hasPrivateDirtyKb(), other.privateDirtyKb_);
                        this.sharedCleanKb_ = visitor.visitInt(hasSharedCleanKb(), this.sharedCleanKb_, other.hasSharedCleanKb(), other.sharedCleanKb_);
                        this.privateCleanKb_ = visitor.visitInt(hasPrivateCleanKb(), this.privateCleanKb_, other.hasPrivateCleanKb(), other.privateCleanKb_);
                        int i = AnonymousClass1.$SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$MemoryInfo$DirtySwapCase[other.getDirtySwapCase().ordinal()];
                        if (i == 1) {
                            if (this.dirtySwapCase_ != 8) {
                                z = false;
                            }
                            this.dirtySwap_ = visitor.visitOneofInt(z, this.dirtySwap_, other.dirtySwap_);
                        } else if (i == 2) {
                            if (this.dirtySwapCase_ != 9) {
                                z = false;
                            }
                            this.dirtySwap_ = visitor.visitOneofInt(z, this.dirtySwap_, other.dirtySwap_);
                        } else if (i == 3) {
                            if (this.dirtySwapCase_ == 0) {
                                z = false;
                            }
                            visitor.visitOneofNotSet(z);
                        }
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            int i2 = other.dirtySwapCase_;
                            if (i2 != 0) {
                                this.dirtySwapCase_ = i2;
                            }
                            this.bitField0_ |= other.bitField0_;
                        }
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                        boolean done = false;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 10) {
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.name_ = s;
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.totalPssKb_ = input.readInt32();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.cleanPssKb_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.sharedDirtyKb_ = input.readInt32();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.privateDirtyKb_ = input.readInt32();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.sharedCleanKb_ = input.readInt32();
                                } else if (tag == 56) {
                                    this.bitField0_ |= 64;
                                    this.privateCleanKb_ = input.readInt32();
                                } else if (tag == 64) {
                                    this.dirtySwapCase_ = 8;
                                    this.dirtySwap_ = Integer.valueOf(input.readInt32());
                                } else if (tag == 72) {
                                    this.dirtySwapCase_ = 9;
                                    this.dirtySwap_ = Integer.valueOf(input.readInt32());
                                } else if (!parseUnknownField(tag, input)) {
                                    done = true;
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                        break;
                    case GET_DEFAULT_INSTANCE:
                        break;
                    case GET_PARSER:
                        if (PARSER == null) {
                            synchronized (MemoryInfo.class) {
                                if (PARSER == null) {
                                    PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                }
                            }
                        }
                        return PARSER;
                    default:
                        throw new UnsupportedOperationException();
                }
                return DEFAULT_INSTANCE;
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            public static MemoryInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<MemoryInfo> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class HeapInfo extends GeneratedMessageLite<HeapInfo, Builder> implements HeapInfoOrBuilder {
            private static final HeapInfo DEFAULT_INSTANCE = new HeapInfo();
            public static final int HEAP_ALLOC_KB_FIELD_NUMBER = 3;
            public static final int HEAP_FREE_KB_FIELD_NUMBER = 4;
            public static final int HEAP_SIZE_KB_FIELD_NUMBER = 2;
            public static final int MEM_INFO_FIELD_NUMBER = 1;
            private static volatile Parser<HeapInfo> PARSER;
            private int bitField0_;
            private int heapAllocKb_ = 0;
            private int heapFreeKb_ = 0;
            private int heapSizeKb_ = 0;
            private MemoryInfo memInfo_;

            private HeapInfo() {
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public boolean hasMemInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public MemoryInfo getMemInfo() {
                MemoryInfo memoryInfo = this.memInfo_;
                return memoryInfo == null ? MemoryInfo.getDefaultInstance() : memoryInfo;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMemInfo(MemoryInfo value) {
                if (value != null) {
                    this.memInfo_ = value;
                    this.bitField0_ |= 1;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMemInfo(MemoryInfo.Builder builderForValue) {
                this.memInfo_ = (MemoryInfo) builderForValue.build();
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void mergeMemInfo(MemoryInfo value) {
                MemoryInfo memoryInfo = this.memInfo_;
                if (memoryInfo == null || memoryInfo == MemoryInfo.getDefaultInstance()) {
                    this.memInfo_ = value;
                } else {
                    this.memInfo_ = (MemoryInfo) ((MemoryInfo.Builder) MemoryInfo.newBuilder(this.memInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearMemInfo() {
                this.memInfo_ = null;
                this.bitField0_ &= -2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public boolean hasHeapSizeKb() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public int getHeapSizeKb() {
                return this.heapSizeKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHeapSizeKb(int value) {
                this.bitField0_ |= 2;
                this.heapSizeKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHeapSizeKb() {
                this.bitField0_ &= -3;
                this.heapSizeKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public boolean hasHeapAllocKb() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public int getHeapAllocKb() {
                return this.heapAllocKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHeapAllocKb(int value) {
                this.bitField0_ |= 4;
                this.heapAllocKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHeapAllocKb() {
                this.bitField0_ &= -5;
                this.heapAllocKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public boolean hasHeapFreeKb() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
            public int getHeapFreeKb() {
                return this.heapFreeKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setHeapFreeKb(int value) {
                this.bitField0_ |= 8;
                this.heapFreeKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearHeapFreeKb() {
                this.bitField0_ &= -9;
                this.heapFreeKb_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeMessage(1, getMemInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.heapSizeKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.heapAllocKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.heapFreeKb_);
                }
                this.unknownFields.writeTo(output);
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeMessageSize(1, getMemInfo());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.heapSizeKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.heapAllocKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.heapFreeKb_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static HeapInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static HeapInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static HeapInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static HeapInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static HeapInfo parseFrom(InputStream input) throws IOException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static HeapInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static HeapInfo parseDelimitedFrom(InputStream input) throws IOException {
                return (HeapInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static HeapInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (HeapInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static HeapInfo parseFrom(CodedInputStream input) throws IOException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static HeapInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (HeapInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(HeapInfo prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<HeapInfo, Builder> implements HeapInfoOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(HeapInfo.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public boolean hasMemInfo() {
                    return ((HeapInfo) this.instance).hasMemInfo();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public MemoryInfo getMemInfo() {
                    return ((HeapInfo) this.instance).getMemInfo();
                }

                public Builder setMemInfo(MemoryInfo value) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).setMemInfo((HeapInfo) value);
                    return this;
                }

                public Builder setMemInfo(MemoryInfo.Builder builderForValue) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).setMemInfo((HeapInfo) builderForValue);
                    return this;
                }

                public Builder mergeMemInfo(MemoryInfo value) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).mergeMemInfo(value);
                    return this;
                }

                public Builder clearMemInfo() {
                    copyOnWrite();
                    ((HeapInfo) this.instance).clearMemInfo();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public boolean hasHeapSizeKb() {
                    return ((HeapInfo) this.instance).hasHeapSizeKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public int getHeapSizeKb() {
                    return ((HeapInfo) this.instance).getHeapSizeKb();
                }

                public Builder setHeapSizeKb(int value) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).setHeapSizeKb(value);
                    return this;
                }

                public Builder clearHeapSizeKb() {
                    copyOnWrite();
                    ((HeapInfo) this.instance).clearHeapSizeKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public boolean hasHeapAllocKb() {
                    return ((HeapInfo) this.instance).hasHeapAllocKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public int getHeapAllocKb() {
                    return ((HeapInfo) this.instance).getHeapAllocKb();
                }

                public Builder setHeapAllocKb(int value) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).setHeapAllocKb(value);
                    return this;
                }

                public Builder clearHeapAllocKb() {
                    copyOnWrite();
                    ((HeapInfo) this.instance).clearHeapAllocKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public boolean hasHeapFreeKb() {
                    return ((HeapInfo) this.instance).hasHeapFreeKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.HeapInfoOrBuilder
                public int getHeapFreeKb() {
                    return ((HeapInfo) this.instance).getHeapFreeKb();
                }

                public Builder setHeapFreeKb(int value) {
                    copyOnWrite();
                    ((HeapInfo) this.instance).setHeapFreeKb(value);
                    return this;
                }

                public Builder clearHeapFreeKb() {
                    copyOnWrite();
                    ((HeapInfo) this.instance).clearHeapFreeKb();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new HeapInfo();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        HeapInfo other = (HeapInfo) arg1;
                        this.memInfo_ = (MemoryInfo) visitor.visitMessage(this.memInfo_, other.memInfo_);
                        this.heapSizeKb_ = visitor.visitInt(hasHeapSizeKb(), this.heapSizeKb_, other.hasHeapSizeKb(), other.heapSizeKb_);
                        this.heapAllocKb_ = visitor.visitInt(hasHeapAllocKb(), this.heapAllocKb_, other.hasHeapAllocKb(), other.heapAllocKb_);
                        this.heapFreeKb_ = visitor.visitInt(hasHeapFreeKb(), this.heapFreeKb_, other.hasHeapFreeKb(), other.heapFreeKb_);
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            this.bitField0_ |= other.bitField0_;
                        }
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                        boolean done = false;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 10) {
                                    MemoryInfo.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (MemoryInfo.Builder) this.memInfo_.toBuilder();
                                    }
                                    this.memInfo_ = (MemoryInfo) input.readMessage(MemoryInfo.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.memInfo_);
                                        this.memInfo_ = (MemoryInfo) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.heapSizeKb_ = input.readInt32();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.heapAllocKb_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.heapFreeKb_ = input.readInt32();
                                } else if (!parseUnknownField(tag, input)) {
                                    done = true;
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                        break;
                    case GET_DEFAULT_INSTANCE:
                        break;
                    case GET_PARSER:
                        if (PARSER == null) {
                            synchronized (HeapInfo.class) {
                                if (PARSER == null) {
                                    PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                }
                            }
                        }
                        return PARSER;
                    default:
                        throw new UnsupportedOperationException();
                }
                return DEFAULT_INSTANCE;
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            public static HeapInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<HeapInfo> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class AppSummary extends GeneratedMessageLite<AppSummary, Builder> implements AppSummaryOrBuilder {
            public static final int CODE_PSS_KB_FIELD_NUMBER = 3;
            private static final AppSummary DEFAULT_INSTANCE = new AppSummary();
            public static final int GRAPHICS_PSS_KB_FIELD_NUMBER = 5;
            public static final int JAVA_HEAP_PSS_KB_FIELD_NUMBER = 1;
            public static final int NATIVE_HEAP_PSS_KB_FIELD_NUMBER = 2;
            private static volatile Parser<AppSummary> PARSER = null;
            public static final int PRIVATE_OTHER_PSS_KB_FIELD_NUMBER = 6;
            public static final int STACK_PSS_KB_FIELD_NUMBER = 4;
            public static final int SYSTEM_PSS_KB_FIELD_NUMBER = 7;
            public static final int TOTAL_SWAP_KB_FIELD_NUMBER = 9;
            public static final int TOTAL_SWAP_PSS_FIELD_NUMBER = 8;
            private int bitField0_;
            private int codePssKb_ = 0;
            private int graphicsPssKb_ = 0;
            private int javaHeapPssKb_ = 0;
            private int nativeHeapPssKb_ = 0;
            private int privateOtherPssKb_ = 0;
            private int stackPssKb_ = 0;
            private int systemPssKb_ = 0;
            private int totalSwapCase_ = 0;
            private Object totalSwap_;

            private AppSummary() {
            }

            public enum TotalSwapCase implements Internal.EnumLite {
                TOTAL_SWAP_PSS(8),
                TOTAL_SWAP_KB(9),
                TOTALSWAP_NOT_SET(0);
                
                private final int value;

                private TotalSwapCase(int value2) {
                    this.value = value2;
                }

                @Deprecated
                public static TotalSwapCase valueOf(int value2) {
                    return forNumber(value2);
                }

                public static TotalSwapCase forNumber(int value2) {
                    if (value2 == 0) {
                        return TOTALSWAP_NOT_SET;
                    }
                    if (value2 == 8) {
                        return TOTAL_SWAP_PSS;
                    }
                    if (value2 != 9) {
                        return null;
                    }
                    return TOTAL_SWAP_KB;
                }

                @Override // com.google.protobuf.Internal.EnumLite
                public int getNumber() {
                    return this.value;
                }
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public TotalSwapCase getTotalSwapCase() {
                return TotalSwapCase.forNumber(this.totalSwapCase_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTotalSwap() {
                this.totalSwapCase_ = 0;
                this.totalSwap_ = null;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasJavaHeapPssKb() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getJavaHeapPssKb() {
                return this.javaHeapPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setJavaHeapPssKb(int value) {
                this.bitField0_ |= 1;
                this.javaHeapPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearJavaHeapPssKb() {
                this.bitField0_ &= -2;
                this.javaHeapPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasNativeHeapPssKb() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getNativeHeapPssKb() {
                return this.nativeHeapPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setNativeHeapPssKb(int value) {
                this.bitField0_ |= 2;
                this.nativeHeapPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearNativeHeapPssKb() {
                this.bitField0_ &= -3;
                this.nativeHeapPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasCodePssKb() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getCodePssKb() {
                return this.codePssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCodePssKb(int value) {
                this.bitField0_ |= 4;
                this.codePssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCodePssKb() {
                this.bitField0_ &= -5;
                this.codePssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasStackPssKb() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getStackPssKb() {
                return this.stackPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStackPssKb(int value) {
                this.bitField0_ |= 8;
                this.stackPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStackPssKb() {
                this.bitField0_ &= -9;
                this.stackPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasGraphicsPssKb() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getGraphicsPssKb() {
                return this.graphicsPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setGraphicsPssKb(int value) {
                this.bitField0_ |= 16;
                this.graphicsPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearGraphicsPssKb() {
                this.bitField0_ &= -17;
                this.graphicsPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasPrivateOtherPssKb() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getPrivateOtherPssKb() {
                return this.privateOtherPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPrivateOtherPssKb(int value) {
                this.bitField0_ |= 32;
                this.privateOtherPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPrivateOtherPssKb() {
                this.bitField0_ &= -33;
                this.privateOtherPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasSystemPssKb() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getSystemPssKb() {
                return this.systemPssKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setSystemPssKb(int value) {
                this.bitField0_ |= 64;
                this.systemPssKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearSystemPssKb() {
                this.bitField0_ &= -65;
                this.systemPssKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasTotalSwapPss() {
                return this.totalSwapCase_ == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getTotalSwapPss() {
                if (this.totalSwapCase_ == 8) {
                    return ((Integer) this.totalSwap_).intValue();
                }
                return 0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTotalSwapPss(int value) {
                this.totalSwapCase_ = 8;
                this.totalSwap_ = Integer.valueOf(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTotalSwapPss() {
                if (this.totalSwapCase_ == 8) {
                    this.totalSwapCase_ = 0;
                    this.totalSwap_ = null;
                }
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public boolean hasTotalSwapKb() {
                return this.totalSwapCase_ == 9;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
            public int getTotalSwapKb() {
                if (this.totalSwapCase_ == 9) {
                    return ((Integer) this.totalSwap_).intValue();
                }
                return 0;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTotalSwapKb(int value) {
                this.totalSwapCase_ = 9;
                this.totalSwap_ = Integer.valueOf(value);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTotalSwapKb() {
                if (this.totalSwapCase_ == 9) {
                    this.totalSwapCase_ = 0;
                    this.totalSwap_ = null;
                }
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.javaHeapPssKb_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.nativeHeapPssKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.codePssKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.stackPssKb_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeInt32(5, this.graphicsPssKb_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt32(6, this.privateOtherPssKb_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeInt32(7, this.systemPssKb_);
                }
                if (this.totalSwapCase_ == 8) {
                    output.writeInt32(8, ((Integer) this.totalSwap_).intValue());
                }
                if (this.totalSwapCase_ == 9) {
                    output.writeInt32(9, ((Integer) this.totalSwap_).intValue());
                }
                this.unknownFields.writeTo(output);
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.javaHeapPssKb_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.nativeHeapPssKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.codePssKb_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.stackPssKb_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeInt32Size(5, this.graphicsPssKb_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt32Size(6, this.privateOtherPssKb_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeInt32Size(7, this.systemPssKb_);
                }
                if (this.totalSwapCase_ == 8) {
                    size2 += CodedOutputStream.computeInt32Size(8, ((Integer) this.totalSwap_).intValue());
                }
                if (this.totalSwapCase_ == 9) {
                    size2 += CodedOutputStream.computeInt32Size(9, ((Integer) this.totalSwap_).intValue());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static AppSummary parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static AppSummary parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static AppSummary parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static AppSummary parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static AppSummary parseFrom(InputStream input) throws IOException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static AppSummary parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static AppSummary parseDelimitedFrom(InputStream input) throws IOException {
                return (AppSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static AppSummary parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AppSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static AppSummary parseFrom(CodedInputStream input) throws IOException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static AppSummary parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (AppSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(AppSummary prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<AppSummary, Builder> implements AppSummaryOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(AppSummary.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public TotalSwapCase getTotalSwapCase() {
                    return ((AppSummary) this.instance).getTotalSwapCase();
                }

                public Builder clearTotalSwap() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearTotalSwap();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasJavaHeapPssKb() {
                    return ((AppSummary) this.instance).hasJavaHeapPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getJavaHeapPssKb() {
                    return ((AppSummary) this.instance).getJavaHeapPssKb();
                }

                public Builder setJavaHeapPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setJavaHeapPssKb(value);
                    return this;
                }

                public Builder clearJavaHeapPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearJavaHeapPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasNativeHeapPssKb() {
                    return ((AppSummary) this.instance).hasNativeHeapPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getNativeHeapPssKb() {
                    return ((AppSummary) this.instance).getNativeHeapPssKb();
                }

                public Builder setNativeHeapPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setNativeHeapPssKb(value);
                    return this;
                }

                public Builder clearNativeHeapPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearNativeHeapPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasCodePssKb() {
                    return ((AppSummary) this.instance).hasCodePssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getCodePssKb() {
                    return ((AppSummary) this.instance).getCodePssKb();
                }

                public Builder setCodePssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setCodePssKb(value);
                    return this;
                }

                public Builder clearCodePssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearCodePssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasStackPssKb() {
                    return ((AppSummary) this.instance).hasStackPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getStackPssKb() {
                    return ((AppSummary) this.instance).getStackPssKb();
                }

                public Builder setStackPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setStackPssKb(value);
                    return this;
                }

                public Builder clearStackPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearStackPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasGraphicsPssKb() {
                    return ((AppSummary) this.instance).hasGraphicsPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getGraphicsPssKb() {
                    return ((AppSummary) this.instance).getGraphicsPssKb();
                }

                public Builder setGraphicsPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setGraphicsPssKb(value);
                    return this;
                }

                public Builder clearGraphicsPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearGraphicsPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasPrivateOtherPssKb() {
                    return ((AppSummary) this.instance).hasPrivateOtherPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getPrivateOtherPssKb() {
                    return ((AppSummary) this.instance).getPrivateOtherPssKb();
                }

                public Builder setPrivateOtherPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setPrivateOtherPssKb(value);
                    return this;
                }

                public Builder clearPrivateOtherPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearPrivateOtherPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasSystemPssKb() {
                    return ((AppSummary) this.instance).hasSystemPssKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getSystemPssKb() {
                    return ((AppSummary) this.instance).getSystemPssKb();
                }

                public Builder setSystemPssKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setSystemPssKb(value);
                    return this;
                }

                public Builder clearSystemPssKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearSystemPssKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasTotalSwapPss() {
                    return ((AppSummary) this.instance).hasTotalSwapPss();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getTotalSwapPss() {
                    return ((AppSummary) this.instance).getTotalSwapPss();
                }

                public Builder setTotalSwapPss(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setTotalSwapPss(value);
                    return this;
                }

                public Builder clearTotalSwapPss() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearTotalSwapPss();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public boolean hasTotalSwapKb() {
                    return ((AppSummary) this.instance).hasTotalSwapKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.ProcessMemory.AppSummaryOrBuilder
                public int getTotalSwapKb() {
                    return ((AppSummary) this.instance).getTotalSwapKb();
                }

                public Builder setTotalSwapKb(int value) {
                    copyOnWrite();
                    ((AppSummary) this.instance).setTotalSwapKb(value);
                    return this;
                }

                public Builder clearTotalSwapKb() {
                    copyOnWrite();
                    ((AppSummary) this.instance).clearTotalSwapKb();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                boolean z = true;
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new AppSummary();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        AppSummary other = (AppSummary) arg1;
                        this.javaHeapPssKb_ = visitor.visitInt(hasJavaHeapPssKb(), this.javaHeapPssKb_, other.hasJavaHeapPssKb(), other.javaHeapPssKb_);
                        this.nativeHeapPssKb_ = visitor.visitInt(hasNativeHeapPssKb(), this.nativeHeapPssKb_, other.hasNativeHeapPssKb(), other.nativeHeapPssKb_);
                        this.codePssKb_ = visitor.visitInt(hasCodePssKb(), this.codePssKb_, other.hasCodePssKb(), other.codePssKb_);
                        this.stackPssKb_ = visitor.visitInt(hasStackPssKb(), this.stackPssKb_, other.hasStackPssKb(), other.stackPssKb_);
                        this.graphicsPssKb_ = visitor.visitInt(hasGraphicsPssKb(), this.graphicsPssKb_, other.hasGraphicsPssKb(), other.graphicsPssKb_);
                        this.privateOtherPssKb_ = visitor.visitInt(hasPrivateOtherPssKb(), this.privateOtherPssKb_, other.hasPrivateOtherPssKb(), other.privateOtherPssKb_);
                        this.systemPssKb_ = visitor.visitInt(hasSystemPssKb(), this.systemPssKb_, other.hasSystemPssKb(), other.systemPssKb_);
                        int i = AnonymousClass1.$SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$AppSummary$TotalSwapCase[other.getTotalSwapCase().ordinal()];
                        if (i == 1) {
                            if (this.totalSwapCase_ != 8) {
                                z = false;
                            }
                            this.totalSwap_ = visitor.visitOneofInt(z, this.totalSwap_, other.totalSwap_);
                        } else if (i == 2) {
                            if (this.totalSwapCase_ != 9) {
                                z = false;
                            }
                            this.totalSwap_ = visitor.visitOneofInt(z, this.totalSwap_, other.totalSwap_);
                        } else if (i == 3) {
                            if (this.totalSwapCase_ == 0) {
                                z = false;
                            }
                            visitor.visitOneofNotSet(z);
                        }
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            int i2 = other.totalSwapCase_;
                            if (i2 != 0) {
                                this.totalSwapCase_ = i2;
                            }
                            this.bitField0_ |= other.bitField0_;
                        }
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                        boolean done = false;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 8) {
                                    this.bitField0_ |= 1;
                                    this.javaHeapPssKb_ = input.readInt32();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.nativeHeapPssKb_ = input.readInt32();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.codePssKb_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.stackPssKb_ = input.readInt32();
                                } else if (tag == 40) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.graphicsPssKb_ = input.readInt32();
                                } else if (tag == 48) {
                                    this.bitField0_ |= 32;
                                    this.privateOtherPssKb_ = input.readInt32();
                                } else if (tag == 56) {
                                    this.bitField0_ |= 64;
                                    this.systemPssKb_ = input.readInt32();
                                } else if (tag == 64) {
                                    this.totalSwapCase_ = 8;
                                    this.totalSwap_ = Integer.valueOf(input.readInt32());
                                } else if (tag == 72) {
                                    this.totalSwapCase_ = 9;
                                    this.totalSwap_ = Integer.valueOf(input.readInt32());
                                } else if (!parseUnknownField(tag, input)) {
                                    done = true;
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                        break;
                    case GET_DEFAULT_INSTANCE:
                        break;
                    case GET_PARSER:
                        if (PARSER == null) {
                            synchronized (AppSummary.class) {
                                if (PARSER == null) {
                                    PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                }
                            }
                        }
                        return PARSER;
                    default:
                        throw new UnsupportedOperationException();
                }
                return DEFAULT_INSTANCE;
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            public static AppSummary getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<AppSummary> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasPid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public int getPid() {
            return this.pid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPid(int value) {
            this.bitField0_ |= 1;
            this.pid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPid() {
            this.bitField0_ &= -2;
            this.pid_ = 0;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasProcessName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public String getProcessName() {
            return this.processName_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public ByteString getProcessNameBytes() {
            return ByteString.copyFromUtf8(this.processName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.processName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessName() {
            this.bitField0_ &= -3;
            this.processName_ = getDefaultInstance().getProcessName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.processName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasNativeHeap() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public HeapInfo getNativeHeap() {
            HeapInfo heapInfo = this.nativeHeap_;
            return heapInfo == null ? HeapInfo.getDefaultInstance() : heapInfo;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNativeHeap(HeapInfo value) {
            if (value != null) {
                this.nativeHeap_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNativeHeap(HeapInfo.Builder builderForValue) {
            this.nativeHeap_ = (HeapInfo) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNativeHeap(HeapInfo value) {
            HeapInfo heapInfo = this.nativeHeap_;
            if (heapInfo == null || heapInfo == HeapInfo.getDefaultInstance()) {
                this.nativeHeap_ = value;
            } else {
                this.nativeHeap_ = (HeapInfo) ((HeapInfo.Builder) HeapInfo.newBuilder(this.nativeHeap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNativeHeap() {
            this.nativeHeap_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasDalvikHeap() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public HeapInfo getDalvikHeap() {
            HeapInfo heapInfo = this.dalvikHeap_;
            return heapInfo == null ? HeapInfo.getDefaultInstance() : heapInfo;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDalvikHeap(HeapInfo value) {
            if (value != null) {
                this.dalvikHeap_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDalvikHeap(HeapInfo.Builder builderForValue) {
            this.dalvikHeap_ = (HeapInfo) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDalvikHeap(HeapInfo value) {
            HeapInfo heapInfo = this.dalvikHeap_;
            if (heapInfo == null || heapInfo == HeapInfo.getDefaultInstance()) {
                this.dalvikHeap_ = value;
            } else {
                this.dalvikHeap_ = (HeapInfo) ((HeapInfo.Builder) HeapInfo.newBuilder(this.dalvikHeap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDalvikHeap() {
            this.dalvikHeap_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public List<MemoryInfo> getOtherHeapsList() {
            return this.otherHeaps_;
        }

        public List<? extends MemoryInfoOrBuilder> getOtherHeapsOrBuilderList() {
            return this.otherHeaps_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public int getOtherHeapsCount() {
            return this.otherHeaps_.size();
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public MemoryInfo getOtherHeaps(int index) {
            return this.otherHeaps_.get(index);
        }

        public MemoryInfoOrBuilder getOtherHeapsOrBuilder(int index) {
            return this.otherHeaps_.get(index);
        }

        private void ensureOtherHeapsIsMutable() {
            if (!this.otherHeaps_.isModifiable()) {
                this.otherHeaps_ = GeneratedMessageLite.mutableCopy(this.otherHeaps_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOtherHeaps(int index, MemoryInfo value) {
            if (value != null) {
                ensureOtherHeapsIsMutable();
                this.otherHeaps_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOtherHeaps(int index, MemoryInfo.Builder builderForValue) {
            ensureOtherHeapsIsMutable();
            this.otherHeaps_.set(index, (MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addOtherHeaps(MemoryInfo value) {
            if (value != null) {
                ensureOtherHeapsIsMutable();
                this.otherHeaps_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addOtherHeaps(int index, MemoryInfo value) {
            if (value != null) {
                ensureOtherHeapsIsMutable();
                this.otherHeaps_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addOtherHeaps(MemoryInfo.Builder builderForValue) {
            ensureOtherHeapsIsMutable();
            this.otherHeaps_.add((MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addOtherHeaps(int index, MemoryInfo.Builder builderForValue) {
            ensureOtherHeapsIsMutable();
            this.otherHeaps_.add(index, (MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllOtherHeaps(Iterable<? extends MemoryInfo> values) {
            ensureOtherHeapsIsMutable();
            AbstractMessageLite.addAll(values, this.otherHeaps_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOtherHeaps() {
            this.otherHeaps_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeOtherHeaps(int index) {
            ensureOtherHeapsIsMutable();
            this.otherHeaps_.remove(index);
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasUnknownHeap() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public MemoryInfo getUnknownHeap() {
            MemoryInfo memoryInfo = this.unknownHeap_;
            return memoryInfo == null ? MemoryInfo.getDefaultInstance() : memoryInfo;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnknownHeap(MemoryInfo value) {
            if (value != null) {
                this.unknownHeap_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnknownHeap(MemoryInfo.Builder builderForValue) {
            this.unknownHeap_ = (MemoryInfo) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeUnknownHeap(MemoryInfo value) {
            MemoryInfo memoryInfo = this.unknownHeap_;
            if (memoryInfo == null || memoryInfo == MemoryInfo.getDefaultInstance()) {
                this.unknownHeap_ = value;
            } else {
                this.unknownHeap_ = (MemoryInfo) ((MemoryInfo.Builder) MemoryInfo.newBuilder(this.unknownHeap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnknownHeap() {
            this.unknownHeap_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasTotalHeap() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public HeapInfo getTotalHeap() {
            HeapInfo heapInfo = this.totalHeap_;
            return heapInfo == null ? HeapInfo.getDefaultInstance() : heapInfo;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalHeap(HeapInfo value) {
            if (value != null) {
                this.totalHeap_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalHeap(HeapInfo.Builder builderForValue) {
            this.totalHeap_ = (HeapInfo) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTotalHeap(HeapInfo value) {
            HeapInfo heapInfo = this.totalHeap_;
            if (heapInfo == null || heapInfo == HeapInfo.getDefaultInstance()) {
                this.totalHeap_ = value;
            } else {
                this.totalHeap_ = (HeapInfo) ((HeapInfo.Builder) HeapInfo.newBuilder(this.totalHeap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalHeap() {
            this.totalHeap_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public List<MemoryInfo> getDalvikDetailsList() {
            return this.dalvikDetails_;
        }

        public List<? extends MemoryInfoOrBuilder> getDalvikDetailsOrBuilderList() {
            return this.dalvikDetails_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public int getDalvikDetailsCount() {
            return this.dalvikDetails_.size();
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public MemoryInfo getDalvikDetails(int index) {
            return this.dalvikDetails_.get(index);
        }

        public MemoryInfoOrBuilder getDalvikDetailsOrBuilder(int index) {
            return this.dalvikDetails_.get(index);
        }

        private void ensureDalvikDetailsIsMutable() {
            if (!this.dalvikDetails_.isModifiable()) {
                this.dalvikDetails_ = GeneratedMessageLite.mutableCopy(this.dalvikDetails_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDalvikDetails(int index, MemoryInfo value) {
            if (value != null) {
                ensureDalvikDetailsIsMutable();
                this.dalvikDetails_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDalvikDetails(int index, MemoryInfo.Builder builderForValue) {
            ensureDalvikDetailsIsMutable();
            this.dalvikDetails_.set(index, (MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDalvikDetails(MemoryInfo value) {
            if (value != null) {
                ensureDalvikDetailsIsMutable();
                this.dalvikDetails_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDalvikDetails(int index, MemoryInfo value) {
            if (value != null) {
                ensureDalvikDetailsIsMutable();
                this.dalvikDetails_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDalvikDetails(MemoryInfo.Builder builderForValue) {
            ensureDalvikDetailsIsMutable();
            this.dalvikDetails_.add((MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDalvikDetails(int index, MemoryInfo.Builder builderForValue) {
            ensureDalvikDetailsIsMutable();
            this.dalvikDetails_.add(index, (MemoryInfo) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllDalvikDetails(Iterable<? extends MemoryInfo> values) {
            ensureDalvikDetailsIsMutable();
            AbstractMessageLite.addAll(values, this.dalvikDetails_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDalvikDetails() {
            this.dalvikDetails_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeDalvikDetails(int index) {
            ensureDalvikDetailsIsMutable();
            this.dalvikDetails_.remove(index);
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public boolean hasAppSummary() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
        public AppSummary getAppSummary() {
            AppSummary appSummary = this.appSummary_;
            return appSummary == null ? AppSummary.getDefaultInstance() : appSummary;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAppSummary(AppSummary value) {
            if (value != null) {
                this.appSummary_ = value;
                this.bitField0_ |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAppSummary(AppSummary.Builder builderForValue) {
            this.appSummary_ = (AppSummary) builderForValue.build();
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAppSummary(AppSummary value) {
            AppSummary appSummary = this.appSummary_;
            if (appSummary == null || appSummary == AppSummary.getDefaultInstance()) {
                this.appSummary_ = value;
            } else {
                this.appSummary_ = (AppSummary) ((AppSummary.Builder) AppSummary.newBuilder(this.appSummary_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAppSummary() {
            this.appSummary_ = null;
            this.bitField0_ &= -65;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getProcessName());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getNativeHeap());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getDalvikHeap());
            }
            for (int i = 0; i < this.otherHeaps_.size(); i++) {
                output.writeMessage(5, this.otherHeaps_.get(i));
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(6, getUnknownHeap());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(7, getTotalHeap());
            }
            for (int i2 = 0; i2 < this.dalvikDetails_.size(); i2++) {
                output.writeMessage(8, this.dalvikDetails_.get(i2));
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(9, getAppSummary());
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getProcessName());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getNativeHeap());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getDalvikHeap());
            }
            for (int i = 0; i < this.otherHeaps_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(5, this.otherHeaps_.get(i));
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(6, getUnknownHeap());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(7, getTotalHeap());
            }
            for (int i2 = 0; i2 < this.dalvikDetails_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(8, this.dalvikDetails_.get(i2));
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeMessageSize(9, getAppSummary());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ProcessMemory parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessMemory parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessMemory parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessMemory parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessMemory parseFrom(InputStream input) throws IOException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessMemory parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessMemory parseDelimitedFrom(InputStream input) throws IOException {
            return (ProcessMemory) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessMemory parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessMemory) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessMemory parseFrom(CodedInputStream input) throws IOException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessMemory parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessMemory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProcessMemory prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ProcessMemory, Builder> implements ProcessMemoryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ProcessMemory.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasPid() {
                return ((ProcessMemory) this.instance).hasPid();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public int getPid() {
                return ((ProcessMemory) this.instance).getPid();
            }

            public Builder setPid(int value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setPid(value);
                return this;
            }

            public Builder clearPid() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearPid();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasProcessName() {
                return ((ProcessMemory) this.instance).hasProcessName();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public String getProcessName() {
                return ((ProcessMemory) this.instance).getProcessName();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public ByteString getProcessNameBytes() {
                return ((ProcessMemory) this.instance).getProcessNameBytes();
            }

            public Builder setProcessName(String value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setProcessName(value);
                return this;
            }

            public Builder clearProcessName() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearProcessName();
                return this;
            }

            public Builder setProcessNameBytes(ByteString value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setProcessNameBytes(value);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasNativeHeap() {
                return ((ProcessMemory) this.instance).hasNativeHeap();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public HeapInfo getNativeHeap() {
                return ((ProcessMemory) this.instance).getNativeHeap();
            }

            public Builder setNativeHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setNativeHeap((ProcessMemory) value);
                return this;
            }

            public Builder setNativeHeap(HeapInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setNativeHeap((ProcessMemory) builderForValue);
                return this;
            }

            public Builder mergeNativeHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).mergeNativeHeap(value);
                return this;
            }

            public Builder clearNativeHeap() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearNativeHeap();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasDalvikHeap() {
                return ((ProcessMemory) this.instance).hasDalvikHeap();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public HeapInfo getDalvikHeap() {
                return ((ProcessMemory) this.instance).getDalvikHeap();
            }

            public Builder setDalvikHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setDalvikHeap((ProcessMemory) value);
                return this;
            }

            public Builder setDalvikHeap(HeapInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setDalvikHeap((ProcessMemory) builderForValue);
                return this;
            }

            public Builder mergeDalvikHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).mergeDalvikHeap(value);
                return this;
            }

            public Builder clearDalvikHeap() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearDalvikHeap();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public List<MemoryInfo> getOtherHeapsList() {
                return Collections.unmodifiableList(((ProcessMemory) this.instance).getOtherHeapsList());
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public int getOtherHeapsCount() {
                return ((ProcessMemory) this.instance).getOtherHeapsCount();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public MemoryInfo getOtherHeaps(int index) {
                return ((ProcessMemory) this.instance).getOtherHeaps(index);
            }

            public Builder setOtherHeaps(int index, MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setOtherHeaps((ProcessMemory) index, (int) value);
                return this;
            }

            public Builder setOtherHeaps(int index, MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setOtherHeaps((ProcessMemory) index, (int) builderForValue);
                return this;
            }

            public Builder addOtherHeaps(MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addOtherHeaps((ProcessMemory) value);
                return this;
            }

            public Builder addOtherHeaps(int index, MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addOtherHeaps((ProcessMemory) index, (int) value);
                return this;
            }

            public Builder addOtherHeaps(MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addOtherHeaps((ProcessMemory) builderForValue);
                return this;
            }

            public Builder addOtherHeaps(int index, MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addOtherHeaps((ProcessMemory) index, (int) builderForValue);
                return this;
            }

            public Builder addAllOtherHeaps(Iterable<? extends MemoryInfo> values) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addAllOtherHeaps(values);
                return this;
            }

            public Builder clearOtherHeaps() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearOtherHeaps();
                return this;
            }

            public Builder removeOtherHeaps(int index) {
                copyOnWrite();
                ((ProcessMemory) this.instance).removeOtherHeaps(index);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasUnknownHeap() {
                return ((ProcessMemory) this.instance).hasUnknownHeap();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public MemoryInfo getUnknownHeap() {
                return ((ProcessMemory) this.instance).getUnknownHeap();
            }

            public Builder setUnknownHeap(MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setUnknownHeap((ProcessMemory) value);
                return this;
            }

            public Builder setUnknownHeap(MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setUnknownHeap((ProcessMemory) builderForValue);
                return this;
            }

            public Builder mergeUnknownHeap(MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).mergeUnknownHeap(value);
                return this;
            }

            public Builder clearUnknownHeap() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearUnknownHeap();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasTotalHeap() {
                return ((ProcessMemory) this.instance).hasTotalHeap();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public HeapInfo getTotalHeap() {
                return ((ProcessMemory) this.instance).getTotalHeap();
            }

            public Builder setTotalHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setTotalHeap((ProcessMemory) value);
                return this;
            }

            public Builder setTotalHeap(HeapInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setTotalHeap((ProcessMemory) builderForValue);
                return this;
            }

            public Builder mergeTotalHeap(HeapInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).mergeTotalHeap(value);
                return this;
            }

            public Builder clearTotalHeap() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearTotalHeap();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public List<MemoryInfo> getDalvikDetailsList() {
                return Collections.unmodifiableList(((ProcessMemory) this.instance).getDalvikDetailsList());
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public int getDalvikDetailsCount() {
                return ((ProcessMemory) this.instance).getDalvikDetailsCount();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public MemoryInfo getDalvikDetails(int index) {
                return ((ProcessMemory) this.instance).getDalvikDetails(index);
            }

            public Builder setDalvikDetails(int index, MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setDalvikDetails((ProcessMemory) index, (int) value);
                return this;
            }

            public Builder setDalvikDetails(int index, MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setDalvikDetails((ProcessMemory) index, (int) builderForValue);
                return this;
            }

            public Builder addDalvikDetails(MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addDalvikDetails((ProcessMemory) value);
                return this;
            }

            public Builder addDalvikDetails(int index, MemoryInfo value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addDalvikDetails((ProcessMemory) index, (int) value);
                return this;
            }

            public Builder addDalvikDetails(MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addDalvikDetails((ProcessMemory) builderForValue);
                return this;
            }

            public Builder addDalvikDetails(int index, MemoryInfo.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addDalvikDetails((ProcessMemory) index, (int) builderForValue);
                return this;
            }

            public Builder addAllDalvikDetails(Iterable<? extends MemoryInfo> values) {
                copyOnWrite();
                ((ProcessMemory) this.instance).addAllDalvikDetails(values);
                return this;
            }

            public Builder clearDalvikDetails() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearDalvikDetails();
                return this;
            }

            public Builder removeDalvikDetails(int index) {
                copyOnWrite();
                ((ProcessMemory) this.instance).removeDalvikDetails(index);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public boolean hasAppSummary() {
                return ((ProcessMemory) this.instance).hasAppSummary();
            }

            @Override // com.android.server.am.MemInfoDumpProto.ProcessMemoryOrBuilder
            public AppSummary getAppSummary() {
                return ((ProcessMemory) this.instance).getAppSummary();
            }

            public Builder setAppSummary(AppSummary value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setAppSummary((ProcessMemory) value);
                return this;
            }

            public Builder setAppSummary(AppSummary.Builder builderForValue) {
                copyOnWrite();
                ((ProcessMemory) this.instance).setAppSummary((ProcessMemory) builderForValue);
                return this;
            }

            public Builder mergeAppSummary(AppSummary value) {
                copyOnWrite();
                ((ProcessMemory) this.instance).mergeAppSummary(value);
                return this;
            }

            public Builder clearAppSummary() {
                copyOnWrite();
                ((ProcessMemory) this.instance).clearAppSummary();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ProcessMemory();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.otherHeaps_.makeImmutable();
                    this.dalvikDetails_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ProcessMemory other = (ProcessMemory) arg1;
                    this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                    this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                    this.nativeHeap_ = (HeapInfo) visitor.visitMessage(this.nativeHeap_, other.nativeHeap_);
                    this.dalvikHeap_ = (HeapInfo) visitor.visitMessage(this.dalvikHeap_, other.dalvikHeap_);
                    this.otherHeaps_ = visitor.visitList(this.otherHeaps_, other.otherHeaps_);
                    this.unknownHeap_ = (MemoryInfo) visitor.visitMessage(this.unknownHeap_, other.unknownHeap_);
                    this.totalHeap_ = (HeapInfo) visitor.visitMessage(this.totalHeap_, other.totalHeap_);
                    this.dalvikDetails_ = visitor.visitList(this.dalvikDetails_, other.dalvikDetails_);
                    this.appSummary_ = (AppSummary) visitor.visitMessage(this.appSummary_, other.appSummary_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.pid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.processName_ = s;
                            } else if (tag == 26) {
                                HeapInfo.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (HeapInfo.Builder) this.nativeHeap_.toBuilder();
                                }
                                this.nativeHeap_ = (HeapInfo) input.readMessage(HeapInfo.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.nativeHeap_);
                                    this.nativeHeap_ = (HeapInfo) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                HeapInfo.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (HeapInfo.Builder) this.dalvikHeap_.toBuilder();
                                }
                                this.dalvikHeap_ = (HeapInfo) input.readMessage(HeapInfo.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dalvikHeap_);
                                    this.dalvikHeap_ = (HeapInfo) subBuilder2.buildPartial();
                                }
                                this.bitField0_ = 8 | this.bitField0_;
                            } else if (tag == 42) {
                                if (!this.otherHeaps_.isModifiable()) {
                                    this.otherHeaps_ = GeneratedMessageLite.mutableCopy(this.otherHeaps_);
                                }
                                this.otherHeaps_.add((MemoryInfo) input.readMessage(MemoryInfo.parser(), extensionRegistry));
                            } else if (tag == 50) {
                                MemoryInfo.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder3 = (MemoryInfo.Builder) this.unknownHeap_.toBuilder();
                                }
                                this.unknownHeap_ = (MemoryInfo) input.readMessage(MemoryInfo.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.unknownHeap_);
                                    this.unknownHeap_ = (MemoryInfo) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            } else if (tag == 58) {
                                HeapInfo.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder4 = (HeapInfo.Builder) this.totalHeap_.toBuilder();
                                }
                                this.totalHeap_ = (HeapInfo) input.readMessage(HeapInfo.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.totalHeap_);
                                    this.totalHeap_ = (HeapInfo) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 32;
                            } else if (tag == 66) {
                                if (!this.dalvikDetails_.isModifiable()) {
                                    this.dalvikDetails_ = GeneratedMessageLite.mutableCopy(this.dalvikDetails_);
                                }
                                this.dalvikDetails_.add((MemoryInfo) input.readMessage(MemoryInfo.parser(), extensionRegistry));
                            } else if (tag == 74) {
                                AppSummary.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder5 = (AppSummary.Builder) this.appSummary_.toBuilder();
                                }
                                this.appSummary_ = (AppSummary) input.readMessage(AppSummary.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.appSummary_);
                                    this.appSummary_ = (AppSummary) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 64;
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (ProcessMemory.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static ProcessMemory getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProcessMemory> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.android.server.am.MemInfoDumpProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$AppSummary$TotalSwapCase = new int[ProcessMemory.AppSummary.TotalSwapCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$MemoryInfo$DirtySwapCase = new int[ProcessMemory.MemoryInfo.DirtySwapCase.values().length];

        static {
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$AppSummary$TotalSwapCase[ProcessMemory.AppSummary.TotalSwapCase.TOTAL_SWAP_PSS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$AppSummary$TotalSwapCase[ProcessMemory.AppSummary.TotalSwapCase.TOTAL_SWAP_KB.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$AppSummary$TotalSwapCase[ProcessMemory.AppSummary.TotalSwapCase.TOTALSWAP_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$MemoryInfo$DirtySwapCase[ProcessMemory.MemoryInfo.DirtySwapCase.DIRTY_SWAP_KB.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$MemoryInfo$DirtySwapCase[ProcessMemory.MemoryInfo.DirtySwapCase.DIRTY_SWAP_PSS_KB.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$android$server$am$MemInfoDumpProto$ProcessMemory$MemoryInfo$DirtySwapCase[ProcessMemory.MemoryInfo.DirtySwapCase.DIRTYSWAP_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    public static final class AppData extends GeneratedMessageLite<AppData, Builder> implements AppDataOrBuilder {
        public static final int ASSET_ALLOCATIONS_FIELD_NUMBER = 4;
        private static final AppData DEFAULT_INSTANCE = new AppData();
        public static final int OBJECTS_FIELD_NUMBER = 2;
        private static volatile Parser<AppData> PARSER = null;
        public static final int PROCESS_MEMORY_FIELD_NUMBER = 1;
        public static final int SQL_FIELD_NUMBER = 3;
        public static final int UNREACHABLE_MEMORY_FIELD_NUMBER = 5;
        private String assetAllocations_ = "";
        private int bitField0_;
        private ObjectStats objects_;
        private ProcessMemory processMemory_;
        private SqlStats sql_;
        private String unreachableMemory_ = "";

        public interface ObjectStatsOrBuilder extends MessageLiteOrBuilder {
            int getActivityInstanceCount();

            int getAppContextInstanceCount();

            int getBinderObjectDeathCount();

            int getGlobalAssetCount();

            int getGlobalAssetManagerCount();

            int getLocalBinderObjectCount();

            int getOpenSslSocketCount();

            int getParcelCount();

            long getParcelMemoryKb();

            int getProxyBinderObjectCount();

            int getViewInstanceCount();

            int getViewRootInstanceCount();

            int getWebviewInstanceCount();

            boolean hasActivityInstanceCount();

            boolean hasAppContextInstanceCount();

            boolean hasBinderObjectDeathCount();

            boolean hasGlobalAssetCount();

            boolean hasGlobalAssetManagerCount();

            boolean hasLocalBinderObjectCount();

            boolean hasOpenSslSocketCount();

            boolean hasParcelCount();

            boolean hasParcelMemoryKb();

            boolean hasProxyBinderObjectCount();

            boolean hasViewInstanceCount();

            boolean hasViewRootInstanceCount();

            boolean hasWebviewInstanceCount();
        }

        public interface SqlStatsOrBuilder extends MessageLiteOrBuilder {
            SqlStats.Database getDatabases(int i);

            int getDatabasesCount();

            List<SqlStats.Database> getDatabasesList();

            int getMallocSizeKb();

            int getMemoryUsedKb();

            int getPagecacheOverflowKb();

            boolean hasMallocSizeKb();

            boolean hasMemoryUsedKb();

            boolean hasPagecacheOverflowKb();
        }

        private AppData() {
        }

        public static final class ObjectStats extends GeneratedMessageLite<ObjectStats, Builder> implements ObjectStatsOrBuilder {
            public static final int ACTIVITY_INSTANCE_COUNT_FIELD_NUMBER = 4;
            public static final int APP_CONTEXT_INSTANCE_COUNT_FIELD_NUMBER = 3;
            public static final int BINDER_OBJECT_DEATH_COUNT_FIELD_NUMBER = 11;
            private static final ObjectStats DEFAULT_INSTANCE = new ObjectStats();
            public static final int GLOBAL_ASSET_COUNT_FIELD_NUMBER = 5;
            public static final int GLOBAL_ASSET_MANAGER_COUNT_FIELD_NUMBER = 6;
            public static final int LOCAL_BINDER_OBJECT_COUNT_FIELD_NUMBER = 7;
            public static final int OPEN_SSL_SOCKET_COUNT_FIELD_NUMBER = 12;
            public static final int PARCEL_COUNT_FIELD_NUMBER = 10;
            public static final int PARCEL_MEMORY_KB_FIELD_NUMBER = 9;
            private static volatile Parser<ObjectStats> PARSER = null;
            public static final int PROXY_BINDER_OBJECT_COUNT_FIELD_NUMBER = 8;
            public static final int VIEW_INSTANCE_COUNT_FIELD_NUMBER = 1;
            public static final int VIEW_ROOT_INSTANCE_COUNT_FIELD_NUMBER = 2;
            public static final int WEBVIEW_INSTANCE_COUNT_FIELD_NUMBER = 13;
            private int activityInstanceCount_ = 0;
            private int appContextInstanceCount_ = 0;
            private int binderObjectDeathCount_ = 0;
            private int bitField0_;
            private int globalAssetCount_ = 0;
            private int globalAssetManagerCount_ = 0;
            private int localBinderObjectCount_ = 0;
            private int openSslSocketCount_ = 0;
            private int parcelCount_ = 0;
            private long parcelMemoryKb_ = 0;
            private int proxyBinderObjectCount_ = 0;
            private int viewInstanceCount_ = 0;
            private int viewRootInstanceCount_ = 0;
            private int webviewInstanceCount_ = 0;

            private ObjectStats() {
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasViewInstanceCount() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getViewInstanceCount() {
                return this.viewInstanceCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setViewInstanceCount(int value) {
                this.bitField0_ |= 1;
                this.viewInstanceCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearViewInstanceCount() {
                this.bitField0_ &= -2;
                this.viewInstanceCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasViewRootInstanceCount() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getViewRootInstanceCount() {
                return this.viewRootInstanceCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setViewRootInstanceCount(int value) {
                this.bitField0_ |= 2;
                this.viewRootInstanceCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearViewRootInstanceCount() {
                this.bitField0_ &= -3;
                this.viewRootInstanceCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasAppContextInstanceCount() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getAppContextInstanceCount() {
                return this.appContextInstanceCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setAppContextInstanceCount(int value) {
                this.bitField0_ |= 4;
                this.appContextInstanceCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearAppContextInstanceCount() {
                this.bitField0_ &= -5;
                this.appContextInstanceCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasActivityInstanceCount() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getActivityInstanceCount() {
                return this.activityInstanceCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setActivityInstanceCount(int value) {
                this.bitField0_ |= 8;
                this.activityInstanceCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearActivityInstanceCount() {
                this.bitField0_ &= -9;
                this.activityInstanceCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasGlobalAssetCount() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getGlobalAssetCount() {
                return this.globalAssetCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setGlobalAssetCount(int value) {
                this.bitField0_ |= 16;
                this.globalAssetCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearGlobalAssetCount() {
                this.bitField0_ &= -17;
                this.globalAssetCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasGlobalAssetManagerCount() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getGlobalAssetManagerCount() {
                return this.globalAssetManagerCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setGlobalAssetManagerCount(int value) {
                this.bitField0_ |= 32;
                this.globalAssetManagerCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearGlobalAssetManagerCount() {
                this.bitField0_ &= -33;
                this.globalAssetManagerCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasLocalBinderObjectCount() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getLocalBinderObjectCount() {
                return this.localBinderObjectCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setLocalBinderObjectCount(int value) {
                this.bitField0_ |= 64;
                this.localBinderObjectCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearLocalBinderObjectCount() {
                this.bitField0_ &= -65;
                this.localBinderObjectCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasProxyBinderObjectCount() {
                return (this.bitField0_ & 128) == 128;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getProxyBinderObjectCount() {
                return this.proxyBinderObjectCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setProxyBinderObjectCount(int value) {
                this.bitField0_ |= 128;
                this.proxyBinderObjectCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearProxyBinderObjectCount() {
                this.bitField0_ &= -129;
                this.proxyBinderObjectCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasParcelMemoryKb() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public long getParcelMemoryKb() {
                return this.parcelMemoryKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setParcelMemoryKb(long value) {
                this.bitField0_ |= 256;
                this.parcelMemoryKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearParcelMemoryKb() {
                this.bitField0_ &= -257;
                this.parcelMemoryKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasParcelCount() {
                return (this.bitField0_ & 512) == 512;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getParcelCount() {
                return this.parcelCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setParcelCount(int value) {
                this.bitField0_ |= 512;
                this.parcelCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearParcelCount() {
                this.bitField0_ &= -513;
                this.parcelCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasBinderObjectDeathCount() {
                return (this.bitField0_ & 1024) == 1024;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getBinderObjectDeathCount() {
                return this.binderObjectDeathCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setBinderObjectDeathCount(int value) {
                this.bitField0_ |= 1024;
                this.binderObjectDeathCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearBinderObjectDeathCount() {
                this.bitField0_ &= -1025;
                this.binderObjectDeathCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasOpenSslSocketCount() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getOpenSslSocketCount() {
                return this.openSslSocketCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setOpenSslSocketCount(int value) {
                this.bitField0_ |= 2048;
                this.openSslSocketCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearOpenSslSocketCount() {
                this.bitField0_ &= -2049;
                this.openSslSocketCount_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public boolean hasWebviewInstanceCount() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
            public int getWebviewInstanceCount() {
                return this.webviewInstanceCount_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setWebviewInstanceCount(int value) {
                this.bitField0_ |= 4096;
                this.webviewInstanceCount_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearWebviewInstanceCount() {
                this.bitField0_ &= -4097;
                this.webviewInstanceCount_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.viewInstanceCount_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.viewRootInstanceCount_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.appContextInstanceCount_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeInt32(4, this.activityInstanceCount_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeInt32(5, this.globalAssetCount_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    output.writeInt32(6, this.globalAssetManagerCount_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    output.writeInt32(7, this.localBinderObjectCount_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    output.writeInt32(8, this.proxyBinderObjectCount_);
                }
                if ((this.bitField0_ & 256) == 256) {
                    output.writeInt64(9, this.parcelMemoryKb_);
                }
                if ((this.bitField0_ & 512) == 512) {
                    output.writeInt32(10, this.parcelCount_);
                }
                if ((this.bitField0_ & 1024) == 1024) {
                    output.writeInt32(11, this.binderObjectDeathCount_);
                }
                if ((this.bitField0_ & 2048) == 2048) {
                    output.writeInt32(12, this.openSslSocketCount_);
                }
                if ((this.bitField0_ & 4096) == 4096) {
                    output.writeInt32(13, this.webviewInstanceCount_);
                }
                this.unknownFields.writeTo(output);
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.viewInstanceCount_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.viewRootInstanceCount_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.appContextInstanceCount_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeInt32Size(4, this.activityInstanceCount_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeInt32Size(5, this.globalAssetCount_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    size2 += CodedOutputStream.computeInt32Size(6, this.globalAssetManagerCount_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    size2 += CodedOutputStream.computeInt32Size(7, this.localBinderObjectCount_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    size2 += CodedOutputStream.computeInt32Size(8, this.proxyBinderObjectCount_);
                }
                if ((this.bitField0_ & 256) == 256) {
                    size2 += CodedOutputStream.computeInt64Size(9, this.parcelMemoryKb_);
                }
                if ((this.bitField0_ & 512) == 512) {
                    size2 += CodedOutputStream.computeInt32Size(10, this.parcelCount_);
                }
                if ((this.bitField0_ & 1024) == 1024) {
                    size2 += CodedOutputStream.computeInt32Size(11, this.binderObjectDeathCount_);
                }
                if ((this.bitField0_ & 2048) == 2048) {
                    size2 += CodedOutputStream.computeInt32Size(12, this.openSslSocketCount_);
                }
                if ((this.bitField0_ & 4096) == 4096) {
                    size2 += CodedOutputStream.computeInt32Size(13, this.webviewInstanceCount_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ObjectStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ObjectStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ObjectStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ObjectStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ObjectStats parseFrom(InputStream input) throws IOException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ObjectStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ObjectStats parseDelimitedFrom(InputStream input) throws IOException {
                return (ObjectStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ObjectStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ObjectStats parseFrom(CodedInputStream input) throws IOException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ObjectStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ObjectStats prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ObjectStats, Builder> implements ObjectStatsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(ObjectStats.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasViewInstanceCount() {
                    return ((ObjectStats) this.instance).hasViewInstanceCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getViewInstanceCount() {
                    return ((ObjectStats) this.instance).getViewInstanceCount();
                }

                public Builder setViewInstanceCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setViewInstanceCount(value);
                    return this;
                }

                public Builder clearViewInstanceCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearViewInstanceCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasViewRootInstanceCount() {
                    return ((ObjectStats) this.instance).hasViewRootInstanceCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getViewRootInstanceCount() {
                    return ((ObjectStats) this.instance).getViewRootInstanceCount();
                }

                public Builder setViewRootInstanceCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setViewRootInstanceCount(value);
                    return this;
                }

                public Builder clearViewRootInstanceCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearViewRootInstanceCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasAppContextInstanceCount() {
                    return ((ObjectStats) this.instance).hasAppContextInstanceCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getAppContextInstanceCount() {
                    return ((ObjectStats) this.instance).getAppContextInstanceCount();
                }

                public Builder setAppContextInstanceCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setAppContextInstanceCount(value);
                    return this;
                }

                public Builder clearAppContextInstanceCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearAppContextInstanceCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasActivityInstanceCount() {
                    return ((ObjectStats) this.instance).hasActivityInstanceCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getActivityInstanceCount() {
                    return ((ObjectStats) this.instance).getActivityInstanceCount();
                }

                public Builder setActivityInstanceCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setActivityInstanceCount(value);
                    return this;
                }

                public Builder clearActivityInstanceCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearActivityInstanceCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasGlobalAssetCount() {
                    return ((ObjectStats) this.instance).hasGlobalAssetCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getGlobalAssetCount() {
                    return ((ObjectStats) this.instance).getGlobalAssetCount();
                }

                public Builder setGlobalAssetCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setGlobalAssetCount(value);
                    return this;
                }

                public Builder clearGlobalAssetCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearGlobalAssetCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasGlobalAssetManagerCount() {
                    return ((ObjectStats) this.instance).hasGlobalAssetManagerCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getGlobalAssetManagerCount() {
                    return ((ObjectStats) this.instance).getGlobalAssetManagerCount();
                }

                public Builder setGlobalAssetManagerCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setGlobalAssetManagerCount(value);
                    return this;
                }

                public Builder clearGlobalAssetManagerCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearGlobalAssetManagerCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasLocalBinderObjectCount() {
                    return ((ObjectStats) this.instance).hasLocalBinderObjectCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getLocalBinderObjectCount() {
                    return ((ObjectStats) this.instance).getLocalBinderObjectCount();
                }

                public Builder setLocalBinderObjectCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setLocalBinderObjectCount(value);
                    return this;
                }

                public Builder clearLocalBinderObjectCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearLocalBinderObjectCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasProxyBinderObjectCount() {
                    return ((ObjectStats) this.instance).hasProxyBinderObjectCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getProxyBinderObjectCount() {
                    return ((ObjectStats) this.instance).getProxyBinderObjectCount();
                }

                public Builder setProxyBinderObjectCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setProxyBinderObjectCount(value);
                    return this;
                }

                public Builder clearProxyBinderObjectCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearProxyBinderObjectCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasParcelMemoryKb() {
                    return ((ObjectStats) this.instance).hasParcelMemoryKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public long getParcelMemoryKb() {
                    return ((ObjectStats) this.instance).getParcelMemoryKb();
                }

                public Builder setParcelMemoryKb(long value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setParcelMemoryKb(value);
                    return this;
                }

                public Builder clearParcelMemoryKb() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearParcelMemoryKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasParcelCount() {
                    return ((ObjectStats) this.instance).hasParcelCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getParcelCount() {
                    return ((ObjectStats) this.instance).getParcelCount();
                }

                public Builder setParcelCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setParcelCount(value);
                    return this;
                }

                public Builder clearParcelCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearParcelCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasBinderObjectDeathCount() {
                    return ((ObjectStats) this.instance).hasBinderObjectDeathCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getBinderObjectDeathCount() {
                    return ((ObjectStats) this.instance).getBinderObjectDeathCount();
                }

                public Builder setBinderObjectDeathCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setBinderObjectDeathCount(value);
                    return this;
                }

                public Builder clearBinderObjectDeathCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearBinderObjectDeathCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasOpenSslSocketCount() {
                    return ((ObjectStats) this.instance).hasOpenSslSocketCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getOpenSslSocketCount() {
                    return ((ObjectStats) this.instance).getOpenSslSocketCount();
                }

                public Builder setOpenSslSocketCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setOpenSslSocketCount(value);
                    return this;
                }

                public Builder clearOpenSslSocketCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearOpenSslSocketCount();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public boolean hasWebviewInstanceCount() {
                    return ((ObjectStats) this.instance).hasWebviewInstanceCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.ObjectStatsOrBuilder
                public int getWebviewInstanceCount() {
                    return ((ObjectStats) this.instance).getWebviewInstanceCount();
                }

                public Builder setWebviewInstanceCount(int value) {
                    copyOnWrite();
                    ((ObjectStats) this.instance).setWebviewInstanceCount(value);
                    return this;
                }

                public Builder clearWebviewInstanceCount() {
                    copyOnWrite();
                    ((ObjectStats) this.instance).clearWebviewInstanceCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ObjectStats();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ObjectStats other = (ObjectStats) arg1;
                        this.viewInstanceCount_ = visitor.visitInt(hasViewInstanceCount(), this.viewInstanceCount_, other.hasViewInstanceCount(), other.viewInstanceCount_);
                        this.viewRootInstanceCount_ = visitor.visitInt(hasViewRootInstanceCount(), this.viewRootInstanceCount_, other.hasViewRootInstanceCount(), other.viewRootInstanceCount_);
                        this.appContextInstanceCount_ = visitor.visitInt(hasAppContextInstanceCount(), this.appContextInstanceCount_, other.hasAppContextInstanceCount(), other.appContextInstanceCount_);
                        this.activityInstanceCount_ = visitor.visitInt(hasActivityInstanceCount(), this.activityInstanceCount_, other.hasActivityInstanceCount(), other.activityInstanceCount_);
                        this.globalAssetCount_ = visitor.visitInt(hasGlobalAssetCount(), this.globalAssetCount_, other.hasGlobalAssetCount(), other.globalAssetCount_);
                        this.globalAssetManagerCount_ = visitor.visitInt(hasGlobalAssetManagerCount(), this.globalAssetManagerCount_, other.hasGlobalAssetManagerCount(), other.globalAssetManagerCount_);
                        this.localBinderObjectCount_ = visitor.visitInt(hasLocalBinderObjectCount(), this.localBinderObjectCount_, other.hasLocalBinderObjectCount(), other.localBinderObjectCount_);
                        this.proxyBinderObjectCount_ = visitor.visitInt(hasProxyBinderObjectCount(), this.proxyBinderObjectCount_, other.hasProxyBinderObjectCount(), other.proxyBinderObjectCount_);
                        this.parcelMemoryKb_ = visitor.visitLong(hasParcelMemoryKb(), this.parcelMemoryKb_, other.hasParcelMemoryKb(), other.parcelMemoryKb_);
                        this.parcelCount_ = visitor.visitInt(hasParcelCount(), this.parcelCount_, other.hasParcelCount(), other.parcelCount_);
                        this.binderObjectDeathCount_ = visitor.visitInt(hasBinderObjectDeathCount(), this.binderObjectDeathCount_, other.hasBinderObjectDeathCount(), other.binderObjectDeathCount_);
                        this.openSslSocketCount_ = visitor.visitInt(hasOpenSslSocketCount(), this.openSslSocketCount_, other.hasOpenSslSocketCount(), other.openSslSocketCount_);
                        this.webviewInstanceCount_ = visitor.visitInt(hasWebviewInstanceCount(), this.webviewInstanceCount_, other.hasWebviewInstanceCount(), other.webviewInstanceCount_);
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            this.bitField0_ |= other.bitField0_;
                        }
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                        boolean done = false;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                switch (tag) {
                                    case 0:
                                        done = true;
                                        break;
                                    case 8:
                                        this.bitField0_ |= 1;
                                        this.viewInstanceCount_ = input.readInt32();
                                        break;
                                    case 16:
                                        this.bitField0_ |= 2;
                                        this.viewRootInstanceCount_ = input.readInt32();
                                        break;
                                    case 24:
                                        this.bitField0_ |= 4;
                                        this.appContextInstanceCount_ = input.readInt32();
                                        break;
                                    case 32:
                                        this.bitField0_ |= 8;
                                        this.activityInstanceCount_ = input.readInt32();
                                        break;
                                    case 40:
                                        this.bitField0_ |= 16;
                                        this.globalAssetCount_ = input.readInt32();
                                        break;
                                    case 48:
                                        this.bitField0_ |= 32;
                                        this.globalAssetManagerCount_ = input.readInt32();
                                        break;
                                    case 56:
                                        this.bitField0_ |= 64;
                                        this.localBinderObjectCount_ = input.readInt32();
                                        break;
                                    case 64:
                                        this.bitField0_ |= 128;
                                        this.proxyBinderObjectCount_ = input.readInt32();
                                        break;
                                    case 72:
                                        this.bitField0_ |= 256;
                                        this.parcelMemoryKb_ = input.readInt64();
                                        break;
                                    case 80:
                                        this.bitField0_ |= 512;
                                        this.parcelCount_ = input.readInt32();
                                        break;
                                    case 88:
                                        this.bitField0_ |= 1024;
                                        this.binderObjectDeathCount_ = input.readInt32();
                                        break;
                                    case 96:
                                        this.bitField0_ |= 2048;
                                        this.openSslSocketCount_ = input.readInt32();
                                        break;
                                    case 104:
                                        this.bitField0_ |= 4096;
                                        this.webviewInstanceCount_ = input.readInt32();
                                        break;
                                    default:
                                        if (parseUnknownField(tag, input)) {
                                            break;
                                        } else {
                                            done = true;
                                            break;
                                        }
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                        break;
                    case GET_DEFAULT_INSTANCE:
                        break;
                    case GET_PARSER:
                        if (PARSER == null) {
                            synchronized (ObjectStats.class) {
                                if (PARSER == null) {
                                    PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                }
                            }
                        }
                        return PARSER;
                    default:
                        throw new UnsupportedOperationException();
                }
                return DEFAULT_INSTANCE;
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            public static ObjectStats getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ObjectStats> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class SqlStats extends GeneratedMessageLite<SqlStats, Builder> implements SqlStatsOrBuilder {
            public static final int DATABASES_FIELD_NUMBER = 4;
            private static final SqlStats DEFAULT_INSTANCE = new SqlStats();
            public static final int MALLOC_SIZE_KB_FIELD_NUMBER = 3;
            public static final int MEMORY_USED_KB_FIELD_NUMBER = 1;
            public static final int PAGECACHE_OVERFLOW_KB_FIELD_NUMBER = 2;
            private static volatile Parser<SqlStats> PARSER;
            private int bitField0_;
            private Internal.ProtobufList<Database> databases_ = emptyProtobufList();
            private int mallocSizeKb_ = 0;
            private int memoryUsedKb_ = 0;
            private int pagecacheOverflowKb_ = 0;

            public interface DatabaseOrBuilder extends MessageLiteOrBuilder {
                String getCache();

                ByteString getCacheBytes();

                int getDbSize();

                int getLookasideB();

                String getName();

                ByteString getNameBytes();

                int getPageSize();

                boolean hasCache();

                boolean hasDbSize();

                boolean hasLookasideB();

                boolean hasName();

                boolean hasPageSize();
            }

            private SqlStats() {
            }

            public static final class Database extends GeneratedMessageLite<Database, Builder> implements DatabaseOrBuilder {
                public static final int CACHE_FIELD_NUMBER = 5;
                public static final int DB_SIZE_FIELD_NUMBER = 3;
                private static final Database DEFAULT_INSTANCE = new Database();
                public static final int LOOKASIDE_B_FIELD_NUMBER = 4;
                public static final int NAME_FIELD_NUMBER = 1;
                public static final int PAGE_SIZE_FIELD_NUMBER = 2;
                private static volatile Parser<Database> PARSER;
                private int bitField0_;
                private String cache_ = "";
                private int dbSize_ = 0;
                private int lookasideB_ = 0;
                private String name_ = "";
                private int pageSize_ = 0;

                private Database() {
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public boolean hasName() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public String getName() {
                    return this.name_;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public ByteString getNameBytes() {
                    return ByteString.copyFromUtf8(this.name_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setName(String value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.name_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearName() {
                    this.bitField0_ &= -2;
                    this.name_ = getDefaultInstance().getName();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setNameBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.name_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public boolean hasPageSize() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public int getPageSize() {
                    return this.pageSize_;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setPageSize(int value) {
                    this.bitField0_ |= 2;
                    this.pageSize_ = value;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearPageSize() {
                    this.bitField0_ &= -3;
                    this.pageSize_ = 0;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public boolean hasDbSize() {
                    return (this.bitField0_ & 4) == 4;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public int getDbSize() {
                    return this.dbSize_;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setDbSize(int value) {
                    this.bitField0_ |= 4;
                    this.dbSize_ = value;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearDbSize() {
                    this.bitField0_ &= -5;
                    this.dbSize_ = 0;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public boolean hasLookasideB() {
                    return (this.bitField0_ & 8) == 8;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public int getLookasideB() {
                    return this.lookasideB_;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setLookasideB(int value) {
                    this.bitField0_ |= 8;
                    this.lookasideB_ = value;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearLookasideB() {
                    this.bitField0_ &= -9;
                    this.lookasideB_ = 0;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public boolean hasCache() {
                    return (this.bitField0_ & 16) == 16;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public String getCache() {
                    return this.cache_;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                public ByteString getCacheBytes() {
                    return ByteString.copyFromUtf8(this.cache_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setCache(String value) {
                    if (value != null) {
                        this.bitField0_ |= 16;
                        this.cache_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearCache() {
                    this.bitField0_ &= -17;
                    this.cache_ = getDefaultInstance().getCache();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setCacheBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 16;
                        this.cache_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.google.protobuf.MessageLite
                public void writeTo(CodedOutputStream output) throws IOException {
                    if ((this.bitField0_ & 1) == 1) {
                        output.writeString(1, getName());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        output.writeInt32(2, this.pageSize_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        output.writeInt32(3, this.dbSize_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        output.writeInt32(4, this.lookasideB_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        output.writeString(5, getCache());
                    }
                    this.unknownFields.writeTo(output);
                }

                @Override // com.google.protobuf.MessageLite
                public int getSerializedSize() {
                    int size = this.memoizedSerializedSize;
                    if (size != -1) {
                        return size;
                    }
                    int size2 = 0;
                    if ((this.bitField0_ & 1) == 1) {
                        size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        size2 += CodedOutputStream.computeInt32Size(2, this.pageSize_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        size2 += CodedOutputStream.computeInt32Size(3, this.dbSize_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        size2 += CodedOutputStream.computeInt32Size(4, this.lookasideB_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        size2 += CodedOutputStream.computeStringSize(5, getCache());
                    }
                    int size3 = size2 + this.unknownFields.getSerializedSize();
                    this.memoizedSerializedSize = size3;
                    return size3;
                }

                public static Database parseFrom(ByteString data) throws InvalidProtocolBufferException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static Database parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static Database parseFrom(byte[] data) throws InvalidProtocolBufferException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static Database parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static Database parseFrom(InputStream input) throws IOException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static Database parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Database parseDelimitedFrom(InputStream input) throws IOException {
                    return (Database) parseDelimitedFrom(DEFAULT_INSTANCE, input);
                }

                public static Database parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (Database) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Database parseFrom(CodedInputStream input) throws IOException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static Database parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (Database) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Builder newBuilder() {
                    return (Builder) DEFAULT_INSTANCE.toBuilder();
                }

                public static Builder newBuilder(Database prototype) {
                    return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
                }

                public static final class Builder extends GeneratedMessageLite.Builder<Database, Builder> implements DatabaseOrBuilder {
                    /* synthetic */ Builder(AnonymousClass1 x0) {
                        this();
                    }

                    private Builder() {
                        super(Database.DEFAULT_INSTANCE);
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public boolean hasName() {
                        return ((Database) this.instance).hasName();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public String getName() {
                        return ((Database) this.instance).getName();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public ByteString getNameBytes() {
                        return ((Database) this.instance).getNameBytes();
                    }

                    public Builder setName(String value) {
                        copyOnWrite();
                        ((Database) this.instance).setName(value);
                        return this;
                    }

                    public Builder clearName() {
                        copyOnWrite();
                        ((Database) this.instance).clearName();
                        return this;
                    }

                    public Builder setNameBytes(ByteString value) {
                        copyOnWrite();
                        ((Database) this.instance).setNameBytes(value);
                        return this;
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public boolean hasPageSize() {
                        return ((Database) this.instance).hasPageSize();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public int getPageSize() {
                        return ((Database) this.instance).getPageSize();
                    }

                    public Builder setPageSize(int value) {
                        copyOnWrite();
                        ((Database) this.instance).setPageSize(value);
                        return this;
                    }

                    public Builder clearPageSize() {
                        copyOnWrite();
                        ((Database) this.instance).clearPageSize();
                        return this;
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public boolean hasDbSize() {
                        return ((Database) this.instance).hasDbSize();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public int getDbSize() {
                        return ((Database) this.instance).getDbSize();
                    }

                    public Builder setDbSize(int value) {
                        copyOnWrite();
                        ((Database) this.instance).setDbSize(value);
                        return this;
                    }

                    public Builder clearDbSize() {
                        copyOnWrite();
                        ((Database) this.instance).clearDbSize();
                        return this;
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public boolean hasLookasideB() {
                        return ((Database) this.instance).hasLookasideB();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public int getLookasideB() {
                        return ((Database) this.instance).getLookasideB();
                    }

                    public Builder setLookasideB(int value) {
                        copyOnWrite();
                        ((Database) this.instance).setLookasideB(value);
                        return this;
                    }

                    public Builder clearLookasideB() {
                        copyOnWrite();
                        ((Database) this.instance).clearLookasideB();
                        return this;
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public boolean hasCache() {
                        return ((Database) this.instance).hasCache();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public String getCache() {
                        return ((Database) this.instance).getCache();
                    }

                    @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStats.DatabaseOrBuilder
                    public ByteString getCacheBytes() {
                        return ((Database) this.instance).getCacheBytes();
                    }

                    public Builder setCache(String value) {
                        copyOnWrite();
                        ((Database) this.instance).setCache(value);
                        return this;
                    }

                    public Builder clearCache() {
                        copyOnWrite();
                        ((Database) this.instance).clearCache();
                        return this;
                    }

                    public Builder setCacheBytes(ByteString value) {
                        copyOnWrite();
                        ((Database) this.instance).setCacheBytes(value);
                        return this;
                    }
                }

                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessageLite
                public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                    switch (method) {
                        case NEW_MUTABLE_INSTANCE:
                            return new Database();
                        case IS_INITIALIZED:
                            return DEFAULT_INSTANCE;
                        case MAKE_IMMUTABLE:
                            return null;
                        case NEW_BUILDER:
                            return new Builder(null);
                        case VISIT:
                            GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                            Database other = (Database) arg1;
                            this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                            this.pageSize_ = visitor.visitInt(hasPageSize(), this.pageSize_, other.hasPageSize(), other.pageSize_);
                            this.dbSize_ = visitor.visitInt(hasDbSize(), this.dbSize_, other.hasDbSize(), other.dbSize_);
                            this.lookasideB_ = visitor.visitInt(hasLookasideB(), this.lookasideB_, other.hasLookasideB(), other.lookasideB_);
                            this.cache_ = visitor.visitString(hasCache(), this.cache_, other.hasCache(), other.cache_);
                            if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                                this.bitField0_ |= other.bitField0_;
                            }
                            return this;
                        case MERGE_FROM_STREAM:
                            CodedInputStream input = (CodedInputStream) arg0;
                            ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                            boolean done = false;
                            while (!done) {
                                try {
                                    int tag = input.readTag();
                                    if (tag == 0) {
                                        done = true;
                                    } else if (tag == 10) {
                                        String s = input.readString();
                                        this.bitField0_ |= 1;
                                        this.name_ = s;
                                    } else if (tag == 16) {
                                        this.bitField0_ |= 2;
                                        this.pageSize_ = input.readInt32();
                                    } else if (tag == 24) {
                                        this.bitField0_ |= 4;
                                        this.dbSize_ = input.readInt32();
                                    } else if (tag == 32) {
                                        this.bitField0_ |= 8;
                                        this.lookasideB_ = input.readInt32();
                                    } else if (tag == 42) {
                                        String s2 = input.readString();
                                        this.bitField0_ = 16 | this.bitField0_;
                                        this.cache_ = s2;
                                    } else if (!parseUnknownField(tag, input)) {
                                        done = true;
                                    }
                                } catch (InvalidProtocolBufferException e) {
                                    throw new RuntimeException(e.setUnfinishedMessage(this));
                                } catch (IOException e2) {
                                    throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                                }
                            }
                            break;
                        case GET_DEFAULT_INSTANCE:
                            break;
                        case GET_PARSER:
                            if (PARSER == null) {
                                synchronized (Database.class) {
                                    if (PARSER == null) {
                                        PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                    }
                                }
                            }
                            return PARSER;
                        default:
                            throw new UnsupportedOperationException();
                    }
                    return DEFAULT_INSTANCE;
                }

                static {
                    DEFAULT_INSTANCE.makeImmutable();
                }

                public static Database getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<Database> parser() {
                    return DEFAULT_INSTANCE.getParserForType();
                }
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public boolean hasMemoryUsedKb() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public int getMemoryUsedKb() {
                return this.memoryUsedKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMemoryUsedKb(int value) {
                this.bitField0_ |= 1;
                this.memoryUsedKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearMemoryUsedKb() {
                this.bitField0_ &= -2;
                this.memoryUsedKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public boolean hasPagecacheOverflowKb() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public int getPagecacheOverflowKb() {
                return this.pagecacheOverflowKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setPagecacheOverflowKb(int value) {
                this.bitField0_ |= 2;
                this.pagecacheOverflowKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearPagecacheOverflowKb() {
                this.bitField0_ &= -3;
                this.pagecacheOverflowKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public boolean hasMallocSizeKb() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public int getMallocSizeKb() {
                return this.mallocSizeKb_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setMallocSizeKb(int value) {
                this.bitField0_ |= 4;
                this.mallocSizeKb_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearMallocSizeKb() {
                this.bitField0_ &= -5;
                this.mallocSizeKb_ = 0;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public List<Database> getDatabasesList() {
                return this.databases_;
            }

            public List<? extends DatabaseOrBuilder> getDatabasesOrBuilderList() {
                return this.databases_;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public int getDatabasesCount() {
                return this.databases_.size();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
            public Database getDatabases(int index) {
                return this.databases_.get(index);
            }

            public DatabaseOrBuilder getDatabasesOrBuilder(int index) {
                return this.databases_.get(index);
            }

            private void ensureDatabasesIsMutable() {
                if (!this.databases_.isModifiable()) {
                    this.databases_ = GeneratedMessageLite.mutableCopy(this.databases_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDatabases(int index, Database value) {
                if (value != null) {
                    ensureDatabasesIsMutable();
                    this.databases_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDatabases(int index, Database.Builder builderForValue) {
                ensureDatabasesIsMutable();
                this.databases_.set(index, (Database) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addDatabases(Database value) {
                if (value != null) {
                    ensureDatabasesIsMutable();
                    this.databases_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addDatabases(int index, Database value) {
                if (value != null) {
                    ensureDatabasesIsMutable();
                    this.databases_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addDatabases(Database.Builder builderForValue) {
                ensureDatabasesIsMutable();
                this.databases_.add((Database) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addDatabases(int index, Database.Builder builderForValue) {
                ensureDatabasesIsMutable();
                this.databases_.add(index, (Database) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllDatabases(Iterable<? extends Database> values) {
                ensureDatabasesIsMutable();
                AbstractMessageLite.addAll(values, this.databases_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDatabases() {
                this.databases_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeDatabases(int index) {
                ensureDatabasesIsMutable();
                this.databases_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.memoryUsedKb_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.pagecacheOverflowKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.mallocSizeKb_);
                }
                for (int i = 0; i < this.databases_.size(); i++) {
                    output.writeMessage(4, this.databases_.get(i));
                }
                this.unknownFields.writeTo(output);
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.memoryUsedKb_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.pagecacheOverflowKb_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.mallocSizeKb_);
                }
                for (int i = 0; i < this.databases_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(4, this.databases_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static SqlStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static SqlStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static SqlStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static SqlStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static SqlStats parseFrom(InputStream input) throws IOException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static SqlStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static SqlStats parseDelimitedFrom(InputStream input) throws IOException {
                return (SqlStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static SqlStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (SqlStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static SqlStats parseFrom(CodedInputStream input) throws IOException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static SqlStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (SqlStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(SqlStats prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<SqlStats, Builder> implements SqlStatsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(SqlStats.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public boolean hasMemoryUsedKb() {
                    return ((SqlStats) this.instance).hasMemoryUsedKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public int getMemoryUsedKb() {
                    return ((SqlStats) this.instance).getMemoryUsedKb();
                }

                public Builder setMemoryUsedKb(int value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).setMemoryUsedKb(value);
                    return this;
                }

                public Builder clearMemoryUsedKb() {
                    copyOnWrite();
                    ((SqlStats) this.instance).clearMemoryUsedKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public boolean hasPagecacheOverflowKb() {
                    return ((SqlStats) this.instance).hasPagecacheOverflowKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public int getPagecacheOverflowKb() {
                    return ((SqlStats) this.instance).getPagecacheOverflowKb();
                }

                public Builder setPagecacheOverflowKb(int value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).setPagecacheOverflowKb(value);
                    return this;
                }

                public Builder clearPagecacheOverflowKb() {
                    copyOnWrite();
                    ((SqlStats) this.instance).clearPagecacheOverflowKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public boolean hasMallocSizeKb() {
                    return ((SqlStats) this.instance).hasMallocSizeKb();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public int getMallocSizeKb() {
                    return ((SqlStats) this.instance).getMallocSizeKb();
                }

                public Builder setMallocSizeKb(int value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).setMallocSizeKb(value);
                    return this;
                }

                public Builder clearMallocSizeKb() {
                    copyOnWrite();
                    ((SqlStats) this.instance).clearMallocSizeKb();
                    return this;
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public List<Database> getDatabasesList() {
                    return Collections.unmodifiableList(((SqlStats) this.instance).getDatabasesList());
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public int getDatabasesCount() {
                    return ((SqlStats) this.instance).getDatabasesCount();
                }

                @Override // com.android.server.am.MemInfoDumpProto.AppData.SqlStatsOrBuilder
                public Database getDatabases(int index) {
                    return ((SqlStats) this.instance).getDatabases(index);
                }

                public Builder setDatabases(int index, Database value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).setDatabases((SqlStats) index, (int) value);
                    return this;
                }

                public Builder setDatabases(int index, Database.Builder builderForValue) {
                    copyOnWrite();
                    ((SqlStats) this.instance).setDatabases((SqlStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addDatabases(Database value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).addDatabases((SqlStats) value);
                    return this;
                }

                public Builder addDatabases(int index, Database value) {
                    copyOnWrite();
                    ((SqlStats) this.instance).addDatabases((SqlStats) index, (int) value);
                    return this;
                }

                public Builder addDatabases(Database.Builder builderForValue) {
                    copyOnWrite();
                    ((SqlStats) this.instance).addDatabases((SqlStats) builderForValue);
                    return this;
                }

                public Builder addDatabases(int index, Database.Builder builderForValue) {
                    copyOnWrite();
                    ((SqlStats) this.instance).addDatabases((SqlStats) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllDatabases(Iterable<? extends Database> values) {
                    copyOnWrite();
                    ((SqlStats) this.instance).addAllDatabases(values);
                    return this;
                }

                public Builder clearDatabases() {
                    copyOnWrite();
                    ((SqlStats) this.instance).clearDatabases();
                    return this;
                }

                public Builder removeDatabases(int index) {
                    copyOnWrite();
                    ((SqlStats) this.instance).removeDatabases(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new SqlStats();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.databases_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder(null);
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        SqlStats other = (SqlStats) arg1;
                        this.memoryUsedKb_ = visitor.visitInt(hasMemoryUsedKb(), this.memoryUsedKb_, other.hasMemoryUsedKb(), other.memoryUsedKb_);
                        this.pagecacheOverflowKb_ = visitor.visitInt(hasPagecacheOverflowKb(), this.pagecacheOverflowKb_, other.hasPagecacheOverflowKb(), other.pagecacheOverflowKb_);
                        this.mallocSizeKb_ = visitor.visitInt(hasMallocSizeKb(), this.mallocSizeKb_, other.hasMallocSizeKb(), other.mallocSizeKb_);
                        this.databases_ = visitor.visitList(this.databases_, other.databases_);
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            this.bitField0_ |= other.bitField0_;
                        }
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                        boolean done = false;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 8) {
                                    this.bitField0_ |= 1;
                                    this.memoryUsedKb_ = input.readInt32();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.pagecacheOverflowKb_ = input.readInt32();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.mallocSizeKb_ = input.readInt32();
                                } else if (tag == 34) {
                                    if (!this.databases_.isModifiable()) {
                                        this.databases_ = GeneratedMessageLite.mutableCopy(this.databases_);
                                    }
                                    this.databases_.add((Database) input.readMessage(Database.parser(), extensionRegistry));
                                } else if (!parseUnknownField(tag, input)) {
                                    done = true;
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                        break;
                    case GET_DEFAULT_INSTANCE:
                        break;
                    case GET_PARSER:
                        if (PARSER == null) {
                            synchronized (SqlStats.class) {
                                if (PARSER == null) {
                                    PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                }
                            }
                        }
                        return PARSER;
                    default:
                        throw new UnsupportedOperationException();
                }
                return DEFAULT_INSTANCE;
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            public static SqlStats getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<SqlStats> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public boolean hasProcessMemory() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public ProcessMemory getProcessMemory() {
            ProcessMemory processMemory = this.processMemory_;
            return processMemory == null ? ProcessMemory.getDefaultInstance() : processMemory;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessMemory(ProcessMemory value) {
            if (value != null) {
                this.processMemory_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessMemory(ProcessMemory.Builder builderForValue) {
            this.processMemory_ = (ProcessMemory) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeProcessMemory(ProcessMemory value) {
            ProcessMemory processMemory = this.processMemory_;
            if (processMemory == null || processMemory == ProcessMemory.getDefaultInstance()) {
                this.processMemory_ = value;
            } else {
                this.processMemory_ = (ProcessMemory) ((ProcessMemory.Builder) ProcessMemory.newBuilder(this.processMemory_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessMemory() {
            this.processMemory_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public boolean hasObjects() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public ObjectStats getObjects() {
            ObjectStats objectStats = this.objects_;
            return objectStats == null ? ObjectStats.getDefaultInstance() : objectStats;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setObjects(ObjectStats value) {
            if (value != null) {
                this.objects_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setObjects(ObjectStats.Builder builderForValue) {
            this.objects_ = (ObjectStats) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeObjects(ObjectStats value) {
            ObjectStats objectStats = this.objects_;
            if (objectStats == null || objectStats == ObjectStats.getDefaultInstance()) {
                this.objects_ = value;
            } else {
                this.objects_ = (ObjectStats) ((ObjectStats.Builder) ObjectStats.newBuilder(this.objects_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearObjects() {
            this.objects_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public boolean hasSql() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public SqlStats getSql() {
            SqlStats sqlStats = this.sql_;
            return sqlStats == null ? SqlStats.getDefaultInstance() : sqlStats;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSql(SqlStats value) {
            if (value != null) {
                this.sql_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSql(SqlStats.Builder builderForValue) {
            this.sql_ = (SqlStats) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSql(SqlStats value) {
            SqlStats sqlStats = this.sql_;
            if (sqlStats == null || sqlStats == SqlStats.getDefaultInstance()) {
                this.sql_ = value;
            } else {
                this.sql_ = (SqlStats) ((SqlStats.Builder) SqlStats.newBuilder(this.sql_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSql() {
            this.sql_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public boolean hasAssetAllocations() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public String getAssetAllocations() {
            return this.assetAllocations_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public ByteString getAssetAllocationsBytes() {
            return ByteString.copyFromUtf8(this.assetAllocations_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAssetAllocations(String value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.assetAllocations_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAssetAllocations() {
            this.bitField0_ &= -9;
            this.assetAllocations_ = getDefaultInstance().getAssetAllocations();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAssetAllocationsBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.assetAllocations_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public boolean hasUnreachableMemory() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public String getUnreachableMemory() {
            return this.unreachableMemory_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
        public ByteString getUnreachableMemoryBytes() {
            return ByteString.copyFromUtf8(this.unreachableMemory_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnreachableMemory(String value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.unreachableMemory_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnreachableMemory() {
            this.bitField0_ &= -17;
            this.unreachableMemory_ = getDefaultInstance().getUnreachableMemory();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnreachableMemoryBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.unreachableMemory_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getProcessMemory());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getObjects());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getSql());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeString(4, getAssetAllocations());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeString(5, getUnreachableMemory());
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getProcessMemory());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getObjects());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getSql());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeStringSize(4, getAssetAllocations());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeStringSize(5, getUnreachableMemory());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AppData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AppData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AppData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AppData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AppData parseFrom(InputStream input) throws IOException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AppData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AppData parseDelimitedFrom(InputStream input) throws IOException {
            return (AppData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AppData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AppData parseFrom(CodedInputStream input) throws IOException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AppData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AppData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AppData, Builder> implements AppDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(AppData.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public boolean hasProcessMemory() {
                return ((AppData) this.instance).hasProcessMemory();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public ProcessMemory getProcessMemory() {
                return ((AppData) this.instance).getProcessMemory();
            }

            public Builder setProcessMemory(ProcessMemory value) {
                copyOnWrite();
                ((AppData) this.instance).setProcessMemory((AppData) value);
                return this;
            }

            public Builder setProcessMemory(ProcessMemory.Builder builderForValue) {
                copyOnWrite();
                ((AppData) this.instance).setProcessMemory((AppData) builderForValue);
                return this;
            }

            public Builder mergeProcessMemory(ProcessMemory value) {
                copyOnWrite();
                ((AppData) this.instance).mergeProcessMemory(value);
                return this;
            }

            public Builder clearProcessMemory() {
                copyOnWrite();
                ((AppData) this.instance).clearProcessMemory();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public boolean hasObjects() {
                return ((AppData) this.instance).hasObjects();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public ObjectStats getObjects() {
                return ((AppData) this.instance).getObjects();
            }

            public Builder setObjects(ObjectStats value) {
                copyOnWrite();
                ((AppData) this.instance).setObjects((AppData) value);
                return this;
            }

            public Builder setObjects(ObjectStats.Builder builderForValue) {
                copyOnWrite();
                ((AppData) this.instance).setObjects((AppData) builderForValue);
                return this;
            }

            public Builder mergeObjects(ObjectStats value) {
                copyOnWrite();
                ((AppData) this.instance).mergeObjects(value);
                return this;
            }

            public Builder clearObjects() {
                copyOnWrite();
                ((AppData) this.instance).clearObjects();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public boolean hasSql() {
                return ((AppData) this.instance).hasSql();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public SqlStats getSql() {
                return ((AppData) this.instance).getSql();
            }

            public Builder setSql(SqlStats value) {
                copyOnWrite();
                ((AppData) this.instance).setSql((AppData) value);
                return this;
            }

            public Builder setSql(SqlStats.Builder builderForValue) {
                copyOnWrite();
                ((AppData) this.instance).setSql((AppData) builderForValue);
                return this;
            }

            public Builder mergeSql(SqlStats value) {
                copyOnWrite();
                ((AppData) this.instance).mergeSql(value);
                return this;
            }

            public Builder clearSql() {
                copyOnWrite();
                ((AppData) this.instance).clearSql();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public boolean hasAssetAllocations() {
                return ((AppData) this.instance).hasAssetAllocations();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public String getAssetAllocations() {
                return ((AppData) this.instance).getAssetAllocations();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public ByteString getAssetAllocationsBytes() {
                return ((AppData) this.instance).getAssetAllocationsBytes();
            }

            public Builder setAssetAllocations(String value) {
                copyOnWrite();
                ((AppData) this.instance).setAssetAllocations(value);
                return this;
            }

            public Builder clearAssetAllocations() {
                copyOnWrite();
                ((AppData) this.instance).clearAssetAllocations();
                return this;
            }

            public Builder setAssetAllocationsBytes(ByteString value) {
                copyOnWrite();
                ((AppData) this.instance).setAssetAllocationsBytes(value);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public boolean hasUnreachableMemory() {
                return ((AppData) this.instance).hasUnreachableMemory();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public String getUnreachableMemory() {
                return ((AppData) this.instance).getUnreachableMemory();
            }

            @Override // com.android.server.am.MemInfoDumpProto.AppDataOrBuilder
            public ByteString getUnreachableMemoryBytes() {
                return ((AppData) this.instance).getUnreachableMemoryBytes();
            }

            public Builder setUnreachableMemory(String value) {
                copyOnWrite();
                ((AppData) this.instance).setUnreachableMemory(value);
                return this;
            }

            public Builder clearUnreachableMemory() {
                copyOnWrite();
                ((AppData) this.instance).clearUnreachableMemory();
                return this;
            }

            public Builder setUnreachableMemoryBytes(ByteString value) {
                copyOnWrite();
                ((AppData) this.instance).setUnreachableMemoryBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AppData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AppData other = (AppData) arg1;
                    this.processMemory_ = (ProcessMemory) visitor.visitMessage(this.processMemory_, other.processMemory_);
                    this.objects_ = (ObjectStats) visitor.visitMessage(this.objects_, other.objects_);
                    this.sql_ = (SqlStats) visitor.visitMessage(this.sql_, other.sql_);
                    this.assetAllocations_ = visitor.visitString(hasAssetAllocations(), this.assetAllocations_, other.hasAssetAllocations(), other.assetAllocations_);
                    this.unreachableMemory_ = visitor.visitString(hasUnreachableMemory(), this.unreachableMemory_, other.hasUnreachableMemory(), other.unreachableMemory_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                ProcessMemory.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ProcessMemory.Builder) this.processMemory_.toBuilder();
                                }
                                this.processMemory_ = (ProcessMemory) input.readMessage(ProcessMemory.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.processMemory_);
                                    this.processMemory_ = (ProcessMemory) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                ObjectStats.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (ObjectStats.Builder) this.objects_.toBuilder();
                                }
                                this.objects_ = (ObjectStats) input.readMessage(ObjectStats.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.objects_);
                                    this.objects_ = (ObjectStats) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SqlStats.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SqlStats.Builder) this.sql_.toBuilder();
                                }
                                this.sql_ = (SqlStats) input.readMessage(SqlStats.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.sql_);
                                    this.sql_ = (SqlStats) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                String s = input.readString();
                                this.bitField0_ |= 8;
                                this.assetAllocations_ = s;
                            } else if (tag == 42) {
                                String s2 = input.readString();
                                this.bitField0_ |= 16;
                                this.unreachableMemory_ = s2;
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (AppData.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static AppData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AppData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MemItem extends GeneratedMessageLite<MemItem, Builder> implements MemItemOrBuilder {
        private static final MemItem DEFAULT_INSTANCE = new MemItem();
        public static final int HAS_ACTIVITIES_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 3;
        public static final int IS_PROC_FIELD_NUMBER = 4;
        public static final int LABEL_FIELD_NUMBER = 2;
        private static volatile Parser<MemItem> PARSER = null;
        public static final int PSS_KB_FIELD_NUMBER = 6;
        public static final int SUB_ITEMS_FIELD_NUMBER = 8;
        public static final int SWAP_PSS_KB_FIELD_NUMBER = 7;
        public static final int TAG_FIELD_NUMBER = 1;
        private int bitField0_;
        private boolean hasActivities_ = false;
        private int id_ = 0;
        private boolean isProc_ = false;
        private String label_ = "";
        private long pssKb_ = 0;
        private Internal.ProtobufList<MemItem> subItems_ = emptyProtobufList();
        private long swapPssKb_ = 0;
        private String tag_ = "";

        private MemItem() {
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public String getTag() {
            return this.tag_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public ByteString getTagBytes() {
            return ByteString.copyFromUtf8(this.tag_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTag(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.tag_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTag() {
            this.bitField0_ &= -2;
            this.tag_ = getDefaultInstance().getTag();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTagBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.tag_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasLabel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public String getLabel() {
            return this.label_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public ByteString getLabelBytes() {
            return ByteString.copyFromUtf8(this.label_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabel(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.label_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLabel() {
            this.bitField0_ &= -3;
            this.label_ = getDefaultInstance().getLabel();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabelBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.label_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 4;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -5;
            this.id_ = 0;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasIsProc() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean getIsProc() {
            return this.isProc_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsProc(boolean value) {
            this.bitField0_ |= 8;
            this.isProc_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsProc() {
            this.bitField0_ &= -9;
            this.isProc_ = false;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasHasActivities() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean getHasActivities() {
            return this.hasActivities_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHasActivities(boolean value) {
            this.bitField0_ |= 16;
            this.hasActivities_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHasActivities() {
            this.bitField0_ &= -17;
            this.hasActivities_ = false;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasPssKb() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public long getPssKb() {
            return this.pssKb_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPssKb(long value) {
            this.bitField0_ |= 32;
            this.pssKb_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPssKb() {
            this.bitField0_ &= -33;
            this.pssKb_ = 0;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public boolean hasSwapPssKb() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public long getSwapPssKb() {
            return this.swapPssKb_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSwapPssKb(long value) {
            this.bitField0_ |= 64;
            this.swapPssKb_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSwapPssKb() {
            this.bitField0_ &= -65;
            this.swapPssKb_ = 0;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public List<MemItem> getSubItemsList() {
            return this.subItems_;
        }

        public List<? extends MemItemOrBuilder> getSubItemsOrBuilderList() {
            return this.subItems_;
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public int getSubItemsCount() {
            return this.subItems_.size();
        }

        @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
        public MemItem getSubItems(int index) {
            return this.subItems_.get(index);
        }

        public MemItemOrBuilder getSubItemsOrBuilder(int index) {
            return this.subItems_.get(index);
        }

        private void ensureSubItemsIsMutable() {
            if (!this.subItems_.isModifiable()) {
                this.subItems_ = GeneratedMessageLite.mutableCopy(this.subItems_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSubItems(int index, MemItem value) {
            if (value != null) {
                ensureSubItemsIsMutable();
                this.subItems_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSubItems(int index, Builder builderForValue) {
            ensureSubItemsIsMutable();
            this.subItems_.set(index, (MemItem) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubItems(MemItem value) {
            if (value != null) {
                ensureSubItemsIsMutable();
                this.subItems_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubItems(int index, MemItem value) {
            if (value != null) {
                ensureSubItemsIsMutable();
                this.subItems_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubItems(Builder builderForValue) {
            ensureSubItemsIsMutable();
            this.subItems_.add((MemItem) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSubItems(int index, Builder builderForValue) {
            ensureSubItemsIsMutable();
            this.subItems_.add(index, (MemItem) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSubItems(Iterable<? extends MemItem> values) {
            ensureSubItemsIsMutable();
            AbstractMessageLite.addAll(values, this.subItems_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSubItems() {
            this.subItems_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeSubItems(int index) {
            ensureSubItemsIsMutable();
            this.subItems_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getTag());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getLabel());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.id_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.isProc_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.hasActivities_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.pssKb_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.swapPssKb_);
            }
            for (int i = 0; i < this.subItems_.size(); i++) {
                output.writeMessage(8, this.subItems_.get(i));
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeStringSize(1, getTag());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getLabel());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.id_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.isProc_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.hasActivities_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.pssKb_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.swapPssKb_);
            }
            for (int i = 0; i < this.subItems_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(8, this.subItems_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MemItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemItem parseFrom(InputStream input) throws IOException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemItem parseDelimitedFrom(InputStream input) throws IOException {
            return (MemItem) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MemItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemItem) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemItem parseFrom(CodedInputStream input) throws IOException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MemItem prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MemItem, Builder> implements MemItemOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(MemItem.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasTag() {
                return ((MemItem) this.instance).hasTag();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public String getTag() {
                return ((MemItem) this.instance).getTag();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public ByteString getTagBytes() {
                return ((MemItem) this.instance).getTagBytes();
            }

            public Builder setTag(String value) {
                copyOnWrite();
                ((MemItem) this.instance).setTag(value);
                return this;
            }

            public Builder clearTag() {
                copyOnWrite();
                ((MemItem) this.instance).clearTag();
                return this;
            }

            public Builder setTagBytes(ByteString value) {
                copyOnWrite();
                ((MemItem) this.instance).setTagBytes(value);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasLabel() {
                return ((MemItem) this.instance).hasLabel();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public String getLabel() {
                return ((MemItem) this.instance).getLabel();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public ByteString getLabelBytes() {
                return ((MemItem) this.instance).getLabelBytes();
            }

            public Builder setLabel(String value) {
                copyOnWrite();
                ((MemItem) this.instance).setLabel(value);
                return this;
            }

            public Builder clearLabel() {
                copyOnWrite();
                ((MemItem) this.instance).clearLabel();
                return this;
            }

            public Builder setLabelBytes(ByteString value) {
                copyOnWrite();
                ((MemItem) this.instance).setLabelBytes(value);
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasId() {
                return ((MemItem) this.instance).hasId();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public int getId() {
                return ((MemItem) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((MemItem) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((MemItem) this.instance).clearId();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasIsProc() {
                return ((MemItem) this.instance).hasIsProc();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean getIsProc() {
                return ((MemItem) this.instance).getIsProc();
            }

            public Builder setIsProc(boolean value) {
                copyOnWrite();
                ((MemItem) this.instance).setIsProc(value);
                return this;
            }

            public Builder clearIsProc() {
                copyOnWrite();
                ((MemItem) this.instance).clearIsProc();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasHasActivities() {
                return ((MemItem) this.instance).hasHasActivities();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean getHasActivities() {
                return ((MemItem) this.instance).getHasActivities();
            }

            public Builder setHasActivities(boolean value) {
                copyOnWrite();
                ((MemItem) this.instance).setHasActivities(value);
                return this;
            }

            public Builder clearHasActivities() {
                copyOnWrite();
                ((MemItem) this.instance).clearHasActivities();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasPssKb() {
                return ((MemItem) this.instance).hasPssKb();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public long getPssKb() {
                return ((MemItem) this.instance).getPssKb();
            }

            public Builder setPssKb(long value) {
                copyOnWrite();
                ((MemItem) this.instance).setPssKb(value);
                return this;
            }

            public Builder clearPssKb() {
                copyOnWrite();
                ((MemItem) this.instance).clearPssKb();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public boolean hasSwapPssKb() {
                return ((MemItem) this.instance).hasSwapPssKb();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public long getSwapPssKb() {
                return ((MemItem) this.instance).getSwapPssKb();
            }

            public Builder setSwapPssKb(long value) {
                copyOnWrite();
                ((MemItem) this.instance).setSwapPssKb(value);
                return this;
            }

            public Builder clearSwapPssKb() {
                copyOnWrite();
                ((MemItem) this.instance).clearSwapPssKb();
                return this;
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public List<MemItem> getSubItemsList() {
                return Collections.unmodifiableList(((MemItem) this.instance).getSubItemsList());
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public int getSubItemsCount() {
                return ((MemItem) this.instance).getSubItemsCount();
            }

            @Override // com.android.server.am.MemInfoDumpProto.MemItemOrBuilder
            public MemItem getSubItems(int index) {
                return ((MemItem) this.instance).getSubItems(index);
            }

            public Builder setSubItems(int index, MemItem value) {
                copyOnWrite();
                ((MemItem) this.instance).setSubItems((MemItem) index, (int) value);
                return this;
            }

            public Builder setSubItems(int index, Builder builderForValue) {
                copyOnWrite();
                ((MemItem) this.instance).setSubItems((MemItem) index, (int) builderForValue);
                return this;
            }

            public Builder addSubItems(MemItem value) {
                copyOnWrite();
                ((MemItem) this.instance).addSubItems(value);
                return this;
            }

            public Builder addSubItems(int index, MemItem value) {
                copyOnWrite();
                ((MemItem) this.instance).addSubItems((MemItem) index, (int) value);
                return this;
            }

            public Builder addSubItems(Builder builderForValue) {
                copyOnWrite();
                ((MemItem) this.instance).addSubItems((MemItem) builderForValue);
                return this;
            }

            public Builder addSubItems(int index, Builder builderForValue) {
                copyOnWrite();
                ((MemItem) this.instance).addSubItems((MemItem) index, (int) builderForValue);
                return this;
            }

            public Builder addAllSubItems(Iterable<? extends MemItem> values) {
                copyOnWrite();
                ((MemItem) this.instance).addAllSubItems(values);
                return this;
            }

            public Builder clearSubItems() {
                copyOnWrite();
                ((MemItem) this.instance).clearSubItems();
                return this;
            }

            public Builder removeSubItems(int index) {
                copyOnWrite();
                ((MemItem) this.instance).removeSubItems(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MemItem();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.subItems_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MemItem other = (MemItem) arg1;
                    this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                    this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.isProc_ = visitor.visitBoolean(hasIsProc(), this.isProc_, other.hasIsProc(), other.isProc_);
                    this.hasActivities_ = visitor.visitBoolean(hasHasActivities(), this.hasActivities_, other.hasHasActivities(), other.hasActivities_);
                    this.pssKb_ = visitor.visitLong(hasPssKb(), this.pssKb_, other.hasPssKb(), other.pssKb_);
                    this.swapPssKb_ = visitor.visitLong(hasSwapPssKb(), this.swapPssKb_, other.hasSwapPssKb(), other.swapPssKb_);
                    this.subItems_ = visitor.visitList(this.subItems_, other.subItems_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.tag_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.label_ = s2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.id_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.isProc_ = input.readBool();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.hasActivities_ = input.readBool();
                            } else if (tag == 48) {
                                this.bitField0_ = 32 | this.bitField0_;
                                this.pssKb_ = input.readInt64();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.swapPssKb_ = input.readInt64();
                            } else if (tag == 66) {
                                if (!this.subItems_.isModifiable()) {
                                    this.subItems_ = GeneratedMessageLite.mutableCopy(this.subItems_);
                                }
                                this.subItems_.add((MemItem) input.readMessage(parser(), extensionRegistry));
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (MemItem.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static MemItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MemItem> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasUptimeDurationMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getUptimeDurationMs() {
        return this.uptimeDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUptimeDurationMs(long value) {
        this.bitField0_ |= 1;
        this.uptimeDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUptimeDurationMs() {
        this.bitField0_ &= -2;
        this.uptimeDurationMs_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasElapsedRealtimeMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getElapsedRealtimeMs() {
        return this.elapsedRealtimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElapsedRealtimeMs(long value) {
        this.bitField0_ |= 2;
        this.elapsedRealtimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearElapsedRealtimeMs() {
        this.bitField0_ &= -3;
        this.elapsedRealtimeMs_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public List<ProcessMemory> getNativeProcessesList() {
        return this.nativeProcesses_;
    }

    public List<? extends ProcessMemoryOrBuilder> getNativeProcessesOrBuilderList() {
        return this.nativeProcesses_;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getNativeProcessesCount() {
        return this.nativeProcesses_.size();
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public ProcessMemory getNativeProcesses(int index) {
        return this.nativeProcesses_.get(index);
    }

    public ProcessMemoryOrBuilder getNativeProcessesOrBuilder(int index) {
        return this.nativeProcesses_.get(index);
    }

    private void ensureNativeProcessesIsMutable() {
        if (!this.nativeProcesses_.isModifiable()) {
            this.nativeProcesses_ = GeneratedMessageLite.mutableCopy(this.nativeProcesses_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeProcesses(int index, ProcessMemory value) {
        if (value != null) {
            ensureNativeProcessesIsMutable();
            this.nativeProcesses_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeProcesses(int index, ProcessMemory.Builder builderForValue) {
        ensureNativeProcessesIsMutable();
        this.nativeProcesses_.set(index, (ProcessMemory) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNativeProcesses(ProcessMemory value) {
        if (value != null) {
            ensureNativeProcessesIsMutable();
            this.nativeProcesses_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNativeProcesses(int index, ProcessMemory value) {
        if (value != null) {
            ensureNativeProcessesIsMutable();
            this.nativeProcesses_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNativeProcesses(ProcessMemory.Builder builderForValue) {
        ensureNativeProcessesIsMutable();
        this.nativeProcesses_.add((ProcessMemory) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNativeProcesses(int index, ProcessMemory.Builder builderForValue) {
        ensureNativeProcessesIsMutable();
        this.nativeProcesses_.add(index, (ProcessMemory) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllNativeProcesses(Iterable<? extends ProcessMemory> values) {
        ensureNativeProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.nativeProcesses_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNativeProcesses() {
        this.nativeProcesses_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeNativeProcesses(int index) {
        ensureNativeProcessesIsMutable();
        this.nativeProcesses_.remove(index);
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public List<AppData> getAppProcessesList() {
        return this.appProcesses_;
    }

    public List<? extends AppDataOrBuilder> getAppProcessesOrBuilderList() {
        return this.appProcesses_;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getAppProcessesCount() {
        return this.appProcesses_.size();
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public AppData getAppProcesses(int index) {
        return this.appProcesses_.get(index);
    }

    public AppDataOrBuilder getAppProcessesOrBuilder(int index) {
        return this.appProcesses_.get(index);
    }

    private void ensureAppProcessesIsMutable() {
        if (!this.appProcesses_.isModifiable()) {
            this.appProcesses_ = GeneratedMessageLite.mutableCopy(this.appProcesses_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppProcesses(int index, AppData value) {
        if (value != null) {
            ensureAppProcessesIsMutable();
            this.appProcesses_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppProcesses(int index, AppData.Builder builderForValue) {
        ensureAppProcessesIsMutable();
        this.appProcesses_.set(index, (AppData) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppProcesses(AppData value) {
        if (value != null) {
            ensureAppProcessesIsMutable();
            this.appProcesses_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppProcesses(int index, AppData value) {
        if (value != null) {
            ensureAppProcessesIsMutable();
            this.appProcesses_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppProcesses(AppData.Builder builderForValue) {
        ensureAppProcessesIsMutable();
        this.appProcesses_.add((AppData) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppProcesses(int index, AppData.Builder builderForValue) {
        ensureAppProcessesIsMutable();
        this.appProcesses_.add(index, (AppData) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAppProcesses(Iterable<? extends AppData> values) {
        ensureAppProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.appProcesses_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppProcesses() {
        this.appProcesses_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAppProcesses(int index) {
        ensureAppProcessesIsMutable();
        this.appProcesses_.remove(index);
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public List<MemItem> getTotalPssByProcessList() {
        return this.totalPssByProcess_;
    }

    public List<? extends MemItemOrBuilder> getTotalPssByProcessOrBuilderList() {
        return this.totalPssByProcess_;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getTotalPssByProcessCount() {
        return this.totalPssByProcess_.size();
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public MemItem getTotalPssByProcess(int index) {
        return this.totalPssByProcess_.get(index);
    }

    public MemItemOrBuilder getTotalPssByProcessOrBuilder(int index) {
        return this.totalPssByProcess_.get(index);
    }

    private void ensureTotalPssByProcessIsMutable() {
        if (!this.totalPssByProcess_.isModifiable()) {
            this.totalPssByProcess_ = GeneratedMessageLite.mutableCopy(this.totalPssByProcess_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByProcess(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByProcessIsMutable();
            this.totalPssByProcess_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByProcess(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByProcessIsMutable();
        this.totalPssByProcess_.set(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByProcess(MemItem value) {
        if (value != null) {
            ensureTotalPssByProcessIsMutable();
            this.totalPssByProcess_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByProcess(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByProcessIsMutable();
            this.totalPssByProcess_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByProcess(MemItem.Builder builderForValue) {
        ensureTotalPssByProcessIsMutable();
        this.totalPssByProcess_.add((MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByProcess(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByProcessIsMutable();
        this.totalPssByProcess_.add(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTotalPssByProcess(Iterable<? extends MemItem> values) {
        ensureTotalPssByProcessIsMutable();
        AbstractMessageLite.addAll(values, this.totalPssByProcess_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalPssByProcess() {
        this.totalPssByProcess_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTotalPssByProcess(int index) {
        ensureTotalPssByProcessIsMutable();
        this.totalPssByProcess_.remove(index);
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public List<MemItem> getTotalPssByOomAdjustmentList() {
        return this.totalPssByOomAdjustment_;
    }

    public List<? extends MemItemOrBuilder> getTotalPssByOomAdjustmentOrBuilderList() {
        return this.totalPssByOomAdjustment_;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getTotalPssByOomAdjustmentCount() {
        return this.totalPssByOomAdjustment_.size();
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public MemItem getTotalPssByOomAdjustment(int index) {
        return this.totalPssByOomAdjustment_.get(index);
    }

    public MemItemOrBuilder getTotalPssByOomAdjustmentOrBuilder(int index) {
        return this.totalPssByOomAdjustment_.get(index);
    }

    private void ensureTotalPssByOomAdjustmentIsMutable() {
        if (!this.totalPssByOomAdjustment_.isModifiable()) {
            this.totalPssByOomAdjustment_ = GeneratedMessageLite.mutableCopy(this.totalPssByOomAdjustment_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByOomAdjustment(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByOomAdjustmentIsMutable();
            this.totalPssByOomAdjustment_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByOomAdjustment(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByOomAdjustmentIsMutable();
        this.totalPssByOomAdjustment_.set(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByOomAdjustment(MemItem value) {
        if (value != null) {
            ensureTotalPssByOomAdjustmentIsMutable();
            this.totalPssByOomAdjustment_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByOomAdjustment(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByOomAdjustmentIsMutable();
            this.totalPssByOomAdjustment_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByOomAdjustment(MemItem.Builder builderForValue) {
        ensureTotalPssByOomAdjustmentIsMutable();
        this.totalPssByOomAdjustment_.add((MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByOomAdjustment(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByOomAdjustmentIsMutable();
        this.totalPssByOomAdjustment_.add(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTotalPssByOomAdjustment(Iterable<? extends MemItem> values) {
        ensureTotalPssByOomAdjustmentIsMutable();
        AbstractMessageLite.addAll(values, this.totalPssByOomAdjustment_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalPssByOomAdjustment() {
        this.totalPssByOomAdjustment_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTotalPssByOomAdjustment(int index) {
        ensureTotalPssByOomAdjustmentIsMutable();
        this.totalPssByOomAdjustment_.remove(index);
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public List<MemItem> getTotalPssByCategoryList() {
        return this.totalPssByCategory_;
    }

    public List<? extends MemItemOrBuilder> getTotalPssByCategoryOrBuilderList() {
        return this.totalPssByCategory_;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getTotalPssByCategoryCount() {
        return this.totalPssByCategory_.size();
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public MemItem getTotalPssByCategory(int index) {
        return this.totalPssByCategory_.get(index);
    }

    public MemItemOrBuilder getTotalPssByCategoryOrBuilder(int index) {
        return this.totalPssByCategory_.get(index);
    }

    private void ensureTotalPssByCategoryIsMutable() {
        if (!this.totalPssByCategory_.isModifiable()) {
            this.totalPssByCategory_ = GeneratedMessageLite.mutableCopy(this.totalPssByCategory_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByCategory(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByCategoryIsMutable();
            this.totalPssByCategory_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalPssByCategory(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByCategoryIsMutable();
        this.totalPssByCategory_.set(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByCategory(MemItem value) {
        if (value != null) {
            ensureTotalPssByCategoryIsMutable();
            this.totalPssByCategory_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByCategory(int index, MemItem value) {
        if (value != null) {
            ensureTotalPssByCategoryIsMutable();
            this.totalPssByCategory_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByCategory(MemItem.Builder builderForValue) {
        ensureTotalPssByCategoryIsMutable();
        this.totalPssByCategory_.add((MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTotalPssByCategory(int index, MemItem.Builder builderForValue) {
        ensureTotalPssByCategoryIsMutable();
        this.totalPssByCategory_.add(index, (MemItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTotalPssByCategory(Iterable<? extends MemItem> values) {
        ensureTotalPssByCategoryIsMutable();
        AbstractMessageLite.addAll(values, this.totalPssByCategory_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalPssByCategory() {
        this.totalPssByCategory_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTotalPssByCategory(int index) {
        ensureTotalPssByCategoryIsMutable();
        this.totalPssByCategory_.remove(index);
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasTotalRamKb() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getTotalRamKb() {
        return this.totalRamKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalRamKb(long value) {
        this.bitField0_ |= 4;
        this.totalRamKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalRamKb() {
        this.bitField0_ &= -5;
        this.totalRamKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasStatus() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public Processstats.ProcessStatsProto.MemoryFactor getStatus() {
        Processstats.ProcessStatsProto.MemoryFactor result = Processstats.ProcessStatsProto.MemoryFactor.forNumber(this.status_);
        return result == null ? Processstats.ProcessStatsProto.MemoryFactor.MEM_FACTOR_NORMAL : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(Processstats.ProcessStatsProto.MemoryFactor value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.status_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.bitField0_ &= -9;
        this.status_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasCachedPssKb() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getCachedPssKb() {
        return this.cachedPssKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedPssKb(long value) {
        this.bitField0_ |= 16;
        this.cachedPssKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCachedPssKb() {
        this.bitField0_ &= -17;
        this.cachedPssKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasCachedKernelKb() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getCachedKernelKb() {
        return this.cachedKernelKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedKernelKb(long value) {
        this.bitField0_ |= 32;
        this.cachedKernelKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCachedKernelKb() {
        this.bitField0_ &= -33;
        this.cachedKernelKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasFreeKb() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getFreeKb() {
        return this.freeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFreeKb(long value) {
        this.bitField0_ |= 64;
        this.freeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFreeKb() {
        this.bitField0_ &= -65;
        this.freeKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasUsedPssKb() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getUsedPssKb() {
        return this.usedPssKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsedPssKb(long value) {
        this.bitField0_ |= 128;
        this.usedPssKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsedPssKb() {
        this.bitField0_ &= -129;
        this.usedPssKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasUsedKernelKb() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getUsedKernelKb() {
        return this.usedKernelKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsedKernelKb(long value) {
        this.bitField0_ |= 256;
        this.usedKernelKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsedKernelKb() {
        this.bitField0_ &= -257;
        this.usedKernelKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasLostRamKb() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getLostRamKb() {
        return this.lostRamKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLostRamKb(long value) {
        this.bitField0_ |= 512;
        this.lostRamKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLostRamKb() {
        this.bitField0_ &= -513;
        this.lostRamKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasTotalZramKb() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getTotalZramKb() {
        return this.totalZramKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalZramKb(long value) {
        this.bitField0_ |= 1024;
        this.totalZramKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalZramKb() {
        this.bitField0_ &= -1025;
        this.totalZramKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasZramPhysicalUsedInSwapKb() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getZramPhysicalUsedInSwapKb() {
        return this.zramPhysicalUsedInSwapKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZramPhysicalUsedInSwapKb(long value) {
        this.bitField0_ |= 2048;
        this.zramPhysicalUsedInSwapKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZramPhysicalUsedInSwapKb() {
        this.bitField0_ &= -2049;
        this.zramPhysicalUsedInSwapKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasTotalZramSwapKb() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getTotalZramSwapKb() {
        return this.totalZramSwapKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalZramSwapKb(long value) {
        this.bitField0_ |= 4096;
        this.totalZramSwapKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalZramSwapKb() {
        this.bitField0_ &= -4097;
        this.totalZramSwapKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasKsmSharingKb() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getKsmSharingKb() {
        return this.ksmSharingKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKsmSharingKb(long value) {
        this.bitField0_ |= 8192;
        this.ksmSharingKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKsmSharingKb() {
        this.bitField0_ &= -8193;
        this.ksmSharingKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasKsmSharedKb() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getKsmSharedKb() {
        return this.ksmSharedKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKsmSharedKb(long value) {
        this.bitField0_ |= 16384;
        this.ksmSharedKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKsmSharedKb() {
        this.bitField0_ &= -16385;
        this.ksmSharedKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasKsmUnsharedKb() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getKsmUnsharedKb() {
        return this.ksmUnsharedKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKsmUnsharedKb(long value) {
        this.bitField0_ |= 32768;
        this.ksmUnsharedKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKsmUnsharedKb() {
        this.bitField0_ &= -32769;
        this.ksmUnsharedKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasKsmVolatileKb() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getKsmVolatileKb() {
        return this.ksmVolatileKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKsmVolatileKb(long value) {
        this.bitField0_ |= 65536;
        this.ksmVolatileKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKsmVolatileKb() {
        this.bitField0_ &= -65537;
        this.ksmVolatileKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasTuningMb() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getTuningMb() {
        return this.tuningMb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTuningMb(int value) {
        this.bitField0_ |= 131072;
        this.tuningMb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTuningMb() {
        this.bitField0_ &= -131073;
        this.tuningMb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasTuningLargeMb() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public int getTuningLargeMb() {
        return this.tuningLargeMb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTuningLargeMb(int value) {
        this.bitField0_ |= 262144;
        this.tuningLargeMb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTuningLargeMb() {
        this.bitField0_ &= -262145;
        this.tuningLargeMb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasOomKb() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getOomKb() {
        return this.oomKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOomKb(long value) {
        this.bitField0_ |= 524288;
        this.oomKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOomKb() {
        this.bitField0_ &= -524289;
        this.oomKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasRestoreLimitKb() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public long getRestoreLimitKb() {
        return this.restoreLimitKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRestoreLimitKb(long value) {
        this.bitField0_ |= 1048576;
        this.restoreLimitKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRestoreLimitKb() {
        this.bitField0_ &= -1048577;
        this.restoreLimitKb_ = 0;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasIsLowRamDevice() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean getIsLowRamDevice() {
        return this.isLowRamDevice_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsLowRamDevice(boolean value) {
        this.bitField0_ |= 2097152;
        this.isLowRamDevice_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsLowRamDevice() {
        this.bitField0_ &= -2097153;
        this.isLowRamDevice_ = false;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean hasIsHighEndGfx() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
    public boolean getIsHighEndGfx() {
        return this.isHighEndGfx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHighEndGfx(boolean value) {
        this.bitField0_ |= 4194304;
        this.isHighEndGfx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHighEndGfx() {
        this.bitField0_ &= -4194305;
        this.isHighEndGfx_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.uptimeDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.elapsedRealtimeMs_);
        }
        for (int i = 0; i < this.nativeProcesses_.size(); i++) {
            output.writeMessage(3, this.nativeProcesses_.get(i));
        }
        for (int i2 = 0; i2 < this.appProcesses_.size(); i2++) {
            output.writeMessage(4, this.appProcesses_.get(i2));
        }
        for (int i3 = 0; i3 < this.totalPssByProcess_.size(); i3++) {
            output.writeMessage(5, this.totalPssByProcess_.get(i3));
        }
        for (int i4 = 0; i4 < this.totalPssByOomAdjustment_.size(); i4++) {
            output.writeMessage(6, this.totalPssByOomAdjustment_.get(i4));
        }
        for (int i5 = 0; i5 < this.totalPssByCategory_.size(); i5++) {
            output.writeMessage(7, this.totalPssByCategory_.get(i5));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(8, this.totalRamKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(9, this.status_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(10, this.cachedPssKb_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(11, this.cachedKernelKb_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt64(12, this.freeKb_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt64(13, this.usedPssKb_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt64(14, this.usedKernelKb_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt64(15, this.lostRamKb_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeInt64(16, this.totalZramKb_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeInt64(17, this.zramPhysicalUsedInSwapKb_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeInt64(18, this.totalZramSwapKb_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeInt64(19, this.ksmSharingKb_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeInt64(20, this.ksmSharedKb_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt64(21, this.ksmUnsharedKb_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt64(22, this.ksmVolatileKb_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeInt32(23, this.tuningMb_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt32(24, this.tuningLargeMb_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeInt64(25, this.oomKb_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeInt64(26, this.restoreLimitKb_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeBool(27, this.isLowRamDevice_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeBool(28, this.isHighEndGfx_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.uptimeDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.elapsedRealtimeMs_);
        }
        for (int i = 0; i < this.nativeProcesses_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.nativeProcesses_.get(i));
        }
        for (int i2 = 0; i2 < this.appProcesses_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.appProcesses_.get(i2));
        }
        for (int i3 = 0; i3 < this.totalPssByProcess_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.totalPssByProcess_.get(i3));
        }
        for (int i4 = 0; i4 < this.totalPssByOomAdjustment_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.totalPssByOomAdjustment_.get(i4));
        }
        for (int i5 = 0; i5 < this.totalPssByCategory_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.totalPssByCategory_.get(i5));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(8, this.totalRamKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(9, this.status_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(10, this.cachedPssKb_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(11, this.cachedKernelKb_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt64Size(12, this.freeKb_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt64Size(13, this.usedPssKb_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt64Size(14, this.usedKernelKb_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt64Size(15, this.lostRamKb_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeInt64Size(16, this.totalZramKb_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeInt64Size(17, this.zramPhysicalUsedInSwapKb_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeInt64Size(18, this.totalZramSwapKb_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeInt64Size(19, this.ksmSharingKb_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeInt64Size(20, this.ksmSharedKb_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeInt64Size(21, this.ksmUnsharedKb_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt64Size(22, this.ksmVolatileKb_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeInt32Size(23, this.tuningMb_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeInt32Size(24, this.tuningLargeMb_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeInt64Size(25, this.oomKb_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeInt64Size(26, this.restoreLimitKb_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeBoolSize(27, this.isLowRamDevice_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeBoolSize(28, this.isHighEndGfx_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MemInfoDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MemInfoDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MemInfoDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MemInfoDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MemInfoDumpProto parseFrom(InputStream input) throws IOException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MemInfoDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MemInfoDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MemInfoDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MemInfoDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MemInfoDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MemInfoDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MemInfoDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MemInfoDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MemInfoDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MemInfoDumpProto, Builder> implements MemInfoDumpProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(MemInfoDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasUptimeDurationMs() {
            return ((MemInfoDumpProto) this.instance).hasUptimeDurationMs();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getUptimeDurationMs() {
            return ((MemInfoDumpProto) this.instance).getUptimeDurationMs();
        }

        public Builder setUptimeDurationMs(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setUptimeDurationMs(value);
            return this;
        }

        public Builder clearUptimeDurationMs() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearUptimeDurationMs();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasElapsedRealtimeMs() {
            return ((MemInfoDumpProto) this.instance).hasElapsedRealtimeMs();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getElapsedRealtimeMs() {
            return ((MemInfoDumpProto) this.instance).getElapsedRealtimeMs();
        }

        public Builder setElapsedRealtimeMs(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setElapsedRealtimeMs(value);
            return this;
        }

        public Builder clearElapsedRealtimeMs() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearElapsedRealtimeMs();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public List<ProcessMemory> getNativeProcessesList() {
            return Collections.unmodifiableList(((MemInfoDumpProto) this.instance).getNativeProcessesList());
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getNativeProcessesCount() {
            return ((MemInfoDumpProto) this.instance).getNativeProcessesCount();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public ProcessMemory getNativeProcesses(int index) {
            return ((MemInfoDumpProto) this.instance).getNativeProcesses(index);
        }

        public Builder setNativeProcesses(int index, ProcessMemory value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setNativeProcesses((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder setNativeProcesses(int index, ProcessMemory.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setNativeProcesses((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addNativeProcesses(ProcessMemory value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addNativeProcesses((MemInfoDumpProto) value);
            return this;
        }

        public Builder addNativeProcesses(int index, ProcessMemory value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addNativeProcesses((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder addNativeProcesses(ProcessMemory.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addNativeProcesses((MemInfoDumpProto) builderForValue);
            return this;
        }

        public Builder addNativeProcesses(int index, ProcessMemory.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addNativeProcesses((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllNativeProcesses(Iterable<? extends ProcessMemory> values) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAllNativeProcesses(values);
            return this;
        }

        public Builder clearNativeProcesses() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearNativeProcesses();
            return this;
        }

        public Builder removeNativeProcesses(int index) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).removeNativeProcesses(index);
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public List<AppData> getAppProcessesList() {
            return Collections.unmodifiableList(((MemInfoDumpProto) this.instance).getAppProcessesList());
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getAppProcessesCount() {
            return ((MemInfoDumpProto) this.instance).getAppProcessesCount();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public AppData getAppProcesses(int index) {
            return ((MemInfoDumpProto) this.instance).getAppProcesses(index);
        }

        public Builder setAppProcesses(int index, AppData value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setAppProcesses((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder setAppProcesses(int index, AppData.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setAppProcesses((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAppProcesses(AppData value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAppProcesses((MemInfoDumpProto) value);
            return this;
        }

        public Builder addAppProcesses(int index, AppData value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAppProcesses((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder addAppProcesses(AppData.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAppProcesses((MemInfoDumpProto) builderForValue);
            return this;
        }

        public Builder addAppProcesses(int index, AppData.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAppProcesses((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAppProcesses(Iterable<? extends AppData> values) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAllAppProcesses(values);
            return this;
        }

        public Builder clearAppProcesses() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearAppProcesses();
            return this;
        }

        public Builder removeAppProcesses(int index) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).removeAppProcesses(index);
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public List<MemItem> getTotalPssByProcessList() {
            return Collections.unmodifiableList(((MemInfoDumpProto) this.instance).getTotalPssByProcessList());
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getTotalPssByProcessCount() {
            return ((MemInfoDumpProto) this.instance).getTotalPssByProcessCount();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public MemItem getTotalPssByProcess(int index) {
            return ((MemInfoDumpProto) this.instance).getTotalPssByProcess(index);
        }

        public Builder setTotalPssByProcess(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByProcess((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder setTotalPssByProcess(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByProcess((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTotalPssByProcess(MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByProcess((MemInfoDumpProto) value);
            return this;
        }

        public Builder addTotalPssByProcess(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByProcess((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder addTotalPssByProcess(MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByProcess((MemInfoDumpProto) builderForValue);
            return this;
        }

        public Builder addTotalPssByProcess(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByProcess((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTotalPssByProcess(Iterable<? extends MemItem> values) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAllTotalPssByProcess(values);
            return this;
        }

        public Builder clearTotalPssByProcess() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalPssByProcess();
            return this;
        }

        public Builder removeTotalPssByProcess(int index) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).removeTotalPssByProcess(index);
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public List<MemItem> getTotalPssByOomAdjustmentList() {
            return Collections.unmodifiableList(((MemInfoDumpProto) this.instance).getTotalPssByOomAdjustmentList());
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getTotalPssByOomAdjustmentCount() {
            return ((MemInfoDumpProto) this.instance).getTotalPssByOomAdjustmentCount();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public MemItem getTotalPssByOomAdjustment(int index) {
            return ((MemInfoDumpProto) this.instance).getTotalPssByOomAdjustment(index);
        }

        public Builder setTotalPssByOomAdjustment(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByOomAdjustment((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder setTotalPssByOomAdjustment(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByOomAdjustment((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTotalPssByOomAdjustment(MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByOomAdjustment((MemInfoDumpProto) value);
            return this;
        }

        public Builder addTotalPssByOomAdjustment(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByOomAdjustment((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder addTotalPssByOomAdjustment(MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByOomAdjustment((MemInfoDumpProto) builderForValue);
            return this;
        }

        public Builder addTotalPssByOomAdjustment(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByOomAdjustment((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTotalPssByOomAdjustment(Iterable<? extends MemItem> values) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAllTotalPssByOomAdjustment(values);
            return this;
        }

        public Builder clearTotalPssByOomAdjustment() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalPssByOomAdjustment();
            return this;
        }

        public Builder removeTotalPssByOomAdjustment(int index) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).removeTotalPssByOomAdjustment(index);
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public List<MemItem> getTotalPssByCategoryList() {
            return Collections.unmodifiableList(((MemInfoDumpProto) this.instance).getTotalPssByCategoryList());
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getTotalPssByCategoryCount() {
            return ((MemInfoDumpProto) this.instance).getTotalPssByCategoryCount();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public MemItem getTotalPssByCategory(int index) {
            return ((MemInfoDumpProto) this.instance).getTotalPssByCategory(index);
        }

        public Builder setTotalPssByCategory(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByCategory((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder setTotalPssByCategory(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalPssByCategory((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTotalPssByCategory(MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByCategory((MemInfoDumpProto) value);
            return this;
        }

        public Builder addTotalPssByCategory(int index, MemItem value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByCategory((MemInfoDumpProto) index, (int) value);
            return this;
        }

        public Builder addTotalPssByCategory(MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByCategory((MemInfoDumpProto) builderForValue);
            return this;
        }

        public Builder addTotalPssByCategory(int index, MemItem.Builder builderForValue) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addTotalPssByCategory((MemInfoDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTotalPssByCategory(Iterable<? extends MemItem> values) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).addAllTotalPssByCategory(values);
            return this;
        }

        public Builder clearTotalPssByCategory() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalPssByCategory();
            return this;
        }

        public Builder removeTotalPssByCategory(int index) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).removeTotalPssByCategory(index);
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasTotalRamKb() {
            return ((MemInfoDumpProto) this.instance).hasTotalRamKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getTotalRamKb() {
            return ((MemInfoDumpProto) this.instance).getTotalRamKb();
        }

        public Builder setTotalRamKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalRamKb(value);
            return this;
        }

        public Builder clearTotalRamKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalRamKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasStatus() {
            return ((MemInfoDumpProto) this.instance).hasStatus();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public Processstats.ProcessStatsProto.MemoryFactor getStatus() {
            return ((MemInfoDumpProto) this.instance).getStatus();
        }

        public Builder setStatus(Processstats.ProcessStatsProto.MemoryFactor value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearStatus();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasCachedPssKb() {
            return ((MemInfoDumpProto) this.instance).hasCachedPssKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getCachedPssKb() {
            return ((MemInfoDumpProto) this.instance).getCachedPssKb();
        }

        public Builder setCachedPssKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setCachedPssKb(value);
            return this;
        }

        public Builder clearCachedPssKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearCachedPssKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasCachedKernelKb() {
            return ((MemInfoDumpProto) this.instance).hasCachedKernelKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getCachedKernelKb() {
            return ((MemInfoDumpProto) this.instance).getCachedKernelKb();
        }

        public Builder setCachedKernelKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setCachedKernelKb(value);
            return this;
        }

        public Builder clearCachedKernelKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearCachedKernelKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasFreeKb() {
            return ((MemInfoDumpProto) this.instance).hasFreeKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getFreeKb() {
            return ((MemInfoDumpProto) this.instance).getFreeKb();
        }

        public Builder setFreeKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setFreeKb(value);
            return this;
        }

        public Builder clearFreeKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearFreeKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasUsedPssKb() {
            return ((MemInfoDumpProto) this.instance).hasUsedPssKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getUsedPssKb() {
            return ((MemInfoDumpProto) this.instance).getUsedPssKb();
        }

        public Builder setUsedPssKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setUsedPssKb(value);
            return this;
        }

        public Builder clearUsedPssKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearUsedPssKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasUsedKernelKb() {
            return ((MemInfoDumpProto) this.instance).hasUsedKernelKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getUsedKernelKb() {
            return ((MemInfoDumpProto) this.instance).getUsedKernelKb();
        }

        public Builder setUsedKernelKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setUsedKernelKb(value);
            return this;
        }

        public Builder clearUsedKernelKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearUsedKernelKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasLostRamKb() {
            return ((MemInfoDumpProto) this.instance).hasLostRamKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getLostRamKb() {
            return ((MemInfoDumpProto) this.instance).getLostRamKb();
        }

        public Builder setLostRamKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setLostRamKb(value);
            return this;
        }

        public Builder clearLostRamKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearLostRamKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasTotalZramKb() {
            return ((MemInfoDumpProto) this.instance).hasTotalZramKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getTotalZramKb() {
            return ((MemInfoDumpProto) this.instance).getTotalZramKb();
        }

        public Builder setTotalZramKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalZramKb(value);
            return this;
        }

        public Builder clearTotalZramKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalZramKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasZramPhysicalUsedInSwapKb() {
            return ((MemInfoDumpProto) this.instance).hasZramPhysicalUsedInSwapKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getZramPhysicalUsedInSwapKb() {
            return ((MemInfoDumpProto) this.instance).getZramPhysicalUsedInSwapKb();
        }

        public Builder setZramPhysicalUsedInSwapKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setZramPhysicalUsedInSwapKb(value);
            return this;
        }

        public Builder clearZramPhysicalUsedInSwapKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearZramPhysicalUsedInSwapKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasTotalZramSwapKb() {
            return ((MemInfoDumpProto) this.instance).hasTotalZramSwapKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getTotalZramSwapKb() {
            return ((MemInfoDumpProto) this.instance).getTotalZramSwapKb();
        }

        public Builder setTotalZramSwapKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTotalZramSwapKb(value);
            return this;
        }

        public Builder clearTotalZramSwapKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTotalZramSwapKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasKsmSharingKb() {
            return ((MemInfoDumpProto) this.instance).hasKsmSharingKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getKsmSharingKb() {
            return ((MemInfoDumpProto) this.instance).getKsmSharingKb();
        }

        public Builder setKsmSharingKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setKsmSharingKb(value);
            return this;
        }

        public Builder clearKsmSharingKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearKsmSharingKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasKsmSharedKb() {
            return ((MemInfoDumpProto) this.instance).hasKsmSharedKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getKsmSharedKb() {
            return ((MemInfoDumpProto) this.instance).getKsmSharedKb();
        }

        public Builder setKsmSharedKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setKsmSharedKb(value);
            return this;
        }

        public Builder clearKsmSharedKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearKsmSharedKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasKsmUnsharedKb() {
            return ((MemInfoDumpProto) this.instance).hasKsmUnsharedKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getKsmUnsharedKb() {
            return ((MemInfoDumpProto) this.instance).getKsmUnsharedKb();
        }

        public Builder setKsmUnsharedKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setKsmUnsharedKb(value);
            return this;
        }

        public Builder clearKsmUnsharedKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearKsmUnsharedKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasKsmVolatileKb() {
            return ((MemInfoDumpProto) this.instance).hasKsmVolatileKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getKsmVolatileKb() {
            return ((MemInfoDumpProto) this.instance).getKsmVolatileKb();
        }

        public Builder setKsmVolatileKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setKsmVolatileKb(value);
            return this;
        }

        public Builder clearKsmVolatileKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearKsmVolatileKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasTuningMb() {
            return ((MemInfoDumpProto) this.instance).hasTuningMb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getTuningMb() {
            return ((MemInfoDumpProto) this.instance).getTuningMb();
        }

        public Builder setTuningMb(int value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTuningMb(value);
            return this;
        }

        public Builder clearTuningMb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTuningMb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasTuningLargeMb() {
            return ((MemInfoDumpProto) this.instance).hasTuningLargeMb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public int getTuningLargeMb() {
            return ((MemInfoDumpProto) this.instance).getTuningLargeMb();
        }

        public Builder setTuningLargeMb(int value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setTuningLargeMb(value);
            return this;
        }

        public Builder clearTuningLargeMb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearTuningLargeMb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasOomKb() {
            return ((MemInfoDumpProto) this.instance).hasOomKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getOomKb() {
            return ((MemInfoDumpProto) this.instance).getOomKb();
        }

        public Builder setOomKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setOomKb(value);
            return this;
        }

        public Builder clearOomKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearOomKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasRestoreLimitKb() {
            return ((MemInfoDumpProto) this.instance).hasRestoreLimitKb();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public long getRestoreLimitKb() {
            return ((MemInfoDumpProto) this.instance).getRestoreLimitKb();
        }

        public Builder setRestoreLimitKb(long value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setRestoreLimitKb(value);
            return this;
        }

        public Builder clearRestoreLimitKb() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearRestoreLimitKb();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasIsLowRamDevice() {
            return ((MemInfoDumpProto) this.instance).hasIsLowRamDevice();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean getIsLowRamDevice() {
            return ((MemInfoDumpProto) this.instance).getIsLowRamDevice();
        }

        public Builder setIsLowRamDevice(boolean value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setIsLowRamDevice(value);
            return this;
        }

        public Builder clearIsLowRamDevice() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearIsLowRamDevice();
            return this;
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean hasIsHighEndGfx() {
            return ((MemInfoDumpProto) this.instance).hasIsHighEndGfx();
        }

        @Override // com.android.server.am.MemInfoDumpProtoOrBuilder
        public boolean getIsHighEndGfx() {
            return ((MemInfoDumpProto) this.instance).getIsHighEndGfx();
        }

        public Builder setIsHighEndGfx(boolean value) {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).setIsHighEndGfx(value);
            return this;
        }

        public Builder clearIsHighEndGfx() {
            copyOnWrite();
            ((MemInfoDumpProto) this.instance).clearIsHighEndGfx();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MemInfoDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.nativeProcesses_.makeImmutable();
                this.appProcesses_.makeImmutable();
                this.totalPssByProcess_.makeImmutable();
                this.totalPssByOomAdjustment_.makeImmutable();
                this.totalPssByCategory_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MemInfoDumpProto other = (MemInfoDumpProto) arg1;
                this.uptimeDurationMs_ = visitor.visitLong(hasUptimeDurationMs(), this.uptimeDurationMs_, other.hasUptimeDurationMs(), other.uptimeDurationMs_);
                this.elapsedRealtimeMs_ = visitor.visitLong(hasElapsedRealtimeMs(), this.elapsedRealtimeMs_, other.hasElapsedRealtimeMs(), other.elapsedRealtimeMs_);
                this.nativeProcesses_ = visitor.visitList(this.nativeProcesses_, other.nativeProcesses_);
                this.appProcesses_ = visitor.visitList(this.appProcesses_, other.appProcesses_);
                this.totalPssByProcess_ = visitor.visitList(this.totalPssByProcess_, other.totalPssByProcess_);
                this.totalPssByOomAdjustment_ = visitor.visitList(this.totalPssByOomAdjustment_, other.totalPssByOomAdjustment_);
                this.totalPssByCategory_ = visitor.visitList(this.totalPssByCategory_, other.totalPssByCategory_);
                this.totalRamKb_ = visitor.visitLong(hasTotalRamKb(), this.totalRamKb_, other.hasTotalRamKb(), other.totalRamKb_);
                this.status_ = visitor.visitInt(hasStatus(), this.status_, other.hasStatus(), other.status_);
                this.cachedPssKb_ = visitor.visitLong(hasCachedPssKb(), this.cachedPssKb_, other.hasCachedPssKb(), other.cachedPssKb_);
                this.cachedKernelKb_ = visitor.visitLong(hasCachedKernelKb(), this.cachedKernelKb_, other.hasCachedKernelKb(), other.cachedKernelKb_);
                this.freeKb_ = visitor.visitLong(hasFreeKb(), this.freeKb_, other.hasFreeKb(), other.freeKb_);
                this.usedPssKb_ = visitor.visitLong(hasUsedPssKb(), this.usedPssKb_, other.hasUsedPssKb(), other.usedPssKb_);
                this.usedKernelKb_ = visitor.visitLong(hasUsedKernelKb(), this.usedKernelKb_, other.hasUsedKernelKb(), other.usedKernelKb_);
                this.lostRamKb_ = visitor.visitLong(hasLostRamKb(), this.lostRamKb_, other.hasLostRamKb(), other.lostRamKb_);
                this.totalZramKb_ = visitor.visitLong(hasTotalZramKb(), this.totalZramKb_, other.hasTotalZramKb(), other.totalZramKb_);
                this.zramPhysicalUsedInSwapKb_ = visitor.visitLong(hasZramPhysicalUsedInSwapKb(), this.zramPhysicalUsedInSwapKb_, other.hasZramPhysicalUsedInSwapKb(), other.zramPhysicalUsedInSwapKb_);
                this.totalZramSwapKb_ = visitor.visitLong(hasTotalZramSwapKb(), this.totalZramSwapKb_, other.hasTotalZramSwapKb(), other.totalZramSwapKb_);
                this.ksmSharingKb_ = visitor.visitLong(hasKsmSharingKb(), this.ksmSharingKb_, other.hasKsmSharingKb(), other.ksmSharingKb_);
                this.ksmSharedKb_ = visitor.visitLong(hasKsmSharedKb(), this.ksmSharedKb_, other.hasKsmSharedKb(), other.ksmSharedKb_);
                this.ksmUnsharedKb_ = visitor.visitLong(hasKsmUnsharedKb(), this.ksmUnsharedKb_, other.hasKsmUnsharedKb(), other.ksmUnsharedKb_);
                this.ksmVolatileKb_ = visitor.visitLong(hasKsmVolatileKb(), this.ksmVolatileKb_, other.hasKsmVolatileKb(), other.ksmVolatileKb_);
                this.tuningMb_ = visitor.visitInt(hasTuningMb(), this.tuningMb_, other.hasTuningMb(), other.tuningMb_);
                this.tuningLargeMb_ = visitor.visitInt(hasTuningLargeMb(), this.tuningLargeMb_, other.hasTuningLargeMb(), other.tuningLargeMb_);
                this.oomKb_ = visitor.visitLong(hasOomKb(), this.oomKb_, other.hasOomKb(), other.oomKb_);
                this.restoreLimitKb_ = visitor.visitLong(hasRestoreLimitKb(), this.restoreLimitKb_, other.hasRestoreLimitKb(), other.restoreLimitKb_);
                this.isLowRamDevice_ = visitor.visitBoolean(hasIsLowRamDevice(), this.isLowRamDevice_, other.hasIsLowRamDevice(), other.isLowRamDevice_);
                this.isHighEndGfx_ = visitor.visitBoolean(hasIsHighEndGfx(), this.isHighEndGfx_, other.hasIsHighEndGfx(), other.isHighEndGfx_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.uptimeDurationMs_ = input.readInt64();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.elapsedRealtimeMs_ = input.readInt64();
                                break;
                            case 26:
                                if (!this.nativeProcesses_.isModifiable()) {
                                    this.nativeProcesses_ = GeneratedMessageLite.mutableCopy(this.nativeProcesses_);
                                }
                                this.nativeProcesses_.add((ProcessMemory) input.readMessage(ProcessMemory.parser(), extensionRegistry));
                                break;
                            case 34:
                                if (!this.appProcesses_.isModifiable()) {
                                    this.appProcesses_ = GeneratedMessageLite.mutableCopy(this.appProcesses_);
                                }
                                this.appProcesses_.add((AppData) input.readMessage(AppData.parser(), extensionRegistry));
                                break;
                            case 42:
                                if (!this.totalPssByProcess_.isModifiable()) {
                                    this.totalPssByProcess_ = GeneratedMessageLite.mutableCopy(this.totalPssByProcess_);
                                }
                                this.totalPssByProcess_.add((MemItem) input.readMessage(MemItem.parser(), extensionRegistry));
                                break;
                            case 50:
                                if (!this.totalPssByOomAdjustment_.isModifiable()) {
                                    this.totalPssByOomAdjustment_ = GeneratedMessageLite.mutableCopy(this.totalPssByOomAdjustment_);
                                }
                                this.totalPssByOomAdjustment_.add((MemItem) input.readMessage(MemItem.parser(), extensionRegistry));
                                break;
                            case 58:
                                if (!this.totalPssByCategory_.isModifiable()) {
                                    this.totalPssByCategory_ = GeneratedMessageLite.mutableCopy(this.totalPssByCategory_);
                                }
                                this.totalPssByCategory_.add((MemItem) input.readMessage(MemItem.parser(), extensionRegistry));
                                break;
                            case 64:
                                this.bitField0_ |= 4;
                                this.totalRamKb_ = input.readInt64();
                                break;
                            case 72:
                                int rawValue = input.readEnum();
                                if (Processstats.ProcessStatsProto.MemoryFactor.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 8;
                                    this.status_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(9, rawValue);
                                    break;
                                }
                            case 80:
                                this.bitField0_ |= 16;
                                this.cachedPssKb_ = input.readInt64();
                                break;
                            case 88:
                                this.bitField0_ |= 32;
                                this.cachedKernelKb_ = input.readInt64();
                                break;
                            case 96:
                                this.bitField0_ |= 64;
                                this.freeKb_ = input.readInt64();
                                break;
                            case 104:
                                this.bitField0_ |= 128;
                                this.usedPssKb_ = input.readInt64();
                                break;
                            case 112:
                                this.bitField0_ |= 256;
                                this.usedKernelKb_ = input.readInt64();
                                break;
                            case 120:
                                this.bitField0_ |= 512;
                                this.lostRamKb_ = input.readInt64();
                                break;
                            case 128:
                                this.bitField0_ |= 1024;
                                this.totalZramKb_ = input.readInt64();
                                break;
                            case 136:
                                this.bitField0_ |= 2048;
                                this.zramPhysicalUsedInSwapKb_ = input.readInt64();
                                break;
                            case 144:
                                this.bitField0_ |= 4096;
                                this.totalZramSwapKb_ = input.readInt64();
                                break;
                            case 152:
                                this.bitField0_ |= 8192;
                                this.ksmSharingKb_ = input.readInt64();
                                break;
                            case 160:
                                this.bitField0_ |= 16384;
                                this.ksmSharedKb_ = input.readInt64();
                                break;
                            case 168:
                                this.bitField0_ |= 32768;
                                this.ksmUnsharedKb_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 65536;
                                this.ksmVolatileKb_ = input.readInt64();
                                break;
                            case 184:
                                this.bitField0_ |= 131072;
                                this.tuningMb_ = input.readInt32();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 262144;
                                this.tuningLargeMb_ = input.readInt32();
                                break;
                            case 200:
                                this.bitField0_ |= 524288;
                                this.oomKb_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.CONTENT_CAPTURE_SESSION_EVENTS_FIELD_NUMBER:
                                this.bitField0_ |= 1048576;
                                this.restoreLimitKb_ = input.readInt64();
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER:
                                this.bitField0_ |= 2097152;
                                this.isLowRamDevice_ = input.readBool();
                                break;
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 4194304;
                                this.isHighEndGfx_ = input.readBool();
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (MemInfoDumpProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static MemInfoDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MemInfoDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
