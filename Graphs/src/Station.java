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
		trajets.add(new Trajet(this,this,"",""));
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
		g.addJumelle(this, duree); //Reciprocite de la jumellitude
	}
	@Override
	public boolean equals(Object obj)
	{
		Station s =(Station)(obj);
		boolean rep=false;
		if (s.ville==ville && s.gare==gare)rep=true;
		return rep;
	}
}
