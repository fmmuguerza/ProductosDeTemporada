package com.example.detemporada;

import java.io.IOException;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ControladorLista extends ListActivity {

	private class ProductoAdapter extends ArrayAdapter<Producto> {

		private ArrayList<Producto> items;

		public ProductoAdapter(Context context, int textViewResourceId,
				ArrayList<Producto> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.lista_item, null);
			}
			Producto producto = items.get(position);
			
			   if (producto != null) {
	                TextView tnombre = (TextView) v.findViewById(R.id.nombre);
	                TextView tdisponibilidad = (TextView) v.findViewById(R.id.disponibilidad);
	                if (tnombre != null) {
	                	tnombre.setText(producto.getNombre());
	                	tdisponibilidad.setText(producto.getDisponibilidad());
	                }
	      
	            }

			return v;
		}
	}

	BaseDatosHelper miBBDDHelper;

	public void crearBBDD() {
		miBBDDHelper = new BaseDatosHelper(this);
		try {
			miBBDDHelper.crearDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle=getIntent().getExtras();
		// Creamos la Base de datos
		crearBBDD();
		// Obtenemos la lista de Productos
		ArrayList<Producto> Productos = getItems(bundle);
		// Entregamos la lista de Productos al adaptador de la lista en el Layout
		// Lista.xml
		setListAdapter(new ProductoAdapter(this, R.layout.lista_item, Productos));
	}

	/*
	 * Obtiene una lista de Productos
	 * 
	 * @returns ArrayList<Producto> Productos
	 */
	public ArrayList<Producto> getItems(Bundle param) {
		// Abrimos una conexión
		miBBDDHelper.abrirBaseDatos();
		// Consultamos los datos
		ArrayList<Producto> listaProductos = miBBDDHelper.GetProductos(param);
		// Cerramos la conexion
		miBBDDHelper.close();
		// Devolvemos los datos
		return listaProductos;
	}
}