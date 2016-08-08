import java.net.InetAddress;

public class OftenMisusedAuthenticationTestFile {

	void sample() {
		String ip = request.getRemoteAddr();
		InetAddress addr = InetAddress.getByName(ip);
		if (addr.getCanonicalHostName().endsWith("trustme.com")) { // Noncompliant {{Often Misused: Authentication}}
			trusted = true;
		}
		addr.getHostName(); // Noncompliant {{Often Misused: Authentication}}
	}
}
