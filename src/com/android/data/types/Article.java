package com.android.data.types;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonProperty("article_id")
	private long articleId;
	// TODO: poner aqui la imagen o el path...

	@JsonProperty("brand")
	private String brand;

	@JsonProperty("colour")
	private String colour;
	// TODO: Poner preferencias de usuarios y features de articulos como un enum
	// comun
	// private String feature;

	@JsonProperty("image")
	private String image;

	@JsonProperty("model")
	private String model;

	@JsonProperty("price")
	private float price;

	@JsonProperty("shop_id")
	private long shop;

	@JsonProperty("size")
	private String size;

	@JsonProperty("type")
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
	public String getColour() {
		return colour;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return articleId;
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
		return price;
	}

	/**
	 * @return the shop
	 */
	public long getShop() {
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

	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.articleId = id;
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
		this.price = prize;
	}

	/**
	 * @param shop
	 */
	public void setShop(long shop) {
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
				+ articleId + ", "
				+ (image != null ? "image=" + image + ", " : "")
				+ (model != null ? "model=" + model + ", " : "") + "prize="
				+ price + ", " + "shop=" + shop + ", "
				+ (size != null ? "size=" + size + ", " : "")
				+ (type != null ? "type=" + type : "") + "]";
	}
}
