import java.io.*;
import java.util.ArrayList;
public class ReadFile {

	private static BufferedReader br;

	// ensemble de points
	public static void fg(){
		int i,j,k,p;
		String ligne,prix,nomgare2,nomgare,ville="";

		ArrayList<Station> mesStations = new ArrayList<Station>();
		Station stati;

		try{
			InputStream ips=new FileInputStream("Gares.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			br = new BufferedReader(ipsr);

			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				if(ligne.contains("Ville :"))
				{
					ville = ligne.substring(8);
				}
				else
				{
					nomgare = ligne.substring(0,2);
					nomgare2 = ligne.substring(3,5);
					prix = ligne.substring(6);
					stati= new Station(ville,nomgare);
					mesStations.add(stati);
					i=mesStations.size()-1;
					stati= new Station(ville,nomgare2);
					mesStations.add(stati);

					//Conversion du prix horaire en entier
					p=0;
					k=0;	
					for (j=prix.length()-1;j>=0;j--)
					{
						p+=(int)(prix.charAt(j)) *Math.pow(10,k);
						k++;
					}
					stati.addJumelle(mesStations.get(i),p);
				}
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
			//}
		}
		
		//fdr
		Station x;
		ArrayList<Trajet> fdr=new ArrayList<Trajet>();
		ArrayList<Trajet> mestrajets = new ArrayList<Trajet>();
		String garedep="",villedep="",hdep="";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream("FeuillesRoutes.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);

			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				
				//Remise a null des variables de depart lors d'une nouvelle fdr
				if(ligne.contains("Feuille :"))
				{
					villedep=null;
					garedep=null;
					hdep=null;
				}

				//lecture d'une ligne d'information sur le train lors d'une entree en gare
				else 
					if(!ligne.contains("prixh"))
					{

						//On enregistre le trajet si on lit la deuxieme gare de la FDR
						if(villedep!=null)
						{
							x=new Station(villedep,garedep);
							if(!mesStations.contains(x))
							{
								mesStations.add(x);
							}
							Station temp=mesStations.get((mesStations.indexOf(x)));
							x=new Station(ligne.substring(12,13),ligne.substring(14));
							if(!mesStations.contains(x))
							{
								mesStations.add(x);
							}
							Station temp2=mesStations.get((mesStations.indexOf(x)));
							fdr.add(new Trajet(temp,temp2,hdep,ligne.substring(6,11)));
						}
						
						//Memorisation du depart du prochain trajet
						garedep=ligne.substring(14);
						villedep=ligne.substring(12,13);
						hdep=ligne.substring(6,11);
					}
					else
						{
							//Conversion du prix horaire en entier
							p=0;
							j=0;
							prix=ligne.substring(8);
							for (i=prix.length()-1;i>=0;i--)
							{
								int plo=(int)Math.pow(10,j);
								int plu=(int)prix.charAt(i)-48;
								p+= plo * plu;
								j++;
							}
							
						
							//Calcul du prix de chaque Trajet , et liaison des arete avec les points
							for(Trajet t : fdr)
							{	
								t.setPrix(p);
								t.gareDep.addTrajet(t);
							} 
							//on ajoute les trajet de cette FDR a l'ensemble des trajets.
							mestrajets.addAll(fdr);
							fdr=new ArrayList<Trajet>();
						}
			}		
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public static void algoritmedikjstra(String depart,String arrive,String heure)
	{
		int[][] etapes; 
		String[] precedents;
		Station tabs[]= new Station[5];
		Station arrive;
		int j=0;
		while(depart!=tabs[j].ville)j++;
		int i=0;
		for (int k=0;k<.length;k++){
			i=0;
			while(depart.precedente.get(k)!=tabs[i])i++;
			int poids[i]=Math.min(poids[j]+depart.precedente.get(k).duree(depart);

		}
		i=1;
		int l;
		while(i<poids.length){
			int p = Integer.MAX_VALUE;
			if(p>poids[i]){
				p=poids[i];
				l=i;
				i++;
			}
			if(tabs[l]!=arrive){
				ReadFile.algoritmedikjstra(tabs[l],poids[i]);
			}
			else{ System.out.println("poids minimal = "+ poids[i];} 

		}

	}*/

}
