package sl.unm.fis.grausl.boardgames.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sl.unm.fis.grausl.boardgames.entities.BarTable;
import sl.unm.fis.grausl.boardgames.entities.Timeslot;
import sl.unm.fis.grausl.boardgames.entities.Player;
import sl.unm.fis.grausl.boardgames.services.GameService;

@RestController
public class GameController {
	@Autowired
	GameService gameService;
	
	@GetMapping("/getAllPlayers")
	public List<Player> getAllPlayers() {
		return gameService.getAllPlayers();
	}
	
	@GetMapping("/getAllTables")
	public List<BarTable> getAllTables() {
		return gameService.getAllTables();
	}
	
	@GetMapping("/getAllTimeslots")
	public List<Timeslot> getAllTimeslots() {
		return gameService.getAllTimeslots();
	}
	
	@PostMapping("/addOrUpdatePlayer")
	public void addOrUpdatePlayer(@RequestBody Player p) {
		gameService.addOrUpdatePlayer(p);
	}
	
	@PostMapping("/addOrUpdateTable")
	public void addOrUpdateTable(@RequestBody BarTable t) {
		gameService.addOrUpdateTable(t);
	}
	
	@PostMapping("/addOrUpdateTimeslot")
	public void addOrUpdateTimeslot(@RequestBody Timeslot t) {
		gameService.addOrUpdateTimeslot(t);
	}
	
	@DeleteMapping("/deletePlayer/{idPlayer}")
	public void deletePlayer(@PathVariable int idPlayer) {
		gameService.deletePlayer(idPlayer);
	}
	
	@DeleteMapping("/deleteTable/{idTable}")
	public void deleteTable(@PathVariable int idTable) {
		gameService.deleteTable(idTable);
	}
	
	@DeleteMapping("/deleteTimeslot/{idTimeslot}")
	public void deleteTimeslot(@PathVariable int idTimeslot) {
		gameService.deleteTimeslot(idTimeslot);
	}	
	
	@GetMapping("/getTimeslotsByTableId/{idTable}")
	public List<Timeslot> getTimeslotsByTableId(@PathVariable int idTable) {
		return gameService.getTimeslotsByTableId(idTable);
	}
	
	@GetMapping("/getPlayersByTimeslotId/{idTimeslot}")
	public List<Player> getPlayersByTimeslotId(@PathVariable int idTimeslot) {
		return gameService.getPlayersByTimeslotId(idTimeslot);
	}
	
	@GetMapping("/getTablesByBoardGame/{boardGame}")
	public List<BarTable> getTablesByBoardGame(@PathVariable String boardGame) {
		return gameService.getTablesByBoardGame(boardGame);
	}
}
