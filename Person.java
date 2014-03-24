// Kulturhuset

public class Person 
{
	private String Navn;
	private String Etternavn;
	private long Personnr;
	Person neste;
	
	public Person (String N, String E, long P, boolean L, int A, String I, Person Etterfølger)
	{
		Navn = N;
		Etternavn = E;
		Personnr = P;
		neste = Etterfølger;
	}
	
	public String getNavn() // Metode for uthenting av personens navn
	{
		return Navn;
	}
	
	public String getEtternavn() // Metode for uthenting av personens etternavn
	{
		return Etternavn;
	}
	public long getPersonnr() // Metode for uthenting av personens PersonNr
	{
		return Personnr;
	}
}
