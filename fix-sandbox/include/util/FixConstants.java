/*
 * Copyright 2013 Peter Lawrey
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

public class FixConstants {
	
	public final static String [] headerFieldName = {
		"BeginString","BodyLength",	"MsgType","SenderCompID", "TargetCompID","OnBehalfOfCompID","DeliverToCompID","SecureDataLen",
		"SecureData","MsgSeqNum","SenderSubID","SenderLocationID","TargetSubID","TargetLocationID","OnBehalfOfSubID","OnBehalfOfLocationID",
		"DeliverToSubID","DeliverToLocationID","PossDupFlag","PossResend","SendingTime","OrigSendingTime","XmlData","XmlDataLen",
		"MessageEncoding","LastMsgSeqNumProcessed","OnBehalfOfSendingTime"
		};
		
	public final static String [] trailerFieldName = {"SignatureLength","Signature", "CheckSum"};
	
	public final static String [] messagesMsgType = {
    	"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", 
    	"P", "Q","R","S","T","V","W","X","Y","Z","a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"
	};
	public final static String [] messsagesMsgName={
		"Heartbeat","TestRequest","ResendRequest","Reject","SequenceReset","Logout","IndicationofInterest","Advertisement","ExecutionReport",
		"OrderCancelReject","Logon","News","Email","NewOrderSingle","NewOrderList","OrderCancelRequest","OrderCancelReplaceRequest","OrderStatusRequest",
		"Allocation","ListCancelRequest","ListExecute","ListStatusRequest","ListStatus","AllocationACK","DontKnowTrade","QuoteRequest","Quote",
		"SettlementInstructions","MarketDataRequest","MarketDataSnapshotFullRefresh","MarketDataIncrementalRefresh","MarketDataRequestReject",
		"QuoteCancel","QuoteStatusRequest","QuoteAcknowledgement","SecurityDefinitionRequest","SecurityDefinition","SecurityStatusRequest",
		"SecurityStatus","TradingSessionStatusRequest","TradingSessionStatus","MassQuote","BusinessMessageReject","BidRequest","BidResponse",
		"ListStrikePrice"
		};
	public final static String [] messagesMsgCat = {"admin", "app"};
	public final static String MESSAGES_TEST_REQID_FN="TestReqID";
	public final static String [] messagesResendReq = {"BeginSeqNo", "EndSeqNo"};
	public final static String [] messagesReject = {"RefSeqNum", "RefTagID","RefMsgType","SessionRejectReason","Text","EncodedTextLen","EncodedText"};
	public final static String [] messagesSeqReset = {"GapFillFlag", "NewSeqNo"};
	public final static String [] messagesLogout = {"Text", "EncodedTextLen","EncodedText"};
	public final static String [] messagesIOI = {
		"IOIid", "IOITransType","IOIRefID","Symbol","SymbolSfx","SecurityID","IDSource","SecurityType","MaturityMonthYear","MaturityDay",
		"PutOrCall","StrikePrice","OptAttribute","ContractMultiplier","CouponRate","SecurityExchange","Issuer","EncodedIssuerLen","EncodedIssuer",
		"SecurityDesc","EncodedSecurityDescLen","EncodedSecurityDesc","Side","IOIShares","Price","Currency","ValidUntilTime","IOIQltyInd",
		"IOINaturalFlag","Text","EncodedTextLen","EncodedText","TransactTime","URLLink","SpreadToBenchmark","Benchmark"												
	};
	public final static String [] messagesIOIGroup = {"NoIOIQualifiers","NoRoutingIDs"};
	public final static String [] messagesIOIGroupFields = {"IOIQualifier","RoutingType","RoutingID"};
	public final static String [] messagesAdv = {
		"AdvId", "AdvTransType","AdvRefID","Symbol","SymbolSfx","SecurityID","IDSource","SecurityType","MaturityMonthYear","MaturityDay",
		"PutOrCall","StrikePrice","OptAttribute","ContractMultiplier","CouponRate","SecurityExchange","Issuer","EncodedIssuerLen","EncodedIssuer",
		"SecurityDesc","EncodedSecurityDescLen","EncodedSecurityDesc","AdvSide","Shares","Price","Currency","TradeDate","TransactTime",
		"Text","EncodedTextLen","EncodedText","LastMkt","URLLink","TradingSessionID"											
	};
	public final static String [] messageExecReport={
		"OrderID","SecondaryOrderID","ClOrdID","OrigClOrdID","ClientID","ExecBroker","ListID","ExecID","ExecTransType","ExecRefID",
		"ExecType","OrdStatus",	"OrdRejReason",	"ExecRestatementReason","Account","SettlmntTyp","FutSettDate","Symbol","SymbolSfx",
		"SecurityID","IDSource","SecurityType", "MaturityMonthYear","MaturityDay","PutOrCall","StrikePrice","OptAttribute",
		"ContractMultiplier","CouponRate","SecurityExchange","Issuer","EncodedIssuerLen","EncodedIssuer","SecurityDesc","EncodedSecurityDescLen",
		"EncodedSecurityDesc","Side","OrderQty","CashOrderQty","OrdType","Price","StopPx","PegDifference","DiscretionInst","DiscretionOffset",
		"Currency","ComplianceID","SolicitedFlag","TimeInForce","EffectiveTime","ExpireDate","ExpireTime","ExecInst","Rule80A","LastShares",
		"LastPx","LastSpotRate","LastForwardPoints","LastMkt","TradingSessionID","LastCapacity","LeavesQty","CumQty","AvgPx","DayOrderQty",
		"DayCumQty","DayAvgPx","GTBookingInst","TradeDate","TransactTime","ReportToExch","Commission","CommType","GrossTradeAmt","SettlCurrAmt",
		"SettlCurrency","SettlCurrFxRate","SettlCurrFxRateCalc","HandlInst","MinQty","MaxFloor","OpenClose","MaxShow","Text","EncodedTextLen",
		"EncodedText","FutSettDate2","OrderQty2","ClearingFirm","ClearingAccount","MultiLegReportingType"
		};
	public final static String messageExecReportGrp= "NoContraBrokers";
	public final static String [] messageExecReportGrpFlds={"ContraBroker","ContraTrader","ContraTradeQty","ContraTradeTime"};
	public final static String [] messageOrderCancelRej={
		"OrderID","SecondaryOrderID" ,"ClOrdID","OrigClOrdID","OrdStatus","ClientID" ,"ExecBroker" ,"ListID" ,"Account" ,"TransactTime" ,
		"CxlRejResponseTo","CxlRejReason" ,"Text" ,"EncodedTextLen" ,"EncodedText" ,};
	public final static String [] messageLogon={
		"EncryptMethod","HeartBtInt","RawDataLength" ,"RawData" ,"ResetSeqNumFlag" ,"MaxMessageSize"
		};
	public final static String [] messageLogonGrpFlds={"RefMsgType","MsgDirection"};
	public final static String messageLogonGrp="NoMsgTypes";
	public final static String [] messageNews={
		"OrigTime" ,"Urgency" ,	"Headline",	"EncodedHeadlineLen" ,	"EncodedHeadline" ,	"URLLink" ,	"RawDataLength" ,"RawData"
	};
	public final static String [] messageNewsGrp={"NoRoutingIDs","NoRelatedSym","LinesOfText"};
	public final static String [] messageNewsGrpFlds={
		"RoutingType" ,"RoutingID" ,"RelatdSym" ,"SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,
		"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,
		"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,	"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,
		"Text",	"EncodedTextLen" ,"EncodedText" };
	public final static String [] messagesEmail={	
		"EmailThreadID","EmailType","OrigTime" ,"Subject","EncodedSubjectLen" ,"EncodedSubject" ,"OrderID","ClOrdID","RawDataLength","RawData" 
	};
	public final static String [] messagesEmaiGrpFlds=messageNewsGrpFlds;
	public final static String [] messagesEmaiGrp=messageNewsGrp;
	public final static String [] messagesNOS={
		"ClOrdID","ClientID" ,"ExecBroker" ,"Account" ,"SettlmntTyp" ,"FutSettDate" ,"HandlInst","ExecInst" ,"MinQty" ,"MaxFloor" ,"ExDestination" ,
		"ProcessCode" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,
		"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,
		"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"PrevClosePx" ,"Side","LocateReqd" ,"TransactTime","OrderQty" ,
		"CashOrderQty" ,"OrdType","Price" ,"StopPx" ,"Currency" ,"ComplianceID" ,"SolicitedFlag" ,"IOIid" ,"QuoteID" ,"TimeInForce" ,
		"EffectiveTime" ,"ExpireDate" ,"ExpireTime" ,"GTBookingInst" ,"Commission" ,"CommType" ,"Rule80A" ,"ForexReq" ,"SettlCurrency" ,
		"Text" ,"EncodedTextLen" ,"EncodedText" ,"FutSettDate2" ,"OrderQty2" ,"OpenClose" ,"CoveredOrUncovered" ,"CustomerOrFirm" ,
		"MaxShow" ,"PegDifference" ,"DiscretionInst" ,"DiscretionOffset" ,"ClearingFirm" ,"ClearingAccount"
	};	
	public final static String[] messagesNOSGrpFlds={"AllocAccount" ,"AllocShares" ,"TradingSessionID"};
	public final static String[] messagesNOSGrp={"NoAllocs" ,"NoTradingSessions"};
	public final static String [] messagesNOL={
		"ListID","BidID" ,"ClientBidID" ,"ProgRptReqs" ,"BidType","ProgPeriodInterval" ,"ListExecInstType" ,"ListExecInst" ,"EncodedListExecInstLen" ,
		"EncodedListExecInst" ,"TotNoOrders",};
	public final static String []messagesNOLOuterGrpFlds={
		"ClOrdID","ListSeqNo","SettlInstMode" ,"ClientID" ,"ExecBroker" ,"Account" ,"SettlmntTyp" ,"FutSettDate" ,"HandlInst" ,"ExecInst" ,
		"MinQty" ,"MaxFloor" ,"ExDestination" ,"ProcessCode" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,
		"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,
		"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,	"EncodedSecurityDesc" ,"PrevClosePx" ,"Side","SideValueInd" ,"LocateReqd" ,
		"TransactTime" ,"OrderQty" ,"CashOrderQty" ,"OrdType" ,"Price" ,"StopPx" ,"Currency" ,"ComplianceID" ,"SolicitedFlag" ,"IOIid" ,
		"QuoteID" ,"TimeInForce" ,"EffectiveTime" ,"ExpireDate" ,"ExpireTime" ,"GTBookingInst" ,"Commission" ,"CommType" ,"Rule80A" ,"ForexReq" ,
		"SettlCurrency" ,"Text" ,"EncodedTextLen" ,"EncodedText" ,"FutSettDate2" ,"OrderQty2" ,"OpenClose" ,"CoveredOrUncovered" ,"CustomerOrFirm" ,
		"MaxShow" ,"PegDifference" ,"DiscretionInst" ,"DiscretionOffset" ,"ClearingFirm" ,"ClearingAccount"
	};
	public final static String messagesNOLOuterGrp="NoOrders";
	public final static String []messagesNOLInnerGrp={"NoAllocs","NoTradingSessions"};
	public final static String []messagesNOLInnerGrpFlds={"AllocAccount","AllocShares","TradingSessionID"};
	public final static String []messagesOCR={
		"OrigClOrdID","OrderID" ,"ClOrdID","ListID" ,"Account" ,"ClientID" ,"ExecBroker" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,
		"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,
		"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,
		"Side","TransactTime","OrderQty" ,"CashOrderQty" ,"ComplianceID" ,"SolicitedFlag" ,"Text" ,"EncodedTextLen" ,"EncodedText"
	};
	public final static String []messagesOCRGrp=messagesNOLInnerGrp;
	public final static String []messagesOCRGrpFlds=messagesNOLInnerGrpFlds;
	public final static String []messagesOSR={
		"OrderID" ,"ClOrdID","ClientID" ,"Account" ,"ExecBroker" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,
		"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,
		"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"Side"};
	public final static String [] messagesAlloc={
		"AllocID","AllocTransType","RefAllocID" ,"AllocLinkID" ,"AllocLinkType" ,"Side","Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,
		"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,
		"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,	"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,
		"Shares","LastMkt" ,"TradingSessionID" ,"AvgPx","Currency" ,"AvgPrxPrecision" ,"TradeDate","TransactTime" ,"SettlmntTyp" ,"FutSettDate" ,
		"GrossTradeAmt" ,"NetMoney" ,"OpenClose" ,"Text" ,"EncodedTextLen" ,"EncodedText" ,"NumDaysInterest" ,"AccruedInterestRate" ,
	};
	public final static String[] messagesAllocOGrp={"NoOrders","NoExecs","NoAllocs"};
	public final static String messagesAllocIGrp="NoMiscFees";
	public final static String[] messagesAllocIGrpFlds={"MiscFeeAmt","MiscFeeCurr","MiscFeeType"};
	public final static String[] messagesAllocOGrpFlds={"ClOrdID" ,
		"OrderID" ,"SecondaryOrderID" ,"ListID" ,"WaveNo" ,"LastShares" ,"ExecID" ,"LastPx" ,"LastCapacity" ,"AllocAccount" ,"AllocPrice" ,
		"AllocShares","ProcessCode" ,"BrokerOfCredit" ,"NotifyBrokerOfCredit" ,"AllocHandlInst" ,"AllocText" ,"EncodedAllocTextLen" ,
		"EncodedAllocText" ,"ExecBroker" ,"ClientID" ,"Commission" ,"CommType" ,"AllocAvgPx" ,"AllocNetMoney" ,"SettlCurrAmt" ,"SettlCurrency" ,
		"SettlCurrFxRate" ,"SettlCurrFxRateCalc" ,"AccruedInterestAmt" ,"SettlInstMode" ,};
	public final static String[] messagesListCancelReq={"ListID","TransactTime","Text","EncodedTextLen","EncodedText"};
	public final static String[] messagesListExec={
		"ListID","ClientBidID" ,"BidID" ,"TransactTime","Text" ,"EncodedTextLen" ,"EncodedText" 
	};
	public final static String[] messagesListStatusReq={
		"ListID","Text" ,"EncodedTextLen" ,"EncodedText" 
	};
	public final static String[] messagesListStatus={		
		"ListID","ListStatusType","NoRpts","ListOrderStatus","RptSeq","ListStatusText" ,"EncodedListStatusTextLen" ,"EncodedListStatusText" ,
		"TransactTime" ,"TotNoOrders"
	};
	public final static String[] messagesListStatusGrpFld={		
		"ClOrdID","CumQty","OrdStatus","LeavesQty","CxlQty","AvgPx","OrdRejReason" ,"Text" ,"EncodedTextLen" ,"EncodedText"
	};
	public final static String[] messagesListStatusGrp={"NoOrders"};
	public final static String[] messagesAllocationAck={
		"ClientID" ,"ExecBroker" ,"AllocID","TradeDate","TransactTime" ,"AllocStatus","AllocRejCode" ,"Text" ,"EncodedTextLen" ,"EncodedText" 
	};
	public final static String[] messagesDNT={
		"OrderID","ExecID",	"DKReason","Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,
		"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,
		"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"Side","OrderQty" ,"CashOrderQty" ,"LastShares" ,
		"LastPx" ,"Text" ,"EncodedTextLen" ,"EncodedText" ,
	};
	public final static String[] messagesQuoteReq={"QuoteReqID"};
	public final static String[] messagesQuoteReqGrp={"NoRelatedSym"};
	public final static String[] messagesQuoteReqGrpFld={
		"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,
		"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,
		"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"PrevClosePx" ,"QuoteRequestType" ,"TradingSessionID" ,"Side" ,"OrderQty" ,"FutSettDate" ,
		"OrdType" ,"FutSettDate2" ,"OrderQty2" ,"ExpireTime" ,"TransactTime" ,"Currency" 
	};
	
	public final static String[] messagesQuote={
		"QuoteReqID" ,"QuoteID","QuoteResponseLevel" ,"TradingSessionID" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,
		"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,
		"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"BidPx" ,"OfferPx" ,
		"BidSize" ,"OfferSize" ,"ValidUntilTime" ,"BidSpotRate" ,"OfferSpotRate" ,"BidForwardPoints" ,"OfferForwardPoints" ,"TransactTime" ,
		"FutSettDate" ,"OrdType" ,"FutSettDate2" ,"OrderQty2" ,"Currency" 
	};
	
	public final static String[] messageSettlementInst={
		"SettlInstID","SettlInstTransType","SettlInstRefID","SettlInstMode","SettlInstSource","AllocAccount","SettlLocation" ,
		"TradeDate" ,"AllocID" ,"LastMkt" ,"TradingSessionID" ,"Side" ,"SecurityType" ,"EffectiveTime" ,"TransactTime",
		"ClientID" ,"ExecBroker" ,"StandInstDbType" ,"StandInstDbName" ,"StandInstDbID" ,"SettlDeliveryType" ,"SettlDepositoryCode" ,
		"SettlBrkrCode" ,"SettlInstCode" ,"SecuritySettlAgentName" ,"SecuritySettlAgentCode" ,"SecuritySettlAgentAcctNum" ,
		"SecuritySettlAgentAcctName" ,"SecuritySettlAgentContactName" ,"SecuritySettlAgentContactPhone" ,"CashSettlAgentName" ,
		"CashSettlAgentCode" ,"CashSettlAgentAcctNum" ,"CashSettlAgentAcctName" ,"CashSettlAgentContactName" ,"CashSettlAgentContactPhone" ,
	};
	public final static String[] messagesMarketDataReq={
		"MDReqID","SubscriptionRequestType","MarketDepth","MDUpdateType" ,"AggregatedBook" 
	};
	public final static String[] messagesMarketDataReqGrpFld={
		"MDEntryType","Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,
		"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,
		"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"TradingSessionID" , 
	};
	public final static String[] messagesMarketDataReqGrp={
		"NoMDEntryTypes","NoRelatedSym"
	};
	public final static String[] messagesMarketDataSSFReq={
		"MDReqID" ,"Symbol","SymbolSfx" ,"SecurityID" ,"IDSource" ,"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,
		"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,
		"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,"FinancialStatus" ,"CorporateAction" ,"TotalVolumeTraded" 
	};
	public final static String[] messagesMarketDataSSFReqGrpFld={
		"MDEntryType","MDEntryPx","Currency" ,"MDEntrySize" ,"MDEntryDate" ,"MDEntryTime" ,"TickDirection" ,"MDMkt" ,"TradingSessionID" ,
		"QuoteCondition" ,"TradeCondition" ,"MDEntryOriginator" ,"LocationID" ,"DeskID" ,"OpenCloseSettleFlag" ,"TimeInForce" ,"ExpireDate" ,
		"ExpireTime" ,"MinQty" ,"ExecInst" ,"SellerDays" ,"OrderID" ,"QuoteEntryID" ,"MDEntryBuyer" ,"MDEntrySeller" ,"NumberOfOrders" ,
		"MDEntryPositionNo" ,"Text" ,"EncodedTextLen" ,"EncodedText" 
	};
	public final static String[] messagesMarketDataSSFReqGrp={
		"NoMDEntries"
	};
	public final static String[] messagesMarketDataINCRReq={
		"MDReqID"
	};
	public final static String[] messagesMarketDataINCRReqGrpFld={
		"MDUpdateAction","DeleteReason" ,"MDEntryType" ,"MDEntryID" ,"MDEntryRefID" ,"Symbol" ,"SymbolSfx" ,"SecurityID" ,"IDSource" ,
		"SecurityType" ,"MaturityMonthYear" ,"MaturityDay" ,"PutOrCall" ,"StrikePrice" ,"OptAttribute" ,"ContractMultiplier" ,"CouponRate" ,
		"SecurityExchange" ,"Issuer" ,"EncodedIssuerLen" ,"EncodedIssuer" ,"SecurityDesc" ,"EncodedSecurityDescLen" ,"EncodedSecurityDesc" ,
		"FinancialStatus" ,"CorporateAction" ,"MDEntryPx" ,"Currency" ,"MDEntrySize" ,"MDEntryDate" ,"MDEntryTime" ,"TickDirection" ,
		"MDMkt" ,"TradingSessionID" ,"QuoteCondition" ,"TradeCondition" ,"MDEntryOriginator" ,"LocationID" ,"DeskID" ,"OpenCloseSettleFlag" ,
		"TimeInForce" ,"ExpireDate" ,"ExpireTime" ,"MinQty" ,"ExecInst" ,"SellerDays" ,"OrderID" ,"QuoteEntryID" ,"MDEntryBuyer" ,"MDEntrySeller" ,
		"NumberOfOrders" ,"MDEntryPositionNo" ,"TotalVolumeTraded" ,"Text" ,"EncodedTextLen" ,"EncodedText" 
	};
	public final static String[] messagesMarketDataINCRReqGrp={
		"NoMDEntries"
	};
	public static void main (String ... args){
		
		System.out.println(messageNewsGrpFlds.length);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
