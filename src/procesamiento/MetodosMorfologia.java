package procesamiento;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class MetodosMorfologia {
	
	Bitmap mapa1, mapa2, mapaImagen;
	int R1[],G1[],B1[],R2[],G2[],B2[],vectImagen[], vectImagen2[], vectEmpalmado[], vectComponentes[];
	int R[],G[],B[], heightTotal, widthTotal;
	int height1,width1, height2,width2;
	public MetodosMorfologia(Bitmap mapa1, Bitmap mapa2)
	{
		this.mapa1 = mapa1;
		this.mapa2 = mapa2;
		height1 = mapa1.getHeight();
		width1 = mapa1.getWidth();
		height2 = mapa2.getHeight();
		width2 = mapa2.getWidth();
		vectImagen = new int[width1 * height1];
		vectImagen2 = new int[width2 * height2];
		if(width1>=width2)
		{
			if(height1>=height2)
			{
				R = new int[width1*height1];
				G = new int[R.length];
				B = new int[R.length];
				heightTotal = height1;
			}
			else
			{
				R = new int[width1*height2];
				G = new int[R.length];
				B = new int[R.length];
				heightTotal=height2;
			}
			widthTotal=width1;
		}
		else
		{
			if(height1>=height2)
			{
				R = new int[width2*height1];
				G = new int[R.length];
				B = new int[R.length];
				heightTotal=height1;
			}
			else
			{
				R = new int[width2*height2];
				G = new int[R.length];
				B = new int[R.length];
				heightTotal=height2;
			}
			widthTotal = width2;
		}
		vectEmpalmado = new int[R.length];
		mapa1.getPixels(vectImagen, 0, width1, 0, 0, width1, height1);
		mapa2.getPixels(vectImagen2, 0, width2, 0, 0, width2, height2);
		for(int i=0;i<R.length;i++)
		{
			R[i]=255;
			G[i]=255;
			B[i]=255;
		}
	}
	public void descomponerRGB()
	{
		R1 = new int[vectImagen.length];
		G1 = new int[vectImagen.length];
		B1 = new int[vectImagen.length];
		
		R2 = new int[vectImagen2.length];
		G2 = new int[vectImagen2.length];
		B2 = new int[vectImagen2.length];
		for(int i=0 ; i<vectImagen.length ; i++)
		{
			R1[i]=(vectImagen[i] & 0x00ff0000)>>16;
			G1[i]=(vectImagen[i] & 0x0000ff00)>>8;
			B1[i]=(vectImagen[i] & 0x000000ff);
		}
		for(int i=0 ; i<vectImagen2.length ; i++)
		{
			R2[i]=(vectImagen2[i] & 0x00ff0000)>>16;
			G2[i]=(vectImagen2[i] & 0x0000ff00)>>8;
			B2[i]=(vectImagen2[i] & 0x000000ff);
		}
	}
	public void componerRGB()
	{
		vectEmpalmado = new int[R.length];
		for(int i=0;i<vectEmpalmado.length;i++)
		{
			vectEmpalmado[i]=(255<<24)|(R[i]<<16)|(G[i]<<8)| B[i];
		}
	}
	public Bitmap getMapa()
	{
		mapaImagen = Bitmap.createBitmap(vectEmpalmado, width1, height1, Config.ARGB_8888);
		return mapaImagen;
	}
	public void escalaGrises()
	{
		for(int i=0; i<vectImagen.length;i++)
		{
			R1[i]=(R1[i]+G1[i]+B1[i])/3;
			G1[i]=R1[i];
			B1[i]=R1[i];
		}
		for(int i=0; i<vectImagen2.length;i++)
		{
			R2[i]=(R2[i]+G2[i]+B2[i])/3;
			G2[i]=R2[i];
			B2[i]=R2[i];
		}
	}
	public void metodoBinarizacion()
	{
		for(int i=0;i<vectImagen.length;i++)
		{
			if(R1[i]>128)
			{
				R1[i]=255;
				G1[i]=255;
				B1[i]=255;
			}
			else
			{
				R1[i]=0;
				G1[i]=0;
				B1[i]=0;
			}
		}
		for(int i=0;i<vectImagen2.length;i++)
		{
			if(R2[i]>128)
			{
				R2[i]=255;
				G2[i]=255;
				B2[i]=255;
			}
			else
			{
				R2[i]=0;
				G2[i]=0;
				B2[i]=0;
			}
		}
	}
	public void metodoAnd()
	{
		vectEmpalmado = new int[vectImagen.length];
		int x1=0,x2=0;
		for(int i=0;i<vectImagen.length;i++) //Obtengo donde comienza la imagen 1
		{
			if(R1[i]==0)
			{
				x1=i;
				break;
			}
		}
		for(int i=0;i<vectImagen2.length;i++) //Obtengo donde comienza la imagen 2
		{
			if(R2[i]==0)
			{
				x2=i;
				break;
			}
		}
		for(int i=x1; i<R1.length; i++) //Paso la Imagen 1
		{
			if(R1[i]!=0)
			{
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
			else if(R1[i]==0)
			{
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}			
		}
		for(int i=x2; i<R2.length;i++) //Paso la Imagen 2 y compruebo que sean blancos
		{
			if(R2[x1]==0 && R[i]==0)
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
			x1++;
		}
	}
	public void metodoEmpalmar()
	{
		vectEmpalmado = new int[vectImagen.length];
		int x1=0,x2=0;
		for(int i=0;i<vectImagen.length;i++) //Obtengo donde comienza la imagen 1
		{
			if(R1[i]==0)
			{
				x1=i;
				break;
			}
		}
		for(int i=0;i<vectImagen2.length;i++) //Obtengo donde comienza la imagen 2
		{
			if(R2[i]==0)
			{
				x2=i;
				break;
			}
		}
		for(int i=x1; i<R1.length; i++) //Paso la Imagen 1
		{
			if(R1[i]!=0)
			{
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
			else if(R1[i]==0)
			{
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}			
		}
		for(int i=x2; i<R2.length;i++) //Paso la Imagen 2 y compruebo que sean blancos
		{
			if(R2[x1]!=0 && R[i]==255)
			{
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
			else if(R2[x1]==0 && R[i]!=0)
			{
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}
			x1++;
		}
	}

	public void metodoDilatacion(int [] elementoEstructurante, int heightEstructurante, int widthEstructurante)
	{
		R = new int[height1*width1];
		G = new int[height1*width1];
		B = new int[height1*width1];
		for(int i=0;i<R.length;i++)
		{
			R[i]=255;
			G[i]=255;
			B[i]=255;
		}
		for(int i=0;i < R1.length-width1;i++)
		{
			if(R1[i]==0)
			{
				for(int j=0; j < widthEstructurante; j++)
				{
					for(int k=0; k < heightEstructurante; k++)
					{
						if(i+j+(width1*k) <= width1*height1)
						{
							R[i+j+(width1*k)] = (elementoEstructurante[j+(widthEstructurante*k)] == 1 ? 0 : 255);
							G[i+j+(width1*k)] = (elementoEstructurante[j+(widthEstructurante*k)] == 1 ? 0 : 255);
							B[i+j+(width1*k)] = (elementoEstructurante[j+(widthEstructurante*k)] == 1 ? 0 : 255);
						}
					}
				}
				
				/*R[i] = 0;
				G[i]=0;
				B[i]=0;
				R[i+width1]=0;
				G[i+width1]=0;
				B[i+width1]=0;*/
			
				//for(int j=0;j<=elementoEstructurante.length-widthEstructurante;j++)

				/*	for(int j=0;j<=widthEstructurante;j++)
				{
					R[i]=(elementoEstructurante[j]==0) ? 255 : 0;
					G[i]=(elementoEstructurante[j]==0) ? 255 : 0;
					B[i]=(elementoEstructurante[j]==0) ? 255 : 0;
					R[i+width1]=(elementoEstructurante[j+widthEstructurante]==0) ? 255 : 0;
					G[i+width1]=(elementoEstructurante[j+widthEstructurante]==0) ? 255 : 0;
					B[i+width1]=(elementoEstructurante[j+widthEstructurante]==0) ? 255 : 0;
				}*/
			}
		}
	}
	public void metodoFreeman()
	{
		for(int i=0; i<R1.length; i++)
		{
			
		}
	
	}
	public void componentes4Conectados()
	{
		int contador=0;
		vectComponentes = new int[R1.length];
		for(int i=0; i <= (height1*width1)-width1; i++)
		{
			vectComponentes[i]=(R1[i]==0) ? contador : 0;
			contador++;
		}
		//4 Conectados Adelante y Atras
		while (cuatroConectadosAdelante() != true)
		{
			cuatroConectadosAtras();
		}
		for(int i=0; i<(height1*width1)-width1; i++)
		{
			R[i]=R1[i];
			G[i]=G1[i];
			B[i]=B1[i];
		}
	}
	
	
	public boolean cuatroConectadosAdelante()
	{
		boolean cambio = false;
		for(int i=width1+1; i <= (height1*width1)-width1;i++)
		{
			if(vectComponentes[i] > vectComponentes[i-width1] && vectComponentes[i-width1]>0)
			{
				vectComponentes[i]=vectComponentes[i-width1];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i-1] && vectComponentes[i-1]>0)
			{
				vectComponentes[i]=vectComponentes[i-1];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i+width1-1] && vectComponentes[i+width1-1]>0)
			{
				vectComponentes[i] = vectComponentes[i+width1];
				cambio = true;
			}
			if(vectComponentes[i] > vectComponentes[i+1] && vectComponentes[i+1]>0)
			{
				vectComponentes[i] = vectComponentes[i+1];
				cambio = true;
			}
		}
		return cambio;
	}
	public void cuatroConectadosAtras()
	{
		for(int i = (width1*height1)-width1; i > width1+1; i--)
		{
			if(vectComponentes[i] > vectComponentes[i-width1] && vectComponentes[i-width1]>0)
				vectComponentes[i]=vectComponentes[i-width1];
				
			if(vectComponentes[i] > vectComponentes[i-1] && vectComponentes[i-1]>0)
				vectComponentes[i]=vectComponentes[i-1];
					
			if(vectComponentes[i] > vectComponentes[i+width1-1] && vectComponentes[i+width1-1]>0)
				vectComponentes[i] = vectComponentes[i+width1];
			
			if(vectComponentes[i] > vectComponentes[i+1] && vectComponentes[i+1]>0)
				vectComponentes[i] = vectComponentes[i+1];
	
		}
	}
	
}
