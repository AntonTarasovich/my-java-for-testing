package ua.projects.javatests.soap;

import net.webservicex.GeoIP;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("109.86.18.91");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }
}
