package by.bntu.fitr.isats.quiz;

import by.bntu.fitr.isats.quiz.entity.game.Lobby;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class LobbyPool {

    private static final LobbyPool INSTANCE = new LobbyPool();
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final Lock READ_LOCK = LOCK.readLock();
    private static final Lock WRITE_LOCK = LOCK.writeLock();

    private static final int FIRST_LOBBY_ID = 0;

    private List<Lobby> lobbies = new ArrayList<>();

    private LobbyPool() {
        // private constructor
    }

    public static LobbyPool getInstance() {
        return INSTANCE;
    }

    public Optional<Lobby> getById(int id) {
        try {
            READ_LOCK.lock();
            return lobbies.stream()
                    .filter(l -> l.getId() == id)
                    .findFirst();
        } finally {
            READ_LOCK.unlock();
        }
    }

    public void addLobby(Lobby lobby) {
        try {
            WRITE_LOCK.lock();
            if (isNotInPool(lobby)) {
                int id = generateId();
                lobby.setId(id);
                lobbies.add(lobby);
            }
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public List<Integer> getAllIds() {
        try {
            READ_LOCK.lock();
            return lobbies.stream()
                    .map(Lobby::getId)
                    .collect(Collectors.toList());
        } finally {
            READ_LOCK.unlock();
        }
    }

    private int generateId() {
        return lobbies.stream()
                .mapToInt(l -> l.getId() + 1)
                .max()
                .orElse(FIRST_LOBBY_ID);
    }

    private boolean isNotInPool(Lobby lobby) {
        return lobbies.stream()
                .noneMatch(l -> l.getId().equals(lobby.getId()));
    }

}
