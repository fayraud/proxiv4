package com.huios.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huios.metier.Adresse;
import com.huios.metier.Client;

public interface IDaoAdresse extends JpaRepository<Adresse,Long> {

}
