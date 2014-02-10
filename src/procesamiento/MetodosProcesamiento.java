package procesamiento;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
public class MetodosProcesamiento 
{
	public int matImagen[][];
	int R[][],G[][],B[][];
	Bitmap mapaImagen;
	public MetodosProcesamiento(Bitmap mapa)
	{
		matImagen=new int[mapa.getWidth()][mapa.getHeight()];
		for(int i=0;i<mapa.getHeight();i++)
		{
			for(int j=0;j<mapa.getWidth();j++)
			{
				matImagen[i][j]=mapa.getPixel(i, j);
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
				R[i][j]=(matImagen[i][j] & 0x00ff0000)>>16;
				G[i][j]=(matImagen[i][j] & 0x0000ff00)>>8;
				B[i][j]=(matImagen[i][j] & 0x000000ff);
			}
		}
		
	}
	public void escalaGrises()
	{
		for(int i=0;i<matImagen[0].length;i++)
		{
			for(int j=0;j<matImagen.length;j++)
			{
				R[i][j]=(R[i][j]+G[i][j]+B[i][j])/3;
				G[i][j]=R[i][j];
				B[i][j]=R[i][j];
			}
		
		}
	}
	
	public Bitmap getMapa()
	{
		
		
	}
	
	
}
