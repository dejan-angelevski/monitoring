package com.finki.monitoring.apirest;

import java.util.List;
import java.util.Random;

import com.finki.monitoring.domain.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JokesController {

	@GetMapping("/joke")
	public ResponseEntity<Joke> getAJoke() {
		return ResponseEntity.ok(JokeProvider.getARandomJoke());
	}

	private static class JokeProvider {

		public static Joke getARandomJoke() {
			List<Joke> jokes = List.of(new Joke("What do you call a pony with a cough?", "A little horse."),
					new Joke(" What do you call a magic dog?", "A labracadabrador."),
					new Joke("What did one hat say to the other?", "You wait here. I’ll go on a head."),
					new Joke(" What did the shark say when he ate the clownfish?", "This tastes a little funny."),
					new Joke("What’s orange and sounds like a carrot?", "A parrot."),
					new Joke("Why can’t you hear a pterodactyl go to the bathroom?", "Because the “P” is silent."),
					new Joke(" What did the pirate say when he turned 80?", "Aye matey."),
					new Joke("Why did the frog take the bus to work today?", "His car got toad away."),
					new Joke("What did the buffalo say when his son left for college?", "Bison."),
					new Joke("What is an astronaut’s favorite part on a computer?", "The space bar."));
			return jokes.get(new Random().nextInt(0, jokes.size()));
		}

	}

}
