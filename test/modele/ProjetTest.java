package modele;

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
        instance.insert();
        assertEquals(4, instance.getId());
        Projet expected = Projet.getById(4);
        assertEquals(expected, instance);
    }

    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        Projet expected = new Projet(); //avec valeur du 1
        Projet result = Projet.getById(1);
        assertEquals(expected, result);
    }
    
}
