import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension
        .class)
class HelloMockitoTestFull {
    @Mock
    private PersonRepository repository;
    @Mock
    private TranslationService translationService;
    @InjectMocks
    private HelloMockito helloMockito;
    @Test
    @DisplayName("Greet Admiral Hopper")
    void greetAPersonThatExists() {
        // set the expectations on the mocks
        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(new Person(1, "Grace", "Hopper",
                        LocalDate.of(1906, Month.DECEMBER, 9))));
        when(translationService
                .translate("Hello, Grace, from Mockito!", "en", "en"))
                .thenReturn("Hello, Grace, from Mockito!");
        // test the greet method
        String greeting = helloMockito.greet(1, "en", "en");
        assertEquals("Hello, Grace, from Mockito!", greeting);
        // verify the methods are called once, in the right order
        InOrder inOrder = inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService)
                .translate(anyString(), eq("en"), eq("en"));
    }
    @Test
    @DisplayName("Greet a person not in the database")
    void greetAPersonThatDoesNotExist() {
        when(repository.findById(anyInt()))
                .thenReturn(Optional.empty());
        when(translationService
                .translate("Hello, World, from Mockito!", "en", "en"))
                .thenReturn("Hello, World, from Mockito!");
        String greeting = helloMockito.greet(100, "en", "en");
        assertEquals("Hello, World, from Mockito!", greeting);
        // verify the methods are called once, in the right order
        InOrder inOrder = inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService)
                .translate(anyString(), eq("en"), eq("en"));
    }

    @Test
    @DisplayName("Greet a person not in the database")
    void greetPersonThrowsException() {
        int id = 1;
        String sourceLang = "en";
        String targetLang = "es";
        // Simula excepcion de personRepository.findById
        when(repository.findById(id)).thenThrow(new RuntimeException("Database error"));
        // llama a greet() y verifica el retorno del resultado
        String result = helloMockito.greet(id, sourceLang, targetLang);
        // Verificamos resultado
        assertEquals("Error occurred while retrieving the person", result);
    }

    @Test
    public void greetPersonThrowsExceptionAndBlocksTranslate() {
        int id = 1;
        String sourceLang = "en";
        String targetLang = "es";
        when(repository.findById(id)).thenThrow(new RuntimeException("Database error"));
        // Llamamos a greet
        String result = helloMockito.greet(id, sourceLang, targetLang);
        // Comparamos
        assertEquals("Error occurred while retrieving the person", result);
        // Verificamos que translationService.translate no ha sido llamado
        verify(translationService, never()).translate(anyString(), anyString(), anyString());
    }
}