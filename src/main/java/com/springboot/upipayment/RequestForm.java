package com.springboot.upipayment;

public class RequestForm {
	String payeeAddress;
	String payeeName;
	String trxNo;
	String amount;
	String purpose;
	String trxRef;
	String currency;

	public RequestForm() {
		super();
	}

	public RequestForm(String payeeAddress, String payeeName, String trxNo, String amount, String purpose,
			String trxRef, String currency) {
		super();
		this.payeeAddress = payeeAddress;
		this.payeeName = payeeName;
		this.trxNo = trxNo;
		this.amount = amount;
		this.purpose = purpose;
		this.trxRef = trxRef;
		this.currency = currency;
	}

	public String getPayeeAddress() {
		return payeeAddress;
	}

	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTrxRef() {
		return trxRef;
	}

	public void setTrxRef(String trxRef) {
		this.trxRef = trxRef;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
