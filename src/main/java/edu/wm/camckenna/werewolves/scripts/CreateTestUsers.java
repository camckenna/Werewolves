package edu.wm.camckenna.werewolves.scripts;

import java.net.UnknownHostException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class CreateTestUsers {
	
	
	public final static String COLLECTION_NAME = "USERS";
	
	//@Autowired private static MongoClient mongo;
	@Autowired 
	private static DB db;
	
	public static void main(String[] args) throws UnknownHostException{
		/*mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB(DATABASE_NAME);
		*/

		createUsers();
		
		
	}
	public static void createUsers(){
		DBCollection userColl = db.getCollection(COLLECTION_NAME);
		BasicDBObject doc = new BasicDBObject();
		
		
		doc.put("id", UUID.randomUUID().toString());
		doc.put("firstName", "Joe");
		doc.put("lastName","Nash");
		doc.put("username", "joenash");
		doc.put("hashedPassword", hashedPassword("letmein"));
		doc.put("imageURL", "no"); 
		doc.put("isAdmin", false);
		doc.put("score", 0);
		userColl.insert(doc);
		
		BasicDBObject doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Jane");
		doc2.put("lastName","Nickolas");
		doc2.put("username", "janenathan");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Lee");
		doc2.put("lastName","Walter");
		doc2.put("username", "MEGABEASTGUY");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Desmond");
		doc2.put("lastName","Miles");
		doc2.put("username", "ThatAssassinGuy");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Lucy");
		doc2.put("lastName","Stillman");
		doc2.put("username", "ThatAssassinGirl");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Jimmy");
		doc2.put("lastName","Novack");
		doc2.put("username", "Castiel");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();		
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Walter");
		doc2.put("lastName","White");
		doc2.put("username", "creppyguy");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();	
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Mycroft");
		doc2.put("lastName","Holmes");
		doc2.put("username", "watchingyou");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();	
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "John");
		doc2.put("lastName","Reese");
		doc2.put("username", "Asset");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
		
		doc2 = new BasicDBObject();	
		
		doc2.put("id", UUID.randomUUID().toString());
		doc2.put("firstName", "Harold");
		doc2.put("lastName","Finch");
		doc2.put("username", "Admin");
		doc2.put("hashedPassword", hashedPassword("letmein"));
		doc2.put("imageURL", "no"); 
		doc2.put("isAdmin", false);
		doc2.put("score", 0);
		userColl.insert(doc2);
	}
	
	public static String hashedPassword(String password){

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
		 
		//return password;
	}
}
