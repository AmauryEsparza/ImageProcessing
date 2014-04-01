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
import android.widget.NumberPicker;
import android.widget.Spinner;


public class ProcesamientoActivity extends Activity{
	ImageView panelImagen;
	Button ButtonFreeman, ButtonSegmentacion;
	Bitmap mapaImagen;
	EditText textFreeman;
	NumberPicker componentePicker;
	String[] nombresPicker;
	MetodosProcesamientoImagenes objProcesamiento;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metodosprocesamiento);
		panelImagen = (ImageView)findViewById(R.id.imageView1);
		ButtonSegmentacion= (Button) findViewById(R.id.buttonSegmentacion);
		textFreeman=(EditText)findViewById(R.id.editFreeman);
		componentePicker = (NumberPicker)findViewById(R.id.componentePicker);
		
		ButtonSegmentacion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vista)
			{
				mapaImagen = ((BitmapDrawable)(panelImagen.getDrawable())).getBitmap();
				objProcesamiento= new MetodosProcesamientoImagenes(mapaImagen);
				objProcesamiento.descomponerRGB();
				objProcesamiento.escalaGrises();
				objProcesamiento.metodoBinarizacion();
				objProcesamiento.componentes4Conectados();
				//.....................ComponentePicker.....................
				componentePicker.setMinValue(0);
				componentePicker.setMaxValue(objProcesamiento.contarComponentesConvexos()-1);
				objProcesamiento.llenarLista();
				objProcesamiento.getComponentesLista(0);
				textFreeman.setText(objProcesamiento.getFreeman(0)+" ");
				objProcesamiento.componerRGB();
				panelImagen.setImageBitmap(objProcesamiento.getMapa());
			}
		});
		componentePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			public void onValueChange(NumberPicker componentePicker, int valAnterior, int valNuevo) {
				objProcesamiento.getComponentesLista(valNuevo);
				textFreeman.setText(objProcesamiento.getFreeman(valNuevo)+"");
				objProcesamiento.componerRGB();
				panelImagen.setImageBitmap(objProcesamiento.getMapa());
				
			}
		});
			
	}
}
