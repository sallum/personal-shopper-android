package com.android.data.retrievers;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import android.content.Context;
import android.util.Log;

import com.android.data.types.Shop;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that implements gets the articles information from the server
 * 
 * @author Ignacio Mulas
 */
public class GetShop implements Callable<Shop> {

	Context context;

	public GetShop(Context context) {
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
	private Shop instantiateShop() {
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			// TODO: read from server!
			// entity = mapper.readValue(URL, clazz);
			InputStream is = context.getAssets().open("shop.json");
			shop = mapper.readValue(is, Shop.class);
			// TODO: Change error messages
		} catch (JsonParseException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
			Log.e("Parsing error", e1.getCause().toString());
		} catch (IOException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		}
		return shop;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shop call() {
		return instantiateShop();
	}
}
