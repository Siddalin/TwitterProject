package com.Adbms.Tweets;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class selectionClass {

	    public static void main(String[] args) {

	        try {

	            // Connect to mongodb
	            MongoClient mongo = new MongoClient("localhost", 27011);

	            // get database
	            // if database doesn't exists, mongodb will create it for you
	            DB db = mongo.getDB("Twitter");

	            // get collection
	            
	            // if collection doesn't exists, mongodb will create it for you
	            DBCollection collection = db.getCollection("tweets");

	            System.out.println("tweets : "+collection.getCount());
	            
	            System.out.println("Printed All tweets in database: ");
	            //------------------------------------
	            // get all document
	           
	            AggregationOutput output = collection.aggregate(
	                    new BasicDBObject("$group",
	                            new BasicDBObject("_id", "$location ")
	                                    .append("count", new BasicDBObject("$sum", 1))));

	            for (final DBObject result: output.results())
	            {
	            	System.out.println(result);
	            }
	           
	           /* DBCursor cursor = collection.find();
	            try {
	                while(cursor.hasNext()) {
	                    System.out.println(cursor.next());
	                }
	            } finally {
	                cursor.close();
	            }*/
	 
	            
	        } catch (MongoException | UnknownHostException e) {
	            e.printStackTrace();
	        }

	    }

}
