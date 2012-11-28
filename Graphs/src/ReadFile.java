import java.io.*;
import java.util.ArrayList;
public class ReadFile {

	public static void fg(){

		String prix;
		String chaine="" ;
		String ville="" ;
		ArrayList<Station> mesStations =null;
		String nomgare="";
		String nomgare2="";
		String gare[];
		Station stati;

		try{
			InputStream ips=new FileInputStream("Gare.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				if(chaine.contains("Ville :"))
				{
					ville="" ;
					ville = ligne.substring(6);
				}
				else
				{		
					gare = new String[2];
					nomgare = ligne.substring(0, 1);
					nomgare2 = ligne.substring(3,4);
					prix = ligne.substring(6);
					gare[0]=nomgare;
					gare[1]=nomgare2;
					stati= new Station(ville,gare,prix);
					mesStations.add(stati);

				}

				/*if(!chaine.contains("prixh") && !chaine.contains("Feuille :")){
					String ville = chaine.substring(12);
					i = ville.indexOf(" ");
					a = new Station(chaine.substring(0,5),chaine.substring(6,11),ville.substring(0, i),ville.substring(i+1),a);

				 */
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}






	public static void fdr(){
		int i,j=0,k=0 ;
		String chaine="";
		String prix;
		ArrayList<Station> a=null;;
		Station stati;
		Trajet traj;
		String tabGares[]=new String[20];
		String tabVilles[]=new String[20];
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream("FeuillesRoutes.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				chaine=ligne;

				if(!chaine.contains("prixh") && !chaine.contains("Feuille :")){
					String ville = chaine.substring(12);
					i = ville.indexOf(" ");
					while(a.get(j).ville != chaine.substring(0,5))
					{j++;}
					while(a.get(j).gare.get(k) != chaine.substring(6,11))
					{k++;}
					traj=new Trajet(a.get(j).ville,a.get(j).gare.get(k).ville,);
					a.get(j).addTrajet(traj);
					j=0;
					while(tabGares[j]!=ville.substring(i+1) && tabGares[j]!=null)
					{
						j++;
					}
					tabGares[j]=ville.substring(i+1);
					System.out.println(tabGares[j]);
					j=0;
					while(tabVilles[j]!=ville.substring(0,i) && tabVilles[j]!=null)
					{
						j++;
					}
					tabVilles[j]=ville.substring(0,i);
					System.out.println(tabVilles[j]);

				}
				else if(chaine.contains("prixh"))
				{
					prix=chaine.substring(7);
					//System.out.println(prix);
					//while(a.precedente !=null){a=a.precedente;}
					//a

				}
				/*else {
					System.out.println("ERREUR");
				}*/
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

	}

}
