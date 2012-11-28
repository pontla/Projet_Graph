
public class Trajet {
String dep;
String arr;
String hdep;
String harr;
int prix;
public Trajet(String d , String gare , String hd , String ha, int p)
{
	
	dep=d;
	arr=a;
	hdep=hd;
	harr=ha;
	prix =p*(duree(hd,ha))/60;
}
public int duree(String d,String a)
{
		
	int h0 = (int)(d.charAt(1)+d.charAt(2));
	int m0 = (int)(d.charAt(4)+d.charAt(5));
	int h = (int)(a.charAt(1)+a.charAt(2));
	int m = (int)(a.charAt(4)+a.charAt(5));
	int hduree= h-h0;
	int mduree= m-m0;
	int distance;
	
	if(mduree<0)
	{
		hduree--;
		mduree+=60;
	}
	
	if (hduree< 0)
	{
		System.out.println("L'horaire d'arrive doit etre posterieur a celui de depart");
		distance = 0;
	}
	
	else
	{
		distance = hduree*60+mduree; 
	}

	
	return distance;
	
}

}
