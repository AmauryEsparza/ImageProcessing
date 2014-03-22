package com.example.procesamientoimagenes;

import procesamiento.MetodosProcesamientoImagenes;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Spinner;


public class ProcesamientoActivity extends Activity{
	ImageView panelImagen;
	Button ButtonFreeman, ButtonSegmentacion;
	Bitmap mapaImagen;
	EditText textFreeman;
	Spinner spinnerComponentes;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metodosprocesamiento);
		panelImagen = (ImageView)findViewById(R.id.imageView1);
		ButtonFreeman = (Button)findViewById(R.id.buttonFreeman);
		ButtonSegmentacion= (Button) findViewById(R.id.buttonSegmentacion);
		textFreeman=(EditText)findViewById(R.id.editFreeman);
		spinnerComponentes = (Spinner)findViewById(R.id.spinnerComponentes);
		
		ButtonSegmentacion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapaImagen = ((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				MetodosProcesamientoImagenes objProcesamiento = new MetodosProcesamientoImagenes(mapaImagen);
				objProcesamiento.descomponerRGB();
				objProcesamiento.escalaGrises();
				objProcesamiento.metodoBinarizacion();
				objProcesamiento.componentes4Conectados();
				textFreeman.setText(objProcesamiento.contarComponentesConvexos()+" ");
				objProcesamiento.llenarLista();
				objProcesamiento.getComponentesLista();
				objProcesamiento.componerRGB();
				panelImagen.setImageBitmap(objProcesamiento.getMapa());
			}
		});
		ButtonFreeman.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapaImagen = ((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				MetodosProcesamientoImagenes objProcesamiento = new MetodosProcesamientoImagenes(mapaImagen);
				objProcesamiento.descomponerRGB();
				objProcesamiento.escalaGrises();
				objProcesamiento.metodoBinarizacion();
				textFreeman.setText(objProcesamiento.metodoFreeman());
				//objProcesamiento.componerRGB();
				//panelImagen.setImageBitmap(objProcesamiento.getMapa());
				
			}
		});
		
		
	}
}
