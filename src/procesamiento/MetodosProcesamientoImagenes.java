package procesamiento;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import java.util.ArrayList;

public class MetodosProcesamientoImagenes {
	
	static final int RIGHT=0;
	static final int LEFT=2;
	static final int UP=1;
	static final int DOWN=3;
	String salida;
	String cadenaHuffman="";
	
	//int patron1[][]={{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	private int patrones[][] ={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
			{1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
			{0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0},
			{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0}	
	};
	String patronesFreeman[] = {"765",	//0
								"7665",
								"76665",
								"766665",
								"7666665",
								"76666665",
								"766666667",
								"123",
								"1223",
								"12223",
								"122223", //10
								"1222223",
								"12222223",
								"567",
								"5667",
								"56667",
								"566667",
								"5666667",
								"56666667",
								"321",
								"3221",  //20
								"32221",
								"322221",
								"3222221",
								"32222221",
								"107",
								"1007",
								"10007",
								"100007",
								"1000007",
								"10000007", //30
								"345",
								"3445",
								"34445",
								"344445",
								"3444445",
								"34444445",
								"701",
								"7001",
								"70001",
								"700001",  //40
								"7000001",
								"70000001",
								"543",
								"5443",
								"54443",
								"544443",
								"5444443",
								"54444443",
								"0707",
								"0000", //50
								"6565",
								"777",
								"7777",
								"5555",
								"1111",
								"3333",
								"66666",
								"22222",
								"6656",
								"7667",
								"6676",
								"3223",
								"4444"}; //63
	//int patron1[] = {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	//int patron2[][]={{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	//int patron3[][]={{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}};
	//int patron4[][]={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0}};
	//int patron5[][]={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{1,1,1,1,1},{0,0,0,0,0}};
	//int patron6[][]={{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
	//int patron7[][]={{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0}};
	//int patron8[][]={{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}};
	/*int patron9[][]={{0,0,0,1,0},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,1,0}};
	int patron10[][]={{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,1}};
	int patron11[][]={{0,0,1,0,0},{0,0,1,0,0},{0,1,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
	int patron12[][]={{0,0,0,0,1},{0,0,0,1,0},{0,0,1,0,0},{0,1,0,0,0},{1,0,0,0,0}};
	int patron13[][]={{0,0,0,0,1},{0,0,0,1,0},{0,0,1,0,0},{0,1,0,0,0},{1,0,0,0,0}};
	int patron14[][]={{0,0,0,0,1},{0,0,0,1,0},{0,1,1,0,0},{1,0,0,0,0},{0,0,0,0,0}};
	int patron15[][]={{0,0,0,0,1},{0,1,1,1,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	int patron16[][]={{0,0,0,1,1},{1,1,1,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	int patron17[][]={{1,1,1,0,0},{0,0,0,1,1},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	int patron18[][]={{1,0,0,0,0},{0,1,1,1,0},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
	int patron19[][]={{1,0,0,0,0},{0,1,0,0,0},{0,0,1,1,0},{0,0,0,0,1},{0,0,0,0,0}};
	int patron20[][]={{1,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1}};
	int patron21[][]={{1,0,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0}};
	int patron22[][]={{1,0,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,0,1,0,0}};
	int patron23[][]={{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{0,1,0,0,0},{0,1,0,0,0}};
	*/
	
	Bitmap mapa, mapaImagen; 
	int vectImagen[], vectComponentes[], contadorComponentes[];
	int R[],G[],B[], RColoreado[], GColoreado[], BColoreado[];                        
	int height,width;
	int aparicionesDireccionFreeman[]; //Guarda el numero de veces que aparece una direccion en la imagen
	ArrayList<Componentes> listaComponentes;
	public MetodosProcesamientoImagenes(Bitmap mapa)
	{
		this.mapa = mapa;
		height = mapa.getHeight();
		width = mapa.getWidth();
		vectImagen = new int[width * height];
		R = new int[width*height];
		G = new int[R.length];
		B = new int[R.length];
		vectComponentes = new int[R.length];
		mapa.getPixels(vectImagen, 0, width, 0, 0, width, height);
		for(int i=0;i<R.length;i++)
		{
			R[i]=255;
			G[i]=255;
			B[i]=255;
		}
	}
	public void descomponerRGB()
	{
		R = new int[vectImagen.length];
		G = new int[vectImagen.length];
		B = new int[vectImagen.length];
		
		for(int i=0 ; i<vectImagen.length ; i++)
		{
			R[i]=(vectImagen[i] & 0x00ff0000)>>16;
			G[i]=(vectImagen[i] & 0x0000ff00)>>8;
			B[i]=(vectImagen[i] & 0x000000ff);
		}
	}
	public void componerRGB()
	{
		for(int i=0;i<vectImagen.length;i++)
		{
			vectImagen[i]=(255<<24)|(R[i]<<16)|(G[i]<<8)| B[i];
		}
	}
	public Bitmap getMapa()
	{
		mapaImagen = Bitmap.createBitmap(vectImagen, width, height, Config.ARGB_8888);
		return mapaImagen;
	}
	public void escalaGrises()
	{
		for(int i=0; i<vectImagen.length;i++)
		{
			R[i]=(R[i]+G[i]+B[i])/3;
			G[i]=R[i];
			B[i]=R[i];
		}
	}
	public void metodoBinarizacion()
	{
		for(int i=0;i<vectImagen.length;i++)
		{
			if(R[i]>190)
			{
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
			else
			{
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}
		}
		
	}
	
	public void componentes4Conectados()
	{
		int contador=0;
		for(int i=0; i <= (height*width)-width; i++)
		{
			vectComponentes[i]=(R[i]==0) ? contador : 0;
			contador++;
		}
		//4 Conectados Adelante y Atras
		while (cuatroConectadosAdelante() != false )
		{
			cuatroConectadosAtras();
		}		
		/*for(int i = 0; i < vectComponentes.length; i++) 
		{
			R[i]=(vectComponentes[i]!=0) ? 0 : 255; //vectComponentes: 0 no componente, !0 es componente
			G[i]=R[i];
			B[i]=R[i];
		}*/
	}
	public void colorearComponentesConvexos()
	{
		
	}
	public void getComponentesLista(int index)
	{
		for(int i=0; i < listaComponentes.get(index).getVectorComponentes().length; i++)
		{
			if(listaComponentes.get(index).getVectorComponentes()[i] == 1 )
			{
				R[i] = 0;
				G[i] = 0;
				B[i] = 0;
			}
			else
			{
				R[i]= 255;
				G[i]= 255;
				B[i]= 255;
			}
		}
	}
	public String getFreeman(int indice)
	{
		return listaComponentes.get(indice).getCadenaFreeman();
	}
	public void llenarLista()
	{
		listaComponentes = new ArrayList<Componentes>(); 
		Componentes objetoLista;
		for(int i=0; i < contadorComponentes.length; i++)
		{
			if(contadorComponentes[i] != 0)
			{
				objetoLista = new Componentes(height, width, patrones.length,patronesFreeman.length); //Crea El Objeto a Insertar a la Lista
				objetoLista.setEtiquetaComponente(contadorComponentes[i]); //Llama a un metodo que guarda la etiqueta del componente i
				objetoLista.setVectorComponentes(vectComponentes);  //Manda la matriz con todos los componentes 
				objetoLista.setCadenaFreeman(); //Manda llamar el metodo de Freeman
				objetoLista.recortarComponentes(); //Recorta el componente
				objetoLista.contarNumeroPixeles(); //Cuenta el numero de pixeles negros en la imagen segmentada
				objetoLista.calcularCentroMasa(); //Calcula los centro de masa de cada imagen segmentada.
				listaComponentes.add(objetoLista); // Se agrega el objeto
			}
		}	
	}
	public int contarComponentesConvexos()
	{
		int contador=0;
		contadorComponentes = new int [1000];
		for(int i=width; i < (vectComponentes.length)-width; i++)
		{
			if(vectComponentes[i]!=0)
			{
				if(buscarEtiqueta(vectComponentes[i],contador) == true)
				{
					contador++;
				}
			}
		}
		return contador;
	}
	public boolean buscarEtiqueta(int etiqueta, int longitud)
	{
		boolean existe = false;
		for(int i = 0; i < longitud; i++)
		{
			if(contadorComponentes[i] == etiqueta)
				existe = true;			
		}
		if(existe == false)
		{
			contadorComponentes[longitud]=etiqueta;
			return true;
		}
		else
			return false;
	}
	
	
	public boolean cuatroConectadosAdelante() //vectComponentes: 0 no componente, !0 es componente
	{
		boolean cambio = false;
		for(int i=width; i < (height*width)-width;i++)
		{
			if(vectComponentes[i] > vectComponentes[i-width] && vectComponentes[i-width]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i]=vectComponentes[i-width];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i-1] && vectComponentes[i-1]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i]=vectComponentes[i-1];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i+width] && vectComponentes[i+width]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i] = vectComponentes[i+width];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i+1] && vectComponentes[i+1]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i] = vectComponentes[i+1];
				cambio = true;
			}
		}
		return cambio;
	}
	public void cuatroConectadosAtras()
	{
		for(int i = (width*height)-width; i > width; i--)
		{
			if(vectComponentes[i] > vectComponentes[i-width] && vectComponentes[i-width]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i]=vectComponentes[i-width];
			}
			if(vectComponentes[i] > vectComponentes[i-1] && vectComponentes[i-1]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i]=vectComponentes[i-1];
			}
			if(vectComponentes[i] > vectComponentes[i+width-1] && vectComponentes[i+width-1]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i] = vectComponentes[i+width];
			}
			if(vectComponentes[i] > vectComponentes[i+1] && vectComponentes[i+1]>0 && vectComponentes[i]>0)
			{
				vectComponentes[i] = vectComponentes[i+1];
			}
		}
	}
	public void empalmarPatrones() 
	{
		int vectorAuxiliar[] = new int[25];
		int componenteNumero=0;
		int k=0;
		boolean iguales;
		do
		{
			for(int stepDown = 0; stepDown < ((height*width)-(5*width)); stepDown = stepDown+(5*width)) //Brinca de 5 en 5 hacia abajo
			{
				for(int stepRight = 0; stepRight < (width-5) ; stepRight = stepRight+5) //Brinca de 5 en 5 hacia la derecha
				{
					for(int i=stepDown;i<stepDown+(5*width);i=i+width)
					{
						for(int j=stepRight; j<stepRight+5;j++)
						{
							//Va obteniendo los 25 caracteres que se van a comparar
							vectorAuxiliar[k] = listaComponentes.get(componenteNumero).getVectorComponentes()[j+i];
							k++;
						}
					}
					k=0;
					
					//Se realizan las comparaciones con los patrones
					for(int i=0; i < patrones.length;i++)
					{
						iguales=true;
						for(int j=0; j < 25; j++)
						{
							if(vectorAuxiliar[j] != patrones[i][j])
							{
								iguales=false;
								break;
							}
						}
						if(iguales) //Si fueron iguales se agrega a un vector de la lista que hubo coincidencia
						{
							listaComponentes.get(componenteNumero).setCoincidencias(i);
						}	
					}
				}
			}
			componenteNumero++;
		}while(componenteNumero<listaComponentes.size());		
	}
	public void compararCadenaFreeman()
	{
		int numeroComponente=0;
		do
		{
			for(int i=0;i<patronesFreeman.length; i++)
			{
				for(int j=0; j<(listaComponentes.get(numeroComponente).getCadenaFreeman().length()); j++)
				{
					if(listaComponentes.get(numeroComponente).getCadenaFreeman().length()-j >= patronesFreeman[i].length())
					{
						if((listaComponentes.get(numeroComponente).getCadenaFreeman().substring(j, j+patronesFreeman[i].length())).compareTo(patronesFreeman[i]) == 0)
						{
							listaComponentes.get(numeroComponente).setCoincidenciasFreeman(i);
						}
					}
				}
			}
			numeroComponente++;
		}while(numeroComponente < listaComponentes.size());
	}
	public void compararCadenaFreemanSaltos() //Metodo para realizar las comparaciones, pero por saltos de la longitud de la cadena a comparar.
	{
		int numeroComponente=0;
		do
		{
			for(int i=0;i<patronesFreeman.length; i++)
			{
				for(int j=0; j<(listaComponentes.get(numeroComponente).getCadenaFreeman().length()); j++)
				{
					if(listaComponentes.get(numeroComponente).getCadenaFreeman().length()-j >= patronesFreeman[i].length())
					{
						if((listaComponentes.get(numeroComponente).getCadenaFreeman().substring(j, j+patronesFreeman[i].length())).compareTo(patronesFreeman[i]) == 0)
						{
							listaComponentes.get(numeroComponente).setCoincidenciasFreeman(i);
							j+=patronesFreeman[i].length();
						}
					}
				}
			}
			numeroComponente++;
		}while(numeroComponente < listaComponentes.size());
	}
	public int getCoincidencias(int index)
	{
		return listaComponentes.get(index).getCoincidencias()[0];
	}
	public int getCoincidenciasFreeman(int componente, int index )
	{
		return listaComponentes.get(componente).getCoincidenciasFreeman()[index];
	}
	public String[] getPatronesFreeman()
	{
		return patronesFreeman;
	}
	public void compararCentrosMasa() //Metodo para comparar los elementos en su centro de masa
	{
		for(int l = 0; l < listaComponentes.size(); l++)
		{
			listaComponentes.get(l).setTamañoDistanciaTanimoto(listaComponentes.size());
			for(int m = 0; m < listaComponentes.size(); m++)
			{
				int escala[] = new int[2];
				escala[0] = listaComponentes.get(l).getCentroMasaX()-listaComponentes.get(m).getCentroMasaX();
				escala[1] = listaComponentes.get(l).getCentroMasaY()-listaComponentes.get(m).getCentroMasaY();
				//Log.d("Centro de Masa", "Aqui empieza");
				//Log.d("Escala",escala[0]+" "+escala[1]);
				//Log.d("Centro de Masa", "Centro Masa O"+ l +" "+listaComponentes.get(l).getCentroMasaX()+" "+listaComponentes.get(l).getCentroMasaY());
				//Log.d("Centro de Masa", "Centro Masa O"+ m +" "+listaComponentes.get(m).getCentroMasaX()+" "+listaComponentes.get(m).getCentroMasaY());
				//Log.d("Centro de masa", listaComponentes.get(l).getAlturaComponenteRecortado()+" "+ listaComponentes.get(m).getAlturaComponenteRecortado());
				//Log.d("Centro de masa", listaComponentes.get(l).getAnchuraComponenteRecortado()+" "+ listaComponentes.get(m).getAnchuraComponenteRecortado());
				
				int numeroCoincidenciasUnion=0;
				
				for(int i=0; i < listaComponentes.get(l).getAlturaComponenteRecortado(); i++ )
				{
					for(int j=0; j < listaComponentes.get(l).getAnchuraComponenteRecortado(); j++)
					{
						if(listaComponentes.get(l).getComponenteRecortado()[(i*listaComponentes.get(l).getAnchuraComponenteRecortado())+j] == 1)
						{
							if((i-escala[1]) >= 0 && (i-escala[1]) < listaComponentes.get(m).getAlturaComponenteRecortado() ) //Si no sobrepasa los limites de los componentes en Y
							{
								if(j-escala[0] >= 0 && (j-escala[0]) < listaComponentes.get(m).getAnchuraComponenteRecortado()) //Si no sobrepasa los limites de los componente en X
								{
									if(listaComponentes.get(m).getComponenteRecortado()[((i-escala[1])*listaComponentes.get(m).getAnchuraComponenteRecortado())+(j-escala[0])] == 1)
									{
										numeroCoincidenciasUnion++;
									}
								}
							}
						}
					}
				}
				//Log.d("Operacion And", numeroCoincidenciasUnion+" "+listaComponentes.get(l).getNumeroPixeles()+" "+listaComponentes.get(m).getNumeroPixeles());
				listaComponentes.get(l).setDistanciaTanimoto((double)(((listaComponentes.get(l).getNumeroPixeles()+listaComponentes.get(m).getNumeroPixeles())-(2*numeroCoincidenciasUnion)))/(double)(((listaComponentes.get(l).getNumeroPixeles()+listaComponentes.get(m).getNumeroPixeles())-numeroCoincidenciasUnion)), m);
				//Log.d("Distancia Tanimoto", String.format("Distancia %.6f",listaComponentes.get(l).getDistanciaTanimoto(m)));
			}
		}
	}
	public double getDistanciaTanimoto(int componente, int indexComponente)
	{
		return listaComponentes.get(componente).getDistanciaTanimoto(indexComponente);
	}
}
