package com.example.detemporada;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Clase que facilita el acceso a una Base de Datos SQLite creando la Base de
 * datos a partir de un fichero en la carpeta Assets blog.findemor.es 06/02/2011
 **/
public class BaseDatosHelper extends SQLiteOpenHelper {

	// La carpeta por defecto donde Android espera encontrar la Base de Datos de
	// tu aplicación
	private static String DB_PATH = "/data/data/com.example.detemporada/databases/";
	private static String DB_NAME = "deTemporada";
	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor
	 * 
	 * Guarda una referencia al contexto para acceder a la carpeta assets de la
	 * aplicación y a los recursos
	 * 
	 * @param contexto
	 **/
	public BaseDatosHelper(Context contexto) {

		super(contexto, DB_NAME, null, 1);
		this.myContext = contexto;
	}

	/**
	 * Crea una base de datos vacía en el sistema y la sobreescribe con la que
	 * hemos puesto en Assets
	 **/
	public void crearDataBase() throws IOException {

		boolean dbExist = comprobarBaseDatos();

		if (dbExist) {
			// Si ya existe no hacemos nada
		} else {
			// Si no existe, creamos una nueva Base de datos en la carpeta por
			// defecto de nuestra aplicación,
			// de esta forma el Sistema nos permitirá sobreescribirla con la que
			// tenemos en la carpeta Assets
			this.getReadableDatabase();
			try {
				copiarBaseDatos();
			} catch (IOException e) {
				throw new Error("Error al copiar la Base de Datos");
			}
		}
	}

	/**
	 * Comprobamos si la base de datos existe
	 * 
	 * @return true si existe, false en otro caso
	 **/
	private boolean comprobarBaseDatos() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// No existe
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copia la base de datos desde la carpeta Assets sobre la base de datos
	 * vacía recién creada en la carpeta del sistema, desde donde es accesible
	 **/
	private void copiarBaseDatos() throws IOException {

		// Abrimos la BBDD de la carpeta Assets como un InputStream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Carpeta de destino (donde hemos creado la BBDD vacia)
		String outFileName = DB_PATH + DB_NAME;

		// Abrimos la BBDD vacia como OutputStream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// Transfiere los Bytes entre el Stream de entrada y el de Salida
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Cerramos los ficheros abiertos
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * Abre la base de datos
	 **/
	public void abrirBaseDatos() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	/**
	 * Cierra la base de datos
	 **/
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// No usamos este método
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// No usamos este método
	}

	// Podemos añadir métodos públicos que accedan al contenido de la base de
	// datos,
	// para realizar consultas, u operaciones CRUD (create, read, update,
	// delete)

	/*
	 * private final String TABLE_LIBROS = "productos"; private final String
	 * TABLE_KEY_ID = "_id"; private final String TABLE_KEY_NOMBRE = "nombre";
	 * private final String TABLE_KEY_TIPO = "tipo"; private final String
	 * TABLE_KEY_ENE="ENE"; private final String TABLE_KEY_FEB="FEB"; private
	 * final String TABLE_KEY_MAR="MAR"; private final String
	 * TABLE_KEY_ABR="ABR"; private final String TABLE_KEY_MAY="MAY"; private
	 * final String TABLE_KEY_JUN="JUN"; private final String
	 * TABLE_KEY_JUL="JUL"; private final String TABLE_KEY_AGO="AGO"; private
	 * final String TABLE_KEY_SEP="SEP"; private final String
	 * TABLE_KEY_OCT="OCT"; private final String TABLE_KEY_NOV="NOV"; private
	 * final String TABLE_KEY_DIC="DIC";
	 */
	/*
	 * Obtiene todos los libros desde la Base de Datos
	 */
	public ArrayList<Producto> GetProductos(Bundle param) {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		/*Calendar cal = new GregorianCalendar();
		
		int mes;
		mes = cal.get(Calendar.MONTH);

		switch (mes) {
		case 0:
			mes_actual = "ENE";
			break;
		case 1:
			mes_actual = "FEB";
			break;
		case 2:
			mes_actual = "MAR";
			break;
		case 3:
			mes_actual = "ABR";
			break;
		case 4:
			mes_actual = "MAY";
			break;
		case 5:
			mes_actual = "JUN";
			break;
		case 6:
			mes_actual = "JUL";
			break;
		case 7:
			mes_actual = "AGO";
			break;
		case 8:
			mes_actual = "SEP";
			break;
		case 9:
			mes_actual = "OCT";
			break;
		case 10:
			mes_actual = "NOV";
			break;
		case 11:
			mes_actual = "DIC";
			break;

		}*/
		String mes_actual = "";
		Producto p = new Producto();
		mes_actual = p.getMesActual();
		
		
		Cursor c = myDataBase.rawQuery("SELECT nombre, "+ mes_actual +" from productos WHERE "
				+ mes_actual + "!=0 AND tipo= "+ param.getLong("tipo"), null);
		// Iteramos a traves de los registros del cursor
		if (c != null) {
			c.moveToFirst();
			do {
				Producto producto = new Producto();
				producto.setNombre(c.getString(0));
				producto.setDisponibilidad(c.getString(1));
				listaProductos.add(producto);
			} while (c.moveToNext());
		}
		c.close();
		return listaProductos;

	}

}