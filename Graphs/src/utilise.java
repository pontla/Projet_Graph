import java.util.ArrayList;


public class utilise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Station> mesStations =ReadFile.fg();
		mesStations.trimToSize();
	
		Station depart=mesStations.get(5);
		Station arrive=mesStations.get(6);
		System.out.println("trajet de "+depart.gare+" a "+arrive.gare);
		ReadFile.algoritmedikjstra(depart, arrive,"07:00", 'g', mesStations);
	}

}
