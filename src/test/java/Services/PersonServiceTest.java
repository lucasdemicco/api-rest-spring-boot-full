package Services;

import Mocks.PersonMock;
import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Entities.Person;
import br.com.rest.Domain.Mapper.PersonParseObjectHelper;
import br.com.rest.Handler.Exceptions.ResourceNotFoundException;
import br.com.rest.Repositories.PersonRepository;
import br.com.rest.Services.Services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    PersonMock personMock;
    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setupMocks() throws Exception{
        personMock = new PersonMock();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll(){
        //Given
        List<Person> entityList = new ArrayList<Person>();

        personMock.mockListPerson()
            .stream()
                .toList()
                .forEach(x -> entityList.add(PersonParseObjectHelper.mapToEntity(x)));

        when(repository.findAll()).thenReturn(entityList);

        //When
        List<PersonDto> findAllPersons = personService.findAll();

        //Then
        assertNotNull(findAllPersons);
        assertEquals(10, findAllPersons.size());
    }

    @Test
    void testFindAllWithReturnEmptyList(){
        //Given
        when(repository.findAll()).thenReturn(Collections.emptyList());

        //When
        List<PersonDto> findAllPersons = personService.findAll();

        //Then
        assertNotNull(findAllPersons);
        assertTrue(findAllPersons.isEmpty());
    }

    @Test
    void  testFindById(){
        // Given
        long id = 1L;
        Person entity = new Person(); // Assume YourEntity is your entity class
        entity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        // When
        PersonDto result = personService.findById(String.valueOf(id));

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findByIdNullObject(){
        //Given
        long id = 1L;

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.findById(String.valueOf(id));
        });
    };
}
