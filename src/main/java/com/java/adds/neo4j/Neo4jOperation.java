///**
// *
// */
//package com.java.adds.neo4j;
//
//import com.alibaba.fastjson.JSONArray;
//import org.neo4j.driver.v1.*;
//
///**
// * @author Dell
// * 查询实体属性
// */
////@Component
//public class Neo4jOperation {
//
////	@Autowired
//	public Neo4jConfiguration neo4jConfiguration;
//
//	/**
//	 * 查询病人数据
//	 */
//	public String cypherPatient() {
//		Driver driver = GraphDatabase.driver("bolt://47.94.174.82:7687", AuthTokens.basic("neo4j", "sa"));
//		Session session = driver.session();
//		StatementResult rs = session.run("MATCH (n:Patient) RETURN n.GENDER as gender,n.RELIGION as religion,n.ETHNICITY as ethnicity,n.BIRTHTIME as birthtime");
//		List<Patient> patientList=new ArrayList<Patient>();
//		while(rs.hasNext()) {
//			Record record=rs.next();
//			Patient patient=new Patient();
//			patient.setGender(record.get("gender").asString());
//			patient.setReligion(record.get("religion").asString());
//			patient.setEthnicity(record.get("ethnicity").asString());
//			patient.setBirthtime(record.get("birthtime").asString());
//			patientList.add(patient);
//		}
//		session.close();
//		driver.close();
//		JSONArray listArray=JSONArray.fromObject(patientList);
//		String s=listArray.toString();
//		return s;
//	}
//
//}
