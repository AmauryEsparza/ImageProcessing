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
	
	Bitmap mapa, mapaImagen; 
	int vectImagen[], vectComponentes[], contadorComponentes[];
	int R[],G[],B[], RColoreado[], GColoreado[], BColoreado[];                        
	int height,width;
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
			if(R[i]>100)
			{
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}
			else
			{
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
		}
		
	}
	
	public String metodoFreeman()
	{
		String cadenaFreeman="";
		int x=0,i;
		for(i=0; i < (R.length-width)-1; i++)
		{
			if(R[i] != 255)
			{
				x=i; //Primer posicion
				break;
			}
			
		}
		i=x;
		do
		{
			switch(x)
			{
				case 0:
					if(R[i-width] != 0)
					{
						x=UP;
						cadenaFreeman+=x;
						i=i-width;
					}
					else if(R[i+1] != 0)
					{
						x=RIGHT;
						cadenaFreeman+=x;
						i=i+1;
					}
					else if(R[i+width] != 0)
					{
						x=DOWN;
						cadenaFreeman+=x;
						i=i+width;
					}
					else if(R[i-1] != 0) 
					{
						x=LEFT;
						cadenaFreeman+=x;
						i=i-1;
					}
				case 1:
					if(R[i-1] != 0)
					{
						x=LEFT;
						cadenaFreeman+=x;
						i=i-1;
					}
					else if(R[i-width] != 0)
					{
						x=UP;
						cadenaFreeman+=x;
						i=i-width;
					}
					else if(R[i+1] != 0)
					{
						x=RIGHT;
						cadenaFreeman+=x;
						i=i+1;
					}
					else if(R[i+width] != 0) 
					{
						x=DOWN;
						cadenaFreeman+=x;
						i=i+width;
					}
				case 2:
					if(R[i+width] != 0)
					{
						x=DOWN;
						cadenaFreeman+=x;
						i=i+width;
					}
					else if(R[i-1] != 0)
					{
						x=LEFT;
						cadenaFreeman+=x;
						i=i-1;
					}
					else if(R[i-width] != 0)
					{
						x=UP;
						cadenaFreeman+=x;
						i=i-width;
					}
					else if(R[i+1] != 0) 
					{
						x=RIGHT;
						cadenaFreeman+=x;
						i=i+1;
					}
				case 3:
					if(R[i+1] != 0)
					{
						x=RIGHT;
						cadenaFreeman+=x;
						i=i+1;
					}
					else if(R[i+width] != 0)
					{
						x=DOWN;
						cadenaFreeman+=x;
						i=i+width;
					}
					else if(R[i-1] != 0)
					{
						x=LEFT;
						cadenaFreeman+=x;
						i=i-1;
					}
					else if(R[i-width] != 0) 
					{
						x=UP;
						cadenaFreeman+=x;
						i=i-width;
					}
			}
		}while(i!=x);
		return cadenaFreeman;
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
	public void getComponentesLista()
	{
		for(int i=0; i < listaComponentes.get(3).getVectorComponentes().length; i++)
		{
			if(listaComponentes.get(3).getVectorComponentes()[i] == 1 )
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
	public void llenarLista()
	{
		listaComponentes = new ArrayList<Componentes>(); 
		Componentes objetoLista;
		for(int i=0; i < contadorComponentes.length; i++)
		{
			if(contadorComponentes[i] != 0)
			{
				objetoLista = new Componentes();
				objetoLista.setEtiquetaComponente(contadorComponentes[i]);
				objetoLista.setVectorComponentes(vectComponentes); // AQUIIIIIIIIIII!!!!!!
				listaComponentes.add(objetoLista);
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
}
