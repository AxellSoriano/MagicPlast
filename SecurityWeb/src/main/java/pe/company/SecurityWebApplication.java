package pe.company;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SecurityWebApplication implements CommandLineRunner
{
	

	public static void main(String[] args) {
		SpringApplication.run(SecurityWebApplication.class, args);
	}

	@Override//método ejecutar y mostrar en consola
	public void run(String... args) throws Exception
	{
		/*
		System.out.println("a123: "+passwordEncoder.encode("a123"));
		System.out.println("w123: "+passwordEncoder.encode("w123"));
		System.out.println("m123: "+passwordEncoder.encode("m123"));
		System.out.println("c123: "+passwordEncoder.encode("c123"));
		*/
		
	}

}
