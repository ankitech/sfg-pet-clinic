package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;

    private final Long ownerId = 1L;
    private final String lastname = "Smith";

    @BeforeEach
    void setUp() {

        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastname).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());

    }

    @Test
    void save() {

        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner ownerSaved = ownerServiceMap.save(owner2);

        assertEquals(id, ownerSaved.getId());
    }

    @Test
    void saveNoId() {

        Owner ownerSaved = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }


    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {

        Owner owner = ownerServiceMap.findByLastName(lastname);
        assertNotNull(owner);
        assertNotNull(owner.getLastName());
    }
}