package Services;

import Mocks.PersonMock;
import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Entities.Person;
import br.com.rest.Domain.Mapper.PersonParseObjectHelper;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        List<Person> entityList = new ArrayList<Person>();

        personMock.mockListPerson()
            .stream()
                .toList()
                .forEach(x -> entityList.add(PersonParseObjectHelper.mapToEntity(x)));


        when(repository.findAll()).thenReturn(entityList);

        List<PersonDto> findAllPersons = personService.findAll();

        assertNotNull(findAllPersons);
        assertEquals(10, findAllPersons.size());
    }
}
