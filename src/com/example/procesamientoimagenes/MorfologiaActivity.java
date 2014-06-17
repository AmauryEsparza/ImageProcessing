package com.example.procesamientoimagenes;

import procesamiento.MetodosMorfologia;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.graphics.Bitmap;


public class MorfologiaActivity extends Activity {
	
	ImageView panelImagen1, panelImagen2;
	Button ButtonEmpalmar,ButtonAnd,ButtonDilatacion, ButtonProcesamiento, ButtonOriginal;
	Bitmap mapa1,mapa2;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.morfologia);
		panelImagen1 = (ImageView)findViewById(R.id.imageView1);
		panelImagen2 = (ImageView) findViewById(R.id.imageView2);
		
		/**Buttons Declaration */
		ButtonAnd = (Button) findViewById(R.id.buttonAnd);
		ButtonEmpalmar = (Button) findViewById(R.id.buttonJuntar);
		ButtonDilatacion = (Button) findViewById(R.id.buttonDilatacion);
		ButtonProcesamiento = (Button) findViewById(R.id.buttonProcesamiento);
		ButtonOriginal = (Button) findViewById(R.id.button_original);
		
		/** Buttons Definition */
		ButtonOriginal.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View vista)
			{
				panelImagen1.setImageResource(R.drawable.f);
				panelImagen2.setImageResource(R.drawable.l);
			}
		});
		
		ButtonEmpalmar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapa1 = ((BitmapDrawable)(panelImagen1.getDrawable())).getBitmap();
				mapa2 = ((BitmapDrawable)(panelImagen2.getDrawable())).getBitmap();
				MetodosMorfologia metodosM = new MetodosMorfologia(mapa1,mapa2);
				metodosM.descomponerRGB();
				metodosM.escalaGrises();
				metodosM.metodoBinarizacion();
				metodosM.metodoEmpalmar();
				metodosM.componerRGB();
				panelImagen1.setImageBitmap(metodosM.getMapa());
			}
		});
		ButtonAnd.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapa1= ((BitmapDrawable)(panelImagen1.getDrawable())).getBitmap();
				mapa2= ((BitmapDrawable)(panelImagen2.getDrawable())).getBitmap();
				MetodosMorfologia metodosM = new MetodosMorfologia(mapa1,mapa2);
				metodosM.descomponerRGB();
				metodosM.escalaGrises();
				metodosM.metodoBinarizacion();
				metodosM.metodoAnd();
				metodosM.componerRGB();
				panelImagen1.setImageBitmap(metodosM.getMapa());
			}
		});
		ButtonDilatacion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				int elementoEstructurante[]={1,1,1,1};
				mapa1= ((BitmapDrawable)(panelImagen1.getDrawable())).getBitmap();
				mapa2= ((BitmapDrawable)(panelImagen2.getDrawable())).getBitmap();
				MetodosMorfologia metodosM = new MetodosMorfologia(mapa1,mapa2);
				metodosM.descomponerRGB();
				metodosM.escalaGrises();
				metodosM.metodoBinarizacion();
				metodosM.metodoDilatacion(elementoEstructurante, 2, 2);
				metodosM.componerRGB();
				panelImagen1.setImageBitmap(metodosM.getMapa());
			}
		});
		ButtonProcesamiento.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				Intent intencion = new Intent(MorfologiaActivity.this,ProcesamientoActivity.class);
				startActivity(intencion);
			}
		});
	}
}
