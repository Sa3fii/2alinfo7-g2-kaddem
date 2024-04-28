import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Test
    public void testRetrieveAllDepartements() {
        Departement dept1 = new Departement();
        Departement dept2 = new Departement();
        when(departementRepository.findAll()).thenReturn(Arrays.asList(dept1, dept2));

        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(departementRepository).findAll();
    }

    @Test
    public void testAddDepartement() {
        Departement dept = new Departement();
        when(departementRepository.save(dept)).thenReturn(dept);

        Departement result = departementService.addDepartement(dept);

        assertNotNull(result);
        verify(departementRepository).save(dept);
    }

    @Test
    public void testUpdateDepartement() {
        Departement dept = new Departement();
        when(departementRepository.save(dept)).thenReturn(dept);

        Departement result = departementService.updateDepartement(dept);

        assertNotNull(result);
        verify(departementRepository).save(dept);
    }

    @Test
    public void testRetrieveDepartement() {
        Integer id = 1;
        Departement dept = new Departement();
        when(departementRepository.findById(id)).thenReturn(Optional.of(dept));

        Departement result = departementService.retrieveDepartement(id);

        assertNotNull(result);
        verify(departementRepository).findById(id);
    }

    @Test
    public void testDeleteDepartement() {
        Integer id = 1;
        Departement dept = new Departement();
        when(departementRepository.findById(id)).thenReturn(Optional.of(dept));
        doNothing().when(departementRepository).delete(dept);

        departementService.deleteDepartement(id);

        verify(departementRepository).delete(dept);
    }
}