package io.github.gilsomanfredi.springbootapp.sql.client
/**
 * Sqls for handling Customers.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
class ClientSaveSql {

    public static final String insert = """
        insert into client (name, fone, email)
        values (:name, :fone, :email)
    """

    public static final String update = """
        update 
            client
        set
            name = :name,
            fone = :fone,
            email = :email
        where id = :id
    """

    public static final String delete = """
        delete from client where id = :id
    """

}
