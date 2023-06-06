package com.api.apirest.repository;

import java.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.api.apirest.model.RequestsLeaves;

@Repository
public interface RequestLeavesRepository extends CrudRepository<RequestsLeaves, Long>  {
	
	Iterable <RequestsLeaves> findByEmployee(int employee);
	
	Iterable <RequestsLeaves> findByStatut(int statut);
	
	Iterable <RequestsLeaves> findByEmployeeAndStatut(int employee, int statut);
	
	Iterable <RequestsLeaves> findByEmployeeAndTypecongeAndStatutAndAnnee(int employee,int typeconge, int statut, int annee);
	
	/*congés echus*/

	Iterable <RequestsLeaves> findByRetourBefore(LocalDate date);
	
	Long countByStatut(int statut);
	}
	
	/**
	 * CrudRepository fournit des méthodes pour les opérations CRUD génériques et si nous voulons ajouter des méthodes personnalisées dans notre interface qui a étendu CrudRepository, nous pouvons le faire de la manière suivante.

a. Nous pouvons commencer les noms de nos méthodes de requête par find...By, read...By, query...By, count...By, et get...By. Avant By, nous pouvons ajouter une expression telle que Distinct . Après By, nous devons ajouter les noms des propriétés de notre entité.
b. Pour obtenir des données sur la base de plusieurs propriétés, nous pouvons concaténer les noms de propriétés en utilisant And et Or lors de la création des noms de méthodes.
c. Si nous voulons utiliser un nom complètement personnalisé pour notre méthode, nous pouvons utiliser l'annotation @Query pour écrire la requête.

Traduit avec www.DeepL.com/Translator (version gratuite)
	 */

	


