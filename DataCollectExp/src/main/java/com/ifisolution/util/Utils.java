//package com.ifisolution.util;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.datastax.driver.core.CodecRegistry;
//import com.datastax.driver.core.DataType;
//import com.datastax.driver.core.Row;
//import com.datastax.driver.core.Session;
//import com.datastax.driver.core.TypeCodec;
//import com.ifisolution.indus.dataservice.dao.ContractDAO;
//import com.ifisolution.indus.dataservice.dao.ObjectDAO;
//import com.ifisolution.indus.dataservice.dao.PulseDAO;
//import com.ifisolution.indus.dataservice.dao.SiteDAO;
//import com.ifisolution.indus.dataservice.dao.operation.CassandraOperation;
//import com.ifisolution.indus.dataservice.exception.ConnectionException;
//import com.ifisolution.indus.dataservice.exception.IoTParamException;
//import com.ifisolution.indus.dataservice.model.contract.Contract;
//import com.ifisolution.indus.dataservice.model.object.ObjectIOTParam;
//import com.ifisolution.indus.dataservice.model.pulse.Pulse;
//import com.ifisolution.indus.dataservice.model.site.Site;
//
//@Service
//public class Utils {
//
//    @Autowired
//	protected CassandraOperation cassandraOperation;
//    
//	private static final Logger logger = Logger.getLogger(Utils.class);
//	
//	private static Map<String, String> mapMeteoCostic = new HashMap<String, String>();
//	static {
//		mapMeteoCostic.put("88136001", "88136001");
//		mapMeteoCostic.put("67122001", "67122001");
//		mapMeteoCostic.put("10", "59183001");
//		mapMeteoCostic.put("100", "52269001");
//		mapMeteoCostic.put("110", "52448001");
//		mapMeteoCostic.put("120", "57039001");
//		mapMeteoCostic.put("130", "54405001");
//		mapMeteoCostic.put("140", "67124001");
//		mapMeteoCostic.put("150", "68224006");
//		mapMeteoCostic.put("160", "90010001");
//		mapMeteoCostic.put("170", "70473001");
//		mapMeteoCostic.put("180", "25056001");
//		mapMeteoCostic.put("190", "21473001");
//		mapMeteoCostic.put("20", "62160001");
//		mapMeteoCostic.put("210", "71105001");
//		mapMeteoCostic.put("220", "1089001");
//		mapMeteoCostic.put("230", "69029001");
//		mapMeteoCostic.put("240", "38384001");
//		mapMeteoCostic.put("250", "73329001");
//		mapMeteoCostic.put("260", "73054001");
//		mapMeteoCostic.put("280", "5046001");
//		mapMeteoCostic.put("290", "6116002");
//		mapMeteoCostic.put("30", "80001001");
//		mapMeteoCostic.put("300", "12145001");
//		mapMeteoCostic.put("310", "46127001");
//		mapMeteoCostic.put("320", "43062001");
//		mapMeteoCostic.put("330", "42005001");
//		mapMeteoCostic.put("340", "63113001");
//		mapMeteoCostic.put("350", "3060001");
//		mapMeteoCostic.put("360", "87085006");
//		mapMeteoCostic.put("361", "15014004");
//		mapMeteoCostic.put("370", "18033001");
//		mapMeteoCostic.put("380", "58160001");
//		mapMeteoCostic.put("390", "41097001");
//		mapMeteoCostic.put("040", "59343001");
//		mapMeteoCostic.put("400", "37179001");
//		mapMeteoCostic.put("410", "45055001");
//		mapMeteoCostic.put("411", "36063001");
//		mapMeteoCostic.put("421", "28198001");
//		mapMeteoCostic.put("430", "91027002");
//		mapMeteoCostic.put("440", "75114001");
//		mapMeteoCostic.put("450", "95088001");
//		mapMeteoCostic.put("460", "60639001");
//		mapMeteoCostic.put("461", "77306001");
//		mapMeteoCostic.put("462", "78640001");
//		mapMeteoCostic.put("470", "76116001");
//		mapMeteoCostic.put("480", "76552001");
//		mapMeteoCostic.put("490", "14137001");
//		mapMeteoCostic.put("50", "59343001");
//		mapMeteoCostic.put("500", "61001001");
//		mapMeteoCostic.put("510", "50129001");
//		mapMeteoCostic.put("520", "35228001");
//		mapMeteoCostic.put("530", "29075001");
//		mapMeteoCostic.put("540", "56185001");
//		mapMeteoCostic.put("551", "22278005");
//		mapMeteoCostic.put("560", "35281001");
//		mapMeteoCostic.put("570", "72181001");
//		mapMeteoCostic.put("580", "35281001");
//		mapMeteoCostic.put("590", "44020001");
//		mapMeteoCostic.put("60", "51153001");
//		mapMeteoCostic.put("600", "17300001");
//		mapMeteoCostic.put("610", "86027001");
//		mapMeteoCostic.put("620", "16089001");
//		mapMeteoCostic.put("640", "33281001");
//		mapMeteoCostic.put("650", "33529001");
//		mapMeteoCostic.put("660", "47091001");
//		mapMeteoCostic.put("670", "40192001");
//		mapMeteoCostic.put("680", "64024001");
//		mapMeteoCostic.put("690", "64549001");
//		mapMeteoCostic.put("70", "10030001");
//		mapMeteoCostic.put("700", "9261001");
//		mapMeteoCostic.put("710", "31069001");
//		mapMeteoCostic.put("720", "11069001");
//		mapMeteoCostic.put("730", "66136001");
//		mapMeteoCostic.put("740", "34154001");
//		mapMeteoCostic.put("750", "30189001");
//		mapMeteoCostic.put("760", "26198001");
//		mapMeteoCostic.put("770", "84087001");
//		mapMeteoCostic.put("780", "13054001");
//		mapMeteoCostic.put("790", "83137001");
//		mapMeteoCostic.put("80", "89346001");
//		mapMeteoCostic.put("800", "83118006");
//		mapMeteoCostic.put("810", "6088001");
//		mapMeteoCostic.put("820", "20148001");
//		mapMeteoCostic.put("830", "20004002");
//		mapMeteoCostic.put("73015001", "73015001");
//		mapMeteoCostic.put("73296005", "73296005");
//		mapMeteoCostic.put("73304005", "73304005");
//		mapMeteoCostic.put("33281001", "33281001");
//		mapMeteoCostic.put("17300001", "17300001");
//		mapMeteoCostic.put("31069001", "31069001");
//		mapMeteoCostic.put("34154001", "34154001");
//		mapMeteoCostic.put("66136001", "66136001");
//		mapMeteoCostic.put("30189001", "30189001");
//		mapMeteoCostic.put("64549001", "64549001");
//		mapMeteoCostic.put("55179001", "55179001");
//		mapMeteoCostic.put("SP38400", "SP38400");
//		mapMeteoCostic.put("38538002", "38538002");
//		mapMeteoCostic.put("6029001", "6029001");
//		mapMeteoCostic.put("54526001", "54526001");
//		mapMeteoCostic.put("13055025", "13055025");
//		mapMeteoCostic.put("49015002", "49015002");
//		mapMeteoCostic.put("44020001", "44020001");
//		mapMeteoCostic.put("95288001", "95288001");
//		mapMeteoCostic.put("53094001", "53094001");
//		mapMeteoCostic.put("44103001", "44103001");
//		mapMeteoCostic.put("SP44020001", "44020001_17");
//		mapMeteoCostic.put("SP570", "72181001");
//		mapMeteoCostic.put("74056001", "74056001");
//		mapMeteoCostic.put("SP55179001", "55179001");
//		mapMeteoCostic.put("19275005", "19275005");
//		mapMeteoCostic.put("SP33281001", "33281001_28");
//		mapMeteoCostic.put("SP59343001-16", "59343001_16");
//		mapMeteoCostic.put("SP59343001-20", "59343001_20");
//		mapMeteoCostic.put("28070001", "28070001");
//		mapMeteoCostic.put("89273001", "89273001");
//		mapMeteoCostic.put("73257001", "73257001");
//		mapMeteoCostic.put("12300004", "12300004");
//		mapMeteoCostic.put("SP69029001", "69029001");
//		mapMeteoCostic.put("50209001", "50209001");
//		mapMeteoCostic.put("50129004", "50129004");
//		mapMeteoCostic.put("SP75114001_24", "75114001_24");
//		mapMeteoCostic.put("SP91027002_24", "91027002_24");
//		mapMeteoCostic.put("SP95527001_24", "95527001_24");
//		mapMeteoCostic.put("SP60639001_24", "60639001_24");
//		mapMeteoCostic.put("SP74093001", "74093001");
//		mapMeteoCostic.put("SP71105001_14", "71105001_14");
//		mapMeteoCostic.put("SP71105001_7", "71105001_7");
//		mapMeteoCostic.put("59183001", "59183001");
//		mapMeteoCostic.put("SP71105001_12", "71105001_12");
//		mapMeteoCostic.put("41281001", "41281001");
//		mapMeteoCostic.put("65344001", "65344001");
//		mapMeteoCostic.put("65125001", "65125001");
//		mapMeteoCostic.put("81284001", "81284001");
//		mapMeteoCostic.put("50129001", "50129001");
//		mapMeteoCostic.put("88033002", "88033002");
//		mapMeteoCostic.put("1999", "34154001");
//		mapMeteoCostic.put("SP88136001-17", "88136001_17");
//		mapMeteoCostic.put("SP59343001-17", "59343001_17");
//		mapMeteoCostic.put("72175002", "72175002");
//		mapMeteoCostic.put("37228001", "37228001");
//		mapMeteoCostic.put("53130008", "53130008");
//		mapMeteoCostic.put("57039001", "57039001");
//		mapMeteoCostic.put("SP18033001", "18033001_24");
//		mapMeteoCostic.put("SP38384001", "38384001");
//		mapMeteoCostic.put("87085006", "87085006");
//		mapMeteoCostic.put("73054001", "73054001");
//		mapMeteoCostic.put("73124001", "73124001");
//		mapMeteoCostic.put("73132001", "73132001");
//		mapMeteoCostic.put("73276001", "73276001");
//		mapMeteoCostic.put("73303005", "73303005");
//		mapMeteoCostic.put("74042003", "74042003");
//		mapMeteoCostic.put("73034002", "73034002");
//		mapMeteoCostic.put("19275004", "19275004");
//		mapMeteoCostic.put("73206001", "73206001");
//		mapMeteoCostic.put("67124001", "67124001");
//		mapMeteoCostic.put("68224006", "68224006");
//		mapMeteoCostic.put("SP47091001_22", "47091001_22");
//		mapMeteoCostic.put("SP02320001-27", "02320001_27");
//		mapMeteoCostic.put("1071001", "1071001");
//		mapMeteoCostic.put("SP57039001_10", "57039001_10");
//		mapMeteoCostic.put("SP54526001_21", "54526001_21");
//		mapMeteoCostic.put("68205001", "68205001");
//		mapMeteoCostic.put("73312001", "73312001");
//		mapMeteoCostic.put("92025001", "92025001");
//		mapMeteoCostic.put("13103001", "13103001");
//		mapMeteoCostic.put("7172002", "7172002");
//		mapMeteoCostic.put("90035001", "90035001");
//		mapMeteoCostic.put("19148001", "19148001");
//		mapMeteoCostic.put("83153001", "83153001");
//		mapMeteoCostic.put("8105005", "8105005");
//		mapMeteoCostic.put("68066004", "68205001");
//		mapMeteoCostic.put("44020001_27", "44020001_27");
//		mapMeteoCostic.put("44020001_14", "44020001_14");
//		mapMeteoCostic.put("SP88136001_19", "88136001_19");
//		mapMeteoCostic.put("SP54526001_19", "54526001_19");
//		mapMeteoCostic.put("78621001", "78621001");
//		mapMeteoCostic.put("88271001", "88271001");
//		mapMeteoCostic.put("74119003", "74119003");
//		mapMeteoCostic.put("75116008", "75116008");
//		mapMeteoCostic.put("13005003", "13005003");
//		mapMeteoCostic.put("51183001", "51183001");
//		mapMeteoCostic.put("49020001", "49020001");
//		mapMeteoCostic.put("73011004", "73011004");
//		mapMeteoCostic.put("73132003", "73132003");
//		mapMeteoCostic.put("73144001", "73144001");
//		mapMeteoCostic.put("73255003", "73255003");
//		mapMeteoCostic.put("73171002", "73171002");
//		mapMeteoCostic.put("19146001", "19146001");
//		mapMeteoCostic.put("73329001", "73329001");
//		mapMeteoCostic.put("70447002", "70447002");
//		mapMeteoCostic.put("SP54526001_8", "54526001_8");
//		mapMeteoCostic.put("54582001", "54582001");
//		mapMeteoCostic.put("73181001", "73181001");
//		mapMeteoCostic.put("73257003", "73257003");
//		mapMeteoCostic.put("17300001_28", "17300001_28");
//		mapMeteoCostic.put("1014002", "1014002");
//		mapMeteoCostic.put("74281001", "74281001");
//		mapMeteoCostic.put("74133001", "74133001");
//		mapMeteoCostic.put("16078001_22", "16078001_22");
//		mapMeteoCostic.put("24138004", "24138004");
//		mapMeteoCostic.put("74182001", "74182001");
//
//	}
//	
//	
//	public static Object getRowAsObject(Row row, CodecRegistry codecRegistry, Field[] fields, Object object) {
//		try {
//			Class<CassandraColumn> clazz = CassandraColumn.class;
//			TypeCodec<Map<String, String>> codec = codecRegistry.codecFor(DataType.map(DataType.ascii(), DataType.text()));
//			Field field = null;
//			CassandraColumn cassandraColumn = null;
//			String fieldName;
//			Object value = null;
//			for (int i = 0; i < fields.length; i++) {
//				field = fields[i];
//				if (field.isAnnotationPresent(clazz)) {
//					cassandraColumn = field.getAnnotation(clazz);
//					fieldName = cassandraColumn.name();
//					field.setAccessible(true);
//					if (Map.class.equals(field.getType())) {
//						value = row.get(fieldName, codec);
//						if (value != null && ((Map) value).isEmpty()){
//							value = null;
//						}
//					} else {
//						value = row.get(fieldName, field.getType());
//					}
//					if (value != null){
//						field.set(object, value);
//					}
//				}
//			}
//		} catch (IllegalAccessException e) {
//			logger.error("ERROR WHILE PARSING ROW TO OBJECT", e);
//		}
//		return object;
//	}
//	
//	
//	
//	public static Object getRowAsObject(Row row, Field[] fields, Object object) {
//		try {
//			Class<CassandraColumn> clazz = CassandraColumn.class;
//			Field field = null;
//			CassandraColumn cassandraColumn = null;
//			String fieldName;
//			Object value = null;
//			for (int i = 0; i < fields.length; i++) {
//				field = fields[i];
//				if (field.isAnnotationPresent(clazz)) {
//					cassandraColumn = field.getAnnotation(clazz);
//					fieldName = cassandraColumn.name();
//					field.setAccessible(true);
//					if (Map.class.equals(field.getType())) {
//						value = row.getMap(fieldName, String.class, String.class);
//						if (value != null && ((Map) value).isEmpty()){
//							value = null;
//						}
//					} else {
//						value = row.get(fieldName, field.getType());
//					}
//					if (value != null){
//						field.set(object, value);
//					}
//				}
//			}
//		} catch (IllegalAccessException e) {
//			logger.error("ERROR WHILE PARSING ROW TO OBJECT", e);
//		}
//		return object;
//	}
//	
//	public static Object getRowAsObjectHaveMetadata(Row row, Class T, String metadata) {
//		Object object = null;
//		try {
//			Field[] fields =  T.getDeclaredFields();
//			object = T.newInstance();
//			Class<CassandraColumn> clazz = CassandraColumn.class;
//			Field field = null;
//			CassandraColumn cassandraColumn = null;
//			String fieldName;
//			Object value = null;
//			for (int i = 0; i < fields.length; i++) {
//				field = fields[i];
//				if (field.isAnnotationPresent(clazz)) {
//					cassandraColumn = field.getAnnotation(clazz);
//					fieldName = cassandraColumn.name();
//					if((!metadata.contains(fieldName) && !Constants.META_DATA_ALL.equalsIgnoreCase(metadata))){
//						continue;
//					}
//					field.setAccessible(true);
//					if (Map.class.equals(field.getType())) {
//						value = row.getMap(fieldName, String.class, String.class);
//					} else {
//						value = row.get(fieldName, field.getType());
//					}
//					field.set(object, value);
//				}
//			}
//		} catch (IllegalAccessException | InstantiationException e) {
//			logger.error("ERROR WHILE PARSING ROW TO OBJECT", e);
//		}
//		return object;
//	}
//	
////	public static Object replectRowToObject(Row row, List<String> lstColums, Class<?> classT) {
////		Object obj = null;
////		try {
////			obj = classT.newInstance();
////			java.lang.reflect.Field[] fields = classT.getDeclaredFields();
////			for (String column : lstColums) {
////				String strColumn = column;
////				strColumn = strColumn.toLowerCase().replaceAll(Constants.CHARAC_SHADOW, Constants.CHARAC_EMPTY);
////				for (java.lang.reflect.Field field : fields) {
////					field.setAccessible(true);
////					String strField = field.getName().toLowerCase().replaceAll(Constants.CHARAC_SHADOW, Constants.CHARAC_EMPTY);
////					if (strColumn.equalsIgnoreCase(strField)) {
////						if (field.getType().isAssignableFrom(String.class)) {
////							field.set(obj, row.getString(column));
////							break;
////						} else if (field.getType().isAssignableFrom(Date.class)) {
////							field.set(obj, row.getTimestamp(column));
////							break;
////						} else if (field.getType().isAssignableFrom(Long.class)) {
////							field.set(obj, row.getLong(column));
////							break;
////						} else if (field.getType().isAssignableFrom(Integer.class)) {
////							field.set(obj, row.getInt(column));
////							break;
////						} else if (field.getType().isAssignableFrom(Double.class)) {
////							field.set(obj, row.getDouble(column));
////							break;
////						} else if (field.getType().isAssignableFrom(Map.class)) {
////							field.set(obj, row.getMap(column, String.class, String.class));
////							break;
////						} else if (field.getType().isAssignableFrom(Float.class)) {
////							field.set(obj, row.get(column,Float.class));
////							break;
////						} else if (field.getType().isAssignableFrom(Boolean.class)) {
////							field.set(obj, row.get(column, Boolean.class));
////							break;
////						}
////					}
////					field.setAccessible(false);
////				}
////			}
////		} catch (IllegalArgumentException | IllegalAccessException e) {
////			logger.error("error when replectRowToObject " + e.getMessage());
////		} catch (InstantiationException e) {
////			logger.error("error when replectRowToObject " + e.getMessage());
////		}
////		return obj;
////	}
//	
//	public static List<String> getListString(String lstString) {
//		String[] arrString = lstString.split(Constants.COMMA);
//		List<String> setString = new ArrayList<String>();
//		for (String str : arrString) {
//			setString.add(str.trim());
//		}
//		return setString;
//	}
//
//	public static String replaceCharacter(String cIn, String cRe, String inputStr) {
//		return inputStr.toLowerCase().replaceAll(cIn, cRe);
//	}
//
//	public static Map<String, String> refactorKeyMap(Map<String, String> mapObject) {
//		Map<String, String> mapR = new HashMap<String, String>();
//		Set<String> keyObject = mapObject.keySet();
//		for (String key : keyObject) {
//			String value = mapObject.get(key);
//			String strKey = standardizedKeyField(key);
//			strKey = standardizedKeyField(strKey);
//			mapR.put(strKey, value);
//		}
//		return mapR;
//	}
//	
//	public static String standardizedKeyField(String strStandard) {
//		if (strStandard.contains(Constants.CHARAC_DOT)) {
//			int dotIndex = strStandard.lastIndexOf(Constants.CHARAC_DOT);
//			strStandard = strStandard.substring(dotIndex + 1, strStandard.length());
//		}
//		return strStandard;
//	}
//
////	public static Map<Object, Object> getObjectFromRow(Row row, List<Definition> lstColums) {
////	return generateRowToObject(row, lstColums);
////  }
//	
////	public static String standardizedKeyField(String strStandard) {
////
////		if (strStandard.contains(Constants.CHARAC_DOT)) {
////			int dotIndex = strStandard.lastIndexOf(Constants.CHARAC_DOT);
////			strStandard = strStandard.substring(dotIndex + 1, strStandard.length());
////		}
////		int length = strStandard.length();
////		for (int i = 0; i < length; i++) {
////			int index = strStandard.indexOf(Constants.CHARAC_SHADOW, i);
////			if (index == -1) {
////				break;
////			}
////			char chrac = strStandard.charAt(index + 1);
////			strStandard = strStandard.replaceAll(Constants.CHARAC_SHADOW + chrac, Character.toUpperCase(chrac) + Constants.CHARAC_EMPTY);
////			i = index;
////		}
////		return strStandard;
////	}
//
////	public static Map<Object, Object> generateRowToObject(Row row, List<Definition> lstColums) {
////		Map<Object, Object> mapR = new HashMap<Object, Object>();
////		Object value = null;
////
////		for (Definition definition : lstColums) {
////			Name nameType = definition.getType().getName();
////			String standartKey = standardizedKeyField(definition.getName());
////
////			if (nameType.equals(Name.MAP)) {
////				Map<String, String> mapValue = row.getMap(definition.getName(), String.class, String.class);
////				if (definition.getName().equalsIgnoreCase(Constants.FIELD)) {
////					Map<String, String> mapValueR = refactorKeyMap(mapValue, Constants.CHARAC_EMPTY);
////					mapR.putAll(mapValueR);
////				} else if (definition.getName().equalsIgnoreCase(Constants.REF_PARENT)) {
////					Map<String, String> mapValueR = refactorKeyMap(mapValue, Constants.PREFIX_PARENT + Constants.CHARAC_SHADOW);
////					mapR.putAll(mapValueR);
////				} else if (definition.getName().equalsIgnoreCase(Constants.REF_CHILD)) {
////					Map<String, String> mapValueR = refactorKeyMap(mapValue, Constants.PREFIX_CHILD + Constants.CHARAC_SHADOW);
////					mapR.putAll(mapValueR);
////				} else {
////					Map<String, String> mapValueR = refactorKeyMap(mapValue, Constants.CHARAC_EMPTY);
////					mapR.put((Object) standartKey, (Object) mapValueR);
////				}
////
////			} else {
////				value = row.getObject(definition.getName());
////				mapR.put(standartKey, value);
////			}
////		}
////		return mapR;
////	}
//	
//	public static List<String> getListColums(String metadata, String column){
//		String[] columns = null;
//		if (Constants.META_DATA_ALL.equalsIgnoreCase(metadata)) {
//			columns = column.split(Constants.COMMA);
//		} else {
//			columns = metadata.split(Constants.COMMA);
//		}
//		List<String> lstColumns = new ArrayList<String>();
//		for (String colum : columns) {
//			lstColumns.add(colum.trim());
//		}
//		return lstColumns;
//	}
//	
//	public static String getColumns(String metadata, String column){
//		String columns = null;
//		if (Constants.META_DATA_ALL.equalsIgnoreCase(metadata)) {
//			columns = column;
//		} else {
//			columns = metadata;
//		}
//		return columns;
//	}
//	
//	public static Map<String, Object> parseObjectToMap(Field[] fields, Object object) throws Exception {
//		Map<String, Object> mapResult = new HashMap<String, Object>();
//		for (Field field : fields) {
//			field.setAccessible(true);
//			String fieldName = field.getName();
//			Object value = field.get(object);
//			mapResult.put(fieldName, value);
//		}
//
//		return mapResult;
//
//	}
//	public static boolean validate(String... fields) {
//		for (int i = 0; i < fields.length; i++) {
//			String field = fields[i];
//			if (field == null || "".equals(field)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public static boolean isNotEmpty(Collection<?> c) {
//		if (c != null && !c.isEmpty()) {
//			return true;
//		}
//		return false;
//	}
//	
//	public Map<String, String> getParamAPILap(String partnerId, String siteId, String algorithmName, String fromTime, String toTime) throws ConnectionException, IoTParamException{
//		Map<String, String> params = new HashMap<String, String>();
//		
//		List<Object> siteObject= siteDAO.getSitebySiteId(partnerId, "1", siteId, "ALL");
//		Site siteBean = (Site) siteObject.get(0);
//		String contractId = "";
//		if (siteBean != null) {
//			contractId = siteBean.getRefParent().get("iot_params.contract.contract_id");
//		}
//		Contract contractBean = null;
//		if (contractId != null && !contractId.equals("")) {
//			contractBean = (Contract)contractDAO.getContractContractId(partnerId, contractId, "ALL");
//		}
//		
//		StringBuilder param1Sb = new StringBuilder();
//
//		param1Sb.append("IDEX_" + siteId);
//		param1Sb.append(",");
//
//		String contractTmp = "";
//		if (contractId != null && siteId != null && !contractId.equals(siteId)) {
//			contractTmp = contractId;
//		}
//		param1Sb.append("IDEX_" + contractTmp);
//		param1Sb.append(",");
//
//		String objectType = "UTILITY,EQUIPEMENT,METER,SUPPLY_SITE,SUPPLY_HEAT,SUPPLY_ECS,PROCESS,USE,CIRCUIT,SITE_GROUP,PIQUAGE,DEPART,SENSOR";
//		List<ObjectIOTParam> objectLst = (List<ObjectIOTParam>) objectDAO.getModulesBySite(partnerId, siteId, objectType, "ALL");
//		List<ObjectIOTParam> objectRLst = new ArrayList<ObjectIOTParam>();
//		Set<String> sIdScanned = new HashSet<String>();
//		Date currentDate = new Date();
//		if (objectLst != null && !objectLst.isEmpty()) {
//			for (ObjectIOTParam objectBean : objectLst) {
//				if (validObjectByTime(objectBean, currentDate, true, sIdScanned)) {
//					objectRLst.add(objectBean);
//				}
//			}
//		}
//		for (ObjectIOTParam object : objectRLst) {
//			param1Sb.append("IDEX_" + siteId + "_" + object.getObjectId());
//			param1Sb.append(",");
//		}
//
//		String typeDeSuviSimulation = "";
//		String allumageSimulation = "";
//		Float nbSimulation = null;
//		Float djuSimulation = null;
//		Float qSimulation = null;
//		String suviPCS = "1";
//		String meteoContract = "";
//		String costicContract = "";
//		String meteoSimulation = "";
//		String costicSimulation = "";
//		String startDate = null;
//	    String endDate = null;
//		if (contractBean != null) {
//			if (contractBean.getContractMeteoId() != null) {
//				meteoContract = contractBean.getContractMeteoId();
//				costicContract = mapMeteoCostic.get(contractBean.getContractMeteoId());
//				meteoSimulation = meteoContract;
//				costicSimulation = costicContract;
//			}
//			if (contractBean.getContractNb() != null) {
//				nbSimulation = contractBean.getContractNb();
//			}
//
//			if (contractBean.getContractDju() != null) {
//				djuSimulation = contractBean.getContractDju();
//			}
//			if (contractBean.getqEcs() != null) {
//				qSimulation = contractBean.getqEcs();
//			}
//			if (contractBean.getField() != null) {
//				suviPCS = contractBean.getField().get("type_suivi");
//				typeDeSuviSimulation = contractBean.getField().get("type_suivi");
//				allumageSimulation = contractBean.getField().get("allumage_exclu");
//			}
//		}
//
//		param1Sb.append(meteoContract);
//		param1Sb.append(",");
//		param1Sb.append(costicContract);
//		param1Sb.append(",");
//		param1Sb.append(meteoSimulation);
//		param1Sb.append(",");
//		param1Sb.append(costicSimulation);
//		param1Sb.append(",");
//
//		// Du lieu cho bang pulse
//		for (int i = 0; i < objectRLst.size(); i++) {
//			ObjectIOTParam obj = objectRLst.get(i);
//			if ("METER".equals(obj.getObjectType())) {
//				String objectCode = "";
//				if (obj.getField() != null && obj.getField().containsKey("object_code")) {
//					objectCode = obj.getField().get("object_code");
//				}
//				param1Sb.append(objectCode);
//				if (i != objectRLst.size() - 1) {
//					param1Sb.append(",");
//				}
//			}
//		}
//
//		// String key = userId + "_" + partnerId + "_" + fromTime + "000000";
//		String releveDate = fromTime + "000000";
//		String dateBeforeOfSite = getReleveDateBeforeOfSite(partnerId, siteId, releveDate); // dateBeforeOfSite
//		logger.info("[LAB ACCOR DS] DateBeforeOfSite:" + dateBeforeOfSite);
//		String param2 = fromTime + "000000"; // dateBeforeOfSite
//		if (dateBeforeOfSite != null && !dateBeforeOfSite.isEmpty()) {
//			param2 = dateBeforeOfSite;
//		}
//		Date fromDate = DateUtils.convertDateByFormatLocal(fromTime, "yyyyMMdd");
//		Date toDate = DateUtils.convertDateByFormatLocal(toTime, "yyyyMMdd");
//        startDate = DateUtils.convertDateByFormatNonUTC(fromDate, "yyyy-MM-dd");
//        endDate = DateUtils.convertDateByFormatNonUTC(toDate, "yyyy-MM-dd");
//		String param3 = toTime + "000000";
//
//		String param4 = algorithmName;
//
//		StringBuilder param5 = new StringBuilder();
//
//		param5.append("type_suivi:" + convertTypeDeSuvi(typeDeSuviSimulation) + ",");
//		param5.append("allumage:" + convertAllumage(allumageSimulation) + ",");
//		param5.append("station:" + meteoSimulation + "&" + costicSimulation + ",");
//		param5.append("nb:" + convertToStr(nbSimulation) + ",");
//		param5.append("DJU:" + convertToStr(djuSimulation) + ",");
//		param5.append("q:" + convertToStr(qSimulation) + ",");
//		param5.append("suivi_pcs:" + suviPCS + ",");
//		param5.append("site:" + siteId);
//		param5.append(",startDate:" + startDate);
//        param5.append(",endDate:" + endDate);
//
//        params.put("param1", param1Sb.toString());
//        params.put("param2", param2+","+param3);
//        params.put("param3", param4);
//        params.put("param4", param5.toString());
//		return params;
//		
//	}
//	
//	private static boolean validObjectByTime(ObjectIOTParam bean, Date currentDate, boolean bDelCondition, Set<String> sIdScanned) {
//		boolean result = false;
//		String objectId = bean.getObjectId();
//		if (!sIdScanned.contains(objectId)) {
//			if (bean.getStartDate().compareTo(currentDate) <= 0) {
//				sIdScanned.add(objectId);
//				Map<String, String> field = bean.getField();
//				if (!bDelCondition || (bDelCondition && field != null && "false".equalsIgnoreCase(field.get("is_deleted")))) {
//					result = true;
//				}
//			}
//		}
//		return result;
//	}
//	
//	private String getReleveDateBeforeOfSite(String partnerId, String siteId, String releveDate) {
//        Date dReleveDate = DateUtils.convertDateByFormatLocal(releveDate, "yyyyMMddHHmmss");
////        logger.info("Start getReleveDateBefore: partnerId= {}, siteId= {}, releveDate= {}", partnerId, siteId, releveDate);
//        List<String> siteIdList = new ArrayList<String>();
//        siteIdList.add(siteId);
//        Map<String, List<Row>> objectListMap = objectDAO.getObjectBySite(partnerId, siteIdList);
//        List<Row> objectLst = objectListMap.get(siteId);
//        Session session = cassandraOperation.getSession();
//        CodecRegistry codecRegistry = session.getCluster().getConfiguration().getCodecRegistry();
//        Field[] fields = ObjectIOTParam.class.getDeclaredFields();
//        Date releveDateMax = null;
//        for (Row row : objectLst) {
//        	ObjectIOTParam object = new ObjectIOTParam();
//			object = (ObjectIOTParam) Utils.getRowAsObject(row, codecRegistry, fields, object);
//            Map<String, String> field = object.getField();
//            if ("METER".equals(object.getObjectType())
//                    && field != null
//                    && "true".equalsIgnoreCase(field.get("actif"))
//                    && "false".equalsIgnoreCase(field.get("is_deleted"))) {
//                Pulse pulse = (Pulse) pulseDAO.getLatestPulse(field.get("object_code"), dReleveDate);
//                if (pulse != null && (releveDateMax == null || releveDateMax.before(pulse.getTs()))) {
//                    releveDateMax = pulse.getTs();
//                }
//            }
//        }
//
////        logger.info("End getReleveDateBefore: releveDateMax= {}", releveDateMax);
//        if (releveDateMax != null) {
//            return DateUtils.convertDateByFormatNonUTC(releveDateMax, "yyyyMMddHHmmss");
//        } else {
//            return "";
//        }
//    }
//	
//	private static String convertTypeDeSuvi(String typeDeSuvi) {
//		if ("1".equals(typeDeSuvi)) {
//			return "P1 compteur";
//		} else if ("2".equals(typeDeSuvi)) {
//			return "P1 DJU";
//		} else {
//			return "Autres";
//		}
//	}
//
//	private static String convertAllumage(String allumage) {
//		if ("1".equals(allumage)) {
//			return "Exclue";
//		} else {
//			return "Include";
//		}
//	}
//
//	private static String convertToStr(Float number) {
//		if (number != null) {
//			String str = number.toString();
//			return str.replace(".", "_");
//		}
//		return null;
//	}
//	
//}
