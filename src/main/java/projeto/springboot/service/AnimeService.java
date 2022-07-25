package projeto.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projeto.springboot.domain.Anime;
import projeto.springboot.repository.AnimeRepository;
import projeto.springboot.requests.AnimePostRequestBody;
import projeto.springboot.requests.AnimePutRequestBody;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service //regra de negócios
public class AnimeService {
    //implements AnimeRepository

    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id){
        //return animes.get((int)id);
        return animeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime não encontrado"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody){
        Anime anime = Anime.builder().nome(animePostRequestBody.getName()).build();
        return animeRepository.save(anime);
    }

    public void delete(long id){
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        System.out.println("O nome do anime é: "+savedAnime.getNome());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .nome(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);
    }
}
