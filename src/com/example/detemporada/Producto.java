package com.example.detemporada;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Producto {
	private String nombre;
	private String tipo;
	private String diponibilidad;
	
	public String getDisponibilidad(){
		return this.diponibilidad;
	}
	public void setDisponibilidad(String diponibilidad){
		this.diponibilidad = diponibilidad;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/* Devuelve ENE, FEB, MAR, etc dependiendo de la fecha actual*/
	public String getMesActual(){
		int mes;
		Calendar cal = new GregorianCalendar();
		String mes_actual = "";
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

		}
		return mes_actual;
	}
	
}
