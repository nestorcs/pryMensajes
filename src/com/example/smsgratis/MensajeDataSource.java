package com.example.smsgratis;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MensajeDataSource {
	
	// Database fields
			private SQLiteDatabase database;
			private MySQLiteHelper dbHelper;
			private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
					MySQLiteHelper.COLUMN_NUMERO, MySQLiteHelper.COLUMN_MENSAJE };

			public MensajeDataSource(Context context) {
				dbHelper = new MySQLiteHelper(context);
			}

			public void open() throws SQLException {
				database = dbHelper.getWritableDatabase();
			}

			public void close() {
				dbHelper.close();
			}

			public Mensaje createMensaje(int numero, String mensaje) {
				ContentValues values = new ContentValues();
				values.put(MySQLiteHelper.COLUMN_NUMERO, numero);
				values.put(MySQLiteHelper.COLUMN_MENSAJE, mensaje);
				long insertId = database.insert(MySQLiteHelper.TABLE_MENSAJES, null,
						values);
				Cursor cursor = database.query(MySQLiteHelper.TABLE_MENSAJES,
						allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
						null, null, null);
				cursor.moveToFirst();
				Mensaje newMensaje = cursorToMensaje(cursor);
				cursor.close();
				return newMensaje;
			}

			public void deleteMensaje(Mensaje mensaje) {
				long id = mensaje.getId();
				System.out.println("Mensaje eliminado con id: " + id);
				database.delete(MySQLiteHelper.TABLE_MENSAJES, MySQLiteHelper.COLUMN_ID
						+ " = " + id, null);
			}

			public List<Mensaje> getAllComments() {
				List<Mensaje> comments = new ArrayList<Mensaje>();

				Cursor cursor = database.query(MySQLiteHelper.TABLE_MENSAJES,
						allColumns, null, null, null, null, null);

				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					Mensaje comment = cursorToMensaje(cursor);
					comments.add(comment);
					cursor.moveToNext();
				}
				// Make sure to close the cursor
				cursor.close();
				return comments;
			}

			private Mensaje cursorToMensaje(Cursor cursor) {
				Mensaje mensaje = new Mensaje();
				mensaje.setId(cursor.getLong(0));
				mensaje.setMensaje(cursor.getString(1));
				return mensaje;
			}

}
