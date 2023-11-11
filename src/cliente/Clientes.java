package cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Clientes {
    public static void main(String[] args) {
        Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306/datoscliente";
        String user = "root";
        String pwd = "1234";
//Buenas
        try {
            connection = DriverManager.getConnection(url, user, pwd);

            ArrayList<String> listaCliente = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Menú");
                System.out.println("1. Leer cliente");
                System.out.println("2. Salir");
                System.out.print("Ingrese la opcion:");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        listaCliente = LeerClientes(connection); 
                        for (String cliente : listaCliente) { 
                            System.out.println(cliente);
                        }
                        break;
                    case 2:
                        System.out.println("Saliendo :D.");
                        return;
                    default:
                        System.out.println("INGRESE OPCION CORRECTA.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<String> LeerClientes(Connection connection) throws SQLException {
        ArrayList<String> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                float nit = resultSet.getFloat("nit");
                String clienteInfo = "Nombre: " + nombre + ", Nit: " + nit;
                listaCliente.add(clienteInfo);
            }
        }
        return listaCliente;
    }
}

