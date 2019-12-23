package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.Player;


public interface PlayerService {


List<Player> getAllPlayer(String game);

void addPlayer(Player player);
Player findPersonByName(String name);
}


