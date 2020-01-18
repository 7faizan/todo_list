package c.singularities.todo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME="productname";
    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_PRODUCTNAME + " TEXT " + ");";
        sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }
    //To add
    public void addProduct(Products product)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_PRODUCTNAME,product.get_productname());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.insert(TABLE_PRODUCTS,null,values);
        sqLiteDatabase.close();
    }
    //DELETE
    public void deleteProduct(String productName)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }
    public String databaseToString()
    {
        String dbString="";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String query=" SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";
        Cursor recordSet= sqLiteDatabase.rawQuery(query,null);
        recordSet.moveToFirst();
        while(!recordSet.isAfterLast())
        {
            if(recordSet.getString(recordSet.getColumnIndex("productname"))!=null) {
                dbString+=recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString+= "\n";
            }
            recordSet.moveToNext();
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
