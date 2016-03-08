package repositories;

import com.mongodb.DB;
import com.mongodb.*;




public class MongoDBConnection {

//    MongoClient mongoClient = new MongoClient("mongodb://admin:admin@ds019038.mlab.com:19038/mearsdriverdb");

    String textUri = "mongodb://admin:admin@ds019038.mlab.com:19038/mearsdriverdb";
    MongoClientURI uri = new MongoClientURI(textUri);
    MongoClient m = new MongoClient(uri);
}
