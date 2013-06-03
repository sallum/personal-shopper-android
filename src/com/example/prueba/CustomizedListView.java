package com.example.prueba;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.personalshopper.R;
 
public class CustomizedListView extends Activity {
    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_ARTICLE = "article"; // parent node
    static final String KEY_TYPE = "type";
    static final String KEY_ID = "id";
    static final String KEY_BRAND = "brand";
    static final String KEY_SIZE = "size";
    static final String KEY_PRIZE = "prize";
    static final String KEY_THUMB_URL = "thumb_url";
 
    ListView list;
    LazyAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        ArrayList<HashMap<String, String>> articleList = new ArrayList<HashMap<String, String>>();
 
        DocumentBuilder docBuilderFactory;
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilderFactory.parse(getAssets().open("articles.xml"));
			doc.getDocumentElement ().normalize ();
			
			NodeList nl = doc.getElementsByTagName(KEY_ARTICLE);
			
			// looping through all song nodes &lt;song&gt;
	        for (int i = 0; i < nl.getLength(); i++) {
	            // creating new HashMap
	            HashMap<String, String> map = new HashMap<String, String>();
	            Element e = (Element) nl.item(i);
	            // adding each child node to HashMap key =&gt; value
	            //KEY_ID
	            NodeList idList = e.getElementsByTagName(KEY_ID);
	            Element firstIdElement = (Element)idList.item(0);
	            NodeList textIdList = firstIdElement.getChildNodes();
	            //--id
	            map.put(KEY_ID,((Node)textIdList.item(0)).getNodeValue().trim());
	            
	            //KEY_TITLE
	            NodeList typeList = e.getElementsByTagName(KEY_TYPE);
	            Element firstTypeElement = (Element)typeList.item(0);
	            NodeList textTypeList = firstTypeElement.getChildNodes();
	            //--title
	            map.put(KEY_TYPE,((Node)textTypeList.item(0)).getNodeValue().trim());
	            
	            //KEY_ARTIST
	            NodeList brandList = e.getElementsByTagName(KEY_BRAND);
	            Element firstBrandElement = (Element)brandList.item(0);
	            NodeList textBrandList = firstBrandElement.getChildNodes();
	            //--artist
	            map.put(KEY_BRAND,((Node)textBrandList.item(0)).getNodeValue().trim());

	            //KEY_DURATION
	            NodeList prizeList = e.getElementsByTagName(KEY_PRIZE);
	            Element firstPrizeElement = (Element)prizeList.item(0);
	            NodeList textPrizeList = firstPrizeElement.getChildNodes();
	            //--duration
	            map.put(KEY_PRIZE,((Node)textPrizeList.item(0)).getNodeValue().trim());

	            //KEY_THUMB_URL
	            NodeList thumbList = e.getElementsByTagName(KEY_THUMB_URL);
	            Element firstThumbElement = (Element)thumbList.item(0);
	            NodeList textThumbList = firstThumbElement.getChildNodes();
	            //--image
	            map.put(KEY_THUMB_URL,((Node)textThumbList.item(0)).getNodeValue().trim());

	            // adding HashList to ArrayList
	            articleList.add(map);
	        }
			
			
		} catch (ParserConfigurationException e1) {
	
			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		} catch (SAXException e1) {

			e1.printStackTrace();
		}

        list=(ListView)findViewById(R.id.list);
 
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, articleList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id) {}});
    }
}