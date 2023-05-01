package com.api.apirest.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Paie;

@Repository
public interface PaieRepository extends CrudRepository<Paie, Long>{
	
	//listing pour une année donnée et un mois déjà échu
		Iterable <Paie> findByEmployee_Id(Integer employee);
		Iterable <Paie> findByMoisAndAnnee(Integer mois,Integer annee);
		Optional <Paie> findByEmployee_IdAndMoisAndAnnee(Integer employee,Integer mois,Integer annee);
		 //List<Paie> findByEmployee_Company_IdAndTrimestreAndYear(Integer cmpID, Integer trimestre, Integer annee);

	

}
