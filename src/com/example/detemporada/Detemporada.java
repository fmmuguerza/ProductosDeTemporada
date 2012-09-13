package com.example.detemporada;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
 
public class Detemporada extends Activity {
	
	final public static String MyKey = "mikey";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detemporada);
        
        Producto p = new Producto();
        TextView mes = (TextView) findViewById(R.id.mes_actual);
		mes.setText(p.getMesActual());
        
        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	// Cargamos el Bundle con la coleccion de objetos que pasaremos
                // a la siguiente vista
                Bundle bundle = new Bundle();
                bundle.putLong("tipo",1);
 
                // Creamos la vista de Lista de objetos y le pasamos la
                // colección de objetos a mostrar
                Intent myIntent = new Intent(view.getContext(),
                        ControladorLista.class);
                myIntent.putExtras(bundle);
 
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button next2 = (Button) findViewById(R.id.Button03);
        next2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	// Cargamos el Bundle con la coleccion de objetos que pasaremos
                // a la siguiente vista
                Bundle bundle2 = new Bundle();
                bundle2.putLong("tipo",2);
 
                // Creamos la vista de Lista de objetos y le pasamos la
                // colección de objetos a mostrar
                Intent myIntent2 = new Intent(view.getContext(),
                        ControladorLista.class);
                myIntent2.putExtras(bundle2);
 
                startActivityForResult(myIntent2, 0);
            }
        });
        
        Button next5 = (Button) findViewById(R.id.Button04);
        next5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	// Cargamos el Bundle con la coleccion de objetos que pasaremos
                // a la siguiente vista
                Bundle bundle5 = new Bundle();
                bundle5.putLong("tipo",4);
 
                // Creamos la vista de Lista de objetos y le pasamos la
                // colección de objetos a mostrar
                Intent myIntent5 = new Intent(view.getContext(),
                        ControladorLista.class);
                myIntent5.putExtras(bundle5);
 
                startActivityForResult(myIntent5, 0);
            }
        });
        
        Button next3 = (Button) findViewById(R.id.Button05);
        next3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	// Cargamos el Bundle con la coleccion de objetos que pasaremos
                // a la siguiente vista
                Bundle bundle3 = new Bundle();
                bundle3.putLong("tipo",3);
 
                // Creamos la vista de Lista de objetos y le pasamos la
                // colección de objetos a mostrar
                Intent myIntent3 = new Intent(view.getContext(),
                        ControladorLista.class);
                myIntent3.putExtras(bundle3);
 
                startActivityForResult(myIntent3, 0);
            }
        });
        
        Button next4 = (Button) findViewById(R.id.Button06);
        next4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	// Cargamos el Bundle con la coleccion de objetos que pasaremos
                // a la siguiente vista
                Bundle bundle4 = new Bundle();
                bundle4.putLong("tipo",5);
 
                // Creamos la vista de Lista de objetos y le pasamos la
                // colección de objetos a mostrar
                Intent myIntent4 = new Intent(view.getContext(),
                        ControladorLista.class);
                myIntent4.putExtras(bundle4);
 
                startActivityForResult(myIntent4, 0);
            }
        });
    }
}

