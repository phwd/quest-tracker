package com.oculus.alpenglow.config;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.google.common.collect.ImmutableList;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMDayOfWeek;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMNetworkType;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusAUICapability;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusGuardianMode;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusHomeButtonBehavior;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusLockScreenPinType;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigTelemetryState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigUpdateRule;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSCrashReportingState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSDeviceAdmin;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSHandTrackingState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMUnknownSourcesSetting;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = DeviceImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class DeviceImpl extends AbstractC007808s implements Device, Tree {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "HWMOculusDevice";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 3769376941L;
    public static final int TYPE_TAG = -525590355;
    @Nullable
    public PersistedDevice _cachedPersistedDevice;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = ManagementInfo.TYPE_TAG)
    @ThreadSafe
    public static final class ManagementInfo extends AbstractC007808s implements Device.ManagementInfo, Tree {
        public static final int TYPE_TAG = 1025843958;

        @GeneratedGraphQL
        @ModelIdentity(typeTag = DeviceConfig.TYPE_TAG)
        @ThreadSafe
        public static final class DeviceConfig extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig, Tree {
            public static final int TYPE_TAG = 807105293;

            @GeneratedGraphQL
            @ModelIdentity(typeTag = DeviceApps.TYPE_TAG)
            @ThreadSafe
            public static final class DeviceApps extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.DeviceApps {
                public static final int TYPE_TAG = 1527114192;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.DeviceApps.Nodes {
                    public static final int TYPE_TAG = -1024713600;
                    @Nullable
                    public LocalApp _cachedLocalApp;

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceApps.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final LocalApp A17() {
                        LocalApp localApp = this._cachedLocalApp;
                        if (localApp != null) {
                            return localApp;
                        }
                        if (getTypeName().hashCode() != -217913868) {
                            return null;
                        }
                        LocalApp localApp2 = (LocalApp) reinterpret(LocalAppImpl.class, LocalAppImpl.TYPE_TAG);
                        this._cachedLocalApp = localApp2;
                        return localApp2;
                    }

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceApps
                @ImmutableGetter
                public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.DeviceApps.Nodes> A4C() {
                    return A04(104993457, Nodes.class, Nodes.TYPE_TAG);
                }

                @DoNotStrip
                public DeviceApps(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = DeviceRemoteApps.TYPE_TAG)
            @ThreadSafe
            public static final class DeviceRemoteApps extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.DeviceRemoteApps {
                public static final int TYPE_TAG = 1782378044;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes, Tree {
                    public static final int TYPE_TAG = -202792343;

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ApplicationBinary.TYPE_TAG)
                    @ThreadSafe
                    public static final class ApplicationBinary extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary, Tree {
                        public static final int TYPE_TAG = 1887981857;
                        @Nullable
                        public RemoteResourceAsset _cachedRemoteResourceAsset;

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary
                        @ImmutableGetter
                        public final RemoteResourceAsset A1B() {
                            RemoteResourceAsset remoteResourceAsset = this._cachedRemoteResourceAsset;
                            if (remoteResourceAsset != null) {
                                return remoteResourceAsset;
                            }
                            RemoteResourceAsset remoteResourceAsset2 = (RemoteResourceAsset) reinterpret(RemoteResourceAssetImpl.class, RemoteResourceAssetImpl.TYPE_TAG);
                            this._cachedRemoteResourceAsset = remoteResourceAsset2;
                            return remoteResourceAsset2;
                        }

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary
                        @ImmutableGetter
                        public final long A3q() {
                            return getTimeValue(338699282);
                        }

                        @DoNotStrip
                        public ApplicationBinary(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ExternalResourceFiles.TYPE_TAG)
                    @ThreadSafe
                    public static final class ExternalResourceFiles extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles {
                        public static final int TYPE_TAG = 1017493961;

                        @GeneratedGraphQL
                        @ModelIdentity(typeTag = ExternalResourceFilesNodes.TYPE_TAG)
                        @ThreadSafe
                        public static final class ExternalResourceFilesNodes extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes, Tree {
                            public static final int TYPE_TAG = -999710108;
                            @Nullable
                            public RemoteResourceAsset _cachedRemoteResourceAsset;

                            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes
                            @ImmutableGetter
                            public final RemoteResourceAsset A1B() {
                                RemoteResourceAsset remoteResourceAsset = this._cachedRemoteResourceAsset;
                                if (remoteResourceAsset != null) {
                                    return remoteResourceAsset;
                                }
                                RemoteResourceAsset remoteResourceAsset2 = (RemoteResourceAsset) reinterpret(RemoteResourceAssetImpl.class, RemoteResourceAssetImpl.TYPE_TAG);
                                this._cachedRemoteResourceAsset = remoteResourceAsset2;
                                return remoteResourceAsset2;
                            }

                            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes
                            @ImmutableGetter
                            @Nullable
                            public final String getId() {
                                return A07(3355);
                            }

                            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes
                            @ImmutableGetter
                            public final long A3q() {
                                return getTimeValue(338699282);
                            }

                            @DoNotStrip
                            public ExternalResourceFilesNodes(int i, @Nullable int[] iArr) {
                                super(i, iArr);
                            }
                        }

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles
                        @ImmutableGetter
                        public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles.ExternalResourceFilesNodes> A4C() {
                            return A04(104993457, ExternalResourceFilesNodes.class, ExternalResourceFilesNodes.TYPE_TAG);
                        }

                        @DoNotStrip
                        public ExternalResourceFiles(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary A2x() {
                        return (Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationBinary) A03(230713808, ApplicationBinary.class, ApplicationBinary.TYPE_TAG);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles A3X() {
                        return (Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ExternalResourceFiles) A03(-1667843590, ExternalResourceFiles.class, ExternalResourceFiles.TYPE_TAG);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final String getId() {
                        return A07(3355);
                    }

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = ApplicationMetadata.TYPE_TAG)
                    @ThreadSafe
                    public static final class ApplicationMetadata extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes.ApplicationMetadata {
                        public static final int TYPE_TAG = -89484970;

                        @DoNotStrip
                        public ApplicationMetadata(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes
                    @ImmutableGetter
                    public final long A3q() {
                        return getTimeValue(338699282);
                    }

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.DeviceRemoteApps
                @ImmutableGetter
                public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.DeviceRemoteApps.Nodes> A4C() {
                    return A04(104993457, Nodes.class, Nodes.TYPE_TAG);
                }

                @DoNotStrip
                public DeviceRemoteApps(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = NetworkConfig.TYPE_TAG)
            @ThreadSafe
            public static final class NetworkConfig extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.NetworkConfig {
                public static final int TYPE_TAG = 4038657;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = WifiNetworks.TYPE_TAG)
                @ThreadSafe
                public static final class WifiNetworks extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks {
                    public static final int TYPE_TAG = -334515703;

                    @GeneratedGraphQL
                    @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                    @ThreadSafe
                    public static final class Nodes extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes, Tree {
                        public static final int TYPE_TAG = 16711603;

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes
                        @ImmutableGetter
                        @Nullable
                        public final GraphQLHWMNetworkType A4B() {
                            return (GraphQLHWMNetworkType) A06(-19457365, GraphQLHWMNetworkType.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                        }

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes
                        @ImmutableGetter
                        @Nullable
                        public final String A3H() {
                            return A07(-683415465);
                        }

                        @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes
                        @ImmutableGetter
                        @Nullable
                        public final String A4X() {
                            return A07(3539835);
                        }

                        @DoNotStrip
                        public Nodes(int i, @Nullable int[] iArr) {
                            super(i, iArr);
                        }
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks
                    @ImmutableGetter
                    public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes> A4C() {
                        return A04(104993457, Nodes.class, Nodes.TYPE_TAG);
                    }

                    @DoNotStrip
                    public WifiNetworks(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.NetworkConfig
                @ImmutableGetter
                @Nullable
                public final Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks A4v() {
                    return (Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks) A03(-541944337, WifiNetworks.class, WifiNetworks.TYPE_TAG);
                }

                @DoNotStrip
                public NetworkConfig(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = OsConfig.TYPE_TAG)
            @ThreadSafe
            public static final class OsConfig extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.OsConfig, Tree {
                public static final int TYPE_TAG = 1816651486;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = LockScreen.TYPE_TAG)
                @ThreadSafe
                public static final class LockScreen extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen {
                    public static final int TYPE_TAG = -1068885961;

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen
                    @ImmutableGetter
                    @Nullable
                    public final GraphQLHWMOculusLockScreenPinType A4m() {
                        return (GraphQLHWMOculusLockScreenPinType) A06(-861514923, GraphQLHWMOculusLockScreenPinType.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen
                    @ImmutableGetter
                    @Nullable
                    public final String A4l() {
                        return A07(-859079142);
                    }

                    @DoNotStrip
                    public LockScreen(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @GeneratedGraphQL
                @ModelIdentity(typeTag = UpdateTimeWindows.TYPE_TAG)
                @ThreadSafe
                public static final class UpdateTimeWindows extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows, Tree {
                    public static final int TYPE_TAG = -1408689442;

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows
                    @ImmutableGetter
                    public final ImmutableList<GraphQLHWMDayOfWeek> A3L() {
                        return A05(-1385911948, GraphQLHWMDayOfWeek.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows
                    @ImmutableGetter
                    public final int A4w() {
                        return getIntValue(1862867980);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows
                    @ImmutableGetter
                    public final int A4x() {
                        return getIntValue(-772124845);
                    }

                    @DoNotStrip
                    public UpdateTimeWindows(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMOculusOSCrashReportingState A3G() {
                    return (GraphQLHWMOculusOSCrashReportingState) A06(-314728792, GraphQLHWMOculusOSCrashReportingState.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMOculusOSDeviceAdmin A3O() {
                    return (GraphQLHWMOculusOSDeviceAdmin) A06(-602477530, GraphQLHWMOculusOSDeviceAdmin.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMOculusOSHandTrackingState A3c() {
                    return (GraphQLHWMOculusOSHandTrackingState) A06(-1286995239, GraphQLHWMOculusOSHandTrackingState.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen A3w() {
                    return (Device.ManagementInfo.DeviceConfig.OsConfig.LockScreen) A03(-381820416, LockScreen.class, LockScreen.TYPE_TAG);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMOculusOSConfigTelemetryState A4d() {
                    return (GraphQLHWMOculusOSConfigTelemetryState) A06(1566891147, GraphQLHWMOculusOSConfigTelemetryState.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMUnknownSourcesSetting A4k() {
                    return (GraphQLHWMUnknownSourcesSetting) A06(74319043, GraphQLHWMUnknownSourcesSetting.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final GraphQLHWMOculusOSConfigUpdateRule A4o() {
                    return (GraphQLHWMOculusOSConfigUpdateRule) A06(-573494094, GraphQLHWMOculusOSConfigUpdateRule.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows> A4p() {
                    return A04(1030618823, UpdateTimeWindows.class, UpdateTimeWindows.TYPE_TAG);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                public final boolean A32() {
                    return getBooleanValue(1439059084);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final String A3v() {
                    return A07(-1097462182);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final String A4e() {
                    return A07(-2076227591);
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.OsConfig
                @ImmutableGetter
                @Nullable
                public final String A4j() {
                    return A07(-298450420);
                }

                @DoNotStrip
                public OsConfig(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @GeneratedGraphQL
            @ModelIdentity(typeTag = ShellModes.TYPE_TAG)
            @ThreadSafe
            public static final class ShellModes extends AbstractC007808s implements Tree, Device.ManagementInfo.DeviceConfig.ShellModes {
                public static final int TYPE_TAG = -1934874541;

                @GeneratedGraphQL
                @ModelIdentity(typeTag = Nodes.TYPE_TAG)
                @ThreadSafe
                public static final class Nodes extends AbstractC007808s implements Device.ManagementInfo.DeviceConfig.ShellModes.Nodes, Tree {
                    public static final int TYPE_TAG = 2012722507;

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    public final ImmutableList<GraphQLHWMOculusAUICapability> A31() {
                        return A05(1467534592, GraphQLHWMOculusAUICapability.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final GraphQLHWMOculusHomeButtonBehavior A3f() {
                        return (GraphQLHWMOculusHomeButtonBehavior) A06(-1756864161, GraphQLHWMOculusHomeButtonBehavior.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final String A3M() {
                        return A07(-436635869);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    public final ImmutableList<String> A3t() {
                        Object A01 = AbstractC007808s.A01(this, -1100859471);
                        if (A01 == null) {
                            A01 = getStringList(-1100859471);
                            AbstractC007808s.A02(this, -1100859471, A01);
                        }
                        return (ImmutableList) A01;
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final String A4l() {
                        return A07(-859079142);
                    }

                    @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes.Nodes
                    @ImmutableGetter
                    @Nullable
                    public final String getName() {
                        return A07(3373707);
                    }

                    @DoNotStrip
                    public Nodes(int i, @Nullable int[] iArr) {
                        super(i, iArr);
                    }
                }

                @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig.ShellModes
                @ImmutableGetter
                public final ImmutableList<? extends Device.ManagementInfo.DeviceConfig.ShellModes.Nodes> A4C() {
                    return A04(104993457, Nodes.class, Nodes.TYPE_TAG);
                }

                @DoNotStrip
                public ShellModes(int i, @Nullable int[] iArr) {
                    super(i, iArr);
                }
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final Device.ManagementInfo.DeviceConfig.DeviceApps A3P() {
                return (Device.ManagementInfo.DeviceConfig.DeviceApps) A03(-1543443781, DeviceApps.class, DeviceApps.TYPE_TAG);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final Device.ManagementInfo.DeviceConfig.DeviceRemoteApps A3R() {
                return (Device.ManagementInfo.DeviceConfig.DeviceRemoteApps) A03(1112175202, DeviceRemoteApps.class, DeviceRemoteApps.TYPE_TAG);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final GraphQLHWMOculusGuardianMode A3b() {
                return (GraphQLHWMOculusGuardianMode) A06(12225425, GraphQLHWMOculusGuardianMode.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final Device.ManagementInfo.DeviceConfig.NetworkConfig A4A() {
                return (Device.ManagementInfo.DeviceConfig.NetworkConfig) A03(-2014644621, NetworkConfig.class, NetworkConfig.TYPE_TAG);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final Device.ManagementInfo.DeviceConfig.OsConfig A4D() {
                return (Device.ManagementInfo.DeviceConfig.OsConfig) A03(1149422077, OsConfig.class, OsConfig.TYPE_TAG);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            @Nullable
            public final Device.ManagementInfo.DeviceConfig.ShellModes A4U() {
                return (Device.ManagementInfo.DeviceConfig.ShellModes) A03(1812093025, ShellModes.class, ShellModes.TYPE_TAG);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            public final int A3C() {
                return getIntValue(-533338917);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            public final boolean A3U() {
                return getBooleanValue(1893553123);
            }

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.DeviceConfig
            @ImmutableGetter
            public final long A3q() {
                return getTimeValue(338699282);
            }

            @DoNotStrip
            public DeviceConfig(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @GeneratedGraphQL
        @ModelIdentity(typeTag = Subscription.TYPE_TAG)
        @ThreadSafe
        public static final class Subscription extends AbstractC007808s implements Tree, Device.ManagementInfo.Subscription {
            public static final int TYPE_TAG = 1186780644;

            @Override // com.oculus.alpenglow.config.Device.ManagementInfo.Subscription
            @ImmutableGetter
            public final long A3W() {
                return getTimeValue(475919162);
            }

            @DoNotStrip
            public Subscription(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @Override // com.oculus.alpenglow.config.Device.ManagementInfo
        @ImmutableGetter
        @Nullable
        public final Device.ManagementInfo.DeviceConfig A3Q() {
            return (Device.ManagementInfo.DeviceConfig) A03(-1429490357, DeviceConfig.class, DeviceConfig.TYPE_TAG);
        }

        @Override // com.oculus.alpenglow.config.Device.ManagementInfo
        @ImmutableGetter
        @Nullable
        public final Device.ManagementInfo.Subscription A4a() {
            return (Device.ManagementInfo.Subscription) A03(341203229, Subscription.class, Subscription.TYPE_TAG);
        }

        @DoNotStrip
        public ManagementInfo(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.config.Device
    @ImmutableGetter
    public final PersistedDevice A19() {
        PersistedDevice persistedDevice = this._cachedPersistedDevice;
        if (persistedDevice != null) {
            return persistedDevice;
        }
        PersistedDevice persistedDevice2 = (PersistedDevice) reinterpret(PersistedDeviceImpl.class, PersistedDeviceImpl.TYPE_TAG);
        this._cachedPersistedDevice = persistedDevice2;
        return persistedDevice2;
    }

    @Override // com.oculus.alpenglow.config.Device
    @ImmutableGetter
    @Nullable
    public final Device.ManagementInfo A3y() {
        return (Device.ManagementInfo) A03(710235946, ManagementInfo.class, ManagementInfo.TYPE_TAG);
    }

    @Override // com.oculus.alpenglow.config.Device
    @ImmutableGetter
    public final boolean A3p() {
        return getBooleanValue(-1959396982);
    }

    @Override // com.oculus.alpenglow.config.Device
    @ImmutableGetter
    @Nullable
    public final String getOwnerName() {
        return A07(557119639);
    }

    @DoNotStrip
    public DeviceImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
