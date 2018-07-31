package com.ifisolution.mapper;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifisolution.model.Emp;
import com.ifisolution.model.MapperEnum;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

@Component
public class EmpMapper extends CustomMapper<HashMap<Object, Object>, Emp>  {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Override
	public void mapAtoB(HashMap<Object, Object> src, Emp des, MappingContext context) {
//		des.setEmpName(OBJECT_MAPPER.convertValue(src.get(MapperEnum.EMP_NAME.get()),String.class));
		des.setEmpName("Emp's Name is secured");
		des.setDateOfBirth(OBJECT_MAPPER.convertValue(src.get(MapperEnum.DATE_OF_BIRTH.get()), Date.class));
		des.setGender(OBJECT_MAPPER.convertValue(src.get(MapperEnum.GENDER.get()), Boolean.class));
		des.setTeamId(OBJECT_MAPPER.convertValue(src.get(MapperEnum.TEAM.get()), String.class));
	}

	@Override
	public void mapBtoA(Emp src, HashMap<Object, Object> desc, MappingContext context) {
		desc.put(MapperEnum.EMP_NAME.get(),src.getEmpName());
		desc.put(MapperEnum.DATE_OF_BIRTH.get(),src.getDateOfBirth());
		desc.put(MapperEnum.GENDER.get(), src.getGender());
		desc.put(MapperEnum.TEAM.get(), src.getTeamId());
		desc.put("Time Now", new Date());
		desc.put("Company", "IFI SOLUTION");
	}
	
}
