package com.zsl.familyfinancing.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;//设置版本常量
	private static final String DBNAME = "account.db";//数据库名字

	public DBOpenHelper(Context context){//重写构造方法
	
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
	
		db.execSQL("create table tb_outaccount (_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),address varchar(100),mark varchar(200))");//创建tb_outaccount表
		db.execSQL("create table tb_inaccount (_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),handler varchar(100),mark varchar(200))");
		db.execSQL("create table tb_pwd (password varchar(20))");
		db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)//数据库更新时调用的方法
	{
	}
}
