import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class StepImplementation {

    @InjectMocks
    EmployeService employeService;
    @Mock
    EmployeRepository employeRepository;

    @Step("Un commercial a une performance de <perfActuelle>. Il a realise un CA de <caTraite> euros. Il devait realiser un CA de <objectifCa> euros. Sa performance passe a <nouvellePerf>")
    public void checkPerformance(Integer perfActuelle, Long caTraite, Long objectifCa, Integer nouvellePerf) throws EmployeException {
        String matricule = "C12345";
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe(
                "John", "Doe", matricule, LocalDate.now(), 2500.0, perfActuelle, 1.0
        ));
        Mockito.when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(50.0);

        employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);

        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Employe commercial = employeArgumentCaptor.getValue();

        Assertions.assertThat(commercial.getPerformance()).isEqualTo(nouvellePerf);
    }
}
