package com.android.retrievedata;

/**
 * @author Ignacio Mulas - Draft
 * 
 */
public class GetArticle extends Thread {

	/**
	 * 
	 */
	public GetArticle() {
	}

	/**
	 * @param runnable
	 */
	public GetArticle(Runnable runnable) {
		super(runnable);
	}

	/**
	 * @param runnable
	 * @param threadName
	 */
	public GetArticle(Runnable runnable, String threadName) {
		super(runnable, threadName);
	}

	/**
	 * @param threadName
	 */
	public GetArticle(String threadName) {
		super(threadName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		// TODO: Here we will fetch the data from the server
	}
}
