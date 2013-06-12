package com.android.data.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.content.Context;

import com.android.data.retrievers.GetArticlesList;
import com.android.data.retrievers.GetShop;
import com.android.data.types.Article;
import com.android.data.types.Shop;

/**
 * Data manager layer. This class will control the cached entity data or request
 * the information to the server if this is not available here.
 * 
 * @author Ignacio Mulas
 * 
 */
public class DataManager {

	/**
	 * Common thread pool to generate requests
	 */
	private static final ExecutorService threadPool = Executors
			.newCachedThreadPool();

	private List<Article> articleList;
	private Map<Long, Shop> shopMap;

	Context context;

	public DataManager(Context context) {
		// TODO: Remove this when server is ready, just a work around to use
		// assets
		this.context = context;
		articleList = Collections.synchronizedList(new ArrayList<Article>());
		shopMap = Collections.synchronizedMap(new HashMap<Long, Shop>());
	}

	/**
	 * Fetches the article list from the server
	 */
	private void getArticlesListFromServer() {
		GetArticlesList articleListThread = new GetArticlesList(context);
		Future<List<Article>> future = threadPool.submit(articleListThread);
		try {
			articleList.addAll(future.get());
			// TODO: Improve exception handling
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to create a number of requests to the server
	 * 
	 * @param numOfThreads
	 */
	public void getArticles(int numOfThreads) {
		for (int i = 0; i < numOfThreads; i++) {
			getArticlesListFromServer();
		}
	}

	/**
	 * Gets the available articles list
	 * 
	 * @return
	 */
	public List<Article> getArticlesList() {
		return articleList;
	}

	/**
	 * This will check if the shop is already present in memory and if it is not
	 * it will download the information from the server.
	 * 
	 * @param shopId
	 */
	public void getShop(long shopId) {
		if (!shopMap.containsKey(shopId)) {
			GetShop shopThread = new GetShop(context);
			Future<Shop> future = threadPool.submit(shopThread);
			try {
				Shop shop = future.get();
				shopMap.put(shop.getShopId(), shop);
				// TODO: Improve exception handling
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
