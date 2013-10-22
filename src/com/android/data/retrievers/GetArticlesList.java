package com.android.data.retrievers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.util.Log;

import com.android.data.types.Article;
import com.android.utils.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that implements gets the articles information from the server
 * 
 * @author Ignacio Mulas
 * 
 */
public class GetArticlesList implements Callable<List<Article>> {

	private URL url;

	public GetArticlesList() {
		try {
			url = new URL(Constants.SERVER_URL + "/rip/articles");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Helper method to instantiate an entity class
	 * 
	 * @param clazz
	 *            - Class to instantiate
	 * @return - instance
	 */
	private List<Article> instantiateArticleList() {
		ObjectMapper mapper = new ObjectMapper();
		List<Article> articleList = null;
		try {
			articleList = mapper.readValue(url,
					new TypeReference<ArrayList<Article>>() {
					});
		} catch (JsonParseException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		} catch (JsonMappingException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		} catch (IOException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		}
		return articleList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> call() throws Exception {
		return instantiateArticleList();
	}
}
