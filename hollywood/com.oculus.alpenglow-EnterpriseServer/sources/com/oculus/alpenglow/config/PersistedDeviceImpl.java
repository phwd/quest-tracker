package com.oculus.alpenglow.config;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.config.PersistedDevice;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = PersistedDeviceImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PersistedDeviceImpl extends AbstractC007808s implements Tree, PersistedDevice {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "HWMOculusDevice";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 903791249;
    public static final int TYPE_TAG = 903791249;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = ManagementInfo.TYPE_TAG)
    @ThreadSafe
    public static final class ManagementInfo extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo {
        public static final int TYPE_TAG = -70968619;

        @GeneratedGraphQL
        @ModelIdentity(typeTag = DeviceConfig.TYPE_TAG)
        @ThreadSafe
        public static final class DeviceConfig extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig {
            public static final int TYPE_TAG = -1196310907;

            @GeneratedGraphQL
            @ModelIdentity(typeTag = DeviceApps.TYPE_TAG)
            @ThreadSafe
            public static final class DeviceApps extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceApps {
                public static final int TYPE_TAG = 953587754;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceApps.Nodes {
                    public static final int TYPE_TAG = 1696543041;
                    @Nullable
                    public LocalApp _cachedLocalApp;

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @DoNotStrip
                public DeviceApps(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = OsConfig.TYPE_TAG)
            @ThreadSafe
            public static final class OsConfig extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.OsConfig {
                public static final int TYPE_TAG = 92644396;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = UpdateTimeWindows.TYPE_TAG)
                @ThreadSafe
                public static final class UpdateTimeWindows extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows {
                    public static final int TYPE_TAG = 1607690243;

                    @DoNotStrip
                    public UpdateTimeWindows(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @DoNotStrip
                public OsConfig(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = ShellModes.TYPE_TAG)
            @ThreadSafe
            public static final class ShellModes extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.ShellModes {
                public static final int TYPE_TAG = -1746241734;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.ShellModes.Nodes {
                    public static final int TYPE_TAG = 2078331167;

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @DoNotStrip
                public ShellModes(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = DeviceRemoteApps.TYPE_TAG)
            @ThreadSafe
            public static final class DeviceRemoteApps extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps {
                public static final int TYPE_TAG = 2054617695;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes {
                    public static final int TYPE_TAG = 1884201337;

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ExternalResourceFiles.TYPE_TAG)
                    @ThreadSafe
                    public static final class ExternalResourceFiles extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles {
                        public static final int TYPE_TAG = -654059006;

                        @GeneratedGraphQL
                        @ModelIdentity(typeTag = ExternalResourceFilesNodes.TYPE_TAG)
                        @ThreadSafe
                        public static final class ExternalResourceFilesNodes extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes {
                            public static final int TYPE_TAG = -2059490908;
                            @Nullable
                            public RemoteResourceAsset _cachedRemoteResourceAsset;

                            @DoNotStrip
                            public ExternalResourceFilesNodes(int i, @Nullable int[] iArr) {
                                super(i, iArr);
                            }
                        }

                        @DoNotStrip
                        public ExternalResourceFiles(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ApplicationBinary.TYPE_TAG)
                    @ThreadSafe
                    public static final class ApplicationBinary extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary {
                        public static final int TYPE_TAG = 118344145;
                        @Nullable
                        public RemoteResourceAsset _cachedRemoteResourceAsset;

                        @DoNotStrip
                        public ApplicationBinary(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ApplicationMetadata.TYPE_TAG)
                    @ThreadSafe
                    public static final class ApplicationMetadata extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationMetadata {
                        public static final int TYPE_TAG = -1606898580;

                        @DoNotStrip
                        public ApplicationMetadata(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @DoNotStrip
                public DeviceRemoteApps(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @DoNotStrip
            public DeviceConfig(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @GeneratedGraphQL
        @ModelIdentity(typeTag = Subscription.TYPE_TAG)
        @ThreadSafe
        public static final class Subscription extends AbstractC007808s implements Tree, PersistedDevice.ManagementInfo.Subscription {
            public static final int TYPE_TAG = -1254988540;

            @DoNotStrip
            public Subscription(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @DoNotStrip
        public ManagementInfo(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @DoNotStrip
    public PersistedDeviceImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
