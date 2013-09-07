package com.example.mdd2013;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.database.*;
import android.database.sqlite.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MainActivity extends Activity {
	EditText category =null;
	EditText product =null;
	EditText tesco_price =null;
	EditText jusco_price =null;
	EditText giant_price =null;
	Button button1=null;
	SQLiteDatabase productdb;
	Context myContext=null;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myContext=this.getApplicationContext();
		productdb=openOrCreateDatabase("productdb.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		
		if(checkForTable()!=true)
		{
		productdb.execSQL("create table checks(number integer primary key not null,category text, product text, tesco text,jusco text, giant text)"); 
		}
		category=(EditText)findViewById(R.id.category);
		product=(EditText)findViewById(R.id.product);
		tesco_price=(EditText)findViewById(R.id.tesco_price);
		jusco_price=(EditText)findViewById(R.id.jusco_price);
		giant_price=(EditText)findViewById(R.id.giant_price);
button1.setOnClickListener (new View.OnClickListener(){
			
			public void onClick(View V)
			{
				String key=category.getText().toString();
				Cursor c=productdb.query("productdb",null,"Product=?",new String[]{key},null,null,null);
			if(c.getCount()>0)
			{
				ContentValues map= new ContentValues();
			map.put("Category", category.getText().toString());
			map.put("Product", product.getText().toString());
			map.put("Tesco", tesco_price.getText().toString());
			map.put("Jusco",jusco_price.getText().toString());
			map.put("Giant", giant_price.getText().toString());
			productdb.update("productdb",map,"Product=?",new String[]{key});
			}else
			{
				ContentValues map= new ContentValues();
				map.put("Category", category.getText().toString());
				map.put("Product", product.getText().toString());
				map.put("Tesco", tesco_price.getText().toString());
				map.put("Jusco",jusco_price.getText().toString());
				map.put("Giant", giant_price.getText().toString());
				productdb.insert("productdb",null,map);
			}
			
			}
		
		});	
	
	}
		
	
		
	
	
		
		public void CreateProduct(View view)
		{
			Intent createproduct = new Intent(this, Create_product.class);
			startActivity(createproduct);
		}
		public boolean checkForTable()
		{
			Cursor c=productdb.query("productdb",null," Product=?",new String[]{"eggs"},null,null,null);
			if(c.getCount()>0)
				return true;
			else
				return false;
		}


	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}


