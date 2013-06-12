package com.android.data.types;

/**
 * Shop Entity, represents a shop.
 * 
 * @author Ignacio Mulas
 * 
 */
public class Shop implements Entity {

	private String address; // TODO: Create class Address
	// TODO: Openning times
	private String email;
	// Meter coordenadas
	// TODO: Change latitude and longitude!!!
	private float latitud;
	private float longitud;
	private String name;
	private String schedule;
	private long shopId;

	/**
	 * Empty constructor used by jackson
	 */
	public Shop() {

	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the latitude
	 */
	public float getLatitud() {
		return latitud;
	}

	/**
	 * @return the longitude
	 */
	public float getLongitud() {
		return longitud;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @return the shopId
	 */
	public long getShopId() {
		return shopId;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param latitude
	 */
	public void setLatitud(float latitude) {
		this.latitud = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitud(float longitude) {
		this.longitud = longitude;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param schedule
	 *            the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * @param shopId
	 *            the shopId to set
	 */
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Shop [" + (address != null ? "address=" + address + ", " : "")
				+ "shopId=" + shopId + ", latitude=" + latitud + ", longitude="
				+ longitud + ", "
				+ (email != null ? "email=" + email + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (schedule != null ? "schedule=" + schedule : "") + "]";
	}

}
