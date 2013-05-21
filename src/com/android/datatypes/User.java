package com.android.datatypes;

import java.util.List;

/**
 * User Entity, represents a user.
 * 
 * @author Ignacio Mulas - 17-05-2013 - Initial version
 * 
 */
public class User implements Entity {

	/**
	 * @author emulign
	 * 
	 */
	public enum Gender {
		FEMALE, MALE;
	}

	// Como poner edad/cumple?
	private String birthday;
	private String email;

	private Gender gender;
	private long id;
	private String nickName;
	// Pensar en poner esto como un enum con opciones predefinidas!
	private List<String> preferences;
	private String sizePants;
	private String sizeShirt;
	private String sizeTShirt;

	/**
	 * Empty Constructor used by jackson
	 */
	public User() {

	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @return the preference1
	 */
	public List<String> getPreference1() {
		return preferences;
	}

	/**
	 * @return the sizePants
	 */
	public String getSizePants() {
		return sizePants;
	}

	/**
	 * @return the sizeShirt
	 */
	public String getSizeShirt() {
		return sizeShirt;
	}

	/**
	 * @return the sizeTShirt
	 */
	public String getSizeTShirt() {
		return sizeTShirt;
	}

	/**
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @param preference1
	 */
	public void setPreference1(List<String> preferences) {
		this.preferences = preferences;
	}

	/**
	 * @param sizePants
	 */
	public void setSizePants(String sizePants) {
		this.sizePants = sizePants;
	}

	/**
	 * @param sizeShirt
	 */
	public void setSizeShirt(String sizeShirt) {
		this.sizeShirt = sizeShirt;
	}

	/**
	 * @param sizeTShirt
	 */
	public void setSizeTShirt(String sizeTShirt) {
		this.sizeTShirt = sizeTShirt;
	}

}
