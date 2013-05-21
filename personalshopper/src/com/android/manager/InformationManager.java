package com.android.manager;

import java.util.HashMap;
import java.util.Map;

import android.util.SparseArray;

import com.android.datatypes.Article;
import com.android.datatypes.Shop;
import com.android.datatypes.User;

/**
 * This class will contain the information brought by the reading threads.
 * 
 * @author Ignacio Mulas - 17-05-2013 - Initial version
 * 
 */
public final class InformationManager {

	private static Map<Long, Article> listArticles = new HashMap<Long, Article>();
	private static SparseArray<Shop> listShops = new SparseArray<Shop>();
	private static Map<Long, User> listUsers = new HashMap<Long, User>();

	/**
	 * Gets an article with a given id
	 * 
	 * @param id
	 * @return
	 */
	public static Article getArticle(long id) {
		return listArticles.get(id);
	}

	/**
	 * Gets a shop with a given id
	 * 
	 * @param id
	 * @return
	 */
	public static Shop getShop(int id) {
		return listShops.get(id);
	}

	/**
	 * Gets a user with a given id
	 * 
	 * @param id
	 * @return
	 */
	public static User getUser(long id) {
		return listUsers.get(id);
	}

	/**
	 * Put an article on the common map.
	 * 
	 * @param article
	 */
	public static void put(Article article) {
		listArticles.put(article.getId(), article);
	}

	/**
	 * Put a shop in the common map.
	 * 
	 * @param shop
	 */
	public static void put(Shop shop) {
		listShops.put(shop.getId(), shop);
	}

	/**
	 * Put an user in the common map.
	 * 
	 * @param user
	 */
	public static void put(User user) {
		listUsers.put(user.getId(), user);
	}

	// /**
	// * Put data in the corresponding list depending on the data type provided
	// to
	// * the method
	// *
	// * @param entity
	// */
	// public static <E extends Entity> void putIntoList(E entity) {
	// if (entity instanceof Article) {
	// listArticles.put(((Article) entity).getId(), (Article) entity);
	// } else if (entity instanceof Shop) {
	// listShops.put(((Shop) entity).getId(), (Shop) entity);
	// } else if (entity instanceof User) {
	// listUsers.put(((User) entity).getId(), (User) entity);
	// }
	// }
}
