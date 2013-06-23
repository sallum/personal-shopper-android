package com.android.personalshopper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.memory.MemoryCache;

public class ArticleView extends Activity {
	private TextView Brand;
	private TextView Type;
	private TextView Size;
	private TextView Colour;
	private TextView Shop;
	private TextView Address;
	private TextView Prize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_view);

		Brand = (TextView) findViewById(R.id.brandOneArticle);
		Type = (TextView) findViewById(R.id.typeOneArticle);
		Size = (TextView) findViewById(R.id.sizeOneArticle);
		Colour = (TextView) findViewById(R.id.colourOneArticle);
		Shop = (TextView) findViewById(R.id.shopOneArticle);
		Address = (TextView) findViewById(R.id.addressOneArticle);
		Prize = (TextView) findViewById(R.id.prizeOneArticle);
		
		Bundle bundle = getIntent().getExtras();
		Brand.setText(bundle.getString("brand"));
		Type.setText(bundle.getString("type"));
		Size.setText(bundle.getString("size"));
		Colour.setText(bundle.getString("colour"));
		Shop.setText(bundle.getString("shop"));
		Address.setText(bundle.getString("address"));
		Prize.setText(bundle.getString("prize"));
		
		

		ImageView thumb_image = (ImageView) findViewById(R.id.imageOneArticle);
		thumb_image.setImageBitmap(MemoryCache.get(getIntent().getExtras()
				.getLong("id")));
	}
}
