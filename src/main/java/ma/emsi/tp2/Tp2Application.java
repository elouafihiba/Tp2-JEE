package ma.emsi.tp2;

import ma.emsi.tp2.entities.Patient;
import ma.emsi.tp2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {
@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(Tp2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		patientRepository.save(
//				new Patient(null,"hiba",new Date(),false,33));
//		patientRepository.save(
//				new Patient(null,"sameh",new Date(),true,12));
//		patientRepository.save(
//				new Patient(null,"jamila",new Date(),false,16));

		for(int i=0;i<100;i++){
			patientRepository.save(
				new Patient(null,"hiba",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
		}

		Page<Patient> patients = patientRepository.findAll(PageRequest.of(1,10));
		System.out.println("total des pages"+patients.getTotalPages());
		System.out.println("nombre des elements "+patients.getTotalElements());
		System.out.println("nombre de page"+patients.getNumber());

		List<Patient> content = patients.getContent();
		System.out.println("================================");
		content.forEach(p->{
			System.out.println(p);
		});

		System.out.println("============================");
		Patient patient=patientRepository.findById(1L).orElse(null);
		if (patient!=null){
			System.out.println(patient);
		}

		System.out.println("=============================");
		System.out.println(patient);
		patient.setScore(100);
		patientRepository.save(patient);
		System.out.println(patient);

		System.out.println("=============================");
		patientRepository.deleteById(1L);






		Page <Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(2,4));
		System.out.println("================================");
		byMalade.forEach(p->{
			System.out.println(p);
		});

	}
}
