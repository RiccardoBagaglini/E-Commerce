package com.webApp.ECommerce.Controller;

import com.webApp.ECommerce.Dto.ArticoliDto;
import com.webApp.ECommerce.Service.ArticoliService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articoli")
public class ArticoliController {
    @Autowired
    ArticoliService articoliService;


    @GetMapping(path= "/tutti")
    public ResponseEntity<List<ArticoliDto>> allArticle()throws  Exception{
        return ResponseEntity.ok(articoliService.findAllAricoli());
    }

    @GetMapping(value = "/findByName/{name}")
    public  ResponseEntity<List<ArticoliDto>> findByArticolo(@PathVariable String name)throws Exception{
        return ResponseEntity.ok(articoliService.findByNomeArticolo(name));
    }

    @GetMapping(path ="/findByCodiceArticolo/{codiceArticolo}")
    public ResponseEntity<ArticoliDto> findByCodiceArticolo(@PathVariable Integer codiceArticolo)throws Exception{
        return ResponseEntity.ok(articoliService.findByCodiceArticolo(codiceArticolo));
    }

    @PostMapping(path ="/insertArticolo")
    public ResponseEntity<?> insertArticolo(@RequestBody ArticoliDto articoliDto)throws Exception{
        articoliService.insertArticolo(articoliDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteArticolo(@PathVariable Integer id)throws Exception{
        articoliService.deleteArticolo(id);
        return ResponseEntity.ok(null);
    }
}
