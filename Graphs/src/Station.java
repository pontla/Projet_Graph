import java.util.*;



public class Station 
{

	public String ville,gare;
	public ArrayList<Trajet> trajets;
	public Station jumelle; //on admet qu'il ni a pas plus de deux gare par ville
	public int dureeNavette;

	public Station(String v1 ,String v2)
	{
		ville= v1;
		trajets = new ArrayList<Trajet>();
		gare = v2;
		jumelle=null;
	}

	public void addTrajet(Trajet traj)
	{
		trajets.add(traj);
	}
	
	public void addJumelle(Station g,int duree)
	{
		jumelle = g ;
		dureeNavette = duree;
	    jumelle.jumelle=this; //Reciprocite de la jumellitude
	}
	
	
	@Override public boolean equals(Object obj)
	{
		Station s =(Station)obj;
		if (s.ville.equals(this.ville) || s.gare.equals(this.gare))return true;
		else return false;
	}
}
