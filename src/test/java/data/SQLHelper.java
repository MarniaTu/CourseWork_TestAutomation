package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner queryRunner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getTransactionStatus() {
        var paymentStatusRequest = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, paymentStatusRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getCreditTransactionStatus() {
        var creditStatusRequest = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, creditStatusRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getTransactionIdFromPaymentEntity() {
        var idRequest = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, idRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getCreditTransactionId() {
        var idRequest = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, idRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getPaymentIdFromOrders() {
        var idRequest = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, idRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getCreditTransactionIdFromOrders() {
        var idRequest = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, idRequest, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void deleteDataFromTables() {
        var connection = getConn();
        queryRunner.execute(connection, "DELETE FROM payment_entity");
        queryRunner.execute(connection, "DELETE FROM credit_request_entity");
        queryRunner.execute(connection, "DELETE FROM order_entity");

    }
}
