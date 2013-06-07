package com.android.data.retrievers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.memory.FileCache;
import com.android.memory.MemoryCache;
import com.android.personalshopper.R;

/**
 * This class will download an image from the server and show it on the list of
 * articles.
 * 
 * @author Ignacio Mulas - 07-06-2013 - Initial version
 */
public class ImageLoader {

	/**
	 * Class containing photo info
	 * 
	 * @author Ignacio Mulas - 07-06-2013 - Initial version
	 */
	private class PhotoToLoad {
		private ImageView imageView;
		private String url;

		/**
		 * Constructor
		 * 
		 * @param url
		 * @param imageView
		 */
		public PhotoToLoad(String url, ImageView imageView) {
			this.url = url;
			this.imageView = imageView;
		}
	}

	/**
	 * Used to display bitmap in the UI thread
	 * 
	 * @author Ignacio Mulas - 07-06-2013 - Initial version
	 */
	class BitmapDisplayer implements Runnable {
		private Bitmap bitmap;
		private PhotoToLoad photoToLoad;

		/**
		 * Constructor
		 * 
		 * @param bitmap
		 * @param photoToLoad
		 */
		public BitmapDisplayer(Bitmap bitmap, PhotoToLoad photoToLoad) {
			this.bitmap = bitmap;
			this.photoToLoad = photoToLoad;
		}

		@Override
		public void run() {
			if (imageViewReused(photoToLoad)) {
				return;
			}
			if (bitmap != null) {
				photoToLoad.imageView.setImageBitmap(bitmap);
			} else {
				photoToLoad.imageView.setImageResource(stub_id);
			}
		}
	}

	/**
	 * Update the articles list view
	 * 
	 * @author Ignacio Mulas - 07-06-2013 - Initial version
	 */
	class PhotosLoader implements Runnable {
		private PhotoToLoad photoToLoad;

		/**
		 * Constructor
		 * 
		 * @param photoToLoad
		 */
		public PhotosLoader(PhotoToLoad photoToLoad) {
			this.photoToLoad = photoToLoad;
		}

		@Override
		public void run() {
			if (imageViewReused(photoToLoad)) {
				return;
			}
			Bitmap bmp = getBitmap(photoToLoad.url);
			memoryCache.put(photoToLoad.url, bmp);
			if (imageViewReused(photoToLoad)) {
				return;
			}
			BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
			Activity a = (Activity) photoToLoad.imageView.getContext();
			a.runOnUiThread(bd);
		}
	}

	private ExecutorService executorService;
	private FileCache fileCache;
	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());
	private MemoryCache memoryCache = new MemoryCache();
	private final int stub_id = R.drawable.ic_launcher;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public ImageLoader(Context context) {
		fileCache = new FileCache(context);
		executorService = Executors.newFixedThreadPool(5);
	}

	/**
	 * Decodes image and scales it to reduce memory consumption
	 * 
	 * @param file
	 * @return bitmap
	 */
	private Bitmap decodeFile(File file) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(file), null, o);

			// TODO: Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 70;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE) {
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(file), null,
					o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	/**
	 * Open a connection and get the bitmap decoded.
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getBitmap(String url) {
		File f = fileCache.getFile(url);

		// from SD cache
		Bitmap b = decodeFile(f);
		if (b != null) {
			return b;
		}

		// from web
		try {
			Bitmap bitmap = null;
			URL imageUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(true);
			InputStream is = conn.getInputStream();
			OutputStream os = new FileOutputStream(f);
			Utils.CopyStream(is, os);
			os.close();
			bitmap = decodeFile(f);
			return bitmap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Internal method to queue photo loader classes
	 * 
	 * @param url
	 * @param imageView
	 */
	private void queuePhoto(String url, ImageView imageView) {
		PhotoToLoad p = new PhotoToLoad(url, imageView);
		executorService.submit(new PhotosLoader(p));
	}

	/**
	 * Internal method to check if an image is already in memory
	 * 
	 * @param photoToLoad
	 * @return
	 */
	boolean imageViewReused(PhotoToLoad photoToLoad) {
		String tag = imageViews.get(photoToLoad.imageView);
		if (tag == null || !tag.equals(photoToLoad.url)) {
			return true;
		}
		return false;
	}

	/**
	 * Clear cache memory
	 */
	public void clearCache() {
		memoryCache.clear();
		fileCache.clear();
	}

	/**
	 * Method that gets an image if it is in memory or queue a new job to get
	 * the image from the server and display it
	 * 
	 * @param url
	 * @param imageView
	 */
	public void displayImage(String url, ImageView imageView) {
		imageViews.put(imageView, url);
		Bitmap bitmap = memoryCache.get(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			queuePhoto(url, imageView);
			imageView.setImageResource(stub_id);
		}
	}
}