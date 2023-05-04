package com.webApp.ECommerce.Repository;

import com.webApp.ECommerce.Entity.Articoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ArticoliRepository extends JpaRepository<Articoli,Integer> {
    public Optional<Articoli> findByCodiceArticolo(@Param("codice_articolo")Integer codiceArticolo);
    public List<Articoli> findByNomeArticolo(@Param("nome_articolo")String nomeArticolo);
}
