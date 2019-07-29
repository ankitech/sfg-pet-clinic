package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiologist");
        specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgen");
        specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentist");
        specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kumar");
        owner1.setLastName("Ankit");
        owner1.setAddress("kunal icon");
        owner1.setCity("pune");
        owner1.setTelephone("123456789");

        Pet mikesPet = new Pet();
        mikesPet.setName("Sandy");
        mikesPet.setPetType(dog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Smita");
        owner2.setLastName("Murmu");
        owner2.setAddress("palm breeze");
        owner2.setCity("banglore");
        owner2.setTelephone("987466213");

        Pet smitaPet = new Pet();
        smitaPet.setName("Fluffy");
        smitaPet.setPetType(cat);
        smitaPet.setOwner(owner2);
        smitaPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(smitaPet);

        ownerService.save(owner2);

        System.out.println("Owners loaded");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Max");
        vet1.getSpecialities().add(dentistry);
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("Harry");
        vet2.setLastName("Potter");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }
}
