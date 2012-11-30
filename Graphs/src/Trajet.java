
public class Trajet {
	public Station gareDep,gareArr;
	public String hDep,hArr;
	public float prix;

	//Constructeur de la classe trajet
	public Trajet(Station gd , Station ga , String hd , String ha)
	{
		gareDep=gd;
		gareArr=ga;
		hDep=hd;
		hArr=ha;
		prix=0;
	}

	// Calcul de la durée du trajet en minutes
	public int duree()
	{
		int hd = (int)(hDep.charAt(1)+hDep.charAt(2));
		int md = (int)(hDep.charAt(4)+hDep.charAt(5));
		int ha = (int)(hArr.charAt(1)+hArr.charAt(2));
		int ma = (int)(hArr.charAt(4)+hArr.charAt(5));
		
		int hduree= ha-hd;
		int mduree= ma-md;
		int duree;
		
//On remet les pendules a l'heure
		if(mduree<0)
		{
			hduree--;
			mduree+=60;
		}
		
		if(hduree< 0)hduree+=24;
		
		//Conversion en minutes
		duree = hduree*60+mduree; 

		return duree;

	}

	//Modificateur du prix :calculé en fonction de de la durée du trajet et du prix-horaire de la ligne
	public void setPrix(int p)
	{
		prix = this.duree()*p/60; //on divise par 60 car la durée est donné en minute et p est le prix horaire
	}
}
