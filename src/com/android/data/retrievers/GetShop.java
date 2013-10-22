package com.android.data.retrievers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import android.util.Log;

import com.android.data.types.Shop;
import com.android.utils.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that implements gets the articles information from the server
 * 
 * @author Ignacio Mulas
 */
public class GetShop implements Callable<Shop> {

	private URL url;

	public GetShop(long shopId) {
		try {
			this.url = new URL(Constants.SERVER_URL + "/rip/shops/" + shopId);
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
	private Shop instantiateShop() {
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(url, Shop.class);
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
