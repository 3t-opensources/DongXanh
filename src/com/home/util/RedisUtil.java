package com.home.util;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	private Jedis jedis;
	
	public RedisUtil(){
	}
	
	public RedisUtil(String host, int port, String auth_pass){
		this.connect(host, port, auth_pass);
	}
	
	public String set(String key, String value){
		return this.jedis.set(key, value);
	}
	
	public String get(String key){
		return this.jedis.get(key);
	}
	
	public void close(){
		jedis.close();
	}
	
	public String resetAllValues(){
		return jedis.flushAll();
	}
	
	public void connect(String host, int port, String auth_pass){
		try {
			jedis = new Jedis(host, port);
			if(auth_pass != null && auth_pass.length() > 0){
				jedis.auth(auth_pass);	
			}
			System.out.println("Connection to server sucessfully"); 
			//check whether server is running or not 
			System.out.println("Server is running: "+jedis.ping());
		} catch (Exception e) {
			//e.printStackTrace();
		} 
	}
	
	public boolean isConnected(){
		try {
			return jedis.isConnected();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) { 
		//Connecting to Redis server on localhost 
		Jedis jedis = new Jedis("nv.dongxanhvn.com", 7379);
		//jedis.auth("0d6a9242c11b99ddadcfb6e86a850b6ba487530a");
		System.out.println("Connection to server sucessfully"); 
		//check whether server is running or not 
		System.out.println("Server is running: "+jedis.ping()); 

		//set the data in redis string 
		jedis.set("TTTOAN_TEST", "Redis tutorial"); 
		// Get the stored data and print it 
		System.out.println("Stored string in redis:: "+ jedis.get("TTTOAN_TEST")); 

		// store data in redis list
		jedis.rpush("TTTOAN_TEST_KEY_A", "Redis");
		jedis.rpush("TTTOAN_TEST_KEY_A", "Mongodb");
		jedis.rpush("TTTOAN_TEST_KEY_A", "Mysql");
		// Get the stored data and print it
		List<String> list = jedis.lrange("TTTOAN_TEST_KEY_A", 0, -1);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}


		System.out.println("_____________GET ALL KEYS_______________");
		// Get the stored data and print it 
		Set<String> set = jedis.keys("*"); 

		for (String s : set) {
			System.out.println(s);
			/**
			 * Delete
			 */
			//jedis.del(s);
		}
		jedis.flushAll();
		
		jedis.close();
	} 
}
