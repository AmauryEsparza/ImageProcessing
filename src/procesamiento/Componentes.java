package procesamiento;

import android.util.Log;

public class Componentes {
	
	private int etiquetaComponente, height, width;
	private String cadenaFreeman="";
	private int vectorComponentes[];
	private int vectorCoincidencias[];
	private int vectorCoincidenciasFreeman[];
	private int numeroPixeles;
	private int vectorComponenteRecortado[];
	private int limiteX, limiteY, limiteInferiorX=1000000, limiteInferiorY=1000000;
	private int centroMasaX, centroMasaY;
	final int DERECHA = 0;
	final int DERECHA_ARRIBA = 1;
	final int ARRIBA = 2;
	final int ARRIBA_IZQUIERDA = 3;
	final int IZQUIERDA = 4;
	final int IZQUIERDA_ABAJO = 5;
	final int ABAJO = 6;
	final int ABAJO_DERECHA = 7;
	private double[] distanciaTanimoto;
	int puntoMinimoX=1000000, puntoMinimoY=1000000;
	
	public Componentes(int height, int width, int longitudPatrones,int numeroPatronesFreeman)
	{
		this.height = height;
		this.width = width;
		vectorCoincidencias = new int[longitudPatrones];
		vectorCoincidenciasFreeman = new int[numeroPatronesFreeman];
	}
	public void setEtiquetaComponente(int etiqueta)
	{
		etiquetaComponente = etiqueta;
	}
	public int getEtiquetaComponente()
	{
		return etiquetaComponente;
	}
	public int getHeight()
	{
		return height;
	}
	public int getWidth()
	{
		return width;
	}
	/*public void recortarComponente()
	{
		int inicio, fin;
		for(int i=0; i < vectorComponentes.length; i++)
		{
			if(vectorComponentes[i] != 0)
			{
				inicio = i;
			}
		}
		int[] vectorComponenteRecortado = new
	}*/
	public void setVectorComponentes(int[] vectorComponentes)
	{
			this.vectorComponentes = new int[vectorComponentes.length];
			for(int i=0; i < vectorComponentes.length; i++)
			{
				if(vectorComponentes[i] == etiquetaComponente)
				{
					this.vectorComponentes[i]=1; 
				}
				else
				{
					this.vectorComponentes[i]=0;
				}
			}
	}
	public int[] getVectorComponentes()
	{
		return vectorComponentes;
	}
	public void setCadenaFreeman()
	{
		int x=0,i,direccion;
		cadenaFreeman = new String();
		for(i=width; i < (vectorComponentes.length) - width; i++)
		{
			if(vectorComponentes[i] != 0 && i > width)
			{
				x=i; //Primer posicion
				break;
			}	
		}
		i=x;
		direccion=DERECHA;
		do
		{
			switch(direccion)
			{
				case 0:
					if(vectorComponentes[i-width] != 0 && i > width)
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1]!= 0 && i > width)
					{
						direccion = DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0)
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-(width-1)))
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0 && i < ((width*height)-width))
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0 && i < (height*width)-width) 
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i-1+width;
					}
					else if(vectorComponentes[i-1] != 0) 
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1) 
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					break;
				case 1:
					if(vectorComponentes[i-width-1] != 0 && i > width)
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width]!= 0 && i > width)
					{
						direccion = ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0 && i > width)
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0 )
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-width))
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0) 
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0) 
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0) 
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					break;
				case 2:
					if(vectorComponentes[i-1] != 0)
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1)
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width)
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0 && i > width)
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+1] != 0)
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-width)-1) 
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0 && i < ((width*height)-width)) 
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0 && i < ((width*height)-width)) 
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					break;
				case 3:
					if(vectorComponentes[i+width-1] != 0)
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0)
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1)
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width)
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0 && i > width	)
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0 ) 
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-width)-1) 
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0 && i < ((width*height)-width)) 
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					break;
				case 4:
					if(vectorComponentes[i+width] != 0 && i < ((width*height)-width))
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0)
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0)
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1)
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width)
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0 && i > width)
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0) 
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < (width*height)-(width-1) ) 
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					break;
				case 5:
					if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-(width-1)))
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0)
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0)
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0)
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1)
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width)
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0) 
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0) 
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					break;
				case 6:
					if(vectorComponentes[i+1] != 0)
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < ((width*height)-(width-1)))
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0)
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0 && i > width) 
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0) 
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1 ) 
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width ) 
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					else if(vectorComponentes[i-width+1] != 0 && i > width ) 
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					break;
				case 7:
					if(vectorComponentes[i-width+1] != 0)
					{
						direccion=DERECHA_ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width+1;
					}
					else if(vectorComponentes[i+1] != 0)
					{
						direccion=DERECHA;
						cadenaFreeman+=direccion;
						i=i+1;
					}
					else if(vectorComponentes[i+width+1] != 0 && i < (height*width)-(width-1))
					{
						direccion=ABAJO_DERECHA;
						cadenaFreeman+=direccion;
						i=i+width+1;
					}
					else if(vectorComponentes[i+width] != 0 && i < (height*width)-width) 
					{
						direccion=ABAJO;
						cadenaFreeman+=direccion;
						i=i+width;
					}
					else if(vectorComponentes[i+width-1] != 0) 
					{
						direccion=IZQUIERDA_ABAJO;
						cadenaFreeman+=direccion;
						i=i+width-1;
					}
					else if(vectorComponentes[i-1] != 0) 
					{
						direccion=IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-1;
					}
					else if(vectorComponentes[i-width-1] != 0 && i > width+1 ) 
					{
						direccion=ARRIBA_IZQUIERDA;
						cadenaFreeman+=direccion;
						i=i-width-1;
					}
					else if(vectorComponentes[i-width] != 0 && i > width ) 
					{
						direccion=ARRIBA;
						cadenaFreeman+=direccion;
						i=i-width;
					}
					break;
			}
		}while(i != x);
	}
	public String getCadenaFreeman()
	{
		return cadenaFreeman;
	}
	public void setCoincidencias(int index)
	{
		vectorCoincidencias[index]++;
	}
	public int[] getCoincidencias()
	{
		return vectorCoincidencias;
	}
	public void setCoincidenciasFreeman(int index)
	{
		vectorCoincidenciasFreeman[index]++;
	}
	public int[] getCoincidenciasFreeman()
	{
		return vectorCoincidenciasFreeman;
	}
	public void contarNumeroPixeles() //Cuenta el numero de pixeles del objeto en la lista
	{
		numeroPixeles = 0;
		for(int i=0; i<vectorComponenteRecortado.length; i++)
		{
			if(vectorComponenteRecortado[i] == 1)
				numeroPixeles++;
		}
		//Log.d("Numero de Pixeles", numeroPixeles+" ");
	}
	public int getNumeroPixeles()
	{
		return numeroPixeles;
	}
	public void calcularCentroMasa() //Calcula el centro de masa 
	{
		int sumaX=0, sumaY=0;
		for(int i=0; i < limiteY; i++)
		{
			for(int j=0 ; j < limiteX; j++)
			{
				if(vectorComponenteRecortado[(i*limiteX)+j] != 0)
				{
					sumaX += j;
					sumaY += i;
				}
			}
		}
		centroMasaX=sumaX/numeroPixeles;
		centroMasaY=sumaY/numeroPixeles;
		//Log.d("Centro de Masa", sumaX/numeroPixeles+" "+sumaY/numeroPixeles);
	}
	public int getCentroMasaX()
	{
		return centroMasaX;
	}
	public int getCentroMasaY()
	{
		return centroMasaY;
	}
	public void setLimitesComponente()
	{
		int auxX=0,auxY=0;
		limiteX=1;limiteY=1;
		for(int i=0; i < height; i++)
		{
			for(int j=0; j < width; j++)
			{
				if(vectorComponentes[(i*width)+j] != 0)
				{
					if (puntoMinimoY > i)
					{
						puntoMinimoY = i; //Punto más chico en el eje Y
						limiteY++;
					}
					if (puntoMinimoX > j)
					{
						puntoMinimoX= j; //Punto más chico en el eje X
						limiteX++;
					}
					if (j > auxX) //Si donde hay un pixel negro la coordenada es mayor a la antes calculada
					{
						auxX=j;
						limiteX++;
					}
					if (i > auxY)
					{
						auxY=i;
						limiteY++;
					}
				}
			}
		}
	}
	public void recortarComponentes()
	{
		int inicio=0;
		setLimitesComponente();
		//Log.d("Limites","Limite(X,Y) "+limiteX+" "+limiteY);
		vectorComponenteRecortado = new int[limiteX*limiteY];
		/*for(int i=0; i<height*width; i++)
		{
			if(vectorComponentes[i]!=0)
			{
				inicio=i; 
				Log.d("Inicio de Recorrido ", "Inicio: "+ inicio);
				break;
			}
		}*/
		for(int i=0; i < limiteY; i++)
		{
			for(int j=0; j < limiteX; j++)
			{
				vectorComponenteRecortado[(i*limiteX)+j] = vectorComponentes[(((puntoMinimoY*width)+puntoMinimoX)+(i*width))+j];
			}
		}
	}
	public int[] getComponenteRecortado() 
	{
		return vectorComponenteRecortado;
	}
	public int getAnchuraComponenteRecortado()
	{
		return limiteX;
	}
	public int getAlturaComponenteRecortado()
	{
		return limiteY;
	}
	public void setTamañoDistanciaTanimoto(int longitudLista) //Se declara el vector de coincidencias
	{
		distanciaTanimoto = new double[longitudLista];
	}
	public void setDistanciaTanimoto(double valor, int componente) 
	{
		distanciaTanimoto[componente] = valor;
	}
	public double getDistanciaTanimoto(int indexComponente)
	{
		return distanciaTanimoto[indexComponente];
	}
	
}
