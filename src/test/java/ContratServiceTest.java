import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllContrats() {
        // Given
        Contrat contrat = new Contrat();
        List  <Contrat> contrats = new ArrayList<>();
        contrats.add(contrat);

        when(contratRepository.findAll()).thenReturn(contrats);

        // When
        List  <Contrat> retrievedContrats = contratService.retrieveAllContrats();

        // Then
        assertEquals(1, retrievedContrats.size());
        assertEquals(contrat, retrievedContrats.get(0));
    }

    @Test
    public void testUpdateContrat() {
        // Given
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        Contrat updatedContrat = contratService.updateContrat(contrat);

        // Then
        assertEquals(contrat, updatedContrat);
    }
    @Test
    public void testAddContrat() {
        // Given
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        Contrat addedContrat = contratService.addContrat(contrat);

        // Then
        assertEquals(contrat, addedContrat);
    }
}