package com.webApp.ECommerce.Service;

import com.webApp.ECommerce.Dto.ArticoliDto;
import com.webApp.ECommerce.Entity.Articoli;

import java.util.List;

public interface ArticoliService {

    public List<ArticoliDto> findAllAricoli()throws Exception;
    public List<ArticoliDto> findByNomeArticolo(String nomeArticolo) throws Exception;

    public ArticoliDto findByCodiceArticolo(Integer codiceArticolo) throws  Exception;

    public void insertArticolo(ArticoliDto articoli) throws Exception;

    public void deleteArticolo(Integer idArticolo) throws Exception;
}
