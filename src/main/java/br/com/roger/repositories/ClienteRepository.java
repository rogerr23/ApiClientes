package br.com.roger.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.roger.entities.Cliente;
import br.com.roger.factories.ConnectionFactory;

public class ClienteRepository {

	public void create(Cliente cliente) {

		try {

			var connectionFactory = new ConnectionFactory();
			var connection = connectionFactory.getConnection();

			var statement = connection
					.prepareStatement("INSERT INTO cliente(id, nome, email, telefone) VALUES(?,?,?,?)");
			statement.setString(1, cliente.getId().toString());
			statement.setString(2, cliente.getNome());
			statement.setString(3, cliente.getEmail());
			statement.setString(4, cliente.getTelefone());
			statement.execute();

			connection.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void update(Cliente cliente) {

		try {

			var connectionFactory = new ConnectionFactory();
			var connection = connectionFactory.getConnection();

			var statement = connection.prepareStatement("UPDATE cliente SET nome=?, email=?, telefone=? WHERE id=?");
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getEmail());
			statement.setString(3, cliente.getTelefone());
			statement.setString(4, cliente.getId().toString());
			statement.execute();

			connection.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void delete(UUID id) {

		try {

			var connectionFactory = new ConnectionFactory();
			var connection = connectionFactory.getConnection();

			var statement = connection.prepareStatement("DELETE FROM cliente WHERE id=?");
			statement.setString(1, id.toString());
			statement.execute();

			connection.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<Cliente> findAll() {

		try {

			var connectionFactory = new ConnectionFactory();
			var connection = connectionFactory.getConnection();

			var statement = connection.prepareStatement("SELECT * FROM cliente ORDER BY nome");
			var resultSet = statement.executeQuery();

			var lista = new ArrayList<Cliente>();

			while (resultSet.next()) {

				var cliente = new Cliente();

				cliente.setId(UUID.fromString(resultSet.getString("id")));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setTelefone(resultSet.getString("telefone"));
				cliente.setDataCadastro(new Date(resultSet.getTimestamp("datacadastro").getTime()));

				lista.add(cliente);

			}

			connection.close();

			return lista;

		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Cliente findById(UUID id) {

		try {
			var connectionFactory = new ConnectionFactory();
			var connection = connectionFactory.getConnection();

			var statement = connection.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setString(1, id.toString());
			var resultSet = statement.executeQuery();

			Cliente cliente = null;

			if (resultSet.next()) {
				cliente = new Cliente();

				cliente.setId(UUID.fromString(resultSet.getString("id")));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setTelefone(resultSet.getString("telefone"));
				cliente.setDataCadastro(new Date(resultSet.getTimestamp("datacadastro").getTime()));

			}

			connection.close();

			return cliente;

		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
