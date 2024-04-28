package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add(new Contrat());
        expectedContrats.add(new Contrat());

        when(contratRepository.findAll()).thenReturn(expectedContrats);

        List<Contrat> result = contratService.retrieveAllContrats();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(contratRepository).findAll();
    }

    @Test
    void testAddContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(any(Contrat.class))).thenReturn(contrat);

        Contrat result = contratService.addContrat(new Contrat());

        assertNotNull(result);
        verify(contratRepository).save(any(Contrat.class));
    }

    @Test
    void testRetrieveContrat() {
        Integer id = 1;
        Optional<Contrat> optionalContrat = Optional.of(new Contrat());
        when(contratRepository.findById(id)).thenReturn(optionalContrat);

        Contrat result = contratService.retrieveContrat(id);

        assertNotNull(result);
        verify(contratRepository).findById(id);
    }

    @Test
    void testRemoveContrat() {
        Integer id = 1;
        Contrat contrat = new Contrat();
        when(contratRepository.findById(id)).thenReturn(Optional.of(contrat));
        doNothing().when(contratRepository).delete(contrat);

        contratService.removeContrat(id);

        verify(contratRepository).findById(id);
        verify(contratRepository).delete(contrat);
    }
}