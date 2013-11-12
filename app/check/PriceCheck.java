package check;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class PriceCheck {
	private String fromAirport;
	private String toAirport;
	private String fromDateTime;
	private String toDateTime;
	
	
	public PriceCheck(String fromAirport, String toAirport, String fromDateTime, String toDateTime) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
	}
	
	
	public String executePostRequest(String url, Map<String, String> params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		List<NameValuePair> paramsList = new ArrayList<NameValuePair>(7);

		for (String key : params.keySet())
			paramsList.add(new BasicNameValuePair(key, params.get(key)));

		StringBuilder content = new StringBuilder();

		try {
			httppost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));

				String line = "";
				while ((line = reader.readLine()) != null)
					content.append(line + "\n");

				reader.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	
	public String initialSearch() {
		String initialSearchURL = "http://book.lufthansa.com/pl/Lufthansa/wds/Override.action";

		Map<String, String> params = new HashMap<String, String>();
		params.put("WDS_WR_SCENARIO", "scenario6");
		params.put("WDS_WR_MARKET", "DE");
		params.put("WDS_WR_LANG", "de");
		params.put("WDS_WR_DCSID", "dcsi8dl8n100000kbqfxzervo_7i6g");
		params.put("WDS_WR_DCSEXT_SCENARIO", "6");
		params.put("WDS_WR_CHANNEL", "LHCOM");
		params.put("WDS_WR_BFT", "K");
		params.put("WDS_WR_AREA", "FRA");
		params.put("WDS_USER_NL_PARAM", "SHOW");
		params.put("WDS_ONHOLD_EXEMPT", "UA:TG:LX:OS:SK:EY:TK:A3:AC:AI:BD:CA:EN:ET:FM:JJ:JK:JP:KF:KM:LG:LO:MS:NH:NZ:OU:QR:SA:SK:SN:SQ:TA:US:VO:XQ:XG:QF:PR:AV:PU:CX:CA:KA:MU:CI:CM:OZ:AS:CO:JP:AM:4U:DE:VF:9W:BL:3K:JQ:MI:AR:H2:PZ:R7:S3:S2:4X:4Z:ZH:PG:BA:MH:BR:GU:UT:UG:AT:JZ:TP:QI:JU:YM:GF:QR");
		params.put("WDS_OFFER_RAILFLY", "TRUE");
		params.put("WDS_FOP_SEQUENCE", "SE");
		params.put("WDS_DISPLAY_MM_CC_INFO", "TRUE");
		params.put("WDS_DISPLAY_BANNERS", "TRUE");
		params.put("WDS_APPLY_CCS_FOR_PAYPAL", "TRUE");
		params.put("WDS_ALLOWED_CC_TYPES", "VI:CA:DC:AX:JC:TP");
		params.put("WDS_ALLOW_TPB", "TRUE");
		params.put("URL_FLOWCONTROL", "/online/portal/lh/de/booking?l=de&cid=18002&action=CallbackHandler&command=callback&view=flight");
		params.put("TRIP_TYPE", "R");
		params.put("TRAVELLER_TYPE_2", "ADT");
		params.put("TRAVELLER_TYPE_1", "ADT");
		params.put("SO_SITE_USE_TKT_SERVICE_FEE", "FALSE");
		params.put("SO_SITE_USE_PENDING_TRIPS", "TRUE");
		params.put("SO_SITE_USE_OH_FULLF_BUFFER", "TRUE");
		params.put("SO_SITE_USE_MIN_DUR_BD_DD", "FALSE");
		params.put("SO_SITE_USE_CREDIT_CARD_FEE", "FALSE");
		params.put("SO_SITE_TOD_ALLOW", "FALSE");
		params.put("SO_SITE_TBM_ALLOW", "FALSE");
		params.put("SO_SITE_STATUS_RK_TAG", "STATUS:ON HOLD/FEE");
		params.put("SO_SITE_QUEUE_SUCCESS_ETKT", "FALSE");
		params.put("SO_SITE_PREFERRED_CARRIER", "000000");
		params.put("SO_SITE_PENDING_TL_IS_LTD", "N");
		params.put("SO_SITE_PENDING_TKT_TYPE", "AUTO_QUEUE");
		params.put("SO_SITE_PENDING_TIME_LIMIT", "D02");
		params.put("SO_SITE_PAY_LATER_GUARANTEE", "TRUE");
		params.put("SO_SITE_ONLINE_INDICATOR", "FALSE");
		params.put("SO_SITE_OH_USE_BKDATE_BUF", "TRUE");
		params.put("SO_SITE_OH_USE_AIRLTD_BUF", "FALSE");
		params.put("SO_SITE_OH_STATUS_RK_TAG", "<OH_SF_AMOUNT><OH_SF_CURRENCY>/");
		params.put("SO_SITE_OH_FULLF_BUFFER", "D01");
		params.put("SO_SITE_OFFICE_ID", "FRALH010Z");
		params.put("SO_SITE_OB_FEES_ENABLED", "TRUE");
		params.put("SO_SITE_MOP_PPAL", "TRUE");
		params.put("SO_SITE_MOP_EXT_WITH_PRECALL", "TRUE");
		params.put("SO_SITE_MOP_DD", "TRUE");
		params.put("SO_SITE_MANUAL_ETKT_CMD", "TTP");
		params.put("SO_SITE_LH_OSI_GLOBAL_PREFIX", "IFW");
		params.put("SO_SITE_LH_FRONTEND_URL", "www.lufthansa.com");
		params.put("SO_SITE_FP_WITHHOLD_TAXES", "FALSE");
		params.put("SO_SITE_FP_AGT_NUMBER", "25000001");
		params.put("SO_SITE_FAKE_CANCEL", "TRUE");
		params.put("SO_SITE_ETKT_Q_OFFICE_ID", "QLHLH0200");
		params.put("SO_SITE_ETKT_Q_AND_CAT", "60C88");
		params.put("SO_SITE_ETIX_QUEUE_OFFICE_ID", "QLHLH0200");
		params.put("SO_SITE_ETIX_QUEUE_ID", "60");
		params.put("SO_SITE_ETIX_QUEUE_CATEGORY", "88");
		params.put("SO_SITE_ETIX_PLUS_TBM_ALLOW", "FALSE");
		params.put("SO_SITE_ETIX_MIN_TIME", "1");
		params.put("SO_SITE_ETIX_ALLOW", "TRUE");
		params.put("SO_SITE_DISP_OBFEES_DETAIL", "TRUE");
		params.put("SO_SITE_DISABLE_PREBOOKING", "FALSE");
		params.put("SO_SITE_DELIVERY_AS_BILLING", "FALSE");
		params.put("SO_SITE_CTRY_OF_COMMENCEMENT", "DE");
		params.put("SO_SITE_COUNTRY_OF_RESIDENCE", "DE");
		params.put("SO_SITE_CHK_BILLING_ADDRESS", "TRUE");
		params.put("SO_SITE_BOOL_RK_ETKT_FAIL", "TRUE");
		params.put("SO_SITE_BOOL_ISSUE_ETKT", "TRUE");
		params.put("SO_SITE_ALLOW_TSC_ON_FARE", "TRUE");
		params.put("SO_SITE_ALLOW_THRD_BK_PLESS", "TRUE");
		params.put("SO_SITE_ALLOW_PNR_REPRICE", "FALSE");
		params.put("SO_SITE_ALLOW_ON_HOLD_CANCEL", "TRUE");
		params.put("SO_SITE_ALLOW_CITP_COMMANDS", "TRUE");
		params.put("SO_SITE_ALLOW_CCS_ON_FARE", "FALSE");
		params.put("SO_SITE_ADDR_DELIVERY_FMT", "ADDR: %M,%Y,%A,%B%P,%Z %C,%N");
		params.put("SO_SITE_AA_MIN_DUR_ON_HOLD", "D02");
		// params.put("SO_GL", "<?xml version="1.0" encoding="iso-8859-1"?><SO_GL><GLOBAL_LIST mode="global"><NAME>SITE_INSURANCE_PRODUCTS</NAME><LIST_ELEMENT><CODE>EDE</CODE><LIST_VALUE>K001</LIST_VALUE><LIST_VALUE>FAKE</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>Y</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>1</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>EDE</CODE><LIST_VALUE>K007</LIST_VALUE><LIST_VALUE>FAKE</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>Y</LIST_VALUE><LIST_VALUE>N</LIST_VALUE><LIST_VALUE>2</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST><GLOBAL_LIST mode="global"><NAME>SITE_LIST_SUPPORTED_OB_FEE</NAME><LIST_ELEMENT><CODE>FCA</CODE><LIST_VALUE>FCA</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>R10</CODE><LIST_VALUE>R10</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>R11</CODE><LIST_VALUE>R11</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST><GLOBAL_LIST><NAME>SITE_SERVICE_FEE</NAME><LIST_ELEMENT><CODE>0</CODE><LIST_VALUE>1</LIST_VALUE><LIST_VALUE>1</LIST_VALUE><LIST_VALUE>30</LIST_VALUE><LIST_VALUE>EUR</LIST_VALUE><LIST_VALUE> </LIST_VALUE><LIST_VALUE> </LIST_VALUE><LIST_VALUE>3</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST><GLOBAL_LIST><NAME>SITE_QUEUE_DEFINITION_LIST</NAME><LIST_ELEMENT><CODE>AAAAAAA</CODE><LIST_VALUE>CAN</LIST_VALUE><LIST_VALUE>FRALH010Z</LIST_VALUE><LIST_VALUE>8</LIST_VALUE><LIST_VALUE>1</LIST_VALUE><LIST_VALUE></LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST><GLOBAL_LIST mode="partial"><NAME>SITE_LIST_OB_FEE_MOP_APPLICATION_RULE</NAME><LIST_ELEMENT><CODE>PPAL</CODE><LIST_VALUE>FCA</LIST_VALUE><LIST_VALUE>Y</LIST_VALUE><LIST_VALUE>N</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST></SO_GL>");
		params.put("SKIN", "LH");
		params.put("SITE", "5AHB5AHB");
		params.put("SEVEN_DAY_SEARCH", "TRUE");
		params.put("SECURE", "FALSE");
		params.put("POS", "de");
		params.put("PORTAL_SESSION", "-RB_Jla9zwxVeVJhm0vkcjr");
		params.put("LANGUAGE", "DE");
		params.put("INCLUDE_TAXES", "TRUE");
		params.put("HAS_INFANT_2", "FALSE");
		params.put("HAS_INFANT_1", "FALSE");
		params.put("EMBEDDED_TRANSACTION", "AirAvailability");
		params.put("E_LOCATION_1", toAirport);
		params.put("DIRECT_NON_STOP", "FALSE");
		params.put("COOKIE_USER", "TRUE");
		params.put("CABIN_PRIORITY_3", "FBER");
		params.put("CABIN_PRIORITY_2", "BFER");
		params.put("CABIN_PRIORITY_1", "ERB");
		params.put("CABIN", "E");
		params.put("BANNER", "FALSE");
		params.put("B_LOCATION_1", fromAirport);
		params.put("B_DATE_2", toDateTime);
		params.put("B_DATE_1", fromDateTime);
		params.put("ARRANGE_BY", "ND");
		params.put("ALLOW_PROMO", "Y");

		String content = executePostRequest(initialSearchURL, params);

		String searchString = "<form name=\"AVAI_FORM\" action=\"";
		int start_id = content.indexOf(searchString);

		if (start_id == -1)
			return "";

		start_id += searchString.length();
		int end_id = content.indexOf("\"", start_id + 1);

		return "http://book.lufthansa.com:80/pl/Lufthansa/wds/"	+ content.substring(start_id, end_id);
	}

	
	public String searchPrice(String url) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("WDS_FROM_PAGE", "AVAI");
		params.put("TRIP_TYPE", "R");
		params.put("STEPS", "2");
		params.put("SKIN", "LH");
		params.put("SITE", "5AHB5AHB");
		params.put("ROW_2", "0");
		params.put("ROW_1", "0");
		params.put("RESTRICTION", "TRUE");
		params.put("RBD_2_9", "Y");
		params.put("RBD_2_9", "Y");
		params.put("RBD_2_8", "Y");
		params.put("RBD_2_8", "Y");
		params.put("RBD_2_7", "Y");
		params.put("RBD_2_7", "Y");
		params.put("RBD_2_6", "Y");
		params.put("RBD_2_6", "Y");
		params.put("RBD_2_5", "Y");
		params.put("RBD_2_5", "Y");
		params.put("RBD_2_4", "Y");
		params.put("RBD_2_4", "Y");
		params.put("RBD_2_3", "Y");
		params.put("RBD_2_3", "Y");
		params.put("RBD_2_2", "Y");
		params.put("RBD_2_14", "Y");
		params.put("RBD_2_14", "Y");
		params.put("RBD_2_13", "Y");
		params.put("RBD_2_13", "Y");
		params.put("RBD_2_12", "Y");
		params.put("RBD_2_12", "Y");
		params.put("RBD_2_11", "Y");
		params.put("RBD_2_11", "Y");
		params.put("RBD_2_10", "Y");
		params.put("RBD_2_10", "Y");
		params.put("RBD_2_1", "Y");
		params.put("RBD_2_0", "Y");
		params.put("RBD_1_0", "Y");
		params.put("PAGE_TICKET", "0");
		params.put("LANGUAGE", "DE");
		params.put("IS_RSB", "false");
		params.put("IS_CUG", "");
		params.put("BANNER", "FALSE");

		String content = executePostRequest(url, params);

		String searchString = "id=\"totalPriceWithoutAgencyFees\">";
		int start_id = content.indexOf(searchString);

		if (start_id == -1)
			return "";

		start_id += searchString.length();
		int end_id = content.indexOf("<", start_id + 1);

		return content.substring(start_id, end_id);
	}


	public String checkPrice() {
		String url = initialSearch();
		String priceString = searchPrice(url);
		
		return priceString;
	}
	
}
