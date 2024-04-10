package org.domain.lec12;

public class AnimalService {
	// AmimalType
	public AnimalDto getInformation(AnimalType type, long id) {
		// Animal animal = animalRepository.findByTypeAndId(type, id);

		Animal animal = new Dog();
		return AnimalDto.of(animal);
	}
}
