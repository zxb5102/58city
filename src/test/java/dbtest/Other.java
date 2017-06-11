package dbtest;

import javax.persistence.Embeddable;

@Embeddable
public class Other {

	private String hobby;
	private String city;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Other [hobby=" + hobby + ", city=" + city + "]";
	}

}
