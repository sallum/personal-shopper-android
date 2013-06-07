package com.android.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.data.retrievers.ImageLoader;
import com.android.data.types.Article;
import com.android.personalshopper.R;

/**
 * Adapter to fill the articles list.
 * 
 * @author Carlos Andres - 03-06-2013 - Initial version
 * @author Ignacio Mulas - 06-06-2013 - Moved and adapted with Json structure
 */
public class LazyAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;
	private List<Article> articleList;

	public LazyAdapter(Activity activity, List<Article> articleList) {
		this.articleList = articleList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return articleList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.list_row, null);
		}

		Article article = new Article();
		article = articleList.get(position);

		// Setting all values in listview
		// TODO: Add all the required fields
		((TextView) vi.findViewById(R.id.type)).setText(article.getType());
		((TextView) vi.findViewById(R.id.brand)).setText(article.getBrand());
		((TextView) vi.findViewById(R.id.prize)).setText(Float.toString(article
				.getPrize()));

		// Setting an image
		ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image);
		ImageLoader imageLoader = new ImageLoader(vi.getContext());
		imageLoader.displayImage(article.getImage(), thumb_image);
		return vi;
	}

}