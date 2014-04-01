package procesamiento;

import android.util.Log;

public class Componentes {
	
	private int etiquetaComponente, height, width;
	private String cadenaFreeman="";
	private int vectorComponentes[];
	
	final int DERECHA = 0;
	final int DERECHA_ARRIBA = 1;
	final int ARRIBA = 2;
	final int ARRIBA_IZQUIERDA = 3;
	final int IZQUIERDA = 4;
	final int IZQUIERDA_ABAJO = 5;
	final int ABAJO = 6;
	final int ABAJO_DERECHA = 7;
	
	public Componentes(int height, int width)
	{
		this.height = height;
		this.width = width;
	}
	public void setEtiquetaComponente(int etiqueta)
	{
		etiquetaComponente = etiqueta;
	}
	public int getEtiquetaComponente()
	{
		return etiquetaComponente;
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
}
