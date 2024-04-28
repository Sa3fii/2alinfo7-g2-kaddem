package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite testUniversite;

    @BeforeEach
    void setUp() {
        testUniversite = new Universite(1, "Test Universite");
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        when(universiteRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(result);
        verify(universiteRepository).findAll();
    }

    @Test
    void testAddUniversite() {
        // Arrange
        when(universiteRepository.save(any(Universite.class))).thenReturn(testUniversite);

        // Act
        Universite result = universiteService.addUniversite(testUniversite);

        // Assert
        assertNotNull(result);
        assertEquals(testUniversite.getIdUniv(), result.getIdUniv());
        verify(universiteRepository).save(testUniversite);
    }

    @Test
    void testUpdateUniversite() {
        // Arrange
        when(universiteRepository.save(any(Universite.class))).thenReturn(testUniversite);

        // Act
        Universite result = universiteService.updateUniversite(testUniversite);

        // Assert
        assertNotNull(result);
        verify(universiteRepository).save(testUniversite);
    }

    @Test
    void testRetrieveUniversite() {
        // Arrange
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(testUniversite));

        // Act
        Universite result = universiteService.retrieveUniversite(1);

        // Assert
        assertNotNull(result);
        assertEquals(testUniversite.getIdUniv(), result.getIdUniv());
        verify(universiteRepository).findById(1);
    }

    @Test
    void testDeleteUniversite() {
        // Arrange
        doNothing().when(universiteRepository).delete(any(Universite.class));
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(testUniversite));

        // Act
        universiteService.deleteUniversite(1);

        // Assert
        verify(universiteRepository).delete(testUniversite);
    }

 @Test
void testAssignUniversiteToDepartement() {
    // Arrange
    Universite u = new Universite();
    u.setDepartements(new HashSet<>());
    when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(u));
    when(departementRepository.findById(anyInt())).thenReturn(Optional.empty()); // No departement found

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
        universiteService.assignUniversiteToDepartement(1, 1);
    });
}


    @Test
    void testRetrieveDepartementsByUniversite() {
        // Arrange
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(testUniversite));

        // Act
        universiteService.retrieveDepartementsByUniversite(1);

        // Assert
        verify(universiteRepository).findById(1);
    }
}
