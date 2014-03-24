// Kulturhuset

public class Instrument 
{
	private int Instrumentklasse;
	private String Model;
	private long Katalognr;
	Instrument neste;
	
	public Instrument (int i, String m, long k, Instrument Etterfølger)
	{
		Instrumentklasse = i;
		Model = m;
		Katalognr = k;
		neste = Etterfølger;
	}
	
	public int getInstrumentklasse() // Metode for uthenting av instrumentklasse (1 = gitar, 2= bass etc..)
	{
		return Instrumentklasse;
	}
	
	public String getModel() // Metode for uthenting av instrumentets model
	{
		return Model;
	}
	public long getKatalognr() // Metode for uthenting av instrumentets nr i registeret
	{
		return Katalognr;
	}
}
