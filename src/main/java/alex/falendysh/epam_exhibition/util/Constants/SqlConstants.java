package alex.falendysh.epam_exhibition.util.Constants;

public class SqlConstants {
    /**
     * SQL CONSTANTS, operated into database
     *
     */

    public static final String SELECT_USER_BY_ID =
            "SELECT id, email, password, first_name, last_name, phone, role " +
                    "FROM exhibition_users WHERE id=?";
    public static final String SELECT_USER_BY_EMAIL =
            "SELECT id, email, password, first_name, last_name, phone, role " +
                    "FROM exhibition_users WHERE email=?";
    public static final String SELECT_ALL_USERS =
            "SELECT id, email, password, first_name, last_name, phone, role " +
                    "FROM exhibition_users";
    public static final String INSERT_INTO_EXHIBITION_USERS = "INSERT INTO exhibition_users (id, email, password, first_name, last_name, phone, role)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_USER = "INSERT INTO exhibition_users " +
            "(email, password, first_name, last_name, phone, role) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE exhibition_users " +
            "SET email=?, password=?, first_name=?, last_name=?, phone=?, role=? WHERE id=?";
    public static final String DELETE_USER_SQL = "DELETE FROM exhibition_users WHERE id=?";


    public static final String SELECT_TICKET_BY_ID =
            "SELECT tickets.id, visit_date, order_date, ticket_type, ticket_price, is_paid," +
                    "user_id, u.email, u.password, u.first_name, u.last_name, u.phone, u.role, " +
                    "exhibition_id, e.name exhibition_name, e.description, e.begin_date, e.end_date, e.full_ticket_price, " +
                    "hall.id hall_id, hall.name hall_name, hall.allowable_number_of_visitors_per_day " +
                    "FROM tickets " +
                    "join exhibition_users u ON tickets.user_id = u.id " +
                    "join exhibitions e on tickets.exhibition_id = e.id " +
                    "join exhibition_halls hall on e.exhibition_hall_id = hall.id where tickets.id=?";
    public static final String SELECT_ALL_USER_TICKETS_BY_USER_ID =
            "SELECT tickets.id, visit_date, order_date, ticket_type, ticket_price, is_paid," +
                    "user_id, u.email, u.password, u.first_name, u.last_name, u.phone, u.role, " +
                    "exhibition_id, e.name exhibition_name, e.description, e.begin_date, e.end_date, e.full_ticket_price, " +
                    "hall.id hall_id, hall.name hall_name, hall.allowable_number_of_visitors_per_day " +
                    "FROM tickets " +
                    "join exhibition_users u ON tickets.user_id = u.id " +
                    "join exhibitions e on tickets.exhibition_id = e.id " +
                    "join exhibition_halls hall on e.exhibition_hall_id = hall.id " +
                    "where user_id=?" +
                    "order by id";
    public static final String SELECT_ALL_TICKETS =
            "SELECT tickets.id, visit_date, order_date, ticket_type, ticket_price, is_paid," +
                    "user_id, u.email, u.password, u.first_name, u.last_name, u.phone, u.role, " +
                    "exhibition_id, e.name exhibition_name, e.description, e.begin_date, e.end_date, e.full_ticket_price, " +
                    "hall.id hall_id, hall.name hall_name, hall.allowable_number_of_visitors_per_day " +
                    "FROM tickets " +
                    "join exhibition_users u ON tickets.user_id = u.id " +
                    "join exhibitions e on tickets.exhibition_id = e.id " +
                    "join exhibition_halls hall on e.exhibition_hall_id = hall.id " +
                    "order by id";
    public static final String COUNT_TICKETS_FOR_VISIT_DATE = "SELECT COUNT(id) FROM tickets WHERE visit_date=?";
    public static final String INSERT_TICKET = "INSERT INTO tickets " +
            "(visit_date, order_date, ticket_type, ticket_price, is_paid, " +
            "user_id, exhibition_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_TICKET = "UPDATE tickets " +
            "SET visit_date=?, order_date=?, ticket_type=?, ticket_price=?, is_paid=?, " +
            "user_id=?, exhibition_id=? WHERE id=?";
    public static final String DELETE_TICKET = "DELETE FROM tickets WHERE id=?";

    public static final String SELECT_EXHIBITION_BY_ID =
            "SELECT exhibitions.id, exhibitions.name, description, begin_date, end_date, " +
                    "full_ticket_price, exhibition_hall_id, " +
                    "exhibition_halls.name, exhibition_halls.allowable_number_of_visitors_per_day " +
                    "FROM exhibitions JOIN exhibition_halls " +
                    "ON exhibitions.exhibition_hall_id=exhibition_halls.id where exhibitions.id=?";
    public static final String SELECT_EXHIBITION_BY_NAME =
            "SELECT exhibitions.id, exhibitions.name, description, begin_date, end_date, " +
                    "full_ticket_price, exhibition_hall_id, " +
                    "exhibition_halls.name, exhibition_halls.allowable_number_of_visitors_per_day " +
                    "FROM exhibitions JOIN exhibition_halls " +
                    "ON exhibitions.exhibition_hall_id=exhibition_halls.id where exhibitions.name=?";
    public static final String SELECT_EXHIBITIONS_BY_HALL_ID =
            "SELECT exhibitions.id, exhibitions.name, description, begin_date, end_date, " +
                    "full_ticket_price, exhibition_hall_id, " +
                    "exhibition_halls.name, exhibition_halls.allowable_number_of_visitors_per_day " +
                    "FROM exhibitions JOIN exhibition_halls " +
                    "ON exhibitions.exhibition_hall_id=exhibition_halls.id where exhibition_hall_id=?";
    public static final String SELECT_ALL_EXHIBITIONS =
            "SELECT exhibitions.id, exhibitions.name, description, begin_date, end_date, " +
                    "full_ticket_price, exhibition_hall_id, " +
                    "exhibition_halls.name, exhibition_halls.allowable_number_of_visitors_per_day " +
                    "FROM exhibitions JOIN exhibition_halls " +
                    "ON exhibitions.exhibition_hall_id=exhibition_halls.id";
    public static final String INSERT_EXHIBITION = "INSERT INTO exhibitions " +
            "(name, description, begin_date, end_date, " +
            "full_ticket_price, exhibition_hall_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_EXHIBITION = "UPDATE exhibitions " +
            "SET name=?, description=?, begin_date=?, end_date=?, " +
            "full_ticket_price=?, exhibition_hall_id=? WHERE id=?";
    public static final String DELETE_EXHIBITION = "DELETE FROM exhibitions WHERE id=?";

    public static final String SELECT_EXHIBITION_HALL_BY_ID =
            "SELECT id, name,capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day FROM exhibition_halls WHERE id=?";
    public static final String SELECT_EXHIBITION_HALL_BY_NAME =
            "SELECT id, name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day FROM exhibition_halls WHERE name=?";
    public static final String SELECT_ALL_EXHIBITION_HALLS =
            "SELECT id, name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day FROM exhibition_halls";
    public static final String INSERT_EXHIBITION_HALL = "INSERT INTO exhibition_halls " +
            "(name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day) VALUES (?, ?, ?)";
    public static final String UPDATE_EXHIBITION_HALL = "UPDATE exhibition_halls " +
            "SET name=?, capacity_of_paintings_on_the_wall=?, allowable_number_of_visitors_per_day=? WHERE id=?";
    public static final String DELETE_EXHIBITION_HALL = "DELETE FROM exhibition_halls WHERE id=?";
}
