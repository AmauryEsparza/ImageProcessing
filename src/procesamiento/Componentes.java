package procesamiento;

public class Componentes {
	
	private int etiquetaComponente;
	private int cadenaFreeman;
	private int vectorComponentes[];
	
	public Componentes()
	{
		
	}
	public void setEtiquetaComponente(int etiqueta)
	{
		etiquetaComponente = etiqueta;
	}
	public int getEtiquetaComponente()
	{
		return etiquetaComponente;
	}
	public int getCadenaFreeman()
	{
		return cadenaFreeman;
	}
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
	
			

}
