package com.zsl.familyfinancing.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsl.familyfinancing.model.Tb_pwd;

public class PwdDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public PwdDAO (Context context){
		helper = new DBOpenHelper(context);
	}

	public void add(Tb_pwd tb_pwd){
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("password",tb_pwd.getPassword());
			db.insert("tb_pwd",null,values);
	}
	public void update(Tb_pwd tb_pwd){
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行修改密码操作
		db.execSQL("update tb_pwd set password = ?",new Object[] { tb_pwd.getPassword() });

	}
	public Tb_pwd find(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select password from tb_pwd",null);
		if(cursor.moveToNext()){
			return  new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
		}
		return null;
	}
	public long getCount(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(password) from tb_pwd", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		return  0;
	}
}
