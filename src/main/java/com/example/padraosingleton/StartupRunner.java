package com.example.padraosingleton;

import com.example.padraosingleton.dao.FuncionarioDAO;
import com.example.padraosingleton.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Override
    public void run(String... args) throws Exception {
        // Criando e salvando 10 funcionários diferentes
        Funcionario funcionario1 = new Funcionario(1, "Carlos Silva", "Rua A, 123");
        Funcionario funcionario2 = new Funcionario(2, "Maria Oliveira", "Rua B, 456");
        Funcionario funcionario3 = new Funcionario(3, "João Pereira", "Rua C, 789");
        Funcionario funcionario4 = new Funcionario(4, "Ana Souza", "Rua D, 101");
        Funcionario funcionario5 = new Funcionario(5, "Pedro Lima", "Rua E, 202");
        Funcionario funcionario6 = new Funcionario(6, "Julia Alves", "Rua F, 303");
        Funcionario funcionario7 = new Funcionario(7, "Ricardo Santos", "Rua G, 404");
        Funcionario funcionario8 = new Funcionario(8, "Fernanda Costa", "Rua H, 505");
        Funcionario funcionario9 = new Funcionario(9, "Paulo Rocha", "Rua I, 606");
        Funcionario funcionario10 = new Funcionario(10, "Laura Martins", "Rua J, 707");

        funcionarioDAO.save(funcionario1);
        funcionarioDAO.save(funcionario2);
        funcionarioDAO.save(funcionario3);
        funcionarioDAO.save(funcionario4);
        funcionarioDAO.save(funcionario5);
        funcionarioDAO.save(funcionario6);
        funcionarioDAO.save(funcionario7);
        funcionarioDAO.save(funcionario8);
        funcionarioDAO.save(funcionario9);
        funcionarioDAO.save(funcionario10);

        // Recuperando todos os funcionários
        System.out.println("Lista de Funcionários:");
        for (Funcionario f : funcionarioDAO.getAll()) {
            System.out.println(f);
        }

        // Atualizando o nome de um funcionário (Exemplo: Alterando o nome do funcionário1)
        String[] params = {"Carlos Souza", "Rua A, 123"};
        funcionarioDAO.update(funcionario1, params);

        // Recuperando funcionário por ID (Exemplo: ID 1)
        Funcionario funcionarioRecuperado = funcionarioDAO.get(1);
        System.out.println("Funcionário Recuperado: " + funcionarioRecuperado);

        // Excluindo um funcionário (Exemplo: Excluindo o funcionário1)
        funcionarioDAO.delete(funcionario1);

        // Recuperando todos os funcionários após a exclusão
        System.out.println("Lista de Funcionários após exclusão:");
        for (Funcionario f : funcionarioDAO.getAll()) {
            System.out.println(f);
        }
    }
}
