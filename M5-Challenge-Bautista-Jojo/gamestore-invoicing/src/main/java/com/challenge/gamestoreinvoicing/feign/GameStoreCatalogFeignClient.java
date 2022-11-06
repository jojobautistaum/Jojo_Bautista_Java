package com.challenge.gamestoreinvoicing.feign;

import com.challenge.gamestoreinvoicing.model.Console;
import com.challenge.gamestoreinvoicing.model.Game;
import com.challenge.gamestoreinvoicing.model.TShirt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="store-catalog")
public interface GameStoreCatalogFeignClient {

    // Console feign client
    @GetMapping("/console/{id}")
    public Console getConsole(@PathVariable("id") long consoleId);

    @GetMapping("/console")
    public List<Console> getAllConsoles();

    @GetMapping("/console/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable("manufacturer") String manu);

    @PostMapping("/console")
    public Console createConsole(@RequestBody @Valid Console console);

    @PutMapping("/console")
    public void updateConsole(@RequestBody @Valid Console console);

    @DeleteMapping("/console/{id}")
    public void deleteConsole(@PathVariable("id") long consoleId);

    // TShirt feign client
    @PostMapping("/tshirt")
    public TShirt createTShirt(@RequestBody @Valid TShirt tShirt);

    @GetMapping("/tshirt/{id}")
    public TShirt getTShirt(@PathVariable("id") long tShirtId);

    @GetMapping("/tshirt/size/{size}")
    public List<TShirt> getTShirtsBySize(@PathVariable("size") String size);

    @GetMapping("/tshirt/color/{color}")
    public List<TShirt> getTShirtsByColor(@PathVariable("color") String color);

    @PutMapping("/tshirt")
    public void updateTShirt(@RequestBody @Valid TShirt tShirt);

    @DeleteMapping("/tshirt/{id}")
    public void deleteTShirt(@PathVariable("id") int tShirtId);

    @GetMapping("/tshirt")
    public List<TShirt> getAllTShirts();

    // Game feign client
    @PostMapping("/game")
    public Game createGame(@RequestBody @Valid Game game);

    @GetMapping("/game/{id}")
    public Game getGameInfo(@PathVariable("id") long gameId);

    @GetMapping("/game/title/{title}")
    public List<Game> getGamesByTitle(@PathVariable("title") String title);

    @GetMapping("/game/esrbrating/{esrb}")
    public List<Game> getGamesByEsrbRating(@PathVariable("esrb") String esrb);

    @GetMapping("/game/studio/{studio}")
    public List<Game> getGamesByStudio(@PathVariable("studio") String studio);

    @PutMapping("/game")
    public void updateGame(@RequestBody @Valid Game game);

    @DeleteMapping("/game/{id}")
    public void deleteGame(@PathVariable("id") int gameId);

    @GetMapping("/game")
    public List<Game> getAllGames();

}