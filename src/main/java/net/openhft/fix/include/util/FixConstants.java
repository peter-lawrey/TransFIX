/*
 * Copyright 2013 peter.lawrey Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.fix.include.util;

/**
 * Represents actual Fix 4.2 header/tailer/messages/fields in static arrays. This class is loaded during initialization of
 * &lt;FixMessagePool&gt; class and access to array elements is via index thereafter.
 */
public class FixConstants {

    public final static String[] headerFieldName = {
            "BeginString", "BodyLength", "MsgType", "SenderCompID", "TargetCompID", "OnBehalfOfCompID", "DeliverToCompID", "SecureDataLen",
            "SecureData", "MsgSeqNum", "SenderSubID", "SenderLocationID", "TargetSubID", "TargetLocationID", "OnBehalfOfSubID", "OnBehalfOfLocationID",
            "DeliverToSubID", "DeliverToLocationID", "PossDupFlag", "PossResend", "SendingTime", "OrigSendingTime", "XmlData", "XmlDataLen",
            "MessageEncoding", "LastMsgSeqNumProcessed", "OnBehalfOfSendingTime"
    };

    public final static String[] trailerFieldName = {"SignatureLength", "Signature", "CheckSum"};

    public final static String[] messagesMsgType = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",
            "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"
    };
    public final static String[] messsagesMsgName = {
            "Heartbeat", "TestRequest", "ResendRequest", "Reject", "SequenceReset", "Logout", "IndicationofInterest", "Advertisement", "ExecutionReport",
            "OrderCancelReject", "Logon", "News", "Email", "NewOrderSingle", "NewOrderList", "OrderCancelRequest", "OrderCancelReplaceRequest", "OrderStatusRequest",
            "Allocation", "ListCancelRequest", "ListExecute", "ListStatusRequest", "ListStatus", "AllocationACK", "DontKnowTrade", "QuoteRequest", "Quote",
            "SettlementInstructions", "MarketDataRequest", "MarketDataSnapshotFullRefresh", "MarketDataIncrementalRefresh", "MarketDataRequestReject",
            "QuoteCancel", "QuoteStatusRequest", "QuoteAcknowledgement", "SecurityDefinitionRequest", "SecurityDefinition", "SecurityStatusRequest",
            "SecurityStatus", "TradingSessionStatusRequest", "TradingSessionStatus", "MassQuote", "BusinessMessageReject", "BidRequest", "BidResponse",
            "ListStrikePrice"
    };
    public final static String[] messagesMsgCat = {"admin", "app"};
    public final static String MESSAGES_TEST_REQID_FN = "TestReqID";
    public final static String[] messagesResendReq = {"BeginSeqNo", "EndSeqNo"};
    public final static String[] messagesReject = {"RefSeqNum", "RefTagID", "RefMsgType", "SessionRejectReason", "Text", "EncodedTextLen", "EncodedText"};
    public final static String[] messagesSeqReset = {"GapFillFlag", "NewSeqNo"};
    public final static String[] messagesLogout = {"Text", "EncodedTextLen", "EncodedText"};
    public final static String[] messagesIOI = {
            "IOIid", "IOITransType", "IOIRefID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay",
            "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Side", "IOIShares", "Price", "Currency", "ValidUntilTime", "IOIQltyInd",
            "IOINaturalFlag", "Text", "EncodedTextLen", "EncodedText", "TransactTime", "URLLink", "SpreadToBenchmark", "Benchmark"
    };
    public final static String[] messagesIOIGroup = {"NoIOIQualifiers", "NoRoutingIDs"};
    public final static String[] messagesIOIGroupFields = {"IOIQualifier", "RoutingType", "RoutingID"};
    public final static String[] messagesAdv = {
            "AdvId", "AdvTransType", "AdvRefID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay",
            "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "AdvSide", "Shares", "Price", "Currency", "TradeDate", "TransactTime",
            "Text", "EncodedTextLen", "EncodedText", "LastMkt", "URLLink", "TradingSessionID"
    };
    public final static String[] messageExecReport = {
            "OrderID", "SecondaryOrderID", "ClOrdID", "OrigClOrdID", "ClientID", "ExecBroker", "ListID", "ExecID", "ExecTransType", "ExecRefID",
            "ExecType", "OrdStatus", "OrdRejReason", "ExecRestatementReason", "Account", "SettlmntTyp", "FutSettDate", "Symbol", "SymbolSfx",
            "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute",
            "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen",
            "EncodedSecurityDesc", "Side", "OrderQty", "CashOrderQty", "OrdType", "Price", "StopPx", "PegDifference", "DiscretionInst", "DiscretionOffset",
            "Currency", "ComplianceID", "SolicitedFlag", "TimeInForce", "EffectiveTime", "ExpireDate", "ExpireTime", "ExecInst", "Rule80A", "LastShares",
            "LastPx", "LastSpotRate", "LastForwardPoints", "LastMkt", "TradingSessionID", "LastCapacity", "LeavesQty", "CumQty", "AvgPx", "DayOrderQty",
            "DayCumQty", "DayAvgPx", "GTBookingInst", "TradeDate", "TransactTime", "ReportToExch", "Commission", "CommType", "GrossTradeAmt", "SettlCurrAmt",
            "SettlCurrency", "SettlCurrFxRate", "SettlCurrFxRateCalc", "HandlInst", "MinQty", "MaxFloor", "OpenClose", "MaxShow", "Text", "EncodedTextLen",
            "EncodedText", "FutSettDate2", "OrderQty2", "ClearingFirm", "ClearingAccount", "MultiLegReportingType"
    };
    public final static String messageExecReportGrp = "NoContraBrokers";
    public final static String[] messageExecReportGrpFlds = {"ContraBroker", "ContraTrader", "ContraTradeQty", "ContraTradeTime"};
    public final static String[] messageOrderCancelRej = {
            "OrderID", "SecondaryOrderID", "ClOrdID", "OrigClOrdID", "OrdStatus", "ClientID", "ExecBroker", "ListID", "Account", "TransactTime",
            "CxlRejResponseTo", "CxlRejReason", "Text", "EncodedTextLen", "EncodedText",};
    public final static String[] messageLogon = {
            "EncryptMethod", "HeartBtInt", "RawDataLength", "RawData", "ResetSeqNumFlag", "MaxMessageSize"
    };
    public final static String[] messageLogonGrpFlds = {"RefMsgType", "MsgDirection"};
    public final static String messageLogonGrp = "NoMsgTypes";
    public final static String[] messageNews = {
            "OrigTime", "Urgency", "Headline", "EncodedHeadlineLen", "EncodedHeadline", "URLLink", "RawDataLength", "RawData"
    };
    public final static String[] messageNewsGrp = {"NoRoutingIDs", "NoRelatedSym", "LinesOfText"};
    public final static String[] messageNewsGrpFlds = {
            "RoutingType", "RoutingID", "RelatdSym", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear",
            "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange",
            "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc",
            "Text", "EncodedTextLen", "EncodedText"};
    public final static String[] messagesEmail = {
            "EmailThreadID", "EmailType", "OrigTime", "Subject", "EncodedSubjectLen", "EncodedSubject", "OrderID", "ClOrdID", "RawDataLength", "RawData"
    };
    public final static String[] messagesEmaiGrpFlds = messageNewsGrpFlds;
    public final static String[] messagesEmaiGrp = messageNewsGrp;
    public final static String[] messagesNOS = {
            "ClOrdID", "ClientID", "ExecBroker", "Account", "SettlmntTyp", "FutSettDate", "HandlInst", "ExecInst", "MinQty", "MaxFloor", "ExDestination",
            "ProcessCode", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "PrevClosePx", "Side", "LocateReqd", "TransactTime", "OrderQty",
            "CashOrderQty", "OrdType", "Price", "StopPx", "Currency", "ComplianceID", "SolicitedFlag", "IOIid", "QuoteID", "TimeInForce",
            "EffectiveTime", "ExpireDate", "ExpireTime", "GTBookingInst", "Commission", "CommType", "Rule80A", "ForexReq", "SettlCurrency",
            "Text", "EncodedTextLen", "EncodedText", "FutSettDate2", "OrderQty2", "OpenClose", "CoveredOrUncovered", "CustomerOrFirm",
            "MaxShow", "PegDifference", "DiscretionInst", "DiscretionOffset", "ClearingFirm", "ClearingAccount"
    };
    public final static String[] messagesNOSGrpFlds = {"AllocAccount", "AllocShares", "TradingSessionID"};
    public final static String[] messagesNOSGrp = {"NoAllocs", "NoTradingSessions"};
    public final static String[] messagesNOL = {
            "ListID", "BidID", "ClientBidID", "ProgRptReqs", "BidType", "ProgPeriodInterval", "ListExecInstType", "ListExecInst", "EncodedListExecInstLen",
            "EncodedListExecInst", "TotNoOrders",};
    public final static String[] messagesNOLOuterGrpFlds = {
            "ClOrdID", "ListSeqNo", "SettlInstMode", "ClientID", "ExecBroker", "Account", "SettlmntTyp", "FutSettDate", "HandlInst", "ExecInst",
            "MinQty", "MaxFloor", "ExDestination", "ProcessCode", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear",
            "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen",
            "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "PrevClosePx", "Side", "SideValueInd", "LocateReqd",
            "TransactTime", "OrderQty", "CashOrderQty", "OrdType", "Price", "StopPx", "Currency", "ComplianceID", "SolicitedFlag", "IOIid",
            "QuoteID", "TimeInForce", "EffectiveTime", "ExpireDate", "ExpireTime", "GTBookingInst", "Commission", "CommType", "Rule80A", "ForexReq",
            "SettlCurrency", "Text", "EncodedTextLen", "EncodedText", "FutSettDate2", "OrderQty2", "OpenClose", "CoveredOrUncovered", "CustomerOrFirm",
            "MaxShow", "PegDifference", "DiscretionInst", "DiscretionOffset", "ClearingFirm", "ClearingAccount"
    };
    public final static String messagesNOLOuterGrp = "NoOrders";
    public final static String[] messagesNOLInnerGrp = {"NoAllocs", "NoTradingSessions"};
    public final static String[] messagesNOLInnerGrpFlds = {"AllocAccount", "AllocShares", "TradingSessionID"};
    public final static String[] messagesOCR = {
            "OrigClOrdID", "OrderID", "ClOrdID", "ListID", "Account", "ClientID", "ExecBroker", "Symbol", "SymbolSfx", "SecurityID", "IDSource",
            "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate",
            "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc",
            "Side", "TransactTime", "OrderQty", "CashOrderQty", "ComplianceID", "SolicitedFlag", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesOCRGrp = messagesNOLInnerGrp;
    public final static String[] messagesOCRGrpFlds = messagesNOLInnerGrpFlds;
    public final static String[] messagesOSR = {
            "OrderID", "ClOrdID", "ClientID", "Account", "ExecBroker", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear",
            "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen",
            "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Side"};
    public final static String[] messagesAlloc = {
            "AllocID", "AllocTransType", "RefAllocID", "AllocLinkID", "AllocLinkType", "Side", "Symbol", "SymbolSfx", "SecurityID", "IDSource",
            "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate",
            "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc",
            "Shares", "LastMkt", "TradingSessionID", "AvgPx", "Currency", "AvgPrxPrecision", "TradeDate", "TransactTime", "SettlmntTyp", "FutSettDate",
            "GrossTradeAmt", "NetMoney", "OpenClose", "Text", "EncodedTextLen", "EncodedText", "NumDaysInterest", "AccruedInterestRate",
    };
    public final static String[] messagesAllocOGrp = {"NoOrders", "NoExecs", "NoAllocs"};
    public final static String messagesAllocIGrp = "NoMiscFees";
    public final static String[] messagesAllocIGrpFlds = {"MiscFeeAmt", "MiscFeeCurr", "MiscFeeType"};
    public final static String[] messagesAllocOGrpFlds = {"ClOrdID",
            "OrderID", "SecondaryOrderID", "ListID", "WaveNo", "LastShares", "ExecID", "LastPx", "LastCapacity", "AllocAccount", "AllocPrice",
            "AllocShares", "ProcessCode", "BrokerOfCredit", "NotifyBrokerOfCredit", "AllocHandlInst", "AllocText", "EncodedAllocTextLen",
            "EncodedAllocText", "ExecBroker", "ClientID", "Commission", "CommType", "AllocAvgPx", "AllocNetMoney", "SettlCurrAmt", "SettlCurrency",
            "SettlCurrFxRate", "SettlCurrFxRateCalc", "AccruedInterestAmt", "SettlInstMode",};
    public final static String[] messagesListCancelReq = {"ListID", "TransactTime", "Text", "EncodedTextLen", "EncodedText"};
    public final static String[] messagesListExec = {
            "ListID", "ClientBidID", "BidID", "TransactTime", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesListStatusReq = {
            "ListID", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesListStatus = {
            "ListID", "ListStatusType", "NoRpts", "ListOrderStatus", "RptSeq", "ListStatusText", "EncodedListStatusTextLen", "EncodedListStatusText",
            "TransactTime", "TotNoOrders"
    };
    public final static String[] messagesListStatusGrpFld = {
            "ClOrdID", "CumQty", "OrdStatus", "LeavesQty", "CxlQty", "AvgPx", "OrdRejReason", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesListStatusGrp = {"NoOrders"};
    public final static String[] messagesAllocationAck = {
            "ClientID", "ExecBroker", "AllocID", "TradeDate", "TransactTime", "AllocStatus", "AllocRejCode", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesDNT = {
            "OrderID", "ExecID", "DKReason", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay",
            "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen",
            "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Side", "OrderQty", "CashOrderQty", "LastShares",
            "LastPx", "Text", "EncodedTextLen", "EncodedText",
    };
    public final static String[] messagesQuoteReq = {"QuoteReqID"};
    public final static String[] messagesQuoteReqGrp = {"NoRelatedSym"};
    public final static String[] messagesQuoteReqGrpFld = {
            "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice",
            "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc",
            "EncodedSecurityDescLen", "EncodedSecurityDesc", "PrevClosePx", "QuoteRequestType", "TradingSessionID", "Side", "OrderQty", "FutSettDate",
            "OrdType", "FutSettDate2", "OrderQty2", "ExpireTime", "TransactTime", "Currency"
    };

    public final static String[] messagesQuote = {
            "QuoteReqID", "QuoteID", "QuoteResponseLevel", "TradingSessionID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType",
            "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange",
            "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "BidPx", "OfferPx",
            "BidSize", "OfferSize", "ValidUntilTime", "BidSpotRate", "OfferSpotRate", "BidForwardPoints", "OfferForwardPoints", "TransactTime",
            "FutSettDate", "OrdType", "FutSettDate2", "OrderQty2", "Currency"
    };

    public final static String[] messageSettlementInst = {
            "SettlInstID", "SettlInstTransType", "SettlInstRefID", "SettlInstMode", "SettlInstSource", "AllocAccount", "SettlLocation",
            "TradeDate", "AllocID", "LastMkt", "TradingSessionID", "Side", "SecurityType", "EffectiveTime", "TransactTime",
            "ClientID", "ExecBroker", "StandInstDbType", "StandInstDbName", "StandInstDbID", "SettlDeliveryType", "SettlDepositoryCode",
            "SettlBrkrCode", "SettlInstCode", "SecuritySettlAgentName", "SecuritySettlAgentCode", "SecuritySettlAgentAcctNum",
            "SecuritySettlAgentAcctName", "SecuritySettlAgentContactName", "SecuritySettlAgentContactPhone", "CashSettlAgentName",
            "CashSettlAgentCode", "CashSettlAgentAcctNum", "CashSettlAgentAcctName", "CashSettlAgentContactName", "CashSettlAgentContactPhone",
    };
    public final static String[] messagesMarketDataReq = {
            "MDReqID", "SubscriptionRequestType", "MarketDepth", "MDUpdateType", "AggregatedBook"
    };
    public final static String[] messagesMarketDataReqGrpFld = {
            "MDEntryType", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay",
            "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer",
            "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "TradingSessionID",
    };
    public final static String[] messagesMarketDataReqGrp = {
            "NoMDEntryTypes", "NoRelatedSym"
    };
    public final static String[] messagesMarketDataSSFReq = {
            "MDReqID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "FinancialStatus", "CorporateAction", "TotalVolumeTraded"
    };
    public final static String[] messagesMarketDataSSFReqGrpFld = {
            "MDEntryType", "MDEntryPx", "Currency", "MDEntrySize", "MDEntryDate", "MDEntryTime", "TickDirection", "MDMkt", "TradingSessionID",
            "QuoteCondition", "TradeCondition", "MDEntryOriginator", "LocationID", "DeskID", "OpenCloseSettleFlag", "TimeInForce", "ExpireDate",
            "ExpireTime", "MinQty", "ExecInst", "SellerDays", "OrderID", "QuoteEntryID", "MDEntryBuyer", "MDEntrySeller", "NumberOfOrders",
            "MDEntryPositionNo", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesMarketDataSSFReqGrp = {
            "NoMDEntries"
    };
    public final static String[] messagesMarketDataINCRReq = {
            "MDReqID"
    };
    public final static String[] messagesMarketDataINCRReqGrpFld = {
            "MDUpdateAction", "DeleteReason", "MDEntryType", "MDEntryID", "MDEntryRefID", "Symbol", "SymbolSfx", "SecurityID", "IDSource",
            "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate",
            "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc",
            "FinancialStatus", "CorporateAction", "MDEntryPx", "Currency", "MDEntrySize", "MDEntryDate", "MDEntryTime", "TickDirection",
            "MDMkt", "TradingSessionID", "QuoteCondition", "TradeCondition", "MDEntryOriginator", "LocationID", "DeskID", "OpenCloseSettleFlag",
            "TimeInForce", "ExpireDate", "ExpireTime", "MinQty", "ExecInst", "SellerDays", "OrderID", "QuoteEntryID", "MDEntryBuyer", "MDEntrySeller",
            "NumberOfOrders", "MDEntryPositionNo", "TotalVolumeTraded", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesMarketDataINCRReqGrp = {
            "NoMDEntries"
    };
    public final static String[] messagesMarketDataReqRej = {
            "MDReqID", "MDReqRejReason", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesQuoteCancel = {
            "QuoteReqID", "QuoteID", "QuoteCancelType", "QuoteResponseLevel", "TradingSessionID"
    };
    public final static String[] messagesQuoteCancelGrp = {
            "NoQuoteEntries"
    };
    public final static String[] messagesQuoteCancelGrpFlds = {
            "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice",
            "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc",
            "EncodedSecurityDescLen", "EncodedSecurityDesc", "UnderlyingSymbol"
    };
    public final static String[] messagesQuoteStmtReq = {
            "QuoteID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Side", "TradingSessionID"
    };
    public final static String[] messagesQuoteAck = {
            "QuoteReqID", "QuoteID", "QuoteAckStatus", "QuoteRejectReason", "QuoteResponseLevel", "TradingSessionID", "Text"
    };
    public final static String[] messagesQuoteAckGrp = {
            "NoQuoteSets"
    };
    public final static String[] messagesQuoteAckGrpFlds = {
            "QuoteSetID", "UnderlyingSymbol", "UnderlyingSymbolSfx", "UnderlyingSecurityID", "UnderlyingIDSource", "UnderlyingSecurityType",
            "UnderlyingMaturityMonthYear", "UnderlyingMaturityDay", "UnderlyingPutOrCall", "UnderlyingStrikePrice", "UnderlyingOptAttribute",
            "UnderlyingContractMultiplier", "UnderlyingCouponRate", "UnderlyingSecurityExchange", "UnderlyingIssuer", "EncodedUnderlyingIssuerLen",
            "EncodedUnderlyingIssuer", "UnderlyingSecurityDesc", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "TotQuoteEntries",
    };
    public final static String[] messagesIQuoteAckGrp = {
            "NoQuoteEntries"
    };
    public final static String[] messagesIQuoteAckGrpFlds = {
            "QuoteEntryID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "QuoteEntryRejectReason"
    };
    public final static String[] messagesSecDefReq = {
            "SecurityReqID", "SecurityRequestType", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear",
            "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer",
            "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Currency", "Text",
            "EncodedTextLen", "EncodedText", "TradingSessionID"
    };
    public final static String[] messagesSecDefReqGrp = {
            "NoRelatedSym"
    };
    public final static String[] messagesSecDefReqGrpFlds = {
            "UnderlyingSymbol", "UnderlyingSymbolSfx", "UnderlyingSecurityID", "UnderlyingIDSource", "UnderlyingSecurityType", "UnderlyingMaturityMonthYear",
            "UnderlyingMaturityDay", "UnderlyingPutOrCall", "UnderlyingStrikePrice", "UnderlyingOptAttribute", "UnderlyingContractMultiplier",
            "UnderlyingCouponRate", "UnderlyingSecurityExchange", "UnderlyingIssuer", "EncodedUnderlyingIssuerLen", "EncodedUnderlyingIssuer",
            "UnderlyingSecurityDesc", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "RatioQty", "Side", "UnderlyingCurrency"
    };
    public final static String[] messagesSecDef = {
            "SecurityReqID", "SecurityResponseID", "SecurityResponseType", "TotalNumSecurities", "Symbol", "SymbolSfx", "SecurityID", "IDSource",
            "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate",
            "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc",
            "Currency", "TradingSessionID", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesSecDefGrp = {
            "NoRelatedSym"
    };
    public final static String[] messagesSecDefGrpFlds = {
            "UnderlyingSymbol", "UnderlyingSymbolSfx", "UnderlyingSecurityID", "UnderlyingIDSource", "UnderlyingSecurityType", "UnderlyingMaturityMonthYear",
            "UnderlyingMaturityDay", "UnderlyingPutOrCall", "UnderlyingStrikePrice", "UnderlyingOptAttribute", "UnderlyingContractMultiplier",
            "UnderlyingCouponRate", "UnderlyingSecurityExchange", "UnderlyingIssuer", "EncodedUnderlyingIssuerLen", "EncodedUnderlyingIssuer",
            "UnderlyingSecurityDesc", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "RatioQty", "Side", "UnderlyingCurrency"
    };
    public final static String[] messagesSecStatusReq = {
            "SecurityStatusReqID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Currency", "SubscriptionRequestType", "TradingSessionID"
    };
    public final static String[] messagesSecStatus = {
            "SecurityStatusReqID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "Currency", "TradingSessionID", "UnsolicitedIndicator", "SecurityTradingStatus",
            "FinancialStatus", "CorporateAction", "HaltReasonChar", "InViewOfCommon", "DueToRelated", "BuyVolume", "SellVolume", "HighPx", "LowPx",
            "LastPx", "TransactTime", "Adjustment"
    };
    public final static String[] messagesTSSReq = {
            "TradSesReqID", "TradingSessionID", "TradSesMethod", "TradSesMode", "SubscriptionRequestType"
    };
    public final static String[] messagesTSS = {
            "TradSesReqID", "TradingSessionID", "TradSesMethod", "TradSesMode", "UnsolicitedIndicator", "TradSesStatus", "TradSesStartTime", "TradSesOpenTime",
            "TradSesPreCloseTime", "TradSesCloseTime", "TradSesEndTime", "TotalVolumeTraded", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesMassQ = {
            "QuoteReqID", "QuoteID", "QuoteResponseLevel", "DefBidSize", "DefOfferSize"
    };
    public final static String[] messagesMassQGrp = {
            "NoQuoteSets"
    };
    public final static String[] messagesMassQGrpFlds = {
            "QuoteSetID", "UnderlyingSymbol", "UnderlyingSymbolSfx", "UnderlyingSecurityID", "UnderlyingIDSource", "UnderlyingSecurityType", "UnderlyingMaturityMonthYear",
            "UnderlyingMaturityDay", "UnderlyingPutOrCall", "UnderlyingStrikePrice", "UnderlyingOptAttribute", "UnderlyingContractMultiplier",
            "UnderlyingCouponRate", "UnderlyingSecurityExchange", "UnderlyingIssuer", "EncodedUnderlyingIssuerLen", "EncodedUnderlyingIssuer",
            "UnderlyingSecurityDesc", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "QuoteSetValidUntilTime", "TotQuoteEntries",};
    public final static String[] messagesIMassQGrp = {
            "NoQuoteEntries"
    };
    public final static String[] messagesIMassQGrpFlds = {
            "QuoteEntryID", "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall",
            "StrikePrice", "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer",
            "SecurityDesc", "EncodedSecurityDescLen", "EncodedSecurityDesc", "BidPx", "OfferPx", "BidSize", "OfferSize", "ValidUntilTime",
            "BidSpotRate", "OfferSpotRate", "BidForwardPoints", "OfferForwardPoints", "TransactTime", "TradingSessionID", "FutSettDate",
            "OrdType", "FutSettDate2", "OrderQty2", "Currency"
    };
    public final static String[] messagesBMRej = {
            "RefSeqNum", "RefMsgType", "BusinessRejectRefID", "BusinessRejectReason", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesBidReq = {
            "BidID", "ClientBidID", "BidRequestTransType", "ListName", "TotalNumSecurities", "BidType", "NumTickets", "Currency", "SideValue1",
            "SideValue2", "LiquidityIndType", "WtAverageLiquidity", "ExchangeForPhysical", "OutMainCntryUIndex", "CrossPercent", "ProgRptReqs",
            "ProgPeriodInterval", "IncTaxInd", "ForexReq", "NumBidders", "TradeDate", "TradeType", "BasisPxType", "StrikeTime", "Text", "EncodedTextLen",
            "EncodedText"
    };
    public final static String[] messagesBidReqGrpFld = {
            "BidDescriptorType", "BidDescriptor", "SideValueInd", "LiquidityValue", "LiquidityNumSecurities", "LiquidityPctLow", "LiquidityPctHigh",
            "EFPTrackingError", "FairValue", "OutsideIndexPct", "ValueOfFutures", "ListID", "Side", "TradingSessionID", "NetGrossInd",
            "SettlmntTyp", "FutSettDate", "Account"
    };
    public final static String[] messagesBidReqGrp = {"NoBidDescriptors", "NoBidComponents"};
    public final static String[] messagesBRes = {
            "RefSeqNum", "RefMsgType", "BusinessRejectRefID", "BusinessRejectReason", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesBidRes = {"BidID", "ClientBidID"};
    public final static String[] messagesBidResGrpFld = {
            "Commission", "CommType", "ListID", "Country", "Side", "Price", "PriceType", "FairValue", "NetGrossInd", "SettlmntTyp",
            "FutSettDate", "TradingSessionID", "Text", "EncodedTextLen", "EncodedText"
    };
    public final static String[] messagesBidResGrp = {"NoBidComponents"};
    public final static String[] messagesLSPrice = {"BidID", "ClientBidID"};
    public final static String[] messagesLSPriceGrpFld = {
            "Symbol", "SymbolSfx", "SecurityID", "IDSource", "SecurityType", "MaturityMonthYear", "MaturityDay", "PutOrCall", "StrikePrice",
            "OptAttribute", "ContractMultiplier", "CouponRate", "SecurityExchange", "Issuer", "EncodedIssuerLen", "EncodedIssuer", "SecurityDesc",
            "EncodedSecurityDescLen", "EncodedSecurityDesc", "PrevClosePx", "ClOrdID", "Side", "Price", "Currency", "Text", "EncodedTextLen",
            "EncodedText"
    };
    public final static String[] messagesLSPriceGrp = {"NoStrikes"};


    public final static int[] fieldsNumber = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
            47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
            90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
            126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158,
            159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191,
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 223, 231, 262, 263, 264,
            265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297,
            298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329,
            330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361,
            362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393,
            394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425,
            426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446};
    public final static int[] fieldsWithDefinedValues = {
            4, 5, 13, 18, 20, 21, 22, 25, 27, 28, 29, 35, 39, 40, 43, 47, 54, 59, 61, 63, 71, 77, 81, 87, 88, 94, 97, 98, 102, 103, 104, 113, 114, 121, 123, 127, 130, 139, 141, 150,
            156, 160, 163, 165, 167, 169, 197, 201, 203, 204, 208, 209, 215, 219, 263, 265, 266, 269, 274, 276, 277, 279, 281, 285, 286, 291, 292, 297, 298, 300, 301, 303, 321,
            323, 325, 326, 327, 328, 329, 334, 338, 339, 340, 347, 368, 373, 374, 377, 378, 380, 385, 388, 409, 411, 414, 416, 418, 419, 423, 427, 430, 433, 434, 442
    };
    public final static String[] fieldsName = {
            "Account", "AdvId", "AdvRefID", "AdvSide", "AdvTransType", "AvgPx", "BeginSeqNo", "BeginString", "BodyLength", "CheckSum", "ClOrdID", "Commission",
            "CommType", "CumQty", "Currency", "EndSeqNo", "ExecID", "ExecInst", "ExecRefID", "ExecTransType", "HandlInst", "IDSource", "IOIid", "IOIOthSvc",
            "IOIQltyInd", "IOIRefID", "IOIShares", "IOITransType", "LastCapacity", "LastMkt", "LastPx", "LastShares", "LinesOfText", "MsgSeqNum", "MsgType",
            "NewSeqNo", "OrderID", "OrderQty", "OrdStatus", "OrdType", "OrigClOrdID", "OrigTime", "PossDupFlag", "Price", "RefSeqNum", "RelatdSym", "Rule80A",
            "SecurityID", "SenderCompID", "SenderSubID", "SendingDate", "SendingTime", "Shares", "Side", "Symbol", "TargetCompID", "TargetSubID", "Text", "TimeInForce",
            "TransactTime", "Urgency", "ValidUntilTime", "SettlmntTyp", "FutSettDate", "SymbolSfx", "ListID", "ListSeqNo", "TotNoOrders", "ListExecInst", "AllocID",
            "AllocTransType", "RefAllocID", "NoOrders", "AvgPrxPrecision", "TradeDate", "ExecBroker", "OpenClose", "NoAllocs", "AllocAccount", "AllocShares",
            "ProcessCode", "NoRpts", "RptSeq", "CxlQty", "NoDlvyInst", "DlvyInst", "AllocStatus", "AllocRejCode", "Signature", "SecureDataLen", "SecureData",
            "BrokerOfCredit", "SignatureLength", "EmailType", "RawDataLength", "RawData", "PossResend", "EncryptMethod", "StopPx", "ExDestination", "CxlRejReason",
            "OrdRejReason", "IOIQualifier", "WaveNo", "Issuer", "SecurityDesc", "HeartBtInt", "ClientID", "MinQty", "MaxFloor", "TestReqID", "ReportToExch",
            "LocateReqd", "OnBehalfOfCompID", "OnBehalfOfSubID", "QuoteID", "NetMoney", "SettlCurrAmt", "SettlCurrency", "ForexReq", "OrigSendingTime", "GapFillFlag",
            "NoExecs", "CxlType", "ExpireTime", "DKReason", "DeliverToCompID", "DeliverToSubID", "IOINaturalFlag", "QuoteReqID", "BidPx", "OfferPx", "BidSize",
            "OfferSize", "NoMiscFees", "MiscFeeAmt", "MiscFeeCurr", "MiscFeeType", "PrevClosePx", "ResetSeqNumFlag", "SenderLocationID", "TargetLocationID",
            "OnBehalfOfLocationID", "DeliverToLocationID", "NoRelatedSym", "Subject", "Headline", "URLLink", "ExecType", "LeavesQty", "CashOrderQty", "AllocAvgPx",
            "AllocNetMoney", "SettlCurrFxRate", "SettlCurrFxRateCalc", "NumDaysInterest", "AccruedInterestRate", "AccruedInterestAmt", "SettlInstMode", "AllocText",
            "SettlInstID", "SettlInstTransType", "EmailThreadID", "SettlInstSource", "SettlLocation", "SecurityType", "EffectiveTime", "StandInstDbType", "StandInstDbName",
            "StandInstDbID", "SettlDeliveryType", "SettlDepositoryCode", "SettlBrkrCode", "SettlInstCode", "SecuritySettlAgentName", "SecuritySettlAgentCode", "SecuritySettlAgentAcctNum",
            "SecuritySettlAgentAcctName", "SecuritySettlAgentContactName", "SecuritySettlAgentContactPhone", "CashSettlAgentName", "CashSettlAgentCode",
            "CashSettlAgentAcctNum", "CashSettlAgentAcctName", "CashSettlAgentContactName", "CashSettlAgentContactPhone", "BidSpotRate", "BidForwardPoints",
            "OfferSpotRate", "OfferForwardPoints", "OrderQty2", "FutSettDate2", "LastSpotRate", "LastForwardPoints", "AllocLinkID", "AllocLinkType", "SecondaryOrderID",
            "NoIOIQualifiers", "MaturityMonthYear", "PutOrCall", "StrikePrice", "CoveredOrUncovered", "CustomerOrFirm", "MaturityDay", "OptAttribute", "SecurityExchange",
            "NotifyBrokerOfCredit", "AllocHandlInst", "MaxShow", "PegDifference", "XmlDataLen", "XmlData", "SettlInstRefID", "NoRoutingIDs", "RoutingType", "RoutingID",
            "SpreadToBenchmark", "Benchmark", "CouponRate", "ContractMultiplier", "MDReqID", "SubscriptionRequestType", "MarketDepth", "MDUpdateType", "AggregatedBook",
            "NoMDEntryTypes", "NoMDEntries", "MDEntryType", "MDEntryPx", "MDEntrySize", "MDEntryDate", "MDEntryTime", "TickDirection", "MDMkt", "QuoteCondition", "TradeCondition",
            "MDEntryID", "MDUpdateAction", "MDEntryRefID", "MDReqRejReason", "MDEntryOriginator", "LocationID", "DeskID", "DeleteReason", "OpenCloseSettleFlag", "SellerDays",
            "MDEntryBuyer", "MDEntrySeller", "MDEntryPositionNo", "FinancialStatus", "CorporateAction", "DefBidSize", "DefOfferSize", "NoQuoteEntries", "NoQuoteSets",
            "QuoteAckStatus", "QuoteCancelType", "QuoteEntryID", "QuoteRejectReason", "QuoteResponseLevel", "QuoteSetID", "QuoteRequestType", "TotQuoteEntries",
            "UnderlyingIDSource", "UnderlyingIssuer", "UnderlyingSecurityDesc", "UnderlyingSecurityExchange", "UnderlyingSecurityID", "UnderlyingSecurityType", "UnderlyingSymbol",
            "UnderlyingSymbolSfx", "UnderlyingMaturityMonthYear", "UnderlyingMaturityDay", "UnderlyingPutOrCall", "UnderlyingStrikePrice", "UnderlyingOptAttribute",
            "UnderlyingCurrency", "RatioQty", "SecurityReqID", "SecurityRequestType", "SecurityResponseID", "SecurityResponseType", "SecurityStatusReqID", "UnsolicitedIndicator",
            "SecurityTradingStatus", "HaltReasonChar", "InViewOfCommon", "DueToRelated", "BuyVolume", "SellVolume", "HighPx", "LowPx", "Adjustment", "TradSesReqID", "TradingSessionID",
            "ContraTrader", "TradSesMethod", "TradSesMode", "TradSesStatus", "TradSesStartTime", "TradSesOpenTime", "TradSesPreCloseTime", "TradSesCloseTime", "TradSesEndTime",
            "NumberOfOrders", "MessageEncoding", "EncodedIssuerLen", "EncodedIssuer", "EncodedSecurityDescLen", "EncodedSecurityDesc", "EncodedListExecInstLen", "EncodedListExecInst",
            "EncodedTextLen", "EncodedText", "EncodedSubjectLen", "EncodedSubject", "EncodedHeadlineLen", "EncodedHeadline", "EncodedAllocTextLen", "EncodedAllocText",
            "EncodedUnderlyingIssuerLen", "EncodedUnderlyingIssuer", "EncodedUnderlyingSecurityDescLen", "EncodedUnderlyingSecurityDesc", "AllocPrice",
            "QuoteSetValidUntilTime", "QuoteEntryRejectReason", "LastMsgSeqNumProcessed", "OnBehalfOfSendingTime", "RefTagID", "RefMsgType", "SessionRejectReason",
            "BidRequestTransType", "ContraBroker", "ComplianceID", "SolicitedFlag", "ExecRestatementReason", "BusinessRejectRefID", "BusinessRejectReason", "GrossTradeAmt",
            "NoContraBrokers", "MaxMessageSize", "NoMsgTypes", "MsgDirection", "NoTradingSessions", "TotalVolumeTraded", "DiscretionInst", "DiscretionOffset",
            "BidID", "ClientBidID", "ListName", "TotalNumSecurities", "BidType", "NumTickets", "SideValue1", "SideValue2", "NoBidDescriptors", "BidDescriptorType", "BidDescriptor",
            "SideValueInd", "LiquidityPctLow", "LiquidityPctHigh", "LiquidityValue", "EFPTrackingError", "FairValue", "OutsideIndexPct", "ValueOfFutures", "LiquidityIndType",
            "WtAverageLiquidity", "ExchangeForPhysical", "OutMainCntryUIndex", "CrossPercent", "ProgRptReqs", "ProgPeriodInterval", "IncTaxInd", "NumBidders",
            "TradeType", "BasisPxType", "NoBidComponents", "Country", "TotNoStrikes", "PriceType", "DayOrderQty", "DayCumQty", "DayAvgPx", "GTBookingInst",
            "NoStrikes", "ListStatusType", "NetGrossInd", "ListOrderStatus", "ExpireDate", "ListExecInstType", "CxlRejResponseTo", "UnderlyingCouponRate",
            "UnderlyingContractMultiplier", "ContraTradeQty", "ContraTradeTime", "ClearingFirm", "ClearingAccount", "LiquidityNumSecurities", "MultiLegReportingType",
            "StrikeTime", "ListStatusText", "EncodedListStatusTextLen", "EncodedListStatusText"
    };
    public final static String[] fieldsTypeOrdering = {
            "STRING", "STRING", "STRING", "CHAR", "STRING", "PRICE", "INT", "STRING", "INT", "STRING", "STRING", "AMT",
            "CHAR", "QTY", "CURRENCY", "INT", "STRING", "MULTIPLEVALUESTRING", "STRING", "CHAR", "CHAR", "STRING", "STRING",
            "CHAR", "CHAR", "STRING", "STRING", "CHAR", "CHAR", "EXCHANGE", "PRICE", "QTY", "INT", "INT", "STRING", "INT", "STRING",
            "QTY", "CHAR", "CHAR", "STRING", "UTCTIMESTAMP", "BOOLEAN", "PRICE", "INT", "STRING", "CHAR", "STRING", "STRING", "STRING",
            "LOCALMKTDATE", "UTCTIMESTAMP", "QTY", "CHAR", "STRING", "STRING", "STRING", "STRING", "CHAR", "UTCTIMESTAMP", "CHAR",
            "UTCTIMESTAMP", "CHAR", "LOCALMKTDATE", "STRING", "STRING", "INT", "INT", "STRING", "STRING", "CHAR", "STRING", "INT",
            "INT", "LOCALMKTDATE", "STRING", "CHAR", "INT", "STRING", "QTY", "CHAR", "INT", "INT", "QTY", "INT", "STRING", "INT", "INT",
            "DATA", "LENGTH", "DATA", "STRING", "LENGTH", "CHAR", "LENGTH", "DATA", "BOOLEAN", "INT", "PRICE", "EXCHANGE", "INT", "INT",
            "CHAR", "STRING", "STRING", "STRING", "INT", "STRING", "QTY", "QTY", "STRING", "BOOLEAN", "BOOLEAN", "STRING", "STRING",
            "STRING", "AMT", "AMT", "CURRENCY", "BOOLEAN", "UTCTIMESTAMP", "BOOLEAN", "INT", "CHAR", "UTCTIMESTAMP", "CHAR", "STRING",
            "STRING", "BOOLEAN", "STRING", "PRICE", "PRICE", "QTY", "QTY", "INT", "AMT", "CURRENCY", "CHAR", "PRICE", "BOOLEAN", "STRING",
            "STRING", "STRING", "STRING", "INT", "STRING", "STRING", "STRING", "CHAR", "QTY", "QTY", "PRICE", "AMT", "FLOAT", "CHAR", "INT",
            "FLOAT", "AMT", "CHAR", "STRING", "STRING", "CHAR", "STRING", "CHAR", "STRING", "STRING", "UTCTIMESTAMP", "INT", "STRING", "STRING",
            "INT", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING",
            "STRING", "STRING", "STRING", "PRICE", "PRICEOFFSET", "PRICE", "PRICEOFFSET", "QTY", "LOCALMKTDATE", "PRICE", "PRICEOFFSET",
            "STRING", "INT", "STRING", "INT", "MONTHYEAR", "INT", "PRICE", "INT", "INT", "DAYOFMONTH", "CHAR", "EXCHANGE", "BOOLEAN",
            "INT", "QTY", "PRICEOFFSET", "LENGTH", "DATA", "STRING", "INT", "INT", "STRING", "PRICEOFFSET", "CHAR", "FLOAT", "FLOAT",
            "STRING", "CHAR", "INT", "INT", "BOOLEAN", "INT", "INT", "CHAR", "PRICE", "QTY", "UTCDATE", "UTCTIMEONLY", "CHAR", "EXCHANGE",
            "MULTIPLEVALUESTRING", "MULTIPLEVALUESTRING", "STRING", "CHAR", "STRING", "CHAR", "STRING", "STRING", "STRING", "CHAR",
            "CHAR", "INT", "STRING", "STRING", "INT", "CHAR", "CHAR", "QTY", "QTY", "INT", "INT", "INT", "INT", "STRING", "INT", "INT",
            "STRING", "INT", "INT", "STRING", "STRING", "STRING", "EXCHANGE", "STRING", "STRING", "STRING", "STRING", "MONTHYEAR", "DAYOFMONTH",
            "INT", "PRICE", "CHAR", "CURRENCY", "QUANTITY", "STRING", "INT", "STRING", "INT", "STRING", "BOOLEAN", "INT", "CHAR", "BOOLEAN",
            "BOOLEAN", "QTY", "QTY", "PRICE", "PRICE", "INT", "STRING", "STRING", "STRING", "INT", "INT", "INT", "UTCTIMESTAMP", "UTCTIMESTAMP",
            "UTCTIMESTAMP", "UTCTIMESTAMP", "UTCTIMESTAMP", "INT", "STRING", "LENGTH", "DATA", "LENGTH", "DATA", "LENGTH", "DATA",
            "LENGTH", "DATA", "LENGTH", "DATA", "LENGTH", "DATA", "LENGTH", "DATA", "LENGTH", "DATA", "LENGTH", "DATA", "PRICE", "UTCTIMESTAMP",
            "INT", "INT", "UTCTIMESTAMP", "INT", "STRING", "INT", "CHAR", "STRING", "STRING", "BOOLEAN", "INT", "STRING", "INT", "AMT",
            "INT", "INT", "INT", "CHAR", "INT", "QTY", "CHAR", "PRICEOFFSET", "STRING", "STRING", "STRING", "INT", "INT", "INT", "AMT", "AMT",
            "INT", "INT", "STRING", "INT", "FLOAT", "FLOAT", "AMT", "FLOAT", "AMT", "FLOAT", "AMT", "INT", "FLOAT", "BOOLEAN", "AMT", "FLOAT",
            "INT", "INT", "INT", "INT", "CHAR", "CHAR", "INT", "STRING", "INT", "INT", "QTY", "QTY", "PRICE", "INT", "INT", "INT", "INT", "INT",
            "LOCALMKTDATE", "CHAR", "CHAR", "FLOAT", "FLOAT", "QTY", "UTCTIMESTAMP", "STRING", "STRING", "INT", "CHAR", "UTCTIMESTAMP",
            "STRING", "LENGTH", "DATA"
    };
}
