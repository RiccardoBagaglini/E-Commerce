package com.webApp.ECommerce.Service;

import com.webApp.ECommerce.Dto.ArticoliDto;
import com.webApp.ECommerce.Entity.Articoli;
import com.webApp.ECommerce.Exception.NotFoundException;
import com.webApp.ECommerce.Repository.ArticoliRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
public class ArticoliServiceImpl implements ArticoliService {

    @Autowired
    ModelMapper modelMapperConfig;

     @Autowired
     ArticoliRepository articoliRepository;

    @Override
    public List<ArticoliDto> findAllAricoli() throws Exception {
         return  articoliRepository.findAll().stream().map(
                 articoli -> {
                     ArticoliDto articoliDto = new ArticoliDto(articoli.getIdArticolo(),articoli.getCodiceArticolo()
                             ,articoli.getNomeArticolo(),articoli.getPrezzoArticolo(),articoli.getQuantita(),articoli.getDisponibilita());
                     return articoliDto;
                 }
         ).collect(Collectors.toList());
    }

    @Override
    public List<ArticoliDto> findByNomeArticolo(String nomeArticolo) throws Exception {
        List<Articoli> articolist = articoliRepository.findByNomeArticolo(nomeArticolo);
        List<ArticoliDto> articoliDtos = new ArrayList<>();
        if (!articolist.isEmpty()) {
            articolist.stream().map(
                    articoli -> {
                        ArticoliDto articoliDto = modelMapperConfig.map(articoli, ArticoliDto.class);
                        articoliDtos.add(articoliDto);
                        return articoliDto;
                    }
            ).collect(Collectors.toList());
        }else{
            throw  new NotFoundException(String.format("Nessun articolo con nome: '%s' trovato",nomeArticolo));
        }
        return articoliDtos;
    }

    @Override
    public ArticoliDto findByCodiceArticolo(Integer codiceArticolo) throws Exception {
        Optional<Articoli> articoli = articoliRepository.findByCodiceArticolo(codiceArticolo);
        if (!articoli.isEmpty()) {
            ArticoliDto articoliDto = modelMapperConfig.map(articoli, ArticoliDto.class);
            return articoliDto;
        }

        throw new Exception("");
    }

    @Override
    public void insertArticolo(ArticoliDto articoli) throws Exception {
        if (articoli != null) {
            Articoli articolo = modelMapperConfig.map(articoli, Articoli.class);
            articoliRepository.save(articolo);
        } else {
            throw new Exception("");
        }

    }

    @Override
    public void deleteArticolo(Integer idArticolo) throws Exception {
        Optional<Articoli> articoli = articoliRepository.findById(idArticolo);

        if (articoli.isEmpty()) {
            throw new Exception("");
        }
        articoliRepository.delete(articoli.get());
    }
}
