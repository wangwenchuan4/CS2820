package production;

public class Address {
	
	/**
	 * 
	 * @author Ted Herman
	 * A local class just to supply addresses
	 * for orders in the Orders component
	 *
	 */
		
	  SimRandom SR;
	  
	  /**
	   * @author Ted Herman
	   * @param SR is SimRandom object, so that all the random
	   * choices by methods of Address will be predictably random
	   */
	  public Address(SimRandom SR) {
		this.SR = SR;
	    }

	  /**
	   * @author Ted Herman
	   * @param R is a SimRandom for predictability in randomness
	   * @return String containing a random address for an order
	   */
	  public String randomAddress() {
		String FirstName = randomFirstName();
		String LastName = randomLastName();
		String StreetNumber = new Integer(randomStreetNumber()).toString();
		String StreetName = randomStreetName();
		String City = randomCity();
		String State = randomState();
		String ZipCode = randomZip();
		String Address = FirstName + " " +
		  LastName + "\n" + StreetNumber + " " +
		  StreetName + "\n" + City + " " + State + ZipCode;
		return Address;
	    }
	  /**
	   * @author Ted Herman
	   * @return a string containing a random street name
	   */
	  private String randomStreetName() {
		final String[] baseNames = {"Park Street",
				"Main Street", "Washington Boulevard",
				"Third Street", "Park Road",
				"Maple Street", "Hill Road"};
		return baseNames[SR.nextInt(baseNames.length)];
	    }
	  /**
	   * @author Ted Herman
	   * @return an integer in the range [1,999] for street address
	   */
	  private int randomStreetNumber () {
		return 1+SR.nextInt(998);
	    }
	  /**
	   * @author Ted Herman
	   * @return a random first name for an address
	   */
	  private String randomFirstName() {
		final String[] baseFirstNames = {"Dakota", "Emma",
				"Julian", "Nigella", "Will", "Asti", "Lee",
				"Pat", "Mavis", "Jerome", "Lilly", "Tess"};
		return baseFirstNames[SR.nextInt(baseFirstNames.length)];
		}
	  /**
	   * @author Ted Herman
	   * @return a random last name for an address
	   */
	  private String randomLastName() {
		final String[] baseLastNames = {"Parker","Mason",
					"Smith","Wright","Jefferson","Iqbal",
					"Owens","Lafleur","Metselen","Vinceroy",
					"Saville","Troitski","Andrews"};
		return baseLastNames[SR.nextInt(baseLastNames.length)];
	    }
	  /**
	   * @author Ted Herman
	   * @return a random city name
	   */
	  private String randomState() {
		final String[] baseState = {"IA","NE","MO",
					"IL","KS","MN","SD","AR","OK","TX"};
		return baseState[SR.nextInt(baseState.length)];
	    }
	  /**
	   * @author Ted Herman
	   * @return a random state code (two letters)
	   */
	  private String randomCity() {
		final String[] baseCity = {"Springfield","Clinton",
					"Madison","Franklin","Chester","Marion",
					"Greenville","Salem","Anytown","Hope"};
		return baseCity[SR.nextInt(baseCity.length)];
	    }
	  /**
	   * @author Ted Herman
	   * @return a random state code (two letters)
	   */
	  private String randomZip() {
	    String ZipCode = "";
	    for (int i=0; i<6; i++) 
	      ZipCode += "0123456789".charAt(SR.nextInt(10));
	    return ZipCode;
	  }	
	


}
