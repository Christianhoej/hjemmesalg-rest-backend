package brugerautorisation.transport.rmi;

import brugerautorisation.data.Diverse;
import brugerautorisation.data.Bruger;
import java.rmi.Naming;

public class Brugeradminklient {

    public static void main(String[] arg) throws Exception {
//		Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost/brugeradmin");
        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        Bruger off = ba.hentBrugerOffentligt("s175129");
        //System.out.println("Fik offentlige data " + Diverse.toString(off));

        //ba.sendGlemtAdgangskodeEmail("s175129", "Dette er en test, husk at skifte kode");
        //ba.ændrAdgangskode("s175129", "kodeqjrhc0", "Kode2405");
        Bruger b = ba.hentBruger("s175129", "Kode2405");
        System.out.println("Fik bruger " + b);
        System.out.println("med data " + Diverse.toString(b));
        // ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");

        //Object ekstraFelt = ba.getEkstraFelt("s175129", "Kode2405", "hobby");
        //System.out.println("Brugerens hobby er: " + ekstraFelt);

        ba.setEkstraFelt("s175129", "Kode2405", "hobby", "Fodbold og programmering"); // Skriv noget andet her

        //String webside = (String) ba.getEkstraFelt("s175129", "Kode2405", "webside");
        //System.out.println("Brugerens webside er: " + webside);
    }
}
