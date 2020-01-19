package com.abelinho.secutil.secutil.webservice.model;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Base64;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public class SecurityUtil {

	private String clientId;
	
	private String clientSecret;
	
private String client_reference; // = {"POC","100","05QQAWQERQWHYTFDYUSwY"};
	
	private String trans_amount;
	
	private String service_id;
	
	

	public SecurityUtil(String clientId, String clientSecret, String client_reference, String trans_amount,
			String service_id) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.client_reference = client_reference;
		this.trans_amount = trans_amount;
		this.service_id = service_id;
	}

	/*public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	
	

	
	
//  POC+100+05QQAWQERQWHYTFDYUSwY

	public String getClient_reference() {
		return client_reference;
	}

	public void setClient_reference(String client_reference) {
		this.client_reference = client_reference;
	}

	public String getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}*/

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setClient_reference(String client_reference) {
		this.client_reference = client_reference;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String encodeBase64(String val) {
		return Base64.getEncoder().encodeToString(val.getBytes());
	}

	public String decodeBase64(String val) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(val), "ASCII");
	}

	public String hmacSha256(String val, String key) {
		return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmacHex(val);
	}

	public String calculateAuthorizationSignature(String reference, String amount, String service, String id, String secret) {
		StringBuilder sb = new StringBuilder();
		
		String[] fields = {reference,amount,service,id,secret};
		boolean addSeparator = false;
		for (String s : fields) {
			if (addSeparator) {
				sb.append("+");
			}
			sb.append(s);
			addSeparator = true;
			
			//System.out.println("this method was called!");
		}

		String serverSignature = hmacSha256(sb.toString(), secret);
		String clientId = encodeBase64(id);

		Instant instant = Instant.now();
		Long timeStampMillis = instant.getEpochSecond();
		String timeStamp = encodeBase64(String.valueOf(timeStampMillis));

		String cipher = serverSignature + "." + timeStamp + "." + clientId;
		return encodeBase64(cipher);
	}

	/*
	 * fields[0] = "POC"; fields[1] = "100"; fields[2] = "05QQAWQERQWHYTFDYUSwY2";
	 * 
	 * 
	 * 
	 * /*String signatureStr = new SecurityUtil()
	 * .calculateAuthorizationSignature(fields, clientId, clientSecret);
	 * 
	 * System.out.println(signatureStr);
	 */

}
