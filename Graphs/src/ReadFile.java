import java.io.*;
import java.util.ArrayList;
public class ReadFile {

	private static BufferedReader br;

	// ensemble de points
	public static ArrayList<Station> fg(){
		int i,j,k,p;
		String ligne,prix,nomgare2,nomgare,ville="";

		ArrayList<Station> mesStations = new ArrayList<Station>();
		Station stati;

		try{
			InputStream ips=new FileInputStream("Gares.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			br = new BufferedReader(ipsr);

			while ((ligne=br.readLine())!=null){
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
		}
		//}

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
		return mesStations;
	}

	//}//fg

	public static void algoritmedikjstra(Station depart,Station arrive,String heuredep, char critere ,ArrayList<Station> mesStations)
	{
		int i,k,j;
		int hd = (int)(heuredep.charAt(0)+heuredep.charAt(1));
		int md = (int)(heuredep.charAt(3)+heuredep.charAt(4));
		int n= mesStations.size();
		int[] poidTrajets = new int[n] ;
		Station[] precedences=new Station[n];
		boolean[] visite=new boolean[n];
		for( i=0; i<n;i++)visite[i]=false;
		for( j=0 ;j<poidTrajets.length;j++)poidTrajets[j]=-1;

		//pointeur i vers la gare de depart
		i=0 ;
		while( mesStations.get(i)!=depart)i++;
		poidTrajets[i]=0;

		//Algo meilleur Temps d arrivee entre lheure demandee pour le depart et l heure d arrivee en gare

		//initialisation
		System.out.println(depart.trajets.isEmpty());
		for (Trajet traj : depart.trajets)
		{
			System.out.println("initialise");
			System.out.println(traj.gareArr.gare+" "+traj.hArr+" "+traj.gareDep.gare+":"+traj.hDep);
			int ha = (int)((traj.hArr.charAt(0))-48)*10+ (int)(traj.hArr.charAt(1))-48;
			int ma = (int)((traj.hArr.charAt(3))-48)*10+ (int)(traj.hArr.charAt(4))-48;
			if ( hd*60+md < ha*60+md)
			{
				//Recherche du pointeur vers la station
				j=0 ;
				while(mesStations.get(j)!= traj.gareArr)i++;
				////////////

				if(poidTrajets[j]<0 || poidTrajets[i]> (ha-hd)*60 +ma-md)
				{
					System.out.println("initialisation remplisage poid");
					poidTrajets[j]=(ha-hd)*60 +ma-md;
					precedences[j]=depart;
				}
			}
		}
		if(depart.jumelle!=null)
		{
			//Recherche des trajet depuis la station voisine
			j=i;//memorisation de la gare de depart
			for (Trajet traj : depart.jumelle.trajets)
			{
				System.out.println("forjumelle");
				int ha = (int)(traj.hArr.charAt(0)+traj.hArr.charAt(1));
				int ma = (int)(traj.hArr.charAt(3)+traj.hArr.charAt(4));
				if ( hd*60+md+depart.dureeNavette < ha*60+ma)
				{
					//Recherche du pointeur vers la station
					i=0 ;
					while(mesStations.get(i)!= traj.gareArr)i++;
					///////////////////////////////////////

					if(poidTrajets[i]<0|| poidTrajets[i]> (ha-hd)*60 +ma-md)
					{
						System.out.println("initialisation remplissage");
						poidTrajets[i]=(ha-hd)*60 +ma-md;
						precedences[i]=depart.jumelle;
						precedences[j]=mesStations.get(i);//precedence de la gare voisine
					}
				}
			}
		}
		System.out.println("recherche distance proche");
		//Algo

		//Recherche de la distance la plus proche
		k=0;
		for( i=1;i<mesStations.size();i++)
		{
			int min=-1;
			if(!visite[i])
			{
				if (min>poidTrajets[i]&&poidTrajets[i]!=-1)
				{
					k=i;
					min=poidTrajets[i];
				}
			}
		}
		Station plusproche= mesStations.get(k);
		//////////////////////////////////////

		boolean bool=(plusproche==arrive||plusproche.jumelle==arrive);
		System.out.println(bool);

		while(!bool)
		{
			System.out.println(plusproche.gare+"DEBUG1");
			visite[k]=true;
			hd = (int)(heuredep.charAt(0)+heuredep.charAt(1));
			md = (int)(heuredep.charAt(3)+heuredep.charAt(4));
			int min=poidTrajets[0];
			for (Trajet traj : mesStations.get(k).trajets)
			{
				System.out.println("DEBUG2");
				int ha = ((int)(traj.hArr.charAt(0) - 48) *10+(int)traj.hArr.charAt(1))-48;
				int ma = ((int)(traj.hArr.charAt(3) - 48) *10+(int)traj.hArr.charAt(4))-48;
				System.out.println(poidTrajets[k]);
				if (hd*60+md+poidTrajets[k] < ha*60+ma)
				{
					System.out.println("DEBUG3");
					i=0 ;
					while(mesStations.get(i)!=traj.gareArr)i++;
					if(poidTrajets[i]==-1 || poidTrajets[i]> (ha-hd)*60 +ma-md)
					{
						System.out.println("DEBUG4");
						poidTrajets[i]=(ha-hd)*60+ma-md;
						precedences[i]=plusproche;
					}
				}
			}
			// recherche pour la Station voisine
			for (Trajet traj : depart.jumelle.trajets)
			{
				int ha = ((int)((traj.hArr.charAt(0))-48) *10+ (int)traj.hArr.charAt(1)) -48;
				int ma = ((int)(traj.hArr.charAt(3) - 48) *10+ (int)traj.hArr.charAt(4)) -48;
				if ( hd*60+md+plusproche.dureeNavette < ha*60+ma)
				{
					//Recherche du pointeur vers la station
					i=0 ;
					while(mesStations.get(i)!= traj.gareArr)i++;
					///////////////////////////////////////

					if(poidTrajets[i]==-1 || poidTrajets[i]> (ha-hd)*60 +ma-md)
					{
						poidTrajets[i]=(ha-hd)*60 +ma-md;
						precedences[i]=plusproche.jumelle;
						precedences[mesStations.indexOf(plusproche.jumelle)]=plusproche;
					}
				}
			}

			//Recherche de la distance la plus proche
			for( i=1;i<n;i++)
			{
				if ((poidTrajets[i]>0 || min>poidTrajets[i])&& !visite[i])
				{
					k=i;
					min=poidTrajets[i];
				}
			}
			plusproche= mesStations.get(k);
			bool=(plusproche==arrive||plusproche.jumelle==arrive||i==n);
		}
		//manque condition darret si impossible
		//affichage
		Station st=mesStations.get(k);
		System.out.println(st.gare);
		/*while(st!=depart)
		{
			System.out.println("jumelle:"+st.jumelle.gare);
			System.out.println(st.ville+"-"+st.gare+">>> ");
			k=mesStations.indexOf(st);
			st=precedences[k];
		}*/
	}
}
