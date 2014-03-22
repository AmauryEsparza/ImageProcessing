package procesamiento;
import android.graphics.Bitmap;
import java.lang.Math;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
public class MetodosProcesamiento 
{
	public int matImagen[][];
	public int vectImagen[], vectBinaria[], vectConvolucion[];
	//int R[][],G[][],B[][];
	int R[],G[],B[];
	int height,width;
	
	Bitmap mapaImagen;
	public MetodosProcesamiento(Bitmap mapa) 
	{
		/* Using Matrix
		matImagen=new int[mapa.getWidth()][mapa.getHeight()];
		for(int i=0;i<mapa.getHeight();i++)
		{
			for(int j=0;j<mapa.getWidth();j++)
			{
				matImagen[j][i]=mapa.getPixel(j, i);
			}
		}*/
		// Using Vector
		height = mapa.getHeight();
		width = mapa.getWidth();
		vectImagen = new int[width * height];
		mapa.getPixels(vectImagen, 0, width, 0, 0, width, height);
	}
	public void descomponerRGB() 
	{
		/*Using Matrix
		R=new int[matImagen.length][matImagen[0].length];
		G=new int[matImagen.length][matImagen[0].length];
		B=new int[matImagen.length][matImagen[0].length];
		
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				R[j][i]=(matImagen[j][i] & 0x00ff0000)>>16;
				G[j][i]=(matImagen[j][i] & 0x0000ff00)>>8;
				B[j][i]=(matImagen[j][i] & 0x000000ff);
			}
		}
		*/
		//Using Array
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
		/*Using Matrix
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				matImagen[j][i]=(255<<24)|(R[j][i]<<16)|(G[j][i]<<8)|(B[j][i]);
			}
		}
		*/
		//Using Array
		for(int i=0;i<vectImagen.length;i++)
		{
			vectImagen[i]=(255<<24)|(R[i]<<16)|(G[i]<<8)| B[i];
		}
	}
	public void escalaGrises()
	{
		/*Using Matrix
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				R[j][i]=(R[j][i]+G[j][i]+B[j][i])/3;
				G[j][i]=R[j][i];
				B[j][i]=R[j][i];
			}
		
		}
		*/
		//Using Array
		for(int i=0 ; i<vectImagen.length ; i++)
		{
			R[i]=(R[i]+G[i]+B[i])/3;
			G[i]=R[i];
			B[i]=R[i];
		}
	}
	public void metodoRoberts()
	{
		
		/* Using Matrix
		int auxiliar;
		for(int i=0;i<matImagen[0].length-1;i++)
		{
			for(int j=0;j<matImagen.length-1;j++)
			{
				auxiliar=(int) Math.sqrt(Math.pow(R[j][i]-R[j+1][i+1],2)+Math.pow(R[j+1][i]-R[j][i+1],2));
				R[j][i]=(auxiliar>255) ? 255 : auxiliar;
				G[j][i]=(auxiliar>255) ? 255 : auxiliar;
				B[j][i]=(auxiliar>255) ? 255 : auxiliar;
				
			}
		}
		*/
		//Using Vector
		int auxiliar;
		for(int i=0 ; i<vectImagen.length-width-1 ; i++)
		{
			auxiliar=(int) Math.sqrt(Math.pow(R[i]-R[i+width+1],2)+Math.pow(R[i+1]-R[i+width],2));
			R[i] = (auxiliar>255) ? 255 : auxiliar;
			G[i] = (auxiliar>255) ? 255 : auxiliar;
			B[i] = (auxiliar>255) ? 255 : auxiliar;
		}
		
	}
	
	
	public Bitmap getMapa()
	{
		/* Using Matrix
		mapaImagen = Bitmap.createBitmap(matImagen.length, matImagen[0].length, Config.ARGB_8888);
		for (int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				//System.out.println("Entro");
				mapaImagen.setPixel(j, i, matImagen[j][i]);
			}
		}
		return mapaImagen;
		*/
		mapaImagen = Bitmap.createBitmap(vectImagen, width, height, Config.ARGB_8888);
		return mapaImagen;
		
	}
	public void metodoConvolucion(int vectMultiplicante[])
	{
		vectConvolucion= new int[height*width];
		int auxiliarHeight=width;
		int sumaR=0;
		int sumaG=0;
		int sumaB=0;
		for(int i=width+1; i<((height*width)-width)-1; i++)
		{
			if(i%width==0)
			{
				sumaR=((R[i]*vectMultiplicante[4])+(R[i-width]*vectMultiplicante[1])+(R[i-width-1]*vectMultiplicante[0])+(R[i-1]*vectMultiplicante[3])+(R[i+width-1]*vectMultiplicante[6])+(R[i+width]*vectMultiplicante[7]))/9;
				sumaG=((G[i]*vectMultiplicante[4])+(G[i-width]*vectMultiplicante[1])+(G[i-width-1]*vectMultiplicante[0])+(G[i-1]*vectMultiplicante[3])+(G[i+width-1]*vectMultiplicante[6])+(G[i+width]*vectMultiplicante[7]))/9;
				sumaB=((B[i]*vectMultiplicante[4])+(B[i-width]*vectMultiplicante[1])+(B[i-width-1]*vectMultiplicante[0])+(B[i-1]*vectMultiplicante[3])+(B[i+width-1]*vectMultiplicante[6])+(B[i+width]*vectMultiplicante[7]))/9;
				//vectConvolucion[i]=((vectImagen[i]*vectMultiplicante[4])+(vectImagen[i-height]*vectMultiplicante[1])+(vectImagen[i-height-1]*vectMultiplicante[0])+(vectImagen[i-1]*vectMultiplicante[3])+(vectImagen[i+height-1]*vectMultiplicante[6])+(vectImagen[i+height]*vectMultiplicante[7]))/9;
				auxiliarHeight=width+1;
			}
			else if(i==auxiliarHeight)
			{
				//vectConvolucion[i]=((vectImagen[i]*vectMultiplicante[3])+(vectImagen[i-height]*vectMultiplicante[0])+(vectImagen[i-height+1]*vectMultiplicante[1])+(vectImagen[i+1]*vectMultiplicante[4])+(vectImagen[i+height+1]*vectMultiplicante[7])+(vectImagen[i+height]*vectMultiplicante[6]))/9;
				sumaR=((R[i]*vectMultiplicante[3])+(R[i-width]*vectMultiplicante[0])+(R[i-width-1]*vectMultiplicante[1])+(R[i-1]*vectMultiplicante[4])+(R[i+width-1]*vectMultiplicante[7])+(R[i+width]*vectMultiplicante[6]))/9;
				sumaG=((G[i]*vectMultiplicante[3])+(G[i-width]*vectMultiplicante[0])+(G[i-width-1]*vectMultiplicante[1])+(G[i-1]*vectMultiplicante[4])+(G[i+width-1]*vectMultiplicante[7])+(G[i+width]*vectMultiplicante[6]))/9;
				sumaB=((B[i]*vectMultiplicante[3])+(B[i-width]*vectMultiplicante[0])+(B[i-width-1]*vectMultiplicante[1])+(B[i-1]*vectMultiplicante[4])+(B[i+width-1]*vectMultiplicante[7])+(B[i+width]*vectMultiplicante[6]))/9;
			}
			else
			{
				//vectConvolucion[i]=((vectImagen[i]*vectMultiplicante[4])+(vectImagen[i-height-1]*vectMultiplicante[0])+(vectImagen[i-height]*vectMultiplicante[1])+(vectImagen[i-height+1]*vectMultiplicante[2])+(vectImagen[i-1]*vectMultiplicante[3])+(vectImagen[i+1]*vectMultiplicante[5])+(vectImagen[i+height-1]*vectMultiplicante[6])+(vectImagen[i+height]*vectMultiplicante[7])+ vectImagen[i+height+1]*vectMultiplicante[8])/9;
				sumaR += R[i]*vectMultiplicante[4];
				sumaR += R[i-width-1]*vectMultiplicante[0];
				sumaR += R[i-width]*vectMultiplicante[1];
				sumaR += R[i-width+1]*vectMultiplicante[2];
				sumaR += R[i-1]*vectMultiplicante[3];
				sumaR += R[i+1]*vectMultiplicante[5];
				sumaR += R[i+width-1]*vectMultiplicante[6];
				sumaR += R[i+width]*vectMultiplicante[7];
				sumaR += R[i+width+1]*vectMultiplicante[8];
				sumaR = sumaR/9;
				sumaG += G[i]*vectMultiplicante[4];
				sumaG += G[i-width-1]*vectMultiplicante[0];
				sumaG += G[i-width]*vectMultiplicante[1];
				sumaG += G[i-width+1]*vectMultiplicante[2];
				sumaG += G[i-1]*vectMultiplicante[3];
				sumaG += G[i+1]*vectMultiplicante[5];
				sumaG += G[i+width-1]*vectMultiplicante[6];
				sumaG += G[i+width]*vectMultiplicante[7];
				sumaG += G[i+width+1]*vectMultiplicante[8];
				sumaG = sumaG/9;
				sumaB += B[i]*vectMultiplicante[4];
				sumaB += B[i-width-1]*vectMultiplicante[0];
				sumaB += B[i-width]*vectMultiplicante[1];
				sumaB += B[i-width+1]*vectMultiplicante[2];
				sumaB += B[i-1]*vectMultiplicante[3];
				sumaB += B[i+1]*vectMultiplicante[5];
				sumaB += B[i+width-1]*vectMultiplicante[6];
				sumaB += B[i+width]*vectMultiplicante[7];
				sumaB += B[i+width+1]*vectMultiplicante[8];
				sumaB = sumaB/9;
				//sumaB = ((G[i]*vectMultiplicante[4])+(G[i-height-1]*vectMultiplicante[0])+(G[i-height]*vectMultiplicante[1])+(G[i-height+1]*vectMultiplicante[2])+(G[i-1]*vectMultiplicante[3])+(G[i+1]*vectMultiplicante[5])+(G[i+height-1]*vectMultiplicante[6])+(G[i+height]*vectMultiplicante[7])+(G[i+height+1]*vectMultiplicante[8]))/9;
				//sumaB=((B[i]*vectMultiplicante[4])+(B[i-height-1]*vectMultiplicante[0])+(B[i-height]*vectMultiplicante[1])+(B[i-height+1]*vectMultiplicante[2])+(B[i-1]*vectMultiplicante[3])+(B[i+1]*vectMultiplicante[5])+(B[i+height-1]*vectMultiplicante[6])+(B[i+height]*vectMultiplicante[7])+(B[i+height+1]*vectMultiplicante[8]))/9;
				
			}
			vectConvolucion[i]=(255 << 24 | sumaR << 16 | sumaG << 8 | sumaB);
			
			sumaR=0;
			sumaG=0;
			sumaB=0;
		}
		//for(int i=height+1;i<height*width-1;i++)
			//vectImagen[i]=0;
		for(int i=0; i<(height*width)-1; i++)
			vectImagen[i]=vectConvolucion[i];
		
	}
	public void metodoBinarizacion(int umbral)
	{
		vectBinaria = new int[vectImagen.length];
		for(int i=0;i<height*width-1;i++)
		{
			if(R[i]>umbral)
			{
				vectBinaria[i]=1;
				R[i]=255;
				G[i]=255;
				B[i]=255;
			}
			else
			{
				vectBinaria[i]=0;
				R[i]=0;
				G[i]=0;
				B[i]=0;
			}
		}
	}
	
}
