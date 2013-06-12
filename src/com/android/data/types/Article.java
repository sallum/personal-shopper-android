package com.android.data.types;

/**
 * Article Entity, represents an article.
 * 
 * @author Ignacio Mulas
 * 
 */
public class Article implements Entity {

	public enum Colour {
		BLACK, BLUE, GREEN, // TODO: añadir colores
		YELLOW;
	}

	public enum Type {
		BLAZER, // TODO: Añadir tipos
		PANT, SHIRT, SHOES, TSHIRT;
	}

	private String brand;
	private Colour colour;
	// TODO: Poner preferencias de usuarios y features de articulos como un enum
	// comun
	// private String feature;
	private long id;
	// TODO: poner aqui la imagen o el path...
	private String image;
	// Como poner esto?
	private String model;
	private float prize;
	private String shop;
	private String size;
	private String type;

	/**
	 * Constructor used by jackson to instantiate
	 */
	public Article() {
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the colour
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the feature
	 */
	// public String getFeature() {
	// return feature;
	// }

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the prize
	 */
	public float getPrize() {
		return prize;
	}

	/**
	 * @return the shop
	 */
	public String getShop() {
		return shop;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @param feature
	 */
	// public void setFeature(String feature) {
	// this.feature = feature;
	// }

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @param prize
	 */
	public void setPrize(float prize) {
		this.prize = prize;
	}

	/**
	 * @param shop
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}

	/**
	 * @param size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [" + (brand != null ? "brand=" + brand + ", " : "")
				+ (colour != null ? "colour=" + colour + ", " : "") + "id="
				+ id + ", " + (image != null ? "image=" + image + ", " : "")
				+ (model != null ? "model=" + model + ", " : "") + "prize="
				+ prize + ", " + (shop != null ? "shop=" + shop + ", " : "")
				+ (size != null ? "size=" + size + ", " : "")
				+ (type != null ? "type=" + type : "") + "]";
	}
}
