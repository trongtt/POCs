package t3.pocs.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import org.junit.Test;

import java.net.UnknownHostException;
import java.util.Set;

public class TestMongoClient {

    @Test
    public void test() throws UnknownHostException {


        // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
        // if it's a member of a replica set:
        MongoClient mongoClient = new MongoClient();
        // or
//        MongoClient mongoClient = new MongoClient( "localhost" );
        // or
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
//        MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
//                                              new ServerAddress("localhost", 27018),
//                                              new ServerAddress("localhost", 27019)));

        DB db = mongoClient.getDB( "mydb" );

        Set<String> colls = db.getCollectionNames();

        for (String s : colls) {
            System.out.println(s);
        }
    }

}
