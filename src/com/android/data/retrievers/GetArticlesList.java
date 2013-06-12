package com.android.data.retrievers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.content.Context;
import android.util.Log;

import com.android.data.types.Article;
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

	Context context;

	public GetArticlesList(Context context) {
		// TODO: Remove this when server is ready, just a work around to use
		// assets
		this.context = context;
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
			// TODO: read from server!
			// entity = mapper.readValue(URL, clazz);
			InputStream is = context.getAssets().open("article.json");
			articleList = mapper.readValue(is,
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
