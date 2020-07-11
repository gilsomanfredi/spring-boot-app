package io.github.gilsomanfredi.springbootapp.sql.client
/**
 * Sqls for select Clients.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
class ClientSelectSql {

    public static final String find_all = """
        select id, name, fone, email from public.client
    """

    public static final String find_by_id = """
        select id, name, fone, email from client where id = :id
    """

    public static final String exists = """
        select count(*) from client where id = :id
    """

}
