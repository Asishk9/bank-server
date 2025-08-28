package genpact.bank.city.entities;

public class City {

	private int cityId;
	private String cityName;
	private int stateId;

	public City() {
		super();
	}

	public City(String cityName, int stateId) {
		super();
		this.cityName = cityName;
		this.stateId = stateId;
	}

	public City(int cityId, String cityName, int stateId) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", stateId=" + stateId + "]";
	}

}
