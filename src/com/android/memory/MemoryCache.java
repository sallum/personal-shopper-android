package com.android.memory;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Fast access memory to temporarily store the images.
 * 
 * @author Ignacio Mulas
 * 
 */
public class MemoryCache {
	private static Map<Long, SoftReference<Bitmap>> cache = Collections
			.synchronizedMap(new HashMap<Long, SoftReference<Bitmap>>());
	private static final String CACHE = "cache";

	/**
	 * Clear memory.
	 */
	public static void clear() {
		cache.clear();
	}

	/**
	 * Getter for the images.
	 * 
	 * @param id
	 * @return
	 */
	public static Bitmap get(long id) {
		if (!cache.containsKey(id)) {
			Log.d(CACHE, "The id is not present in the cache: " + id);
			return null;
		}
		SoftReference<Bitmap> ref = cache.get(id);
		return ref.get();
	}

	/**
	 * Setter for the images.
	 * 
	 * @param id
	 * @param bitmap
	 */
	public static void put(long id, Bitmap bitmap) {
		Log.d(CACHE, "Adding a new bitmap in the cache with id: " + id);
		cache.put(id, new SoftReference<Bitmap>(bitmap));
	}
}