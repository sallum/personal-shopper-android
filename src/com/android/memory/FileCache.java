package com.android.memory;

import java.io.File;

import android.content.Context;

/**
 * Class to handle external storage in the android device.
 * 
 * @author Ignacio Mulas
 * 
 */
public class FileCache {

	private File cacheDir;

	public FileCache(Context context) {
		// Find the dir to save cached images
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			cacheDir = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"LazyList");
		} else {
			cacheDir = context.getCacheDir();
		}
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			f.delete();
		}
	}

	public File getFile(String url) {
		// TODO: Check for better ways?
		// I identify images by hashcode. Not a perfect solution, good for the
		// demo.
		String filename = String.valueOf(url.hashCode());
		// Another possible solution (thanks to grantland)
		// String filename = URLEncoder.encode(url);
		File f = new File(cacheDir, filename);
		return f;

	}

}