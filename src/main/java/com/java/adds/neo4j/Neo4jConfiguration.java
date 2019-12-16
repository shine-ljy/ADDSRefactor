/**
 * 
 */
package com.java.adds.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

/**
 * @author ljy
 * neo4j数据库连接以及配置
 */
public class Neo4jConfiguration {
	public static final Driver driver = GraphDatabase.driver("bolt://localhost:7474", AuthTokens.basic("neo4j", "root"));
}
