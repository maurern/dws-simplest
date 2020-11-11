package com.mediamarktsaturn.dws_simplest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mediamarktsaturn.dws_simplest");

        noClasses()
            .that()
            .resideInAnyPackage("com.mediamarktsaturn.dws_simplest.service..")
            .or()
            .resideInAnyPackage("com.mediamarktsaturn.dws_simplest.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mediamarktsaturn.dws_simplest.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
