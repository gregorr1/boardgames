package sl.unm.fis.grausl.boardgames.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Timeslot {
	@Id
	private int id;
	private int dayInWeek;
	private int startTime;
	private int endTime;
	@OneToMany(mappedBy="timeslot")
	@JsonIgnore
	private List<Player> listPlayers;
	@ManyToOne
	private BarTable table;
	
	public Timeslot() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDayInWeek() {
		return dayInWeek;
	}
	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public List<Player> getListPlayers() {
		return listPlayers;
	}
	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}
	public BarTable getTable() {
		return table;
	}
	public void setTable(BarTable table) {
		this.table = table;
	}
}
