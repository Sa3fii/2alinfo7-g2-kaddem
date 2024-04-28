import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.*;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;


    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> mockEtudiants = Arrays.asList(new Etudiant(), new Etudiant());
        when(etudiantRepository.findAll()).thenReturn(mockEtudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();
        assertEquals(2, retrievedEtudiants.size());
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiantToAdd = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiantToAdd);

        Etudiant addedEtudiant = etudiantService.addEtudiant(new Etudiant());
        assertEquals(etudiantToAdd, addedEtudiant);
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiantToUpdate = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiantToUpdate);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(new Etudiant());
        assertEquals(etudiantToUpdate, updatedEtudiant);
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant mockEtudiant = new Etudiant();
        mockEtudiant.setIdEtudiant(1); // Set ID as an example
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(mockEtudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);
        assertEquals(1, retrievedEtudiant.getIdEtudiant());
    }

    @Test
    public void testRemoveEtudiant() {
        Etudiant etudiantToDelete = new Etudiant();
        etudiantToDelete.setIdEtudiant(1); // Set ID as an example
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiantToDelete));

        etudiantService.removeEtudiant(1);
        verify(etudiantRepository, times(1)).delete(etudiantToDelete);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();
        when(etudiantRepository.findById(any(Integer.class))).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(any(Integer.class))).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(1, 1);
        assertEquals(departement, etudiant.getDepartement());
    }


    @Test
    public void testGetEtudiantsByDepartement() {
        List<Etudiant> mockEtudiants = Arrays.asList(new Etudiant(), new Etudiant());
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(any(Integer.class))).thenReturn(mockEtudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.getEtudiantsByDepartement(1);
        assertEquals(2, retrievedEtudiants.size());
    }
}
