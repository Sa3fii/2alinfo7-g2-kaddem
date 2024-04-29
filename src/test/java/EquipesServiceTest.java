import static org.mockito.Mockito.*;

import java.util.*;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;


import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

public class EquipesServiceTest {
    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testRetrieveAllEquipes() {
        List<Equipe> mockEquipes = Arrays.asList(new Equipe(), new Equipe());
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();
        assertEquals(2, retrievedEquipes.size());
    }

    @Test
    public void testAddEquipe() {
        Equipe equipeToAdd = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipeToAdd);

        Equipe addedEquipe = equipeService.addEquipe(new Equipe());
        assertEquals(equipeToAdd, addedEquipe);
    }

    @Test
    public void testDeleteEquipe() {
        Equipe equipeToDelete = new Equipe();
        equipeToDelete.setIdEquipe(1); // Set ID as an example
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipeToDelete));

        equipeService.deleteEquipe(1);
        verify(equipeRepository, times(1)).delete(equipeToDelete);
    }

    @Test
    public void testRetrieveEquipe() {
        Equipe mockEquipe = new Equipe();
        mockEquipe.setIdEquipe(1); // Set ID as an example
        when(equipeRepository.findById(1)).thenReturn(Optional.of(mockEquipe));

        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);
        assertEquals(1, retrievedEquipe.getIdEquipe());
    }

    @Test
    public void testUpdateEquipe() {
        Equipe equipeToUpdate = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipeToUpdate);

        Equipe updatedEquipe = equipeService.updateEquipe(new Equipe());
        assertEquals(equipeToUpdate, updatedEquipe);
    }




    /*@Test
    public void testEvoluerEquipes_JuniorToSenior() {
        // Mock Equipe objects
        Equipe equipe1 = new Equipe("junior",Niveau.JUNIOR);
        List<Etudiant> etudiants1 = new ArrayList<>();
        Etudiant etudiant1 = new Etudiant();
        Set<Contrat> contrats1 = new HashSet<>();
        contrats1.add(createActiveContrat(new Date(System.currentTimeMillis() - 2 * 365 * 24 * 60 * 60 * 1000))); // Active contract (more than 1 year)
        etudiant1.setContrats(contrats1);
        etudiants1.add(etudiant1);

        // Convert List to Set before setting students
        Set<Etudiant> etudiantsSet1 = new HashSet<>(etudiants1);
        equipe1.setEtudiants(etudiantsSet1);

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe1);

        // Mock equipeRepository.findAll()
        when(equipeRepository.findAll()).thenReturn(equipes);

        // Call the method
        equipeService.evoluerEquipes();

        // Verify equipe is updated to SENIOR and saved
        verify(equipeRepository).save(equipe1);
        assertEquals(Niveau.SENIOR, equipe1.getNiveau());
    }

    @Test
    public void testEvoluerEquipes_SeniorToExpert() {
        // Similar structure to the previous test, but with equipe at Niveau.SENIOR and 3+ active contracts
        Equipe equipe2 = new Equipe("senior",Niveau.SENIOR);
        List<Etudiant> etudiants2 = new ArrayList<>();
        Etudiant etudiant2 = new Etudiant();
        Set<Contrat> contrats2 = new HashSet<>();
        contrats2.add(createActiveContrat(new Date(System.currentTimeMillis() - 2 * 365 * 24 * 60 * 60 * 1000))); // Active contract (more than 1 year)
        contrats2.add(createActiveContrat(new Date(System.currentTimeMillis() - 1 * 365 * 24 * 60 * 60 * 1000))); // Active contract (more than 1 year)
        etudiant2.setContrats(contrats2);
        etudiants2.add(etudiant2);

        // Convert List to Set before setting students
        Set<Etudiant> etudiantsSet2 = new HashSet<>(etudiants2);
        equipe2.setEtudiants(etudiantsSet2);

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe2);

        when(equipeRepository.findAll()).thenReturn(equipes);

        equipeService.evoluerEquipes();

        verify(equipeRepository).save(equipe2);
        assertEquals(Niveau.EXPERT, equipe2.getNiveau());
    }

    private Contrat createActiveContrat(Date dateFinContrat) {
        Contrat contrat = new Contrat();
        contrat.setArchive(false); // Set archive to false indicating active contract
        contrat.setDateFinContrat(dateFinContrat);
        return contrat;
    }
*/
}