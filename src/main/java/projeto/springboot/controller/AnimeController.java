package projeto.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import projeto.springboot.domain.Anime;
import projeto.springboot.service.AnimeService;
import projeto.springboot.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    //ResponseEntity retorna tanto a resposta quanto o estado atual
    @GetMapping //sem o path = "list"
    @ResponseBody //Rest
    public ResponseEntity<List<Anime>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK) ;
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Anime> findById(@PathVariable long id){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findById(id),HttpStatus.OK);
    }
}
