import com.esliceu.PoolConnections;

import java.sql.Connection;

public class Pool {

    Pool(Connection con){
        PoolConnections poolCon = new PoolConnections();
        poolCon.addConnection(con);

    }

}
