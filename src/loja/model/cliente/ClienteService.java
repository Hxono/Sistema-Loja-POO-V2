package loja.model.cliente;

import java.util.ArrayList;
import java.util.List;


public class ClienteService {
        private List<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public Cliente buscarPorId(String id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
              
    }

