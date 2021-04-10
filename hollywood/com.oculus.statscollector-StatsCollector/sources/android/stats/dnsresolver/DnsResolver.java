package android.stats.dnsresolver;

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

public final class DnsResolver {

    public interface DnsQueryEventOrBuilder extends MessageLiteOrBuilder {
        CacheStatus getCacheHit();

        boolean getConnected();

        int getDnsServerIndex();

        IpVersion getIpVersion();

        int getLatencyMicros();

        Protocol getProtocol();

        NsRcode getRcode();

        int getRetryTimes();

        NsType getType();

        boolean hasCacheHit();

        boolean hasConnected();

        boolean hasDnsServerIndex();

        boolean hasIpVersion();

        boolean hasLatencyMicros();

        boolean hasProtocol();

        boolean hasRcode();

        boolean hasRetryTimes();

        boolean hasType();
    }

    public interface DnsQueryEventsOrBuilder extends MessageLiteOrBuilder {
        DnsQueryEvent getDnsQueryEvent(int i);

        int getDnsQueryEventCount();

        List<DnsQueryEvent> getDnsQueryEventList();
    }

    private DnsResolver() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum EventType implements Internal.EnumLite {
        EVENT_UNKNOWN(0),
        EVENT_GETADDRINFO(1),
        EVENT_GETHOSTBYNAME(2),
        EVENT_GETHOSTBYADDR(3),
        EVENT_RES_NSEND(4);
        
        public static final int EVENT_GETADDRINFO_VALUE = 1;
        public static final int EVENT_GETHOSTBYADDR_VALUE = 3;
        public static final int EVENT_GETHOSTBYNAME_VALUE = 2;
        public static final int EVENT_RES_NSEND_VALUE = 4;
        public static final int EVENT_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<EventType> internalValueMap = new Internal.EnumLiteMap<EventType>() {
            /* class android.stats.dnsresolver.DnsResolver.EventType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventType findValueByNumber(int number) {
                return EventType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static EventType valueOf(int value2) {
            return forNumber(value2);
        }

        public static EventType forNumber(int value2) {
            if (value2 == 0) {
                return EVENT_UNKNOWN;
            }
            if (value2 == 1) {
                return EVENT_GETADDRINFO;
            }
            if (value2 == 2) {
                return EVENT_GETHOSTBYNAME;
            }
            if (value2 == 3) {
                return EVENT_GETHOSTBYADDR;
            }
            if (value2 != 4) {
                return null;
            }
            return EVENT_RES_NSEND;
        }

        public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
            return internalValueMap;
        }

        private EventType(int value2) {
            this.value = value2;
        }
    }

    public enum ReturnCode implements Internal.EnumLite {
        RC_EAI_NO_ERROR(0),
        RC_EAI_ADDRFAMILY(1),
        RC_EAI_AGAIN(2),
        RC_EAI_BADFLAGS(3),
        RC_EAI_FAIL(4),
        RC_EAI_FAMILY(5),
        RC_EAI_MEMORY(6),
        RC_EAI_NODATA(7),
        RC_EAI_NONAME(8),
        RC_EAI_SERVICE(9),
        RC_EAI_SOCKTYPE(10),
        RC_EAI_SYSTEM(11),
        RC_EAI_BADHINTS(12),
        RC_EAI_PROTOCOL(13),
        RC_EAI_OVERFLOW(14),
        RC_RESOLV_TIMEOUT(255),
        RC_EAI_MAX(256);
        
        public static final int RC_EAI_ADDRFAMILY_VALUE = 1;
        public static final int RC_EAI_AGAIN_VALUE = 2;
        public static final int RC_EAI_BADFLAGS_VALUE = 3;
        public static final int RC_EAI_BADHINTS_VALUE = 12;
        public static final int RC_EAI_FAIL_VALUE = 4;
        public static final int RC_EAI_FAMILY_VALUE = 5;
        public static final int RC_EAI_MAX_VALUE = 256;
        public static final int RC_EAI_MEMORY_VALUE = 6;
        public static final int RC_EAI_NODATA_VALUE = 7;
        public static final int RC_EAI_NONAME_VALUE = 8;
        public static final int RC_EAI_NO_ERROR_VALUE = 0;
        public static final int RC_EAI_OVERFLOW_VALUE = 14;
        public static final int RC_EAI_PROTOCOL_VALUE = 13;
        public static final int RC_EAI_SERVICE_VALUE = 9;
        public static final int RC_EAI_SOCKTYPE_VALUE = 10;
        public static final int RC_EAI_SYSTEM_VALUE = 11;
        public static final int RC_RESOLV_TIMEOUT_VALUE = 255;
        private static final Internal.EnumLiteMap<ReturnCode> internalValueMap = new Internal.EnumLiteMap<ReturnCode>() {
            /* class android.stats.dnsresolver.DnsResolver.ReturnCode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ReturnCode findValueByNumber(int number) {
                return ReturnCode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ReturnCode valueOf(int value2) {
            return forNumber(value2);
        }

        public static ReturnCode forNumber(int value2) {
            if (value2 == 255) {
                return RC_RESOLV_TIMEOUT;
            }
            if (value2 == 256) {
                return RC_EAI_MAX;
            }
            switch (value2) {
                case 0:
                    return RC_EAI_NO_ERROR;
                case 1:
                    return RC_EAI_ADDRFAMILY;
                case 2:
                    return RC_EAI_AGAIN;
                case 3:
                    return RC_EAI_BADFLAGS;
                case 4:
                    return RC_EAI_FAIL;
                case 5:
                    return RC_EAI_FAMILY;
                case 6:
                    return RC_EAI_MEMORY;
                case 7:
                    return RC_EAI_NODATA;
                case 8:
                    return RC_EAI_NONAME;
                case 9:
                    return RC_EAI_SERVICE;
                case 10:
                    return RC_EAI_SOCKTYPE;
                case 11:
                    return RC_EAI_SYSTEM;
                case 12:
                    return RC_EAI_BADHINTS;
                case 13:
                    return RC_EAI_PROTOCOL;
                case 14:
                    return RC_EAI_OVERFLOW;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ReturnCode> internalGetValueMap() {
            return internalValueMap;
        }

        private ReturnCode(int value2) {
            this.value = value2;
        }
    }

    public enum NsRcode implements Internal.EnumLite {
        NS_R_NO_ERROR(0),
        NS_R_FORMERR(1),
        NS_R_SERVFAIL(2),
        NS_R_NXDOMAIN(3),
        NS_R_NOTIMPL(4),
        NS_R_REFUSED(5),
        NS_R_YXDOMAIN(6),
        NS_R_YXRRSET(7),
        NS_R_NXRRSET(8),
        NS_R_NOTAUTH(9),
        NS_R_NOTZONE(10),
        NS_R_MAX(11),
        NS_R_BADVERS(16),
        NS_R_BADKEY(17),
        NS_R_BADTIME(18),
        NS_R_INTERNAL_ERROR(254),
        NS_R_TIMEOUT(255);
        
        public static final int NS_R_BADKEY_VALUE = 17;
        public static final int NS_R_BADTIME_VALUE = 18;
        public static final int NS_R_BADVERS_VALUE = 16;
        public static final int NS_R_FORMERR_VALUE = 1;
        public static final int NS_R_INTERNAL_ERROR_VALUE = 254;
        public static final int NS_R_MAX_VALUE = 11;
        public static final int NS_R_NOTAUTH_VALUE = 9;
        public static final int NS_R_NOTIMPL_VALUE = 4;
        public static final int NS_R_NOTZONE_VALUE = 10;
        public static final int NS_R_NO_ERROR_VALUE = 0;
        public static final int NS_R_NXDOMAIN_VALUE = 3;
        public static final int NS_R_NXRRSET_VALUE = 8;
        public static final int NS_R_REFUSED_VALUE = 5;
        public static final int NS_R_SERVFAIL_VALUE = 2;
        public static final int NS_R_TIMEOUT_VALUE = 255;
        public static final int NS_R_YXDOMAIN_VALUE = 6;
        public static final int NS_R_YXRRSET_VALUE = 7;
        private static final Internal.EnumLiteMap<NsRcode> internalValueMap = new Internal.EnumLiteMap<NsRcode>() {
            /* class android.stats.dnsresolver.DnsResolver.NsRcode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NsRcode findValueByNumber(int number) {
                return NsRcode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NsRcode valueOf(int value2) {
            return forNumber(value2);
        }

        public static NsRcode forNumber(int value2) {
            if (value2 == 254) {
                return NS_R_INTERNAL_ERROR;
            }
            if (value2 == 255) {
                return NS_R_TIMEOUT;
            }
            switch (value2) {
                case 0:
                    return NS_R_NO_ERROR;
                case 1:
                    return NS_R_FORMERR;
                case 2:
                    return NS_R_SERVFAIL;
                case 3:
                    return NS_R_NXDOMAIN;
                case 4:
                    return NS_R_NOTIMPL;
                case 5:
                    return NS_R_REFUSED;
                case 6:
                    return NS_R_YXDOMAIN;
                case 7:
                    return NS_R_YXRRSET;
                case 8:
                    return NS_R_NXRRSET;
                case 9:
                    return NS_R_NOTAUTH;
                case 10:
                    return NS_R_NOTZONE;
                case 11:
                    return NS_R_MAX;
                default:
                    switch (value2) {
                        case 16:
                            return NS_R_BADVERS;
                        case 17:
                            return NS_R_BADKEY;
                        case 18:
                            return NS_R_BADTIME;
                        default:
                            return null;
                    }
            }
        }

        public static Internal.EnumLiteMap<NsRcode> internalGetValueMap() {
            return internalValueMap;
        }

        private NsRcode(int value2) {
            this.value = value2;
        }
    }

    public enum NsType implements Internal.EnumLite {
        NS_T_INVALID(0),
        NS_T_A(1),
        NS_T_NS(2),
        NS_T_MD(3),
        NS_T_MF(4),
        NS_T_CNAME(5),
        NS_T_SOA(6),
        NS_T_MB(7),
        NS_T_MG(8),
        NS_T_MR(9),
        NS_T_NULL(10),
        NS_T_WKS(11),
        NS_T_PTR(12),
        NS_T_HINFO(13),
        NS_T_MINFO(14),
        NS_T_MX(15),
        NS_T_TXT(16),
        NS_T_RP(17),
        NS_T_AFSDB(18),
        NS_T_X25(19),
        NS_T_ISDN(20),
        NS_T_RT(21),
        NS_T_NSAP(22),
        NS_T_NSAP_PTR(23),
        NS_T_SIG(24),
        NS_T_KEY(25),
        NS_T_PX(26),
        NS_T_GPOS(27),
        NS_T_AAAA(28),
        NS_T_LOC(29),
        NS_T_NXT(30),
        NS_T_EID(31),
        NS_T_NIMLOC(32),
        NS_T_SRV(33),
        NS_T_ATMA(34),
        NS_T_NAPTR(35),
        NS_T_KX(36),
        NS_T_CERT(37),
        NS_T_A6(38),
        NS_T_DNAME(39),
        NS_T_SINK(40),
        NS_T_OPT(41),
        NS_T_APL(42),
        NS_T_DS(43),
        NS_T_SSHFP(44),
        NS_T_IPSECKEY(45),
        NS_T_RRSIG(46),
        NS_T_NSEC(47),
        NS_T_DNSKEY(48),
        NS_T_DHCID(49),
        NS_T_NSEC3(50),
        NS_T_NSEC3PARAM(51),
        NS_T_HIP(55),
        NS_T_SPF(99),
        NS_T_TKEY(249),
        NS_T_TSIG(NS_T_TSIG_VALUE),
        NS_T_IXFR(NS_T_IXFR_VALUE),
        NS_T_AXFR(NS_T_AXFR_VALUE),
        NS_T_MAILB(253),
        NS_T_MAILA(254),
        NS_T_ANY(255),
        NS_T_ZXFR(256),
        NS_T_DLV(NS_T_DLV_VALUE),
        NS_T_MAX(65536);
        
        public static final int NS_T_A6_VALUE = 38;
        public static final int NS_T_AAAA_VALUE = 28;
        public static final int NS_T_AFSDB_VALUE = 18;
        public static final int NS_T_ANY_VALUE = 255;
        public static final int NS_T_APL_VALUE = 42;
        public static final int NS_T_ATMA_VALUE = 34;
        public static final int NS_T_AXFR_VALUE = 252;
        public static final int NS_T_A_VALUE = 1;
        public static final int NS_T_CERT_VALUE = 37;
        public static final int NS_T_CNAME_VALUE = 5;
        public static final int NS_T_DHCID_VALUE = 49;
        public static final int NS_T_DLV_VALUE = 32769;
        public static final int NS_T_DNAME_VALUE = 39;
        public static final int NS_T_DNSKEY_VALUE = 48;
        public static final int NS_T_DS_VALUE = 43;
        public static final int NS_T_EID_VALUE = 31;
        public static final int NS_T_GPOS_VALUE = 27;
        public static final int NS_T_HINFO_VALUE = 13;
        public static final int NS_T_HIP_VALUE = 55;
        public static final int NS_T_INVALID_VALUE = 0;
        public static final int NS_T_IPSECKEY_VALUE = 45;
        public static final int NS_T_ISDN_VALUE = 20;
        public static final int NS_T_IXFR_VALUE = 251;
        public static final int NS_T_KEY_VALUE = 25;
        public static final int NS_T_KX_VALUE = 36;
        public static final int NS_T_LOC_VALUE = 29;
        public static final int NS_T_MAILA_VALUE = 254;
        public static final int NS_T_MAILB_VALUE = 253;
        public static final int NS_T_MAX_VALUE = 65536;
        public static final int NS_T_MB_VALUE = 7;
        public static final int NS_T_MD_VALUE = 3;
        public static final int NS_T_MF_VALUE = 4;
        public static final int NS_T_MG_VALUE = 8;
        public static final int NS_T_MINFO_VALUE = 14;
        public static final int NS_T_MR_VALUE = 9;
        public static final int NS_T_MX_VALUE = 15;
        public static final int NS_T_NAPTR_VALUE = 35;
        public static final int NS_T_NIMLOC_VALUE = 32;
        public static final int NS_T_NSAP_PTR_VALUE = 23;
        public static final int NS_T_NSAP_VALUE = 22;
        public static final int NS_T_NSEC3PARAM_VALUE = 51;
        public static final int NS_T_NSEC3_VALUE = 50;
        public static final int NS_T_NSEC_VALUE = 47;
        public static final int NS_T_NS_VALUE = 2;
        public static final int NS_T_NULL_VALUE = 10;
        public static final int NS_T_NXT_VALUE = 30;
        public static final int NS_T_OPT_VALUE = 41;
        public static final int NS_T_PTR_VALUE = 12;
        public static final int NS_T_PX_VALUE = 26;
        public static final int NS_T_RP_VALUE = 17;
        public static final int NS_T_RRSIG_VALUE = 46;
        public static final int NS_T_RT_VALUE = 21;
        public static final int NS_T_SIG_VALUE = 24;
        public static final int NS_T_SINK_VALUE = 40;
        public static final int NS_T_SOA_VALUE = 6;
        public static final int NS_T_SPF_VALUE = 99;
        public static final int NS_T_SRV_VALUE = 33;
        public static final int NS_T_SSHFP_VALUE = 44;
        public static final int NS_T_TKEY_VALUE = 249;
        public static final int NS_T_TSIG_VALUE = 250;
        public static final int NS_T_TXT_VALUE = 16;
        public static final int NS_T_WKS_VALUE = 11;
        public static final int NS_T_X25_VALUE = 19;
        public static final int NS_T_ZXFR_VALUE = 256;
        private static final Internal.EnumLiteMap<NsType> internalValueMap = new Internal.EnumLiteMap<NsType>() {
            /* class android.stats.dnsresolver.DnsResolver.NsType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NsType findValueByNumber(int number) {
                return NsType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NsType valueOf(int value2) {
            return forNumber(value2);
        }

        public static NsType forNumber(int value2) {
            if (value2 == 55) {
                return NS_T_HIP;
            }
            if (value2 == 99) {
                return NS_T_SPF;
            }
            if (value2 == 32769) {
                return NS_T_DLV;
            }
            if (value2 == 65536) {
                return NS_T_MAX;
            }
            switch (value2) {
                case 0:
                    return NS_T_INVALID;
                case 1:
                    return NS_T_A;
                case 2:
                    return NS_T_NS;
                case 3:
                    return NS_T_MD;
                case 4:
                    return NS_T_MF;
                case 5:
                    return NS_T_CNAME;
                case 6:
                    return NS_T_SOA;
                case 7:
                    return NS_T_MB;
                case 8:
                    return NS_T_MG;
                case 9:
                    return NS_T_MR;
                case 10:
                    return NS_T_NULL;
                case 11:
                    return NS_T_WKS;
                case 12:
                    return NS_T_PTR;
                case 13:
                    return NS_T_HINFO;
                case 14:
                    return NS_T_MINFO;
                case 15:
                    return NS_T_MX;
                case 16:
                    return NS_T_TXT;
                case 17:
                    return NS_T_RP;
                case 18:
                    return NS_T_AFSDB;
                case 19:
                    return NS_T_X25;
                case 20:
                    return NS_T_ISDN;
                case 21:
                    return NS_T_RT;
                case 22:
                    return NS_T_NSAP;
                case 23:
                    return NS_T_NSAP_PTR;
                case 24:
                    return NS_T_SIG;
                case 25:
                    return NS_T_KEY;
                case 26:
                    return NS_T_PX;
                case 27:
                    return NS_T_GPOS;
                case 28:
                    return NS_T_AAAA;
                case 29:
                    return NS_T_LOC;
                case 30:
                    return NS_T_NXT;
                case 31:
                    return NS_T_EID;
                case 32:
                    return NS_T_NIMLOC;
                case 33:
                    return NS_T_SRV;
                case 34:
                    return NS_T_ATMA;
                case 35:
                    return NS_T_NAPTR;
                case 36:
                    return NS_T_KX;
                case 37:
                    return NS_T_CERT;
                case 38:
                    return NS_T_A6;
                case 39:
                    return NS_T_DNAME;
                case 40:
                    return NS_T_SINK;
                case 41:
                    return NS_T_OPT;
                case 42:
                    return NS_T_APL;
                case 43:
                    return NS_T_DS;
                case 44:
                    return NS_T_SSHFP;
                case 45:
                    return NS_T_IPSECKEY;
                case 46:
                    return NS_T_RRSIG;
                case 47:
                    return NS_T_NSEC;
                case 48:
                    return NS_T_DNSKEY;
                case 49:
                    return NS_T_DHCID;
                case 50:
                    return NS_T_NSEC3;
                case 51:
                    return NS_T_NSEC3PARAM;
                default:
                    switch (value2) {
                        case 249:
                            return NS_T_TKEY;
                        case NS_T_TSIG_VALUE:
                            return NS_T_TSIG;
                        case NS_T_IXFR_VALUE:
                            return NS_T_IXFR;
                        case NS_T_AXFR_VALUE:
                            return NS_T_AXFR;
                        case 253:
                            return NS_T_MAILB;
                        case 254:
                            return NS_T_MAILA;
                        case 255:
                            return NS_T_ANY;
                        case 256:
                            return NS_T_ZXFR;
                        default:
                            return null;
                    }
            }
        }

        public static Internal.EnumLiteMap<NsType> internalGetValueMap() {
            return internalValueMap;
        }

        private NsType(int value2) {
            this.value = value2;
        }
    }

    public enum IpVersion implements Internal.EnumLite {
        IV_UNKNOWN(0),
        IV_IPV4(1),
        IV_IPV6(2);
        
        public static final int IV_IPV4_VALUE = 1;
        public static final int IV_IPV6_VALUE = 2;
        public static final int IV_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<IpVersion> internalValueMap = new Internal.EnumLiteMap<IpVersion>() {
            /* class android.stats.dnsresolver.DnsResolver.IpVersion.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public IpVersion findValueByNumber(int number) {
                return IpVersion.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static IpVersion valueOf(int value2) {
            return forNumber(value2);
        }

        public static IpVersion forNumber(int value2) {
            if (value2 == 0) {
                return IV_UNKNOWN;
            }
            if (value2 == 1) {
                return IV_IPV4;
            }
            if (value2 != 2) {
                return null;
            }
            return IV_IPV6;
        }

        public static Internal.EnumLiteMap<IpVersion> internalGetValueMap() {
            return internalValueMap;
        }

        private IpVersion(int value2) {
            this.value = value2;
        }
    }

    public enum Protocol implements Internal.EnumLite {
        PROTO_UNKNOWN(0),
        PROTO_UDP(1),
        PROTO_TCP(2),
        PROTO_DOT(3);
        
        public static final int PROTO_DOT_VALUE = 3;
        public static final int PROTO_TCP_VALUE = 2;
        public static final int PROTO_UDP_VALUE = 1;
        public static final int PROTO_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Protocol> internalValueMap = new Internal.EnumLiteMap<Protocol>() {
            /* class android.stats.dnsresolver.DnsResolver.Protocol.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Protocol findValueByNumber(int number) {
                return Protocol.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Protocol valueOf(int value2) {
            return forNumber(value2);
        }

        public static Protocol forNumber(int value2) {
            if (value2 == 0) {
                return PROTO_UNKNOWN;
            }
            if (value2 == 1) {
                return PROTO_UDP;
            }
            if (value2 == 2) {
                return PROTO_TCP;
            }
            if (value2 != 3) {
                return null;
            }
            return PROTO_DOT;
        }

        public static Internal.EnumLiteMap<Protocol> internalGetValueMap() {
            return internalValueMap;
        }

        private Protocol(int value2) {
            this.value = value2;
        }
    }

    public enum PrivateDnsModes implements Internal.EnumLite {
        PDM_UNKNOWN(0),
        PDM_OFF(1),
        PDM_OPPORTUNISTIC(2),
        PDM_STRICT(3);
        
        public static final int PDM_OFF_VALUE = 1;
        public static final int PDM_OPPORTUNISTIC_VALUE = 2;
        public static final int PDM_STRICT_VALUE = 3;
        public static final int PDM_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<PrivateDnsModes> internalValueMap = new Internal.EnumLiteMap<PrivateDnsModes>() {
            /* class android.stats.dnsresolver.DnsResolver.PrivateDnsModes.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PrivateDnsModes findValueByNumber(int number) {
                return PrivateDnsModes.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PrivateDnsModes valueOf(int value2) {
            return forNumber(value2);
        }

        public static PrivateDnsModes forNumber(int value2) {
            if (value2 == 0) {
                return PDM_UNKNOWN;
            }
            if (value2 == 1) {
                return PDM_OFF;
            }
            if (value2 == 2) {
                return PDM_OPPORTUNISTIC;
            }
            if (value2 != 3) {
                return null;
            }
            return PDM_STRICT;
        }

        public static Internal.EnumLiteMap<PrivateDnsModes> internalGetValueMap() {
            return internalValueMap;
        }

        private PrivateDnsModes(int value2) {
            this.value = value2;
        }
    }

    public enum NetworkType implements Internal.EnumLite {
        NT_UNKNOWN(0),
        NT_CELLULAR(1),
        NT_WIFI(2),
        NT_BLUETOOTH(3),
        NT_ETHERNET(4),
        NT_VPN(5),
        NT_WIFI_AWARE(6),
        NT_LOWPAN(7);
        
        public static final int NT_BLUETOOTH_VALUE = 3;
        public static final int NT_CELLULAR_VALUE = 1;
        public static final int NT_ETHERNET_VALUE = 4;
        public static final int NT_LOWPAN_VALUE = 7;
        public static final int NT_UNKNOWN_VALUE = 0;
        public static final int NT_VPN_VALUE = 5;
        public static final int NT_WIFI_AWARE_VALUE = 6;
        public static final int NT_WIFI_VALUE = 2;
        private static final Internal.EnumLiteMap<NetworkType> internalValueMap = new Internal.EnumLiteMap<NetworkType>() {
            /* class android.stats.dnsresolver.DnsResolver.NetworkType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NetworkType findValueByNumber(int number) {
                return NetworkType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NetworkType valueOf(int value2) {
            return forNumber(value2);
        }

        public static NetworkType forNumber(int value2) {
            switch (value2) {
                case 0:
                    return NT_UNKNOWN;
                case 1:
                    return NT_CELLULAR;
                case 2:
                    return NT_WIFI;
                case 3:
                    return NT_BLUETOOTH;
                case 4:
                    return NT_ETHERNET;
                case 5:
                    return NT_VPN;
                case 6:
                    return NT_WIFI_AWARE;
                case 7:
                    return NT_LOWPAN;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NetworkType> internalGetValueMap() {
            return internalValueMap;
        }

        private NetworkType(int value2) {
            this.value = value2;
        }
    }

    public enum CacheStatus implements Internal.EnumLite {
        CS_UNSUPPORTED(0),
        CS_NOTFOUND(1),
        CS_FOUND(2),
        CS_SKIP(3);
        
        public static final int CS_FOUND_VALUE = 2;
        public static final int CS_NOTFOUND_VALUE = 1;
        public static final int CS_SKIP_VALUE = 3;
        public static final int CS_UNSUPPORTED_VALUE = 0;
        private static final Internal.EnumLiteMap<CacheStatus> internalValueMap = new Internal.EnumLiteMap<CacheStatus>() {
            /* class android.stats.dnsresolver.DnsResolver.CacheStatus.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CacheStatus findValueByNumber(int number) {
                return CacheStatus.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CacheStatus valueOf(int value2) {
            return forNumber(value2);
        }

        public static CacheStatus forNumber(int value2) {
            if (value2 == 0) {
                return CS_UNSUPPORTED;
            }
            if (value2 == 1) {
                return CS_NOTFOUND;
            }
            if (value2 == 2) {
                return CS_FOUND;
            }
            if (value2 != 3) {
                return null;
            }
            return CS_SKIP;
        }

        public static Internal.EnumLiteMap<CacheStatus> internalGetValueMap() {
            return internalValueMap;
        }

        private CacheStatus(int value2) {
            this.value = value2;
        }
    }

    public static final class DnsQueryEvent extends GeneratedMessageLite<DnsQueryEvent, Builder> implements DnsQueryEventOrBuilder {
        public static final int CACHE_HIT_FIELD_NUMBER = 3;
        public static final int CONNECTED_FIELD_NUMBER = 8;
        private static final DnsQueryEvent DEFAULT_INSTANCE = new DnsQueryEvent();
        public static final int DNS_SERVER_INDEX_FIELD_NUMBER = 7;
        public static final int IP_VERSION_FIELD_NUMBER = 4;
        public static final int LATENCY_MICROS_FIELD_NUMBER = 9;
        private static volatile Parser<DnsQueryEvent> PARSER = null;
        public static final int PROTOCOL_FIELD_NUMBER = 5;
        public static final int RCODE_FIELD_NUMBER = 1;
        public static final int RETRY_TIMES_FIELD_NUMBER = 6;
        public static final int TYPE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int cacheHit_ = 0;
        private boolean connected_ = false;
        private int dnsServerIndex_ = 0;
        private int ipVersion_ = 0;
        private int latencyMicros_ = 0;
        private int protocol_ = 0;
        private int rcode_ = 0;
        private int retryTimes_ = 0;
        private int type_ = 0;

        private DnsQueryEvent() {
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasRcode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public NsRcode getRcode() {
            NsRcode result = NsRcode.forNumber(this.rcode_);
            return result == null ? NsRcode.NS_R_NO_ERROR : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRcode(NsRcode value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.rcode_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRcode() {
            this.bitField0_ &= -2;
            this.rcode_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public NsType getType() {
            NsType result = NsType.forNumber(this.type_);
            return result == null ? NsType.NS_T_INVALID : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(NsType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.type_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -3;
            this.type_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasCacheHit() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public CacheStatus getCacheHit() {
            CacheStatus result = CacheStatus.forNumber(this.cacheHit_);
            return result == null ? CacheStatus.CS_UNSUPPORTED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCacheHit(CacheStatus value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.cacheHit_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCacheHit() {
            this.bitField0_ &= -5;
            this.cacheHit_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasIpVersion() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public IpVersion getIpVersion() {
            IpVersion result = IpVersion.forNumber(this.ipVersion_);
            return result == null ? IpVersion.IV_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIpVersion(IpVersion value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.ipVersion_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIpVersion() {
            this.bitField0_ &= -9;
            this.ipVersion_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasProtocol() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public Protocol getProtocol() {
            Protocol result = Protocol.forNumber(this.protocol_);
            return result == null ? Protocol.PROTO_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProtocol(Protocol value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.protocol_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProtocol() {
            this.bitField0_ &= -17;
            this.protocol_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasRetryTimes() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public int getRetryTimes() {
            return this.retryTimes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRetryTimes(int value) {
            this.bitField0_ |= 32;
            this.retryTimes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRetryTimes() {
            this.bitField0_ &= -33;
            this.retryTimes_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasDnsServerIndex() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public int getDnsServerIndex() {
            return this.dnsServerIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDnsServerIndex(int value) {
            this.bitField0_ |= 64;
            this.dnsServerIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDnsServerIndex() {
            this.bitField0_ &= -65;
            this.dnsServerIndex_ = 0;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasConnected() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean getConnected() {
            return this.connected_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setConnected(boolean value) {
            this.bitField0_ |= 128;
            this.connected_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearConnected() {
            this.bitField0_ &= -129;
            this.connected_ = false;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public boolean hasLatencyMicros() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
        public int getLatencyMicros() {
            return this.latencyMicros_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLatencyMicros(int value) {
            this.bitField0_ |= 256;
            this.latencyMicros_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatencyMicros() {
            this.bitField0_ &= -257;
            this.latencyMicros_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.rcode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.type_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.cacheHit_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.ipVersion_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.protocol_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.retryTimes_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.dnsServerIndex_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.connected_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.latencyMicros_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.rcode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.type_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.cacheHit_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.ipVersion_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.protocol_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.retryTimes_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.dnsServerIndex_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.connected_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.latencyMicros_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DnsQueryEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DnsQueryEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DnsQueryEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DnsQueryEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DnsQueryEvent parseFrom(InputStream input) throws IOException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DnsQueryEvent parseDelimitedFrom(InputStream input) throws IOException {
            return (DnsQueryEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DnsQueryEvent parseFrom(CodedInputStream input) throws IOException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DnsQueryEvent prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DnsQueryEvent, Builder> implements DnsQueryEventOrBuilder {
            private Builder() {
                super(DnsQueryEvent.DEFAULT_INSTANCE);
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasRcode() {
                return ((DnsQueryEvent) this.instance).hasRcode();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public NsRcode getRcode() {
                return ((DnsQueryEvent) this.instance).getRcode();
            }

            public Builder setRcode(NsRcode value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setRcode(value);
                return this;
            }

            public Builder clearRcode() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearRcode();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasType() {
                return ((DnsQueryEvent) this.instance).hasType();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public NsType getType() {
                return ((DnsQueryEvent) this.instance).getType();
            }

            public Builder setType(NsType value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearType();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasCacheHit() {
                return ((DnsQueryEvent) this.instance).hasCacheHit();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public CacheStatus getCacheHit() {
                return ((DnsQueryEvent) this.instance).getCacheHit();
            }

            public Builder setCacheHit(CacheStatus value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setCacheHit(value);
                return this;
            }

            public Builder clearCacheHit() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearCacheHit();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasIpVersion() {
                return ((DnsQueryEvent) this.instance).hasIpVersion();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public IpVersion getIpVersion() {
                return ((DnsQueryEvent) this.instance).getIpVersion();
            }

            public Builder setIpVersion(IpVersion value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setIpVersion(value);
                return this;
            }

            public Builder clearIpVersion() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearIpVersion();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasProtocol() {
                return ((DnsQueryEvent) this.instance).hasProtocol();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public Protocol getProtocol() {
                return ((DnsQueryEvent) this.instance).getProtocol();
            }

            public Builder setProtocol(Protocol value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setProtocol(value);
                return this;
            }

            public Builder clearProtocol() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearProtocol();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasRetryTimes() {
                return ((DnsQueryEvent) this.instance).hasRetryTimes();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public int getRetryTimes() {
                return ((DnsQueryEvent) this.instance).getRetryTimes();
            }

            public Builder setRetryTimes(int value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setRetryTimes(value);
                return this;
            }

            public Builder clearRetryTimes() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearRetryTimes();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasDnsServerIndex() {
                return ((DnsQueryEvent) this.instance).hasDnsServerIndex();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public int getDnsServerIndex() {
                return ((DnsQueryEvent) this.instance).getDnsServerIndex();
            }

            public Builder setDnsServerIndex(int value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setDnsServerIndex(value);
                return this;
            }

            public Builder clearDnsServerIndex() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearDnsServerIndex();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasConnected() {
                return ((DnsQueryEvent) this.instance).hasConnected();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean getConnected() {
                return ((DnsQueryEvent) this.instance).getConnected();
            }

            public Builder setConnected(boolean value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setConnected(value);
                return this;
            }

            public Builder clearConnected() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearConnected();
                return this;
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public boolean hasLatencyMicros() {
                return ((DnsQueryEvent) this.instance).hasLatencyMicros();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventOrBuilder
            public int getLatencyMicros() {
                return ((DnsQueryEvent) this.instance).getLatencyMicros();
            }

            public Builder setLatencyMicros(int value) {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).setLatencyMicros(value);
                return this;
            }

            public Builder clearLatencyMicros() {
                copyOnWrite();
                ((DnsQueryEvent) this.instance).clearLatencyMicros();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DnsQueryEvent();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DnsQueryEvent other = (DnsQueryEvent) arg1;
                    this.rcode_ = visitor.visitInt(hasRcode(), this.rcode_, other.hasRcode(), other.rcode_);
                    this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                    this.cacheHit_ = visitor.visitInt(hasCacheHit(), this.cacheHit_, other.hasCacheHit(), other.cacheHit_);
                    this.ipVersion_ = visitor.visitInt(hasIpVersion(), this.ipVersion_, other.hasIpVersion(), other.ipVersion_);
                    this.protocol_ = visitor.visitInt(hasProtocol(), this.protocol_, other.hasProtocol(), other.protocol_);
                    this.retryTimes_ = visitor.visitInt(hasRetryTimes(), this.retryTimes_, other.hasRetryTimes(), other.retryTimes_);
                    this.dnsServerIndex_ = visitor.visitInt(hasDnsServerIndex(), this.dnsServerIndex_, other.hasDnsServerIndex(), other.dnsServerIndex_);
                    this.connected_ = visitor.visitBoolean(hasConnected(), this.connected_, other.hasConnected(), other.connected_);
                    this.latencyMicros_ = visitor.visitInt(hasLatencyMicros(), this.latencyMicros_, other.hasLatencyMicros(), other.latencyMicros_);
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
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                if (NsRcode.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.rcode_ = rawValue;
                                }
                            } else if (tag == 16) {
                                int rawValue2 = input.readEnum();
                                if (NsType.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(2, rawValue2);
                                } else {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.type_ = rawValue2;
                                }
                            } else if (tag == 24) {
                                int rawValue3 = input.readEnum();
                                if (CacheStatus.forNumber(rawValue3) == null) {
                                    super.mergeVarintField(3, rawValue3);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.cacheHit_ = rawValue3;
                                }
                            } else if (tag == 32) {
                                int rawValue4 = input.readEnum();
                                if (IpVersion.forNumber(rawValue4) == null) {
                                    super.mergeVarintField(4, rawValue4);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.ipVersion_ = rawValue4;
                                }
                            } else if (tag == 40) {
                                int rawValue5 = input.readEnum();
                                if (Protocol.forNumber(rawValue5) == null) {
                                    super.mergeVarintField(5, rawValue5);
                                } else {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.protocol_ = rawValue5;
                                }
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.retryTimes_ = input.readInt32();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.dnsServerIndex_ = input.readInt32();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.connected_ = input.readBool();
                            } else if (tag == 72) {
                                this.bitField0_ |= 256;
                                this.latencyMicros_ = input.readInt32();
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
                        synchronized (DnsQueryEvent.class) {
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

        public static DnsQueryEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DnsQueryEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DnsQueryEvents extends GeneratedMessageLite<DnsQueryEvents, Builder> implements DnsQueryEventsOrBuilder {
        private static final DnsQueryEvents DEFAULT_INSTANCE = new DnsQueryEvents();
        public static final int DNS_QUERY_EVENT_FIELD_NUMBER = 1;
        private static volatile Parser<DnsQueryEvents> PARSER;
        private Internal.ProtobufList<DnsQueryEvent> dnsQueryEvent_ = emptyProtobufList();

        private DnsQueryEvents() {
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
        public List<DnsQueryEvent> getDnsQueryEventList() {
            return this.dnsQueryEvent_;
        }

        public List<? extends DnsQueryEventOrBuilder> getDnsQueryEventOrBuilderList() {
            return this.dnsQueryEvent_;
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
        public int getDnsQueryEventCount() {
            return this.dnsQueryEvent_.size();
        }

        @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
        public DnsQueryEvent getDnsQueryEvent(int index) {
            return this.dnsQueryEvent_.get(index);
        }

        public DnsQueryEventOrBuilder getDnsQueryEventOrBuilder(int index) {
            return this.dnsQueryEvent_.get(index);
        }

        private void ensureDnsQueryEventIsMutable() {
            if (!this.dnsQueryEvent_.isModifiable()) {
                this.dnsQueryEvent_ = GeneratedMessageLite.mutableCopy(this.dnsQueryEvent_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDnsQueryEvent(int index, DnsQueryEvent value) {
            if (value != null) {
                ensureDnsQueryEventIsMutable();
                this.dnsQueryEvent_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDnsQueryEvent(int index, DnsQueryEvent.Builder builderForValue) {
            ensureDnsQueryEventIsMutable();
            this.dnsQueryEvent_.set(index, (DnsQueryEvent) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDnsQueryEvent(DnsQueryEvent value) {
            if (value != null) {
                ensureDnsQueryEventIsMutable();
                this.dnsQueryEvent_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDnsQueryEvent(int index, DnsQueryEvent value) {
            if (value != null) {
                ensureDnsQueryEventIsMutable();
                this.dnsQueryEvent_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDnsQueryEvent(DnsQueryEvent.Builder builderForValue) {
            ensureDnsQueryEventIsMutable();
            this.dnsQueryEvent_.add((DnsQueryEvent) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addDnsQueryEvent(int index, DnsQueryEvent.Builder builderForValue) {
            ensureDnsQueryEventIsMutable();
            this.dnsQueryEvent_.add(index, (DnsQueryEvent) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllDnsQueryEvent(Iterable<? extends DnsQueryEvent> values) {
            ensureDnsQueryEventIsMutable();
            AbstractMessageLite.addAll(values, this.dnsQueryEvent_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDnsQueryEvent() {
            this.dnsQueryEvent_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeDnsQueryEvent(int index) {
            ensureDnsQueryEventIsMutable();
            this.dnsQueryEvent_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.dnsQueryEvent_.size(); i++) {
                output.writeMessage(1, this.dnsQueryEvent_.get(i));
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
            for (int i = 0; i < this.dnsQueryEvent_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.dnsQueryEvent_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DnsQueryEvents parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DnsQueryEvents parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DnsQueryEvents parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DnsQueryEvents parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DnsQueryEvents parseFrom(InputStream input) throws IOException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvents parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DnsQueryEvents parseDelimitedFrom(InputStream input) throws IOException {
            return (DnsQueryEvents) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvents parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvents) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DnsQueryEvents parseFrom(CodedInputStream input) throws IOException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DnsQueryEvents parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DnsQueryEvents) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DnsQueryEvents prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DnsQueryEvents, Builder> implements DnsQueryEventsOrBuilder {
            private Builder() {
                super(DnsQueryEvents.DEFAULT_INSTANCE);
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
            public List<DnsQueryEvent> getDnsQueryEventList() {
                return Collections.unmodifiableList(((DnsQueryEvents) this.instance).getDnsQueryEventList());
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
            public int getDnsQueryEventCount() {
                return ((DnsQueryEvents) this.instance).getDnsQueryEventCount();
            }

            @Override // android.stats.dnsresolver.DnsResolver.DnsQueryEventsOrBuilder
            public DnsQueryEvent getDnsQueryEvent(int index) {
                return ((DnsQueryEvents) this.instance).getDnsQueryEvent(index);
            }

            public Builder setDnsQueryEvent(int index, DnsQueryEvent value) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).setDnsQueryEvent((DnsQueryEvents) index, (int) value);
                return this;
            }

            public Builder setDnsQueryEvent(int index, DnsQueryEvent.Builder builderForValue) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).setDnsQueryEvent((DnsQueryEvents) index, (int) builderForValue);
                return this;
            }

            public Builder addDnsQueryEvent(DnsQueryEvent value) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).addDnsQueryEvent((DnsQueryEvents) value);
                return this;
            }

            public Builder addDnsQueryEvent(int index, DnsQueryEvent value) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).addDnsQueryEvent((DnsQueryEvents) index, (int) value);
                return this;
            }

            public Builder addDnsQueryEvent(DnsQueryEvent.Builder builderForValue) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).addDnsQueryEvent((DnsQueryEvents) builderForValue);
                return this;
            }

            public Builder addDnsQueryEvent(int index, DnsQueryEvent.Builder builderForValue) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).addDnsQueryEvent((DnsQueryEvents) index, (int) builderForValue);
                return this;
            }

            public Builder addAllDnsQueryEvent(Iterable<? extends DnsQueryEvent> values) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).addAllDnsQueryEvent(values);
                return this;
            }

            public Builder clearDnsQueryEvent() {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).clearDnsQueryEvent();
                return this;
            }

            public Builder removeDnsQueryEvent(int index) {
                copyOnWrite();
                ((DnsQueryEvents) this.instance).removeDnsQueryEvent(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DnsQueryEvents();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.dnsQueryEvent_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.dnsQueryEvent_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.dnsQueryEvent_, ((DnsQueryEvents) arg1).dnsQueryEvent_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                                if (!this.dnsQueryEvent_.isModifiable()) {
                                    this.dnsQueryEvent_ = GeneratedMessageLite.mutableCopy(this.dnsQueryEvent_);
                                }
                                this.dnsQueryEvent_.add((DnsQueryEvent) input.readMessage(DnsQueryEvent.parser(), extensionRegistry));
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
                        synchronized (DnsQueryEvents.class) {
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

        public static DnsQueryEvents getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DnsQueryEvents> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
