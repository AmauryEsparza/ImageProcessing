package com.example.procesamientoimagenes;

import procesamiento.MetodosProcesamiento;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.Button;

public class MainActivity extends Activity {
	ImageView panelImagen;
	MetodosProcesamiento metodoProcesar;
	Button botonGrises, botonRoberts;
	Bitmap mapa;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		panelImagen = (ImageView)findViewById(R.id.imageView);
		
		
	//	panelImagen.setImageBitmap(mapa);
	//	
		botonRoberts = (Button)findViewById(R.id.buttonRoberts);
		botonGrises=(Button)findViewById(R.id.BGrises);
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
	}

}
