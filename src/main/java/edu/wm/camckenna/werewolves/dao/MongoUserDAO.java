package edu.wm.camckenna.werewolves.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.domain.UserCredentials;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;

public class MongoUserDAO implements IUserDAO, UserDetailsService {

	
	//private DB db;
	
	public final static String COLLECTION_NAME = "USERS";
	public final static String DATABASE_NAME = "WEREWOLVES";
	
	//@Autowired private MongoClient mongo;
	@Autowired private DB db;
	public MongoUserDAO() throws UnknownHostException {
		//mongo = new MongoClient("localhost", 27017);
		//db = mongo.getDB(DATABASE_NAME);
	}
	@Override
	public void createUser(User user) {
		DBCollection userColl = getCollection();
		BasicDBObject doc = new BasicDBObject();
		
		
		doc.put("id", user.getId());
		doc.put("firstName", user.getFirstName());
		doc.put("lastName",user.getLastName());
		doc.put("username", user.getUsername());
		doc.put("hashedPassword", user.getHashedPassword());
		doc.put("imageURL", user.getImageURL()); 
		doc.put("isAdmin", user.isAdmin());
		doc.put("score", user.getScore());
		userColl.insert(doc);

	}

	@Override
	public void updateUser(User user) {
		DBCollection coll = getCollection();
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", user.getId());
		
		BasicDBObject doc = new BasicDBObject();
		doc.put("firstName", user.getFirstName());
		doc.put("lastName",user.getLastName());
		doc.put("username", user.getUsername());
		doc.put("hashedPassword", user.getHashedPassword());
		doc.put("imageURL", user.getImageURL()); 
		doc.put("isAdmin", user.isAdmin());
		doc.put("score", user.getScore());
	 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", doc);
	 
		coll.update(query, updateObj);
		
	}

	@Override
	public void remove(User user) {
		DBCollection coll = getCollection();
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", user.getId());
	 
		coll.remove(searchQuery);		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		DBCollection userColl = getCollection();
		DBCursor cursor = userColl.find();
		
		while(cursor.hasNext()){
			User user = convertFromObject(cursor.next());
			users.add(user);
		}
		return users;
	}

	@Override
	public List<User> getAdmin() {
		List<User> users = new ArrayList<>();
		DBCollection userColl = getCollection();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isAdmin", true);
		DBCursor cursor = userColl.find(searchQuery);
		
		while(cursor.hasNext()){
			User user = convertFromObject(cursor.next());
			users.add(user);
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersInOrderofScore() {
		List<User> users = new ArrayList<>();
		DBCollection userColl = getCollection();
		DBCursor cursor = userColl.find();
		
		while(cursor.hasNext()){
			User user = convertFromObject(cursor.next());
			users.add(user);
		}
		Collections.sort(users);
		return users;
	}

	@Override
	public User getUserById(String id) throws NoUserFoundException, MultipleUsersWithSameIDException{
		DBCollection userColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);
		DBCursor cursor = userColl.find(searchQuery);
		
		if(cursor.count() < 1){
			throw new NoUserFoundException(id);
		}
		if(cursor.count() > 1){
			throw new MultipleUsersWithSameIDException(id, cursor.count());
		}
		
		DBObject obj = cursor.next();
		return convertFromObject(obj);	
	}

	@Override
	public User getUserByUsername(String username) throws NoUserFoundException,
			MultipleUsersWithSameIDException {
		DBCollection coll = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", username);
		DBCursor cursor = coll.find(searchQuery);
		
		if(cursor.count() < 1){
			throw new NoUserFoundException(username);
		}
		if(cursor.count() > 1){
			throw new MultipleUsersWithSameIDException(username, cursor.count());
		}
		
		DBObject obj = cursor.next();
		return convertFromObject(obj);	
	}
	@Override
	public boolean equals(User u1, User u2) {
		
		return u1.getId().equals(u2.getId())
				&& u1.getFirstName().equals(u2.getFirstName())
				&& u1.getLastName().equals(u2.getLastName())
				&& u1.getUsername().equals(u2.getUsername())
				&& u1.getHashedPassword().equals(u2.getHashedPassword())
				&& u1.isAdmin() == u2.isAdmin()
				&& u1.getScore()==u2.getScore();
	}
	
	private DBCollection getCollection() {
		return db.getCollection(COLLECTION_NAME);
	}
	protected User convertFromObject(DBObject obj){
		User user = new User();
		
		user.setId((String)obj.get("id"));
		user.setFirstName((String)obj.get("firstName"));
		user.setLastName((String)obj.get("lastName"));
		user.setUsername((String)obj.get("username"));
		user.setHashedPassword((String)obj.get("hashedPassword"));
		user.setImageURL((String)obj.get("imageURL"));
		user.setScore((int)obj.get("score"));
		user.setAdmin((boolean)obj.get("isAdmin"));
				
		return user;
	}
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	    User user = null;
		try {
			user = getUserByUsername(username);
		} catch (NoUserFoundException | MultipleUsersWithSameIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    if (user == null) { 
	      throw new UsernameNotFoundException("Invalid username/password."); 
	    } 
	    Collection<? extends GrantedAuthority> authorities = 
	      createAuthorities(user); 
	    System.out.println("I am loading a user now: " + username);
	    System.out.println("Auth:" + authorities.toString());
	    System.out.println("Password:" + user.getHashedPassword());
	    
	    return new UserCredentials(user.getUsername(), user.getHashedPassword(), 
	      authorities); 
	}

	private Collection<? extends GrantedAuthority> createAuthorities(User user){
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		if(!user.isAdmin()){
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else if(user.isAdmin()){
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else{}
		return roles;
	}
}
