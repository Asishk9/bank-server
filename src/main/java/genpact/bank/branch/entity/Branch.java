package genpact.bank.branch.entity;

public class Branch {

	private int branchId;
	private String branchName;
	private String branchCode;
	private String address;
	private int cityId;
	private String pincode;
	private String phone;
	private int bmId;
	private int rmId;
	
	private String cityName;
	private String rmName;
	private String bmName;

	
	
	public Branch() {
		super();
	}

	public Branch(String branchName, String branchCode, String address, int cityId, String pincode, String phone,
			int bmId, int rmId) {
		super();
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.address = address;
		this.cityId = cityId;
		this.pincode = pincode;
		this.phone = phone;
		this.bmId = bmId;
		this.rmId = rmId;
	}

	public Branch(int branchId, String branchName, String branchCode, String address, int cityId, String pincode,
			String phone, int bmId, int rmId) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.address = address;
		this.cityId = cityId;
		this.pincode = pincode;
		this.phone = phone;
		this.bmId = bmId;
		this.rmId = rmId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBmId() {
		return bmId;
	}

	public void setBmId(int bmId) {
		this.bmId = bmId;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public String getBmName() {
		return bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	
	@Override
	public String toString() {
		return "\n Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchCode=" + branchCode
				+ ", address=" + address + ", cityId=" + cityId + ", pincode=" + pincode + ", phone=" + phone
				+ ", bmId=" + bmId + ", rmId=" + rmId + "]";
	}

}
