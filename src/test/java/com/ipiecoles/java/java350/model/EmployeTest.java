package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmployeTest {

  @ParameterizedTest(name = "Salaire : {0}, augmentation : {1} -> nouveau salaire : {2}")
  @CsvSource({
          "1000.0, 0.75, 1750.0",
          "1500.0, 0.15, 1725.0",
          "2150.0, -0.0, 2150.0",
          "2150.0, 1.50, 5375.0"
  })
  void testAugmenterSalaireCasNominaux(Double ancienSalaire, Double augmentation, Double nouveauSalaire) {
    //Given, When, Then
    Employe employe = new Employe();
    employe.setSalaire(ancienSalaire);
    employe.augmenterSalaire(augmentation);
    Assertions.assertThat(employe.getSalaire()).isEqualTo(nouveauSalaire);
  }

  @ParameterizedTest(name = "Salaire : {0}, augmentation : {1} -> nouveau salaire : {2}")
  @CsvSource({
          "1253.25, 0.33, 1666.82",
          "2169.75, 0.66, 3601.79",
          "2046.1546, 1.0034, 4099.27",
          "1342.1564, 0.6481, 2212.01"

  })
  void testAugmenterSalaireCasLimites(Double ancienSalaire, Double augmentation, Double nouveauSalaire) {
    //Given, When, Then
    Employe employe = new Employe();
    employe.setSalaire(ancienSalaire);
    employe.augmenterSalaire(augmentation);
    Assertions.assertThat(employe.getSalaire()).isEqualTo(nouveauSalaire);
  }

  @Test
  void testAugmenterSalaireTauxNull() {
    // Given
    Employe employe = new Employe();
    employe.setSalaire(1500d);
    Double augmentation = null;

    // When
    try {
      employe.augmenterSalaire(augmentation);
      Assertions.fail("La méthode augmenterSalaire() aurait dû lancer une exception");
    } catch (IllegalArgumentException e) {
      //Then
      Assertions.assertThat(e.getMessage()).isEqualTo("Le pourcentage d'augmentation ne peut être null");
    }
  }

  @Test
  void testAugmenterSalaireTauxNegatif() {
    // Given
    Employe employe = new Employe();
    employe.setSalaire(1500d);
    Double augmentation = -0.01;

    // When
    try {
      employe.augmenterSalaire(augmentation);
      Assertions.fail("La méthode augmenterSalaire() aurait dû lancer une exception");
    } catch (IllegalArgumentException e) {
      //Then
      Assertions.assertThat(e.getMessage()).isEqualTo("Le pourcentage d'augmentation ne peut être négatif");
    }
  }
}