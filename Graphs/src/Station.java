import java.util.*;



public class Station 
{

	public String ville;
	public ArrayList<String> gare;
	public ArrayList<Trajet> trajets;
	public int ptransfert;

	public Station()
	{

	}

	public Station(String v1 , String[] g,String p)
	{
		ptransfert=0;
		ville= v1;
		double j=0;
				for(int i=0;i<g.length;i++){
					gare.add(g[i]);
				}
		for(int i=p.length();i>0;i--){
			ptransfert += p.charAt(i-1)*Math.pow(10, j);
			j++;
		}

	}

	public void addTrajet(Trajet traj){
		trajets.add(traj);
	}
	public boolean equals(Station s){
		boolean rep=false;

		if (s.ville == this.ville)
		{
			rep=true;
		}
		return rep;
	}
}
