import java.util.ArrayList;


public class utilise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Station> mesStations =ReadFile.fg();
		mesStations.trimToSize();
	
		Station depart=mesStations.get(1);
		Station arrive=mesStations.get(6);
		System.out.println(depart.ville+"a "+arrive.ville);
		ReadFile.algoritmedikjstra(depart, arrive,"07:00", 'g', mesStations);
	}

}
