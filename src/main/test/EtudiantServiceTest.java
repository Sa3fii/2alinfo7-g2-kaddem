import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Mock data
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1, "John", "Doe"));
        etudiants.add(new Etudiant(2, "Jane", "Doe"));

        // Mocking repository behavior
        when(etudiantRepository.findAll()).thenReturn(etudiants);
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Verify that the service method returns the expected result
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNomE());
        assertEquals("Doe", result.get(0).getPrenomE());
        assertEquals("Jane", result.get(1).getNomE());
        assertEquals("Doe", result.get(1).getPrenomE());
    }
}
