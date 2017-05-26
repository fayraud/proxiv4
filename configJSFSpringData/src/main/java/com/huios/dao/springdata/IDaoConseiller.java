package com.huios.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huios.metier.Conseiller;


public interface IDaoConseiller extends JpaRepository<Conseiller,Long>{

	@Query("SELECT c FROM Conseiller c WHERE c.login = :login AND c.pwd = :pwd")
	public Conseiller verificationLogin(@Param("login")String login,@Param("pwd") String pwd);
	
	
}
