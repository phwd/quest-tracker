package com.facebook.proxygen;

import X.AnonymousClass08;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestStats {
    public static final Map mFlowTimeMap = Collections.unmodifiableMap(new HashMap<String, List<String>>() {
        /* class com.facebook.proxygen.RequestStats.AnonymousClass1 */

        {
            put(TraceEventType.RequestExchange, Arrays.asList(TraceFieldType.Uri, TraceFieldType.IsSecure, TraceFieldType.StatusCode, TraceFieldType.StatusMessage, TraceFieldType.ContentType, TraceFieldType.ClientPublicAddr, TraceFieldType.ServerCluster, TraceFieldType.LocalPort, TraceFieldType.ReqHeaderSize, TraceFieldType.ReqHeaderCompSize, TraceFieldType.ReqBodySize, TraceFieldType.RspHeaderSize, TraceFieldType.RspHeaderCompSize, TraceFieldType.RedirectLocation, TraceFieldType.NumRedirects, TraceFieldType.Protocol, TraceFieldType.FirstBodyByteFlushedRatio, TraceFieldType.LastBodyByteFlushedRatio, TraceFieldType.SecurityProtocol, TraceFieldType.FlowControlPauses, TraceFieldType.ReplaySafe, TraceFieldType.HTTPMethod, TraceFieldType.RangeRequest, TraceFieldType.TraceStringId, TraceFieldType.TransportReplaySafe, TraceFieldType.EvbAvgLoopTime, TraceFieldType.TripId, TraceFieldType.TimeSinceFirstHTTPClientInit, TraceFieldType.TimeSinceHTTPClientInit, TraceFieldType.NumberHTTPClientInits, TraceFieldType.PreviousNetworkType, TraceFieldType.NetworkType, TraceFieldType.HTTPResponsePriority, TraceFieldType.H3PriorityChanges));
            put(TraceEventType.ResponseBodyRead, Arrays.asList(TraceFieldType.RspIntvlAvg, TraceFieldType.RspIntvlStdDev, TraceFieldType.RspNumOnBody, TraceFieldType.ServerQuality, TraceFieldType.RecvToAck, TraceFieldType.ServerUpstreamLatency));
            put(TraceEventType.TotalConnect, Arrays.asList(new String[0]));
            put(TraceEventType.PreConnect, Arrays.asList(TraceFieldType.NewConnection, TraceFieldType.IsWaitingForNewConn, TraceFieldType.NewConnTimeout, TraceFieldType.InFlightConns, TraceFieldType.CachedSessions, TraceFieldType.CachedActiveSessions, TraceFieldType.ConnRoutingStale, TraceFieldType.StartedQuicAsync, TraceFieldType.ConnsStarted, TraceFieldType.RequestsWaited, TraceFieldType.TotalConnsStarted, TraceFieldType.TotalRequestsWaited, TraceFieldType.SessionCacheHitType, TraceFieldType.PerDomainLimit, TraceFieldType.DynamicDomainLimitRatio, TraceFieldType.LoadBalancing, TraceFieldType.MaxConnectionRetryCount, TraceFieldType.MaxIdleHTTPSessions, TraceFieldType.MaxIdleHTTP2Sessions, TraceFieldType.StartedExtraTcp, TraceFieldType.InflightQuicConns, TraceFieldType.QuicEnabled));
            put(TraceEventType.DnsConnect, Arrays.asList(new String[0]));
            put(TraceEventType.DnsResolution, Arrays.asList(TraceFieldType.HostName, TraceFieldType.IpAddr, TraceFieldType.Port, TraceFieldType.CanonicalName, TraceFieldType.CNameRedirects, TraceFieldType.NumberResolvers, TraceFieldType.ResolversSerialized, TraceFieldType.RequestFamily, TraceFieldType.NumberAnswers, TraceFieldType.TXT, TraceFieldType.DNSCryptUsed, TraceFieldType.DNSCryptCertSerial));
            put(TraceEventType.DnsCache, Arrays.asList(TraceFieldType.HostName, TraceFieldType.IpAddr, TraceFieldType.Port, TraceFieldType.CanonicalName, TraceFieldType.CNameRedirects, TraceFieldType.NumberResolvers, TraceFieldType.RequestFamily, TraceFieldType.NumberAnswers, TraceFieldType.DNSCacheHit, TraceFieldType.DNSCacheHitV4, TraceFieldType.DNSCacheHitV6, TraceFieldType.DNSCacheStale, TraceFieldType.DNSCacheStaleV4, TraceFieldType.DNSCacheStaleV6));
            put(TraceEventType.RetryingDnsResolution, Arrays.asList(TraceFieldType.NumberDNSRetries, TraceFieldType.ResolvedSuccess));
            put(TraceEventType.TcpConnect, Arrays.asList(TraceFieldType.CachedFamily, TraceFieldType.CachedFamilyStatus, TraceFieldType.HEDrivenDns, TraceFieldType.HESerializedEvents));
            put(TraceEventType.TlsSetup, Arrays.asList(TraceFieldType.TLSReused, TraceFieldType.TLSCacheHit, TraceFieldType.CipherName, TraceFieldType.TLSVersion, TraceFieldType.OpenSSLVersion, TraceFieldType.TLSCachePersistence));
            put(TraceEventType.ProxyConnect, Arrays.asList(TraceFieldType.ProxyHost, TraceFieldType.ProxyPort));
            put(TraceEventType.PostConnect, Arrays.asList(TraceFieldType.NewSession, TraceFieldType.NumWaiting, TraceFieldType.TransportType, TraceFieldType.LocalAddr, TraceFieldType.LocalPort));
            put(TraceEventType.SessionTransactions, Arrays.asList(TraceFieldType.CurrentTransactions, TraceFieldType.HistoricalMaximumTransactions, TraceFieldType.NumberTransactionsServed));
            put(TraceEventType.TransportInfo, Arrays.asList(TraceFieldType.Cwnd, TraceFieldType.CwndBytes, TraceFieldType.TotalRetx, TraceFieldType.InflightPacketLoss, TraceFieldType.RTO, TraceFieldType.MSS, TraceFieldType.MTU, TraceFieldType.RcvWnd, TraceFieldType.UpstreamCapacity, TraceFieldType.QuicClientCID, TraceFieldType.QuicServerCID, TraceFieldType.RTOCount, TraceFieldType.TotalRTOCount, TraceFieldType.QuicTransportSent, TraceFieldType.QuicTransportRecvd));
            put(TraceEventType.PostTransactionTransportInfo, Arrays.asList(TraceFieldType.RTT, TraceFieldType.RTTVar));
            put(TraceEventType.ConnInfo, Arrays.asList(TraceFieldType.ConnLifeSpan, TraceFieldType.EgressBuffered, TraceFieldType.SessionType));
            put(TraceEventType.Decompression, Arrays.asList(TraceFieldType.RspBodySize, TraceFieldType.RspBodyCompSize, TraceFieldType.CompressionType, TraceFieldType.RspBodyDecompressionTime));
            put(TraceEventType.ReplaySafety, Arrays.asList(new String[0]));
            put(TraceEventType.Push, Arrays.asList(TraceFieldType.IsPushRequest, TraceFieldType.PushConnectedInFlight, TraceFieldType.PushOrphaned));
            put(TraceEventType.CertVerification, Arrays.asList(TraceFieldType.VerificationImpl));
            put(TraceEventType.FizzConnect, Arrays.asList(TraceFieldType.FizzHandshakeSuccess, TraceFieldType.FizzProtocolVersion, TraceFieldType.FizzPskType, TraceFieldType.FizzEarlyDataType, TraceFieldType.PskUses));
            put(TraceEventType.QuicConnect, Arrays.asList(TraceFieldType.QuicClientCID, TraceFieldType.QuicDNSAddrs, TraceFieldType.QuicEarlyDataEnabled, TraceFieldType.PskUses));
            put(TraceEventType.ConnSelector, Arrays.asList(TraceFieldType.QuicResult));
            put(TraceEventType.NetworkChange, Arrays.asList(TraceFieldType.NetworkChangeDetail));
            put(TraceEventType.DnsFallback, Arrays.asList(TraceFieldType.DNSFallbackOutcome));
        }
    });
    public TraceEvent[] mEvents;

    public static boolean isValidCodecProtocolStr(String str) {
        if (str.equals("http/1.1") || str.equals("http/2")) {
            return true;
        }
        return false;
    }

    public Map getCertificateVerificationData() {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        TraceEvent[] traceEventArr = this.mEvents;
        boolean z = false;
        for (TraceEvent traceEvent : traceEventArr) {
            if (traceEvent.mName.equals(TraceEventType.CertVerification)) {
                for (Map.Entry entry : traceEvent.getMetaData().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
                z = true;
            } else {
                if (traceEvent.mName.equals(TraceEventType.QuicConnect) || traceEvent.mName.equals(TraceEventType.TcpConnect) || traceEvent.mName.equals(TraceEventType.PostConnect)) {
                    Map metaData = traceEvent.getMetaData();
                    String readStrMeta = readStrMeta(metaData, TraceFieldType.ServerAddr, OacrConstants.AUTO_SPEECH_DOMAIN);
                    int readIntMeta = readIntMeta(metaData, TraceFieldType.ServerPort, 0);
                    if (!readStrMeta.equals(OacrConstants.AUTO_SPEECH_DOMAIN) && readIntMeta > 0) {
                        str = AnonymousClass08.A05(readStrMeta, ":", String.valueOf(readIntMeta));
                        str2 = TraceFieldType.VerifiedServerAddress;
                    }
                } else if (traceEvent.mName.equals(TraceEventType.TlsSetup)) {
                    Map metaData2 = traceEvent.getMetaData();
                    String readStrMeta2 = readStrMeta(metaData2, TraceFieldType.CipherName, OacrConstants.AUTO_SPEECH_DOMAIN);
                    int readIntMeta2 = readIntMeta(metaData2, TraceFieldType.TLSVersion, 0);
                    long readIntMeta3 = (long) readIntMeta(metaData2, TraceFieldType.OpenSSLVersion, 0);
                    if (!readStrMeta2.equals(OacrConstants.AUTO_SPEECH_DOMAIN)) {
                        hashMap.put(TraceFieldType.CipherName, readStrMeta2);
                    }
                    if (readIntMeta2 != 0) {
                        hashMap.put(TraceFieldType.TLSVersion, String.valueOf(readIntMeta2));
                    }
                    if (readIntMeta3 != 0) {
                        hashMap.put(TraceFieldType.OpenSSLVersion, String.valueOf(readIntMeta3));
                    }
                } else if (traceEvent.mName.equals(TraceEventType.ProxyConnect)) {
                    Map metaData3 = traceEvent.getMetaData();
                    String readStrMeta3 = readStrMeta(metaData3, TraceFieldType.ProxyHost, OacrConstants.AUTO_SPEECH_DOMAIN);
                    int readIntMeta4 = readIntMeta(metaData3, TraceFieldType.ProxyPort, 0);
                    if (!readStrMeta3.equals(OacrConstants.AUTO_SPEECH_DOMAIN) && readIntMeta4 > 0) {
                        str = AnonymousClass08.A05(readStrMeta3, ":", String.valueOf(readIntMeta4));
                        str2 = TraceFieldType.VerifiedProxyAddress;
                    }
                }
                hashMap.put(str2, str);
            }
        }
        if (!z) {
            return new HashMap(0);
        }
        return hashMap;
    }

    public HTTPFlowStats getFlowStats() {
        TraceEvent[] traceEventArr = this.mEvents;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = -1;
        long j10 = -1;
        long j11 = -1;
        long j12 = -1;
        long j13 = -1;
        long j14 = -1;
        long j15 = -1;
        long j16 = -1;
        long j17 = -1;
        String str = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str3 = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str4 = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str5 = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str6 = OacrConstants.AUTO_SPEECH_DOMAIN;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z5 = false;
        int i9 = -1;
        for (TraceEvent traceEvent : traceEventArr) {
            Map metaData = traceEvent.getMetaData();
            String str7 = traceEvent.mName;
            if (TraceEventType.RequestExchange.equals(str7)) {
                str5 = readStrMeta(metaData, TraceFieldType.Protocol, OacrConstants.AUTO_SPEECH_DOMAIN);
                z = isValidCodecProtocolStr(str5);
                str4 = readStrMeta(metaData, TraceFieldType.ClientPublicAddr, OacrConstants.AUTO_SPEECH_DOMAIN);
                str3 = readStrMeta(metaData, TraceFieldType.ServerCluster, OacrConstants.AUTO_SPEECH_DOMAIN);
                i2 = readIntMeta(metaData, TraceFieldType.LocalPort, 0);
                i3 = readIntMeta(metaData, TraceFieldType.ReqHeaderSize, 0);
                i4 = readIntMeta(metaData, TraceFieldType.ReqHeaderCompSize, 0);
                z4 = true;
                z3 = false;
                if (i3 != 0) {
                    z3 = true;
                }
                i5 = readIntMeta(metaData, TraceFieldType.ReqBodySize, 0);
                i6 = readIntMeta(metaData, TraceFieldType.RspHeaderSize, 0);
                i7 = readIntMeta(metaData, TraceFieldType.RspHeaderCompSize, 0);
                if (i6 == 0) {
                    z4 = false;
                }
                j15 = (long) readIntMeta(metaData, TraceFieldType.RequestSendTime, 0);
                j16 = (long) readIntMeta(metaData, TraceFieldType.FirstByteFlushed, 0);
                j17 = (long) readIntMeta(metaData, TraceFieldType.LastByteFlushed, 0);
                i9 = readIntMeta(metaData, TraceFieldType.StatusCode, 0);
            } else {
                if (TraceEventType.PostConnect.equals(str7)) {
                    z2 = readBooleanMeta(metaData, TraceFieldType.NewSession, false);
                } else if (TraceEventType.ResponseBodyRead.equals(str7)) {
                    j8 = traceEvent.mEnd - traceEvent.mStart;
                    str6 = readStrMeta(metaData, TraceFieldType.ServerQuality, OacrConstants.AUTO_SPEECH_DOMAIN);
                    j9 = (long) readIntMeta(metaData, TraceFieldType.ServerRtt, -1);
                    j10 = (long) readIntMeta(metaData, TraceFieldType.ServerRtx, -1);
                    j11 = (long) readIntMeta(metaData, TraceFieldType.ServerCwnd, -1);
                    j12 = (long) readIntMeta(metaData, TraceFieldType.ServerMss, -1);
                    j13 = (long) readIntMeta(metaData, TraceFieldType.ServerTotalBytesWritten, -1);
                    j14 = (long) readIntMeta(metaData, TraceFieldType.ServerUpstreamLatency, -1);
                } else if (TraceEventType.TcpConnect.equals(str7)) {
                    str2 = readStrMeta(metaData, TraceFieldType.ServerAddr, str2);
                    j2 = traceEvent.mEnd - traceEvent.mStart;
                } else if (!TraceEventType.QuicConnect.equals(str7)) {
                    if (TraceEventType.PreConnect.equals(str7) || TraceEventType.DnsCache.equals(str7)) {
                        str = readStrMeta(metaData, TraceFieldType.HostName, str);
                    } else if (TraceEventType.DnsResolution.equals(str7)) {
                        str = readStrMeta(metaData, TraceFieldType.HostName, str);
                        j = traceEvent.mEnd - traceEvent.mStart;
                    } else if (TraceEventType.TlsSetup.equals(str7)) {
                        j3 = traceEvent.mEnd - traceEvent.mStart;
                    } else if (TraceEventType.Decompression.equals(str7)) {
                        i8 = readIntMeta(metaData, TraceFieldType.RspBodySize, 0);
                        int readIntMeta = readIntMeta(metaData, TraceFieldType.RspBodyCompSize, 0);
                        if (readIntMeta == 0 || (i != 0 && readIntMeta >= i)) {
                            readIntMeta = i;
                        }
                        i = readIntMeta;
                    } else if (TraceEventType.PostTransactionTransportInfo.equals(str7)) {
                        j4 = (long) readIntMeta(metaData, TraceFieldType.RTT, 0);
                    } else if (TraceEventType.TotalRequest.equals(str7)) {
                        j5 = (long) readIntMeta(metaData, TraceFieldType.TTFB, 0);
                        j6 = (long) readIntMeta(metaData, TraceFieldType.TTLB, 0);
                    } else if (TraceEventType.TotalConnect.equals(str7)) {
                        j7 = traceEvent.mEnd - traceEvent.mStart;
                    } else if (TraceEventType.Push.equals(str7)) {
                        z5 = readBooleanMeta(metaData, TraceFieldType.IsPushRequest, false);
                    }
                }
                str2 = readStrMeta(metaData, TraceFieldType.ServerAddr, str2);
            }
        }
        return new HTTPFlowStats(str, str2, str3, str4, i2, z, z2, z3, z4, false, str5, i3, i4, i5, i6, i7, i8, i, j, j2, j3, j4, j5, j6, j7, j8, str6, new ConnQuality(j9, j10, j11, j12, j13, j14), j15, z5, j16, j17, i9);
    }

    public Map getFlowTimeData() {
        String str;
        HashMap hashMap = new HashMap();
        TraceEvent[] traceEventArr = this.mEvents;
        for (TraceEvent traceEvent : traceEventArr) {
            if (mFlowTimeMap.containsKey(traceEvent.mName)) {
                Map metaData = traceEvent.getMetaData();
                long j = traceEvent.mStart;
                if (j != 0) {
                    long j2 = traceEvent.mEnd;
                    if (j2 != 0 && j < j2) {
                        hashMap.put(traceEvent.mName, String.valueOf(j2 - j));
                    }
                }
                for (Object obj : (List) mFlowTimeMap.get(traceEvent.mName)) {
                    if (metaData.containsKey(obj)) {
                        hashMap.put(obj, metaData.get(obj));
                    }
                }
                if (metaData.containsKey(TraceFieldType.ServerAddr)) {
                    hashMap.put(TraceFieldType.ServerAddr, metaData.get(TraceFieldType.ServerAddr));
                    hashMap.put(TraceFieldType.ServerPort, metaData.get(TraceFieldType.ServerPort));
                }
                if (metaData.containsKey(TraceFieldType.Error)) {
                    if (hashMap.containsKey(TraceFieldType.ErrorStage)) {
                        str = AnonymousClass08.A05((String) hashMap.get(TraceFieldType.ErrorStage), ";", traceEvent.mName);
                    } else {
                        str = traceEvent.mName;
                    }
                    hashMap.put(TraceFieldType.ErrorStage, str);
                    joinMetaFields(metaData, hashMap, TraceFieldType.Error);
                    joinMetaFields(metaData, hashMap, TraceFieldType.DirectionError);
                    joinMetaFields(metaData, hashMap, TraceFieldType.ProxygenError);
                    joinMetaFields(metaData, hashMap, TraceFieldType.CodecError);
                }
            }
        }
        return hashMap;
    }

    public RequestStats(TraceEvent[] traceEventArr) {
        this.mEvents = traceEventArr;
    }

    public static Map getFlowTimeFieldsMap() {
        return mFlowTimeMap;
    }

    public static void joinMetaFields(Map map, Map map2, String str) {
        if (!map.containsKey(str)) {
            return;
        }
        if (map2.containsKey(str)) {
            map2.put(str, AnonymousClass08.A05((String) map2.get(str), ";", (String) map.get(str)));
        } else {
            map2.put(str, map.get(str));
        }
    }

    public static boolean readBooleanMeta(Map map, String str, boolean z) {
        if (!map.containsKey(str)) {
            return z;
        }
        if (Boolean.parseBoolean((String) map.get(str)) || ((String) map.get(str)).equals("1")) {
            return true;
        }
        return false;
    }

    public static int readIntMeta(Map map, String str, int i) {
        if (!map.containsKey(str)) {
            return i;
        }
        try {
            return Integer.parseInt((String) map.get(str));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static String readStrMeta(Map map, String str, String str2) {
        if (!map.containsKey(str)) {
            return str2;
        }
        return (String) map.get(str);
    }

    public TraceEvent[] getTraceEvents() {
        return this.mEvents;
    }
}
