package genpact.bank.user.entity;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class User {

	private int userId;
	private String fullName;
	private char gender;
	private Date dateOfBirth;
	private String email;
	private String phone;
	private String address;
	private int cityId;
	private String pincode;
	private String role;
	private int branchId;	
	private String username;
	private String password;
	private String passSalt;
	private String passHash;
	private boolean approved;
	private int approverId;
	
	private MultipartFile profileImage;
	private MultipartFile idProof;
	private MultipartFile addressProof;	

	private String branchName;
	private String cityName;
	private String stateName;
	
	public User() {
		super();
	}

	public User(String fullName, char gender, Date dateOfBirth, String email, String phone, String address, int cityId,
			String pincode, String role, int branchId, MultipartFile profileImage, MultipartFile idProof,
			MultipartFile addressProof, String username, String password, String passSalt, String passHash,
			boolean approved, int approverId) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cityId = cityId;
		this.pincode = pincode;
		this.role = role;
		this.branchId = branchId;
		this.profileImage = profileImage;
		this.idProof = idProof;
		this.addressProof = addressProof;
		this.username = username;
		this.password = password;
		this.passSalt = passSalt;
		this.passHash = passHash;
		this.approved = approved;
		this.approverId = approverId;
	}

	public User(int userId, String fullName, char gender, Date dateOfBirth, String email, String phone, String address,
			int cityId, String pincode, String role, int branchId, MultipartFile profileImage, MultipartFile idProof,
			MultipartFile addressProof, String username, String password, String passSalt, String passHash,
			boolean approved, int approverId) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cityId = cityId;
		this.pincode = pincode;
		this.role = role;
		this.branchId = branchId;
		this.profileImage = profileImage;
		this.idProof = idProof;
		this.addressProof = addressProof;
		this.username = username;
		this.password = password;
		this.passSalt = passSalt;
		this.passHash = passHash;
		this.approved = approved;
		this.approverId = approverId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public MultipartFile getIdProof() {
		return idProof;
	}

	public void setIdProof(MultipartFile idProof) {
		this.idProof = idProof;
	}

	public MultipartFile getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(MultipartFile addressProof) {
		this.addressProof = addressProof;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassSalt() {
		return passSalt;
	}

	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", phone=" + phone + ", address=" + address + ", cityId=" + cityId
				+ ", pincode=" + pincode + ", role=" + role + ", branchId=" + branchId + ", profileImage="
				+ profileImage + ", idProof=" + idProof + ", addressProof=" + addressProof + ", username=" + username
				+ ", password=" + password + ", passSalt=" + passSalt + ", passHash=" + passHash + ", approved="
				+ approved + ", approverId=" + approverId + "]";
	}

}
