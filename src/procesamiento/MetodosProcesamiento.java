package procesamiento;
import android.graphics.Bitmap;
import java.lang.Math;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
public class MetodosProcesamiento 
{
	public int matImagen[][];
	public int vectImagen[];
	int R[][],G[][],B[][];
	Bitmap mapaImagen;
	public MetodosProcesamiento(Bitmap mapa)
	{
		matImagen=new int[mapa.getWidth()][mapa.getHeight()];
		vectImagen= new int[mapa.getWidth() * mapa.getHeight()];
		for(int i=0;i<mapa.getHeight();i++)
		{
			for(int j=0;j<mapa.getWidth();j++)
			{
				matImagen[j][i]=mapa.getPixel(j, i);
			}
		}
	}
	public void descomponerRGB() 
	{
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
		
	}
	public void componerRGB()
	{
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				matImagen[j][i]=(255<<24)|(R[j][i]<<16)|(G[j][i]<<8)|(B[j][i]);
			}
		}
	}
	public void escalaGrises()
	{
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				R[j][i]=(R[j][i]+G[j][i]+B[j][i])/3;
				G[j][i]=R[j][i];
				B[j][i]=R[j][i];
			}
		
		}
	}
	public void metodoRoberts()
	{
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
	}
	
	
	public Bitmap getMapa()
	{
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
	}
	
	
}
