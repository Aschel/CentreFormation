package modele;

import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilisateur
 */
public class ProjetTest {

    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Projet instance = new Projet();
        //instance.insert();
        assertEquals(4, instance.getId());
        Projet expected = Projet.getById(4);
        assertEquals(expected, instance);
    }

    @Test
    public void testGetById() throws SQLException, ParseException {
        System.out.println("getById");
        int id = 0;
        String strDeb = "2016-06-11 00:00:00";
        String strFin = "2017-07-01 00:00:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateDeb = formatter.parse(strDeb);
        Date dateFin = formatter.parse(strFin);

        Projet expected = new Projet(1, 1, 10, "Organisation du projet de creation web", "Organisation", dateDeb, dateFin); //avec valeur du 1
        Projet result = Projet.getById(1);
        assertEquals(expected, result);
    }

}
