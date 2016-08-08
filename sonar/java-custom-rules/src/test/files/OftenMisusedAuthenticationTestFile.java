import java.net.InetAddress;

public class OftenMisusedAuthenticationTestFile {

	void sample() {
		String ip = request.getRemoteAddr();
		InetAddress addr = InetAddress.getByName(ip);
		if (addr.getCanonicalHostName().endsWith("trustme.com")) { // Noncompliant {{message}}
			trusted = true;
		}
		addr.getHostName(); // Noncompliant {{message}}
	}
}
