package server.testing;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import server.database.tables.Login;
import server.database.tables.User;



class DBConf{
    private static DBConf instance;

    public static DBConf getInstance() {
        if(instance == null){
            instance = new DBConf();
        }
        return instance;
    }

    private static SessionFactory factory;

    public DBConf(){setup();}

    private void setup(){
        Configuration configuration = new Configuration();
        configuration.configure();

        factory = configuration.buildSessionFactory();
    }

    public Session getSession(){return factory.openSession();}
}
