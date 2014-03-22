package com.example.procesamientoimagenes;


import procesamiento.MetodosProcesamiento;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.Button;

public class MainActivity extends Activity {
	ImageView panelImagen;
	MetodosProcesamiento metodoProcesar;
	Button botonGrises, botonRoberts,botonConvolucion,botonBinarizacion, botonOriginal, botonEmpalmar;
	EditText umbral;
	Bitmap mapa;
	int vectMultiplicante[]={1,1,1,1,1,1,1,1,1};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		panelImagen = (ImageView)findViewById(R.id.imageView);
		panelImagen.setImageResource(R.drawable.imagen2);
		 
		
	//	panelImagen.setImageBitmap(mapa);
	//	
		botonRoberts = (Button)findViewById(R.id.buttonRoberts);
		botonConvolucion = (Button) findViewById(R.id.buttonConvolucion);
		botonGrises=(Button)findViewById(R.id.BGrises);
		botonBinarizacion = (Button)findViewById(R.id.buttonBinarizacion);
		botonOriginal = (Button) findViewById(R.id.buttonOriginal);
		botonEmpalmar = (Button) findViewById(R.id.buttonEmpalmar);
		botonGrises.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				//mapa = Bitmap.createBitmap(panelImagen.getWidth(),panelImagen.getHeight(), Config.ARGB_8888);
				//mapa=Bitmap.createBitmap(980,310,Config.ARGB_8888);
				mapa = ((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				metodoProcesar = new MetodosProcesamiento(mapa);
				metodoProcesar.descomponerRGB();
				metodoProcesar.escalaGrises();
				metodoProcesar.componerRGB();
				panelImagen.setImageBitmap(metodoProcesar.getMapa());
			}
		});
		botonRoberts.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapa=((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				metodoProcesar = new MetodosProcesamiento(mapa);
				metodoProcesar.descomponerRGB();
				metodoProcesar.escalaGrises();
				metodoProcesar.metodoRoberts();
				metodoProcesar.componerRGB();
				panelImagen.setImageBitmap(metodoProcesar.getMapa());
			}
		});
		botonConvolucion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapa=((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				metodoProcesar = new MetodosProcesamiento(mapa);
				metodoProcesar.descomponerRGB();
				//metodoProcesar.escalaGrises();
				//metodoProcesar.componerRGB();
				metodoProcesar.metodoConvolucion(vectMultiplicante);
				panelImagen.setImageBitmap(metodoProcesar.getMapa());
			}
		});
		botonBinarizacion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				umbral = (EditText)findViewById(R.id.editTextUmbral);
				mapa=((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				metodoProcesar = new MetodosProcesamiento(mapa);
				metodoProcesar.descomponerRGB();
				metodoProcesar.escalaGrises();
				metodoProcesar.metodoBinarizacion(Integer.parseInt(umbral.getText().toString()));
				metodoProcesar.componerRGB();
				panelImagen.setImageBitmap(metodoProcesar.getMapa());
			}
		});
		botonOriginal.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				panelImagen.setImageResource(R.drawable.imagen2);
			}
		});
		botonEmpalmar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				Intent intencion = new Intent(MainActivity.this,MorfologiaActivity.class);
				startActivity(intencion);
			}
		});
		
		
	}

}
