package service;

import dao.ClientDao;
import entity.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private static ClientService INSTANCE = null;
    private static Object LOCK = new Object();

    private ClientService(){}

    public static ClientService getInstance() {
        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null) {
                    INSTANCE = new ClientService();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Client> save(Client client) {
        return ClientDao.getInstance().save(client);
    }

    public Optional<Client> getClientByEmail(String email) {
        return ClientDao.getInstance().getByEmail(email);
    }

    public Optional<Client> getClientByID(long id) {
        return ClientDao.getInstance().getByID(id);
    }

    public List<Client> getAll() {
        return ClientDao.getInstance().getAll();
    }

    public Optional<Client> getByEmailAndPass(String email, String pass) {
        return ClientDao.getInstance().getByEmailAndPass(email, pass);
    }
}