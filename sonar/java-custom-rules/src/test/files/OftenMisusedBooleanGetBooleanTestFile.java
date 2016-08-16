
public class OftenMisusedAuthenticationTestFile {

	void sample() {
		
		 String isValid = "true";
		 if ( Boolean.getBoolean(isValid) ) { // Noncompliant {{Often Misused: Boolean.getBoolean(String)}}
		     System.out.println("TRUE");
		 }
		 else {
		     System.out.println("FALSE");
		 }
		 Boolean.valueOf(isValid); // Compliant
		 Boolean.parseBoolean(isValid); // Compliant
	}
}
