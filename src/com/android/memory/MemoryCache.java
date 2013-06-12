package com.android.memory;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

/**
 * Fast access memory to temporarily store the images.
 * 
 * @author Ignacio Mulas
 * 
 */
public class MemoryCache {
	private Map<String, SoftReference<Bitmap>> cache = Collections
			.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

	/**
	 * Clear memory.
	 */
	public void clear() {
		cache.clear();
	}

	/**
	 * Getter for the images.
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap get(String id) {
		if (!cache.containsKey(id)) {
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
	public void put(String id, Bitmap bitmap) {
		cache.put(id, new SoftReference<Bitmap>(bitmap));
	}
}